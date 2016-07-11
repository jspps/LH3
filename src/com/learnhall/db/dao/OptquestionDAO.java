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

//learnhall3_design - optquestion
@SuppressWarnings({"rawtypes", "unchecked"})
public class OptquestionDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(OptquestionDAO.class);

    public static final String TABLE = "optquestion";
    public static String TABLENAME = "optquestion";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"optid", "examid", "type", "content", "right_2", "analyse", "voiceurl", "videourl", "position", "status", "createtime", "examcatalogid", "answernum", "gid", "imgPic"};
    public static String coulmns = "optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic";
    public static String coulmns2 = "examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic";

    public OptquestionDAO(Connection conn) {
        super(conn);
    }

    public OptquestionDAO(DataSource ds) {
        super(ds);
    }

    public OptquestionDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Optquestion optquestion) {
        return insert(optquestion, TABLENAME);
    }

    public int insert(final Optquestion optquestion, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            optquestion.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic) VALUES (:examid, :type, :content, :right_2, :analyse, :voiceurl, :videourl, :position, :status, :createtime, :examcatalogid, :answernum, :gid, :imgPic)");
            Map map = super.insert(sql.toString(), optquestion);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Optquestion optquestion) {
        return asyncInsert(optquestion, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Optquestion optquestion, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(optquestion, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Optquestion optquestion) {
        return asyncInsert2(optquestion, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Optquestion optquestion, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(optquestion, TABLENAME2);
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

    public int insert2(final Optquestion optquestion) {
        return insert2(optquestion, TABLENAME);
    }

    public int insert2(final Optquestion optquestion, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            optquestion.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic) VALUES (:optid, :examid, :type, :content, :right_2, :analyse, :voiceurl, :videourl, :position, :status, :createtime, :examcatalogid, :answernum, :gid, :imgPic)");
            Map map = super.insert(sql.toString(), optquestion);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Optquestion> optquestions) {
        return insert(optquestions, TABLENAME);
    }

    public int[] insert(final List<Optquestion> optquestions, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(optquestions == null || optquestions.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic) VALUES (:examid, :type, :content, :right_2, :analyse, :voiceurl, :videourl, :position, :status, :createtime, :examcatalogid, :answernum, :gid, :imgPic)");
            return super.batchInsert(sql.toString(), optquestions);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int optid) {
        return deleteByKey(optid, TABLENAME);
    }

    public int deleteByKey(final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int optid) {
        return asyncDeleteByKey(optid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int optid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(optid, TABLENAME2);
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

    public int[] deleteByKey(final int[] optids) {
        return deleteByKey(optids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE optid=:optid");
            List list = newList();
            for (int optid : keys) {
                Map params = newMap();
                params.put("optid", optid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE optid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Optquestion> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Optquestion> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Optquestion optquestion = beans.get(i);
                int optid = optquestion.optid;
                sb.append(optid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE optid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Optquestion> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" ORDER BY optid");
            return super.queryForList(sql.toString(), Optquestion.class);
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
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" ORDER BY optid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
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
            sql.append("SELECT optid, examid, type, examcatalogid FROM ").append(TABLENAME2).append(" ORDER BY optid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Optquestion> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE optid in (").append(str).append(" ) ORDER BY optid");
            return super.queryForList(sql.toString(), Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Optquestion> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE optid in ( :str ) ORDER BY optid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Optquestion.class);
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
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE optid in (").append(str).append(" ) ORDER BY optid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
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

    public List<Optquestion> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Optquestion> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" ORDER BY optid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Optquestion.class);
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
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" ORDER BY optid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Optquestion last() {
        return last(TABLENAME);
    }

    public Optquestion last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" ORDER BY optid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Optquestion.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectGtKeyNum(final int optid, final int _num) {
        return selectGtKeyNum(optid, _num, TABLENAME);
    }

    public List<Optquestion> selectGtKeyNum(final int optid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE optid > :optid ORDER BY optid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectGtKey(final int optid) {
        return selectGtKey(optid, TABLENAME);
    }

    public List<Optquestion> selectGtKey(final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE optid > :optid ORDER BY optid");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int optid) {
        return selectGtKeyPKs(optid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE optid > :optid ORDER BY optid");
            Map params = newMap();
            params.put("optid", optid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Optquestion selectByKey(final int optid) {
        return selectByKey(optid, TABLENAME);
    }

    public Optquestion selectByKey(final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE optid = :optid");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForObject(sql.toString(), params, Optquestion.class);
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
            sql.append("SELECT MAX(optid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Optquestion selectByOptid(final Integer optid) {
        return selectByOptid(optid, TABLENAME);
    }

    public Optquestion selectByOptid(final Integer optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE optid = :optid");
            Map params = newMap();
            params.put("optid", optid);
            return super.queryForObject(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByExamidExamcatalogid(final Integer examid, Integer examcatalogid) {
        return  countByExamidExamcatalogid(examid, examcatalogid, TABLENAME);
    }

    public int countByExamidExamcatalogid(final Integer examid, Integer examcatalogid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE examid=:examid AND examcatalogid=:examcatalogid ");
            Map params = newMap();
            params.put("examid", examid);
            params.put("examcatalogid", examcatalogid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectByExamidExamcatalogid(final Integer examid, Integer examcatalogid) {
        return selectByExamidExamcatalogid(examid, examcatalogid, TABLENAME);
    }

    public List<Optquestion> selectByExamidExamcatalogid(final Integer examid, Integer examcatalogid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE examid=:examid AND examcatalogid=:examcatalogid ORDER BY optid ");
            Map params = newMap();
            params.put("examid", examid);
            params.put("examcatalogid", examcatalogid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExamidExamcatalogidPKs(final Integer examid, Integer examcatalogid) {
        return selectByExamidExamcatalogidPKs(examid, examcatalogid, TABLENAME);
    }

    public List<Integer> selectByExamidExamcatalogidPKs(final Integer examid, Integer examcatalogid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE examid=:examid AND examcatalogid=:examcatalogid ORDER BY optid ");
            Map params = newMap();
            params.put("examid", examid);
            params.put("examcatalogid", examcatalogid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectPageByExamidExamcatalogid(final Integer examid, Integer examcatalogid, final int begin, final int num) {
        return selectPageByExamidExamcatalogid(examid, examcatalogid, begin, num, TABLENAME);
    }

    public List<Optquestion> selectPageByExamidExamcatalogid(final Integer examid, Integer examcatalogid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE examid=:examid AND examcatalogid=:examcatalogid ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            params.put("examcatalogid", examcatalogid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExamidExamcatalogidPKs(final Integer examid, Integer examcatalogid, final int begin, final int num) {
        return selectPageByExamidExamcatalogidPKs(examid, examcatalogid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExamidExamcatalogidPKs(final Integer examid, Integer examcatalogid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE examid=:examid AND examcatalogid=:examcatalogid ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            params.put("examcatalogid", examcatalogid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByExamcatalogid(final Integer examcatalogid) {
        return countByExamcatalogid(examcatalogid, TABLENAME);
    }

    public int countByExamcatalogid(final Integer examcatalogid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE examcatalogid = :examcatalogid ");
            Map params = newMap();
            params.put("examcatalogid", examcatalogid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectByExamcatalogid(final Integer examcatalogid) {
        return selectByExamcatalogid(examcatalogid, TABLENAME);
    }

    public List<Optquestion> selectByExamcatalogid(final Integer examcatalogid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE examcatalogid = :examcatalogid ORDER BY optid ");
            Map params = newMap();
            params.put("examcatalogid", examcatalogid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExamcatalogidPKs(final Integer examcatalogid) {
        return selectByExamcatalogidPKs(examcatalogid, TABLENAME);
    }

    public List<Integer> selectByExamcatalogidPKs(final Integer examcatalogid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE examcatalogid = :examcatalogid ORDER BY optid ");
            Map params = newMap();
            params.put("examcatalogid", examcatalogid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectPageByExamcatalogid(final Integer examcatalogid, final int begin, final int num) {
        return selectPageByExamcatalogid(examcatalogid, begin, num, TABLENAME);
    }

    public List<Optquestion> selectPageByExamcatalogid(final Integer examcatalogid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE examcatalogid = :examcatalogid ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examcatalogid", examcatalogid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExamcatalogidPKs(final Integer examcatalogid, final int begin, final int num) {
        return selectPageByExamcatalogidPKs(examcatalogid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExamcatalogidPKs(final Integer examcatalogid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE examcatalogid = :examcatalogid ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examcatalogid", examcatalogid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByType(final Integer type) {
        return countByType(type, TABLENAME);
    }

    public int countByType(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type = :type ");
            Map params = newMap();
            params.put("type", type);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectByType(final Integer type) {
        return selectByType(type, TABLENAME);
    }

    public List<Optquestion> selectByType(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY optid ");
            Map params = newMap();
            params.put("type", type);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypePKs(final Integer type) {
        return selectByTypePKs(type, TABLENAME);
    }

    public List<Integer> selectByTypePKs(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY optid ");
            Map params = newMap();
            params.put("type", type);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectPageByType(final Integer type, final int begin, final int num) {
        return selectPageByType(type, begin, num, TABLENAME);
    }

    public List<Optquestion> selectPageByType(final Integer type, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypePKs(final Integer type, final int begin, final int num) {
        return selectPageByTypePKs(type, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypePKs(final Integer type, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByExamid(final Integer examid) {
        return countByExamid(examid, TABLENAME);
    }

    public int countByExamid(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE examid = :examid ");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectByExamid(final Integer examid) {
        return selectByExamid(examid, TABLENAME);
    }

    public List<Optquestion> selectByExamid(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY optid ");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByExamidPKs(final Integer examid) {
        return selectByExamidPKs(examid, TABLENAME);
    }

    public List<Integer> selectByExamidPKs(final Integer examid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY optid ");
            Map params = newMap();
            params.put("examid", examid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Optquestion> selectPageByExamid(final Integer examid, final int begin, final int num) {
        return selectPageByExamid(examid, begin, num, TABLENAME);
    }

    public List<Optquestion> selectPageByExamid(final Integer examid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            return super.queryForList(sql.toString(), params, Optquestion.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByExamidPKs(final Integer examid, final int begin, final int num) {
        return selectPageByExamidPKs(examid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByExamidPKs(final Integer examid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" WHERE examid = :examid ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("examid", examid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
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

    public List<Optquestion> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Optquestion> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic FROM ").append(TABLENAME2).append(" ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Optquestion.class);
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
            sql.append("SELECT optid FROM ").append(TABLENAME2).append(" ORDER BY optid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "optid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Optquestion optquestion) {
        return updateByKey(optquestion, TABLENAME);
    }

    public int updateByKey(final Optquestion optquestion, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = optquestion.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE optid=:optid");
            return super.update(sql.toString(), optquestion);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Optquestion optquestion) {
        return asyncUpdate(optquestion, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Optquestion optquestion, final String TABLENAME2) {
        try {

            String _ustr = optquestion.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE optid=:optid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, optquestion);
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

    public int updateExamidByKey(final int examid, final int optid){
        return updateExamidByKey(examid, optid, TABLENAME);
    }

    public int updateExamidByKey(final int examid, final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid=examid+:examid WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMinByKey(final int optid, final int examid, final int _min){
        return updateExamidWithMinByKey(optid, examid, _min, TABLENAME);
    }

    public int updateExamidWithMinByKey(final int optid, final int examid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid<=:_min then :_min else examid+:examid end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_min", _min);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMinInKeys(final List<Integer> keys, final int examid, final int _min){
        return updateExamidWithMinInKeys(keys, examid, _min, TABLENAME);
    }

    public int updateExamidWithMinInKeys(final List<Integer> keys, final int examid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid<=:_min then :_min else examid+:examid end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMaxByKey(final int optid, final int examid, final int _max){
        return updateExamidWithMaxByKey(optid, examid, _max, TABLENAME);
    }

    public int updateExamidWithMaxByKey(final int optid, final int examid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid>=:_max then :_max else examid+:examid end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_max", _max);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMaxInKeys(final List<Integer> keys, final int examid, final int _max){
        return updateExamidWithMaxInKeys(keys, examid, _max, TABLENAME);
    }

    public int updateExamidWithMaxInKeys(final List<Integer> keys, final int examid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid = (select case when examid+:examid>=:_max then :_max else examid+:examid end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examid", examid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamidWithMinMaxByKey(final int optid, final int examid, final int _min, final int _max){
        return updateExamidWithMinMaxByKey(optid, examid, _min, _max, TABLENAME);
    }

    public int updateExamidWithMinMaxByKey(final int optid, final int examid, final int _min, final int _max, final String TABLENAME2){
        if( examid < 0 ) {
            return updateExamidWithMinByKey(optid, examid, _min, TABLENAME2);
        } else {
            return updateExamidWithMaxByKey(optid, examid, _max, TABLENAME2);
        }
    }

    public int updateExamidWithMinMaxInKeys(final List<Integer> keys, final int examid, final int _min, final int _max){
        return updateExamidWithMinMaxInKeys(keys, examid, _min, _max, TABLENAME);
    }

    public int updateExamidWithMinMaxInKeys(final List<Integer> keys, final int examid, final int _min, final int _max, final String TABLENAME2){
        if( examid < 0 ) {
            return updateExamidWithMinInKeys(keys, examid, _min, TABLENAME2);
        } else {
            return updateExamidWithMaxInKeys(keys, examid, _max, TABLENAME2);
        }
    }

    public int updateTypeByKey(final int type, final int optid){
        return updateTypeByKey(type, optid, TABLENAME);
    }

    public int updateTypeByKey(final int type, final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=type+:type WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinByKey(final int optid, final int type, final int _min){
        return updateTypeWithMinByKey(optid, type, _min, TABLENAME);
    }

    public int updateTypeWithMinByKey(final int optid, final int type, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE optid in (").append(str).append(")");
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

    public int updateTypeWithMaxByKey(final int optid, final int type, final int _max){
        return updateTypeWithMaxByKey(optid, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxByKey(final int optid, final int type, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE optid in (").append(str).append(")");
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

    public int updateTypeWithMinMaxByKey(final int optid, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxByKey(optid, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxByKey(final int optid, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinByKey(optid, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxByKey(optid, type, _max, TABLENAME2);
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

    public int updateStatusByKey(final int status, final int optid){
        return updateStatusByKey(status, optid, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int optid, final int status, final int _min){
        return updateStatusWithMinByKey(optid, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int optid, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE optid in (").append(str).append(")");
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

    public int updateStatusWithMaxByKey(final int optid, final int status, final int _max){
        return updateStatusWithMaxByKey(optid, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int optid, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE optid in (").append(str).append(")");
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

    public int updateStatusWithMinMaxByKey(final int optid, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(optid, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int optid, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(optid, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(optid, status, _max, TABLENAME2);
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

    public int updateExamcatalogidByKey(final int examcatalogid, final int optid){
        return updateExamcatalogidByKey(examcatalogid, optid, TABLENAME);
    }

    public int updateExamcatalogidByKey(final int examcatalogid, final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examcatalogid=examcatalogid+:examcatalogid WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("examcatalogid", examcatalogid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamcatalogidWithMinByKey(final int optid, final int examcatalogid, final int _min){
        return updateExamcatalogidWithMinByKey(optid, examcatalogid, _min, TABLENAME);
    }

    public int updateExamcatalogidWithMinByKey(final int optid, final int examcatalogid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examcatalogid = (select case when examcatalogid+:examcatalogid<=:_min then :_min else examcatalogid+:examcatalogid end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_min", _min);
            params.put("examcatalogid", examcatalogid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamcatalogidWithMinInKeys(final List<Integer> keys, final int examcatalogid, final int _min){
        return updateExamcatalogidWithMinInKeys(keys, examcatalogid, _min, TABLENAME);
    }

    public int updateExamcatalogidWithMinInKeys(final List<Integer> keys, final int examcatalogid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examcatalogid = (select case when examcatalogid+:examcatalogid<=:_min then :_min else examcatalogid+:examcatalogid end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("examcatalogid", examcatalogid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamcatalogidWithMaxByKey(final int optid, final int examcatalogid, final int _max){
        return updateExamcatalogidWithMaxByKey(optid, examcatalogid, _max, TABLENAME);
    }

    public int updateExamcatalogidWithMaxByKey(final int optid, final int examcatalogid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examcatalogid = (select case when examcatalogid+:examcatalogid>=:_max then :_max else examcatalogid+:examcatalogid end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_max", _max);
            params.put("examcatalogid", examcatalogid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamcatalogidWithMaxInKeys(final List<Integer> keys, final int examcatalogid, final int _max){
        return updateExamcatalogidWithMaxInKeys(keys, examcatalogid, _max, TABLENAME);
    }

    public int updateExamcatalogidWithMaxInKeys(final List<Integer> keys, final int examcatalogid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examcatalogid = (select case when examcatalogid+:examcatalogid>=:_max then :_max else examcatalogid+:examcatalogid end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("examcatalogid", examcatalogid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExamcatalogidWithMinMaxByKey(final int optid, final int examcatalogid, final int _min, final int _max){
        return updateExamcatalogidWithMinMaxByKey(optid, examcatalogid, _min, _max, TABLENAME);
    }

    public int updateExamcatalogidWithMinMaxByKey(final int optid, final int examcatalogid, final int _min, final int _max, final String TABLENAME2){
        if( examcatalogid < 0 ) {
            return updateExamcatalogidWithMinByKey(optid, examcatalogid, _min, TABLENAME2);
        } else {
            return updateExamcatalogidWithMaxByKey(optid, examcatalogid, _max, TABLENAME2);
        }
    }

    public int updateExamcatalogidWithMinMaxInKeys(final List<Integer> keys, final int examcatalogid, final int _min, final int _max){
        return updateExamcatalogidWithMinMaxInKeys(keys, examcatalogid, _min, _max, TABLENAME);
    }

    public int updateExamcatalogidWithMinMaxInKeys(final List<Integer> keys, final int examcatalogid, final int _min, final int _max, final String TABLENAME2){
        if( examcatalogid < 0 ) {
            return updateExamcatalogidWithMinInKeys(keys, examcatalogid, _min, TABLENAME2);
        } else {
            return updateExamcatalogidWithMaxInKeys(keys, examcatalogid, _max, TABLENAME2);
        }
    }

    public int updateAnswernumByKey(final int answernum, final int optid){
        return updateAnswernumByKey(answernum, optid, TABLENAME);
    }

    public int updateAnswernumByKey(final int answernum, final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answernum=answernum+:answernum WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("answernum", answernum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnswernumWithMinByKey(final int optid, final int answernum, final int _min){
        return updateAnswernumWithMinByKey(optid, answernum, _min, TABLENAME);
    }

    public int updateAnswernumWithMinByKey(final int optid, final int answernum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answernum = (select case when answernum+:answernum<=:_min then :_min else answernum+:answernum end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_min", _min);
            params.put("answernum", answernum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnswernumWithMinInKeys(final List<Integer> keys, final int answernum, final int _min){
        return updateAnswernumWithMinInKeys(keys, answernum, _min, TABLENAME);
    }

    public int updateAnswernumWithMinInKeys(final List<Integer> keys, final int answernum, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answernum = (select case when answernum+:answernum<=:_min then :_min else answernum+:answernum end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("answernum", answernum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnswernumWithMaxByKey(final int optid, final int answernum, final int _max){
        return updateAnswernumWithMaxByKey(optid, answernum, _max, TABLENAME);
    }

    public int updateAnswernumWithMaxByKey(final int optid, final int answernum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answernum = (select case when answernum+:answernum>=:_max then :_max else answernum+:answernum end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_max", _max);
            params.put("answernum", answernum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnswernumWithMaxInKeys(final List<Integer> keys, final int answernum, final int _max){
        return updateAnswernumWithMaxInKeys(keys, answernum, _max, TABLENAME);
    }

    public int updateAnswernumWithMaxInKeys(final List<Integer> keys, final int answernum, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET answernum = (select case when answernum+:answernum>=:_max then :_max else answernum+:answernum end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("answernum", answernum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAnswernumWithMinMaxByKey(final int optid, final int answernum, final int _min, final int _max){
        return updateAnswernumWithMinMaxByKey(optid, answernum, _min, _max, TABLENAME);
    }

    public int updateAnswernumWithMinMaxByKey(final int optid, final int answernum, final int _min, final int _max, final String TABLENAME2){
        if( answernum < 0 ) {
            return updateAnswernumWithMinByKey(optid, answernum, _min, TABLENAME2);
        } else {
            return updateAnswernumWithMaxByKey(optid, answernum, _max, TABLENAME2);
        }
    }

    public int updateAnswernumWithMinMaxInKeys(final List<Integer> keys, final int answernum, final int _min, final int _max){
        return updateAnswernumWithMinMaxInKeys(keys, answernum, _min, _max, TABLENAME);
    }

    public int updateAnswernumWithMinMaxInKeys(final List<Integer> keys, final int answernum, final int _min, final int _max, final String TABLENAME2){
        if( answernum < 0 ) {
            return updateAnswernumWithMinInKeys(keys, answernum, _min, TABLENAME2);
        } else {
            return updateAnswernumWithMaxInKeys(keys, answernum, _max, TABLENAME2);
        }
    }

    public int updateGidByKey(final int gid, final int optid){
        return updateGidByKey(gid, optid, TABLENAME);
    }

    public int updateGidByKey(final int gid, final int optid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid=gid+:gid WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinByKey(final int optid, final int gid, final int _min){
        return updateGidWithMinByKey(optid, gid, _min, TABLENAME);
    }

    public int updateGidWithMinByKey(final int optid, final int gid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid<=:_min then :_min else gid+:gid end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_min", _min);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinInKeys(final List<Integer> keys, final int gid, final int _min){
        return updateGidWithMinInKeys(keys, gid, _min, TABLENAME);
    }

    public int updateGidWithMinInKeys(final List<Integer> keys, final int gid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid<=:_min then :_min else gid+:gid end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMaxByKey(final int optid, final int gid, final int _max){
        return updateGidWithMaxByKey(optid, gid, _max, TABLENAME);
    }

    public int updateGidWithMaxByKey(final int optid, final int gid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid>=:_max then :_max else gid+:gid end) WHERE optid=:optid");
            Map params = newMap();
            params.put("optid", optid);
            params.put("_max", _max);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMaxInKeys(final List<Integer> keys, final int gid, final int _max){
        return updateGidWithMaxInKeys(keys, gid, _max, TABLENAME);
    }

    public int updateGidWithMaxInKeys(final List<Integer> keys, final int gid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid>=:_max then :_max else gid+:gid end) WHERE optid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinMaxByKey(final int optid, final int gid, final int _min, final int _max){
        return updateGidWithMinMaxByKey(optid, gid, _min, _max, TABLENAME);
    }

    public int updateGidWithMinMaxByKey(final int optid, final int gid, final int _min, final int _max, final String TABLENAME2){
        if( gid < 0 ) {
            return updateGidWithMinByKey(optid, gid, _min, TABLENAME2);
        } else {
            return updateGidWithMaxByKey(optid, gid, _max, TABLENAME2);
        }
    }

    public int updateGidWithMinMaxInKeys(final List<Integer> keys, final int gid, final int _min, final int _max){
        return updateGidWithMinMaxInKeys(keys, gid, _min, _max, TABLENAME);
    }

    public int updateGidWithMinMaxInKeys(final List<Integer> keys, final int gid, final int _min, final int _max, final String TABLENAME2){
        if( gid < 0 ) {
            return updateGidWithMinInKeys(keys, gid, _min, TABLENAME2);
        } else {
            return updateGidWithMaxInKeys(keys, gid, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Optquestion> optquestions) {
        return updateByKey(optquestions, TABLENAME);
    }

    public int[] updateByKey (final List<Optquestion> optquestions, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(optquestions == null || optquestions.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET examid=:examid, type=:type, content=:content, right_2=:right_2, analyse=:analyse, voiceurl=:voiceurl, videourl=:videourl, position=:position, status=:status, createtime=:createtime, examcatalogid=:examcatalogid, answernum=:answernum, gid=:gid, imgPic=:imgPic WHERE optid=:optid");
            return super.batchUpdate2(sql.toString(), optquestions);
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
                "	`optid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`examid`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`right_2`  TEXT NOT NULL," +
                "	`analyse`  TEXT NOT NULL," +
                "	`voiceurl`  VARCHAR(128) NOT NULL," +
                "	`videourl`  VARCHAR(128) NOT NULL," +
                "	`position`  TINYTEXT NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`examcatalogid`  INT(11) NOT NULL," +
                "	`answernum`  INT(11) NOT NULL," +
                "	`gid`  INT(11) NOT NULL," +
                "	`imgPic`  TINYTEXT NOT NULL," +
                "	PRIMARY KEY (`optid`)," +
                "	KEY `type` (`type`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `examid_catalogid` (`examid`, `examcatalogid`)," +
                "	KEY `examcatalogid` (`examcatalogid`)" +
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
                "	`optid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`examid`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`right_2`  TEXT NOT NULL," +
                "	`analyse`  TEXT NOT NULL," +
                "	`voiceurl`  VARCHAR(128) NOT NULL," +
                "	`videourl`  VARCHAR(128) NOT NULL," +
                "	`position`  TINYTEXT NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`examcatalogid`  INT(11) NOT NULL," +
                "	`answernum`  INT(11) NOT NULL," +
                "	`gid`  INT(11) NOT NULL," +
                "	`imgPic`  TINYTEXT NOT NULL," +
                "	PRIMARY KEY (`optid`)," +
                "	KEY `type` (`type`)," +
                "	KEY `examid` (`examid`)," +
                "	KEY `examid_catalogid` (`examid`, `examcatalogid`)," +
                "	KEY `examcatalogid` (`examcatalogid`)" +
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
