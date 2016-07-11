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

import static com.learnhall.db.bean.Itms4day.Col;

//learnhall3_design - itms4day
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Itms4dayInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Itms4dayInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Itms4dayInternal(){}

    public static Itms4dayDAO DAO(){
        return Itms4dayEntity.Itms4dayDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Itms4day[MAX];
    }
    private static Itms4day[] FIXED = new Itms4day[MAX];
    public static final Map<Integer, Itms4day> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByCustidKindid = newSortedMap();

    private static final List<Itms4day> fixedCache = newList();

    public static void put(Itms4day itms4day, boolean force){
        if(itms4day == null || itms4day.id <= 0) return ;

        int id = itms4day.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(itms4day))
                	FIXED[id - 1] = itms4day;
                if (!fixedCache.contains(itms4day))
                	fixedCache.add(itms4day);
            }
        } else {
            vars.put(id, itms4day);
        }

        { // custid_kindid
          boolean bChanged = itms4day.inChanged(Col.custid, Col.kindid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vcustid = itms4day.oldOrNew(Col.custid);
              Object vkindid = itms4day.oldOrNew(Col.kindid);
              String okey = com.bowlong.lang.PStr.b().a(vcustid, "-", vkindid).e();
              Set m1 = varsByCustidKindid.get(okey);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                       varsByCustidKindid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vcustid = itms4day.getCustid();
              int vkindid = itms4day.getKindid();
              String vkey = com.bowlong.lang.PStr.b().a(vcustid, "-", vkindid).e();
              Set m1 = varsByCustidKindid.get(vkey);
              if(m1 == null){
                  m1 = newSortedSet();
                  varsByCustidKindid.put(vkey, m1);
              }
              m1.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustidKindid.clear();
        FIXED = new Itms4day[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Itms4dayDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Itms4dayDAO DAO, String TABLENAME2){
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

    public static void relocate(Itms4dayDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Itms4dayDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Itms4dayDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Itms4dayDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Itms4dayDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Itms4dayDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Itms4dayDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Itms4dayDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Itms4dayDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Itms4dayDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Itms4dayDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Itms4dayDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Itms4dayDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Itms4day[maxId(DAO)];

        List<Itms4day> itms4days = DAO.selectAll();
        for (Itms4day itms4day : itms4days) {
            itms4day.reset();
            put(itms4day, true);
        }
    }

    public static Map toMap(Itms4day itms4day){
        return itms4day.toMap();
    }

    public static List<Map> toMap(List<Itms4day> itms4days){
        List<Map> ret = new Vector<Map>();
        for (Itms4day itms4day : itms4days){
            Map e = itms4day.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Itms4day> sortZh(List<Itms4day> itms4days, final String key) {
        Collections.sort(itms4days, new Comparator<Itms4day>() {
            public int compare(Itms4day o1, Itms4day o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return itms4days;
    }

    public static List<Itms4day> sort(List<Itms4day> itms4days, final String key) {
        Collections.sort(itms4days, new Comparator<Itms4day>() {
            public int compare(Itms4day o1, Itms4day o2) {
                return o1.compareTo(o2, key);
            }
        });
        return itms4days;
    }

    public static List<Itms4day> sort(List<Itms4day> itms4days){
        Collections.sort(itms4days, new Comparator<Itms4day>(){
            public int compare(Itms4day o1, Itms4day o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return itms4days;
    }

    public static List<Itms4day> sortReverse(List<Itms4day> itms4days){
        Collections.sort(itms4days, new Comparator<Itms4day>(){
            public int compare(Itms4day o1, Itms4day o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return itms4days;
    }

    public static Itms4day insert(Itms4day itms4day) {
        Itms4dayDAO DAO = DAO();
        return insert(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day insert(Itms4dayDAO DAO, Itms4day itms4day) {
        return insert(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day insert(Itms4day itms4day, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return insert(DAO, itms4day, TABLENAME2);
    }

    public static Itms4day insert(Itms4dayDAO DAO, Itms4day itms4day, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(itms4day, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        itms4day.id = n;
        if(cacheModel != NO_CACHE) put(itms4day, true);

        return itms4day;
    }

    public static Itms4day asyncInsert(Itms4day itms4day) {
        Itms4dayDAO DAO = DAO();
        return asyncInsert(DAO, itms4day, DAO.TABLENAME);
    }
    public static Itms4day asyncInsert(Itms4dayDAO DAO, Itms4day itms4day) {
        return asyncInsert(DAO, itms4day, DAO.TABLENAME);
    }
    public static Itms4day asyncInsert(Itms4day itms4day, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return asyncInsert(DAO, itms4day, TABLENAME2);
    }
    public static Itms4day asyncInsert(Itms4dayDAO DAO, Itms4day itms4day, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(itms4day, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        itms4day.id = n;
        if(cacheModel != NO_CACHE) put(itms4day, true);
        return itms4day;
    }
    public static Itms4day insert2(Itms4day itms4day) {
        Itms4dayDAO DAO = DAO();
        return insert2(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day insert2(Itms4dayDAO DAO, Itms4day itms4day) {
        return insert2(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day insert2(Itms4day itms4day, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return insert2(DAO, itms4day, TABLENAME2);
    }

    public static Itms4day insert2(Itms4dayDAO DAO, Itms4day itms4day, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(itms4day, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        itms4day.id = n;
        if(cacheModel != NO_CACHE) put(itms4day, true);

        return itms4day;
    }

    public static Itms4day asyncInsert2(Itms4day itms4day) {
        Itms4dayDAO DAO = DAO();
        return asyncInsert2(DAO, itms4day, DAO.TABLENAME);
    }
    public static Itms4day asyncInsert2(Itms4dayDAO DAO, Itms4day itms4day) {
        return asyncInsert2(DAO, itms4day, DAO.TABLENAME);
    }
    public static Itms4day asyncInsert2(Itms4day itms4day, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return asyncInsert2(DAO, itms4day, TABLENAME2);
    }
    public static Itms4day asyncInsert2(Itms4dayDAO DAO, Itms4day itms4day, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        itms4day.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(itms4day, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(itms4day, true);
        return itms4day;
    }
    public static int[] insert(List<Itms4day> itms4days) {
        Itms4dayDAO DAO = DAO();
        return insert(DAO, itms4days, DAO.TABLENAME);
    }

    public static int[] insert(Itms4dayDAO DAO, List<Itms4day> itms4days) {
        return insert(DAO, itms4days, DAO.TABLENAME);
    }

    public static int[] insert(List<Itms4day> itms4days, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return insert(DAO, itms4days, TABLENAME2);
    }

    public static int[] insert(Itms4dayDAO DAO, List<Itms4day> itms4days, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(itms4days, TABLENAME2);
            int n = 0;
            for(Itms4day itms4day : itms4days){
                itms4day.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[itms4days.size()];
        int n = 0;
        for(Itms4day itms4day : itms4days){
            itms4day = insert(DAO, itms4day, TABLENAME2);
            ret[n++] = (itms4day == null) ? 0 : (int)itms4day.id;
        }
        return ret;
    }

    public static int delete(Itms4day itms4day) {
        int id = itms4day.id;
        Itms4dayDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Itms4dayDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Itms4dayDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Itms4dayDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Itms4day itms4day) {
        int id = itms4day.id;
        Itms4dayDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Itms4dayDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Itms4dayDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Itms4dayDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Itms4dayDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Itms4dayDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Itms4dayDAO DAO, int[] ids,String TABLENAME2) {
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
        Itms4dayDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Itms4dayDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Itms4dayDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Itms4day> beans) {
        Itms4dayDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Itms4day> beans, Itms4dayDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Itms4day> beans, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Itms4day> beans, final Itms4dayDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Itms4day itms4day : beans){
                int id = itms4day.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Itms4day> getAll() {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getAll(Itms4dayDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getAll(String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Itms4day> getAll(final Itms4dayDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4day> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Itms4day> getNoSortAll() {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getNoSortAll(Itms4dayDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getNoSortAll(String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Itms4day> getNoSortAll(final Itms4dayDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Itms4day> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4day> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Itms4day itms4day = FIXED[i];
                if (itms4day != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Itms4day> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Itms4day> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Itms4dayDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Itms4dayDAO DAO, final String TABLENAME2) {
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
        Itms4dayDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Itms4dayDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Itms4dayDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Itms4day> getIn(List<Integer> keys) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getIn(List<Integer> keys, Itms4dayDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getIn(List<Integer> keys, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Itms4day> getIn(final List<Integer> keys, final Itms4dayDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4day> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Itms4day> getNoSortIn(List<Integer> keys) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getNoSortIn(List<Integer> keys, Itms4dayDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4day> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Itms4day> getNoSortIn(final List<Integer> keys, final Itms4dayDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Itms4day> result = newList();
            for (int key : keys) {
                Itms4day itms4day = getByKeyFromMemory(key);
                if( itms4day == null ) continue;
                result.add(itms4day);
            }
            return result;
        }
    }

    public static List<Itms4day> getLast(int num) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Itms4day> getLast(Itms4dayDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Itms4day> getLast(int num, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Itms4day> getLast(final Itms4dayDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Itms4day> result = newList();
            List<Itms4day> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Itms4day last() {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Itms4day last(Itms4dayDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Itms4day last(String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Itms4day last(final Itms4dayDAO DAO, final String TABLENAME2) {
        Itms4day result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Itms4dayDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Itms4dayDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Itms4dayDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Itms4day> getGtKey(int id) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Itms4day> getGtKey(Itms4dayDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Itms4day> getGtKey(int id, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Itms4day> getGtKey(final Itms4dayDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4day> result = newList();
            List<Itms4day> itms4days = getAll();
            for (Itms4day itms4day : itms4days) {
                if(itms4day.id <= id) continue;
                result.add(itms4day);
            }
            return result;
        }
    }

    public static Itms4day getByKey(int id) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Itms4day> asyncGetByKey(final int id) {
        Future<Itms4day> f = Async.exec(new Callable<Itms4day>() {
            public Itms4day call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Itms4day getByKey(Itms4dayDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Itms4day getByKey(int id, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Itms4day getByKey(final Itms4dayDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Itms4day getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Itms4day deleteFromMemory(final int id) {
        Itms4day itms4day;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            itms4day = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(itms4day);
        } else {
            itms4day = vars.remove(id);
        }
        if(itms4day == null) return null;

        { // custid_kindid
            int vcustid = itms4day.getCustid();
            int vkindid = itms4day.getKindid();
            String vkey = com.bowlong.lang.PStr.b().a(vcustid, "-", vkindid).e();
            Set m1 = varsByCustidKindid.get(vkey);
            if(m1 != null) { 
                m1.remove(id);
                if(m1.isEmpty())
                    varsByCustidKindid.remove(vkey);
            }
        }

        return itms4day;
    }

    public static List<Itms4day> getByPage(int page, int size) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Itms4day> getByPage(Itms4dayDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Itms4day> getByPage(int page, int size, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Itms4day> getByPage(final Itms4dayDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4day> result = newList();
            List<Itms4day> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Itms4dayDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Itms4dayDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustidKindid(Integer custid, Integer kindid) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustidKindid(DAO, custid, kindid, DAO.TABLENAME);
    }

    public static int countByCustidKindid(Itms4dayDAO DAO, Integer custid, Integer kindid) {
        return countByCustidKindid(DAO, custid, kindid, DAO.TABLENAME);
    }

    public static int countByCustidKindid(Integer custid, Integer kindid, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustidKindid(DAO, custid, kindid, TABLENAME2);
    }

    public static int countByCustidKindid(final Itms4dayDAO DAO, Integer custid, Integer kindid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustidKindid(custid, kindid, TABLENAME2);
        }
        List<Itms4day> itms4days = getByCustidKindid(custid, kindid, TABLENAME2);
        return itms4days.size();
    }

    public static List<Itms4day> getByCustidKindid(Integer custid, Integer kindid) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustidKindid(DAO, custid, kindid, DAO.TABLENAME);
    }

    public static List<Itms4day> getByCustidKindid(Itms4dayDAO DAO, Integer custid, Integer kindid) {
        return getByCustidKindid(DAO, custid, kindid, DAO.TABLENAME);
    }

    public static List<Itms4day> getByCustidKindid(Integer custid, Integer kindid, String TABLENAME2) {
        Itms4dayDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustidKindid(DAO, custid, kindid, TABLENAME2);
    }

    public static List<Itms4day> getByCustidKindid(final Itms4dayDAO DAO, Integer custid, Integer kindid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustidKindid(custid, kindid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Itms4day> result = newList();
            String vkey = custid+"-"+kindid;
            Set<Integer> m1 = varsByCustidKindid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Itms4day e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _custid = e.getCustid();
                int _kindid = e.getKindid();
                String _key = com.bowlong.lang.PStr.b().a(_custid, "-", _kindid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Itms4day update(Itms4day itms4day) {
        Itms4dayDAO DAO = DAO();
        return update(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day update(Itms4dayDAO DAO, Itms4day itms4day) {
        return update(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day update(Itms4day itms4day, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return update(DAO, itms4day, TABLENAME2);
    }

    public static Itms4day update(final Itms4dayDAO DAO, final Itms4day itms4day,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(itms4day, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(itms4day, TABLENAME2);
            if(n == -1) 
                return itms4day;
            else if(n <= 0) 
                return null;
        }
        return itms4day;
    }

    public static Itms4day asyncUpdate(Itms4day itms4day) {
        Itms4dayDAO DAO = DAO();
        return asyncUpdate(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day asyncUpdate(Itms4dayDAO DAO, Itms4day itms4day) {
        return asyncUpdate(DAO, itms4day, DAO.TABLENAME);
    }

    public static Itms4day asyncUpdate(Itms4day itms4day, String TABLENAME2) {
        Itms4dayDAO DAO = DAO();
        return asyncUpdate(DAO, itms4day, TABLENAME2);
    }

    public static Itms4day asyncUpdate(final Itms4dayDAO DAO, final Itms4day itms4day,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(itms4day, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(itms4day, TABLENAME2);
        }
        return itms4day;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Itms4dayDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Itms4dayDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Itms4dayDAO DAO, final String TABLENAME2) {
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

        List<Itms4day> itms4days = getAll();
        for (Itms4day itms4day : itms4days) {
            int n = DAO.insert2(itms4day, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
