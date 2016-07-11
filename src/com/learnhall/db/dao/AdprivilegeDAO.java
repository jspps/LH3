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

//learnhall3_design - adprivilege
@SuppressWarnings({"rawtypes", "unchecked"})
public class AdprivilegeDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(AdprivilegeDAO.class);

    public static final String TABLE = "adprivilege";
    public static String TABLENAME = "adprivilege";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"prid", "name", "pdesc", "url", "parentid"};
    public static String coulmns = "prid, name, pdesc, url, parentid";
    public static String coulmns2 = "name, pdesc, url, parentid";

    public AdprivilegeDAO(Connection conn) {
        super(conn);
    }

    public AdprivilegeDAO(DataSource ds) {
        super(ds);
    }

    public AdprivilegeDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Adprivilege adprivilege) {
        return insert(adprivilege, TABLENAME);
    }

    public int insert(final Adprivilege adprivilege, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            adprivilege.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name, pdesc, url, parentid) VALUES (:name, :pdesc, :url, :parentid)");
            Map map = super.insert(sql.toString(), adprivilege);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Adprivilege adprivilege) {
        return asyncInsert(adprivilege, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Adprivilege adprivilege, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(adprivilege, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Adprivilege adprivilege) {
        return asyncInsert2(adprivilege, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Adprivilege adprivilege, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(adprivilege, TABLENAME2);
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

    public int insert2(final Adprivilege adprivilege) {
        return insert2(adprivilege, TABLENAME);
    }

    public int insert2(final Adprivilege adprivilege, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            adprivilege.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (prid, name, pdesc, url, parentid) VALUES (:prid, :name, :pdesc, :url, :parentid)");
            Map map = super.insert(sql.toString(), adprivilege);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Adprivilege> adprivileges) {
        return insert(adprivileges, TABLENAME);
    }

    public int[] insert(final List<Adprivilege> adprivileges, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(adprivileges == null || adprivileges.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name, pdesc, url, parentid) VALUES (:name, :pdesc, :url, :parentid)");
            return super.batchInsert(sql.toString(), adprivileges);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int prid) {
        return deleteByKey(prid, TABLENAME);
    }

    public int deleteByKey(final int prid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE prid=:prid");
            Map params = newMap();
            params.put("prid", prid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int prid) {
        return asyncDeleteByKey(prid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int prid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(prid, TABLENAME2);
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

    public int[] deleteByKey(final int[] prids) {
        return deleteByKey(prids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE prid=:prid");
            List list = newList();
            for (int prid : keys) {
                Map params = newMap();
                params.put("prid", prid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE prid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Adprivilege> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Adprivilege> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Adprivilege adprivilege = beans.get(i);
                int prid = adprivilege.prid;
                sb.append(prid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE prid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adprivilege> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Adprivilege> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" ORDER BY prid");
            return super.queryForList(sql.toString(), Adprivilege.class);
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
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" ORDER BY prid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "prid") );
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
            sql.append("SELECT prid, parentid FROM ").append(TABLENAME2).append(" ORDER BY prid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adprivilege> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Adprivilege> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE prid in (").append(str).append(" ) ORDER BY prid");
            return super.queryForList(sql.toString(), Adprivilege.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adprivilege> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Adprivilege> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE prid in ( :str ) ORDER BY prid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Adprivilege.class);
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
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" WHERE prid in (").append(str).append(" ) ORDER BY prid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "prid") );
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

    public List<Adprivilege> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Adprivilege> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" ORDER BY prid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Adprivilege.class);
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
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" ORDER BY prid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "prid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adprivilege last() {
        return last(TABLENAME);
    }

    public Adprivilege last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" ORDER BY prid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Adprivilege.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adprivilege> selectGtKeyNum(final int prid, final int _num) {
        return selectGtKeyNum(prid, _num, TABLENAME);
    }

    public List<Adprivilege> selectGtKeyNum(final int prid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE prid > :prid ORDER BY prid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("prid", prid);
            return super.queryForList(sql.toString(), params, Adprivilege.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adprivilege> selectGtKey(final int prid) {
        return selectGtKey(prid, TABLENAME);
    }

    public List<Adprivilege> selectGtKey(final int prid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE prid > :prid ORDER BY prid");
            Map params = newMap();
            params.put("prid", prid);
            return super.queryForList(sql.toString(), params, Adprivilege.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int prid) {
        return selectGtKeyPKs(prid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int prid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" WHERE prid > :prid ORDER BY prid");
            Map params = newMap();
            params.put("prid", prid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "prid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adprivilege selectByKey(final int prid) {
        return selectByKey(prid, TABLENAME);
    }

    public Adprivilege selectByKey(final int prid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE prid = :prid");
            Map params = newMap();
            params.put("prid", prid);
            return super.queryForObject(sql.toString(), params, Adprivilege.class);
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
            sql.append("SELECT MAX(prid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
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

    public List<Adprivilege> selectByParentid(final Integer parentid) {
        return selectByParentid(parentid, TABLENAME);
    }

    public List<Adprivilege> selectByParentid(final Integer parentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY prid ");
            Map params = newMap();
            params.put("parentid", parentid);
            return super.queryForList(sql.toString(), params, Adprivilege.class);
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
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY prid ");
            Map params = newMap();
            params.put("parentid", parentid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "prid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adprivilege> selectPageByParentid(final Integer parentid, final int begin, final int num) {
        return selectPageByParentid(parentid, begin, num, TABLENAME);
    }

    public List<Adprivilege> selectPageByParentid(final Integer parentid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY prid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("parentid", parentid);
            return super.queryForList(sql.toString(), params, Adprivilege.class);
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
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" WHERE parentid = :parentid ORDER BY prid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("parentid", parentid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "prid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adprivilege selectByPrid(final Integer prid) {
        return selectByPrid(prid, TABLENAME);
    }

    public Adprivilege selectByPrid(final Integer prid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" WHERE prid = :prid");
            Map params = newMap();
            params.put("prid", prid);
            return super.queryForObject(sql.toString(), params, Adprivilege.class);
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

    public List<Adprivilege> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Adprivilege> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT prid, name, pdesc, url, parentid FROM ").append(TABLENAME2).append(" ORDER BY prid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Adprivilege.class);
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
            sql.append("SELECT prid FROM ").append(TABLENAME2).append(" ORDER BY prid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "prid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Adprivilege adprivilege) {
        return updateByKey(adprivilege, TABLENAME);
    }

    public int updateByKey(final Adprivilege adprivilege, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = adprivilege.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE prid=:prid");
            return super.update(sql.toString(), adprivilege);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Adprivilege adprivilege) {
        return asyncUpdate(adprivilege, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Adprivilege adprivilege, final String TABLENAME2) {
        try {

            String _ustr = adprivilege.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE prid=:prid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, adprivilege);
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

    public int updateParentidByKey(final int parentid, final int prid){
        return updateParentidByKey(parentid, prid, TABLENAME);
    }

    public int updateParentidByKey(final int parentid, final int prid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid=parentid+:parentid WHERE prid=:prid");
            Map params = newMap();
            params.put("prid", prid);
            params.put("parentid", parentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateParentidWithMinByKey(final int prid, final int parentid, final int _min){
        return updateParentidWithMinByKey(prid, parentid, _min, TABLENAME);
    }

    public int updateParentidWithMinByKey(final int prid, final int parentid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid<=:_min then :_min else parentid+:parentid end) WHERE prid=:prid");
            Map params = newMap();
            params.put("prid", prid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid<=:_min then :_min else parentid+:parentid end) WHERE prid in (").append(str).append(")");
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

    public int updateParentidWithMaxByKey(final int prid, final int parentid, final int _max){
        return updateParentidWithMaxByKey(prid, parentid, _max, TABLENAME);
    }

    public int updateParentidWithMaxByKey(final int prid, final int parentid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid>=:_max then :_max else parentid+:parentid end) WHERE prid=:prid");
            Map params = newMap();
            params.put("prid", prid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET parentid = (select case when parentid+:parentid>=:_max then :_max else parentid+:parentid end) WHERE prid in (").append(str).append(")");
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

    public int updateParentidWithMinMaxByKey(final int prid, final int parentid, final int _min, final int _max){
        return updateParentidWithMinMaxByKey(prid, parentid, _min, _max, TABLENAME);
    }

    public int updateParentidWithMinMaxByKey(final int prid, final int parentid, final int _min, final int _max, final String TABLENAME2){
        if( parentid < 0 ) {
            return updateParentidWithMinByKey(prid, parentid, _min, TABLENAME2);
        } else {
            return updateParentidWithMaxByKey(prid, parentid, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Adprivilege> adprivileges) {
        return updateByKey(adprivileges, TABLENAME);
    }

    public int[] updateByKey (final List<Adprivilege> adprivileges, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(adprivileges == null || adprivileges.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET name=:name, pdesc=:pdesc, url=:url, parentid=:parentid WHERE prid=:prid");
            return super.batchUpdate2(sql.toString(), adprivileges);
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
                "	`prid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  VARCHAR(30) NOT NULL," +
                "	`pdesc`  VARCHAR(200) NOT NULL," +
                "	`url`  VARCHAR(200) NOT NULL," +
                "	`parentid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`prid`)," +
                "	KEY `fk_privilege` (`parentid`)" +
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
                "	`prid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  VARCHAR(30) NOT NULL," +
                "	`pdesc`  VARCHAR(200) NOT NULL," +
                "	`url`  VARCHAR(200) NOT NULL," +
                "	`parentid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`prid`)," +
                "	KEY `fk_privilege` (`parentid`)" +
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
