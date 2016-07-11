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


//learnhall3_design - kindclass
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class KindclassInternal extends InternalSupport{
    static Log log = LogFactory.getLog(KindclassInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public KindclassInternal(){}

    public static KindclassDAO DAO(){
        return KindclassEntity.KindclassDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Kindclass[MAX];
    }
    private static Kindclass[] FIXED = new Kindclass[MAX];
    public static final Map<Integer, Kindclass> vars = newSortedMap();

    private static final List<Kindclass> fixedCache = newList();

    public static void put(Kindclass kindclass, boolean force){
        if(kindclass == null || kindclass.id <= 0) return ;

        int id = kindclass.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(kindclass))
                	FIXED[id - 1] = kindclass;
                if (!fixedCache.contains(kindclass))
                	fixedCache.add(kindclass);
            }
        } else {
            vars.put(id, kindclass);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Kindclass[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(KindclassDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(KindclassDAO DAO, String TABLENAME2){
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

    public static void relocate(KindclassDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        KindclassDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(KindclassDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        KindclassDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(KindclassDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        KindclassDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(KindclassDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        KindclassDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(KindclassDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(KindclassDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        KindclassDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(KindclassDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(KindclassDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        KindclassDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(KindclassDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Kindclass[maxId(DAO)];

        List<Kindclass> kindclasss = DAO.selectAll();
        for (Kindclass kindclass : kindclasss) {
            kindclass.reset();
            put(kindclass, true);
        }
    }

    public static Map toMap(Kindclass kindclass){
        return kindclass.toMap();
    }

    public static List<Map> toMap(List<Kindclass> kindclasss){
        List<Map> ret = new Vector<Map>();
        for (Kindclass kindclass : kindclasss){
            Map e = kindclass.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Kindclass> sortZh(List<Kindclass> kindclasss, final String key) {
        Collections.sort(kindclasss, new Comparator<Kindclass>() {
            public int compare(Kindclass o1, Kindclass o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return kindclasss;
    }

    public static List<Kindclass> sort(List<Kindclass> kindclasss, final String key) {
        Collections.sort(kindclasss, new Comparator<Kindclass>() {
            public int compare(Kindclass o1, Kindclass o2) {
                return o1.compareTo(o2, key);
            }
        });
        return kindclasss;
    }

    public static List<Kindclass> sort(List<Kindclass> kindclasss){
        Collections.sort(kindclasss, new Comparator<Kindclass>(){
            public int compare(Kindclass o1, Kindclass o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return kindclasss;
    }

    public static List<Kindclass> sortReverse(List<Kindclass> kindclasss){
        Collections.sort(kindclasss, new Comparator<Kindclass>(){
            public int compare(Kindclass o1, Kindclass o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return kindclasss;
    }

    public static Kindclass insert(Kindclass kindclass) {
        KindclassDAO DAO = DAO();
        return insert(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass insert(KindclassDAO DAO, Kindclass kindclass) {
        return insert(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass insert(Kindclass kindclass, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return insert(DAO, kindclass, TABLENAME2);
    }

    public static Kindclass insert(KindclassDAO DAO, Kindclass kindclass, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(kindclass, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        kindclass.id = n;
        if(cacheModel != NO_CACHE) put(kindclass, true);

        return kindclass;
    }

    public static Kindclass asyncInsert(Kindclass kindclass) {
        KindclassDAO DAO = DAO();
        return asyncInsert(DAO, kindclass, DAO.TABLENAME);
    }
    public static Kindclass asyncInsert(KindclassDAO DAO, Kindclass kindclass) {
        return asyncInsert(DAO, kindclass, DAO.TABLENAME);
    }
    public static Kindclass asyncInsert(Kindclass kindclass, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return asyncInsert(DAO, kindclass, TABLENAME2);
    }
    public static Kindclass asyncInsert(KindclassDAO DAO, Kindclass kindclass, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(kindclass, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        kindclass.id = n;
        if(cacheModel != NO_CACHE) put(kindclass, true);
        return kindclass;
    }
    public static Kindclass insert2(Kindclass kindclass) {
        KindclassDAO DAO = DAO();
        return insert2(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass insert2(KindclassDAO DAO, Kindclass kindclass) {
        return insert2(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass insert2(Kindclass kindclass, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return insert2(DAO, kindclass, TABLENAME2);
    }

    public static Kindclass insert2(KindclassDAO DAO, Kindclass kindclass, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(kindclass, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        kindclass.id = n;
        if(cacheModel != NO_CACHE) put(kindclass, true);

        return kindclass;
    }

    public static Kindclass asyncInsert2(Kindclass kindclass) {
        KindclassDAO DAO = DAO();
        return asyncInsert2(DAO, kindclass, DAO.TABLENAME);
    }
    public static Kindclass asyncInsert2(KindclassDAO DAO, Kindclass kindclass) {
        return asyncInsert2(DAO, kindclass, DAO.TABLENAME);
    }
    public static Kindclass asyncInsert2(Kindclass kindclass, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return asyncInsert2(DAO, kindclass, TABLENAME2);
    }
    public static Kindclass asyncInsert2(KindclassDAO DAO, Kindclass kindclass, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        kindclass.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(kindclass, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(kindclass, true);
        return kindclass;
    }
    public static int[] insert(List<Kindclass> kindclasss) {
        KindclassDAO DAO = DAO();
        return insert(DAO, kindclasss, DAO.TABLENAME);
    }

    public static int[] insert(KindclassDAO DAO, List<Kindclass> kindclasss) {
        return insert(DAO, kindclasss, DAO.TABLENAME);
    }

    public static int[] insert(List<Kindclass> kindclasss, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return insert(DAO, kindclasss, TABLENAME2);
    }

    public static int[] insert(KindclassDAO DAO, List<Kindclass> kindclasss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(kindclasss, TABLENAME2);
            int n = 0;
            for(Kindclass kindclass : kindclasss){
                kindclass.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[kindclasss.size()];
        int n = 0;
        for(Kindclass kindclass : kindclasss){
            kindclass = insert(DAO, kindclass, TABLENAME2);
            ret[n++] = (kindclass == null) ? 0 : (int)kindclass.id;
        }
        return ret;
    }

    public static int delete(Kindclass kindclass) {
        int id = kindclass.id;
        KindclassDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        KindclassDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(KindclassDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(KindclassDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Kindclass kindclass) {
        int id = kindclass.id;
        KindclassDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        KindclassDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(KindclassDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(KindclassDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        KindclassDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(KindclassDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(KindclassDAO DAO, int[] ids,String TABLENAME2) {
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
        KindclassDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, KindclassDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final KindclassDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Kindclass> beans) {
        KindclassDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Kindclass> beans, KindclassDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Kindclass> beans, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Kindclass> beans, final KindclassDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Kindclass kindclass : beans){
                int id = kindclass.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Kindclass> getAll() {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getAll(KindclassDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getAll(String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Kindclass> getAll(final KindclassDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kindclass> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Kindclass> getNoSortAll() {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getNoSortAll(KindclassDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getNoSortAll(String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Kindclass> getNoSortAll(final KindclassDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Kindclass> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kindclass> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Kindclass kindclass = FIXED[i];
                if (kindclass != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Kindclass> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Kindclass> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(KindclassDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final KindclassDAO DAO, final String TABLENAME2) {
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
        KindclassDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(KindclassDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final KindclassDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Kindclass> getIn(List<Integer> keys) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getIn(List<Integer> keys, KindclassDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getIn(List<Integer> keys, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Kindclass> getIn(final List<Integer> keys, final KindclassDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kindclass> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Kindclass> getNoSortIn(List<Integer> keys) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getNoSortIn(List<Integer> keys, KindclassDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Kindclass> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Kindclass> getNoSortIn(final List<Integer> keys, final KindclassDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Kindclass> result = newList();
            for (int key : keys) {
                Kindclass kindclass = getByKeyFromMemory(key);
                if( kindclass == null ) continue;
                result.add(kindclass);
            }
            return result;
        }
    }

    public static List<Kindclass> getLast(int num) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Kindclass> getLast(KindclassDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Kindclass> getLast(int num, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Kindclass> getLast(final KindclassDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Kindclass> result = newList();
            List<Kindclass> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Kindclass last() {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Kindclass last(KindclassDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Kindclass last(String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Kindclass last(final KindclassDAO DAO, final String TABLENAME2) {
        Kindclass result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        KindclassDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(KindclassDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final KindclassDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Kindclass> getGtKey(int id) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Kindclass> getGtKey(KindclassDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Kindclass> getGtKey(int id, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Kindclass> getGtKey(final KindclassDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kindclass> result = newList();
            List<Kindclass> kindclasss = getAll();
            for (Kindclass kindclass : kindclasss) {
                if(kindclass.id <= id) continue;
                result.add(kindclass);
            }
            return result;
        }
    }

    public static Kindclass getByKey(int id) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Kindclass> asyncGetByKey(final int id) {
        Future<Kindclass> f = Async.exec(new Callable<Kindclass>() {
            public Kindclass call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Kindclass getByKey(KindclassDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Kindclass getByKey(int id, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Kindclass getByKey(final KindclassDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Kindclass getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Kindclass deleteFromMemory(final int id) {
        Kindclass kindclass;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            kindclass = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(kindclass);
        } else {
            kindclass = vars.remove(id);
        }
        if(kindclass == null) return null;

        return kindclass;
    }

    public static List<Kindclass> getByPage(int page, int size) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Kindclass> getByPage(KindclassDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Kindclass> getByPage(int page, int size, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Kindclass> getByPage(final KindclassDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Kindclass> result = newList();
            List<Kindclass> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(KindclassDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        KindclassDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final KindclassDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Kindclass update(Kindclass kindclass) {
        KindclassDAO DAO = DAO();
        return update(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass update(KindclassDAO DAO, Kindclass kindclass) {
        return update(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass update(Kindclass kindclass, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return update(DAO, kindclass, TABLENAME2);
    }

    public static Kindclass update(final KindclassDAO DAO, final Kindclass kindclass,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(kindclass, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(kindclass, TABLENAME2);
            if(n == -1) 
                return kindclass;
            else if(n <= 0) 
                return null;
        }
        return kindclass;
    }

    public static Kindclass asyncUpdate(Kindclass kindclass) {
        KindclassDAO DAO = DAO();
        return asyncUpdate(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass asyncUpdate(KindclassDAO DAO, Kindclass kindclass) {
        return asyncUpdate(DAO, kindclass, DAO.TABLENAME);
    }

    public static Kindclass asyncUpdate(Kindclass kindclass, String TABLENAME2) {
        KindclassDAO DAO = DAO();
        return asyncUpdate(DAO, kindclass, TABLENAME2);
    }

    public static Kindclass asyncUpdate(final KindclassDAO DAO, final Kindclass kindclass,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(kindclass, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(kindclass, TABLENAME2);
        }
        return kindclass;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        KindclassDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(KindclassDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final KindclassDAO DAO, final String TABLENAME2) {
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

        List<Kindclass> kindclasss = getAll();
        for (Kindclass kindclass : kindclasss) {
            int n = DAO.insert2(kindclass, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
