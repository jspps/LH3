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

//learnhall3_design - exchangermb
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExchangermbDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ExchangermbDAO.class);

    public static final String TABLE = "exchangermb";
    public static String TABLENAME = "exchangermb";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "type", "makerid", "nmMaker", "alipay", "alipayName", "reason", "moneyCur", "monyeApply", "monyeReal", "status", "batchNo", "statusOpt", "createtime", "content", "lasttime"};
    public static String coulmns = "id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime";
    public static String coulmns2 = "type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime";

    public ExchangermbDAO(Connection conn) {
        super(conn);
    }

    public ExchangermbDAO(DataSource ds) {
        super(ds);
    }

    public ExchangermbDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Exchangermb exchangermb) {
        return insert(exchangermb, TABLENAME);
    }

    public int insert(final Exchangermb exchangermb, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            exchangermb.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime) VALUES (:type, :makerid, :nmMaker, :alipay, :alipayName, :reason, :moneyCur, :monyeApply, :monyeReal, :status, :batchNo, :statusOpt, :createtime, :content, :lasttime)");
            Map map = super.insert(sql.toString(), exchangermb);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Exchangermb exchangermb) {
        return asyncInsert(exchangermb, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Exchangermb exchangermb, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(exchangermb, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Exchangermb exchangermb) {
        return asyncInsert2(exchangermb, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Exchangermb exchangermb, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(exchangermb, TABLENAME2);
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

    public int insert2(final Exchangermb exchangermb) {
        return insert2(exchangermb, TABLENAME);
    }

    public int insert2(final Exchangermb exchangermb, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            exchangermb.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime) VALUES (:id, :type, :makerid, :nmMaker, :alipay, :alipayName, :reason, :moneyCur, :monyeApply, :monyeReal, :status, :batchNo, :statusOpt, :createtime, :content, :lasttime)");
            Map map = super.insert(sql.toString(), exchangermb);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Exchangermb> exchangermbs) {
        return insert(exchangermbs, TABLENAME);
    }

    public int[] insert(final List<Exchangermb> exchangermbs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(exchangermbs == null || exchangermbs.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime) VALUES (:type, :makerid, :nmMaker, :alipay, :alipayName, :reason, :moneyCur, :monyeApply, :monyeReal, :status, :batchNo, :statusOpt, :createtime, :content, :lasttime)");
            return super.batchInsert(sql.toString(), exchangermbs);
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

    public int deleteInBeans(final List<Exchangermb> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Exchangermb> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Exchangermb exchangermb = beans.get(i);
                int id = exchangermb.id;
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

    public List<Exchangermb> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Exchangermb> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Exchangermb.class);
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
            sql.append("SELECT id, type, makerid, batchNo FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exchangermb> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Exchangermb> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Exchangermb.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exchangermb> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Exchangermb> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
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

    public List<Exchangermb> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Exchangermb> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Exchangermb.class);
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

    public Exchangermb last() {
        return last(TABLENAME);
    }

    public Exchangermb last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Exchangermb.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exchangermb> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Exchangermb> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exchangermb> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Exchangermb> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
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

    public Exchangermb selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Exchangermb selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Exchangermb.class);
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

    public List<Exchangermb> selectByTypeMakerid(final Integer type, Integer makerid) {
        return selectByTypeMakerid(type, makerid, TABLENAME);
    }

    public List<Exchangermb> selectByTypeMakerid(final Integer type, Integer makerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
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

    public List<Exchangermb> selectPageByTypeMakerid(final Integer type, Integer makerid, final int begin, final int num) {
        return selectPageByTypeMakerid(type, makerid, begin, num, TABLENAME);
    }

    public List<Exchangermb> selectPageByTypeMakerid(final Integer type, Integer makerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE type=:type AND makerid=:makerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("makerid", makerid);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
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

    public int countByBatchNo(final String batchNo) {
        return countByBatchNo(batchNo, TABLENAME);
    }

    public int countByBatchNo(final String batchNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE batchNo = :batchNo ");
            Map params = newMap();
            params.put("batchNo", batchNo);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exchangermb> selectByBatchNo(final String batchNo) {
        return selectByBatchNo(batchNo, TABLENAME);
    }

    public List<Exchangermb> selectByBatchNo(final String batchNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE batchNo = :batchNo ORDER BY id ");
            Map params = newMap();
            params.put("batchNo", batchNo);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByBatchNoPKs(final String batchNo) {
        return selectByBatchNoPKs(batchNo, TABLENAME);
    }

    public List<Integer> selectByBatchNoPKs(final String batchNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE batchNo = :batchNo ORDER BY id ");
            Map params = newMap();
            params.put("batchNo", batchNo);
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

    public List<Exchangermb> selectPageByBatchNo(final String batchNo, final int begin, final int num) {
        return selectPageByBatchNo(batchNo, begin, num, TABLENAME);
    }

    public List<Exchangermb> selectPageByBatchNo(final String batchNo, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE batchNo = :batchNo ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("batchNo", batchNo);
            return super.queryForList(sql.toString(), params, Exchangermb.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByBatchNoPKs(final String batchNo, final int begin, final int num) {
        return selectPageByBatchNoPKs(batchNo, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByBatchNoPKs(final String batchNo, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE batchNo = :batchNo ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("batchNo", batchNo);
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

    public int countLikeBatchNo(final String batchNo) {
        return countLikeBatchNo(batchNo, TABLENAME);
    }

    public int countLikeBatchNo(final String batchNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE batchNo LIKE '%").append(batchNo).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Exchangermb> selectLikeBatchNo(final String batchNo) {
        return selectLikeBatchNo(batchNo, TABLENAME);
    }

    public List<Exchangermb> selectLikeBatchNo(final String batchNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE batchNo LIKE '%").append(batchNo).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Exchangermb.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeBatchNoPKs(final String batchNo) {
        return selectLikeBatchNoPKs(batchNo, TABLENAME);
    }

    public List<Integer> selectLikeBatchNoPKs(final String batchNo, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE batchNo LIKE '%").append(batchNo).append("%' ORDER BY id ");
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

    public Exchangermb selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Exchangermb selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Exchangermb.class);
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

    public List<Exchangermb> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Exchangermb> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Exchangermb.class);
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

    public int updateByKey(final Exchangermb exchangermb) {
        return updateByKey(exchangermb, TABLENAME);
    }

    public int updateByKey(final Exchangermb exchangermb, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = exchangermb.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), exchangermb);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Exchangermb exchangermb) {
        return asyncUpdate(exchangermb, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Exchangermb exchangermb, final String TABLENAME2) {
        try {

            String _ustr = exchangermb.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, exchangermb);
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

    public int updateMonyeApplyByKey(final double monyeApply, final int id){
        return updateMonyeApplyByKey(monyeApply, id, TABLENAME);
    }

    public int updateMonyeApplyByKey(final double monyeApply, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeApply=monyeApply+:monyeApply WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("monyeApply", monyeApply);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeApplyWithMinByKey(final int id, final double monyeApply, final double _min){
        return updateMonyeApplyWithMinByKey(id, monyeApply, _min, TABLENAME);
    }

    public int updateMonyeApplyWithMinByKey(final int id, final double monyeApply, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeApply = (select case when monyeApply+:monyeApply<=:_min then :_min else monyeApply+:monyeApply end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("monyeApply", monyeApply);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeApplyWithMinInKeys(final List<Integer> keys, final double monyeApply, final double _min){
        return updateMonyeApplyWithMinInKeys(keys, monyeApply, _min, TABLENAME);
    }

    public int updateMonyeApplyWithMinInKeys(final List<Integer> keys, final double monyeApply, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeApply = (select case when monyeApply+:monyeApply<=:_min then :_min else monyeApply+:monyeApply end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("monyeApply", monyeApply);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeApplyWithMaxByKey(final int id, final double monyeApply, final double _max){
        return updateMonyeApplyWithMaxByKey(id, monyeApply, _max, TABLENAME);
    }

    public int updateMonyeApplyWithMaxByKey(final int id, final double monyeApply, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeApply = (select case when monyeApply+:monyeApply>=:_max then :_max else monyeApply+:monyeApply end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("monyeApply", monyeApply);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeApplyWithMaxInKeys(final List<Integer> keys, final double monyeApply, final double _max){
        return updateMonyeApplyWithMaxInKeys(keys, monyeApply, _max, TABLENAME);
    }

    public int updateMonyeApplyWithMaxInKeys(final List<Integer> keys, final double monyeApply, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeApply = (select case when monyeApply+:monyeApply>=:_max then :_max else monyeApply+:monyeApply end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("monyeApply", monyeApply);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeApplyWithMinMaxByKey(final int id, final double monyeApply, final double _min, final double _max){
        return updateMonyeApplyWithMinMaxByKey(id, monyeApply, _min, _max, TABLENAME);
    }

    public int updateMonyeApplyWithMinMaxByKey(final int id, final double monyeApply, final double _min, final double _max, final String TABLENAME2){
        if( monyeApply < 0 ) {
            return updateMonyeApplyWithMinByKey(id, monyeApply, _min, TABLENAME2);
        } else {
            return updateMonyeApplyWithMaxByKey(id, monyeApply, _max, TABLENAME2);
        }
    }

    public int updateMonyeApplyWithMinMaxInKeys(final List<Integer> keys, final double monyeApply, final double _min, final double _max){
        return updateMonyeApplyWithMinMaxInKeys(keys, monyeApply, _min, _max, TABLENAME);
    }

    public int updateMonyeApplyWithMinMaxInKeys(final List<Integer> keys, final double monyeApply, final double _min, final double _max, final String TABLENAME2){
        if( monyeApply < 0 ) {
            return updateMonyeApplyWithMinInKeys(keys, monyeApply, _min, TABLENAME2);
        } else {
            return updateMonyeApplyWithMaxInKeys(keys, monyeApply, _max, TABLENAME2);
        }
    }

    public int updateMonyeRealByKey(final double monyeReal, final int id){
        return updateMonyeRealByKey(monyeReal, id, TABLENAME);
    }

    public int updateMonyeRealByKey(final double monyeReal, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeReal=monyeReal+:monyeReal WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("monyeReal", monyeReal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeRealWithMinByKey(final int id, final double monyeReal, final double _min){
        return updateMonyeRealWithMinByKey(id, monyeReal, _min, TABLENAME);
    }

    public int updateMonyeRealWithMinByKey(final int id, final double monyeReal, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeReal = (select case when monyeReal+:monyeReal<=:_min then :_min else monyeReal+:monyeReal end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("monyeReal", monyeReal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeRealWithMinInKeys(final List<Integer> keys, final double monyeReal, final double _min){
        return updateMonyeRealWithMinInKeys(keys, monyeReal, _min, TABLENAME);
    }

    public int updateMonyeRealWithMinInKeys(final List<Integer> keys, final double monyeReal, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeReal = (select case when monyeReal+:monyeReal<=:_min then :_min else monyeReal+:monyeReal end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("monyeReal", monyeReal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeRealWithMaxByKey(final int id, final double monyeReal, final double _max){
        return updateMonyeRealWithMaxByKey(id, monyeReal, _max, TABLENAME);
    }

    public int updateMonyeRealWithMaxByKey(final int id, final double monyeReal, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeReal = (select case when monyeReal+:monyeReal>=:_max then :_max else monyeReal+:monyeReal end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("monyeReal", monyeReal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeRealWithMaxInKeys(final List<Integer> keys, final double monyeReal, final double _max){
        return updateMonyeRealWithMaxInKeys(keys, monyeReal, _max, TABLENAME);
    }

    public int updateMonyeRealWithMaxInKeys(final List<Integer> keys, final double monyeReal, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monyeReal = (select case when monyeReal+:monyeReal>=:_max then :_max else monyeReal+:monyeReal end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("monyeReal", monyeReal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonyeRealWithMinMaxByKey(final int id, final double monyeReal, final double _min, final double _max){
        return updateMonyeRealWithMinMaxByKey(id, monyeReal, _min, _max, TABLENAME);
    }

    public int updateMonyeRealWithMinMaxByKey(final int id, final double monyeReal, final double _min, final double _max, final String TABLENAME2){
        if( monyeReal < 0 ) {
            return updateMonyeRealWithMinByKey(id, monyeReal, _min, TABLENAME2);
        } else {
            return updateMonyeRealWithMaxByKey(id, monyeReal, _max, TABLENAME2);
        }
    }

    public int updateMonyeRealWithMinMaxInKeys(final List<Integer> keys, final double monyeReal, final double _min, final double _max){
        return updateMonyeRealWithMinMaxInKeys(keys, monyeReal, _min, _max, TABLENAME);
    }

    public int updateMonyeRealWithMinMaxInKeys(final List<Integer> keys, final double monyeReal, final double _min, final double _max, final String TABLENAME2){
        if( monyeReal < 0 ) {
            return updateMonyeRealWithMinInKeys(keys, monyeReal, _min, TABLENAME2);
        } else {
            return updateMonyeRealWithMaxInKeys(keys, monyeReal, _max, TABLENAME2);
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

    public int updateStatusOptByKey(final int statusOpt, final int id){
        return updateStatusOptByKey(statusOpt, id, TABLENAME);
    }

    public int updateStatusOptByKey(final int statusOpt, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusOpt=statusOpt+:statusOpt WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("statusOpt", statusOpt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusOptWithMinByKey(final int id, final int statusOpt, final int _min){
        return updateStatusOptWithMinByKey(id, statusOpt, _min, TABLENAME);
    }

    public int updateStatusOptWithMinByKey(final int id, final int statusOpt, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusOpt = (select case when statusOpt+:statusOpt<=:_min then :_min else statusOpt+:statusOpt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("statusOpt", statusOpt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusOptWithMinInKeys(final List<Integer> keys, final int statusOpt, final int _min){
        return updateStatusOptWithMinInKeys(keys, statusOpt, _min, TABLENAME);
    }

    public int updateStatusOptWithMinInKeys(final List<Integer> keys, final int statusOpt, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusOpt = (select case when statusOpt+:statusOpt<=:_min then :_min else statusOpt+:statusOpt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("statusOpt", statusOpt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusOptWithMaxByKey(final int id, final int statusOpt, final int _max){
        return updateStatusOptWithMaxByKey(id, statusOpt, _max, TABLENAME);
    }

    public int updateStatusOptWithMaxByKey(final int id, final int statusOpt, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusOpt = (select case when statusOpt+:statusOpt>=:_max then :_max else statusOpt+:statusOpt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("statusOpt", statusOpt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusOptWithMaxInKeys(final List<Integer> keys, final int statusOpt, final int _max){
        return updateStatusOptWithMaxInKeys(keys, statusOpt, _max, TABLENAME);
    }

    public int updateStatusOptWithMaxInKeys(final List<Integer> keys, final int statusOpt, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusOpt = (select case when statusOpt+:statusOpt>=:_max then :_max else statusOpt+:statusOpt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("statusOpt", statusOpt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusOptWithMinMaxByKey(final int id, final int statusOpt, final int _min, final int _max){
        return updateStatusOptWithMinMaxByKey(id, statusOpt, _min, _max, TABLENAME);
    }

    public int updateStatusOptWithMinMaxByKey(final int id, final int statusOpt, final int _min, final int _max, final String TABLENAME2){
        if( statusOpt < 0 ) {
            return updateStatusOptWithMinByKey(id, statusOpt, _min, TABLENAME2);
        } else {
            return updateStatusOptWithMaxByKey(id, statusOpt, _max, TABLENAME2);
        }
    }

    public int updateStatusOptWithMinMaxInKeys(final List<Integer> keys, final int statusOpt, final int _min, final int _max){
        return updateStatusOptWithMinMaxInKeys(keys, statusOpt, _min, _max, TABLENAME);
    }

    public int updateStatusOptWithMinMaxInKeys(final List<Integer> keys, final int statusOpt, final int _min, final int _max, final String TABLENAME2){
        if( statusOpt < 0 ) {
            return updateStatusOptWithMinInKeys(keys, statusOpt, _min, TABLENAME2);
        } else {
            return updateStatusOptWithMaxInKeys(keys, statusOpt, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Exchangermb> exchangermbs) {
        return updateByKey(exchangermbs, TABLENAME);
    }

    public int[] updateByKey (final List<Exchangermb> exchangermbs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(exchangermbs == null || exchangermbs.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=:type, makerid=:makerid, nmMaker=:nmMaker, alipay=:alipay, alipayName=:alipayName, reason=:reason, moneyCur=:moneyCur, monyeApply=:monyeApply, monyeReal=:monyeReal, status=:status, batchNo=:batchNo, statusOpt=:statusOpt, createtime=:createtime, content=:content, lasttime=:lasttime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), exchangermbs);
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
                "	`makerid`  INT(11) NOT NULL," +
                "	`nmMaker`  TINYTEXT NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`alipayName`  TINYTEXT NOT NULL," +
                "	`reason`  TEXT NOT NULL," +
                "	`moneyCur`  DOUBLE NOT NULL," +
                "	`monyeApply`  DOUBLE NOT NULL," +
                "	`monyeReal`  DOUBLE NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`batchNo`  VARCHAR(64) NOT NULL," +
                "	`statusOpt`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type_makerid` (`type`, `makerid`)," +
                "	KEY `batchNo` (`batchNo`)" +
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
                "	`makerid`  INT(11) NOT NULL," +
                "	`nmMaker`  TINYTEXT NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`alipayName`  TINYTEXT NOT NULL," +
                "	`reason`  TEXT NOT NULL," +
                "	`moneyCur`  DOUBLE NOT NULL," +
                "	`monyeApply`  DOUBLE NOT NULL," +
                "	`monyeReal`  DOUBLE NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`batchNo`  VARCHAR(64) NOT NULL," +
                "	`statusOpt`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type_makerid` (`type`, `makerid`)," +
                "	KEY `batchNo` (`batchNo`)" +
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
