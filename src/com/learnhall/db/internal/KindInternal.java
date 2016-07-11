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

import static com.learnhall.db.bean.Kind.Col;

//learnhall3_design - kind
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class KindInternal extends InternalSupport{
    static Log log = LogFactory.getLog(KindInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public KindInternal(){}

    public static KindDAO DAO(){
        return KindEntity.KindDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Kind[MAX];
    }
    private static Kind[] FIXED = new Kind[MAX];
    public static final Map<Integer, Kind> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByProductid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCoursid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKclassid = newSortedMap();
    public static final Map<String, Integer> varsByKclassidProductidLhubid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByProductidLhubid = newSortedMap();

    private static final List<Kind> fixedCache = newList();

    public static void put(Kind kind, boolean force){
        if(kind == null || kind.id <= 0) return ;

        int id = kind.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(kind))
                	FIXED[id - 1] = kind;
                if (!fixedCache.contains(kind))
                	fixedCache.add(kind);
            }
        } else {
            vars.put(id, kind);
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = kind.oldVal(Col.lhubid);
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
            int lhubid = kind.getLhubid();
            Set m1 = varsByLhubid.get(lhubid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByLhubid.put(lhubid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index productid
          Object ov = kind.oldVal(Col.productid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByProductid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByProductid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int productid = kind.getProductid();
            Set m2 = varsByProductid.get(productid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByProductid.put(productid, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index coursid
          Object ov = kind.oldVal(Col.coursid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByCoursid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByCoursid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int coursid = kind.getCoursid();
            Set m3 = varsByCoursid.get(coursid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByCoursid.put(coursid, m3);
            }
            m3.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kclassid
          Object ov = kind.oldVal(Col.kclassid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m4 = varsByKclassid.get(_val);
              if(m4 != null) {
                  m4.remove(id);
                  if(m4.isEmpty())
                      varsByKclassid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int kclassid = kind.getKclassid();
            Set m4 = varsByKclassid.get(kclassid);
            if(m4 == null){
                m4 = newSortedSet();
                varsByKclassid.put(kclassid, m4);
            }
            m4.add(id);
          }
        }

        { // kc_pr_lhub
          boolean bChanged = kind.inChanged(Col.kclassid, Col.productid, Col.lhubid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vkclassid = kind.oldOrNew(Col.kclassid);
            Object vproductid = kind.oldOrNew(Col.productid);
            Object vlhubid = kind.oldOrNew(Col.lhubid);
            String okey = com.bowlong.lang.PStr.b().a(vkclassid, "-", vproductid, "-", vlhubid).e();
            varsByKclassidProductidLhubid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vkclassid = kind.getKclassid();
              int vproductid = kind.getProductid();
              int vlhubid = kind.getLhubid();
              String vkey = com.bowlong.lang.PStr.b().a(vkclassid, "-", vproductid, "-", vlhubid).e();
              varsByKclassidProductidLhubid.put(vkey, id);
          }
        }

        { // productid_lhubid
          boolean bChanged = kind.inChanged(Col.productid, Col.lhubid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vproductid = kind.oldOrNew(Col.productid);
              Object vlhubid = kind.oldOrNew(Col.lhubid);
              String okey = com.bowlong.lang.PStr.b().a(vproductid, "-", vlhubid).e();
              Set m5 = varsByProductidLhubid.get(okey);
              if(m5 != null) {
                  m5.remove(id);
                  if(m5.isEmpty())
                       varsByProductidLhubid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vproductid = kind.getProductid();
              int vlhubid = kind.getLhubid();
              String vkey = com.bowlong.lang.PStr.b().a(vproductid, "-", vlhubid).e();
              Set m5 = varsByProductidLhubid.get(vkey);
              if(m5 == null){
                  m5 = newSortedSet();
                  varsByProductidLhubid.put(vkey, m5);
              }
              m5.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLhubid.clear();
        varsByProductid.clear();
        varsByCoursid.clear();
        varsByKclassid.clear();
        varsByKclassidProductidLhubid.clear();
        varsByProductidLhubid.clear();
        FIXED = new Kind[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(KindDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(KindDAO DAO, String TABLENAME2){
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

    public static void relocate(KindDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        KindDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(KindDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        KindDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(KindDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        KindDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(KindDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        KindDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(KindDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(KindDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        KindDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(KindDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(KindDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        KindDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(KindDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Kind[maxId(DAO)];

        List<Kind> kinds = DAO.selectAll();
        for (Kind kind : kinds) {
            kind.reset();
            put(kind, true);
        }
    }

    public static Map toMap(Kind kind){
        return kind.toMap();
    }

    public static List<Map> toMap(List<Kind> kinds){
        List<Map> ret = new Vector<Map>();
        for (Kind kind : kinds){
            Map e = kind.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Kind> sortZh(List<Kind> kinds, final String key) {
        Collections.sort(kinds, new Comparator<Kind>() {
            public int compare(Kind o1, Kind o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return kinds;
    }

    public static List<Kind> sort(List<Kind> kinds, final String key) {
        Collections.sort(kinds, new Comparator<Kind>() {
            public int compare(Kind o1, Kind o2) {
                return o1.compareTo(o2, key);
            }
        });
        return kinds;
    }

    public static List<Kind> sort(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return kinds;
    }

    public static List<Kind> sortReverse(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return kinds;
    }

    public static List<Kind> sortLhubid(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return kinds;
    }

    public static List<Kind> sortLhubidRo(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return kinds;
    }

    public static List<Kind> sortProductid(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getProductid();
                Object v2 = o2.getProductid();
                return compareTo(v1, v2);
            }
        });
        return kinds;
    }

    public static List<Kind> sortProductidRo(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getProductid();
                Object v2 = o2.getProductid();
                return 0 - compareTo(v1, v2);
            };
        });
        return kinds;
    }

    public static List<Kind> sortCoursid(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getCoursid();
                Object v2 = o2.getCoursid();
                return compareTo(v1, v2);
            }
        });
        return kinds;
    }

    public static List<Kind> sortCoursidRo(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getCoursid();
                Object v2 = o2.getCoursid();
                return 0 - compareTo(v1, v2);
            };
        });
        return kinds;
    }

    public static List<Kind> sortKclassid(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getKclassid();
                Object v2 = o2.getKclassid();
                return compareTo(v1, v2);
            }
        });
        return kinds;
    }

    public static List<Kind> sortKclassidRo(List<Kind> kinds){
        Collections.sort(kinds, new Comparator<Kind>(){
            public int compare(Kind o1, Kind o2) {
                Object v1 = o1.getKclassid();
                Object v2 = o2.getKclassid();
                return 0 - compareTo(v1, v2);
            };
        });
        return kinds;
    }

    public static Kind insert(Kind kind) {
        KindDAO DAO = DAO();
        return insert(DAO, kind, DAO.TABLENAME);
    }

    public static Kind insert(KindDAO DAO, Kind kind) {
        return insert(DAO, kind, DAO.TABLENAME);
    }

    public static Kind insert(Kind kind, String TABLENAME2) {
        KindDAO DAO = DAO();
        return insert(DAO, kind, TABLENAME2);
    }

    public static Kind insert(KindDAO DAO, Kind kind, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(kind, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        kind.id = n;
        if(cacheModel != NO_CACHE) put(kind, true);

        return kind;
    }

    public static Kind asyncInsert(Kind kind) {
        KindDAO DAO = DAO();
        return asyncInsert(DAO, kind, DAO.TABLENAME);
    }
    public static Kind asyncInsert(KindDAO DAO, Kind kind) {
        return asyncInsert(DAO, kind, DAO.TABLENAME);
    }
    public static Kind asyncInsert(Kind kind, String TABLENAME2) {
        KindDAO DAO = DAO();
        return asyncInsert(DAO, kind, TABLENAME2);
    }
    public static Kind asyncInsert(KindDAO DAO, Kind kind, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(kind, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        kind.id = n;
        if(cacheModel != NO_CACHE) put(kind, true);
        return kind;
    }
    public static Kind insert2(Kind kind) {
        KindDAO DAO = DAO();
        return insert2(DAO, kind, DAO.TABLENAME);
    }

    public static Kind insert2(KindDAO DAO, Kind kind) {
        return insert2(DAO, kind, DAO.TABLENAME);
    }

    public static Kind insert2(Kind kind, String TABLENAME2) {
        KindDAO DAO = DAO();
        return insert2(DAO, kind, TABLENAME2);
    }

    public static Kind insert2(KindDAO DAO, Kind kind, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(kind, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        kind.id = n;
        if(cacheModel != NO_CACHE) put(kind, true);

        return kind;
    }

    public static Kind asyncInsert2(Kind kind) {
        KindDAO DAO = DAO();
        return asyncInsert2(DAO, kind, DAO.TABLENAME);
    }
    public static Kind asyncInsert2(KindDAO DAO, Kind kind) {
        return asyncInsert2(DAO, kind, DAO.TABLENAME);
    }
    public static Kind asyncInsert2(Kind kind, String TABLENAME2) {
        KindDAO DAO = DAO();
        return asyncInsert2(DAO, kind, TABLENAME2);
    }
    public static Kind asyncInsert2(KindDAO DAO, Kind kind, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        kind.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(kind, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(kind, true);
        return kind;
    }
    public static int[] insert(List<Kind> kinds) {
        KindDAO DAO = DAO();
        return insert(DAO, kinds, DAO.TABLENAME);
    }

    public static int[] insert(KindDAO DAO, List<Kind> kinds) {
        return insert(DAO, kinds, DAO.TABLENAME);
    }

    public static int[] insert(List<Kind> kinds, String TABLENAME2) {
        KindDAO DAO = DAO();
        return insert(DAO, kinds, TABLENAME2);
    }

    public static int[] insert(KindDAO DAO, List<Kind> kinds, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(kinds, TABLENAME2);
            int n = 0;
            for(Kind kind : kinds){
                kind.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[kinds.size()];
        int n = 0;
        for(Kind kind : kinds){
            kind = insert(DAO, kind, TABLENAME2);
            ret[n++] = (kind == null) ? 0 : (int)kind.id;
        }
        return ret;
    }

    public static int delete(Kind kind) {
        int id = kind.id;
        KindDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        KindDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(KindDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        KindDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(KindDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Kind kind) {
        int id = kind.id;
        KindDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        KindDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(KindDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        KindDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(KindDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        KindDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(KindDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        KindDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(KindDAO DAO, int[] ids,String TABLENAME2) {
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
        KindDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, KindDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        KindDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final KindDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Kind> beans) {
        KindDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Kind> beans, KindDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Kind> beans, String TABLENAME2) {
        KindDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Kind> beans, final KindDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Kind kind : beans){
                int id = kind.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Kind> getAll() {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Kind> getAll(KindDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Kind> getAll(String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Kind> getAll(final KindDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kind> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Kind> getNoSortAll() {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Kind> getNoSortAll(KindDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Kind> getNoSortAll(String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Kind> getNoSortAll(final KindDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Kind> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kind> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Kind kind = FIXED[i];
                if (kind != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Kind> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Kind> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(KindDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final KindDAO DAO, final String TABLENAME2) {
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
        KindDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(KindDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        KindDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final KindDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Kind> getIn(List<Integer> keys) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kind> getIn(List<Integer> keys, KindDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kind> getIn(List<Integer> keys, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Kind> getIn(final List<Integer> keys, final KindDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kind> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Kind> getNoSortIn(List<Integer> keys) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kind> getNoSortIn(List<Integer> keys, KindDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kind> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Kind> getNoSortIn(final List<Integer> keys, final KindDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Kind> result = newList();
            for (int key : keys) {
                Kind kind = getByKeyFromMemory(key);
                if( kind == null ) continue;
                result.add(kind);
            }
            return result;
        }
    }

    public static List<Kind> getLast(int num) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Kind> getLast(KindDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Kind> getLast(int num, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Kind> getLast(final KindDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Kind> result = newList();
            List<Kind> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Kind last() {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Kind last(KindDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Kind last(String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Kind last(final KindDAO DAO, final String TABLENAME2) {
        Kind result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        KindDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(KindDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        KindDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final KindDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Kind> getGtKey(int id) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Kind> getGtKey(KindDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Kind> getGtKey(int id, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Kind> getGtKey(final KindDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kind> result = newList();
            List<Kind> kinds = getAll();
            for (Kind kind : kinds) {
                if(kind.id <= id) continue;
                result.add(kind);
            }
            return result;
        }
    }

    public static Kind getByKey(int id) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Kind> asyncGetByKey(final int id) {
        Future<Kind> f = Async.exec(new Callable<Kind>() {
            public Kind call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Kind getByKey(KindDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Kind getByKey(int id, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Kind getByKey(final KindDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Kind getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Kind deleteFromMemory(final int id) {
        Kind kind;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            kind = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(kind);
        } else {
            kind = vars.remove(id);
        }
        if(kind == null) return null;

        int lhubid = kind.getLhubid();
        Set m1 = varsByLhubid.get(lhubid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        int productid = kind.getProductid();
        Set m2 = varsByProductid.get(productid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByProductid.remove(productid);
        }

        int coursid = kind.getCoursid();
        Set m3 = varsByCoursid.get(coursid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByCoursid.remove(coursid);
        }

        int kclassid = kind.getKclassid();
        Set m4 = varsByKclassid.get(kclassid);
        if(m4 != null) {
            m4.remove(id);
            if(m4.isEmpty())
                varsByKclassid.remove(kclassid);
        }

        { // kc_pr_lhub
            int vkclassid = kind.getKclassid();
            int vproductid = kind.getProductid();
            int vlhubid = kind.getLhubid();
            String vkey = com.bowlong.lang.PStr.b().a(vkclassid, "-", vproductid, "-", vlhubid).e();
            varsByKclassidProductidLhubid.remove(vkey);
        }

        { // productid_lhubid
            int vproductid = kind.getProductid();
            int vlhubid = kind.getLhubid();
            String vkey = com.bowlong.lang.PStr.b().a(vproductid, "-", vlhubid).e();
            Set m5 = varsByProductidLhubid.get(vkey);
            if(m5 != null) { 
                m5.remove(id);
                if(m5.isEmpty())
                    varsByProductidLhubid.remove(vkey);
            }
        }

        return kind;
    }

    public static List<Kind> getByPage(int page, int size) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Kind> getByPage(KindDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Kind> getByPage(int page, int size, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Kind> getByPage(final KindDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kind> result = newList();
            List<Kind> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(KindDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final KindDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByLhubid(Integer lhubid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(KindDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final KindDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Kind> kinds = getByLhubid(DAO, lhubid, TABLENAME2);
        return kinds.size();
    }

    public static List<Kind> getByLhubid(Integer lhubid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Kind> getByLhubid(KindDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Kind> getByLhubid(Integer lhubid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Kind> getByLhubid(final KindDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Kind> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Kind e = getByKey(DAO, key, TABLENAME2);
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
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static int countByProductid(KindDAO DAO, Integer productid) {
        return countByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static int countByProductid(Integer productid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByProductid(DAO, productid, TABLENAME2);
    }

    public static int countByProductid(final KindDAO DAO, final Integer productid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByProductid(productid, TABLENAME2);
        }
        List<Kind> kinds = getByProductid(DAO, productid, TABLENAME2);
        return kinds.size();
    }

    public static List<Kind> getByProductid(Integer productid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static List<Kind> getByProductid(KindDAO DAO, Integer productid) {
        return getByProductid(DAO, productid, DAO.TABLENAME);
    }

    public static List<Kind> getByProductid(Integer productid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByProductid(DAO, productid, TABLENAME2);
    }

    public static List<Kind> getByProductid(final KindDAO DAO, final Integer productid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByProductid(productid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Kind> result = newList();
            Set<Integer> m1 = varsByProductid.get(productid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Kind e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByCoursid(Integer coursid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCoursid(DAO, coursid, DAO.TABLENAME);
    }

    public static int countByCoursid(KindDAO DAO, Integer coursid) {
        return countByCoursid(DAO, coursid, DAO.TABLENAME);
    }

    public static int countByCoursid(Integer coursid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCoursid(DAO, coursid, TABLENAME2);
    }

    public static int countByCoursid(final KindDAO DAO, final Integer coursid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCoursid(coursid, TABLENAME2);
        }
        List<Kind> kinds = getByCoursid(DAO, coursid, TABLENAME2);
        return kinds.size();
    }

    public static List<Kind> getByCoursid(Integer coursid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCoursid(DAO, coursid, DAO.TABLENAME);
    }

    public static List<Kind> getByCoursid(KindDAO DAO, Integer coursid) {
        return getByCoursid(DAO, coursid, DAO.TABLENAME);
    }

    public static List<Kind> getByCoursid(Integer coursid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCoursid(DAO, coursid, TABLENAME2);
    }

    public static List<Kind> getByCoursid(final KindDAO DAO, final Integer coursid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCoursid(coursid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Kind> result = newList();
            Set<Integer> m1 = varsByCoursid.get(coursid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Kind e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _coursid = e.getCoursid();
                if(_coursid != coursid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByKclassid(Integer kclassid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKclassid(DAO, kclassid, DAO.TABLENAME);
    }

    public static int countByKclassid(KindDAO DAO, Integer kclassid) {
        return countByKclassid(DAO, kclassid, DAO.TABLENAME);
    }

    public static int countByKclassid(Integer kclassid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKclassid(DAO, kclassid, TABLENAME2);
    }

    public static int countByKclassid(final KindDAO DAO, final Integer kclassid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKclassid(kclassid, TABLENAME2);
        }
        List<Kind> kinds = getByKclassid(DAO, kclassid, TABLENAME2);
        return kinds.size();
    }

    public static List<Kind> getByKclassid(Integer kclassid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKclassid(DAO, kclassid, DAO.TABLENAME);
    }

    public static List<Kind> getByKclassid(KindDAO DAO, Integer kclassid) {
        return getByKclassid(DAO, kclassid, DAO.TABLENAME);
    }

    public static List<Kind> getByKclassid(Integer kclassid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKclassid(DAO, kclassid, TABLENAME2);
    }

    public static List<Kind> getByKclassid(final KindDAO DAO, final Integer kclassid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKclassid(kclassid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Kind> result = newList();
            Set<Integer> m1 = varsByKclassid.get(kclassid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Kind e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _kclassid = e.getKclassid();
                if(_kclassid != kclassid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Kind getByKclassidProductidLhubid(Integer kclassid, Integer productid, Integer lhubid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKclassidProductidLhubid(DAO, kclassid, productid, lhubid, DAO.TABLENAME);
    }

    public static Kind getByKclassidProductidLhubid(KindDAO DAO, Integer kclassid, Integer productid, Integer lhubid) {
        return getByKclassidProductidLhubid(DAO, kclassid, productid, lhubid, DAO.TABLENAME);
    }

    public static Kind getByKclassidProductidLhubid(Integer kclassid, Integer productid, Integer lhubid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKclassidProductidLhubid(DAO, kclassid, productid, lhubid, TABLENAME2);
    }

    public static Kind getByKclassidProductidLhubid(final KindDAO DAO, Integer kclassid, Integer productid, Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKclassidProductidLhubid(kclassid, productid, lhubid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = kclassid+"-"+productid+"-"+lhubid;
            Integer id = varsByKclassidProductidLhubid.get(vkey);
            if(id == null) return null;
            Kind result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getKclassid() != kclassid){
                varsByKclassidProductidLhubid.remove(vkey);
                return null;
            }
            if(result.getProductid() != productid){
                varsByKclassidProductidLhubid.remove(vkey);
                return null;
            }
            if(result.getLhubid() != lhubid){
                varsByKclassidProductidLhubid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static int countByProductidLhubid(Integer productid, Integer lhubid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByProductidLhubid(DAO, productid, lhubid, DAO.TABLENAME);
    }

    public static int countByProductidLhubid(KindDAO DAO, Integer productid, Integer lhubid) {
        return countByProductidLhubid(DAO, productid, lhubid, DAO.TABLENAME);
    }

    public static int countByProductidLhubid(Integer productid, Integer lhubid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByProductidLhubid(DAO, productid, lhubid, TABLENAME2);
    }

    public static int countByProductidLhubid(final KindDAO DAO, Integer productid, Integer lhubid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByProductidLhubid(productid, lhubid, TABLENAME2);
        }
        List<Kind> kinds = getByProductidLhubid(productid, lhubid, TABLENAME2);
        return kinds.size();
    }

    public static List<Kind> getByProductidLhubid(Integer productid, Integer lhubid) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByProductidLhubid(DAO, productid, lhubid, DAO.TABLENAME);
    }

    public static List<Kind> getByProductidLhubid(KindDAO DAO, Integer productid, Integer lhubid) {
        return getByProductidLhubid(DAO, productid, lhubid, DAO.TABLENAME);
    }

    public static List<Kind> getByProductidLhubid(Integer productid, Integer lhubid, String TABLENAME2) {
        KindDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByProductidLhubid(DAO, productid, lhubid, TABLENAME2);
    }

    public static List<Kind> getByProductidLhubid(final KindDAO DAO, Integer productid, Integer lhubid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByProductidLhubid(productid, lhubid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Kind> result = newList();
            String vkey = productid+"-"+lhubid;
            Set<Integer> m1 = varsByProductidLhubid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Kind e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _productid = e.getProductid();
                int _lhubid = e.getLhubid();
                String _key = com.bowlong.lang.PStr.b().a(_productid, "-", _lhubid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Kind update(Kind kind) {
        KindDAO DAO = DAO();
        return update(DAO, kind, DAO.TABLENAME);
    }

    public static Kind update(KindDAO DAO, Kind kind) {
        return update(DAO, kind, DAO.TABLENAME);
    }

    public static Kind update(Kind kind, String TABLENAME2) {
        KindDAO DAO = DAO();
        return update(DAO, kind, TABLENAME2);
    }

    public static Kind update(final KindDAO DAO, final Kind kind,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(kind, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(kind, TABLENAME2);
            if(n == -1) 
                return kind;
            else if(n <= 0) 
                return null;
        }
        return kind;
    }

    public static Kind asyncUpdate(Kind kind) {
        KindDAO DAO = DAO();
        return asyncUpdate(DAO, kind, DAO.TABLENAME);
    }

    public static Kind asyncUpdate(KindDAO DAO, Kind kind) {
        return asyncUpdate(DAO, kind, DAO.TABLENAME);
    }

    public static Kind asyncUpdate(Kind kind, String TABLENAME2) {
        KindDAO DAO = DAO();
        return asyncUpdate(DAO, kind, TABLENAME2);
    }

    public static Kind asyncUpdate(final KindDAO DAO, final Kind kind,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(kind, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(kind, TABLENAME2);
        }
        return kind;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        KindDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(KindDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final KindDAO DAO, final String TABLENAME2) {
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

        List<Kind> kinds = getAll();
        for (Kind kind : kinds) {
            int n = DAO.insert2(kind, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
