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

import static com.learnhall.db.bean.Examcatalog.Col;

//learnhall3_design - examcatalog
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ExamcatalogInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ExamcatalogInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ExamcatalogInternal(){}

    public static ExamcatalogDAO DAO(){
        return ExamcatalogEntity.ExamcatalogDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Examcatalog[MAX];
    }
    private static Examcatalog[] FIXED = new Examcatalog[MAX];
    public static final Map<Integer, Examcatalog> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByParentid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByExamid = newSortedMap();

    private static final List<Examcatalog> fixedCache = newList();

    public static void put(Examcatalog examcatalog, boolean force){
        if(examcatalog == null || examcatalog.id <= 0) return ;

        int id = examcatalog.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(examcatalog))
                	FIXED[id - 1] = examcatalog;
                if (!fixedCache.contains(examcatalog))
                	fixedCache.add(examcatalog);
            }
        } else {
            vars.put(id, examcatalog);
        }

        { // 单-非唯一索引 remove old index parentid
          Object ov = examcatalog.oldVal(Col.parentid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByParentid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByParentid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int parentid = examcatalog.getParentid();
            Set m1 = varsByParentid.get(parentid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByParentid.put(parentid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index examid
          Object ov = examcatalog.oldVal(Col.examid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByExamid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByExamid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int examid = examcatalog.getExamid();
            Set m2 = varsByExamid.get(examid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByExamid.put(examid, m2);
            }
            m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByParentid.clear();
        varsByExamid.clear();
        FIXED = new Examcatalog[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ExamcatalogDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ExamcatalogDAO DAO, String TABLENAME2){
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

    public static void relocate(ExamcatalogDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ExamcatalogDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ExamcatalogDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ExamcatalogDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ExamcatalogDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ExamcatalogDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ExamcatalogDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ExamcatalogDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ExamcatalogDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ExamcatalogDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ExamcatalogDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ExamcatalogDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ExamcatalogDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Examcatalog[maxId(DAO)];

        List<Examcatalog> examcatalogs = DAO.selectAll();
        for (Examcatalog examcatalog : examcatalogs) {
            examcatalog.reset();
            put(examcatalog, true);
        }
    }

    public static Map toMap(Examcatalog examcatalog){
        return examcatalog.toMap();
    }

    public static List<Map> toMap(List<Examcatalog> examcatalogs){
        List<Map> ret = new Vector<Map>();
        for (Examcatalog examcatalog : examcatalogs){
            Map e = examcatalog.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Examcatalog> sortZh(List<Examcatalog> examcatalogs, final String key) {
        Collections.sort(examcatalogs, new Comparator<Examcatalog>() {
            public int compare(Examcatalog o1, Examcatalog o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sort(List<Examcatalog> examcatalogs, final String key) {
        Collections.sort(examcatalogs, new Comparator<Examcatalog>() {
            public int compare(Examcatalog o1, Examcatalog o2) {
                return o1.compareTo(o2, key);
            }
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sort(List<Examcatalog> examcatalogs){
        Collections.sort(examcatalogs, new Comparator<Examcatalog>(){
            public int compare(Examcatalog o1, Examcatalog o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sortReverse(List<Examcatalog> examcatalogs){
        Collections.sort(examcatalogs, new Comparator<Examcatalog>(){
            public int compare(Examcatalog o1, Examcatalog o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sortParentid(List<Examcatalog> examcatalogs){
        Collections.sort(examcatalogs, new Comparator<Examcatalog>(){
            public int compare(Examcatalog o1, Examcatalog o2) {
                Object v1 = o1.getParentid();
                Object v2 = o2.getParentid();
                return compareTo(v1, v2);
            }
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sortParentidRo(List<Examcatalog> examcatalogs){
        Collections.sort(examcatalogs, new Comparator<Examcatalog>(){
            public int compare(Examcatalog o1, Examcatalog o2) {
                Object v1 = o1.getParentid();
                Object v2 = o2.getParentid();
                return 0 - compareTo(v1, v2);
            };
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sortExamid(List<Examcatalog> examcatalogs){
        Collections.sort(examcatalogs, new Comparator<Examcatalog>(){
            public int compare(Examcatalog o1, Examcatalog o2) {
                Object v1 = o1.getExamid();
                Object v2 = o2.getExamid();
                return compareTo(v1, v2);
            }
        });
        return examcatalogs;
    }

    public static List<Examcatalog> sortExamidRo(List<Examcatalog> examcatalogs){
        Collections.sort(examcatalogs, new Comparator<Examcatalog>(){
            public int compare(Examcatalog o1, Examcatalog o2) {
                Object v1 = o1.getExamid();
                Object v2 = o2.getExamid();
                return 0 - compareTo(v1, v2);
            };
        });
        return examcatalogs;
    }

    public static Examcatalog insert(Examcatalog examcatalog) {
        ExamcatalogDAO DAO = DAO();
        return insert(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog insert(ExamcatalogDAO DAO, Examcatalog examcatalog) {
        return insert(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog insert(Examcatalog examcatalog, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return insert(DAO, examcatalog, TABLENAME2);
    }

    public static Examcatalog insert(ExamcatalogDAO DAO, Examcatalog examcatalog, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(examcatalog, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        examcatalog.id = n;
        if(cacheModel != NO_CACHE) put(examcatalog, true);

        return examcatalog;
    }

    public static Examcatalog asyncInsert(Examcatalog examcatalog) {
        ExamcatalogDAO DAO = DAO();
        return asyncInsert(DAO, examcatalog, DAO.TABLENAME);
    }
    public static Examcatalog asyncInsert(ExamcatalogDAO DAO, Examcatalog examcatalog) {
        return asyncInsert(DAO, examcatalog, DAO.TABLENAME);
    }
    public static Examcatalog asyncInsert(Examcatalog examcatalog, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return asyncInsert(DAO, examcatalog, TABLENAME2);
    }
    public static Examcatalog asyncInsert(ExamcatalogDAO DAO, Examcatalog examcatalog, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(examcatalog, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        examcatalog.id = n;
        if(cacheModel != NO_CACHE) put(examcatalog, true);
        return examcatalog;
    }
    public static Examcatalog insert2(Examcatalog examcatalog) {
        ExamcatalogDAO DAO = DAO();
        return insert2(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog insert2(ExamcatalogDAO DAO, Examcatalog examcatalog) {
        return insert2(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog insert2(Examcatalog examcatalog, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return insert2(DAO, examcatalog, TABLENAME2);
    }

    public static Examcatalog insert2(ExamcatalogDAO DAO, Examcatalog examcatalog, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(examcatalog, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        examcatalog.id = n;
        if(cacheModel != NO_CACHE) put(examcatalog, true);

        return examcatalog;
    }

    public static Examcatalog asyncInsert2(Examcatalog examcatalog) {
        ExamcatalogDAO DAO = DAO();
        return asyncInsert2(DAO, examcatalog, DAO.TABLENAME);
    }
    public static Examcatalog asyncInsert2(ExamcatalogDAO DAO, Examcatalog examcatalog) {
        return asyncInsert2(DAO, examcatalog, DAO.TABLENAME);
    }
    public static Examcatalog asyncInsert2(Examcatalog examcatalog, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return asyncInsert2(DAO, examcatalog, TABLENAME2);
    }
    public static Examcatalog asyncInsert2(ExamcatalogDAO DAO, Examcatalog examcatalog, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        examcatalog.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(examcatalog, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(examcatalog, true);
        return examcatalog;
    }
    public static int[] insert(List<Examcatalog> examcatalogs) {
        ExamcatalogDAO DAO = DAO();
        return insert(DAO, examcatalogs, DAO.TABLENAME);
    }

    public static int[] insert(ExamcatalogDAO DAO, List<Examcatalog> examcatalogs) {
        return insert(DAO, examcatalogs, DAO.TABLENAME);
    }

    public static int[] insert(List<Examcatalog> examcatalogs, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return insert(DAO, examcatalogs, TABLENAME2);
    }

    public static int[] insert(ExamcatalogDAO DAO, List<Examcatalog> examcatalogs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(examcatalogs, TABLENAME2);
            int n = 0;
            for(Examcatalog examcatalog : examcatalogs){
                examcatalog.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[examcatalogs.size()];
        int n = 0;
        for(Examcatalog examcatalog : examcatalogs){
            examcatalog = insert(DAO, examcatalog, TABLENAME2);
            ret[n++] = (examcatalog == null) ? 0 : (int)examcatalog.id;
        }
        return ret;
    }

    public static int delete(Examcatalog examcatalog) {
        int id = examcatalog.id;
        ExamcatalogDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ExamcatalogDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ExamcatalogDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ExamcatalogDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Examcatalog examcatalog) {
        int id = examcatalog.id;
        ExamcatalogDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ExamcatalogDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ExamcatalogDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ExamcatalogDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ExamcatalogDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ExamcatalogDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ExamcatalogDAO DAO, int[] ids,String TABLENAME2) {
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
        ExamcatalogDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ExamcatalogDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ExamcatalogDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Examcatalog> beans) {
        ExamcatalogDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Examcatalog> beans, ExamcatalogDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Examcatalog> beans, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Examcatalog> beans, final ExamcatalogDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Examcatalog examcatalog : beans){
                int id = examcatalog.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Examcatalog> getAll() {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getAll(ExamcatalogDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getAll(String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Examcatalog> getAll(final ExamcatalogDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examcatalog> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Examcatalog> getNoSortAll() {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getNoSortAll(ExamcatalogDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getNoSortAll(String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Examcatalog> getNoSortAll(final ExamcatalogDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Examcatalog> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examcatalog> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Examcatalog examcatalog = FIXED[i];
                if (examcatalog != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Examcatalog> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Examcatalog> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ExamcatalogDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ExamcatalogDAO DAO, final String TABLENAME2) {
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
        ExamcatalogDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ExamcatalogDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ExamcatalogDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Examcatalog> getIn(List<Integer> keys) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getIn(List<Integer> keys, ExamcatalogDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getIn(List<Integer> keys, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Examcatalog> getIn(final List<Integer> keys, final ExamcatalogDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examcatalog> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Examcatalog> getNoSortIn(List<Integer> keys) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getNoSortIn(List<Integer> keys, ExamcatalogDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examcatalog> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Examcatalog> getNoSortIn(final List<Integer> keys, final ExamcatalogDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Examcatalog> result = newList();
            for (int key : keys) {
                Examcatalog examcatalog = getByKeyFromMemory(key);
                if( examcatalog == null ) continue;
                result.add(examcatalog);
            }
            return result;
        }
    }

    public static List<Examcatalog> getLast(int num) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Examcatalog> getLast(ExamcatalogDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Examcatalog> getLast(int num, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Examcatalog> getLast(final ExamcatalogDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Examcatalog> result = newList();
            List<Examcatalog> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Examcatalog last() {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Examcatalog last(ExamcatalogDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Examcatalog last(String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Examcatalog last(final ExamcatalogDAO DAO, final String TABLENAME2) {
        Examcatalog result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ExamcatalogDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ExamcatalogDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ExamcatalogDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Examcatalog> getGtKey(int id) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Examcatalog> getGtKey(ExamcatalogDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Examcatalog> getGtKey(int id, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Examcatalog> getGtKey(final ExamcatalogDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examcatalog> result = newList();
            List<Examcatalog> examcatalogs = getAll();
            for (Examcatalog examcatalog : examcatalogs) {
                if(examcatalog.id <= id) continue;
                result.add(examcatalog);
            }
            return result;
        }
    }

    public static Examcatalog getByKey(int id) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Examcatalog> asyncGetByKey(final int id) {
        Future<Examcatalog> f = Async.exec(new Callable<Examcatalog>() {
            public Examcatalog call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Examcatalog getByKey(ExamcatalogDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Examcatalog getByKey(int id, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Examcatalog getByKey(final ExamcatalogDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Examcatalog getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Examcatalog deleteFromMemory(final int id) {
        Examcatalog examcatalog;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            examcatalog = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(examcatalog);
        } else {
            examcatalog = vars.remove(id);
        }
        if(examcatalog == null) return null;

        int parentid = examcatalog.getParentid();
        Set m1 = varsByParentid.get(parentid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByParentid.remove(parentid);
        }

        int examid = examcatalog.getExamid();
        Set m2 = varsByExamid.get(examid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByExamid.remove(examid);
        }

        return examcatalog;
    }

    public static List<Examcatalog> getByPage(int page, int size) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Examcatalog> getByPage(ExamcatalogDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Examcatalog> getByPage(int page, int size, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Examcatalog> getByPage(final ExamcatalogDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examcatalog> result = newList();
            List<Examcatalog> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ExamcatalogDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ExamcatalogDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByParentid(Integer parentid) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static int countByParentid(ExamcatalogDAO DAO, Integer parentid) {
        return countByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static int countByParentid(Integer parentid, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByParentid(DAO, parentid, TABLENAME2);
    }

    public static int countByParentid(final ExamcatalogDAO DAO, final Integer parentid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByParentid(parentid, TABLENAME2);
        }
        List<Examcatalog> examcatalogs = getByParentid(DAO, parentid, TABLENAME2);
        return examcatalogs.size();
    }

    public static List<Examcatalog> getByParentid(Integer parentid) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static List<Examcatalog> getByParentid(ExamcatalogDAO DAO, Integer parentid) {
        return getByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static List<Examcatalog> getByParentid(Integer parentid, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByParentid(DAO, parentid, TABLENAME2);
    }

    public static List<Examcatalog> getByParentid(final ExamcatalogDAO DAO, final Integer parentid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByParentid(parentid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Examcatalog> result = newList();
            Set<Integer> m1 = varsByParentid.get(parentid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Examcatalog e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _parentid = e.getParentid();
                if(_parentid != parentid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByExamid(Integer examid) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static int countByExamid(ExamcatalogDAO DAO, Integer examid) {
        return countByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static int countByExamid(Integer examid, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamid(DAO, examid, TABLENAME2);
    }

    public static int countByExamid(final ExamcatalogDAO DAO, final Integer examid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamid(examid, TABLENAME2);
        }
        List<Examcatalog> examcatalogs = getByExamid(DAO, examid, TABLENAME2);
        return examcatalogs.size();
    }

    public static List<Examcatalog> getByExamid(Integer examid) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static List<Examcatalog> getByExamid(ExamcatalogDAO DAO, Integer examid) {
        return getByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static List<Examcatalog> getByExamid(Integer examid, String TABLENAME2) {
        ExamcatalogDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamid(DAO, examid, TABLENAME2);
    }

    public static List<Examcatalog> getByExamid(final ExamcatalogDAO DAO, final Integer examid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamid(examid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Examcatalog> result = newList();
            Set<Integer> m1 = varsByExamid.get(examid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Examcatalog e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _examid = e.getExamid();
                if(_examid != examid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Examcatalog update(Examcatalog examcatalog) {
        ExamcatalogDAO DAO = DAO();
        return update(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog update(ExamcatalogDAO DAO, Examcatalog examcatalog) {
        return update(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog update(Examcatalog examcatalog, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return update(DAO, examcatalog, TABLENAME2);
    }

    public static Examcatalog update(final ExamcatalogDAO DAO, final Examcatalog examcatalog,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(examcatalog, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(examcatalog, TABLENAME2);
            if(n == -1) 
                return examcatalog;
            else if(n <= 0) 
                return null;
        }
        return examcatalog;
    }

    public static Examcatalog asyncUpdate(Examcatalog examcatalog) {
        ExamcatalogDAO DAO = DAO();
        return asyncUpdate(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog asyncUpdate(ExamcatalogDAO DAO, Examcatalog examcatalog) {
        return asyncUpdate(DAO, examcatalog, DAO.TABLENAME);
    }

    public static Examcatalog asyncUpdate(Examcatalog examcatalog, String TABLENAME2) {
        ExamcatalogDAO DAO = DAO();
        return asyncUpdate(DAO, examcatalog, TABLENAME2);
    }

    public static Examcatalog asyncUpdate(final ExamcatalogDAO DAO, final Examcatalog examcatalog,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(examcatalog, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(examcatalog, TABLENAME2);
        }
        return examcatalog;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ExamcatalogDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ExamcatalogDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ExamcatalogDAO DAO, final String TABLENAME2) {
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

        List<Examcatalog> examcatalogs = getAll();
        for (Examcatalog examcatalog : examcatalogs) {
            int n = DAO.insert2(examcatalog, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
