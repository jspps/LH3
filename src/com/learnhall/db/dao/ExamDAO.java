package com.learnhall.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import java.sql.*;
import java.util.concurrent.*;
import javax.sql.*;
import com.bowlong.util.DateEx;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.learnhall.db.bean.*;

//learnhall3_design - exam
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExamDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ExamDAO.class);

    public static final String TABLE = "exam";
    public static String TABLENAME = "exam";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "examtypeid", "name", "score", "lhubid", "examtime", "status", "createtime", "productid", "pro0etpid", "descstr"};
    public static String coulmns = "id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr";
    public static String coulmns2 = "examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr";

    public ExamDAO(Connection conn) {
        super(conn);
    }

    public ExamDAO(DataSource ds) {
        super(ds);
    }

    public ExamDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Exam exam) {
        return insert(exam, TABLENAME);
    }

    public int insert(final Exam exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            exam.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr) VALUES (:examtypeid, :name, :score, :lhubid, :examtime, :status, :createtime, :productid, :pro0etpid, :descstr)");
            Map map = super.insert(sql.toString(), exam);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Exam exam) {
        return asyncInsert(exam, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Exam exam, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(exam, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                       return 0;
                   } finally {
                       decrementAndGet();
                   }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public Future<Integer> asyncInsert2(final Exam exam) {
        return asyncInsert2(exam, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Exam exam, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(exam, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                       return 0;
                   } finally {
                       decrementAndGet();
                   }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int insert2(final Exam exam) {
        return insert2(exam, TABLENAME);
    }

    public int insert2(final Exam exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            exam.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr) VALUES (:id, :examtypeid, :name, :score, :lhubid, :examtime, :status, :createtime, :productid, :pro0etpid, :descstr)");
            Map map = super.insert(sql.toString(), exam);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Exam> exams) {
        return insert(exams, TABLENAME);
    }

    public int[] insert(final List<Exam> exams, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(exams == null || exams.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr) VALUES (:examtypeid, :name, :score, :lhubid, :examtime, :status, :createtime, :productid, :pro0etpid, :descstr)");
            return super.batchInsert(sql.toString(), exams);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int id) {
        return deleteByKey(id, TABLENAME);
    }

    public int deleteByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int id) {
        return asyncDeleteByKey(id, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int id, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(id, TABLENAME2);
                    } catch (Exception e) {
                       log.info(e2s(e));
                       return 0;
                    } finally {
                        decrementAndGet();
                    }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int[] deleteByKey(final int[] ids) {
        return deleteByKey(ids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            List list = newList();
            for (int id : keys) {
                Map params = newMap();
                params.put("id", id);
                list.add(params);
            }
            return super.batchUpdate(sql.toString(), list);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInKeys(final List<Integer> keys) {
        return deleteInKeys(keys, TABLENAME);
    }

    public int deleteInKeys(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Exam> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Exam> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Exam exam = beans.get(i);
                int id = exam.id;
                sb.append(id);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Exam> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPKs() {
        return selectPKs(TABLENAME);
    }

    public List<Integer> selectPKs(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Map> selectInIndex() {
        return selectInIndex(TABLENAME);
    }

    public List<Map> selectInIndex(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, lhubid, pro0etpid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Exam> selectIn(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Exam> selectIn2(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectInPKs(final List<Integer> keys) {
        return selectInPKs(keys, TABLENAME);
    }

    public List<Integer> selectInPKs(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            List<Integer> result = newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Exam> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLastPKs(final int num) {
        return selectLastPKs(num, TABLENAME);
    }

    public List<Integer> selectLastPKs(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Exam last() {
        return last(TABLENAME);
    }

    public Exam last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Exam.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Exam> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Exam> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int id) {
        return selectGtKeyPKs(id, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Exam selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Exam selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int maxId() {
        return maxId(TABLENAME);
    }

    public int maxId(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT MAX(id) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByLhubid(final Integer lhubid) {
        return countByLhubid(lhubid, TABLENAME);
    }

    public int countByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectByLhubid(final Integer lhubid) {
        return selectByLhubid(lhubid, TABLENAME);
    }

    public List<Exam> selectByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByLhubidPKs(final Integer lhubid) {
        return selectByLhubidPKs(lhubid, TABLENAME);
    }

    public List<Integer> selectByLhubidPKs(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectPageByLhubid(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubid(lhubid, begin, num, TABLENAME);
    }

    public List<Exam> selectPageByLhubid(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByLhubidPKs(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubidPKs(lhubid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByLhubidPKs(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByExamtypeid(final Integer examtypeid) {
        return countByExamtypeid(examtypeid, TABLENAME);
    }

    public int countByExamtypeid(final Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectByExamtypeid(final Integer examtypeid) {
        return selectByExamtypeid(examtypeid, TABLENAME);
    }

    public List<Exam> selectByExamtypeid(final Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id ");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExamtypeidPKs(final Integer examtypeid) {
        return selectByExamtypeidPKs(examtypeid, TABLENAME);
    }

    public List<Integer> selectByExamtypeidPKs(final Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id ");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectPageByExamtypeid(final Integer examtypeid, final int begin, final int num) {
        return selectPageByExamtypeid(examtypeid, begin, num, TABLENAME);
    }

    public List<Exam> selectPageByExamtypeid(final Integer examtypeid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExamtypeidPKs(final Integer examtypeid, final int begin, final int num) {
        return selectPageByExamtypeidPKs(examtypeid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExamtypeidPKs(final Integer examtypeid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Exam selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Exam selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByPro0etpid(final Integer pro0etpid) {
        return countByPro0etpid(pro0etpid, TABLENAME);
    }

    public int countByPro0etpid(final Integer pro0etpid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE pro0etpid = :pro0etpid ");
            Map params = newMap();
            params.put("pro0etpid", pro0etpid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectByPro0etpid(final Integer pro0etpid) {
        return selectByPro0etpid(pro0etpid, TABLENAME);
    }

    public List<Exam> selectByPro0etpid(final Integer pro0etpid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE pro0etpid = :pro0etpid ORDER BY id ");
            Map params = newMap();
            params.put("pro0etpid", pro0etpid);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPro0etpidPKs(final Integer pro0etpid) {
        return selectByPro0etpidPKs(pro0etpid, TABLENAME);
    }

    public List<Integer> selectByPro0etpidPKs(final Integer pro0etpid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pro0etpid = :pro0etpid ORDER BY id ");
            Map params = newMap();
            params.put("pro0etpid", pro0etpid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectPageByPro0etpid(final Integer pro0etpid, final int begin, final int num) {
        return selectPageByPro0etpid(pro0etpid, begin, num, TABLENAME);
    }

    public List<Exam> selectPageByPro0etpid(final Integer pro0etpid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" WHERE pro0etpid = :pro0etpid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pro0etpid", pro0etpid);
            return super.queryForList(sql.toString(), params, Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByPro0etpidPKs(final Integer pro0etpid, final int begin, final int num) {
        return selectPageByPro0etpidPKs(pro0etpid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByPro0etpidPKs(final Integer pro0etpid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pro0etpid = :pro0etpid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pro0etpid", pro0etpid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int count() {
        return count(TABLENAME);
    }

    public int count(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append("");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exam> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Exam> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPagePKs(final int begin, final int num) {
        return selectByPagePKs(begin, num, TABLENAME);
    }

    public List<Integer> selectByPagePKs(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Exam exam) {
        return updateByKey(exam, TABLENAME);
    }

    public int updateByKey(final Exam exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = exam.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), exam);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Exam exam) {
        return asyncUpdate(exam, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Exam exam, final String TABLENAME2) {
        try {

            String _ustr = exam.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, exam);
                    } catch (Exception e) {
                        log.error(e2s(e));
                        return 0;
                    } finally {
                        decrementAndGet();
                    }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int updateExamtypeidByKey(final int examtypeid, final int id){
        return updateExamtypeidByKey(examtypeid, id, TABLENAME);
    }

    public int updateExamtypeidByKey(final int examtypeid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid=examtypeid+:examtypeid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMinByKey(final int id, final int examtypeid, final int _min){
        return updateExamtypeidWithMinByKey(id, examtypeid, _min, TABLENAME);
    }

    public int updateExamtypeidWithMinByKey(final int id, final int examtypeid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid<=:_min then :_min else examtypeid+:examtypeid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMinInKeys(final List<Integer> keys, final int examtypeid, final int _min){
        return updateExamtypeidWithMinInKeys(keys, examtypeid, _min, TABLENAME);
    }

    public int updateExamtypeidWithMinInKeys(final List<Integer> keys, final int examtypeid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid<=:_min then :_min else examtypeid+:examtypeid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMaxByKey(final int id, final int examtypeid, final int _max){
        return updateExamtypeidWithMaxByKey(id, examtypeid, _max, TABLENAME);
    }

    public int updateExamtypeidWithMaxByKey(final int id, final int examtypeid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid>=:_max then :_max else examtypeid+:examtypeid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMaxInKeys(final List<Integer> keys, final int examtypeid, final int _max){
        return updateExamtypeidWithMaxInKeys(keys, examtypeid, _max, TABLENAME);
    }

    public int updateExamtypeidWithMaxInKeys(final List<Integer> keys, final int examtypeid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid>=:_max then :_max else examtypeid+:examtypeid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMinMaxByKey(final int id, final int examtypeid, final int _min, final int _max){
        return updateExamtypeidWithMinMaxByKey(id, examtypeid, _min, _max, TABLENAME);
    }

    public int updateExamtypeidWithMinMaxByKey(final int id, final int examtypeid, final int _min, final int _max, final String TABLENAME2){
        if( examtypeid < 0 ) {
            return updateExamtypeidWithMinByKey(id, examtypeid, _min, TABLENAME2);
        } else {
            return updateExamtypeidWithMaxByKey(id, examtypeid, _max, TABLENAME2);
        }
    }

    public int updateExamtypeidWithMinMaxInKeys(final List<Integer> keys, final int examtypeid, final int _min, final int _max){
        return updateExamtypeidWithMinMaxInKeys(keys, examtypeid, _min, _max, TABLENAME);
    }

    public int updateExamtypeidWithMinMaxInKeys(final List<Integer> keys, final int examtypeid, final int _min, final int _max, final String TABLENAME2){
        if( examtypeid < 0 ) {
            return updateExamtypeidWithMinInKeys(keys, examtypeid, _min, TABLENAME2);
        } else {
            return updateExamtypeidWithMaxInKeys(keys, examtypeid, _max, TABLENAME2);
        }
    }

    public int updateScoreByKey(final int score, final int id){
        return updateScoreByKey(score, id, TABLENAME);
    }

    public int updateScoreByKey(final int score, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score=score+:score WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("score", score);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScoreWithMinByKey(final int id, final int score, final int _min){
        return updateScoreWithMinByKey(id, score, _min, TABLENAME);
    }

    public int updateScoreWithMinByKey(final int id, final int score, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score = (select case when score+:score<=:_min then :_min else score+:score end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("score", score);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScoreWithMinInKeys(final List<Integer> keys, final int score, final int _min){
        return updateScoreWithMinInKeys(keys, score, _min, TABLENAME);
    }

    public int updateScoreWithMinInKeys(final List<Integer> keys, final int score, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score = (select case when score+:score<=:_min then :_min else score+:score end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("score", score);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScoreWithMaxByKey(final int id, final int score, final int _max){
        return updateScoreWithMaxByKey(id, score, _max, TABLENAME);
    }

    public int updateScoreWithMaxByKey(final int id, final int score, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score = (select case when score+:score>=:_max then :_max else score+:score end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("score", score);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScoreWithMaxInKeys(final List<Integer> keys, final int score, final int _max){
        return updateScoreWithMaxInKeys(keys, score, _max, TABLENAME);
    }

    public int updateScoreWithMaxInKeys(final List<Integer> keys, final int score, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score = (select case when score+:score>=:_max then :_max else score+:score end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("score", score);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScoreWithMinMaxByKey(final int id, final int score, final int _min, final int _max){
        return updateScoreWithMinMaxByKey(id, score, _min, _max, TABLENAME);
    }

    public int updateScoreWithMinMaxByKey(final int id, final int score, final int _min, final int _max, final String TABLENAME2){
        if( score < 0 ) {
            return updateScoreWithMinByKey(id, score, _min, TABLENAME2);
        } else {
            return updateScoreWithMaxByKey(id, score, _max, TABLENAME2);
        }
    }

    public int updateScoreWithMinMaxInKeys(final List<Integer> keys, final int score, final int _min, final int _max){
        return updateScoreWithMinMaxInKeys(keys, score, _min, _max, TABLENAME);
    }

    public int updateScoreWithMinMaxInKeys(final List<Integer> keys, final int score, final int _min, final int _max, final String TABLENAME2){
        if( score < 0 ) {
            return updateScoreWithMinInKeys(keys, score, _min, TABLENAME2);
        } else {
            return updateScoreWithMaxInKeys(keys, score, _max, TABLENAME2);
        }
    }

    public int updateLhubidByKey(final int lhubid, final int id){
        return updateLhubidByKey(lhubid, id, TABLENAME);
    }

    public int updateLhubidByKey(final int lhubid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid=lhubid+:lhubid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMinByKey(final int id, final int lhubid, final int _min){
        return updateLhubidWithMinByKey(id, lhubid, _min, TABLENAME);
    }

    public int updateLhubidWithMinByKey(final int id, final int lhubid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid<=:_min then :_min else lhubid+:lhubid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMinInKeys(final List<Integer> keys, final int lhubid, final int _min){
        return updateLhubidWithMinInKeys(keys, lhubid, _min, TABLENAME);
    }

    public int updateLhubidWithMinInKeys(final List<Integer> keys, final int lhubid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid<=:_min then :_min else lhubid+:lhubid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMaxByKey(final int id, final int lhubid, final int _max){
        return updateLhubidWithMaxByKey(id, lhubid, _max, TABLENAME);
    }

    public int updateLhubidWithMaxByKey(final int id, final int lhubid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid>=:_max then :_max else lhubid+:lhubid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMaxInKeys(final List<Integer> keys, final int lhubid, final int _max){
        return updateLhubidWithMaxInKeys(keys, lhubid, _max, TABLENAME);
    }

    public int updateLhubidWithMaxInKeys(final List<Integer> keys, final int lhubid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid>=:_max then :_max else lhubid+:lhubid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMinMaxByKey(final int id, final int lhubid, final int _min, final int _max){
        return updateLhubidWithMinMaxByKey(id, lhubid, _min, _max, TABLENAME);
    }

    public int updateLhubidWithMinMaxByKey(final int id, final int lhubid, final int _min, final int _max, final String TABLENAME2){
        if( lhubid < 0 ) {
            return updateLhubidWithMinByKey(id, lhubid, _min, TABLENAME2);
        } else {
            return updateLhubidWithMaxByKey(id, lhubid, _max, TABLENAME2);
        }
    }

    public int updateLhubidWithMinMaxInKeys(final List<Integer> keys, final int lhubid, final int _min, final int _max){
        return updateLhubidWithMinMaxInKeys(keys, lhubid, _min, _max, TABLENAME);
    }

    public int updateLhubidWithMinMaxInKeys(final List<Integer> keys, final int lhubid, final int _min, final int _max, final String TABLENAME2){
        if( lhubid < 0 ) {
            return updateLhubidWithMinInKeys(keys, lhubid, _min, TABLENAME2);
        } else {
            return updateLhubidWithMaxInKeys(keys, lhubid, _max, TABLENAME2);
        }
    }

    public int updateExamtimeByKey(final int examtime, final int id){
        return updateExamtimeByKey(examtime, id, TABLENAME);
    }

    public int updateExamtimeByKey(final int examtime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtime=examtime+:examtime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("examtime", examtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtimeWithMinByKey(final int id, final int examtime, final int _min){
        return updateExamtimeWithMinByKey(id, examtime, _min, TABLENAME);
    }

    public int updateExamtimeWithMinByKey(final int id, final int examtime, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtime = (select case when examtime+:examtime<=:_min then :_min else examtime+:examtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("examtime", examtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtimeWithMinInKeys(final List<Integer> keys, final int examtime, final int _min){
        return updateExamtimeWithMinInKeys(keys, examtime, _min, TABLENAME);
    }

    public int updateExamtimeWithMinInKeys(final List<Integer> keys, final int examtime, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtime = (select case when examtime+:examtime<=:_min then :_min else examtime+:examtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examtime", examtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtimeWithMaxByKey(final int id, final int examtime, final int _max){
        return updateExamtimeWithMaxByKey(id, examtime, _max, TABLENAME);
    }

    public int updateExamtimeWithMaxByKey(final int id, final int examtime, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtime = (select case when examtime+:examtime>=:_max then :_max else examtime+:examtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("examtime", examtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtimeWithMaxInKeys(final List<Integer> keys, final int examtime, final int _max){
        return updateExamtimeWithMaxInKeys(keys, examtime, _max, TABLENAME);
    }

    public int updateExamtimeWithMaxInKeys(final List<Integer> keys, final int examtime, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtime = (select case when examtime+:examtime>=:_max then :_max else examtime+:examtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examtime", examtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtimeWithMinMaxByKey(final int id, final int examtime, final int _min, final int _max){
        return updateExamtimeWithMinMaxByKey(id, examtime, _min, _max, TABLENAME);
    }

    public int updateExamtimeWithMinMaxByKey(final int id, final int examtime, final int _min, final int _max, final String TABLENAME2){
        if( examtime < 0 ) {
            return updateExamtimeWithMinByKey(id, examtime, _min, TABLENAME2);
        } else {
            return updateExamtimeWithMaxByKey(id, examtime, _max, TABLENAME2);
        }
    }

    public int updateExamtimeWithMinMaxInKeys(final List<Integer> keys, final int examtime, final int _min, final int _max){
        return updateExamtimeWithMinMaxInKeys(keys, examtime, _min, _max, TABLENAME);
    }

    public int updateExamtimeWithMinMaxInKeys(final List<Integer> keys, final int examtime, final int _min, final int _max, final String TABLENAME2){
        if( examtime < 0 ) {
            return updateExamtimeWithMinInKeys(keys, examtime, _min, TABLENAME2);
        } else {
            return updateExamtimeWithMaxInKeys(keys, examtime, _max, TABLENAME2);
        }
    }

    public int updateStatusByKey(final int status, final int id){
        return updateStatusByKey(status, id, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int id, final int status, final int _min){
        return updateStatusWithMinByKey(id, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int id, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinInKeys(final List<Integer> keys, final int status, final int _min){
        return updateStatusWithMinInKeys(keys, status, _min, TABLENAME);
    }

    public int updateStatusWithMinInKeys(final List<Integer> keys, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMaxByKey(final int id, final int status, final int _max){
        return updateStatusWithMaxByKey(id, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int id, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMaxInKeys(final List<Integer> keys, final int status, final int _max){
        return updateStatusWithMaxInKeys(keys, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxInKeys(final List<Integer> keys, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinMaxByKey(final int id, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(id, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int id, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(id, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(id, status, _max, TABLENAME2);
        }
    }

    public int updateStatusWithMinMaxInKeys(final List<Integer> keys, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxInKeys(keys, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxInKeys(final List<Integer> keys, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinInKeys(keys, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxInKeys(keys, status, _max, TABLENAME2);
        }
    }

    public int updateProductidByKey(final int productid, final int id){
        return updateProductidByKey(productid, id, TABLENAME);
    }

    public int updateProductidByKey(final int productid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid=productid+:productid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinByKey(final int id, final int productid, final int _min){
        return updateProductidWithMinByKey(id, productid, _min, TABLENAME);
    }

    public int updateProductidWithMinByKey(final int id, final int productid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid<=:_min then :_min else productid+:productid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinInKeys(final List<Integer> keys, final int productid, final int _min){
        return updateProductidWithMinInKeys(keys, productid, _min, TABLENAME);
    }

    public int updateProductidWithMinInKeys(final List<Integer> keys, final int productid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid<=:_min then :_min else productid+:productid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMaxByKey(final int id, final int productid, final int _max){
        return updateProductidWithMaxByKey(id, productid, _max, TABLENAME);
    }

    public int updateProductidWithMaxByKey(final int id, final int productid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid>=:_max then :_max else productid+:productid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMaxInKeys(final List<Integer> keys, final int productid, final int _max){
        return updateProductidWithMaxInKeys(keys, productid, _max, TABLENAME);
    }

    public int updateProductidWithMaxInKeys(final List<Integer> keys, final int productid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid>=:_max then :_max else productid+:productid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinMaxByKey(final int id, final int productid, final int _min, final int _max){
        return updateProductidWithMinMaxByKey(id, productid, _min, _max, TABLENAME);
    }

    public int updateProductidWithMinMaxByKey(final int id, final int productid, final int _min, final int _max, final String TABLENAME2){
        if( productid < 0 ) {
            return updateProductidWithMinByKey(id, productid, _min, TABLENAME2);
        } else {
            return updateProductidWithMaxByKey(id, productid, _max, TABLENAME2);
        }
    }

    public int updateProductidWithMinMaxInKeys(final List<Integer> keys, final int productid, final int _min, final int _max){
        return updateProductidWithMinMaxInKeys(keys, productid, _min, _max, TABLENAME);
    }

    public int updateProductidWithMinMaxInKeys(final List<Integer> keys, final int productid, final int _min, final int _max, final String TABLENAME2){
        if( productid < 0 ) {
            return updateProductidWithMinInKeys(keys, productid, _min, TABLENAME2);
        } else {
            return updateProductidWithMaxInKeys(keys, productid, _max, TABLENAME2);
        }
    }

    public int updatePro0etpidByKey(final int pro0etpid, final int id){
        return updatePro0etpidByKey(pro0etpid, id, TABLENAME);
    }

    public int updatePro0etpidByKey(final int pro0etpid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pro0etpid=pro0etpid+:pro0etpid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pro0etpid", pro0etpid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePro0etpidWithMinByKey(final int id, final int pro0etpid, final int _min){
        return updatePro0etpidWithMinByKey(id, pro0etpid, _min, TABLENAME);
    }

    public int updatePro0etpidWithMinByKey(final int id, final int pro0etpid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pro0etpid = (select case when pro0etpid+:pro0etpid<=:_min then :_min else pro0etpid+:pro0etpid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pro0etpid", pro0etpid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePro0etpidWithMinInKeys(final List<Integer> keys, final int pro0etpid, final int _min){
        return updatePro0etpidWithMinInKeys(keys, pro0etpid, _min, TABLENAME);
    }

    public int updatePro0etpidWithMinInKeys(final List<Integer> keys, final int pro0etpid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pro0etpid = (select case when pro0etpid+:pro0etpid<=:_min then :_min else pro0etpid+:pro0etpid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pro0etpid", pro0etpid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePro0etpidWithMaxByKey(final int id, final int pro0etpid, final int _max){
        return updatePro0etpidWithMaxByKey(id, pro0etpid, _max, TABLENAME);
    }

    public int updatePro0etpidWithMaxByKey(final int id, final int pro0etpid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pro0etpid = (select case when pro0etpid+:pro0etpid>=:_max then :_max else pro0etpid+:pro0etpid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pro0etpid", pro0etpid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePro0etpidWithMaxInKeys(final List<Integer> keys, final int pro0etpid, final int _max){
        return updatePro0etpidWithMaxInKeys(keys, pro0etpid, _max, TABLENAME);
    }

    public int updatePro0etpidWithMaxInKeys(final List<Integer> keys, final int pro0etpid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pro0etpid = (select case when pro0etpid+:pro0etpid>=:_max then :_max else pro0etpid+:pro0etpid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pro0etpid", pro0etpid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePro0etpidWithMinMaxByKey(final int id, final int pro0etpid, final int _min, final int _max){
        return updatePro0etpidWithMinMaxByKey(id, pro0etpid, _min, _max, TABLENAME);
    }

    public int updatePro0etpidWithMinMaxByKey(final int id, final int pro0etpid, final int _min, final int _max, final String TABLENAME2){
        if( pro0etpid < 0 ) {
            return updatePro0etpidWithMinByKey(id, pro0etpid, _min, TABLENAME2);
        } else {
            return updatePro0etpidWithMaxByKey(id, pro0etpid, _max, TABLENAME2);
        }
    }

    public int updatePro0etpidWithMinMaxInKeys(final List<Integer> keys, final int pro0etpid, final int _min, final int _max){
        return updatePro0etpidWithMinMaxInKeys(keys, pro0etpid, _min, _max, TABLENAME);
    }

    public int updatePro0etpidWithMinMaxInKeys(final List<Integer> keys, final int pro0etpid, final int _min, final int _max, final String TABLENAME2){
        if( pro0etpid < 0 ) {
            return updatePro0etpidWithMinInKeys(keys, pro0etpid, _min, TABLENAME2);
        } else {
            return updatePro0etpidWithMaxInKeys(keys, pro0etpid, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Exam> exams) {
        return updateByKey(exams, TABLENAME);
    }

    public int[] updateByKey (final List<Exam> exams, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(exams == null || exams.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid=:examtypeid, name=:name, score=:score, lhubid=:lhubid, examtime=:examtime, status=:status, createtime=:createtime, productid=:productid, pro0etpid=:pro0etpid, descstr=:descstr WHERE id=:id");
            return super.batchUpdate2(sql.toString(), exams);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void createTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`examtypeid`  INT(11) NOT NULL," +
                "	`name`  VARCHAR(128) NOT NULL," +
                "	`score`  INT(11) NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`examtime`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`productid`  INT(11) NOT NULL," +
                "	`pro0etpid`  INT(11) NOT NULL," +
                "	`descstr`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `${TABLENAME}typeid` (`examtypeid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `pro0etpid` (`pro0etpid`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`examtypeid`  INT(11) NOT NULL," +
                "	`name`  VARCHAR(128) NOT NULL," +
                "	`score`  INT(11) NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`examtime`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`productid`  INT(11) NOT NULL," +
                "	`pro0etpid`  INT(11) NOT NULL," +
                "	`descstr`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `${TABLENAME}typeid` (`examtypeid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `pro0etpid` (`pro0etpid`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void truncate(){
        try {
            super.truncate(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void repair(){
        try {
            super.repair(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void optimize(){
        try {
            super.optimize(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void dropTable(){
        try {
            super.dropTable(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

}
