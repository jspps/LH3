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

import static com.learnhall.db.bean.Adprivilege.Col;

//learnhall3_design - adprivilege
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AdprivilegeInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AdprivilegeInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AdprivilegeInternal(){}

    public static AdprivilegeDAO DAO(){
        return AdprivilegeEntity.AdprivilegeDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Adprivilege[MAX];
    }
    private static Adprivilege[] FIXED = new Adprivilege[MAX];
    public static final Map<Integer, Adprivilege> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByParentid = newSortedMap();

    private static final List<Adprivilege> fixedCache = newList();

    public static void put(Adprivilege adprivilege, boolean force){
        if(adprivilege == null || adprivilege.prid <= 0) return ;

        int prid = adprivilege.prid;
        if (cacheModel == STATIC_CACHE) {
            if (prid > 0 && prid <= FIXED.length) {
                if (FIXED[prid - 1] == null || !FIXED[prid - 1].equals(adprivilege))
                	FIXED[prid - 1] = adprivilege;
                if (!fixedCache.contains(adprivilege))
                	fixedCache.add(adprivilege);
            }
        } else {
            vars.put(prid, adprivilege);
        }

        { // 单-非唯一索引 remove old index parentid
          Object ov = adprivilege.oldVal(Col.parentid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByParentid.get(_val);
              if(m1 != null) {
                  m1.remove(prid);
                  if(m1.isEmpty())
                      varsByParentid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int parentid = adprivilege.getParentid();
            Set m1 = varsByParentid.get(parentid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByParentid.put(parentid, m1);
            }
            m1.add(prid);
          }
        }

        // LASTID = prid > LASTID ? prid : LASTID;
        if (prid > LASTID.get()) LASTID.set(prid);
    }

    public static void clear(){
        varsByParentid.clear();
        FIXED = new Adprivilege[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AdprivilegeDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AdprivilegeDAO DAO, String TABLENAME2){
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

    public static void relocate(AdprivilegeDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AdprivilegeDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AdprivilegeDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AdprivilegeDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AdprivilegeDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AdprivilegeDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AdprivilegeDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AdprivilegeDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AdprivilegeDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AdprivilegeDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AdprivilegeDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AdprivilegeDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AdprivilegeDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Adprivilege[maxId(DAO)];

        List<Adprivilege> adprivileges = DAO.selectAll();
        for (Adprivilege adprivilege : adprivileges) {
            adprivilege.reset();
            put(adprivilege, true);
        }
    }

    public static Map toMap(Adprivilege adprivilege){
        return adprivilege.toMap();
    }

    public static List<Map> toMap(List<Adprivilege> adprivileges){
        List<Map> ret = new Vector<Map>();
        for (Adprivilege adprivilege : adprivileges){
            Map e = adprivilege.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Adprivilege> sortZh(List<Adprivilege> adprivileges, final String key) {
        Collections.sort(adprivileges, new Comparator<Adprivilege>() {
            public int compare(Adprivilege o1, Adprivilege o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return adprivileges;
    }

    public static List<Adprivilege> sort(List<Adprivilege> adprivileges, final String key) {
        Collections.sort(adprivileges, new Comparator<Adprivilege>() {
            public int compare(Adprivilege o1, Adprivilege o2) {
                return o1.compareTo(o2, key);
            }
        });
        return adprivileges;
    }

    public static List<Adprivilege> sort(List<Adprivilege> adprivileges){
        Collections.sort(adprivileges, new Comparator<Adprivilege>(){
            public int compare(Adprivilege o1, Adprivilege o2) {
                Object v1 = o1.prid;
                Object v2 = o2.prid;
                return compareTo(v1, v2);
            }
        });
        return adprivileges;
    }

    public static List<Adprivilege> sortReverse(List<Adprivilege> adprivileges){
        Collections.sort(adprivileges, new Comparator<Adprivilege>(){
            public int compare(Adprivilege o1, Adprivilege o2) {
                Object v1 = o1.prid;
                Object v2 = o2.prid;
                return 0 - compareTo(v1, v2);
            }
        });
        return adprivileges;
    }

    public static List<Adprivilege> sortParentid(List<Adprivilege> adprivileges){
        Collections.sort(adprivileges, new Comparator<Adprivilege>(){
            public int compare(Adprivilege o1, Adprivilege o2) {
                Object v1 = o1.getParentid();
                Object v2 = o2.getParentid();
                return compareTo(v1, v2);
            }
        });
        return adprivileges;
    }

    public static List<Adprivilege> sortParentidRo(List<Adprivilege> adprivileges){
        Collections.sort(adprivileges, new Comparator<Adprivilege>(){
            public int compare(Adprivilege o1, Adprivilege o2) {
                Object v1 = o1.getParentid();
                Object v2 = o2.getParentid();
                return 0 - compareTo(v1, v2);
            };
        });
        return adprivileges;
    }

    public static Adprivilege insert(Adprivilege adprivilege) {
        AdprivilegeDAO DAO = DAO();
        return insert(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege insert(AdprivilegeDAO DAO, Adprivilege adprivilege) {
        return insert(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege insert(Adprivilege adprivilege, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return insert(DAO, adprivilege, TABLENAME2);
    }

    public static Adprivilege insert(AdprivilegeDAO DAO, Adprivilege adprivilege, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(adprivilege, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        adprivilege.prid = n;
        if(cacheModel != NO_CACHE) put(adprivilege, true);

        return adprivilege;
    }

    public static Adprivilege asyncInsert(Adprivilege adprivilege) {
        AdprivilegeDAO DAO = DAO();
        return asyncInsert(DAO, adprivilege, DAO.TABLENAME);
    }
    public static Adprivilege asyncInsert(AdprivilegeDAO DAO, Adprivilege adprivilege) {
        return asyncInsert(DAO, adprivilege, DAO.TABLENAME);
    }
    public static Adprivilege asyncInsert(Adprivilege adprivilege, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return asyncInsert(DAO, adprivilege, TABLENAME2);
    }
    public static Adprivilege asyncInsert(AdprivilegeDAO DAO, Adprivilege adprivilege, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(adprivilege, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        adprivilege.prid = n;
        if(cacheModel != NO_CACHE) put(adprivilege, true);
        return adprivilege;
    }
    public static Adprivilege insert2(Adprivilege adprivilege) {
        AdprivilegeDAO DAO = DAO();
        return insert2(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege insert2(AdprivilegeDAO DAO, Adprivilege adprivilege) {
        return insert2(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege insert2(Adprivilege adprivilege, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return insert2(DAO, adprivilege, TABLENAME2);
    }

    public static Adprivilege insert2(AdprivilegeDAO DAO, Adprivilege adprivilege, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(adprivilege, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        adprivilege.prid = n;
        if(cacheModel != NO_CACHE) put(adprivilege, true);

        return adprivilege;
    }

    public static Adprivilege asyncInsert2(Adprivilege adprivilege) {
        AdprivilegeDAO DAO = DAO();
        return asyncInsert2(DAO, adprivilege, DAO.TABLENAME);
    }
    public static Adprivilege asyncInsert2(AdprivilegeDAO DAO, Adprivilege adprivilege) {
        return asyncInsert2(DAO, adprivilege, DAO.TABLENAME);
    }
    public static Adprivilege asyncInsert2(Adprivilege adprivilege, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return asyncInsert2(DAO, adprivilege, TABLENAME2);
    }
    public static Adprivilege asyncInsert2(AdprivilegeDAO DAO, Adprivilege adprivilege, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        adprivilege.prid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(adprivilege, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(adprivilege, true);
        return adprivilege;
    }
    public static int[] insert(List<Adprivilege> adprivileges) {
        AdprivilegeDAO DAO = DAO();
        return insert(DAO, adprivileges, DAO.TABLENAME);
    }

    public static int[] insert(AdprivilegeDAO DAO, List<Adprivilege> adprivileges) {
        return insert(DAO, adprivileges, DAO.TABLENAME);
    }

    public static int[] insert(List<Adprivilege> adprivileges, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return insert(DAO, adprivileges, TABLENAME2);
    }

    public static int[] insert(AdprivilegeDAO DAO, List<Adprivilege> adprivileges, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(adprivileges, TABLENAME2);
            int n = 0;
            for(Adprivilege adprivilege : adprivileges){
                adprivilege.prid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[adprivileges.size()];
        int n = 0;
        for(Adprivilege adprivilege : adprivileges){
            adprivilege = insert(DAO, adprivilege, TABLENAME2);
            ret[n++] = (adprivilege == null) ? 0 : (int)adprivilege.prid;
        }
        return ret;
    }

    public static int delete(Adprivilege adprivilege) {
        int prid = adprivilege.prid;
        AdprivilegeDAO DAO = DAO();
        return delete(DAO, prid, DAO.TABLENAME);
    }

    public static int delete(int prid) {
        AdprivilegeDAO DAO = DAO();
        return delete(DAO, prid, DAO.TABLENAME);
    }

    public static int delete(AdprivilegeDAO DAO, int prid) {
        return delete(DAO, prid, DAO.TABLENAME);
    }

    public static int delete(int prid, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return delete(DAO, prid, TABLENAME2);
    }

    public static int delete(AdprivilegeDAO DAO, int prid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(prid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(prid);
        }
        return n;
    }

    public static void asyncDelete(Adprivilege adprivilege) {
        int prid = adprivilege.prid;
        AdprivilegeDAO DAO = DAO();
        asyncDelete(DAO, prid, DAO.TABLENAME);
    }

    public static void asyncDelete(int prid) {
        AdprivilegeDAO DAO = DAO();
        asyncDelete(DAO, prid, DAO.TABLENAME);
    }

    public static void asyncDelete(AdprivilegeDAO DAO, int prid) {
        asyncDelete(DAO, prid, DAO.TABLENAME);
    }

    public static void asyncDelete(int prid, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        asyncDelete(DAO, prid, TABLENAME2);
    }

    public static void asyncDelete(AdprivilegeDAO DAO, int prid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(prid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(prid);
        }
    }

    public static int[] delete(int[] prids) {
        AdprivilegeDAO DAO = DAO();
        return delete(DAO, prids, DAO.TABLENAME);
    }

    public static int[] delete(AdprivilegeDAO DAO, int[] prids) {
        return delete(DAO, prids, DAO.TABLENAME);
    }

    public static int[] delete(int[] prids,String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return delete(DAO, prids, TABLENAME2);
    }

    public static int[] delete(AdprivilegeDAO DAO, int[] prids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(prids, TABLENAME2);
        }
        int[] ret = new int[prids.length];
        int n = 0;
        for(int prid : prids){
            ret[n++] = delete(DAO, prid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        AdprivilegeDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AdprivilegeDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AdprivilegeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer prid : keys){
                deleteFromMemory(prid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Adprivilege> beans) {
        AdprivilegeDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Adprivilege> beans, AdprivilegeDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Adprivilege> beans, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Adprivilege> beans, final AdprivilegeDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Adprivilege adprivilege : beans){
                int prid = adprivilege.prid;
                deleteFromMemory(prid);
            }
        }
        return result;
    }

    public static List<Adprivilege> getAll() {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getAll(AdprivilegeDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getAll(String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Adprivilege> getAll(final AdprivilegeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adprivilege> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Adprivilege> getNoSortAll() {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getNoSortAll(AdprivilegeDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getNoSortAll(String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Adprivilege> getNoSortAll(final AdprivilegeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Adprivilege> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adprivilege> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Adprivilege adprivilege = FIXED[i];
                if (adprivilege != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Adprivilege> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Adprivilege> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AdprivilegeDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AdprivilegeDAO DAO, final String TABLENAME2) {
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
        AdprivilegeDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AdprivilegeDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AdprivilegeDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Adprivilege> getIn(List<Integer> keys) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getIn(List<Integer> keys, AdprivilegeDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getIn(List<Integer> keys, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Adprivilege> getIn(final List<Integer> keys, final AdprivilegeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adprivilege> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Adprivilege> getNoSortIn(List<Integer> keys) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getNoSortIn(List<Integer> keys, AdprivilegeDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Adprivilege> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Adprivilege> getNoSortIn(final List<Integer> keys, final AdprivilegeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Adprivilege> result = newList();
            for (int key : keys) {
                Adprivilege adprivilege = getByKeyFromMemory(key);
                if( adprivilege == null ) continue;
                result.add(adprivilege);
            }
            return result;
        }
    }

    public static List<Adprivilege> getLast(int num) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Adprivilege> getLast(AdprivilegeDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Adprivilege> getLast(int num, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Adprivilege> getLast(final AdprivilegeDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Adprivilege> result = newList();
            List<Adprivilege> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Adprivilege last() {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Adprivilege last(AdprivilegeDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Adprivilege last(String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Adprivilege last(final AdprivilegeDAO DAO, final String TABLENAME2) {
        Adprivilege result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AdprivilegeDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AdprivilegeDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AdprivilegeDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Adprivilege> getGtKey(int prid) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, prid, DAO.TABLENAME);
    }

    public static List<Adprivilege> getGtKey(AdprivilegeDAO DAO, int prid) {
        return getGtKey(DAO, prid, DAO.TABLENAME);
    }

    public static List<Adprivilege> getGtKey(int prid, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, prid, TABLENAME2);
    }

    public static List<Adprivilege> getGtKey(final AdprivilegeDAO DAO, final int prid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(prid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adprivilege> result = newList();
            List<Adprivilege> adprivileges = getAll();
            for (Adprivilege adprivilege : adprivileges) {
                if(adprivilege.prid <= prid) continue;
                result.add(adprivilege);
            }
            return result;
        }
    }

    public static Adprivilege getByKey(int prid) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, prid, DAO.TABLENAME);
    }

    public static Future<Adprivilege> asyncGetByKey(final int prid) {
        Future<Adprivilege> f = Async.exec(new Callable<Adprivilege>() {
            public Adprivilege call() throws Exception {
                return getByKey(prid);
            }
        });
        return f;
    }

    public static Adprivilege getByKey(AdprivilegeDAO DAO, int prid) {
        return getByKey(DAO, prid, DAO.TABLENAME);
    }

    public static Adprivilege getByKey(int prid, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, prid, TABLENAME2);
    }

    public static Adprivilege getByKey(final AdprivilegeDAO DAO, final int prid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(prid, TABLENAME2);
        }
        return getByKeyFromMemory(prid);
    }

    public static Adprivilege getByKeyFromMemory(final int prid) {
        if (cacheModel == STATIC_CACHE) {
            if (prid < 1 || FIXED == null || FIXED.length < prid) return null;
            return FIXED[prid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(prid);
        }
        return null;
    }

    public static Adprivilege deleteFromMemory(final int prid) {
        Adprivilege adprivilege;
        if (cacheModel == STATIC_CACHE) {
            if (prid < 1 || FIXED == null || FIXED.length < prid || FIXED[prid-1]==null) return null;
            adprivilege = FIXED[prid - 1];
            FIXED[prid - 1] = null;
            fixedCache.remove(adprivilege);
        } else {
            adprivilege = vars.remove(prid);
        }
        if(adprivilege == null) return null;

        int parentid = adprivilege.getParentid();
        Set m1 = varsByParentid.get(parentid);
        if(m1 != null) {
            m1.remove(prid);
            if(m1.isEmpty())
                varsByParentid.remove(parentid);
        }

        return adprivilege;
    }

    public static List<Adprivilege> getByPage(int page, int size) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Adprivilege> getByPage(AdprivilegeDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Adprivilege> getByPage(int page, int size, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Adprivilege> getByPage(final AdprivilegeDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Adprivilege> result = newList();
            List<Adprivilege> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AdprivilegeDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AdprivilegeDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByParentid(Integer parentid) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static int countByParentid(AdprivilegeDAO DAO, Integer parentid) {
        return countByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static int countByParentid(Integer parentid, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByParentid(DAO, parentid, TABLENAME2);
    }

    public static int countByParentid(final AdprivilegeDAO DAO, final Integer parentid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByParentid(parentid, TABLENAME2);
        }
        List<Adprivilege> adprivileges = getByParentid(DAO, parentid, TABLENAME2);
        return adprivileges.size();
    }

    public static List<Adprivilege> getByParentid(Integer parentid) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static List<Adprivilege> getByParentid(AdprivilegeDAO DAO, Integer parentid) {
        return getByParentid(DAO, parentid, DAO.TABLENAME);
    }

    public static List<Adprivilege> getByParentid(Integer parentid, String TABLENAME2) {
        AdprivilegeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByParentid(DAO, parentid, TABLENAME2);
    }

    public static List<Adprivilege> getByParentid(final AdprivilegeDAO DAO, final Integer parentid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByParentid(parentid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Adprivilege> result = newList();
            Set<Integer> m1 = varsByParentid.get(parentid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Adprivilege e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _parentid = e.getParentid();
                if(_parentid != parentid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Adprivilege update(Adprivilege adprivilege) {
        AdprivilegeDAO DAO = DAO();
        return update(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege update(AdprivilegeDAO DAO, Adprivilege adprivilege) {
        return update(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege update(Adprivilege adprivilege, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return update(DAO, adprivilege, TABLENAME2);
    }

    public static Adprivilege update(final AdprivilegeDAO DAO, final Adprivilege adprivilege,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(adprivilege, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(adprivilege, TABLENAME2);
            if(n == -1) 
                return adprivilege;
            else if(n <= 0) 
                return null;
        }
        return adprivilege;
    }

    public static Adprivilege asyncUpdate(Adprivilege adprivilege) {
        AdprivilegeDAO DAO = DAO();
        return asyncUpdate(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege asyncUpdate(AdprivilegeDAO DAO, Adprivilege adprivilege) {
        return asyncUpdate(DAO, adprivilege, DAO.TABLENAME);
    }

    public static Adprivilege asyncUpdate(Adprivilege adprivilege, String TABLENAME2) {
        AdprivilegeDAO DAO = DAO();
        return asyncUpdate(DAO, adprivilege, TABLENAME2);
    }

    public static Adprivilege asyncUpdate(final AdprivilegeDAO DAO, final Adprivilege adprivilege,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(adprivilege, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(adprivilege, TABLENAME2);
        }
        return adprivilege;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AdprivilegeDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AdprivilegeDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AdprivilegeDAO DAO, final String TABLENAME2) {
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

        List<Adprivilege> adprivileges = getAll();
        for (Adprivilege adprivilege : adprivileges) {
            int n = DAO.insert2(adprivilege, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
