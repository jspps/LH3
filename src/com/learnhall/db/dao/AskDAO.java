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

//learnhall3_design - ask
@SuppressWarnings({"rawtypes", "unchecked"})
public class AskDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(AskDAO.class);

    public static final String TABLE = "ask";
    public static String TABLENAME = "ask";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "customerid", "title", "rewardamount", "expirationtime", "status", "tag", "answerid", "createtime", "status_opt"};
    public static String coulmns = "id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt";
    public static String coulmns2 = "customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt";

    public AskDAO(Connection conn) {
        super(conn);
    }

    public AskDAO(DataSource ds) {
        super(ds);
    }

    public AskDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Ask ask) {
        return insert(ask, TABLENAME);
    }

    public int insert(final Ask ask, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            ask.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt) VALUES (:customerid, :title, :rewardamount, :expirationtime, :status, :tag, :answerid, :createtime, :status_opt)");
            Map map = super.insert(sql.toString(), ask);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Ask ask) {
        return asyncInsert(ask, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Ask ask, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(ask, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Ask ask) {
        return asyncInsert2(ask, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Ask ask, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(ask, TABLENAME2);
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

    public int insert2(final Ask ask) {
        return insert2(ask, TABLENAME);
    }

    public int insert2(final Ask ask, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            ask.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt) VALUES (:id, :customerid, :title, :rewardamount, :expirationtime, :status, :tag, :answerid, :createtime, :status_opt)");
            Map map = super.insert(sql.toString(), ask);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Ask> asks) {
        return insert(asks, TABLENAME);
    }

    public int[] insert(final List<Ask> asks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(asks == null || asks.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt) VALUES (:customerid, :title, :rewardamount, :expirationtime, :status, :tag, :answerid, :createtime, :status_opt)");
            return super.batchInsert(sql.toString(), asks);
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

    public int deleteInBeans(final List<Ask> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Ask> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Ask ask = beans.get(i);
                int id = ask.id;
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

    public List<Ask> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Ask> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Ask.class);
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
            sql.append("SELECT id, customerid, answerid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ask> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Ask> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Ask.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ask> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Ask> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Ask.class);
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

    public List<Ask> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Ask> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Ask.class);
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

    public Ask last() {
        return last(TABLENAME);
    }

    public Ask last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Ask.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ask> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Ask> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Ask.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ask> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Ask> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Ask.class);
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

    public Ask selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Ask selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Ask.class);
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

    public List<Ask> selectByCustomerid(final Integer customerid) {
        return selectByCustomerid(customerid, TABLENAME);
    }

    public List<Ask> selectByCustomerid(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id ");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Ask.class);
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

    public List<Ask> selectPageByCustomerid(final Integer customerid, final int begin, final int num) {
        return selectPageByCustomerid(customerid, begin, num, TABLENAME);
    }

    public List<Ask> selectPageByCustomerid(final Integer customerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Ask.class);
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

    public Ask selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Ask selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Ask.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByAnswerid(final Integer answerid) {
        return countByAnswerid(answerid, TABLENAME);
    }

    public int countByAnswerid(final Integer answerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE answerid = :answerid ");
            Map params = newMap();
            params.put("answerid", answerid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ask> selectByAnswerid(final Integer answerid) {
        return selectByAnswerid(answerid, TABLENAME);
    }

    public List<Ask> selectByAnswerid(final Integer answerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE answerid = :answerid ORDER BY id ");
            Map params = newMap();
            params.put("answerid", answerid);
            return super.queryForList(sql.toString(), params, Ask.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByAnsweridPKs(final Integer answerid) {
        return selectByAnsweridPKs(answerid, TABLENAME);
    }

    public List<Integer> selectByAnsweridPKs(final Integer answerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE answerid = :answerid ORDER BY id ");
            Map params = newMap();
            params.put("answerid", answerid);
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

    public List<Ask> selectPageByAnswerid(final Integer answerid, final int begin, final int num) {
        return selectPageByAnswerid(answerid, begin, num, TABLENAME);
    }

    public List<Ask> selectPageByAnswerid(final Integer answerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" WHERE answerid = :answerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("answerid", answerid);
            return super.queryForList(sql.toString(), params, Ask.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByAnsweridPKs(final Integer answerid, final int begin, final int num) {
        return selectPageByAnsweridPKs(answerid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByAnsweridPKs(final Integer answerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE answerid = :answerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("answerid", answerid);
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

    public List<Ask> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Ask> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Ask.class);
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

    public int updateByKey(final Ask ask) {
        return updateByKey(ask, TABLENAME);
    }

    public int updateByKey(final Ask ask, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = ask.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), ask);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Ask ask) {
        return asyncUpdate(ask, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Ask ask, final String TABLENAME2) {
        try {

            String _ustr = ask.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, ask);
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

    public int updateRewardamountByKey(final double rewardamount, final int id){
        return updateRewardamountByKey(rewardamount, id, TABLENAME);
    }

    public int updateRewardamountByKey(final double rewardamount, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET rewardamount=rewardamount+:rewardamount WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("rewardamount", rewardamount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRewardamountWithMinByKey(final int id, final double rewardamount, final double _min){
        return updateRewardamountWithMinByKey(id, rewardamount, _min, TABLENAME);
    }

    public int updateRewardamountWithMinByKey(final int id, final double rewardamount, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET rewardamount = (select case when rewardamount+:rewardamount<=:_min then :_min else rewardamount+:rewardamount end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("rewardamount", rewardamount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRewardamountWithMinInKeys(final List<Integer> keys, final double rewardamount, final double _min){
        return updateRewardamountWithMinInKeys(keys, rewardamount, _min, TABLENAME);
    }

    public int updateRewardamountWithMinInKeys(final List<Integer> keys, final double rewardamount, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET rewardamount = (select case when rewardamount+:rewardamount<=:_min then :_min else rewardamount+:rewardamount end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("rewardamount", rewardamount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRewardamountWithMaxByKey(final int id, final double rewardamount, final double _max){
        return updateRewardamountWithMaxByKey(id, rewardamount, _max, TABLENAME);
    }

    public int updateRewardamountWithMaxByKey(final int id, final double rewardamount, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET rewardamount = (select case when rewardamount+:rewardamount>=:_max then :_max else rewardamount+:rewardamount end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("rewardamount", rewardamount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRewardamountWithMaxInKeys(final List<Integer> keys, final double rewardamount, final double _max){
        return updateRewardamountWithMaxInKeys(keys, rewardamount, _max, TABLENAME);
    }

    public int updateRewardamountWithMaxInKeys(final List<Integer> keys, final double rewardamount, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET rewardamount = (select case when rewardamount+:rewardamount>=:_max then :_max else rewardamount+:rewardamount end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("rewardamount", rewardamount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRewardamountWithMinMaxByKey(final int id, final double rewardamount, final double _min, final double _max){
        return updateRewardamountWithMinMaxByKey(id, rewardamount, _min, _max, TABLENAME);
    }

    public int updateRewardamountWithMinMaxByKey(final int id, final double rewardamount, final double _min, final double _max, final String TABLENAME2){
        if( rewardamount < 0 ) {
            return updateRewardamountWithMinByKey(id, rewardamount, _min, TABLENAME2);
        } else {
            return updateRewardamountWithMaxByKey(id, rewardamount, _max, TABLENAME2);
        }
    }

    public int updateRewardamountWithMinMaxInKeys(final List<Integer> keys, final double rewardamount, final double _min, final double _max){
        return updateRewardamountWithMinMaxInKeys(keys, rewardamount, _min, _max, TABLENAME);
    }

    public int updateRewardamountWithMinMaxInKeys(final List<Integer> keys, final double rewardamount, final double _min, final double _max, final String TABLENAME2){
        if( rewardamount < 0 ) {
            return updateRewardamountWithMinInKeys(keys, rewardamount, _min, TABLENAME2);
        } else {
            return updateRewardamountWithMaxInKeys(keys, rewardamount, _max, TABLENAME2);
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

    public int updateAnsweridByKey(final int answerid, final int id){
        return updateAnsweridByKey(answerid, id, TABLENAME);
    }

    public int updateAnsweridByKey(final int answerid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answerid=answerid+:answerid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("answerid", answerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnsweridWithMinByKey(final int id, final int answerid, final int _min){
        return updateAnsweridWithMinByKey(id, answerid, _min, TABLENAME);
    }

    public int updateAnsweridWithMinByKey(final int id, final int answerid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answerid = (select case when answerid+:answerid<=:_min then :_min else answerid+:answerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("answerid", answerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnsweridWithMinInKeys(final List<Integer> keys, final int answerid, final int _min){
        return updateAnsweridWithMinInKeys(keys, answerid, _min, TABLENAME);
    }

    public int updateAnsweridWithMinInKeys(final List<Integer> keys, final int answerid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answerid = (select case when answerid+:answerid<=:_min then :_min else answerid+:answerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("answerid", answerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnsweridWithMaxByKey(final int id, final int answerid, final int _max){
        return updateAnsweridWithMaxByKey(id, answerid, _max, TABLENAME);
    }

    public int updateAnsweridWithMaxByKey(final int id, final int answerid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answerid = (select case when answerid+:answerid>=:_max then :_max else answerid+:answerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("answerid", answerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnsweridWithMaxInKeys(final List<Integer> keys, final int answerid, final int _max){
        return updateAnsweridWithMaxInKeys(keys, answerid, _max, TABLENAME);
    }

    public int updateAnsweridWithMaxInKeys(final List<Integer> keys, final int answerid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answerid = (select case when answerid+:answerid>=:_max then :_max else answerid+:answerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("answerid", answerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnsweridWithMinMaxByKey(final int id, final int answerid, final int _min, final int _max){
        return updateAnsweridWithMinMaxByKey(id, answerid, _min, _max, TABLENAME);
    }

    public int updateAnsweridWithMinMaxByKey(final int id, final int answerid, final int _min, final int _max, final String TABLENAME2){
        if( answerid < 0 ) {
            return updateAnsweridWithMinByKey(id, answerid, _min, TABLENAME2);
        } else {
            return updateAnsweridWithMaxByKey(id, answerid, _max, TABLENAME2);
        }
    }

    public int updateAnsweridWithMinMaxInKeys(final List<Integer> keys, final int answerid, final int _min, final int _max){
        return updateAnsweridWithMinMaxInKeys(keys, answerid, _min, _max, TABLENAME);
    }

    public int updateAnsweridWithMinMaxInKeys(final List<Integer> keys, final int answerid, final int _min, final int _max, final String TABLENAME2){
        if( answerid < 0 ) {
            return updateAnsweridWithMinInKeys(keys, answerid, _min, TABLENAME2);
        } else {
            return updateAnsweridWithMaxInKeys(keys, answerid, _max, TABLENAME2);
        }
    }

    public int updateStatus_optByKey(final int status_opt, final int id){
        return updateStatus_optByKey(status_opt, id, TABLENAME);
    }

    public int updateStatus_optByKey(final int status_opt, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status_opt=status_opt+:status_opt WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("status_opt", status_opt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatus_optWithMinByKey(final int id, final int status_opt, final int _min){
        return updateStatus_optWithMinByKey(id, status_opt, _min, TABLENAME);
    }

    public int updateStatus_optWithMinByKey(final int id, final int status_opt, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status_opt = (select case when status_opt+:status_opt<=:_min then :_min else status_opt+:status_opt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("status_opt", status_opt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatus_optWithMinInKeys(final List<Integer> keys, final int status_opt, final int _min){
        return updateStatus_optWithMinInKeys(keys, status_opt, _min, TABLENAME);
    }

    public int updateStatus_optWithMinInKeys(final List<Integer> keys, final int status_opt, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status_opt = (select case when status_opt+:status_opt<=:_min then :_min else status_opt+:status_opt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("status_opt", status_opt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatus_optWithMaxByKey(final int id, final int status_opt, final int _max){
        return updateStatus_optWithMaxByKey(id, status_opt, _max, TABLENAME);
    }

    public int updateStatus_optWithMaxByKey(final int id, final int status_opt, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status_opt = (select case when status_opt+:status_opt>=:_max then :_max else status_opt+:status_opt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("status_opt", status_opt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatus_optWithMaxInKeys(final List<Integer> keys, final int status_opt, final int _max){
        return updateStatus_optWithMaxInKeys(keys, status_opt, _max, TABLENAME);
    }

    public int updateStatus_optWithMaxInKeys(final List<Integer> keys, final int status_opt, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status_opt = (select case when status_opt+:status_opt>=:_max then :_max else status_opt+:status_opt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("status_opt", status_opt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatus_optWithMinMaxByKey(final int id, final int status_opt, final int _min, final int _max){
        return updateStatus_optWithMinMaxByKey(id, status_opt, _min, _max, TABLENAME);
    }

    public int updateStatus_optWithMinMaxByKey(final int id, final int status_opt, final int _min, final int _max, final String TABLENAME2){
        if( status_opt < 0 ) {
            return updateStatus_optWithMinByKey(id, status_opt, _min, TABLENAME2);
        } else {
            return updateStatus_optWithMaxByKey(id, status_opt, _max, TABLENAME2);
        }
    }

    public int updateStatus_optWithMinMaxInKeys(final List<Integer> keys, final int status_opt, final int _min, final int _max){
        return updateStatus_optWithMinMaxInKeys(keys, status_opt, _min, _max, TABLENAME);
    }

    public int updateStatus_optWithMinMaxInKeys(final List<Integer> keys, final int status_opt, final int _min, final int _max, final String TABLENAME2){
        if( status_opt < 0 ) {
            return updateStatus_optWithMinInKeys(keys, status_opt, _min, TABLENAME2);
        } else {
            return updateStatus_optWithMaxInKeys(keys, status_opt, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Ask> asks) {
        return updateByKey(asks, TABLENAME);
    }

    public int[] updateByKey (final List<Ask> asks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(asks == null || asks.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid=:customerid, title=:title, rewardamount=:rewardamount, expirationtime=:expirationtime, status=:status, tag=:tag, answerid=:answerid, createtime=:createtime, status_opt=:status_opt WHERE id=:id");
            return super.batchUpdate2(sql.toString(), asks);
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
                "	`customerid`  INT(11) NOT NULL," +
                "	`title`  TEXT NOT NULL," +
                "	`rewardamount`  DOUBLE NOT NULL," +
                "	`expirationtime`  DATETIME NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`tag`  TINYTEXT NOT NULL," +
                "	`answerid`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`status_opt`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `${TABLENAME}id` (`answerid`)" +
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
                "	`customerid`  INT(11) NOT NULL," +
                "	`title`  TEXT NOT NULL," +
                "	`rewardamount`  DOUBLE NOT NULL," +
                "	`expirationtime`  DATETIME NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`tag`  TINYTEXT NOT NULL," +
                "	`answerid`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`status_opt`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `${TABLENAME}id` (`answerid`)" +
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
