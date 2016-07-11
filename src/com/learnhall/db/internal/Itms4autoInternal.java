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

import static com.learnhall.db.bean.Itms4auto.Col;

//learnhall3_design - itms4auto
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Itms4autoInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Itms4autoInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Itms4autoInternal(){}

    public static Itms4autoDAO DAO(){
        return Itms4autoEntity.Itms4autoDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Itms4auto[MAX];
    }
    private static Itms4auto[] FIXED = new Itms4auto[MAX];
    public static final Map<Integer, Itms4auto> vars = newSortedMap();
    public static final Map<Integer, Integer> varsByKindid = newSortedMap();

    private static final List<Itms4auto> fixedCache = newList();

    public static void put(Itms4auto itms4auto, boolean force){
        if(itms4auto == null || itms4auto.id <= 0) return ;

        int id = itms4auto.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(itms4auto))
                	FIXED[id - 1] = itms4auto;
                if (!fixedCache.contains(itms4auto))
                	fixedCache.add(itms4auto);
            }
        } else {
            vars.put(id, itms4auto);
        }

        { // 单-唯一索引 remove old index kindid
          Object ov = itms4auto.oldVal(Col.kindid);
          if(ov != null)
              varsByKindid.remove(ov);
          if(ov != null || force){ // put new index
            int kindid = itms4auto.getKindid();
            varsByKindid.put(kindid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByKindid.clear();
        FIXED = new Itms4auto[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Itms4autoDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Itms4autoDAO DAO, String TABLENAME2){
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

    public static void relocate(Itms4autoDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Itms4autoDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Itms4autoDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Itms4autoDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Itms4autoDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Itms4autoDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Itms4autoDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Itms4autoDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Itms4autoDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Itms4autoDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Itms4autoDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Itms4autoDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Itms4autoDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Itms4auto[maxId(DAO)];

        List<Itms4auto> itms4autos = DAO.selectAll();
        for (Itms4auto itms4auto : itms4autos) {
            itms4auto.reset();
            put(itms4auto, true);
        }
    }

    public static Map toMap(Itms4auto itms4auto){
        return itms4auto.toMap();
    }

    public static List<Map> toMap(List<Itms4auto> itms4autos){
        List<Map> ret = new Vector<Map>();
        for (Itms4auto itms4auto : itms4autos){
            Map e = itms4auto.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Itms4auto> sortZh(List<Itms4auto> itms4autos, final String key) {
        Collections.sort(itms4autos, new Comparator<Itms4auto>() {
            public int compare(Itms4auto o1, Itms4auto o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return itms4autos;
    }

    public static List<Itms4auto> sort(List<Itms4auto> itms4autos, final String key) {
        Collections.sort(itms4autos, new Comparator<Itms4auto>() {
            public int compare(Itms4auto o1, Itms4auto o2) {
                return o1.compareTo(o2, key);
            }
        });
        return itms4autos;
    }

    public static List<Itms4auto> sort(List<Itms4auto> itms4autos){
        Collections.sort(itms4autos, new Comparator<Itms4auto>(){
            public int compare(Itms4auto o1, Itms4auto o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return itms4autos;
    }

    public static List<Itms4auto> sortReverse(List<Itms4auto> itms4autos){
        Collections.sort(itms4autos, new Comparator<Itms4auto>(){
            public int compare(Itms4auto o1, Itms4auto o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return itms4autos;
    }

    public static List<Itms4auto> sortKindid(List<Itms4auto> itms4autos){
        Collections.sort(itms4autos, new Comparator<Itms4auto>(){
            public int compare(Itms4auto o1, Itms4auto o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return itms4autos;
    }

    public static List<Itms4auto> sortKindidRo(List<Itms4auto> itms4autos){
        Collections.sort(itms4autos, new Comparator<Itms4auto>(){
            public int compare(Itms4auto o1, Itms4auto o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return itms4autos;
    }

    public static Itms4auto insert(Itms4auto itms4auto) {
        Itms4autoDAO DAO = DAO();
        return insert(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto insert(Itms4autoDAO DAO, Itms4auto itms4auto) {
        return insert(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto insert(Itms4auto itms4auto, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return insert(DAO, itms4auto, TABLENAME2);
    }

    public static Itms4auto insert(Itms4autoDAO DAO, Itms4auto itms4auto, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(itms4auto, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        itms4auto.id = n;
        if(cacheModel != NO_CACHE) put(itms4auto, true);

        return itms4auto;
    }

    public static Itms4auto asyncInsert(Itms4auto itms4auto) {
        Itms4autoDAO DAO = DAO();
        return asyncInsert(DAO, itms4auto, DAO.TABLENAME);
    }
    public static Itms4auto asyncInsert(Itms4autoDAO DAO, Itms4auto itms4auto) {
        return asyncInsert(DAO, itms4auto, DAO.TABLENAME);
    }
    public static Itms4auto asyncInsert(Itms4auto itms4auto, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return asyncInsert(DAO, itms4auto, TABLENAME2);
    }
    public static Itms4auto asyncInsert(Itms4autoDAO DAO, Itms4auto itms4auto, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(itms4auto, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        itms4auto.id = n;
        if(cacheModel != NO_CACHE) put(itms4auto, true);
        return itms4auto;
    }
    public static Itms4auto insert2(Itms4auto itms4auto) {
        Itms4autoDAO DAO = DAO();
        return insert2(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto insert2(Itms4autoDAO DAO, Itms4auto itms4auto) {
        return insert2(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto insert2(Itms4auto itms4auto, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return insert2(DAO, itms4auto, TABLENAME2);
    }

    public static Itms4auto insert2(Itms4autoDAO DAO, Itms4auto itms4auto, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(itms4auto, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        itms4auto.id = n;
        if(cacheModel != NO_CACHE) put(itms4auto, true);

        return itms4auto;
    }

    public static Itms4auto asyncInsert2(Itms4auto itms4auto) {
        Itms4autoDAO DAO = DAO();
        return asyncInsert2(DAO, itms4auto, DAO.TABLENAME);
    }
    public static Itms4auto asyncInsert2(Itms4autoDAO DAO, Itms4auto itms4auto) {
        return asyncInsert2(DAO, itms4auto, DAO.TABLENAME);
    }
    public static Itms4auto asyncInsert2(Itms4auto itms4auto, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return asyncInsert2(DAO, itms4auto, TABLENAME2);
    }
    public static Itms4auto asyncInsert2(Itms4autoDAO DAO, Itms4auto itms4auto, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        itms4auto.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(itms4auto, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(itms4auto, true);
        return itms4auto;
    }
    public static int[] insert(List<Itms4auto> itms4autos) {
        Itms4autoDAO DAO = DAO();
        return insert(DAO, itms4autos, DAO.TABLENAME);
    }

    public static int[] insert(Itms4autoDAO DAO, List<Itms4auto> itms4autos) {
        return insert(DAO, itms4autos, DAO.TABLENAME);
    }

    public static int[] insert(List<Itms4auto> itms4autos, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return insert(DAO, itms4autos, TABLENAME2);
    }

    public static int[] insert(Itms4autoDAO DAO, List<Itms4auto> itms4autos, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(itms4autos, TABLENAME2);
            int n = 0;
            for(Itms4auto itms4auto : itms4autos){
                itms4auto.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[itms4autos.size()];
        int n = 0;
        for(Itms4auto itms4auto : itms4autos){
            itms4auto = insert(DAO, itms4auto, TABLENAME2);
            ret[n++] = (itms4auto == null) ? 0 : (int)itms4auto.id;
        }
        return ret;
    }

    public static int delete(Itms4auto itms4auto) {
        int id = itms4auto.id;
        Itms4autoDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Itms4autoDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Itms4autoDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Itms4autoDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Itms4auto itms4auto) {
        int id = itms4auto.id;
        Itms4autoDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Itms4autoDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Itms4autoDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Itms4autoDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Itms4autoDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Itms4autoDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Itms4autoDAO DAO, int[] ids,String TABLENAME2) {
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
        Itms4autoDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Itms4autoDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Itms4autoDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Itms4auto> beans) {
        Itms4autoDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Itms4auto> beans, Itms4autoDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Itms4auto> beans, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Itms4auto> beans, final Itms4autoDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Itms4auto itms4auto : beans){
                int id = itms4auto.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Itms4auto> getAll() {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getAll(Itms4autoDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getAll(String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Itms4auto> getAll(final Itms4autoDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4auto> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Itms4auto> getNoSortAll() {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getNoSortAll(Itms4autoDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getNoSortAll(String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Itms4auto> getNoSortAll(final Itms4autoDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Itms4auto> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4auto> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Itms4auto itms4auto = FIXED[i];
                if (itms4auto != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Itms4auto> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Itms4auto> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Itms4autoDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Itms4autoDAO DAO, final String TABLENAME2) {
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
        Itms4autoDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Itms4autoDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Itms4autoDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Itms4auto> getIn(List<Integer> keys) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getIn(List<Integer> keys, Itms4autoDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getIn(List<Integer> keys, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Itms4auto> getIn(final List<Integer> keys, final Itms4autoDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4auto> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Itms4auto> getNoSortIn(List<Integer> keys) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getNoSortIn(List<Integer> keys, Itms4autoDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Itms4auto> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Itms4auto> getNoSortIn(final List<Integer> keys, final Itms4autoDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Itms4auto> result = newList();
            for (int key : keys) {
                Itms4auto itms4auto = getByKeyFromMemory(key);
                if( itms4auto == null ) continue;
                result.add(itms4auto);
            }
            return result;
        }
    }

    public static List<Itms4auto> getLast(int num) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Itms4auto> getLast(Itms4autoDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Itms4auto> getLast(int num, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Itms4auto> getLast(final Itms4autoDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Itms4auto> result = newList();
            List<Itms4auto> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Itms4auto last() {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Itms4auto last(Itms4autoDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Itms4auto last(String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Itms4auto last(final Itms4autoDAO DAO, final String TABLENAME2) {
        Itms4auto result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Itms4autoDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Itms4autoDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Itms4autoDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Itms4auto> getGtKey(int id) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Itms4auto> getGtKey(Itms4autoDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Itms4auto> getGtKey(int id, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Itms4auto> getGtKey(final Itms4autoDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4auto> result = newList();
            List<Itms4auto> itms4autos = getAll();
            for (Itms4auto itms4auto : itms4autos) {
                if(itms4auto.id <= id) continue;
                result.add(itms4auto);
            }
            return result;
        }
    }

    public static Itms4auto getByKey(int id) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Itms4auto> asyncGetByKey(final int id) {
        Future<Itms4auto> f = Async.exec(new Callable<Itms4auto>() {
            public Itms4auto call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Itms4auto getByKey(Itms4autoDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Itms4auto getByKey(int id, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Itms4auto getByKey(final Itms4autoDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Itms4auto getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Itms4auto deleteFromMemory(final int id) {
        Itms4auto itms4auto;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            itms4auto = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(itms4auto);
        } else {
            itms4auto = vars.remove(id);
        }
        if(itms4auto == null) return null;

        int kindid = itms4auto.getKindid();
        varsByKindid.remove(kindid);

        return itms4auto;
    }

    public static List<Itms4auto> getByPage(int page, int size) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Itms4auto> getByPage(Itms4autoDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Itms4auto> getByPage(int page, int size, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Itms4auto> getByPage(final Itms4autoDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Itms4auto> result = newList();
            List<Itms4auto> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Itms4autoDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Itms4autoDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Itms4auto getByKindid(Integer kindid) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static Itms4auto getByKindid(Itms4autoDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static Itms4auto getByKindid(Integer kindid, String TABLENAME2) {
        Itms4autoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static Itms4auto getByKindid(final Itms4autoDAO DAO, final int kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByKindid.get(kindid);
            if(id == null) return null;
            Itms4auto result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getKindid() != kindid){
                varsByKindid.remove(kindid);
                return null;
            }
            return result;
        }
    }

    public static Itms4auto update(Itms4auto itms4auto) {
        Itms4autoDAO DAO = DAO();
        return update(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto update(Itms4autoDAO DAO, Itms4auto itms4auto) {
        return update(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto update(Itms4auto itms4auto, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return update(DAO, itms4auto, TABLENAME2);
    }

    public static Itms4auto update(final Itms4autoDAO DAO, final Itms4auto itms4auto,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(itms4auto, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(itms4auto, TABLENAME2);
            if(n == -1) 
                return itms4auto;
            else if(n <= 0) 
                return null;
        }
        return itms4auto;
    }

    public static Itms4auto asyncUpdate(Itms4auto itms4auto) {
        Itms4autoDAO DAO = DAO();
        return asyncUpdate(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto asyncUpdate(Itms4autoDAO DAO, Itms4auto itms4auto) {
        return asyncUpdate(DAO, itms4auto, DAO.TABLENAME);
    }

    public static Itms4auto asyncUpdate(Itms4auto itms4auto, String TABLENAME2) {
        Itms4autoDAO DAO = DAO();
        return asyncUpdate(DAO, itms4auto, TABLENAME2);
    }

    public static Itms4auto asyncUpdate(final Itms4autoDAO DAO, final Itms4auto itms4auto,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(itms4auto, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(itms4auto, TABLENAME2);
        }
        return itms4auto;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Itms4autoDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Itms4autoDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Itms4autoDAO DAO, final String TABLENAME2) {
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

        List<Itms4auto> itms4autos = getAll();
        for (Itms4auto itms4auto : itms4autos) {
            int n = DAO.insert2(itms4auto, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
