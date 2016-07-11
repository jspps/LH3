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

import static com.learnhall.db.bean.Openkind4customer.Col;

//learnhall3_design - openkind4customer
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Openkind4customerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Openkind4customerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Openkind4customerInternal(){}

    public static Openkind4customerDAO DAO(){
        return Openkind4customerEntity.Openkind4customerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Openkind4customer[MAX];
    }
    private static Openkind4customer[] FIXED = new Openkind4customer[MAX];
    public static final Map<Integer, Openkind4customer> vars = newSortedMap();
    public static final Map<String, Integer> varsByCustomeridKindid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKindid = newSortedMap();

    private static final List<Openkind4customer> fixedCache = newList();

    public static void put(Openkind4customer openkind4customer, boolean force){
        if(openkind4customer == null || openkind4customer.id <= 0) return ;

        int id = openkind4customer.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(openkind4customer))
                	FIXED[id - 1] = openkind4customer;
                if (!fixedCache.contains(openkind4customer))
                	fixedCache.add(openkind4customer);
            }
        } else {
            vars.put(id, openkind4customer);
        }

        { // custid_kindid
          boolean bChanged = openkind4customer.inChanged(Col.customerid, Col.kindid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vcustomerid = openkind4customer.oldOrNew(Col.customerid);
            Object vkindid = openkind4customer.oldOrNew(Col.kindid);
            String okey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
            varsByCustomeridKindid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vcustomerid = openkind4customer.getCustomerid();
              int vkindid = openkind4customer.getKindid();
              String vkey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
              varsByCustomeridKindid.put(vkey, id);
          }
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = openkind4customer.oldVal(Col.customerid);
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
            int customerid = openkind4customer.getCustomerid();
            Set m1 = varsByCustomerid.get(customerid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByCustomerid.put(customerid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kindid
          Object ov = openkind4customer.oldVal(Col.kindid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByKindid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByKindid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int kindid = openkind4customer.getKindid();
            Set m2 = varsByKindid.get(kindid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByKindid.put(kindid, m2);
            }
            m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustomeridKindid.clear();
        varsByCustomerid.clear();
        varsByKindid.clear();
        FIXED = new Openkind4customer[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Openkind4customerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Openkind4customerDAO DAO, String TABLENAME2){
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

    public static void relocate(Openkind4customerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Openkind4customerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Openkind4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Openkind4customerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Openkind4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Openkind4customerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Openkind4customerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Openkind4customerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Openkind4customerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Openkind4customerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Openkind4customerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Openkind4customerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Openkind4customerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Openkind4customer[maxId(DAO)];

        List<Openkind4customer> openkind4customers = DAO.selectAll();
        for (Openkind4customer openkind4customer : openkind4customers) {
            openkind4customer.reset();
            put(openkind4customer, true);
        }
    }

    public static Map toMap(Openkind4customer openkind4customer){
        return openkind4customer.toMap();
    }

    public static List<Map> toMap(List<Openkind4customer> openkind4customers){
        List<Map> ret = new Vector<Map>();
        for (Openkind4customer openkind4customer : openkind4customers){
            Map e = openkind4customer.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Openkind4customer> sortZh(List<Openkind4customer> openkind4customers, final String key) {
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>() {
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sort(List<Openkind4customer> openkind4customers, final String key) {
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>() {
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                return o1.compareTo(o2, key);
            }
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sort(List<Openkind4customer> openkind4customers){
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>(){
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sortReverse(List<Openkind4customer> openkind4customers){
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>(){
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sortCustomerid(List<Openkind4customer> openkind4customers){
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>(){
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sortCustomeridRo(List<Openkind4customer> openkind4customers){
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>(){
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sortKindid(List<Openkind4customer> openkind4customers){
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>(){
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return openkind4customers;
    }

    public static List<Openkind4customer> sortKindidRo(List<Openkind4customer> openkind4customers){
        Collections.sort(openkind4customers, new Comparator<Openkind4customer>(){
            public int compare(Openkind4customer o1, Openkind4customer o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return openkind4customers;
    }

    public static Openkind4customer insert(Openkind4customer openkind4customer) {
        Openkind4customerDAO DAO = DAO();
        return insert(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer insert(Openkind4customerDAO DAO, Openkind4customer openkind4customer) {
        return insert(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer insert(Openkind4customer openkind4customer, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return insert(DAO, openkind4customer, TABLENAME2);
    }

    public static Openkind4customer insert(Openkind4customerDAO DAO, Openkind4customer openkind4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(openkind4customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        openkind4customer.id = n;
        if(cacheModel != NO_CACHE) put(openkind4customer, true);

        return openkind4customer;
    }

    public static Openkind4customer asyncInsert(Openkind4customer openkind4customer) {
        Openkind4customerDAO DAO = DAO();
        return asyncInsert(DAO, openkind4customer, DAO.TABLENAME);
    }
    public static Openkind4customer asyncInsert(Openkind4customerDAO DAO, Openkind4customer openkind4customer) {
        return asyncInsert(DAO, openkind4customer, DAO.TABLENAME);
    }
    public static Openkind4customer asyncInsert(Openkind4customer openkind4customer, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return asyncInsert(DAO, openkind4customer, TABLENAME2);
    }
    public static Openkind4customer asyncInsert(Openkind4customerDAO DAO, Openkind4customer openkind4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(openkind4customer, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        openkind4customer.id = n;
        if(cacheModel != NO_CACHE) put(openkind4customer, true);
        return openkind4customer;
    }
    public static Openkind4customer insert2(Openkind4customer openkind4customer) {
        Openkind4customerDAO DAO = DAO();
        return insert2(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer insert2(Openkind4customerDAO DAO, Openkind4customer openkind4customer) {
        return insert2(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer insert2(Openkind4customer openkind4customer, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return insert2(DAO, openkind4customer, TABLENAME2);
    }

    public static Openkind4customer insert2(Openkind4customerDAO DAO, Openkind4customer openkind4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(openkind4customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        openkind4customer.id = n;
        if(cacheModel != NO_CACHE) put(openkind4customer, true);

        return openkind4customer;
    }

    public static Openkind4customer asyncInsert2(Openkind4customer openkind4customer) {
        Openkind4customerDAO DAO = DAO();
        return asyncInsert2(DAO, openkind4customer, DAO.TABLENAME);
    }
    public static Openkind4customer asyncInsert2(Openkind4customerDAO DAO, Openkind4customer openkind4customer) {
        return asyncInsert2(DAO, openkind4customer, DAO.TABLENAME);
    }
    public static Openkind4customer asyncInsert2(Openkind4customer openkind4customer, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return asyncInsert2(DAO, openkind4customer, TABLENAME2);
    }
    public static Openkind4customer asyncInsert2(Openkind4customerDAO DAO, Openkind4customer openkind4customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        openkind4customer.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(openkind4customer, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(openkind4customer, true);
        return openkind4customer;
    }
    public static int[] insert(List<Openkind4customer> openkind4customers) {
        Openkind4customerDAO DAO = DAO();
        return insert(DAO, openkind4customers, DAO.TABLENAME);
    }

    public static int[] insert(Openkind4customerDAO DAO, List<Openkind4customer> openkind4customers) {
        return insert(DAO, openkind4customers, DAO.TABLENAME);
    }

    public static int[] insert(List<Openkind4customer> openkind4customers, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return insert(DAO, openkind4customers, TABLENAME2);
    }

    public static int[] insert(Openkind4customerDAO DAO, List<Openkind4customer> openkind4customers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(openkind4customers, TABLENAME2);
            int n = 0;
            for(Openkind4customer openkind4customer : openkind4customers){
                openkind4customer.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[openkind4customers.size()];
        int n = 0;
        for(Openkind4customer openkind4customer : openkind4customers){
            openkind4customer = insert(DAO, openkind4customer, TABLENAME2);
            ret[n++] = (openkind4customer == null) ? 0 : (int)openkind4customer.id;
        }
        return ret;
    }

    public static int delete(Openkind4customer openkind4customer) {
        int id = openkind4customer.id;
        Openkind4customerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Openkind4customerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Openkind4customerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Openkind4customerDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Openkind4customer openkind4customer) {
        int id = openkind4customer.id;
        Openkind4customerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Openkind4customerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Openkind4customerDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Openkind4customerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Openkind4customerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Openkind4customerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Openkind4customerDAO DAO, int[] ids,String TABLENAME2) {
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
        Openkind4customerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Openkind4customerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Openkind4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Openkind4customer> beans) {
        Openkind4customerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Openkind4customer> beans, Openkind4customerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Openkind4customer> beans, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Openkind4customer> beans, final Openkind4customerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Openkind4customer openkind4customer : beans){
                int id = openkind4customer.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Openkind4customer> getAll() {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getAll(Openkind4customerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getAll(String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Openkind4customer> getAll(final Openkind4customerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4customer> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Openkind4customer> getNoSortAll() {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getNoSortAll(Openkind4customerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getNoSortAll(String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Openkind4customer> getNoSortAll(final Openkind4customerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Openkind4customer> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4customer> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Openkind4customer openkind4customer = FIXED[i];
                if (openkind4customer != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Openkind4customer> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Openkind4customer> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Openkind4customerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Openkind4customerDAO DAO, final String TABLENAME2) {
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
        Openkind4customerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Openkind4customerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Openkind4customerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Openkind4customer> getIn(List<Integer> keys) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getIn(List<Integer> keys, Openkind4customerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getIn(List<Integer> keys, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Openkind4customer> getIn(final List<Integer> keys, final Openkind4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4customer> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Openkind4customer> getNoSortIn(List<Integer> keys) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getNoSortIn(List<Integer> keys, Openkind4customerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Openkind4customer> getNoSortIn(final List<Integer> keys, final Openkind4customerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Openkind4customer> result = newList();
            for (int key : keys) {
                Openkind4customer openkind4customer = getByKeyFromMemory(key);
                if( openkind4customer == null ) continue;
                result.add(openkind4customer);
            }
            return result;
        }
    }

    public static List<Openkind4customer> getLast(int num) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getLast(Openkind4customerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getLast(int num, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Openkind4customer> getLast(final Openkind4customerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Openkind4customer> result = newList();
            List<Openkind4customer> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Openkind4customer last() {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Openkind4customer last(Openkind4customerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Openkind4customer last(String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Openkind4customer last(final Openkind4customerDAO DAO, final String TABLENAME2) {
        Openkind4customer result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Openkind4customerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Openkind4customerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Openkind4customerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Openkind4customer> getGtKey(int id) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getGtKey(Openkind4customerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getGtKey(int id, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Openkind4customer> getGtKey(final Openkind4customerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4customer> result = newList();
            List<Openkind4customer> openkind4customers = getAll();
            for (Openkind4customer openkind4customer : openkind4customers) {
                if(openkind4customer.id <= id) continue;
                result.add(openkind4customer);
            }
            return result;
        }
    }

    public static Openkind4customer getByKey(int id) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Openkind4customer> asyncGetByKey(final int id) {
        Future<Openkind4customer> f = Async.exec(new Callable<Openkind4customer>() {
            public Openkind4customer call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Openkind4customer getByKey(Openkind4customerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Openkind4customer getByKey(int id, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Openkind4customer getByKey(final Openkind4customerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Openkind4customer getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Openkind4customer deleteFromMemory(final int id) {
        Openkind4customer openkind4customer;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            openkind4customer = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(openkind4customer);
        } else {
            openkind4customer = vars.remove(id);
        }
        if(openkind4customer == null) return null;

        { // custid_kindid
            int vcustomerid = openkind4customer.getCustomerid();
            int vkindid = openkind4customer.getKindid();
            String vkey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
            varsByCustomeridKindid.remove(vkey);
        }

        int customerid = openkind4customer.getCustomerid();
        Set m1 = varsByCustomerid.get(customerid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int kindid = openkind4customer.getKindid();
        Set m2 = varsByKindid.get(kindid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByKindid.remove(kindid);
        }

        return openkind4customer;
    }

    public static List<Openkind4customer> getByPage(int page, int size) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getByPage(Openkind4customerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getByPage(int page, int size, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Openkind4customer> getByPage(final Openkind4customerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4customer> result = newList();
            List<Openkind4customer> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Openkind4customerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Openkind4customerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Openkind4customer getByCustomeridKindid(Integer customerid, Integer kindid) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static Openkind4customer getByCustomeridKindid(Openkind4customerDAO DAO, Integer customerid, Integer kindid) {
        return getByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static Openkind4customer getByCustomeridKindid(Integer customerid, Integer kindid, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomeridKindid(DAO, customerid, kindid, TABLENAME2);
    }

    public static Openkind4customer getByCustomeridKindid(final Openkind4customerDAO DAO, Integer customerid, Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomeridKindid(customerid, kindid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = customerid+"-"+kindid;
            Integer id = varsByCustomeridKindid.get(vkey);
            if(id == null) return null;
            Openkind4customer result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getCustomerid() != customerid){
                varsByCustomeridKindid.remove(vkey);
                return null;
            }
            if(result.getKindid() != kindid){
                varsByCustomeridKindid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static int countByCustomerid(Integer customerid) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Openkind4customerDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final Openkind4customerDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Openkind4customer> openkind4customers = getByCustomerid(DAO, customerid, TABLENAME2);
        return openkind4customers.size();
    }

    public static List<Openkind4customer> getByCustomerid(Integer customerid) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getByCustomerid(Openkind4customerDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getByCustomerid(Integer customerid, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Openkind4customer> getByCustomerid(final Openkind4customerDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Openkind4customer> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Openkind4customer e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByKindid(Integer kindid) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Openkind4customerDAO DAO, Integer kindid) {
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Integer kindid, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, TABLENAME2);
    }

    public static int countByKindid(final Openkind4customerDAO DAO, final Integer kindid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKindid(kindid, TABLENAME2);
        }
        List<Openkind4customer> openkind4customers = getByKindid(DAO, kindid, TABLENAME2);
        return openkind4customers.size();
    }

    public static List<Openkind4customer> getByKindid(Integer kindid) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getByKindid(Openkind4customerDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Openkind4customer> getByKindid(Integer kindid, String TABLENAME2) {
        Openkind4customerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static List<Openkind4customer> getByKindid(final Openkind4customerDAO DAO, final Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Openkind4customer> result = newList();
            Set<Integer> m1 = varsByKindid.get(kindid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Openkind4customer e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _kindid = e.getKindid();
                if(_kindid != kindid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Openkind4customer update(Openkind4customer openkind4customer) {
        Openkind4customerDAO DAO = DAO();
        return update(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer update(Openkind4customerDAO DAO, Openkind4customer openkind4customer) {
        return update(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer update(Openkind4customer openkind4customer, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return update(DAO, openkind4customer, TABLENAME2);
    }

    public static Openkind4customer update(final Openkind4customerDAO DAO, final Openkind4customer openkind4customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(openkind4customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(openkind4customer, TABLENAME2);
            if(n == -1) 
                return openkind4customer;
            else if(n <= 0) 
                return null;
        }
        return openkind4customer;
    }

    public static Openkind4customer asyncUpdate(Openkind4customer openkind4customer) {
        Openkind4customerDAO DAO = DAO();
        return asyncUpdate(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer asyncUpdate(Openkind4customerDAO DAO, Openkind4customer openkind4customer) {
        return asyncUpdate(DAO, openkind4customer, DAO.TABLENAME);
    }

    public static Openkind4customer asyncUpdate(Openkind4customer openkind4customer, String TABLENAME2) {
        Openkind4customerDAO DAO = DAO();
        return asyncUpdate(DAO, openkind4customer, TABLENAME2);
    }

    public static Openkind4customer asyncUpdate(final Openkind4customerDAO DAO, final Openkind4customer openkind4customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(openkind4customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(openkind4customer, TABLENAME2);
        }
        return openkind4customer;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Openkind4customerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Openkind4customerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Openkind4customerDAO DAO, final String TABLENAME2) {
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

        List<Openkind4customer> openkind4customers = getAll();
        for (Openkind4customer openkind4customer : openkind4customers) {
            int n = DAO.insert2(openkind4customer, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
