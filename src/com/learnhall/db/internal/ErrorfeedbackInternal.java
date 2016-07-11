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

import static com.learnhall.db.bean.Errorfeedback.Col;

//learnhall3_design - errorfeedback
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ErrorfeedbackInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ErrorfeedbackInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ErrorfeedbackInternal(){}

    public static ErrorfeedbackDAO DAO(){
        return ErrorfeedbackEntity.ErrorfeedbackDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Errorfeedback[MAX];
    }
    private static Errorfeedback[] FIXED = new Errorfeedback[MAX];
    public static final Map<Integer, Errorfeedback> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByOptid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByExamid = newSortedMap();

    private static final List<Errorfeedback> fixedCache = newList();

    public static void put(Errorfeedback errorfeedback, boolean force){
        if(errorfeedback == null || errorfeedback.id <= 0) return ;

        int id = errorfeedback.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(errorfeedback))
                	FIXED[id - 1] = errorfeedback;
                if (!fixedCache.contains(errorfeedback))
                	fixedCache.add(errorfeedback);
            }
        } else {
            vars.put(id, errorfeedback);
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = errorfeedback.oldVal(Col.lhubid);
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
            int lhubid = errorfeedback.getLhubid();
            Set m1 = varsByLhubid.get(lhubid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByLhubid.put(lhubid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index optid
          Object ov = errorfeedback.oldVal(Col.optid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByOptid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByOptid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int optid = errorfeedback.getOptid();
            Set m2 = varsByOptid.get(optid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByOptid.put(optid, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = errorfeedback.oldVal(Col.customerid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByCustomerid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByCustomerid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int customerid = errorfeedback.getCustomerid();
            Set m3 = varsByCustomerid.get(customerid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByCustomerid.put(customerid, m3);
            }
            m3.add(id);
          }
        }

        { // 单-非唯一索引 remove old index examid
          Object ov = errorfeedback.oldVal(Col.examid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m4 = varsByExamid.get(_val);
              if(m4 != null) {
                  m4.remove(id);
                  if(m4.isEmpty())
                      varsByExamid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int examid = errorfeedback.getExamid();
            Set m4 = varsByExamid.get(examid);
            if(m4 == null){
                m4 = newSortedSet();
                varsByExamid.put(examid, m4);
            }
            m4.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLhubid.clear();
        varsByOptid.clear();
        varsByCustomerid.clear();
        varsByExamid.clear();
        FIXED = new Errorfeedback[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ErrorfeedbackDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ErrorfeedbackDAO DAO, String TABLENAME2){
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

    public static void relocate(ErrorfeedbackDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ErrorfeedbackDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ErrorfeedbackDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ErrorfeedbackDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ErrorfeedbackDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ErrorfeedbackDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ErrorfeedbackDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ErrorfeedbackDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ErrorfeedbackDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ErrorfeedbackDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ErrorfeedbackDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ErrorfeedbackDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ErrorfeedbackDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Errorfeedback[maxId(DAO)];

        List<Errorfeedback> errorfeedbacks = DAO.selectAll();
        for (Errorfeedback errorfeedback : errorfeedbacks) {
            errorfeedback.reset();
            put(errorfeedback, true);
        }
    }

    public static Map toMap(Errorfeedback errorfeedback){
        return errorfeedback.toMap();
    }

    public static List<Map> toMap(List<Errorfeedback> errorfeedbacks){
        List<Map> ret = new Vector<Map>();
        for (Errorfeedback errorfeedback : errorfeedbacks){
            Map e = errorfeedback.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Errorfeedback> sortZh(List<Errorfeedback> errorfeedbacks, final String key) {
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>() {
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sort(List<Errorfeedback> errorfeedbacks, final String key) {
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>() {
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                return o1.compareTo(o2, key);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sort(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortReverse(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortLhubid(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortLhubidRo(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortOptid(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getOptid();
                Object v2 = o2.getOptid();
                return compareTo(v1, v2);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortOptidRo(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getOptid();
                Object v2 = o2.getOptid();
                return 0 - compareTo(v1, v2);
            };
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortCustomerid(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortCustomeridRo(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortExamid(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getExamid();
                Object v2 = o2.getExamid();
                return compareTo(v1, v2);
            }
        });
        return errorfeedbacks;
    }

    public static List<Errorfeedback> sortExamidRo(List<Errorfeedback> errorfeedbacks){
        Collections.sort(errorfeedbacks, new Comparator<Errorfeedback>(){
            public int compare(Errorfeedback o1, Errorfeedback o2) {
                Object v1 = o1.getExamid();
                Object v2 = o2.getExamid();
                return 0 - compareTo(v1, v2);
            };
        });
        return errorfeedbacks;
    }

    public static Errorfeedback insert(Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = DAO();
        return insert(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback insert(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback) {
        return insert(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback insert(Errorfeedback errorfeedback, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return insert(DAO, errorfeedback, TABLENAME2);
    }

    public static Errorfeedback insert(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(errorfeedback, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        errorfeedback.id = n;
        if(cacheModel != NO_CACHE) put(errorfeedback, true);

        return errorfeedback;
    }

    public static Errorfeedback asyncInsert(Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = DAO();
        return asyncInsert(DAO, errorfeedback, DAO.TABLENAME);
    }
    public static Errorfeedback asyncInsert(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback) {
        return asyncInsert(DAO, errorfeedback, DAO.TABLENAME);
    }
    public static Errorfeedback asyncInsert(Errorfeedback errorfeedback, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return asyncInsert(DAO, errorfeedback, TABLENAME2);
    }
    public static Errorfeedback asyncInsert(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(errorfeedback, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        errorfeedback.id = n;
        if(cacheModel != NO_CACHE) put(errorfeedback, true);
        return errorfeedback;
    }
    public static Errorfeedback insert2(Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = DAO();
        return insert2(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback insert2(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback) {
        return insert2(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback insert2(Errorfeedback errorfeedback, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return insert2(DAO, errorfeedback, TABLENAME2);
    }

    public static Errorfeedback insert2(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(errorfeedback, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        errorfeedback.id = n;
        if(cacheModel != NO_CACHE) put(errorfeedback, true);

        return errorfeedback;
    }

    public static Errorfeedback asyncInsert2(Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = DAO();
        return asyncInsert2(DAO, errorfeedback, DAO.TABLENAME);
    }
    public static Errorfeedback asyncInsert2(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback) {
        return asyncInsert2(DAO, errorfeedback, DAO.TABLENAME);
    }
    public static Errorfeedback asyncInsert2(Errorfeedback errorfeedback, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return asyncInsert2(DAO, errorfeedback, TABLENAME2);
    }
    public static Errorfeedback asyncInsert2(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        errorfeedback.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(errorfeedback, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(errorfeedback, true);
        return errorfeedback;
    }
    public static int[] insert(List<Errorfeedback> errorfeedbacks) {
        ErrorfeedbackDAO DAO = DAO();
        return insert(DAO, errorfeedbacks, DAO.TABLENAME);
    }

    public static int[] insert(ErrorfeedbackDAO DAO, List<Errorfeedback> errorfeedbacks) {
        return insert(DAO, errorfeedbacks, DAO.TABLENAME);
    }

    public static int[] insert(List<Errorfeedback> errorfeedbacks, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return insert(DAO, errorfeedbacks, TABLENAME2);
    }

    public static int[] insert(ErrorfeedbackDAO DAO, List<Errorfeedback> errorfeedbacks, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(errorfeedbacks, TABLENAME2);
            int n = 0;
            for(Errorfeedback errorfeedback : errorfeedbacks){
                errorfeedback.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[errorfeedbacks.size()];
        int n = 0;
        for(Errorfeedback errorfeedback : errorfeedbacks){
            errorfeedback = insert(DAO, errorfeedback, TABLENAME2);
            ret[n++] = (errorfeedback == null) ? 0 : (int)errorfeedback.id;
        }
        return ret;
    }

    public static int delete(Errorfeedback errorfeedback) {
        int id = errorfeedback.id;
        ErrorfeedbackDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ErrorfeedbackDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ErrorfeedbackDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ErrorfeedbackDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Errorfeedback errorfeedback) {
        int id = errorfeedback.id;
        ErrorfeedbackDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ErrorfeedbackDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ErrorfeedbackDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ErrorfeedbackDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ErrorfeedbackDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ErrorfeedbackDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ErrorfeedbackDAO DAO, int[] ids,String TABLENAME2) {
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
        ErrorfeedbackDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ErrorfeedbackDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Errorfeedback> beans) {
        ErrorfeedbackDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Errorfeedback> beans, ErrorfeedbackDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Errorfeedback> beans, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Errorfeedback> beans, final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Errorfeedback errorfeedback : beans){
                int id = errorfeedback.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Errorfeedback> getAll() {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getAll(ErrorfeedbackDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getAll(String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Errorfeedback> getAll(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Errorfeedback> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Errorfeedback> getNoSortAll() {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getNoSortAll(ErrorfeedbackDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getNoSortAll(String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Errorfeedback> getNoSortAll(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Errorfeedback> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Errorfeedback> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Errorfeedback errorfeedback = FIXED[i];
                if (errorfeedback != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Errorfeedback> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Errorfeedback> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ErrorfeedbackDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
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
        ErrorfeedbackDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ErrorfeedbackDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Errorfeedback> getIn(List<Integer> keys) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getIn(List<Integer> keys, ErrorfeedbackDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getIn(List<Integer> keys, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Errorfeedback> getIn(final List<Integer> keys, final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Errorfeedback> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Errorfeedback> getNoSortIn(List<Integer> keys) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getNoSortIn(List<Integer> keys, ErrorfeedbackDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Errorfeedback> getNoSortIn(final List<Integer> keys, final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Errorfeedback> result = newList();
            for (int key : keys) {
                Errorfeedback errorfeedback = getByKeyFromMemory(key);
                if( errorfeedback == null ) continue;
                result.add(errorfeedback);
            }
            return result;
        }
    }

    public static List<Errorfeedback> getLast(int num) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getLast(ErrorfeedbackDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getLast(int num, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Errorfeedback> getLast(final ErrorfeedbackDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Errorfeedback> result = newList();
            List<Errorfeedback> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Errorfeedback last() {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Errorfeedback last(ErrorfeedbackDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Errorfeedback last(String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Errorfeedback last(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        Errorfeedback result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ErrorfeedbackDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ErrorfeedbackDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Errorfeedback> getGtKey(int id) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getGtKey(ErrorfeedbackDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getGtKey(int id, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Errorfeedback> getGtKey(final ErrorfeedbackDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Errorfeedback> result = newList();
            List<Errorfeedback> errorfeedbacks = getAll();
            for (Errorfeedback errorfeedback : errorfeedbacks) {
                if(errorfeedback.id <= id) continue;
                result.add(errorfeedback);
            }
            return result;
        }
    }

    public static Errorfeedback getByKey(int id) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Errorfeedback> asyncGetByKey(final int id) {
        Future<Errorfeedback> f = Async.exec(new Callable<Errorfeedback>() {
            public Errorfeedback call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Errorfeedback getByKey(ErrorfeedbackDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Errorfeedback getByKey(int id, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Errorfeedback getByKey(final ErrorfeedbackDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Errorfeedback getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Errorfeedback deleteFromMemory(final int id) {
        Errorfeedback errorfeedback;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            errorfeedback = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(errorfeedback);
        } else {
            errorfeedback = vars.remove(id);
        }
        if(errorfeedback == null) return null;

        int lhubid = errorfeedback.getLhubid();
        Set m1 = varsByLhubid.get(lhubid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        int optid = errorfeedback.getOptid();
        Set m2 = varsByOptid.get(optid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByOptid.remove(optid);
        }

        int customerid = errorfeedback.getCustomerid();
        Set m3 = varsByCustomerid.get(customerid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int examid = errorfeedback.getExamid();
        Set m4 = varsByExamid.get(examid);
        if(m4 != null) {
            m4.remove(id);
            if(m4.isEmpty())
                varsByExamid.remove(examid);
        }

        return errorfeedback;
    }

    public static List<Errorfeedback> getByPage(int page, int size) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByPage(ErrorfeedbackDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByPage(int page, int size, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Errorfeedback> getByPage(final ErrorfeedbackDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Errorfeedback> result = newList();
            List<Errorfeedback> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ErrorfeedbackDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ErrorfeedbackDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByLhubid(Integer lhubid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(ErrorfeedbackDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final ErrorfeedbackDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Errorfeedback> errorfeedbacks = getByLhubid(DAO, lhubid, TABLENAME2);
        return errorfeedbacks.size();
    }

    public static List<Errorfeedback> getByLhubid(Integer lhubid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByLhubid(ErrorfeedbackDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByLhubid(Integer lhubid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Errorfeedback> getByLhubid(final ErrorfeedbackDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Errorfeedback> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Errorfeedback e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByOptid(Integer optid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByOptid(DAO, optid, DAO.TABLENAME);
    }

    public static int countByOptid(ErrorfeedbackDAO DAO, Integer optid) {
        return countByOptid(DAO, optid, DAO.TABLENAME);
    }

    public static int countByOptid(Integer optid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByOptid(DAO, optid, TABLENAME2);
    }

    public static int countByOptid(final ErrorfeedbackDAO DAO, final Integer optid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByOptid(optid, TABLENAME2);
        }
        List<Errorfeedback> errorfeedbacks = getByOptid(DAO, optid, TABLENAME2);
        return errorfeedbacks.size();
    }

    public static List<Errorfeedback> getByOptid(Integer optid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByOptid(DAO, optid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByOptid(ErrorfeedbackDAO DAO, Integer optid) {
        return getByOptid(DAO, optid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByOptid(Integer optid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByOptid(DAO, optid, TABLENAME2);
    }

    public static List<Errorfeedback> getByOptid(final ErrorfeedbackDAO DAO, final Integer optid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByOptid(optid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Errorfeedback> result = newList();
            Set<Integer> m1 = varsByOptid.get(optid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Errorfeedback e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _optid = e.getOptid();
                if(_optid != optid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByCustomerid(Integer customerid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(ErrorfeedbackDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final ErrorfeedbackDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Errorfeedback> errorfeedbacks = getByCustomerid(DAO, customerid, TABLENAME2);
        return errorfeedbacks.size();
    }

    public static List<Errorfeedback> getByCustomerid(Integer customerid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByCustomerid(ErrorfeedbackDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByCustomerid(Integer customerid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Errorfeedback> getByCustomerid(final ErrorfeedbackDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Errorfeedback> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Errorfeedback e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByExamid(Integer examid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static int countByExamid(ErrorfeedbackDAO DAO, Integer examid) {
        return countByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static int countByExamid(Integer examid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamid(DAO, examid, TABLENAME2);
    }

    public static int countByExamid(final ErrorfeedbackDAO DAO, final Integer examid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamid(examid, TABLENAME2);
        }
        List<Errorfeedback> errorfeedbacks = getByExamid(DAO, examid, TABLENAME2);
        return errorfeedbacks.size();
    }

    public static List<Errorfeedback> getByExamid(Integer examid) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByExamid(ErrorfeedbackDAO DAO, Integer examid) {
        return getByExamid(DAO, examid, DAO.TABLENAME);
    }

    public static List<Errorfeedback> getByExamid(Integer examid, String TABLENAME2) {
        ErrorfeedbackDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamid(DAO, examid, TABLENAME2);
    }

    public static List<Errorfeedback> getByExamid(final ErrorfeedbackDAO DAO, final Integer examid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamid(examid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Errorfeedback> result = newList();
            Set<Integer> m1 = varsByExamid.get(examid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Errorfeedback e = getByKey(DAO, key, TABLENAME2);
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

    public static Errorfeedback update(Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = DAO();
        return update(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback update(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback) {
        return update(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback update(Errorfeedback errorfeedback, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return update(DAO, errorfeedback, TABLENAME2);
    }

    public static Errorfeedback update(final ErrorfeedbackDAO DAO, final Errorfeedback errorfeedback,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(errorfeedback, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(errorfeedback, TABLENAME2);
            if(n == -1) 
                return errorfeedback;
            else if(n <= 0) 
                return null;
        }
        return errorfeedback;
    }

    public static Errorfeedback asyncUpdate(Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = DAO();
        return asyncUpdate(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback asyncUpdate(ErrorfeedbackDAO DAO, Errorfeedback errorfeedback) {
        return asyncUpdate(DAO, errorfeedback, DAO.TABLENAME);
    }

    public static Errorfeedback asyncUpdate(Errorfeedback errorfeedback, String TABLENAME2) {
        ErrorfeedbackDAO DAO = DAO();
        return asyncUpdate(DAO, errorfeedback, TABLENAME2);
    }

    public static Errorfeedback asyncUpdate(final ErrorfeedbackDAO DAO, final Errorfeedback errorfeedback,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(errorfeedback, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(errorfeedback, TABLENAME2);
        }
        return errorfeedback;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ErrorfeedbackDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ErrorfeedbackDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ErrorfeedbackDAO DAO, final String TABLENAME2) {
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

        List<Errorfeedback> errorfeedbacks = getAll();
        for (Errorfeedback errorfeedback : errorfeedbacks) {
            int n = DAO.insert2(errorfeedback, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
