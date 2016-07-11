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

import static com.learnhall.db.bean.Exam.Col;

//learnhall3_design - exam
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ExamInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ExamInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ExamInternal(){}

    public static ExamDAO DAO(){
        return ExamEntity.ExamDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Exam[MAX];
    }
    private static Exam[] FIXED = new Exam[MAX];
    public static final Map<Integer, Exam> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByExamtypeid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByPro0etpid = newSortedMap();

    private static final List<Exam> fixedCache = newList();

    public static void put(Exam exam, boolean force){
        if(exam == null || exam.id <= 0) return ;

        int id = exam.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(exam))
                	FIXED[id - 1] = exam;
                if (!fixedCache.contains(exam))
                	fixedCache.add(exam);
            }
        } else {
            vars.put(id, exam);
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = exam.oldVal(Col.lhubid);
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
            int lhubid = exam.getLhubid();
            Set m1 = varsByLhubid.get(lhubid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByLhubid.put(lhubid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index examtypeid
          Object ov = exam.oldVal(Col.examtypeid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByExamtypeid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByExamtypeid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int examtypeid = exam.getExamtypeid();
            Set m2 = varsByExamtypeid.get(examtypeid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByExamtypeid.put(examtypeid, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index pro0etpid
          Object ov = exam.oldVal(Col.pro0etpid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByPro0etpid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByPro0etpid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int pro0etpid = exam.getPro0etpid();
            Set m3 = varsByPro0etpid.get(pro0etpid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByPro0etpid.put(pro0etpid, m3);
            }
            m3.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLhubid.clear();
        varsByExamtypeid.clear();
        varsByPro0etpid.clear();
        FIXED = new Exam[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ExamDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ExamDAO DAO, String TABLENAME2){
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

    public static void relocate(ExamDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ExamDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ExamDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ExamDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ExamDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ExamDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ExamDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ExamDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ExamDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ExamDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ExamDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ExamDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ExamDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ExamDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ExamDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Exam[maxId(DAO)];

        List<Exam> exams = DAO.selectAll();
        for (Exam exam : exams) {
            exam.reset();
            put(exam, true);
        }
    }

    public static Map toMap(Exam exam){
        return exam.toMap();
    }

    public static List<Map> toMap(List<Exam> exams){
        List<Map> ret = new Vector<Map>();
        for (Exam exam : exams){
            Map e = exam.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Exam> sortZh(List<Exam> exams, final String key) {
        Collections.sort(exams, new Comparator<Exam>() {
            public int compare(Exam o1, Exam o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return exams;
    }

    public static List<Exam> sort(List<Exam> exams, final String key) {
        Collections.sort(exams, new Comparator<Exam>() {
            public int compare(Exam o1, Exam o2) {
                return o1.compareTo(o2, key);
            }
        });
        return exams;
    }

    public static List<Exam> sort(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return exams;
    }

    public static List<Exam> sortReverse(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return exams;
    }

    public static List<Exam> sortLhubid(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return exams;
    }

    public static List<Exam> sortLhubidRo(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return exams;
    }

    public static List<Exam> sortExamtypeid(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.getExamtypeid();
                Object v2 = o2.getExamtypeid();
                return compareTo(v1, v2);
            }
        });
        return exams;
    }

    public static List<Exam> sortExamtypeidRo(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.getExamtypeid();
                Object v2 = o2.getExamtypeid();
                return 0 - compareTo(v1, v2);
            };
        });
        return exams;
    }

    public static List<Exam> sortPro0etpid(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.getPro0etpid();
                Object v2 = o2.getPro0etpid();
                return compareTo(v1, v2);
            }
        });
        return exams;
    }

    public static List<Exam> sortPro0etpidRo(List<Exam> exams){
        Collections.sort(exams, new Comparator<Exam>(){
            public int compare(Exam o1, Exam o2) {
                Object v1 = o1.getPro0etpid();
                Object v2 = o2.getPro0etpid();
                return 0 - compareTo(v1, v2);
            };
        });
        return exams;
    }

    public static Exam insert(Exam exam) {
        ExamDAO DAO = DAO();
        return insert(DAO, exam, DAO.TABLENAME);
    }

    public static Exam insert(ExamDAO DAO, Exam exam) {
        return insert(DAO, exam, DAO.TABLENAME);
    }

    public static Exam insert(Exam exam, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return insert(DAO, exam, TABLENAME2);
    }

    public static Exam insert(ExamDAO DAO, Exam exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(exam, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        exam.id = n;
        if(cacheModel != NO_CACHE) put(exam, true);

        return exam;
    }

    public static Exam asyncInsert(Exam exam) {
        ExamDAO DAO = DAO();
        return asyncInsert(DAO, exam, DAO.TABLENAME);
    }
    public static Exam asyncInsert(ExamDAO DAO, Exam exam) {
        return asyncInsert(DAO, exam, DAO.TABLENAME);
    }
    public static Exam asyncInsert(Exam exam, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return asyncInsert(DAO, exam, TABLENAME2);
    }
    public static Exam asyncInsert(ExamDAO DAO, Exam exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(exam, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        exam.id = n;
        if(cacheModel != NO_CACHE) put(exam, true);
        return exam;
    }
    public static Exam insert2(Exam exam) {
        ExamDAO DAO = DAO();
        return insert2(DAO, exam, DAO.TABLENAME);
    }

    public static Exam insert2(ExamDAO DAO, Exam exam) {
        return insert2(DAO, exam, DAO.TABLENAME);
    }

    public static Exam insert2(Exam exam, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return insert2(DAO, exam, TABLENAME2);
    }

    public static Exam insert2(ExamDAO DAO, Exam exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(exam, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        exam.id = n;
        if(cacheModel != NO_CACHE) put(exam, true);

        return exam;
    }

    public static Exam asyncInsert2(Exam exam) {
        ExamDAO DAO = DAO();
        return asyncInsert2(DAO, exam, DAO.TABLENAME);
    }
    public static Exam asyncInsert2(ExamDAO DAO, Exam exam) {
        return asyncInsert2(DAO, exam, DAO.TABLENAME);
    }
    public static Exam asyncInsert2(Exam exam, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return asyncInsert2(DAO, exam, TABLENAME2);
    }
    public static Exam asyncInsert2(ExamDAO DAO, Exam exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        exam.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(exam, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(exam, true);
        return exam;
    }
    public static int[] insert(List<Exam> exams) {
        ExamDAO DAO = DAO();
        return insert(DAO, exams, DAO.TABLENAME);
    }

    public static int[] insert(ExamDAO DAO, List<Exam> exams) {
        return insert(DAO, exams, DAO.TABLENAME);
    }

    public static int[] insert(List<Exam> exams, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return insert(DAO, exams, TABLENAME2);
    }

    public static int[] insert(ExamDAO DAO, List<Exam> exams, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(exams, TABLENAME2);
            int n = 0;
            for(Exam exam : exams){
                exam.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[exams.size()];
        int n = 0;
        for(Exam exam : exams){
            exam = insert(DAO, exam, TABLENAME2);
            ret[n++] = (exam == null) ? 0 : (int)exam.id;
        }
        return ret;
    }

    public static int delete(Exam exam) {
        int id = exam.id;
        ExamDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ExamDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ExamDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ExamDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Exam exam) {
        int id = exam.id;
        ExamDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ExamDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ExamDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ExamDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ExamDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ExamDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ExamDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ExamDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ExamDAO DAO, int[] ids,String TABLENAME2) {
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
        ExamDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ExamDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ExamDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Exam> beans) {
        ExamDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Exam> beans, ExamDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Exam> beans, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Exam> beans, final ExamDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Exam exam : beans){
                int id = exam.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Exam> getAll() {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Exam> getAll(ExamDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Exam> getAll(String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Exam> getAll(final ExamDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exam> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Exam> getNoSortAll() {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Exam> getNoSortAll(ExamDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Exam> getNoSortAll(String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Exam> getNoSortAll(final ExamDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Exam> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exam> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Exam exam = FIXED[i];
                if (exam != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Exam> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Exam> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ExamDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ExamDAO DAO, final String TABLENAME2) {
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
        ExamDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ExamDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ExamDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ExamDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Exam> getIn(List<Integer> keys) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exam> getIn(List<Integer> keys, ExamDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exam> getIn(List<Integer> keys, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Exam> getIn(final List<Integer> keys, final ExamDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exam> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Exam> getNoSortIn(List<Integer> keys) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exam> getNoSortIn(List<Integer> keys, ExamDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Exam> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Exam> getNoSortIn(final List<Integer> keys, final ExamDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Exam> result = newList();
            for (int key : keys) {
                Exam exam = getByKeyFromMemory(key);
                if( exam == null ) continue;
                result.add(exam);
            }
            return result;
        }
    }

    public static List<Exam> getLast(int num) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Exam> getLast(ExamDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Exam> getLast(int num, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Exam> getLast(final ExamDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Exam> result = newList();
            List<Exam> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Exam last() {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Exam last(ExamDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Exam last(String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Exam last(final ExamDAO DAO, final String TABLENAME2) {
        Exam result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ExamDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ExamDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ExamDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ExamDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Exam> getGtKey(int id) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Exam> getGtKey(ExamDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Exam> getGtKey(int id, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Exam> getGtKey(final ExamDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exam> result = newList();
            List<Exam> exams = getAll();
            for (Exam exam : exams) {
                if(exam.id <= id) continue;
                result.add(exam);
            }
            return result;
        }
    }

    public static Exam getByKey(int id) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Exam> asyncGetByKey(final int id) {
        Future<Exam> f = Async.exec(new Callable<Exam>() {
            public Exam call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Exam getByKey(ExamDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Exam getByKey(int id, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Exam getByKey(final ExamDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Exam getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Exam deleteFromMemory(final int id) {
        Exam exam;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            exam = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(exam);
        } else {
            exam = vars.remove(id);
        }
        if(exam == null) return null;

        int lhubid = exam.getLhubid();
        Set m1 = varsByLhubid.get(lhubid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        int examtypeid = exam.getExamtypeid();
        Set m2 = varsByExamtypeid.get(examtypeid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByExamtypeid.remove(examtypeid);
        }

        int pro0etpid = exam.getPro0etpid();
        Set m3 = varsByPro0etpid.get(pro0etpid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByPro0etpid.remove(pro0etpid);
        }

        return exam;
    }

    public static List<Exam> getByPage(int page, int size) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Exam> getByPage(ExamDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Exam> getByPage(int page, int size, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Exam> getByPage(final ExamDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Exam> result = newList();
            List<Exam> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ExamDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ExamDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByLhubid(Integer lhubid) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(ExamDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final ExamDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Exam> exams = getByLhubid(DAO, lhubid, TABLENAME2);
        return exams.size();
    }

    public static List<Exam> getByLhubid(Integer lhubid) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Exam> getByLhubid(ExamDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Exam> getByLhubid(Integer lhubid, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Exam> getByLhubid(final ExamDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Exam> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Exam e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByExamtypeid(Integer examtypeid) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static int countByExamtypeid(ExamDAO DAO, Integer examtypeid) {
        return countByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static int countByExamtypeid(Integer examtypeid, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExamtypeid(DAO, examtypeid, TABLENAME2);
    }

    public static int countByExamtypeid(final ExamDAO DAO, final Integer examtypeid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExamtypeid(examtypeid, TABLENAME2);
        }
        List<Exam> exams = getByExamtypeid(DAO, examtypeid, TABLENAME2);
        return exams.size();
    }

    public static List<Exam> getByExamtypeid(Integer examtypeid) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static List<Exam> getByExamtypeid(ExamDAO DAO, Integer examtypeid) {
        return getByExamtypeid(DAO, examtypeid, DAO.TABLENAME);
    }

    public static List<Exam> getByExamtypeid(Integer examtypeid, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExamtypeid(DAO, examtypeid, TABLENAME2);
    }

    public static List<Exam> getByExamtypeid(final ExamDAO DAO, final Integer examtypeid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExamtypeid(examtypeid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Exam> result = newList();
            Set<Integer> m1 = varsByExamtypeid.get(examtypeid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Exam e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByPro0etpid(Integer pro0etpid) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPro0etpid(DAO, pro0etpid, DAO.TABLENAME);
    }

    public static int countByPro0etpid(ExamDAO DAO, Integer pro0etpid) {
        return countByPro0etpid(DAO, pro0etpid, DAO.TABLENAME);
    }

    public static int countByPro0etpid(Integer pro0etpid, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPro0etpid(DAO, pro0etpid, TABLENAME2);
    }

    public static int countByPro0etpid(final ExamDAO DAO, final Integer pro0etpid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPro0etpid(pro0etpid, TABLENAME2);
        }
        List<Exam> exams = getByPro0etpid(DAO, pro0etpid, TABLENAME2);
        return exams.size();
    }

    public static List<Exam> getByPro0etpid(Integer pro0etpid) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPro0etpid(DAO, pro0etpid, DAO.TABLENAME);
    }

    public static List<Exam> getByPro0etpid(ExamDAO DAO, Integer pro0etpid) {
        return getByPro0etpid(DAO, pro0etpid, DAO.TABLENAME);
    }

    public static List<Exam> getByPro0etpid(Integer pro0etpid, String TABLENAME2) {
        ExamDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPro0etpid(DAO, pro0etpid, TABLENAME2);
    }

    public static List<Exam> getByPro0etpid(final ExamDAO DAO, final Integer pro0etpid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPro0etpid(pro0etpid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Exam> result = newList();
            Set<Integer> m1 = varsByPro0etpid.get(pro0etpid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Exam e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _pro0etpid = e.getPro0etpid();
                if(_pro0etpid != pro0etpid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Exam update(Exam exam) {
        ExamDAO DAO = DAO();
        return update(DAO, exam, DAO.TABLENAME);
    }

    public static Exam update(ExamDAO DAO, Exam exam) {
        return update(DAO, exam, DAO.TABLENAME);
    }

    public static Exam update(Exam exam, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return update(DAO, exam, TABLENAME2);
    }

    public static Exam update(final ExamDAO DAO, final Exam exam,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(exam, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(exam, TABLENAME2);
            if(n == -1) 
                return exam;
            else if(n <= 0) 
                return null;
        }
        return exam;
    }

    public static Exam asyncUpdate(Exam exam) {
        ExamDAO DAO = DAO();
        return asyncUpdate(DAO, exam, DAO.TABLENAME);
    }

    public static Exam asyncUpdate(ExamDAO DAO, Exam exam) {
        return asyncUpdate(DAO, exam, DAO.TABLENAME);
    }

    public static Exam asyncUpdate(Exam exam, String TABLENAME2) {
        ExamDAO DAO = DAO();
        return asyncUpdate(DAO, exam, TABLENAME2);
    }

    public static Exam asyncUpdate(final ExamDAO DAO, final Exam exam,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(exam, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(exam, TABLENAME2);
        }
        return exam;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ExamDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ExamDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ExamDAO DAO, final String TABLENAME2) {
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

        List<Exam> exams = getAll();
        for (Exam exam : exams) {
            int n = DAO.insert2(exam, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
