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

import static com.learnhall.db.bean.Appraise.Col;

//learnhall3_design - appraise
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AppraiseInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AppraiseInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AppraiseInternal(){}

    public static AppraiseDAO DAO(){
        return AppraiseEntity.AppraiseDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Appraise[MAX];
    }
    private static Appraise[] FIXED = new Appraise[MAX];
    public static final Map<Integer, Appraise> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKindid = newSortedMap();

    private static final List<Appraise> fixedCache = newList();

    public static void put(Appraise appraise, boolean force){
        if(appraise == null || appraise.id <= 0) return ;

        int id = appraise.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(appraise))
                	FIXED[id - 1] = appraise;
                if (!fixedCache.contains(appraise))
                	fixedCache.add(appraise);
            }
        } else {
            vars.put(id, appraise);
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = appraise.oldVal(Col.customerid);
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
            int customerid = appraise.getCustomerid();
            Set m1 = varsByCustomerid.get(customerid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByCustomerid.put(customerid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kindid
          Object ov = appraise.oldVal(Col.kindid);
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
            int kindid = appraise.getKindid();
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
        varsByCustomerid.clear();
        varsByKindid.clear();
        FIXED = new Appraise[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AppraiseDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AppraiseDAO DAO, String TABLENAME2){
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

    public static void relocate(AppraiseDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AppraiseDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AppraiseDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AppraiseDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AppraiseDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AppraiseDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AppraiseDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AppraiseDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AppraiseDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AppraiseDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AppraiseDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AppraiseDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AppraiseDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Appraise[maxId(DAO)];

        List<Appraise> appraises = DAO.selectAll();
        for (Appraise appraise : appraises) {
            appraise.reset();
            put(appraise, true);
        }
    }

    public static Map toMap(Appraise appraise){
        return appraise.toMap();
    }

    public static List<Map> toMap(List<Appraise> appraises){
        List<Map> ret = new Vector<Map>();
        for (Appraise appraise : appraises){
            Map e = appraise.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Appraise> sortZh(List<Appraise> appraises, final String key) {
        Collections.sort(appraises, new Comparator<Appraise>() {
            public int compare(Appraise o1, Appraise o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return appraises;
    }

    public static List<Appraise> sort(List<Appraise> appraises, final String key) {
        Collections.sort(appraises, new Comparator<Appraise>() {
            public int compare(Appraise o1, Appraise o2) {
                return o1.compareTo(o2, key);
            }
        });
        return appraises;
    }

    public static List<Appraise> sort(List<Appraise> appraises){
        Collections.sort(appraises, new Comparator<Appraise>(){
            public int compare(Appraise o1, Appraise o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return appraises;
    }

    public static List<Appraise> sortReverse(List<Appraise> appraises){
        Collections.sort(appraises, new Comparator<Appraise>(){
            public int compare(Appraise o1, Appraise o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return appraises;
    }

    public static List<Appraise> sortCustomerid(List<Appraise> appraises){
        Collections.sort(appraises, new Comparator<Appraise>(){
            public int compare(Appraise o1, Appraise o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return appraises;
    }

    public static List<Appraise> sortCustomeridRo(List<Appraise> appraises){
        Collections.sort(appraises, new Comparator<Appraise>(){
            public int compare(Appraise o1, Appraise o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return appraises;
    }

    public static List<Appraise> sortKindid(List<Appraise> appraises){
        Collections.sort(appraises, new Comparator<Appraise>(){
            public int compare(Appraise o1, Appraise o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return appraises;
    }

    public static List<Appraise> sortKindidRo(List<Appraise> appraises){
        Collections.sort(appraises, new Comparator<Appraise>(){
            public int compare(Appraise o1, Appraise o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return appraises;
    }

    public static Appraise insert(Appraise appraise) {
        AppraiseDAO DAO = DAO();
        return insert(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise insert(AppraiseDAO DAO, Appraise appraise) {
        return insert(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise insert(Appraise appraise, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return insert(DAO, appraise, TABLENAME2);
    }

    public static Appraise insert(AppraiseDAO DAO, Appraise appraise, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(appraise, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        appraise.id = n;
        if(cacheModel != NO_CACHE) put(appraise, true);

        return appraise;
    }

    public static Appraise asyncInsert(Appraise appraise) {
        AppraiseDAO DAO = DAO();
        return asyncInsert(DAO, appraise, DAO.TABLENAME);
    }
    public static Appraise asyncInsert(AppraiseDAO DAO, Appraise appraise) {
        return asyncInsert(DAO, appraise, DAO.TABLENAME);
    }
    public static Appraise asyncInsert(Appraise appraise, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return asyncInsert(DAO, appraise, TABLENAME2);
    }
    public static Appraise asyncInsert(AppraiseDAO DAO, Appraise appraise, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(appraise, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        appraise.id = n;
        if(cacheModel != NO_CACHE) put(appraise, true);
        return appraise;
    }
    public static Appraise insert2(Appraise appraise) {
        AppraiseDAO DAO = DAO();
        return insert2(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise insert2(AppraiseDAO DAO, Appraise appraise) {
        return insert2(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise insert2(Appraise appraise, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return insert2(DAO, appraise, TABLENAME2);
    }

    public static Appraise insert2(AppraiseDAO DAO, Appraise appraise, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(appraise, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        appraise.id = n;
        if(cacheModel != NO_CACHE) put(appraise, true);

        return appraise;
    }

    public static Appraise asyncInsert2(Appraise appraise) {
        AppraiseDAO DAO = DAO();
        return asyncInsert2(DAO, appraise, DAO.TABLENAME);
    }
    public static Appraise asyncInsert2(AppraiseDAO DAO, Appraise appraise) {
        return asyncInsert2(DAO, appraise, DAO.TABLENAME);
    }
    public static Appraise asyncInsert2(Appraise appraise, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return asyncInsert2(DAO, appraise, TABLENAME2);
    }
    public static Appraise asyncInsert2(AppraiseDAO DAO, Appraise appraise, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        appraise.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(appraise, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(appraise, true);
        return appraise;
    }
    public static int[] insert(List<Appraise> appraises) {
        AppraiseDAO DAO = DAO();
        return insert(DAO, appraises, DAO.TABLENAME);
    }

    public static int[] insert(AppraiseDAO DAO, List<Appraise> appraises) {
        return insert(DAO, appraises, DAO.TABLENAME);
    }

    public static int[] insert(List<Appraise> appraises, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return insert(DAO, appraises, TABLENAME2);
    }

    public static int[] insert(AppraiseDAO DAO, List<Appraise> appraises, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(appraises, TABLENAME2);
            int n = 0;
            for(Appraise appraise : appraises){
                appraise.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[appraises.size()];
        int n = 0;
        for(Appraise appraise : appraises){
            appraise = insert(DAO, appraise, TABLENAME2);
            ret[n++] = (appraise == null) ? 0 : (int)appraise.id;
        }
        return ret;
    }

    public static int delete(Appraise appraise) {
        int id = appraise.id;
        AppraiseDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        AppraiseDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(AppraiseDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(AppraiseDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Appraise appraise) {
        int id = appraise.id;
        AppraiseDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        AppraiseDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(AppraiseDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(AppraiseDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        AppraiseDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(AppraiseDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(AppraiseDAO DAO, int[] ids,String TABLENAME2) {
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
        AppraiseDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AppraiseDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AppraiseDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Appraise> beans) {
        AppraiseDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Appraise> beans, AppraiseDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Appraise> beans, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Appraise> beans, final AppraiseDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Appraise appraise : beans){
                int id = appraise.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Appraise> getAll() {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getAll(AppraiseDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getAll(String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Appraise> getAll(final AppraiseDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Appraise> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Appraise> getNoSortAll() {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getNoSortAll(AppraiseDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getNoSortAll(String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Appraise> getNoSortAll(final AppraiseDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Appraise> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Appraise> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Appraise appraise = FIXED[i];
                if (appraise != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Appraise> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Appraise> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AppraiseDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AppraiseDAO DAO, final String TABLENAME2) {
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
        AppraiseDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AppraiseDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AppraiseDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Appraise> getIn(List<Integer> keys) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getIn(List<Integer> keys, AppraiseDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getIn(List<Integer> keys, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Appraise> getIn(final List<Integer> keys, final AppraiseDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Appraise> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Appraise> getNoSortIn(List<Integer> keys) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getNoSortIn(List<Integer> keys, AppraiseDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Appraise> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Appraise> getNoSortIn(final List<Integer> keys, final AppraiseDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Appraise> result = newList();
            for (int key : keys) {
                Appraise appraise = getByKeyFromMemory(key);
                if( appraise == null ) continue;
                result.add(appraise);
            }
            return result;
        }
    }

    public static List<Appraise> getLast(int num) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Appraise> getLast(AppraiseDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Appraise> getLast(int num, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Appraise> getLast(final AppraiseDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Appraise> result = newList();
            List<Appraise> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Appraise last() {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Appraise last(AppraiseDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Appraise last(String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Appraise last(final AppraiseDAO DAO, final String TABLENAME2) {
        Appraise result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AppraiseDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AppraiseDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AppraiseDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Appraise> getGtKey(int id) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Appraise> getGtKey(AppraiseDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Appraise> getGtKey(int id, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Appraise> getGtKey(final AppraiseDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Appraise> result = newList();
            List<Appraise> appraises = getAll();
            for (Appraise appraise : appraises) {
                if(appraise.id <= id) continue;
                result.add(appraise);
            }
            return result;
        }
    }

    public static Appraise getByKey(int id) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Appraise> asyncGetByKey(final int id) {
        Future<Appraise> f = Async.exec(new Callable<Appraise>() {
            public Appraise call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Appraise getByKey(AppraiseDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Appraise getByKey(int id, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Appraise getByKey(final AppraiseDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Appraise getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Appraise deleteFromMemory(final int id) {
        Appraise appraise;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            appraise = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(appraise);
        } else {
            appraise = vars.remove(id);
        }
        if(appraise == null) return null;

        int customerid = appraise.getCustomerid();
        Set m1 = varsByCustomerid.get(customerid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int kindid = appraise.getKindid();
        Set m2 = varsByKindid.get(kindid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByKindid.remove(kindid);
        }

        return appraise;
    }

    public static List<Appraise> getByPage(int page, int size) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Appraise> getByPage(AppraiseDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Appraise> getByPage(int page, int size, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Appraise> getByPage(final AppraiseDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Appraise> result = newList();
            List<Appraise> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AppraiseDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AppraiseDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustomerid(Integer customerid) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(AppraiseDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final AppraiseDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Appraise> appraises = getByCustomerid(DAO, customerid, TABLENAME2);
        return appraises.size();
    }

    public static List<Appraise> getByCustomerid(Integer customerid) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Appraise> getByCustomerid(AppraiseDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Appraise> getByCustomerid(Integer customerid, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Appraise> getByCustomerid(final AppraiseDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Appraise> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Appraise e = getByKey(DAO, key, TABLENAME2);
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
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(AppraiseDAO DAO, Integer kindid) {
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Integer kindid, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, TABLENAME2);
    }

    public static int countByKindid(final AppraiseDAO DAO, final Integer kindid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKindid(kindid, TABLENAME2);
        }
        List<Appraise> appraises = getByKindid(DAO, kindid, TABLENAME2);
        return appraises.size();
    }

    public static List<Appraise> getByKindid(Integer kindid) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Appraise> getByKindid(AppraiseDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Appraise> getByKindid(Integer kindid, String TABLENAME2) {
        AppraiseDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static List<Appraise> getByKindid(final AppraiseDAO DAO, final Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Appraise> result = newList();
            Set<Integer> m1 = varsByKindid.get(kindid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Appraise e = getByKey(DAO, key, TABLENAME2);
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

    public static Appraise update(Appraise appraise) {
        AppraiseDAO DAO = DAO();
        return update(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise update(AppraiseDAO DAO, Appraise appraise) {
        return update(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise update(Appraise appraise, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return update(DAO, appraise, TABLENAME2);
    }

    public static Appraise update(final AppraiseDAO DAO, final Appraise appraise,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(appraise, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(appraise, TABLENAME2);
            if(n == -1) 
                return appraise;
            else if(n <= 0) 
                return null;
        }
        return appraise;
    }

    public static Appraise asyncUpdate(Appraise appraise) {
        AppraiseDAO DAO = DAO();
        return asyncUpdate(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise asyncUpdate(AppraiseDAO DAO, Appraise appraise) {
        return asyncUpdate(DAO, appraise, DAO.TABLENAME);
    }

    public static Appraise asyncUpdate(Appraise appraise, String TABLENAME2) {
        AppraiseDAO DAO = DAO();
        return asyncUpdate(DAO, appraise, TABLENAME2);
    }

    public static Appraise asyncUpdate(final AppraiseDAO DAO, final Appraise appraise,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(appraise, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(appraise, TABLENAME2);
        }
        return appraise;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AppraiseDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AppraiseDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AppraiseDAO DAO, final String TABLENAME2) {
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

        List<Appraise> appraises = getAll();
        for (Appraise appraise : appraises) {
            int n = DAO.insert2(appraise, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
