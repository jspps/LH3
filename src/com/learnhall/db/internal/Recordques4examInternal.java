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

import static com.learnhall.db.bean.Recordques4exam.Col;

//learnhall3_design - recordques4exam
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Recordques4examInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Recordques4examInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Recordques4examInternal(){}

    public static Recordques4examDAO DAO(){
        return Recordques4examEntity.Recordques4examDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Recordques4exam[MAX];
    }
    private static Recordques4exam[] FIXED = new Recordques4exam[MAX];
    public static final Map<Integer, Recordques4exam> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByCustomidCatalog4Exam = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByQuestionid = newSortedMap();
    public static final Map<String, Integer> varsByCustomidQuestionid = newSortedMap();

    private static final List<Recordques4exam> fixedCache = newList();

    public static void put(Recordques4exam recordques4exam, boolean force){
        if(recordques4exam == null || recordques4exam.id <= 0) return ;

        int id = recordques4exam.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(recordques4exam))
                	FIXED[id - 1] = recordques4exam;
                if (!fixedCache.contains(recordques4exam))
                	fixedCache.add(recordques4exam);
            }
        } else {
            vars.put(id, recordques4exam);
        }

        { // 单-非唯一索引 remove old index customid
          Object ov = recordques4exam.oldVal(Col.customid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByCustomid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByCustomid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int customid = recordques4exam.getCustomid();
            Set m1 = varsByCustomid.get(customid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByCustomid.put(customid, m1);
            }
            m1.add(id);
          }
        }

        { // customid_catalog
          boolean bChanged = recordques4exam.inChanged(Col.customid, Col.catalog4Exam);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vcustomid = recordques4exam.oldOrNew(Col.customid);
              Object vcatalog4Exam = recordques4exam.oldOrNew(Col.catalog4Exam);
              String okey = com.bowlong.lang.PStr.b().a(vcustomid, "-", vcatalog4Exam).e();
              Set m2 = varsByCustomidCatalog4Exam.get(okey);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                       varsByCustomidCatalog4Exam.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vcustomid = recordques4exam.getCustomid();
              int vcatalog4Exam = recordques4exam.getCatalog4Exam();
              String vkey = com.bowlong.lang.PStr.b().a(vcustomid, "-", vcatalog4Exam).e();
              Set m2 = varsByCustomidCatalog4Exam.get(vkey);
              if(m2 == null){
                  m2 = newSortedSet();
                  varsByCustomidCatalog4Exam.put(vkey, m2);
              }
              m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index questionid
          Object ov = recordques4exam.oldVal(Col.questionid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByQuestionid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByQuestionid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int questionid = recordques4exam.getQuestionid();
            Set m3 = varsByQuestionid.get(questionid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByQuestionid.put(questionid, m3);
            }
            m3.add(id);
          }
        }

        { // customid_quesid
          boolean bChanged = recordques4exam.inChanged(Col.customid, Col.questionid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vcustomid = recordques4exam.oldOrNew(Col.customid);
            Object vquestionid = recordques4exam.oldOrNew(Col.questionid);
            String okey = com.bowlong.lang.PStr.b().a(vcustomid, "-", vquestionid).e();
            varsByCustomidQuestionid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vcustomid = recordques4exam.getCustomid();
              int vquestionid = recordques4exam.getQuestionid();
              String vkey = com.bowlong.lang.PStr.b().a(vcustomid, "-", vquestionid).e();
              varsByCustomidQuestionid.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustomid.clear();
        varsByCustomidCatalog4Exam.clear();
        varsByQuestionid.clear();
        varsByCustomidQuestionid.clear();
        FIXED = new Recordques4exam[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Recordques4examDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Recordques4examDAO DAO, String TABLENAME2){
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

    public static void relocate(Recordques4examDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Recordques4examDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Recordques4examDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Recordques4examDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Recordques4examDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Recordques4examDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Recordques4examDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Recordques4examDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Recordques4examDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Recordques4examDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Recordques4examDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Recordques4examDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Recordques4examDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Recordques4exam[maxId(DAO)];

        List<Recordques4exam> recordques4exams = DAO.selectAll();
        for (Recordques4exam recordques4exam : recordques4exams) {
            recordques4exam.reset();
            put(recordques4exam, true);
        }
    }

    public static Map toMap(Recordques4exam recordques4exam){
        return recordques4exam.toMap();
    }

    public static List<Map> toMap(List<Recordques4exam> recordques4exams){
        List<Map> ret = new Vector<Map>();
        for (Recordques4exam recordques4exam : recordques4exams){
            Map e = recordques4exam.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Recordques4exam> sortZh(List<Recordques4exam> recordques4exams, final String key) {
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>() {
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sort(List<Recordques4exam> recordques4exams, final String key) {
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>() {
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                return o1.compareTo(o2, key);
            }
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sort(List<Recordques4exam> recordques4exams){
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>(){
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sortReverse(List<Recordques4exam> recordques4exams){
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>(){
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sortCustomid(List<Recordques4exam> recordques4exams){
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>(){
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                Object v1 = o1.getCustomid();
                Object v2 = o2.getCustomid();
                return compareTo(v1, v2);
            }
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sortCustomidRo(List<Recordques4exam> recordques4exams){
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>(){
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                Object v1 = o1.getCustomid();
                Object v2 = o2.getCustomid();
                return 0 - compareTo(v1, v2);
            };
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sortQuestionid(List<Recordques4exam> recordques4exams){
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>(){
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                Object v1 = o1.getQuestionid();
                Object v2 = o2.getQuestionid();
                return compareTo(v1, v2);
            }
        });
        return recordques4exams;
    }

    public static List<Recordques4exam> sortQuestionidRo(List<Recordques4exam> recordques4exams){
        Collections.sort(recordques4exams, new Comparator<Recordques4exam>(){
            public int compare(Recordques4exam o1, Recordques4exam o2) {
                Object v1 = o1.getQuestionid();
                Object v2 = o2.getQuestionid();
                return 0 - compareTo(v1, v2);
            };
        });
        return recordques4exams;
    }

    public static Recordques4exam insert(Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = DAO();
        return insert(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam insert(Recordques4examDAO DAO, Recordques4exam recordques4exam) {
        return insert(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam insert(Recordques4exam recordques4exam, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return insert(DAO, recordques4exam, TABLENAME2);
    }

    public static Recordques4exam insert(Recordques4examDAO DAO, Recordques4exam recordques4exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(recordques4exam, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recordques4exam.id = n;
        if(cacheModel != NO_CACHE) put(recordques4exam, true);

        return recordques4exam;
    }

    public static Recordques4exam asyncInsert(Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = DAO();
        return asyncInsert(DAO, recordques4exam, DAO.TABLENAME);
    }
    public static Recordques4exam asyncInsert(Recordques4examDAO DAO, Recordques4exam recordques4exam) {
        return asyncInsert(DAO, recordques4exam, DAO.TABLENAME);
    }
    public static Recordques4exam asyncInsert(Recordques4exam recordques4exam, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return asyncInsert(DAO, recordques4exam, TABLENAME2);
    }
    public static Recordques4exam asyncInsert(Recordques4examDAO DAO, Recordques4exam recordques4exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(recordques4exam, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        recordques4exam.id = n;
        if(cacheModel != NO_CACHE) put(recordques4exam, true);
        return recordques4exam;
    }
    public static Recordques4exam insert2(Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = DAO();
        return insert2(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam insert2(Recordques4examDAO DAO, Recordques4exam recordques4exam) {
        return insert2(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam insert2(Recordques4exam recordques4exam, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return insert2(DAO, recordques4exam, TABLENAME2);
    }

    public static Recordques4exam insert2(Recordques4examDAO DAO, Recordques4exam recordques4exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(recordques4exam, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recordques4exam.id = n;
        if(cacheModel != NO_CACHE) put(recordques4exam, true);

        return recordques4exam;
    }

    public static Recordques4exam asyncInsert2(Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = DAO();
        return asyncInsert2(DAO, recordques4exam, DAO.TABLENAME);
    }
    public static Recordques4exam asyncInsert2(Recordques4examDAO DAO, Recordques4exam recordques4exam) {
        return asyncInsert2(DAO, recordques4exam, DAO.TABLENAME);
    }
    public static Recordques4exam asyncInsert2(Recordques4exam recordques4exam, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return asyncInsert2(DAO, recordques4exam, TABLENAME2);
    }
    public static Recordques4exam asyncInsert2(Recordques4examDAO DAO, Recordques4exam recordques4exam, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        recordques4exam.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(recordques4exam, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(recordques4exam, true);
        return recordques4exam;
    }
    public static int[] insert(List<Recordques4exam> recordques4exams) {
        Recordques4examDAO DAO = DAO();
        return insert(DAO, recordques4exams, DAO.TABLENAME);
    }

    public static int[] insert(Recordques4examDAO DAO, List<Recordques4exam> recordques4exams) {
        return insert(DAO, recordques4exams, DAO.TABLENAME);
    }

    public static int[] insert(List<Recordques4exam> recordques4exams, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return insert(DAO, recordques4exams, TABLENAME2);
    }

    public static int[] insert(Recordques4examDAO DAO, List<Recordques4exam> recordques4exams, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(recordques4exams, TABLENAME2);
            int n = 0;
            for(Recordques4exam recordques4exam : recordques4exams){
                recordques4exam.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[recordques4exams.size()];
        int n = 0;
        for(Recordques4exam recordques4exam : recordques4exams){
            recordques4exam = insert(DAO, recordques4exam, TABLENAME2);
            ret[n++] = (recordques4exam == null) ? 0 : (int)recordques4exam.id;
        }
        return ret;
    }

    public static int delete(Recordques4exam recordques4exam) {
        int id = recordques4exam.id;
        Recordques4examDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Recordques4examDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Recordques4examDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Recordques4examDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Recordques4exam recordques4exam) {
        int id = recordques4exam.id;
        Recordques4examDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Recordques4examDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Recordques4examDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Recordques4examDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Recordques4examDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Recordques4examDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Recordques4examDAO DAO, int[] ids,String TABLENAME2) {
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
        Recordques4examDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Recordques4examDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Recordques4examDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Recordques4exam> beans) {
        Recordques4examDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recordques4exam> beans, Recordques4examDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recordques4exam> beans, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Recordques4exam> beans, final Recordques4examDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Recordques4exam recordques4exam : beans){
                int id = recordques4exam.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Recordques4exam> getAll() {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getAll(Recordques4examDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getAll(String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Recordques4exam> getAll(final Recordques4examDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordques4exam> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recordques4exam> getNoSortAll() {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getNoSortAll(Recordques4examDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getNoSortAll(String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Recordques4exam> getNoSortAll(final Recordques4examDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Recordques4exam> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordques4exam> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Recordques4exam recordques4exam = FIXED[i];
                if (recordques4exam != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Recordques4exam> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Recordques4exam> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Recordques4examDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Recordques4examDAO DAO, final String TABLENAME2) {
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
        Recordques4examDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Recordques4examDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Recordques4examDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Recordques4exam> getIn(List<Integer> keys) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getIn(List<Integer> keys, Recordques4examDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getIn(List<Integer> keys, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Recordques4exam> getIn(final List<Integer> keys, final Recordques4examDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordques4exam> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recordques4exam> getNoSortIn(List<Integer> keys) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getNoSortIn(List<Integer> keys, Recordques4examDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Recordques4exam> getNoSortIn(final List<Integer> keys, final Recordques4examDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Recordques4exam> result = newList();
            for (int key : keys) {
                Recordques4exam recordques4exam = getByKeyFromMemory(key);
                if( recordques4exam == null ) continue;
                result.add(recordques4exam);
            }
            return result;
        }
    }

    public static List<Recordques4exam> getLast(int num) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getLast(Recordques4examDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getLast(int num, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Recordques4exam> getLast(final Recordques4examDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Recordques4exam> result = newList();
            List<Recordques4exam> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Recordques4exam last() {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Recordques4exam last(Recordques4examDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Recordques4exam last(String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Recordques4exam last(final Recordques4examDAO DAO, final String TABLENAME2) {
        Recordques4exam result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Recordques4examDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Recordques4examDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Recordques4examDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Recordques4exam> getGtKey(int id) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getGtKey(Recordques4examDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getGtKey(int id, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Recordques4exam> getGtKey(final Recordques4examDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordques4exam> result = newList();
            List<Recordques4exam> recordques4exams = getAll();
            for (Recordques4exam recordques4exam : recordques4exams) {
                if(recordques4exam.id <= id) continue;
                result.add(recordques4exam);
            }
            return result;
        }
    }

    public static Recordques4exam getByKey(int id) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Recordques4exam> asyncGetByKey(final int id) {
        Future<Recordques4exam> f = Async.exec(new Callable<Recordques4exam>() {
            public Recordques4exam call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Recordques4exam getByKey(Recordques4examDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Recordques4exam getByKey(int id, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Recordques4exam getByKey(final Recordques4examDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Recordques4exam getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Recordques4exam deleteFromMemory(final int id) {
        Recordques4exam recordques4exam;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            recordques4exam = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(recordques4exam);
        } else {
            recordques4exam = vars.remove(id);
        }
        if(recordques4exam == null) return null;

        int customid = recordques4exam.getCustomid();
        Set m1 = varsByCustomid.get(customid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByCustomid.remove(customid);
        }

        { // customid_catalog
            int vcustomid = recordques4exam.getCustomid();
            int vcatalog4Exam = recordques4exam.getCatalog4Exam();
            String vkey = com.bowlong.lang.PStr.b().a(vcustomid, "-", vcatalog4Exam).e();
            Set m2 = varsByCustomidCatalog4Exam.get(vkey);
            if(m2 != null) { 
                m2.remove(id);
                if(m2.isEmpty())
                    varsByCustomidCatalog4Exam.remove(vkey);
            }
        }

        int questionid = recordques4exam.getQuestionid();
        Set m3 = varsByQuestionid.get(questionid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByQuestionid.remove(questionid);
        }

        { // customid_quesid
            int vcustomid = recordques4exam.getCustomid();
            int vquestionid = recordques4exam.getQuestionid();
            String vkey = com.bowlong.lang.PStr.b().a(vcustomid, "-", vquestionid).e();
            varsByCustomidQuestionid.remove(vkey);
        }

        return recordques4exam;
    }

    public static List<Recordques4exam> getByPage(int page, int size) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByPage(Recordques4examDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByPage(int page, int size, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Recordques4exam> getByPage(final Recordques4examDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recordques4exam> result = newList();
            List<Recordques4exam> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Recordques4examDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Recordques4examDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustomid(Integer customid) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomid(DAO, customid, DAO.TABLENAME);
    }

    public static int countByCustomid(Recordques4examDAO DAO, Integer customid) {
        return countByCustomid(DAO, customid, DAO.TABLENAME);
    }

    public static int countByCustomid(Integer customid, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomid(DAO, customid, TABLENAME2);
    }

    public static int countByCustomid(final Recordques4examDAO DAO, final Integer customid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomid(customid, TABLENAME2);
        }
        List<Recordques4exam> recordques4exams = getByCustomid(DAO, customid, TABLENAME2);
        return recordques4exams.size();
    }

    public static List<Recordques4exam> getByCustomid(Integer customid) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomid(DAO, customid, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByCustomid(Recordques4examDAO DAO, Integer customid) {
        return getByCustomid(DAO, customid, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByCustomid(Integer customid, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomid(DAO, customid, TABLENAME2);
    }

    public static List<Recordques4exam> getByCustomid(final Recordques4examDAO DAO, final Integer customid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomid(customid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Recordques4exam> result = newList();
            Set<Integer> m1 = varsByCustomid.get(customid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Recordques4exam e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _customid = e.getCustomid();
                if(_customid != customid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByCustomidCatalog4Exam(Integer customid, Integer catalog4Exam) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomidCatalog4Exam(DAO, customid, catalog4Exam, DAO.TABLENAME);
    }

    public static int countByCustomidCatalog4Exam(Recordques4examDAO DAO, Integer customid, Integer catalog4Exam) {
        return countByCustomidCatalog4Exam(DAO, customid, catalog4Exam, DAO.TABLENAME);
    }

    public static int countByCustomidCatalog4Exam(Integer customid, Integer catalog4Exam, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomidCatalog4Exam(DAO, customid, catalog4Exam, TABLENAME2);
    }

    public static int countByCustomidCatalog4Exam(final Recordques4examDAO DAO, Integer customid, Integer catalog4Exam, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomidCatalog4Exam(customid, catalog4Exam, TABLENAME2);
        }
        List<Recordques4exam> recordques4exams = getByCustomidCatalog4Exam(customid, catalog4Exam, TABLENAME2);
        return recordques4exams.size();
    }

    public static List<Recordques4exam> getByCustomidCatalog4Exam(Integer customid, Integer catalog4Exam) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomidCatalog4Exam(DAO, customid, catalog4Exam, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByCustomidCatalog4Exam(Recordques4examDAO DAO, Integer customid, Integer catalog4Exam) {
        return getByCustomidCatalog4Exam(DAO, customid, catalog4Exam, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByCustomidCatalog4Exam(Integer customid, Integer catalog4Exam, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomidCatalog4Exam(DAO, customid, catalog4Exam, TABLENAME2);
    }

    public static List<Recordques4exam> getByCustomidCatalog4Exam(final Recordques4examDAO DAO, Integer customid, Integer catalog4Exam, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomidCatalog4Exam(customid, catalog4Exam, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Recordques4exam> result = newList();
            String vkey = customid+"-"+catalog4Exam;
            Set<Integer> m1 = varsByCustomidCatalog4Exam.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Recordques4exam e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _customid = e.getCustomid();
                int _catalog4Exam = e.getCatalog4Exam();
                String _key = com.bowlong.lang.PStr.b().a(_customid, "-", _catalog4Exam).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByQuestionid(Integer questionid) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByQuestionid(DAO, questionid, DAO.TABLENAME);
    }

    public static int countByQuestionid(Recordques4examDAO DAO, Integer questionid) {
        return countByQuestionid(DAO, questionid, DAO.TABLENAME);
    }

    public static int countByQuestionid(Integer questionid, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByQuestionid(DAO, questionid, TABLENAME2);
    }

    public static int countByQuestionid(final Recordques4examDAO DAO, final Integer questionid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByQuestionid(questionid, TABLENAME2);
        }
        List<Recordques4exam> recordques4exams = getByQuestionid(DAO, questionid, TABLENAME2);
        return recordques4exams.size();
    }

    public static List<Recordques4exam> getByQuestionid(Integer questionid) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByQuestionid(DAO, questionid, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByQuestionid(Recordques4examDAO DAO, Integer questionid) {
        return getByQuestionid(DAO, questionid, DAO.TABLENAME);
    }

    public static List<Recordques4exam> getByQuestionid(Integer questionid, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByQuestionid(DAO, questionid, TABLENAME2);
    }

    public static List<Recordques4exam> getByQuestionid(final Recordques4examDAO DAO, final Integer questionid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByQuestionid(questionid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Recordques4exam> result = newList();
            Set<Integer> m1 = varsByQuestionid.get(questionid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Recordques4exam e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _questionid = e.getQuestionid();
                if(_questionid != questionid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Recordques4exam getByCustomidQuestionid(Integer customid, Integer questionid) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomidQuestionid(DAO, customid, questionid, DAO.TABLENAME);
    }

    public static Recordques4exam getByCustomidQuestionid(Recordques4examDAO DAO, Integer customid, Integer questionid) {
        return getByCustomidQuestionid(DAO, customid, questionid, DAO.TABLENAME);
    }

    public static Recordques4exam getByCustomidQuestionid(Integer customid, Integer questionid, String TABLENAME2) {
        Recordques4examDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomidQuestionid(DAO, customid, questionid, TABLENAME2);
    }

    public static Recordques4exam getByCustomidQuestionid(final Recordques4examDAO DAO, Integer customid, Integer questionid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomidQuestionid(customid, questionid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = customid+"-"+questionid;
            Integer id = varsByCustomidQuestionid.get(vkey);
            if(id == null) return null;
            Recordques4exam result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getCustomid() != customid){
                varsByCustomidQuestionid.remove(vkey);
                return null;
            }
            if(result.getQuestionid() != questionid){
                varsByCustomidQuestionid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Recordques4exam update(Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = DAO();
        return update(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam update(Recordques4examDAO DAO, Recordques4exam recordques4exam) {
        return update(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam update(Recordques4exam recordques4exam, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return update(DAO, recordques4exam, TABLENAME2);
    }

    public static Recordques4exam update(final Recordques4examDAO DAO, final Recordques4exam recordques4exam,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recordques4exam, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(recordques4exam, TABLENAME2);
            if(n == -1) 
                return recordques4exam;
            else if(n <= 0) 
                return null;
        }
        return recordques4exam;
    }

    public static Recordques4exam asyncUpdate(Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = DAO();
        return asyncUpdate(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam asyncUpdate(Recordques4examDAO DAO, Recordques4exam recordques4exam) {
        return asyncUpdate(DAO, recordques4exam, DAO.TABLENAME);
    }

    public static Recordques4exam asyncUpdate(Recordques4exam recordques4exam, String TABLENAME2) {
        Recordques4examDAO DAO = DAO();
        return asyncUpdate(DAO, recordques4exam, TABLENAME2);
    }

    public static Recordques4exam asyncUpdate(final Recordques4examDAO DAO, final Recordques4exam recordques4exam,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recordques4exam, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(recordques4exam, TABLENAME2);
        }
        return recordques4exam;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Recordques4examDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Recordques4examDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Recordques4examDAO DAO, final String TABLENAME2) {
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

        List<Recordques4exam> recordques4exams = getAll();
        for (Recordques4exam recordques4exam : recordques4exams) {
            int n = DAO.insert2(recordques4exam, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
