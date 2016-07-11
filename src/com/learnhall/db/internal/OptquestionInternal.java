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

import static com.learnhall.db.bean.Optquestion.Col;

//learnhall3_design - optquestion
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class OptquestionInternal extends InternalSupport{
    static Log log = LogFactory.getLog(OptquestionInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public OptquestionInternal(){}

    public static OptquestionDAO DAO(){
        return OptquestionEntity.OptquestionDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Optquestion[MAX];
    }
    private static Optquestion[] FIXED = new Optquestion[MAX];
    public static final Map<Integer, Optquestion> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByExamidExamcatalogid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByExamcatalogid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByType = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByExamid = newSortedMap();

    private static final List<Optquestion> fixedCache = newList();

    public static void put(Optquestion optquestion, boolean force){
        if(optquestion == null || optquestion.optid <= 0) return ;

        int optid = optquestion.optid;
        if (cacheModel == STATIC_CACHE) {
            if (optid > 0 && optid <= FIXED.length) {
                if (FIXED[optid - 1] == null || !FIXED[optid - 1].equals(optquestion))
                	FIXED[optid - 1] = optquestion;
                if (!fixedCache.contains(optquestion))
                	fixedCache.add(optquestion);
            }
        } else {
            vars.put(optid, optquestion);
        }

        { // examid_catalogid
          boolean bChanged = optquestion.inChanged(Col.examid, Col.examcatalogid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vexamid = optquestion.oldOrNew(Col.examid);
              Object vexamcatalogid = optquestion.oldOrNew(Col.examcatalogid);
              String okey = com.bowlong.lang.PStr.b().a(vexamid, "-", vexamcatalogid).e();
              Set m1 = varsByExamidExamcatalogid.get(okey);
              if(m1 != null) {
                  m1.remove(optid);
                  if(m1.isEmpty())
                       varsByExamidExamcatalogid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vexamid = optquestion.getExamid();
              int vexamcatalogid = optquestion.getExamcatalogid();
              String vkey = com.bowlong.lang.PStr.b().a(vexamid, "-", vexamcatalogid).e();
              Set m1 = varsByExamidExamcatalogid.get(vkey);
              if(m1 == null){
                  m1 = newSortedSet();
                  varsByExamidExamcatalogid.put(vkey, m1);
              }
              m1.add(optid);
          }
        }

        { // 单-非唯一索引 remove old index examcatalogid
          Object ov = optquestion.oldVal(Col.examcatalogid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByExamcatalogid.get(_val);
              if(m2 != null) {
                  m2.remove(optid);
                  if(m2.isEmpty())
                      varsByExamcatalogid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int examcatalogid = optquestion.getExamcatalogid();
            Set m2 = varsByExamcatalogid.get(examcatalogid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByExamcatalogid.put(examcatalogid, m2);
            }
            m2.add(optid);
          }
        }

        { // 单-非唯一索引 remove old index type
          Object ov = optquestion.oldVal(Col.type);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByType.get(_val);
              if(m3 != null) {
                  m3.remove(optid);
                  if(m3.isEmpty())
                      varsByType.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int type = optquestion.getType();
            Set m3 = varsByType.get(type);
            if(m3 == null){
                m3 = newSortedSet();
                varsByType.put(type, m3);
            }
            m3.add(optid);
          }
        }

        { // 单-非唯一索引 remove old index examid
          Object ov = optquestion.oldVal(Col.examid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m4 = varsByExamid.get(_val);
              if(m4 != null) {
                  m4.remove(optid);
                  if(m4.isEmpty())
                      varsByExamid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int examid = optquestion.getExamid();
            Set m4 = varsByExamid.get(examid);
            if(m4 == null){
                m4 = newSortedSet();
                varsByExamid.put(examid, m4);
            }
            m4.add(optid);
          }
        }

        // LASTID = optid > LASTID ? optid : LASTID;
        if (optid > LASTID.get()) LASTID.set(optid);
    }

    public static void clear(){
        varsByExamidExamcatalogid.clear();
        varsByExamcatalogid.clear();
        varsByType.clear();
        varsByExamid.clear();
        FIXED = new Optquestion[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(OptquestionDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(OptquestionDAO DAO, String TABLENAME2){
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

    public static void relocate(OptquestionDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        OptquestionDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(OptquestionDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        OptquestionDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(OptquestionDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        OptquestionDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(OptquestionDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(OptquestionDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(OptquestionDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(OptquestionDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(OptquestionDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        OptquestionDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(OptquestionDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Optquestion[maxId(DAO)];

        List<Optquestion> optquestions = DAO.selectAll();
        for (Optquestion optquestion : optquestions) {
            optquestion.reset();
            put(optquestion, true);
        }
    }

    public static Map toMap(Optquestion optquestion){
        return optquestion.toMap();
    }

    public static List<Map> toMap(List<Optquestion> optquestions){
        List<Map> ret = new Vector<Map>();
        for (Optquestion optquestion : optquestions){
            Map e = optquestion.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Optquestion> sortZh(List<Optquestion> optquestions, final String key) {
        Collections.sort(optquestions, new Comparator<Optquestion>() {
            public int compare(Optquestion o1, Optquestion o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sort(List<Optquestion> optquestions, final String key) {
        Collections.sort(optquestions, new Comparator<Optquestion>() {
            public int compare(Optquestion o1, Optquestion o2) {
                return o1.compareTo(o2, key);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sort(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.optid;
                Object v2 = o2.optid;
                return compareTo(v1, v2);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sortReverse(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.optid;
                Object v2 = o2.optid;
                return 0 - compareTo(v1, v2);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sortExamcatalogid(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.getExamcatalogid();
                Object v2 = o2.getExamcatalogid();
                return compareTo(v1, v2);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sortExamcatalogidRo(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.getExamcatalogid();
                Object v2 = o2.getExamcatalogid();
                return 0 - compareTo(v1, v2);
            };
        });
        return optquestions;
    }

    public static List<Optquestion> sortType(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.getType();
                Object v2 = o2.getType();
                return compareTo(v1, v2);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sortTypeRo(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.getType();
                Object v2 = o2.getType();
                return 0 - compareTo(v1, v2);
            };
        });
        return optquestions;
    }

    public static List<Optquestion> sortExamid(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.getExamid();
                Object v2 = o2.getExamid();
                return compareTo(v1, v2);
            }
        });
        return optquestions;
    }

    public static List<Optquestion> sortExamidRo(List<Optquestion> optquestions){
        Collections.sort(optquestions, new Comparator<Optquestion>(){
            public int compare(Optquestion o1, Optquestion o2) {
                Object v1 = o1.getExamid();
                Object v2 = o2.getExamid();
                return 0 - compareTo(v1, v2);
            };
        });
        return optquestions;
    }

    public static Optquestion insert(Optquestion optquestion) {
        OptquestionDAO DAO = DAO();
        return insert(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion insert(OptquestionDAO DAO, Optquestion optquestion) {
        return insert(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion insert(Optquestion optquestion, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return insert(DAO, optquestion, TABLENAME2);
    }

    public static Optquestion insert(OptquestionDAO DAO, Optquestion optquestion, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(optquestion, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        optquestion.optid = n;
        if(cacheModel != NO_CACHE) put(optquestion, true);

        return optquestion;
    }

    public static Optquestion asyncInsert(Optquestion optquestion) {
        OptquestionDAO DAO = DAO();
        return asyncInsert(DAO, optquestion, DAO.TABLENAME);
    }
    public static Optquestion asyncInsert(OptquestionDAO DAO, Optquestion optquestion) {
        return asyncInsert(DAO, optquestion, DAO.TABLENAME);
    }
    public static Optquestion asyncInsert(Optquestion optquestion, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return asyncInsert(DAO, optquestion, TABLENAME2);
    }
    public static Optquestion asyncInsert(OptquestionDAO DAO, Optquestion optquestion, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(optquestion, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        optquestion.optid = n;
        if(cacheModel != NO_CACHE) put(optquestion, true);
        return optquestion;
    }
    public static Optquestion insert2(Optquestion optquestion) {
        OptquestionDAO DAO = DAO();
        return insert2(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion insert2(OptquestionDAO DAO, Optquestion optquestion) {
        return insert2(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion insert2(Optquestion optquestion, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return insert2(DAO, optquestion, TABLENAME2);
    }

    public static Optquestion insert2(OptquestionDAO DAO, Optquestion optquestion, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(optquestion, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        optquestion.optid = n;
        if(cacheModel != NO_CACHE) put(optquestion, true);

        return optquestion;
    }

    public static Optquestion asyncInsert2(Optquestion optquestion) {
        OptquestionDAO DAO = DAO();
        return asyncInsert2(DAO, optquestion, DAO.TABLENAME);
    }
    public static Optquestion asyncInsert2(OptquestionDAO DAO, Optquestion optquestion) {
        return asyncInsert2(DAO, optquestion, DAO.TABLENAME);
    }
    public static Optquestion asyncInsert2(Optquestion optquestion, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return asyncInsert2(DAO, optquestion, TABLENAME2);
    }
    public static Optquestion asyncInsert2(OptquestionDAO DAO, Optquestion optquestion, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        optquestion.optid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(optquestion, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(optquestion, true);
        return optquestion;
    }
    public static int[] insert(List<Optquestion> optquestions) {
        OptquestionDAO DAO = DAO();
        return insert(DAO, optquestions, DAO.TABLENAME);
    }

    public static int[] insert(OptquestionDAO DAO, List<Optquestion> optquestions) {
        return insert(DAO, optquestions, DAO.TABLENAME);
    }

    public static int[] insert(List<Optquestion> optquestions, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return insert(DAO, optquestions, TABLENAME2);
    }

    public static int[] insert(OptquestionDAO DAO, List<Optquestion> optquestions, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(optquestions, TABLENAME2);
            int n = 0;
            for(Optquestion optquestion : optquestions){
                optquestion.optid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[optquestions.size()];
        int n = 0;
        for(Optquestion optquestion : optquestions){
            optquestion = insert(DAO, optquestion, TABLENAME2);
            ret[n++] = (optquestion == null) ? 0 : (int)optquestion.optid;
        }
        return ret;
    }

    public static int delete(Optquestion optquestion) {
        int optid = optquestion.optid;
        OptquestionDAO DAO = DAO();
        return delete(DAO, optid, DAO.TABLENAME);
    }

    public static int delete(int optid) {
        OptquestionDAO DAO = DAO();
        return delete(DAO, optid, DAO.TABLENAME);
    }

    public static int delete(OptquestionDAO DAO, int optid) {
        return delete(DAO, optid, DAO.TABLENAME);
    }

    public static int delete(int optid, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return delete(DAO, optid, TABLENAME2);
    }

    public static int delete(OptquestionDAO DAO, int optid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(optid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(optid);
        }
        return n;
    }

    public static void asyncDelete(Optquestion optquestion) {
        int optid = optquestion.optid;
        OptquestionDAO DAO = DAO();
        asyncDelete(DAO, optid, DAO.TABLENAME);
    }

    public static void asyncDelete(int optid) {
        OptquestionDAO DAO = DAO();
        asyncDelete(DAO, optid, DAO.TABLENAME);
    }

    public static void asyncDelete(OptquestionDAO DAO, int optid) {
        asyncDelete(DAO, optid, DAO.TABLENAME);
    }

    public static void asyncDelete(int optid, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        asyncDelete(DAO, optid, TABLENAME2);
    }

    public static void asyncDelete(OptquestionDAO DAO, int optid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(optid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(optid);
        }
    }

    public static int[] delete(int[] optids) {
        OptquestionDAO DAO = DAO();
        return delete(DAO, optids, DAO.TABLENAME);
    }

    public static int[] delete(OptquestionDAO DAO, int[] optids) {
        return delete(DAO, optids, DAO.TABLENAME);
    }

    public static int[] delete(int[] optids,String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return delete(DAO, optids, TABLENAME2);
    }

    public static int[] delete(OptquestionDAO DAO, int[] optids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(optids, TABLENAME2);
        }
        int[] ret = new int[optids.length];
        int n = 0;
        for(int optid : optids){
            ret[n++] = delete(DAO, optid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        OptquestionDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, OptquestionDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final OptquestionDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer optid : keys){
                deleteFromMemory(optid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Optquestion> beans) {
        OptquestionDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Optquestion> beans, OptquestionDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Optquestion> beans, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Optquestion> beans, final OptquestionDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Optquestion optquestion : beans){
                int optid = optquestion.optid;
                deleteFromMemory(optid);
            }
        }
        return result;
    }

    public static List<Optquestion> getAll() {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getAll(OptquestionDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getAll(String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Optquestion> getAll(final OptquestionDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Optquestion> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Optquestion> getNoSortAll() {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getNoSortAll(OptquestionDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getNoSortAll(String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Optquestion> getNoSortAll(final OptquestionDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Optquestion> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Optquestion> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Optquestion optquestion = FIXED[i];
                if (optquestion != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Optquestion> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Optquestion> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(OptquestionDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final OptquestionDAO DAO, final String TABLENAME2) {
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
        OptquestionDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(OptquestionDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final OptquestionDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Optquestion> getIn(List<Integer> keys) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getIn(List<Integer> keys, OptquestionDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getIn(List<Integer> keys, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Optquestion> getIn(final List<Integer> keys, final OptquestionDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Optquestion> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Optquestion> getNoSortIn(List<Integer> keys) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getNoSortIn(List<Integer> keys, OptquestionDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Optquestion> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Optquestion> getNoSortIn(final List<Integer> keys, final OptquestionDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Optquestion> result = newList();
            for (int key : keys) {
                Optquestion optquestion = getByKeyFromMemory(key);
                if( optquestion == null ) continue;
                result.add(optquestion);
            }
            return result;
        }
    }

    public static List<Optquestion> getLast(int num) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Optquestion> getLast(OptquestionDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Optquestion> getLast(int num, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Optquestion> getLast(final OptquestionDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Optquestion> result = newList();
            List<Optquestion> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Optquestion last() {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Optquestion last(OptquestionDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Optquestion last(String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Optquestion last(final OptquestionDAO DAO, final String TABLENAME2) {
        Optquestion result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        OptquestionDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(OptquestionDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final OptquestionDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Optquestion> getGtKey(int optid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, optid, DAO.TABLENAME);
    }

    public static List<Optquestion> getGtKey(OptquestionDAO DAO, int optid) {
        return getGtKey(DAO, optid, DAO.TABLENAME);
    }

    public static List<Optquestion> getGtKey(int optid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, optid, TABLENAME2);
    }

    public static List<Optquestion> getGtKey(final OptquestionDAO DAO, final int optid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(optid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Optquestion> result = newList();
            List<Optquestion> optquestions = getAll();
            for (Optquestion optquestion : optquestions) {
                if(optquestion.optid <= optid) continue;
                result.add(optquestion);
            }
            return result;
        }
    }

    public static Optquestion getByKey(int optid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, optid, DAO.TABLENAME);
    }

    public static Future<Optquestion> asyncGetByKey(final int optid) {
        Future<Optquestion> f = Async.exec(new Callable<Optquestion>() {
            public Optquestion call() throws Exception {
                return getByKey(optid);
            }
        });
        return f;
    }

    public static Optquestion getByKey(OptquestionDAO DAO, int optid) {
        return getByKey(DAO, optid, DAO.TABLENAME);
    }

    public static Optquestion getByKey(int optid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, optid, TABLENAME2);
    }

    public static Optquestion getByKey(final OptquestionDAO DAO, final int optid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(optid, TABLENAME2);
        }
        return getByKeyFromMemory(optid);
    }

    public static Optquestion getByKeyFromMemory(final int optid) {
        if (cacheModel == STATIC_CACHE) {
            if (optid < 1 || FIXED == null || FIXED.length < optid) return null;
            return FIXED[optid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(optid);
        }
        return null;
    }

    public static Optquestion deleteFromMemory(final int optid) {
        Optquestion optquestion;
        if (cacheModel == STATIC_CACHE) {
            if (optid < 1 || FIXED == null || FIXED.length < optid || FIXED[optid-1]==null) return null;
            optquestion = FIXED[optid - 1];
            FIXED[optid - 1] = null;
            fixedCache.remove(optquestion);
        } else {
            optquestion = vars.remove(optid);
        }
        if(optquestion == null) return null;

        { // examid_catalogid
            int vexamid = optquestion.getExamid();
            int vexamcatalogid = optquestion.getExamcatalogid();
            String vkey = com.bowlong.lang.PStr.b().a(vexamid, "-", vexamcatalogid).e();
            Set m1 = varsByExamidExamcatalogid.get(vkey);
            if(m1 != null) { 
                m1.remove(optid);
                if(m1.isEmpty())
                    varsByExamidExamcatalogid.remove(vkey);
            }
        }

        int examcatalogid = optquestion.getExamcatalogid();
        Set m2 = varsByExamcatalogid.get(examcatalogid);
        if(m2 != null) {
            m2.remove(optid);
            if(m2.isEmpty())
                varsByExamcatalogid.remove(examcatalogid);
        }

        int type = optquestion.getType();
        Set m3 = varsByType.get(type);
        if(m3 != null) {
            m3.remove(optid);
            if(m3.isEmpty())
                varsByType.remove(type);
        }

        int examid = optquestion.getExamid();
        Set m4 = varsByExamid.get(examid);
        if(m4 != null) {
            m4.remove(optid);
            if(m4.isEmpty())
                varsByExamid.remove(examid);
        }

        return optquestion;
    }

    public static List<Optquestion> getByPage(int page, int size) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Optquestion> getByPage(OptquestionDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Optquestion> getByPage(int page, int size, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Optquestion> getByPage(final OptquestionDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Optquestion> result = newList();
            List<Optquestion> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(OptquestionDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final OptquestionDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByExamidExamcatalogid(Integer examid, Integer examcatalogid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamidExamcatalogid(DAO, examid, examcatalogid, DAO.TABLENAME);
    }

    public static int countByExamidExamcatalogid(OptquestionDAO DAO, Integer examid, Integer examcatalogid) {
        return countByExamidExamcatalogid(DAO, examid, examcatalogid, DAO.TABLENAME);
    }

    public static int countByExamidExamcatalogid(Integer examid, Integer examcatalogid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamidExamcatalogid(DAO, examid, examcatalogid, TABLENAME2);
    }

    public static int countByExamidExamcatalogid(final OptquestionDAO DAO, Integer examid, Integer examcatalogid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamidExamcatalogid(examid, examcatalogid, TABLENAME2);
        }
        List<Optquestion> optquestions = getByExamidExamcatalogid(examid, examcatalogid, TABLENAME2);
        return optquestions.size();
    }

    public static List<Optquestion> getByExamidExamcatalogid(Integer examid, Integer examcatalogid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamidExamcatalogid(DAO, examid, examcatalogid, DAO.TABLENAME);
    }

    public static List<Optquestion> getByExamidExamcatalogid(OptquestionDAO DAO, Integer examid, Integer examcatalogid) {
        return getByExamidExamcatalogid(DAO, examid, examcatalogid, DAO.TABLENAME);
    }

    public static List<Optquestion> getByExamidExamcatalogid(Integer examid, Integer examcatalogid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamidExamcatalogid(DAO, examid, examcatalogid, TABLENAME2);
    }

    public static List<Optquestion> getByExamidExamcatalogid(final OptquestionDAO DAO, Integer examid, Integer examcatalogid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamidExamcatalogid(examid, examcatalogid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Optquestion> result = newList();
            String vkey = examid+"-"+examcatalogid;
            Set<Integer> m1 = varsByExamidExamcatalogid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Optquestion e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _examid = e.getExamid();
                int _examcatalogid = e.getExamcatalogid();
                String _key = com.bowlong.lang.PStr.b().a(_examid, "-", _examcatalogid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByExamcatalogid(Integer examcatalogid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamcatalogid(DAO, examcatalogid, DAO.TABLENAME);
    }

    public static int countByExamcatalogid(OptquestionDAO DAO, Integer examcatalogid) {
        return countByExamcatalogid(DAO, examcatalogid, DAO.TABLENAME);
    }

    public static int countByExamcatalogid(Integer examcatalogid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamcatalogid(DAO, examcatalogid, TABLENAME2);
    }

    public static int countByExamcatalogid(final OptquestionDAO DAO, final Integer examcatalogid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamcatalogid(examcatalogid, TABLENAME2);
        }
        List<Optquestion> optquestions = getByExamcatalogid(DAO, examcatalogid, TABLENAME2);
        return optquestions.size();
    }

    public static List<Optquestion> getByExamcatalogid(Integer examcatalogid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamcatalogid(DAO, examcatalogid, DAO.TABLENAME);
    }

    public static List<Optquestion> getByExamcatalogid(OptquestionDAO DAO, Integer examcatalogid) {
        return getByExamcatalogid(DAO, examcatalogid, DAO.TABLENAME);
    }

    public static List<Optquestion> getByExamcatalogid(Integer examcatalogid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamcatalogid(DAO, examcatalogid, TABLENAME2);
    }

    public static List<Optquestion> getByExamcatalogid(final OptquestionDAO DAO, final Integer examcatalogid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamcatalogid(examcatalogid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Optquestion> result = newList();
            Set<Integer> m1 = varsByExamcatalogid.get(examcatalogid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Optquestion e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _examcatalogid = e.getExamcatalogid();
                if(_examcatalogid != examcatalogid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByType(Integer type) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByType(DAO, type, DAO.TABLENAME);
    }

    public static int countByType(OptquestionDAO DAO, Integer type) {
        return countByType(DAO, type, DAO.TABLENAME);
    }

    public static int countByType(Integer type, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByType(DAO, type, TABLENAME2);
    }

    public static int countByType(final OptquestionDAO DAO, final Integer type,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByType(type, TABLENAME2);
        }
        List<Optquestion> optquestions = getByType(DAO, type, TABLENAME2);
        return optquestions.size();
    }

    public static List<Optquestion> getByType(Integer type) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByType(DAO, type, DAO.TABLENAME);
    }

    public static List<Optquestion> getByType(OptquestionDAO DAO, Integer type) {
        return getByType(DAO, type, DAO.TABLENAME);
    }

    public static List<Optquestion> getByType(Integer type, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByType(DAO, type, TABLENAME2);
    }

    public static List<Optquestion> getByType(final OptquestionDAO DAO, final Integer type,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByType(type, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Optquestion> result = newList();
            Set<Integer> m1 = varsByType.get(type);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Optquestion e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                if(_type != type){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByExamid(Integer examid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static int countByExamid(OptquestionDAO DAO, Integer examid) {
        return countByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static int countByExamid(Integer examid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamid(DAO, examid, TABLENAME2);
    }

    public static int countByExamid(final OptquestionDAO DAO, final Integer examid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamid(examid, TABLENAME2);
        }
        List<Optquestion> optquestions = getByExamid(DAO, examid, TABLENAME2);
        return optquestions.size();
    }

    public static List<Optquestion> getByExamid(Integer examid) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static List<Optquestion> getByExamid(OptquestionDAO DAO, Integer examid) {
        return getByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static List<Optquestion> getByExamid(Integer examid, String TABLENAME2) {
        OptquestionDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamid(DAO, examid, TABLENAME2);
    }

    public static List<Optquestion> getByExamid(final OptquestionDAO DAO, final Integer examid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamid(examid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Optquestion> result = newList();
            Set<Integer> m1 = varsByExamid.get(examid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Optquestion e = getByKey(DAO, key, TABLENAME2);
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

    public static Optquestion update(Optquestion optquestion) {
        OptquestionDAO DAO = DAO();
        return update(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion update(OptquestionDAO DAO, Optquestion optquestion) {
        return update(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion update(Optquestion optquestion, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return update(DAO, optquestion, TABLENAME2);
    }

    public static Optquestion update(final OptquestionDAO DAO, final Optquestion optquestion,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(optquestion, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(optquestion, TABLENAME2);
            if(n == -1) 
                return optquestion;
            else if(n <= 0) 
                return null;
        }
        return optquestion;
    }

    public static Optquestion asyncUpdate(Optquestion optquestion) {
        OptquestionDAO DAO = DAO();
        return asyncUpdate(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion asyncUpdate(OptquestionDAO DAO, Optquestion optquestion) {
        return asyncUpdate(DAO, optquestion, DAO.TABLENAME);
    }

    public static Optquestion asyncUpdate(Optquestion optquestion, String TABLENAME2) {
        OptquestionDAO DAO = DAO();
        return asyncUpdate(DAO, optquestion, TABLENAME2);
    }

    public static Optquestion asyncUpdate(final OptquestionDAO DAO, final Optquestion optquestion,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(optquestion, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(optquestion, TABLENAME2);
        }
        return optquestion;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        OptquestionDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(OptquestionDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final OptquestionDAO DAO, final String TABLENAME2) {
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

        List<Optquestion> optquestions = getAll();
        for (Optquestion optquestion : optquestions) {
            int n = DAO.insert2(optquestion, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
