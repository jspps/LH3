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

import static com.learnhall.db.bean.Learnhub.Col;

//learnhall3_design - learnhub
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class LearnhubInternal extends InternalSupport{
    static Log log = LogFactory.getLog(LearnhubInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public LearnhubInternal(){}

    public static LearnhubDAO DAO(){
        return LearnhubEntity.LearnhubDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Learnhub[MAX];
    }
    private static Learnhub[] FIXED = new Learnhub[MAX];
    public static final Map<Integer, Learnhub> vars = newSortedMap();
    public static final Map<String, Integer> varsByCodeid = newSortedMap();
    public static final Map<Integer, Integer> varsByAccountid = newSortedMap();

    private static final List<Learnhub> fixedCache = newList();

    public static void put(Learnhub learnhub, boolean force){
        if(learnhub == null || learnhub.lhid <= 0) return ;

        int lhid = learnhub.lhid;
        if (cacheModel == STATIC_CACHE) {
            if (lhid > 0 && lhid <= FIXED.length) {
                if (FIXED[lhid - 1] == null || !FIXED[lhid - 1].equals(learnhub))
                	FIXED[lhid - 1] = learnhub;
                if (!fixedCache.contains(learnhub))
                	fixedCache.add(learnhub);
            }
        } else {
            vars.put(lhid, learnhub);
        }

        { // 单-唯一索引 remove old index codeid
          Object ov = learnhub.oldVal(Col.codeid);
          if(ov != null)
              varsByCodeid.remove(ov);
          if(ov != null || force){ // put new index
            String codeid = learnhub.getCodeid();
            varsByCodeid.put(codeid, lhid);
          }
        }

        { // 单-唯一索引 remove old index accountid
          Object ov = learnhub.oldVal(Col.accountid);
          if(ov != null)
              varsByAccountid.remove(ov);
          if(ov != null || force){ // put new index
            int accountid = learnhub.getAccountid();
            varsByAccountid.put(accountid, lhid);
          }
        }

        // LASTID = lhid > LASTID ? lhid : LASTID;
        if (lhid > LASTID.get()) LASTID.set(lhid);
    }

    public static void clear(){
        varsByCodeid.clear();
        varsByAccountid.clear();
        FIXED = new Learnhub[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(LearnhubDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(LearnhubDAO DAO, String TABLENAME2){
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

    public static void relocate(LearnhubDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        LearnhubDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(LearnhubDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        LearnhubDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(LearnhubDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        LearnhubDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(LearnhubDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(LearnhubDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(LearnhubDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(LearnhubDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(LearnhubDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        LearnhubDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(LearnhubDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Learnhub[maxId(DAO)];

        List<Learnhub> learnhubs = DAO.selectAll();
        for (Learnhub learnhub : learnhubs) {
            learnhub.reset();
            put(learnhub, true);
        }
    }

    public static Map toMap(Learnhub learnhub){
        return learnhub.toMap();
    }

    public static List<Map> toMap(List<Learnhub> learnhubs){
        List<Map> ret = new Vector<Map>();
        for (Learnhub learnhub : learnhubs){
            Map e = learnhub.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Learnhub> sortZh(List<Learnhub> learnhubs, final String key) {
        Collections.sort(learnhubs, new Comparator<Learnhub>() {
            public int compare(Learnhub o1, Learnhub o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return learnhubs;
    }

    public static List<Learnhub> sort(List<Learnhub> learnhubs, final String key) {
        Collections.sort(learnhubs, new Comparator<Learnhub>() {
            public int compare(Learnhub o1, Learnhub o2) {
                return o1.compareTo(o2, key);
            }
        });
        return learnhubs;
    }

    public static List<Learnhub> sort(List<Learnhub> learnhubs){
        Collections.sort(learnhubs, new Comparator<Learnhub>(){
            public int compare(Learnhub o1, Learnhub o2) {
                Object v1 = o1.lhid;
                Object v2 = o2.lhid;
                return compareTo(v1, v2);
            }
        });
        return learnhubs;
    }

    public static List<Learnhub> sortReverse(List<Learnhub> learnhubs){
        Collections.sort(learnhubs, new Comparator<Learnhub>(){
            public int compare(Learnhub o1, Learnhub o2) {
                Object v1 = o1.lhid;
                Object v2 = o2.lhid;
                return 0 - compareTo(v1, v2);
            }
        });
        return learnhubs;
    }

    public static List<Learnhub> sortCodeid(List<Learnhub> learnhubs){
        Collections.sort(learnhubs, new Comparator<Learnhub>(){
            public int compare(Learnhub o1, Learnhub o2) {
                Object v1 = o1.getCodeid();
                Object v2 = o2.getCodeid();
                return compareTo(v1, v2);
            }
        });
        return learnhubs;
    }

    public static List<Learnhub> sortCodeidRo(List<Learnhub> learnhubs){
        Collections.sort(learnhubs, new Comparator<Learnhub>(){
            public int compare(Learnhub o1, Learnhub o2) {
                Object v1 = o1.getCodeid();
                Object v2 = o2.getCodeid();
                return 0 - compareTo(v1, v2);
            };
        });
        return learnhubs;
    }

    public static List<Learnhub> sortAccountid(List<Learnhub> learnhubs){
        Collections.sort(learnhubs, new Comparator<Learnhub>(){
            public int compare(Learnhub o1, Learnhub o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return compareTo(v1, v2);
            }
        });
        return learnhubs;
    }

    public static List<Learnhub> sortAccountidRo(List<Learnhub> learnhubs){
        Collections.sort(learnhubs, new Comparator<Learnhub>(){
            public int compare(Learnhub o1, Learnhub o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return 0 - compareTo(v1, v2);
            };
        });
        return learnhubs;
    }

    public static Learnhub insert(Learnhub learnhub) {
        LearnhubDAO DAO = DAO();
        return insert(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub insert(LearnhubDAO DAO, Learnhub learnhub) {
        return insert(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub insert(Learnhub learnhub, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return insert(DAO, learnhub, TABLENAME2);
    }

    public static Learnhub insert(LearnhubDAO DAO, Learnhub learnhub, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(learnhub, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        learnhub.lhid = n;
        if(cacheModel != NO_CACHE) put(learnhub, true);

        return learnhub;
    }

    public static Learnhub asyncInsert(Learnhub learnhub) {
        LearnhubDAO DAO = DAO();
        return asyncInsert(DAO, learnhub, DAO.TABLENAME);
    }
    public static Learnhub asyncInsert(LearnhubDAO DAO, Learnhub learnhub) {
        return asyncInsert(DAO, learnhub, DAO.TABLENAME);
    }
    public static Learnhub asyncInsert(Learnhub learnhub, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return asyncInsert(DAO, learnhub, TABLENAME2);
    }
    public static Learnhub asyncInsert(LearnhubDAO DAO, Learnhub learnhub, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(learnhub, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        learnhub.lhid = n;
        if(cacheModel != NO_CACHE) put(learnhub, true);
        return learnhub;
    }
    public static Learnhub insert2(Learnhub learnhub) {
        LearnhubDAO DAO = DAO();
        return insert2(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub insert2(LearnhubDAO DAO, Learnhub learnhub) {
        return insert2(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub insert2(Learnhub learnhub, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return insert2(DAO, learnhub, TABLENAME2);
    }

    public static Learnhub insert2(LearnhubDAO DAO, Learnhub learnhub, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(learnhub, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        learnhub.lhid = n;
        if(cacheModel != NO_CACHE) put(learnhub, true);

        return learnhub;
    }

    public static Learnhub asyncInsert2(Learnhub learnhub) {
        LearnhubDAO DAO = DAO();
        return asyncInsert2(DAO, learnhub, DAO.TABLENAME);
    }
    public static Learnhub asyncInsert2(LearnhubDAO DAO, Learnhub learnhub) {
        return asyncInsert2(DAO, learnhub, DAO.TABLENAME);
    }
    public static Learnhub asyncInsert2(Learnhub learnhub, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return asyncInsert2(DAO, learnhub, TABLENAME2);
    }
    public static Learnhub asyncInsert2(LearnhubDAO DAO, Learnhub learnhub, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        learnhub.lhid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(learnhub, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(learnhub, true);
        return learnhub;
    }
    public static int[] insert(List<Learnhub> learnhubs) {
        LearnhubDAO DAO = DAO();
        return insert(DAO, learnhubs, DAO.TABLENAME);
    }

    public static int[] insert(LearnhubDAO DAO, List<Learnhub> learnhubs) {
        return insert(DAO, learnhubs, DAO.TABLENAME);
    }

    public static int[] insert(List<Learnhub> learnhubs, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return insert(DAO, learnhubs, TABLENAME2);
    }

    public static int[] insert(LearnhubDAO DAO, List<Learnhub> learnhubs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(learnhubs, TABLENAME2);
            int n = 0;
            for(Learnhub learnhub : learnhubs){
                learnhub.lhid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[learnhubs.size()];
        int n = 0;
        for(Learnhub learnhub : learnhubs){
            learnhub = insert(DAO, learnhub, TABLENAME2);
            ret[n++] = (learnhub == null) ? 0 : (int)learnhub.lhid;
        }
        return ret;
    }

    public static int delete(Learnhub learnhub) {
        int lhid = learnhub.lhid;
        LearnhubDAO DAO = DAO();
        return delete(DAO, lhid, DAO.TABLENAME);
    }

    public static int delete(int lhid) {
        LearnhubDAO DAO = DAO();
        return delete(DAO, lhid, DAO.TABLENAME);
    }

    public static int delete(LearnhubDAO DAO, int lhid) {
        return delete(DAO, lhid, DAO.TABLENAME);
    }

    public static int delete(int lhid, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return delete(DAO, lhid, TABLENAME2);
    }

    public static int delete(LearnhubDAO DAO, int lhid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(lhid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(lhid);
        }
        return n;
    }

    public static void asyncDelete(Learnhub learnhub) {
        int lhid = learnhub.lhid;
        LearnhubDAO DAO = DAO();
        asyncDelete(DAO, lhid, DAO.TABLENAME);
    }

    public static void asyncDelete(int lhid) {
        LearnhubDAO DAO = DAO();
        asyncDelete(DAO, lhid, DAO.TABLENAME);
    }

    public static void asyncDelete(LearnhubDAO DAO, int lhid) {
        asyncDelete(DAO, lhid, DAO.TABLENAME);
    }

    public static void asyncDelete(int lhid, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        asyncDelete(DAO, lhid, TABLENAME2);
    }

    public static void asyncDelete(LearnhubDAO DAO, int lhid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(lhid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(lhid);
        }
    }

    public static int[] delete(int[] lhids) {
        LearnhubDAO DAO = DAO();
        return delete(DAO, lhids, DAO.TABLENAME);
    }

    public static int[] delete(LearnhubDAO DAO, int[] lhids) {
        return delete(DAO, lhids, DAO.TABLENAME);
    }

    public static int[] delete(int[] lhids,String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return delete(DAO, lhids, TABLENAME2);
    }

    public static int[] delete(LearnhubDAO DAO, int[] lhids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(lhids, TABLENAME2);
        }
        int[] ret = new int[lhids.length];
        int n = 0;
        for(int lhid : lhids){
            ret[n++] = delete(DAO, lhid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        LearnhubDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, LearnhubDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final LearnhubDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer lhid : keys){
                deleteFromMemory(lhid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Learnhub> beans) {
        LearnhubDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Learnhub> beans, LearnhubDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Learnhub> beans, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Learnhub> beans, final LearnhubDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Learnhub learnhub : beans){
                int lhid = learnhub.lhid;
                deleteFromMemory(lhid);
            }
        }
        return result;
    }

    public static List<Learnhub> getAll() {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getAll(LearnhubDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getAll(String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Learnhub> getAll(final LearnhubDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Learnhub> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Learnhub> getNoSortAll() {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getNoSortAll(LearnhubDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getNoSortAll(String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Learnhub> getNoSortAll(final LearnhubDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Learnhub> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Learnhub> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Learnhub learnhub = FIXED[i];
                if (learnhub != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Learnhub> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Learnhub> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(LearnhubDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final LearnhubDAO DAO, final String TABLENAME2) {
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
        LearnhubDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(LearnhubDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final LearnhubDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Learnhub> getIn(List<Integer> keys) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getIn(List<Integer> keys, LearnhubDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getIn(List<Integer> keys, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Learnhub> getIn(final List<Integer> keys, final LearnhubDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Learnhub> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Learnhub> getNoSortIn(List<Integer> keys) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getNoSortIn(List<Integer> keys, LearnhubDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Learnhub> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Learnhub> getNoSortIn(final List<Integer> keys, final LearnhubDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Learnhub> result = newList();
            for (int key : keys) {
                Learnhub learnhub = getByKeyFromMemory(key);
                if( learnhub == null ) continue;
                result.add(learnhub);
            }
            return result;
        }
    }

    public static List<Learnhub> getLast(int num) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Learnhub> getLast(LearnhubDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Learnhub> getLast(int num, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Learnhub> getLast(final LearnhubDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Learnhub> result = newList();
            List<Learnhub> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Learnhub last() {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Learnhub last(LearnhubDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Learnhub last(String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Learnhub last(final LearnhubDAO DAO, final String TABLENAME2) {
        Learnhub result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        LearnhubDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(LearnhubDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final LearnhubDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Learnhub> getGtKey(int lhid) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, lhid, DAO.TABLENAME);
    }

    public static List<Learnhub> getGtKey(LearnhubDAO DAO, int lhid) {
        return getGtKey(DAO, lhid, DAO.TABLENAME);
    }

    public static List<Learnhub> getGtKey(int lhid, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, lhid, TABLENAME2);
    }

    public static List<Learnhub> getGtKey(final LearnhubDAO DAO, final int lhid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(lhid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Learnhub> result = newList();
            List<Learnhub> learnhubs = getAll();
            for (Learnhub learnhub : learnhubs) {
                if(learnhub.lhid <= lhid) continue;
                result.add(learnhub);
            }
            return result;
        }
    }

    public static Learnhub getByKey(int lhid) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, lhid, DAO.TABLENAME);
    }

    public static Future<Learnhub> asyncGetByKey(final int lhid) {
        Future<Learnhub> f = Async.exec(new Callable<Learnhub>() {
            public Learnhub call() throws Exception {
                return getByKey(lhid);
            }
        });
        return f;
    }

    public static Learnhub getByKey(LearnhubDAO DAO, int lhid) {
        return getByKey(DAO, lhid, DAO.TABLENAME);
    }

    public static Learnhub getByKey(int lhid, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, lhid, TABLENAME2);
    }

    public static Learnhub getByKey(final LearnhubDAO DAO, final int lhid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(lhid, TABLENAME2);
        }
        return getByKeyFromMemory(lhid);
    }

    public static Learnhub getByKeyFromMemory(final int lhid) {
        if (cacheModel == STATIC_CACHE) {
            if (lhid < 1 || FIXED == null || FIXED.length < lhid) return null;
            return FIXED[lhid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(lhid);
        }
        return null;
    }

    public static Learnhub deleteFromMemory(final int lhid) {
        Learnhub learnhub;
        if (cacheModel == STATIC_CACHE) {
            if (lhid < 1 || FIXED == null || FIXED.length < lhid || FIXED[lhid-1]==null) return null;
            learnhub = FIXED[lhid - 1];
            FIXED[lhid - 1] = null;
            fixedCache.remove(learnhub);
        } else {
            learnhub = vars.remove(lhid);
        }
        if(learnhub == null) return null;

        String codeid = learnhub.getCodeid();
        varsByCodeid.remove(codeid);

        int accountid = learnhub.getAccountid();
        varsByAccountid.remove(accountid);

        return learnhub;
    }

    public static List<Learnhub> getByPage(int page, int size) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Learnhub> getByPage(LearnhubDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Learnhub> getByPage(int page, int size, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Learnhub> getByPage(final LearnhubDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Learnhub> result = newList();
            List<Learnhub> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(LearnhubDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final LearnhubDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Learnhub getByCodeid(String codeid) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCodeid(DAO, codeid, DAO.TABLENAME);
    }

    public static Learnhub getByCodeid(LearnhubDAO DAO, String codeid) {
        return getByCodeid(DAO, codeid, DAO.TABLENAME);
    }

    public static Learnhub getByCodeid(String codeid, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCodeid(DAO, codeid, TABLENAME2);
    }

    public static Learnhub getByCodeid(final LearnhubDAO DAO, final String codeid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCodeid(codeid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer lhid = varsByCodeid.get(codeid);
            if(lhid == null) return null;
            Learnhub result = getByKey(DAO, lhid, TABLENAME2);
            if(result == null) return null;
            if(!result.getCodeid().equals(codeid)){
                varsByCodeid.remove(codeid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeCodeid(String codeid) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCodeid(DAO, codeid, DAO.TABLENAME);
    }

    public static int countLikeCodeid(LearnhubDAO DAO, String codeid) {
        return countLikeCodeid(DAO, codeid, DAO.TABLENAME);
    }

    public static int countLikeCodeid(String codeid, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCodeid(DAO, codeid, TABLENAME2);
    }

    public static int countLikeCodeid(final LearnhubDAO DAO, final String codeid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeCodeid(codeid, TABLENAME2);
        }
        List<Learnhub> learnhubs = getLikeCodeid(DAO, codeid, TABLENAME2);
        return learnhubs.size();
    }

    public static List<Learnhub> getLikeCodeid(String codeid) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCodeid(DAO, codeid, DAO.TABLENAME);
    }

    public static List<Learnhub> getLikeCodeid(LearnhubDAO DAO, String codeid) {
        return getLikeCodeid(DAO, codeid, DAO.TABLENAME);
    }

    public static List<Learnhub> getLikeCodeid(String codeid, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCodeid(DAO, codeid, TABLENAME2);
    }

    public static List<Learnhub> getLikeCodeid(final LearnhubDAO DAO, final String codeid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeCodeid(codeid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Learnhub> result = newList();
            List<Learnhub> learnhubs = getAll(DAO, TABLENAME2);
            for(Learnhub e : learnhubs){
                String _var = e.getCodeid();
                if(_var.indexOf(codeid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Learnhub getByAccountid(Integer accountid) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Learnhub getByAccountid(LearnhubDAO DAO, Integer accountid) {
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Learnhub getByAccountid(Integer accountid, String TABLENAME2) {
        LearnhubDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, TABLENAME2);
    }

    public static Learnhub getByAccountid(final LearnhubDAO DAO, final int accountid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAccountid(accountid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer lhid = varsByAccountid.get(accountid);
            if(lhid == null) return null;
            Learnhub result = getByKey(DAO, lhid, TABLENAME2);
            if(result == null) return null;
            if(result.getAccountid() != accountid){
                varsByAccountid.remove(accountid);
                return null;
            }
            return result;
        }
    }

    public static Learnhub update(Learnhub learnhub) {
        LearnhubDAO DAO = DAO();
        return update(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub update(LearnhubDAO DAO, Learnhub learnhub) {
        return update(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub update(Learnhub learnhub, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return update(DAO, learnhub, TABLENAME2);
    }

    public static Learnhub update(final LearnhubDAO DAO, final Learnhub learnhub,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(learnhub, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(learnhub, TABLENAME2);
            if(n == -1) 
                return learnhub;
            else if(n <= 0) 
                return null;
        }
        return learnhub;
    }

    public static Learnhub asyncUpdate(Learnhub learnhub) {
        LearnhubDAO DAO = DAO();
        return asyncUpdate(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub asyncUpdate(LearnhubDAO DAO, Learnhub learnhub) {
        return asyncUpdate(DAO, learnhub, DAO.TABLENAME);
    }

    public static Learnhub asyncUpdate(Learnhub learnhub, String TABLENAME2) {
        LearnhubDAO DAO = DAO();
        return asyncUpdate(DAO, learnhub, TABLENAME2);
    }

    public static Learnhub asyncUpdate(final LearnhubDAO DAO, final Learnhub learnhub,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(learnhub, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(learnhub, TABLENAME2);
        }
        return learnhub;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        LearnhubDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(LearnhubDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final LearnhubDAO DAO, final String TABLENAME2) {
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

        List<Learnhub> learnhubs = getAll();
        for (Learnhub learnhub : learnhubs) {
            int n = DAO.insert2(learnhub, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
