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

//learnhall3_design - adqdepartment
@SuppressWarnings({"rawtypes", "unchecked"})
public class AdqdepartmentDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(AdqdepartmentDAO.class);

    public static final String TABLE = "adqdepartment";
    public static String TABLENAME = "adqdepartment";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"did", "name"};
    public static String coulmns = "did, name";
    public static String coulmns2 = "name";

    public AdqdepartmentDAO(Connection conn) {
        super(conn);
    }

    public AdqdepartmentDAO(DataSource ds) {
        super(ds);
    }

    public AdqdepartmentDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Adqdepartment adqdepartment) {
        return insert(adqdepartment, TABLENAME);
    }

    public int insert(final Adqdepartment adqdepartment, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            adqdepartment.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name) VALUES (:name)");
            Map map = super.insert(sql.toString(), adqdepartment);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Adqdepartment adqdepartment) {
        return asyncInsert(adqdepartment, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Adqdepartment adqdepartment, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(adqdepartment, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Adqdepartment adqdepartment) {
        return asyncInsert2(adqdepartment, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Adqdepartment adqdepartment, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(adqdepartment, TABLENAME2);
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

    public int insert2(final Adqdepartment adqdepartment) {
        return insert2(adqdepartment, TABLENAME);
    }

    public int insert2(final Adqdepartment adqdepartment, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            adqdepartment.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (did, name) VALUES (:did, :name)");
            Map map = super.insert(sql.toString(), adqdepartment);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Adqdepartment> adqdepartments) {
        return insert(adqdepartments, TABLENAME);
    }

    public int[] insert(final List<Adqdepartment> adqdepartments, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(adqdepartments == null || adqdepartments.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name) VALUES (:name)");
            return super.batchInsert(sql.toString(), adqdepartments);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int did) {
        return deleteByKey(did, TABLENAME);
    }

    public int deleteByKey(final int did, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE did=:did");
            Map params = newMap();
            params.put("did", did);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int did) {
        return asyncDeleteByKey(did, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int did, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(did, TABLENAME2);
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

    public int[] deleteByKey(final int[] dids) {
        return deleteByKey(dids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE did=:did");
            List list = newList();
            for (int did : keys) {
                Map params = newMap();
                params.put("did", did);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE did in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Adqdepartment> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Adqdepartment> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Adqdepartment adqdepartment = beans.get(i);
                int did = adqdepartment.did;
                sb.append(did);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE did in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adqdepartment> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Adqdepartment> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" ORDER BY did");
            return super.queryForList(sql.toString(), Adqdepartment.class);
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
            sql.append("SELECT did FROM ").append(TABLENAME2).append(" ORDER BY did");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "did") );
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
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" ORDER BY did");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adqdepartment> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Adqdepartment> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE did in (").append(str).append(" ) ORDER BY did");
            return super.queryForList(sql.toString(), Adqdepartment.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adqdepartment> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Adqdepartment> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE did in ( :str ) ORDER BY did");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Adqdepartment.class);
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
            sql.append("SELECT did FROM ").append(TABLENAME2).append(" WHERE did in (").append(str).append(" ) ORDER BY did");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "did") );
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

    public List<Adqdepartment> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Adqdepartment> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" ORDER BY did DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Adqdepartment.class);
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
            sql.append("SELECT did FROM ").append(TABLENAME2).append(" ORDER BY did DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "did") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adqdepartment last() {
        return last(TABLENAME);
    }

    public Adqdepartment last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" ORDER BY did DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Adqdepartment.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adqdepartment> selectGtKeyNum(final int did, final int _num) {
        return selectGtKeyNum(did, _num, TABLENAME);
    }

    public List<Adqdepartment> selectGtKeyNum(final int did, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE did > :did ORDER BY did LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("did", did);
            return super.queryForList(sql.toString(), params, Adqdepartment.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adqdepartment> selectGtKey(final int did) {
        return selectGtKey(did, TABLENAME);
    }

    public List<Adqdepartment> selectGtKey(final int did, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE did > :did ORDER BY did");
            Map params = newMap();
            params.put("did", did);
            return super.queryForList(sql.toString(), params, Adqdepartment.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int did) {
        return selectGtKeyPKs(did, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int did, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT did FROM ").append(TABLENAME2).append(" WHERE did > :did ORDER BY did");
            Map params = newMap();
            params.put("did", did);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "did") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adqdepartment selectByKey(final int did) {
        return selectByKey(did, TABLENAME);
    }

    public Adqdepartment selectByKey(final int did, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE did = :did");
            Map params = newMap();
            params.put("did", did);
            return super.queryForObject(sql.toString(), params, Adqdepartment.class);
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
            sql.append("SELECT MAX(did) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adqdepartment selectByDid(final Integer did) {
        return selectByDid(did, TABLENAME);
    }

    public Adqdepartment selectByDid(final Integer did, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE did = :did");
            Map params = newMap();
            params.put("did", did);
            return super.queryForObject(sql.toString(), params, Adqdepartment.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adqdepartment selectByName(final String name) {
        return selectByName(name, TABLENAME);
    }

    public Adqdepartment selectByName(final String name, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE name = :name");
            Map params = newMap();
            params.put("name", name);
            return super.queryForObject(sql.toString(), params, Adqdepartment.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeName(final String name) {
        return countLikeName(name, TABLENAME);
    }

    public int countLikeName(final String name, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE name LIKE '%").append(name).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adqdepartment> selectLikeName(final String name) {
        return selectLikeName(name, TABLENAME);
    }

    public List<Adqdepartment> selectLikeName(final String name, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" WHERE name LIKE '%").append(name).append("%' ORDER BY did ");
            return super.queryForList(sql.toString(), Adqdepartment.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeNamePKs(final String name) {
        return selectLikeNamePKs(name, TABLENAME);
    }

    public List<Integer> selectLikeNamePKs(final String name, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT did FROM ").append(TABLENAME2).append(" WHERE name LIKE '%").append(name).append("%' ORDER BY did ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "did") );
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

    public List<Adqdepartment> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Adqdepartment> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT did, name FROM ").append(TABLENAME2).append(" ORDER BY did LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Adqdepartment.class);
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
            sql.append("SELECT did FROM ").append(TABLENAME2).append(" ORDER BY did LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "did") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Adqdepartment adqdepartment) {
        return updateByKey(adqdepartment, TABLENAME);
    }

    public int updateByKey(final Adqdepartment adqdepartment, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = adqdepartment.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE did=:did");
            return super.update(sql.toString(), adqdepartment);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Adqdepartment adqdepartment) {
        return asyncUpdate(adqdepartment, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Adqdepartment adqdepartment, final String TABLENAME2) {
        try {

            String _ustr = adqdepartment.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE did=:did");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, adqdepartment);
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

    public int[] updateByKey (final List<Adqdepartment> adqdepartments) {
        return updateByKey(adqdepartments, TABLENAME);
    }

    public int[] updateByKey (final List<Adqdepartment> adqdepartments, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(adqdepartments == null || adqdepartments.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET name=:name WHERE did=:did");
            return super.batchUpdate2(sql.toString(), adqdepartments);
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
                "	`did`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  VARCHAR(16) NOT NULL," +
                "	PRIMARY KEY (`did`)," +
                "	UNIQUE KEY `name` (`name`)" +
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
                "	`did`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  VARCHAR(16) NOT NULL," +
                "	PRIMARY KEY (`did`)," +
                "	KEY `name` (`name`)" +
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
