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

import static com.learnhall.db.bean.Record4seeanswer.Col;

//learnhall3_design - record4seeanswer
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Record4seeanswerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Record4seeanswerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Record4seeanswerInternal(){}

    public static Record4seeanswerDAO DAO(){
        return Record4seeanswerEntity.Record4seeanswerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Record4seeanswer[MAX];
    }
    private static Record4seeanswer[] FIXED = new Record4seeanswer[MAX];
    public static final Map<Integer, Record4seeanswer> vars = newSortedMap();
    public static final Map<String, Integer> varsByAskidCustid = newSortedMap();

    private static final List<Record4seeanswer> fixedCache = newList();

    public static void put(Record4seeanswer record4seeanswer, boolean force){
        if(record4seeanswer == null || record4seeanswer.id <= 0) return ;

        int id = record4seeanswer.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(record4seeanswer))
                	FIXED[id - 1] = record4seeanswer;
                if (!fixedCache.contains(record4seeanswer))
                	fixedCache.add(record4seeanswer);
            }
        } else {
            vars.put(id, record4seeanswer);
        }

        { // askid
          boolean bChanged = record4seeanswer.inChanged(Col.askid, Col.custid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vaskid = record4seeanswer.oldOrNew(Col.askid);
            Object vcustid = record4seeanswer.oldOrNew(Col.custid);
            String okey = com.bowlong.lang.PStr.b().a(vaskid, "-", vcustid).e();
            varsByAskidCustid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vaskid = record4seeanswer.getAskid();
              int vcustid = record4seeanswer.getCustid();
              String vkey = com.bowlong.lang.PStr.b().a(vaskid, "-", vcustid).e();
              varsByAskidCustid.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByAskidCustid.clear();
        FIXED = new Record4seeanswer[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Record4seeanswerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Record4seeanswerDAO DAO, String TABLENAME2){
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

    public static void relocate(Record4seeanswerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Record4seeanswerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Record4seeanswerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Record4seeanswerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Record4seeanswerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Record4seeanswerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Record4seeanswerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Record4seeanswerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Record4seeanswerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Record4seeanswerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Record4seeanswerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Record4seeanswerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Record4seeanswerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Record4seeanswer[maxId(DAO)];

        List<Record4seeanswer> record4seeanswers = DAO.selectAll();
        for (Record4seeanswer record4seeanswer : record4seeanswers) {
            record4seeanswer.reset();
            put(record4seeanswer, true);
        }
    }

    public static Map toMap(Record4seeanswer record4seeanswer){
        return record4seeanswer.toMap();
    }

    public static List<Map> toMap(List<Record4seeanswer> record4seeanswers){
        List<Map> ret = new Vector<Map>();
        for (Record4seeanswer record4seeanswer : record4seeanswers){
            Map e = record4seeanswer.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Record4seeanswer> sortZh(List<Record4seeanswer> record4seeanswers, final String key) {
        Collections.sort(record4seeanswers, new Comparator<Record4seeanswer>() {
            public int compare(Record4seeanswer o1, Record4seeanswer o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return record4seeanswers;
    }

    public static List<Record4seeanswer> sort(List<Record4seeanswer> record4seeanswers, final String key) {
        Collections.sort(record4seeanswers, new Comparator<Record4seeanswer>() {
            public int compare(Record4seeanswer o1, Record4seeanswer o2) {
                return o1.compareTo(o2, key);
            }
        });
        return record4seeanswers;
    }

    public static List<Record4seeanswer> sort(List<Record4seeanswer> record4seeanswers){
        Collections.sort(record4seeanswers, new Comparator<Record4seeanswer>(){
            public int compare(Record4seeanswer o1, Record4seeanswer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return record4seeanswers;
    }

    public static List<Record4seeanswer> sortReverse(List<Record4seeanswer> record4seeanswers){
        Collections.sort(record4seeanswers, new Comparator<Record4seeanswer>(){
            public int compare(Record4seeanswer o1, Record4seeanswer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return record4seeanswers;
    }

    public static Record4seeanswer insert(Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = DAO();
        return insert(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer insert(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer) {
        return insert(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer insert(Record4seeanswer record4seeanswer, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return insert(DAO, record4seeanswer, TABLENAME2);
    }

    public static Record4seeanswer insert(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(record4seeanswer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        record4seeanswer.id = n;
        if(cacheModel != NO_CACHE) put(record4seeanswer, true);

        return record4seeanswer;
    }

    public static Record4seeanswer asyncInsert(Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = DAO();
        return asyncInsert(DAO, record4seeanswer, DAO.TABLENAME);
    }
    public static Record4seeanswer asyncInsert(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer) {
        return asyncInsert(DAO, record4seeanswer, DAO.TABLENAME);
    }
    public static Record4seeanswer asyncInsert(Record4seeanswer record4seeanswer, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return asyncInsert(DAO, record4seeanswer, TABLENAME2);
    }
    public static Record4seeanswer asyncInsert(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(record4seeanswer, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        record4seeanswer.id = n;
        if(cacheModel != NO_CACHE) put(record4seeanswer, true);
        return record4seeanswer;
    }
    public static Record4seeanswer insert2(Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = DAO();
        return insert2(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer insert2(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer) {
        return insert2(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer insert2(Record4seeanswer record4seeanswer, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return insert2(DAO, record4seeanswer, TABLENAME2);
    }

    public static Record4seeanswer insert2(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(record4seeanswer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        record4seeanswer.id = n;
        if(cacheModel != NO_CACHE) put(record4seeanswer, true);

        return record4seeanswer;
    }

    public static Record4seeanswer asyncInsert2(Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = DAO();
        return asyncInsert2(DAO, record4seeanswer, DAO.TABLENAME);
    }
    public static Record4seeanswer asyncInsert2(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer) {
        return asyncInsert2(DAO, record4seeanswer, DAO.TABLENAME);
    }
    public static Record4seeanswer asyncInsert2(Record4seeanswer record4seeanswer, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return asyncInsert2(DAO, record4seeanswer, TABLENAME2);
    }
    public static Record4seeanswer asyncInsert2(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        record4seeanswer.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(record4seeanswer, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(record4seeanswer, true);
        return record4seeanswer;
    }
    public static int[] insert(List<Record4seeanswer> record4seeanswers) {
        Record4seeanswerDAO DAO = DAO();
        return insert(DAO, record4seeanswers, DAO.TABLENAME);
    }

    public static int[] insert(Record4seeanswerDAO DAO, List<Record4seeanswer> record4seeanswers) {
        return insert(DAO, record4seeanswers, DAO.TABLENAME);
    }

    public static int[] insert(List<Record4seeanswer> record4seeanswers, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return insert(DAO, record4seeanswers, TABLENAME2);
    }

    public static int[] insert(Record4seeanswerDAO DAO, List<Record4seeanswer> record4seeanswers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(record4seeanswers, TABLENAME2);
            int n = 0;
            for(Record4seeanswer record4seeanswer : record4seeanswers){
                record4seeanswer.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[record4seeanswers.size()];
        int n = 0;
        for(Record4seeanswer record4seeanswer : record4seeanswers){
            record4seeanswer = insert(DAO, record4seeanswer, TABLENAME2);
            ret[n++] = (record4seeanswer == null) ? 0 : (int)record4seeanswer.id;
        }
        return ret;
    }

    public static int delete(Record4seeanswer record4seeanswer) {
        int id = record4seeanswer.id;
        Record4seeanswerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Record4seeanswerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Record4seeanswerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Record4seeanswerDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Record4seeanswer record4seeanswer) {
        int id = record4seeanswer.id;
        Record4seeanswerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Record4seeanswerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Record4seeanswerDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Record4seeanswerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Record4seeanswerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Record4seeanswerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Record4seeanswerDAO DAO, int[] ids,String TABLENAME2) {
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
        Record4seeanswerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Record4seeanswerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Record4seeanswer> beans) {
        Record4seeanswerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Record4seeanswer> beans, Record4seeanswerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Record4seeanswer> beans, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Record4seeanswer> beans, final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Record4seeanswer record4seeanswer : beans){
                int id = record4seeanswer.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Record4seeanswer> getAll() {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getAll(Record4seeanswerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getAll(String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Record4seeanswer> getAll(final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4seeanswer> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Record4seeanswer> getNoSortAll() {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getNoSortAll(Record4seeanswerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getNoSortAll(String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Record4seeanswer> getNoSortAll(final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Record4seeanswer> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4seeanswer> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Record4seeanswer record4seeanswer = FIXED[i];
                if (record4seeanswer != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Record4seeanswer> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Record4seeanswer> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Record4seeanswerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Record4seeanswerDAO DAO, final String TABLENAME2) {
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
        Record4seeanswerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Record4seeanswerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Record4seeanswerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Record4seeanswer> getIn(List<Integer> keys) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getIn(List<Integer> keys, Record4seeanswerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getIn(List<Integer> keys, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Record4seeanswer> getIn(final List<Integer> keys, final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4seeanswer> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Record4seeanswer> getNoSortIn(List<Integer> keys) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getNoSortIn(List<Integer> keys, Record4seeanswerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Record4seeanswer> getNoSortIn(final List<Integer> keys, final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Record4seeanswer> result = newList();
            for (int key : keys) {
                Record4seeanswer record4seeanswer = getByKeyFromMemory(key);
                if( record4seeanswer == null ) continue;
                result.add(record4seeanswer);
            }
            return result;
        }
    }

    public static List<Record4seeanswer> getLast(int num) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getLast(Record4seeanswerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getLast(int num, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Record4seeanswer> getLast(final Record4seeanswerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Record4seeanswer> result = newList();
            List<Record4seeanswer> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Record4seeanswer last() {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Record4seeanswer last(Record4seeanswerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Record4seeanswer last(String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Record4seeanswer last(final Record4seeanswerDAO DAO, final String TABLENAME2) {
        Record4seeanswer result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Record4seeanswerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Record4seeanswerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Record4seeanswerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Record4seeanswer> getGtKey(int id) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getGtKey(Record4seeanswerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getGtKey(int id, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Record4seeanswer> getGtKey(final Record4seeanswerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4seeanswer> result = newList();
            List<Record4seeanswer> record4seeanswers = getAll();
            for (Record4seeanswer record4seeanswer : record4seeanswers) {
                if(record4seeanswer.id <= id) continue;
                result.add(record4seeanswer);
            }
            return result;
        }
    }

    public static Record4seeanswer getByKey(int id) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Record4seeanswer> asyncGetByKey(final int id) {
        Future<Record4seeanswer> f = Async.exec(new Callable<Record4seeanswer>() {
            public Record4seeanswer call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Record4seeanswer getByKey(Record4seeanswerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Record4seeanswer getByKey(int id, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Record4seeanswer getByKey(final Record4seeanswerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Record4seeanswer getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Record4seeanswer deleteFromMemory(final int id) {
        Record4seeanswer record4seeanswer;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            record4seeanswer = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(record4seeanswer);
        } else {
            record4seeanswer = vars.remove(id);
        }
        if(record4seeanswer == null) return null;

        { // askid
            int vaskid = record4seeanswer.getAskid();
            int vcustid = record4seeanswer.getCustid();
            String vkey = com.bowlong.lang.PStr.b().a(vaskid, "-", vcustid).e();
            varsByAskidCustid.remove(vkey);
        }

        return record4seeanswer;
    }

    public static List<Record4seeanswer> getByPage(int page, int size) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getByPage(Record4seeanswerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Record4seeanswer> getByPage(int page, int size, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Record4seeanswer> getByPage(final Record4seeanswerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4seeanswer> result = newList();
            List<Record4seeanswer> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Record4seeanswerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Record4seeanswerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Record4seeanswer getByAskidCustid(Integer askid, Integer custid) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAskidCustid(DAO, askid, custid, DAO.TABLENAME);
    }

    public static Record4seeanswer getByAskidCustid(Record4seeanswerDAO DAO, Integer askid, Integer custid) {
        return getByAskidCustid(DAO, askid, custid, DAO.TABLENAME);
    }

    public static Record4seeanswer getByAskidCustid(Integer askid, Integer custid, String TABLENAME2) {
        Record4seeanswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAskidCustid(DAO, askid, custid, TABLENAME2);
    }

    public static Record4seeanswer getByAskidCustid(final Record4seeanswerDAO DAO, Integer askid, Integer custid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAskidCustid(askid, custid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = askid+"-"+custid;
            Integer id = varsByAskidCustid.get(vkey);
            if(id == null) return null;
            Record4seeanswer result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getAskid() != askid){
                varsByAskidCustid.remove(vkey);
                return null;
            }
            if(result.getCustid() != custid){
                varsByAskidCustid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Record4seeanswer update(Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = DAO();
        return update(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer update(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer) {
        return update(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer update(Record4seeanswer record4seeanswer, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return update(DAO, record4seeanswer, TABLENAME2);
    }

    public static Record4seeanswer update(final Record4seeanswerDAO DAO, final Record4seeanswer record4seeanswer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(record4seeanswer, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(record4seeanswer, TABLENAME2);
            if(n == -1) 
                return record4seeanswer;
            else if(n <= 0) 
                return null;
        }
        return record4seeanswer;
    }

    public static Record4seeanswer asyncUpdate(Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = DAO();
        return asyncUpdate(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer asyncUpdate(Record4seeanswerDAO DAO, Record4seeanswer record4seeanswer) {
        return asyncUpdate(DAO, record4seeanswer, DAO.TABLENAME);
    }

    public static Record4seeanswer asyncUpdate(Record4seeanswer record4seeanswer, String TABLENAME2) {
        Record4seeanswerDAO DAO = DAO();
        return asyncUpdate(DAO, record4seeanswer, TABLENAME2);
    }

    public static Record4seeanswer asyncUpdate(final Record4seeanswerDAO DAO, final Record4seeanswer record4seeanswer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(record4seeanswer, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(record4seeanswer, TABLENAME2);
        }
        return record4seeanswer;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Record4seeanswerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Record4seeanswerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Record4seeanswerDAO DAO, final String TABLENAME2) {
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

        List<Record4seeanswer> record4seeanswers = getAll();
        for (Record4seeanswer record4seeanswer : record4seeanswers) {
            int n = DAO.insert2(record4seeanswer, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
