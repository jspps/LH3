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

//learnhall3_design - cfgs
@SuppressWarnings({"rawtypes", "unchecked"})
public class CfgsDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(CfgsDAO.class);

    public static final String TABLE = "cfgs";
    public static String TABLENAME = "cfgs";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"cfgid", "name", "valStr", "valInt"};
    public static String coulmns = "cfgid, name, valStr, valInt";
    public static String coulmns2 = "name, valStr, valInt";

    public CfgsDAO(Connection conn) {
        super(conn);
    }

    public CfgsDAO(DataSource ds) {
        super(ds);
    }

    public CfgsDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Cfgs cfgs) {
        return insert(cfgs, TABLENAME);
    }

    public int insert(final Cfgs cfgs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            cfgs.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name, valStr, valInt) VALUES (:name, :valStr, :valInt)");
            Map map = super.insert(sql.toString(), cfgs);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Cfgs cfgs) {
        return asyncInsert(cfgs, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Cfgs cfgs, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(cfgs, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Cfgs cfgs) {
        return asyncInsert2(cfgs, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Cfgs cfgs, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(cfgs, TABLENAME2);
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

    public int insert2(final Cfgs cfgs) {
        return insert2(cfgs, TABLENAME);
    }

    public int insert2(final Cfgs cfgs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            cfgs.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (cfgid, name, valStr, valInt) VALUES (:cfgid, :name, :valStr, :valInt)");
            Map map = super.insert(sql.toString(), cfgs);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Cfgs> cfgss) {
        return insert(cfgss, TABLENAME);
    }

    public int[] insert(final List<Cfgs> cfgss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(cfgss == null || cfgss.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name, valStr, valInt) VALUES (:name, :valStr, :valInt)");
            return super.batchInsert(sql.toString(), cfgss);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int cfgid) {
        return deleteByKey(cfgid, TABLENAME);
    }

    public int deleteByKey(final int cfgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cfgid=:cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int cfgid) {
        return asyncDeleteByKey(cfgid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int cfgid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(cfgid, TABLENAME2);
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

    public int[] deleteByKey(final int[] cfgids) {
        return deleteByKey(cfgids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cfgid=:cfgid");
            List list = newList();
            for (int cfgid : keys) {
                Map params = newMap();
                params.put("cfgid", cfgid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cfgid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Cfgs> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Cfgs> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Cfgs cfgs = beans.get(i);
                int cfgid = cfgs.cfgid;
                sb.append(cfgid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cfgid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cfgs> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Cfgs> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" ORDER BY cfgid");
            return super.queryForList(sql.toString(), Cfgs.class);
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
            sql.append("SELECT cfgid FROM ").append(TABLENAME2).append(" ORDER BY cfgid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "cfgid") );
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
            sql.append("SELECT cfgid FROM ").append(TABLENAME2).append(" ORDER BY cfgid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cfgs> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Cfgs> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" WHERE cfgid in (").append(str).append(" ) ORDER BY cfgid");
            return super.queryForList(sql.toString(), Cfgs.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cfgs> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Cfgs> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" WHERE cfgid in ( :str ) ORDER BY cfgid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Cfgs.class);
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
            sql.append("SELECT cfgid FROM ").append(TABLENAME2).append(" WHERE cfgid in (").append(str).append(" ) ORDER BY cfgid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "cfgid") );
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

    public List<Cfgs> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Cfgs> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" ORDER BY cfgid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Cfgs.class);
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
            sql.append("SELECT cfgid FROM ").append(TABLENAME2).append(" ORDER BY cfgid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "cfgid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Cfgs last() {
        return last(TABLENAME);
    }

    public Cfgs last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" ORDER BY cfgid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Cfgs.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cfgs> selectGtKeyNum(final int cfgid, final int _num) {
        return selectGtKeyNum(cfgid, _num, TABLENAME);
    }

    public List<Cfgs> selectGtKeyNum(final int cfgid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" WHERE cfgid > :cfgid ORDER BY cfgid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("cfgid", cfgid);
            return super.queryForList(sql.toString(), params, Cfgs.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cfgs> selectGtKey(final int cfgid) {
        return selectGtKey(cfgid, TABLENAME);
    }

    public List<Cfgs> selectGtKey(final int cfgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" WHERE cfgid > :cfgid ORDER BY cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            return super.queryForList(sql.toString(), params, Cfgs.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int cfgid) {
        return selectGtKeyPKs(cfgid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int cfgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT cfgid FROM ").append(TABLENAME2).append(" WHERE cfgid > :cfgid ORDER BY cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "cfgid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Cfgs selectByKey(final int cfgid) {
        return selectByKey(cfgid, TABLENAME);
    }

    public Cfgs selectByKey(final int cfgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" WHERE cfgid = :cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            return super.queryForObject(sql.toString(), params, Cfgs.class);
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
            sql.append("SELECT MAX(cfgid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Cfgs selectByCfgid(final Integer cfgid) {
        return selectByCfgid(cfgid, TABLENAME);
    }

    public Cfgs selectByCfgid(final Integer cfgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" WHERE cfgid = :cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            return super.queryForObject(sql.toString(), params, Cfgs.class);
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

    public List<Cfgs> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Cfgs> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cfgid, name, valStr, valInt FROM ").append(TABLENAME2).append(" ORDER BY cfgid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Cfgs.class);
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
            sql.append("SELECT cfgid FROM ").append(TABLENAME2).append(" ORDER BY cfgid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "cfgid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Cfgs cfgs) {
        return updateByKey(cfgs, TABLENAME);
    }

    public int updateByKey(final Cfgs cfgs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = cfgs.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE cfgid=:cfgid");
            return super.update(sql.toString(), cfgs);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Cfgs cfgs) {
        return asyncUpdate(cfgs, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Cfgs cfgs, final String TABLENAME2) {
        try {

            String _ustr = cfgs.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE cfgid=:cfgid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, cfgs);
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

    public int updateValIntByKey(final int valInt, final int cfgid){
        return updateValIntByKey(valInt, cfgid, TABLENAME);
    }

    public int updateValIntByKey(final int valInt, final int cfgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET valInt=valInt+:valInt WHERE cfgid=:cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            params.put("valInt", valInt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValIntWithMinByKey(final int cfgid, final int valInt, final int _min){
        return updateValIntWithMinByKey(cfgid, valInt, _min, TABLENAME);
    }

    public int updateValIntWithMinByKey(final int cfgid, final int valInt, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET valInt = (select case when valInt+:valInt<=:_min then :_min else valInt+:valInt end) WHERE cfgid=:cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            params.put("_min", _min);
            params.put("valInt", valInt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValIntWithMinInKeys(final List<Integer> keys, final int valInt, final int _min){
        return updateValIntWithMinInKeys(keys, valInt, _min, TABLENAME);
    }

    public int updateValIntWithMinInKeys(final List<Integer> keys, final int valInt, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET valInt = (select case when valInt+:valInt<=:_min then :_min else valInt+:valInt end) WHERE cfgid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("valInt", valInt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValIntWithMaxByKey(final int cfgid, final int valInt, final int _max){
        return updateValIntWithMaxByKey(cfgid, valInt, _max, TABLENAME);
    }

    public int updateValIntWithMaxByKey(final int cfgid, final int valInt, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET valInt = (select case when valInt+:valInt>=:_max then :_max else valInt+:valInt end) WHERE cfgid=:cfgid");
            Map params = newMap();
            params.put("cfgid", cfgid);
            params.put("_max", _max);
            params.put("valInt", valInt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValIntWithMaxInKeys(final List<Integer> keys, final int valInt, final int _max){
        return updateValIntWithMaxInKeys(keys, valInt, _max, TABLENAME);
    }

    public int updateValIntWithMaxInKeys(final List<Integer> keys, final int valInt, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET valInt = (select case when valInt+:valInt>=:_max then :_max else valInt+:valInt end) WHERE cfgid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("valInt", valInt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValIntWithMinMaxByKey(final int cfgid, final int valInt, final int _min, final int _max){
        return updateValIntWithMinMaxByKey(cfgid, valInt, _min, _max, TABLENAME);
    }

    public int updateValIntWithMinMaxByKey(final int cfgid, final int valInt, final int _min, final int _max, final String TABLENAME2){
        if( valInt < 0 ) {
            return updateValIntWithMinByKey(cfgid, valInt, _min, TABLENAME2);
        } else {
            return updateValIntWithMaxByKey(cfgid, valInt, _max, TABLENAME2);
        }
    }

    public int updateValIntWithMinMaxInKeys(final List<Integer> keys, final int valInt, final int _min, final int _max){
        return updateValIntWithMinMaxInKeys(keys, valInt, _min, _max, TABLENAME);
    }

    public int updateValIntWithMinMaxInKeys(final List<Integer> keys, final int valInt, final int _min, final int _max, final String TABLENAME2){
        if( valInt < 0 ) {
            return updateValIntWithMinInKeys(keys, valInt, _min, TABLENAME2);
        } else {
            return updateValIntWithMaxInKeys(keys, valInt, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Cfgs> cfgss) {
        return updateByKey(cfgss, TABLENAME);
    }

    public int[] updateByKey (final List<Cfgs> cfgss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(cfgss == null || cfgss.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET name=:name, valStr=:valStr, valInt=:valInt WHERE cfgid=:cfgid");
            return super.batchUpdate2(sql.toString(), cfgss);
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
                "	`cfgid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  VARCHAR(32) NOT NULL," +
                "	`valStr`  TEXT NOT NULL," +
                "	`valInt`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`cfgid`)" +
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
                "	`cfgid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  VARCHAR(32) NOT NULL," +
                "	`valStr`  TEXT NOT NULL," +
                "	`valInt`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`cfgid`)" +
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
