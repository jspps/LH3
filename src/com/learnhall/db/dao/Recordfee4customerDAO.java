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

//learnhall3_design - recordfee4customer
@SuppressWarnings({"rawtypes", "unchecked"})
public class Recordfee4customerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Recordfee4customerDAO.class);

    public static final String TABLE = "recordfee4customer";
    public static String TABLENAME = "recordfee4customer";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "type", "custid", "custname", "val", "cont", "kindid", "createtime"};
    public static String coulmns = "id, type, custid, custname, val, cont, kindid, createtime";
    public static String coulmns2 = "type, custid, custname, val, cont, kindid, createtime";

    public Recordfee4customerDAO(Connection conn) {
        super(conn);
    }

    public Recordfee4customerDAO(DataSource ds) {
        super(ds);
    }

    public Recordfee4customerDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Recordfee4customer recordfee4customer) {
        return insert(recordfee4customer, TABLENAME);
    }

    public int insert(final Recordfee4customer recordfee4customer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            recordfee4customer.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (type, custid, custname, val, cont, kindid, createtime) VALUES (:type, :custid, :custname, :val, :cont, :kindid, :createtime)");
            Map map = super.insert(sql.toString(), recordfee4customer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Recordfee4customer recordfee4customer) {
        return asyncInsert(recordfee4customer, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Recordfee4customer recordfee4customer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(recordfee4customer, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Recordfee4customer recordfee4customer) {
        return asyncInsert2(recordfee4customer, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Recordfee4customer recordfee4customer, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(recordfee4customer, TABLENAME2);
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

    public int insert2(final Recordfee4customer recordfee4customer) {
        return insert2(recordfee4customer, TABLENAME);
    }

    public int insert2(final Recordfee4customer recordfee4customer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            recordfee4customer.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, type, custid, custname, val, cont, kindid, createtime) VALUES (:id, :type, :custid, :custname, :val, :cont, :kindid, :createtime)");
            Map map = super.insert(sql.toString(), recordfee4customer);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Recordfee4customer> recordfee4customers) {
        return insert(recordfee4customers, TABLENAME);
    }

    public int[] insert(final List<Recordfee4customer> recordfee4customers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(recordfee4customers == null || recordfee4customers.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (type, custid, custname, val, cont, kindid, createtime) VALUES (:type, :custid, :custname, :val, :cont, :kindid, :createtime)");
            return super.batchInsert(sql.toString(), recordfee4customers);
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

    public int deleteInBeans(final List<Recordfee4customer> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Recordfee4customer> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Recordfee4customer recordfee4customer = beans.get(i);
                int id = recordfee4customer.id;
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

    public List<Recordfee4customer> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Recordfee4customer> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Recordfee4customer.class);
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
            sql.append("SELECT id, type, custid, kindid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordfee4customer> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Recordfee4customer> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Recordfee4customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordfee4customer> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Recordfee4customer> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
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

    public List<Recordfee4customer> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Recordfee4customer> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Recordfee4customer.class);
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

    public Recordfee4customer last() {
        return last(TABLENAME);
    }

    public Recordfee4customer last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Recordfee4customer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordfee4customer> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Recordfee4customer> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordfee4customer> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Recordfee4customer> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
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

    public Recordfee4customer selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Recordfee4customer selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recordfee4customer.class);
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

    public int countByTypeCustidKindid(final Integer type, Integer custid, Integer kindid) {
        return  countByTypeCustidKindid(type, custid, kindid, TABLENAME);
    }

    public int countByTypeCustidKindid(final Integer type, Integer custid, Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid AND kindid=:kindid ");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
            params.put("kindid", kindid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordfee4customer> selectByTypeCustidKindid(final Integer type, Integer custid, Integer kindid) {
        return selectByTypeCustidKindid(type, custid, kindid, TABLENAME);
    }

    public List<Recordfee4customer> selectByTypeCustidKindid(final Integer type, Integer custid, Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid AND kindid=:kindid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
            params.put("kindid", kindid);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeCustidKindidPKs(final Integer type, Integer custid, Integer kindid) {
        return selectByTypeCustidKindidPKs(type, custid, kindid, TABLENAME);
    }

    public List<Integer> selectByTypeCustidKindidPKs(final Integer type, Integer custid, Integer kindid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid AND kindid=:kindid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
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

    public List<Recordfee4customer> selectPageByTypeCustidKindid(final Integer type, Integer custid, Integer kindid, final int begin, final int num) {
        return selectPageByTypeCustidKindid(type, custid, kindid, begin, num, TABLENAME);
    }

    public List<Recordfee4customer> selectPageByTypeCustidKindid(final Integer type, Integer custid, Integer kindid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid AND kindid=:kindid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
            params.put("kindid", kindid);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeCustidKindidPKs(final Integer type, Integer custid, Integer kindid, final int begin, final int num) {
        return selectPageByTypeCustidKindidPKs(type, custid, kindid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeCustidKindidPKs(final Integer type, Integer custid, Integer kindid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid AND kindid=:kindid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
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

    public Recordfee4customer selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Recordfee4customer selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recordfee4customer.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByTypeCustid(final Integer type, Integer custid) {
        return  countByTypeCustid(type, custid, TABLENAME);
    }

    public int countByTypeCustid(final Integer type, Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid ");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recordfee4customer> selectByTypeCustid(final Integer type, Integer custid) {
        return selectByTypeCustid(type, custid, TABLENAME);
    }

    public List<Recordfee4customer> selectByTypeCustid(final Integer type, Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeCustidPKs(final Integer type, Integer custid) {
        return selectByTypeCustidPKs(type, custid, TABLENAME);
    }

    public List<Integer> selectByTypeCustidPKs(final Integer type, Integer custid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
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

    public List<Recordfee4customer> selectPageByTypeCustid(final Integer type, Integer custid, final int begin, final int num) {
        return selectPageByTypeCustid(type, custid, begin, num, TABLENAME);
    }

    public List<Recordfee4customer> selectPageByTypeCustid(final Integer type, Integer custid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("custid", custid);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeCustidPKs(final Integer type, Integer custid, final int begin, final int num) {
        return selectPageByTypeCustidPKs(type, custid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeCustidPKs(final Integer type, Integer custid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND custid=:custid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
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

    public List<Recordfee4customer> selectByCustid(final Integer custid) {
        return selectByCustid(custid, TABLENAME);
    }

    public List<Recordfee4customer> selectByCustid(final Integer custid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE custid = :custid ORDER BY id ");
            Map params = newMap();
            params.put("custid", custid);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
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

    public List<Recordfee4customer> selectPageByCustid(final Integer custid, final int begin, final int num) {
        return selectPageByCustid(custid, begin, num, TABLENAME);
    }

    public List<Recordfee4customer> selectPageByCustid(final Integer custid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" WHERE custid = :custid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("custid", custid);
            return super.queryForList(sql.toString(), params, Recordfee4customer.class);
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

    public List<Recordfee4customer> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Recordfee4customer> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, custid, custname, val, cont, kindid, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Recordfee4customer.class);
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

    public int updateByKey(final Recordfee4customer recordfee4customer) {
        return updateByKey(recordfee4customer, TABLENAME);
    }

    public int updateByKey(final Recordfee4customer recordfee4customer, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = recordfee4customer.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), recordfee4customer);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Recordfee4customer recordfee4customer) {
        return asyncUpdate(recordfee4customer, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Recordfee4customer recordfee4customer, final String TABLENAME2) {
        try {

            String _ustr = recordfee4customer.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, recordfee4customer);
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

    public int updateValByKey(final double val, final int id){
        return updateValByKey(val, id, TABLENAME);
    }

    public int updateValByKey(final double val, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET val=val+:val WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("val", val);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValWithMinByKey(final int id, final double val, final double _min){
        return updateValWithMinByKey(id, val, _min, TABLENAME);
    }

    public int updateValWithMinByKey(final int id, final double val, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET val = (select case when val+:val<=:_min then :_min else val+:val end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("val", val);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValWithMinInKeys(final List<Integer> keys, final double val, final double _min){
        return updateValWithMinInKeys(keys, val, _min, TABLENAME);
    }

    public int updateValWithMinInKeys(final List<Integer> keys, final double val, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET val = (select case when val+:val<=:_min then :_min else val+:val end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("val", val);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValWithMaxByKey(final int id, final double val, final double _max){
        return updateValWithMaxByKey(id, val, _max, TABLENAME);
    }

    public int updateValWithMaxByKey(final int id, final double val, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET val = (select case when val+:val>=:_max then :_max else val+:val end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("val", val);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValWithMaxInKeys(final List<Integer> keys, final double val, final double _max){
        return updateValWithMaxInKeys(keys, val, _max, TABLENAME);
    }

    public int updateValWithMaxInKeys(final List<Integer> keys, final double val, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET val = (select case when val+:val>=:_max then :_max else val+:val end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("val", val);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValWithMinMaxByKey(final int id, final double val, final double _min, final double _max){
        return updateValWithMinMaxByKey(id, val, _min, _max, TABLENAME);
    }

    public int updateValWithMinMaxByKey(final int id, final double val, final double _min, final double _max, final String TABLENAME2){
        if( val < 0 ) {
            return updateValWithMinByKey(id, val, _min, TABLENAME2);
        } else {
            return updateValWithMaxByKey(id, val, _max, TABLENAME2);
        }
    }

    public int updateValWithMinMaxInKeys(final List<Integer> keys, final double val, final double _min, final double _max){
        return updateValWithMinMaxInKeys(keys, val, _min, _max, TABLENAME);
    }

    public int updateValWithMinMaxInKeys(final List<Integer> keys, final double val, final double _min, final double _max, final String TABLENAME2){
        if( val < 0 ) {
            return updateValWithMinInKeys(keys, val, _min, TABLENAME2);
        } else {
            return updateValWithMaxInKeys(keys, val, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Recordfee4customer> recordfee4customers) {
        return updateByKey(recordfee4customers, TABLENAME);
    }

    public int[] updateByKey (final List<Recordfee4customer> recordfee4customers, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(recordfee4customers == null || recordfee4customers.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=:type, custid=:custid, custname=:custname, val=:val, cont=:cont, kindid=:kindid, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), recordfee4customers);
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
                "	`custid`  INT(11) NOT NULL," +
                "	`custname`  TINYTEXT NOT NULL," +
                "	`val`  DOUBLE NOT NULL," +
                "	`cont`  TINYTEXT NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type_custid` (`type`, `custid`)," +
                "	KEY `type_custid_kindid` (`type`, `custid`, `kindid`)," +
                "	KEY `custid` (`custid`)" +
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
                "	`custid`  INT(11) NOT NULL," +
                "	`custname`  TINYTEXT NOT NULL," +
                "	`val`  DOUBLE NOT NULL," +
                "	`cont`  TINYTEXT NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type_custid` (`type`, `custid`)," +
                "	KEY `type_custid_kindid` (`type`, `custid`, `kindid`)," +
                "	KEY `custid` (`custid`)" +
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
