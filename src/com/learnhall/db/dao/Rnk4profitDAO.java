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

//learnhall3_design - rnk4profit
@SuppressWarnings({"rawtypes", "unchecked"})
public class Rnk4profitDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Rnk4profitDAO.class);

    public static final String TABLE = "rnk4profit";
    public static String TABLENAME = "rnk4profit";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "indexs", "type", "ownerid", "money", "bonus", "royalty"};
    public static String coulmns = "id, indexs, type, ownerid, money, bonus, royalty";
    public static String coulmns2 = "indexs, type, ownerid, money, bonus, royalty";

    public Rnk4profitDAO(Connection conn) {
        super(conn);
    }

    public Rnk4profitDAO(DataSource ds) {
        super(ds);
    }

    public Rnk4profitDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Rnk4profit rnk4profit) {
        return insert(rnk4profit, TABLENAME);
    }

    public int insert(final Rnk4profit rnk4profit, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            rnk4profit.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (indexs, type, ownerid, money, bonus, royalty) VALUES (:indexs, :type, :ownerid, :money, :bonus, :royalty)");
            Map map = super.insert(sql.toString(), rnk4profit);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Rnk4profit rnk4profit) {
        return asyncInsert(rnk4profit, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Rnk4profit rnk4profit, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(rnk4profit, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Rnk4profit rnk4profit) {
        return asyncInsert2(rnk4profit, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Rnk4profit rnk4profit, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(rnk4profit, TABLENAME2);
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

    public int insert2(final Rnk4profit rnk4profit) {
        return insert2(rnk4profit, TABLENAME);
    }

    public int insert2(final Rnk4profit rnk4profit, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            rnk4profit.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, indexs, type, ownerid, money, bonus, royalty) VALUES (:id, :indexs, :type, :ownerid, :money, :bonus, :royalty)");
            Map map = super.insert(sql.toString(), rnk4profit);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Rnk4profit> rnk4profits) {
        return insert(rnk4profits, TABLENAME);
    }

    public int[] insert(final List<Rnk4profit> rnk4profits, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(rnk4profits == null || rnk4profits.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (indexs, type, ownerid, money, bonus, royalty) VALUES (:indexs, :type, :ownerid, :money, :bonus, :royalty)");
            return super.batchInsert(sql.toString(), rnk4profits);
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

    public int deleteInBeans(final List<Rnk4profit> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Rnk4profit> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Rnk4profit rnk4profit = beans.get(i);
                int id = rnk4profit.id;
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

    public List<Rnk4profit> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Rnk4profit> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Rnk4profit.class);
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
            sql.append("SELECT id, type, ownerid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rnk4profit> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Rnk4profit> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Rnk4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rnk4profit> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Rnk4profit> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Rnk4profit.class);
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

    public List<Rnk4profit> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Rnk4profit> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Rnk4profit.class);
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

    public Rnk4profit last() {
        return last(TABLENAME);
    }

    public Rnk4profit last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Rnk4profit.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rnk4profit> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Rnk4profit> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Rnk4profit.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rnk4profit> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Rnk4profit> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Rnk4profit.class);
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

    public Rnk4profit selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Rnk4profit selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Rnk4profit.class);
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

    public Rnk4profit selectByTypeOwnerid(final Integer type, Integer ownerid) {
        return selectByTypeOwnerid(type, ownerid, TABLENAME);
    }

    public Rnk4profit selectByTypeOwnerid(final Integer type, Integer ownerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE type=:type AND ownerid=:ownerid");
            Map params = newMap();
            params.put("type", type);
            params.put("ownerid", ownerid);
            return super.queryForObject(sql.toString(), params, Rnk4profit.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Rnk4profit selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Rnk4profit selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Rnk4profit.class);
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

    public List<Rnk4profit> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Rnk4profit> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, type, ownerid, money, bonus, royalty FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Rnk4profit.class);
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

    public int updateByKey(final Rnk4profit rnk4profit) {
        return updateByKey(rnk4profit, TABLENAME);
    }

    public int updateByKey(final Rnk4profit rnk4profit, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = rnk4profit.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), rnk4profit);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Rnk4profit rnk4profit) {
        return asyncUpdate(rnk4profit, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Rnk4profit rnk4profit, final String TABLENAME2) {
        try {

            String _ustr = rnk4profit.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, rnk4profit);
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

    public int updateIndexsByKey(final int indexs, final int id){
        return updateIndexsByKey(indexs, id, TABLENAME);
    }

    public int updateIndexsByKey(final int indexs, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs=indexs+:indexs WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMinByKey(final int id, final int indexs, final int _min){
        return updateIndexsWithMinByKey(id, indexs, _min, TABLENAME);
    }

    public int updateIndexsWithMinByKey(final int id, final int indexs, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs<=:_min then :_min else indexs+:indexs end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMinInKeys(final List<Integer> keys, final int indexs, final int _min){
        return updateIndexsWithMinInKeys(keys, indexs, _min, TABLENAME);
    }

    public int updateIndexsWithMinInKeys(final List<Integer> keys, final int indexs, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs<=:_min then :_min else indexs+:indexs end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMaxByKey(final int id, final int indexs, final int _max){
        return updateIndexsWithMaxByKey(id, indexs, _max, TABLENAME);
    }

    public int updateIndexsWithMaxByKey(final int id, final int indexs, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs>=:_max then :_max else indexs+:indexs end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMaxInKeys(final List<Integer> keys, final int indexs, final int _max){
        return updateIndexsWithMaxInKeys(keys, indexs, _max, TABLENAME);
    }

    public int updateIndexsWithMaxInKeys(final List<Integer> keys, final int indexs, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs>=:_max then :_max else indexs+:indexs end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMinMaxByKey(final int id, final int indexs, final int _min, final int _max){
        return updateIndexsWithMinMaxByKey(id, indexs, _min, _max, TABLENAME);
    }

    public int updateIndexsWithMinMaxByKey(final int id, final int indexs, final int _min, final int _max, final String TABLENAME2){
        if( indexs < 0 ) {
            return updateIndexsWithMinByKey(id, indexs, _min, TABLENAME2);
        } else {
            return updateIndexsWithMaxByKey(id, indexs, _max, TABLENAME2);
        }
    }

    public int updateIndexsWithMinMaxInKeys(final List<Integer> keys, final int indexs, final int _min, final int _max){
        return updateIndexsWithMinMaxInKeys(keys, indexs, _min, _max, TABLENAME);
    }

    public int updateIndexsWithMinMaxInKeys(final List<Integer> keys, final int indexs, final int _min, final int _max, final String TABLENAME2){
        if( indexs < 0 ) {
            return updateIndexsWithMinInKeys(keys, indexs, _min, TABLENAME2);
        } else {
            return updateIndexsWithMaxInKeys(keys, indexs, _max, TABLENAME2);
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

    public int updateOwneridByKey(final int ownerid, final int id){
        return updateOwneridByKey(ownerid, id, TABLENAME);
    }

    public int updateOwneridByKey(final int ownerid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ownerid=ownerid+:ownerid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("ownerid", ownerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOwneridWithMinByKey(final int id, final int ownerid, final int _min){
        return updateOwneridWithMinByKey(id, ownerid, _min, TABLENAME);
    }

    public int updateOwneridWithMinByKey(final int id, final int ownerid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ownerid = (select case when ownerid+:ownerid<=:_min then :_min else ownerid+:ownerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("ownerid", ownerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOwneridWithMinInKeys(final List<Integer> keys, final int ownerid, final int _min){
        return updateOwneridWithMinInKeys(keys, ownerid, _min, TABLENAME);
    }

    public int updateOwneridWithMinInKeys(final List<Integer> keys, final int ownerid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ownerid = (select case when ownerid+:ownerid<=:_min then :_min else ownerid+:ownerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("ownerid", ownerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOwneridWithMaxByKey(final int id, final int ownerid, final int _max){
        return updateOwneridWithMaxByKey(id, ownerid, _max, TABLENAME);
    }

    public int updateOwneridWithMaxByKey(final int id, final int ownerid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ownerid = (select case when ownerid+:ownerid>=:_max then :_max else ownerid+:ownerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("ownerid", ownerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOwneridWithMaxInKeys(final List<Integer> keys, final int ownerid, final int _max){
        return updateOwneridWithMaxInKeys(keys, ownerid, _max, TABLENAME);
    }

    public int updateOwneridWithMaxInKeys(final List<Integer> keys, final int ownerid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ownerid = (select case when ownerid+:ownerid>=:_max then :_max else ownerid+:ownerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("ownerid", ownerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOwneridWithMinMaxByKey(final int id, final int ownerid, final int _min, final int _max){
        return updateOwneridWithMinMaxByKey(id, ownerid, _min, _max, TABLENAME);
    }

    public int updateOwneridWithMinMaxByKey(final int id, final int ownerid, final int _min, final int _max, final String TABLENAME2){
        if( ownerid < 0 ) {
            return updateOwneridWithMinByKey(id, ownerid, _min, TABLENAME2);
        } else {
            return updateOwneridWithMaxByKey(id, ownerid, _max, TABLENAME2);
        }
    }

    public int updateOwneridWithMinMaxInKeys(final List<Integer> keys, final int ownerid, final int _min, final int _max){
        return updateOwneridWithMinMaxInKeys(keys, ownerid, _min, _max, TABLENAME);
    }

    public int updateOwneridWithMinMaxInKeys(final List<Integer> keys, final int ownerid, final int _min, final int _max, final String TABLENAME2){
        if( ownerid < 0 ) {
            return updateOwneridWithMinInKeys(keys, ownerid, _min, TABLENAME2);
        } else {
            return updateOwneridWithMaxInKeys(keys, ownerid, _max, TABLENAME2);
        }
    }

    public int updateMoneyByKey(final double money, final int id){
        return updateMoneyByKey(money, id, TABLENAME);
    }

    public int updateMoneyByKey(final double money, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money=money+:money WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMinByKey(final int id, final double money, final double _min){
        return updateMoneyWithMinByKey(id, money, _min, TABLENAME);
    }

    public int updateMoneyWithMinByKey(final int id, final double money, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money<=:_min then :_min else money+:money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMinInKeys(final List<Integer> keys, final double money, final double _min){
        return updateMoneyWithMinInKeys(keys, money, _min, TABLENAME);
    }

    public int updateMoneyWithMinInKeys(final List<Integer> keys, final double money, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money<=:_min then :_min else money+:money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMaxByKey(final int id, final double money, final double _max){
        return updateMoneyWithMaxByKey(id, money, _max, TABLENAME);
    }

    public int updateMoneyWithMaxByKey(final int id, final double money, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money>=:_max then :_max else money+:money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMaxInKeys(final List<Integer> keys, final double money, final double _max){
        return updateMoneyWithMaxInKeys(keys, money, _max, TABLENAME);
    }

    public int updateMoneyWithMaxInKeys(final List<Integer> keys, final double money, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money>=:_max then :_max else money+:money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMinMaxByKey(final int id, final double money, final double _min, final double _max){
        return updateMoneyWithMinMaxByKey(id, money, _min, _max, TABLENAME);
    }

    public int updateMoneyWithMinMaxByKey(final int id, final double money, final double _min, final double _max, final String TABLENAME2){
        if( money < 0 ) {
            return updateMoneyWithMinByKey(id, money, _min, TABLENAME2);
        } else {
            return updateMoneyWithMaxByKey(id, money, _max, TABLENAME2);
        }
    }

    public int updateMoneyWithMinMaxInKeys(final List<Integer> keys, final double money, final double _min, final double _max){
        return updateMoneyWithMinMaxInKeys(keys, money, _min, _max, TABLENAME);
    }

    public int updateMoneyWithMinMaxInKeys(final List<Integer> keys, final double money, final double _min, final double _max, final String TABLENAME2){
        if( money < 0 ) {
            return updateMoneyWithMinInKeys(keys, money, _min, TABLENAME2);
        } else {
            return updateMoneyWithMaxInKeys(keys, money, _max, TABLENAME2);
        }
    }

    public int updateBonusByKey(final double bonus, final int id){
        return updateBonusByKey(bonus, id, TABLENAME);
    }

    public int updateBonusByKey(final double bonus, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus=bonus+:bonus WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMinByKey(final int id, final double bonus, final double _min){
        return updateBonusWithMinByKey(id, bonus, _min, TABLENAME);
    }

    public int updateBonusWithMinByKey(final int id, final double bonus, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus<=:_min then :_min else bonus+:bonus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMinInKeys(final List<Integer> keys, final double bonus, final double _min){
        return updateBonusWithMinInKeys(keys, bonus, _min, TABLENAME);
    }

    public int updateBonusWithMinInKeys(final List<Integer> keys, final double bonus, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus<=:_min then :_min else bonus+:bonus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMaxByKey(final int id, final double bonus, final double _max){
        return updateBonusWithMaxByKey(id, bonus, _max, TABLENAME);
    }

    public int updateBonusWithMaxByKey(final int id, final double bonus, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus>=:_max then :_max else bonus+:bonus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMaxInKeys(final List<Integer> keys, final double bonus, final double _max){
        return updateBonusWithMaxInKeys(keys, bonus, _max, TABLENAME);
    }

    public int updateBonusWithMaxInKeys(final List<Integer> keys, final double bonus, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bonus = (select case when bonus+:bonus>=:_max then :_max else bonus+:bonus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("bonus", bonus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBonusWithMinMaxByKey(final int id, final double bonus, final double _min, final double _max){
        return updateBonusWithMinMaxByKey(id, bonus, _min, _max, TABLENAME);
    }

    public int updateBonusWithMinMaxByKey(final int id, final double bonus, final double _min, final double _max, final String TABLENAME2){
        if( bonus < 0 ) {
            return updateBonusWithMinByKey(id, bonus, _min, TABLENAME2);
        } else {
            return updateBonusWithMaxByKey(id, bonus, _max, TABLENAME2);
        }
    }

    public int updateBonusWithMinMaxInKeys(final List<Integer> keys, final double bonus, final double _min, final double _max){
        return updateBonusWithMinMaxInKeys(keys, bonus, _min, _max, TABLENAME);
    }

    public int updateBonusWithMinMaxInKeys(final List<Integer> keys, final double bonus, final double _min, final double _max, final String TABLENAME2){
        if( bonus < 0 ) {
            return updateBonusWithMinInKeys(keys, bonus, _min, TABLENAME2);
        } else {
            return updateBonusWithMaxInKeys(keys, bonus, _max, TABLENAME2);
        }
    }

    public int updateRoyaltyByKey(final double royalty, final int id){
        return updateRoyaltyByKey(royalty, id, TABLENAME);
    }

    public int updateRoyaltyByKey(final double royalty, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET royalty=royalty+:royalty WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("royalty", royalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRoyaltyWithMinByKey(final int id, final double royalty, final double _min){
        return updateRoyaltyWithMinByKey(id, royalty, _min, TABLENAME);
    }

    public int updateRoyaltyWithMinByKey(final int id, final double royalty, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET royalty = (select case when royalty+:royalty<=:_min then :_min else royalty+:royalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("royalty", royalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRoyaltyWithMinInKeys(final List<Integer> keys, final double royalty, final double _min){
        return updateRoyaltyWithMinInKeys(keys, royalty, _min, TABLENAME);
    }

    public int updateRoyaltyWithMinInKeys(final List<Integer> keys, final double royalty, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET royalty = (select case when royalty+:royalty<=:_min then :_min else royalty+:royalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("royalty", royalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRoyaltyWithMaxByKey(final int id, final double royalty, final double _max){
        return updateRoyaltyWithMaxByKey(id, royalty, _max, TABLENAME);
    }

    public int updateRoyaltyWithMaxByKey(final int id, final double royalty, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET royalty = (select case when royalty+:royalty>=:_max then :_max else royalty+:royalty end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("royalty", royalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRoyaltyWithMaxInKeys(final List<Integer> keys, final double royalty, final double _max){
        return updateRoyaltyWithMaxInKeys(keys, royalty, _max, TABLENAME);
    }

    public int updateRoyaltyWithMaxInKeys(final List<Integer> keys, final double royalty, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET royalty = (select case when royalty+:royalty>=:_max then :_max else royalty+:royalty end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("royalty", royalty);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRoyaltyWithMinMaxByKey(final int id, final double royalty, final double _min, final double _max){
        return updateRoyaltyWithMinMaxByKey(id, royalty, _min, _max, TABLENAME);
    }

    public int updateRoyaltyWithMinMaxByKey(final int id, final double royalty, final double _min, final double _max, final String TABLENAME2){
        if( royalty < 0 ) {
            return updateRoyaltyWithMinByKey(id, royalty, _min, TABLENAME2);
        } else {
            return updateRoyaltyWithMaxByKey(id, royalty, _max, TABLENAME2);
        }
    }

    public int updateRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double royalty, final double _min, final double _max){
        return updateRoyaltyWithMinMaxInKeys(keys, royalty, _min, _max, TABLENAME);
    }

    public int updateRoyaltyWithMinMaxInKeys(final List<Integer> keys, final double royalty, final double _min, final double _max, final String TABLENAME2){
        if( royalty < 0 ) {
            return updateRoyaltyWithMinInKeys(keys, royalty, _min, TABLENAME2);
        } else {
            return updateRoyaltyWithMaxInKeys(keys, royalty, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Rnk4profit> rnk4profits) {
        return updateByKey(rnk4profits, TABLENAME);
    }

    public int[] updateByKey (final List<Rnk4profit> rnk4profits, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(rnk4profits == null || rnk4profits.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs=:indexs, type=:type, ownerid=:ownerid, money=:money, bonus=:bonus, royalty=:royalty WHERE id=:id");
            return super.batchUpdate2(sql.toString(), rnk4profits);
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
                "	`indexs`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`ownerid`  INT(11) NOT NULL," +
                "	`money`  DOUBLE NOT NULL," +
                "	`bonus`  DOUBLE NOT NULL," +
                "	`royalty`  DOUBLE NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `type_ownerid` (`type`, `ownerid`)" +
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
                "	`indexs`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`ownerid`  INT(11) NOT NULL," +
                "	`money`  DOUBLE NOT NULL," +
                "	`bonus`  DOUBLE NOT NULL," +
                "	`royalty`  DOUBLE NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type_ownerid` (`type`, `ownerid`)" +
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
