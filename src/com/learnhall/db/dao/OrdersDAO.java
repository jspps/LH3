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

//learnhall3_design - orders
@SuppressWarnings({"rawtypes", "unchecked"})
public class OrdersDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(OrdersDAO.class);

    public static final String TABLE = "orders";
    public static String TABLENAME = "orders";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "type", "name", "extra_param", "makerid", "price", "kbi", "createtime", "status", "statusProcess", "nmMaker", "discount", "recommendCode", "lasttime", "orderNo", "realprice"};
    public static String coulmns = "id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice";
    public static String coulmns2 = "type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice";

    public OrdersDAO(Connection conn) {
        super(conn);
    }

    public OrdersDAO(DataSource ds) {
        super(ds);
    }

    public OrdersDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Orders orders) {
        return insert(orders, TABLENAME);
    }

    public int insert(final Orders orders, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            orders.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice) VALUES (:type, :name, :extra_param, :makerid, :price, :kbi, :createtime, :status, :statusProcess, :nmMaker, :discount, :recommendCode, :lasttime, :orderNo, :realprice)");
            Map map = super.insert(sql.toString(), orders);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Orders orders) {
        return asyncInsert(orders, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Orders orders, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(orders, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Orders orders) {
        return asyncInsert2(orders, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Orders orders, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(orders, TABLENAME2);
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

    public int insert2(final Orders orders) {
        return insert2(orders, TABLENAME);
    }

    public int insert2(final Orders orders, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            orders.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice) VALUES (:id, :type, :name, :extra_param, :makerid, :price, :kbi, :createtime, :status, :statusProcess, :nmMaker, :discount, :recommendCode, :lasttime, :orderNo, :realprice)");
            Map map = super.insert(sql.toString(), orders);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Orders> orderss) {
        return insert(orderss, TABLENAME);
    }

    public int[] insert(final List<Orders> orderss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(orderss == null || orderss.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice) VALUES (:type, :name, :extra_param, :makerid, :price, :kbi, :createtime, :status, :statusProcess, :nmMaker, :discount, :recommendCode, :lasttime, :orderNo, :realprice)");
            return super.batchInsert(sql.toString(), orderss);
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

    public int deleteInBeans(final List<Orders> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Orders> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Orders orders = beans.get(i);
                int id = orders.id;
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

    public List<Orders> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Orders> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Orders.class);
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
            sql.append("SELECT id, type, extra_param, makerid, orderNo FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Orders> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Orders> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Orders.class);
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

    public List<Orders> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Orders> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Orders.class);
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

    public Orders last() {
        return last(TABLENAME);
    }

    public Orders last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Orders.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Orders> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Orders> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Orders.class);
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

    public Orders selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Orders selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Orders.class);
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

    public Orders selectByOrderNo(final String orderNo) {
        return selectByOrderNo(orderNo, TABLENAME);
    }

    public Orders selectByOrderNo(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE orderNo = :orderNo");
            Map params = newMap();
            params.put("orderNo", orderNo);
            return super.queryForObject(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeOrderNo(final String orderNo) {
        return countLikeOrderNo(orderNo, TABLENAME);
    }

    public int countLikeOrderNo(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE orderNo LIKE '%").append(orderNo).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectLikeOrderNo(final String orderNo) {
        return selectLikeOrderNo(orderNo, TABLENAME);
    }

    public List<Orders> selectLikeOrderNo(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE orderNo LIKE '%").append(orderNo).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeOrderNoPKs(final String orderNo) {
        return selectLikeOrderNoPKs(orderNo, TABLENAME);
    }

    public List<Integer> selectLikeOrderNoPKs(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE orderNo LIKE '%").append(orderNo).append("%' ORDER BY id ");
            Map params = newMap();
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

    public Orders selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Orders selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByExtra_param(final String extra_param) {
        return countByExtra_param(extra_param, TABLENAME);
    }

    public int countByExtra_param(final String extra_param, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE extra_param = :extra_param ");
            Map params = newMap();
            params.put("extra_param", extra_param);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectByExtra_param(final String extra_param) {
        return selectByExtra_param(extra_param, TABLENAME);
    }

    public List<Orders> selectByExtra_param(final String extra_param, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE extra_param = :extra_param ORDER BY id ");
            Map params = newMap();
            params.put("extra_param", extra_param);
            return super.queryForList(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExtra_paramPKs(final String extra_param) {
        return selectByExtra_paramPKs(extra_param, TABLENAME);
    }

    public List<Integer> selectByExtra_paramPKs(final String extra_param, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE extra_param = :extra_param ORDER BY id ");
            Map params = newMap();
            params.put("extra_param", extra_param);
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

    public List<Orders> selectPageByExtra_param(final String extra_param, final int begin, final int num) {
        return selectPageByExtra_param(extra_param, begin, num, TABLENAME);
    }

    public List<Orders> selectPageByExtra_param(final String extra_param, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE extra_param = :extra_param ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("extra_param", extra_param);
            return super.queryForList(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExtra_paramPKs(final String extra_param, final int begin, final int num) {
        return selectPageByExtra_paramPKs(extra_param, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExtra_paramPKs(final String extra_param, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE extra_param = :extra_param ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("extra_param", extra_param);
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

    public int countLikeExtra_param(final String extra_param) {
        return countLikeExtra_param(extra_param, TABLENAME);
    }

    public int countLikeExtra_param(final String extra_param, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE extra_param LIKE '%").append(extra_param).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectLikeExtra_param(final String extra_param) {
        return selectLikeExtra_param(extra_param, TABLENAME);
    }

    public List<Orders> selectLikeExtra_param(final String extra_param, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE extra_param LIKE '%").append(extra_param).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeExtra_paramPKs(final String extra_param) {
        return selectLikeExtra_paramPKs(extra_param, TABLENAME);
    }

    public List<Integer> selectLikeExtra_paramPKs(final String extra_param, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE extra_param LIKE '%").append(extra_param).append("%' ORDER BY id ");
            Map params = newMap();
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

    public int countByTypeMakerid(final Integer type, Integer makerid) {
        return  countByTypeMakerid(type, makerid, TABLENAME);
    }

    public int countByTypeMakerid(final Integer type, Integer makerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders> selectByTypeMakerid(final Integer type, Integer makerid) {
        return selectByTypeMakerid(type, makerid, TABLENAME);
    }

    public List<Orders> selectByTypeMakerid(final Integer type, Integer makerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
            return super.queryForList(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeMakeridPKs(final Integer type, Integer makerid) {
        return selectByTypeMakeridPKs(type, makerid, TABLENAME);
    }

    public List<Integer> selectByTypeMakeridPKs(final Integer type, Integer makerid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
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

    public List<Orders> selectPageByTypeMakerid(final Integer type, Integer makerid, final int begin, final int num) {
        return selectPageByTypeMakerid(type, makerid, begin, num, TABLENAME);
    }

    public List<Orders> selectPageByTypeMakerid(final Integer type, Integer makerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
            return super.queryForList(sql.toString(), params, Orders.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeMakeridPKs(final Integer type, Integer makerid, final int begin, final int num) {
        return selectPageByTypeMakeridPKs(type, makerid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeMakeridPKs(final Integer type, Integer makerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
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

    public List<Orders> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Orders> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Orders.class);
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

    public int updateByKey(final Orders orders) {
        return updateByKey(orders, TABLENAME);
    }

    public int updateByKey(final Orders orders, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = orders.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), orders);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Orders orders) {
        return asyncUpdate(orders, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Orders orders, final String TABLENAME2) {
        try {

            String _ustr = orders.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, orders);
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

    public int updateTypeByKey(final int type, final int id){
        return updateTypeByKey(type, id, TABLENAME);
    }

    public int updateTypeByKey(final int type, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=type+:type WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinByKey(final int id, final int type, final int _min){
        return updateTypeWithMinByKey(id, type, _min, TABLENAME);
    }

    public int updateTypeWithMinByKey(final int id, final int type, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinInKeys(final List<Integer> keys, final int type, final int _min){
        return updateTypeWithMinInKeys(keys, type, _min, TABLENAME);
    }

    public int updateTypeWithMinInKeys(final List<Integer> keys, final int type, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMaxByKey(final int id, final int type, final int _max){
        return updateTypeWithMaxByKey(id, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxByKey(final int id, final int type, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMaxInKeys(final List<Integer> keys, final int type, final int _max){
        return updateTypeWithMaxInKeys(keys, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxInKeys(final List<Integer> keys, final int type, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinMaxByKey(final int id, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxByKey(id, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxByKey(final int id, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinByKey(id, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxByKey(id, type, _max, TABLENAME2);
        }
    }

    public int updateTypeWithMinMaxInKeys(final List<Integer> keys, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxInKeys(keys, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxInKeys(final List<Integer> keys, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinInKeys(keys, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxInKeys(keys, type, _max, TABLENAME2);
        }
    }

    public int updateMakeridByKey(final int makerid, final int id){
        return updateMakeridByKey(makerid, id, TABLENAME);
    }

    public int updateMakeridByKey(final int makerid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET makerid=makerid+:makerid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("makerid", makerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMakeridWithMinByKey(final int id, final int makerid, final int _min){
        return updateMakeridWithMinByKey(id, makerid, _min, TABLENAME);
    }

    public int updateMakeridWithMinByKey(final int id, final int makerid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET makerid = (select case when makerid+:makerid<=:_min then :_min else makerid+:makerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("makerid", makerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMakeridWithMinInKeys(final List<Integer> keys, final int makerid, final int _min){
        return updateMakeridWithMinInKeys(keys, makerid, _min, TABLENAME);
    }

    public int updateMakeridWithMinInKeys(final List<Integer> keys, final int makerid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET makerid = (select case when makerid+:makerid<=:_min then :_min else makerid+:makerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("makerid", makerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMakeridWithMaxByKey(final int id, final int makerid, final int _max){
        return updateMakeridWithMaxByKey(id, makerid, _max, TABLENAME);
    }

    public int updateMakeridWithMaxByKey(final int id, final int makerid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET makerid = (select case when makerid+:makerid>=:_max then :_max else makerid+:makerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("makerid", makerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMakeridWithMaxInKeys(final List<Integer> keys, final int makerid, final int _max){
        return updateMakeridWithMaxInKeys(keys, makerid, _max, TABLENAME);
    }

    public int updateMakeridWithMaxInKeys(final List<Integer> keys, final int makerid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET makerid = (select case when makerid+:makerid>=:_max then :_max else makerid+:makerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("makerid", makerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMakeridWithMinMaxByKey(final int id, final int makerid, final int _min, final int _max){
        return updateMakeridWithMinMaxByKey(id, makerid, _min, _max, TABLENAME);
    }

    public int updateMakeridWithMinMaxByKey(final int id, final int makerid, final int _min, final int _max, final String TABLENAME2){
        if( makerid < 0 ) {
            return updateMakeridWithMinByKey(id, makerid, _min, TABLENAME2);
        } else {
            return updateMakeridWithMaxByKey(id, makerid, _max, TABLENAME2);
        }
    }

    public int updateMakeridWithMinMaxInKeys(final List<Integer> keys, final int makerid, final int _min, final int _max){
        return updateMakeridWithMinMaxInKeys(keys, makerid, _min, _max, TABLENAME);
    }

    public int updateMakeridWithMinMaxInKeys(final List<Integer> keys, final int makerid, final int _min, final int _max, final String TABLENAME2){
        if( makerid < 0 ) {
            return updateMakeridWithMinInKeys(keys, makerid, _min, TABLENAME2);
        } else {
            return updateMakeridWithMaxInKeys(keys, makerid, _max, TABLENAME2);
        }
    }

    public int updatePriceByKey(final double price, final int id){
        return updatePriceByKey(price, id, TABLENAME);
    }

    public int updatePriceByKey(final double price, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price=price+:price WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMinByKey(final int id, final double price, final double _min){
        return updatePriceWithMinByKey(id, price, _min, TABLENAME);
    }

    public int updatePriceWithMinByKey(final int id, final double price, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price<=:_min then :_min else price+:price end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMinInKeys(final List<Integer> keys, final double price, final double _min){
        return updatePriceWithMinInKeys(keys, price, _min, TABLENAME);
    }

    public int updatePriceWithMinInKeys(final List<Integer> keys, final double price, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price<=:_min then :_min else price+:price end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMaxByKey(final int id, final double price, final double _max){
        return updatePriceWithMaxByKey(id, price, _max, TABLENAME);
    }

    public int updatePriceWithMaxByKey(final int id, final double price, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price>=:_max then :_max else price+:price end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMaxInKeys(final List<Integer> keys, final double price, final double _max){
        return updatePriceWithMaxInKeys(keys, price, _max, TABLENAME);
    }

    public int updatePriceWithMaxInKeys(final List<Integer> keys, final double price, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price>=:_max then :_max else price+:price end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMinMaxByKey(final int id, final double price, final double _min, final double _max){
        return updatePriceWithMinMaxByKey(id, price, _min, _max, TABLENAME);
    }

    public int updatePriceWithMinMaxByKey(final int id, final double price, final double _min, final double _max, final String TABLENAME2){
        if( price < 0 ) {
            return updatePriceWithMinByKey(id, price, _min, TABLENAME2);
        } else {
            return updatePriceWithMaxByKey(id, price, _max, TABLENAME2);
        }
    }

    public int updatePriceWithMinMaxInKeys(final List<Integer> keys, final double price, final double _min, final double _max){
        return updatePriceWithMinMaxInKeys(keys, price, _min, _max, TABLENAME);
    }

    public int updatePriceWithMinMaxInKeys(final List<Integer> keys, final double price, final double _min, final double _max, final String TABLENAME2){
        if( price < 0 ) {
            return updatePriceWithMinInKeys(keys, price, _min, TABLENAME2);
        } else {
            return updatePriceWithMaxInKeys(keys, price, _max, TABLENAME2);
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

    public int updateStatusProcessByKey(final int statusProcess, final int id){
        return updateStatusProcessByKey(statusProcess, id, TABLENAME);
    }

    public int updateStatusProcessByKey(final int statusProcess, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusProcess=statusProcess+:statusProcess WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("statusProcess", statusProcess);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusProcessWithMinByKey(final int id, final int statusProcess, final int _min){
        return updateStatusProcessWithMinByKey(id, statusProcess, _min, TABLENAME);
    }

    public int updateStatusProcessWithMinByKey(final int id, final int statusProcess, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusProcess = (select case when statusProcess+:statusProcess<=:_min then :_min else statusProcess+:statusProcess end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("statusProcess", statusProcess);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusProcessWithMinInKeys(final List<Integer> keys, final int statusProcess, final int _min){
        return updateStatusProcessWithMinInKeys(keys, statusProcess, _min, TABLENAME);
    }

    public int updateStatusProcessWithMinInKeys(final List<Integer> keys, final int statusProcess, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusProcess = (select case when statusProcess+:statusProcess<=:_min then :_min else statusProcess+:statusProcess end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("statusProcess", statusProcess);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusProcessWithMaxByKey(final int id, final int statusProcess, final int _max){
        return updateStatusProcessWithMaxByKey(id, statusProcess, _max, TABLENAME);
    }

    public int updateStatusProcessWithMaxByKey(final int id, final int statusProcess, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusProcess = (select case when statusProcess+:statusProcess>=:_max then :_max else statusProcess+:statusProcess end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("statusProcess", statusProcess);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusProcessWithMaxInKeys(final List<Integer> keys, final int statusProcess, final int _max){
        return updateStatusProcessWithMaxInKeys(keys, statusProcess, _max, TABLENAME);
    }

    public int updateStatusProcessWithMaxInKeys(final List<Integer> keys, final int statusProcess, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusProcess = (select case when statusProcess+:statusProcess>=:_max then :_max else statusProcess+:statusProcess end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("statusProcess", statusProcess);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusProcessWithMinMaxByKey(final int id, final int statusProcess, final int _min, final int _max){
        return updateStatusProcessWithMinMaxByKey(id, statusProcess, _min, _max, TABLENAME);
    }

    public int updateStatusProcessWithMinMaxByKey(final int id, final int statusProcess, final int _min, final int _max, final String TABLENAME2){
        if( statusProcess < 0 ) {
            return updateStatusProcessWithMinByKey(id, statusProcess, _min, TABLENAME2);
        } else {
            return updateStatusProcessWithMaxByKey(id, statusProcess, _max, TABLENAME2);
        }
    }

    public int updateStatusProcessWithMinMaxInKeys(final List<Integer> keys, final int statusProcess, final int _min, final int _max){
        return updateStatusProcessWithMinMaxInKeys(keys, statusProcess, _min, _max, TABLENAME);
    }

    public int updateStatusProcessWithMinMaxInKeys(final List<Integer> keys, final int statusProcess, final int _min, final int _max, final String TABLENAME2){
        if( statusProcess < 0 ) {
            return updateStatusProcessWithMinInKeys(keys, statusProcess, _min, TABLENAME2);
        } else {
            return updateStatusProcessWithMaxInKeys(keys, statusProcess, _max, TABLENAME2);
        }
    }

    public int updateDiscountByKey(final double discount, final int id){
        return updateDiscountByKey(discount, id, TABLENAME);
    }

    public int updateDiscountByKey(final double discount, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET discount=discount+:discount WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("discount", discount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDiscountWithMinByKey(final int id, final double discount, final double _min){
        return updateDiscountWithMinByKey(id, discount, _min, TABLENAME);
    }

    public int updateDiscountWithMinByKey(final int id, final double discount, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET discount = (select case when discount+:discount<=:_min then :_min else discount+:discount end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("discount", discount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDiscountWithMinInKeys(final List<Integer> keys, final double discount, final double _min){
        return updateDiscountWithMinInKeys(keys, discount, _min, TABLENAME);
    }

    public int updateDiscountWithMinInKeys(final List<Integer> keys, final double discount, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET discount = (select case when discount+:discount<=:_min then :_min else discount+:discount end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("discount", discount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDiscountWithMaxByKey(final int id, final double discount, final double _max){
        return updateDiscountWithMaxByKey(id, discount, _max, TABLENAME);
    }

    public int updateDiscountWithMaxByKey(final int id, final double discount, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET discount = (select case when discount+:discount>=:_max then :_max else discount+:discount end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("discount", discount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDiscountWithMaxInKeys(final List<Integer> keys, final double discount, final double _max){
        return updateDiscountWithMaxInKeys(keys, discount, _max, TABLENAME);
    }

    public int updateDiscountWithMaxInKeys(final List<Integer> keys, final double discount, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET discount = (select case when discount+:discount>=:_max then :_max else discount+:discount end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("discount", discount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDiscountWithMinMaxByKey(final int id, final double discount, final double _min, final double _max){
        return updateDiscountWithMinMaxByKey(id, discount, _min, _max, TABLENAME);
    }

    public int updateDiscountWithMinMaxByKey(final int id, final double discount, final double _min, final double _max, final String TABLENAME2){
        if( discount < 0 ) {
            return updateDiscountWithMinByKey(id, discount, _min, TABLENAME2);
        } else {
            return updateDiscountWithMaxByKey(id, discount, _max, TABLENAME2);
        }
    }

    public int updateDiscountWithMinMaxInKeys(final List<Integer> keys, final double discount, final double _min, final double _max){
        return updateDiscountWithMinMaxInKeys(keys, discount, _min, _max, TABLENAME);
    }

    public int updateDiscountWithMinMaxInKeys(final List<Integer> keys, final double discount, final double _min, final double _max, final String TABLENAME2){
        if( discount < 0 ) {
            return updateDiscountWithMinInKeys(keys, discount, _min, TABLENAME2);
        } else {
            return updateDiscountWithMaxInKeys(keys, discount, _max, TABLENAME2);
        }
    }

    public int updateRealpriceByKey(final double realprice, final int id){
        return updateRealpriceByKey(realprice, id, TABLENAME);
    }

    public int updateRealpriceByKey(final double realprice, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET realprice=realprice+:realprice WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("realprice", realprice);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRealpriceWithMinByKey(final int id, final double realprice, final double _min){
        return updateRealpriceWithMinByKey(id, realprice, _min, TABLENAME);
    }

    public int updateRealpriceWithMinByKey(final int id, final double realprice, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET realprice = (select case when realprice+:realprice<=:_min then :_min else realprice+:realprice end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("realprice", realprice);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRealpriceWithMinInKeys(final List<Integer> keys, final double realprice, final double _min){
        return updateRealpriceWithMinInKeys(keys, realprice, _min, TABLENAME);
    }

    public int updateRealpriceWithMinInKeys(final List<Integer> keys, final double realprice, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET realprice = (select case when realprice+:realprice<=:_min then :_min else realprice+:realprice end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("realprice", realprice);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRealpriceWithMaxByKey(final int id, final double realprice, final double _max){
        return updateRealpriceWithMaxByKey(id, realprice, _max, TABLENAME);
    }

    public int updateRealpriceWithMaxByKey(final int id, final double realprice, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET realprice = (select case when realprice+:realprice>=:_max then :_max else realprice+:realprice end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("realprice", realprice);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRealpriceWithMaxInKeys(final List<Integer> keys, final double realprice, final double _max){
        return updateRealpriceWithMaxInKeys(keys, realprice, _max, TABLENAME);
    }

    public int updateRealpriceWithMaxInKeys(final List<Integer> keys, final double realprice, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET realprice = (select case when realprice+:realprice>=:_max then :_max else realprice+:realprice end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("realprice", realprice);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRealpriceWithMinMaxByKey(final int id, final double realprice, final double _min, final double _max){
        return updateRealpriceWithMinMaxByKey(id, realprice, _min, _max, TABLENAME);
    }

    public int updateRealpriceWithMinMaxByKey(final int id, final double realprice, final double _min, final double _max, final String TABLENAME2){
        if( realprice < 0 ) {
            return updateRealpriceWithMinByKey(id, realprice, _min, TABLENAME2);
        } else {
            return updateRealpriceWithMaxByKey(id, realprice, _max, TABLENAME2);
        }
    }

    public int updateRealpriceWithMinMaxInKeys(final List<Integer> keys, final double realprice, final double _min, final double _max){
        return updateRealpriceWithMinMaxInKeys(keys, realprice, _min, _max, TABLENAME);
    }

    public int updateRealpriceWithMinMaxInKeys(final List<Integer> keys, final double realprice, final double _min, final double _max, final String TABLENAME2){
        if( realprice < 0 ) {
            return updateRealpriceWithMinInKeys(keys, realprice, _min, TABLENAME2);
        } else {
            return updateRealpriceWithMaxInKeys(keys, realprice, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Orders> orderss) {
        return updateByKey(orderss, TABLENAME);
    }

    public int[] updateByKey (final List<Orders> orderss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(orderss == null || orderss.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=:type, name=:name, extra_param=:extra_param, makerid=:makerid, price=:price, kbi=:kbi, createtime=:createtime, status=:status, statusProcess=:statusProcess, nmMaker=:nmMaker, discount=:discount, recommendCode=:recommendCode, lasttime=:lasttime, orderNo=:orderNo, realprice=:realprice WHERE id=:id");
            return super.batchUpdate2(sql.toString(), orderss);
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
                "	`type`  INT(11) NOT NULL," +
                "	`name`  TINYTEXT NOT NULL," +
                "	`extra_param`  TINYTEXT NOT NULL," +
                "	`makerid`  INT(11) NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`statusProcess`  INT(11) NOT NULL," +
                "	`nmMaker`  TINYTEXT NOT NULL," +
                "	`discount`  DOUBLE NOT NULL," +
                "	`recommendCode`  VARCHAR(64) NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	`orderNo`  VARCHAR(64) NOT NULL," +
                "	`realprice`  DOUBLE NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `orderNo` (`orderNo`)," +
                "	KEY `kindid` (`extra_param`)," +
                "	KEY `type_makeid` (`type`, `makerid`)" +
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
                "	`type`  INT(11) NOT NULL," +
                "	`name`  TINYTEXT NOT NULL," +
                "	`extra_param`  TINYTEXT NOT NULL," +
                "	`makerid`  INT(11) NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`statusProcess`  INT(11) NOT NULL," +
                "	`nmMaker`  TINYTEXT NOT NULL," +
                "	`discount`  DOUBLE NOT NULL," +
                "	`recommendCode`  VARCHAR(64) NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	`orderNo`  VARCHAR(64) NOT NULL," +
                "	`realprice`  DOUBLE NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `orderNo` (`orderNo`)," +
                "	KEY `kindid` (`extra_param`)," +
                "	KEY `type_makeid` (`type`, `makerid`)" +
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
