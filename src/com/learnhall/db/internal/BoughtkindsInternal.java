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

import static com.learnhall.db.bean.Boughtkinds.Col;

//learnhall3_design - boughtkinds
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class BoughtkindsInternal extends InternalSupport{
    static Log log = LogFactory.getLog(BoughtkindsInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public BoughtkindsInternal(){}

    public static BoughtkindsDAO DAO(){
        return BoughtkindsEntity.BoughtkindsDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Boughtkinds[MAX];
    }
    private static Boughtkinds[] FIXED = new Boughtkinds[MAX];
    public static final Map<Integer, Boughtkinds> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKindid = newSortedMap();
    public static final Map<String, Integer> varsByCustomeridKindid = newSortedMap();

    private static final List<Boughtkinds> fixedCache = newList();

    public static void put(Boughtkinds boughtkinds, boolean force){
        if(boughtkinds == null || boughtkinds.id <= 0) return ;

        int id = boughtkinds.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(boughtkinds))
                	FIXED[id - 1] = boughtkinds;
                if (!fixedCache.contains(boughtkinds))
                	fixedCache.add(boughtkinds);
            }
        } else {
            vars.put(id, boughtkinds);
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = boughtkinds.oldVal(Col.customerid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByCustomerid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByCustomerid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int customerid = boughtkinds.getCustomerid();
            Set m1 = varsByCustomerid.get(customerid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByCustomerid.put(customerid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kindid
          Object ov = boughtkinds.oldVal(Col.kindid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByKindid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByKindid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int kindid = boughtkinds.getKindid();
            Set m2 = varsByKindid.get(kindid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByKindid.put(kindid, m2);
            }
            m2.add(id);
          }
        }

        { // customerid_kind
          boolean bChanged = boughtkinds.inChanged(Col.customerid, Col.kindid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vcustomerid = boughtkinds.oldOrNew(Col.customerid);
            Object vkindid = boughtkinds.oldOrNew(Col.kindid);
            String okey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
            varsByCustomeridKindid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vcustomerid = boughtkinds.getCustomerid();
              int vkindid = boughtkinds.getKindid();
              String vkey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
              varsByCustomeridKindid.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustomerid.clear();
        varsByKindid.clear();
        varsByCustomeridKindid.clear();
        FIXED = new Boughtkinds[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(BoughtkindsDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(BoughtkindsDAO DAO, String TABLENAME2){
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

    public static void relocate(BoughtkindsDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        BoughtkindsDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(BoughtkindsDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        BoughtkindsDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(BoughtkindsDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        BoughtkindsDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(BoughtkindsDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(BoughtkindsDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(BoughtkindsDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(BoughtkindsDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(BoughtkindsDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        BoughtkindsDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(BoughtkindsDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Boughtkinds[maxId(DAO)];

        List<Boughtkinds> boughtkindss = DAO.selectAll();
        for (Boughtkinds boughtkinds : boughtkindss) {
            boughtkinds.reset();
            put(boughtkinds, true);
        }
    }

    public static Map toMap(Boughtkinds boughtkinds){
        return boughtkinds.toMap();
    }

    public static List<Map> toMap(List<Boughtkinds> boughtkindss){
        List<Map> ret = new Vector<Map>();
        for (Boughtkinds boughtkinds : boughtkindss){
            Map e = boughtkinds.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Boughtkinds> sortZh(List<Boughtkinds> boughtkindss, final String key) {
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>() {
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sort(List<Boughtkinds> boughtkindss, final String key) {
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>() {
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                return o1.compareTo(o2, key);
            }
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sort(List<Boughtkinds> boughtkindss){
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>(){
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sortReverse(List<Boughtkinds> boughtkindss){
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>(){
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sortCustomerid(List<Boughtkinds> boughtkindss){
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>(){
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sortCustomeridRo(List<Boughtkinds> boughtkindss){
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>(){
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sortKindid(List<Boughtkinds> boughtkindss){
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>(){
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return boughtkindss;
    }

    public static List<Boughtkinds> sortKindidRo(List<Boughtkinds> boughtkindss){
        Collections.sort(boughtkindss, new Comparator<Boughtkinds>(){
            public int compare(Boughtkinds o1, Boughtkinds o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return boughtkindss;
    }

    public static Boughtkinds insert(Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = DAO();
        return insert(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds insert(BoughtkindsDAO DAO, Boughtkinds boughtkinds) {
        return insert(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds insert(Boughtkinds boughtkinds, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return insert(DAO, boughtkinds, TABLENAME2);
    }

    public static Boughtkinds insert(BoughtkindsDAO DAO, Boughtkinds boughtkinds, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(boughtkinds, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        boughtkinds.id = n;
        if(cacheModel != NO_CACHE) put(boughtkinds, true);

        return boughtkinds;
    }

    public static Boughtkinds asyncInsert(Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = DAO();
        return asyncInsert(DAO, boughtkinds, DAO.TABLENAME);
    }
    public static Boughtkinds asyncInsert(BoughtkindsDAO DAO, Boughtkinds boughtkinds) {
        return asyncInsert(DAO, boughtkinds, DAO.TABLENAME);
    }
    public static Boughtkinds asyncInsert(Boughtkinds boughtkinds, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return asyncInsert(DAO, boughtkinds, TABLENAME2);
    }
    public static Boughtkinds asyncInsert(BoughtkindsDAO DAO, Boughtkinds boughtkinds, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(boughtkinds, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        boughtkinds.id = n;
        if(cacheModel != NO_CACHE) put(boughtkinds, true);
        return boughtkinds;
    }
    public static Boughtkinds insert2(Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = DAO();
        return insert2(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds insert2(BoughtkindsDAO DAO, Boughtkinds boughtkinds) {
        return insert2(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds insert2(Boughtkinds boughtkinds, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return insert2(DAO, boughtkinds, TABLENAME2);
    }

    public static Boughtkinds insert2(BoughtkindsDAO DAO, Boughtkinds boughtkinds, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(boughtkinds, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        boughtkinds.id = n;
        if(cacheModel != NO_CACHE) put(boughtkinds, true);

        return boughtkinds;
    }

    public static Boughtkinds asyncInsert2(Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = DAO();
        return asyncInsert2(DAO, boughtkinds, DAO.TABLENAME);
    }
    public static Boughtkinds asyncInsert2(BoughtkindsDAO DAO, Boughtkinds boughtkinds) {
        return asyncInsert2(DAO, boughtkinds, DAO.TABLENAME);
    }
    public static Boughtkinds asyncInsert2(Boughtkinds boughtkinds, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return asyncInsert2(DAO, boughtkinds, TABLENAME2);
    }
    public static Boughtkinds asyncInsert2(BoughtkindsDAO DAO, Boughtkinds boughtkinds, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        boughtkinds.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(boughtkinds, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(boughtkinds, true);
        return boughtkinds;
    }
    public static int[] insert(List<Boughtkinds> boughtkindss) {
        BoughtkindsDAO DAO = DAO();
        return insert(DAO, boughtkindss, DAO.TABLENAME);
    }

    public static int[] insert(BoughtkindsDAO DAO, List<Boughtkinds> boughtkindss) {
        return insert(DAO, boughtkindss, DAO.TABLENAME);
    }

    public static int[] insert(List<Boughtkinds> boughtkindss, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return insert(DAO, boughtkindss, TABLENAME2);
    }

    public static int[] insert(BoughtkindsDAO DAO, List<Boughtkinds> boughtkindss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(boughtkindss, TABLENAME2);
            int n = 0;
            for(Boughtkinds boughtkinds : boughtkindss){
                boughtkinds.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[boughtkindss.size()];
        int n = 0;
        for(Boughtkinds boughtkinds : boughtkindss){
            boughtkinds = insert(DAO, boughtkinds, TABLENAME2);
            ret[n++] = (boughtkinds == null) ? 0 : (int)boughtkinds.id;
        }
        return ret;
    }

    public static int delete(Boughtkinds boughtkinds) {
        int id = boughtkinds.id;
        BoughtkindsDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        BoughtkindsDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(BoughtkindsDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(BoughtkindsDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Boughtkinds boughtkinds) {
        int id = boughtkinds.id;
        BoughtkindsDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        BoughtkindsDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(BoughtkindsDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(BoughtkindsDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        BoughtkindsDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(BoughtkindsDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(BoughtkindsDAO DAO, int[] ids,String TABLENAME2) {
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
        BoughtkindsDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, BoughtkindsDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final BoughtkindsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Boughtkinds> beans) {
        BoughtkindsDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Boughtkinds> beans, BoughtkindsDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Boughtkinds> beans, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Boughtkinds> beans, final BoughtkindsDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Boughtkinds boughtkinds : beans){
                int id = boughtkinds.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Boughtkinds> getAll() {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getAll(BoughtkindsDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getAll(String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Boughtkinds> getAll(final BoughtkindsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Boughtkinds> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Boughtkinds> getNoSortAll() {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getNoSortAll(BoughtkindsDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getNoSortAll(String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Boughtkinds> getNoSortAll(final BoughtkindsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Boughtkinds> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Boughtkinds> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Boughtkinds boughtkinds = FIXED[i];
                if (boughtkinds != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Boughtkinds> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Boughtkinds> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(BoughtkindsDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final BoughtkindsDAO DAO, final String TABLENAME2) {
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
        BoughtkindsDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(BoughtkindsDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final BoughtkindsDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Boughtkinds> getIn(List<Integer> keys) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getIn(List<Integer> keys, BoughtkindsDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getIn(List<Integer> keys, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Boughtkinds> getIn(final List<Integer> keys, final BoughtkindsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Boughtkinds> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Boughtkinds> getNoSortIn(List<Integer> keys) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getNoSortIn(List<Integer> keys, BoughtkindsDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Boughtkinds> getNoSortIn(final List<Integer> keys, final BoughtkindsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Boughtkinds> result = newList();
            for (int key : keys) {
                Boughtkinds boughtkinds = getByKeyFromMemory(key);
                if( boughtkinds == null ) continue;
                result.add(boughtkinds);
            }
            return result;
        }
    }

    public static List<Boughtkinds> getLast(int num) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getLast(BoughtkindsDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getLast(int num, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Boughtkinds> getLast(final BoughtkindsDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Boughtkinds> result = newList();
            List<Boughtkinds> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Boughtkinds last() {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Boughtkinds last(BoughtkindsDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Boughtkinds last(String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Boughtkinds last(final BoughtkindsDAO DAO, final String TABLENAME2) {
        Boughtkinds result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        BoughtkindsDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(BoughtkindsDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final BoughtkindsDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Boughtkinds> getGtKey(int id) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getGtKey(BoughtkindsDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getGtKey(int id, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Boughtkinds> getGtKey(final BoughtkindsDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Boughtkinds> result = newList();
            List<Boughtkinds> boughtkindss = getAll();
            for (Boughtkinds boughtkinds : boughtkindss) {
                if(boughtkinds.id <= id) continue;
                result.add(boughtkinds);
            }
            return result;
        }
    }

    public static Boughtkinds getByKey(int id) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Boughtkinds> asyncGetByKey(final int id) {
        Future<Boughtkinds> f = Async.exec(new Callable<Boughtkinds>() {
            public Boughtkinds call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Boughtkinds getByKey(BoughtkindsDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Boughtkinds getByKey(int id, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Boughtkinds getByKey(final BoughtkindsDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Boughtkinds getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Boughtkinds deleteFromMemory(final int id) {
        Boughtkinds boughtkinds;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            boughtkinds = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(boughtkinds);
        } else {
            boughtkinds = vars.remove(id);
        }
        if(boughtkinds == null) return null;

        int customerid = boughtkinds.getCustomerid();
        Set m1 = varsByCustomerid.get(customerid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int kindid = boughtkinds.getKindid();
        Set m2 = varsByKindid.get(kindid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByKindid.remove(kindid);
        }

        { // customerid_kind
            int vcustomerid = boughtkinds.getCustomerid();
            int vkindid = boughtkinds.getKindid();
            String vkey = com.bowlong.lang.PStr.b().a(vcustomerid, "-", vkindid).e();
            varsByCustomeridKindid.remove(vkey);
        }

        return boughtkinds;
    }

    public static List<Boughtkinds> getByPage(int page, int size) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getByPage(BoughtkindsDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getByPage(int page, int size, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Boughtkinds> getByPage(final BoughtkindsDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Boughtkinds> result = newList();
            List<Boughtkinds> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(BoughtkindsDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final BoughtkindsDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustomerid(Integer customerid) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(BoughtkindsDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final BoughtkindsDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Boughtkinds> boughtkindss = getByCustomerid(DAO, customerid, TABLENAME2);
        return boughtkindss.size();
    }

    public static List<Boughtkinds> getByCustomerid(Integer customerid) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getByCustomerid(BoughtkindsDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getByCustomerid(Integer customerid, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Boughtkinds> getByCustomerid(final BoughtkindsDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Boughtkinds> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Boughtkinds e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _customerid = e.getCustomerid();
                if(_customerid != customerid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByKindid(Integer kindid) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(BoughtkindsDAO DAO, Integer kindid) {
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Integer kindid, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, TABLENAME2);
    }

    public static int countByKindid(final BoughtkindsDAO DAO, final Integer kindid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKindid(kindid, TABLENAME2);
        }
        List<Boughtkinds> boughtkindss = getByKindid(DAO, kindid, TABLENAME2);
        return boughtkindss.size();
    }

    public static List<Boughtkinds> getByKindid(Integer kindid) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getByKindid(BoughtkindsDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Boughtkinds> getByKindid(Integer kindid, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static List<Boughtkinds> getByKindid(final BoughtkindsDAO DAO, final Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Boughtkinds> result = newList();
            Set<Integer> m1 = varsByKindid.get(kindid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Boughtkinds e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _kindid = e.getKindid();
                if(_kindid != kindid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Boughtkinds getByCustomeridKindid(Integer customerid, Integer kindid) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static Boughtkinds getByCustomeridKindid(BoughtkindsDAO DAO, Integer customerid, Integer kindid) {
        return getByCustomeridKindid(DAO, customerid, kindid, DAO.TABLENAME);
    }

    public static Boughtkinds getByCustomeridKindid(Integer customerid, Integer kindid, String TABLENAME2) {
        BoughtkindsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomeridKindid(DAO, customerid, kindid, TABLENAME2);
    }

    public static Boughtkinds getByCustomeridKindid(final BoughtkindsDAO DAO, Integer customerid, Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomeridKindid(customerid, kindid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = customerid+"-"+kindid;
            Integer id = varsByCustomeridKindid.get(vkey);
            if(id == null) return null;
            Boughtkinds result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getCustomerid() != customerid){
                varsByCustomeridKindid.remove(vkey);
                return null;
            }
            if(result.getKindid() != kindid){
                varsByCustomeridKindid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Boughtkinds update(Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = DAO();
        return update(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds update(BoughtkindsDAO DAO, Boughtkinds boughtkinds) {
        return update(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds update(Boughtkinds boughtkinds, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return update(DAO, boughtkinds, TABLENAME2);
    }

    public static Boughtkinds update(final BoughtkindsDAO DAO, final Boughtkinds boughtkinds,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(boughtkinds, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(boughtkinds, TABLENAME2);
            if(n == -1) 
                return boughtkinds;
            else if(n <= 0) 
                return null;
        }
        return boughtkinds;
    }

    public static Boughtkinds asyncUpdate(Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = DAO();
        return asyncUpdate(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds asyncUpdate(BoughtkindsDAO DAO, Boughtkinds boughtkinds) {
        return asyncUpdate(DAO, boughtkinds, DAO.TABLENAME);
    }

    public static Boughtkinds asyncUpdate(Boughtkinds boughtkinds, String TABLENAME2) {
        BoughtkindsDAO DAO = DAO();
        return asyncUpdate(DAO, boughtkinds, TABLENAME2);
    }

    public static Boughtkinds asyncUpdate(final BoughtkindsDAO DAO, final Boughtkinds boughtkinds,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(boughtkinds, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(boughtkinds, TABLENAME2);
        }
        return boughtkinds;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        BoughtkindsDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(BoughtkindsDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final BoughtkindsDAO DAO, final String TABLENAME2) {
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

        List<Boughtkinds> boughtkindss = getAll();
        for (Boughtkinds boughtkinds : boughtkindss) {
            int n = DAO.insert2(boughtkinds, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
