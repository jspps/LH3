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

//learnhall3_design - itms4auto
@SuppressWarnings({"rawtypes", "unchecked"})
public class Itms4autoDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Itms4autoDAO.class);

    public static final String TABLE = "itms4auto";
    public static String TABLENAME = "itms4auto";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "kindid", "num4radio", "num4chbox", "num4judge", "num4fill", "num4jd", "num4luns"};
    public static String coulmns = "id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns";
    public static String coulmns2 = "kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns";

    public Itms4autoDAO(Connection conn) {
        super(conn);
    }

    public Itms4autoDAO(DataSource ds) {
        super(ds);
    }

    public Itms4autoDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Itms4auto itms4auto) {
        return insert(itms4auto, TABLENAME);
    }

    public int insert(final Itms4auto itms4auto, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            itms4auto.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns) VALUES (:kindid, :num4radio, :num4chbox, :num4judge, :num4fill, :num4jd, :num4luns)");
            Map map = super.insert(sql.toString(), itms4auto);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Itms4auto itms4auto) {
        return asyncInsert(itms4auto, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Itms4auto itms4auto, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(itms4auto, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Itms4auto itms4auto) {
        return asyncInsert2(itms4auto, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Itms4auto itms4auto, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(itms4auto, TABLENAME2);
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

    public int insert2(final Itms4auto itms4auto) {
        return insert2(itms4auto, TABLENAME);
    }

    public int insert2(final Itms4auto itms4auto, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            itms4auto.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns) VALUES (:id, :kindid, :num4radio, :num4chbox, :num4judge, :num4fill, :num4jd, :num4luns)");
            Map map = super.insert(sql.toString(), itms4auto);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Itms4auto> itms4autos) {
        return insert(itms4autos, TABLENAME);
    }

    public int[] insert(final List<Itms4auto> itms4autos, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(itms4autos == null || itms4autos.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns) VALUES (:kindid, :num4radio, :num4chbox, :num4judge, :num4fill, :num4jd, :num4luns)");
            return super.batchInsert(sql.toString(), itms4autos);
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

    public int deleteInBeans(final List<Itms4auto> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Itms4auto> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Itms4auto itms4auto = beans.get(i);
                int id = itms4auto.id;
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

    public List<Itms4auto> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Itms4auto> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Itms4auto.class);
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
            sql.append("SELECT id, kindid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Itms4auto> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Itms4auto> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Itms4auto.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Itms4auto> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Itms4auto> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Itms4auto.class);
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

    public List<Itms4auto> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Itms4auto> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Itms4auto.class);
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

    public Itms4auto last() {
        return last(TABLENAME);
    }

    public Itms4auto last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Itms4auto.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Itms4auto> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Itms4auto> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Itms4auto.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Itms4auto> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Itms4auto> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Itms4auto.class);
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

    public Itms4auto selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Itms4auto selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Itms4auto.class);
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

    public Itms4auto selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Itms4auto selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Itms4auto.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Itms4auto selectByKindid(final Integer kindid) {
        return selectByKindid(kindid, TABLENAME);
    }

    public Itms4auto selectByKindid(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForObject(sql.toString(), params, Itms4auto.class);
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

    public List<Itms4auto> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Itms4auto> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Itms4auto.class);
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

    public int updateByKey(final Itms4auto itms4auto) {
        return updateByKey(itms4auto, TABLENAME);
    }

    public int updateByKey(final Itms4auto itms4auto, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = itms4auto.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), itms4auto);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Itms4auto itms4auto) {
        return asyncUpdate(itms4auto, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Itms4auto itms4auto, final String TABLENAME2) {
        try {

            String _ustr = itms4auto.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, itms4auto);
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

    public int updateNum4radioByKey(final int num4radio, final int id){
        return updateNum4radioByKey(num4radio, id, TABLENAME);
    }

    public int updateNum4radioByKey(final int num4radio, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4radio=num4radio+:num4radio WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num4radio", num4radio);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4radioWithMinByKey(final int id, final int num4radio, final int _min){
        return updateNum4radioWithMinByKey(id, num4radio, _min, TABLENAME);
    }

    public int updateNum4radioWithMinByKey(final int id, final int num4radio, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4radio = (select case when num4radio+:num4radio<=:_min then :_min else num4radio+:num4radio end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num4radio", num4radio);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4radioWithMinInKeys(final List<Integer> keys, final int num4radio, final int _min){
        return updateNum4radioWithMinInKeys(keys, num4radio, _min, TABLENAME);
    }

    public int updateNum4radioWithMinInKeys(final List<Integer> keys, final int num4radio, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4radio = (select case when num4radio+:num4radio<=:_min then :_min else num4radio+:num4radio end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num4radio", num4radio);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4radioWithMaxByKey(final int id, final int num4radio, final int _max){
        return updateNum4radioWithMaxByKey(id, num4radio, _max, TABLENAME);
    }

    public int updateNum4radioWithMaxByKey(final int id, final int num4radio, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4radio = (select case when num4radio+:num4radio>=:_max then :_max else num4radio+:num4radio end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num4radio", num4radio);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4radioWithMaxInKeys(final List<Integer> keys, final int num4radio, final int _max){
        return updateNum4radioWithMaxInKeys(keys, num4radio, _max, TABLENAME);
    }

    public int updateNum4radioWithMaxInKeys(final List<Integer> keys, final int num4radio, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4radio = (select case when num4radio+:num4radio>=:_max then :_max else num4radio+:num4radio end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num4radio", num4radio);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4radioWithMinMaxByKey(final int id, final int num4radio, final int _min, final int _max){
        return updateNum4radioWithMinMaxByKey(id, num4radio, _min, _max, TABLENAME);
    }

    public int updateNum4radioWithMinMaxByKey(final int id, final int num4radio, final int _min, final int _max, final String TABLENAME2){
        if( num4radio < 0 ) {
            return updateNum4radioWithMinByKey(id, num4radio, _min, TABLENAME2);
        } else {
            return updateNum4radioWithMaxByKey(id, num4radio, _max, TABLENAME2);
        }
    }

    public int updateNum4radioWithMinMaxInKeys(final List<Integer> keys, final int num4radio, final int _min, final int _max){
        return updateNum4radioWithMinMaxInKeys(keys, num4radio, _min, _max, TABLENAME);
    }

    public int updateNum4radioWithMinMaxInKeys(final List<Integer> keys, final int num4radio, final int _min, final int _max, final String TABLENAME2){
        if( num4radio < 0 ) {
            return updateNum4radioWithMinInKeys(keys, num4radio, _min, TABLENAME2);
        } else {
            return updateNum4radioWithMaxInKeys(keys, num4radio, _max, TABLENAME2);
        }
    }

    public int updateNum4chboxByKey(final int num4chbox, final int id){
        return updateNum4chboxByKey(num4chbox, id, TABLENAME);
    }

    public int updateNum4chboxByKey(final int num4chbox, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4chbox=num4chbox+:num4chbox WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num4chbox", num4chbox);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4chboxWithMinByKey(final int id, final int num4chbox, final int _min){
        return updateNum4chboxWithMinByKey(id, num4chbox, _min, TABLENAME);
    }

    public int updateNum4chboxWithMinByKey(final int id, final int num4chbox, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4chbox = (select case when num4chbox+:num4chbox<=:_min then :_min else num4chbox+:num4chbox end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num4chbox", num4chbox);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4chboxWithMinInKeys(final List<Integer> keys, final int num4chbox, final int _min){
        return updateNum4chboxWithMinInKeys(keys, num4chbox, _min, TABLENAME);
    }

    public int updateNum4chboxWithMinInKeys(final List<Integer> keys, final int num4chbox, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4chbox = (select case when num4chbox+:num4chbox<=:_min then :_min else num4chbox+:num4chbox end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num4chbox", num4chbox);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4chboxWithMaxByKey(final int id, final int num4chbox, final int _max){
        return updateNum4chboxWithMaxByKey(id, num4chbox, _max, TABLENAME);
    }

    public int updateNum4chboxWithMaxByKey(final int id, final int num4chbox, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4chbox = (select case when num4chbox+:num4chbox>=:_max then :_max else num4chbox+:num4chbox end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num4chbox", num4chbox);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4chboxWithMaxInKeys(final List<Integer> keys, final int num4chbox, final int _max){
        return updateNum4chboxWithMaxInKeys(keys, num4chbox, _max, TABLENAME);
    }

    public int updateNum4chboxWithMaxInKeys(final List<Integer> keys, final int num4chbox, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4chbox = (select case when num4chbox+:num4chbox>=:_max then :_max else num4chbox+:num4chbox end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num4chbox", num4chbox);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4chboxWithMinMaxByKey(final int id, final int num4chbox, final int _min, final int _max){
        return updateNum4chboxWithMinMaxByKey(id, num4chbox, _min, _max, TABLENAME);
    }

    public int updateNum4chboxWithMinMaxByKey(final int id, final int num4chbox, final int _min, final int _max, final String TABLENAME2){
        if( num4chbox < 0 ) {
            return updateNum4chboxWithMinByKey(id, num4chbox, _min, TABLENAME2);
        } else {
            return updateNum4chboxWithMaxByKey(id, num4chbox, _max, TABLENAME2);
        }
    }

    public int updateNum4chboxWithMinMaxInKeys(final List<Integer> keys, final int num4chbox, final int _min, final int _max){
        return updateNum4chboxWithMinMaxInKeys(keys, num4chbox, _min, _max, TABLENAME);
    }

    public int updateNum4chboxWithMinMaxInKeys(final List<Integer> keys, final int num4chbox, final int _min, final int _max, final String TABLENAME2){
        if( num4chbox < 0 ) {
            return updateNum4chboxWithMinInKeys(keys, num4chbox, _min, TABLENAME2);
        } else {
            return updateNum4chboxWithMaxInKeys(keys, num4chbox, _max, TABLENAME2);
        }
    }

    public int updateNum4judgeByKey(final int num4judge, final int id){
        return updateNum4judgeByKey(num4judge, id, TABLENAME);
    }

    public int updateNum4judgeByKey(final int num4judge, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4judge=num4judge+:num4judge WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num4judge", num4judge);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4judgeWithMinByKey(final int id, final int num4judge, final int _min){
        return updateNum4judgeWithMinByKey(id, num4judge, _min, TABLENAME);
    }

    public int updateNum4judgeWithMinByKey(final int id, final int num4judge, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4judge = (select case when num4judge+:num4judge<=:_min then :_min else num4judge+:num4judge end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num4judge", num4judge);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4judgeWithMinInKeys(final List<Integer> keys, final int num4judge, final int _min){
        return updateNum4judgeWithMinInKeys(keys, num4judge, _min, TABLENAME);
    }

    public int updateNum4judgeWithMinInKeys(final List<Integer> keys, final int num4judge, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4judge = (select case when num4judge+:num4judge<=:_min then :_min else num4judge+:num4judge end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num4judge", num4judge);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4judgeWithMaxByKey(final int id, final int num4judge, final int _max){
        return updateNum4judgeWithMaxByKey(id, num4judge, _max, TABLENAME);
    }

    public int updateNum4judgeWithMaxByKey(final int id, final int num4judge, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4judge = (select case when num4judge+:num4judge>=:_max then :_max else num4judge+:num4judge end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num4judge", num4judge);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4judgeWithMaxInKeys(final List<Integer> keys, final int num4judge, final int _max){
        return updateNum4judgeWithMaxInKeys(keys, num4judge, _max, TABLENAME);
    }

    public int updateNum4judgeWithMaxInKeys(final List<Integer> keys, final int num4judge, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4judge = (select case when num4judge+:num4judge>=:_max then :_max else num4judge+:num4judge end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num4judge", num4judge);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4judgeWithMinMaxByKey(final int id, final int num4judge, final int _min, final int _max){
        return updateNum4judgeWithMinMaxByKey(id, num4judge, _min, _max, TABLENAME);
    }

    public int updateNum4judgeWithMinMaxByKey(final int id, final int num4judge, final int _min, final int _max, final String TABLENAME2){
        if( num4judge < 0 ) {
            return updateNum4judgeWithMinByKey(id, num4judge, _min, TABLENAME2);
        } else {
            return updateNum4judgeWithMaxByKey(id, num4judge, _max, TABLENAME2);
        }
    }

    public int updateNum4judgeWithMinMaxInKeys(final List<Integer> keys, final int num4judge, final int _min, final int _max){
        return updateNum4judgeWithMinMaxInKeys(keys, num4judge, _min, _max, TABLENAME);
    }

    public int updateNum4judgeWithMinMaxInKeys(final List<Integer> keys, final int num4judge, final int _min, final int _max, final String TABLENAME2){
        if( num4judge < 0 ) {
            return updateNum4judgeWithMinInKeys(keys, num4judge, _min, TABLENAME2);
        } else {
            return updateNum4judgeWithMaxInKeys(keys, num4judge, _max, TABLENAME2);
        }
    }

    public int updateNum4fillByKey(final int num4fill, final int id){
        return updateNum4fillByKey(num4fill, id, TABLENAME);
    }

    public int updateNum4fillByKey(final int num4fill, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4fill=num4fill+:num4fill WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num4fill", num4fill);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4fillWithMinByKey(final int id, final int num4fill, final int _min){
        return updateNum4fillWithMinByKey(id, num4fill, _min, TABLENAME);
    }

    public int updateNum4fillWithMinByKey(final int id, final int num4fill, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4fill = (select case when num4fill+:num4fill<=:_min then :_min else num4fill+:num4fill end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num4fill", num4fill);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4fillWithMinInKeys(final List<Integer> keys, final int num4fill, final int _min){
        return updateNum4fillWithMinInKeys(keys, num4fill, _min, TABLENAME);
    }

    public int updateNum4fillWithMinInKeys(final List<Integer> keys, final int num4fill, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4fill = (select case when num4fill+:num4fill<=:_min then :_min else num4fill+:num4fill end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num4fill", num4fill);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4fillWithMaxByKey(final int id, final int num4fill, final int _max){
        return updateNum4fillWithMaxByKey(id, num4fill, _max, TABLENAME);
    }

    public int updateNum4fillWithMaxByKey(final int id, final int num4fill, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4fill = (select case when num4fill+:num4fill>=:_max then :_max else num4fill+:num4fill end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num4fill", num4fill);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4fillWithMaxInKeys(final List<Integer> keys, final int num4fill, final int _max){
        return updateNum4fillWithMaxInKeys(keys, num4fill, _max, TABLENAME);
    }

    public int updateNum4fillWithMaxInKeys(final List<Integer> keys, final int num4fill, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4fill = (select case when num4fill+:num4fill>=:_max then :_max else num4fill+:num4fill end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num4fill", num4fill);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4fillWithMinMaxByKey(final int id, final int num4fill, final int _min, final int _max){
        return updateNum4fillWithMinMaxByKey(id, num4fill, _min, _max, TABLENAME);
    }

    public int updateNum4fillWithMinMaxByKey(final int id, final int num4fill, final int _min, final int _max, final String TABLENAME2){
        if( num4fill < 0 ) {
            return updateNum4fillWithMinByKey(id, num4fill, _min, TABLENAME2);
        } else {
            return updateNum4fillWithMaxByKey(id, num4fill, _max, TABLENAME2);
        }
    }

    public int updateNum4fillWithMinMaxInKeys(final List<Integer> keys, final int num4fill, final int _min, final int _max){
        return updateNum4fillWithMinMaxInKeys(keys, num4fill, _min, _max, TABLENAME);
    }

    public int updateNum4fillWithMinMaxInKeys(final List<Integer> keys, final int num4fill, final int _min, final int _max, final String TABLENAME2){
        if( num4fill < 0 ) {
            return updateNum4fillWithMinInKeys(keys, num4fill, _min, TABLENAME2);
        } else {
            return updateNum4fillWithMaxInKeys(keys, num4fill, _max, TABLENAME2);
        }
    }

    public int updateNum4jdByKey(final int num4jd, final int id){
        return updateNum4jdByKey(num4jd, id, TABLENAME);
    }

    public int updateNum4jdByKey(final int num4jd, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4jd=num4jd+:num4jd WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num4jd", num4jd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4jdWithMinByKey(final int id, final int num4jd, final int _min){
        return updateNum4jdWithMinByKey(id, num4jd, _min, TABLENAME);
    }

    public int updateNum4jdWithMinByKey(final int id, final int num4jd, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4jd = (select case when num4jd+:num4jd<=:_min then :_min else num4jd+:num4jd end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num4jd", num4jd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4jdWithMinInKeys(final List<Integer> keys, final int num4jd, final int _min){
        return updateNum4jdWithMinInKeys(keys, num4jd, _min, TABLENAME);
    }

    public int updateNum4jdWithMinInKeys(final List<Integer> keys, final int num4jd, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4jd = (select case when num4jd+:num4jd<=:_min then :_min else num4jd+:num4jd end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num4jd", num4jd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4jdWithMaxByKey(final int id, final int num4jd, final int _max){
        return updateNum4jdWithMaxByKey(id, num4jd, _max, TABLENAME);
    }

    public int updateNum4jdWithMaxByKey(final int id, final int num4jd, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4jd = (select case when num4jd+:num4jd>=:_max then :_max else num4jd+:num4jd end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num4jd", num4jd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4jdWithMaxInKeys(final List<Integer> keys, final int num4jd, final int _max){
        return updateNum4jdWithMaxInKeys(keys, num4jd, _max, TABLENAME);
    }

    public int updateNum4jdWithMaxInKeys(final List<Integer> keys, final int num4jd, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4jd = (select case when num4jd+:num4jd>=:_max then :_max else num4jd+:num4jd end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num4jd", num4jd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4jdWithMinMaxByKey(final int id, final int num4jd, final int _min, final int _max){
        return updateNum4jdWithMinMaxByKey(id, num4jd, _min, _max, TABLENAME);
    }

    public int updateNum4jdWithMinMaxByKey(final int id, final int num4jd, final int _min, final int _max, final String TABLENAME2){
        if( num4jd < 0 ) {
            return updateNum4jdWithMinByKey(id, num4jd, _min, TABLENAME2);
        } else {
            return updateNum4jdWithMaxByKey(id, num4jd, _max, TABLENAME2);
        }
    }

    public int updateNum4jdWithMinMaxInKeys(final List<Integer> keys, final int num4jd, final int _min, final int _max){
        return updateNum4jdWithMinMaxInKeys(keys, num4jd, _min, _max, TABLENAME);
    }

    public int updateNum4jdWithMinMaxInKeys(final List<Integer> keys, final int num4jd, final int _min, final int _max, final String TABLENAME2){
        if( num4jd < 0 ) {
            return updateNum4jdWithMinInKeys(keys, num4jd, _min, TABLENAME2);
        } else {
            return updateNum4jdWithMaxInKeys(keys, num4jd, _max, TABLENAME2);
        }
    }

    public int updateNum4lunsByKey(final int num4luns, final int id){
        return updateNum4lunsByKey(num4luns, id, TABLENAME);
    }

    public int updateNum4lunsByKey(final int num4luns, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4luns=num4luns+:num4luns WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num4luns", num4luns);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4lunsWithMinByKey(final int id, final int num4luns, final int _min){
        return updateNum4lunsWithMinByKey(id, num4luns, _min, TABLENAME);
    }

    public int updateNum4lunsWithMinByKey(final int id, final int num4luns, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4luns = (select case when num4luns+:num4luns<=:_min then :_min else num4luns+:num4luns end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num4luns", num4luns);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4lunsWithMinInKeys(final List<Integer> keys, final int num4luns, final int _min){
        return updateNum4lunsWithMinInKeys(keys, num4luns, _min, TABLENAME);
    }

    public int updateNum4lunsWithMinInKeys(final List<Integer> keys, final int num4luns, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4luns = (select case when num4luns+:num4luns<=:_min then :_min else num4luns+:num4luns end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num4luns", num4luns);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4lunsWithMaxByKey(final int id, final int num4luns, final int _max){
        return updateNum4lunsWithMaxByKey(id, num4luns, _max, TABLENAME);
    }

    public int updateNum4lunsWithMaxByKey(final int id, final int num4luns, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4luns = (select case when num4luns+:num4luns>=:_max then :_max else num4luns+:num4luns end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num4luns", num4luns);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4lunsWithMaxInKeys(final List<Integer> keys, final int num4luns, final int _max){
        return updateNum4lunsWithMaxInKeys(keys, num4luns, _max, TABLENAME);
    }

    public int updateNum4lunsWithMaxInKeys(final List<Integer> keys, final int num4luns, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num4luns = (select case when num4luns+:num4luns>=:_max then :_max else num4luns+:num4luns end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num4luns", num4luns);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNum4lunsWithMinMaxByKey(final int id, final int num4luns, final int _min, final int _max){
        return updateNum4lunsWithMinMaxByKey(id, num4luns, _min, _max, TABLENAME);
    }

    public int updateNum4lunsWithMinMaxByKey(final int id, final int num4luns, final int _min, final int _max, final String TABLENAME2){
        if( num4luns < 0 ) {
            return updateNum4lunsWithMinByKey(id, num4luns, _min, TABLENAME2);
        } else {
            return updateNum4lunsWithMaxByKey(id, num4luns, _max, TABLENAME2);
        }
    }

    public int updateNum4lunsWithMinMaxInKeys(final List<Integer> keys, final int num4luns, final int _min, final int _max){
        return updateNum4lunsWithMinMaxInKeys(keys, num4luns, _min, _max, TABLENAME);
    }

    public int updateNum4lunsWithMinMaxInKeys(final List<Integer> keys, final int num4luns, final int _min, final int _max, final String TABLENAME2){
        if( num4luns < 0 ) {
            return updateNum4lunsWithMinInKeys(keys, num4luns, _min, TABLENAME2);
        } else {
            return updateNum4lunsWithMaxInKeys(keys, num4luns, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Itms4auto> itms4autos) {
        return updateByKey(itms4autos, TABLENAME);
    }

    public int[] updateByKey (final List<Itms4auto> itms4autos, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(itms4autos == null || itms4autos.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid=:kindid, num4radio=:num4radio, num4chbox=:num4chbox, num4judge=:num4judge, num4fill=:num4fill, num4jd=:num4jd, num4luns=:num4luns WHERE id=:id");
            return super.batchUpdate2(sql.toString(), itms4autos);
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
                "	`kindid`  INT(11) NOT NULL," +
                "	`num4radio`  INT(11) NOT NULL," +
                "	`num4chbox`  INT(11) NOT NULL," +
                "	`num4judge`  INT(11) NOT NULL," +
                "	`num4fill`  INT(11) NOT NULL," +
                "	`num4jd`  INT(11) NOT NULL," +
                "	`num4luns`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `kindid` (`kindid`)" +
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
                "	`kindid`  INT(11) NOT NULL," +
                "	`num4radio`  INT(11) NOT NULL," +
                "	`num4chbox`  INT(11) NOT NULL," +
                "	`num4judge`  INT(11) NOT NULL," +
                "	`num4fill`  INT(11) NOT NULL," +
                "	`num4jd`  INT(11) NOT NULL," +
                "	`num4luns`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `kindid` (`kindid`)" +
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
