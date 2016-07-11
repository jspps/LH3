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

import static com.learnhall.db.bean.Recordkbi4customer.Col;

//learnhall3_design - recordkbi4customer
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Recordkbi4customerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Recordkbi4customerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Recordkbi4customerInternal(){}

    public static Recordkbi4customerDAO DAO(){
        return Recordkbi4customerEntity.Recordkbi4customerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Recordkbi4customer[MAX];
    }
    private static Recordkbi4customer[] FIXED = new Recordkbi4customer[MAX];
    public static final Map<Integer, Recordkbi4customer> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeCustid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustid = newSortedMap();

    private static final List<Recordkbi4customer> fixedCache = newList();

    public static void put(Recordkbi4customer recordkbi4customer, boolean force){
        if(recordkbi4customer == null || recordkbi4customer.id <= 0) return ;

        int id = recordkbi4customer.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(recordkbi4customer))
                	FIXED[id - 1] = recordkbi4customer;
                if (!fixedCache.contains(recordkbi4customer))
                	fixedCache.add(recordkbi4customer);
            }
        } else {
            vars.put(id, recordkbi4customer);
        }

        { // type_custid
          boolean bChanged = recordkbi4customer.inChanged(Col.type, Col.custid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vtype = recordkbi4customer.oldOrNew(Col.type);
              Object vcustid = recordkbi4customer.oldOrNew(Col.custid);
              String okey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid).e();
              Set m1 = varsByTypeCustid.get(okey);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                       varsByTypeCustid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vtype = recordkbi4customer.getType();
              int vcustid = recordkbi4customer.getCustid();
              String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid).e();
              Set m1 = varsByTypeCustid.get(vkey);
              if(m1 == null){
                  m1 = newSortedSet();
                  varsByTypeCustid.put(vkey, m1);
              }
              m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index custid
          Object ov = recordkbi4customer.oldVal(Col.custid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByCustid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByCustid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int custid = recordkbi4customer.getCustid();
            Set m2 = varsByCustid.get(custid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByCustid.put(custid, m2);
            }
            m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTypeCustid.clear();
        varsByCustid.clear();
        FIXED = new Recordkbi4customer[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Recordkbi4customerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Recordkbi4customerDAO DAO, String TABLENAME2){
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

    public static void relocate(Recordkbi4customerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Recordkbi4customerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Recordkbi4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Recordkbi4customerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Recordkbi4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Recordkbi4customerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Recordkbi4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Recordkbi4customerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Recordkbi4customerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Recordkbi4customerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Recordkbi4customerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Recordkbi4customerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Recordkbi4customerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Recordkbi4customer[maxId(DAO)];

        List<Recordkbi4customer> recordkbi4customers = DAO.selectAll();
        for (Recordkbi4customer recordkbi4customer : recordkbi4customers) {
            recordkbi4customer.reset();
            put(recordkbi4customer, true);
        }
    }

    public static Map toMap(Recordkbi4customer recordkbi4customer){
        return recordkbi4customer.toMap();
    }

    public static List<Map> toMap(List<Recordkbi4customer> recordkbi4customers){
        List<Map> ret = new Vector<Map>();
        for (Recordkbi4customer recordkbi4customer : recordkbi4customers){
            Map e = recordkbi4customer.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Recordkbi4customer> sortZh(List<Recordkbi4customer> recordkbi4customers, final String key) {
        Collections.sort(recordkbi4customers, new Comparator<Recordkbi4customer>() {
            public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return recordkbi4customers;
    }

    public static List<Recordkbi4customer> sort(List<Recordkbi4customer> recordkbi4customers, final String key) {
        Collections.sort(recordkbi4customers, new Comparator<Recordkbi4customer>() {
            public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
                return o1.compareTo(o2, key);
            }
        });
        return recordkbi4customers;
    }

    public static List<Recordkbi4customer> sort(List<Recordkbi4customer> recordkbi4customers){
        Collections.sort(recordkbi4customers, new Comparator<Recordkbi4customer>(){
            public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return recordkbi4customers;
    }

    public static List<Recordkbi4customer> sortReverse(List<Recordkbi4customer> recordkbi4customers){
        Collections.sort(recordkbi4customers, new Comparator<Recordkbi4customer>(){
            public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return recordkbi4customers;
    }

    public static List<Recordkbi4customer> sortCustid(List<Recordkbi4customer> recordkbi4customers){
        Collections.sort(recordkbi4customers, new Comparator<Recordkbi4customer>(){
            public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
                Object v1 = o1.getCustid();
                Object v2 = o2.getCustid();
                return compareTo(v1, v2);
            }
        });
        return recordkbi4customers;
    }

    public static List<Recordkbi4customer> sortCustidRo(List<Recordkbi4customer> recordkbi4customers){
        Collections.sort(recordkbi4customers, new Comparator<Recordkbi4customer>(){
            public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
                Object v1 = o1.getCustid();
                Object v2 = o2.getCustid();
                return 0 - compareTo(v1, v2);
            };
        });
        return recordkbi4customers;
    }

    public static Recordkbi4customer insert(Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = DAO();
        return insert(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer insert(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer) {
        return insert(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer insert(Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return insert(DAO, recordkbi4customer, TABLENAME2);
    }

    public static Recordkbi4customer insert(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(recordkbi4customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recordkbi4customer.id = n;
        if(cacheModel != NO_CACHE) put(recordkbi4customer, true);

        return recordkbi4customer;
    }

    public static Recordkbi4customer asyncInsert(Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = DAO();
        return asyncInsert(DAO, recordkbi4customer, DAO.TABLENAME);
    }
    public static Recordkbi4customer asyncInsert(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer) {
        return asyncInsert(DAO, recordkbi4customer, DAO.TABLENAME);
    }
    public static Recordkbi4customer asyncInsert(Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return asyncInsert(DAO, recordkbi4customer, TABLENAME2);
    }
    public static Recordkbi4customer asyncInsert(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(recordkbi4customer, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        recordkbi4customer.id = n;
        if(cacheModel != NO_CACHE) put(recordkbi4customer, true);
        return recordkbi4customer;
    }
    public static Recordkbi4customer insert2(Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = DAO();
        return insert2(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer insert2(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer) {
        return insert2(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer insert2(Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return insert2(DAO, recordkbi4customer, TABLENAME2);
    }

    public static Recordkbi4customer insert2(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(recordkbi4customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recordkbi4customer.id = n;
        if(cacheModel != NO_CACHE) put(recordkbi4customer, true);

        return recordkbi4customer;
    }

    public static Recordkbi4customer asyncInsert2(Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = DAO();
        return asyncInsert2(DAO, recordkbi4customer, DAO.TABLENAME);
    }
    public static Recordkbi4customer asyncInsert2(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer) {
        return asyncInsert2(DAO, recordkbi4customer, DAO.TABLENAME);
    }
    public static Recordkbi4customer asyncInsert2(Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return asyncInsert2(DAO, recordkbi4customer, TABLENAME2);
    }
    public static Recordkbi4customer asyncInsert2(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        recordkbi4customer.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(recordkbi4customer, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(recordkbi4customer, true);
        return recordkbi4customer;
    }
    public static int[] insert(List<Recordkbi4customer> recordkbi4customers) {
        Recordkbi4customerDAO DAO = DAO();
        return insert(DAO, recordkbi4customers, DAO.TABLENAME);
    }

    public static int[] insert(Recordkbi4customerDAO DAO, List<Recordkbi4customer> recordkbi4customers) {
        return insert(DAO, recordkbi4customers, DAO.TABLENAME);
    }

    public static int[] insert(List<Recordkbi4customer> recordkbi4customers, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return insert(DAO, recordkbi4customers, TABLENAME2);
    }

    public static int[] insert(Recordkbi4customerDAO DAO, List<Recordkbi4customer> recordkbi4customers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(recordkbi4customers, TABLENAME2);
            int n = 0;
            for(Recordkbi4customer recordkbi4customer : recordkbi4customers){
                recordkbi4customer.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[recordkbi4customers.size()];
        int n = 0;
        for(Recordkbi4customer recordkbi4customer : recordkbi4customers){
            recordkbi4customer = insert(DAO, recordkbi4customer, TABLENAME2);
            ret[n++] = (recordkbi4customer == null) ? 0 : (int)recordkbi4customer.id;
        }
        return ret;
    }

    public static int delete(Recordkbi4customer recordkbi4customer) {
        int id = recordkbi4customer.id;
        Recordkbi4customerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Recordkbi4customerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Recordkbi4customerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Recordkbi4customerDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Recordkbi4customer recordkbi4customer) {
        int id = recordkbi4customer.id;
        Recordkbi4customerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Recordkbi4customerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Recordkbi4customerDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Recordkbi4customerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Recordkbi4customerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Recordkbi4customerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Recordkbi4customerDAO DAO, int[] ids,String TABLENAME2) {
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
        Recordkbi4customerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Recordkbi4customerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Recordkbi4customer> beans) {
        Recordkbi4customerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recordkbi4customer> beans, Recordkbi4customerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recordkbi4customer> beans, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Recordkbi4customer> beans, final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Recordkbi4customer recordkbi4customer : beans){
                int id = recordkbi4customer.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Recordkbi4customer> getAll() {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getAll(Recordkbi4customerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getAll(String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Recordkbi4customer> getAll(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordkbi4customer> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recordkbi4customer> getNoSortAll() {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getNoSortAll(Recordkbi4customerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getNoSortAll(String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Recordkbi4customer> getNoSortAll(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Recordkbi4customer> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordkbi4customer> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Recordkbi4customer recordkbi4customer = FIXED[i];
                if (recordkbi4customer != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Recordkbi4customer> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Recordkbi4customer> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Recordkbi4customerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
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
        Recordkbi4customerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Recordkbi4customerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Recordkbi4customer> getIn(List<Integer> keys) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getIn(List<Integer> keys, Recordkbi4customerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getIn(List<Integer> keys, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Recordkbi4customer> getIn(final List<Integer> keys, final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordkbi4customer> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recordkbi4customer> getNoSortIn(List<Integer> keys) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getNoSortIn(List<Integer> keys, Recordkbi4customerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Recordkbi4customer> getNoSortIn(final List<Integer> keys, final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Recordkbi4customer> result = newList();
            for (int key : keys) {
                Recordkbi4customer recordkbi4customer = getByKeyFromMemory(key);
                if( recordkbi4customer == null ) continue;
                result.add(recordkbi4customer);
            }
            return result;
        }
    }

    public static List<Recordkbi4customer> getLast(int num) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getLast(Recordkbi4customerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getLast(int num, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Recordkbi4customer> getLast(final Recordkbi4customerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Recordkbi4customer> result = newList();
            List<Recordkbi4customer> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Recordkbi4customer last() {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Recordkbi4customer last(Recordkbi4customerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Recordkbi4customer last(String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Recordkbi4customer last(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        Recordkbi4customer result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Recordkbi4customerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Recordkbi4customerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Recordkbi4customer> getGtKey(int id) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getGtKey(Recordkbi4customerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getGtKey(int id, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Recordkbi4customer> getGtKey(final Recordkbi4customerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordkbi4customer> result = newList();
            List<Recordkbi4customer> recordkbi4customers = getAll();
            for (Recordkbi4customer recordkbi4customer : recordkbi4customers) {
                if(recordkbi4customer.id <= id) continue;
                result.add(recordkbi4customer);
            }
            return result;
        }
    }

    public static Recordkbi4customer getByKey(int id) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Recordkbi4customer> asyncGetByKey(final int id) {
        Future<Recordkbi4customer> f = Async.exec(new Callable<Recordkbi4customer>() {
            public Recordkbi4customer call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Recordkbi4customer getByKey(Recordkbi4customerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Recordkbi4customer getByKey(int id, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Recordkbi4customer getByKey(final Recordkbi4customerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Recordkbi4customer getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Recordkbi4customer deleteFromMemory(final int id) {
        Recordkbi4customer recordkbi4customer;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            recordkbi4customer = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(recordkbi4customer);
        } else {
            recordkbi4customer = vars.remove(id);
        }
        if(recordkbi4customer == null) return null;

        { // type_custid
            int vtype = recordkbi4customer.getType();
            int vcustid = recordkbi4customer.getCustid();
            String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid).e();
            Set m1 = varsByTypeCustid.get(vkey);
            if(m1 != null) { 
                m1.remove(id);
                if(m1.isEmpty())
                    varsByTypeCustid.remove(vkey);
            }
        }

        int custid = recordkbi4customer.getCustid();
        Set m2 = varsByCustid.get(custid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByCustid.remove(custid);
        }

        return recordkbi4customer;
    }

    public static List<Recordkbi4customer> getByPage(int page, int size) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getByPage(Recordkbi4customerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getByPage(int page, int size, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Recordkbi4customer> getByPage(final Recordkbi4customerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordkbi4customer> result = newList();
            List<Recordkbi4customer> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Recordkbi4customerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Recordkbi4customerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByTypeCustid(Integer type, Integer custid) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static int countByTypeCustid(Recordkbi4customerDAO DAO, Integer type, Integer custid) {
        return countByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static int countByTypeCustid(Integer type, Integer custid, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeCustid(DAO, type, custid, TABLENAME2);
    }

    public static int countByTypeCustid(final Recordkbi4customerDAO DAO, Integer type, Integer custid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeCustid(type, custid, TABLENAME2);
        }
        List<Recordkbi4customer> recordkbi4customers = getByTypeCustid(type, custid, TABLENAME2);
        return recordkbi4customers.size();
    }

    public static List<Recordkbi4customer> getByTypeCustid(Integer type, Integer custid) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getByTypeCustid(Recordkbi4customerDAO DAO, Integer type, Integer custid) {
        return getByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getByTypeCustid(Integer type, Integer custid, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeCustid(DAO, type, custid, TABLENAME2);
    }

    public static List<Recordkbi4customer> getByTypeCustid(final Recordkbi4customerDAO DAO, Integer type, Integer custid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeCustid(type, custid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Recordkbi4customer> result = newList();
            String vkey = type+"-"+custid;
            Set<Integer> m1 = varsByTypeCustid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Recordkbi4customer e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                int _custid = e.getCustid();
                String _key = com.bowlong.lang.PStr.b().a(_type, "-", _custid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByCustid(Integer custid) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static int countByCustid(Recordkbi4customerDAO DAO, Integer custid) {
        return countByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static int countByCustid(Integer custid, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustid(DAO, custid, TABLENAME2);
    }

    public static int countByCustid(final Recordkbi4customerDAO DAO, final Integer custid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustid(custid, TABLENAME2);
        }
        List<Recordkbi4customer> recordkbi4customers = getByCustid(DAO, custid, TABLENAME2);
        return recordkbi4customers.size();
    }

    public static List<Recordkbi4customer> getByCustid(Integer custid) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getByCustid(Recordkbi4customerDAO DAO, Integer custid) {
        return getByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static List<Recordkbi4customer> getByCustid(Integer custid, String TABLENAME2) {
        Recordkbi4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustid(DAO, custid, TABLENAME2);
    }

    public static List<Recordkbi4customer> getByCustid(final Recordkbi4customerDAO DAO, final Integer custid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustid(custid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Recordkbi4customer> result = newList();
            Set<Integer> m1 = varsByCustid.get(custid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Recordkbi4customer e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _custid = e.getCustid();
                if(_custid != custid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Recordkbi4customer update(Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = DAO();
        return update(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer update(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer) {
        return update(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer update(Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return update(DAO, recordkbi4customer, TABLENAME2);
    }

    public static Recordkbi4customer update(final Recordkbi4customerDAO DAO, final Recordkbi4customer recordkbi4customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recordkbi4customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(recordkbi4customer, TABLENAME2);
            if(n == -1) 
                return recordkbi4customer;
            else if(n <= 0) 
                return null;
        }
        return recordkbi4customer;
    }

    public static Recordkbi4customer asyncUpdate(Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = DAO();
        return asyncUpdate(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer asyncUpdate(Recordkbi4customerDAO DAO, Recordkbi4customer recordkbi4customer) {
        return asyncUpdate(DAO, recordkbi4customer, DAO.TABLENAME);
    }

    public static Recordkbi4customer asyncUpdate(Recordkbi4customer recordkbi4customer, String TABLENAME2) {
        Recordkbi4customerDAO DAO = DAO();
        return asyncUpdate(DAO, recordkbi4customer, TABLENAME2);
    }

    public static Recordkbi4customer asyncUpdate(final Recordkbi4customerDAO DAO, final Recordkbi4customer recordkbi4customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recordkbi4customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(recordkbi4customer, TABLENAME2);
        }
        return recordkbi4customer;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Recordkbi4customerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Recordkbi4customerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Recordkbi4customerDAO DAO, final String TABLENAME2) {
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

        List<Recordkbi4customer> recordkbi4customers = getAll();
        for (Recordkbi4customer recordkbi4customer : recordkbi4customers) {
            int n = DAO.insert2(recordkbi4customer, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
