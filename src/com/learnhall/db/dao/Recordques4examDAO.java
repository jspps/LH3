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

//learnhall3_design - recordques4exam
@SuppressWarnings({"rawtypes", "unchecked"})
public class Recordques4examDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Recordques4examDAO.class);

    public static final String TABLE = "recordques4exam";
    public static String TABLENAME = "recordques4exam";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "customid", "questionid", "catalog4Exam", "numError", "createtime"};
    public static String coulmns = "id, customid, questionid, catalog4Exam, numError, createtime";
    public static String coulmns2 = "customid, questionid, catalog4Exam, numError, createtime";

    public Recordques4examDAO(Connection conn) {
        super(conn);
    }

    public Recordques4examDAO(DataSource ds) {
        super(ds);
    }

    public Recordques4examDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Recordques4exam recordques4exam) {
        return insert(recordques4exam, TABLENAME);
    }

    public int insert(final Recordques4exam recordques4exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            recordques4exam.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (customid, questionid, catalog4Exam, numError, createtime) VALUES (:customid, :questionid, :catalog4Exam, :numError, :createtime)");
            Map map = super.insert(sql.toString(), recordques4exam);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Recordques4exam recordques4exam) {
        return asyncInsert(recordques4exam, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Recordques4exam recordques4exam, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(recordques4exam, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Recordques4exam recordques4exam) {
        return asyncInsert2(recordques4exam, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Recordques4exam recordques4exam, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(recordques4exam, TABLENAME2);
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

    public int insert2(final Recordques4exam recordques4exam) {
        return insert2(recordques4exam, TABLENAME);
    }

    public int insert2(final Recordques4exam recordques4exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            recordques4exam.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, customid, questionid, catalog4Exam, numError, createtime) VALUES (:id, :customid, :questionid, :catalog4Exam, :numError, :createtime)");
            Map map = super.insert(sql.toString(), recordques4exam);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Recordques4exam> recordques4exams) {
        return insert(recordques4exams, TABLENAME);
    }

    public int[] insert(final List<Recordques4exam> recordques4exams, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(recordques4exams == null || recordques4exams.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (customid, questionid, catalog4Exam, numError, createtime) VALUES (:customid, :questionid, :catalog4Exam, :numError, :createtime)");
            return super.batchInsert(sql.toString(), recordques4exams);
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

    public int deleteInBeans(final List<Recordques4exam> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Recordques4exam> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Recordques4exam recordques4exam = beans.get(i);
                int id = recordques4exam.id;
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

    public List<Recordques4exam> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Recordques4exam> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Recordques4exam.class);
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
            sql.append("SELECT id, customid, questionid, catalog4Exam FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Recordques4exam> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Recordques4exam> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
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

    public List<Recordques4exam> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Recordques4exam> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Recordques4exam.class);
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

    public Recordques4exam last() {
        return last(TABLENAME);
    }

    public Recordques4exam last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Recordques4exam.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Recordques4exam> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Recordques4exam> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
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

    public Recordques4exam selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Recordques4exam selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recordques4exam.class);
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

    public Recordques4exam selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Recordques4exam selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByCustomid(final Integer customid) {
        return countByCustomid(customid, TABLENAME);
    }

    public int countByCustomid(final Integer customid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE customid = :customid ");
            Map params = newMap();
            params.put("customid", customid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectByCustomid(final Integer customid) {
        return selectByCustomid(customid, TABLENAME);
    }

    public List<Recordques4exam> selectByCustomid(final Integer customid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE customid = :customid ORDER BY id ");
            Map params = newMap();
            params.put("customid", customid);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCustomidPKs(final Integer customid) {
        return selectByCustomidPKs(customid, TABLENAME);
    }

    public List<Integer> selectByCustomidPKs(final Integer customid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customid = :customid ORDER BY id ");
            Map params = newMap();
            params.put("customid", customid);
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

    public List<Recordques4exam> selectPageByCustomid(final Integer customid, final int begin, final int num) {
        return selectPageByCustomid(customid, begin, num, TABLENAME);
    }

    public List<Recordques4exam> selectPageByCustomid(final Integer customid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE customid = :customid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customid", customid);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCustomidPKs(final Integer customid, final int begin, final int num) {
        return selectPageByCustomidPKs(customid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCustomidPKs(final Integer customid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customid = :customid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customid", customid);
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

    public int countByCustomidCatalog4Exam(final Integer customid, Integer catalog4Exam) {
        return  countByCustomidCatalog4Exam(customid, catalog4Exam, TABLENAME);
    }

    public int countByCustomidCatalog4Exam(final Integer customid, Integer catalog4Exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE customid=:customid AND catalog4Exam=:catalog4Exam ");
            Map params = newMap();
            params.put("customid", customid);
            params.put("catalog4Exam", catalog4Exam);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectByCustomidCatalog4Exam(final Integer customid, Integer catalog4Exam) {
        return selectByCustomidCatalog4Exam(customid, catalog4Exam, TABLENAME);
    }

    public List<Recordques4exam> selectByCustomidCatalog4Exam(final Integer customid, Integer catalog4Exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE customid=:customid AND catalog4Exam=:catalog4Exam ORDER BY id ");
            Map params = newMap();
            params.put("customid", customid);
            params.put("catalog4Exam", catalog4Exam);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCustomidCatalog4ExamPKs(final Integer customid, Integer catalog4Exam) {
        return selectByCustomidCatalog4ExamPKs(customid, catalog4Exam, TABLENAME);
    }

    public List<Integer> selectByCustomidCatalog4ExamPKs(final Integer customid, Integer catalog4Exam, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customid=:customid AND catalog4Exam=:catalog4Exam ORDER BY id ");
            Map params = newMap();
            params.put("customid", customid);
            params.put("catalog4Exam", catalog4Exam);
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

    public List<Recordques4exam> selectPageByCustomidCatalog4Exam(final Integer customid, Integer catalog4Exam, final int begin, final int num) {
        return selectPageByCustomidCatalog4Exam(customid, catalog4Exam, begin, num, TABLENAME);
    }

    public List<Recordques4exam> selectPageByCustomidCatalog4Exam(final Integer customid, Integer catalog4Exam, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE customid=:customid AND catalog4Exam=:catalog4Exam ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customid", customid);
            params.put("catalog4Exam", catalog4Exam);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCustomidCatalog4ExamPKs(final Integer customid, Integer catalog4Exam, final int begin, final int num) {
        return selectPageByCustomidCatalog4ExamPKs(customid, catalog4Exam, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCustomidCatalog4ExamPKs(final Integer customid, Integer catalog4Exam, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customid=:customid AND catalog4Exam=:catalog4Exam ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customid", customid);
            params.put("catalog4Exam", catalog4Exam);
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

    public int countByQuestionid(final Integer questionid) {
        return countByQuestionid(questionid, TABLENAME);
    }

    public int countByQuestionid(final Integer questionid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE questionid = :questionid ");
            Map params = newMap();
            params.put("questionid", questionid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordques4exam> selectByQuestionid(final Integer questionid) {
        return selectByQuestionid(questionid, TABLENAME);
    }

    public List<Recordques4exam> selectByQuestionid(final Integer questionid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE questionid = :questionid ORDER BY id ");
            Map params = newMap();
            params.put("questionid", questionid);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByQuestionidPKs(final Integer questionid) {
        return selectByQuestionidPKs(questionid, TABLENAME);
    }

    public List<Integer> selectByQuestionidPKs(final Integer questionid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE questionid = :questionid ORDER BY id ");
            Map params = newMap();
            params.put("questionid", questionid);
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

    public List<Recordques4exam> selectPageByQuestionid(final Integer questionid, final int begin, final int num) {
        return selectPageByQuestionid(questionid, begin, num, TABLENAME);
    }

    public List<Recordques4exam> selectPageByQuestionid(final Integer questionid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE questionid = :questionid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("questionid", questionid);
            return super.queryForList(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByQuestionidPKs(final Integer questionid, final int begin, final int num) {
        return selectPageByQuestionidPKs(questionid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByQuestionidPKs(final Integer questionid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE questionid = :questionid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("questionid", questionid);
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

    public Recordques4exam selectByCustomidQuestionid(final Integer customid, Integer questionid) {
        return selectByCustomidQuestionid(customid, questionid, TABLENAME);
    }

    public Recordques4exam selectByCustomidQuestionid(final Integer customid, Integer questionid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" WHERE customid=:customid AND questionid=:questionid");
            Map params = newMap();
            params.put("customid", customid);
            params.put("questionid", questionid);
            return super.queryForObject(sql.toString(), params, Recordques4exam.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
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

    public List<Recordques4exam> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Recordques4exam> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customid, questionid, catalog4Exam, numError, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Recordques4exam.class);
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

    public int updateByKey(final Recordques4exam recordques4exam) {
        return updateByKey(recordques4exam, TABLENAME);
    }

    public int updateByKey(final Recordques4exam recordques4exam, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = recordques4exam.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), recordques4exam);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Recordques4exam recordques4exam) {
        return asyncUpdate(recordques4exam, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Recordques4exam recordques4exam, final String TABLENAME2) {
        try {

            String _ustr = recordques4exam.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, recordques4exam);
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

    public int updateCustomidByKey(final int customid, final int id){
        return updateCustomidByKey(customid, id, TABLENAME);
    }

    public int updateCustomidByKey(final int customid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customid=customid+:customid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("customid", customid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomidWithMinByKey(final int id, final int customid, final int _min){
        return updateCustomidWithMinByKey(id, customid, _min, TABLENAME);
    }

    public int updateCustomidWithMinByKey(final int id, final int customid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customid = (select case when customid+:customid<=:_min then :_min else customid+:customid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("customid", customid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomidWithMinInKeys(final List<Integer> keys, final int customid, final int _min){
        return updateCustomidWithMinInKeys(keys, customid, _min, TABLENAME);
    }

    public int updateCustomidWithMinInKeys(final List<Integer> keys, final int customid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customid = (select case when customid+:customid<=:_min then :_min else customid+:customid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("customid", customid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomidWithMaxByKey(final int id, final int customid, final int _max){
        return updateCustomidWithMaxByKey(id, customid, _max, TABLENAME);
    }

    public int updateCustomidWithMaxByKey(final int id, final int customid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customid = (select case when customid+:customid>=:_max then :_max else customid+:customid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("customid", customid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomidWithMaxInKeys(final List<Integer> keys, final int customid, final int _max){
        return updateCustomidWithMaxInKeys(keys, customid, _max, TABLENAME);
    }

    public int updateCustomidWithMaxInKeys(final List<Integer> keys, final int customid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customid = (select case when customid+:customid>=:_max then :_max else customid+:customid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("customid", customid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomidWithMinMaxByKey(final int id, final int customid, final int _min, final int _max){
        return updateCustomidWithMinMaxByKey(id, customid, _min, _max, TABLENAME);
    }

    public int updateCustomidWithMinMaxByKey(final int id, final int customid, final int _min, final int _max, final String TABLENAME2){
        if( customid < 0 ) {
            return updateCustomidWithMinByKey(id, customid, _min, TABLENAME2);
        } else {
            return updateCustomidWithMaxByKey(id, customid, _max, TABLENAME2);
        }
    }

    public int updateCustomidWithMinMaxInKeys(final List<Integer> keys, final int customid, final int _min, final int _max){
        return updateCustomidWithMinMaxInKeys(keys, customid, _min, _max, TABLENAME);
    }

    public int updateCustomidWithMinMaxInKeys(final List<Integer> keys, final int customid, final int _min, final int _max, final String TABLENAME2){
        if( customid < 0 ) {
            return updateCustomidWithMinInKeys(keys, customid, _min, TABLENAME2);
        } else {
            return updateCustomidWithMaxInKeys(keys, customid, _max, TABLENAME2);
        }
    }

    public int updateQuestionidByKey(final int questionid, final int id){
        return updateQuestionidByKey(questionid, id, TABLENAME);
    }

    public int updateQuestionidByKey(final int questionid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET questionid=questionid+:questionid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("questionid", questionid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQuestionidWithMinByKey(final int id, final int questionid, final int _min){
        return updateQuestionidWithMinByKey(id, questionid, _min, TABLENAME);
    }

    public int updateQuestionidWithMinByKey(final int id, final int questionid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET questionid = (select case when questionid+:questionid<=:_min then :_min else questionid+:questionid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("questionid", questionid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQuestionidWithMinInKeys(final List<Integer> keys, final int questionid, final int _min){
        return updateQuestionidWithMinInKeys(keys, questionid, _min, TABLENAME);
    }

    public int updateQuestionidWithMinInKeys(final List<Integer> keys, final int questionid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET questionid = (select case when questionid+:questionid<=:_min then :_min else questionid+:questionid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("questionid", questionid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQuestionidWithMaxByKey(final int id, final int questionid, final int _max){
        return updateQuestionidWithMaxByKey(id, questionid, _max, TABLENAME);
    }

    public int updateQuestionidWithMaxByKey(final int id, final int questionid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET questionid = (select case when questionid+:questionid>=:_max then :_max else questionid+:questionid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("questionid", questionid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQuestionidWithMaxInKeys(final List<Integer> keys, final int questionid, final int _max){
        return updateQuestionidWithMaxInKeys(keys, questionid, _max, TABLENAME);
    }

    public int updateQuestionidWithMaxInKeys(final List<Integer> keys, final int questionid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET questionid = (select case when questionid+:questionid>=:_max then :_max else questionid+:questionid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("questionid", questionid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQuestionidWithMinMaxByKey(final int id, final int questionid, final int _min, final int _max){
        return updateQuestionidWithMinMaxByKey(id, questionid, _min, _max, TABLENAME);
    }

    public int updateQuestionidWithMinMaxByKey(final int id, final int questionid, final int _min, final int _max, final String TABLENAME2){
        if( questionid < 0 ) {
            return updateQuestionidWithMinByKey(id, questionid, _min, TABLENAME2);
        } else {
            return updateQuestionidWithMaxByKey(id, questionid, _max, TABLENAME2);
        }
    }

    public int updateQuestionidWithMinMaxInKeys(final List<Integer> keys, final int questionid, final int _min, final int _max){
        return updateQuestionidWithMinMaxInKeys(keys, questionid, _min, _max, TABLENAME);
    }

    public int updateQuestionidWithMinMaxInKeys(final List<Integer> keys, final int questionid, final int _min, final int _max, final String TABLENAME2){
        if( questionid < 0 ) {
            return updateQuestionidWithMinInKeys(keys, questionid, _min, TABLENAME2);
        } else {
            return updateQuestionidWithMaxInKeys(keys, questionid, _max, TABLENAME2);
        }
    }

    public int updateCatalog4ExamByKey(final int catalog4Exam, final int id){
        return updateCatalog4ExamByKey(catalog4Exam, id, TABLENAME);
    }

    public int updateCatalog4ExamByKey(final int catalog4Exam, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalog4Exam=catalog4Exam+:catalog4Exam WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("catalog4Exam", catalog4Exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalog4ExamWithMinByKey(final int id, final int catalog4Exam, final int _min){
        return updateCatalog4ExamWithMinByKey(id, catalog4Exam, _min, TABLENAME);
    }

    public int updateCatalog4ExamWithMinByKey(final int id, final int catalog4Exam, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalog4Exam = (select case when catalog4Exam+:catalog4Exam<=:_min then :_min else catalog4Exam+:catalog4Exam end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("catalog4Exam", catalog4Exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalog4ExamWithMinInKeys(final List<Integer> keys, final int catalog4Exam, final int _min){
        return updateCatalog4ExamWithMinInKeys(keys, catalog4Exam, _min, TABLENAME);
    }

    public int updateCatalog4ExamWithMinInKeys(final List<Integer> keys, final int catalog4Exam, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalog4Exam = (select case when catalog4Exam+:catalog4Exam<=:_min then :_min else catalog4Exam+:catalog4Exam end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("catalog4Exam", catalog4Exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalog4ExamWithMaxByKey(final int id, final int catalog4Exam, final int _max){
        return updateCatalog4ExamWithMaxByKey(id, catalog4Exam, _max, TABLENAME);
    }

    public int updateCatalog4ExamWithMaxByKey(final int id, final int catalog4Exam, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalog4Exam = (select case when catalog4Exam+:catalog4Exam>=:_max then :_max else catalog4Exam+:catalog4Exam end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("catalog4Exam", catalog4Exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalog4ExamWithMaxInKeys(final List<Integer> keys, final int catalog4Exam, final int _max){
        return updateCatalog4ExamWithMaxInKeys(keys, catalog4Exam, _max, TABLENAME);
    }

    public int updateCatalog4ExamWithMaxInKeys(final List<Integer> keys, final int catalog4Exam, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalog4Exam = (select case when catalog4Exam+:catalog4Exam>=:_max then :_max else catalog4Exam+:catalog4Exam end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("catalog4Exam", catalog4Exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalog4ExamWithMinMaxByKey(final int id, final int catalog4Exam, final int _min, final int _max){
        return updateCatalog4ExamWithMinMaxByKey(id, catalog4Exam, _min, _max, TABLENAME);
    }

    public int updateCatalog4ExamWithMinMaxByKey(final int id, final int catalog4Exam, final int _min, final int _max, final String TABLENAME2){
        if( catalog4Exam < 0 ) {
            return updateCatalog4ExamWithMinByKey(id, catalog4Exam, _min, TABLENAME2);
        } else {
            return updateCatalog4ExamWithMaxByKey(id, catalog4Exam, _max, TABLENAME2);
        }
    }

    public int updateCatalog4ExamWithMinMaxInKeys(final List<Integer> keys, final int catalog4Exam, final int _min, final int _max){
        return updateCatalog4ExamWithMinMaxInKeys(keys, catalog4Exam, _min, _max, TABLENAME);
    }

    public int updateCatalog4ExamWithMinMaxInKeys(final List<Integer> keys, final int catalog4Exam, final int _min, final int _max, final String TABLENAME2){
        if( catalog4Exam < 0 ) {
            return updateCatalog4ExamWithMinInKeys(keys, catalog4Exam, _min, TABLENAME2);
        } else {
            return updateCatalog4ExamWithMaxInKeys(keys, catalog4Exam, _max, TABLENAME2);
        }
    }

    public int updateNumErrorByKey(final int numError, final int id){
        return updateNumErrorByKey(numError, id, TABLENAME);
    }

    public int updateNumErrorByKey(final int numError, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numError=numError+:numError WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("numError", numError);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumErrorWithMinByKey(final int id, final int numError, final int _min){
        return updateNumErrorWithMinByKey(id, numError, _min, TABLENAME);
    }

    public int updateNumErrorWithMinByKey(final int id, final int numError, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numError = (select case when numError+:numError<=:_min then :_min else numError+:numError end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("numError", numError);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumErrorWithMinInKeys(final List<Integer> keys, final int numError, final int _min){
        return updateNumErrorWithMinInKeys(keys, numError, _min, TABLENAME);
    }

    public int updateNumErrorWithMinInKeys(final List<Integer> keys, final int numError, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numError = (select case when numError+:numError<=:_min then :_min else numError+:numError end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("numError", numError);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumErrorWithMaxByKey(final int id, final int numError, final int _max){
        return updateNumErrorWithMaxByKey(id, numError, _max, TABLENAME);
    }

    public int updateNumErrorWithMaxByKey(final int id, final int numError, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numError = (select case when numError+:numError>=:_max then :_max else numError+:numError end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("numError", numError);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumErrorWithMaxInKeys(final List<Integer> keys, final int numError, final int _max){
        return updateNumErrorWithMaxInKeys(keys, numError, _max, TABLENAME);
    }

    public int updateNumErrorWithMaxInKeys(final List<Integer> keys, final int numError, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numError = (select case when numError+:numError>=:_max then :_max else numError+:numError end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("numError", numError);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumErrorWithMinMaxByKey(final int id, final int numError, final int _min, final int _max){
        return updateNumErrorWithMinMaxByKey(id, numError, _min, _max, TABLENAME);
    }

    public int updateNumErrorWithMinMaxByKey(final int id, final int numError, final int _min, final int _max, final String TABLENAME2){
        if( numError < 0 ) {
            return updateNumErrorWithMinByKey(id, numError, _min, TABLENAME2);
        } else {
            return updateNumErrorWithMaxByKey(id, numError, _max, TABLENAME2);
        }
    }

    public int updateNumErrorWithMinMaxInKeys(final List<Integer> keys, final int numError, final int _min, final int _max){
        return updateNumErrorWithMinMaxInKeys(keys, numError, _min, _max, TABLENAME);
    }

    public int updateNumErrorWithMinMaxInKeys(final List<Integer> keys, final int numError, final int _min, final int _max, final String TABLENAME2){
        if( numError < 0 ) {
            return updateNumErrorWithMinInKeys(keys, numError, _min, TABLENAME2);
        } else {
            return updateNumErrorWithMaxInKeys(keys, numError, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Recordques4exam> recordques4exams) {
        return updateByKey(recordques4exams, TABLENAME);
    }

    public int[] updateByKey (final List<Recordques4exam> recordques4exams, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(recordques4exams == null || recordques4exams.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customid=:customid, questionid=:questionid, catalog4Exam=:catalog4Exam, numError=:numError, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), recordques4exams);
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
                "	`customid`  INT(11) NOT NULL," +
                "	`questionid`  INT(11) NOT NULL," +
                "	`catalog4Exam`  INT(11) NOT NULL," +
                "	`numError`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `customid_quesid` (`customid`, `questionid`)," +
                "	KEY `customid` (`customid`)," +
                "	KEY `questionid` (`questionid`)," +
                "	KEY `customid_catalog` (`customid`, `catalog4Exam`)" +
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
                "	`customid`  INT(11) NOT NULL," +
                "	`questionid`  INT(11) NOT NULL," +
                "	`catalog4Exam`  INT(11) NOT NULL," +
                "	`numError`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `customid_quesid` (`customid`, `questionid`)," +
                "	KEY `customid` (`customid`)," +
                "	KEY `questionid` (`questionid`)," +
                "	KEY `customid_catalog` (`customid`, `catalog4Exam`)" +
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
