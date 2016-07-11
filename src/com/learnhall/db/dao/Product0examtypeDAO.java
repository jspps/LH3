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

//learnhall3_design - product0examtype
@SuppressWarnings({"rawtypes", "unchecked"})
public class Product0examtypeDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Product0examtypeDAO.class);

    public static final String TABLE = "product0examtype";
    public static String TABLENAME = "product0examtype";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "lhubid", "productid", "examtypeid", "buymoney", "friend", "kbi", "status", "createtime"};
    public static String coulmns = "id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime";
    public static String coulmns2 = "lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime";

    public Product0examtypeDAO(Connection conn) {
        super(conn);
    }

    public Product0examtypeDAO(DataSource ds) {
        super(ds);
    }

    public Product0examtypeDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Product0examtype product0examtype) {
        return insert(product0examtype, TABLENAME);
    }

    public int insert(final Product0examtype product0examtype, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            product0examtype.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime) VALUES (:lhubid, :productid, :examtypeid, :buymoney, :friend, :kbi, :status, :createtime)");
            Map map = super.insert(sql.toString(), product0examtype);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Product0examtype product0examtype) {
        return asyncInsert(product0examtype, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Product0examtype product0examtype, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(product0examtype, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Product0examtype product0examtype) {
        return asyncInsert2(product0examtype, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Product0examtype product0examtype, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(product0examtype, TABLENAME2);
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

    public int insert2(final Product0examtype product0examtype) {
        return insert2(product0examtype, TABLENAME);
    }

    public int insert2(final Product0examtype product0examtype, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            product0examtype.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime) VALUES (:id, :lhubid, :productid, :examtypeid, :buymoney, :friend, :kbi, :status, :createtime)");
            Map map = super.insert(sql.toString(), product0examtype);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Product0examtype> product0examtypes) {
        return insert(product0examtypes, TABLENAME);
    }

    public int[] insert(final List<Product0examtype> product0examtypes, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(product0examtypes == null || product0examtypes.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime) VALUES (:lhubid, :productid, :examtypeid, :buymoney, :friend, :kbi, :status, :createtime)");
            return super.batchInsert(sql.toString(), product0examtypes);
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

    public int deleteInBeans(final List<Product0examtype> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Product0examtype> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Product0examtype product0examtype = beans.get(i);
                int id = product0examtype.id;
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

    public List<Product0examtype> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Product0examtype> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Product0examtype.class);
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
            sql.append("SELECT id, lhubid, productid, examtypeid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Product0examtype> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Product0examtype> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
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

    public List<Product0examtype> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Product0examtype> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Product0examtype.class);
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

    public Product0examtype last() {
        return last(TABLENAME);
    }

    public Product0examtype last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Product0examtype.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Product0examtype> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Product0examtype> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
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

    public Product0examtype selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Product0examtype selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Product0examtype.class);
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

    public int countByExamtypeid(final Integer examtypeid) {
        return countByExamtypeid(examtypeid, TABLENAME);
    }

    public int countByExamtypeid(final Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectByExamtypeid(final Integer examtypeid) {
        return selectByExamtypeid(examtypeid, TABLENAME);
    }

    public List<Product0examtype> selectByExamtypeid(final Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id ");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExamtypeidPKs(final Integer examtypeid) {
        return selectByExamtypeidPKs(examtypeid, TABLENAME);
    }

    public List<Integer> selectByExamtypeidPKs(final Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id ");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
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

    public List<Product0examtype> selectPageByExamtypeid(final Integer examtypeid, final int begin, final int num) {
        return selectPageByExamtypeid(examtypeid, begin, num, TABLENAME);
    }

    public List<Product0examtype> selectPageByExamtypeid(final Integer examtypeid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExamtypeidPKs(final Integer examtypeid, final int begin, final int num) {
        return selectPageByExamtypeidPKs(examtypeid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExamtypeidPKs(final Integer examtypeid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE examtypeid = :examtypeid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examtypeid", examtypeid);
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

    public int countByLhubid(final Integer lhubid) {
        return countByLhubid(lhubid, TABLENAME);
    }

    public int countByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectByLhubid(final Integer lhubid) {
        return selectByLhubid(lhubid, TABLENAME);
    }

    public List<Product0examtype> selectByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByLhubidPKs(final Integer lhubid) {
        return selectByLhubidPKs(lhubid, TABLENAME);
    }

    public List<Integer> selectByLhubidPKs(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
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

    public List<Product0examtype> selectPageByLhubid(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubid(lhubid, begin, num, TABLENAME);
    }

    public List<Product0examtype> selectPageByLhubid(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByLhubidPKs(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubidPKs(lhubid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByLhubidPKs(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
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

    public Product0examtype selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Product0examtype selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByProductid(final Integer productid) {
        return countByProductid(productid, TABLENAME);
    }

    public int countByProductid(final Integer productid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE productid = :productid ");
            Map params = newMap();
            params.put("productid", productid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product0examtype> selectByProductid(final Integer productid) {
        return selectByProductid(productid, TABLENAME);
    }

    public List<Product0examtype> selectByProductid(final Integer productid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id ");
            Map params = newMap();
            params.put("productid", productid);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByProductidPKs(final Integer productid) {
        return selectByProductidPKs(productid, TABLENAME);
    }

    public List<Integer> selectByProductidPKs(final Integer productid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id ");
            Map params = newMap();
            params.put("productid", productid);
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

    public List<Product0examtype> selectPageByProductid(final Integer productid, final int begin, final int num) {
        return selectPageByProductid(productid, begin, num, TABLENAME);
    }

    public List<Product0examtype> selectPageByProductid(final Integer productid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("productid", productid);
            return super.queryForList(sql.toString(), params, Product0examtype.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByProductidPKs(final Integer productid, final int begin, final int num) {
        return selectPageByProductidPKs(productid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByProductidPKs(final Integer productid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("productid", productid);
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

    public Product0examtype selectByLhubidProductidExamtypeid(final Integer lhubid, Integer productid, Integer examtypeid) {
        return selectByLhubidProductidExamtypeid(lhubid, productid, examtypeid, TABLENAME);
    }

    public Product0examtype selectByLhubidProductidExamtypeid(final Integer lhubid, Integer productid, Integer examtypeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid=:lhubid AND productid=:productid AND examtypeid=:examtypeid");
            Map params = newMap();
            params.put("lhubid", lhubid);
            params.put("productid", productid);
            params.put("examtypeid", examtypeid);
            return super.queryForObject(sql.toString(), params, Product0examtype.class);
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

    public List<Product0examtype> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Product0examtype> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Product0examtype.class);
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

    public int updateByKey(final Product0examtype product0examtype) {
        return updateByKey(product0examtype, TABLENAME);
    }

    public int updateByKey(final Product0examtype product0examtype, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = product0examtype.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), product0examtype);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Product0examtype product0examtype) {
        return asyncUpdate(product0examtype, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Product0examtype product0examtype, final String TABLENAME2) {
        try {

            String _ustr = product0examtype.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, product0examtype);
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

    public int updateProductidByKey(final int productid, final int id){
        return updateProductidByKey(productid, id, TABLENAME);
    }

    public int updateProductidByKey(final int productid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid=productid+:productid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinByKey(final int id, final int productid, final int _min){
        return updateProductidWithMinByKey(id, productid, _min, TABLENAME);
    }

    public int updateProductidWithMinByKey(final int id, final int productid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid<=:_min then :_min else productid+:productid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinInKeys(final List<Integer> keys, final int productid, final int _min){
        return updateProductidWithMinInKeys(keys, productid, _min, TABLENAME);
    }

    public int updateProductidWithMinInKeys(final List<Integer> keys, final int productid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid<=:_min then :_min else productid+:productid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMaxByKey(final int id, final int productid, final int _max){
        return updateProductidWithMaxByKey(id, productid, _max, TABLENAME);
    }

    public int updateProductidWithMaxByKey(final int id, final int productid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid>=:_max then :_max else productid+:productid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMaxInKeys(final List<Integer> keys, final int productid, final int _max){
        return updateProductidWithMaxInKeys(keys, productid, _max, TABLENAME);
    }

    public int updateProductidWithMaxInKeys(final List<Integer> keys, final int productid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid>=:_max then :_max else productid+:productid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinMaxByKey(final int id, final int productid, final int _min, final int _max){
        return updateProductidWithMinMaxByKey(id, productid, _min, _max, TABLENAME);
    }

    public int updateProductidWithMinMaxByKey(final int id, final int productid, final int _min, final int _max, final String TABLENAME2){
        if( productid < 0 ) {
            return updateProductidWithMinByKey(id, productid, _min, TABLENAME2);
        } else {
            return updateProductidWithMaxByKey(id, productid, _max, TABLENAME2);
        }
    }

    public int updateProductidWithMinMaxInKeys(final List<Integer> keys, final int productid, final int _min, final int _max){
        return updateProductidWithMinMaxInKeys(keys, productid, _min, _max, TABLENAME);
    }

    public int updateProductidWithMinMaxInKeys(final List<Integer> keys, final int productid, final int _min, final int _max, final String TABLENAME2){
        if( productid < 0 ) {
            return updateProductidWithMinInKeys(keys, productid, _min, TABLENAME2);
        } else {
            return updateProductidWithMaxInKeys(keys, productid, _max, TABLENAME2);
        }
    }

    public int updateExamtypeidByKey(final int examtypeid, final int id){
        return updateExamtypeidByKey(examtypeid, id, TABLENAME);
    }

    public int updateExamtypeidByKey(final int examtypeid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid=examtypeid+:examtypeid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMinByKey(final int id, final int examtypeid, final int _min){
        return updateExamtypeidWithMinByKey(id, examtypeid, _min, TABLENAME);
    }

    public int updateExamtypeidWithMinByKey(final int id, final int examtypeid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid<=:_min then :_min else examtypeid+:examtypeid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMinInKeys(final List<Integer> keys, final int examtypeid, final int _min){
        return updateExamtypeidWithMinInKeys(keys, examtypeid, _min, TABLENAME);
    }

    public int updateExamtypeidWithMinInKeys(final List<Integer> keys, final int examtypeid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid<=:_min then :_min else examtypeid+:examtypeid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMaxByKey(final int id, final int examtypeid, final int _max){
        return updateExamtypeidWithMaxByKey(id, examtypeid, _max, TABLENAME);
    }

    public int updateExamtypeidWithMaxByKey(final int id, final int examtypeid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid>=:_max then :_max else examtypeid+:examtypeid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMaxInKeys(final List<Integer> keys, final int examtypeid, final int _max){
        return updateExamtypeidWithMaxInKeys(keys, examtypeid, _max, TABLENAME);
    }

    public int updateExamtypeidWithMaxInKeys(final List<Integer> keys, final int examtypeid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examtypeid = (select case when examtypeid+:examtypeid>=:_max then :_max else examtypeid+:examtypeid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examtypeid", examtypeid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamtypeidWithMinMaxByKey(final int id, final int examtypeid, final int _min, final int _max){
        return updateExamtypeidWithMinMaxByKey(id, examtypeid, _min, _max, TABLENAME);
    }

    public int updateExamtypeidWithMinMaxByKey(final int id, final int examtypeid, final int _min, final int _max, final String TABLENAME2){
        if( examtypeid < 0 ) {
            return updateExamtypeidWithMinByKey(id, examtypeid, _min, TABLENAME2);
        } else {
            return updateExamtypeidWithMaxByKey(id, examtypeid, _max, TABLENAME2);
        }
    }

    public int updateExamtypeidWithMinMaxInKeys(final List<Integer> keys, final int examtypeid, final int _min, final int _max){
        return updateExamtypeidWithMinMaxInKeys(keys, examtypeid, _min, _max, TABLENAME);
    }

    public int updateExamtypeidWithMinMaxInKeys(final List<Integer> keys, final int examtypeid, final int _min, final int _max, final String TABLENAME2){
        if( examtypeid < 0 ) {
            return updateExamtypeidWithMinInKeys(keys, examtypeid, _min, TABLENAME2);
        } else {
            return updateExamtypeidWithMaxInKeys(keys, examtypeid, _max, TABLENAME2);
        }
    }

    public int updateBuymoneyByKey(final double buymoney, final int id){
        return updateBuymoneyByKey(buymoney, id, TABLENAME);
    }

    public int updateBuymoneyByKey(final double buymoney, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buymoney=buymoney+:buymoney WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("buymoney", buymoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuymoneyWithMinByKey(final int id, final double buymoney, final double _min){
        return updateBuymoneyWithMinByKey(id, buymoney, _min, TABLENAME);
    }

    public int updateBuymoneyWithMinByKey(final int id, final double buymoney, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buymoney = (select case when buymoney+:buymoney<=:_min then :_min else buymoney+:buymoney end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("buymoney", buymoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuymoneyWithMinInKeys(final List<Integer> keys, final double buymoney, final double _min){
        return updateBuymoneyWithMinInKeys(keys, buymoney, _min, TABLENAME);
    }

    public int updateBuymoneyWithMinInKeys(final List<Integer> keys, final double buymoney, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buymoney = (select case when buymoney+:buymoney<=:_min then :_min else buymoney+:buymoney end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("buymoney", buymoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuymoneyWithMaxByKey(final int id, final double buymoney, final double _max){
        return updateBuymoneyWithMaxByKey(id, buymoney, _max, TABLENAME);
    }

    public int updateBuymoneyWithMaxByKey(final int id, final double buymoney, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buymoney = (select case when buymoney+:buymoney>=:_max then :_max else buymoney+:buymoney end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("buymoney", buymoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuymoneyWithMaxInKeys(final List<Integer> keys, final double buymoney, final double _max){
        return updateBuymoneyWithMaxInKeys(keys, buymoney, _max, TABLENAME);
    }

    public int updateBuymoneyWithMaxInKeys(final List<Integer> keys, final double buymoney, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buymoney = (select case when buymoney+:buymoney>=:_max then :_max else buymoney+:buymoney end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("buymoney", buymoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuymoneyWithMinMaxByKey(final int id, final double buymoney, final double _min, final double _max){
        return updateBuymoneyWithMinMaxByKey(id, buymoney, _min, _max, TABLENAME);
    }

    public int updateBuymoneyWithMinMaxByKey(final int id, final double buymoney, final double _min, final double _max, final String TABLENAME2){
        if( buymoney < 0 ) {
            return updateBuymoneyWithMinByKey(id, buymoney, _min, TABLENAME2);
        } else {
            return updateBuymoneyWithMaxByKey(id, buymoney, _max, TABLENAME2);
        }
    }

    public int updateBuymoneyWithMinMaxInKeys(final List<Integer> keys, final double buymoney, final double _min, final double _max){
        return updateBuymoneyWithMinMaxInKeys(keys, buymoney, _min, _max, TABLENAME);
    }

    public int updateBuymoneyWithMinMaxInKeys(final List<Integer> keys, final double buymoney, final double _min, final double _max, final String TABLENAME2){
        if( buymoney < 0 ) {
            return updateBuymoneyWithMinInKeys(keys, buymoney, _min, TABLENAME2);
        } else {
            return updateBuymoneyWithMaxInKeys(keys, buymoney, _max, TABLENAME2);
        }
    }

    public int updateFriendByKey(final int friend, final int id){
        return updateFriendByKey(friend, id, TABLENAME);
    }

    public int updateFriendByKey(final int friend, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET friend=friend+:friend WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("friend", friend);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFriendWithMinByKey(final int id, final int friend, final int _min){
        return updateFriendWithMinByKey(id, friend, _min, TABLENAME);
    }

    public int updateFriendWithMinByKey(final int id, final int friend, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET friend = (select case when friend+:friend<=:_min then :_min else friend+:friend end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("friend", friend);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFriendWithMinInKeys(final List<Integer> keys, final int friend, final int _min){
        return updateFriendWithMinInKeys(keys, friend, _min, TABLENAME);
    }

    public int updateFriendWithMinInKeys(final List<Integer> keys, final int friend, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET friend = (select case when friend+:friend<=:_min then :_min else friend+:friend end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("friend", friend);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFriendWithMaxByKey(final int id, final int friend, final int _max){
        return updateFriendWithMaxByKey(id, friend, _max, TABLENAME);
    }

    public int updateFriendWithMaxByKey(final int id, final int friend, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET friend = (select case when friend+:friend>=:_max then :_max else friend+:friend end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("friend", friend);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFriendWithMaxInKeys(final List<Integer> keys, final int friend, final int _max){
        return updateFriendWithMaxInKeys(keys, friend, _max, TABLENAME);
    }

    public int updateFriendWithMaxInKeys(final List<Integer> keys, final int friend, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET friend = (select case when friend+:friend>=:_max then :_max else friend+:friend end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("friend", friend);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFriendWithMinMaxByKey(final int id, final int friend, final int _min, final int _max){
        return updateFriendWithMinMaxByKey(id, friend, _min, _max, TABLENAME);
    }

    public int updateFriendWithMinMaxByKey(final int id, final int friend, final int _min, final int _max, final String TABLENAME2){
        if( friend < 0 ) {
            return updateFriendWithMinByKey(id, friend, _min, TABLENAME2);
        } else {
            return updateFriendWithMaxByKey(id, friend, _max, TABLENAME2);
        }
    }

    public int updateFriendWithMinMaxInKeys(final List<Integer> keys, final int friend, final int _min, final int _max){
        return updateFriendWithMinMaxInKeys(keys, friend, _min, _max, TABLENAME);
    }

    public int updateFriendWithMinMaxInKeys(final List<Integer> keys, final int friend, final int _min, final int _max, final String TABLENAME2){
        if( friend < 0 ) {
            return updateFriendWithMinInKeys(keys, friend, _min, TABLENAME2);
        } else {
            return updateFriendWithMaxInKeys(keys, friend, _max, TABLENAME2);
        }
    }

    public int updateKbiByKey(final int kbi, final int id){
        return updateKbiByKey(kbi, id, TABLENAME);
    }

    public int updateKbiByKey(final int kbi, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi=kbi+:kbi WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMinByKey(final int id, final int kbi, final int _min){
        return updateKbiWithMinByKey(id, kbi, _min, TABLENAME);
    }

    public int updateKbiWithMinByKey(final int id, final int kbi, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi<=:_min then :_min else kbi+:kbi end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMinInKeys(final List<Integer> keys, final int kbi, final int _min){
        return updateKbiWithMinInKeys(keys, kbi, _min, TABLENAME);
    }

    public int updateKbiWithMinInKeys(final List<Integer> keys, final int kbi, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi<=:_min then :_min else kbi+:kbi end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMaxByKey(final int id, final int kbi, final int _max){
        return updateKbiWithMaxByKey(id, kbi, _max, TABLENAME);
    }

    public int updateKbiWithMaxByKey(final int id, final int kbi, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi>=:_max then :_max else kbi+:kbi end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMaxInKeys(final List<Integer> keys, final int kbi, final int _max){
        return updateKbiWithMaxInKeys(keys, kbi, _max, TABLENAME);
    }

    public int updateKbiWithMaxInKeys(final List<Integer> keys, final int kbi, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi>=:_max then :_max else kbi+:kbi end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMinMaxByKey(final int id, final int kbi, final int _min, final int _max){
        return updateKbiWithMinMaxByKey(id, kbi, _min, _max, TABLENAME);
    }

    public int updateKbiWithMinMaxByKey(final int id, final int kbi, final int _min, final int _max, final String TABLENAME2){
        if( kbi < 0 ) {
            return updateKbiWithMinByKey(id, kbi, _min, TABLENAME2);
        } else {
            return updateKbiWithMaxByKey(id, kbi, _max, TABLENAME2);
        }
    }

    public int updateKbiWithMinMaxInKeys(final List<Integer> keys, final int kbi, final int _min, final int _max){
        return updateKbiWithMinMaxInKeys(keys, kbi, _min, _max, TABLENAME);
    }

    public int updateKbiWithMinMaxInKeys(final List<Integer> keys, final int kbi, final int _min, final int _max, final String TABLENAME2){
        if( kbi < 0 ) {
            return updateKbiWithMinInKeys(keys, kbi, _min, TABLENAME2);
        } else {
            return updateKbiWithMaxInKeys(keys, kbi, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Product0examtype> product0examtypes) {
        return updateByKey(product0examtypes, TABLENAME);
    }

    public int[] updateByKey (final List<Product0examtype> product0examtypes, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(product0examtypes == null || product0examtypes.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid=:lhubid, productid=:productid, examtypeid=:examtypeid, buymoney=:buymoney, friend=:friend, kbi=:kbi, status=:status, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), product0examtypes);
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
                "	`lhubid`  INT(11) NOT NULL," +
                "	`productid`  INT(11) NOT NULL," +
                "	`examtypeid`  INT(11) NOT NULL," +
                "	`buymoney`  DOUBLE NOT NULL," +
                "	`friend`  INT(11) NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `lhubid_prid_etypeid` (`lhubid`, `productid`, `examtypeid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `productid` (`productid`)," +
                "	KEY `examtypeid` (`examtypeid`)" +
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
                "	`lhubid`  INT(11) NOT NULL," +
                "	`productid`  INT(11) NOT NULL," +
                "	`examtypeid`  INT(11) NOT NULL," +
                "	`buymoney`  DOUBLE NOT NULL," +
                "	`friend`  INT(11) NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `lhubid_prid_etypeid` (`lhubid`, `productid`, `examtypeid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `productid` (`productid`)," +
                "	KEY `examtypeid` (`examtypeid`)" +
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
