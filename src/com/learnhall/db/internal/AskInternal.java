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

import static com.learnhall.db.bean.Ask.Col;

//learnhall3_design - ask
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AskInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AskInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AskInternal(){}

    public static AskDAO DAO(){
        return AskEntity.AskDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Ask[MAX];
    }
    private static Ask[] FIXED = new Ask[MAX];
    public static final Map<Integer, Ask> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByAnswerid = newSortedMap();

    private static final List<Ask> fixedCache = newList();

    public static void put(Ask ask, boolean force){
        if(ask == null || ask.id <= 0) return ;

        int id = ask.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(ask))
                	FIXED[id - 1] = ask;
                if (!fixedCache.contains(ask))
                	fixedCache.add(ask);
            }
        } else {
            vars.put(id, ask);
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = ask.oldVal(Col.customerid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByCustomerid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByCustomerid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int customerid = ask.getCustomerid();
            Set m1 = varsByCustomerid.get(customerid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByCustomerid.put(customerid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index answerid
          Object ov = ask.oldVal(Col.answerid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByAnswerid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByAnswerid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int answerid = ask.getAnswerid();
            Set m2 = varsByAnswerid.get(answerid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByAnswerid.put(answerid, m2);
            }
            m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustomerid.clear();
        varsByAnswerid.clear();
        FIXED = new Ask[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AskDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AskDAO DAO, String TABLENAME2){
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

    public static void relocate(AskDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AskDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AskDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AskDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AskDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AskDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AskDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AskDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AskDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AskDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AskDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AskDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AskDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AskDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AskDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Ask[maxId(DAO)];

        List<Ask> asks = DAO.selectAll();
        for (Ask ask : asks) {
            ask.reset();
            put(ask, true);
        }
    }

    public static Map toMap(Ask ask){
        return ask.toMap();
    }

    public static List<Map> toMap(List<Ask> asks){
        List<Map> ret = new Vector<Map>();
        for (Ask ask : asks){
            Map e = ask.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Ask> sortZh(List<Ask> asks, final String key) {
        Collections.sort(asks, new Comparator<Ask>() {
            public int compare(Ask o1, Ask o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return asks;
    }

    public static List<Ask> sort(List<Ask> asks, final String key) {
        Collections.sort(asks, new Comparator<Ask>() {
            public int compare(Ask o1, Ask o2) {
                return o1.compareTo(o2, key);
            }
        });
        return asks;
    }

    public static List<Ask> sort(List<Ask> asks){
        Collections.sort(asks, new Comparator<Ask>(){
            public int compare(Ask o1, Ask o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return asks;
    }

    public static List<Ask> sortReverse(List<Ask> asks){
        Collections.sort(asks, new Comparator<Ask>(){
            public int compare(Ask o1, Ask o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return asks;
    }

    public static List<Ask> sortCustomerid(List<Ask> asks){
        Collections.sort(asks, new Comparator<Ask>(){
            public int compare(Ask o1, Ask o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return asks;
    }

    public static List<Ask> sortCustomeridRo(List<Ask> asks){
        Collections.sort(asks, new Comparator<Ask>(){
            public int compare(Ask o1, Ask o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return asks;
    }

    public static List<Ask> sortAnswerid(List<Ask> asks){
        Collections.sort(asks, new Comparator<Ask>(){
            public int compare(Ask o1, Ask o2) {
                Object v1 = o1.getAnswerid();
                Object v2 = o2.getAnswerid();
                return compareTo(v1, v2);
            }
        });
        return asks;
    }

    public static List<Ask> sortAnsweridRo(List<Ask> asks){
        Collections.sort(asks, new Comparator<Ask>(){
            public int compare(Ask o1, Ask o2) {
                Object v1 = o1.getAnswerid();
                Object v2 = o2.getAnswerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return asks;
    }

    public static Ask insert(Ask ask) {
        AskDAO DAO = DAO();
        return insert(DAO, ask, DAO.TABLENAME);
    }

    public static Ask insert(AskDAO DAO, Ask ask) {
        return insert(DAO, ask, DAO.TABLENAME);
    }

    public static Ask insert(Ask ask, String TABLENAME2) {
        AskDAO DAO = DAO();
        return insert(DAO, ask, TABLENAME2);
    }

    public static Ask insert(AskDAO DAO, Ask ask, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(ask, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        ask.id = n;
        if(cacheModel != NO_CACHE) put(ask, true);

        return ask;
    }

    public static Ask asyncInsert(Ask ask) {
        AskDAO DAO = DAO();
        return asyncInsert(DAO, ask, DAO.TABLENAME);
    }
    public static Ask asyncInsert(AskDAO DAO, Ask ask) {
        return asyncInsert(DAO, ask, DAO.TABLENAME);
    }
    public static Ask asyncInsert(Ask ask, String TABLENAME2) {
        AskDAO DAO = DAO();
        return asyncInsert(DAO, ask, TABLENAME2);
    }
    public static Ask asyncInsert(AskDAO DAO, Ask ask, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(ask, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        ask.id = n;
        if(cacheModel != NO_CACHE) put(ask, true);
        return ask;
    }
    public static Ask insert2(Ask ask) {
        AskDAO DAO = DAO();
        return insert2(DAO, ask, DAO.TABLENAME);
    }

    public static Ask insert2(AskDAO DAO, Ask ask) {
        return insert2(DAO, ask, DAO.TABLENAME);
    }

    public static Ask insert2(Ask ask, String TABLENAME2) {
        AskDAO DAO = DAO();
        return insert2(DAO, ask, TABLENAME2);
    }

    public static Ask insert2(AskDAO DAO, Ask ask, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(ask, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        ask.id = n;
        if(cacheModel != NO_CACHE) put(ask, true);

        return ask;
    }

    public static Ask asyncInsert2(Ask ask) {
        AskDAO DAO = DAO();
        return asyncInsert2(DAO, ask, DAO.TABLENAME);
    }
    public static Ask asyncInsert2(AskDAO DAO, Ask ask) {
        return asyncInsert2(DAO, ask, DAO.TABLENAME);
    }
    public static Ask asyncInsert2(Ask ask, String TABLENAME2) {
        AskDAO DAO = DAO();
        return asyncInsert2(DAO, ask, TABLENAME2);
    }
    public static Ask asyncInsert2(AskDAO DAO, Ask ask, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        ask.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(ask, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(ask, true);
        return ask;
    }
    public static int[] insert(List<Ask> asks) {
        AskDAO DAO = DAO();
        return insert(DAO, asks, DAO.TABLENAME);
    }

    public static int[] insert(AskDAO DAO, List<Ask> asks) {
        return insert(DAO, asks, DAO.TABLENAME);
    }

    public static int[] insert(List<Ask> asks, String TABLENAME2) {
        AskDAO DAO = DAO();
        return insert(DAO, asks, TABLENAME2);
    }

    public static int[] insert(AskDAO DAO, List<Ask> asks, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(asks, TABLENAME2);
            int n = 0;
            for(Ask ask : asks){
                ask.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[asks.size()];
        int n = 0;
        for(Ask ask : asks){
            ask = insert(DAO, ask, TABLENAME2);
            ret[n++] = (ask == null) ? 0 : (int)ask.id;
        }
        return ret;
    }

    public static int delete(Ask ask) {
        int id = ask.id;
        AskDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        AskDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(AskDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        AskDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(AskDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Ask ask) {
        int id = ask.id;
        AskDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        AskDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(AskDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        AskDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(AskDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        AskDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(AskDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        AskDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(AskDAO DAO, int[] ids,String TABLENAME2) {
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
        AskDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AskDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AskDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AskDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Ask> beans) {
        AskDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Ask> beans, AskDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Ask> beans, String TABLENAME2) {
        AskDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Ask> beans, final AskDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Ask ask : beans){
                int id = ask.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Ask> getAll() {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Ask> getAll(AskDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Ask> getAll(String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Ask> getAll(final AskDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ask> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Ask> getNoSortAll() {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Ask> getNoSortAll(AskDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Ask> getNoSortAll(String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Ask> getNoSortAll(final AskDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Ask> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ask> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Ask ask = FIXED[i];
                if (ask != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Ask> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Ask> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AskDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AskDAO DAO, final String TABLENAME2) {
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
        AskDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AskDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AskDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AskDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Ask> getIn(List<Integer> keys) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ask> getIn(List<Integer> keys, AskDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ask> getIn(List<Integer> keys, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Ask> getIn(final List<Integer> keys, final AskDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ask> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Ask> getNoSortIn(List<Integer> keys) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ask> getNoSortIn(List<Integer> keys, AskDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ask> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Ask> getNoSortIn(final List<Integer> keys, final AskDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Ask> result = newList();
            for (int key : keys) {
                Ask ask = getByKeyFromMemory(key);
                if( ask == null ) continue;
                result.add(ask);
            }
            return result;
        }
    }

    public static List<Ask> getLast(int num) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Ask> getLast(AskDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Ask> getLast(int num, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Ask> getLast(final AskDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Ask> result = newList();
            List<Ask> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Ask last() {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Ask last(AskDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Ask last(String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Ask last(final AskDAO DAO, final String TABLENAME2) {
        Ask result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AskDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AskDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AskDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AskDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Ask> getGtKey(int id) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Ask> getGtKey(AskDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Ask> getGtKey(int id, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Ask> getGtKey(final AskDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ask> result = newList();
            List<Ask> asks = getAll();
            for (Ask ask : asks) {
                if(ask.id <= id) continue;
                result.add(ask);
            }
            return result;
        }
    }

    public static Ask getByKey(int id) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Ask> asyncGetByKey(final int id) {
        Future<Ask> f = Async.exec(new Callable<Ask>() {
            public Ask call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Ask getByKey(AskDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Ask getByKey(int id, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Ask getByKey(final AskDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Ask getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Ask deleteFromMemory(final int id) {
        Ask ask;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            ask = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(ask);
        } else {
            ask = vars.remove(id);
        }
        if(ask == null) return null;

        int customerid = ask.getCustomerid();
        Set m1 = varsByCustomerid.get(customerid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int answerid = ask.getAnswerid();
        Set m2 = varsByAnswerid.get(answerid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByAnswerid.remove(answerid);
        }

        return ask;
    }

    public static List<Ask> getByPage(int page, int size) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Ask> getByPage(AskDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Ask> getByPage(int page, int size, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Ask> getByPage(final AskDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ask> result = newList();
            List<Ask> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AskDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AskDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustomerid(Integer customerid) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(AskDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final AskDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Ask> asks = getByCustomerid(DAO, customerid, TABLENAME2);
        return asks.size();
    }

    public static List<Ask> getByCustomerid(Integer customerid) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Ask> getByCustomerid(AskDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Ask> getByCustomerid(Integer customerid, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Ask> getByCustomerid(final AskDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Ask> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Ask e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _customerid = e.getCustomerid();
                if(_customerid != customerid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByAnswerid(Integer answerid) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAnswerid(DAO, answerid, DAO.TABLENAME);
    }

    public static int countByAnswerid(AskDAO DAO, Integer answerid) {
        return countByAnswerid(DAO, answerid, DAO.TABLENAME);
    }

    public static int countByAnswerid(Integer answerid, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAnswerid(DAO, answerid, TABLENAME2);
    }

    public static int countByAnswerid(final AskDAO DAO, final Integer answerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByAnswerid(answerid, TABLENAME2);
        }
        List<Ask> asks = getByAnswerid(DAO, answerid, TABLENAME2);
        return asks.size();
    }

    public static List<Ask> getByAnswerid(Integer answerid) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAnswerid(DAO, answerid, DAO.TABLENAME);
    }

    public static List<Ask> getByAnswerid(AskDAO DAO, Integer answerid) {
        return getByAnswerid(DAO, answerid, DAO.TABLENAME);
    }

    public static List<Ask> getByAnswerid(Integer answerid, String TABLENAME2) {
        AskDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAnswerid(DAO, answerid, TABLENAME2);
    }

    public static List<Ask> getByAnswerid(final AskDAO DAO, final Integer answerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAnswerid(answerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Ask> result = newList();
            Set<Integer> m1 = varsByAnswerid.get(answerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Ask e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _answerid = e.getAnswerid();
                if(_answerid != answerid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Ask update(Ask ask) {
        AskDAO DAO = DAO();
        return update(DAO, ask, DAO.TABLENAME);
    }

    public static Ask update(AskDAO DAO, Ask ask) {
        return update(DAO, ask, DAO.TABLENAME);
    }

    public static Ask update(Ask ask, String TABLENAME2) {
        AskDAO DAO = DAO();
        return update(DAO, ask, TABLENAME2);
    }

    public static Ask update(final AskDAO DAO, final Ask ask,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(ask, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(ask, TABLENAME2);
            if(n == -1) 
                return ask;
            else if(n <= 0) 
                return null;
        }
        return ask;
    }

    public static Ask asyncUpdate(Ask ask) {
        AskDAO DAO = DAO();
        return asyncUpdate(DAO, ask, DAO.TABLENAME);
    }

    public static Ask asyncUpdate(AskDAO DAO, Ask ask) {
        return asyncUpdate(DAO, ask, DAO.TABLENAME);
    }

    public static Ask asyncUpdate(Ask ask, String TABLENAME2) {
        AskDAO DAO = DAO();
        return asyncUpdate(DAO, ask, TABLENAME2);
    }

    public static Ask asyncUpdate(final AskDAO DAO, final Ask ask,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(ask, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(ask, TABLENAME2);
        }
        return ask;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AskDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AskDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AskDAO DAO, final String TABLENAME2) {
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

        List<Ask> asks = getAll();
        for (Ask ask : asks) {
            int n = DAO.insert2(ask, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
