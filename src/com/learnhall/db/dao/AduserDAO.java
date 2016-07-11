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

//learnhall3_design - aduser
@SuppressWarnings({"rawtypes", "unchecked"})
public class AduserDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(AduserDAO.class);

    public static final String TABLE = "aduser";
    public static String TABLENAME = "aduser";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"uid", "accountid", "uname", "powerids", "remarks"};
    public static String coulmns = "uid, accountid, uname, powerids, remarks";
    public static String coulmns2 = "accountid, uname, powerids, remarks";

    public AduserDAO(Connection conn) {
        super(conn);
    }

    public AduserDAO(DataSource ds) {
        super(ds);
    }

    public AduserDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Aduser aduser) {
        return insert(aduser, TABLENAME);
    }

    public int insert(final Aduser aduser, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            aduser.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, uname, powerids, remarks) VALUES (:accountid, :uname, :powerids, :remarks)");
            Map map = super.insert(sql.toString(), aduser);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Aduser aduser) {
        return asyncInsert(aduser, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Aduser aduser, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(aduser, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Aduser aduser) {
        return asyncInsert2(aduser, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Aduser aduser, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(aduser, TABLENAME2);
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

    public int insert2(final Aduser aduser) {
        return insert2(aduser, TABLENAME);
    }

    public int insert2(final Aduser aduser, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            aduser.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (uid, accountid, uname, powerids, remarks) VALUES (:uid, :accountid, :uname, :powerids, :remarks)");
            Map map = super.insert(sql.toString(), aduser);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Aduser> adusers) {
        return insert(adusers, TABLENAME);
    }

    public int[] insert(final List<Aduser> adusers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(adusers == null || adusers.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, uname, powerids, remarks) VALUES (:accountid, :uname, :powerids, :remarks)");
            return super.batchInsert(sql.toString(), adusers);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int uid) {
        return deleteByKey(uid, TABLENAME);
    }

    public int deleteByKey(final int uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE uid=:uid");
            Map params = newMap();
            params.put("uid", uid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int uid) {
        return asyncDeleteByKey(uid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int uid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(uid, TABLENAME2);
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

    public int[] deleteByKey(final int[] uids) {
        return deleteByKey(uids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE uid=:uid");
            List list = newList();
            for (int uid : keys) {
                Map params = newMap();
                params.put("uid", uid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE uid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Aduser> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Aduser> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Aduser aduser = beans.get(i);
                int uid = aduser.uid;
                sb.append(uid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE uid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Aduser> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Aduser> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" ORDER BY uid");
            return super.queryForList(sql.toString(), Aduser.class);
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
            sql.append("SELECT uid FROM ").append(TABLENAME2).append(" ORDER BY uid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "uid") );
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
            sql.append("SELECT uid, accountid, uname FROM ").append(TABLENAME2).append(" ORDER BY uid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Aduser> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Aduser> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uid in (").append(str).append(" ) ORDER BY uid");
            return super.queryForList(sql.toString(), Aduser.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Aduser> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Aduser> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uid in ( :str ) ORDER BY uid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Aduser.class);
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
            sql.append("SELECT uid FROM ").append(TABLENAME2).append(" WHERE uid in (").append(str).append(" ) ORDER BY uid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "uid") );
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

    public List<Aduser> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Aduser> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" ORDER BY uid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Aduser.class);
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
            sql.append("SELECT uid FROM ").append(TABLENAME2).append(" ORDER BY uid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "uid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Aduser last() {
        return last(TABLENAME);
    }

    public Aduser last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" ORDER BY uid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Aduser.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Aduser> selectGtKeyNum(final int uid, final int _num) {
        return selectGtKeyNum(uid, _num, TABLENAME);
    }

    public List<Aduser> selectGtKeyNum(final int uid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uid > :uid ORDER BY uid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("uid", uid);
            return super.queryForList(sql.toString(), params, Aduser.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Aduser> selectGtKey(final int uid) {
        return selectGtKey(uid, TABLENAME);
    }

    public List<Aduser> selectGtKey(final int uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uid > :uid ORDER BY uid");
            Map params = newMap();
            params.put("uid", uid);
            return super.queryForList(sql.toString(), params, Aduser.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int uid) {
        return selectGtKeyPKs(uid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT uid FROM ").append(TABLENAME2).append(" WHERE uid > :uid ORDER BY uid");
            Map params = newMap();
            params.put("uid", uid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "uid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Aduser selectByKey(final int uid) {
        return selectByKey(uid, TABLENAME);
    }

    public Aduser selectByKey(final int uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uid = :uid");
            Map params = newMap();
            params.put("uid", uid);
            return super.queryForObject(sql.toString(), params, Aduser.class);
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
            sql.append("SELECT MAX(uid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Aduser selectByUid(final Integer uid) {
        return selectByUid(uid, TABLENAME);
    }

    public Aduser selectByUid(final Integer uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uid = :uid");
            Map params = newMap();
            params.put("uid", uid);
            return super.queryForObject(sql.toString(), params, Aduser.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Aduser selectByUname(final String uname) {
        return selectByUname(uname, TABLENAME);
    }

    public Aduser selectByUname(final String uname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uname = :uname");
            Map params = newMap();
            params.put("uname", uname);
            return super.queryForObject(sql.toString(), params, Aduser.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeUname(final String uname) {
        return countLikeUname(uname, TABLENAME);
    }

    public int countLikeUname(final String uname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE uname LIKE '%").append(uname).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Aduser> selectLikeUname(final String uname) {
        return selectLikeUname(uname, TABLENAME);
    }

    public List<Aduser> selectLikeUname(final String uname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE uname LIKE '%").append(uname).append("%' ORDER BY uid ");
            return super.queryForList(sql.toString(), Aduser.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeUnamePKs(final String uname) {
        return selectLikeUnamePKs(uname, TABLENAME);
    }

    public List<Integer> selectLikeUnamePKs(final String uname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT uid FROM ").append(TABLENAME2).append(" WHERE uname LIKE '%").append(uname).append("%' ORDER BY uid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "uid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Aduser selectByAccountid(final Integer accountid) {
        return selectByAccountid(accountid, TABLENAME);
    }

    public Aduser selectByAccountid(final Integer accountid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" WHERE accountid = :accountid");
            Map params = newMap();
            params.put("accountid", accountid);
            return super.queryForObject(sql.toString(), params, Aduser.class);
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

    public List<Aduser> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Aduser> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT uid, accountid, uname, powerids, remarks FROM ").append(TABLENAME2).append(" ORDER BY uid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Aduser.class);
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
            sql.append("SELECT uid FROM ").append(TABLENAME2).append(" ORDER BY uid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "uid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Aduser aduser) {
        return updateByKey(aduser, TABLENAME);
    }

    public int updateByKey(final Aduser aduser, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = aduser.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE uid=:uid");
            return super.update(sql.toString(), aduser);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Aduser aduser) {
        return asyncUpdate(aduser, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Aduser aduser, final String TABLENAME2) {
        try {

            String _ustr = aduser.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE uid=:uid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, aduser);
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

    public int updateAccountidByKey(final int accountid, final int uid){
        return updateAccountidByKey(accountid, uid, TABLENAME);
    }

    public int updateAccountidByKey(final int accountid, final int uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=accountid+:accountid WHERE uid=:uid");
            Map params = newMap();
            params.put("uid", uid);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMinByKey(final int uid, final int accountid, final int _min){
        return updateAccountidWithMinByKey(uid, accountid, _min, TABLENAME);
    }

    public int updateAccountidWithMinByKey(final int uid, final int accountid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE uid=:uid");
            Map params = newMap();
            params.put("uid", uid);
            params.put("_min", _min);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMinInKeys(final List<Integer> keys, final int accountid, final int _min){
        return updateAccountidWithMinInKeys(keys, accountid, _min, TABLENAME);
    }

    public int updateAccountidWithMinInKeys(final List<Integer> keys, final int accountid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE uid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMaxByKey(final int uid, final int accountid, final int _max){
        return updateAccountidWithMaxByKey(uid, accountid, _max, TABLENAME);
    }

    public int updateAccountidWithMaxByKey(final int uid, final int accountid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE uid=:uid");
            Map params = newMap();
            params.put("uid", uid);
            params.put("_max", _max);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMaxInKeys(final List<Integer> keys, final int accountid, final int _max){
        return updateAccountidWithMaxInKeys(keys, accountid, _max, TABLENAME);
    }

    public int updateAccountidWithMaxInKeys(final List<Integer> keys, final int accountid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE uid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMinMaxByKey(final int uid, final int accountid, final int _min, final int _max){
        return updateAccountidWithMinMaxByKey(uid, accountid, _min, _max, TABLENAME);
    }

    public int updateAccountidWithMinMaxByKey(final int uid, final int accountid, final int _min, final int _max, final String TABLENAME2){
        if( accountid < 0 ) {
            return updateAccountidWithMinByKey(uid, accountid, _min, TABLENAME2);
        } else {
            return updateAccountidWithMaxByKey(uid, accountid, _max, TABLENAME2);
        }
    }

    public int updateAccountidWithMinMaxInKeys(final List<Integer> keys, final int accountid, final int _min, final int _max){
        return updateAccountidWithMinMaxInKeys(keys, accountid, _min, _max, TABLENAME);
    }

    public int updateAccountidWithMinMaxInKeys(final List<Integer> keys, final int accountid, final int _min, final int _max, final String TABLENAME2){
        if( accountid < 0 ) {
            return updateAccountidWithMinInKeys(keys, accountid, _min, TABLENAME2);
        } else {
            return updateAccountidWithMaxInKeys(keys, accountid, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Aduser> adusers) {
        return updateByKey(adusers, TABLENAME);
    }

    public int[] updateByKey (final List<Aduser> adusers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(adusers == null || adusers.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=:accountid, uname=:uname, powerids=:powerids, remarks=:remarks WHERE uid=:uid");
            return super.batchUpdate2(sql.toString(), adusers);
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
                "	`uid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`uname`  VARCHAR(50) NOT NULL," +
                "	`powerids`  VARCHAR(128) NOT NULL," +
                "	`remarks`  VARCHAR(200)," +
                "	PRIMARY KEY (`uid`)," +
                "	UNIQUE KEY `uname` (`uname`)," +
                "	UNIQUE KEY `accountid` (`accountid`)" +
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
                "	`uid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`uname`  VARCHAR(50) NOT NULL," +
                "	`powerids`  VARCHAR(128) NOT NULL," +
                "	`remarks`  VARCHAR(200)," +
                "	PRIMARY KEY (`uid`)," +
                "	KEY `uname` (`uname`)," +
                "	KEY `accountid` (`accountid`)" +
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
