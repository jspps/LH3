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

//learnhall3_design - kind
@SuppressWarnings({"rawtypes", "unchecked"})
public class KindDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(KindDAO.class);

    public static final String TABLE = "kind";
    public static String TABLENAME = "kind";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "kclassid", "nmKClass", "coursid", "productid", "nmProduct", "lhubid", "nmLhub", "price", "discount", "kbi", "validity", "imgurl", "buycount", "visit", "praise", "examtypes", "status", "numExercises", "numZhenti", "numSimulation", "numVast", "createtime", "isHasITMS", "examids"};
    public static String coulmns = "id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids";
    public static String coulmns2 = "kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids";

    public KindDAO(Connection conn) {
        super(conn);
    }

    public KindDAO(DataSource ds) {
        super(ds);
    }

    public KindDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Kind kind) {
        return insert(kind, TABLENAME);
    }

    public int insert(final Kind kind, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            kind.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids) VALUES (:kclassid, :nmKClass, :coursid, :productid, :nmProduct, :lhubid, :nmLhub, :price, :discount, :kbi, :validity, :imgurl, :buycount, :visit, :praise, :examtypes, :status, :numExercises, :numZhenti, :numSimulation, :numVast, :createtime, :isHasITMS, :examids)");
            Map map = super.insert(sql.toString(), kind);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Kind kind) {
        return asyncInsert(kind, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Kind kind, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(kind, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Kind kind) {
        return asyncInsert2(kind, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Kind kind, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(kind, TABLENAME2);
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

    public int insert2(final Kind kind) {
        return insert2(kind, TABLENAME);
    }

    public int insert2(final Kind kind, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            kind.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids) VALUES (:id, :kclassid, :nmKClass, :coursid, :productid, :nmProduct, :lhubid, :nmLhub, :price, :discount, :kbi, :validity, :imgurl, :buycount, :visit, :praise, :examtypes, :status, :numExercises, :numZhenti, :numSimulation, :numVast, :createtime, :isHasITMS, :examids)");
            Map map = super.insert(sql.toString(), kind);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Kind> kinds) {
        return insert(kinds, TABLENAME);
    }

    public int[] insert(final List<Kind> kinds, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(kinds == null || kinds.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids) VALUES (:kclassid, :nmKClass, :coursid, :productid, :nmProduct, :lhubid, :nmLhub, :price, :discount, :kbi, :validity, :imgurl, :buycount, :visit, :praise, :examtypes, :status, :numExercises, :numZhenti, :numSimulation, :numVast, :createtime, :isHasITMS, :examids)");
            return super.batchInsert(sql.toString(), kinds);
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

    public int deleteInBeans(final List<Kind> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Kind> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Kind kind = beans.get(i);
                int id = kind.id;
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

    public List<Kind> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Kind> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Kind.class);
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
            sql.append("SELECT id, kclassid, coursid, productid, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Kind> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Kind> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Kind.class);
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

    public List<Kind> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Kind> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Kind.class);
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

    public Kind last() {
        return last(TABLENAME);
    }

    public Kind last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Kind.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Kind> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Kind> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Kind.class);
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

    public Kind selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Kind selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Kind.class);
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

    public List<Kind> selectByLhubid(final Integer lhubid) {
        return selectByLhubid(lhubid, TABLENAME);
    }

    public List<Kind> selectByLhubid(final Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id ");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Kind.class);
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

    public List<Kind> selectPageByLhubid(final Integer lhubid, final int begin, final int num) {
        return selectPageByLhubid(lhubid, begin, num, TABLENAME);
    }

    public List<Kind> selectPageByLhubid(final Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE lhubid = :lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Kind.class);
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

    public Kind selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Kind selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByProductid(final Integer productid) {
        return countByProductid(productid, TABLENAME);
    }

    public int countByProductid(final Integer productid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE productid = :productid ");
            Map params = newMap();
            params.put("productid", productid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectByProductid(final Integer productid) {
        return selectByProductid(productid, TABLENAME);
    }

    public List<Kind> selectByProductid(final Integer productid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id ");
            Map params = newMap();
            params.put("productid", productid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByProductidPKs(final Integer productid) {
        return selectByProductidPKs(productid, TABLENAME);
    }

    public List<Integer> selectByProductidPKs(final Integer productid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id ");
            Map params = newMap();
            params.put("productid", productid);
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

    public List<Kind> selectPageByProductid(final Integer productid, final int begin, final int num) {
        return selectPageByProductid(productid, begin, num, TABLENAME);
    }

    public List<Kind> selectPageByProductid(final Integer productid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("productid", productid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByProductidPKs(final Integer productid, final int begin, final int num) {
        return selectPageByProductidPKs(productid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByProductidPKs(final Integer productid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE productid = :productid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("productid", productid);
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

    public int countByCoursid(final Integer coursid) {
        return countByCoursid(coursid, TABLENAME);
    }

    public int countByCoursid(final Integer coursid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE coursid = :coursid ");
            Map params = newMap();
            params.put("coursid", coursid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectByCoursid(final Integer coursid) {
        return selectByCoursid(coursid, TABLENAME);
    }

    public List<Kind> selectByCoursid(final Integer coursid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE coursid = :coursid ORDER BY id ");
            Map params = newMap();
            params.put("coursid", coursid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCoursidPKs(final Integer coursid) {
        return selectByCoursidPKs(coursid, TABLENAME);
    }

    public List<Integer> selectByCoursidPKs(final Integer coursid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE coursid = :coursid ORDER BY id ");
            Map params = newMap();
            params.put("coursid", coursid);
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

    public List<Kind> selectPageByCoursid(final Integer coursid, final int begin, final int num) {
        return selectPageByCoursid(coursid, begin, num, TABLENAME);
    }

    public List<Kind> selectPageByCoursid(final Integer coursid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE coursid = :coursid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("coursid", coursid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCoursidPKs(final Integer coursid, final int begin, final int num) {
        return selectPageByCoursidPKs(coursid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCoursidPKs(final Integer coursid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE coursid = :coursid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("coursid", coursid);
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

    public int countByKclassid(final Integer kclassid) {
        return countByKclassid(kclassid, TABLENAME);
    }

    public int countByKclassid(final Integer kclassid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE kclassid = :kclassid ");
            Map params = newMap();
            params.put("kclassid", kclassid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectByKclassid(final Integer kclassid) {
        return selectByKclassid(kclassid, TABLENAME);
    }

    public List<Kind> selectByKclassid(final Integer kclassid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE kclassid = :kclassid ORDER BY id ");
            Map params = newMap();
            params.put("kclassid", kclassid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByKclassidPKs(final Integer kclassid) {
        return selectByKclassidPKs(kclassid, TABLENAME);
    }

    public List<Integer> selectByKclassidPKs(final Integer kclassid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE kclassid = :kclassid ORDER BY id ");
            Map params = newMap();
            params.put("kclassid", kclassid);
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

    public List<Kind> selectPageByKclassid(final Integer kclassid, final int begin, final int num) {
        return selectPageByKclassid(kclassid, begin, num, TABLENAME);
    }

    public List<Kind> selectPageByKclassid(final Integer kclassid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE kclassid = :kclassid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("kclassid", kclassid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByKclassidPKs(final Integer kclassid, final int begin, final int num) {
        return selectPageByKclassidPKs(kclassid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByKclassidPKs(final Integer kclassid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE kclassid = :kclassid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("kclassid", kclassid);
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

    public Kind selectByKclassidProductidLhubid(final Integer kclassid, Integer productid, Integer lhubid) {
        return selectByKclassidProductidLhubid(kclassid, productid, lhubid, TABLENAME);
    }

    public Kind selectByKclassidProductidLhubid(final Integer kclassid, Integer productid, Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE kclassid=:kclassid AND productid=:productid AND lhubid=:lhubid");
            Map params = newMap();
            params.put("kclassid", kclassid);
            params.put("productid", productid);
            params.put("lhubid", lhubid);
            return super.queryForObject(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByProductidLhubid(final Integer productid, Integer lhubid) {
        return  countByProductidLhubid(productid, lhubid, TABLENAME);
    }

    public int countByProductidLhubid(final Integer productid, Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE productid=:productid AND lhubid=:lhubid ");
            Map params = newMap();
            params.put("productid", productid);
            params.put("lhubid", lhubid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Kind> selectByProductidLhubid(final Integer productid, Integer lhubid) {
        return selectByProductidLhubid(productid, lhubid, TABLENAME);
    }

    public List<Kind> selectByProductidLhubid(final Integer productid, Integer lhubid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE productid=:productid AND lhubid=:lhubid ORDER BY id ");
            Map params = newMap();
            params.put("productid", productid);
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByProductidLhubidPKs(final Integer productid, Integer lhubid) {
        return selectByProductidLhubidPKs(productid, lhubid, TABLENAME);
    }

    public List<Integer> selectByProductidLhubidPKs(final Integer productid, Integer lhubid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE productid=:productid AND lhubid=:lhubid ORDER BY id ");
            Map params = newMap();
            params.put("productid", productid);
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

    public List<Kind> selectPageByProductidLhubid(final Integer productid, Integer lhubid, final int begin, final int num) {
        return selectPageByProductidLhubid(productid, lhubid, begin, num, TABLENAME);
    }

    public List<Kind> selectPageByProductidLhubid(final Integer productid, Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" WHERE productid=:productid AND lhubid=:lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("productid", productid);
            params.put("lhubid", lhubid);
            return super.queryForList(sql.toString(), params, Kind.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByProductidLhubidPKs(final Integer productid, Integer lhubid, final int begin, final int num) {
        return selectPageByProductidLhubidPKs(productid, lhubid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByProductidLhubidPKs(final Integer productid, Integer lhubid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE productid=:productid AND lhubid=:lhubid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("productid", productid);
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

    public List<Kind> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Kind> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Kind.class);
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

    public int updateByKey(final Kind kind) {
        return updateByKey(kind, TABLENAME);
    }

    public int updateByKey(final Kind kind, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = kind.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), kind);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Kind kind) {
        return asyncUpdate(kind, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Kind kind, final String TABLENAME2) {
        try {

            String _ustr = kind.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, kind);
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

    public int updateKclassidByKey(final int kclassid, final int id){
        return updateKclassidByKey(kclassid, id, TABLENAME);
    }

    public int updateKclassidByKey(final int kclassid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kclassid=kclassid+:kclassid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kclassid", kclassid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKclassidWithMinByKey(final int id, final int kclassid, final int _min){
        return updateKclassidWithMinByKey(id, kclassid, _min, TABLENAME);
    }

    public int updateKclassidWithMinByKey(final int id, final int kclassid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kclassid = (select case when kclassid+:kclassid<=:_min then :_min else kclassid+:kclassid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kclassid", kclassid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKclassidWithMinInKeys(final List<Integer> keys, final int kclassid, final int _min){
        return updateKclassidWithMinInKeys(keys, kclassid, _min, TABLENAME);
    }

    public int updateKclassidWithMinInKeys(final List<Integer> keys, final int kclassid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kclassid = (select case when kclassid+:kclassid<=:_min then :_min else kclassid+:kclassid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kclassid", kclassid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKclassidWithMaxByKey(final int id, final int kclassid, final int _max){
        return updateKclassidWithMaxByKey(id, kclassid, _max, TABLENAME);
    }

    public int updateKclassidWithMaxByKey(final int id, final int kclassid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kclassid = (select case when kclassid+:kclassid>=:_max then :_max else kclassid+:kclassid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kclassid", kclassid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKclassidWithMaxInKeys(final List<Integer> keys, final int kclassid, final int _max){
        return updateKclassidWithMaxInKeys(keys, kclassid, _max, TABLENAME);
    }

    public int updateKclassidWithMaxInKeys(final List<Integer> keys, final int kclassid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kclassid = (select case when kclassid+:kclassid>=:_max then :_max else kclassid+:kclassid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kclassid", kclassid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKclassidWithMinMaxByKey(final int id, final int kclassid, final int _min, final int _max){
        return updateKclassidWithMinMaxByKey(id, kclassid, _min, _max, TABLENAME);
    }

    public int updateKclassidWithMinMaxByKey(final int id, final int kclassid, final int _min, final int _max, final String TABLENAME2){
        if( kclassid < 0 ) {
            return updateKclassidWithMinByKey(id, kclassid, _min, TABLENAME2);
        } else {
            return updateKclassidWithMaxByKey(id, kclassid, _max, TABLENAME2);
        }
    }

    public int updateKclassidWithMinMaxInKeys(final List<Integer> keys, final int kclassid, final int _min, final int _max){
        return updateKclassidWithMinMaxInKeys(keys, kclassid, _min, _max, TABLENAME);
    }

    public int updateKclassidWithMinMaxInKeys(final List<Integer> keys, final int kclassid, final int _min, final int _max, final String TABLENAME2){
        if( kclassid < 0 ) {
            return updateKclassidWithMinInKeys(keys, kclassid, _min, TABLENAME2);
        } else {
            return updateKclassidWithMaxInKeys(keys, kclassid, _max, TABLENAME2);
        }
    }

    public int updateCoursidByKey(final int coursid, final int id){
        return updateCoursidByKey(coursid, id, TABLENAME);
    }

    public int updateCoursidByKey(final int coursid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursid=coursid+:coursid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("coursid", coursid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursidWithMinByKey(final int id, final int coursid, final int _min){
        return updateCoursidWithMinByKey(id, coursid, _min, TABLENAME);
    }

    public int updateCoursidWithMinByKey(final int id, final int coursid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursid = (select case when coursid+:coursid<=:_min then :_min else coursid+:coursid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("coursid", coursid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursidWithMinInKeys(final List<Integer> keys, final int coursid, final int _min){
        return updateCoursidWithMinInKeys(keys, coursid, _min, TABLENAME);
    }

    public int updateCoursidWithMinInKeys(final List<Integer> keys, final int coursid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursid = (select case when coursid+:coursid<=:_min then :_min else coursid+:coursid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("coursid", coursid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursidWithMaxByKey(final int id, final int coursid, final int _max){
        return updateCoursidWithMaxByKey(id, coursid, _max, TABLENAME);
    }

    public int updateCoursidWithMaxByKey(final int id, final int coursid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursid = (select case when coursid+:coursid>=:_max then :_max else coursid+:coursid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("coursid", coursid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursidWithMaxInKeys(final List<Integer> keys, final int coursid, final int _max){
        return updateCoursidWithMaxInKeys(keys, coursid, _max, TABLENAME);
    }

    public int updateCoursidWithMaxInKeys(final List<Integer> keys, final int coursid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET coursid = (select case when coursid+:coursid>=:_max then :_max else coursid+:coursid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("coursid", coursid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCoursidWithMinMaxByKey(final int id, final int coursid, final int _min, final int _max){
        return updateCoursidWithMinMaxByKey(id, coursid, _min, _max, TABLENAME);
    }

    public int updateCoursidWithMinMaxByKey(final int id, final int coursid, final int _min, final int _max, final String TABLENAME2){
        if( coursid < 0 ) {
            return updateCoursidWithMinByKey(id, coursid, _min, TABLENAME2);
        } else {
            return updateCoursidWithMaxByKey(id, coursid, _max, TABLENAME2);
        }
    }

    public int updateCoursidWithMinMaxInKeys(final List<Integer> keys, final int coursid, final int _min, final int _max){
        return updateCoursidWithMinMaxInKeys(keys, coursid, _min, _max, TABLENAME);
    }

    public int updateCoursidWithMinMaxInKeys(final List<Integer> keys, final int coursid, final int _min, final int _max, final String TABLENAME2){
        if( coursid < 0 ) {
            return updateCoursidWithMinInKeys(keys, coursid, _min, TABLENAME2);
        } else {
            return updateCoursidWithMaxInKeys(keys, coursid, _max, TABLENAME2);
        }
    }

    public int updateProductidByKey(final int productid, final int id){
        return updateProductidByKey(productid, id, TABLENAME);
    }

    public int updateProductidByKey(final int productid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid=productid+:productid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinByKey(final int id, final int productid, final int _min){
        return updateProductidWithMinByKey(id, productid, _min, TABLENAME);
    }

    public int updateProductidWithMinByKey(final int id, final int productid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid<=:_min then :_min else productid+:productid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinInKeys(final List<Integer> keys, final int productid, final int _min){
        return updateProductidWithMinInKeys(keys, productid, _min, TABLENAME);
    }

    public int updateProductidWithMinInKeys(final List<Integer> keys, final int productid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid<=:_min then :_min else productid+:productid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMaxByKey(final int id, final int productid, final int _max){
        return updateProductidWithMaxByKey(id, productid, _max, TABLENAME);
    }

    public int updateProductidWithMaxByKey(final int id, final int productid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid>=:_max then :_max else productid+:productid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMaxInKeys(final List<Integer> keys, final int productid, final int _max){
        return updateProductidWithMaxInKeys(keys, productid, _max, TABLENAME);
    }

    public int updateProductidWithMaxInKeys(final List<Integer> keys, final int productid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET productid = (select case when productid+:productid>=:_max then :_max else productid+:productid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("productid", productid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProductidWithMinMaxByKey(final int id, final int productid, final int _min, final int _max){
        return updateProductidWithMinMaxByKey(id, productid, _min, _max, TABLENAME);
    }

    public int updateProductidWithMinMaxByKey(final int id, final int productid, final int _min, final int _max, final String TABLENAME2){
        if( productid < 0 ) {
            return updateProductidWithMinByKey(id, productid, _min, TABLENAME2);
        } else {
            return updateProductidWithMaxByKey(id, productid, _max, TABLENAME2);
        }
    }

    public int updateProductidWithMinMaxInKeys(final List<Integer> keys, final int productid, final int _min, final int _max){
        return updateProductidWithMinMaxInKeys(keys, productid, _min, _max, TABLENAME);
    }

    public int updateProductidWithMinMaxInKeys(final List<Integer> keys, final int productid, final int _min, final int _max, final String TABLENAME2){
        if( productid < 0 ) {
            return updateProductidWithMinInKeys(keys, productid, _min, TABLENAME2);
        } else {
            return updateProductidWithMaxInKeys(keys, productid, _max, TABLENAME2);
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

    public int updateValidityByKey(final int validity, final int id){
        return updateValidityByKey(validity, id, TABLENAME);
    }

    public int updateValidityByKey(final int validity, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validity=validity+:validity WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("validity", validity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidityWithMinByKey(final int id, final int validity, final int _min){
        return updateValidityWithMinByKey(id, validity, _min, TABLENAME);
    }

    public int updateValidityWithMinByKey(final int id, final int validity, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validity = (select case when validity+:validity<=:_min then :_min else validity+:validity end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("validity", validity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidityWithMinInKeys(final List<Integer> keys, final int validity, final int _min){
        return updateValidityWithMinInKeys(keys, validity, _min, TABLENAME);
    }

    public int updateValidityWithMinInKeys(final List<Integer> keys, final int validity, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validity = (select case when validity+:validity<=:_min then :_min else validity+:validity end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("validity", validity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidityWithMaxByKey(final int id, final int validity, final int _max){
        return updateValidityWithMaxByKey(id, validity, _max, TABLENAME);
    }

    public int updateValidityWithMaxByKey(final int id, final int validity, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validity = (select case when validity+:validity>=:_max then :_max else validity+:validity end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("validity", validity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidityWithMaxInKeys(final List<Integer> keys, final int validity, final int _max){
        return updateValidityWithMaxInKeys(keys, validity, _max, TABLENAME);
    }

    public int updateValidityWithMaxInKeys(final List<Integer> keys, final int validity, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validity = (select case when validity+:validity>=:_max then :_max else validity+:validity end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("validity", validity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidityWithMinMaxByKey(final int id, final int validity, final int _min, final int _max){
        return updateValidityWithMinMaxByKey(id, validity, _min, _max, TABLENAME);
    }

    public int updateValidityWithMinMaxByKey(final int id, final int validity, final int _min, final int _max, final String TABLENAME2){
        if( validity < 0 ) {
            return updateValidityWithMinByKey(id, validity, _min, TABLENAME2);
        } else {
            return updateValidityWithMaxByKey(id, validity, _max, TABLENAME2);
        }
    }

    public int updateValidityWithMinMaxInKeys(final List<Integer> keys, final int validity, final int _min, final int _max){
        return updateValidityWithMinMaxInKeys(keys, validity, _min, _max, TABLENAME);
    }

    public int updateValidityWithMinMaxInKeys(final List<Integer> keys, final int validity, final int _min, final int _max, final String TABLENAME2){
        if( validity < 0 ) {
            return updateValidityWithMinInKeys(keys, validity, _min, TABLENAME2);
        } else {
            return updateValidityWithMaxInKeys(keys, validity, _max, TABLENAME2);
        }
    }

    public int updateBuycountByKey(final int buycount, final int id){
        return updateBuycountByKey(buycount, id, TABLENAME);
    }

    public int updateBuycountByKey(final int buycount, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buycount=buycount+:buycount WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("buycount", buycount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuycountWithMinByKey(final int id, final int buycount, final int _min){
        return updateBuycountWithMinByKey(id, buycount, _min, TABLENAME);
    }

    public int updateBuycountWithMinByKey(final int id, final int buycount, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buycount = (select case when buycount+:buycount<=:_min then :_min else buycount+:buycount end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("buycount", buycount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuycountWithMinInKeys(final List<Integer> keys, final int buycount, final int _min){
        return updateBuycountWithMinInKeys(keys, buycount, _min, TABLENAME);
    }

    public int updateBuycountWithMinInKeys(final List<Integer> keys, final int buycount, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buycount = (select case when buycount+:buycount<=:_min then :_min else buycount+:buycount end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("buycount", buycount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuycountWithMaxByKey(final int id, final int buycount, final int _max){
        return updateBuycountWithMaxByKey(id, buycount, _max, TABLENAME);
    }

    public int updateBuycountWithMaxByKey(final int id, final int buycount, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buycount = (select case when buycount+:buycount>=:_max then :_max else buycount+:buycount end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("buycount", buycount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuycountWithMaxInKeys(final List<Integer> keys, final int buycount, final int _max){
        return updateBuycountWithMaxInKeys(keys, buycount, _max, TABLENAME);
    }

    public int updateBuycountWithMaxInKeys(final List<Integer> keys, final int buycount, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET buycount = (select case when buycount+:buycount>=:_max then :_max else buycount+:buycount end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("buycount", buycount);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBuycountWithMinMaxByKey(final int id, final int buycount, final int _min, final int _max){
        return updateBuycountWithMinMaxByKey(id, buycount, _min, _max, TABLENAME);
    }

    public int updateBuycountWithMinMaxByKey(final int id, final int buycount, final int _min, final int _max, final String TABLENAME2){
        if( buycount < 0 ) {
            return updateBuycountWithMinByKey(id, buycount, _min, TABLENAME2);
        } else {
            return updateBuycountWithMaxByKey(id, buycount, _max, TABLENAME2);
        }
    }

    public int updateBuycountWithMinMaxInKeys(final List<Integer> keys, final int buycount, final int _min, final int _max){
        return updateBuycountWithMinMaxInKeys(keys, buycount, _min, _max, TABLENAME);
    }

    public int updateBuycountWithMinMaxInKeys(final List<Integer> keys, final int buycount, final int _min, final int _max, final String TABLENAME2){
        if( buycount < 0 ) {
            return updateBuycountWithMinInKeys(keys, buycount, _min, TABLENAME2);
        } else {
            return updateBuycountWithMaxInKeys(keys, buycount, _max, TABLENAME2);
        }
    }

    public int updateVisitByKey(final int visit, final int id){
        return updateVisitByKey(visit, id, TABLENAME);
    }

    public int updateVisitByKey(final int visit, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET visit=visit+:visit WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("visit", visit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVisitWithMinByKey(final int id, final int visit, final int _min){
        return updateVisitWithMinByKey(id, visit, _min, TABLENAME);
    }

    public int updateVisitWithMinByKey(final int id, final int visit, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET visit = (select case when visit+:visit<=:_min then :_min else visit+:visit end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("visit", visit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVisitWithMinInKeys(final List<Integer> keys, final int visit, final int _min){
        return updateVisitWithMinInKeys(keys, visit, _min, TABLENAME);
    }

    public int updateVisitWithMinInKeys(final List<Integer> keys, final int visit, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET visit = (select case when visit+:visit<=:_min then :_min else visit+:visit end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("visit", visit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVisitWithMaxByKey(final int id, final int visit, final int _max){
        return updateVisitWithMaxByKey(id, visit, _max, TABLENAME);
    }

    public int updateVisitWithMaxByKey(final int id, final int visit, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET visit = (select case when visit+:visit>=:_max then :_max else visit+:visit end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("visit", visit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVisitWithMaxInKeys(final List<Integer> keys, final int visit, final int _max){
        return updateVisitWithMaxInKeys(keys, visit, _max, TABLENAME);
    }

    public int updateVisitWithMaxInKeys(final List<Integer> keys, final int visit, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET visit = (select case when visit+:visit>=:_max then :_max else visit+:visit end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("visit", visit);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateVisitWithMinMaxByKey(final int id, final int visit, final int _min, final int _max){
        return updateVisitWithMinMaxByKey(id, visit, _min, _max, TABLENAME);
    }

    public int updateVisitWithMinMaxByKey(final int id, final int visit, final int _min, final int _max, final String TABLENAME2){
        if( visit < 0 ) {
            return updateVisitWithMinByKey(id, visit, _min, TABLENAME2);
        } else {
            return updateVisitWithMaxByKey(id, visit, _max, TABLENAME2);
        }
    }

    public int updateVisitWithMinMaxInKeys(final List<Integer> keys, final int visit, final int _min, final int _max){
        return updateVisitWithMinMaxInKeys(keys, visit, _min, _max, TABLENAME);
    }

    public int updateVisitWithMinMaxInKeys(final List<Integer> keys, final int visit, final int _min, final int _max, final String TABLENAME2){
        if( visit < 0 ) {
            return updateVisitWithMinInKeys(keys, visit, _min, TABLENAME2);
        } else {
            return updateVisitWithMaxInKeys(keys, visit, _max, TABLENAME2);
        }
    }

    public int updatePraiseByKey(final int praise, final int id){
        return updatePraiseByKey(praise, id, TABLENAME);
    }

    public int updatePraiseByKey(final int praise, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET praise=praise+:praise WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("praise", praise);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePraiseWithMinByKey(final int id, final int praise, final int _min){
        return updatePraiseWithMinByKey(id, praise, _min, TABLENAME);
    }

    public int updatePraiseWithMinByKey(final int id, final int praise, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET praise = (select case when praise+:praise<=:_min then :_min else praise+:praise end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("praise", praise);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePraiseWithMinInKeys(final List<Integer> keys, final int praise, final int _min){
        return updatePraiseWithMinInKeys(keys, praise, _min, TABLENAME);
    }

    public int updatePraiseWithMinInKeys(final List<Integer> keys, final int praise, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET praise = (select case when praise+:praise<=:_min then :_min else praise+:praise end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("praise", praise);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePraiseWithMaxByKey(final int id, final int praise, final int _max){
        return updatePraiseWithMaxByKey(id, praise, _max, TABLENAME);
    }

    public int updatePraiseWithMaxByKey(final int id, final int praise, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET praise = (select case when praise+:praise>=:_max then :_max else praise+:praise end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("praise", praise);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePraiseWithMaxInKeys(final List<Integer> keys, final int praise, final int _max){
        return updatePraiseWithMaxInKeys(keys, praise, _max, TABLENAME);
    }

    public int updatePraiseWithMaxInKeys(final List<Integer> keys, final int praise, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET praise = (select case when praise+:praise>=:_max then :_max else praise+:praise end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("praise", praise);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePraiseWithMinMaxByKey(final int id, final int praise, final int _min, final int _max){
        return updatePraiseWithMinMaxByKey(id, praise, _min, _max, TABLENAME);
    }

    public int updatePraiseWithMinMaxByKey(final int id, final int praise, final int _min, final int _max, final String TABLENAME2){
        if( praise < 0 ) {
            return updatePraiseWithMinByKey(id, praise, _min, TABLENAME2);
        } else {
            return updatePraiseWithMaxByKey(id, praise, _max, TABLENAME2);
        }
    }

    public int updatePraiseWithMinMaxInKeys(final List<Integer> keys, final int praise, final int _min, final int _max){
        return updatePraiseWithMinMaxInKeys(keys, praise, _min, _max, TABLENAME);
    }

    public int updatePraiseWithMinMaxInKeys(final List<Integer> keys, final int praise, final int _min, final int _max, final String TABLENAME2){
        if( praise < 0 ) {
            return updatePraiseWithMinInKeys(keys, praise, _min, TABLENAME2);
        } else {
            return updatePraiseWithMaxInKeys(keys, praise, _max, TABLENAME2);
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

    public int updateNumExercisesByKey(final int numExercises, final int id){
        return updateNumExercisesByKey(numExercises, id, TABLENAME);
    }

    public int updateNumExercisesByKey(final int numExercises, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numExercises=numExercises+:numExercises WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("numExercises", numExercises);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumExercisesWithMinByKey(final int id, final int numExercises, final int _min){
        return updateNumExercisesWithMinByKey(id, numExercises, _min, TABLENAME);
    }

    public int updateNumExercisesWithMinByKey(final int id, final int numExercises, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numExercises = (select case when numExercises+:numExercises<=:_min then :_min else numExercises+:numExercises end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("numExercises", numExercises);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumExercisesWithMinInKeys(final List<Integer> keys, final int numExercises, final int _min){
        return updateNumExercisesWithMinInKeys(keys, numExercises, _min, TABLENAME);
    }

    public int updateNumExercisesWithMinInKeys(final List<Integer> keys, final int numExercises, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numExercises = (select case when numExercises+:numExercises<=:_min then :_min else numExercises+:numExercises end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("numExercises", numExercises);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumExercisesWithMaxByKey(final int id, final int numExercises, final int _max){
        return updateNumExercisesWithMaxByKey(id, numExercises, _max, TABLENAME);
    }

    public int updateNumExercisesWithMaxByKey(final int id, final int numExercises, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numExercises = (select case when numExercises+:numExercises>=:_max then :_max else numExercises+:numExercises end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("numExercises", numExercises);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumExercisesWithMaxInKeys(final List<Integer> keys, final int numExercises, final int _max){
        return updateNumExercisesWithMaxInKeys(keys, numExercises, _max, TABLENAME);
    }

    public int updateNumExercisesWithMaxInKeys(final List<Integer> keys, final int numExercises, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numExercises = (select case when numExercises+:numExercises>=:_max then :_max else numExercises+:numExercises end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("numExercises", numExercises);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumExercisesWithMinMaxByKey(final int id, final int numExercises, final int _min, final int _max){
        return updateNumExercisesWithMinMaxByKey(id, numExercises, _min, _max, TABLENAME);
    }

    public int updateNumExercisesWithMinMaxByKey(final int id, final int numExercises, final int _min, final int _max, final String TABLENAME2){
        if( numExercises < 0 ) {
            return updateNumExercisesWithMinByKey(id, numExercises, _min, TABLENAME2);
        } else {
            return updateNumExercisesWithMaxByKey(id, numExercises, _max, TABLENAME2);
        }
    }

    public int updateNumExercisesWithMinMaxInKeys(final List<Integer> keys, final int numExercises, final int _min, final int _max){
        return updateNumExercisesWithMinMaxInKeys(keys, numExercises, _min, _max, TABLENAME);
    }

    public int updateNumExercisesWithMinMaxInKeys(final List<Integer> keys, final int numExercises, final int _min, final int _max, final String TABLENAME2){
        if( numExercises < 0 ) {
            return updateNumExercisesWithMinInKeys(keys, numExercises, _min, TABLENAME2);
        } else {
            return updateNumExercisesWithMaxInKeys(keys, numExercises, _max, TABLENAME2);
        }
    }

    public int updateNumZhentiByKey(final int numZhenti, final int id){
        return updateNumZhentiByKey(numZhenti, id, TABLENAME);
    }

    public int updateNumZhentiByKey(final int numZhenti, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numZhenti=numZhenti+:numZhenti WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("numZhenti", numZhenti);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumZhentiWithMinByKey(final int id, final int numZhenti, final int _min){
        return updateNumZhentiWithMinByKey(id, numZhenti, _min, TABLENAME);
    }

    public int updateNumZhentiWithMinByKey(final int id, final int numZhenti, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numZhenti = (select case when numZhenti+:numZhenti<=:_min then :_min else numZhenti+:numZhenti end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("numZhenti", numZhenti);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumZhentiWithMinInKeys(final List<Integer> keys, final int numZhenti, final int _min){
        return updateNumZhentiWithMinInKeys(keys, numZhenti, _min, TABLENAME);
    }

    public int updateNumZhentiWithMinInKeys(final List<Integer> keys, final int numZhenti, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numZhenti = (select case when numZhenti+:numZhenti<=:_min then :_min else numZhenti+:numZhenti end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("numZhenti", numZhenti);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumZhentiWithMaxByKey(final int id, final int numZhenti, final int _max){
        return updateNumZhentiWithMaxByKey(id, numZhenti, _max, TABLENAME);
    }

    public int updateNumZhentiWithMaxByKey(final int id, final int numZhenti, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numZhenti = (select case when numZhenti+:numZhenti>=:_max then :_max else numZhenti+:numZhenti end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("numZhenti", numZhenti);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumZhentiWithMaxInKeys(final List<Integer> keys, final int numZhenti, final int _max){
        return updateNumZhentiWithMaxInKeys(keys, numZhenti, _max, TABLENAME);
    }

    public int updateNumZhentiWithMaxInKeys(final List<Integer> keys, final int numZhenti, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numZhenti = (select case when numZhenti+:numZhenti>=:_max then :_max else numZhenti+:numZhenti end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("numZhenti", numZhenti);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumZhentiWithMinMaxByKey(final int id, final int numZhenti, final int _min, final int _max){
        return updateNumZhentiWithMinMaxByKey(id, numZhenti, _min, _max, TABLENAME);
    }

    public int updateNumZhentiWithMinMaxByKey(final int id, final int numZhenti, final int _min, final int _max, final String TABLENAME2){
        if( numZhenti < 0 ) {
            return updateNumZhentiWithMinByKey(id, numZhenti, _min, TABLENAME2);
        } else {
            return updateNumZhentiWithMaxByKey(id, numZhenti, _max, TABLENAME2);
        }
    }

    public int updateNumZhentiWithMinMaxInKeys(final List<Integer> keys, final int numZhenti, final int _min, final int _max){
        return updateNumZhentiWithMinMaxInKeys(keys, numZhenti, _min, _max, TABLENAME);
    }

    public int updateNumZhentiWithMinMaxInKeys(final List<Integer> keys, final int numZhenti, final int _min, final int _max, final String TABLENAME2){
        if( numZhenti < 0 ) {
            return updateNumZhentiWithMinInKeys(keys, numZhenti, _min, TABLENAME2);
        } else {
            return updateNumZhentiWithMaxInKeys(keys, numZhenti, _max, TABLENAME2);
        }
    }

    public int updateNumSimulationByKey(final int numSimulation, final int id){
        return updateNumSimulationByKey(numSimulation, id, TABLENAME);
    }

    public int updateNumSimulationByKey(final int numSimulation, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numSimulation=numSimulation+:numSimulation WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("numSimulation", numSimulation);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumSimulationWithMinByKey(final int id, final int numSimulation, final int _min){
        return updateNumSimulationWithMinByKey(id, numSimulation, _min, TABLENAME);
    }

    public int updateNumSimulationWithMinByKey(final int id, final int numSimulation, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numSimulation = (select case when numSimulation+:numSimulation<=:_min then :_min else numSimulation+:numSimulation end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("numSimulation", numSimulation);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumSimulationWithMinInKeys(final List<Integer> keys, final int numSimulation, final int _min){
        return updateNumSimulationWithMinInKeys(keys, numSimulation, _min, TABLENAME);
    }

    public int updateNumSimulationWithMinInKeys(final List<Integer> keys, final int numSimulation, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numSimulation = (select case when numSimulation+:numSimulation<=:_min then :_min else numSimulation+:numSimulation end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("numSimulation", numSimulation);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumSimulationWithMaxByKey(final int id, final int numSimulation, final int _max){
        return updateNumSimulationWithMaxByKey(id, numSimulation, _max, TABLENAME);
    }

    public int updateNumSimulationWithMaxByKey(final int id, final int numSimulation, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numSimulation = (select case when numSimulation+:numSimulation>=:_max then :_max else numSimulation+:numSimulation end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("numSimulation", numSimulation);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumSimulationWithMaxInKeys(final List<Integer> keys, final int numSimulation, final int _max){
        return updateNumSimulationWithMaxInKeys(keys, numSimulation, _max, TABLENAME);
    }

    public int updateNumSimulationWithMaxInKeys(final List<Integer> keys, final int numSimulation, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numSimulation = (select case when numSimulation+:numSimulation>=:_max then :_max else numSimulation+:numSimulation end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("numSimulation", numSimulation);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumSimulationWithMinMaxByKey(final int id, final int numSimulation, final int _min, final int _max){
        return updateNumSimulationWithMinMaxByKey(id, numSimulation, _min, _max, TABLENAME);
    }

    public int updateNumSimulationWithMinMaxByKey(final int id, final int numSimulation, final int _min, final int _max, final String TABLENAME2){
        if( numSimulation < 0 ) {
            return updateNumSimulationWithMinByKey(id, numSimulation, _min, TABLENAME2);
        } else {
            return updateNumSimulationWithMaxByKey(id, numSimulation, _max, TABLENAME2);
        }
    }

    public int updateNumSimulationWithMinMaxInKeys(final List<Integer> keys, final int numSimulation, final int _min, final int _max){
        return updateNumSimulationWithMinMaxInKeys(keys, numSimulation, _min, _max, TABLENAME);
    }

    public int updateNumSimulationWithMinMaxInKeys(final List<Integer> keys, final int numSimulation, final int _min, final int _max, final String TABLENAME2){
        if( numSimulation < 0 ) {
            return updateNumSimulationWithMinInKeys(keys, numSimulation, _min, TABLENAME2);
        } else {
            return updateNumSimulationWithMaxInKeys(keys, numSimulation, _max, TABLENAME2);
        }
    }

    public int updateNumVastByKey(final int numVast, final int id){
        return updateNumVastByKey(numVast, id, TABLENAME);
    }

    public int updateNumVastByKey(final int numVast, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numVast=numVast+:numVast WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("numVast", numVast);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumVastWithMinByKey(final int id, final int numVast, final int _min){
        return updateNumVastWithMinByKey(id, numVast, _min, TABLENAME);
    }

    public int updateNumVastWithMinByKey(final int id, final int numVast, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numVast = (select case when numVast+:numVast<=:_min then :_min else numVast+:numVast end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("numVast", numVast);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumVastWithMinInKeys(final List<Integer> keys, final int numVast, final int _min){
        return updateNumVastWithMinInKeys(keys, numVast, _min, TABLENAME);
    }

    public int updateNumVastWithMinInKeys(final List<Integer> keys, final int numVast, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numVast = (select case when numVast+:numVast<=:_min then :_min else numVast+:numVast end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("numVast", numVast);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumVastWithMaxByKey(final int id, final int numVast, final int _max){
        return updateNumVastWithMaxByKey(id, numVast, _max, TABLENAME);
    }

    public int updateNumVastWithMaxByKey(final int id, final int numVast, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numVast = (select case when numVast+:numVast>=:_max then :_max else numVast+:numVast end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("numVast", numVast);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumVastWithMaxInKeys(final List<Integer> keys, final int numVast, final int _max){
        return updateNumVastWithMaxInKeys(keys, numVast, _max, TABLENAME);
    }

    public int updateNumVastWithMaxInKeys(final List<Integer> keys, final int numVast, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET numVast = (select case when numVast+:numVast>=:_max then :_max else numVast+:numVast end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("numVast", numVast);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumVastWithMinMaxByKey(final int id, final int numVast, final int _min, final int _max){
        return updateNumVastWithMinMaxByKey(id, numVast, _min, _max, TABLENAME);
    }

    public int updateNumVastWithMinMaxByKey(final int id, final int numVast, final int _min, final int _max, final String TABLENAME2){
        if( numVast < 0 ) {
            return updateNumVastWithMinByKey(id, numVast, _min, TABLENAME2);
        } else {
            return updateNumVastWithMaxByKey(id, numVast, _max, TABLENAME2);
        }
    }

    public int updateNumVastWithMinMaxInKeys(final List<Integer> keys, final int numVast, final int _min, final int _max){
        return updateNumVastWithMinMaxInKeys(keys, numVast, _min, _max, TABLENAME);
    }

    public int updateNumVastWithMinMaxInKeys(final List<Integer> keys, final int numVast, final int _min, final int _max, final String TABLENAME2){
        if( numVast < 0 ) {
            return updateNumVastWithMinInKeys(keys, numVast, _min, TABLENAME2);
        } else {
            return updateNumVastWithMaxInKeys(keys, numVast, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Kind> kinds) {
        return updateByKey(kinds, TABLENAME);
    }

    public int[] updateByKey (final List<Kind> kinds, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(kinds == null || kinds.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kclassid=:kclassid, nmKClass=:nmKClass, coursid=:coursid, productid=:productid, nmProduct=:nmProduct, lhubid=:lhubid, nmLhub=:nmLhub, price=:price, discount=:discount, kbi=:kbi, validity=:validity, imgurl=:imgurl, buycount=:buycount, visit=:visit, praise=:praise, examtypes=:examtypes, status=:status, numExercises=:numExercises, numZhenti=:numZhenti, numSimulation=:numSimulation, numVast=:numVast, createtime=:createtime, isHasITMS=:isHasITMS, examids=:examids WHERE id=:id");
            return super.batchUpdate2(sql.toString(), kinds);
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
                "	`kclassid`  INT(11) NOT NULL," +
                "	`nmKClass`  TINYTEXT NOT NULL," +
                "	`coursid`  INT(11) NOT NULL," +
                "	`productid`  INT(11) NOT NULL," +
                "	`nmProduct`  TINYTEXT NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`nmLhub`  TINYTEXT NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`discount`  DOUBLE NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`validity`  INT(11) NOT NULL," +
                "	`imgurl`  VARCHAR(128) NOT NULL," +
                "	`buycount`  INT(11) NOT NULL," +
                "	`visit`  INT(11) NOT NULL," +
                "	`praise`  INT(11) NOT NULL," +
                "	`examtypes`  TINYTEXT NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`numExercises`  INT(11) NOT NULL," +
                "	`numZhenti`  INT(11) NOT NULL," +
                "	`numSimulation`  INT(11) NOT NULL," +
                "	`numVast`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`isHasITMS`  BIT(1) NOT NULL," +
                "	`examids`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `kc_pr_lhub` (`kclassid`, `productid`, `lhubid`)," +
                "	KEY `kclassid` (`kclassid`)," +
                "	KEY `productid` (`productid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `productid_lhubid` (`productid`, `lhubid`)," +
                "	KEY `coursid` (`coursid`)" +
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
                "	`kclassid`  INT(11) NOT NULL," +
                "	`nmKClass`  TINYTEXT NOT NULL," +
                "	`coursid`  INT(11) NOT NULL," +
                "	`productid`  INT(11) NOT NULL," +
                "	`nmProduct`  TINYTEXT NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	`nmLhub`  TINYTEXT NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`discount`  DOUBLE NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`validity`  INT(11) NOT NULL," +
                "	`imgurl`  VARCHAR(128) NOT NULL," +
                "	`buycount`  INT(11) NOT NULL," +
                "	`visit`  INT(11) NOT NULL," +
                "	`praise`  INT(11) NOT NULL," +
                "	`examtypes`  TINYTEXT NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`numExercises`  INT(11) NOT NULL," +
                "	`numZhenti`  INT(11) NOT NULL," +
                "	`numSimulation`  INT(11) NOT NULL," +
                "	`numVast`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`isHasITMS`  BIT(1) NOT NULL," +
                "	`examids`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `kc_pr_lhub` (`kclassid`, `productid`, `lhubid`)," +
                "	KEY `kclassid` (`kclassid`)," +
                "	KEY `productid` (`productid`)," +
                "	KEY `lhubid` (`lhubid`)," +
                "	KEY `productid_lhubid` (`productid`, `lhubid`)," +
                "	KEY `coursid` (`coursid`)" +
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
