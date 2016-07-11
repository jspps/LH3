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

//learnhall3_design - customer
@SuppressWarnings({"rawtypes", "unchecked"})
public class CustomerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(CustomerDAO.class);

    public static final String TABLE = "customer";
    public static String TABLENAME = "customer";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "accountid", "name", "kbiAll", "kbiUse", "email", "province", "city", "seat", "headIcon", "descr", "moneyAll", "moneyCur", "recommendCode", "alipay", "alipayRealName", "isVerifyAlipay", "backAlipay", "backAlipayName"};
    public static String coulmns = "id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName";
    public static String coulmns2 = "accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName";

    public CustomerDAO(Connection conn) {
        super(conn);
    }

    public CustomerDAO(DataSource ds) {
        super(ds);
    }

    public CustomerDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Customer customer) {
        return insert(customer, TABLENAME);
    }

    public int insert(final Customer customer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            customer.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName) VALUES (:accountid, :name, :kbiAll, :kbiUse, :email, :province, :city, :seat, :headIcon, :descr, :moneyAll, :moneyCur, :recommendCode, :alipay, :alipayRealName, :isVerifyAlipay, :backAlipay, :backAlipayName)");
            Map map = super.insert(sql.toString(), customer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Customer customer) {
        return asyncInsert(customer, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Customer customer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(customer, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Customer customer) {
        return asyncInsert2(customer, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Customer customer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(customer, TABLENAME2);
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

    public int insert2(final Customer customer) {
        return insert2(customer, TABLENAME);
    }

    public int insert2(final Customer customer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            customer.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName) VALUES (:id, :accountid, :name, :kbiAll, :kbiUse, :email, :province, :city, :seat, :headIcon, :descr, :moneyAll, :moneyCur, :recommendCode, :alipay, :alipayRealName, :isVerifyAlipay, :backAlipay, :backAlipayName)");
            Map map = super.insert(sql.toString(), customer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Customer> customers) {
        return insert(customers, TABLENAME);
    }

    public int[] insert(final List<Customer> customers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(customers == null || customers.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName) VALUES (:accountid, :name, :kbiAll, :kbiUse, :email, :province, :city, :seat, :headIcon, :descr, :moneyAll, :moneyCur, :recommendCode, :alipay, :alipayRealName, :isVerifyAlipay, :backAlipay, :backAlipayName)");
            return super.batchInsert(sql.toString(), customers);
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

    public int deleteInBeans(final List<Customer> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Customer> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Customer customer = beans.get(i);
                int id = customer.id;
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

    public List<Customer> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Customer> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Customer.class);
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
            sql.append("SELECT id, accountid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Customer> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Customer> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Customer> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Customer> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Customer.class);
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

    public List<Customer> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Customer> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Customer.class);
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

    public Customer last() {
        return last(TABLENAME);
    }

    public Customer last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Customer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Customer> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Customer> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Customer> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Customer> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Customer.class);
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

    public Customer selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Customer selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Customer.class);
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

    public Customer selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Customer selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Customer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Customer selectByAccountid(final Integer accountid) {
        return selectByAccountid(accountid, TABLENAME);
    }

    public Customer selectByAccountid(final Integer accountid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" WHERE accountid = :accountid");
            Map params = newMap();
            params.put("accountid", accountid);
            return super.queryForObject(sql.toString(), params, Customer.class);
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

    public List<Customer> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Customer> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Customer.class);
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

    public int updateByKey(final Customer customer) {
        return updateByKey(customer, TABLENAME);
    }

    public int updateByKey(final Customer customer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = customer.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), customer);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Customer customer) {
        return asyncUpdate(customer, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Customer customer, final String TABLENAME2) {
        try {

            String _ustr = customer.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, customer);
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

    public int updateAccountidByKey(final int accountid, final int id){
        return updateAccountidByKey(accountid, id, TABLENAME);
    }

    public int updateAccountidByKey(final int accountid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=accountid+:accountid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMinByKey(final int id, final int accountid, final int _min){
        return updateAccountidWithMinByKey(id, accountid, _min, TABLENAME);
    }

    public int updateAccountidWithMinByKey(final int id, final int accountid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE id in (").append(str).append(")");
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

    public int updateAccountidWithMaxByKey(final int id, final int accountid, final int _max){
        return updateAccountidWithMaxByKey(id, accountid, _max, TABLENAME);
    }

    public int updateAccountidWithMaxByKey(final int id, final int accountid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE id in (").append(str).append(")");
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

    public int updateAccountidWithMinMaxByKey(final int id, final int accountid, final int _min, final int _max){
        return updateAccountidWithMinMaxByKey(id, accountid, _min, _max, TABLENAME);
    }

    public int updateAccountidWithMinMaxByKey(final int id, final int accountid, final int _min, final int _max, final String TABLENAME2){
        if( accountid < 0 ) {
            return updateAccountidWithMinByKey(id, accountid, _min, TABLENAME2);
        } else {
            return updateAccountidWithMaxByKey(id, accountid, _max, TABLENAME2);
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

    public int updateKbiAllByKey(final int kbiAll, final int id){
        return updateKbiAllByKey(kbiAll, id, TABLENAME);
    }

    public int updateKbiAllByKey(final int kbiAll, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiAll=kbiAll+:kbiAll WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kbiAll", kbiAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiAllWithMinByKey(final int id, final int kbiAll, final int _min){
        return updateKbiAllWithMinByKey(id, kbiAll, _min, TABLENAME);
    }

    public int updateKbiAllWithMinByKey(final int id, final int kbiAll, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiAll = (select case when kbiAll+:kbiAll<=:_min then :_min else kbiAll+:kbiAll end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kbiAll", kbiAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiAllWithMinInKeys(final List<Integer> keys, final int kbiAll, final int _min){
        return updateKbiAllWithMinInKeys(keys, kbiAll, _min, TABLENAME);
    }

    public int updateKbiAllWithMinInKeys(final List<Integer> keys, final int kbiAll, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiAll = (select case when kbiAll+:kbiAll<=:_min then :_min else kbiAll+:kbiAll end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kbiAll", kbiAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiAllWithMaxByKey(final int id, final int kbiAll, final int _max){
        return updateKbiAllWithMaxByKey(id, kbiAll, _max, TABLENAME);
    }

    public int updateKbiAllWithMaxByKey(final int id, final int kbiAll, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiAll = (select case when kbiAll+:kbiAll>=:_max then :_max else kbiAll+:kbiAll end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kbiAll", kbiAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiAllWithMaxInKeys(final List<Integer> keys, final int kbiAll, final int _max){
        return updateKbiAllWithMaxInKeys(keys, kbiAll, _max, TABLENAME);
    }

    public int updateKbiAllWithMaxInKeys(final List<Integer> keys, final int kbiAll, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiAll = (select case when kbiAll+:kbiAll>=:_max then :_max else kbiAll+:kbiAll end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kbiAll", kbiAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiAllWithMinMaxByKey(final int id, final int kbiAll, final int _min, final int _max){
        return updateKbiAllWithMinMaxByKey(id, kbiAll, _min, _max, TABLENAME);
    }

    public int updateKbiAllWithMinMaxByKey(final int id, final int kbiAll, final int _min, final int _max, final String TABLENAME2){
        if( kbiAll < 0 ) {
            return updateKbiAllWithMinByKey(id, kbiAll, _min, TABLENAME2);
        } else {
            return updateKbiAllWithMaxByKey(id, kbiAll, _max, TABLENAME2);
        }
    }

    public int updateKbiAllWithMinMaxInKeys(final List<Integer> keys, final int kbiAll, final int _min, final int _max){
        return updateKbiAllWithMinMaxInKeys(keys, kbiAll, _min, _max, TABLENAME);
    }

    public int updateKbiAllWithMinMaxInKeys(final List<Integer> keys, final int kbiAll, final int _min, final int _max, final String TABLENAME2){
        if( kbiAll < 0 ) {
            return updateKbiAllWithMinInKeys(keys, kbiAll, _min, TABLENAME2);
        } else {
            return updateKbiAllWithMaxInKeys(keys, kbiAll, _max, TABLENAME2);
        }
    }

    public int updateKbiUseByKey(final int kbiUse, final int id){
        return updateKbiUseByKey(kbiUse, id, TABLENAME);
    }

    public int updateKbiUseByKey(final int kbiUse, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiUse=kbiUse+:kbiUse WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kbiUse", kbiUse);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiUseWithMinByKey(final int id, final int kbiUse, final int _min){
        return updateKbiUseWithMinByKey(id, kbiUse, _min, TABLENAME);
    }

    public int updateKbiUseWithMinByKey(final int id, final int kbiUse, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiUse = (select case when kbiUse+:kbiUse<=:_min then :_min else kbiUse+:kbiUse end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kbiUse", kbiUse);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiUseWithMinInKeys(final List<Integer> keys, final int kbiUse, final int _min){
        return updateKbiUseWithMinInKeys(keys, kbiUse, _min, TABLENAME);
    }

    public int updateKbiUseWithMinInKeys(final List<Integer> keys, final int kbiUse, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiUse = (select case when kbiUse+:kbiUse<=:_min then :_min else kbiUse+:kbiUse end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kbiUse", kbiUse);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiUseWithMaxByKey(final int id, final int kbiUse, final int _max){
        return updateKbiUseWithMaxByKey(id, kbiUse, _max, TABLENAME);
    }

    public int updateKbiUseWithMaxByKey(final int id, final int kbiUse, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiUse = (select case when kbiUse+:kbiUse>=:_max then :_max else kbiUse+:kbiUse end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kbiUse", kbiUse);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiUseWithMaxInKeys(final List<Integer> keys, final int kbiUse, final int _max){
        return updateKbiUseWithMaxInKeys(keys, kbiUse, _max, TABLENAME);
    }

    public int updateKbiUseWithMaxInKeys(final List<Integer> keys, final int kbiUse, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbiUse = (select case when kbiUse+:kbiUse>=:_max then :_max else kbiUse+:kbiUse end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kbiUse", kbiUse);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiUseWithMinMaxByKey(final int id, final int kbiUse, final int _min, final int _max){
        return updateKbiUseWithMinMaxByKey(id, kbiUse, _min, _max, TABLENAME);
    }

    public int updateKbiUseWithMinMaxByKey(final int id, final int kbiUse, final int _min, final int _max, final String TABLENAME2){
        if( kbiUse < 0 ) {
            return updateKbiUseWithMinByKey(id, kbiUse, _min, TABLENAME2);
        } else {
            return updateKbiUseWithMaxByKey(id, kbiUse, _max, TABLENAME2);
        }
    }

    public int updateKbiUseWithMinMaxInKeys(final List<Integer> keys, final int kbiUse, final int _min, final int _max){
        return updateKbiUseWithMinMaxInKeys(keys, kbiUse, _min, _max, TABLENAME);
    }

    public int updateKbiUseWithMinMaxInKeys(final List<Integer> keys, final int kbiUse, final int _min, final int _max, final String TABLENAME2){
        if( kbiUse < 0 ) {
            return updateKbiUseWithMinInKeys(keys, kbiUse, _min, TABLENAME2);
        } else {
            return updateKbiUseWithMaxInKeys(keys, kbiUse, _max, TABLENAME2);
        }
    }

    public int updateMoneyAllByKey(final double moneyAll, final int id){
        return updateMoneyAllByKey(moneyAll, id, TABLENAME);
    }

    public int updateMoneyAllByKey(final double moneyAll, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll=moneyAll+:moneyAll WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("moneyAll", moneyAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyAllWithMinByKey(final int id, final double moneyAll, final double _min){
        return updateMoneyAllWithMinByKey(id, moneyAll, _min, TABLENAME);
    }

    public int updateMoneyAllWithMinByKey(final int id, final double moneyAll, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll<=:_min then :_min else moneyAll+:moneyAll end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("moneyAll", moneyAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyAllWithMinInKeys(final List<Integer> keys, final double moneyAll, final double _min){
        return updateMoneyAllWithMinInKeys(keys, moneyAll, _min, TABLENAME);
    }

    public int updateMoneyAllWithMinInKeys(final List<Integer> keys, final double moneyAll, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll<=:_min then :_min else moneyAll+:moneyAll end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("moneyAll", moneyAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyAllWithMaxByKey(final int id, final double moneyAll, final double _max){
        return updateMoneyAllWithMaxByKey(id, moneyAll, _max, TABLENAME);
    }

    public int updateMoneyAllWithMaxByKey(final int id, final double moneyAll, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll>=:_max then :_max else moneyAll+:moneyAll end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("moneyAll", moneyAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyAllWithMaxInKeys(final List<Integer> keys, final double moneyAll, final double _max){
        return updateMoneyAllWithMaxInKeys(keys, moneyAll, _max, TABLENAME);
    }

    public int updateMoneyAllWithMaxInKeys(final List<Integer> keys, final double moneyAll, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll>=:_max then :_max else moneyAll+:moneyAll end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("moneyAll", moneyAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyAllWithMinMaxByKey(final int id, final double moneyAll, final double _min, final double _max){
        return updateMoneyAllWithMinMaxByKey(id, moneyAll, _min, _max, TABLENAME);
    }

    public int updateMoneyAllWithMinMaxByKey(final int id, final double moneyAll, final double _min, final double _max, final String TABLENAME2){
        if( moneyAll < 0 ) {
            return updateMoneyAllWithMinByKey(id, moneyAll, _min, TABLENAME2);
        } else {
            return updateMoneyAllWithMaxByKey(id, moneyAll, _max, TABLENAME2);
        }
    }

    public int updateMoneyAllWithMinMaxInKeys(final List<Integer> keys, final double moneyAll, final double _min, final double _max){
        return updateMoneyAllWithMinMaxInKeys(keys, moneyAll, _min, _max, TABLENAME);
    }

    public int updateMoneyAllWithMinMaxInKeys(final List<Integer> keys, final double moneyAll, final double _min, final double _max, final String TABLENAME2){
        if( moneyAll < 0 ) {
            return updateMoneyAllWithMinInKeys(keys, moneyAll, _min, TABLENAME2);
        } else {
            return updateMoneyAllWithMaxInKeys(keys, moneyAll, _max, TABLENAME2);
        }
    }

    public int updateMoneyCurByKey(final double moneyCur, final int id){
        return updateMoneyCurByKey(moneyCur, id, TABLENAME);
    }

    public int updateMoneyCurByKey(final double moneyCur, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur=moneyCur+:moneyCur WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("moneyCur", moneyCur);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyCurWithMinByKey(final int id, final double moneyCur, final double _min){
        return updateMoneyCurWithMinByKey(id, moneyCur, _min, TABLENAME);
    }

    public int updateMoneyCurWithMinByKey(final int id, final double moneyCur, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur<=:_min then :_min else moneyCur+:moneyCur end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("moneyCur", moneyCur);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyCurWithMinInKeys(final List<Integer> keys, final double moneyCur, final double _min){
        return updateMoneyCurWithMinInKeys(keys, moneyCur, _min, TABLENAME);
    }

    public int updateMoneyCurWithMinInKeys(final List<Integer> keys, final double moneyCur, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur<=:_min then :_min else moneyCur+:moneyCur end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("moneyCur", moneyCur);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyCurWithMaxByKey(final int id, final double moneyCur, final double _max){
        return updateMoneyCurWithMaxByKey(id, moneyCur, _max, TABLENAME);
    }

    public int updateMoneyCurWithMaxByKey(final int id, final double moneyCur, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur>=:_max then :_max else moneyCur+:moneyCur end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("moneyCur", moneyCur);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyCurWithMaxInKeys(final List<Integer> keys, final double moneyCur, final double _max){
        return updateMoneyCurWithMaxInKeys(keys, moneyCur, _max, TABLENAME);
    }

    public int updateMoneyCurWithMaxInKeys(final List<Integer> keys, final double moneyCur, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur>=:_max then :_max else moneyCur+:moneyCur end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("moneyCur", moneyCur);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyCurWithMinMaxByKey(final int id, final double moneyCur, final double _min, final double _max){
        return updateMoneyCurWithMinMaxByKey(id, moneyCur, _min, _max, TABLENAME);
    }

    public int updateMoneyCurWithMinMaxByKey(final int id, final double moneyCur, final double _min, final double _max, final String TABLENAME2){
        if( moneyCur < 0 ) {
            return updateMoneyCurWithMinByKey(id, moneyCur, _min, TABLENAME2);
        } else {
            return updateMoneyCurWithMaxByKey(id, moneyCur, _max, TABLENAME2);
        }
    }

    public int updateMoneyCurWithMinMaxInKeys(final List<Integer> keys, final double moneyCur, final double _min, final double _max){
        return updateMoneyCurWithMinMaxInKeys(keys, moneyCur, _min, _max, TABLENAME);
    }

    public int updateMoneyCurWithMinMaxInKeys(final List<Integer> keys, final double moneyCur, final double _min, final double _max, final String TABLENAME2){
        if( moneyCur < 0 ) {
            return updateMoneyCurWithMinInKeys(keys, moneyCur, _min, TABLENAME2);
        } else {
            return updateMoneyCurWithMaxInKeys(keys, moneyCur, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Customer> customers) {
        return updateByKey(customers, TABLENAME);
    }

    public int[] updateByKey (final List<Customer> customers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(customers == null || customers.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=:accountid, name=:name, kbiAll=:kbiAll, kbiUse=:kbiUse, email=:email, province=:province, city=:city, seat=:seat, headIcon=:headIcon, descr=:descr, moneyAll=:moneyAll, moneyCur=:moneyCur, recommendCode=:recommendCode, alipay=:alipay, alipayRealName=:alipayRealName, isVerifyAlipay=:isVerifyAlipay, backAlipay=:backAlipay, backAlipayName=:backAlipayName WHERE id=:id");
            return super.batchUpdate2(sql.toString(), customers);
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
                "	`accountid`  INT(11) NOT NULL," +
                "	`name`  TINYTEXT NOT NULL," +
                "	`kbiAll`  INT(11) NOT NULL," +
                "	`kbiUse`  INT(11) NOT NULL," +
                "	`email`  VARCHAR(128) NOT NULL," +
                "	`province`  VARCHAR(32) NOT NULL," +
                "	`city`  VARCHAR(32) NOT NULL," +
                "	`seat`  VARCHAR(128) NOT NULL," +
                "	`headIcon`  VARCHAR(128) NOT NULL," +
                "	`descr`  TEXT NOT NULL," +
                "	`moneyAll`  DOUBLE NOT NULL," +
                "	`moneyCur`  DOUBLE NOT NULL," +
                "	`recommendCode`  VARCHAR(64) NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`alipayRealName`  TINYTEXT NOT NULL," +
                "	`isVerifyAlipay`  BIT(1) NOT NULL," +
                "	`backAlipay`  TINYTEXT NOT NULL," +
                "	`backAlipayName`  TINYTEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
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
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`name`  TINYTEXT NOT NULL," +
                "	`kbiAll`  INT(11) NOT NULL," +
                "	`kbiUse`  INT(11) NOT NULL," +
                "	`email`  VARCHAR(128) NOT NULL," +
                "	`province`  VARCHAR(32) NOT NULL," +
                "	`city`  VARCHAR(32) NOT NULL," +
                "	`seat`  VARCHAR(128) NOT NULL," +
                "	`headIcon`  VARCHAR(128) NOT NULL," +
                "	`descr`  TEXT NOT NULL," +
                "	`moneyAll`  DOUBLE NOT NULL," +
                "	`moneyCur`  DOUBLE NOT NULL," +
                "	`recommendCode`  VARCHAR(64) NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`alipayRealName`  TINYTEXT NOT NULL," +
                "	`isVerifyAlipay`  BIT(1) NOT NULL," +
                "	`backAlipay`  TINYTEXT NOT NULL," +
                "	`backAlipayName`  TINYTEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
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
