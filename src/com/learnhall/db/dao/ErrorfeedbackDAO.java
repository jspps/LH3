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

//learnhall3_design - errorfeedback
@SuppressWarnings({"rawtypes", "unchecked"})
public class ErrorfeedbackDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ErrorfeedbackDAO.class);

    public static final String TABLE = "errorfeedback";
    public static String TABLENAME = "errorfeedback";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "examid", "nmExam", "optid", "opttype", "optgid", "customerid", "nmCust", "description", "lhubid", "status", "createtime"};
    public static String coulmns = "id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime";
    public static String coulmns2 = "examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime";

    public ErrorfeedbackDAO(Connection conn) {
        super(conn);
    }

    public ErrorfeedbackDAO(DataSource ds) {
        super(ds);
    }

    public ErrorfeedbackDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Errorfeedback errorfeedback) {
        return insert(errorfeedback, TABLENAME);
    }

    public int insert(final Errorfeedback errorfeedback, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            errorfeedback.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime) VALUES (:examid, :nmExam, :optid, :opttype, :optgid, :customerid, :nmCust, :description, :lhubid, :status, :createtime)");
            Map map = super.insert(sql.toString(), errorfeedback);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Errorfeedback errorfeedback) {
        return asyncInsert(errorfeedback, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Errorfeedback errorfeedback, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(errorfeedback, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Errorfeedback errorfeedback) {
        return asyncInsert2(errorfeedback, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Errorfeedback errorfeedback, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(errorfeedback, TABLENAME2);
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

    public int insert2(final Errorfeedback errorfeedback) {
        return insert2(errorfeedback, TABLENAME);
    }

    public int insert2(final Errorfeedback errorfeedback, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            errorfeedback.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime) VALUES (:id, :examid, :nmExam, :optid, :opttype, :optgid, :customerid, :nmCust, :description, :lhubid, :status, :createtime)");
            Map map = super.insert(sql.toString(), errorfeedback);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Errorfeedback> errorfeedbacks) {
        return insert(errorfeedbacks, TABLENAME);
    }

    public int[] insert(final List<Errorfeedback> errorfeedbacks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(errorfeedbacks == null || errorfeedbacks.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime) VALUES (:examid, :nmExam, :optid, :opttype, :optgid, :customerid, :nmCust, :description, :lhubid, :status, :createtime)");
            return super.batchInsert(sql.toString(), errorfeedbacks);
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

    public int deleteInBeans(final List<Errorfeedback> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Errorfeedback> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Errorfeedback errorfeedback = beans.get(i);
                int id = errorfeedback.id;
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

    public List<Errorfeedback> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Errorfeedback> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Errorfeedback.class);
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
            sql.append("SELECT id, examid, optid, customerid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Errorfeedback> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Errorfeedback> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
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

    public List<Errorfeedback> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Errorfeedback> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Errorfeedback.class);
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

    public Errorfeedback last() {
        return last(TABLENAME);
    }

    public Errorfeedback last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Errorfeedback.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Errorfeedback> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Errorfeedback> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
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

    public Errorfeedback selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Errorfeedback selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Errorfeedback.class);
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

    public List<Errorfeedback> selectByLhubid(final Integer lhubid) {
        return selectByLhubid(lhubid, TABLENAME);
    }

    public List<Errorfeedback> selectByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
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

    public List<Errorfeedback> selectPageByLhubid(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubid(lhubid, begin, num, TABLENAME);
    }

    public List<Errorfeedback> selectPageByLhubid(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
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

    public int countByOptid(final Integer optid) {
        return countByOptid(optid, TABLENAME);
    }

    public int countByOptid(final Integer optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE optid = :optid ");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectByOptid(final Integer optid) {
        return selectByOptid(optid, TABLENAME);
    }

    public List<Errorfeedback> selectByOptid(final Integer optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE optid = :optid ORDER BY id ");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByOptidPKs(final Integer optid) {
        return selectByOptidPKs(optid, TABLENAME);
    }

    public List<Integer> selectByOptidPKs(final Integer optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE optid = :optid ORDER BY id ");
            Map params = newMap();
            params.put("optid", optid);
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

    public List<Errorfeedback> selectPageByOptid(final Integer optid, final int begin, final int num) {
        return selectPageByOptid(optid, begin, num, TABLENAME);
    }

    public List<Errorfeedback> selectPageByOptid(final Integer optid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE optid = :optid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByOptidPKs(final Integer optid, final int begin, final int num) {
        return selectPageByOptidPKs(optid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByOptidPKs(final Integer optid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE optid = :optid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("optid", optid);
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

    public int countByCustomerid(final Integer customerid) {
        return countByCustomerid(customerid, TABLENAME);
    }

    public int countByCustomerid(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectByCustomerid(final Integer customerid) {
        return selectByCustomerid(customerid, TABLENAME);
    }

    public List<Errorfeedback> selectByCustomerid(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id ");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCustomeridPKs(final Integer customerid) {
        return selectByCustomeridPKs(customerid, TABLENAME);
    }

    public List<Integer> selectByCustomeridPKs(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id ");
            Map params = newMap();
            params.put("customerid", customerid);
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

    public List<Errorfeedback> selectPageByCustomerid(final Integer customerid, final int begin, final int num) {
        return selectPageByCustomerid(customerid, begin, num, TABLENAME);
    }

    public List<Errorfeedback> selectPageByCustomerid(final Integer customerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCustomeridPKs(final Integer customerid, final int begin, final int num) {
        return selectPageByCustomeridPKs(customerid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCustomeridPKs(final Integer customerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customerid", customerid);
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

    public Errorfeedback selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Errorfeedback selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByExamid(final Integer examid) {
        return countByExamid(examid, TABLENAME);
    }

    public int countByExamid(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE examid = :examid ");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Errorfeedback> selectByExamid(final Integer examid) {
        return selectByExamid(examid, TABLENAME);
    }

    public List<Errorfeedback> selectByExamid(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id ");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExamidPKs(final Integer examid) {
        return selectByExamidPKs(examid, TABLENAME);
    }

    public List<Integer> selectByExamidPKs(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id ");
            Map params = newMap();
            params.put("examid", examid);
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

    public List<Errorfeedback> selectPageByExamid(final Integer examid, final int begin, final int num) {
        return selectPageByExamid(examid, begin, num, TABLENAME);
    }

    public List<Errorfeedback> selectPageByExamid(final Integer examid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Errorfeedback.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExamidPKs(final Integer examid, final int begin, final int num) {
        return selectPageByExamidPKs(examid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExamidPKs(final Integer examid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
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

    public List<Errorfeedback> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Errorfeedback> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Errorfeedback.class);
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

    public int updateByKey(final Errorfeedback errorfeedback) {
        return updateByKey(errorfeedback, TABLENAME);
    }

    public int updateByKey(final Errorfeedback errorfeedback, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = errorfeedback.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), errorfeedback);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Errorfeedback errorfeedback) {
        return asyncUpdate(errorfeedback, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Errorfeedback errorfeedback, final String TABLENAME2) {
        try {

            String _ustr = errorfeedback.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, errorfeedback);
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

    public int updateExamidByKey(final int examid, final int id){
        return updateExamidByKey(examid, id, TABLENAME);
    }

    public int updateExamidByKey(final int examid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid=examid+:examid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMinByKey(final int id, final int examid, final int _min){
        return updateExamidWithMinByKey(id, examid, _min, TABLENAME);
    }

    public int updateExamidWithMinByKey(final int id, final int examid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid<=:_min then :_min else examid+:examid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMinInKeys(final List<Integer> keys, final int examid, final int _min){
        return updateExamidWithMinInKeys(keys, examid, _min, TABLENAME);
    }

    public int updateExamidWithMinInKeys(final List<Integer> keys, final int examid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid<=:_min then :_min else examid+:examid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMaxByKey(final int id, final int examid, final int _max){
        return updateExamidWithMaxByKey(id, examid, _max, TABLENAME);
    }

    public int updateExamidWithMaxByKey(final int id, final int examid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid>=:_max then :_max else examid+:examid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMaxInKeys(final List<Integer> keys, final int examid, final int _max){
        return updateExamidWithMaxInKeys(keys, examid, _max, TABLENAME);
    }

    public int updateExamidWithMaxInKeys(final List<Integer> keys, final int examid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid>=:_max then :_max else examid+:examid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMinMaxByKey(final int id, final int examid, final int _min, final int _max){
        return updateExamidWithMinMaxByKey(id, examid, _min, _max, TABLENAME);
    }

    public int updateExamidWithMinMaxByKey(final int id, final int examid, final int _min, final int _max, final String TABLENAME2){
        if( examid < 0 ) {
            return updateExamidWithMinByKey(id, examid, _min, TABLENAME2);
        } else {
            return updateExamidWithMaxByKey(id, examid, _max, TABLENAME2);
        }
    }

    public int updateExamidWithMinMaxInKeys(final List<Integer> keys, final int examid, final int _min, final int _max){
        return updateExamidWithMinMaxInKeys(keys, examid, _min, _max, TABLENAME);
    }

    public int updateExamidWithMinMaxInKeys(final List<Integer> keys, final int examid, final int _min, final int _max, final String TABLENAME2){
        if( examid < 0 ) {
            return updateExamidWithMinInKeys(keys, examid, _min, TABLENAME2);
        } else {
            return updateExamidWithMaxInKeys(keys, examid, _max, TABLENAME2);
        }
    }

    public int updateOptidByKey(final int optid, final int id){
        return updateOptidByKey(optid, id, TABLENAME);
    }

    public int updateOptidByKey(final int optid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optid=optid+:optid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("optid", optid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptidWithMinByKey(final int id, final int optid, final int _min){
        return updateOptidWithMinByKey(id, optid, _min, TABLENAME);
    }

    public int updateOptidWithMinByKey(final int id, final int optid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optid = (select case when optid+:optid<=:_min then :_min else optid+:optid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("optid", optid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptidWithMinInKeys(final List<Integer> keys, final int optid, final int _min){
        return updateOptidWithMinInKeys(keys, optid, _min, TABLENAME);
    }

    public int updateOptidWithMinInKeys(final List<Integer> keys, final int optid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optid = (select case when optid+:optid<=:_min then :_min else optid+:optid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("optid", optid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptidWithMaxByKey(final int id, final int optid, final int _max){
        return updateOptidWithMaxByKey(id, optid, _max, TABLENAME);
    }

    public int updateOptidWithMaxByKey(final int id, final int optid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optid = (select case when optid+:optid>=:_max then :_max else optid+:optid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("optid", optid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptidWithMaxInKeys(final List<Integer> keys, final int optid, final int _max){
        return updateOptidWithMaxInKeys(keys, optid, _max, TABLENAME);
    }

    public int updateOptidWithMaxInKeys(final List<Integer> keys, final int optid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optid = (select case when optid+:optid>=:_max then :_max else optid+:optid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("optid", optid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptidWithMinMaxByKey(final int id, final int optid, final int _min, final int _max){
        return updateOptidWithMinMaxByKey(id, optid, _min, _max, TABLENAME);
    }

    public int updateOptidWithMinMaxByKey(final int id, final int optid, final int _min, final int _max, final String TABLENAME2){
        if( optid < 0 ) {
            return updateOptidWithMinByKey(id, optid, _min, TABLENAME2);
        } else {
            return updateOptidWithMaxByKey(id, optid, _max, TABLENAME2);
        }
    }

    public int updateOptidWithMinMaxInKeys(final List<Integer> keys, final int optid, final int _min, final int _max){
        return updateOptidWithMinMaxInKeys(keys, optid, _min, _max, TABLENAME);
    }

    public int updateOptidWithMinMaxInKeys(final List<Integer> keys, final int optid, final int _min, final int _max, final String TABLENAME2){
        if( optid < 0 ) {
            return updateOptidWithMinInKeys(keys, optid, _min, TABLENAME2);
        } else {
            return updateOptidWithMaxInKeys(keys, optid, _max, TABLENAME2);
        }
    }

    public int updateOpttypeByKey(final int opttype, final int id){
        return updateOpttypeByKey(opttype, id, TABLENAME);
    }

    public int updateOpttypeByKey(final int opttype, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET opttype=opttype+:opttype WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("opttype", opttype);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOpttypeWithMinByKey(final int id, final int opttype, final int _min){
        return updateOpttypeWithMinByKey(id, opttype, _min, TABLENAME);
    }

    public int updateOpttypeWithMinByKey(final int id, final int opttype, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET opttype = (select case when opttype+:opttype<=:_min then :_min else opttype+:opttype end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("opttype", opttype);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOpttypeWithMinInKeys(final List<Integer> keys, final int opttype, final int _min){
        return updateOpttypeWithMinInKeys(keys, opttype, _min, TABLENAME);
    }

    public int updateOpttypeWithMinInKeys(final List<Integer> keys, final int opttype, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET opttype = (select case when opttype+:opttype<=:_min then :_min else opttype+:opttype end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("opttype", opttype);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOpttypeWithMaxByKey(final int id, final int opttype, final int _max){
        return updateOpttypeWithMaxByKey(id, opttype, _max, TABLENAME);
    }

    public int updateOpttypeWithMaxByKey(final int id, final int opttype, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET opttype = (select case when opttype+:opttype>=:_max then :_max else opttype+:opttype end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("opttype", opttype);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOpttypeWithMaxInKeys(final List<Integer> keys, final int opttype, final int _max){
        return updateOpttypeWithMaxInKeys(keys, opttype, _max, TABLENAME);
    }

    public int updateOpttypeWithMaxInKeys(final List<Integer> keys, final int opttype, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET opttype = (select case when opttype+:opttype>=:_max then :_max else opttype+:opttype end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("opttype", opttype);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOpttypeWithMinMaxByKey(final int id, final int opttype, final int _min, final int _max){
        return updateOpttypeWithMinMaxByKey(id, opttype, _min, _max, TABLENAME);
    }

    public int updateOpttypeWithMinMaxByKey(final int id, final int opttype, final int _min, final int _max, final String TABLENAME2){
        if( opttype < 0 ) {
            return updateOpttypeWithMinByKey(id, opttype, _min, TABLENAME2);
        } else {
            return updateOpttypeWithMaxByKey(id, opttype, _max, TABLENAME2);
        }
    }

    public int updateOpttypeWithMinMaxInKeys(final List<Integer> keys, final int opttype, final int _min, final int _max){
        return updateOpttypeWithMinMaxInKeys(keys, opttype, _min, _max, TABLENAME);
    }

    public int updateOpttypeWithMinMaxInKeys(final List<Integer> keys, final int opttype, final int _min, final int _max, final String TABLENAME2){
        if( opttype < 0 ) {
            return updateOpttypeWithMinInKeys(keys, opttype, _min, TABLENAME2);
        } else {
            return updateOpttypeWithMaxInKeys(keys, opttype, _max, TABLENAME2);
        }
    }

    public int updateOptgidByKey(final int optgid, final int id){
        return updateOptgidByKey(optgid, id, TABLENAME);
    }

    public int updateOptgidByKey(final int optgid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optgid=optgid+:optgid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("optgid", optgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptgidWithMinByKey(final int id, final int optgid, final int _min){
        return updateOptgidWithMinByKey(id, optgid, _min, TABLENAME);
    }

    public int updateOptgidWithMinByKey(final int id, final int optgid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optgid = (select case when optgid+:optgid<=:_min then :_min else optgid+:optgid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("optgid", optgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptgidWithMinInKeys(final List<Integer> keys, final int optgid, final int _min){
        return updateOptgidWithMinInKeys(keys, optgid, _min, TABLENAME);
    }

    public int updateOptgidWithMinInKeys(final List<Integer> keys, final int optgid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optgid = (select case when optgid+:optgid<=:_min then :_min else optgid+:optgid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("optgid", optgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptgidWithMaxByKey(final int id, final int optgid, final int _max){
        return updateOptgidWithMaxByKey(id, optgid, _max, TABLENAME);
    }

    public int updateOptgidWithMaxByKey(final int id, final int optgid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optgid = (select case when optgid+:optgid>=:_max then :_max else optgid+:optgid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("optgid", optgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptgidWithMaxInKeys(final List<Integer> keys, final int optgid, final int _max){
        return updateOptgidWithMaxInKeys(keys, optgid, _max, TABLENAME);
    }

    public int updateOptgidWithMaxInKeys(final List<Integer> keys, final int optgid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET optgid = (select case when optgid+:optgid>=:_max then :_max else optgid+:optgid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("optgid", optgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOptgidWithMinMaxByKey(final int id, final int optgid, final int _min, final int _max){
        return updateOptgidWithMinMaxByKey(id, optgid, _min, _max, TABLENAME);
    }

    public int updateOptgidWithMinMaxByKey(final int id, final int optgid, final int _min, final int _max, final String TABLENAME2){
        if( optgid < 0 ) {
            return updateOptgidWithMinByKey(id, optgid, _min, TABLENAME2);
        } else {
            return updateOptgidWithMaxByKey(id, optgid, _max, TABLENAME2);
        }
    }

    public int updateOptgidWithMinMaxInKeys(final List<Integer> keys, final int optgid, final int _min, final int _max){
        return updateOptgidWithMinMaxInKeys(keys, optgid, _min, _max, TABLENAME);
    }

    public int updateOptgidWithMinMaxInKeys(final List<Integer> keys, final int optgid, final int _min, final int _max, final String TABLENAME2){
        if( optgid < 0 ) {
            return updateOptgidWithMinInKeys(keys, optgid, _min, TABLENAME2);
        } else {
            return updateOptgidWithMaxInKeys(keys, optgid, _max, TABLENAME2);
        }
    }

    public int updateCustomeridByKey(final int customerid, final int id){
        return updateCustomeridByKey(customerid, id, TABLENAME);
    }

    public int updateCustomeridByKey(final int customerid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid=customerid+:customerid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMinByKey(final int id, final int customerid, final int _min){
        return updateCustomeridWithMinByKey(id, customerid, _min, TABLENAME);
    }

    public int updateCustomeridWithMinByKey(final int id, final int customerid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid<=:_min then :_min else customerid+:customerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMinInKeys(final List<Integer> keys, final int customerid, final int _min){
        return updateCustomeridWithMinInKeys(keys, customerid, _min, TABLENAME);
    }

    public int updateCustomeridWithMinInKeys(final List<Integer> keys, final int customerid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid<=:_min then :_min else customerid+:customerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMaxByKey(final int id, final int customerid, final int _max){
        return updateCustomeridWithMaxByKey(id, customerid, _max, TABLENAME);
    }

    public int updateCustomeridWithMaxByKey(final int id, final int customerid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid>=:_max then :_max else customerid+:customerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMaxInKeys(final List<Integer> keys, final int customerid, final int _max){
        return updateCustomeridWithMaxInKeys(keys, customerid, _max, TABLENAME);
    }

    public int updateCustomeridWithMaxInKeys(final List<Integer> keys, final int customerid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid>=:_max then :_max else customerid+:customerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMinMaxByKey(final int id, final int customerid, final int _min, final int _max){
        return updateCustomeridWithMinMaxByKey(id, customerid, _min, _max, TABLENAME);
    }

    public int updateCustomeridWithMinMaxByKey(final int id, final int customerid, final int _min, final int _max, final String TABLENAME2){
        if( customerid < 0 ) {
            return updateCustomeridWithMinByKey(id, customerid, _min, TABLENAME2);
        } else {
            return updateCustomeridWithMaxByKey(id, customerid, _max, TABLENAME2);
        }
    }

    public int updateCustomeridWithMinMaxInKeys(final List<Integer> keys, final int customerid, final int _min, final int _max){
        return updateCustomeridWithMinMaxInKeys(keys, customerid, _min, _max, TABLENAME);
    }

    public int updateCustomeridWithMinMaxInKeys(final List<Integer> keys, final int customerid, final int _min, final int _max, final String TABLENAME2){
        if( customerid < 0 ) {
            return updateCustomeridWithMinInKeys(keys, customerid, _min, TABLENAME2);
        } else {
            return updateCustomeridWithMaxInKeys(keys, customerid, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Errorfeedback> errorfeedbacks) {
        return updateByKey(errorfeedbacks, TABLENAME);
    }

    public int[] updateByKey (final List<Errorfeedback> errorfeedbacks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(errorfeedbacks == null || errorfeedbacks.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid=:examid, nmExam=:nmExam, optid=:optid, opttype=:opttype, optgid=:optgid, customerid=:customerid, nmCust=:nmCust, description=:description, lhubid=:lhubid, status=:status, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), errorfeedbacks);
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
                "	`examid`  INT(11) NOT NULL," +
                "	`nmExam`  TINYTEXT NOT NULL," +
                "	`optid`  INT(11) NOT NULL," +
                "	`opttype`  INT(11) NOT NULL," +
                "	`optgid`  INT(11) NOT NULL," +
                "	`customerid`  INT(11) NOT NULL," +
                "	`nmCust`  TINYTEXT NOT NULL," +
                "	`description`  TEXT NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `optquestionid` (`optid`)" +
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
                "	`examid`  INT(11) NOT NULL," +
                "	`nmExam`  TINYTEXT NOT NULL," +
                "	`optid`  INT(11) NOT NULL," +
                "	`opttype`  INT(11) NOT NULL," +
                "	`optgid`  INT(11) NOT NULL," +
                "	`customerid`  INT(11) NOT NULL," +
                "	`nmCust`  TINYTEXT NOT NULL," +
                "	`description`  TEXT NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `optquestionid` (`optid`)" +
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
