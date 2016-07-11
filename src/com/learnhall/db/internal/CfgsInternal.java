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


//learnhall3_design - cfgs
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class CfgsInternal extends InternalSupport{
    static Log log = LogFactory.getLog(CfgsInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public CfgsInternal(){}

    public static CfgsDAO DAO(){
        return CfgsEntity.CfgsDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Cfgs[MAX];
    }
    private static Cfgs[] FIXED = new Cfgs[MAX];
    public static final Map<Integer, Cfgs> vars = newSortedMap();

    private static final List<Cfgs> fixedCache = newList();

    public static void put(Cfgs cfgs, boolean force){
        if(cfgs == null || cfgs.cfgid <= 0) return ;

        int cfgid = cfgs.cfgid;
        if (cacheModel == STATIC_CACHE) {
            if (cfgid > 0 && cfgid <= FIXED.length) {
                if (FIXED[cfgid - 1] == null || !FIXED[cfgid - 1].equals(cfgs))
                	FIXED[cfgid - 1] = cfgs;
                if (!fixedCache.contains(cfgs))
                	fixedCache.add(cfgs);
            }
        } else {
            vars.put(cfgid, cfgs);
        }

        // LASTID = cfgid > LASTID ? cfgid : LASTID;
        if (cfgid > LASTID.get()) LASTID.set(cfgid);
    }

    public static void clear(){
        FIXED = new Cfgs[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(CfgsDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(CfgsDAO DAO, String TABLENAME2){
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

    public static void relocate(CfgsDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        CfgsDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(CfgsDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        CfgsDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(CfgsDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        CfgsDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(CfgsDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        CfgsDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(CfgsDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(CfgsDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        CfgsDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(CfgsDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(CfgsDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        CfgsDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(CfgsDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Cfgs[maxId(DAO)];

        List<Cfgs> cfgss = DAO.selectAll();
        for (Cfgs cfgs : cfgss) {
            cfgs.reset();
            put(cfgs, true);
        }
    }

    public static Map toMap(Cfgs cfgs){
        return cfgs.toMap();
    }

    public static List<Map> toMap(List<Cfgs> cfgss){
        List<Map> ret = new Vector<Map>();
        for (Cfgs cfgs : cfgss){
            Map e = cfgs.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Cfgs> sortZh(List<Cfgs> cfgss, final String key) {
        Collections.sort(cfgss, new Comparator<Cfgs>() {
            public int compare(Cfgs o1, Cfgs o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return cfgss;
    }

    public static List<Cfgs> sort(List<Cfgs> cfgss, final String key) {
        Collections.sort(cfgss, new Comparator<Cfgs>() {
            public int compare(Cfgs o1, Cfgs o2) {
                return o1.compareTo(o2, key);
            }
        });
        return cfgss;
    }

    public static List<Cfgs> sort(List<Cfgs> cfgss){
        Collections.sort(cfgss, new Comparator<Cfgs>(){
            public int compare(Cfgs o1, Cfgs o2) {
                Object v1 = o1.cfgid;
                Object v2 = o2.cfgid;
                return compareTo(v1, v2);
            }
        });
        return cfgss;
    }

    public static List<Cfgs> sortReverse(List<Cfgs> cfgss){
        Collections.sort(cfgss, new Comparator<Cfgs>(){
            public int compare(Cfgs o1, Cfgs o2) {
                Object v1 = o1.cfgid;
                Object v2 = o2.cfgid;
                return 0 - compareTo(v1, v2);
            }
        });
        return cfgss;
    }

    public static Cfgs insert(Cfgs cfgs) {
        CfgsDAO DAO = DAO();
        return insert(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs insert(CfgsDAO DAO, Cfgs cfgs) {
        return insert(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs insert(Cfgs cfgs, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return insert(DAO, cfgs, TABLENAME2);
    }

    public static Cfgs insert(CfgsDAO DAO, Cfgs cfgs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(cfgs, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        cfgs.cfgid = n;
        if(cacheModel != NO_CACHE) put(cfgs, true);

        return cfgs;
    }

    public static Cfgs asyncInsert(Cfgs cfgs) {
        CfgsDAO DAO = DAO();
        return asyncInsert(DAO, cfgs, DAO.TABLENAME);
    }
    public static Cfgs asyncInsert(CfgsDAO DAO, Cfgs cfgs) {
        return asyncInsert(DAO, cfgs, DAO.TABLENAME);
    }
    public static Cfgs asyncInsert(Cfgs cfgs, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return asyncInsert(DAO, cfgs, TABLENAME2);
    }
    public static Cfgs asyncInsert(CfgsDAO DAO, Cfgs cfgs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(cfgs, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        cfgs.cfgid = n;
        if(cacheModel != NO_CACHE) put(cfgs, true);
        return cfgs;
    }
    public static Cfgs insert2(Cfgs cfgs) {
        CfgsDAO DAO = DAO();
        return insert2(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs insert2(CfgsDAO DAO, Cfgs cfgs) {
        return insert2(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs insert2(Cfgs cfgs, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return insert2(DAO, cfgs, TABLENAME2);
    }

    public static Cfgs insert2(CfgsDAO DAO, Cfgs cfgs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(cfgs, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        cfgs.cfgid = n;
        if(cacheModel != NO_CACHE) put(cfgs, true);

        return cfgs;
    }

    public static Cfgs asyncInsert2(Cfgs cfgs) {
        CfgsDAO DAO = DAO();
        return asyncInsert2(DAO, cfgs, DAO.TABLENAME);
    }
    public static Cfgs asyncInsert2(CfgsDAO DAO, Cfgs cfgs) {
        return asyncInsert2(DAO, cfgs, DAO.TABLENAME);
    }
    public static Cfgs asyncInsert2(Cfgs cfgs, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return asyncInsert2(DAO, cfgs, TABLENAME2);
    }
    public static Cfgs asyncInsert2(CfgsDAO DAO, Cfgs cfgs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        cfgs.cfgid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(cfgs, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(cfgs, true);
        return cfgs;
    }
    public static int[] insert(List<Cfgs> cfgss) {
        CfgsDAO DAO = DAO();
        return insert(DAO, cfgss, DAO.TABLENAME);
    }

    public static int[] insert(CfgsDAO DAO, List<Cfgs> cfgss) {
        return insert(DAO, cfgss, DAO.TABLENAME);
    }

    public static int[] insert(List<Cfgs> cfgss, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return insert(DAO, cfgss, TABLENAME2);
    }

    public static int[] insert(CfgsDAO DAO, List<Cfgs> cfgss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(cfgss, TABLENAME2);
            int n = 0;
            for(Cfgs cfgs : cfgss){
                cfgs.cfgid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[cfgss.size()];
        int n = 0;
        for(Cfgs cfgs : cfgss){
            cfgs = insert(DAO, cfgs, TABLENAME2);
            ret[n++] = (cfgs == null) ? 0 : (int)cfgs.cfgid;
        }
        return ret;
    }

    public static int delete(Cfgs cfgs) {
        int cfgid = cfgs.cfgid;
        CfgsDAO DAO = DAO();
        return delete(DAO, cfgid, DAO.TABLENAME);
    }

    public static int delete(int cfgid) {
        CfgsDAO DAO = DAO();
        return delete(DAO, cfgid, DAO.TABLENAME);
    }

    public static int delete(CfgsDAO DAO, int cfgid) {
        return delete(DAO, cfgid, DAO.TABLENAME);
    }

    public static int delete(int cfgid, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return delete(DAO, cfgid, TABLENAME2);
    }

    public static int delete(CfgsDAO DAO, int cfgid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(cfgid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(cfgid);
        }
        return n;
    }

    public static void asyncDelete(Cfgs cfgs) {
        int cfgid = cfgs.cfgid;
        CfgsDAO DAO = DAO();
        asyncDelete(DAO, cfgid, DAO.TABLENAME);
    }

    public static void asyncDelete(int cfgid) {
        CfgsDAO DAO = DAO();
        asyncDelete(DAO, cfgid, DAO.TABLENAME);
    }

    public static void asyncDelete(CfgsDAO DAO, int cfgid) {
        asyncDelete(DAO, cfgid, DAO.TABLENAME);
    }

    public static void asyncDelete(int cfgid, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        asyncDelete(DAO, cfgid, TABLENAME2);
    }

    public static void asyncDelete(CfgsDAO DAO, int cfgid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(cfgid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(cfgid);
        }
    }

    public static int[] delete(int[] cfgids) {
        CfgsDAO DAO = DAO();
        return delete(DAO, cfgids, DAO.TABLENAME);
    }

    public static int[] delete(CfgsDAO DAO, int[] cfgids) {
        return delete(DAO, cfgids, DAO.TABLENAME);
    }

    public static int[] delete(int[] cfgids,String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return delete(DAO, cfgids, TABLENAME2);
    }

    public static int[] delete(CfgsDAO DAO, int[] cfgids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(cfgids, TABLENAME2);
        }
        int[] ret = new int[cfgids.length];
        int n = 0;
        for(int cfgid : cfgids){
            ret[n++] = delete(DAO, cfgid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        CfgsDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, CfgsDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final CfgsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer cfgid : keys){
                deleteFromMemory(cfgid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Cfgs> beans) {
        CfgsDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Cfgs> beans, CfgsDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Cfgs> beans, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Cfgs> beans, final CfgsDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Cfgs cfgs : beans){
                int cfgid = cfgs.cfgid;
                deleteFromMemory(cfgid);
            }
        }
        return result;
    }

    public static List<Cfgs> getAll() {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getAll(CfgsDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getAll(String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Cfgs> getAll(final CfgsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cfgs> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Cfgs> getNoSortAll() {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getNoSortAll(CfgsDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getNoSortAll(String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Cfgs> getNoSortAll(final CfgsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Cfgs> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cfgs> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Cfgs cfgs = FIXED[i];
                if (cfgs != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Cfgs> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Cfgs> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(CfgsDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final CfgsDAO DAO, final String TABLENAME2) {
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
        CfgsDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(CfgsDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final CfgsDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Cfgs> getIn(List<Integer> keys) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getIn(List<Integer> keys, CfgsDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getIn(List<Integer> keys, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Cfgs> getIn(final List<Integer> keys, final CfgsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cfgs> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Cfgs> getNoSortIn(List<Integer> keys) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getNoSortIn(List<Integer> keys, CfgsDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cfgs> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Cfgs> getNoSortIn(final List<Integer> keys, final CfgsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Cfgs> result = newList();
            for (int key : keys) {
                Cfgs cfgs = getByKeyFromMemory(key);
                if( cfgs == null ) continue;
                result.add(cfgs);
            }
            return result;
        }
    }

    public static List<Cfgs> getLast(int num) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Cfgs> getLast(CfgsDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Cfgs> getLast(int num, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Cfgs> getLast(final CfgsDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Cfgs> result = newList();
            List<Cfgs> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Cfgs last() {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Cfgs last(CfgsDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Cfgs last(String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Cfgs last(final CfgsDAO DAO, final String TABLENAME2) {
        Cfgs result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        CfgsDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(CfgsDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final CfgsDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Cfgs> getGtKey(int cfgid) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, cfgid, DAO.TABLENAME);
    }

    public static List<Cfgs> getGtKey(CfgsDAO DAO, int cfgid) {
        return getGtKey(DAO, cfgid, DAO.TABLENAME);
    }

    public static List<Cfgs> getGtKey(int cfgid, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, cfgid, TABLENAME2);
    }

    public static List<Cfgs> getGtKey(final CfgsDAO DAO, final int cfgid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(cfgid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cfgs> result = newList();
            List<Cfgs> cfgss = getAll();
            for (Cfgs cfgs : cfgss) {
                if(cfgs.cfgid <= cfgid) continue;
                result.add(cfgs);
            }
            return result;
        }
    }

    public static Cfgs getByKey(int cfgid) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, cfgid, DAO.TABLENAME);
    }

    public static Future<Cfgs> asyncGetByKey(final int cfgid) {
        Future<Cfgs> f = Async.exec(new Callable<Cfgs>() {
            public Cfgs call() throws Exception {
                return getByKey(cfgid);
            }
        });
        return f;
    }

    public static Cfgs getByKey(CfgsDAO DAO, int cfgid) {
        return getByKey(DAO, cfgid, DAO.TABLENAME);
    }

    public static Cfgs getByKey(int cfgid, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, cfgid, TABLENAME2);
    }

    public static Cfgs getByKey(final CfgsDAO DAO, final int cfgid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(cfgid, TABLENAME2);
        }
        return getByKeyFromMemory(cfgid);
    }

    public static Cfgs getByKeyFromMemory(final int cfgid) {
        if (cacheModel == STATIC_CACHE) {
            if (cfgid < 1 || FIXED == null || FIXED.length < cfgid) return null;
            return FIXED[cfgid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(cfgid);
        }
        return null;
    }

    public static Cfgs deleteFromMemory(final int cfgid) {
        Cfgs cfgs;
        if (cacheModel == STATIC_CACHE) {
            if (cfgid < 1 || FIXED == null || FIXED.length < cfgid || FIXED[cfgid-1]==null) return null;
            cfgs = FIXED[cfgid - 1];
            FIXED[cfgid - 1] = null;
            fixedCache.remove(cfgs);
        } else {
            cfgs = vars.remove(cfgid);
        }
        if(cfgs == null) return null;

        return cfgs;
    }

    public static List<Cfgs> getByPage(int page, int size) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Cfgs> getByPage(CfgsDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Cfgs> getByPage(int page, int size, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Cfgs> getByPage(final CfgsDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cfgs> result = newList();
            List<Cfgs> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(CfgsDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        CfgsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final CfgsDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Cfgs update(Cfgs cfgs) {
        CfgsDAO DAO = DAO();
        return update(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs update(CfgsDAO DAO, Cfgs cfgs) {
        return update(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs update(Cfgs cfgs, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return update(DAO, cfgs, TABLENAME2);
    }

    public static Cfgs update(final CfgsDAO DAO, final Cfgs cfgs,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(cfgs, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(cfgs, TABLENAME2);
            if(n == -1) 
                return cfgs;
            else if(n <= 0) 
                return null;
        }
        return cfgs;
    }

    public static Cfgs asyncUpdate(Cfgs cfgs) {
        CfgsDAO DAO = DAO();
        return asyncUpdate(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs asyncUpdate(CfgsDAO DAO, Cfgs cfgs) {
        return asyncUpdate(DAO, cfgs, DAO.TABLENAME);
    }

    public static Cfgs asyncUpdate(Cfgs cfgs, String TABLENAME2) {
        CfgsDAO DAO = DAO();
        return asyncUpdate(DAO, cfgs, TABLENAME2);
    }

    public static Cfgs asyncUpdate(final CfgsDAO DAO, final Cfgs cfgs,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(cfgs, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(cfgs, TABLENAME2);
        }
        return cfgs;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        CfgsDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(CfgsDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final CfgsDAO DAO, final String TABLENAME2) {
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

        List<Cfgs> cfgss = getAll();
        for (Cfgs cfgs : cfgss) {
            int n = DAO.insert2(cfgs, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
