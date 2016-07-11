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

//learnhall3_design - orders4profit
@SuppressWarnings({"rawtypes", "unchecked"})
public class Orders4profitDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Orders4profitDAO.class);

    public static final String TABLE = "orders4profit";
    public static String TABLENAME = "orders4profit";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "orderNo", "kindid", "custid", "lhubid", "agentid", "agentBonus", "agentRoyalty", "lhubRoyalty", "lhubDeposit", "developRoyalty", "artRoyalty", "price", "discount", "realprice", "isProfit4Agent", "isProfit4Lhub", "status", "createtime"};
    public static String coulmns = "id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime";
    public static String coulmns2 = "orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime";

    public Orders4profitDAO(Connection conn) {
        super(conn);
    }

    public Orders4profitDAO(DataSource ds) {
        super(ds);
    }

    public Orders4profitDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Orders4profit orders4profit) {
        return insert(orders4profit, TABLENAME);
    }

    public int insert(final Orders4profit orders4profit, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            orders4profit.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime) VALUES (:orderNo, :kindid, :custid, :lhubid, :agentid, :agentBonus, :agentRoyalty, :lhubRoyalty, :lhubDeposit, :developRoyalty, :artRoyalty, :price, :discount, :realprice, :isProfit4Agent, :isProfit4Lhub, :status, :createtime)");
            Map map = super.insert(sql.toString(), orders4profit);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Orders4profit orders4profit) {
        return asyncInsert(orders4profit, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Orders4profit orders4profit, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(orders4profit, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Orders4profit orders4profit) {
        return asyncInsert2(orders4profit, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Orders4profit orders4profit, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(orders4profit, TABLENAME2);
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

    public int insert2(final Orders4profit orders4profit) {
        return insert2(orders4profit, TABLENAME);
    }

    public int insert2(final Orders4profit orders4profit, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            orders4profit.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime) VALUES (:id, :orderNo, :kindid, :custid, :lhubid, :agentid, :agentBonus, :agentRoyalty, :lhubRoyalty, :lhubDeposit, :developRoyalty, :artRoyalty, :price, :discount, :realprice, :isProfit4Agent, :isProfit4Lhub, :status, :createtime)");
            Map map = super.insert(sql.toString(), orders4profit);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Orders4profit> orders4profits) {
        return insert(orders4profits, TABLENAME);
    }

    public int[] insert(final List<Orders4profit> orders4profits, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(orders4profits == null || orders4profits.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime) VALUES (:orderNo, :kindid, :custid, :lhubid, :agentid, :agentBonus, :agentRoyalty, :lhubRoyalty, :lhubDeposit, :developRoyalty, :artRoyalty, :price, :discount, :realprice, :isProfit4Agent, :isProfit4Lhub, :status, :createtime)");
            return super.batchInsert(sql.toString(), orders4profits);
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

    public int deleteInBeans(final List<Orders4profit> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Orders4profit> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Orders4profit orders4profit = beans.get(i);
                int id = orders4profit.id;
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

    public List<Orders4profit> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Orders4profit> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Orders4profit.class);
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
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Orders4profit> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Orders4profit> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
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

    public List<Orders4profit> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Orders4profit> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Orders4profit.class);
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

    public Orders4profit last() {
        return last(TABLENAME);
    }

    public Orders4profit last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Orders4profit.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Orders4profit> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Orders4profit> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
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

    public Orders4profit selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Orders4profit selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Orders4profit.class);
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

    public List<Orders4profit> selectByLhubid(final Integer lhubid) {
        return selectByLhubid(lhubid, TABLENAME);
    }

    public List<Orders4profit> selectByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
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

    public List<Orders4profit> selectPageByLhubid(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubid(lhubid, begin, num, TABLENAME);
    }

    public List<Orders4profit> selectPageByLhubid(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
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

    public int countByOrderNo(final String orderNo) {
        return countByOrderNo(orderNo, TABLENAME);
    }

    public int countByOrderNo(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE orderNo = :orderNo ");
            Map params = newMap();
            params.put("orderNo", orderNo);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectByOrderNo(final String orderNo) {
        return selectByOrderNo(orderNo, TABLENAME);
    }

    public List<Orders4profit> selectByOrderNo(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE orderNo = :orderNo ORDER BY id ");
            Map params = newMap();
            params.put("orderNo", orderNo);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByOrderNoPKs(final String orderNo) {
        return selectByOrderNoPKs(orderNo, TABLENAME);
    }

    public List<Integer> selectByOrderNoPKs(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE orderNo = :orderNo ORDER BY id ");
            Map params = newMap();
            params.put("orderNo", orderNo);
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

    public List<Orders4profit> selectPageByOrderNo(final String orderNo, final int begin, final int num) {
        return selectPageByOrderNo(orderNo, begin, num, TABLENAME);
    }

    public List<Orders4profit> selectPageByOrderNo(final String orderNo, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE orderNo = :orderNo ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("orderNo", orderNo);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByOrderNoPKs(final String orderNo, final int begin, final int num) {
        return selectPageByOrderNoPKs(orderNo, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByOrderNoPKs(final String orderNo, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE orderNo = :orderNo ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("orderNo", orderNo);
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

    public List<Orders4profit> selectLikeOrderNo(final String orderNo) {
        return selectLikeOrderNo(orderNo, TABLENAME);
    }

    public List<Orders4profit> selectLikeOrderNo(final String orderNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE orderNo LIKE '%").append(orderNo).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Orders4profit.class);
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

    public Orders4profit selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Orders4profit selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByKindid(final Integer kindid) {
        return countByKindid(kindid, TABLENAME);
    }

    public int countByKindid(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectByKindid(final Integer kindid) {
        return selectByKindid(kindid, TABLENAME);
    }

    public List<Orders4profit> selectByKindid(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id ");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByKindidPKs(final Integer kindid) {
        return selectByKindidPKs(kindid, TABLENAME);
    }

    public List<Integer> selectByKindidPKs(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id ");
            Map params = newMap();
            params.put("kindid", kindid);
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

    public List<Orders4profit> selectPageByKindid(final Integer kindid, final int begin, final int num) {
        return selectPageByKindid(kindid, begin, num, TABLENAME);
    }

    public List<Orders4profit> selectPageByKindid(final Integer kindid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByKindidPKs(final Integer kindid, final int begin, final int num) {
        return selectPageByKindidPKs(kindid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByKindidPKs(final Integer kindid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("kindid", kindid);
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

    public int countByCustid(final Integer custid) {
        return countByCustid(custid, TABLENAME);
    }

    public int countByCustid(final Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE custid = :custid ");
            Map params = newMap();
            params.put("custid", custid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectByCustid(final Integer custid) {
        return selectByCustid(custid, TABLENAME);
    }

    public List<Orders4profit> selectByCustid(final Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE custid = :custid ORDER BY id ");
            Map params = newMap();
            params.put("custid", custid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCustidPKs(final Integer custid) {
        return selectByCustidPKs(custid, TABLENAME);
    }

    public List<Integer> selectByCustidPKs(final Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE custid = :custid ORDER BY id ");
            Map params = newMap();
            params.put("custid", custid);
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

    public List<Orders4profit> selectPageByCustid(final Integer custid, final int begin, final int num) {
        return selectPageByCustid(custid, begin, num, TABLENAME);
    }

    public List<Orders4profit> selectPageByCustid(final Integer custid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE custid = :custid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("custid", custid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCustidPKs(final Integer custid, final int begin, final int num) {
        return selectPageByCustidPKs(custid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCustidPKs(final Integer custid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE custid = :custid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("custid", custid);
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

    public int countByAgentid(final Integer agentid) {
        return countByAgentid(agentid, TABLENAME);
    }

    public int countByAgentid(final Integer agentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE agentid = :agentid ");
            Map params = newMap();
            params.put("agentid", agentid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Orders4profit> selectByAgentid(final Integer agentid) {
        return selectByAgentid(agentid, TABLENAME);
    }

    public List<Orders4profit> selectByAgentid(final Integer agentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE agentid = :agentid ORDER BY id ");
            Map params = newMap();
            params.put("agentid", agentid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByAgentidPKs(final Integer agentid) {
        return selectByAgentidPKs(agentid, TABLENAME);
    }

    public List<Integer> selectByAgentidPKs(final Integer agentid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE agentid = :agentid ORDER BY id ");
            Map params = newMap();
            params.put("agentid", agentid);
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

    public List<Orders4profit> selectPageByAgentid(final Integer agentid, final int begin, final int num) {
        return selectPageByAgentid(agentid, begin, num, TABLENAME);
    }

    public List<Orders4profit> selectPageByAgentid(final Integer agentid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" WHERE agentid = :agentid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("agentid", agentid);
            return super.queryForList(sql.toString(), params, Orders4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByAgentidPKs(final Integer agentid, final int begin, final int num) {
        return selectPageByAgentidPKs(agentid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByAgentidPKs(final Integer agentid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE agentid = :agentid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("agentid", agentid);
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

    public List<Orders4profit> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Orders4profit> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Orders4profit.class);
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

    public int updateByKey(final Orders4profit orders4profit) {
        return updateByKey(orders4profit, TABLENAME);
    }

    public int updateByKey(final Orders4profit orders4profit, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = orders4profit.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), orders4profit);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Orders4profit orders4profit) {
        return asyncUpdate(orders4profit, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Orders4profit orders4profit, final String TABLENAME2) {
        try {

            String _ustr = orders4profit.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, orders4profit);
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

    public int updateAgentidByKey(final int agentid, final int id){
        return updateAgentidByKey(agentid, id, TABLENAME);
    }

    public int updateAgentidByKey(final int agentid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentid=agentid+:agentid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("agentid", agentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentidWithMinByKey(final int id, final int agentid, final int _min){
        return updateAgentidWithMinByKey(id, agentid, _min, TABLENAME);
    }

    public int updateAgentidWithMinByKey(final int id, final int agentid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentid = (select case when agentid+:agentid<=:_min then :_min else agentid+:agentid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("agentid", agentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentidWithMinInKeys(final List<Integer> keys, final int agentid, final int _min){
        return updateAgentidWithMinInKeys(keys, agentid, _min, TABLENAME);
    }

    public int updateAgentidWithMinInKeys(final List<Integer> keys, final int agentid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentid = (select case when agentid+:agentid<=:_min then :_min else agentid+:agentid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("agentid", agentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentidWithMaxByKey(final int id, final int agentid, final int _max){
        return updateAgentidWithMaxByKey(id, agentid, _max, TABLENAME);
    }

    public int updateAgentidWithMaxByKey(final int id, final int agentid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentid = (select case when agentid+:agentid>=:_max then :_max else agentid+:agentid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("agentid", agentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentidWithMaxInKeys(final List<Integer> keys, final int agentid, final int _max){
        return updateAgentidWithMaxInKeys(keys, agentid, _max, TABLENAME);
    }

    public int updateAgentidWithMaxInKeys(final List<Integer> keys, final int agentid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentid = (select case when agentid+:agentid>=:_max then :_max else agentid+:agentid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("agentid", agentid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentidWithMinMaxByKey(final int id, final int agentid, final int _min, final int _max){
        return updateAgentidWithMinMaxByKey(id, agentid, _min, _max, TABLENAME);
    }

    public int updateAgentidWithMinMaxByKey(final int id, final int agentid, final int _min, final int _max, final String TABLENAME2){
        if( agentid < 0 ) {
            return updateAgentidWithMinByKey(id, agentid, _min, TABLENAME2);
        } else {
            return updateAgentidWithMaxByKey(id, agentid, _max, TABLENAME2);
        }
    }

    public int updateAgentidWithMinMaxInKeys(final List<Integer> keys, final int agentid, final int _min, final int _max){
        return updateAgentidWithMinMaxInKeys(keys, agentid, _min, _max, TABLENAME);
    }

    public int updateAgentidWithMinMaxInKeys(final List<Integer> keys, final int agentid, final int _min, final int _max, final String TABLENAME2){
        if( agentid < 0 ) {
            return updateAgentidWithMinInKeys(keys, agentid, _min, TABLENAME2);
        } else {
            return updateAgentidWithMaxInKeys(keys, agentid, _max, TABLENAME2);
        }
    }

    public int updateAgentBonusByKey(final double agentBonus, final int id){
        return updateAgentBonusByKey(agentBonus, id, TABLENAME);
    }

    public int updateAgentBonusByKey(final double agentBonus, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentBonus=agentBonus+:agentBonus WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("agentBonus", agentBonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentBonusWithMinByKey(final int id, final double agentBonus, final double _min){
        return updateAgentBonusWithMinByKey(id, agentBonus, _min, TABLENAME);
    }

    public int updateAgentBonusWithMinByKey(final int id, final double agentBonus, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentBonus = (select case when agentBonus+:agentBonus<=:_min then :_min else agentBonus+:agentBonus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("agentBonus", agentBonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentBonusWithMinInKeys(final List<Integer> keys, final double agentBonus, final double _min){
        return updateAgentBonusWithMinInKeys(keys, agentBonus, _min, TABLENAME);
    }

    public int updateAgentBonusWithMinInKeys(final List<Integer> keys, final double agentBonus, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentBonus = (select case when agentBonus+:agentBonus<=:_min then :_min else agentBonus+:agentBonus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("agentBonus", agentBonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentBonusWithMaxByKey(final int id, final double agentBonus, final double _max){
        return updateAgentBonusWithMaxByKey(id, agentBonus, _max, TABLENAME);
    }

    public int updateAgentBonusWithMaxByKey(final int id, final double agentBonus, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentBonus = (select case when agentBonus+:agentBonus>=:_max then :_max else agentBonus+:agentBonus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("agentBonus", agentBonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentBonusWithMaxInKeys(final List<Integer> keys, final double agentBonus, final double _max){
        return updateAgentBonusWithMaxInKeys(keys, agentBonus, _max, TABLENAME);
    }

    public int updateAgentBonusWithMaxInKeys(final List<Integer> keys, final double agentBonus, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentBonus = (select case when agentBonus+:agentBonus>=:_max then :_max else agentBonus+:agentBonus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("agentBonus", agentBonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentBonusWithMinMaxByKey(final int id, final double agentBonus, final double _min, final double _max){
        return updateAgentBonusWithMinMaxByKey(id, agentBonus, _min, _max, TABLENAME);
    }

    public int updateAgentBonusWithMinMaxByKey(final int id, final double agentBonus, final double _min, final double _max, final String TABLENAME2){
        if( agentBonus < 0 ) {
            return updateAgentBonusWithMinByKey(id, agentBonus, _min, TABLENAME2);
        } else {
            return updateAgentBonusWithMaxByKey(id, agentBonus, _max, TABLENAME2);
        }
    }

    public int updateAgentBonusWithMinMaxInKeys(final List<Integer> keys, final double agentBonus, final double _min, final double _max){
        return updateAgentBonusWithMinMaxInKeys(keys, agentBonus, _min, _max, TABLENAME);
    }

    public int updateAgentBonusWithMinMaxInKeys(final List<Integer> keys, final double agentBonus, final double _min, final double _max, final String TABLENAME2){
        if( agentBonus < 0 ) {
            return updateAgentBonusWithMinInKeys(keys, agentBonus, _min, TABLENAME2);
        } else {
            return updateAgentBonusWithMaxInKeys(keys, agentBonus, _max, TABLENAME2);
        }
    }

    public int updateAgentRoyaltyByKey(final double agentRoyalty, final int id){
        return updateAgentRoyaltyByKey(agentRoyalty, id, TABLENAME);
    }

    public int updateAgentRoyaltyByKey(final double agentRoyalty, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentRoyalty=agentRoyalty+:agentRoyalty WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("agentRoyalty", agentRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentRoyaltyWithMinByKey(final int id, final double agentRoyalty, final double _min){
        return updateAgentRoyaltyWithMinByKey(id, agentRoyalty, _min, TABLENAME);
    }

    public int updateAgentRoyaltyWithMinByKey(final int id, final double agentRoyalty, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentRoyalty = (select case when agentRoyalty+:agentRoyalty<=:_min then :_min else agentRoyalty+:agentRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("agentRoyalty", agentRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentRoyaltyWithMinInKeys(final List<Integer> keys, final double agentRoyalty, final double _min){
        return updateAgentRoyaltyWithMinInKeys(keys, agentRoyalty, _min, TABLENAME);
    }

    public int updateAgentRoyaltyWithMinInKeys(final List<Integer> keys, final double agentRoyalty, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentRoyalty = (select case when agentRoyalty+:agentRoyalty<=:_min then :_min else agentRoyalty+:agentRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("agentRoyalty", agentRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentRoyaltyWithMaxByKey(final int id, final double agentRoyalty, final double _max){
        return updateAgentRoyaltyWithMaxByKey(id, agentRoyalty, _max, TABLENAME);
    }

    public int updateAgentRoyaltyWithMaxByKey(final int id, final double agentRoyalty, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentRoyalty = (select case when agentRoyalty+:agentRoyalty>=:_max then :_max else agentRoyalty+:agentRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("agentRoyalty", agentRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentRoyaltyWithMaxInKeys(final List<Integer> keys, final double agentRoyalty, final double _max){
        return updateAgentRoyaltyWithMaxInKeys(keys, agentRoyalty, _max, TABLENAME);
    }

    public int updateAgentRoyaltyWithMaxInKeys(final List<Integer> keys, final double agentRoyalty, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET agentRoyalty = (select case when agentRoyalty+:agentRoyalty>=:_max then :_max else agentRoyalty+:agentRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("agentRoyalty", agentRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAgentRoyaltyWithMinMaxByKey(final int id, final double agentRoyalty, final double _min, final double _max){
        return updateAgentRoyaltyWithMinMaxByKey(id, agentRoyalty, _min, _max, TABLENAME);
    }

    public int updateAgentRoyaltyWithMinMaxByKey(final int id, final double agentRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( agentRoyalty < 0 ) {
            return updateAgentRoyaltyWithMinByKey(id, agentRoyalty, _min, TABLENAME2);
        } else {
            return updateAgentRoyaltyWithMaxByKey(id, agentRoyalty, _max, TABLENAME2);
        }
    }

    public int updateAgentRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double agentRoyalty, final double _min, final double _max){
        return updateAgentRoyaltyWithMinMaxInKeys(keys, agentRoyalty, _min, _max, TABLENAME);
    }

    public int updateAgentRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double agentRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( agentRoyalty < 0 ) {
            return updateAgentRoyaltyWithMinInKeys(keys, agentRoyalty, _min, TABLENAME2);
        } else {
            return updateAgentRoyaltyWithMaxInKeys(keys, agentRoyalty, _max, TABLENAME2);
        }
    }

    public int updateLhubRoyaltyByKey(final double lhubRoyalty, final int id){
        return updateLhubRoyaltyByKey(lhubRoyalty, id, TABLENAME);
    }

    public int updateLhubRoyaltyByKey(final double lhubRoyalty, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubRoyalty=lhubRoyalty+:lhubRoyalty WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lhubRoyalty", lhubRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubRoyaltyWithMinByKey(final int id, final double lhubRoyalty, final double _min){
        return updateLhubRoyaltyWithMinByKey(id, lhubRoyalty, _min, TABLENAME);
    }

    public int updateLhubRoyaltyWithMinByKey(final int id, final double lhubRoyalty, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubRoyalty = (select case when lhubRoyalty+:lhubRoyalty<=:_min then :_min else lhubRoyalty+:lhubRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lhubRoyalty", lhubRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubRoyaltyWithMinInKeys(final List<Integer> keys, final double lhubRoyalty, final double _min){
        return updateLhubRoyaltyWithMinInKeys(keys, lhubRoyalty, _min, TABLENAME);
    }

    public int updateLhubRoyaltyWithMinInKeys(final List<Integer> keys, final double lhubRoyalty, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubRoyalty = (select case when lhubRoyalty+:lhubRoyalty<=:_min then :_min else lhubRoyalty+:lhubRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lhubRoyalty", lhubRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubRoyaltyWithMaxByKey(final int id, final double lhubRoyalty, final double _max){
        return updateLhubRoyaltyWithMaxByKey(id, lhubRoyalty, _max, TABLENAME);
    }

    public int updateLhubRoyaltyWithMaxByKey(final int id, final double lhubRoyalty, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubRoyalty = (select case when lhubRoyalty+:lhubRoyalty>=:_max then :_max else lhubRoyalty+:lhubRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lhubRoyalty", lhubRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubRoyaltyWithMaxInKeys(final List<Integer> keys, final double lhubRoyalty, final double _max){
        return updateLhubRoyaltyWithMaxInKeys(keys, lhubRoyalty, _max, TABLENAME);
    }

    public int updateLhubRoyaltyWithMaxInKeys(final List<Integer> keys, final double lhubRoyalty, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubRoyalty = (select case when lhubRoyalty+:lhubRoyalty>=:_max then :_max else lhubRoyalty+:lhubRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lhubRoyalty", lhubRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubRoyaltyWithMinMaxByKey(final int id, final double lhubRoyalty, final double _min, final double _max){
        return updateLhubRoyaltyWithMinMaxByKey(id, lhubRoyalty, _min, _max, TABLENAME);
    }

    public int updateLhubRoyaltyWithMinMaxByKey(final int id, final double lhubRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( lhubRoyalty < 0 ) {
            return updateLhubRoyaltyWithMinByKey(id, lhubRoyalty, _min, TABLENAME2);
        } else {
            return updateLhubRoyaltyWithMaxByKey(id, lhubRoyalty, _max, TABLENAME2);
        }
    }

    public int updateLhubRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double lhubRoyalty, final double _min, final double _max){
        return updateLhubRoyaltyWithMinMaxInKeys(keys, lhubRoyalty, _min, _max, TABLENAME);
    }

    public int updateLhubRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double lhubRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( lhubRoyalty < 0 ) {
            return updateLhubRoyaltyWithMinInKeys(keys, lhubRoyalty, _min, TABLENAME2);
        } else {
            return updateLhubRoyaltyWithMaxInKeys(keys, lhubRoyalty, _max, TABLENAME2);
        }
    }

    public int updateLhubDepositByKey(final double lhubDeposit, final int id){
        return updateLhubDepositByKey(lhubDeposit, id, TABLENAME);
    }

    public int updateLhubDepositByKey(final double lhubDeposit, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubDeposit=lhubDeposit+:lhubDeposit WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lhubDeposit", lhubDeposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubDepositWithMinByKey(final int id, final double lhubDeposit, final double _min){
        return updateLhubDepositWithMinByKey(id, lhubDeposit, _min, TABLENAME);
    }

    public int updateLhubDepositWithMinByKey(final int id, final double lhubDeposit, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubDeposit = (select case when lhubDeposit+:lhubDeposit<=:_min then :_min else lhubDeposit+:lhubDeposit end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lhubDeposit", lhubDeposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubDepositWithMinInKeys(final List<Integer> keys, final double lhubDeposit, final double _min){
        return updateLhubDepositWithMinInKeys(keys, lhubDeposit, _min, TABLENAME);
    }

    public int updateLhubDepositWithMinInKeys(final List<Integer> keys, final double lhubDeposit, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubDeposit = (select case when lhubDeposit+:lhubDeposit<=:_min then :_min else lhubDeposit+:lhubDeposit end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lhubDeposit", lhubDeposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubDepositWithMaxByKey(final int id, final double lhubDeposit, final double _max){
        return updateLhubDepositWithMaxByKey(id, lhubDeposit, _max, TABLENAME);
    }

    public int updateLhubDepositWithMaxByKey(final int id, final double lhubDeposit, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubDeposit = (select case when lhubDeposit+:lhubDeposit>=:_max then :_max else lhubDeposit+:lhubDeposit end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lhubDeposit", lhubDeposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubDepositWithMaxInKeys(final List<Integer> keys, final double lhubDeposit, final double _max){
        return updateLhubDepositWithMaxInKeys(keys, lhubDeposit, _max, TABLENAME);
    }

    public int updateLhubDepositWithMaxInKeys(final List<Integer> keys, final double lhubDeposit, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubDeposit = (select case when lhubDeposit+:lhubDeposit>=:_max then :_max else lhubDeposit+:lhubDeposit end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lhubDeposit", lhubDeposit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubDepositWithMinMaxByKey(final int id, final double lhubDeposit, final double _min, final double _max){
        return updateLhubDepositWithMinMaxByKey(id, lhubDeposit, _min, _max, TABLENAME);
    }

    public int updateLhubDepositWithMinMaxByKey(final int id, final double lhubDeposit, final double _min, final double _max, final String TABLENAME2){
        if( lhubDeposit < 0 ) {
            return updateLhubDepositWithMinByKey(id, lhubDeposit, _min, TABLENAME2);
        } else {
            return updateLhubDepositWithMaxByKey(id, lhubDeposit, _max, TABLENAME2);
        }
    }

    public int updateLhubDepositWithMinMaxInKeys(final List<Integer> keys, final double lhubDeposit, final double _min, final double _max){
        return updateLhubDepositWithMinMaxInKeys(keys, lhubDeposit, _min, _max, TABLENAME);
    }

    public int updateLhubDepositWithMinMaxInKeys(final List<Integer> keys, final double lhubDeposit, final double _min, final double _max, final String TABLENAME2){
        if( lhubDeposit < 0 ) {
            return updateLhubDepositWithMinInKeys(keys, lhubDeposit, _min, TABLENAME2);
        } else {
            return updateLhubDepositWithMaxInKeys(keys, lhubDeposit, _max, TABLENAME2);
        }
    }

    public int updateDevelopRoyaltyByKey(final double developRoyalty, final int id){
        return updateDevelopRoyaltyByKey(developRoyalty, id, TABLENAME);
    }

    public int updateDevelopRoyaltyByKey(final double developRoyalty, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET developRoyalty=developRoyalty+:developRoyalty WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("developRoyalty", developRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDevelopRoyaltyWithMinByKey(final int id, final double developRoyalty, final double _min){
        return updateDevelopRoyaltyWithMinByKey(id, developRoyalty, _min, TABLENAME);
    }

    public int updateDevelopRoyaltyWithMinByKey(final int id, final double developRoyalty, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET developRoyalty = (select case when developRoyalty+:developRoyalty<=:_min then :_min else developRoyalty+:developRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("developRoyalty", developRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDevelopRoyaltyWithMinInKeys(final List<Integer> keys, final double developRoyalty, final double _min){
        return updateDevelopRoyaltyWithMinInKeys(keys, developRoyalty, _min, TABLENAME);
    }

    public int updateDevelopRoyaltyWithMinInKeys(final List<Integer> keys, final double developRoyalty, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET developRoyalty = (select case when developRoyalty+:developRoyalty<=:_min then :_min else developRoyalty+:developRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("developRoyalty", developRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDevelopRoyaltyWithMaxByKey(final int id, final double developRoyalty, final double _max){
        return updateDevelopRoyaltyWithMaxByKey(id, developRoyalty, _max, TABLENAME);
    }

    public int updateDevelopRoyaltyWithMaxByKey(final int id, final double developRoyalty, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET developRoyalty = (select case when developRoyalty+:developRoyalty>=:_max then :_max else developRoyalty+:developRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("developRoyalty", developRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDevelopRoyaltyWithMaxInKeys(final List<Integer> keys, final double developRoyalty, final double _max){
        return updateDevelopRoyaltyWithMaxInKeys(keys, developRoyalty, _max, TABLENAME);
    }

    public int updateDevelopRoyaltyWithMaxInKeys(final List<Integer> keys, final double developRoyalty, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET developRoyalty = (select case when developRoyalty+:developRoyalty>=:_max then :_max else developRoyalty+:developRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("developRoyalty", developRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDevelopRoyaltyWithMinMaxByKey(final int id, final double developRoyalty, final double _min, final double _max){
        return updateDevelopRoyaltyWithMinMaxByKey(id, developRoyalty, _min, _max, TABLENAME);
    }

    public int updateDevelopRoyaltyWithMinMaxByKey(final int id, final double developRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( developRoyalty < 0 ) {
            return updateDevelopRoyaltyWithMinByKey(id, developRoyalty, _min, TABLENAME2);
        } else {
            return updateDevelopRoyaltyWithMaxByKey(id, developRoyalty, _max, TABLENAME2);
        }
    }

    public int updateDevelopRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double developRoyalty, final double _min, final double _max){
        return updateDevelopRoyaltyWithMinMaxInKeys(keys, developRoyalty, _min, _max, TABLENAME);
    }

    public int updateDevelopRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double developRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( developRoyalty < 0 ) {
            return updateDevelopRoyaltyWithMinInKeys(keys, developRoyalty, _min, TABLENAME2);
        } else {
            return updateDevelopRoyaltyWithMaxInKeys(keys, developRoyalty, _max, TABLENAME2);
        }
    }

    public int updateArtRoyaltyByKey(final double artRoyalty, final int id){
        return updateArtRoyaltyByKey(artRoyalty, id, TABLENAME);
    }

    public int updateArtRoyaltyByKey(final double artRoyalty, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET artRoyalty=artRoyalty+:artRoyalty WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("artRoyalty", artRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtRoyaltyWithMinByKey(final int id, final double artRoyalty, final double _min){
        return updateArtRoyaltyWithMinByKey(id, artRoyalty, _min, TABLENAME);
    }

    public int updateArtRoyaltyWithMinByKey(final int id, final double artRoyalty, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET artRoyalty = (select case when artRoyalty+:artRoyalty<=:_min then :_min else artRoyalty+:artRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("artRoyalty", artRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtRoyaltyWithMinInKeys(final List<Integer> keys, final double artRoyalty, final double _min){
        return updateArtRoyaltyWithMinInKeys(keys, artRoyalty, _min, TABLENAME);
    }

    public int updateArtRoyaltyWithMinInKeys(final List<Integer> keys, final double artRoyalty, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET artRoyalty = (select case when artRoyalty+:artRoyalty<=:_min then :_min else artRoyalty+:artRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("artRoyalty", artRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtRoyaltyWithMaxByKey(final int id, final double artRoyalty, final double _max){
        return updateArtRoyaltyWithMaxByKey(id, artRoyalty, _max, TABLENAME);
    }

    public int updateArtRoyaltyWithMaxByKey(final int id, final double artRoyalty, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET artRoyalty = (select case when artRoyalty+:artRoyalty>=:_max then :_max else artRoyalty+:artRoyalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("artRoyalty", artRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtRoyaltyWithMaxInKeys(final List<Integer> keys, final double artRoyalty, final double _max){
        return updateArtRoyaltyWithMaxInKeys(keys, artRoyalty, _max, TABLENAME);
    }

    public int updateArtRoyaltyWithMaxInKeys(final List<Integer> keys, final double artRoyalty, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET artRoyalty = (select case when artRoyalty+:artRoyalty>=:_max then :_max else artRoyalty+:artRoyalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("artRoyalty", artRoyalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateArtRoyaltyWithMinMaxByKey(final int id, final double artRoyalty, final double _min, final double _max){
        return updateArtRoyaltyWithMinMaxByKey(id, artRoyalty, _min, _max, TABLENAME);
    }

    public int updateArtRoyaltyWithMinMaxByKey(final int id, final double artRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( artRoyalty < 0 ) {
            return updateArtRoyaltyWithMinByKey(id, artRoyalty, _min, TABLENAME2);
        } else {
            return updateArtRoyaltyWithMaxByKey(id, artRoyalty, _max, TABLENAME2);
        }
    }

    public int updateArtRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double artRoyalty, final double _min, final double _max){
        return updateArtRoyaltyWithMinMaxInKeys(keys, artRoyalty, _min, _max, TABLENAME);
    }

    public int updateArtRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double artRoyalty, final double _min, final double _max, final String TABLENAME2){
        if( artRoyalty < 0 ) {
            return updateArtRoyaltyWithMinInKeys(keys, artRoyalty, _min, TABLENAME2);
        } else {
            return updateArtRoyaltyWithMaxInKeys(keys, artRoyalty, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Orders4profit> orders4profits) {
        return updateByKey(orders4profits, TABLENAME);
    }

    public int[] updateByKey (final List<Orders4profit> orders4profits, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(orders4profits == null || orders4profits.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET orderNo=:orderNo, kindid=:kindid, custid=:custid, lhubid=:lhubid, agentid=:agentid, agentBonus=:agentBonus, agentRoyalty=:agentRoyalty, lhubRoyalty=:lhubRoyalty, lhubDeposit=:lhubDeposit, developRoyalty=:developRoyalty, artRoyalty=:artRoyalty, price=:price, discount=:discount, realprice=:realprice, isProfit4Agent=:isProfit4Agent, isProfit4Lhub=:isProfit4Lhub, status=:status, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), orders4profits);
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
                "	`orderNo`  VARCHAR(64) NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`custid`  INT(11) NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`agentid`  INT(11) NOT NULL," +
                "	`agentBonus`  DOUBLE NOT NULL," +
                "	`agentRoyalty`  DOUBLE NOT NULL," +
                "	`lhubRoyalty`  DOUBLE NOT NULL," +
                "	`lhubDeposit`  DOUBLE NOT NULL," +
                "	`developRoyalty`  DOUBLE NOT NULL," +
                "	`artRoyalty`  DOUBLE NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`discount`  DOUBLE NOT NULL," +
                "	`realprice`  DOUBLE NOT NULL," +
                "	`isProfit4Agent`  BIT(1) NOT NULL," +
                "	`isProfit4Lhub`  BIT(1) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `orderNo` (`orderNo`)," +
                "	KEY `custid` (`custid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `agentid` (`agentid`)," +
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

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`orderNo`  VARCHAR(64) NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`custid`  INT(11) NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`agentid`  INT(11) NOT NULL," +
                "	`agentBonus`  DOUBLE NOT NULL," +
                "	`agentRoyalty`  DOUBLE NOT NULL," +
                "	`lhubRoyalty`  DOUBLE NOT NULL," +
                "	`lhubDeposit`  DOUBLE NOT NULL," +
                "	`developRoyalty`  DOUBLE NOT NULL," +
                "	`artRoyalty`  DOUBLE NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`discount`  DOUBLE NOT NULL," +
                "	`realprice`  DOUBLE NOT NULL," +
                "	`isProfit4Agent`  BIT(1) NOT NULL," +
                "	`isProfit4Lhub`  BIT(1) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `orderNo` (`orderNo`)," +
                "	KEY `custid` (`custid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `agentid` (`agentid`)," +
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
