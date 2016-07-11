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

import static com.learnhall.db.bean.Aduser.Col;

//learnhall3_design - aduser
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AduserInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AduserInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AduserInternal(){}

    public static AduserDAO DAO(){
        return AduserEntity.AduserDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Aduser[MAX];
    }
    private static Aduser[] FIXED = new Aduser[MAX];
    public static final Map<Integer, Aduser> vars = newSortedMap();
    public static final Map<String, Integer> varsByUname = newSortedMap();
    public static final Map<Integer, Integer> varsByAccountid = newSortedMap();

    private static final List<Aduser> fixedCache = newList();

    public static void put(Aduser aduser, boolean force){
        if(aduser == null || aduser.uid <= 0) return ;

        int uid = aduser.uid;
        if (cacheModel == STATIC_CACHE) {
            if (uid > 0 && uid <= FIXED.length) {
                if (FIXED[uid - 1] == null || !FIXED[uid - 1].equals(aduser))
                	FIXED[uid - 1] = aduser;
                if (!fixedCache.contains(aduser))
                	fixedCache.add(aduser);
            }
        } else {
            vars.put(uid, aduser);
        }

        { // 单-唯一索引 remove old index uname
          Object ov = aduser.oldVal(Col.uname);
          if(ov != null)
              varsByUname.remove(ov);
          if(ov != null || force){ // put new index
            String uname = aduser.getUname();
            varsByUname.put(uname, uid);
          }
        }

        { // 单-唯一索引 remove old index accountid
          Object ov = aduser.oldVal(Col.accountid);
          if(ov != null)
              varsByAccountid.remove(ov);
          if(ov != null || force){ // put new index
            int accountid = aduser.getAccountid();
            varsByAccountid.put(accountid, uid);
          }
        }

        // LASTID = uid > LASTID ? uid : LASTID;
        if (uid > LASTID.get()) LASTID.set(uid);
    }

    public static void clear(){
        varsByUname.clear();
        varsByAccountid.clear();
        FIXED = new Aduser[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AduserDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AduserDAO DAO, String TABLENAME2){
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

    public static void relocate(AduserDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AduserDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AduserDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AduserDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AduserDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AduserDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AduserDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AduserDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AduserDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AduserDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AduserDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AduserDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AduserDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AduserDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AduserDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Aduser[maxId(DAO)];

        List<Aduser> adusers = DAO.selectAll();
        for (Aduser aduser : adusers) {
            aduser.reset();
            put(aduser, true);
        }
    }

    public static Map toMap(Aduser aduser){
        return aduser.toMap();
    }

    public static List<Map> toMap(List<Aduser> adusers){
        List<Map> ret = new Vector<Map>();
        for (Aduser aduser : adusers){
            Map e = aduser.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Aduser> sortZh(List<Aduser> adusers, final String key) {
        Collections.sort(adusers, new Comparator<Aduser>() {
            public int compare(Aduser o1, Aduser o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return adusers;
    }

    public static List<Aduser> sort(List<Aduser> adusers, final String key) {
        Collections.sort(adusers, new Comparator<Aduser>() {
            public int compare(Aduser o1, Aduser o2) {
                return o1.compareTo(o2, key);
            }
        });
        return adusers;
    }

    public static List<Aduser> sort(List<Aduser> adusers){
        Collections.sort(adusers, new Comparator<Aduser>(){
            public int compare(Aduser o1, Aduser o2) {
                Object v1 = o1.uid;
                Object v2 = o2.uid;
                return compareTo(v1, v2);
            }
        });
        return adusers;
    }

    public static List<Aduser> sortReverse(List<Aduser> adusers){
        Collections.sort(adusers, new Comparator<Aduser>(){
            public int compare(Aduser o1, Aduser o2) {
                Object v1 = o1.uid;
                Object v2 = o2.uid;
                return 0 - compareTo(v1, v2);
            }
        });
        return adusers;
    }

    public static List<Aduser> sortUname(List<Aduser> adusers){
        Collections.sort(adusers, new Comparator<Aduser>(){
            public int compare(Aduser o1, Aduser o2) {
                Object v1 = o1.getUname();
                Object v2 = o2.getUname();
                return compareTo(v1, v2);
            }
        });
        return adusers;
    }

    public static List<Aduser> sortUnameRo(List<Aduser> adusers){
        Collections.sort(adusers, new Comparator<Aduser>(){
            public int compare(Aduser o1, Aduser o2) {
                Object v1 = o1.getUname();
                Object v2 = o2.getUname();
                return 0 - compareTo(v1, v2);
            };
        });
        return adusers;
    }

    public static List<Aduser> sortAccountid(List<Aduser> adusers){
        Collections.sort(adusers, new Comparator<Aduser>(){
            public int compare(Aduser o1, Aduser o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return compareTo(v1, v2);
            }
        });
        return adusers;
    }

    public static List<Aduser> sortAccountidRo(List<Aduser> adusers){
        Collections.sort(adusers, new Comparator<Aduser>(){
            public int compare(Aduser o1, Aduser o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return 0 - compareTo(v1, v2);
            };
        });
        return adusers;
    }

    public static Aduser insert(Aduser aduser) {
        AduserDAO DAO = DAO();
        return insert(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser insert(AduserDAO DAO, Aduser aduser) {
        return insert(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser insert(Aduser aduser, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return insert(DAO, aduser, TABLENAME2);
    }

    public static Aduser insert(AduserDAO DAO, Aduser aduser, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(aduser, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        aduser.uid = n;
        if(cacheModel != NO_CACHE) put(aduser, true);

        return aduser;
    }

    public static Aduser asyncInsert(Aduser aduser) {
        AduserDAO DAO = DAO();
        return asyncInsert(DAO, aduser, DAO.TABLENAME);
    }
    public static Aduser asyncInsert(AduserDAO DAO, Aduser aduser) {
        return asyncInsert(DAO, aduser, DAO.TABLENAME);
    }
    public static Aduser asyncInsert(Aduser aduser, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return asyncInsert(DAO, aduser, TABLENAME2);
    }
    public static Aduser asyncInsert(AduserDAO DAO, Aduser aduser, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(aduser, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        aduser.uid = n;
        if(cacheModel != NO_CACHE) put(aduser, true);
        return aduser;
    }
    public static Aduser insert2(Aduser aduser) {
        AduserDAO DAO = DAO();
        return insert2(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser insert2(AduserDAO DAO, Aduser aduser) {
        return insert2(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser insert2(Aduser aduser, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return insert2(DAO, aduser, TABLENAME2);
    }

    public static Aduser insert2(AduserDAO DAO, Aduser aduser, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(aduser, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        aduser.uid = n;
        if(cacheModel != NO_CACHE) put(aduser, true);

        return aduser;
    }

    public static Aduser asyncInsert2(Aduser aduser) {
        AduserDAO DAO = DAO();
        return asyncInsert2(DAO, aduser, DAO.TABLENAME);
    }
    public static Aduser asyncInsert2(AduserDAO DAO, Aduser aduser) {
        return asyncInsert2(DAO, aduser, DAO.TABLENAME);
    }
    public static Aduser asyncInsert2(Aduser aduser, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return asyncInsert2(DAO, aduser, TABLENAME2);
    }
    public static Aduser asyncInsert2(AduserDAO DAO, Aduser aduser, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        aduser.uid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(aduser, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(aduser, true);
        return aduser;
    }
    public static int[] insert(List<Aduser> adusers) {
        AduserDAO DAO = DAO();
        return insert(DAO, adusers, DAO.TABLENAME);
    }

    public static int[] insert(AduserDAO DAO, List<Aduser> adusers) {
        return insert(DAO, adusers, DAO.TABLENAME);
    }

    public static int[] insert(List<Aduser> adusers, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return insert(DAO, adusers, TABLENAME2);
    }

    public static int[] insert(AduserDAO DAO, List<Aduser> adusers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(adusers, TABLENAME2);
            int n = 0;
            for(Aduser aduser : adusers){
                aduser.uid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[adusers.size()];
        int n = 0;
        for(Aduser aduser : adusers){
            aduser = insert(DAO, aduser, TABLENAME2);
            ret[n++] = (aduser == null) ? 0 : (int)aduser.uid;
        }
        return ret;
    }

    public static int delete(Aduser aduser) {
        int uid = aduser.uid;
        AduserDAO DAO = DAO();
        return delete(DAO, uid, DAO.TABLENAME);
    }

    public static int delete(int uid) {
        AduserDAO DAO = DAO();
        return delete(DAO, uid, DAO.TABLENAME);
    }

    public static int delete(AduserDAO DAO, int uid) {
        return delete(DAO, uid, DAO.TABLENAME);
    }

    public static int delete(int uid, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return delete(DAO, uid, TABLENAME2);
    }

    public static int delete(AduserDAO DAO, int uid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(uid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(uid);
        }
        return n;
    }

    public static void asyncDelete(Aduser aduser) {
        int uid = aduser.uid;
        AduserDAO DAO = DAO();
        asyncDelete(DAO, uid, DAO.TABLENAME);
    }

    public static void asyncDelete(int uid) {
        AduserDAO DAO = DAO();
        asyncDelete(DAO, uid, DAO.TABLENAME);
    }

    public static void asyncDelete(AduserDAO DAO, int uid) {
        asyncDelete(DAO, uid, DAO.TABLENAME);
    }

    public static void asyncDelete(int uid, String TABLENAME2) {
        AduserDAO DAO = DAO();
        asyncDelete(DAO, uid, TABLENAME2);
    }

    public static void asyncDelete(AduserDAO DAO, int uid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(uid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(uid);
        }
    }

    public static int[] delete(int[] uids) {
        AduserDAO DAO = DAO();
        return delete(DAO, uids, DAO.TABLENAME);
    }

    public static int[] delete(AduserDAO DAO, int[] uids) {
        return delete(DAO, uids, DAO.TABLENAME);
    }

    public static int[] delete(int[] uids,String TABLENAME2) {
        AduserDAO DAO = DAO();
        return delete(DAO, uids, TABLENAME2);
    }

    public static int[] delete(AduserDAO DAO, int[] uids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(uids, TABLENAME2);
        }
        int[] ret = new int[uids.length];
        int n = 0;
        for(int uid : uids){
            ret[n++] = delete(DAO, uid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        AduserDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AduserDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AduserDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer uid : keys){
                deleteFromMemory(uid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Aduser> beans) {
        AduserDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Aduser> beans, AduserDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Aduser> beans, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Aduser> beans, final AduserDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Aduser aduser : beans){
                int uid = aduser.uid;
                deleteFromMemory(uid);
            }
        }
        return result;
    }

    public static List<Aduser> getAll() {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getAll(AduserDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getAll(String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Aduser> getAll(final AduserDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Aduser> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Aduser> getNoSortAll() {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getNoSortAll(AduserDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getNoSortAll(String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Aduser> getNoSortAll(final AduserDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Aduser> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Aduser> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Aduser aduser = FIXED[i];
                if (aduser != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Aduser> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Aduser> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AduserDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AduserDAO DAO, final String TABLENAME2) {
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
        AduserDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AduserDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AduserDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AduserDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Aduser> getIn(List<Integer> keys) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getIn(List<Integer> keys, AduserDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getIn(List<Integer> keys, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Aduser> getIn(final List<Integer> keys, final AduserDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Aduser> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Aduser> getNoSortIn(List<Integer> keys) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getNoSortIn(List<Integer> keys, AduserDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Aduser> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Aduser> getNoSortIn(final List<Integer> keys, final AduserDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Aduser> result = newList();
            for (int key : keys) {
                Aduser aduser = getByKeyFromMemory(key);
                if( aduser == null ) continue;
                result.add(aduser);
            }
            return result;
        }
    }

    public static List<Aduser> getLast(int num) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Aduser> getLast(AduserDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Aduser> getLast(int num, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Aduser> getLast(final AduserDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Aduser> result = newList();
            List<Aduser> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Aduser last() {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Aduser last(AduserDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Aduser last(String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Aduser last(final AduserDAO DAO, final String TABLENAME2) {
        Aduser result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AduserDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AduserDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AduserDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AduserDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Aduser> getGtKey(int uid) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, uid, DAO.TABLENAME);
    }

    public static List<Aduser> getGtKey(AduserDAO DAO, int uid) {
        return getGtKey(DAO, uid, DAO.TABLENAME);
    }

    public static List<Aduser> getGtKey(int uid, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, uid, TABLENAME2);
    }

    public static List<Aduser> getGtKey(final AduserDAO DAO, final int uid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(uid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Aduser> result = newList();
            List<Aduser> adusers = getAll();
            for (Aduser aduser : adusers) {
                if(aduser.uid <= uid) continue;
                result.add(aduser);
            }
            return result;
        }
    }

    public static Aduser getByKey(int uid) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, uid, DAO.TABLENAME);
    }

    public static Future<Aduser> asyncGetByKey(final int uid) {
        Future<Aduser> f = Async.exec(new Callable<Aduser>() {
            public Aduser call() throws Exception {
                return getByKey(uid);
            }
        });
        return f;
    }

    public static Aduser getByKey(AduserDAO DAO, int uid) {
        return getByKey(DAO, uid, DAO.TABLENAME);
    }

    public static Aduser getByKey(int uid, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, uid, TABLENAME2);
    }

    public static Aduser getByKey(final AduserDAO DAO, final int uid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(uid, TABLENAME2);
        }
        return getByKeyFromMemory(uid);
    }

    public static Aduser getByKeyFromMemory(final int uid) {
        if (cacheModel == STATIC_CACHE) {
            if (uid < 1 || FIXED == null || FIXED.length < uid) return null;
            return FIXED[uid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(uid);
        }
        return null;
    }

    public static Aduser deleteFromMemory(final int uid) {
        Aduser aduser;
        if (cacheModel == STATIC_CACHE) {
            if (uid < 1 || FIXED == null || FIXED.length < uid || FIXED[uid-1]==null) return null;
            aduser = FIXED[uid - 1];
            FIXED[uid - 1] = null;
            fixedCache.remove(aduser);
        } else {
            aduser = vars.remove(uid);
        }
        if(aduser == null) return null;

        String uname = aduser.getUname();
        varsByUname.remove(uname);

        int accountid = aduser.getAccountid();
        varsByAccountid.remove(accountid);

        return aduser;
    }

    public static List<Aduser> getByPage(int page, int size) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Aduser> getByPage(AduserDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Aduser> getByPage(int page, int size, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Aduser> getByPage(final AduserDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Aduser> result = newList();
            List<Aduser> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AduserDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AduserDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Aduser getByUname(String uname) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUname(DAO, uname, DAO.TABLENAME);
    }

    public static Aduser getByUname(AduserDAO DAO, String uname) {
        return getByUname(DAO, uname, DAO.TABLENAME);
    }

    public static Aduser getByUname(String uname, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUname(DAO, uname, TABLENAME2);
    }

    public static Aduser getByUname(final AduserDAO DAO, final String uname,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUname(uname, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer uid = varsByUname.get(uname);
            if(uid == null) return null;
            Aduser result = getByKey(DAO, uid, TABLENAME2);
            if(result == null) return null;
            if(!result.getUname().equals(uname)){
                varsByUname.remove(uname);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUname(String uname) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUname(DAO, uname, DAO.TABLENAME);
    }

    public static int countLikeUname(AduserDAO DAO, String uname) {
        return countLikeUname(DAO, uname, DAO.TABLENAME);
    }

    public static int countLikeUname(String uname, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUname(DAO, uname, TABLENAME2);
    }

    public static int countLikeUname(final AduserDAO DAO, final String uname,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUname(uname, TABLENAME2);
        }
        List<Aduser> adusers = getLikeUname(DAO, uname, TABLENAME2);
        return adusers.size();
    }

    public static List<Aduser> getLikeUname(String uname) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUname(DAO, uname, DAO.TABLENAME);
    }

    public static List<Aduser> getLikeUname(AduserDAO DAO, String uname) {
        return getLikeUname(DAO, uname, DAO.TABLENAME);
    }

    public static List<Aduser> getLikeUname(String uname, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUname(DAO, uname, TABLENAME2);
    }

    public static List<Aduser> getLikeUname(final AduserDAO DAO, final String uname,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUname(uname, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Aduser> result = newList();
            List<Aduser> adusers = getAll(DAO, TABLENAME2);
            for(Aduser e : adusers){
                String _var = e.getUname();
                if(_var.indexOf(uname) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Aduser getByAccountid(Integer accountid) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Aduser getByAccountid(AduserDAO DAO, Integer accountid) {
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Aduser getByAccountid(Integer accountid, String TABLENAME2) {
        AduserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, TABLENAME2);
    }

    public static Aduser getByAccountid(final AduserDAO DAO, final int accountid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAccountid(accountid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer uid = varsByAccountid.get(accountid);
            if(uid == null) return null;
            Aduser result = getByKey(DAO, uid, TABLENAME2);
            if(result == null) return null;
            if(result.getAccountid() != accountid){
                varsByAccountid.remove(accountid);
                return null;
            }
            return result;
        }
    }

    public static Aduser update(Aduser aduser) {
        AduserDAO DAO = DAO();
        return update(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser update(AduserDAO DAO, Aduser aduser) {
        return update(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser update(Aduser aduser, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return update(DAO, aduser, TABLENAME2);
    }

    public static Aduser update(final AduserDAO DAO, final Aduser aduser,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(aduser, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(aduser, TABLENAME2);
            if(n == -1) 
                return aduser;
            else if(n <= 0) 
                return null;
        }
        return aduser;
    }

    public static Aduser asyncUpdate(Aduser aduser) {
        AduserDAO DAO = DAO();
        return asyncUpdate(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser asyncUpdate(AduserDAO DAO, Aduser aduser) {
        return asyncUpdate(DAO, aduser, DAO.TABLENAME);
    }

    public static Aduser asyncUpdate(Aduser aduser, String TABLENAME2) {
        AduserDAO DAO = DAO();
        return asyncUpdate(DAO, aduser, TABLENAME2);
    }

    public static Aduser asyncUpdate(final AduserDAO DAO, final Aduser aduser,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(aduser, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(aduser, TABLENAME2);
        }
        return aduser;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AduserDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AduserDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AduserDAO DAO, final String TABLENAME2) {
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

        List<Aduser> adusers = getAll();
        for (Aduser aduser : adusers) {
            int n = DAO.insert2(aduser, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
