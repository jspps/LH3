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

//learnhall3_design - product
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProductDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ProductDAO.class);

    public static final String TABLE = "product";
    public static String TABLENAME = "product";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "coursesid", "name", "imgurl", "descr", "lhubid", "status", "examineStatus", "examineDes", "complete", "createtime", "cruces", "isRecommend", "lastTime4Recommend", "protection", "lasttime"};
    public static String coulmns = "id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime";
    public static String coulmns2 = "coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime";

    public ProductDAO(Connection conn) {
        super(conn);
    }

    public ProductDAO(DataSource ds) {
        super(ds);
    }

    public ProductDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Product product) {
        return insert(product, TABLENAME);
    }

    public int insert(final Product product, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            product.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime) VALUES (:coursesid, :name, :imgurl, :descr, :lhubid, :status, :examineStatus, :examineDes, :complete, :createtime, :cruces, :isRecommend, :lastTime4Recommend, :protection, :lasttime)");
            Map map = super.insert(sql.toString(), product);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Product product) {
        return asyncInsert(product, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Product product, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(product, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Product product) {
        return asyncInsert2(product, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Product product, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(product, TABLENAME2);
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

    public int insert2(final Product product) {
        return insert2(product, TABLENAME);
    }

    public int insert2(final Product product, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            product.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime) VALUES (:id, :coursesid, :name, :imgurl, :descr, :lhubid, :status, :examineStatus, :examineDes, :complete, :createtime, :cruces, :isRecommend, :lastTime4Recommend, :protection, :lasttime)");
            Map map = super.insert(sql.toString(), product);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Product> products) {
        return insert(products, TABLENAME);
    }

    public int[] insert(final List<Product> products, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(products == null || products.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime) VALUES (:coursesid, :name, :imgurl, :descr, :lhubid, :status, :examineStatus, :examineDes, :complete, :createtime, :cruces, :isRecommend, :lastTime4Recommend, :protection, :lasttime)");
            return super.batchInsert(sql.toString(), products);
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

    public int deleteInBeans(final List<Product> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Product> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Product product = beans.get(i);
                int id = product.id;
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

    public List<Product> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Product> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Product.class);
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
            sql.append("SELECT id, coursesid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Product> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Product.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Product> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Product.class);
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

    public List<Product> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Product> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Product.class);
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

    public Product last() {
        return last(TABLENAME);
    }

    public Product last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Product.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Product> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Product.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Product> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Product.class);
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

    public Product selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Product selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Product.class);
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

    public List<Product> selectByLhubid(final Integer lhubid) {
        return selectByLhubid(lhubid, TABLENAME);
    }

    public List<Product> selectByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Product.class);
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

    public List<Product> selectPageByLhubid(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubid(lhubid, begin, num, TABLENAME);
    }

    public List<Product> selectPageByLhubid(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Product.class);
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

    public Product selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Product selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Product.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByCoursesid(final Integer coursesid) {
        return countByCoursesid(coursesid, TABLENAME);
    }

    public int countByCoursesid(final Integer coursesid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE coursesid = :coursesid ");
            Map params = newMap();
            params.put("coursesid", coursesid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Product> selectByCoursesid(final Integer coursesid) {
        return selectByCoursesid(coursesid, TABLENAME);
    }

    public List<Product> selectByCoursesid(final Integer coursesid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE coursesid = :coursesid ORDER BY id ");
            Map params = newMap();
            params.put("coursesid", coursesid);
            return super.queryForList(sql.toString(), params, Product.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCoursesidPKs(final Integer coursesid) {
        return selectByCoursesidPKs(coursesid, TABLENAME);
    }

    public List<Integer> selectByCoursesidPKs(final Integer coursesid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE coursesid = :coursesid ORDER BY id ");
            Map params = newMap();
            params.put("coursesid", coursesid);
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

    public List<Product> selectPageByCoursesid(final Integer coursesid, final int begin, final int num) {
        return selectPageByCoursesid(coursesid, begin, num, TABLENAME);
    }

    public List<Product> selectPageByCoursesid(final Integer coursesid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" WHERE coursesid = :coursesid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("coursesid", coursesid);
            return super.queryForList(sql.toString(), params, Product.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCoursesidPKs(final Integer coursesid, final int begin, final int num) {
        return selectPageByCoursesidPKs(coursesid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCoursesidPKs(final Integer coursesid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE coursesid = :coursesid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("coursesid", coursesid);
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

    public List<Product> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Product> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Product.class);
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

    public int updateByKey(final Product product) {
        return updateByKey(product, TABLENAME);
    }

    public int updateByKey(final Product product, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = product.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), product);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Product product) {
        return asyncUpdate(product, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Product product, final String TABLENAME2) {
        try {

            String _ustr = product.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, product);
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

    public int updateCoursesidByKey(final int coursesid, final int id){
        return updateCoursesidByKey(coursesid, id, TABLENAME);
    }

    public int updateCoursesidByKey(final int coursesid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursesid=coursesid+:coursesid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("coursesid", coursesid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursesidWithMinByKey(final int id, final int coursesid, final int _min){
        return updateCoursesidWithMinByKey(id, coursesid, _min, TABLENAME);
    }

    public int updateCoursesidWithMinByKey(final int id, final int coursesid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursesid = (select case when coursesid+:coursesid<=:_min then :_min else coursesid+:coursesid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("coursesid", coursesid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursesidWithMinInKeys(final List<Integer> keys, final int coursesid, final int _min){
        return updateCoursesidWithMinInKeys(keys, coursesid, _min, TABLENAME);
    }

    public int updateCoursesidWithMinInKeys(final List<Integer> keys, final int coursesid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursesid = (select case when coursesid+:coursesid<=:_min then :_min else coursesid+:coursesid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("coursesid", coursesid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursesidWithMaxByKey(final int id, final int coursesid, final int _max){
        return updateCoursesidWithMaxByKey(id, coursesid, _max, TABLENAME);
    }

    public int updateCoursesidWithMaxByKey(final int id, final int coursesid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursesid = (select case when coursesid+:coursesid>=:_max then :_max else coursesid+:coursesid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("coursesid", coursesid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursesidWithMaxInKeys(final List<Integer> keys, final int coursesid, final int _max){
        return updateCoursesidWithMaxInKeys(keys, coursesid, _max, TABLENAME);
    }

    public int updateCoursesidWithMaxInKeys(final List<Integer> keys, final int coursesid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursesid = (select case when coursesid+:coursesid>=:_max then :_max else coursesid+:coursesid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("coursesid", coursesid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursesidWithMinMaxByKey(final int id, final int coursesid, final int _min, final int _max){
        return updateCoursesidWithMinMaxByKey(id, coursesid, _min, _max, TABLENAME);
    }

    public int updateCoursesidWithMinMaxByKey(final int id, final int coursesid, final int _min, final int _max, final String TABLENAME2){
        if( coursesid < 0 ) {
            return updateCoursesidWithMinByKey(id, coursesid, _min, TABLENAME2);
        } else {
            return updateCoursesidWithMaxByKey(id, coursesid, _max, TABLENAME2);
        }
    }

    public int updateCoursesidWithMinMaxInKeys(final List<Integer> keys, final int coursesid, final int _min, final int _max){
        return updateCoursesidWithMinMaxInKeys(keys, coursesid, _min, _max, TABLENAME);
    }

    public int updateCoursesidWithMinMaxInKeys(final List<Integer> keys, final int coursesid, final int _min, final int _max, final String TABLENAME2){
        if( coursesid < 0 ) {
            return updateCoursesidWithMinInKeys(keys, coursesid, _min, TABLENAME2);
        } else {
            return updateCoursesidWithMaxInKeys(keys, coursesid, _max, TABLENAME2);
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

    public int updateExamineStatusByKey(final int examineStatus, final int id){
        return updateExamineStatusByKey(examineStatus, id, TABLENAME);
    }

    public int updateExamineStatusByKey(final int examineStatus, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus=examineStatus+:examineStatus WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMinByKey(final int id, final int examineStatus, final int _min){
        return updateExamineStatusWithMinByKey(id, examineStatus, _min, TABLENAME);
    }

    public int updateExamineStatusWithMinByKey(final int id, final int examineStatus, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus<=:_min then :_min else examineStatus+:examineStatus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMinInKeys(final List<Integer> keys, final int examineStatus, final int _min){
        return updateExamineStatusWithMinInKeys(keys, examineStatus, _min, TABLENAME);
    }

    public int updateExamineStatusWithMinInKeys(final List<Integer> keys, final int examineStatus, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus<=:_min then :_min else examineStatus+:examineStatus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMaxByKey(final int id, final int examineStatus, final int _max){
        return updateExamineStatusWithMaxByKey(id, examineStatus, _max, TABLENAME);
    }

    public int updateExamineStatusWithMaxByKey(final int id, final int examineStatus, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus>=:_max then :_max else examineStatus+:examineStatus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMaxInKeys(final List<Integer> keys, final int examineStatus, final int _max){
        return updateExamineStatusWithMaxInKeys(keys, examineStatus, _max, TABLENAME);
    }

    public int updateExamineStatusWithMaxInKeys(final List<Integer> keys, final int examineStatus, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus>=:_max then :_max else examineStatus+:examineStatus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMinMaxByKey(final int id, final int examineStatus, final int _min, final int _max){
        return updateExamineStatusWithMinMaxByKey(id, examineStatus, _min, _max, TABLENAME);
    }

    public int updateExamineStatusWithMinMaxByKey(final int id, final int examineStatus, final int _min, final int _max, final String TABLENAME2){
        if( examineStatus < 0 ) {
            return updateExamineStatusWithMinByKey(id, examineStatus, _min, TABLENAME2);
        } else {
            return updateExamineStatusWithMaxByKey(id, examineStatus, _max, TABLENAME2);
        }
    }

    public int updateExamineStatusWithMinMaxInKeys(final List<Integer> keys, final int examineStatus, final int _min, final int _max){
        return updateExamineStatusWithMinMaxInKeys(keys, examineStatus, _min, _max, TABLENAME);
    }

    public int updateExamineStatusWithMinMaxInKeys(final List<Integer> keys, final int examineStatus, final int _min, final int _max, final String TABLENAME2){
        if( examineStatus < 0 ) {
            return updateExamineStatusWithMinInKeys(keys, examineStatus, _min, TABLENAME2);
        } else {
            return updateExamineStatusWithMaxInKeys(keys, examineStatus, _max, TABLENAME2);
        }
    }

    public int updateCompleteByKey(final int complete, final int id){
        return updateCompleteByKey(complete, id, TABLENAME);
    }

    public int updateCompleteByKey(final int complete, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET complete=complete+:complete WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("complete", complete);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCompleteWithMinByKey(final int id, final int complete, final int _min){
        return updateCompleteWithMinByKey(id, complete, _min, TABLENAME);
    }

    public int updateCompleteWithMinByKey(final int id, final int complete, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET complete = (select case when complete+:complete<=:_min then :_min else complete+:complete end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("complete", complete);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCompleteWithMinInKeys(final List<Integer> keys, final int complete, final int _min){
        return updateCompleteWithMinInKeys(keys, complete, _min, TABLENAME);
    }

    public int updateCompleteWithMinInKeys(final List<Integer> keys, final int complete, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET complete = (select case when complete+:complete<=:_min then :_min else complete+:complete end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("complete", complete);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCompleteWithMaxByKey(final int id, final int complete, final int _max){
        return updateCompleteWithMaxByKey(id, complete, _max, TABLENAME);
    }

    public int updateCompleteWithMaxByKey(final int id, final int complete, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET complete = (select case when complete+:complete>=:_max then :_max else complete+:complete end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("complete", complete);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCompleteWithMaxInKeys(final List<Integer> keys, final int complete, final int _max){
        return updateCompleteWithMaxInKeys(keys, complete, _max, TABLENAME);
    }

    public int updateCompleteWithMaxInKeys(final List<Integer> keys, final int complete, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET complete = (select case when complete+:complete>=:_max then :_max else complete+:complete end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("complete", complete);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCompleteWithMinMaxByKey(final int id, final int complete, final int _min, final int _max){
        return updateCompleteWithMinMaxByKey(id, complete, _min, _max, TABLENAME);
    }

    public int updateCompleteWithMinMaxByKey(final int id, final int complete, final int _min, final int _max, final String TABLENAME2){
        if( complete < 0 ) {
            return updateCompleteWithMinByKey(id, complete, _min, TABLENAME2);
        } else {
            return updateCompleteWithMaxByKey(id, complete, _max, TABLENAME2);
        }
    }

    public int updateCompleteWithMinMaxInKeys(final List<Integer> keys, final int complete, final int _min, final int _max){
        return updateCompleteWithMinMaxInKeys(keys, complete, _min, _max, TABLENAME);
    }

    public int updateCompleteWithMinMaxInKeys(final List<Integer> keys, final int complete, final int _min, final int _max, final String TABLENAME2){
        if( complete < 0 ) {
            return updateCompleteWithMinInKeys(keys, complete, _min, TABLENAME2);
        } else {
            return updateCompleteWithMaxInKeys(keys, complete, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Product> products) {
        return updateByKey(products, TABLENAME);
    }

    public int[] updateByKey (final List<Product> products, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(products == null || products.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursesid=:coursesid, name=:name, imgurl=:imgurl, descr=:descr, lhubid=:lhubid, status=:status, examineStatus=:examineStatus, examineDes=:examineDes, complete=:complete, createtime=:createtime, cruces=:cruces, isRecommend=:isRecommend, lastTime4Recommend=:lastTime4Recommend, protection=:protection, lasttime=:lasttime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), products);
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
                "	`coursesid`  INT(11) NOT NULL," +
                "	`name`  VARCHAR(128) NOT NULL," +
                "	`imgurl`  VARCHAR(128) NOT NULL," +
                "	`descr`  TEXT NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`examineStatus`  INT(11) NOT NULL," +
                "	`examineDes`  TEXT NOT NULL," +
                "	`complete`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`cruces`  TEXT NOT NULL," +
                "	`isRecommend`  BIT(1) NOT NULL," +
                "	`lastTime4Recommend`  DATETIME NOT NULL," +
                "	`protection`  TEXT NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `coursesid` (`coursesid`)," +
                "	KEY `lhubid` (`lhubid`)" +
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
                "	`coursesid`  INT(11) NOT NULL," +
                "	`name`  VARCHAR(128) NOT NULL," +
                "	`imgurl`  VARCHAR(128) NOT NULL," +
                "	`descr`  TEXT NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`examineStatus`  INT(11) NOT NULL," +
                "	`examineDes`  TEXT NOT NULL," +
                "	`complete`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`cruces`  TEXT NOT NULL," +
                "	`isRecommend`  BIT(1) NOT NULL," +
                "	`lastTime4Recommend`  DATETIME NOT NULL," +
                "	`protection`  TEXT NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `coursesid` (`coursesid`)," +
                "	KEY `lhubid` (`lhubid`)" +
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
