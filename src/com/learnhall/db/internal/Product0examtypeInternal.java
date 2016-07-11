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

import static com.learnhall.db.bean.Product0examtype.Col;

//learnhall3_design - product0examtype
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Product0examtypeInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Product0examtypeInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Product0examtypeInternal(){}

    public static Product0examtypeDAO DAO(){
        return Product0examtypeEntity.Product0examtypeDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Product0examtype[MAX];
    }
    private static Product0examtype[] FIXED = new Product0examtype[MAX];
    public static final Map<Integer, Product0examtype> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByExamtypeid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByProductid = newSortedMap();
    public static final Map<String, Integer> varsByLhubidProductidExamtypeid = newSortedMap();

    private static final List<Product0examtype> fixedCache = newList();

    public static void put(Product0examtype product0examtype, boolean force){
        if(product0examtype == null || product0examtype.id <= 0) return ;

        int id = product0examtype.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(product0examtype))
                	FIXED[id - 1] = product0examtype;
                if (!fixedCache.contains(product0examtype))
                	fixedCache.add(product0examtype);
            }
        } else {
            vars.put(id, product0examtype);
        }

        { // 单-非唯一索引 remove old index examtypeid
          Object ov = product0examtype.oldVal(Col.examtypeid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByExamtypeid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByExamtypeid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int examtypeid = product0examtype.getExamtypeid();
            Set m1 = varsByExamtypeid.get(examtypeid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByExamtypeid.put(examtypeid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = product0examtype.oldVal(Col.lhubid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByLhubid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByLhubid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int lhubid = product0examtype.getLhubid();
            Set m2 = varsByLhubid.get(lhubid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByLhubid.put(lhubid, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index productid
          Object ov = product0examtype.oldVal(Col.productid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByProductid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByProductid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int productid = product0examtype.getProductid();
            Set m3 = varsByProductid.get(productid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByProductid.put(productid, m3);
            }
            m3.add(id);
          }
        }

        { // lhubid_prid_etypeid
          boolean bChanged = product0examtype.inChanged(Col.lhubid, Col.productid, Col.examtypeid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vlhubid = product0examtype.oldOrNew(Col.lhubid);
            Object vproductid = product0examtype.oldOrNew(Col.productid);
            Object vexamtypeid = product0examtype.oldOrNew(Col.examtypeid);
            String okey = com.bowlong.lang.PStr.b().a(vlhubid, "-", vproductid, "-", vexamtypeid).e();
            varsByLhubidProductidExamtypeid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vlhubid = product0examtype.getLhubid();
              int vproductid = product0examtype.getProductid();
              int vexamtypeid = product0examtype.getExamtypeid();
              String vkey = com.bowlong.lang.PStr.b().a(vlhubid, "-", vproductid, "-", vexamtypeid).e();
              varsByLhubidProductidExamtypeid.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByExamtypeid.clear();
        varsByLhubid.clear();
        varsByProductid.clear();
        varsByLhubidProductidExamtypeid.clear();
        FIXED = new Product0examtype[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Product0examtypeDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Product0examtypeDAO DAO, String TABLENAME2){
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

    public static void relocate(Product0examtypeDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Product0examtypeDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Product0examtypeDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Product0examtypeDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Product0examtypeDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Product0examtypeDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Product0examtypeDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Product0examtypeDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Product0examtypeDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Product0examtypeDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Product0examtypeDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Product0examtypeDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Product0examtypeDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Product0examtype[maxId(DAO)];

        List<Product0examtype> product0examtypes = DAO.selectAll();
        for (Product0examtype product0examtype : product0examtypes) {
            product0examtype.reset();
            put(product0examtype, true);
        }
    }

    public static Map toMap(Product0examtype product0examtype){
        return product0examtype.toMap();
    }

    public static List<Map> toMap(List<Product0examtype> product0examtypes){
        List<Map> ret = new Vector<Map>();
        for (Product0examtype product0examtype : product0examtypes){
            Map e = product0examtype.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Product0examtype> sortZh(List<Product0examtype> product0examtypes, final String key) {
        Collections.sort(product0examtypes, new Comparator<Product0examtype>() {
            public int compare(Product0examtype o1, Product0examtype o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sort(List<Product0examtype> product0examtypes, final String key) {
        Collections.sort(product0examtypes, new Comparator<Product0examtype>() {
            public int compare(Product0examtype o1, Product0examtype o2) {
                return o1.compareTo(o2, key);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sort(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortReverse(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortExamtypeid(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.getExamtypeid();
                Object v2 = o2.getExamtypeid();
                return compareTo(v1, v2);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortExamtypeidRo(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.getExamtypeid();
                Object v2 = o2.getExamtypeid();
                return 0 - compareTo(v1, v2);
            };
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortLhubid(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortLhubidRo(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortProductid(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.getProductid();
                Object v2 = o2.getProductid();
                return compareTo(v1, v2);
            }
        });
        return product0examtypes;
    }

    public static List<Product0examtype> sortProductidRo(List<Product0examtype> product0examtypes){
        Collections.sort(product0examtypes, new Comparator<Product0examtype>(){
            public int compare(Product0examtype o1, Product0examtype o2) {
                Object v1 = o1.getProductid();
                Object v2 = o2.getProductid();
                return 0 - compareTo(v1, v2);
            };
        });
        return product0examtypes;
    }

    public static Product0examtype insert(Product0examtype product0examtype) {
        Product0examtypeDAO DAO = DAO();
        return insert(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype insert(Product0examtypeDAO DAO, Product0examtype product0examtype) {
        return insert(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype insert(Product0examtype product0examtype, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return insert(DAO, product0examtype, TABLENAME2);
    }

    public static Product0examtype insert(Product0examtypeDAO DAO, Product0examtype product0examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(product0examtype, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        product0examtype.id = n;
        if(cacheModel != NO_CACHE) put(product0examtype, true);

        return product0examtype;
    }

    public static Product0examtype asyncInsert(Product0examtype product0examtype) {
        Product0examtypeDAO DAO = DAO();
        return asyncInsert(DAO, product0examtype, DAO.TABLENAME);
    }
    public static Product0examtype asyncInsert(Product0examtypeDAO DAO, Product0examtype product0examtype) {
        return asyncInsert(DAO, product0examtype, DAO.TABLENAME);
    }
    public static Product0examtype asyncInsert(Product0examtype product0examtype, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return asyncInsert(DAO, product0examtype, TABLENAME2);
    }
    public static Product0examtype asyncInsert(Product0examtypeDAO DAO, Product0examtype product0examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(product0examtype, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        product0examtype.id = n;
        if(cacheModel != NO_CACHE) put(product0examtype, true);
        return product0examtype;
    }
    public static Product0examtype insert2(Product0examtype product0examtype) {
        Product0examtypeDAO DAO = DAO();
        return insert2(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype insert2(Product0examtypeDAO DAO, Product0examtype product0examtype) {
        return insert2(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype insert2(Product0examtype product0examtype, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return insert2(DAO, product0examtype, TABLENAME2);
    }

    public static Product0examtype insert2(Product0examtypeDAO DAO, Product0examtype product0examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(product0examtype, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        product0examtype.id = n;
        if(cacheModel != NO_CACHE) put(product0examtype, true);

        return product0examtype;
    }

    public static Product0examtype asyncInsert2(Product0examtype product0examtype) {
        Product0examtypeDAO DAO = DAO();
        return asyncInsert2(DAO, product0examtype, DAO.TABLENAME);
    }
    public static Product0examtype asyncInsert2(Product0examtypeDAO DAO, Product0examtype product0examtype) {
        return asyncInsert2(DAO, product0examtype, DAO.TABLENAME);
    }
    public static Product0examtype asyncInsert2(Product0examtype product0examtype, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return asyncInsert2(DAO, product0examtype, TABLENAME2);
    }
    public static Product0examtype asyncInsert2(Product0examtypeDAO DAO, Product0examtype product0examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        product0examtype.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(product0examtype, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(product0examtype, true);
        return product0examtype;
    }
    public static int[] insert(List<Product0examtype> product0examtypes) {
        Product0examtypeDAO DAO = DAO();
        return insert(DAO, product0examtypes, DAO.TABLENAME);
    }

    public static int[] insert(Product0examtypeDAO DAO, List<Product0examtype> product0examtypes) {
        return insert(DAO, product0examtypes, DAO.TABLENAME);
    }

    public static int[] insert(List<Product0examtype> product0examtypes, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return insert(DAO, product0examtypes, TABLENAME2);
    }

    public static int[] insert(Product0examtypeDAO DAO, List<Product0examtype> product0examtypes, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(product0examtypes, TABLENAME2);
            int n = 0;
            for(Product0examtype product0examtype : product0examtypes){
                product0examtype.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[product0examtypes.size()];
        int n = 0;
        for(Product0examtype product0examtype : product0examtypes){
            product0examtype = insert(DAO, product0examtype, TABLENAME2);
            ret[n++] = (product0examtype == null) ? 0 : (int)product0examtype.id;
        }
        return ret;
    }

    public static int delete(Product0examtype product0examtype) {
        int id = product0examtype.id;
        Product0examtypeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Product0examtypeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Product0examtypeDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Product0examtypeDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Product0examtype product0examtype) {
        int id = product0examtype.id;
        Product0examtypeDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Product0examtypeDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Product0examtypeDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Product0examtypeDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Product0examtypeDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Product0examtypeDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Product0examtypeDAO DAO, int[] ids,String TABLENAME2) {
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
        Product0examtypeDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Product0examtypeDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Product0examtypeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Product0examtype> beans) {
        Product0examtypeDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Product0examtype> beans, Product0examtypeDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Product0examtype> beans, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Product0examtype> beans, final Product0examtypeDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Product0examtype product0examtype : beans){
                int id = product0examtype.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Product0examtype> getAll() {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getAll(Product0examtypeDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getAll(String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Product0examtype> getAll(final Product0examtypeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product0examtype> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Product0examtype> getNoSortAll() {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getNoSortAll(Product0examtypeDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getNoSortAll(String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Product0examtype> getNoSortAll(final Product0examtypeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Product0examtype> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product0examtype> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Product0examtype product0examtype = FIXED[i];
                if (product0examtype != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Product0examtype> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Product0examtype> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Product0examtypeDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Product0examtypeDAO DAO, final String TABLENAME2) {
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
        Product0examtypeDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Product0examtypeDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Product0examtypeDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Product0examtype> getIn(List<Integer> keys) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getIn(List<Integer> keys, Product0examtypeDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getIn(List<Integer> keys, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Product0examtype> getIn(final List<Integer> keys, final Product0examtypeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product0examtype> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Product0examtype> getNoSortIn(List<Integer> keys) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getNoSortIn(List<Integer> keys, Product0examtypeDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Product0examtype> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Product0examtype> getNoSortIn(final List<Integer> keys, final Product0examtypeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Product0examtype> result = newList();
            for (int key : keys) {
                Product0examtype product0examtype = getByKeyFromMemory(key);
                if( product0examtype == null ) continue;
                result.add(product0examtype);
            }
            return result;
        }
    }

    public static List<Product0examtype> getLast(int num) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Product0examtype> getLast(Product0examtypeDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Product0examtype> getLast(int num, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Product0examtype> getLast(final Product0examtypeDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Product0examtype> result = newList();
            List<Product0examtype> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Product0examtype last() {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Product0examtype last(Product0examtypeDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Product0examtype last(String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Product0examtype last(final Product0examtypeDAO DAO, final String TABLENAME2) {
        Product0examtype result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Product0examtypeDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Product0examtypeDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Product0examtypeDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Product0examtype> getGtKey(int id) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Product0examtype> getGtKey(Product0examtypeDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Product0examtype> getGtKey(int id, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Product0examtype> getGtKey(final Product0examtypeDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product0examtype> result = newList();
            List<Product0examtype> product0examtypes = getAll();
            for (Product0examtype product0examtype : product0examtypes) {
                if(product0examtype.id <= id) continue;
                result.add(product0examtype);
            }
            return result;
        }
    }

    public static Product0examtype getByKey(int id) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Product0examtype> asyncGetByKey(final int id) {
        Future<Product0examtype> f = Async.exec(new Callable<Product0examtype>() {
            public Product0examtype call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Product0examtype getByKey(Product0examtypeDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Product0examtype getByKey(int id, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Product0examtype getByKey(final Product0examtypeDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Product0examtype getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Product0examtype deleteFromMemory(final int id) {
        Product0examtype product0examtype;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            product0examtype = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(product0examtype);
        } else {
            product0examtype = vars.remove(id);
        }
        if(product0examtype == null) return null;

        int examtypeid = product0examtype.getExamtypeid();
        Set m1 = varsByExamtypeid.get(examtypeid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByExamtypeid.remove(examtypeid);
        }

        int lhubid = product0examtype.getLhubid();
        Set m2 = varsByLhubid.get(lhubid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        int productid = product0examtype.getProductid();
        Set m3 = varsByProductid.get(productid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByProductid.remove(productid);
        }

        { // lhubid_prid_etypeid
            int vlhubid = product0examtype.getLhubid();
            int vproductid = product0examtype.getProductid();
            int vexamtypeid = product0examtype.getExamtypeid();
            String vkey = com.bowlong.lang.PStr.b().a(vlhubid, "-", vproductid, "-", vexamtypeid).e();
            varsByLhubidProductidExamtypeid.remove(vkey);
        }

        return product0examtype;
    }

    public static List<Product0examtype> getByPage(int page, int size) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByPage(Product0examtypeDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByPage(int page, int size, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Product0examtype> getByPage(final Product0examtypeDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Product0examtype> result = newList();
            List<Product0examtype> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Product0examtypeDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Product0examtypeDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByExamtypeid(Integer examtypeid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static int countByExamtypeid(Product0examtypeDAO DAO, Integer examtypeid) {
        return countByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static int countByExamtypeid(Integer examtypeid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamtypeid(DAO, examtypeid, TABLENAME2);
    }

    public static int countByExamtypeid(final Product0examtypeDAO DAO, final Integer examtypeid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamtypeid(examtypeid, TABLENAME2);
        }
        List<Product0examtype> product0examtypes = getByExamtypeid(DAO, examtypeid, TABLENAME2);
        return product0examtypes.size();
    }

    public static List<Product0examtype> getByExamtypeid(Integer examtypeid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByExamtypeid(Product0examtypeDAO DAO, Integer examtypeid) {
        return getByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByExamtypeid(Integer examtypeid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamtypeid(DAO, examtypeid, TABLENAME2);
    }

    public static List<Product0examtype> getByExamtypeid(final Product0examtypeDAO DAO, final Integer examtypeid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamtypeid(examtypeid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Product0examtype> result = newList();
            Set<Integer> m1 = varsByExamtypeid.get(examtypeid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Product0examtype e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _examtypeid = e.getExamtypeid();
                if(_examtypeid != examtypeid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByLhubid(Integer lhubid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Product0examtypeDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final Product0examtypeDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Product0examtype> product0examtypes = getByLhubid(DAO, lhubid, TABLENAME2);
        return product0examtypes.size();
    }

    public static List<Product0examtype> getByLhubid(Integer lhubid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByLhubid(Product0examtypeDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByLhubid(Integer lhubid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Product0examtype> getByLhubid(final Product0examtypeDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Product0examtype> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Product0examtype e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByProductid(Integer productid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static int countByProductid(Product0examtypeDAO DAO, Integer productid) {
        return countByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static int countByProductid(Integer productid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByProductid(DAO, productid, TABLENAME2);
    }

    public static int countByProductid(final Product0examtypeDAO DAO, final Integer productid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByProductid(productid, TABLENAME2);
        }
        List<Product0examtype> product0examtypes = getByProductid(DAO, productid, TABLENAME2);
        return product0examtypes.size();
    }

    public static List<Product0examtype> getByProductid(Integer productid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByProductid(Product0examtypeDAO DAO, Integer productid) {
        return getByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static List<Product0examtype> getByProductid(Integer productid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByProductid(DAO, productid, TABLENAME2);
    }

    public static List<Product0examtype> getByProductid(final Product0examtypeDAO DAO, final Integer productid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByProductid(productid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Product0examtype> result = newList();
            Set<Integer> m1 = varsByProductid.get(productid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Product0examtype e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _productid = e.getProductid();
                if(_productid != productid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Product0examtype getByLhubidProductidExamtypeid(Integer lhubid, Integer productid, Integer examtypeid) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubidProductidExamtypeid(DAO, lhubid, productid, examtypeid, DAO.TABLENAME);
    }

    public static Product0examtype getByLhubidProductidExamtypeid(Product0examtypeDAO DAO, Integer lhubid, Integer productid, Integer examtypeid) {
        return getByLhubidProductidExamtypeid(DAO, lhubid, productid, examtypeid, DAO.TABLENAME);
    }

    public static Product0examtype getByLhubidProductidExamtypeid(Integer lhubid, Integer productid, Integer examtypeid, String TABLENAME2) {
        Product0examtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubidProductidExamtypeid(DAO, lhubid, productid, examtypeid, TABLENAME2);
    }

    public static Product0examtype getByLhubidProductidExamtypeid(final Product0examtypeDAO DAO, Integer lhubid, Integer productid, Integer examtypeid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubidProductidExamtypeid(lhubid, productid, examtypeid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = lhubid+"-"+productid+"-"+examtypeid;
            Integer id = varsByLhubidProductidExamtypeid.get(vkey);
            if(id == null) return null;
            Product0examtype result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getLhubid() != lhubid){
                varsByLhubidProductidExamtypeid.remove(vkey);
                return null;
            }
            if(result.getProductid() != productid){
                varsByLhubidProductidExamtypeid.remove(vkey);
                return null;
            }
            if(result.getExamtypeid() != examtypeid){
                varsByLhubidProductidExamtypeid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Product0examtype update(Product0examtype product0examtype) {
        Product0examtypeDAO DAO = DAO();
        return update(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype update(Product0examtypeDAO DAO, Product0examtype product0examtype) {
        return update(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype update(Product0examtype product0examtype, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return update(DAO, product0examtype, TABLENAME2);
    }

    public static Product0examtype update(final Product0examtypeDAO DAO, final Product0examtype product0examtype,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(product0examtype, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(product0examtype, TABLENAME2);
            if(n == -1) 
                return product0examtype;
            else if(n <= 0) 
                return null;
        }
        return product0examtype;
    }

    public static Product0examtype asyncUpdate(Product0examtype product0examtype) {
        Product0examtypeDAO DAO = DAO();
        return asyncUpdate(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype asyncUpdate(Product0examtypeDAO DAO, Product0examtype product0examtype) {
        return asyncUpdate(DAO, product0examtype, DAO.TABLENAME);
    }

    public static Product0examtype asyncUpdate(Product0examtype product0examtype, String TABLENAME2) {
        Product0examtypeDAO DAO = DAO();
        return asyncUpdate(DAO, product0examtype, TABLENAME2);
    }

    public static Product0examtype asyncUpdate(final Product0examtypeDAO DAO, final Product0examtype product0examtype,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(product0examtype, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(product0examtype, TABLENAME2);
        }
        return product0examtype;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Product0examtypeDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Product0examtypeDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Product0examtypeDAO DAO, final String TABLENAME2) {
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

        List<Product0examtype> product0examtypes = getAll();
        for (Product0examtype product0examtype : product0examtypes) {
            int n = DAO.insert2(product0examtype, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
