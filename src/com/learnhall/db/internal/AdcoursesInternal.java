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

import static com.learnhall.db.bean.Adcourses.Col;

//learnhall3_design - adcourses
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AdcoursesInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AdcoursesInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AdcoursesInternal(){}

    public static AdcoursesDAO DAO(){
        return AdcoursesEntity.AdcoursesDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Adcourses[MAX];
    }
    private static Adcourses[] FIXED = new Adcourses[MAX];
    public static final Map<Integer, Adcourses> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByDepartid = newSortedMap();

    private static final List<Adcourses> fixedCache = newList();

    public static void put(Adcourses adcourses, boolean force){
        if(adcourses == null || adcourses.cid <= 0) return ;

        int cid = adcourses.cid;
        if (cacheModel == STATIC_CACHE) {
            if (cid > 0 && cid <= FIXED.length) {
                if (FIXED[cid - 1] == null || !FIXED[cid - 1].equals(adcourses))
                	FIXED[cid - 1] = adcourses;
                if (!fixedCache.contains(adcourses))
                	fixedCache.add(adcourses);
            }
        } else {
            vars.put(cid, adcourses);
        }

        { // 单-非唯一索引 remove old index departid
          Object ov = adcourses.oldVal(Col.departid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByDepartid.get(_val);
              if(m1 != null) {
                  m1.remove(cid);
                  if(m1.isEmpty())
                      varsByDepartid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int departid = adcourses.getDepartid();
            Set m1 = varsByDepartid.get(departid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByDepartid.put(departid, m1);
            }
            m1.add(cid);
          }
        }

        // LASTID = cid > LASTID ? cid : LASTID;
        if (cid > LASTID.get()) LASTID.set(cid);
    }

    public static void clear(){
        varsByDepartid.clear();
        FIXED = new Adcourses[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AdcoursesDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AdcoursesDAO DAO, String TABLENAME2){
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

    public static void relocate(AdcoursesDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AdcoursesDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AdcoursesDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AdcoursesDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AdcoursesDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AdcoursesDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AdcoursesDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AdcoursesDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AdcoursesDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AdcoursesDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AdcoursesDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AdcoursesDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AdcoursesDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Adcourses[maxId(DAO)];

        List<Adcourses> adcoursess = DAO.selectAll();
        for (Adcourses adcourses : adcoursess) {
            adcourses.reset();
            put(adcourses, true);
        }
    }

    public static Map toMap(Adcourses adcourses){
        return adcourses.toMap();
    }

    public static List<Map> toMap(List<Adcourses> adcoursess){
        List<Map> ret = new Vector<Map>();
        for (Adcourses adcourses : adcoursess){
            Map e = adcourses.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Adcourses> sortZh(List<Adcourses> adcoursess, final String key) {
        Collections.sort(adcoursess, new Comparator<Adcourses>() {
            public int compare(Adcourses o1, Adcourses o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return adcoursess;
    }

    public static List<Adcourses> sort(List<Adcourses> adcoursess, final String key) {
        Collections.sort(adcoursess, new Comparator<Adcourses>() {
            public int compare(Adcourses o1, Adcourses o2) {
                return o1.compareTo(o2, key);
            }
        });
        return adcoursess;
    }

    public static List<Adcourses> sort(List<Adcourses> adcoursess){
        Collections.sort(adcoursess, new Comparator<Adcourses>(){
            public int compare(Adcourses o1, Adcourses o2) {
                Object v1 = o1.cid;
                Object v2 = o2.cid;
                return compareTo(v1, v2);
            }
        });
        return adcoursess;
    }

    public static List<Adcourses> sortReverse(List<Adcourses> adcoursess){
        Collections.sort(adcoursess, new Comparator<Adcourses>(){
            public int compare(Adcourses o1, Adcourses o2) {
                Object v1 = o1.cid;
                Object v2 = o2.cid;
                return 0 - compareTo(v1, v2);
            }
        });
        return adcoursess;
    }

    public static List<Adcourses> sortDepartid(List<Adcourses> adcoursess){
        Collections.sort(adcoursess, new Comparator<Adcourses>(){
            public int compare(Adcourses o1, Adcourses o2) {
                Object v1 = o1.getDepartid();
                Object v2 = o2.getDepartid();
                return compareTo(v1, v2);
            }
        });
        return adcoursess;
    }

    public static List<Adcourses> sortDepartidRo(List<Adcourses> adcoursess){
        Collections.sort(adcoursess, new Comparator<Adcourses>(){
            public int compare(Adcourses o1, Adcourses o2) {
                Object v1 = o1.getDepartid();
                Object v2 = o2.getDepartid();
                return 0 - compareTo(v1, v2);
            };
        });
        return adcoursess;
    }

    public static Adcourses insert(Adcourses adcourses) {
        AdcoursesDAO DAO = DAO();
        return insert(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses insert(AdcoursesDAO DAO, Adcourses adcourses) {
        return insert(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses insert(Adcourses adcourses, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return insert(DAO, adcourses, TABLENAME2);
    }

    public static Adcourses insert(AdcoursesDAO DAO, Adcourses adcourses, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(adcourses, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        adcourses.cid = n;
        if(cacheModel != NO_CACHE) put(adcourses, true);

        return adcourses;
    }

    public static Adcourses asyncInsert(Adcourses adcourses) {
        AdcoursesDAO DAO = DAO();
        return asyncInsert(DAO, adcourses, DAO.TABLENAME);
    }
    public static Adcourses asyncInsert(AdcoursesDAO DAO, Adcourses adcourses) {
        return asyncInsert(DAO, adcourses, DAO.TABLENAME);
    }
    public static Adcourses asyncInsert(Adcourses adcourses, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return asyncInsert(DAO, adcourses, TABLENAME2);
    }
    public static Adcourses asyncInsert(AdcoursesDAO DAO, Adcourses adcourses, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(adcourses, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        adcourses.cid = n;
        if(cacheModel != NO_CACHE) put(adcourses, true);
        return adcourses;
    }
    public static Adcourses insert2(Adcourses adcourses) {
        AdcoursesDAO DAO = DAO();
        return insert2(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses insert2(AdcoursesDAO DAO, Adcourses adcourses) {
        return insert2(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses insert2(Adcourses adcourses, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return insert2(DAO, adcourses, TABLENAME2);
    }

    public static Adcourses insert2(AdcoursesDAO DAO, Adcourses adcourses, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(adcourses, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        adcourses.cid = n;
        if(cacheModel != NO_CACHE) put(adcourses, true);

        return adcourses;
    }

    public static Adcourses asyncInsert2(Adcourses adcourses) {
        AdcoursesDAO DAO = DAO();
        return asyncInsert2(DAO, adcourses, DAO.TABLENAME);
    }
    public static Adcourses asyncInsert2(AdcoursesDAO DAO, Adcourses adcourses) {
        return asyncInsert2(DAO, adcourses, DAO.TABLENAME);
    }
    public static Adcourses asyncInsert2(Adcourses adcourses, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return asyncInsert2(DAO, adcourses, TABLENAME2);
    }
    public static Adcourses asyncInsert2(AdcoursesDAO DAO, Adcourses adcourses, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        adcourses.cid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(adcourses, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(adcourses, true);
        return adcourses;
    }
    public static int[] insert(List<Adcourses> adcoursess) {
        AdcoursesDAO DAO = DAO();
        return insert(DAO, adcoursess, DAO.TABLENAME);
    }

    public static int[] insert(AdcoursesDAO DAO, List<Adcourses> adcoursess) {
        return insert(DAO, adcoursess, DAO.TABLENAME);
    }

    public static int[] insert(List<Adcourses> adcoursess, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return insert(DAO, adcoursess, TABLENAME2);
    }

    public static int[] insert(AdcoursesDAO DAO, List<Adcourses> adcoursess, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(adcoursess, TABLENAME2);
            int n = 0;
            for(Adcourses adcourses : adcoursess){
                adcourses.cid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[adcoursess.size()];
        int n = 0;
        for(Adcourses adcourses : adcoursess){
            adcourses = insert(DAO, adcourses, TABLENAME2);
            ret[n++] = (adcourses == null) ? 0 : (int)adcourses.cid;
        }
        return ret;
    }

    public static int delete(Adcourses adcourses) {
        int cid = adcourses.cid;
        AdcoursesDAO DAO = DAO();
        return delete(DAO, cid, DAO.TABLENAME);
    }

    public static int delete(int cid) {
        AdcoursesDAO DAO = DAO();
        return delete(DAO, cid, DAO.TABLENAME);
    }

    public static int delete(AdcoursesDAO DAO, int cid) {
        return delete(DAO, cid, DAO.TABLENAME);
    }

    public static int delete(int cid, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return delete(DAO, cid, TABLENAME2);
    }

    public static int delete(AdcoursesDAO DAO, int cid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(cid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(cid);
        }
        return n;
    }

    public static void asyncDelete(Adcourses adcourses) {
        int cid = adcourses.cid;
        AdcoursesDAO DAO = DAO();
        asyncDelete(DAO, cid, DAO.TABLENAME);
    }

    public static void asyncDelete(int cid) {
        AdcoursesDAO DAO = DAO();
        asyncDelete(DAO, cid, DAO.TABLENAME);
    }

    public static void asyncDelete(AdcoursesDAO DAO, int cid) {
        asyncDelete(DAO, cid, DAO.TABLENAME);
    }

    public static void asyncDelete(int cid, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        asyncDelete(DAO, cid, TABLENAME2);
    }

    public static void asyncDelete(AdcoursesDAO DAO, int cid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(cid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(cid);
        }
    }

    public static int[] delete(int[] cids) {
        AdcoursesDAO DAO = DAO();
        return delete(DAO, cids, DAO.TABLENAME);
    }

    public static int[] delete(AdcoursesDAO DAO, int[] cids) {
        return delete(DAO, cids, DAO.TABLENAME);
    }

    public static int[] delete(int[] cids,String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return delete(DAO, cids, TABLENAME2);
    }

    public static int[] delete(AdcoursesDAO DAO, int[] cids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(cids, TABLENAME2);
        }
        int[] ret = new int[cids.length];
        int n = 0;
        for(int cid : cids){
            ret[n++] = delete(DAO, cid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        AdcoursesDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AdcoursesDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AdcoursesDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer cid : keys){
                deleteFromMemory(cid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Adcourses> beans) {
        AdcoursesDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Adcourses> beans, AdcoursesDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Adcourses> beans, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Adcourses> beans, final AdcoursesDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Adcourses adcourses : beans){
                int cid = adcourses.cid;
                deleteFromMemory(cid);
            }
        }
        return result;
    }

    public static List<Adcourses> getAll() {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getAll(AdcoursesDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getAll(String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Adcourses> getAll(final AdcoursesDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adcourses> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Adcourses> getNoSortAll() {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getNoSortAll(AdcoursesDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getNoSortAll(String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Adcourses> getNoSortAll(final AdcoursesDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Adcourses> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adcourses> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Adcourses adcourses = FIXED[i];
                if (adcourses != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Adcourses> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Adcourses> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AdcoursesDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AdcoursesDAO DAO, final String TABLENAME2) {
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
        AdcoursesDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AdcoursesDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AdcoursesDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Adcourses> getIn(List<Integer> keys) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getIn(List<Integer> keys, AdcoursesDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getIn(List<Integer> keys, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Adcourses> getIn(final List<Integer> keys, final AdcoursesDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adcourses> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Adcourses> getNoSortIn(List<Integer> keys) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getNoSortIn(List<Integer> keys, AdcoursesDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adcourses> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Adcourses> getNoSortIn(final List<Integer> keys, final AdcoursesDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Adcourses> result = newList();
            for (int key : keys) {
                Adcourses adcourses = getByKeyFromMemory(key);
                if( adcourses == null ) continue;
                result.add(adcourses);
            }
            return result;
        }
    }

    public static List<Adcourses> getLast(int num) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Adcourses> getLast(AdcoursesDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Adcourses> getLast(int num, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Adcourses> getLast(final AdcoursesDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Adcourses> result = newList();
            List<Adcourses> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Adcourses last() {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Adcourses last(AdcoursesDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Adcourses last(String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Adcourses last(final AdcoursesDAO DAO, final String TABLENAME2) {
        Adcourses result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AdcoursesDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AdcoursesDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AdcoursesDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Adcourses> getGtKey(int cid) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, cid, DAO.TABLENAME);
    }

    public static List<Adcourses> getGtKey(AdcoursesDAO DAO, int cid) {
        return getGtKey(DAO, cid, DAO.TABLENAME);
    }

    public static List<Adcourses> getGtKey(int cid, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, cid, TABLENAME2);
    }

    public static List<Adcourses> getGtKey(final AdcoursesDAO DAO, final int cid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(cid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adcourses> result = newList();
            List<Adcourses> adcoursess = getAll();
            for (Adcourses adcourses : adcoursess) {
                if(adcourses.cid <= cid) continue;
                result.add(adcourses);
            }
            return result;
        }
    }

    public static Adcourses getByKey(int cid) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, cid, DAO.TABLENAME);
    }

    public static Future<Adcourses> asyncGetByKey(final int cid) {
        Future<Adcourses> f = Async.exec(new Callable<Adcourses>() {
            public Adcourses call() throws Exception {
                return getByKey(cid);
            }
        });
        return f;
    }

    public static Adcourses getByKey(AdcoursesDAO DAO, int cid) {
        return getByKey(DAO, cid, DAO.TABLENAME);
    }

    public static Adcourses getByKey(int cid, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, cid, TABLENAME2);
    }

    public static Adcourses getByKey(final AdcoursesDAO DAO, final int cid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(cid, TABLENAME2);
        }
        return getByKeyFromMemory(cid);
    }

    public static Adcourses getByKeyFromMemory(final int cid) {
        if (cacheModel == STATIC_CACHE) {
            if (cid < 1 || FIXED == null || FIXED.length < cid) return null;
            return FIXED[cid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(cid);
        }
        return null;
    }

    public static Adcourses deleteFromMemory(final int cid) {
        Adcourses adcourses;
        if (cacheModel == STATIC_CACHE) {
            if (cid < 1 || FIXED == null || FIXED.length < cid || FIXED[cid-1]==null) return null;
            adcourses = FIXED[cid - 1];
            FIXED[cid - 1] = null;
            fixedCache.remove(adcourses);
        } else {
            adcourses = vars.remove(cid);
        }
        if(adcourses == null) return null;

        int departid = adcourses.getDepartid();
        Set m1 = varsByDepartid.get(departid);
        if(m1 != null) {
            m1.remove(cid);
            if(m1.isEmpty())
                varsByDepartid.remove(departid);
        }

        return adcourses;
    }

    public static List<Adcourses> getByPage(int page, int size) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Adcourses> getByPage(AdcoursesDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Adcourses> getByPage(int page, int size, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Adcourses> getByPage(final AdcoursesDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adcourses> result = newList();
            List<Adcourses> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AdcoursesDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AdcoursesDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByDepartid(Integer departid) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByDepartid(DAO, departid, DAO.TABLENAME);
    }

    public static int countByDepartid(AdcoursesDAO DAO, Integer departid) {
        return countByDepartid(DAO, departid, DAO.TABLENAME);
    }

    public static int countByDepartid(Integer departid, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByDepartid(DAO, departid, TABLENAME2);
    }

    public static int countByDepartid(final AdcoursesDAO DAO, final Integer departid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByDepartid(departid, TABLENAME2);
        }
        List<Adcourses> adcoursess = getByDepartid(DAO, departid, TABLENAME2);
        return adcoursess.size();
    }

    public static List<Adcourses> getByDepartid(Integer departid) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByDepartid(DAO, departid, DAO.TABLENAME);
    }

    public static List<Adcourses> getByDepartid(AdcoursesDAO DAO, Integer departid) {
        return getByDepartid(DAO, departid, DAO.TABLENAME);
    }

    public static List<Adcourses> getByDepartid(Integer departid, String TABLENAME2) {
        AdcoursesDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByDepartid(DAO, departid, TABLENAME2);
    }

    public static List<Adcourses> getByDepartid(final AdcoursesDAO DAO, final Integer departid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByDepartid(departid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Adcourses> result = newList();
            Set<Integer> m1 = varsByDepartid.get(departid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Adcourses e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _departid = e.getDepartid();
                if(_departid != departid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Adcourses update(Adcourses adcourses) {
        AdcoursesDAO DAO = DAO();
        return update(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses update(AdcoursesDAO DAO, Adcourses adcourses) {
        return update(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses update(Adcourses adcourses, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return update(DAO, adcourses, TABLENAME2);
    }

    public static Adcourses update(final AdcoursesDAO DAO, final Adcourses adcourses,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(adcourses, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(adcourses, TABLENAME2);
            if(n == -1) 
                return adcourses;
            else if(n <= 0) 
                return null;
        }
        return adcourses;
    }

    public static Adcourses asyncUpdate(Adcourses adcourses) {
        AdcoursesDAO DAO = DAO();
        return asyncUpdate(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses asyncUpdate(AdcoursesDAO DAO, Adcourses adcourses) {
        return asyncUpdate(DAO, adcourses, DAO.TABLENAME);
    }

    public static Adcourses asyncUpdate(Adcourses adcourses, String TABLENAME2) {
        AdcoursesDAO DAO = DAO();
        return asyncUpdate(DAO, adcourses, TABLENAME2);
    }

    public static Adcourses asyncUpdate(final AdcoursesDAO DAO, final Adcourses adcourses,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(adcourses, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(adcourses, TABLENAME2);
        }
        return adcourses;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AdcoursesDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AdcoursesDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AdcoursesDAO DAO, final String TABLENAME2) {
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

        List<Adcourses> adcoursess = getAll();
        for (Adcourses adcourses : adcoursess) {
            int n = DAO.insert2(adcourses, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
