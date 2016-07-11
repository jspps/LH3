package com.learnhall.db.internal;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.apache.commons.logging.*;

import com.bowlong.sql.*;
import com.bowlong.concurrent.async.*;
import static com.bowlong.sql.CacheModel.*;

import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.entity.*;

import static com.learnhall.db.bean.Msg.Col;

//learnhall3_design - msg
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class MsgInternal extends InternalSupport{
    static Log log = LogFactory.getLog(MsgInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public MsgInternal(){}

    public static MsgDAO DAO(){
        return MsgEntity.MsgDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Msg[MAX];
    }
    private static Msg[] FIXED = new Msg[MAX];
    public static final Map<Integer, Msg> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTarget = newSortedMap();

    private static final List<Msg> fixedCache = newList();

    public static void put(Msg msg, boolean force){
        if(msg == null || msg.id <= 0) return ;

        int id = msg.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(msg))
                	FIXED[id - 1] = msg;
                if (!fixedCache.contains(msg))
                	fixedCache.add(msg);
            }
        } else {
            vars.put(id, msg);
        }

        { // 单-非唯一索引 remove old index target
          Object ov = msg.oldVal(Col.target);
          if(ov != null) {
              String _val = (String) ov;
              Set m1 = varsByTarget.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByTarget.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String target = msg.getTarget();
            Set m1 = varsByTarget.get(target);
            if(m1 == null){
                m1 = newSortedSet();
                varsByTarget.put(target, m1);
            }
            m1.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTarget.clear();
        FIXED = new Msg[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(MsgDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(MsgDAO DAO, String TABLENAME2){
        if( cacheModel == NO_CACHE ){
            return DAO.count(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            return fixedCache.size();
        } else {  // FULL_CACHE || FULL_MEMORY
            return vars.size();
        }
    }

    public static void relocate(String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static void relocate(MsgDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        MsgDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(MsgDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        MsgDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(MsgDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        MsgDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(MsgDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        MsgDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(MsgDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(MsgDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        MsgDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(MsgDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(MsgDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        MsgDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(MsgDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Msg[maxId(DAO)];

        List<Msg> msgs = DAO.selectAll();
        for (Msg msg : msgs) {
            msg.reset();
            put(msg, true);
        }
    }

    public static Map toMap(Msg msg){
        return msg.toMap();
    }

    public static List<Map> toMap(List<Msg> msgs){
        List<Map> ret = new Vector<Map>();
        for (Msg msg : msgs){
            Map e = msg.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Msg> sortZh(List<Msg> msgs, final String key) {
        Collections.sort(msgs, new Comparator<Msg>() {
            public int compare(Msg o1, Msg o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return msgs;
    }

    public static List<Msg> sort(List<Msg> msgs, final String key) {
        Collections.sort(msgs, new Comparator<Msg>() {
            public int compare(Msg o1, Msg o2) {
                return o1.compareTo(o2, key);
            }
        });
        return msgs;
    }

    public static List<Msg> sort(List<Msg> msgs){
        Collections.sort(msgs, new Comparator<Msg>(){
            public int compare(Msg o1, Msg o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return msgs;
    }

    public static List<Msg> sortReverse(List<Msg> msgs){
        Collections.sort(msgs, new Comparator<Msg>(){
            public int compare(Msg o1, Msg o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return msgs;
    }

    public static List<Msg> sortTarget(List<Msg> msgs){
        Collections.sort(msgs, new Comparator<Msg>(){
            public int compare(Msg o1, Msg o2) {
                Object v1 = o1.getTarget();
                Object v2 = o2.getTarget();
                return compareTo(v1, v2);
            }
        });
        return msgs;
    }

    public static List<Msg> sortTargetRo(List<Msg> msgs){
        Collections.sort(msgs, new Comparator<Msg>(){
            public int compare(Msg o1, Msg o2) {
                Object v1 = o1.getTarget();
                Object v2 = o2.getTarget();
                return 0 - compareTo(v1, v2);
            };
        });
        return msgs;
    }

    public static Msg insert(Msg msg) {
        MsgDAO DAO = DAO();
        return insert(DAO, msg, DAO.TABLENAME);
    }

    public static Msg insert(MsgDAO DAO, Msg msg) {
        return insert(DAO, msg, DAO.TABLENAME);
    }

    public static Msg insert(Msg msg, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return insert(DAO, msg, TABLENAME2);
    }

    public static Msg insert(MsgDAO DAO, Msg msg, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(msg, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        msg.id = n;
        if(cacheModel != NO_CACHE) put(msg, true);

        return msg;
    }

    public static Msg asyncInsert(Msg msg) {
        MsgDAO DAO = DAO();
        return asyncInsert(DAO, msg, DAO.TABLENAME);
    }
    public static Msg asyncInsert(MsgDAO DAO, Msg msg) {
        return asyncInsert(DAO, msg, DAO.TABLENAME);
    }
    public static Msg asyncInsert(Msg msg, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return asyncInsert(DAO, msg, TABLENAME2);
    }
    public static Msg asyncInsert(MsgDAO DAO, Msg msg, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(msg, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        msg.id = n;
        if(cacheModel != NO_CACHE) put(msg, true);
        return msg;
    }
    public static Msg insert2(Msg msg) {
        MsgDAO DAO = DAO();
        return insert2(DAO, msg, DAO.TABLENAME);
    }

    public static Msg insert2(MsgDAO DAO, Msg msg) {
        return insert2(DAO, msg, DAO.TABLENAME);
    }

    public static Msg insert2(Msg msg, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return insert2(DAO, msg, TABLENAME2);
    }

    public static Msg insert2(MsgDAO DAO, Msg msg, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(msg, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        msg.id = n;
        if(cacheModel != NO_CACHE) put(msg, true);

        return msg;
    }

    public static Msg asyncInsert2(Msg msg) {
        MsgDAO DAO = DAO();
        return asyncInsert2(DAO, msg, DAO.TABLENAME);
    }
    public static Msg asyncInsert2(MsgDAO DAO, Msg msg) {
        return asyncInsert2(DAO, msg, DAO.TABLENAME);
    }
    public static Msg asyncInsert2(Msg msg, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return asyncInsert2(DAO, msg, TABLENAME2);
    }
    public static Msg asyncInsert2(MsgDAO DAO, Msg msg, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        msg.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(msg, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(msg, true);
        return msg;
    }
    public static int[] insert(List<Msg> msgs) {
        MsgDAO DAO = DAO();
        return insert(DAO, msgs, DAO.TABLENAME);
    }

    public static int[] insert(MsgDAO DAO, List<Msg> msgs) {
        return insert(DAO, msgs, DAO.TABLENAME);
    }

    public static int[] insert(List<Msg> msgs, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return insert(DAO, msgs, TABLENAME2);
    }

    public static int[] insert(MsgDAO DAO, List<Msg> msgs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(msgs, TABLENAME2);
            int n = 0;
            for(Msg msg : msgs){
                msg.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[msgs.size()];
        int n = 0;
        for(Msg msg : msgs){
            msg = insert(DAO, msg, TABLENAME2);
            ret[n++] = (msg == null) ? 0 : (int)msg.id;
        }
        return ret;
    }

    public static int delete(Msg msg) {
        int id = msg.id;
        MsgDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        MsgDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(MsgDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(MsgDAO DAO, int id, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(id, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
        return n;
    }

    public static void asyncDelete(Msg msg) {
        int id = msg.id;
        MsgDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        MsgDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(MsgDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        MsgDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(MsgDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        MsgDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(MsgDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        MsgDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(MsgDAO DAO, int[] ids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(ids, TABLENAME2);
        }
        int[] ret = new int[ids.length];
        int n = 0;
        for(int id : ids){
            ret[n++] = delete(DAO, id, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        MsgDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, MsgDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final MsgDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Msg> beans) {
        MsgDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Msg> beans, MsgDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Msg> beans, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Msg> beans, final MsgDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Msg msg : beans){
                int id = msg.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Msg> getAll() {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Msg> getAll(MsgDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Msg> getAll(String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Msg> getAll(final MsgDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Msg> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Msg> getNoSortAll() {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Msg> getNoSortAll(MsgDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Msg> getNoSortAll(String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Msg> getNoSortAll(final MsgDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Msg> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Msg> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Msg msg = FIXED[i];
                if (msg != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Msg> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Msg> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(MsgDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final MsgDAO DAO, final String TABLENAME2) {
        if ( cacheModel == NO_CACHE) { 
            return DAO.selectPKs(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Integer> result = newList();
            result.addAll(memoryKeys());
            return result;
        } else {
            List<Integer> result = newList();
            result.addAll(vars.keySet());
            return result;
        }
    }

    public static List<Map> getInIndex() {
        MsgDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(MsgDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        MsgDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final MsgDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Msg> getIn(List<Integer> keys) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Msg> getIn(List<Integer> keys, MsgDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Msg> getIn(List<Integer> keys, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Msg> getIn(final List<Integer> keys, final MsgDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Msg> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Msg> getNoSortIn(List<Integer> keys) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Msg> getNoSortIn(List<Integer> keys, MsgDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Msg> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Msg> getNoSortIn(final List<Integer> keys, final MsgDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Msg> result = newList();
            for (int key : keys) {
                Msg msg = getByKeyFromMemory(key);
                if( msg == null ) continue;
                result.add(msg);
            }
            return result;
        }
    }

    public static List<Msg> getLast(int num) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Msg> getLast(MsgDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Msg> getLast(int num, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Msg> getLast(final MsgDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Msg> result = newList();
            List<Msg> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Msg last() {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Msg last(MsgDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Msg last(String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Msg last(final MsgDAO DAO, final String TABLENAME2) {
        Msg result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        MsgDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(MsgDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        MsgDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final MsgDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Msg> getGtKey(int id) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Msg> getGtKey(MsgDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Msg> getGtKey(int id, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Msg> getGtKey(final MsgDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Msg> result = newList();
            List<Msg> msgs = getAll();
            for (Msg msg : msgs) {
                if(msg.id <= id) continue;
                result.add(msg);
            }
            return result;
        }
    }

    public static Msg getByKey(int id) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Msg> asyncGetByKey(final int id) {
        Future<Msg> f = Async.exec(new Callable<Msg>() {
            public Msg call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Msg getByKey(MsgDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Msg getByKey(int id, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Msg getByKey(final MsgDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Msg getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Msg deleteFromMemory(final int id) {
        Msg msg;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            msg = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(msg);
        } else {
            msg = vars.remove(id);
        }
        if(msg == null) return null;

        String target = msg.getTarget();
        Set m1 = varsByTarget.get(target);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByTarget.remove(target);
        }

        return msg;
    }

    public static List<Msg> getByPage(int page, int size) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Msg> getByPage(MsgDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Msg> getByPage(int page, int size, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Msg> getByPage(final MsgDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Msg> result = newList();
            List<Msg> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(MsgDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final MsgDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByTarget(String target) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTarget(DAO, target, DAO.TABLENAME);
    }

    public static int countByTarget(MsgDAO DAO, String target) {
        return countByTarget(DAO, target, DAO.TABLENAME);
    }

    public static int countByTarget(String target, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTarget(DAO, target, TABLENAME2);
    }

    public static int countByTarget(final MsgDAO DAO, final String target,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTarget(target, TABLENAME2);
        }
        List<Msg> msgs = getByTarget(DAO, target, TABLENAME2);
        return msgs.size();
    }

    public static List<Msg> getByTarget(String target) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTarget(DAO, target, DAO.TABLENAME);
    }

    public static List<Msg> getByTarget(MsgDAO DAO, String target) {
        return getByTarget(DAO, target, DAO.TABLENAME);
    }

    public static List<Msg> getByTarget(String target, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTarget(DAO, target, TABLENAME2);
    }

    public static List<Msg> getByTarget(final MsgDAO DAO, final String target,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTarget(target, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Msg> result = newList();
            Set<Integer> m1 = varsByTarget.get(target);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Msg e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _target = e.getTarget();
                if(!_target.equals(target)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeTarget(String target) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeTarget(DAO, target, DAO.TABLENAME);
    }

    public static int countLikeTarget(MsgDAO DAO, String target) {
        return countLikeTarget(DAO, target, DAO.TABLENAME);
    }

    public static int countLikeTarget(String target, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeTarget(DAO, target, TABLENAME2);
    }

    public static int countLikeTarget(final MsgDAO DAO, final String target,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeTarget(target, TABLENAME2);
        }
        List<Msg> msgs = getLikeTarget(DAO, target, TABLENAME2);
        return msgs.size();
    }

    public static List<Msg> getLikeTarget(String target) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeTarget(DAO, target, DAO.TABLENAME);
    }

    public static List<Msg> getLikeTarget(MsgDAO DAO, String target) {
        return getLikeTarget(DAO, target, DAO.TABLENAME);
    }

    public static List<Msg> getLikeTarget(String target, String TABLENAME2) {
        MsgDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeTarget(DAO, target, TABLENAME2);
    }

    public static List<Msg> getLikeTarget(final MsgDAO DAO, final String target,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeTarget(target, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Msg> result = newList();
            List<Msg> msgs = getAll(DAO, TABLENAME2);
            for(Msg e : msgs){
                String _var = e.getTarget();
                if(_var.indexOf(target) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Msg update(Msg msg) {
        MsgDAO DAO = DAO();
        return update(DAO, msg, DAO.TABLENAME);
    }

    public static Msg update(MsgDAO DAO, Msg msg) {
        return update(DAO, msg, DAO.TABLENAME);
    }

    public static Msg update(Msg msg, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return update(DAO, msg, TABLENAME2);
    }

    public static Msg update(final MsgDAO DAO, final Msg msg,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(msg, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(msg, TABLENAME2);
            if(n == -1) 
                return msg;
            else if(n <= 0) 
                return null;
        }
        return msg;
    }

    public static Msg asyncUpdate(Msg msg) {
        MsgDAO DAO = DAO();
        return asyncUpdate(DAO, msg, DAO.TABLENAME);
    }

    public static Msg asyncUpdate(MsgDAO DAO, Msg msg) {
        return asyncUpdate(DAO, msg, DAO.TABLENAME);
    }

    public static Msg asyncUpdate(Msg msg, String TABLENAME2) {
        MsgDAO DAO = DAO();
        return asyncUpdate(DAO, msg, TABLENAME2);
    }

    public static Msg asyncUpdate(final MsgDAO DAO, final Msg msg,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(msg, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(msg, TABLENAME2);
        }
        return msg;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        MsgDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(MsgDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final MsgDAO DAO, final String TABLENAME2) {
        int result = 0;
        if(cacheModel != FULL_MEMORY)
            return result;
        try {
            DAO.truncate(TABLENAME2);
            DAO.repair(TABLENAME2);
            DAO.optimize(TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }

        List<Msg> msgs = getAll();
        for (Msg msg : msgs) {
            int n = DAO.insert2(msg, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
