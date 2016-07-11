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

//learnhall3_design - agent
@SuppressWarnings({"rawtypes", "unchecked"})
public class AgentDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(AgentDAO.class);

    public static final String TABLE = "agent";
    public static String TABLENAME = "agent";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"agid", "accountid", "uname", "code", "province", "city", "seat", "qq", "need", "goodness", "volume", "curmoney", "allmoney", "createtime", "endtime", "status", "examineStatus", "examineDes", "alipay", "isVerifyAlipay"};
    public static String coulmns = "agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay";
    public static String coulmns2 = "accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay";

    public AgentDAO(Connection conn) {
        super(conn);
    }

    public AgentDAO(DataSource ds) {
        super(ds);
    }

    public AgentDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Agent agent) {
        return insert(agent, TABLENAME);
    }

    public int insert(final Agent agent, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            agent.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay) VALUES (:accountid, :uname, :code, :province, :city, :seat, :qq, :need, :goodness, :volume, :curmoney, :allmoney, :createtime, :endtime, :status, :examineStatus, :examineDes, :alipay, :isVerifyAlipay)");
            Map map = super.insert(sql.toString(), agent);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Agent agent) {
        return asyncInsert(agent, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Agent agent, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(agent, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Agent agent) {
        return asyncInsert2(agent, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Agent agent, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(agent, TABLENAME2);
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

    public int insert2(final Agent agent) {
        return insert2(agent, TABLENAME);
    }

    public int insert2(final Agent agent, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            agent.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay) VALUES (:agid, :accountid, :uname, :code, :province, :city, :seat, :qq, :need, :goodness, :volume, :curmoney, :allmoney, :createtime, :endtime, :status, :examineStatus, :examineDes, :alipay, :isVerifyAlipay)");
            Map map = super.insert(sql.toString(), agent);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Agent> agents) {
        return insert(agents, TABLENAME);
    }

    public int[] insert(final List<Agent> agents, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(agents == null || agents.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay) VALUES (:accountid, :uname, :code, :province, :city, :seat, :qq, :need, :goodness, :volume, :curmoney, :allmoney, :createtime, :endtime, :status, :examineStatus, :examineDes, :alipay, :isVerifyAlipay)");
            return super.batchInsert(sql.toString(), agents);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int agid) {
        return deleteByKey(agid, TABLENAME);
    }

    public int deleteByKey(final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int agid) {
        return asyncDeleteByKey(agid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int agid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(agid, TABLENAME2);
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

    public int[] deleteByKey(final int[] agids) {
        return deleteByKey(agids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE agid=:agid");
            List list = newList();
            for (int agid : keys) {
                Map params = newMap();
                params.put("agid", agid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE agid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Agent> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Agent> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Agent agent = beans.get(i);
                int agid = agent.agid;
                sb.append(agid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE agid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Agent> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Agent> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" ORDER BY agid");
            return super.queryForList(sql.toString(), Agent.class);
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
            sql.append("SELECT agid FROM ").append(TABLENAME2).append(" ORDER BY agid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "agid") );
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
            sql.append("SELECT agid, accountid, code FROM ").append(TABLENAME2).append(" ORDER BY agid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Agent> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Agent> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE agid in (").append(str).append(" ) ORDER BY agid");
            return super.queryForList(sql.toString(), Agent.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Agent> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Agent> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE agid in ( :str ) ORDER BY agid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Agent.class);
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
            sql.append("SELECT agid FROM ").append(TABLENAME2).append(" WHERE agid in (").append(str).append(" ) ORDER BY agid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "agid") );
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

    public List<Agent> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Agent> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" ORDER BY agid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Agent.class);
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
            sql.append("SELECT agid FROM ").append(TABLENAME2).append(" ORDER BY agid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "agid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Agent last() {
        return last(TABLENAME);
    }

    public Agent last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" ORDER BY agid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Agent.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Agent> selectGtKeyNum(final int agid, final int _num) {
        return selectGtKeyNum(agid, _num, TABLENAME);
    }

    public List<Agent> selectGtKeyNum(final int agid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE agid > :agid ORDER BY agid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("agid", agid);
            return super.queryForList(sql.toString(), params, Agent.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Agent> selectGtKey(final int agid) {
        return selectGtKey(agid, TABLENAME);
    }

    public List<Agent> selectGtKey(final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE agid > :agid ORDER BY agid");
            Map params = newMap();
            params.put("agid", agid);
            return super.queryForList(sql.toString(), params, Agent.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int agid) {
        return selectGtKeyPKs(agid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT agid FROM ").append(TABLENAME2).append(" WHERE agid > :agid ORDER BY agid");
            Map params = newMap();
            params.put("agid", agid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "agid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Agent selectByKey(final int agid) {
        return selectByKey(agid, TABLENAME);
    }

    public Agent selectByKey(final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE agid = :agid");
            Map params = newMap();
            params.put("agid", agid);
            return super.queryForObject(sql.toString(), params, Agent.class);
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
            sql.append("SELECT MAX(agid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Agent selectByAgid(final Integer agid) {
        return selectByAgid(agid, TABLENAME);
    }

    public Agent selectByAgid(final Integer agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE agid = :agid");
            Map params = newMap();
            params.put("agid", agid);
            return super.queryForObject(sql.toString(), params, Agent.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Agent selectByCode(final String code) {
        return selectByCode(code, TABLENAME);
    }

    public Agent selectByCode(final String code, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE code = :code");
            Map params = newMap();
            params.put("code", code);
            return super.queryForObject(sql.toString(), params, Agent.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeCode(final String code) {
        return countLikeCode(code, TABLENAME);
    }

    public int countLikeCode(final String code, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE code LIKE '%").append(code).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Agent> selectLikeCode(final String code) {
        return selectLikeCode(code, TABLENAME);
    }

    public List<Agent> selectLikeCode(final String code, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE code LIKE '%").append(code).append("%' ORDER BY agid ");
            return super.queryForList(sql.toString(), Agent.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeCodePKs(final String code) {
        return selectLikeCodePKs(code, TABLENAME);
    }

    public List<Integer> selectLikeCodePKs(final String code, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT agid FROM ").append(TABLENAME2).append(" WHERE code LIKE '%").append(code).append("%' ORDER BY agid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "agid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Agent selectByAccountid(final Integer accountid) {
        return selectByAccountid(accountid, TABLENAME);
    }

    public Agent selectByAccountid(final Integer accountid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" WHERE accountid = :accountid");
            Map params = newMap();
            params.put("accountid", accountid);
            return super.queryForObject(sql.toString(), params, Agent.class);
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

    public List<Agent> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Agent> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay FROM ").append(TABLENAME2).append(" ORDER BY agid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Agent.class);
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
            sql.append("SELECT agid FROM ").append(TABLENAME2).append(" ORDER BY agid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "agid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Agent agent) {
        return updateByKey(agent, TABLENAME);
    }

    public int updateByKey(final Agent agent, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = agent.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE agid=:agid");
            return super.update(sql.toString(), agent);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Agent agent) {
        return asyncUpdate(agent, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Agent agent, final String TABLENAME2) {
        try {

            String _ustr = agent.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE agid=:agid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, agent);
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

    public int updateAccountidByKey(final int accountid, final int agid){
        return updateAccountidByKey(accountid, agid, TABLENAME);
    }

    public int updateAccountidByKey(final int accountid, final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=accountid+:accountid WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("accountid", accountid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAccountidWithMinByKey(final int agid, final int accountid, final int _min){
        return updateAccountidWithMinByKey(agid, accountid, _min, TABLENAME);
    }

    public int updateAccountidWithMinByKey(final int agid, final int accountid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid<=:_min then :_min else accountid+:accountid end) WHERE agid in (").append(str).append(")");
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

    public int updateAccountidWithMaxByKey(final int agid, final int accountid, final int _max){
        return updateAccountidWithMaxByKey(agid, accountid, _max, TABLENAME);
    }

    public int updateAccountidWithMaxByKey(final int agid, final int accountid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid = (select case when accountid+:accountid>=:_max then :_max else accountid+:accountid end) WHERE agid in (").append(str).append(")");
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

    public int updateAccountidWithMinMaxByKey(final int agid, final int accountid, final int _min, final int _max){
        return updateAccountidWithMinMaxByKey(agid, accountid, _min, _max, TABLENAME);
    }

    public int updateAccountidWithMinMaxByKey(final int agid, final int accountid, final int _min, final int _max, final String TABLENAME2){
        if( accountid < 0 ) {
            return updateAccountidWithMinByKey(agid, accountid, _min, TABLENAME2);
        } else {
            return updateAccountidWithMaxByKey(agid, accountid, _max, TABLENAME2);
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

    public int updateVolumeByKey(final int volume, final int agid){
        return updateVolumeByKey(volume, agid, TABLENAME);
    }

    public int updateVolumeByKey(final int volume, final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume=volume+:volume WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("volume", volume);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVolumeWithMinByKey(final int agid, final int volume, final int _min){
        return updateVolumeWithMinByKey(agid, volume, _min, TABLENAME);
    }

    public int updateVolumeWithMinByKey(final int agid, final int volume, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume<=:_min then :_min else volume+:volume end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume<=:_min then :_min else volume+:volume end) WHERE agid in (").append(str).append(")");
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

    public int updateVolumeWithMaxByKey(final int agid, final int volume, final int _max){
        return updateVolumeWithMaxByKey(agid, volume, _max, TABLENAME);
    }

    public int updateVolumeWithMaxByKey(final int agid, final int volume, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume>=:_max then :_max else volume+:volume end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET volume = (select case when volume+:volume>=:_max then :_max else volume+:volume end) WHERE agid in (").append(str).append(")");
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

    public int updateVolumeWithMinMaxByKey(final int agid, final int volume, final int _min, final int _max){
        return updateVolumeWithMinMaxByKey(agid, volume, _min, _max, TABLENAME);
    }

    public int updateVolumeWithMinMaxByKey(final int agid, final int volume, final int _min, final int _max, final String TABLENAME2){
        if( volume < 0 ) {
            return updateVolumeWithMinByKey(agid, volume, _min, TABLENAME2);
        } else {
            return updateVolumeWithMaxByKey(agid, volume, _max, TABLENAME2);
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

    public int updateCurmoneyByKey(final double curmoney, final int agid){
        return updateCurmoneyByKey(curmoney, agid, TABLENAME);
    }

    public int updateCurmoneyByKey(final double curmoney, final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curmoney=curmoney+:curmoney WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("curmoney", curmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurmoneyWithMinByKey(final int agid, final double curmoney, final double _min){
        return updateCurmoneyWithMinByKey(agid, curmoney, _min, TABLENAME);
    }

    public int updateCurmoneyWithMinByKey(final int agid, final double curmoney, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curmoney = (select case when curmoney+:curmoney<=:_min then :_min else curmoney+:curmoney end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("_min", _min);
            params.put("curmoney", curmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurmoneyWithMinInKeys(final List<Integer> keys, final double curmoney, final double _min){
        return updateCurmoneyWithMinInKeys(keys, curmoney, _min, TABLENAME);
    }

    public int updateCurmoneyWithMinInKeys(final List<Integer> keys, final double curmoney, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curmoney = (select case when curmoney+:curmoney<=:_min then :_min else curmoney+:curmoney end) WHERE agid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curmoney", curmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurmoneyWithMaxByKey(final int agid, final double curmoney, final double _max){
        return updateCurmoneyWithMaxByKey(agid, curmoney, _max, TABLENAME);
    }

    public int updateCurmoneyWithMaxByKey(final int agid, final double curmoney, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curmoney = (select case when curmoney+:curmoney>=:_max then :_max else curmoney+:curmoney end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("_max", _max);
            params.put("curmoney", curmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurmoneyWithMaxInKeys(final List<Integer> keys, final double curmoney, final double _max){
        return updateCurmoneyWithMaxInKeys(keys, curmoney, _max, TABLENAME);
    }

    public int updateCurmoneyWithMaxInKeys(final List<Integer> keys, final double curmoney, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curmoney = (select case when curmoney+:curmoney>=:_max then :_max else curmoney+:curmoney end) WHERE agid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curmoney", curmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurmoneyWithMinMaxByKey(final int agid, final double curmoney, final double _min, final double _max){
        return updateCurmoneyWithMinMaxByKey(agid, curmoney, _min, _max, TABLENAME);
    }

    public int updateCurmoneyWithMinMaxByKey(final int agid, final double curmoney, final double _min, final double _max, final String TABLENAME2){
        if( curmoney < 0 ) {
            return updateCurmoneyWithMinByKey(agid, curmoney, _min, TABLENAME2);
        } else {
            return updateCurmoneyWithMaxByKey(agid, curmoney, _max, TABLENAME2);
        }
    }

    public int updateCurmoneyWithMinMaxInKeys(final List<Integer> keys, final double curmoney, final double _min, final double _max){
        return updateCurmoneyWithMinMaxInKeys(keys, curmoney, _min, _max, TABLENAME);
    }

    public int updateCurmoneyWithMinMaxInKeys(final List<Integer> keys, final double curmoney, final double _min, final double _max, final String TABLENAME2){
        if( curmoney < 0 ) {
            return updateCurmoneyWithMinInKeys(keys, curmoney, _min, TABLENAME2);
        } else {
            return updateCurmoneyWithMaxInKeys(keys, curmoney, _max, TABLENAME2);
        }
    }

    public int updateAllmoneyByKey(final double allmoney, final int agid){
        return updateAllmoneyByKey(allmoney, agid, TABLENAME);
    }

    public int updateAllmoneyByKey(final double allmoney, final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET allmoney=allmoney+:allmoney WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("allmoney", allmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAllmoneyWithMinByKey(final int agid, final double allmoney, final double _min){
        return updateAllmoneyWithMinByKey(agid, allmoney, _min, TABLENAME);
    }

    public int updateAllmoneyWithMinByKey(final int agid, final double allmoney, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET allmoney = (select case when allmoney+:allmoney<=:_min then :_min else allmoney+:allmoney end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("_min", _min);
            params.put("allmoney", allmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAllmoneyWithMinInKeys(final List<Integer> keys, final double allmoney, final double _min){
        return updateAllmoneyWithMinInKeys(keys, allmoney, _min, TABLENAME);
    }

    public int updateAllmoneyWithMinInKeys(final List<Integer> keys, final double allmoney, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET allmoney = (select case when allmoney+:allmoney<=:_min then :_min else allmoney+:allmoney end) WHERE agid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("allmoney", allmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAllmoneyWithMaxByKey(final int agid, final double allmoney, final double _max){
        return updateAllmoneyWithMaxByKey(agid, allmoney, _max, TABLENAME);
    }

    public int updateAllmoneyWithMaxByKey(final int agid, final double allmoney, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET allmoney = (select case when allmoney+:allmoney>=:_max then :_max else allmoney+:allmoney end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("_max", _max);
            params.put("allmoney", allmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAllmoneyWithMaxInKeys(final List<Integer> keys, final double allmoney, final double _max){
        return updateAllmoneyWithMaxInKeys(keys, allmoney, _max, TABLENAME);
    }

    public int updateAllmoneyWithMaxInKeys(final List<Integer> keys, final double allmoney, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET allmoney = (select case when allmoney+:allmoney>=:_max then :_max else allmoney+:allmoney end) WHERE agid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("allmoney", allmoney);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAllmoneyWithMinMaxByKey(final int agid, final double allmoney, final double _min, final double _max){
        return updateAllmoneyWithMinMaxByKey(agid, allmoney, _min, _max, TABLENAME);
    }

    public int updateAllmoneyWithMinMaxByKey(final int agid, final double allmoney, final double _min, final double _max, final String TABLENAME2){
        if( allmoney < 0 ) {
            return updateAllmoneyWithMinByKey(agid, allmoney, _min, TABLENAME2);
        } else {
            return updateAllmoneyWithMaxByKey(agid, allmoney, _max, TABLENAME2);
        }
    }

    public int updateAllmoneyWithMinMaxInKeys(final List<Integer> keys, final double allmoney, final double _min, final double _max){
        return updateAllmoneyWithMinMaxInKeys(keys, allmoney, _min, _max, TABLENAME);
    }

    public int updateAllmoneyWithMinMaxInKeys(final List<Integer> keys, final double allmoney, final double _min, final double _max, final String TABLENAME2){
        if( allmoney < 0 ) {
            return updateAllmoneyWithMinInKeys(keys, allmoney, _min, TABLENAME2);
        } else {
            return updateAllmoneyWithMaxInKeys(keys, allmoney, _max, TABLENAME2);
        }
    }

    public int updateStatusByKey(final int status, final int agid){
        return updateStatusByKey(status, agid, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int agid, final int status, final int _min){
        return updateStatusWithMinByKey(agid, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int agid, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE agid in (").append(str).append(")");
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

    public int updateStatusWithMaxByKey(final int agid, final int status, final int _max){
        return updateStatusWithMaxByKey(agid, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int agid, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE agid in (").append(str).append(")");
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

    public int updateStatusWithMinMaxByKey(final int agid, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(agid, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int agid, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(agid, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(agid, status, _max, TABLENAME2);
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

    public int updateExamineStatusByKey(final int examineStatus, final int agid){
        return updateExamineStatusByKey(examineStatus, agid, TABLENAME);
    }

    public int updateExamineStatusByKey(final int examineStatus, final int agid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus=examineStatus+:examineStatus WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
            params.put("examineStatus", examineStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamineStatusWithMinByKey(final int agid, final int examineStatus, final int _min){
        return updateExamineStatusWithMinByKey(agid, examineStatus, _min, TABLENAME);
    }

    public int updateExamineStatusWithMinByKey(final int agid, final int examineStatus, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus<=:_min then :_min else examineStatus+:examineStatus end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus<=:_min then :_min else examineStatus+:examineStatus end) WHERE agid in (").append(str).append(")");
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

    public int updateExamineStatusWithMaxByKey(final int agid, final int examineStatus, final int _max){
        return updateExamineStatusWithMaxByKey(agid, examineStatus, _max, TABLENAME);
    }

    public int updateExamineStatusWithMaxByKey(final int agid, final int examineStatus, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus>=:_max then :_max else examineStatus+:examineStatus end) WHERE agid=:agid");
            Map params = newMap();
            params.put("agid", agid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examineStatus = (select case when examineStatus+:examineStatus>=:_max then :_max else examineStatus+:examineStatus end) WHERE agid in (").append(str).append(")");
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

    public int updateExamineStatusWithMinMaxByKey(final int agid, final int examineStatus, final int _min, final int _max){
        return updateExamineStatusWithMinMaxByKey(agid, examineStatus, _min, _max, TABLENAME);
    }

    public int updateExamineStatusWithMinMaxByKey(final int agid, final int examineStatus, final int _min, final int _max, final String TABLENAME2){
        if( examineStatus < 0 ) {
            return updateExamineStatusWithMinByKey(agid, examineStatus, _min, TABLENAME2);
        } else {
            return updateExamineStatusWithMaxByKey(agid, examineStatus, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Agent> agents) {
        return updateByKey(agents, TABLENAME);
    }

    public int[] updateByKey (final List<Agent> agents, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(agents == null || agents.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET accountid=:accountid, uname=:uname, code=:code, province=:province, city=:city, seat=:seat, qq=:qq, need=:need, goodness=:goodness, volume=:volume, curmoney=:curmoney, allmoney=:allmoney, createtime=:createtime, endtime=:endtime, status=:status, examineStatus=:examineStatus, examineDes=:examineDes, alipay=:alipay, isVerifyAlipay=:isVerifyAlipay WHERE agid=:agid");
            return super.batchUpdate2(sql.toString(), agents);
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
                "	`agid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`uname`  VARCHAR(50) NOT NULL," +
                "	`code`  VARCHAR(64) NOT NULL," +
                "	`province`  VARCHAR(32) NOT NULL," +
                "	`city`  VARCHAR(32) NOT NULL," +
                "	`seat`  TINYTEXT NOT NULL," +
                "	`qq`  VARCHAR(128) NOT NULL," +
                "	`need`  VARCHAR(128) NOT NULL," +
                "	`goodness`  TINYTEXT NOT NULL," +
                "	`volume`  INT(11) NOT NULL," +
                "	`curmoney`  DOUBLE NOT NULL," +
                "	`allmoney`  DOUBLE NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`endtime`  DATETIME NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`examineStatus`  INT(11) NOT NULL," +
                "	`examineDes`  TEXT NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`isVerifyAlipay`  BIT(1) NOT NULL," +
                "	PRIMARY KEY (`agid`)," +
                "	UNIQUE KEY `code` (`code`)," +
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
                "	`agid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`accountid`  INT(11) NOT NULL," +
                "	`uname`  VARCHAR(50) NOT NULL," +
                "	`code`  VARCHAR(64) NOT NULL," +
                "	`province`  VARCHAR(32) NOT NULL," +
                "	`city`  VARCHAR(32) NOT NULL," +
                "	`seat`  TINYTEXT NOT NULL," +
                "	`qq`  VARCHAR(128) NOT NULL," +
                "	`need`  VARCHAR(128) NOT NULL," +
                "	`goodness`  TINYTEXT NOT NULL," +
                "	`volume`  INT(11) NOT NULL," +
                "	`curmoney`  DOUBLE NOT NULL," +
                "	`allmoney`  DOUBLE NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`endtime`  DATETIME NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`examineStatus`  INT(11) NOT NULL," +
                "	`examineDes`  TEXT NOT NULL," +
                "	`alipay`  TINYTEXT NOT NULL," +
                "	`isVerifyAlipay`  BIT(1) NOT NULL," +
                "	PRIMARY KEY (`agid`)," +
                "	KEY `code` (`code`)," +
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
