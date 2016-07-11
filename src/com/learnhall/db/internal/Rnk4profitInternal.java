package com.learnhall.db.internal;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.apache.commons.logging.*;

import com.bowlong.sql.*;
import com.bowlong.concurrent.async.*;
import static com.bowlong.sql.CacheModel.*;

import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.entity.*;

import static com.learnhall.db.bean.Rnk4profit.Col;

//learnhall3_design - rnk4profit
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Rnk4profitInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Rnk4profitInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Rnk4profitInternal(){}

    public static Rnk4profitDAO DAO(){
        return Rnk4profitEntity.Rnk4profitDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Rnk4profit[MAX];
    }
    private static Rnk4profit[] FIXED = new Rnk4profit[MAX];
    public static final Map<Integer, Rnk4profit> vars = newSortedMap();
    public static final Map<String, Integer> varsByTypeOwnerid = newSortedMap();

    private static final List<Rnk4profit> fixedCache = newList();

    public static void put(Rnk4profit rnk4profit, boolean force){
        if(rnk4profit == null || rnk4profit.id <= 0) return ;

        int id = rnk4profit.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(rnk4profit))
                	FIXED[id - 1] = rnk4profit;
                if (!fixedCache.contains(rnk4profit))
                	fixedCache.add(rnk4profit);
            }
        } else {
            vars.put(id, rnk4profit);
        }

        { // type_ownerid
          boolean bChanged = rnk4profit.inChanged(Col.type, Col.ownerid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vtype = rnk4profit.oldOrNew(Col.type);
            Object vownerid = rnk4profit.oldOrNew(Col.ownerid);
            String okey = com.bowlong.lang.PStr.b().a(vtype, "-", vownerid).e();
            varsByTypeOwnerid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vtype = rnk4profit.getType();
              int vownerid = rnk4profit.getOwnerid();
              String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vownerid).e();
              varsByTypeOwnerid.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTypeOwnerid.clear();
        FIXED = new Rnk4profit[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Rnk4profitDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Rnk4profitDAO DAO, String TABLENAME2){
        if( cacheModel == NO_CACHE ){
            return DAO.count(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            return fixedCache.size();
        } else {  // FULL_CACHE || FULL_MEMORY
            return vars.size();
        }
    }

    public static void relocate(String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static void relocate(Rnk4profitDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Rnk4profitDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Rnk4profitDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Rnk4profitDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Rnk4profitDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Rnk4profitDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Rnk4profitDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Rnk4profitDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Rnk4profitDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Rnk4profitDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Rnk4profitDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Rnk4profitDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Rnk4profitDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Rnk4profit[maxId(DAO)];

        List<Rnk4profit> rnk4profits = DAO.selectAll();
        for (Rnk4profit rnk4profit : rnk4profits) {
            rnk4profit.reset();
            put(rnk4profit, true);
        }
    }

    public static Map toMap(Rnk4profit rnk4profit){
        return rnk4profit.toMap();
    }

    public static List<Map> toMap(List<Rnk4profit> rnk4profits){
        List<Map> ret = new Vector<Map>();
        for (Rnk4profit rnk4profit : rnk4profits){
            Map e = rnk4profit.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Rnk4profit> sortZh(List<Rnk4profit> rnk4profits, final String key) {
        Collections.sort(rnk4profits, new Comparator<Rnk4profit>() {
            public int compare(Rnk4profit o1, Rnk4profit o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rnk4profits;
    }

    public static List<Rnk4profit> sort(List<Rnk4profit> rnk4profits, final String key) {
        Collections.sort(rnk4profits, new Comparator<Rnk4profit>() {
            public int compare(Rnk4profit o1, Rnk4profit o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rnk4profits;
    }

    public static List<Rnk4profit> sort(List<Rnk4profit> rnk4profits){
        Collections.sort(rnk4profits, new Comparator<Rnk4profit>(){
            public int compare(Rnk4profit o1, Rnk4profit o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rnk4profits;
    }

    public static List<Rnk4profit> sortReverse(List<Rnk4profit> rnk4profits){
        Collections.sort(rnk4profits, new Comparator<Rnk4profit>(){
            public int compare(Rnk4profit o1, Rnk4profit o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rnk4profits;
    }

    public static Rnk4profit insert(Rnk4profit rnk4profit) {
        Rnk4profitDAO DAO = DAO();
        return insert(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit insert(Rnk4profitDAO DAO, Rnk4profit rnk4profit) {
        return insert(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit insert(Rnk4profit rnk4profit, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return insert(DAO, rnk4profit, TABLENAME2);
    }

    public static Rnk4profit insert(Rnk4profitDAO DAO, Rnk4profit rnk4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(rnk4profit, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rnk4profit.id = n;
        if(cacheModel != NO_CACHE) put(rnk4profit, true);

        return rnk4profit;
    }

    public static Rnk4profit asyncInsert(Rnk4profit rnk4profit) {
        Rnk4profitDAO DAO = DAO();
        return asyncInsert(DAO, rnk4profit, DAO.TABLENAME);
    }
    public static Rnk4profit asyncInsert(Rnk4profitDAO DAO, Rnk4profit rnk4profit) {
        return asyncInsert(DAO, rnk4profit, DAO.TABLENAME);
    }
    public static Rnk4profit asyncInsert(Rnk4profit rnk4profit, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return asyncInsert(DAO, rnk4profit, TABLENAME2);
    }
    public static Rnk4profit asyncInsert(Rnk4profitDAO DAO, Rnk4profit rnk4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(rnk4profit, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        rnk4profit.id = n;
        if(cacheModel != NO_CACHE) put(rnk4profit, true);
        return rnk4profit;
    }
    public static Rnk4profit insert2(Rnk4profit rnk4profit) {
        Rnk4profitDAO DAO = DAO();
        return insert2(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit insert2(Rnk4profitDAO DAO, Rnk4profit rnk4profit) {
        return insert2(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit insert2(Rnk4profit rnk4profit, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return insert2(DAO, rnk4profit, TABLENAME2);
    }

    public static Rnk4profit insert2(Rnk4profitDAO DAO, Rnk4profit rnk4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(rnk4profit, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rnk4profit.id = n;
        if(cacheModel != NO_CACHE) put(rnk4profit, true);

        return rnk4profit;
    }

    public static Rnk4profit asyncInsert2(Rnk4profit rnk4profit) {
        Rnk4profitDAO DAO = DAO();
        return asyncInsert2(DAO, rnk4profit, DAO.TABLENAME);
    }
    public static Rnk4profit asyncInsert2(Rnk4profitDAO DAO, Rnk4profit rnk4profit) {
        return asyncInsert2(DAO, rnk4profit, DAO.TABLENAME);
    }
    public static Rnk4profit asyncInsert2(Rnk4profit rnk4profit, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return asyncInsert2(DAO, rnk4profit, TABLENAME2);
    }
    public static Rnk4profit asyncInsert2(Rnk4profitDAO DAO, Rnk4profit rnk4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        rnk4profit.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(rnk4profit, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(rnk4profit, true);
        return rnk4profit;
    }
    public static int[] insert(List<Rnk4profit> rnk4profits) {
        Rnk4profitDAO DAO = DAO();
        return insert(DAO, rnk4profits, DAO.TABLENAME);
    }

    public static int[] insert(Rnk4profitDAO DAO, List<Rnk4profit> rnk4profits) {
        return insert(DAO, rnk4profits, DAO.TABLENAME);
    }

    public static int[] insert(List<Rnk4profit> rnk4profits, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return insert(DAO, rnk4profits, TABLENAME2);
    }

    public static int[] insert(Rnk4profitDAO DAO, List<Rnk4profit> rnk4profits, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rnk4profits, TABLENAME2);
            int n = 0;
            for(Rnk4profit rnk4profit : rnk4profits){
                rnk4profit.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rnk4profits.size()];
        int n = 0;
        for(Rnk4profit rnk4profit : rnk4profits){
            rnk4profit = insert(DAO, rnk4profit, TABLENAME2);
            ret[n++] = (rnk4profit == null) ? 0 : (int)rnk4profit.id;
        }
        return ret;
    }

    public static int delete(Rnk4profit rnk4profit) {
        int id = rnk4profit.id;
        Rnk4profitDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Rnk4profitDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Rnk4profitDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Rnk4profitDAO DAO, int id, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(id, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
        return n;
    }

    public static void asyncDelete(Rnk4profit rnk4profit) {
        int id = rnk4profit.id;
        Rnk4profitDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Rnk4profitDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Rnk4profitDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Rnk4profitDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Rnk4profitDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Rnk4profitDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Rnk4profitDAO DAO, int[] ids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(ids, TABLENAME2);
        }
        int[] ret = new int[ids.length];
        int n = 0;
        for(int id : ids){
            ret[n++] = delete(DAO, id, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        Rnk4profitDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Rnk4profitDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Rnk4profitDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Rnk4profit> beans) {
        Rnk4profitDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rnk4profit> beans, Rnk4profitDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rnk4profit> beans, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Rnk4profit> beans, final Rnk4profitDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Rnk4profit rnk4profit : beans){
                int id = rnk4profit.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Rnk4profit> getAll() {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getAll(Rnk4profitDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getAll(String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Rnk4profit> getAll(final Rnk4profitDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rnk4profit> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rnk4profit> getNoSortAll() {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getNoSortAll(Rnk4profitDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getNoSortAll(String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Rnk4profit> getNoSortAll(final Rnk4profitDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Rnk4profit> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rnk4profit> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Rnk4profit rnk4profit = FIXED[i];
                if (rnk4profit != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Rnk4profit> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Rnk4profit> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Rnk4profitDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Rnk4profitDAO DAO, final String TABLENAME2) {
        if ( cacheModel == NO_CACHE) { 
            return DAO.selectPKs(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Integer> result = newList();
            result.addAll(memoryKeys());
            return result;
        } else {
            List<Integer> result = newList();
            result.addAll(vars.keySet());
            return result;
        }
    }

    public static List<Map> getInIndex() {
        Rnk4profitDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Rnk4profitDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Rnk4profitDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Rnk4profit> getIn(List<Integer> keys) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getIn(List<Integer> keys, Rnk4profitDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getIn(List<Integer> keys, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Rnk4profit> getIn(final List<Integer> keys, final Rnk4profitDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rnk4profit> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rnk4profit> getNoSortIn(List<Integer> keys) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getNoSortIn(List<Integer> keys, Rnk4profitDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Rnk4profit> getNoSortIn(final List<Integer> keys, final Rnk4profitDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Rnk4profit> result = newList();
            for (int key : keys) {
                Rnk4profit rnk4profit = getByKeyFromMemory(key);
                if( rnk4profit == null ) continue;
                result.add(rnk4profit);
            }
            return result;
        }
    }

    public static List<Rnk4profit> getLast(int num) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getLast(Rnk4profitDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getLast(int num, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Rnk4profit> getLast(final Rnk4profitDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Rnk4profit> result = newList();
            List<Rnk4profit> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Rnk4profit last() {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Rnk4profit last(Rnk4profitDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Rnk4profit last(String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Rnk4profit last(final Rnk4profitDAO DAO, final String TABLENAME2) {
        Rnk4profit result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Rnk4profitDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Rnk4profitDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Rnk4profitDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Rnk4profit> getGtKey(int id) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getGtKey(Rnk4profitDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getGtKey(int id, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Rnk4profit> getGtKey(final Rnk4profitDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rnk4profit> result = newList();
            List<Rnk4profit> rnk4profits = getAll();
            for (Rnk4profit rnk4profit : rnk4profits) {
                if(rnk4profit.id <= id) continue;
                result.add(rnk4profit);
            }
            return result;
        }
    }

    public static Rnk4profit getByKey(int id) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Rnk4profit> asyncGetByKey(final int id) {
        Future<Rnk4profit> f = Async.exec(new Callable<Rnk4profit>() {
            public Rnk4profit call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Rnk4profit getByKey(Rnk4profitDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rnk4profit getByKey(int id, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Rnk4profit getByKey(final Rnk4profitDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Rnk4profit getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Rnk4profit deleteFromMemory(final int id) {
        Rnk4profit rnk4profit;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            rnk4profit = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(rnk4profit);
        } else {
            rnk4profit = vars.remove(id);
        }
        if(rnk4profit == null) return null;

        { // type_ownerid
            int vtype = rnk4profit.getType();
            int vownerid = rnk4profit.getOwnerid();
            String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vownerid).e();
            varsByTypeOwnerid.remove(vkey);
        }

        return rnk4profit;
    }

    public static List<Rnk4profit> getByPage(int page, int size) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getByPage(Rnk4profitDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rnk4profit> getByPage(int page, int size, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Rnk4profit> getByPage(final Rnk4profitDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rnk4profit> result = newList();
            List<Rnk4profit> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Rnk4profitDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Rnk4profitDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Rnk4profit getByTypeOwnerid(Integer type, Integer ownerid) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeOwnerid(DAO, type, ownerid, DAO.TABLENAME);
    }

    public static Rnk4profit getByTypeOwnerid(Rnk4profitDAO DAO, Integer type, Integer ownerid) {
        return getByTypeOwnerid(DAO, type, ownerid, DAO.TABLENAME);
    }

    public static Rnk4profit getByTypeOwnerid(Integer type, Integer ownerid, String TABLENAME2) {
        Rnk4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeOwnerid(DAO, type, ownerid, TABLENAME2);
    }

    public static Rnk4profit getByTypeOwnerid(final Rnk4profitDAO DAO, Integer type, Integer ownerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeOwnerid(type, ownerid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = type+"-"+ownerid;
            Integer id = varsByTypeOwnerid.get(vkey);
            if(id == null) return null;
            Rnk4profit result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getType() != type){
                varsByTypeOwnerid.remove(vkey);
                return null;
            }
            if(result.getOwnerid() != ownerid){
                varsByTypeOwnerid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Rnk4profit update(Rnk4profit rnk4profit) {
        Rnk4profitDAO DAO = DAO();
        return update(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit update(Rnk4profitDAO DAO, Rnk4profit rnk4profit) {
        return update(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit update(Rnk4profit rnk4profit, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return update(DAO, rnk4profit, TABLENAME2);
    }

    public static Rnk4profit update(final Rnk4profitDAO DAO, final Rnk4profit rnk4profit,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rnk4profit, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(rnk4profit, TABLENAME2);
            if(n == -1) 
                return rnk4profit;
            else if(n <= 0) 
                return null;
        }
        return rnk4profit;
    }

    public static Rnk4profit asyncUpdate(Rnk4profit rnk4profit) {
        Rnk4profitDAO DAO = DAO();
        return asyncUpdate(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit asyncUpdate(Rnk4profitDAO DAO, Rnk4profit rnk4profit) {
        return asyncUpdate(DAO, rnk4profit, DAO.TABLENAME);
    }

    public static Rnk4profit asyncUpdate(Rnk4profit rnk4profit, String TABLENAME2) {
        Rnk4profitDAO DAO = DAO();
        return asyncUpdate(DAO, rnk4profit, TABLENAME2);
    }

    public static Rnk4profit asyncUpdate(final Rnk4profitDAO DAO, final Rnk4profit rnk4profit,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rnk4profit, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(rnk4profit, TABLENAME2);
        }
        return rnk4profit;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Rnk4profitDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Rnk4profitDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Rnk4profitDAO DAO, final String TABLENAME2) {
        int result = 0;
        if(cacheModel != FULL_MEMORY)
            return result;
        try {
            DAO.truncate(TABLENAME2);
            DAO.repair(TABLENAME2);
            DAO.optimize(TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }

        List<Rnk4profit> rnk4profits = getAll();
        for (Rnk4profit rnk4profit : rnk4profits) {
            int n = DAO.insert2(rnk4profit, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
