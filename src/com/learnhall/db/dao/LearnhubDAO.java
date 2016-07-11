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

//learnhall3_design - learnhub
@SuppressWarnings({"rawtypes", "unchecked"})
public class LearnhubDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(LearnhubDAO.class);

    public static final String TABLE = "learnhub";
    public static String TABLENAME = "learnhub";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"lhid", "accountid", "name", "type", "codeid", "province", "city", "seat", "qq", "uname", "salesmode", "img4jg", "volume", "moneyAll", "moneyCur", "isselfadmin", "status", "tiku", "quality", "wrong", "examineStatus", "examineDes", "createtime", "imgr4Cover", "descr", "alipay", "isVerifyAlipay", "img4idface", "img4idback"};
    public static String coulmns = "lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback";
    public static String coulmns2 = "accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback";

    public LearnhubDAO(Connection conn) {
        super(conn);
    }

    public LearnhubDAO(DataSource ds) {
        super(ds);
    }

    public LearnhubDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Learnhub learnhub) {
        return insert(learnhub, TABLENAME);
    }

    public int insert(final Learnhub learnhub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            learnhub.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback) VALUES (:accountid, :name, :type, :codeid, :province, :city, :seat, :qq, :uname, :salesmode, :img4jg, :volume, :moneyAll, :moneyCur, :isselfadmin, :status, :tiku, :quality, :wrong, :examineStatus, :examineDes, :createtime, :imgr4Cover, :descr, :alipay, :isVerifyAlipay, :img4idface, :img4idback)");
            Map map = super.insert(sql.toString(), learnhub);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Learnhub learnhub) {
        return asyncInsert(learnhub, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Learnhub learnhub, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(learnhub, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Learnhub learnhub) {
        return asyncInsert2(learnhub, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Learnhub learnhub, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(learnhub, TABLENAME2);
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

    public int insert2(final Learnhub learnhub) {
        return insert2(learnhub, TABLENAME);
    }

    public int insert2(final Learnhub learnhub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            learnhub.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback) VALUES (:lhid, :accountid, :name, :type, :codeid, :province, :city, :seat, :qq, :uname, :salesmode, :img4jg, :volume, :moneyAll, :moneyCur, :isselfadmin, :status, :tiku, :quality, :wrong, :examineStatus, :examineDes, :createtime, :imgr4Cover, :descr, :alipay, :isVerifyAlipay, :img4idface, :img4idback)");
            Map map = super.insert(sql.toString(), learnhub);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Learnhub> learnhubs) {
        return insert(learnhubs, TABLENAME);
    }

    public int[] insert(final List<Learnhub> learnhubs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(learnhubs == null || learnhubs.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback) VALUES (:accountid, :name, :type, :codeid, :province, :city, :seat, :qq, :uname, :salesmode, :img4jg, :volume, :moneyAll, :moneyCur, :isselfadmin, :status, :tiku, :quality, :wrong, :examineStatus, :examineDes, :createtime, :imgr4Cover, :descr, :alipay, :isVerifyAlipay, :img4idface, :img4idback)");
            return super.batchInsert(sql.toString(), learnhubs);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int lhid) {
        return deleteByKey(lhid, TABLENAME);
    }

    public int deleteByKey(final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int lhid) {
        return asyncDeleteByKey(lhid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int lhid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(lhid, TABLENAME2);
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

    public int[] deleteByKey(final int[] lhids) {
        return deleteByKey(lhids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE lhid=:lhid");
            List list = newList();
            for (int lhid : keys) {
                Map params = newMap();
                params.put("lhid", lhid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE lhid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Learnhub> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Learnhub> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Learnhub learnhub = beans.get(i);
                int lhid = learnhub.lhid;
                sb.append(lhid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE lhid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Learnhub> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Learnhub> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" ORDER BY lhid");
            return super.queryForList(sql.toString(), Learnhub.class);
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
            sql.append("SELECT lhid FROM ").append(TABLENAME2).append(" ORDER BY lhid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "lhid") );
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
            sql.append("SELECT lhid, accountid, codeid FROM ").append(TABLENAME2).append(" ORDER BY lhid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Learnhub> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Learnhub> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE lhid in (").append(str).append(" ) ORDER BY lhid");
            return super.queryForList(sql.toString(), Learnhub.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Learnhub> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Learnhub> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE lhid in ( :str ) ORDER BY lhid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Learnhub.class);
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
            sql.append("SELECT lhid FROM ").append(TABLENAME2).append(" WHERE lhid in (").append(str).append(" ) ORDER BY lhid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "lhid") );
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

    public List<Learnhub> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Learnhub> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" ORDER BY lhid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Learnhub.class);
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
            sql.append("SELECT lhid FROM ").append(TABLENAME2).append(" ORDER BY lhid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "lhid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Learnhub last() {
        return last(TABLENAME);
    }

    public Learnhub last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" ORDER BY lhid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Learnhub.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Learnhub> selectGtKeyNum(final int lhid, final int _num) {
        return selectGtKeyNum(lhid, _num, TABLENAME);
    }

    public List<Learnhub> selectGtKeyNum(final int lhid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE lhid > :lhid ORDER BY lhid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("lhid", lhid);
            return super.queryForList(sql.toString(), params, Learnhub.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Learnhub> selectGtKey(final int lhid) {
        return selectGtKey(lhid, TABLENAME);
    }

    public List<Learnhub> selectGtKey(final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE lhid > :lhid ORDER BY lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            return super.queryForList(sql.toString(), params, Learnhub.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int lhid) {
        return selectGtKeyPKs(lhid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT lhid FROM ").append(TABLENAME2).append(" WHERE lhid > :lhid ORDER BY lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "lhid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Learnhub selectByKey(final int lhid) {
        return selectByKey(lhid, TABLENAME);
    }

    public Learnhub selectByKey(final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE lhid = :lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            return super.queryForObject(sql.toString(), params, Learnhub.class);
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
            sql.append("SELECT MAX(lhid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Learnhub selectByLhid(final Integer lhid) {
        return selectByLhid(lhid, TABLENAME);
    }

    public Learnhub selectByLhid(final Integer lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE lhid = :lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            return super.queryForObject(sql.toString(), params, Learnhub.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Learnhub selectByCodeid(final String codeid) {
        return selectByCodeid(codeid, TABLENAME);
    }

    public Learnhub selectByCodeid(final String codeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE codeid = :codeid");
            Map params = newMap();
            params.put("codeid", codeid);
            return super.queryForObject(sql.toString(), params, Learnhub.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeCodeid(final String codeid) {
        return countLikeCodeid(codeid, TABLENAME);
    }

    public int countLikeCodeid(final String codeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE codeid LIKE '%").append(codeid).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Learnhub> selectLikeCodeid(final String codeid) {
        return selectLikeCodeid(codeid, TABLENAME);
    }

    public List<Learnhub> selectLikeCodeid(final String codeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE codeid LIKE '%").append(codeid).append("%' ORDER BY lhid ");
            return super.queryForList(sql.toString(), Learnhub.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeCodeidPKs(final String codeid) {
        return selectLikeCodeidPKs(codeid, TABLENAME);
    }

    public List<Integer> selectLikeCodeidPKs(final String codeid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT lhid FROM ").append(TABLENAME2).append(" WHERE codeid LIKE '%").append(codeid).append("%' ORDER BY lhid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "lhid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Learnhub selectByAccountid(final Integer accountid) {
        return selectByAccountid(accountid, TABLENAME);
    }

    public Learnhub selectByAccountid(final Integer accountid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" WHERE accountid = :accountid");
            Map params = newMap();
            params.put("accountid", accountid);
            return super.queryForObject(sql.toString(), params, Learnhub.class);
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

    public List<Learnhub> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Learnhub> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback FROM ").append(TABLENAME2).append(" ORDER BY lhid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Learnhub.class);
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
            sql.append("SELECT lhid FROM ").append(TABLENAME2).append(" ORDER BY lhid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "lhid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Learnhub learnhub) {
        return updateByKey(learnhub, TABLENAME);
    }

    public int updateByKey(final Learnhub learnhub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = learnhub.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE lhid=:lhid");
            return super.update(sql.toString(), learnhub);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Learnhub learnhub) {
        return asyncUpdate(learnhub, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Learnhub learnhub, final String TABLENAME2) {
        try {

            String _ustr = learnhub.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE lhid=:lhid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, learnhub);
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

    public int updateAccountidByKey(final int accountid, final int lhid){
        return updateAccountidByKey(accountid, lhid, TABLENAME);
    }

    public int updateAccountidByKey(final int accountid, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=accountid+:accountid WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMinByKey(final int lhid, final int accountid, final int _min){
        return updateAccountidWithMinByKey(lhid, accountid, _min, TABLENAME);
    }

    public int updateAccountidWithMinByKey(final int lhid, final int accountid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE lhid in (").append(str).append(")");
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

    public int updateAccountidWithMaxByKey(final int lhid, final int accountid, final int _max){
        return updateAccountidWithMaxByKey(lhid, accountid, _max, TABLENAME);
    }

    public int updateAccountidWithMaxByKey(final int lhid, final int accountid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE lhid in (").append(str).append(")");
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

    public int updateAccountidWithMinMaxByKey(final int lhid, final int accountid, final int _min, final int _max){
        return updateAccountidWithMinMaxByKey(lhid, accountid, _min, _max, TABLENAME);
    }

    public int updateAccountidWithMinMaxByKey(final int lhid, final int accountid, final int _min, final int _max, final String TABLENAME2){
        if( accountid < 0 ) {
            return updateAccountidWithMinByKey(lhid, accountid, _min, TABLENAME2);
        } else {
            return updateAccountidWithMaxByKey(lhid, accountid, _max, TABLENAME2);
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

    public int updateTypeByKey(final int type, final int lhid){
        return updateTypeByKey(type, lhid, TABLENAME);
    }

    public int updateTypeByKey(final int type, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=type+:type WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinByKey(final int lhid, final int type, final int _min){
        return updateTypeWithMinByKey(lhid, type, _min, TABLENAME);
    }

    public int updateTypeWithMinByKey(final int lhid, final int type, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE lhid in (").append(str).append(")");
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

    public int updateTypeWithMaxByKey(final int lhid, final int type, final int _max){
        return updateTypeWithMaxByKey(lhid, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxByKey(final int lhid, final int type, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE lhid in (").append(str).append(")");
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

    public int updateTypeWithMinMaxByKey(final int lhid, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxByKey(lhid, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxByKey(final int lhid, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinByKey(lhid, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxByKey(lhid, type, _max, TABLENAME2);
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

    public int updateSalesmodeByKey(final int salesmode, final int lhid){
        return updateSalesmodeByKey(salesmode, lhid, TABLENAME);
    }

    public int updateSalesmodeByKey(final int salesmode, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET salesmode=salesmode+:salesmode WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("salesmode", salesmode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSalesmodeWithMinByKey(final int lhid, final int salesmode, final int _min){
        return updateSalesmodeWithMinByKey(lhid, salesmode, _min, TABLENAME);
    }

    public int updateSalesmodeWithMinByKey(final int lhid, final int salesmode, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET salesmode = (select case when salesmode+:salesmode<=:_min then :_min else salesmode+:salesmode end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_min", _min);
            params.put("salesmode", salesmode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSalesmodeWithMinInKeys(final List<Integer> keys, final int salesmode, final int _min){
        return updateSalesmodeWithMinInKeys(keys, salesmode, _min, TABLENAME);
    }

    public int updateSalesmodeWithMinInKeys(final List<Integer> keys, final int salesmode, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET salesmode = (select case when salesmode+:salesmode<=:_min then :_min else salesmode+:salesmode end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("salesmode", salesmode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSalesmodeWithMaxByKey(final int lhid, final int salesmode, final int _max){
        return updateSalesmodeWithMaxByKey(lhid, salesmode, _max, TABLENAME);
    }

    public int updateSalesmodeWithMaxByKey(final int lhid, final int salesmode, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET salesmode = (select case when salesmode+:salesmode>=:_max then :_max else salesmode+:salesmode end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_max", _max);
            params.put("salesmode", salesmode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSalesmodeWithMaxInKeys(final List<Integer> keys, final int salesmode, final int _max){
        return updateSalesmodeWithMaxInKeys(keys, salesmode, _max, TABLENAME);
    }

    public int updateSalesmodeWithMaxInKeys(final List<Integer> keys, final int salesmode, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET salesmode = (select case when salesmode+:salesmode>=:_max then :_max else salesmode+:salesmode end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("salesmode", salesmode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSalesmodeWithMinMaxByKey(final int lhid, final int salesmode, final int _min, final int _max){
        return updateSalesmodeWithMinMaxByKey(lhid, salesmode, _min, _max, TABLENAME);
    }

    public int updateSalesmodeWithMinMaxByKey(final int lhid, final int salesmode, final int _min, final int _max, final String TABLENAME2){
        if( salesmode < 0 ) {
            return updateSalesmodeWithMinByKey(lhid, salesmode, _min, TABLENAME2);
        } else {
            return updateSalesmodeWithMaxByKey(lhid, salesmode, _max, TABLENAME2);
        }
    }

    public int updateSalesmodeWithMinMaxInKeys(final List<Integer> keys, final int salesmode, final int _min, final int _max){
        return updateSalesmodeWithMinMaxInKeys(keys, salesmode, _min, _max, TABLENAME);
    }

    public int updateSalesmodeWithMinMaxInKeys(final List<Integer> keys, final int salesmode, final int _min, final int _max, final String TABLENAME2){
        if( salesmode < 0 ) {
            return updateSalesmodeWithMinInKeys(keys, salesmode, _min, TABLENAME2);
        } else {
            return updateSalesmodeWithMaxInKeys(keys, salesmode, _max, TABLENAME2);
        }
    }

    public int updateVolumeByKey(final int volume, final int lhid){
        return updateVolumeByKey(volume, lhid, TABLENAME);
    }

    public int updateVolumeByKey(final int volume, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume=volume+:volume WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("volume", volume);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVolumeWithMinByKey(final int lhid, final int volume, final int _min){
        return updateVolumeWithMinByKey(lhid, volume, _min, TABLENAME);
    }

    public int updateVolumeWithMinByKey(final int lhid, final int volume, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume<=:_min then :_min else volume+:volume end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_min", _min);
            params.put("volume", volume);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVolumeWithMinInKeys(final List<Integer> keys, final int volume, final int _min){
        return updateVolumeWithMinInKeys(keys, volume, _min, TABLENAME);
    }

    public int updateVolumeWithMinInKeys(final List<Integer> keys, final int volume, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume<=:_min then :_min else volume+:volume end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("volume", volume);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVolumeWithMaxByKey(final int lhid, final int volume, final int _max){
        return updateVolumeWithMaxByKey(lhid, volume, _max, TABLENAME);
    }

    public int updateVolumeWithMaxByKey(final int lhid, final int volume, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume>=:_max then :_max else volume+:volume end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_max", _max);
            params.put("volume", volume);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVolumeWithMaxInKeys(final List<Integer> keys, final int volume, final int _max){
        return updateVolumeWithMaxInKeys(keys, volume, _max, TABLENAME);
    }

    public int updateVolumeWithMaxInKeys(final List<Integer> keys, final int volume, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume>=:_max then :_max else volume+:volume end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("volume", volume);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVolumeWithMinMaxByKey(final int lhid, final int volume, final int _min, final int _max){
        return updateVolumeWithMinMaxByKey(lhid, volume, _min, _max, TABLENAME);
    }

    public int updateVolumeWithMinMaxByKey(final int lhid, final int volume, final int _min, final int _max, final String TABLENAME2){
        if( volume < 0 ) {
            return updateVolumeWithMinByKey(lhid, volume, _min, TABLENAME2);
        } else {
            return updateVolumeWithMaxByKey(lhid, volume, _max, TABLENAME2);
        }
    }

    public int updateVolumeWithMinMaxInKeys(final List<Integer> keys, final int volume, final int _min, final int _max){
        return updateVolumeWithMinMaxInKeys(keys, volume, _min, _max, TABLENAME);
    }

    public int updateVolumeWithMinMaxInKeys(final List<Integer> keys, final int volume, final int _min, final int _max, final String TABLENAME2){
        if( volume < 0 ) {
            return updateVolumeWithMinInKeys(keys, volume, _min, TABLENAME2);
        } else {
            return updateVolumeWithMaxInKeys(keys, volume, _max, TABLENAME2);
        }
    }

    public int updateMoneyAllByKey(final double moneyAll, final int lhid){
        return updateMoneyAllByKey(moneyAll, lhid, TABLENAME);
    }

    public int updateMoneyAllByKey(final double moneyAll, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll=moneyAll+:moneyAll WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("moneyAll", moneyAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyAllWithMinByKey(final int lhid, final double moneyAll, final double _min){
        return updateMoneyAllWithMinByKey(lhid, moneyAll, _min, TABLENAME);
    }

    public int updateMoneyAllWithMinByKey(final int lhid, final double moneyAll, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll<=:_min then :_min else moneyAll+:moneyAll end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll<=:_min then :_min else moneyAll+:moneyAll end) WHERE lhid in (").append(str).append(")");
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

    public int updateMoneyAllWithMaxByKey(final int lhid, final double moneyAll, final double _max){
        return updateMoneyAllWithMaxByKey(lhid, moneyAll, _max, TABLENAME);
    }

    public int updateMoneyAllWithMaxByKey(final int lhid, final double moneyAll, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll>=:_max then :_max else moneyAll+:moneyAll end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyAll = (select case when moneyAll+:moneyAll>=:_max then :_max else moneyAll+:moneyAll end) WHERE lhid in (").append(str).append(")");
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

    public int updateMoneyAllWithMinMaxByKey(final int lhid, final double moneyAll, final double _min, final double _max){
        return updateMoneyAllWithMinMaxByKey(lhid, moneyAll, _min, _max, TABLENAME);
    }

    public int updateMoneyAllWithMinMaxByKey(final int lhid, final double moneyAll, final double _min, final double _max, final String TABLENAME2){
        if( moneyAll < 0 ) {
            return updateMoneyAllWithMinByKey(lhid, moneyAll, _min, TABLENAME2);
        } else {
            return updateMoneyAllWithMaxByKey(lhid, moneyAll, _max, TABLENAME2);
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

    public int updateMoneyCurByKey(final double moneyCur, final int lhid){
        return updateMoneyCurByKey(moneyCur, lhid, TABLENAME);
    }

    public int updateMoneyCurByKey(final double moneyCur, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur=moneyCur+:moneyCur WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("moneyCur", moneyCur);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyCurWithMinByKey(final int lhid, final double moneyCur, final double _min){
        return updateMoneyCurWithMinByKey(lhid, moneyCur, _min, TABLENAME);
    }

    public int updateMoneyCurWithMinByKey(final int lhid, final double moneyCur, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur<=:_min then :_min else moneyCur+:moneyCur end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur<=:_min then :_min else moneyCur+:moneyCur end) WHERE lhid in (").append(str).append(")");
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

    public int updateMoneyCurWithMaxByKey(final int lhid, final double moneyCur, final double _max){
        return updateMoneyCurWithMaxByKey(lhid, moneyCur, _max, TABLENAME);
    }

    public int updateMoneyCurWithMaxByKey(final int lhid, final double moneyCur, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur>=:_max then :_max else moneyCur+:moneyCur end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyCur = (select case when moneyCur+:moneyCur>=:_max then :_max else moneyCur+:moneyCur end) WHERE lhid in (").append(str).append(")");
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

    public int updateMoneyCurWithMinMaxByKey(final int lhid, final double moneyCur, final double _min, final double _max){
        return updateMoneyCurWithMinMaxByKey(lhid, moneyCur, _min, _max, TABLENAME);
    }

    public int updateMoneyCurWithMinMaxByKey(final int lhid, final double moneyCur, final double _min, final double _max, final String TABLENAME2){
        if( moneyCur < 0 ) {
            return updateMoneyCurWithMinByKey(lhid, moneyCur, _min, TABLENAME2);
        } else {
            return updateMoneyCurWithMaxByKey(lhid, moneyCur, _max, TABLENAME2);
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

    public int updateStatusByKey(final int status, final int lhid){
        return updateStatusByKey(status, lhid, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int lhid, final int status, final int _min){
        return updateStatusWithMinByKey(lhid, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int lhid, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE lhid in (").append(str).append(")");
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

    public int updateStatusWithMaxByKey(final int lhid, final int status, final int _max){
        return updateStatusWithMaxByKey(lhid, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int lhid, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE lhid in (").append(str).append(")");
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

    public int updateStatusWithMinMaxByKey(final int lhid, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(lhid, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int lhid, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(lhid, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(lhid, status, _max, TABLENAME2);
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

    public int updateTikuByKey(final int tiku, final int lhid){
        return updateTikuByKey(tiku, lhid, TABLENAME);
    }

    public int updateTikuByKey(final int tiku, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET tiku=tiku+:tiku WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("tiku", tiku);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTikuWithMinByKey(final int lhid, final int tiku, final int _min){
        return updateTikuWithMinByKey(lhid, tiku, _min, TABLENAME);
    }

    public int updateTikuWithMinByKey(final int lhid, final int tiku, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET tiku = (select case when tiku+:tiku<=:_min then :_min else tiku+:tiku end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_min", _min);
            params.put("tiku", tiku);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTikuWithMinInKeys(final List<Integer> keys, final int tiku, final int _min){
        return updateTikuWithMinInKeys(keys, tiku, _min, TABLENAME);
    }

    public int updateTikuWithMinInKeys(final List<Integer> keys, final int tiku, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET tiku = (select case when tiku+:tiku<=:_min then :_min else tiku+:tiku end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("tiku", tiku);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTikuWithMaxByKey(final int lhid, final int tiku, final int _max){
        return updateTikuWithMaxByKey(lhid, tiku, _max, TABLENAME);
    }

    public int updateTikuWithMaxByKey(final int lhid, final int tiku, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET tiku = (select case when tiku+:tiku>=:_max then :_max else tiku+:tiku end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_max", _max);
            params.put("tiku", tiku);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTikuWithMaxInKeys(final List<Integer> keys, final int tiku, final int _max){
        return updateTikuWithMaxInKeys(keys, tiku, _max, TABLENAME);
    }

    public int updateTikuWithMaxInKeys(final List<Integer> keys, final int tiku, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET tiku = (select case when tiku+:tiku>=:_max then :_max else tiku+:tiku end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("tiku", tiku);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTikuWithMinMaxByKey(final int lhid, final int tiku, final int _min, final int _max){
        return updateTikuWithMinMaxByKey(lhid, tiku, _min, _max, TABLENAME);
    }

    public int updateTikuWithMinMaxByKey(final int lhid, final int tiku, final int _min, final int _max, final String TABLENAME2){
        if( tiku < 0 ) {
            return updateTikuWithMinByKey(lhid, tiku, _min, TABLENAME2);
        } else {
            return updateTikuWithMaxByKey(lhid, tiku, _max, TABLENAME2);
        }
    }

    public int updateTikuWithMinMaxInKeys(final List<Integer> keys, final int tiku, final int _min, final int _max){
        return updateTikuWithMinMaxInKeys(keys, tiku, _min, _max, TABLENAME);
    }

    public int updateTikuWithMinMaxInKeys(final List<Integer> keys, final int tiku, final int _min, final int _max, final String TABLENAME2){
        if( tiku < 0 ) {
            return updateTikuWithMinInKeys(keys, tiku, _min, TABLENAME2);
        } else {
            return updateTikuWithMaxInKeys(keys, tiku, _max, TABLENAME2);
        }
    }

    public int updateQualityByKey(final int quality, final int lhid){
        return updateQualityByKey(quality, lhid, TABLENAME);
    }

    public int updateQualityByKey(final int quality, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET quality=quality+:quality WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("quality", quality);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQualityWithMinByKey(final int lhid, final int quality, final int _min){
        return updateQualityWithMinByKey(lhid, quality, _min, TABLENAME);
    }

    public int updateQualityWithMinByKey(final int lhid, final int quality, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET quality = (select case when quality+:quality<=:_min then :_min else quality+:quality end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_min", _min);
            params.put("quality", quality);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQualityWithMinInKeys(final List<Integer> keys, final int quality, final int _min){
        return updateQualityWithMinInKeys(keys, quality, _min, TABLENAME);
    }

    public int updateQualityWithMinInKeys(final List<Integer> keys, final int quality, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET quality = (select case when quality+:quality<=:_min then :_min else quality+:quality end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("quality", quality);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQualityWithMaxByKey(final int lhid, final int quality, final int _max){
        return updateQualityWithMaxByKey(lhid, quality, _max, TABLENAME);
    }

    public int updateQualityWithMaxByKey(final int lhid, final int quality, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET quality = (select case when quality+:quality>=:_max then :_max else quality+:quality end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("_max", _max);
            params.put("quality", quality);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQualityWithMaxInKeys(final List<Integer> keys, final int quality, final int _max){
        return updateQualityWithMaxInKeys(keys, quality, _max, TABLENAME);
    }

    public int updateQualityWithMaxInKeys(final List<Integer> keys, final int quality, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET quality = (select case when quality+:quality>=:_max then :_max else quality+:quality end) WHERE lhid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("quality", quality);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateQualityWithMinMaxByKey(final int lhid, final int quality, final int _min, final int _max){
        return updateQualityWithMinMaxByKey(lhid, quality, _min, _max, TABLENAME);
    }

    public int updateQualityWithMinMaxByKey(final int lhid, final int quality, final int _min, final int _max, final String TABLENAME2){
        if( quality < 0 ) {
            return updateQualityWithMinByKey(lhid, quality, _min, TABLENAME2);
        } else {
            return updateQualityWithMaxByKey(lhid, quality, _max, TABLENAME2);
        }
    }

    public int updateQualityWithMinMaxInKeys(final List<Integer> keys, final int quality, final int _min, final int _max){
        return updateQualityWithMinMaxInKeys(keys, quality, _min, _max, TABLENAME);
    }

    public int updateQualityWithMinMaxInKeys(final List<Integer> keys, final int quality, final int _min, final int _max, final String TABLENAME2){
        if( quality < 0 ) {
            return updateQualityWithMinInKeys(keys, quality, _min, TABLENAME2);
        } else {
            return updateQualityWithMaxInKeys(keys, quality, _max, TABLENAME2);
        }
    }

    public int updateWrongByKey(final int wrong, final int lhid){
        return updateWrongByKey(wrong, lhid, TABLENAME);
    }

    public int updateWrongByKey(final int wrong, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong=wrong+:wrong WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("wrong", wrong);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWrongWithMinByKey(final int lhid, final int wrong, final int _min){
        return updateWrongWithMinByKey(lhid, wrong, _min, TABLENAME);
    }

    public int updateWrongWithMinByKey(final int lhid, final int wrong, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong<=:_min then :_min else wrong+:wrong end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong<=:_min then :_min else wrong+:wrong end) WHERE lhid in (").append(str).append(")");
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

    public int updateWrongWithMaxByKey(final int lhid, final int wrong, final int _max){
        return updateWrongWithMaxByKey(lhid, wrong, _max, TABLENAME);
    }

    public int updateWrongWithMaxByKey(final int lhid, final int wrong, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong>=:_max then :_max else wrong+:wrong end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wrong = (select case when wrong+:wrong>=:_max then :_max else wrong+:wrong end) WHERE lhid in (").append(str).append(")");
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

    public int updateWrongWithMinMaxByKey(final int lhid, final int wrong, final int _min, final int _max){
        return updateWrongWithMinMaxByKey(lhid, wrong, _min, _max, TABLENAME);
    }

    public int updateWrongWithMinMaxByKey(final int lhid, final int wrong, final int _min, final int _max, final String TABLENAME2){
        if( wrong < 0 ) {
            return updateWrongWithMinByKey(lhid, wrong, _min, TABLENAME2);
        } else {
            return updateWrongWithMaxByKey(lhid, wrong, _max, TABLENAME2);
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

    public int updateExamineStatusByKey(final int examineStatus, final int lhid){
        return updateExamineStatusByKey(examineStatus, lhid, TABLENAME);
    }

    public int updateExamineStatusByKey(final int examineStatus, final int lhid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus=examineStatus+:examineStatus WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMinByKey(final int lhid, final int examineStatus, final int _min){
        return updateExamineStatusWithMinByKey(lhid, examineStatus, _min, TABLENAME);
    }

    public int updateExamineStatusWithMinByKey(final int lhid, final int examineStatus, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus<=:_min then :_min else examineStatus+:examineStatus end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus<=:_min then :_min else examineStatus+:examineStatus end) WHERE lhid in (").append(str).append(")");
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

    public int updateExamineStatusWithMaxByKey(final int lhid, final int examineStatus, final int _max){
        return updateExamineStatusWithMaxByKey(lhid, examineStatus, _max, TABLENAME);
    }

    public int updateExamineStatusWithMaxByKey(final int lhid, final int examineStatus, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus>=:_max then :_max else examineStatus+:examineStatus end) WHERE lhid=:lhid");
            Map params = newMap();
            params.put("lhid", lhid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus>=:_max then :_max else examineStatus+:examineStatus end) WHERE lhid in (").append(str).append(")");
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

    public int updateExamineStatusWithMinMaxByKey(final int lhid, final int examineStatus, final int _min, final int _max){
        return updateExamineStatusWithMinMaxByKey(lhid, examineStatus, _min, _max, TABLENAME);
    }

    public int updateExamineStatusWithMinMaxByKey(final int lhid, final int examineStatus, final int _min, final int _max, final String TABLENAME2){
        if( examineStatus < 0 ) {
            return updateExamineStatusWithMinByKey(lhid, examineStatus, _min, TABLENAME2);
        } else {
            return updateExamineStatusWithMaxByKey(lhid, examineStatus, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Learnhub> learnhubs) {
        return updateByKey(learnhubs, TABLENAME);
    }

    public int[] updateByKey (final List<Learnhub> learnhubs, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(learnhubs == null || learnhubs.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=:accountid, name=:name, type=:type, codeid=:codeid, province=:province, city=:city, seat=:seat, qq=:qq, uname=:uname, salesmode=:salesmode, img4jg=:img4jg, volume=:volume, moneyAll=:moneyAll, moneyCur=:moneyCur, isselfadmin=:isselfadmin, status=:status, tiku=:tiku, quality=:quality, wrong=:wrong, examineStatus=:examineStatus, examineDes=:examineDes, createtime=:createtime, imgr4Cover=:imgr4Cover, descr=:descr, alipay=:alipay, isVerifyAlipay=:isVerifyAlipay, img4idface=:img4idface, img4idback=:img4idback WHERE lhid=:lhid");
            return super.batchUpdate2(sql.toString(), learnhubs);
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
                "	`lhid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`name`  VARCHAR(64) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`codeid`  VARCHAR(20) NOT NULL," +
                "	`province`  VARCHAR(32) NOT NULL," +
                "	`city`  VARCHAR(32) NOT NULL," +
                "	`seat`  TINYTEXT NOT NULL," +
                "	`qq`  VARCHAR(128) NOT NULL," +
                "	`uname`  VARCHAR(50) NOT NULL," +
                "	`salesmode`  INT(11) NOT NULL," +
                "	`img4jg`  TINYTEXT NOT NULL," +
                "	`volume`  INT(11) NOT NULL," +
                "	`moneyAll`  DOUBLE NOT NULL," +
                "	`moneyCur`  DOUBLE NOT NULL," +
                "	`isselfadmin`  BIT(1) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`tiku`  INT(11) NOT NULL," +
                "	`quality`  INT(11) NOT NULL," +
                "	`wrong`  INT(11) NOT NULL," +
                "	`examineStatus`  INT(11) NOT NULL," +
                "	`examineDes`  TEXT NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`imgr4Cover`  TINYTEXT NOT NULL," +
                "	`descr`  TEXT NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`isVerifyAlipay`  BIT(1) NOT NULL," +
                "	`img4idface`  TINYTEXT NOT NULL," +
                "	`img4idback`  TINYTEXT NOT NULL," +
                "	PRIMARY KEY (`lhid`)," +
                "	UNIQUE KEY `codeid` (`codeid`)," +
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
                "	`lhid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`name`  VARCHAR(64) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`codeid`  VARCHAR(20) NOT NULL," +
                "	`province`  VARCHAR(32) NOT NULL," +
                "	`city`  VARCHAR(32) NOT NULL," +
                "	`seat`  TINYTEXT NOT NULL," +
                "	`qq`  VARCHAR(128) NOT NULL," +
                "	`uname`  VARCHAR(50) NOT NULL," +
                "	`salesmode`  INT(11) NOT NULL," +
                "	`img4jg`  TINYTEXT NOT NULL," +
                "	`volume`  INT(11) NOT NULL," +
                "	`moneyAll`  DOUBLE NOT NULL," +
                "	`moneyCur`  DOUBLE NOT NULL," +
                "	`isselfadmin`  BIT(1) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`tiku`  INT(11) NOT NULL," +
                "	`quality`  INT(11) NOT NULL," +
                "	`wrong`  INT(11) NOT NULL," +
                "	`examineStatus`  INT(11) NOT NULL," +
                "	`examineDes`  TEXT NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`imgr4Cover`  TINYTEXT NOT NULL," +
                "	`descr`  TEXT NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`isVerifyAlipay`  BIT(1) NOT NULL," +
                "	`img4idface`  TINYTEXT NOT NULL," +
                "	`img4idback`  TINYTEXT NOT NULL," +
                "	PRIMARY KEY (`lhid`)," +
                "	KEY `codeid` (`codeid`)," +
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
