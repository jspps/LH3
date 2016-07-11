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

//learnhall3_design - recordanswer
@SuppressWarnings({"rawtypes", "unchecked"})
public class RecordanswerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(RecordanswerDAO.class);

    public static final String TABLE = "recordanswer";
    public static String TABLENAME = "recordanswer";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "examid", "nmExam", "customerid", "custname", "score", "avecorrectrate", "lasttime", "num", "status", "anwers", "createtime", "costTimes", "catalog", "examType", "score4ques", "kindid", "lens4exam", "lens4right", "courseid", "lhubid"};
    public static String coulmns = "id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid";
    public static String coulmns2 = "examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid";

    public RecordanswerDAO(Connection conn) {
        super(conn);
    }

    public RecordanswerDAO(DataSource ds) {
        super(ds);
    }

    public RecordanswerDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Recordanswer recordanswer) {
        return insert(recordanswer, TABLENAME);
    }

    public int insert(final Recordanswer recordanswer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            recordanswer.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid) VALUES (:examid, :nmExam, :customerid, :custname, :score, :avecorrectrate, :lasttime, :num, :status, :anwers, :createtime, :costTimes, :catalog, :examType, :score4ques, :kindid, :lens4exam, :lens4right, :courseid, :lhubid)");
            Map map = super.insert(sql.toString(), recordanswer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Recordanswer recordanswer) {
        return asyncInsert(recordanswer, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Recordanswer recordanswer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(recordanswer, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Recordanswer recordanswer) {
        return asyncInsert2(recordanswer, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Recordanswer recordanswer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(recordanswer, TABLENAME2);
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

    public int insert2(final Recordanswer recordanswer) {
        return insert2(recordanswer, TABLENAME);
    }

    public int insert2(final Recordanswer recordanswer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            recordanswer.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid) VALUES (:id, :examid, :nmExam, :customerid, :custname, :score, :avecorrectrate, :lasttime, :num, :status, :anwers, :createtime, :costTimes, :catalog, :examType, :score4ques, :kindid, :lens4exam, :lens4right, :courseid, :lhubid)");
            Map map = super.insert(sql.toString(), recordanswer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Recordanswer> recordanswers) {
        return insert(recordanswers, TABLENAME);
    }

    public int[] insert(final List<Recordanswer> recordanswers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(recordanswers == null || recordanswers.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid) VALUES (:examid, :nmExam, :customerid, :custname, :score, :avecorrectrate, :lasttime, :num, :status, :anwers, :createtime, :costTimes, :catalog, :examType, :score4ques, :kindid, :lens4exam, :lens4right, :courseid, :lhubid)");
            return super.batchInsert(sql.toString(), recordanswers);
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

    public int deleteInBeans(final List<Recordanswer> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Recordanswer> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Recordanswer recordanswer = beans.get(i);
                int id = recordanswer.id;
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

    public List<Recordanswer> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Recordanswer> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Recordanswer.class);
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
            sql.append("SELECT id, examid, customerid, courseid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordanswer> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Recordanswer> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Recordanswer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordanswer> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Recordanswer> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
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

    public List<Recordanswer> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Recordanswer> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Recordanswer.class);
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

    public Recordanswer last() {
        return last(TABLENAME);
    }

    public Recordanswer last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Recordanswer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordanswer> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Recordanswer> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordanswer> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Recordanswer> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
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

    public Recordanswer selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Recordanswer selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recordanswer.class);
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

    public List<Recordanswer> selectByCustomerid(final Integer customerid) {
        return selectByCustomerid(customerid, TABLENAME);
    }

    public List<Recordanswer> selectByCustomerid(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id ");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
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

    public List<Recordanswer> selectPageByCustomerid(final Integer customerid, final int begin, final int num) {
        return selectPageByCustomerid(customerid, begin, num, TABLENAME);
    }

    public List<Recordanswer> selectPageByCustomerid(final Integer customerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
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

    public Recordanswer selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Recordanswer selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recordanswer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByCourseid(final Integer courseid) {
        return countByCourseid(courseid, TABLENAME);
    }

    public int countByCourseid(final Integer courseid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE courseid = :courseid ");
            Map params = newMap();
            params.put("courseid", courseid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordanswer> selectByCourseid(final Integer courseid) {
        return selectByCourseid(courseid, TABLENAME);
    }

    public List<Recordanswer> selectByCourseid(final Integer courseid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE courseid = :courseid ORDER BY id ");
            Map params = newMap();
            params.put("courseid", courseid);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCourseidPKs(final Integer courseid) {
        return selectByCourseidPKs(courseid, TABLENAME);
    }

    public List<Integer> selectByCourseidPKs(final Integer courseid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE courseid = :courseid ORDER BY id ");
            Map params = newMap();
            params.put("courseid", courseid);
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

    public List<Recordanswer> selectPageByCourseid(final Integer courseid, final int begin, final int num) {
        return selectPageByCourseid(courseid, begin, num, TABLENAME);
    }

    public List<Recordanswer> selectPageByCourseid(final Integer courseid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE courseid = :courseid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("courseid", courseid);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCourseidPKs(final Integer courseid, final int begin, final int num) {
        return selectPageByCourseidPKs(courseid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCourseidPKs(final Integer courseid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE courseid = :courseid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("courseid", courseid);
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

    public List<Recordanswer> selectByExamid(final Integer examid) {
        return selectByExamid(examid, TABLENAME);
    }

    public List<Recordanswer> selectByExamid(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id ");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
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

    public List<Recordanswer> selectPageByExamid(final Integer examid, final int begin, final int num) {
        return selectPageByExamid(examid, begin, num, TABLENAME);
    }

    public List<Recordanswer> selectPageByExamid(final Integer examid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Recordanswer.class);
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

    public Recordanswer selectByExamidCustomerid(final Integer examid, Integer customerid) {
        return selectByExamidCustomerid(examid, customerid, TABLENAME);
    }

    public Recordanswer selectByExamidCustomerid(final Integer examid, Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" WHERE examid=:examid AND customerid=:customerid");
            Map params = newMap();
            params.put("examid", examid);
            params.put("customerid", customerid);
            return super.queryForObject(sql.toString(), params, Recordanswer.class);
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

    public List<Recordanswer> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Recordanswer> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Recordanswer.class);
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

    public int updateByKey(final Recordanswer recordanswer) {
        return updateByKey(recordanswer, TABLENAME);
    }

    public int updateByKey(final Recordanswer recordanswer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = recordanswer.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), recordanswer);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Recordanswer recordanswer) {
        return asyncUpdate(recordanswer, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Recordanswer recordanswer, final String TABLENAME2) {
        try {

            String _ustr = recordanswer.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, recordanswer);
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

    public int updateAvecorrectrateByKey(final int avecorrectrate, final int id){
        return updateAvecorrectrateByKey(avecorrectrate, id, TABLENAME);
    }

    public int updateAvecorrectrateByKey(final int avecorrectrate, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET avecorrectrate=avecorrectrate+:avecorrectrate WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("avecorrectrate", avecorrectrate);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAvecorrectrateWithMinByKey(final int id, final int avecorrectrate, final int _min){
        return updateAvecorrectrateWithMinByKey(id, avecorrectrate, _min, TABLENAME);
    }

    public int updateAvecorrectrateWithMinByKey(final int id, final int avecorrectrate, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET avecorrectrate = (select case when avecorrectrate+:avecorrectrate<=:_min then :_min else avecorrectrate+:avecorrectrate end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("avecorrectrate", avecorrectrate);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAvecorrectrateWithMinInKeys(final List<Integer> keys, final int avecorrectrate, final int _min){
        return updateAvecorrectrateWithMinInKeys(keys, avecorrectrate, _min, TABLENAME);
    }

    public int updateAvecorrectrateWithMinInKeys(final List<Integer> keys, final int avecorrectrate, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET avecorrectrate = (select case when avecorrectrate+:avecorrectrate<=:_min then :_min else avecorrectrate+:avecorrectrate end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("avecorrectrate", avecorrectrate);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAvecorrectrateWithMaxByKey(final int id, final int avecorrectrate, final int _max){
        return updateAvecorrectrateWithMaxByKey(id, avecorrectrate, _max, TABLENAME);
    }

    public int updateAvecorrectrateWithMaxByKey(final int id, final int avecorrectrate, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET avecorrectrate = (select case when avecorrectrate+:avecorrectrate>=:_max then :_max else avecorrectrate+:avecorrectrate end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("avecorrectrate", avecorrectrate);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAvecorrectrateWithMaxInKeys(final List<Integer> keys, final int avecorrectrate, final int _max){
        return updateAvecorrectrateWithMaxInKeys(keys, avecorrectrate, _max, TABLENAME);
    }

    public int updateAvecorrectrateWithMaxInKeys(final List<Integer> keys, final int avecorrectrate, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET avecorrectrate = (select case when avecorrectrate+:avecorrectrate>=:_max then :_max else avecorrectrate+:avecorrectrate end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("avecorrectrate", avecorrectrate);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAvecorrectrateWithMinMaxByKey(final int id, final int avecorrectrate, final int _min, final int _max){
        return updateAvecorrectrateWithMinMaxByKey(id, avecorrectrate, _min, _max, TABLENAME);
    }

    public int updateAvecorrectrateWithMinMaxByKey(final int id, final int avecorrectrate, final int _min, final int _max, final String TABLENAME2){
        if( avecorrectrate < 0 ) {
            return updateAvecorrectrateWithMinByKey(id, avecorrectrate, _min, TABLENAME2);
        } else {
            return updateAvecorrectrateWithMaxByKey(id, avecorrectrate, _max, TABLENAME2);
        }
    }

    public int updateAvecorrectrateWithMinMaxInKeys(final List<Integer> keys, final int avecorrectrate, final int _min, final int _max){
        return updateAvecorrectrateWithMinMaxInKeys(keys, avecorrectrate, _min, _max, TABLENAME);
    }

    public int updateAvecorrectrateWithMinMaxInKeys(final List<Integer> keys, final int avecorrectrate, final int _min, final int _max, final String TABLENAME2){
        if( avecorrectrate < 0 ) {
            return updateAvecorrectrateWithMinInKeys(keys, avecorrectrate, _min, TABLENAME2);
        } else {
            return updateAvecorrectrateWithMaxInKeys(keys, avecorrectrate, _max, TABLENAME2);
        }
    }

    public int updateNumByKey(final int num, final int id){
        return updateNumByKey(num, id, TABLENAME);
    }

    public int updateNumByKey(final int num, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num=num+:num WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinByKey(final int id, final int num, final int _min){
        return updateNumWithMinByKey(id, num, _min, TABLENAME);
    }

    public int updateNumWithMinByKey(final int id, final int num, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num<=:_min then :_min else num+:num end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinInKeys(final List<Integer> keys, final int num, final int _min){
        return updateNumWithMinInKeys(keys, num, _min, TABLENAME);
    }

    public int updateNumWithMinInKeys(final List<Integer> keys, final int num, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num<=:_min then :_min else num+:num end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMaxByKey(final int id, final int num, final int _max){
        return updateNumWithMaxByKey(id, num, _max, TABLENAME);
    }

    public int updateNumWithMaxByKey(final int id, final int num, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num>=:_max then :_max else num+:num end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMaxInKeys(final List<Integer> keys, final int num, final int _max){
        return updateNumWithMaxInKeys(keys, num, _max, TABLENAME);
    }

    public int updateNumWithMaxInKeys(final List<Integer> keys, final int num, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num>=:_max then :_max else num+:num end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinMaxByKey(final int id, final int num, final int _min, final int _max){
        return updateNumWithMinMaxByKey(id, num, _min, _max, TABLENAME);
    }

    public int updateNumWithMinMaxByKey(final int id, final int num, final int _min, final int _max, final String TABLENAME2){
        if( num < 0 ) {
            return updateNumWithMinByKey(id, num, _min, TABLENAME2);
        } else {
            return updateNumWithMaxByKey(id, num, _max, TABLENAME2);
        }
    }

    public int updateNumWithMinMaxInKeys(final List<Integer> keys, final int num, final int _min, final int _max){
        return updateNumWithMinMaxInKeys(keys, num, _min, _max, TABLENAME);
    }

    public int updateNumWithMinMaxInKeys(final List<Integer> keys, final int num, final int _min, final int _max, final String TABLENAME2){
        if( num < 0 ) {
            return updateNumWithMinInKeys(keys, num, _min, TABLENAME2);
        } else {
            return updateNumWithMaxInKeys(keys, num, _max, TABLENAME2);
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

    public int updateCostTimesByKey(final int costTimes, final int id){
        return updateCostTimesByKey(costTimes, id, TABLENAME);
    }

    public int updateCostTimesByKey(final int costTimes, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET costTimes=costTimes+:costTimes WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("costTimes", costTimes);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCostTimesWithMinByKey(final int id, final int costTimes, final int _min){
        return updateCostTimesWithMinByKey(id, costTimes, _min, TABLENAME);
    }

    public int updateCostTimesWithMinByKey(final int id, final int costTimes, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET costTimes = (select case when costTimes+:costTimes<=:_min then :_min else costTimes+:costTimes end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("costTimes", costTimes);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCostTimesWithMinInKeys(final List<Integer> keys, final int costTimes, final int _min){
        return updateCostTimesWithMinInKeys(keys, costTimes, _min, TABLENAME);
    }

    public int updateCostTimesWithMinInKeys(final List<Integer> keys, final int costTimes, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET costTimes = (select case when costTimes+:costTimes<=:_min then :_min else costTimes+:costTimes end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("costTimes", costTimes);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCostTimesWithMaxByKey(final int id, final int costTimes, final int _max){
        return updateCostTimesWithMaxByKey(id, costTimes, _max, TABLENAME);
    }

    public int updateCostTimesWithMaxByKey(final int id, final int costTimes, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET costTimes = (select case when costTimes+:costTimes>=:_max then :_max else costTimes+:costTimes end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("costTimes", costTimes);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCostTimesWithMaxInKeys(final List<Integer> keys, final int costTimes, final int _max){
        return updateCostTimesWithMaxInKeys(keys, costTimes, _max, TABLENAME);
    }

    public int updateCostTimesWithMaxInKeys(final List<Integer> keys, final int costTimes, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET costTimes = (select case when costTimes+:costTimes>=:_max then :_max else costTimes+:costTimes end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("costTimes", costTimes);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCostTimesWithMinMaxByKey(final int id, final int costTimes, final int _min, final int _max){
        return updateCostTimesWithMinMaxByKey(id, costTimes, _min, _max, TABLENAME);
    }

    public int updateCostTimesWithMinMaxByKey(final int id, final int costTimes, final int _min, final int _max, final String TABLENAME2){
        if( costTimes < 0 ) {
            return updateCostTimesWithMinByKey(id, costTimes, _min, TABLENAME2);
        } else {
            return updateCostTimesWithMaxByKey(id, costTimes, _max, TABLENAME2);
        }
    }

    public int updateCostTimesWithMinMaxInKeys(final List<Integer> keys, final int costTimes, final int _min, final int _max){
        return updateCostTimesWithMinMaxInKeys(keys, costTimes, _min, _max, TABLENAME);
    }

    public int updateCostTimesWithMinMaxInKeys(final List<Integer> keys, final int costTimes, final int _min, final int _max, final String TABLENAME2){
        if( costTimes < 0 ) {
            return updateCostTimesWithMinInKeys(keys, costTimes, _min, TABLENAME2);
        } else {
            return updateCostTimesWithMaxInKeys(keys, costTimes, _max, TABLENAME2);
        }
    }

    public int updateExamTypeByKey(final int examType, final int id){
        return updateExamTypeByKey(examType, id, TABLENAME);
    }

    public int updateExamTypeByKey(final int examType, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examType=examType+:examType WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("examType", examType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamTypeWithMinByKey(final int id, final int examType, final int _min){
        return updateExamTypeWithMinByKey(id, examType, _min, TABLENAME);
    }

    public int updateExamTypeWithMinByKey(final int id, final int examType, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examType = (select case when examType+:examType<=:_min then :_min else examType+:examType end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("examType", examType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamTypeWithMinInKeys(final List<Integer> keys, final int examType, final int _min){
        return updateExamTypeWithMinInKeys(keys, examType, _min, TABLENAME);
    }

    public int updateExamTypeWithMinInKeys(final List<Integer> keys, final int examType, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examType = (select case when examType+:examType<=:_min then :_min else examType+:examType end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examType", examType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamTypeWithMaxByKey(final int id, final int examType, final int _max){
        return updateExamTypeWithMaxByKey(id, examType, _max, TABLENAME);
    }

    public int updateExamTypeWithMaxByKey(final int id, final int examType, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examType = (select case when examType+:examType>=:_max then :_max else examType+:examType end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("examType", examType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamTypeWithMaxInKeys(final List<Integer> keys, final int examType, final int _max){
        return updateExamTypeWithMaxInKeys(keys, examType, _max, TABLENAME);
    }

    public int updateExamTypeWithMaxInKeys(final List<Integer> keys, final int examType, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examType = (select case when examType+:examType>=:_max then :_max else examType+:examType end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examType", examType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamTypeWithMinMaxByKey(final int id, final int examType, final int _min, final int _max){
        return updateExamTypeWithMinMaxByKey(id, examType, _min, _max, TABLENAME);
    }

    public int updateExamTypeWithMinMaxByKey(final int id, final int examType, final int _min, final int _max, final String TABLENAME2){
        if( examType < 0 ) {
            return updateExamTypeWithMinByKey(id, examType, _min, TABLENAME2);
        } else {
            return updateExamTypeWithMaxByKey(id, examType, _max, TABLENAME2);
        }
    }

    public int updateExamTypeWithMinMaxInKeys(final List<Integer> keys, final int examType, final int _min, final int _max){
        return updateExamTypeWithMinMaxInKeys(keys, examType, _min, _max, TABLENAME);
    }

    public int updateExamTypeWithMinMaxInKeys(final List<Integer> keys, final int examType, final int _min, final int _max, final String TABLENAME2){
        if( examType < 0 ) {
            return updateExamTypeWithMinInKeys(keys, examType, _min, TABLENAME2);
        } else {
            return updateExamTypeWithMaxInKeys(keys, examType, _max, TABLENAME2);
        }
    }

    public int updateKindidByKey(final int kindid, final int id){
        return updateKindidByKey(kindid, id, TABLENAME);
    }

    public int updateKindidByKey(final int kindid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid=kindid+:kindid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMinByKey(final int id, final int kindid, final int _min){
        return updateKindidWithMinByKey(id, kindid, _min, TABLENAME);
    }

    public int updateKindidWithMinByKey(final int id, final int kindid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid<=:_min then :_min else kindid+:kindid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMinInKeys(final List<Integer> keys, final int kindid, final int _min){
        return updateKindidWithMinInKeys(keys, kindid, _min, TABLENAME);
    }

    public int updateKindidWithMinInKeys(final List<Integer> keys, final int kindid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid<=:_min then :_min else kindid+:kindid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMaxByKey(final int id, final int kindid, final int _max){
        return updateKindidWithMaxByKey(id, kindid, _max, TABLENAME);
    }

    public int updateKindidWithMaxByKey(final int id, final int kindid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid>=:_max then :_max else kindid+:kindid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMaxInKeys(final List<Integer> keys, final int kindid, final int _max){
        return updateKindidWithMaxInKeys(keys, kindid, _max, TABLENAME);
    }

    public int updateKindidWithMaxInKeys(final List<Integer> keys, final int kindid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid>=:_max then :_max else kindid+:kindid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMinMaxByKey(final int id, final int kindid, final int _min, final int _max){
        return updateKindidWithMinMaxByKey(id, kindid, _min, _max, TABLENAME);
    }

    public int updateKindidWithMinMaxByKey(final int id, final int kindid, final int _min, final int _max, final String TABLENAME2){
        if( kindid < 0 ) {
            return updateKindidWithMinByKey(id, kindid, _min, TABLENAME2);
        } else {
            return updateKindidWithMaxByKey(id, kindid, _max, TABLENAME2);
        }
    }

    public int updateKindidWithMinMaxInKeys(final List<Integer> keys, final int kindid, final int _min, final int _max){
        return updateKindidWithMinMaxInKeys(keys, kindid, _min, _max, TABLENAME);
    }

    public int updateKindidWithMinMaxInKeys(final List<Integer> keys, final int kindid, final int _min, final int _max, final String TABLENAME2){
        if( kindid < 0 ) {
            return updateKindidWithMinInKeys(keys, kindid, _min, TABLENAME2);
        } else {
            return updateKindidWithMaxInKeys(keys, kindid, _max, TABLENAME2);
        }
    }

    public int updateLens4examByKey(final int lens4exam, final int id){
        return updateLens4examByKey(lens4exam, id, TABLENAME);
    }

    public int updateLens4examByKey(final int lens4exam, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4exam=lens4exam+:lens4exam WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lens4exam", lens4exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4examWithMinByKey(final int id, final int lens4exam, final int _min){
        return updateLens4examWithMinByKey(id, lens4exam, _min, TABLENAME);
    }

    public int updateLens4examWithMinByKey(final int id, final int lens4exam, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4exam = (select case when lens4exam+:lens4exam<=:_min then :_min else lens4exam+:lens4exam end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lens4exam", lens4exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4examWithMinInKeys(final List<Integer> keys, final int lens4exam, final int _min){
        return updateLens4examWithMinInKeys(keys, lens4exam, _min, TABLENAME);
    }

    public int updateLens4examWithMinInKeys(final List<Integer> keys, final int lens4exam, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4exam = (select case when lens4exam+:lens4exam<=:_min then :_min else lens4exam+:lens4exam end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lens4exam", lens4exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4examWithMaxByKey(final int id, final int lens4exam, final int _max){
        return updateLens4examWithMaxByKey(id, lens4exam, _max, TABLENAME);
    }

    public int updateLens4examWithMaxByKey(final int id, final int lens4exam, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4exam = (select case when lens4exam+:lens4exam>=:_max then :_max else lens4exam+:lens4exam end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lens4exam", lens4exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4examWithMaxInKeys(final List<Integer> keys, final int lens4exam, final int _max){
        return updateLens4examWithMaxInKeys(keys, lens4exam, _max, TABLENAME);
    }

    public int updateLens4examWithMaxInKeys(final List<Integer> keys, final int lens4exam, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4exam = (select case when lens4exam+:lens4exam>=:_max then :_max else lens4exam+:lens4exam end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lens4exam", lens4exam);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4examWithMinMaxByKey(final int id, final int lens4exam, final int _min, final int _max){
        return updateLens4examWithMinMaxByKey(id, lens4exam, _min, _max, TABLENAME);
    }

    public int updateLens4examWithMinMaxByKey(final int id, final int lens4exam, final int _min, final int _max, final String TABLENAME2){
        if( lens4exam < 0 ) {
            return updateLens4examWithMinByKey(id, lens4exam, _min, TABLENAME2);
        } else {
            return updateLens4examWithMaxByKey(id, lens4exam, _max, TABLENAME2);
        }
    }

    public int updateLens4examWithMinMaxInKeys(final List<Integer> keys, final int lens4exam, final int _min, final int _max){
        return updateLens4examWithMinMaxInKeys(keys, lens4exam, _min, _max, TABLENAME);
    }

    public int updateLens4examWithMinMaxInKeys(final List<Integer> keys, final int lens4exam, final int _min, final int _max, final String TABLENAME2){
        if( lens4exam < 0 ) {
            return updateLens4examWithMinInKeys(keys, lens4exam, _min, TABLENAME2);
        } else {
            return updateLens4examWithMaxInKeys(keys, lens4exam, _max, TABLENAME2);
        }
    }

    public int updateLens4rightByKey(final int lens4right, final int id){
        return updateLens4rightByKey(lens4right, id, TABLENAME);
    }

    public int updateLens4rightByKey(final int lens4right, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4right=lens4right+:lens4right WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lens4right", lens4right);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4rightWithMinByKey(final int id, final int lens4right, final int _min){
        return updateLens4rightWithMinByKey(id, lens4right, _min, TABLENAME);
    }

    public int updateLens4rightWithMinByKey(final int id, final int lens4right, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4right = (select case when lens4right+:lens4right<=:_min then :_min else lens4right+:lens4right end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lens4right", lens4right);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4rightWithMinInKeys(final List<Integer> keys, final int lens4right, final int _min){
        return updateLens4rightWithMinInKeys(keys, lens4right, _min, TABLENAME);
    }

    public int updateLens4rightWithMinInKeys(final List<Integer> keys, final int lens4right, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4right = (select case when lens4right+:lens4right<=:_min then :_min else lens4right+:lens4right end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lens4right", lens4right);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4rightWithMaxByKey(final int id, final int lens4right, final int _max){
        return updateLens4rightWithMaxByKey(id, lens4right, _max, TABLENAME);
    }

    public int updateLens4rightWithMaxByKey(final int id, final int lens4right, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4right = (select case when lens4right+:lens4right>=:_max then :_max else lens4right+:lens4right end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lens4right", lens4right);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4rightWithMaxInKeys(final List<Integer> keys, final int lens4right, final int _max){
        return updateLens4rightWithMaxInKeys(keys, lens4right, _max, TABLENAME);
    }

    public int updateLens4rightWithMaxInKeys(final List<Integer> keys, final int lens4right, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lens4right = (select case when lens4right+:lens4right>=:_max then :_max else lens4right+:lens4right end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lens4right", lens4right);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLens4rightWithMinMaxByKey(final int id, final int lens4right, final int _min, final int _max){
        return updateLens4rightWithMinMaxByKey(id, lens4right, _min, _max, TABLENAME);
    }

    public int updateLens4rightWithMinMaxByKey(final int id, final int lens4right, final int _min, final int _max, final String TABLENAME2){
        if( lens4right < 0 ) {
            return updateLens4rightWithMinByKey(id, lens4right, _min, TABLENAME2);
        } else {
            return updateLens4rightWithMaxByKey(id, lens4right, _max, TABLENAME2);
        }
    }

    public int updateLens4rightWithMinMaxInKeys(final List<Integer> keys, final int lens4right, final int _min, final int _max){
        return updateLens4rightWithMinMaxInKeys(keys, lens4right, _min, _max, TABLENAME);
    }

    public int updateLens4rightWithMinMaxInKeys(final List<Integer> keys, final int lens4right, final int _min, final int _max, final String TABLENAME2){
        if( lens4right < 0 ) {
            return updateLens4rightWithMinInKeys(keys, lens4right, _min, TABLENAME2);
        } else {
            return updateLens4rightWithMaxInKeys(keys, lens4right, _max, TABLENAME2);
        }
    }

    public int updateCourseidByKey(final int courseid, final int id){
        return updateCourseidByKey(courseid, id, TABLENAME);
    }

    public int updateCourseidByKey(final int courseid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET courseid=courseid+:courseid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("courseid", courseid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCourseidWithMinByKey(final int id, final int courseid, final int _min){
        return updateCourseidWithMinByKey(id, courseid, _min, TABLENAME);
    }

    public int updateCourseidWithMinByKey(final int id, final int courseid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET courseid = (select case when courseid+:courseid<=:_min then :_min else courseid+:courseid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("courseid", courseid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCourseidWithMinInKeys(final List<Integer> keys, final int courseid, final int _min){
        return updateCourseidWithMinInKeys(keys, courseid, _min, TABLENAME);
    }

    public int updateCourseidWithMinInKeys(final List<Integer> keys, final int courseid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET courseid = (select case when courseid+:courseid<=:_min then :_min else courseid+:courseid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("courseid", courseid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCourseidWithMaxByKey(final int id, final int courseid, final int _max){
        return updateCourseidWithMaxByKey(id, courseid, _max, TABLENAME);
    }

    public int updateCourseidWithMaxByKey(final int id, final int courseid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET courseid = (select case when courseid+:courseid>=:_max then :_max else courseid+:courseid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("courseid", courseid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCourseidWithMaxInKeys(final List<Integer> keys, final int courseid, final int _max){
        return updateCourseidWithMaxInKeys(keys, courseid, _max, TABLENAME);
    }

    public int updateCourseidWithMaxInKeys(final List<Integer> keys, final int courseid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET courseid = (select case when courseid+:courseid>=:_max then :_max else courseid+:courseid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("courseid", courseid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCourseidWithMinMaxByKey(final int id, final int courseid, final int _min, final int _max){
        return updateCourseidWithMinMaxByKey(id, courseid, _min, _max, TABLENAME);
    }

    public int updateCourseidWithMinMaxByKey(final int id, final int courseid, final int _min, final int _max, final String TABLENAME2){
        if( courseid < 0 ) {
            return updateCourseidWithMinByKey(id, courseid, _min, TABLENAME2);
        } else {
            return updateCourseidWithMaxByKey(id, courseid, _max, TABLENAME2);
        }
    }

    public int updateCourseidWithMinMaxInKeys(final List<Integer> keys, final int courseid, final int _min, final int _max){
        return updateCourseidWithMinMaxInKeys(keys, courseid, _min, _max, TABLENAME);
    }

    public int updateCourseidWithMinMaxInKeys(final List<Integer> keys, final int courseid, final int _min, final int _max, final String TABLENAME2){
        if( courseid < 0 ) {
            return updateCourseidWithMinInKeys(keys, courseid, _min, TABLENAME2);
        } else {
            return updateCourseidWithMaxInKeys(keys, courseid, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Recordanswer> recordanswers) {
        return updateByKey(recordanswers, TABLENAME);
    }

    public int[] updateByKey (final List<Recordanswer> recordanswers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(recordanswers == null || recordanswers.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid=:examid, nmExam=:nmExam, customerid=:customerid, custname=:custname, score=:score, avecorrectrate=:avecorrectrate, lasttime=:lasttime, num=:num, status=:status, anwers=:anwers, createtime=:createtime, costTimes=:costTimes, catalog=:catalog, examType=:examType, score4ques=:score4ques, kindid=:kindid, lens4exam=:lens4exam, lens4right=:lens4right, courseid=:courseid, lhubid=:lhubid WHERE id=:id");
            return super.batchUpdate2(sql.toString(), recordanswers);
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
                "	`customerid`  INT(11) NOT NULL," +
                "	`custname`  VARCHAR(128) NOT NULL," +
                "	`score`  INT(11) NOT NULL," +
                "	`avecorrectrate`  INT(11) NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`anwers`  BLOB NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`costTimes`  INT(11) NOT NULL," +
                "	`catalog`  BLOB NOT NULL," +
                "	`examType`  INT(11) NOT NULL," +
                "	`score4ques`  MEDIUMBLOB NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`lens4exam`  INT(11) NOT NULL," +
                "	`lens4right`  INT(11) NOT NULL," +
                "	`courseid`  INT(11) NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `examid_cust_id` (`examid`, `customerid`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `courseid` (`courseid`)" +
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
                "	`customerid`  INT(11) NOT NULL," +
                "	`custname`  VARCHAR(128) NOT NULL," +
                "	`score`  INT(11) NOT NULL," +
                "	`avecorrectrate`  INT(11) NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`anwers`  BLOB NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`costTimes`  INT(11) NOT NULL," +
                "	`catalog`  BLOB NOT NULL," +
                "	`examType`  INT(11) NOT NULL," +
                "	`score4ques`  MEDIUMBLOB NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`lens4exam`  INT(11) NOT NULL," +
                "	`lens4right`  INT(11) NOT NULL," +
                "	`courseid`  INT(11) NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `examid_cust_id` (`examid`, `customerid`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `courseid` (`courseid`)" +
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
