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

import static com.learnhall.db.bean.Product.Col;

//learnhall3_design - product
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ProductInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ProductInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ProductInternal(){}

    public static ProductDAO DAO(){
        return ProductEntity.ProductDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Product[MAX];
    }
    private static Product[] FIXED = new Product[MAX];
    public static final Map<Integer, Product> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCoursesid = newSortedMap();

    private static final List<Product> fixedCache = newList();

    public static void put(Product product, boolean force){
        if(product == null || product.id <= 0) return ;

        int id = product.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(product))
                	FIXED[id - 1] = product;
                if (!fixedCache.contains(product))
                	fixedCache.add(product);
            }
        } else {
            vars.put(id, product);
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = product.oldVal(Col.lhubid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByLhubid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByLhubid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int lhubid = product.getLhubid();
            Set m1 = varsByLhubid.get(lhubid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByLhubid.put(lhubid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index coursesid
          Object ov = product.oldVal(Col.coursesid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByCoursesid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByCoursesid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int coursesid = product.getCoursesid();
            Set m2 = varsByCoursesid.get(coursesid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByCoursesid.put(coursesid, m2);
            }
            m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLhubid.clear();
        varsByCoursesid.clear();
        FIXED = new Product[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ProductDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ProductDAO DAO, String TABLENAME2){
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

    public static void relocate(ProductDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ProductDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ProductDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ProductDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ProductDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ProductDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ProductDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ProductDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ProductDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ProductDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ProductDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ProductDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ProductDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ProductDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ProductDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Product[maxId(DAO)];

        List<Product> products = DAO.selectAll();
        for (Product product : products) {
            product.reset();
            put(product, true);
        }
    }

    public static Map toMap(Product product){
        return product.toMap();
    }

    public static List<Map> toMap(List<Product> products){
        List<Map> ret = new Vector<Map>();
        for (Product product : products){
            Map e = product.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Product> sortZh(List<Product> products, final String key) {
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return products;
    }

    public static List<Product> sort(List<Product> products, final String key) {
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return o1.compareTo(o2, key);
            }
        });
        return products;
    }

    public static List<Product> sort(List<Product> products){
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product o1, Product o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return products;
    }

    public static List<Product> sortReverse(List<Product> products){
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product o1, Product o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return products;
    }

    public static List<Product> sortLhubid(List<Product> products){
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product o1, Product o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return products;
    }

    public static List<Product> sortLhubidRo(List<Product> products){
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product o1, Product o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return products;
    }

    public static List<Product> sortCoursesid(List<Product> products){
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product o1, Product o2) {
                Object v1 = o1.getCoursesid();
                Object v2 = o2.getCoursesid();
                return compareTo(v1, v2);
            }
        });
        return products;
    }

    public static List<Product> sortCoursesidRo(List<Product> products){
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product o1, Product o2) {
                Object v1 = o1.getCoursesid();
                Object v2 = o2.getCoursesid();
                return 0 - compareTo(v1, v2);
            };
        });
        return products;
    }

    public static Product insert(Product product) {
        ProductDAO DAO = DAO();
        return insert(DAO, product, DAO.TABLENAME);
    }

    public static Product insert(ProductDAO DAO, Product product) {
        return insert(DAO, product, DAO.TABLENAME);
    }

    public static Product insert(Product product, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return insert(DAO, product, TABLENAME2);
    }

    public static Product insert(ProductDAO DAO, Product product, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(product, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        product.id = n;
        if(cacheModel != NO_CACHE) put(product, true);

        return product;
    }

    public static Product asyncInsert(Product product) {
        ProductDAO DAO = DAO();
        return asyncInsert(DAO, product, DAO.TABLENAME);
    }
    public static Product asyncInsert(ProductDAO DAO, Product product) {
        return asyncInsert(DAO, product, DAO.TABLENAME);
    }
    public static Product asyncInsert(Product product, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return asyncInsert(DAO, product, TABLENAME2);
    }
    public static Product asyncInsert(ProductDAO DAO, Product product, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(product, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        product.id = n;
        if(cacheModel != NO_CACHE) put(product, true);
        return product;
    }
    public static Product insert2(Product product) {
        ProductDAO DAO = DAO();
        return insert2(DAO, product, DAO.TABLENAME);
    }

    public static Product insert2(ProductDAO DAO, Product product) {
        return insert2(DAO, product, DAO.TABLENAME);
    }

    public static Product insert2(Product product, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return insert2(DAO, product, TABLENAME2);
    }

    public static Product insert2(ProductDAO DAO, Product product, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(product, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        product.id = n;
        if(cacheModel != NO_CACHE) put(product, true);

        return product;
    }

    public static Product asyncInsert2(Product product) {
        ProductDAO DAO = DAO();
        return asyncInsert2(DAO, product, DAO.TABLENAME);
    }
    public static Product asyncInsert2(ProductDAO DAO, Product product) {
        return asyncInsert2(DAO, product, DAO.TABLENAME);
    }
    public static Product asyncInsert2(Product product, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return asyncInsert2(DAO, product, TABLENAME2);
    }
    public static Product asyncInsert2(ProductDAO DAO, Product product, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        product.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(product, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(product, true);
        return product;
    }
    public static int[] insert(List<Product> products) {
        ProductDAO DAO = DAO();
        return insert(DAO, products, DAO.TABLENAME);
    }

    public static int[] insert(ProductDAO DAO, List<Product> products) {
        return insert(DAO, products, DAO.TABLENAME);
    }

    public static int[] insert(List<Product> products, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return insert(DAO, products, TABLENAME2);
    }

    public static int[] insert(ProductDAO DAO, List<Product> products, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(products, TABLENAME2);
            int n = 0;
            for(Product product : products){
                product.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[products.size()];
        int n = 0;
        for(Product product : products){
            product = insert(DAO, product, TABLENAME2);
            ret[n++] = (product == null) ? 0 : (int)product.id;
        }
        return ret;
    }

    public static int delete(Product product) {
        int id = product.id;
        ProductDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ProductDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ProductDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ProductDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Product product) {
        int id = product.id;
        ProductDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ProductDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ProductDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ProductDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ProductDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ProductDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ProductDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ProductDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ProductDAO DAO, int[] ids,String TABLENAME2) {
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
        ProductDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ProductDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ProductDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Product> beans) {
        ProductDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Product> beans, ProductDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Product> beans, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Product> beans, final ProductDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Product product : beans){
                int id = product.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Product> getAll() {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Product> getAll(ProductDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Product> getAll(String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Product> getAll(final ProductDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Product> getNoSortAll() {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Product> getNoSortAll(ProductDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Product> getNoSortAll(String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Product> getNoSortAll(final ProductDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Product> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Product product = FIXED[i];
                if (product != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Product> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Product> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ProductDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ProductDAO DAO, final String TABLENAME2) {
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
        ProductDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ProductDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ProductDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ProductDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Product> getIn(List<Integer> keys) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product> getIn(List<Integer> keys, ProductDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product> getIn(List<Integer> keys, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Product> getIn(final List<Integer> keys, final ProductDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Product> getNoSortIn(List<Integer> keys) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product> getNoSortIn(List<Integer> keys, ProductDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Product> getNoSortIn(final List<Integer> keys, final ProductDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Product> result = newList();
            for (int key : keys) {
                Product product = getByKeyFromMemory(key);
                if( product == null ) continue;
                result.add(product);
            }
            return result;
        }
    }

    public static List<Product> getLast(int num) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Product> getLast(ProductDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Product> getLast(int num, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Product> getLast(final ProductDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Product> result = newList();
            List<Product> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Product last() {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Product last(ProductDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Product last(String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Product last(final ProductDAO DAO, final String TABLENAME2) {
        Product result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ProductDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ProductDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ProductDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ProductDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Product> getGtKey(int id) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Product> getGtKey(ProductDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Product> getGtKey(int id, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Product> getGtKey(final ProductDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product> result = newList();
            List<Product> products = getAll();
            for (Product product : products) {
                if(product.id <= id) continue;
                result.add(product);
            }
            return result;
        }
    }

    public static Product getByKey(int id) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Product> asyncGetByKey(final int id) {
        Future<Product> f = Async.exec(new Callable<Product>() {
            public Product call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Product getByKey(ProductDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Product getByKey(int id, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Product getByKey(final ProductDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Product getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Product deleteFromMemory(final int id) {
        Product product;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            product = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(product);
        } else {
            product = vars.remove(id);
        }
        if(product == null) return null;

        int lhubid = product.getLhubid();
        Set m1 = varsByLhubid.get(lhubid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        int coursesid = product.getCoursesid();
        Set m2 = varsByCoursesid.get(coursesid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByCoursesid.remove(coursesid);
        }

        return product;
    }

    public static List<Product> getByPage(int page, int size) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Product> getByPage(ProductDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Product> getByPage(int page, int size, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Product> getByPage(final ProductDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product> result = newList();
            List<Product> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ProductDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ProductDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByLhubid(Integer lhubid) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(ProductDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final ProductDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Product> products = getByLhubid(DAO, lhubid, TABLENAME2);
        return products.size();
    }

    public static List<Product> getByLhubid(Integer lhubid) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Product> getByLhubid(ProductDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Product> getByLhubid(Integer lhubid, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Product> getByLhubid(final ProductDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Product> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Product e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _lhubid = e.getLhubid();
                if(_lhubid != lhubid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByCoursesid(Integer coursesid) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCoursesid(DAO, coursesid, DAO.TABLENAME);
    }

    public static int countByCoursesid(ProductDAO DAO, Integer coursesid) {
        return countByCoursesid(DAO, coursesid, DAO.TABLENAME);
    }

    public static int countByCoursesid(Integer coursesid, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCoursesid(DAO, coursesid, TABLENAME2);
    }

    public static int countByCoursesid(final ProductDAO DAO, final Integer coursesid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCoursesid(coursesid, TABLENAME2);
        }
        List<Product> products = getByCoursesid(DAO, coursesid, TABLENAME2);
        return products.size();
    }

    public static List<Product> getByCoursesid(Integer coursesid) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCoursesid(DAO, coursesid, DAO.TABLENAME);
    }

    public static List<Product> getByCoursesid(ProductDAO DAO, Integer coursesid) {
        return getByCoursesid(DAO, coursesid, DAO.TABLENAME);
    }

    public static List<Product> getByCoursesid(Integer coursesid, String TABLENAME2) {
        ProductDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCoursesid(DAO, coursesid, TABLENAME2);
    }

    public static List<Product> getByCoursesid(final ProductDAO DAO, final Integer coursesid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCoursesid(coursesid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Product> result = newList();
            Set<Integer> m1 = varsByCoursesid.get(coursesid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Product e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _coursesid = e.getCoursesid();
                if(_coursesid != coursesid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Product update(Product product) {
        ProductDAO DAO = DAO();
        return update(DAO, product, DAO.TABLENAME);
    }

    public static Product update(ProductDAO DAO, Product product) {
        return update(DAO, product, DAO.TABLENAME);
    }

    public static Product update(Product product, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return update(DAO, product, TABLENAME2);
    }

    public static Product update(final ProductDAO DAO, final Product product,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(product, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(product, TABLENAME2);
            if(n == -1) 
                return product;
            else if(n <= 0) 
                return null;
        }
        return product;
    }

    public static Product asyncUpdate(Product product) {
        ProductDAO DAO = DAO();
        return asyncUpdate(DAO, product, DAO.TABLENAME);
    }

    public static Product asyncUpdate(ProductDAO DAO, Product product) {
        return asyncUpdate(DAO, product, DAO.TABLENAME);
    }

    public static Product asyncUpdate(Product product, String TABLENAME2) {
        ProductDAO DAO = DAO();
        return asyncUpdate(DAO, product, TABLENAME2);
    }

    public static Product asyncUpdate(final ProductDAO DAO, final Product product,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(product, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(product, TABLENAME2);
        }
        return product;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ProductDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ProductDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ProductDAO DAO, final String TABLENAME2) {
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

        List<Product> products = getAll();
        for (Product product : products) {
            int n = DAO.insert2(product, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
