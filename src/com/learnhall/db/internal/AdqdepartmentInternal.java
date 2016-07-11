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

import static com.learnhall.db.bean.Adqdepartment.Col;

//learnhall3_design - adqdepartment
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AdqdepartmentInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AdqdepartmentInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AdqdepartmentInternal(){}

    public static AdqdepartmentDAO DAO(){
        return AdqdepartmentEntity.AdqdepartmentDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Adqdepartment[MAX];
    }
    private static Adqdepartment[] FIXED = new Adqdepartment[MAX];
    public static final Map<Integer, Adqdepartment> vars = newSortedMap();
    public static final Map<String, Integer> varsByName = newSortedMap();

    private static final List<Adqdepartment> fixedCache = newList();

    public static void put(Adqdepartment adqdepartment, boolean force){
        if(adqdepartment == null || adqdepartment.did <= 0) return ;

        int did = adqdepartment.did;
        if (cacheModel == STATIC_CACHE) {
            if (did > 0 && did <= FIXED.length) {
                if (FIXED[did - 1] == null || !FIXED[did - 1].equals(adqdepartment))
                	FIXED[did - 1] = adqdepartment;
                if (!fixedCache.contains(adqdepartment))
                	fixedCache.add(adqdepartment);
            }
        } else {
            vars.put(did, adqdepartment);
        }

        { // 单-唯一索引 remove old index name
          Object ov = adqdepartment.oldVal(Col.name);
          if(ov != null)
              varsByName.remove(ov);
          if(ov != null || force){ // put new index
            String name = adqdepartment.getName();
            varsByName.put(name, did);
          }
        }

        // LASTID = did > LASTID ? did : LASTID;
        if (did > LASTID.get()) LASTID.set(did);
    }

    public static void clear(){
        varsByName.clear();
        FIXED = new Adqdepartment[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AdqdepartmentDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AdqdepartmentDAO DAO, String TABLENAME2){
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

    public static void relocate(AdqdepartmentDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AdqdepartmentDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AdqdepartmentDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AdqdepartmentDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AdqdepartmentDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AdqdepartmentDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AdqdepartmentDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AdqdepartmentDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AdqdepartmentDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AdqdepartmentDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AdqdepartmentDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AdqdepartmentDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AdqdepartmentDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Adqdepartment[maxId(DAO)];

        List<Adqdepartment> adqdepartments = DAO.selectAll();
        for (Adqdepartment adqdepartment : adqdepartments) {
            adqdepartment.reset();
            put(adqdepartment, true);
        }
    }

    public static Map toMap(Adqdepartment adqdepartment){
        return adqdepartment.toMap();
    }

    public static List<Map> toMap(List<Adqdepartment> adqdepartments){
        List<Map> ret = new Vector<Map>();
        for (Adqdepartment adqdepartment : adqdepartments){
            Map e = adqdepartment.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Adqdepartment> sortZh(List<Adqdepartment> adqdepartments, final String key) {
        Collections.sort(adqdepartments, new Comparator<Adqdepartment>() {
            public int compare(Adqdepartment o1, Adqdepartment o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return adqdepartments;
    }

    public static List<Adqdepartment> sort(List<Adqdepartment> adqdepartments, final String key) {
        Collections.sort(adqdepartments, new Comparator<Adqdepartment>() {
            public int compare(Adqdepartment o1, Adqdepartment o2) {
                return o1.compareTo(o2, key);
            }
        });
        return adqdepartments;
    }

    public static List<Adqdepartment> sort(List<Adqdepartment> adqdepartments){
        Collections.sort(adqdepartments, new Comparator<Adqdepartment>(){
            public int compare(Adqdepartment o1, Adqdepartment o2) {
                Object v1 = o1.did;
                Object v2 = o2.did;
                return compareTo(v1, v2);
            }
        });
        return adqdepartments;
    }

    public static List<Adqdepartment> sortReverse(List<Adqdepartment> adqdepartments){
        Collections.sort(adqdepartments, new Comparator<Adqdepartment>(){
            public int compare(Adqdepartment o1, Adqdepartment o2) {
                Object v1 = o1.did;
                Object v2 = o2.did;
                return 0 - compareTo(v1, v2);
            }
        });
        return adqdepartments;
    }

    public static List<Adqdepartment> sortName(List<Adqdepartment> adqdepartments){
        Collections.sort(adqdepartments, new Comparator<Adqdepartment>(){
            public int compare(Adqdepartment o1, Adqdepartment o2) {
                Object v1 = o1.getName();
                Object v2 = o2.getName();
                return compareTo(v1, v2);
            }
        });
        return adqdepartments;
    }

    public static List<Adqdepartment> sortNameRo(List<Adqdepartment> adqdepartments){
        Collections.sort(adqdepartments, new Comparator<Adqdepartment>(){
            public int compare(Adqdepartment o1, Adqdepartment o2) {
                Object v1 = o1.getName();
                Object v2 = o2.getName();
                return 0 - compareTo(v1, v2);
            };
        });
        return adqdepartments;
    }

    public static Adqdepartment insert(Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = DAO();
        return insert(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment insert(AdqdepartmentDAO DAO, Adqdepartment adqdepartment) {
        return insert(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment insert(Adqdepartment adqdepartment, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return insert(DAO, adqdepartment, TABLENAME2);
    }

    public static Adqdepartment insert(AdqdepartmentDAO DAO, Adqdepartment adqdepartment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(adqdepartment, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        adqdepartment.did = n;
        if(cacheModel != NO_CACHE) put(adqdepartment, true);

        return adqdepartment;
    }

    public static Adqdepartment asyncInsert(Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = DAO();
        return asyncInsert(DAO, adqdepartment, DAO.TABLENAME);
    }
    public static Adqdepartment asyncInsert(AdqdepartmentDAO DAO, Adqdepartment adqdepartment) {
        return asyncInsert(DAO, adqdepartment, DAO.TABLENAME);
    }
    public static Adqdepartment asyncInsert(Adqdepartment adqdepartment, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return asyncInsert(DAO, adqdepartment, TABLENAME2);
    }
    public static Adqdepartment asyncInsert(AdqdepartmentDAO DAO, Adqdepartment adqdepartment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(adqdepartment, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        adqdepartment.did = n;
        if(cacheModel != NO_CACHE) put(adqdepartment, true);
        return adqdepartment;
    }
    public static Adqdepartment insert2(Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = DAO();
        return insert2(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment insert2(AdqdepartmentDAO DAO, Adqdepartment adqdepartment) {
        return insert2(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment insert2(Adqdepartment adqdepartment, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return insert2(DAO, adqdepartment, TABLENAME2);
    }

    public static Adqdepartment insert2(AdqdepartmentDAO DAO, Adqdepartment adqdepartment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(adqdepartment, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        adqdepartment.did = n;
        if(cacheModel != NO_CACHE) put(adqdepartment, true);

        return adqdepartment;
    }

    public static Adqdepartment asyncInsert2(Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = DAO();
        return asyncInsert2(DAO, adqdepartment, DAO.TABLENAME);
    }
    public static Adqdepartment asyncInsert2(AdqdepartmentDAO DAO, Adqdepartment adqdepartment) {
        return asyncInsert2(DAO, adqdepartment, DAO.TABLENAME);
    }
    public static Adqdepartment asyncInsert2(Adqdepartment adqdepartment, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return asyncInsert2(DAO, adqdepartment, TABLENAME2);
    }
    public static Adqdepartment asyncInsert2(AdqdepartmentDAO DAO, Adqdepartment adqdepartment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        adqdepartment.did = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(adqdepartment, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(adqdepartment, true);
        return adqdepartment;
    }
    public static int[] insert(List<Adqdepartment> adqdepartments) {
        AdqdepartmentDAO DAO = DAO();
        return insert(DAO, adqdepartments, DAO.TABLENAME);
    }

    public static int[] insert(AdqdepartmentDAO DAO, List<Adqdepartment> adqdepartments) {
        return insert(DAO, adqdepartments, DAO.TABLENAME);
    }

    public static int[] insert(List<Adqdepartment> adqdepartments, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return insert(DAO, adqdepartments, TABLENAME2);
    }

    public static int[] insert(AdqdepartmentDAO DAO, List<Adqdepartment> adqdepartments, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(adqdepartments, TABLENAME2);
            int n = 0;
            for(Adqdepartment adqdepartment : adqdepartments){
                adqdepartment.did = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[adqdepartments.size()];
        int n = 0;
        for(Adqdepartment adqdepartment : adqdepartments){
            adqdepartment = insert(DAO, adqdepartment, TABLENAME2);
            ret[n++] = (adqdepartment == null) ? 0 : (int)adqdepartment.did;
        }
        return ret;
    }

    public static int delete(Adqdepartment adqdepartment) {
        int did = adqdepartment.did;
        AdqdepartmentDAO DAO = DAO();
        return delete(DAO, did, DAO.TABLENAME);
    }

    public static int delete(int did) {
        AdqdepartmentDAO DAO = DAO();
        return delete(DAO, did, DAO.TABLENAME);
    }

    public static int delete(AdqdepartmentDAO DAO, int did) {
        return delete(DAO, did, DAO.TABLENAME);
    }

    public static int delete(int did, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return delete(DAO, did, TABLENAME2);
    }

    public static int delete(AdqdepartmentDAO DAO, int did, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(did, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(did);
        }
        return n;
    }

    public static void asyncDelete(Adqdepartment adqdepartment) {
        int did = adqdepartment.did;
        AdqdepartmentDAO DAO = DAO();
        asyncDelete(DAO, did, DAO.TABLENAME);
    }

    public static void asyncDelete(int did) {
        AdqdepartmentDAO DAO = DAO();
        asyncDelete(DAO, did, DAO.TABLENAME);
    }

    public static void asyncDelete(AdqdepartmentDAO DAO, int did) {
        asyncDelete(DAO, did, DAO.TABLENAME);
    }

    public static void asyncDelete(int did, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        asyncDelete(DAO, did, TABLENAME2);
    }

    public static void asyncDelete(AdqdepartmentDAO DAO, int did, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(did, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(did);
        }
    }

    public static int[] delete(int[] dids) {
        AdqdepartmentDAO DAO = DAO();
        return delete(DAO, dids, DAO.TABLENAME);
    }

    public static int[] delete(AdqdepartmentDAO DAO, int[] dids) {
        return delete(DAO, dids, DAO.TABLENAME);
    }

    public static int[] delete(int[] dids,String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return delete(DAO, dids, TABLENAME2);
    }

    public static int[] delete(AdqdepartmentDAO DAO, int[] dids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(dids, TABLENAME2);
        }
        int[] ret = new int[dids.length];
        int n = 0;
        for(int did : dids){
            ret[n++] = delete(DAO, did, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        AdqdepartmentDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AdqdepartmentDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer did : keys){
                deleteFromMemory(did);
            }
        }
        return result;
    }

    public static int deleteWith(List<Adqdepartment> beans) {
        AdqdepartmentDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Adqdepartment> beans, AdqdepartmentDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Adqdepartment> beans, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Adqdepartment> beans, final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Adqdepartment adqdepartment : beans){
                int did = adqdepartment.did;
                deleteFromMemory(did);
            }
        }
        return result;
    }

    public static List<Adqdepartment> getAll() {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getAll(AdqdepartmentDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getAll(String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Adqdepartment> getAll(final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adqdepartment> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Adqdepartment> getNoSortAll() {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getNoSortAll(AdqdepartmentDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getNoSortAll(String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Adqdepartment> getNoSortAll(final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Adqdepartment> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adqdepartment> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Adqdepartment adqdepartment = FIXED[i];
                if (adqdepartment != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Adqdepartment> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Adqdepartment> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AdqdepartmentDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AdqdepartmentDAO DAO, final String TABLENAME2) {
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
        AdqdepartmentDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AdqdepartmentDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AdqdepartmentDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Adqdepartment> getIn(List<Integer> keys) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getIn(List<Integer> keys, AdqdepartmentDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getIn(List<Integer> keys, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Adqdepartment> getIn(final List<Integer> keys, final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adqdepartment> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Adqdepartment> getNoSortIn(List<Integer> keys) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getNoSortIn(List<Integer> keys, AdqdepartmentDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Adqdepartment> getNoSortIn(final List<Integer> keys, final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Adqdepartment> result = newList();
            for (int key : keys) {
                Adqdepartment adqdepartment = getByKeyFromMemory(key);
                if( adqdepartment == null ) continue;
                result.add(adqdepartment);
            }
            return result;
        }
    }

    public static List<Adqdepartment> getLast(int num) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getLast(AdqdepartmentDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getLast(int num, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Adqdepartment> getLast(final AdqdepartmentDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Adqdepartment> result = newList();
            List<Adqdepartment> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Adqdepartment last() {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Adqdepartment last(AdqdepartmentDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Adqdepartment last(String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Adqdepartment last(final AdqdepartmentDAO DAO, final String TABLENAME2) {
        Adqdepartment result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AdqdepartmentDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AdqdepartmentDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AdqdepartmentDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Adqdepartment> getGtKey(int did) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, did, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getGtKey(AdqdepartmentDAO DAO, int did) {
        return getGtKey(DAO, did, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getGtKey(int did, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, did, TABLENAME2);
    }

    public static List<Adqdepartment> getGtKey(final AdqdepartmentDAO DAO, final int did,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(did, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adqdepartment> result = newList();
            List<Adqdepartment> adqdepartments = getAll();
            for (Adqdepartment adqdepartment : adqdepartments) {
                if(adqdepartment.did <= did) continue;
                result.add(adqdepartment);
            }
            return result;
        }
    }

    public static Adqdepartment getByKey(int did) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, did, DAO.TABLENAME);
    }

    public static Future<Adqdepartment> asyncGetByKey(final int did) {
        Future<Adqdepartment> f = Async.exec(new Callable<Adqdepartment>() {
            public Adqdepartment call() throws Exception {
                return getByKey(did);
            }
        });
        return f;
    }

    public static Adqdepartment getByKey(AdqdepartmentDAO DAO, int did) {
        return getByKey(DAO, did, DAO.TABLENAME);
    }

    public static Adqdepartment getByKey(int did, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, did, TABLENAME2);
    }

    public static Adqdepartment getByKey(final AdqdepartmentDAO DAO, final int did,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(did, TABLENAME2);
        }
        return getByKeyFromMemory(did);
    }

    public static Adqdepartment getByKeyFromMemory(final int did) {
        if (cacheModel == STATIC_CACHE) {
            if (did < 1 || FIXED == null || FIXED.length < did) return null;
            return FIXED[did - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(did);
        }
        return null;
    }

    public static Adqdepartment deleteFromMemory(final int did) {
        Adqdepartment adqdepartment;
        if (cacheModel == STATIC_CACHE) {
            if (did < 1 || FIXED == null || FIXED.length < did || FIXED[did-1]==null) return null;
            adqdepartment = FIXED[did - 1];
            FIXED[did - 1] = null;
            fixedCache.remove(adqdepartment);
        } else {
            adqdepartment = vars.remove(did);
        }
        if(adqdepartment == null) return null;

        String name = adqdepartment.getName();
        varsByName.remove(name);

        return adqdepartment;
    }

    public static List<Adqdepartment> getByPage(int page, int size) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getByPage(AdqdepartmentDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getByPage(int page, int size, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Adqdepartment> getByPage(final AdqdepartmentDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adqdepartment> result = newList();
            List<Adqdepartment> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AdqdepartmentDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AdqdepartmentDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Adqdepartment getByName(String name) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByName(DAO, name, DAO.TABLENAME);
    }

    public static Adqdepartment getByName(AdqdepartmentDAO DAO, String name) {
        return getByName(DAO, name, DAO.TABLENAME);
    }

    public static Adqdepartment getByName(String name, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByName(DAO, name, TABLENAME2);
    }

    public static Adqdepartment getByName(final AdqdepartmentDAO DAO, final String name,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByName(name, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer did = varsByName.get(name);
            if(did == null) return null;
            Adqdepartment result = getByKey(DAO, did, TABLENAME2);
            if(result == null) return null;
            if(!result.getName().equals(name)){
                varsByName.remove(name);
                return null;
            }
            return result;
        }
    }

    public static int countLikeName(String name) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeName(DAO, name, DAO.TABLENAME);
    }

    public static int countLikeName(AdqdepartmentDAO DAO, String name) {
        return countLikeName(DAO, name, DAO.TABLENAME);
    }

    public static int countLikeName(String name, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeName(DAO, name, TABLENAME2);
    }

    public static int countLikeName(final AdqdepartmentDAO DAO, final String name,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeName(name, TABLENAME2);
        }
        List<Adqdepartment> adqdepartments = getLikeName(DAO, name, TABLENAME2);
        return adqdepartments.size();
    }

    public static List<Adqdepartment> getLikeName(String name) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeName(DAO, name, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getLikeName(AdqdepartmentDAO DAO, String name) {
        return getLikeName(DAO, name, DAO.TABLENAME);
    }

    public static List<Adqdepartment> getLikeName(String name, String TABLENAME2) {
        AdqdepartmentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeName(DAO, name, TABLENAME2);
    }

    public static List<Adqdepartment> getLikeName(final AdqdepartmentDAO DAO, final String name,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeName(name, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Adqdepartment> result = newList();
            List<Adqdepartment> adqdepartments = getAll(DAO, TABLENAME2);
            for(Adqdepartment e : adqdepartments){
                String _var = e.getName();
                if(_var.indexOf(name) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Adqdepartment update(Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = DAO();
        return update(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment update(AdqdepartmentDAO DAO, Adqdepartment adqdepartment) {
        return update(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment update(Adqdepartment adqdepartment, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return update(DAO, adqdepartment, TABLENAME2);
    }

    public static Adqdepartment update(final AdqdepartmentDAO DAO, final Adqdepartment adqdepartment,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(adqdepartment, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(adqdepartment, TABLENAME2);
            if(n == -1) 
                return adqdepartment;
            else if(n <= 0) 
                return null;
        }
        return adqdepartment;
    }

    public static Adqdepartment asyncUpdate(Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = DAO();
        return asyncUpdate(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment asyncUpdate(AdqdepartmentDAO DAO, Adqdepartment adqdepartment) {
        return asyncUpdate(DAO, adqdepartment, DAO.TABLENAME);
    }

    public static Adqdepartment asyncUpdate(Adqdepartment adqdepartment, String TABLENAME2) {
        AdqdepartmentDAO DAO = DAO();
        return asyncUpdate(DAO, adqdepartment, TABLENAME2);
    }

    public static Adqdepartment asyncUpdate(final AdqdepartmentDAO DAO, final Adqdepartment adqdepartment,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(adqdepartment, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(adqdepartment, TABLENAME2);
        }
        return adqdepartment;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AdqdepartmentDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AdqdepartmentDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AdqdepartmentDAO DAO, final String TABLENAME2) {
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

        List<Adqdepartment> adqdepartments = getAll();
        for (Adqdepartment adqdepartment : adqdepartments) {
            int n = DAO.insert2(adqdepartment, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
