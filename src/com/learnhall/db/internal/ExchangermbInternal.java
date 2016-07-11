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

import static com.learnhall.db.bean.Exchangermb.Col;

//learnhall3_design - exchangermb
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ExchangermbInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ExchangermbInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ExchangermbInternal(){}

    public static ExchangermbDAO DAO(){
        return ExchangermbEntity.ExchangermbDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Exchangermb[MAX];
    }
    private static Exchangermb[] FIXED = new Exchangermb[MAX];
    public static final Map<Integer, Exchangermb> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeMakerid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByBatchNo = newSortedMap();

    private static final List<Exchangermb> fixedCache = newList();

    public static void put(Exchangermb exchangermb, boolean force){
        if(exchangermb == null || exchangermb.id <= 0) return ;

        int id = exchangermb.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(exchangermb))
                	FIXED[id - 1] = exchangermb;
                if (!fixedCache.contains(exchangermb))
                	fixedCache.add(exchangermb);
            }
        } else {
            vars.put(id, exchangermb);
        }

        { // type_makerid
          boolean bChanged = exchangermb.inChanged(Col.type, Col.makerid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vtype = exchangermb.oldOrNew(Col.type);
              Object vmakerid = exchangermb.oldOrNew(Col.makerid);
              String okey = com.bowlong.lang.PStr.b().a(vtype, "-", vmakerid).e();
              Set m1 = varsByTypeMakerid.get(okey);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                       varsByTypeMakerid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vtype = exchangermb.getType();
              int vmakerid = exchangermb.getMakerid();
              String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vmakerid).e();
              Set m1 = varsByTypeMakerid.get(vkey);
              if(m1 == null){
                  m1 = newSortedSet();
                  varsByTypeMakerid.put(vkey, m1);
              }
              m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index batchNo
          Object ov = exchangermb.oldVal(Col.batchNo);
          if(ov != null) {
              String _val = (String) ov;
              Set m2 = varsByBatchNo.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByBatchNo.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String batchNo = exchangermb.getBatchNo();
            Set m2 = varsByBatchNo.get(batchNo);
            if(m2 == null){
                m2 = newSortedSet();
                varsByBatchNo.put(batchNo, m2);
            }
            m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTypeMakerid.clear();
        varsByBatchNo.clear();
        FIXED = new Exchangermb[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ExchangermbDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ExchangermbDAO DAO, String TABLENAME2){
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

    public static void relocate(ExchangermbDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ExchangermbDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ExchangermbDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ExchangermbDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ExchangermbDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ExchangermbDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ExchangermbDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ExchangermbDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ExchangermbDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ExchangermbDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ExchangermbDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ExchangermbDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ExchangermbDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Exchangermb[maxId(DAO)];

        List<Exchangermb> exchangermbs = DAO.selectAll();
        for (Exchangermb exchangermb : exchangermbs) {
            exchangermb.reset();
            put(exchangermb, true);
        }
    }

    public static Map toMap(Exchangermb exchangermb){
        return exchangermb.toMap();
    }

    public static List<Map> toMap(List<Exchangermb> exchangermbs){
        List<Map> ret = new Vector<Map>();
        for (Exchangermb exchangermb : exchangermbs){
            Map e = exchangermb.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Exchangermb> sortZh(List<Exchangermb> exchangermbs, final String key) {
        Collections.sort(exchangermbs, new Comparator<Exchangermb>() {
            public int compare(Exchangermb o1, Exchangermb o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return exchangermbs;
    }

    public static List<Exchangermb> sort(List<Exchangermb> exchangermbs, final String key) {
        Collections.sort(exchangermbs, new Comparator<Exchangermb>() {
            public int compare(Exchangermb o1, Exchangermb o2) {
                return o1.compareTo(o2, key);
            }
        });
        return exchangermbs;
    }

    public static List<Exchangermb> sort(List<Exchangermb> exchangermbs){
        Collections.sort(exchangermbs, new Comparator<Exchangermb>(){
            public int compare(Exchangermb o1, Exchangermb o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return exchangermbs;
    }

    public static List<Exchangermb> sortReverse(List<Exchangermb> exchangermbs){
        Collections.sort(exchangermbs, new Comparator<Exchangermb>(){
            public int compare(Exchangermb o1, Exchangermb o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return exchangermbs;
    }

    public static List<Exchangermb> sortBatchNo(List<Exchangermb> exchangermbs){
        Collections.sort(exchangermbs, new Comparator<Exchangermb>(){
            public int compare(Exchangermb o1, Exchangermb o2) {
                Object v1 = o1.getBatchNo();
                Object v2 = o2.getBatchNo();
                return compareTo(v1, v2);
            }
        });
        return exchangermbs;
    }

    public static List<Exchangermb> sortBatchNoRo(List<Exchangermb> exchangermbs){
        Collections.sort(exchangermbs, new Comparator<Exchangermb>(){
            public int compare(Exchangermb o1, Exchangermb o2) {
                Object v1 = o1.getBatchNo();
                Object v2 = o2.getBatchNo();
                return 0 - compareTo(v1, v2);
            };
        });
        return exchangermbs;
    }

    public static Exchangermb insert(Exchangermb exchangermb) {
        ExchangermbDAO DAO = DAO();
        return insert(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb insert(ExchangermbDAO DAO, Exchangermb exchangermb) {
        return insert(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb insert(Exchangermb exchangermb, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return insert(DAO, exchangermb, TABLENAME2);
    }

    public static Exchangermb insert(ExchangermbDAO DAO, Exchangermb exchangermb, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(exchangermb, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        exchangermb.id = n;
        if(cacheModel != NO_CACHE) put(exchangermb, true);

        return exchangermb;
    }

    public static Exchangermb asyncInsert(Exchangermb exchangermb) {
        ExchangermbDAO DAO = DAO();
        return asyncInsert(DAO, exchangermb, DAO.TABLENAME);
    }
    public static Exchangermb asyncInsert(ExchangermbDAO DAO, Exchangermb exchangermb) {
        return asyncInsert(DAO, exchangermb, DAO.TABLENAME);
    }
    public static Exchangermb asyncInsert(Exchangermb exchangermb, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return asyncInsert(DAO, exchangermb, TABLENAME2);
    }
    public static Exchangermb asyncInsert(ExchangermbDAO DAO, Exchangermb exchangermb, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(exchangermb, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        exchangermb.id = n;
        if(cacheModel != NO_CACHE) put(exchangermb, true);
        return exchangermb;
    }
    public static Exchangermb insert2(Exchangermb exchangermb) {
        ExchangermbDAO DAO = DAO();
        return insert2(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb insert2(ExchangermbDAO DAO, Exchangermb exchangermb) {
        return insert2(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb insert2(Exchangermb exchangermb, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return insert2(DAO, exchangermb, TABLENAME2);
    }

    public static Exchangermb insert2(ExchangermbDAO DAO, Exchangermb exchangermb, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(exchangermb, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        exchangermb.id = n;
        if(cacheModel != NO_CACHE) put(exchangermb, true);

        return exchangermb;
    }

    public static Exchangermb asyncInsert2(Exchangermb exchangermb) {
        ExchangermbDAO DAO = DAO();
        return asyncInsert2(DAO, exchangermb, DAO.TABLENAME);
    }
    public static Exchangermb asyncInsert2(ExchangermbDAO DAO, Exchangermb exchangermb) {
        return asyncInsert2(DAO, exchangermb, DAO.TABLENAME);
    }
    public static Exchangermb asyncInsert2(Exchangermb exchangermb, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return asyncInsert2(DAO, exchangermb, TABLENAME2);
    }
    public static Exchangermb asyncInsert2(ExchangermbDAO DAO, Exchangermb exchangermb, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        exchangermb.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(exchangermb, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(exchangermb, true);
        return exchangermb;
    }
    public static int[] insert(List<Exchangermb> exchangermbs) {
        ExchangermbDAO DAO = DAO();
        return insert(DAO, exchangermbs, DAO.TABLENAME);
    }

    public static int[] insert(ExchangermbDAO DAO, List<Exchangermb> exchangermbs) {
        return insert(DAO, exchangermbs, DAO.TABLENAME);
    }

    public static int[] insert(List<Exchangermb> exchangermbs, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return insert(DAO, exchangermbs, TABLENAME2);
    }

    public static int[] insert(ExchangermbDAO DAO, List<Exchangermb> exchangermbs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(exchangermbs, TABLENAME2);
            int n = 0;
            for(Exchangermb exchangermb : exchangermbs){
                exchangermb.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[exchangermbs.size()];
        int n = 0;
        for(Exchangermb exchangermb : exchangermbs){
            exchangermb = insert(DAO, exchangermb, TABLENAME2);
            ret[n++] = (exchangermb == null) ? 0 : (int)exchangermb.id;
        }
        return ret;
    }

    public static int delete(Exchangermb exchangermb) {
        int id = exchangermb.id;
        ExchangermbDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ExchangermbDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ExchangermbDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ExchangermbDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Exchangermb exchangermb) {
        int id = exchangermb.id;
        ExchangermbDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ExchangermbDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ExchangermbDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ExchangermbDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ExchangermbDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ExchangermbDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ExchangermbDAO DAO, int[] ids,String TABLENAME2) {
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
        ExchangermbDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ExchangermbDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ExchangermbDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Exchangermb> beans) {
        ExchangermbDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Exchangermb> beans, ExchangermbDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Exchangermb> beans, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Exchangermb> beans, final ExchangermbDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Exchangermb exchangermb : beans){
                int id = exchangermb.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Exchangermb> getAll() {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getAll(ExchangermbDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getAll(String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Exchangermb> getAll(final ExchangermbDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exchangermb> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Exchangermb> getNoSortAll() {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getNoSortAll(ExchangermbDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getNoSortAll(String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Exchangermb> getNoSortAll(final ExchangermbDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Exchangermb> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exchangermb> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Exchangermb exchangermb = FIXED[i];
                if (exchangermb != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Exchangermb> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Exchangermb> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ExchangermbDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ExchangermbDAO DAO, final String TABLENAME2) {
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
        ExchangermbDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ExchangermbDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ExchangermbDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Exchangermb> getIn(List<Integer> keys) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getIn(List<Integer> keys, ExchangermbDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getIn(List<Integer> keys, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Exchangermb> getIn(final List<Integer> keys, final ExchangermbDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exchangermb> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Exchangermb> getNoSortIn(List<Integer> keys) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getNoSortIn(List<Integer> keys, ExchangermbDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exchangermb> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Exchangermb> getNoSortIn(final List<Integer> keys, final ExchangermbDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Exchangermb> result = newList();
            for (int key : keys) {
                Exchangermb exchangermb = getByKeyFromMemory(key);
                if( exchangermb == null ) continue;
                result.add(exchangermb);
            }
            return result;
        }
    }

    public static List<Exchangermb> getLast(int num) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Exchangermb> getLast(ExchangermbDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Exchangermb> getLast(int num, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Exchangermb> getLast(final ExchangermbDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Exchangermb> result = newList();
            List<Exchangermb> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Exchangermb last() {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Exchangermb last(ExchangermbDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Exchangermb last(String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Exchangermb last(final ExchangermbDAO DAO, final String TABLENAME2) {
        Exchangermb result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ExchangermbDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ExchangermbDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ExchangermbDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Exchangermb> getGtKey(int id) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Exchangermb> getGtKey(ExchangermbDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Exchangermb> getGtKey(int id, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Exchangermb> getGtKey(final ExchangermbDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exchangermb> result = newList();
            List<Exchangermb> exchangermbs = getAll();
            for (Exchangermb exchangermb : exchangermbs) {
                if(exchangermb.id <= id) continue;
                result.add(exchangermb);
            }
            return result;
        }
    }

    public static Exchangermb getByKey(int id) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Exchangermb> asyncGetByKey(final int id) {
        Future<Exchangermb> f = Async.exec(new Callable<Exchangermb>() {
            public Exchangermb call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Exchangermb getByKey(ExchangermbDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Exchangermb getByKey(int id, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Exchangermb getByKey(final ExchangermbDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Exchangermb getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Exchangermb deleteFromMemory(final int id) {
        Exchangermb exchangermb;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            exchangermb = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(exchangermb);
        } else {
            exchangermb = vars.remove(id);
        }
        if(exchangermb == null) return null;

        { // type_makerid
            int vtype = exchangermb.getType();
            int vmakerid = exchangermb.getMakerid();
            String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vmakerid).e();
            Set m1 = varsByTypeMakerid.get(vkey);
            if(m1 != null) { 
                m1.remove(id);
                if(m1.isEmpty())
                    varsByTypeMakerid.remove(vkey);
            }
        }

        String batchNo = exchangermb.getBatchNo();
        Set m2 = varsByBatchNo.get(batchNo);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByBatchNo.remove(batchNo);
        }

        return exchangermb;
    }

    public static List<Exchangermb> getByPage(int page, int size) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Exchangermb> getByPage(ExchangermbDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Exchangermb> getByPage(int page, int size, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Exchangermb> getByPage(final ExchangermbDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exchangermb> result = newList();
            List<Exchangermb> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ExchangermbDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ExchangermbDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByTypeMakerid(Integer type, Integer makerid) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static int countByTypeMakerid(ExchangermbDAO DAO, Integer type, Integer makerid) {
        return countByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static int countByTypeMakerid(Integer type, Integer makerid, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeMakerid(DAO, type, makerid, TABLENAME2);
    }

    public static int countByTypeMakerid(final ExchangermbDAO DAO, Integer type, Integer makerid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeMakerid(type, makerid, TABLENAME2);
        }
        List<Exchangermb> exchangermbs = getByTypeMakerid(type, makerid, TABLENAME2);
        return exchangermbs.size();
    }

    public static List<Exchangermb> getByTypeMakerid(Integer type, Integer makerid) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static List<Exchangermb> getByTypeMakerid(ExchangermbDAO DAO, Integer type, Integer makerid) {
        return getByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static List<Exchangermb> getByTypeMakerid(Integer type, Integer makerid, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeMakerid(DAO, type, makerid, TABLENAME2);
    }

    public static List<Exchangermb> getByTypeMakerid(final ExchangermbDAO DAO, Integer type, Integer makerid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeMakerid(type, makerid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Exchangermb> result = newList();
            String vkey = type+"-"+makerid;
            Set<Integer> m1 = varsByTypeMakerid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Exchangermb e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                int _makerid = e.getMakerid();
                String _key = com.bowlong.lang.PStr.b().a(_type, "-", _makerid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByBatchNo(String batchNo) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static int countByBatchNo(ExchangermbDAO DAO, String batchNo) {
        return countByBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static int countByBatchNo(String batchNo, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByBatchNo(DAO, batchNo, TABLENAME2);
    }

    public static int countByBatchNo(final ExchangermbDAO DAO, final String batchNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByBatchNo(batchNo, TABLENAME2);
        }
        List<Exchangermb> exchangermbs = getByBatchNo(DAO, batchNo, TABLENAME2);
        return exchangermbs.size();
    }

    public static List<Exchangermb> getByBatchNo(String batchNo) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static List<Exchangermb> getByBatchNo(ExchangermbDAO DAO, String batchNo) {
        return getByBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static List<Exchangermb> getByBatchNo(String batchNo, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBatchNo(DAO, batchNo, TABLENAME2);
    }

    public static List<Exchangermb> getByBatchNo(final ExchangermbDAO DAO, final String batchNo,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByBatchNo(batchNo, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Exchangermb> result = newList();
            Set<Integer> m1 = varsByBatchNo.get(batchNo);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Exchangermb e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _batchNo = e.getBatchNo();
                if(!_batchNo.equals(batchNo)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeBatchNo(String batchNo) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static int countLikeBatchNo(ExchangermbDAO DAO, String batchNo) {
        return countLikeBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static int countLikeBatchNo(String batchNo, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeBatchNo(DAO, batchNo, TABLENAME2);
    }

    public static int countLikeBatchNo(final ExchangermbDAO DAO, final String batchNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeBatchNo(batchNo, TABLENAME2);
        }
        List<Exchangermb> exchangermbs = getLikeBatchNo(DAO, batchNo, TABLENAME2);
        return exchangermbs.size();
    }

    public static List<Exchangermb> getLikeBatchNo(String batchNo) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static List<Exchangermb> getLikeBatchNo(ExchangermbDAO DAO, String batchNo) {
        return getLikeBatchNo(DAO, batchNo, DAO.TABLENAME);
    }

    public static List<Exchangermb> getLikeBatchNo(String batchNo, String TABLENAME2) {
        ExchangermbDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeBatchNo(DAO, batchNo, TABLENAME2);
    }

    public static List<Exchangermb> getLikeBatchNo(final ExchangermbDAO DAO, final String batchNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeBatchNo(batchNo, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Exchangermb> result = newList();
            List<Exchangermb> exchangermbs = getAll(DAO, TABLENAME2);
            for(Exchangermb e : exchangermbs){
                String _var = e.getBatchNo();
                if(_var.indexOf(batchNo) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Exchangermb update(Exchangermb exchangermb) {
        ExchangermbDAO DAO = DAO();
        return update(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb update(ExchangermbDAO DAO, Exchangermb exchangermb) {
        return update(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb update(Exchangermb exchangermb, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return update(DAO, exchangermb, TABLENAME2);
    }

    public static Exchangermb update(final ExchangermbDAO DAO, final Exchangermb exchangermb,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(exchangermb, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(exchangermb, TABLENAME2);
            if(n == -1) 
                return exchangermb;
            else if(n <= 0) 
                return null;
        }
        return exchangermb;
    }

    public static Exchangermb asyncUpdate(Exchangermb exchangermb) {
        ExchangermbDAO DAO = DAO();
        return asyncUpdate(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb asyncUpdate(ExchangermbDAO DAO, Exchangermb exchangermb) {
        return asyncUpdate(DAO, exchangermb, DAO.TABLENAME);
    }

    public static Exchangermb asyncUpdate(Exchangermb exchangermb, String TABLENAME2) {
        ExchangermbDAO DAO = DAO();
        return asyncUpdate(DAO, exchangermb, TABLENAME2);
    }

    public static Exchangermb asyncUpdate(final ExchangermbDAO DAO, final Exchangermb exchangermb,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(exchangermb, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(exchangermb, TABLENAME2);
        }
        return exchangermb;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ExchangermbDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ExchangermbDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ExchangermbDAO DAO, final String TABLENAME2) {
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

        List<Exchangermb> exchangermbs = getAll();
        for (Exchangermb exchangermb : exchangermbs) {
            int n = DAO.insert2(exchangermb, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
