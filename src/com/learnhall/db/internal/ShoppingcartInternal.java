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

import static com.learnhall.db.bean.Shoppingcart.Col;

//learnhall3_design - shoppingcart
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ShoppingcartInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ShoppingcartInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ShoppingcartInternal(){}

    public static ShoppingcartDAO DAO(){
        return ShoppingcartEntity.ShoppingcartDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Shoppingcart[MAX];
    }
    private static Shoppingcart[] FIXED = new Shoppingcart[MAX];
    public static final Map<Integer, Shoppingcart> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByCustomeridKindid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKindid = newSortedMap();

    private static final List<Shoppingcart> fixedCache = newList();

    public static void put(Shoppingcart shoppingcart, boolean force){
        if(shoppingcart == null || shoppingcart.id <= 0) return ;

        int id = shoppingcart.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(shoppingcart))
                	FIXED[id - 1] = shoppingcart;
                if (!fixedCache.contains(shoppingcart))
                	fixedCache.add(shoppingcart);
            }
        } else {
            vars.put(id, shoppingcart);
        }

        { // custid_kindid
          boolean bChanged = shoppingcart.inChanged(Col.customerid, Col.kindid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vcustomerid = shoppingcart.oldOrNew(Col.customerid);
              Object vkindid = shoppingcart.oldOrNew(Col.kindid);
              String okey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
              Set m1 = varsByCustomeridKindid.get(okey);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                       varsByCustomeridKindid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vcustomerid = shoppingcart.getCustomerid();
              int vkindid = shoppingcart.getKindid();
              String vkey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
              Set m1 = varsByCustomeridKindid.get(vkey);
              if(m1 == null){
                  m1 = newSortedSet();
                  varsByCustomeridKindid.put(vkey, m1);
              }
              m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = shoppingcart.oldVal(Col.customerid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByCustomerid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByCustomerid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int customerid = shoppingcart.getCustomerid();
            Set m2 = varsByCustomerid.get(customerid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByCustomerid.put(customerid, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kindid
          Object ov = shoppingcart.oldVal(Col.kindid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByKindid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByKindid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int kindid = shoppingcart.getKindid();
            Set m3 = varsByKindid.get(kindid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByKindid.put(kindid, m3);
            }
            m3.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustomeridKindid.clear();
        varsByCustomerid.clear();
        varsByKindid.clear();
        FIXED = new Shoppingcart[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ShoppingcartDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ShoppingcartDAO DAO, String TABLENAME2){
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

    public static void relocate(ShoppingcartDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ShoppingcartDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ShoppingcartDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ShoppingcartDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ShoppingcartDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ShoppingcartDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ShoppingcartDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ShoppingcartDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ShoppingcartDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ShoppingcartDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ShoppingcartDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ShoppingcartDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ShoppingcartDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Shoppingcart[maxId(DAO)];

        List<Shoppingcart> shoppingcarts = DAO.selectAll();
        for (Shoppingcart shoppingcart : shoppingcarts) {
            shoppingcart.reset();
            put(shoppingcart, true);
        }
    }

    public static Map toMap(Shoppingcart shoppingcart){
        return shoppingcart.toMap();
    }

    public static List<Map> toMap(List<Shoppingcart> shoppingcarts){
        List<Map> ret = new Vector<Map>();
        for (Shoppingcart shoppingcart : shoppingcarts){
            Map e = shoppingcart.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Shoppingcart> sortZh(List<Shoppingcart> shoppingcarts, final String key) {
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>() {
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sort(List<Shoppingcart> shoppingcarts, final String key) {
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>() {
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                return o1.compareTo(o2, key);
            }
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sort(List<Shoppingcart> shoppingcarts){
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>(){
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sortReverse(List<Shoppingcart> shoppingcarts){
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>(){
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sortCustomerid(List<Shoppingcart> shoppingcarts){
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>(){
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sortCustomeridRo(List<Shoppingcart> shoppingcarts){
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>(){
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sortKindid(List<Shoppingcart> shoppingcarts){
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>(){
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return shoppingcarts;
    }

    public static List<Shoppingcart> sortKindidRo(List<Shoppingcart> shoppingcarts){
        Collections.sort(shoppingcarts, new Comparator<Shoppingcart>(){
            public int compare(Shoppingcart o1, Shoppingcart o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return shoppingcarts;
    }

    public static Shoppingcart insert(Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = DAO();
        return insert(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart insert(ShoppingcartDAO DAO, Shoppingcart shoppingcart) {
        return insert(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart insert(Shoppingcart shoppingcart, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return insert(DAO, shoppingcart, TABLENAME2);
    }

    public static Shoppingcart insert(ShoppingcartDAO DAO, Shoppingcart shoppingcart, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(shoppingcart, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        shoppingcart.id = n;
        if(cacheModel != NO_CACHE) put(shoppingcart, true);

        return shoppingcart;
    }

    public static Shoppingcart asyncInsert(Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = DAO();
        return asyncInsert(DAO, shoppingcart, DAO.TABLENAME);
    }
    public static Shoppingcart asyncInsert(ShoppingcartDAO DAO, Shoppingcart shoppingcart) {
        return asyncInsert(DAO, shoppingcart, DAO.TABLENAME);
    }
    public static Shoppingcart asyncInsert(Shoppingcart shoppingcart, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return asyncInsert(DAO, shoppingcart, TABLENAME2);
    }
    public static Shoppingcart asyncInsert(ShoppingcartDAO DAO, Shoppingcart shoppingcart, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(shoppingcart, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        shoppingcart.id = n;
        if(cacheModel != NO_CACHE) put(shoppingcart, true);
        return shoppingcart;
    }
    public static Shoppingcart insert2(Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = DAO();
        return insert2(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart insert2(ShoppingcartDAO DAO, Shoppingcart shoppingcart) {
        return insert2(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart insert2(Shoppingcart shoppingcart, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return insert2(DAO, shoppingcart, TABLENAME2);
    }

    public static Shoppingcart insert2(ShoppingcartDAO DAO, Shoppingcart shoppingcart, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(shoppingcart, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        shoppingcart.id = n;
        if(cacheModel != NO_CACHE) put(shoppingcart, true);

        return shoppingcart;
    }

    public static Shoppingcart asyncInsert2(Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = DAO();
        return asyncInsert2(DAO, shoppingcart, DAO.TABLENAME);
    }
    public static Shoppingcart asyncInsert2(ShoppingcartDAO DAO, Shoppingcart shoppingcart) {
        return asyncInsert2(DAO, shoppingcart, DAO.TABLENAME);
    }
    public static Shoppingcart asyncInsert2(Shoppingcart shoppingcart, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return asyncInsert2(DAO, shoppingcart, TABLENAME2);
    }
    public static Shoppingcart asyncInsert2(ShoppingcartDAO DAO, Shoppingcart shoppingcart, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        shoppingcart.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(shoppingcart, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(shoppingcart, true);
        return shoppingcart;
    }
    public static int[] insert(List<Shoppingcart> shoppingcarts) {
        ShoppingcartDAO DAO = DAO();
        return insert(DAO, shoppingcarts, DAO.TABLENAME);
    }

    public static int[] insert(ShoppingcartDAO DAO, List<Shoppingcart> shoppingcarts) {
        return insert(DAO, shoppingcarts, DAO.TABLENAME);
    }

    public static int[] insert(List<Shoppingcart> shoppingcarts, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return insert(DAO, shoppingcarts, TABLENAME2);
    }

    public static int[] insert(ShoppingcartDAO DAO, List<Shoppingcart> shoppingcarts, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(shoppingcarts, TABLENAME2);
            int n = 0;
            for(Shoppingcart shoppingcart : shoppingcarts){
                shoppingcart.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[shoppingcarts.size()];
        int n = 0;
        for(Shoppingcart shoppingcart : shoppingcarts){
            shoppingcart = insert(DAO, shoppingcart, TABLENAME2);
            ret[n++] = (shoppingcart == null) ? 0 : (int)shoppingcart.id;
        }
        return ret;
    }

    public static int delete(Shoppingcart shoppingcart) {
        int id = shoppingcart.id;
        ShoppingcartDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ShoppingcartDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ShoppingcartDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ShoppingcartDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Shoppingcart shoppingcart) {
        int id = shoppingcart.id;
        ShoppingcartDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ShoppingcartDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ShoppingcartDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ShoppingcartDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ShoppingcartDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ShoppingcartDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ShoppingcartDAO DAO, int[] ids,String TABLENAME2) {
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
        ShoppingcartDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ShoppingcartDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ShoppingcartDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Shoppingcart> beans) {
        ShoppingcartDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Shoppingcart> beans, ShoppingcartDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Shoppingcart> beans, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Shoppingcart> beans, final ShoppingcartDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Shoppingcart shoppingcart : beans){
                int id = shoppingcart.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Shoppingcart> getAll() {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getAll(ShoppingcartDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getAll(String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Shoppingcart> getAll(final ShoppingcartDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Shoppingcart> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Shoppingcart> getNoSortAll() {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getNoSortAll(ShoppingcartDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getNoSortAll(String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Shoppingcart> getNoSortAll(final ShoppingcartDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Shoppingcart> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Shoppingcart> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Shoppingcart shoppingcart = FIXED[i];
                if (shoppingcart != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Shoppingcart> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Shoppingcart> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ShoppingcartDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ShoppingcartDAO DAO, final String TABLENAME2) {
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
        ShoppingcartDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ShoppingcartDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ShoppingcartDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Shoppingcart> getIn(List<Integer> keys) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getIn(List<Integer> keys, ShoppingcartDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getIn(List<Integer> keys, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Shoppingcart> getIn(final List<Integer> keys, final ShoppingcartDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Shoppingcart> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Shoppingcart> getNoSortIn(List<Integer> keys) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getNoSortIn(List<Integer> keys, ShoppingcartDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Shoppingcart> getNoSortIn(final List<Integer> keys, final ShoppingcartDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Shoppingcart> result = newList();
            for (int key : keys) {
                Shoppingcart shoppingcart = getByKeyFromMemory(key);
                if( shoppingcart == null ) continue;
                result.add(shoppingcart);
            }
            return result;
        }
    }

    public static List<Shoppingcart> getLast(int num) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getLast(ShoppingcartDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getLast(int num, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Shoppingcart> getLast(final ShoppingcartDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Shoppingcart> result = newList();
            List<Shoppingcart> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Shoppingcart last() {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Shoppingcart last(ShoppingcartDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Shoppingcart last(String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Shoppingcart last(final ShoppingcartDAO DAO, final String TABLENAME2) {
        Shoppingcart result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ShoppingcartDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ShoppingcartDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ShoppingcartDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Shoppingcart> getGtKey(int id) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getGtKey(ShoppingcartDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getGtKey(int id, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Shoppingcart> getGtKey(final ShoppingcartDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Shoppingcart> result = newList();
            List<Shoppingcart> shoppingcarts = getAll();
            for (Shoppingcart shoppingcart : shoppingcarts) {
                if(shoppingcart.id <= id) continue;
                result.add(shoppingcart);
            }
            return result;
        }
    }

    public static Shoppingcart getByKey(int id) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Shoppingcart> asyncGetByKey(final int id) {
        Future<Shoppingcart> f = Async.exec(new Callable<Shoppingcart>() {
            public Shoppingcart call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Shoppingcart getByKey(ShoppingcartDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Shoppingcart getByKey(int id, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Shoppingcart getByKey(final ShoppingcartDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Shoppingcart getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Shoppingcart deleteFromMemory(final int id) {
        Shoppingcart shoppingcart;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            shoppingcart = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(shoppingcart);
        } else {
            shoppingcart = vars.remove(id);
        }
        if(shoppingcart == null) return null;

        { // custid_kindid
            int vcustomerid = shoppingcart.getCustomerid();
            int vkindid = shoppingcart.getKindid();
            String vkey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
            Set m1 = varsByCustomeridKindid.get(vkey);
            if(m1 != null) { 
                m1.remove(id);
                if(m1.isEmpty())
                    varsByCustomeridKindid.remove(vkey);
            }
        }

        int customerid = shoppingcart.getCustomerid();
        Set m2 = varsByCustomerid.get(customerid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int kindid = shoppingcart.getKindid();
        Set m3 = varsByKindid.get(kindid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByKindid.remove(kindid);
        }

        return shoppingcart;
    }

    public static List<Shoppingcart> getByPage(int page, int size) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByPage(ShoppingcartDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByPage(int page, int size, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Shoppingcart> getByPage(final ShoppingcartDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Shoppingcart> result = newList();
            List<Shoppingcart> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ShoppingcartDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ShoppingcartDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustomeridKindid(Integer customerid, Integer kindid) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static int countByCustomeridKindid(ShoppingcartDAO DAO, Integer customerid, Integer kindid) {
        return countByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static int countByCustomeridKindid(Integer customerid, Integer kindid, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomeridKindid(DAO, customerid, kindid, TABLENAME2);
    }

    public static int countByCustomeridKindid(final ShoppingcartDAO DAO, Integer customerid, Integer kindid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomeridKindid(customerid, kindid, TABLENAME2);
        }
        List<Shoppingcart> shoppingcarts = getByCustomeridKindid(customerid, kindid, TABLENAME2);
        return shoppingcarts.size();
    }

    public static List<Shoppingcart> getByCustomeridKindid(Integer customerid, Integer kindid) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByCustomeridKindid(ShoppingcartDAO DAO, Integer customerid, Integer kindid) {
        return getByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByCustomeridKindid(Integer customerid, Integer kindid, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomeridKindid(DAO, customerid, kindid, TABLENAME2);
    }

    public static List<Shoppingcart> getByCustomeridKindid(final ShoppingcartDAO DAO, Integer customerid, Integer kindid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomeridKindid(customerid, kindid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Shoppingcart> result = newList();
            String vkey = customerid+"-"+kindid;
            Set<Integer> m1 = varsByCustomeridKindid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Shoppingcart e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _customerid = e.getCustomerid();
                int _kindid = e.getKindid();
                String _key = com.bowlong.lang.PStr.b().a(_customerid, "-", _kindid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByCustomerid(Integer customerid) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(ShoppingcartDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final ShoppingcartDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Shoppingcart> shoppingcarts = getByCustomerid(DAO, customerid, TABLENAME2);
        return shoppingcarts.size();
    }

    public static List<Shoppingcart> getByCustomerid(Integer customerid) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByCustomerid(ShoppingcartDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByCustomerid(Integer customerid, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Shoppingcart> getByCustomerid(final ShoppingcartDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Shoppingcart> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Shoppingcart e = getByKey(DAO, key, TABLENAME2);
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
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(ShoppingcartDAO DAO, Integer kindid) {
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Integer kindid, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, TABLENAME2);
    }

    public static int countByKindid(final ShoppingcartDAO DAO, final Integer kindid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKindid(kindid, TABLENAME2);
        }
        List<Shoppingcart> shoppingcarts = getByKindid(DAO, kindid, TABLENAME2);
        return shoppingcarts.size();
    }

    public static List<Shoppingcart> getByKindid(Integer kindid) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByKindid(ShoppingcartDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Shoppingcart> getByKindid(Integer kindid, String TABLENAME2) {
        ShoppingcartDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static List<Shoppingcart> getByKindid(final ShoppingcartDAO DAO, final Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Shoppingcart> result = newList();
            Set<Integer> m1 = varsByKindid.get(kindid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Shoppingcart e = getByKey(DAO, key, TABLENAME2);
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

    public static Shoppingcart update(Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = DAO();
        return update(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart update(ShoppingcartDAO DAO, Shoppingcart shoppingcart) {
        return update(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart update(Shoppingcart shoppingcart, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return update(DAO, shoppingcart, TABLENAME2);
    }

    public static Shoppingcart update(final ShoppingcartDAO DAO, final Shoppingcart shoppingcart,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(shoppingcart, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(shoppingcart, TABLENAME2);
            if(n == -1) 
                return shoppingcart;
            else if(n <= 0) 
                return null;
        }
        return shoppingcart;
    }

    public static Shoppingcart asyncUpdate(Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = DAO();
        return asyncUpdate(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart asyncUpdate(ShoppingcartDAO DAO, Shoppingcart shoppingcart) {
        return asyncUpdate(DAO, shoppingcart, DAO.TABLENAME);
    }

    public static Shoppingcart asyncUpdate(Shoppingcart shoppingcart, String TABLENAME2) {
        ShoppingcartDAO DAO = DAO();
        return asyncUpdate(DAO, shoppingcart, TABLENAME2);
    }

    public static Shoppingcart asyncUpdate(final ShoppingcartDAO DAO, final Shoppingcart shoppingcart,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(shoppingcart, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(shoppingcart, TABLENAME2);
        }
        return shoppingcart;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ShoppingcartDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ShoppingcartDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ShoppingcartDAO DAO, final String TABLENAME2) {
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

        List<Shoppingcart> shoppingcarts = getAll();
        for (Shoppingcart shoppingcart : shoppingcarts) {
            int n = DAO.insert2(shoppingcart, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
