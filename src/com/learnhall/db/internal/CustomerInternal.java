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

import static com.learnhall.db.bean.Customer.Col;

//learnhall3_design - customer
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class CustomerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(CustomerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public CustomerInternal(){}

    public static CustomerDAO DAO(){
        return CustomerEntity.CustomerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Customer[MAX];
    }
    private static Customer[] FIXED = new Customer[MAX];
    public static final Map<Integer, Customer> vars = newSortedMap();
    public static final Map<Integer, Integer> varsByAccountid = newSortedMap();

    private static final List<Customer> fixedCache = newList();

    public static void put(Customer customer, boolean force){
        if(customer == null || customer.id <= 0) return ;

        int id = customer.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(customer))
                	FIXED[id - 1] = customer;
                if (!fixedCache.contains(customer))
                	fixedCache.add(customer);
            }
        } else {
            vars.put(id, customer);
        }

        { // 单-唯一索引 remove old index accountid
          Object ov = customer.oldVal(Col.accountid);
          if(ov != null)
              varsByAccountid.remove(ov);
          if(ov != null || force){ // put new index
            int accountid = customer.getAccountid();
            varsByAccountid.put(accountid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByAccountid.clear();
        FIXED = new Customer[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(CustomerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(CustomerDAO DAO, String TABLENAME2){
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

    public static void relocate(CustomerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        CustomerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(CustomerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        CustomerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(CustomerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        CustomerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(CustomerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        CustomerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(CustomerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(CustomerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        CustomerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(CustomerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(CustomerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        CustomerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(CustomerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Customer[maxId(DAO)];

        List<Customer> customers = DAO.selectAll();
        for (Customer customer : customers) {
            customer.reset();
            put(customer, true);
        }
    }

    public static Map toMap(Customer customer){
        return customer.toMap();
    }

    public static List<Map> toMap(List<Customer> customers){
        List<Map> ret = new Vector<Map>();
        for (Customer customer : customers){
            Map e = customer.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Customer> sortZh(List<Customer> customers, final String key) {
        Collections.sort(customers, new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return customers;
    }

    public static List<Customer> sort(List<Customer> customers, final String key) {
        Collections.sort(customers, new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                return o1.compareTo(o2, key);
            }
        });
        return customers;
    }

    public static List<Customer> sort(List<Customer> customers){
        Collections.sort(customers, new Comparator<Customer>(){
            public int compare(Customer o1, Customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return customers;
    }

    public static List<Customer> sortReverse(List<Customer> customers){
        Collections.sort(customers, new Comparator<Customer>(){
            public int compare(Customer o1, Customer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return customers;
    }

    public static List<Customer> sortAccountid(List<Customer> customers){
        Collections.sort(customers, new Comparator<Customer>(){
            public int compare(Customer o1, Customer o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return compareTo(v1, v2);
            }
        });
        return customers;
    }

    public static List<Customer> sortAccountidRo(List<Customer> customers){
        Collections.sort(customers, new Comparator<Customer>(){
            public int compare(Customer o1, Customer o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return 0 - compareTo(v1, v2);
            };
        });
        return customers;
    }

    public static Customer insert(Customer customer) {
        CustomerDAO DAO = DAO();
        return insert(DAO, customer, DAO.TABLENAME);
    }

    public static Customer insert(CustomerDAO DAO, Customer customer) {
        return insert(DAO, customer, DAO.TABLENAME);
    }

    public static Customer insert(Customer customer, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return insert(DAO, customer, TABLENAME2);
    }

    public static Customer insert(CustomerDAO DAO, Customer customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        customer.id = n;
        if(cacheModel != NO_CACHE) put(customer, true);

        return customer;
    }

    public static Customer asyncInsert(Customer customer) {
        CustomerDAO DAO = DAO();
        return asyncInsert(DAO, customer, DAO.TABLENAME);
    }
    public static Customer asyncInsert(CustomerDAO DAO, Customer customer) {
        return asyncInsert(DAO, customer, DAO.TABLENAME);
    }
    public static Customer asyncInsert(Customer customer, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return asyncInsert(DAO, customer, TABLENAME2);
    }
    public static Customer asyncInsert(CustomerDAO DAO, Customer customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(customer, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        customer.id = n;
        if(cacheModel != NO_CACHE) put(customer, true);
        return customer;
    }
    public static Customer insert2(Customer customer) {
        CustomerDAO DAO = DAO();
        return insert2(DAO, customer, DAO.TABLENAME);
    }

    public static Customer insert2(CustomerDAO DAO, Customer customer) {
        return insert2(DAO, customer, DAO.TABLENAME);
    }

    public static Customer insert2(Customer customer, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return insert2(DAO, customer, TABLENAME2);
    }

    public static Customer insert2(CustomerDAO DAO, Customer customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(customer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        customer.id = n;
        if(cacheModel != NO_CACHE) put(customer, true);

        return customer;
    }

    public static Customer asyncInsert2(Customer customer) {
        CustomerDAO DAO = DAO();
        return asyncInsert2(DAO, customer, DAO.TABLENAME);
    }
    public static Customer asyncInsert2(CustomerDAO DAO, Customer customer) {
        return asyncInsert2(DAO, customer, DAO.TABLENAME);
    }
    public static Customer asyncInsert2(Customer customer, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return asyncInsert2(DAO, customer, TABLENAME2);
    }
    public static Customer asyncInsert2(CustomerDAO DAO, Customer customer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        customer.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(customer, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(customer, true);
        return customer;
    }
    public static int[] insert(List<Customer> customers) {
        CustomerDAO DAO = DAO();
        return insert(DAO, customers, DAO.TABLENAME);
    }

    public static int[] insert(CustomerDAO DAO, List<Customer> customers) {
        return insert(DAO, customers, DAO.TABLENAME);
    }

    public static int[] insert(List<Customer> customers, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return insert(DAO, customers, TABLENAME2);
    }

    public static int[] insert(CustomerDAO DAO, List<Customer> customers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(customers, TABLENAME2);
            int n = 0;
            for(Customer customer : customers){
                customer.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[customers.size()];
        int n = 0;
        for(Customer customer : customers){
            customer = insert(DAO, customer, TABLENAME2);
            ret[n++] = (customer == null) ? 0 : (int)customer.id;
        }
        return ret;
    }

    public static int delete(Customer customer) {
        int id = customer.id;
        CustomerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        CustomerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(CustomerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(CustomerDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Customer customer) {
        int id = customer.id;
        CustomerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        CustomerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(CustomerDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(CustomerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        CustomerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(CustomerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(CustomerDAO DAO, int[] ids,String TABLENAME2) {
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
        CustomerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, CustomerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final CustomerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Customer> beans) {
        CustomerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Customer> beans, CustomerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Customer> beans, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Customer> beans, final CustomerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Customer customer : beans){
                int id = customer.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Customer> getAll() {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Customer> getAll(CustomerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Customer> getAll(String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Customer> getAll(final CustomerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Customer> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Customer> getNoSortAll() {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Customer> getNoSortAll(CustomerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Customer> getNoSortAll(String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Customer> getNoSortAll(final CustomerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Customer> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Customer> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Customer customer = FIXED[i];
                if (customer != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Customer> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Customer> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(CustomerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final CustomerDAO DAO, final String TABLENAME2) {
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
        CustomerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(CustomerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final CustomerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Customer> getIn(List<Integer> keys) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Customer> getIn(List<Integer> keys, CustomerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Customer> getIn(List<Integer> keys, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Customer> getIn(final List<Integer> keys, final CustomerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Customer> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Customer> getNoSortIn(List<Integer> keys) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Customer> getNoSortIn(List<Integer> keys, CustomerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Customer> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Customer> getNoSortIn(final List<Integer> keys, final CustomerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Customer> result = newList();
            for (int key : keys) {
                Customer customer = getByKeyFromMemory(key);
                if( customer == null ) continue;
                result.add(customer);
            }
            return result;
        }
    }

    public static List<Customer> getLast(int num) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Customer> getLast(CustomerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Customer> getLast(int num, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Customer> getLast(final CustomerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Customer> result = newList();
            List<Customer> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Customer last() {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Customer last(CustomerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Customer last(String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Customer last(final CustomerDAO DAO, final String TABLENAME2) {
        Customer result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        CustomerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(CustomerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final CustomerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Customer> getGtKey(int id) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Customer> getGtKey(CustomerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Customer> getGtKey(int id, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Customer> getGtKey(final CustomerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Customer> result = newList();
            List<Customer> customers = getAll();
            for (Customer customer : customers) {
                if(customer.id <= id) continue;
                result.add(customer);
            }
            return result;
        }
    }

    public static Customer getByKey(int id) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Customer> asyncGetByKey(final int id) {
        Future<Customer> f = Async.exec(new Callable<Customer>() {
            public Customer call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Customer getByKey(CustomerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Customer getByKey(int id, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Customer getByKey(final CustomerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Customer getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Customer deleteFromMemory(final int id) {
        Customer customer;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            customer = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(customer);
        } else {
            customer = vars.remove(id);
        }
        if(customer == null) return null;

        int accountid = customer.getAccountid();
        varsByAccountid.remove(accountid);

        return customer;
    }

    public static List<Customer> getByPage(int page, int size) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Customer> getByPage(CustomerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Customer> getByPage(int page, int size, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Customer> getByPage(final CustomerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Customer> result = newList();
            List<Customer> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(CustomerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final CustomerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Customer getByAccountid(Integer accountid) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Customer getByAccountid(CustomerDAO DAO, Integer accountid) {
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Customer getByAccountid(Integer accountid, String TABLENAME2) {
        CustomerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, TABLENAME2);
    }

    public static Customer getByAccountid(final CustomerDAO DAO, final int accountid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAccountid(accountid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByAccountid.get(accountid);
            if(id == null) return null;
            Customer result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getAccountid() != accountid){
                varsByAccountid.remove(accountid);
                return null;
            }
            return result;
        }
    }

    public static Customer update(Customer customer) {
        CustomerDAO DAO = DAO();
        return update(DAO, customer, DAO.TABLENAME);
    }

    public static Customer update(CustomerDAO DAO, Customer customer) {
        return update(DAO, customer, DAO.TABLENAME);
    }

    public static Customer update(Customer customer, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return update(DAO, customer, TABLENAME2);
    }

    public static Customer update(final CustomerDAO DAO, final Customer customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(customer, TABLENAME2);
            if(n == -1) 
                return customer;
            else if(n <= 0) 
                return null;
        }
        return customer;
    }

    public static Customer asyncUpdate(Customer customer) {
        CustomerDAO DAO = DAO();
        return asyncUpdate(DAO, customer, DAO.TABLENAME);
    }

    public static Customer asyncUpdate(CustomerDAO DAO, Customer customer) {
        return asyncUpdate(DAO, customer, DAO.TABLENAME);
    }

    public static Customer asyncUpdate(Customer customer, String TABLENAME2) {
        CustomerDAO DAO = DAO();
        return asyncUpdate(DAO, customer, TABLENAME2);
    }

    public static Customer asyncUpdate(final CustomerDAO DAO, final Customer customer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(customer, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(customer, TABLENAME2);
        }
        return customer;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        CustomerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(CustomerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final CustomerDAO DAO, final String TABLENAME2) {
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

        List<Customer> customers = getAll();
        for (Customer customer : customers) {
            int n = DAO.insert2(customer, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
