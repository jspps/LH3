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

//learnhall3_design - record4seeanswer
@SuppressWarnings({"rawtypes", "unchecked"})
public class Record4seeanswerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Record4seeanswerDAO.class);

    public static final String TABLE = "record4seeanswer";
    public static String TABLENAME = "record4seeanswer";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "askid", "custid", "createtime"};
    public static String coulmns = "id, askid, custid, createtime";
    public static String coulmns2 = "askid, custid, createtime";

    public Record4seeanswerDAO(Connection conn) {
        super(conn);
    }

    public Record4seeanswerDAO(DataSource ds) {
        super(ds);
    }

    public Record4seeanswerDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Record4seeanswer record4seeanswer) {
        return insert(record4seeanswer, TABLENAME);
    }

    public int insert(final Record4seeanswer record4seeanswer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            record4seeanswer.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (askid, custid, createtime) VALUES (:askid, :custid, :createtime)");
            Map map = super.insert(sql.toString(), record4seeanswer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Record4seeanswer record4seeanswer) {
        return asyncInsert(record4seeanswer, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Record4seeanswer record4seeanswer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(record4seeanswer, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Record4seeanswer record4seeanswer) {
        return asyncInsert2(record4seeanswer, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Record4seeanswer record4seeanswer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(record4seeanswer, TABLENAME2);
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

    public int insert2(final Record4seeanswer record4seeanswer) {
        return insert2(record4seeanswer, TABLENAME);
    }

    public int insert2(final Record4seeanswer record4seeanswer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            record4seeanswer.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, askid, custid, createtime) VALUES (:id, :askid, :custid, :createtime)");
            Map map = super.insert(sql.toString(), record4seeanswer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Record4seeanswer> record4seeanswers) {
        return insert(record4seeanswers, TABLENAME);
    }

    public int[] insert(final List<Record4seeanswer> record4seeanswers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(record4seeanswers == null || record4seeanswers.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (askid, custid, createtime) VALUES (:askid, :custid, :createtime)");
            return super.batchInsert(sql.toString(), record4seeanswers);
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

    public int deleteInBeans(final List<Record4seeanswer> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Record4seeanswer> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Record4seeanswer record4seeanswer = beans.get(i);
                int id = record4seeanswer.id;
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

    public List<Record4seeanswer> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Record4seeanswer> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Record4seeanswer.class);
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
            sql.append("SELECT id, askid, custid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Record4seeanswer> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Record4seeanswer> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Record4seeanswer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Record4seeanswer> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Record4seeanswer> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Record4seeanswer.class);
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

    public List<Record4seeanswer> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Record4seeanswer> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Record4seeanswer.class);
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

    public Record4seeanswer last() {
        return last(TABLENAME);
    }

    public Record4seeanswer last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Record4seeanswer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Record4seeanswer> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Record4seeanswer> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Record4seeanswer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Record4seeanswer> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Record4seeanswer> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Record4seeanswer.class);
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

    public Record4seeanswer selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Record4seeanswer selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Record4seeanswer.class);
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

    public Record4seeanswer selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Record4seeanswer selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Record4seeanswer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Record4seeanswer selectByAskidCustid(final Integer askid, Integer custid) {
        return selectByAskidCustid(askid, custid, TABLENAME);
    }

    public Record4seeanswer selectByAskidCustid(final Integer askid, Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" WHERE askid=:askid AND custid=:custid");
            Map params = newMap();
            params.put("askid", askid);
            params.put("custid", custid);
            return super.queryForObject(sql.toString(), params, Record4seeanswer.class);
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

    public List<Record4seeanswer> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Record4seeanswer> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, askid, custid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Record4seeanswer.class);
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

    public int updateByKey(final Record4seeanswer record4seeanswer) {
        return updateByKey(record4seeanswer, TABLENAME);
    }

    public int updateByKey(final Record4seeanswer record4seeanswer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = record4seeanswer.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), record4seeanswer);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Record4seeanswer record4seeanswer) {
        return asyncUpdate(record4seeanswer, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Record4seeanswer record4seeanswer, final String TABLENAME2) {
        try {

            String _ustr = record4seeanswer.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, record4seeanswer);
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

    public int updateAskidByKey(final int askid, final int id){
        return updateAskidByKey(askid, id, TABLENAME);
    }

    public int updateAskidByKey(final int askid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET askid=askid+:askid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("askid", askid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAskidWithMinByKey(final int id, final int askid, final int _min){
        return updateAskidWithMinByKey(id, askid, _min, TABLENAME);
    }

    public int updateAskidWithMinByKey(final int id, final int askid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET askid = (select case when askid+:askid<=:_min then :_min else askid+:askid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("askid", askid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAskidWithMinInKeys(final List<Integer> keys, final int askid, final int _min){
        return updateAskidWithMinInKeys(keys, askid, _min, TABLENAME);
    }

    public int updateAskidWithMinInKeys(final List<Integer> keys, final int askid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET askid = (select case when askid+:askid<=:_min then :_min else askid+:askid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("askid", askid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAskidWithMaxByKey(final int id, final int askid, final int _max){
        return updateAskidWithMaxByKey(id, askid, _max, TABLENAME);
    }

    public int updateAskidWithMaxByKey(final int id, final int askid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET askid = (select case when askid+:askid>=:_max then :_max else askid+:askid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("askid", askid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAskidWithMaxInKeys(final List<Integer> keys, final int askid, final int _max){
        return updateAskidWithMaxInKeys(keys, askid, _max, TABLENAME);
    }

    public int updateAskidWithMaxInKeys(final List<Integer> keys, final int askid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET askid = (select case when askid+:askid>=:_max then :_max else askid+:askid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("askid", askid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAskidWithMinMaxByKey(final int id, final int askid, final int _min, final int _max){
        return updateAskidWithMinMaxByKey(id, askid, _min, _max, TABLENAME);
    }

    public int updateAskidWithMinMaxByKey(final int id, final int askid, final int _min, final int _max, final String TABLENAME2){
        if( askid < 0 ) {
            return updateAskidWithMinByKey(id, askid, _min, TABLENAME2);
        } else {
            return updateAskidWithMaxByKey(id, askid, _max, TABLENAME2);
        }
    }

    public int updateAskidWithMinMaxInKeys(final List<Integer> keys, final int askid, final int _min, final int _max){
        return updateAskidWithMinMaxInKeys(keys, askid, _min, _max, TABLENAME);
    }

    public int updateAskidWithMinMaxInKeys(final List<Integer> keys, final int askid, final int _min, final int _max, final String TABLENAME2){
        if( askid < 0 ) {
            return updateAskidWithMinInKeys(keys, askid, _min, TABLENAME2);
        } else {
            return updateAskidWithMaxInKeys(keys, askid, _max, TABLENAME2);
        }
    }

    public int updateCustidByKey(final int custid, final int id){
        return updateCustidByKey(custid, id, TABLENAME);
    }

    public int updateCustidByKey(final int custid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET custid=custid+:custid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("custid", custid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustidWithMinByKey(final int id, final int custid, final int _min){
        return updateCustidWithMinByKey(id, custid, _min, TABLENAME);
    }

    public int updateCustidWithMinByKey(final int id, final int custid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET custid = (select case when custid+:custid<=:_min then :_min else custid+:custid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("custid", custid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustidWithMinInKeys(final List<Integer> keys, final int custid, final int _min){
        return updateCustidWithMinInKeys(keys, custid, _min, TABLENAME);
    }

    public int updateCustidWithMinInKeys(final List<Integer> keys, final int custid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET custid = (select case when custid+:custid<=:_min then :_min else custid+:custid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("custid", custid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustidWithMaxByKey(final int id, final int custid, final int _max){
        return updateCustidWithMaxByKey(id, custid, _max, TABLENAME);
    }

    public int updateCustidWithMaxByKey(final int id, final int custid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET custid = (select case when custid+:custid>=:_max then :_max else custid+:custid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("custid", custid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustidWithMaxInKeys(final List<Integer> keys, final int custid, final int _max){
        return updateCustidWithMaxInKeys(keys, custid, _max, TABLENAME);
    }

    public int updateCustidWithMaxInKeys(final List<Integer> keys, final int custid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET custid = (select case when custid+:custid>=:_max then :_max else custid+:custid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("custid", custid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustidWithMinMaxByKey(final int id, final int custid, final int _min, final int _max){
        return updateCustidWithMinMaxByKey(id, custid, _min, _max, TABLENAME);
    }

    public int updateCustidWithMinMaxByKey(final int id, final int custid, final int _min, final int _max, final String TABLENAME2){
        if( custid < 0 ) {
            return updateCustidWithMinByKey(id, custid, _min, TABLENAME2);
        } else {
            return updateCustidWithMaxByKey(id, custid, _max, TABLENAME2);
        }
    }

    public int updateCustidWithMinMaxInKeys(final List<Integer> keys, final int custid, final int _min, final int _max){
        return updateCustidWithMinMaxInKeys(keys, custid, _min, _max, TABLENAME);
    }

    public int updateCustidWithMinMaxInKeys(final List<Integer> keys, final int custid, final int _min, final int _max, final String TABLENAME2){
        if( custid < 0 ) {
            return updateCustidWithMinInKeys(keys, custid, _min, TABLENAME2);
        } else {
            return updateCustidWithMaxInKeys(keys, custid, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Record4seeanswer> record4seeanswers) {
        return updateByKey(record4seeanswers, TABLENAME);
    }

    public int[] updateByKey (final List<Record4seeanswer> record4seeanswers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(record4seeanswers == null || record4seeanswers.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET askid=:askid, custid=:custid, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), record4seeanswers);
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
                "	`askid`  INT(11) NOT NULL," +
                "	`custid`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `askid` (`askid`, `custid`)" +
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
                "	`askid`  INT(11) NOT NULL," +
                "	`custid`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `askid` (`askid`, `custid`)" +
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
