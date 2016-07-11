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

//learnhall3_design - adcourses
@SuppressWarnings({"rawtypes", "unchecked"})
public class AdcoursesDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(AdcoursesDAO.class);

    public static final String TABLE = "adcourses";
    public static String TABLENAME = "adcourses";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"cid", "departid", "nmMajor", "nmLevel", "nmSub", "nmArea", "profitAgent", "profitOwner", "deposit", "bonus", "wrong", "program", "art", "status", "createtime", "imgurl4major"};
    public static String coulmns = "cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major";
    public static String coulmns2 = "departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major";

    public AdcoursesDAO(Connection conn) {
        super(conn);
    }

    public AdcoursesDAO(DataSource ds) {
        super(ds);
    }

    public AdcoursesDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Adcourses adcourses) {
        return insert(adcourses, TABLENAME);
    }

    public int insert(final Adcourses adcourses, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            adcourses.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major) VALUES (:departid, :nmMajor, :nmLevel, :nmSub, :nmArea, :profitAgent, :profitOwner, :deposit, :bonus, :wrong, :program, :art, :status, :createtime, :imgurl4major)");
            Map map = super.insert(sql.toString(), adcourses);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Adcourses adcourses) {
        return asyncInsert(adcourses, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Adcourses adcourses, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(adcourses, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Adcourses adcourses) {
        return asyncInsert2(adcourses, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Adcourses adcourses, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(adcourses, TABLENAME2);
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

    public int insert2(final Adcourses adcourses) {
        return insert2(adcourses, TABLENAME);
    }

    public int insert2(final Adcourses adcourses, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            adcourses.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major) VALUES (:cid, :departid, :nmMajor, :nmLevel, :nmSub, :nmArea, :profitAgent, :profitOwner, :deposit, :bonus, :wrong, :program, :art, :status, :createtime, :imgurl4major)");
            Map map = super.insert(sql.toString(), adcourses);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Adcourses> adcoursess) {
        return insert(adcoursess, TABLENAME);
    }

    public int[] insert(final List<Adcourses> adcoursess, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(adcoursess == null || adcoursess.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major) VALUES (:departid, :nmMajor, :nmLevel, :nmSub, :nmArea, :profitAgent, :profitOwner, :deposit, :bonus, :wrong, :program, :art, :status, :createtime, :imgurl4major)");
            return super.batchInsert(sql.toString(), adcoursess);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int cid) {
        return deleteByKey(cid, TABLENAME);
    }

    public int deleteByKey(final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int cid) {
        return asyncDeleteByKey(cid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int cid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(cid, TABLENAME2);
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

    public int[] deleteByKey(final int[] cids) {
        return deleteByKey(cids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cid=:cid");
            List list = newList();
            for (int cid : keys) {
                Map params = newMap();
                params.put("cid", cid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Adcourses> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Adcourses> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Adcourses adcourses = beans.get(i);
                int cid = adcourses.cid;
                sb.append(cid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE cid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Adcourses> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" ORDER BY cid");
            return super.queryForList(sql.toString(), Adcourses.class);
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
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" ORDER BY cid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "cid") );
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
            sql.append("SELECT cid, departid FROM ").append(TABLENAME2).append(" ORDER BY cid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Adcourses> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE cid in (").append(str).append(" ) ORDER BY cid");
            return super.queryForList(sql.toString(), Adcourses.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Adcourses> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE cid in ( :str ) ORDER BY cid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Adcourses.class);
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
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" WHERE cid in (").append(str).append(" ) ORDER BY cid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "cid") );
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

    public List<Adcourses> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Adcourses> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" ORDER BY cid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Adcourses.class);
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
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" ORDER BY cid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "cid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adcourses last() {
        return last(TABLENAME);
    }

    public Adcourses last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" ORDER BY cid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Adcourses.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectGtKeyNum(final int cid, final int _num) {
        return selectGtKeyNum(cid, _num, TABLENAME);
    }

    public List<Adcourses> selectGtKeyNum(final int cid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE cid > :cid ORDER BY cid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("cid", cid);
            return super.queryForList(sql.toString(), params, Adcourses.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectGtKey(final int cid) {
        return selectGtKey(cid, TABLENAME);
    }

    public List<Adcourses> selectGtKey(final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE cid > :cid ORDER BY cid");
            Map params = newMap();
            params.put("cid", cid);
            return super.queryForList(sql.toString(), params, Adcourses.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int cid) {
        return selectGtKeyPKs(cid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" WHERE cid > :cid ORDER BY cid");
            Map params = newMap();
            params.put("cid", cid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "cid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adcourses selectByKey(final int cid) {
        return selectByKey(cid, TABLENAME);
    }

    public Adcourses selectByKey(final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE cid = :cid");
            Map params = newMap();
            params.put("cid", cid);
            return super.queryForObject(sql.toString(), params, Adcourses.class);
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
            sql.append("SELECT MAX(cid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByDepartid(final Integer departid) {
        return countByDepartid(departid, TABLENAME);
    }

    public int countByDepartid(final Integer departid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE departid = :departid ");
            Map params = newMap();
            params.put("departid", departid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectByDepartid(final Integer departid) {
        return selectByDepartid(departid, TABLENAME);
    }

    public List<Adcourses> selectByDepartid(final Integer departid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE departid = :departid ORDER BY cid ");
            Map params = newMap();
            params.put("departid", departid);
            return super.queryForList(sql.toString(), params, Adcourses.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByDepartidPKs(final Integer departid) {
        return selectByDepartidPKs(departid, TABLENAME);
    }

    public List<Integer> selectByDepartidPKs(final Integer departid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" WHERE departid = :departid ORDER BY cid ");
            Map params = newMap();
            params.put("departid", departid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "cid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Adcourses> selectPageByDepartid(final Integer departid, final int begin, final int num) {
        return selectPageByDepartid(departid, begin, num, TABLENAME);
    }

    public List<Adcourses> selectPageByDepartid(final Integer departid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE departid = :departid ORDER BY cid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("departid", departid);
            return super.queryForList(sql.toString(), params, Adcourses.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByDepartidPKs(final Integer departid, final int begin, final int num) {
        return selectPageByDepartidPKs(departid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByDepartidPKs(final Integer departid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" WHERE departid = :departid ORDER BY cid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("departid", departid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "cid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Adcourses selectByCid(final Integer cid) {
        return selectByCid(cid, TABLENAME);
    }

    public Adcourses selectByCid(final Integer cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" WHERE cid = :cid");
            Map params = newMap();
            params.put("cid", cid);
            return super.queryForObject(sql.toString(), params, Adcourses.class);
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

    public List<Adcourses> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Adcourses> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime, imgurl4major FROM ").append(TABLENAME2).append(" ORDER BY cid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Adcourses.class);
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
            sql.append("SELECT cid FROM ").append(TABLENAME2).append(" ORDER BY cid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "cid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Adcourses adcourses) {
        return updateByKey(adcourses, TABLENAME);
    }

    public int updateByKey(final Adcourses adcourses, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = adcourses.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE cid=:cid");
            return super.update(sql.toString(), adcourses);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Adcourses adcourses) {
        return asyncUpdate(adcourses, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Adcourses adcourses, final String TABLENAME2) {
        try {

            String _ustr = adcourses.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE cid=:cid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, adcourses);
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

    public int updateDepartidByKey(final int departid, final int cid){
        return updateDepartidByKey(departid, cid, TABLENAME);
    }

    public int updateDepartidByKey(final int departid, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET departid=departid+:departid WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("departid", departid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepartidWithMinByKey(final int cid, final int departid, final int _min){
        return updateDepartidWithMinByKey(cid, departid, _min, TABLENAME);
    }

    public int updateDepartidWithMinByKey(final int cid, final int departid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET departid = (select case when departid+:departid<=:_min then :_min else departid+:departid end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("departid", departid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepartidWithMinInKeys(final List<Integer> keys, final int departid, final int _min){
        return updateDepartidWithMinInKeys(keys, departid, _min, TABLENAME);
    }

    public int updateDepartidWithMinInKeys(final List<Integer> keys, final int departid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET departid = (select case when departid+:departid<=:_min then :_min else departid+:departid end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("departid", departid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepartidWithMaxByKey(final int cid, final int departid, final int _max){
        return updateDepartidWithMaxByKey(cid, departid, _max, TABLENAME);
    }

    public int updateDepartidWithMaxByKey(final int cid, final int departid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET departid = (select case when departid+:departid>=:_max then :_max else departid+:departid end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("departid", departid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepartidWithMaxInKeys(final List<Integer> keys, final int departid, final int _max){
        return updateDepartidWithMaxInKeys(keys, departid, _max, TABLENAME);
    }

    public int updateDepartidWithMaxInKeys(final List<Integer> keys, final int departid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET departid = (select case when departid+:departid>=:_max then :_max else departid+:departid end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("departid", departid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepartidWithMinMaxByKey(final int cid, final int departid, final int _min, final int _max){
        return updateDepartidWithMinMaxByKey(cid, departid, _min, _max, TABLENAME);
    }

    public int updateDepartidWithMinMaxByKey(final int cid, final int departid, final int _min, final int _max, final String TABLENAME2){
        if( departid < 0 ) {
            return updateDepartidWithMinByKey(cid, departid, _min, TABLENAME2);
        } else {
            return updateDepartidWithMaxByKey(cid, departid, _max, TABLENAME2);
        }
    }

    public int updateDepartidWithMinMaxInKeys(final List<Integer> keys, final int departid, final int _min, final int _max){
        return updateDepartidWithMinMaxInKeys(keys, departid, _min, _max, TABLENAME);
    }

    public int updateDepartidWithMinMaxInKeys(final List<Integer> keys, final int departid, final int _min, final int _max, final String TABLENAME2){
        if( departid < 0 ) {
            return updateDepartidWithMinInKeys(keys, departid, _min, TABLENAME2);
        } else {
            return updateDepartidWithMaxInKeys(keys, departid, _max, TABLENAME2);
        }
    }

    public int updateProfitAgentByKey(final int profitAgent, final int cid){
        return updateProfitAgentByKey(profitAgent, cid, TABLENAME);
    }

    public int updateProfitAgentByKey(final int profitAgent, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitAgent=profitAgent+:profitAgent WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("profitAgent", profitAgent);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitAgentWithMinByKey(final int cid, final int profitAgent, final int _min){
        return updateProfitAgentWithMinByKey(cid, profitAgent, _min, TABLENAME);
    }

    public int updateProfitAgentWithMinByKey(final int cid, final int profitAgent, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitAgent = (select case when profitAgent+:profitAgent<=:_min then :_min else profitAgent+:profitAgent end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("profitAgent", profitAgent);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitAgentWithMinInKeys(final List<Integer> keys, final int profitAgent, final int _min){
        return updateProfitAgentWithMinInKeys(keys, profitAgent, _min, TABLENAME);
    }

    public int updateProfitAgentWithMinInKeys(final List<Integer> keys, final int profitAgent, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitAgent = (select case when profitAgent+:profitAgent<=:_min then :_min else profitAgent+:profitAgent end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("profitAgent", profitAgent);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitAgentWithMaxByKey(final int cid, final int profitAgent, final int _max){
        return updateProfitAgentWithMaxByKey(cid, profitAgent, _max, TABLENAME);
    }

    public int updateProfitAgentWithMaxByKey(final int cid, final int profitAgent, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitAgent = (select case when profitAgent+:profitAgent>=:_max then :_max else profitAgent+:profitAgent end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("profitAgent", profitAgent);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitAgentWithMaxInKeys(final List<Integer> keys, final int profitAgent, final int _max){
        return updateProfitAgentWithMaxInKeys(keys, profitAgent, _max, TABLENAME);
    }

    public int updateProfitAgentWithMaxInKeys(final List<Integer> keys, final int profitAgent, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitAgent = (select case when profitAgent+:profitAgent>=:_max then :_max else profitAgent+:profitAgent end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("profitAgent", profitAgent);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitAgentWithMinMaxByKey(final int cid, final int profitAgent, final int _min, final int _max){
        return updateProfitAgentWithMinMaxByKey(cid, profitAgent, _min, _max, TABLENAME);
    }

    public int updateProfitAgentWithMinMaxByKey(final int cid, final int profitAgent, final int _min, final int _max, final String TABLENAME2){
        if( profitAgent < 0 ) {
            return updateProfitAgentWithMinByKey(cid, profitAgent, _min, TABLENAME2);
        } else {
            return updateProfitAgentWithMaxByKey(cid, profitAgent, _max, TABLENAME2);
        }
    }

    public int updateProfitAgentWithMinMaxInKeys(final List<Integer> keys, final int profitAgent, final int _min, final int _max){
        return updateProfitAgentWithMinMaxInKeys(keys, profitAgent, _min, _max, TABLENAME);
    }

    public int updateProfitAgentWithMinMaxInKeys(final List<Integer> keys, final int profitAgent, final int _min, final int _max, final String TABLENAME2){
        if( profitAgent < 0 ) {
            return updateProfitAgentWithMinInKeys(keys, profitAgent, _min, TABLENAME2);
        } else {
            return updateProfitAgentWithMaxInKeys(keys, profitAgent, _max, TABLENAME2);
        }
    }

    public int updateProfitOwnerByKey(final int profitOwner, final int cid){
        return updateProfitOwnerByKey(profitOwner, cid, TABLENAME);
    }

    public int updateProfitOwnerByKey(final int profitOwner, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitOwner=profitOwner+:profitOwner WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("profitOwner", profitOwner);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitOwnerWithMinByKey(final int cid, final int profitOwner, final int _min){
        return updateProfitOwnerWithMinByKey(cid, profitOwner, _min, TABLENAME);
    }

    public int updateProfitOwnerWithMinByKey(final int cid, final int profitOwner, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitOwner = (select case when profitOwner+:profitOwner<=:_min then :_min else profitOwner+:profitOwner end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("profitOwner", profitOwner);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitOwnerWithMinInKeys(final List<Integer> keys, final int profitOwner, final int _min){
        return updateProfitOwnerWithMinInKeys(keys, profitOwner, _min, TABLENAME);
    }

    public int updateProfitOwnerWithMinInKeys(final List<Integer> keys, final int profitOwner, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitOwner = (select case when profitOwner+:profitOwner<=:_min then :_min else profitOwner+:profitOwner end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("profitOwner", profitOwner);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitOwnerWithMaxByKey(final int cid, final int profitOwner, final int _max){
        return updateProfitOwnerWithMaxByKey(cid, profitOwner, _max, TABLENAME);
    }

    public int updateProfitOwnerWithMaxByKey(final int cid, final int profitOwner, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitOwner = (select case when profitOwner+:profitOwner>=:_max then :_max else profitOwner+:profitOwner end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("profitOwner", profitOwner);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitOwnerWithMaxInKeys(final List<Integer> keys, final int profitOwner, final int _max){
        return updateProfitOwnerWithMaxInKeys(keys, profitOwner, _max, TABLENAME);
    }

    public int updateProfitOwnerWithMaxInKeys(final List<Integer> keys, final int profitOwner, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET profitOwner = (select case when profitOwner+:profitOwner>=:_max then :_max else profitOwner+:profitOwner end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("profitOwner", profitOwner);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProfitOwnerWithMinMaxByKey(final int cid, final int profitOwner, final int _min, final int _max){
        return updateProfitOwnerWithMinMaxByKey(cid, profitOwner, _min, _max, TABLENAME);
    }

    public int updateProfitOwnerWithMinMaxByKey(final int cid, final int profitOwner, final int _min, final int _max, final String TABLENAME2){
        if( profitOwner < 0 ) {
            return updateProfitOwnerWithMinByKey(cid, profitOwner, _min, TABLENAME2);
        } else {
            return updateProfitOwnerWithMaxByKey(cid, profitOwner, _max, TABLENAME2);
        }
    }

    public int updateProfitOwnerWithMinMaxInKeys(final List<Integer> keys, final int profitOwner, final int _min, final int _max){
        return updateProfitOwnerWithMinMaxInKeys(keys, profitOwner, _min, _max, TABLENAME);
    }

    public int updateProfitOwnerWithMinMaxInKeys(final List<Integer> keys, final int profitOwner, final int _min, final int _max, final String TABLENAME2){
        if( profitOwner < 0 ) {
            return updateProfitOwnerWithMinInKeys(keys, profitOwner, _min, TABLENAME2);
        } else {
            return updateProfitOwnerWithMaxInKeys(keys, profitOwner, _max, TABLENAME2);
        }
    }

    public int updateDepositByKey(final int deposit, final int cid){
        return updateDepositByKey(deposit, cid, TABLENAME);
    }

    public int updateDepositByKey(final int deposit, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deposit=deposit+:deposit WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("deposit", deposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepositWithMinByKey(final int cid, final int deposit, final int _min){
        return updateDepositWithMinByKey(cid, deposit, _min, TABLENAME);
    }

    public int updateDepositWithMinByKey(final int cid, final int deposit, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deposit = (select case when deposit+:deposit<=:_min then :_min else deposit+:deposit end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("deposit", deposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepositWithMinInKeys(final List<Integer> keys, final int deposit, final int _min){
        return updateDepositWithMinInKeys(keys, deposit, _min, TABLENAME);
    }

    public int updateDepositWithMinInKeys(final List<Integer> keys, final int deposit, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deposit = (select case when deposit+:deposit<=:_min then :_min else deposit+:deposit end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("deposit", deposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepositWithMaxByKey(final int cid, final int deposit, final int _max){
        return updateDepositWithMaxByKey(cid, deposit, _max, TABLENAME);
    }

    public int updateDepositWithMaxByKey(final int cid, final int deposit, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deposit = (select case when deposit+:deposit>=:_max then :_max else deposit+:deposit end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("deposit", deposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepositWithMaxInKeys(final List<Integer> keys, final int deposit, final int _max){
        return updateDepositWithMaxInKeys(keys, deposit, _max, TABLENAME);
    }

    public int updateDepositWithMaxInKeys(final List<Integer> keys, final int deposit, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deposit = (select case when deposit+:deposit>=:_max then :_max else deposit+:deposit end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("deposit", deposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDepositWithMinMaxByKey(final int cid, final int deposit, final int _min, final int _max){
        return updateDepositWithMinMaxByKey(cid, deposit, _min, _max, TABLENAME);
    }

    public int updateDepositWithMinMaxByKey(final int cid, final int deposit, final int _min, final int _max, final String TABLENAME2){
        if( deposit < 0 ) {
            return updateDepositWithMinByKey(cid, deposit, _min, TABLENAME2);
        } else {
            return updateDepositWithMaxByKey(cid, deposit, _max, TABLENAME2);
        }
    }

    public int updateDepositWithMinMaxInKeys(final List<Integer> keys, final int deposit, final int _min, final int _max){
        return updateDepositWithMinMaxInKeys(keys, deposit, _min, _max, TABLENAME);
    }

    public int updateDepositWithMinMaxInKeys(final List<Integer> keys, final int deposit, final int _min, final int _max, final String TABLENAME2){
        if( deposit < 0 ) {
            return updateDepositWithMinInKeys(keys, deposit, _min, TABLENAME2);
        } else {
            return updateDepositWithMaxInKeys(keys, deposit, _max, TABLENAME2);
        }
    }

    public int updateBonusByKey(final int bonus, final int cid){
        return updateBonusByKey(bonus, cid, TABLENAME);
    }

    public int updateBonusByKey(final int bonus, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus=bonus+:bonus WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMinByKey(final int cid, final int bonus, final int _min){
        return updateBonusWithMinByKey(cid, bonus, _min, TABLENAME);
    }

    public int updateBonusWithMinByKey(final int cid, final int bonus, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus<=:_min then :_min else bonus+:bonus end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMinInKeys(final List<Integer> keys, final int bonus, final int _min){
        return updateBonusWithMinInKeys(keys, bonus, _min, TABLENAME);
    }

    public int updateBonusWithMinInKeys(final List<Integer> keys, final int bonus, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus<=:_min then :_min else bonus+:bonus end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMaxByKey(final int cid, final int bonus, final int _max){
        return updateBonusWithMaxByKey(cid, bonus, _max, TABLENAME);
    }

    public int updateBonusWithMaxByKey(final int cid, final int bonus, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus>=:_max then :_max else bonus+:bonus end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMaxInKeys(final List<Integer> keys, final int bonus, final int _max){
        return updateBonusWithMaxInKeys(keys, bonus, _max, TABLENAME);
    }

    public int updateBonusWithMaxInKeys(final List<Integer> keys, final int bonus, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus>=:_max then :_max else bonus+:bonus end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMinMaxByKey(final int cid, final int bonus, final int _min, final int _max){
        return updateBonusWithMinMaxByKey(cid, bonus, _min, _max, TABLENAME);
    }

    public int updateBonusWithMinMaxByKey(final int cid, final int bonus, final int _min, final int _max, final String TABLENAME2){
        if( bonus < 0 ) {
            return updateBonusWithMinByKey(cid, bonus, _min, TABLENAME2);
        } else {
            return updateBonusWithMaxByKey(cid, bonus, _max, TABLENAME2);
        }
    }

    public int updateBonusWithMinMaxInKeys(final List<Integer> keys, final int bonus, final int _min, final int _max){
        return updateBonusWithMinMaxInKeys(keys, bonus, _min, _max, TABLENAME);
    }

    public int updateBonusWithMinMaxInKeys(final List<Integer> keys, final int bonus, final int _min, final int _max, final String TABLENAME2){
        if( bonus < 0 ) {
            return updateBonusWithMinInKeys(keys, bonus, _min, TABLENAME2);
        } else {
            return updateBonusWithMaxInKeys(keys, bonus, _max, TABLENAME2);
        }
    }

    public int updateWrongByKey(final int wrong, final int cid){
        return updateWrongByKey(wrong, cid, TABLENAME);
    }

    public int updateWrongByKey(final int wrong, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong=wrong+:wrong WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("wrong", wrong);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWrongWithMinByKey(final int cid, final int wrong, final int _min){
        return updateWrongWithMinByKey(cid, wrong, _min, TABLENAME);
    }

    public int updateWrongWithMinByKey(final int cid, final int wrong, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong<=:_min then :_min else wrong+:wrong end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("wrong", wrong);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWrongWithMinInKeys(final List<Integer> keys, final int wrong, final int _min){
        return updateWrongWithMinInKeys(keys, wrong, _min, TABLENAME);
    }

    public int updateWrongWithMinInKeys(final List<Integer> keys, final int wrong, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong<=:_min then :_min else wrong+:wrong end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("wrong", wrong);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWrongWithMaxByKey(final int cid, final int wrong, final int _max){
        return updateWrongWithMaxByKey(cid, wrong, _max, TABLENAME);
    }

    public int updateWrongWithMaxByKey(final int cid, final int wrong, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong>=:_max then :_max else wrong+:wrong end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("wrong", wrong);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWrongWithMaxInKeys(final List<Integer> keys, final int wrong, final int _max){
        return updateWrongWithMaxInKeys(keys, wrong, _max, TABLENAME);
    }

    public int updateWrongWithMaxInKeys(final List<Integer> keys, final int wrong, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong>=:_max then :_max else wrong+:wrong end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("wrong", wrong);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWrongWithMinMaxByKey(final int cid, final int wrong, final int _min, final int _max){
        return updateWrongWithMinMaxByKey(cid, wrong, _min, _max, TABLENAME);
    }

    public int updateWrongWithMinMaxByKey(final int cid, final int wrong, final int _min, final int _max, final String TABLENAME2){
        if( wrong < 0 ) {
            return updateWrongWithMinByKey(cid, wrong, _min, TABLENAME2);
        } else {
            return updateWrongWithMaxByKey(cid, wrong, _max, TABLENAME2);
        }
    }

    public int updateWrongWithMinMaxInKeys(final List<Integer> keys, final int wrong, final int _min, final int _max){
        return updateWrongWithMinMaxInKeys(keys, wrong, _min, _max, TABLENAME);
    }

    public int updateWrongWithMinMaxInKeys(final List<Integer> keys, final int wrong, final int _min, final int _max, final String TABLENAME2){
        if( wrong < 0 ) {
            return updateWrongWithMinInKeys(keys, wrong, _min, TABLENAME2);
        } else {
            return updateWrongWithMaxInKeys(keys, wrong, _max, TABLENAME2);
        }
    }

    public int updateProgramByKey(final int program, final int cid){
        return updateProgramByKey(program, cid, TABLENAME);
    }

    public int updateProgramByKey(final int program, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET program=program+:program WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("program", program);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProgramWithMinByKey(final int cid, final int program, final int _min){
        return updateProgramWithMinByKey(cid, program, _min, TABLENAME);
    }

    public int updateProgramWithMinByKey(final int cid, final int program, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET program = (select case when program+:program<=:_min then :_min else program+:program end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("program", program);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProgramWithMinInKeys(final List<Integer> keys, final int program, final int _min){
        return updateProgramWithMinInKeys(keys, program, _min, TABLENAME);
    }

    public int updateProgramWithMinInKeys(final List<Integer> keys, final int program, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET program = (select case when program+:program<=:_min then :_min else program+:program end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("program", program);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProgramWithMaxByKey(final int cid, final int program, final int _max){
        return updateProgramWithMaxByKey(cid, program, _max, TABLENAME);
    }

    public int updateProgramWithMaxByKey(final int cid, final int program, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET program = (select case when program+:program>=:_max then :_max else program+:program end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("program", program);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProgramWithMaxInKeys(final List<Integer> keys, final int program, final int _max){
        return updateProgramWithMaxInKeys(keys, program, _max, TABLENAME);
    }

    public int updateProgramWithMaxInKeys(final List<Integer> keys, final int program, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET program = (select case when program+:program>=:_max then :_max else program+:program end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("program", program);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProgramWithMinMaxByKey(final int cid, final int program, final int _min, final int _max){
        return updateProgramWithMinMaxByKey(cid, program, _min, _max, TABLENAME);
    }

    public int updateProgramWithMinMaxByKey(final int cid, final int program, final int _min, final int _max, final String TABLENAME2){
        if( program < 0 ) {
            return updateProgramWithMinByKey(cid, program, _min, TABLENAME2);
        } else {
            return updateProgramWithMaxByKey(cid, program, _max, TABLENAME2);
        }
    }

    public int updateProgramWithMinMaxInKeys(final List<Integer> keys, final int program, final int _min, final int _max){
        return updateProgramWithMinMaxInKeys(keys, program, _min, _max, TABLENAME);
    }

    public int updateProgramWithMinMaxInKeys(final List<Integer> keys, final int program, final int _min, final int _max, final String TABLENAME2){
        if( program < 0 ) {
            return updateProgramWithMinInKeys(keys, program, _min, TABLENAME2);
        } else {
            return updateProgramWithMaxInKeys(keys, program, _max, TABLENAME2);
        }
    }

    public int updateArtByKey(final int art, final int cid){
        return updateArtByKey(art, cid, TABLENAME);
    }

    public int updateArtByKey(final int art, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET art=art+:art WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("art", art);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtWithMinByKey(final int cid, final int art, final int _min){
        return updateArtWithMinByKey(cid, art, _min, TABLENAME);
    }

    public int updateArtWithMinByKey(final int cid, final int art, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET art = (select case when art+:art<=:_min then :_min else art+:art end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_min", _min);
            params.put("art", art);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtWithMinInKeys(final List<Integer> keys, final int art, final int _min){
        return updateArtWithMinInKeys(keys, art, _min, TABLENAME);
    }

    public int updateArtWithMinInKeys(final List<Integer> keys, final int art, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET art = (select case when art+:art<=:_min then :_min else art+:art end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("art", art);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtWithMaxByKey(final int cid, final int art, final int _max){
        return updateArtWithMaxByKey(cid, art, _max, TABLENAME);
    }

    public int updateArtWithMaxByKey(final int cid, final int art, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET art = (select case when art+:art>=:_max then :_max else art+:art end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("_max", _max);
            params.put("art", art);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtWithMaxInKeys(final List<Integer> keys, final int art, final int _max){
        return updateArtWithMaxInKeys(keys, art, _max, TABLENAME);
    }

    public int updateArtWithMaxInKeys(final List<Integer> keys, final int art, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET art = (select case when art+:art>=:_max then :_max else art+:art end) WHERE cid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("art", art);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtWithMinMaxByKey(final int cid, final int art, final int _min, final int _max){
        return updateArtWithMinMaxByKey(cid, art, _min, _max, TABLENAME);
    }

    public int updateArtWithMinMaxByKey(final int cid, final int art, final int _min, final int _max, final String TABLENAME2){
        if( art < 0 ) {
            return updateArtWithMinByKey(cid, art, _min, TABLENAME2);
        } else {
            return updateArtWithMaxByKey(cid, art, _max, TABLENAME2);
        }
    }

    public int updateArtWithMinMaxInKeys(final List<Integer> keys, final int art, final int _min, final int _max){
        return updateArtWithMinMaxInKeys(keys, art, _min, _max, TABLENAME);
    }

    public int updateArtWithMinMaxInKeys(final List<Integer> keys, final int art, final int _min, final int _max, final String TABLENAME2){
        if( art < 0 ) {
            return updateArtWithMinInKeys(keys, art, _min, TABLENAME2);
        } else {
            return updateArtWithMaxInKeys(keys, art, _max, TABLENAME2);
        }
    }

    public int updateStatusByKey(final int status, final int cid){
        return updateStatusByKey(status, cid, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int cid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int cid, final int status, final int _min){
        return updateStatusWithMinByKey(cid, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int cid, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE cid in (").append(str).append(")");
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

    public int updateStatusWithMaxByKey(final int cid, final int status, final int _max){
        return updateStatusWithMaxByKey(cid, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int cid, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE cid=:cid");
            Map params = newMap();
            params.put("cid", cid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE cid in (").append(str).append(")");
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

    public int updateStatusWithMinMaxByKey(final int cid, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(cid, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int cid, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(cid, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(cid, status, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Adcourses> adcoursess) {
        return updateByKey(adcoursess, TABLENAME);
    }

    public int[] updateByKey (final List<Adcourses> adcoursess, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(adcoursess == null || adcoursess.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET departid=:departid, nmMajor=:nmMajor, nmLevel=:nmLevel, nmSub=:nmSub, nmArea=:nmArea, profitAgent=:profitAgent, profitOwner=:profitOwner, deposit=:deposit, bonus=:bonus, wrong=:wrong, program=:program, art=:art, status=:status, createtime=:createtime, imgurl4major=:imgurl4major WHERE cid=:cid");
            return super.batchUpdate2(sql.toString(), adcoursess);
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
                "	`cid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`departid`  INT(11) NOT NULL," +
                "	`nmMajor`  TINYTEXT NOT NULL," +
                "	`nmLevel`  TINYTEXT NOT NULL," +
                "	`nmSub`  TINYTEXT NOT NULL," +
                "	`nmArea`  TINYTEXT NOT NULL," +
                "	`profitAgent`  INT(11) NOT NULL," +
                "	`profitOwner`  INT(11) NOT NULL," +
                "	`deposit`  INT(11) NOT NULL," +
                "	`bonus`  INT(11) NOT NULL," +
                "	`wrong`  INT(11) NOT NULL," +
                "	`program`  INT(11) NOT NULL," +
                "	`art`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`imgurl4major`  VARCHAR(128) NOT NULL," +
                "	PRIMARY KEY (`cid`)," +
                "	KEY `departid` (`departid`)" +
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
                "	`cid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`departid`  INT(11) NOT NULL," +
                "	`nmMajor`  TINYTEXT NOT NULL," +
                "	`nmLevel`  TINYTEXT NOT NULL," +
                "	`nmSub`  TINYTEXT NOT NULL," +
                "	`nmArea`  TINYTEXT NOT NULL," +
                "	`profitAgent`  INT(11) NOT NULL," +
                "	`profitOwner`  INT(11) NOT NULL," +
                "	`deposit`  INT(11) NOT NULL," +
                "	`bonus`  INT(11) NOT NULL," +
                "	`wrong`  INT(11) NOT NULL," +
                "	`program`  INT(11) NOT NULL," +
                "	`art`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`imgurl4major`  VARCHAR(128) NOT NULL," +
                "	PRIMARY KEY (`cid`)," +
                "	KEY `departid` (`departid`)" +
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
