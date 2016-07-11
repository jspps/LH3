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

//learnhall3_design - examcatalog
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExamcatalogDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ExamcatalogDAO.class);

    public static final String TABLE = "examcatalog";
    public static String TABLENAME = "examcatalog";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "examid", "serial", "catalogType", "gid", "bigtypes", "isSubjective", "num", "everyScore", "title", "status", "createtime", "parentid"};
    public static String coulmns = "id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid";
    public static String coulmns2 = "examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid";

    public ExamcatalogDAO(Connection conn) {
        super(conn);
    }

    public ExamcatalogDAO(DataSource ds) {
        super(ds);
    }

    public ExamcatalogDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Examcatalog examcatalog) {
        return insert(examcatalog, TABLENAME);
    }

    public int insert(final Examcatalog examcatalog, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            examcatalog.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid) VALUES (:examid, :serial, :catalogType, :gid, :bigtypes, :isSubjective, :num, :everyScore, :title, :status, :createtime, :parentid)");
            Map map = super.insert(sql.toString(), examcatalog);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Examcatalog examcatalog) {
        return asyncInsert(examcatalog, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Examcatalog examcatalog, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(examcatalog, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Examcatalog examcatalog) {
        return asyncInsert2(examcatalog, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Examcatalog examcatalog, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(examcatalog, TABLENAME2);
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

    public int insert2(final Examcatalog examcatalog) {
        return insert2(examcatalog, TABLENAME);
    }

    public int insert2(final Examcatalog examcatalog, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            examcatalog.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid) VALUES (:id, :examid, :serial, :catalogType, :gid, :bigtypes, :isSubjective, :num, :everyScore, :title, :status, :createtime, :parentid)");
            Map map = super.insert(sql.toString(), examcatalog);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Examcatalog> examcatalogs) {
        return insert(examcatalogs, TABLENAME);
    }

    public int[] insert(final List<Examcatalog> examcatalogs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(examcatalogs == null || examcatalogs.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid) VALUES (:examid, :serial, :catalogType, :gid, :bigtypes, :isSubjective, :num, :everyScore, :title, :status, :createtime, :parentid)");
            return super.batchInsert(sql.toString(), examcatalogs);
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

    public int deleteInBeans(final List<Examcatalog> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Examcatalog> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Examcatalog examcatalog = beans.get(i);
                int id = examcatalog.id;
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

    public List<Examcatalog> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Examcatalog> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Examcatalog.class);
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
            sql.append("SELECT id, examid, parentid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Examcatalog> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Examcatalog> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Examcatalog.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Examcatalog> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Examcatalog> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
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

    public List<Examcatalog> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Examcatalog> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Examcatalog.class);
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

    public Examcatalog last() {
        return last(TABLENAME);
    }

    public Examcatalog last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Examcatalog.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Examcatalog> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Examcatalog> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Examcatalog> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Examcatalog> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
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

    public Examcatalog selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Examcatalog selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Examcatalog.class);
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

    public Examcatalog selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Examcatalog selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Examcatalog.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByParentid(final Integer parentid) {
        return countByParentid(parentid, TABLENAME);
    }

    public int countByParentid(final Integer parentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ");
            Map params = newMap();
            params.put("parentid", parentid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Examcatalog> selectByParentid(final Integer parentid) {
        return selectByParentid(parentid, TABLENAME);
    }

    public List<Examcatalog> selectByParentid(final Integer parentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY id ");
            Map params = newMap();
            params.put("parentid", parentid);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByParentidPKs(final Integer parentid) {
        return selectByParentidPKs(parentid, TABLENAME);
    }

    public List<Integer> selectByParentidPKs(final Integer parentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY id ");
            Map params = newMap();
            params.put("parentid", parentid);
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

    public List<Examcatalog> selectPageByParentid(final Integer parentid, final int begin, final int num) {
        return selectPageByParentid(parentid, begin, num, TABLENAME);
    }

    public List<Examcatalog> selectPageByParentid(final Integer parentid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("parentid", parentid);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByParentidPKs(final Integer parentid, final int begin, final int num) {
        return selectPageByParentidPKs(parentid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByParentidPKs(final Integer parentid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("parentid", parentid);
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

    public List<Examcatalog> selectByExamid(final Integer examid) {
        return selectByExamid(examid, TABLENAME);
    }

    public List<Examcatalog> selectByExamid(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id ");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
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

    public List<Examcatalog> selectPageByExamid(final Integer examid, final int begin, final int num) {
        return selectPageByExamid(examid, begin, num, TABLENAME);
    }

    public List<Examcatalog> selectPageByExamid(final Integer examid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Examcatalog.class);
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

    public List<Examcatalog> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Examcatalog> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Examcatalog.class);
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

    public int updateByKey(final Examcatalog examcatalog) {
        return updateByKey(examcatalog, TABLENAME);
    }

    public int updateByKey(final Examcatalog examcatalog, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = examcatalog.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), examcatalog);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Examcatalog examcatalog) {
        return asyncUpdate(examcatalog, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Examcatalog examcatalog, final String TABLENAME2) {
        try {

            String _ustr = examcatalog.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, examcatalog);
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

    public int updateCatalogTypeByKey(final int catalogType, final int id){
        return updateCatalogTypeByKey(catalogType, id, TABLENAME);
    }

    public int updateCatalogTypeByKey(final int catalogType, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalogType=catalogType+:catalogType WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("catalogType", catalogType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalogTypeWithMinByKey(final int id, final int catalogType, final int _min){
        return updateCatalogTypeWithMinByKey(id, catalogType, _min, TABLENAME);
    }

    public int updateCatalogTypeWithMinByKey(final int id, final int catalogType, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalogType = (select case when catalogType+:catalogType<=:_min then :_min else catalogType+:catalogType end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("catalogType", catalogType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalogTypeWithMinInKeys(final List<Integer> keys, final int catalogType, final int _min){
        return updateCatalogTypeWithMinInKeys(keys, catalogType, _min, TABLENAME);
    }

    public int updateCatalogTypeWithMinInKeys(final List<Integer> keys, final int catalogType, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalogType = (select case when catalogType+:catalogType<=:_min then :_min else catalogType+:catalogType end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("catalogType", catalogType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalogTypeWithMaxByKey(final int id, final int catalogType, final int _max){
        return updateCatalogTypeWithMaxByKey(id, catalogType, _max, TABLENAME);
    }

    public int updateCatalogTypeWithMaxByKey(final int id, final int catalogType, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalogType = (select case when catalogType+:catalogType>=:_max then :_max else catalogType+:catalogType end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("catalogType", catalogType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalogTypeWithMaxInKeys(final List<Integer> keys, final int catalogType, final int _max){
        return updateCatalogTypeWithMaxInKeys(keys, catalogType, _max, TABLENAME);
    }

    public int updateCatalogTypeWithMaxInKeys(final List<Integer> keys, final int catalogType, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET catalogType = (select case when catalogType+:catalogType>=:_max then :_max else catalogType+:catalogType end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("catalogType", catalogType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCatalogTypeWithMinMaxByKey(final int id, final int catalogType, final int _min, final int _max){
        return updateCatalogTypeWithMinMaxByKey(id, catalogType, _min, _max, TABLENAME);
    }

    public int updateCatalogTypeWithMinMaxByKey(final int id, final int catalogType, final int _min, final int _max, final String TABLENAME2){
        if( catalogType < 0 ) {
            return updateCatalogTypeWithMinByKey(id, catalogType, _min, TABLENAME2);
        } else {
            return updateCatalogTypeWithMaxByKey(id, catalogType, _max, TABLENAME2);
        }
    }

    public int updateCatalogTypeWithMinMaxInKeys(final List<Integer> keys, final int catalogType, final int _min, final int _max){
        return updateCatalogTypeWithMinMaxInKeys(keys, catalogType, _min, _max, TABLENAME);
    }

    public int updateCatalogTypeWithMinMaxInKeys(final List<Integer> keys, final int catalogType, final int _min, final int _max, final String TABLENAME2){
        if( catalogType < 0 ) {
            return updateCatalogTypeWithMinInKeys(keys, catalogType, _min, TABLENAME2);
        } else {
            return updateCatalogTypeWithMaxInKeys(keys, catalogType, _max, TABLENAME2);
        }
    }

    public int updateGidByKey(final int gid, final int id){
        return updateGidByKey(gid, id, TABLENAME);
    }

    public int updateGidByKey(final int gid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid=gid+:gid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinByKey(final int id, final int gid, final int _min){
        return updateGidWithMinByKey(id, gid, _min, TABLENAME);
    }

    public int updateGidWithMinByKey(final int id, final int gid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid<=:_min then :_min else gid+:gid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinInKeys(final List<Integer> keys, final int gid, final int _min){
        return updateGidWithMinInKeys(keys, gid, _min, TABLENAME);
    }

    public int updateGidWithMinInKeys(final List<Integer> keys, final int gid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid<=:_min then :_min else gid+:gid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMaxByKey(final int id, final int gid, final int _max){
        return updateGidWithMaxByKey(id, gid, _max, TABLENAME);
    }

    public int updateGidWithMaxByKey(final int id, final int gid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid>=:_max then :_max else gid+:gid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMaxInKeys(final List<Integer> keys, final int gid, final int _max){
        return updateGidWithMaxInKeys(keys, gid, _max, TABLENAME);
    }

    public int updateGidWithMaxInKeys(final List<Integer> keys, final int gid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid>=:_max then :_max else gid+:gid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinMaxByKey(final int id, final int gid, final int _min, final int _max){
        return updateGidWithMinMaxByKey(id, gid, _min, _max, TABLENAME);
    }

    public int updateGidWithMinMaxByKey(final int id, final int gid, final int _min, final int _max, final String TABLENAME2){
        if( gid < 0 ) {
            return updateGidWithMinByKey(id, gid, _min, TABLENAME2);
        } else {
            return updateGidWithMaxByKey(id, gid, _max, TABLENAME2);
        }
    }

    public int updateGidWithMinMaxInKeys(final List<Integer> keys, final int gid, final int _min, final int _max){
        return updateGidWithMinMaxInKeys(keys, gid, _min, _max, TABLENAME);
    }

    public int updateGidWithMinMaxInKeys(final List<Integer> keys, final int gid, final int _min, final int _max, final String TABLENAME2){
        if( gid < 0 ) {
            return updateGidWithMinInKeys(keys, gid, _min, TABLENAME2);
        } else {
            return updateGidWithMaxInKeys(keys, gid, _max, TABLENAME2);
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

    public int updateEveryScoreByKey(final int everyScore, final int id){
        return updateEveryScoreByKey(everyScore, id, TABLENAME);
    }

    public int updateEveryScoreByKey(final int everyScore, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET everyScore=everyScore+:everyScore WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("everyScore", everyScore);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEveryScoreWithMinByKey(final int id, final int everyScore, final int _min){
        return updateEveryScoreWithMinByKey(id, everyScore, _min, TABLENAME);
    }

    public int updateEveryScoreWithMinByKey(final int id, final int everyScore, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET everyScore = (select case when everyScore+:everyScore<=:_min then :_min else everyScore+:everyScore end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("everyScore", everyScore);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEveryScoreWithMinInKeys(final List<Integer> keys, final int everyScore, final int _min){
        return updateEveryScoreWithMinInKeys(keys, everyScore, _min, TABLENAME);
    }

    public int updateEveryScoreWithMinInKeys(final List<Integer> keys, final int everyScore, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET everyScore = (select case when everyScore+:everyScore<=:_min then :_min else everyScore+:everyScore end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("everyScore", everyScore);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEveryScoreWithMaxByKey(final int id, final int everyScore, final int _max){
        return updateEveryScoreWithMaxByKey(id, everyScore, _max, TABLENAME);
    }

    public int updateEveryScoreWithMaxByKey(final int id, final int everyScore, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET everyScore = (select case when everyScore+:everyScore>=:_max then :_max else everyScore+:everyScore end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("everyScore", everyScore);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEveryScoreWithMaxInKeys(final List<Integer> keys, final int everyScore, final int _max){
        return updateEveryScoreWithMaxInKeys(keys, everyScore, _max, TABLENAME);
    }

    public int updateEveryScoreWithMaxInKeys(final List<Integer> keys, final int everyScore, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET everyScore = (select case when everyScore+:everyScore>=:_max then :_max else everyScore+:everyScore end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("everyScore", everyScore);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEveryScoreWithMinMaxByKey(final int id, final int everyScore, final int _min, final int _max){
        return updateEveryScoreWithMinMaxByKey(id, everyScore, _min, _max, TABLENAME);
    }

    public int updateEveryScoreWithMinMaxByKey(final int id, final int everyScore, final int _min, final int _max, final String TABLENAME2){
        if( everyScore < 0 ) {
            return updateEveryScoreWithMinByKey(id, everyScore, _min, TABLENAME2);
        } else {
            return updateEveryScoreWithMaxByKey(id, everyScore, _max, TABLENAME2);
        }
    }

    public int updateEveryScoreWithMinMaxInKeys(final List<Integer> keys, final int everyScore, final int _min, final int _max){
        return updateEveryScoreWithMinMaxInKeys(keys, everyScore, _min, _max, TABLENAME);
    }

    public int updateEveryScoreWithMinMaxInKeys(final List<Integer> keys, final int everyScore, final int _min, final int _max, final String TABLENAME2){
        if( everyScore < 0 ) {
            return updateEveryScoreWithMinInKeys(keys, everyScore, _min, TABLENAME2);
        } else {
            return updateEveryScoreWithMaxInKeys(keys, everyScore, _max, TABLENAME2);
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

    public int updateParentidByKey(final int parentid, final int id){
        return updateParentidByKey(parentid, id, TABLENAME);
    }

    public int updateParentidByKey(final int parentid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid=parentid+:parentid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("parentid", parentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateParentidWithMinByKey(final int id, final int parentid, final int _min){
        return updateParentidWithMinByKey(id, parentid, _min, TABLENAME);
    }

    public int updateParentidWithMinByKey(final int id, final int parentid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid<=:_min then :_min else parentid+:parentid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("parentid", parentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateParentidWithMinInKeys(final List<Integer> keys, final int parentid, final int _min){
        return updateParentidWithMinInKeys(keys, parentid, _min, TABLENAME);
    }

    public int updateParentidWithMinInKeys(final List<Integer> keys, final int parentid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid<=:_min then :_min else parentid+:parentid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("parentid", parentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateParentidWithMaxByKey(final int id, final int parentid, final int _max){
        return updateParentidWithMaxByKey(id, parentid, _max, TABLENAME);
    }

    public int updateParentidWithMaxByKey(final int id, final int parentid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid>=:_max then :_max else parentid+:parentid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("parentid", parentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateParentidWithMaxInKeys(final List<Integer> keys, final int parentid, final int _max){
        return updateParentidWithMaxInKeys(keys, parentid, _max, TABLENAME);
    }

    public int updateParentidWithMaxInKeys(final List<Integer> keys, final int parentid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid>=:_max then :_max else parentid+:parentid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("parentid", parentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateParentidWithMinMaxByKey(final int id, final int parentid, final int _min, final int _max){
        return updateParentidWithMinMaxByKey(id, parentid, _min, _max, TABLENAME);
    }

    public int updateParentidWithMinMaxByKey(final int id, final int parentid, final int _min, final int _max, final String TABLENAME2){
        if( parentid < 0 ) {
            return updateParentidWithMinByKey(id, parentid, _min, TABLENAME2);
        } else {
            return updateParentidWithMaxByKey(id, parentid, _max, TABLENAME2);
        }
    }

    public int updateParentidWithMinMaxInKeys(final List<Integer> keys, final int parentid, final int _min, final int _max){
        return updateParentidWithMinMaxInKeys(keys, parentid, _min, _max, TABLENAME);
    }

    public int updateParentidWithMinMaxInKeys(final List<Integer> keys, final int parentid, final int _min, final int _max, final String TABLENAME2){
        if( parentid < 0 ) {
            return updateParentidWithMinInKeys(keys, parentid, _min, TABLENAME2);
        } else {
            return updateParentidWithMaxInKeys(keys, parentid, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Examcatalog> examcatalogs) {
        return updateByKey(examcatalogs, TABLENAME);
    }

    public int[] updateByKey (final List<Examcatalog> examcatalogs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(examcatalogs == null || examcatalogs.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid=:examid, serial=:serial, catalogType=:catalogType, gid=:gid, bigtypes=:bigtypes, isSubjective=:isSubjective, num=:num, everyScore=:everyScore, title=:title, status=:status, createtime=:createtime, parentid=:parentid WHERE id=:id");
            return super.batchUpdate2(sql.toString(), examcatalogs);
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
                "	`serial`  VARCHAR(32) NOT NULL," +
                "	`catalogType`  INT(11) NOT NULL," +
                "	`gid`  INT(11) NOT NULL," +
                "	`bigtypes`  TINYTEXT NOT NULL," +
                "	`isSubjective`  BIT(1) NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`everyScore`  INT(11) NOT NULL," +
                "	`title`  TEXT NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`parentid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `parentid` (`parentid`)" +
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
                "	`serial`  VARCHAR(32) NOT NULL," +
                "	`catalogType`  INT(11) NOT NULL," +
                "	`gid`  INT(11) NOT NULL," +
                "	`bigtypes`  TINYTEXT NOT NULL," +
                "	`isSubjective`  BIT(1) NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`everyScore`  INT(11) NOT NULL," +
                "	`title`  TEXT NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`parentid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `parentid` (`parentid`)" +
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
