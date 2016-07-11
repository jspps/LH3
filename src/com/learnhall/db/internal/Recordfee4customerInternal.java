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

import static com.learnhall.db.bean.Recordfee4customer.Col;

//learnhall3_design - recordfee4customer
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Recordfee4customerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Recordfee4customerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Recordfee4customerInternal(){}

    public static Recordfee4customerDAO DAO(){
        return Recordfee4customerEntity.Recordfee4customerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Recordfee4customer[MAX];
    }
    private static Recordfee4customer[] FIXED = new Recordfee4customer[MAX];
    public static final Map<Integer, Recordfee4customer> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeCustidKindid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeCustid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustid = newSortedMap();

    private static final List<Recordfee4customer> fixedCache = newList();

    public static void put(Recordfee4customer recordfee4customer, boolean force){
        if(recordfee4customer == null || recordfee4customer.id <= 0) return ;

        int id = recordfee4customer.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(recordfee4customer))
                	FIXED[id - 1] = recordfee4customer;
                if (!fixedCache.contains(recordfee4customer))
                	fixedCache.add(recordfee4customer);
            }
        } else {
            vars.put(id, recordfee4customer);
        }

        { // type_custid_kindid
          boolean bChanged = recordfee4customer.inChanged(Col.type, Col.custid, Col.kindid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vtype = recordfee4customer.oldOrNew(Col.type);
              Object vcustid = recordfee4customer.oldOrNew(Col.custid);
              Object vkindid = recordfee4customer.oldOrNew(Col.kindid);
              String okey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid, "-", vkindid).e();
              Set m1 = varsByTypeCustidKindid.get(okey);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                       varsByTypeCustidKindid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vtype = recordfee4customer.getType();
              int vcustid = recordfee4customer.getCustid();
              int vkindid = recordfee4customer.getKindid();
              String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid, "-", vkindid).e();
              Set m1 = varsByTypeCustidKindid.get(vkey);
              if(m1 == null){
                  m1 = newSortedSet();
                  varsByTypeCustidKindid.put(vkey, m1);
              }
              m1.add(id);
          }
        }

        { // type_custid
          boolean bChanged = recordfee4customer.inChanged(Col.type, Col.custid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vtype = recordfee4customer.oldOrNew(Col.type);
              Object vcustid = recordfee4customer.oldOrNew(Col.custid);
              String okey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid).e();
              Set m2 = varsByTypeCustid.get(okey);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                       varsByTypeCustid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vtype = recordfee4customer.getType();
              int vcustid = recordfee4customer.getCustid();
              String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid).e();
              Set m2 = varsByTypeCustid.get(vkey);
              if(m2 == null){
                  m2 = newSortedSet();
                  varsByTypeCustid.put(vkey, m2);
              }
              m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index custid
          Object ov = recordfee4customer.oldVal(Col.custid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByCustid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByCustid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int custid = recordfee4customer.getCustid();
            Set m3 = varsByCustid.get(custid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByCustid.put(custid, m3);
            }
            m3.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTypeCustidKindid.clear();
        varsByTypeCustid.clear();
        varsByCustid.clear();
        FIXED = new Recordfee4customer[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Recordfee4customerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Recordfee4customerDAO DAO, String TABLENAME2){
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

    public static void relocate(Recordfee4customerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Recordfee4customerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Recordfee4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Recordfee4customerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Recordfee4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Recordfee4customerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Recordfee4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Recordfee4customerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Recordfee4customerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Recordfee4customerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Recordfee4customerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Recordfee4customerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Recordfee4customerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Recordfee4customer[maxId(DAO)];

        List<Recordfee4customer> recordfee4customers = DAO.selectAll();
        for (Recordfee4customer recordfee4customer : recordfee4customers) {
            recordfee4customer.reset();
            put(recordfee4customer, true);
        }
    }

    public static Map toMap(Recordfee4customer recordfee4customer){
        return recordfee4customer.toMap();
    }

    public static List<Map> toMap(List<Recordfee4customer> recordfee4customers){
        List<Map> ret = new Vector<Map>();
        for (Recordfee4customer recordfee4customer : recordfee4customers){
            Map e = recordfee4customer.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Recordfee4customer> sortZh(List<Recordfee4customer> recordfee4customers, final String key) {
        Collections.sort(recordfee4customers, new Comparator<Recordfee4customer>() {
            public int compare(Recordfee4customer o1, Recordfee4customer o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return recordfee4customers;
    }

    public static List<Recordfee4customer> sort(List<Recordfee4customer> recordfee4customers, final String key) {
        Collections.sort(recordfee4customers, new Comparator<Recordfee4customer>() {
            public int compare(Recordfee4customer o1, Recordfee4customer o2) {
                return o1.compareTo(o2, key);
            }
        });
        return recordfee4customers;
    }

    public static List<Recordfee4customer> sort(List<Recordfee4customer> recordfee4customers){
        Collections.sort(recordfee4customers, new Comparator<Recordfee4customer>(){
            public int compare(Recordfee4customer o1, Recordfee4customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return recordfee4customers;
    }

    public static List<Recordfee4customer> sortReverse(List<Recordfee4customer> recordfee4customers){
        Collections.sort(recordfee4customers, new Comparator<Recordfee4customer>(){
            public int compare(Recordfee4customer o1, Recordfee4customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return recordfee4customers;
    }

    public static List<Recordfee4customer> sortCustid(List<Recordfee4customer> recordfee4customers){
        Collections.sort(recordfee4customers, new Comparator<Recordfee4customer>(){
            public int compare(Recordfee4customer o1, Recordfee4customer o2) {
                Object v1 = o1.getCustid();
                Object v2 = o2.getCustid();
                return compareTo(v1, v2);
            }
        });
        return recordfee4customers;
    }

    public static List<Recordfee4customer> sortCustidRo(List<Recordfee4customer> recordfee4customers){
        Collections.sort(recordfee4customers, new Comparator<Recordfee4customer>(){
            public int compare(Recordfee4customer o1, Recordfee4customer o2) {
                Object v1 = o1.getCustid();
                Object v2 = o2.getCustid();
                return 0 - compareTo(v1, v2);
            };
        });
        return recordfee4customers;
    }

    public static Recordfee4customer insert(Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = DAO();
        return insert(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer insert(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer) {
        return insert(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer insert(Recordfee4customer recordfee4customer, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return insert(DAO, recordfee4customer, TABLENAME2);
    }

    public static Recordfee4customer insert(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(recordfee4customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recordfee4customer.id = n;
        if(cacheModel != NO_CACHE) put(recordfee4customer, true);

        return recordfee4customer;
    }

    public static Recordfee4customer asyncInsert(Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = DAO();
        return asyncInsert(DAO, recordfee4customer, DAO.TABLENAME);
    }
    public static Recordfee4customer asyncInsert(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer) {
        return asyncInsert(DAO, recordfee4customer, DAO.TABLENAME);
    }
    public static Recordfee4customer asyncInsert(Recordfee4customer recordfee4customer, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return asyncInsert(DAO, recordfee4customer, TABLENAME2);
    }
    public static Recordfee4customer asyncInsert(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(recordfee4customer, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        recordfee4customer.id = n;
        if(cacheModel != NO_CACHE) put(recordfee4customer, true);
        return recordfee4customer;
    }
    public static Recordfee4customer insert2(Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = DAO();
        return insert2(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer insert2(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer) {
        return insert2(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer insert2(Recordfee4customer recordfee4customer, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return insert2(DAO, recordfee4customer, TABLENAME2);
    }

    public static Recordfee4customer insert2(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(recordfee4customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recordfee4customer.id = n;
        if(cacheModel != NO_CACHE) put(recordfee4customer, true);

        return recordfee4customer;
    }

    public static Recordfee4customer asyncInsert2(Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = DAO();
        return asyncInsert2(DAO, recordfee4customer, DAO.TABLENAME);
    }
    public static Recordfee4customer asyncInsert2(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer) {
        return asyncInsert2(DAO, recordfee4customer, DAO.TABLENAME);
    }
    public static Recordfee4customer asyncInsert2(Recordfee4customer recordfee4customer, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return asyncInsert2(DAO, recordfee4customer, TABLENAME2);
    }
    public static Recordfee4customer asyncInsert2(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        recordfee4customer.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(recordfee4customer, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(recordfee4customer, true);
        return recordfee4customer;
    }
    public static int[] insert(List<Recordfee4customer> recordfee4customers) {
        Recordfee4customerDAO DAO = DAO();
        return insert(DAO, recordfee4customers, DAO.TABLENAME);
    }

    public static int[] insert(Recordfee4customerDAO DAO, List<Recordfee4customer> recordfee4customers) {
        return insert(DAO, recordfee4customers, DAO.TABLENAME);
    }

    public static int[] insert(List<Recordfee4customer> recordfee4customers, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return insert(DAO, recordfee4customers, TABLENAME2);
    }

    public static int[] insert(Recordfee4customerDAO DAO, List<Recordfee4customer> recordfee4customers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(recordfee4customers, TABLENAME2);
            int n = 0;
            for(Recordfee4customer recordfee4customer : recordfee4customers){
                recordfee4customer.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[recordfee4customers.size()];
        int n = 0;
        for(Recordfee4customer recordfee4customer : recordfee4customers){
            recordfee4customer = insert(DAO, recordfee4customer, TABLENAME2);
            ret[n++] = (recordfee4customer == null) ? 0 : (int)recordfee4customer.id;
        }
        return ret;
    }

    public static int delete(Recordfee4customer recordfee4customer) {
        int id = recordfee4customer.id;
        Recordfee4customerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Recordfee4customerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Recordfee4customerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Recordfee4customerDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Recordfee4customer recordfee4customer) {
        int id = recordfee4customer.id;
        Recordfee4customerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Recordfee4customerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Recordfee4customerDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Recordfee4customerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Recordfee4customerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Recordfee4customerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Recordfee4customerDAO DAO, int[] ids,String TABLENAME2) {
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
        Recordfee4customerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Recordfee4customerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Recordfee4customer> beans) {
        Recordfee4customerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recordfee4customer> beans, Recordfee4customerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recordfee4customer> beans, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Recordfee4customer> beans, final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Recordfee4customer recordfee4customer : beans){
                int id = recordfee4customer.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Recordfee4customer> getAll() {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getAll(Recordfee4customerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getAll(String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Recordfee4customer> getAll(final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordfee4customer> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recordfee4customer> getNoSortAll() {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getNoSortAll(Recordfee4customerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getNoSortAll(String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Recordfee4customer> getNoSortAll(final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Recordfee4customer> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordfee4customer> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Recordfee4customer recordfee4customer = FIXED[i];
                if (recordfee4customer != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Recordfee4customer> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Recordfee4customer> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Recordfee4customerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Recordfee4customerDAO DAO, final String TABLENAME2) {
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
        Recordfee4customerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Recordfee4customerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Recordfee4customerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Recordfee4customer> getIn(List<Integer> keys) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getIn(List<Integer> keys, Recordfee4customerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getIn(List<Integer> keys, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Recordfee4customer> getIn(final List<Integer> keys, final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordfee4customer> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recordfee4customer> getNoSortIn(List<Integer> keys) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getNoSortIn(List<Integer> keys, Recordfee4customerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Recordfee4customer> getNoSortIn(final List<Integer> keys, final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Recordfee4customer> result = newList();
            for (int key : keys) {
                Recordfee4customer recordfee4customer = getByKeyFromMemory(key);
                if( recordfee4customer == null ) continue;
                result.add(recordfee4customer);
            }
            return result;
        }
    }

    public static List<Recordfee4customer> getLast(int num) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getLast(Recordfee4customerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getLast(int num, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Recordfee4customer> getLast(final Recordfee4customerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Recordfee4customer> result = newList();
            List<Recordfee4customer> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Recordfee4customer last() {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Recordfee4customer last(Recordfee4customerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Recordfee4customer last(String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Recordfee4customer last(final Recordfee4customerDAO DAO, final String TABLENAME2) {
        Recordfee4customer result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Recordfee4customerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Recordfee4customerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Recordfee4customerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Recordfee4customer> getGtKey(int id) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getGtKey(Recordfee4customerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getGtKey(int id, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Recordfee4customer> getGtKey(final Recordfee4customerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordfee4customer> result = newList();
            List<Recordfee4customer> recordfee4customers = getAll();
            for (Recordfee4customer recordfee4customer : recordfee4customers) {
                if(recordfee4customer.id <= id) continue;
                result.add(recordfee4customer);
            }
            return result;
        }
    }

    public static Recordfee4customer getByKey(int id) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Recordfee4customer> asyncGetByKey(final int id) {
        Future<Recordfee4customer> f = Async.exec(new Callable<Recordfee4customer>() {
            public Recordfee4customer call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Recordfee4customer getByKey(Recordfee4customerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Recordfee4customer getByKey(int id, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Recordfee4customer getByKey(final Recordfee4customerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Recordfee4customer getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Recordfee4customer deleteFromMemory(final int id) {
        Recordfee4customer recordfee4customer;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            recordfee4customer = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(recordfee4customer);
        } else {
            recordfee4customer = vars.remove(id);
        }
        if(recordfee4customer == null) return null;

        { // type_custid_kindid
            int vtype = recordfee4customer.getType();
            int vcustid = recordfee4customer.getCustid();
            int vkindid = recordfee4customer.getKindid();
            String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid, "-", vkindid).e();
            Set m1 = varsByTypeCustidKindid.get(vkey);
            if(m1 != null) { 
                m1.remove(id);
                if(m1.isEmpty())
                    varsByTypeCustidKindid.remove(vkey);
            }
        }

        { // type_custid
            int vtype = recordfee4customer.getType();
            int vcustid = recordfee4customer.getCustid();
            String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vcustid).e();
            Set m2 = varsByTypeCustid.get(vkey);
            if(m2 != null) { 
                m2.remove(id);
                if(m2.isEmpty())
                    varsByTypeCustid.remove(vkey);
            }
        }

        int custid = recordfee4customer.getCustid();
        Set m3 = varsByCustid.get(custid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByCustid.remove(custid);
        }

        return recordfee4customer;
    }

    public static List<Recordfee4customer> getByPage(int page, int size) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByPage(Recordfee4customerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByPage(int page, int size, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Recordfee4customer> getByPage(final Recordfee4customerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordfee4customer> result = newList();
            List<Recordfee4customer> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Recordfee4customerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Recordfee4customerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByTypeCustidKindid(Integer type, Integer custid, Integer kindid) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeCustidKindid(DAO, type, custid, kindid, DAO.TABLENAME);
    }

    public static int countByTypeCustidKindid(Recordfee4customerDAO DAO, Integer type, Integer custid, Integer kindid) {
        return countByTypeCustidKindid(DAO, type, custid, kindid, DAO.TABLENAME);
    }

    public static int countByTypeCustidKindid(Integer type, Integer custid, Integer kindid, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeCustidKindid(DAO, type, custid, kindid, TABLENAME2);
    }

    public static int countByTypeCustidKindid(final Recordfee4customerDAO DAO, Integer type, Integer custid, Integer kindid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeCustidKindid(type, custid, kindid, TABLENAME2);
        }
        List<Recordfee4customer> recordfee4customers = getByTypeCustidKindid(type, custid, kindid, TABLENAME2);
        return recordfee4customers.size();
    }

    public static List<Recordfee4customer> getByTypeCustidKindid(Integer type, Integer custid, Integer kindid) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeCustidKindid(DAO, type, custid, kindid, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByTypeCustidKindid(Recordfee4customerDAO DAO, Integer type, Integer custid, Integer kindid) {
        return getByTypeCustidKindid(DAO, type, custid, kindid, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByTypeCustidKindid(Integer type, Integer custid, Integer kindid, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeCustidKindid(DAO, type, custid, kindid, TABLENAME2);
    }

    public static List<Recordfee4customer> getByTypeCustidKindid(final Recordfee4customerDAO DAO, Integer type, Integer custid, Integer kindid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeCustidKindid(type, custid, kindid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Recordfee4customer> result = newList();
            String vkey = type+"-"+custid+"-"+kindid;
            Set<Integer> m1 = varsByTypeCustidKindid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Recordfee4customer e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                int _custid = e.getCustid();
                int _kindid = e.getKindid();
                String _key = com.bowlong.lang.PStr.b().a(_type, "-", _custid, "-", _kindid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByTypeCustid(Integer type, Integer custid) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static int countByTypeCustid(Recordfee4customerDAO DAO, Integer type, Integer custid) {
        return countByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static int countByTypeCustid(Integer type, Integer custid, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeCustid(DAO, type, custid, TABLENAME2);
    }

    public static int countByTypeCustid(final Recordfee4customerDAO DAO, Integer type, Integer custid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeCustid(type, custid, TABLENAME2);
        }
        List<Recordfee4customer> recordfee4customers = getByTypeCustid(type, custid, TABLENAME2);
        return recordfee4customers.size();
    }

    public static List<Recordfee4customer> getByTypeCustid(Integer type, Integer custid) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByTypeCustid(Recordfee4customerDAO DAO, Integer type, Integer custid) {
        return getByTypeCustid(DAO, type, custid, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByTypeCustid(Integer type, Integer custid, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeCustid(DAO, type, custid, TABLENAME2);
    }

    public static List<Recordfee4customer> getByTypeCustid(final Recordfee4customerDAO DAO, Integer type, Integer custid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeCustid(type, custid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Recordfee4customer> result = newList();
            String vkey = type+"-"+custid;
            Set<Integer> m1 = varsByTypeCustid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Recordfee4customer e = getByKey(DAO, key, TABLENAME2);
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
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static int countByCustid(Recordfee4customerDAO DAO, Integer custid) {
        return countByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static int countByCustid(Integer custid, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustid(DAO, custid, TABLENAME2);
    }

    public static int countByCustid(final Recordfee4customerDAO DAO, final Integer custid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustid(custid, TABLENAME2);
        }
        List<Recordfee4customer> recordfee4customers = getByCustid(DAO, custid, TABLENAME2);
        return recordfee4customers.size();
    }

    public static List<Recordfee4customer> getByCustid(Integer custid) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByCustid(Recordfee4customerDAO DAO, Integer custid) {
        return getByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static List<Recordfee4customer> getByCustid(Integer custid, String TABLENAME2) {
        Recordfee4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustid(DAO, custid, TABLENAME2);
    }

    public static List<Recordfee4customer> getByCustid(final Recordfee4customerDAO DAO, final Integer custid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustid(custid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Recordfee4customer> result = newList();
            Set<Integer> m1 = varsByCustid.get(custid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Recordfee4customer e = getByKey(DAO, key, TABLENAME2);
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

    public static Recordfee4customer update(Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = DAO();
        return update(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer update(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer) {
        return update(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer update(Recordfee4customer recordfee4customer, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return update(DAO, recordfee4customer, TABLENAME2);
    }

    public static Recordfee4customer update(final Recordfee4customerDAO DAO, final Recordfee4customer recordfee4customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recordfee4customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(recordfee4customer, TABLENAME2);
            if(n == -1) 
                return recordfee4customer;
            else if(n <= 0) 
                return null;
        }
        return recordfee4customer;
    }

    public static Recordfee4customer asyncUpdate(Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = DAO();
        return asyncUpdate(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer asyncUpdate(Recordfee4customerDAO DAO, Recordfee4customer recordfee4customer) {
        return asyncUpdate(DAO, recordfee4customer, DAO.TABLENAME);
    }

    public static Recordfee4customer asyncUpdate(Recordfee4customer recordfee4customer, String TABLENAME2) {
        Recordfee4customerDAO DAO = DAO();
        return asyncUpdate(DAO, recordfee4customer, TABLENAME2);
    }

    public static Recordfee4customer asyncUpdate(final Recordfee4customerDAO DAO, final Recordfee4customer recordfee4customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recordfee4customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(recordfee4customer, TABLENAME2);
        }
        return recordfee4customer;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Recordfee4customerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Recordfee4customerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Recordfee4customerDAO DAO, final String TABLENAME2) {
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

        List<Recordfee4customer> recordfee4customers = getAll();
        for (Recordfee4customer recordfee4customer : recordfee4customers) {
            int n = DAO.insert2(recordfee4customer, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
