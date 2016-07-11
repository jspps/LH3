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


//learnhall3_design - record4orders
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Record4ordersInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Record4ordersInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Record4ordersInternal(){}

    public static Record4ordersDAO DAO(){
        return Record4ordersEntity.Record4ordersDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Record4orders[MAX];
    }
    private static Record4orders[] FIXED = new Record4orders[MAX];
    public static final Map<Integer, Record4orders> vars = newSortedMap();

    private static final List<Record4orders> fixedCache = newList();

    public static void put(Record4orders record4orders, boolean force){
        if(record4orders == null || record4orders.id <= 0) return ;

        int id = record4orders.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(record4orders))
                	FIXED[id - 1] = record4orders;
                if (!fixedCache.contains(record4orders))
                	fixedCache.add(record4orders);
            }
        } else {
            vars.put(id, record4orders);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Record4orders[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Record4ordersDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Record4ordersDAO DAO, String TABLENAME2){
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

    public static void relocate(Record4ordersDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Record4ordersDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Record4ordersDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Record4ordersDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Record4ordersDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Record4ordersDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Record4ordersDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Record4ordersDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Record4ordersDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Record4ordersDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Record4ordersDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Record4ordersDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Record4ordersDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Record4orders[maxId(DAO)];

        List<Record4orders> record4orderss = DAO.selectAll();
        for (Record4orders record4orders : record4orderss) {
            record4orders.reset();
            put(record4orders, true);
        }
    }

    public static Map toMap(Record4orders record4orders){
        return record4orders.toMap();
    }

    public static List<Map> toMap(List<Record4orders> record4orderss){
        List<Map> ret = new Vector<Map>();
        for (Record4orders record4orders : record4orderss){
            Map e = record4orders.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Record4orders> sortZh(List<Record4orders> record4orderss, final String key) {
        Collections.sort(record4orderss, new Comparator<Record4orders>() {
            public int compare(Record4orders o1, Record4orders o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return record4orderss;
    }

    public static List<Record4orders> sort(List<Record4orders> record4orderss, final String key) {
        Collections.sort(record4orderss, new Comparator<Record4orders>() {
            public int compare(Record4orders o1, Record4orders o2) {
                return o1.compareTo(o2, key);
            }
        });
        return record4orderss;
    }

    public static List<Record4orders> sort(List<Record4orders> record4orderss){
        Collections.sort(record4orderss, new Comparator<Record4orders>(){
            public int compare(Record4orders o1, Record4orders o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return record4orderss;
    }

    public static List<Record4orders> sortReverse(List<Record4orders> record4orderss){
        Collections.sort(record4orderss, new Comparator<Record4orders>(){
            public int compare(Record4orders o1, Record4orders o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return record4orderss;
    }

    public static Record4orders insert(Record4orders record4orders) {
        Record4ordersDAO DAO = DAO();
        return insert(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders insert(Record4ordersDAO DAO, Record4orders record4orders) {
        return insert(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders insert(Record4orders record4orders, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return insert(DAO, record4orders, TABLENAME2);
    }

    public static Record4orders insert(Record4ordersDAO DAO, Record4orders record4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(record4orders, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        record4orders.id = n;
        if(cacheModel != NO_CACHE) put(record4orders, true);

        return record4orders;
    }

    public static Record4orders asyncInsert(Record4orders record4orders) {
        Record4ordersDAO DAO = DAO();
        return asyncInsert(DAO, record4orders, DAO.TABLENAME);
    }
    public static Record4orders asyncInsert(Record4ordersDAO DAO, Record4orders record4orders) {
        return asyncInsert(DAO, record4orders, DAO.TABLENAME);
    }
    public static Record4orders asyncInsert(Record4orders record4orders, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return asyncInsert(DAO, record4orders, TABLENAME2);
    }
    public static Record4orders asyncInsert(Record4ordersDAO DAO, Record4orders record4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(record4orders, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        record4orders.id = n;
        if(cacheModel != NO_CACHE) put(record4orders, true);
        return record4orders;
    }
    public static Record4orders insert2(Record4orders record4orders) {
        Record4ordersDAO DAO = DAO();
        return insert2(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders insert2(Record4ordersDAO DAO, Record4orders record4orders) {
        return insert2(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders insert2(Record4orders record4orders, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return insert2(DAO, record4orders, TABLENAME2);
    }

    public static Record4orders insert2(Record4ordersDAO DAO, Record4orders record4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(record4orders, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        record4orders.id = n;
        if(cacheModel != NO_CACHE) put(record4orders, true);

        return record4orders;
    }

    public static Record4orders asyncInsert2(Record4orders record4orders) {
        Record4ordersDAO DAO = DAO();
        return asyncInsert2(DAO, record4orders, DAO.TABLENAME);
    }
    public static Record4orders asyncInsert2(Record4ordersDAO DAO, Record4orders record4orders) {
        return asyncInsert2(DAO, record4orders, DAO.TABLENAME);
    }
    public static Record4orders asyncInsert2(Record4orders record4orders, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return asyncInsert2(DAO, record4orders, TABLENAME2);
    }
    public static Record4orders asyncInsert2(Record4ordersDAO DAO, Record4orders record4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        record4orders.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(record4orders, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(record4orders, true);
        return record4orders;
    }
    public static int[] insert(List<Record4orders> record4orderss) {
        Record4ordersDAO DAO = DAO();
        return insert(DAO, record4orderss, DAO.TABLENAME);
    }

    public static int[] insert(Record4ordersDAO DAO, List<Record4orders> record4orderss) {
        return insert(DAO, record4orderss, DAO.TABLENAME);
    }

    public static int[] insert(List<Record4orders> record4orderss, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return insert(DAO, record4orderss, TABLENAME2);
    }

    public static int[] insert(Record4ordersDAO DAO, List<Record4orders> record4orderss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(record4orderss, TABLENAME2);
            int n = 0;
            for(Record4orders record4orders : record4orderss){
                record4orders.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[record4orderss.size()];
        int n = 0;
        for(Record4orders record4orders : record4orderss){
            record4orders = insert(DAO, record4orders, TABLENAME2);
            ret[n++] = (record4orders == null) ? 0 : (int)record4orders.id;
        }
        return ret;
    }

    public static int delete(Record4orders record4orders) {
        int id = record4orders.id;
        Record4ordersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Record4ordersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Record4ordersDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Record4ordersDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Record4orders record4orders) {
        int id = record4orders.id;
        Record4ordersDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Record4ordersDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Record4ordersDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Record4ordersDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Record4ordersDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Record4ordersDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Record4ordersDAO DAO, int[] ids,String TABLENAME2) {
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
        Record4ordersDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Record4ordersDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Record4ordersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Record4orders> beans) {
        Record4ordersDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Record4orders> beans, Record4ordersDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Record4orders> beans, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Record4orders> beans, final Record4ordersDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Record4orders record4orders : beans){
                int id = record4orders.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Record4orders> getAll() {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getAll(Record4ordersDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getAll(String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Record4orders> getAll(final Record4ordersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4orders> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Record4orders> getNoSortAll() {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getNoSortAll(Record4ordersDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getNoSortAll(String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Record4orders> getNoSortAll(final Record4ordersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Record4orders> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4orders> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Record4orders record4orders = FIXED[i];
                if (record4orders != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Record4orders> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Record4orders> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Record4ordersDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Record4ordersDAO DAO, final String TABLENAME2) {
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
        Record4ordersDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Record4ordersDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Record4ordersDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Record4orders> getIn(List<Integer> keys) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getIn(List<Integer> keys, Record4ordersDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getIn(List<Integer> keys, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Record4orders> getIn(final List<Integer> keys, final Record4ordersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4orders> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Record4orders> getNoSortIn(List<Integer> keys) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getNoSortIn(List<Integer> keys, Record4ordersDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Record4orders> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Record4orders> getNoSortIn(final List<Integer> keys, final Record4ordersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Record4orders> result = newList();
            for (int key : keys) {
                Record4orders record4orders = getByKeyFromMemory(key);
                if( record4orders == null ) continue;
                result.add(record4orders);
            }
            return result;
        }
    }

    public static List<Record4orders> getLast(int num) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Record4orders> getLast(Record4ordersDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Record4orders> getLast(int num, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Record4orders> getLast(final Record4ordersDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Record4orders> result = newList();
            List<Record4orders> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Record4orders last() {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Record4orders last(Record4ordersDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Record4orders last(String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Record4orders last(final Record4ordersDAO DAO, final String TABLENAME2) {
        Record4orders result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Record4ordersDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Record4ordersDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Record4ordersDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Record4orders> getGtKey(int id) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Record4orders> getGtKey(Record4ordersDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Record4orders> getGtKey(int id, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Record4orders> getGtKey(final Record4ordersDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4orders> result = newList();
            List<Record4orders> record4orderss = getAll();
            for (Record4orders record4orders : record4orderss) {
                if(record4orders.id <= id) continue;
                result.add(record4orders);
            }
            return result;
        }
    }

    public static Record4orders getByKey(int id) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Record4orders> asyncGetByKey(final int id) {
        Future<Record4orders> f = Async.exec(new Callable<Record4orders>() {
            public Record4orders call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Record4orders getByKey(Record4ordersDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Record4orders getByKey(int id, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Record4orders getByKey(final Record4ordersDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Record4orders getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Record4orders deleteFromMemory(final int id) {
        Record4orders record4orders;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            record4orders = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(record4orders);
        } else {
            record4orders = vars.remove(id);
        }
        if(record4orders == null) return null;

        return record4orders;
    }

    public static List<Record4orders> getByPage(int page, int size) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Record4orders> getByPage(Record4ordersDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Record4orders> getByPage(int page, int size, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Record4orders> getByPage(final Record4ordersDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Record4orders> result = newList();
            List<Record4orders> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Record4ordersDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Record4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Record4ordersDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Record4orders update(Record4orders record4orders) {
        Record4ordersDAO DAO = DAO();
        return update(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders update(Record4ordersDAO DAO, Record4orders record4orders) {
        return update(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders update(Record4orders record4orders, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return update(DAO, record4orders, TABLENAME2);
    }

    public static Record4orders update(final Record4ordersDAO DAO, final Record4orders record4orders,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(record4orders, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(record4orders, TABLENAME2);
            if(n == -1) 
                return record4orders;
            else if(n <= 0) 
                return null;
        }
        return record4orders;
    }

    public static Record4orders asyncUpdate(Record4orders record4orders) {
        Record4ordersDAO DAO = DAO();
        return asyncUpdate(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders asyncUpdate(Record4ordersDAO DAO, Record4orders record4orders) {
        return asyncUpdate(DAO, record4orders, DAO.TABLENAME);
    }

    public static Record4orders asyncUpdate(Record4orders record4orders, String TABLENAME2) {
        Record4ordersDAO DAO = DAO();
        return asyncUpdate(DAO, record4orders, TABLENAME2);
    }

    public static Record4orders asyncUpdate(final Record4ordersDAO DAO, final Record4orders record4orders,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(record4orders, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(record4orders, TABLENAME2);
        }
        return record4orders;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Record4ordersDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Record4ordersDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Record4ordersDAO DAO, final String TABLENAME2) {
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

        List<Record4orders> record4orderss = getAll();
        for (Record4orders record4orders : record4orderss) {
            int n = DAO.insert2(record4orders, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
