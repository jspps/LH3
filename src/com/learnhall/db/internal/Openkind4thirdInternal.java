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

import static com.learnhall.db.bean.Openkind4third.Col;

//learnhall3_design - openkind4third
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Openkind4thirdInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Openkind4thirdInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Openkind4thirdInternal(){}

    public static Openkind4thirdDAO DAO(){
        return Openkind4thirdEntity.Openkind4thirdDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Openkind4third[MAX];
    }
    private static Openkind4third[] FIXED = new Openkind4third[MAX];
    public static final Map<Integer, Openkind4third> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByPhone = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKindid = newSortedMap();
    public static final Map<String, Integer> varsByKindidLhubidAgentid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByAgentid = newSortedMap();

    private static final List<Openkind4third> fixedCache = newList();

    public static void put(Openkind4third openkind4third, boolean force){
        if(openkind4third == null || openkind4third.id <= 0) return ;

        int id = openkind4third.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(openkind4third))
                	FIXED[id - 1] = openkind4third;
                if (!fixedCache.contains(openkind4third))
                	fixedCache.add(openkind4third);
            }
        } else {
            vars.put(id, openkind4third);
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = openkind4third.oldVal(Col.lhubid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m1 = varsByLhubid.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByLhubid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int lhubid = openkind4third.getLhubid();
            Set m1 = varsByLhubid.get(lhubid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByLhubid.put(lhubid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index phone
          Object ov = openkind4third.oldVal(Col.phone);
          if(ov != null) {
              String _val = (String) ov;
              Set m2 = varsByPhone.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByPhone.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String phone = openkind4third.getPhone();
            Set m2 = varsByPhone.get(phone);
            if(m2 == null){
                m2 = newSortedSet();
                varsByPhone.put(phone, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kindid
          Object ov = openkind4third.oldVal(Col.kindid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m3 = varsByKindid.get(_val);
              if(m3 != null) {
                  m3.remove(id);
                  if(m3.isEmpty())
                      varsByKindid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int kindid = openkind4third.getKindid();
            Set m3 = varsByKindid.get(kindid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByKindid.put(kindid, m3);
            }
            m3.add(id);
          }
        }

        { // kindid_lhubid_agentid
          boolean bChanged = openkind4third.inChanged(Col.kindid, Col.lhubid, Col.agentid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vkindid = openkind4third.oldOrNew(Col.kindid);
            Object vlhubid = openkind4third.oldOrNew(Col.lhubid);
            Object vagentid = openkind4third.oldOrNew(Col.agentid);
            String okey = com.bowlong.lang.PStr.b().a(vkindid, "-", vlhubid, "-", vagentid).e();
            varsByKindidLhubidAgentid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vkindid = openkind4third.getKindid();
              int vlhubid = openkind4third.getLhubid();
              int vagentid = openkind4third.getAgentid();
              String vkey = com.bowlong.lang.PStr.b().a(vkindid, "-", vlhubid, "-", vagentid).e();
              varsByKindidLhubidAgentid.put(vkey, id);
          }
        }

        { // 单-非唯一索引 remove old index agentid
          Object ov = openkind4third.oldVal(Col.agentid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m4 = varsByAgentid.get(_val);
              if(m4 != null) {
                  m4.remove(id);
                  if(m4.isEmpty())
                      varsByAgentid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int agentid = openkind4third.getAgentid();
            Set m4 = varsByAgentid.get(agentid);
            if(m4 == null){
                m4 = newSortedSet();
                varsByAgentid.put(agentid, m4);
            }
            m4.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLhubid.clear();
        varsByPhone.clear();
        varsByKindid.clear();
        varsByKindidLhubidAgentid.clear();
        varsByAgentid.clear();
        FIXED = new Openkind4third[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Openkind4thirdDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Openkind4thirdDAO DAO, String TABLENAME2){
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

    public static void relocate(Openkind4thirdDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Openkind4thirdDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Openkind4thirdDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Openkind4thirdDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Openkind4thirdDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Openkind4thirdDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Openkind4thirdDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Openkind4thirdDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Openkind4thirdDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Openkind4thirdDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Openkind4thirdDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Openkind4thirdDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Openkind4thirdDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Openkind4third[maxId(DAO)];

        List<Openkind4third> openkind4thirds = DAO.selectAll();
        for (Openkind4third openkind4third : openkind4thirds) {
            openkind4third.reset();
            put(openkind4third, true);
        }
    }

    public static Map toMap(Openkind4third openkind4third){
        return openkind4third.toMap();
    }

    public static List<Map> toMap(List<Openkind4third> openkind4thirds){
        List<Map> ret = new Vector<Map>();
        for (Openkind4third openkind4third : openkind4thirds){
            Map e = openkind4third.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Openkind4third> sortZh(List<Openkind4third> openkind4thirds, final String key) {
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>() {
            public int compare(Openkind4third o1, Openkind4third o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sort(List<Openkind4third> openkind4thirds, final String key) {
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>() {
            public int compare(Openkind4third o1, Openkind4third o2) {
                return o1.compareTo(o2, key);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sort(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortReverse(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortLhubid(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortLhubidRo(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortPhone(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getPhone();
                Object v2 = o2.getPhone();
                return compareTo(v1, v2);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortPhoneRo(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getPhone();
                Object v2 = o2.getPhone();
                return 0 - compareTo(v1, v2);
            };
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortKindid(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortKindidRo(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortAgentid(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getAgentid();
                Object v2 = o2.getAgentid();
                return compareTo(v1, v2);
            }
        });
        return openkind4thirds;
    }

    public static List<Openkind4third> sortAgentidRo(List<Openkind4third> openkind4thirds){
        Collections.sort(openkind4thirds, new Comparator<Openkind4third>(){
            public int compare(Openkind4third o1, Openkind4third o2) {
                Object v1 = o1.getAgentid();
                Object v2 = o2.getAgentid();
                return 0 - compareTo(v1, v2);
            };
        });
        return openkind4thirds;
    }

    public static Openkind4third insert(Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = DAO();
        return insert(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third insert(Openkind4thirdDAO DAO, Openkind4third openkind4third) {
        return insert(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third insert(Openkind4third openkind4third, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return insert(DAO, openkind4third, TABLENAME2);
    }

    public static Openkind4third insert(Openkind4thirdDAO DAO, Openkind4third openkind4third, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(openkind4third, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        openkind4third.id = n;
        if(cacheModel != NO_CACHE) put(openkind4third, true);

        return openkind4third;
    }

    public static Openkind4third asyncInsert(Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = DAO();
        return asyncInsert(DAO, openkind4third, DAO.TABLENAME);
    }
    public static Openkind4third asyncInsert(Openkind4thirdDAO DAO, Openkind4third openkind4third) {
        return asyncInsert(DAO, openkind4third, DAO.TABLENAME);
    }
    public static Openkind4third asyncInsert(Openkind4third openkind4third, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return asyncInsert(DAO, openkind4third, TABLENAME2);
    }
    public static Openkind4third asyncInsert(Openkind4thirdDAO DAO, Openkind4third openkind4third, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(openkind4third, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        openkind4third.id = n;
        if(cacheModel != NO_CACHE) put(openkind4third, true);
        return openkind4third;
    }
    public static Openkind4third insert2(Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = DAO();
        return insert2(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third insert2(Openkind4thirdDAO DAO, Openkind4third openkind4third) {
        return insert2(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third insert2(Openkind4third openkind4third, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return insert2(DAO, openkind4third, TABLENAME2);
    }

    public static Openkind4third insert2(Openkind4thirdDAO DAO, Openkind4third openkind4third, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(openkind4third, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        openkind4third.id = n;
        if(cacheModel != NO_CACHE) put(openkind4third, true);

        return openkind4third;
    }

    public static Openkind4third asyncInsert2(Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = DAO();
        return asyncInsert2(DAO, openkind4third, DAO.TABLENAME);
    }
    public static Openkind4third asyncInsert2(Openkind4thirdDAO DAO, Openkind4third openkind4third) {
        return asyncInsert2(DAO, openkind4third, DAO.TABLENAME);
    }
    public static Openkind4third asyncInsert2(Openkind4third openkind4third, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return asyncInsert2(DAO, openkind4third, TABLENAME2);
    }
    public static Openkind4third asyncInsert2(Openkind4thirdDAO DAO, Openkind4third openkind4third, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        openkind4third.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(openkind4third, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(openkind4third, true);
        return openkind4third;
    }
    public static int[] insert(List<Openkind4third> openkind4thirds) {
        Openkind4thirdDAO DAO = DAO();
        return insert(DAO, openkind4thirds, DAO.TABLENAME);
    }

    public static int[] insert(Openkind4thirdDAO DAO, List<Openkind4third> openkind4thirds) {
        return insert(DAO, openkind4thirds, DAO.TABLENAME);
    }

    public static int[] insert(List<Openkind4third> openkind4thirds, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return insert(DAO, openkind4thirds, TABLENAME2);
    }

    public static int[] insert(Openkind4thirdDAO DAO, List<Openkind4third> openkind4thirds, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(openkind4thirds, TABLENAME2);
            int n = 0;
            for(Openkind4third openkind4third : openkind4thirds){
                openkind4third.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[openkind4thirds.size()];
        int n = 0;
        for(Openkind4third openkind4third : openkind4thirds){
            openkind4third = insert(DAO, openkind4third, TABLENAME2);
            ret[n++] = (openkind4third == null) ? 0 : (int)openkind4third.id;
        }
        return ret;
    }

    public static int delete(Openkind4third openkind4third) {
        int id = openkind4third.id;
        Openkind4thirdDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Openkind4thirdDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Openkind4thirdDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Openkind4thirdDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Openkind4third openkind4third) {
        int id = openkind4third.id;
        Openkind4thirdDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Openkind4thirdDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Openkind4thirdDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Openkind4thirdDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Openkind4thirdDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Openkind4thirdDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Openkind4thirdDAO DAO, int[] ids,String TABLENAME2) {
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
        Openkind4thirdDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Openkind4thirdDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Openkind4third> beans) {
        Openkind4thirdDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Openkind4third> beans, Openkind4thirdDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Openkind4third> beans, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Openkind4third> beans, final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Openkind4third openkind4third : beans){
                int id = openkind4third.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Openkind4third> getAll() {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getAll(Openkind4thirdDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getAll(String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Openkind4third> getAll(final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4third> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Openkind4third> getNoSortAll() {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getNoSortAll(Openkind4thirdDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getNoSortAll(String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Openkind4third> getNoSortAll(final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Openkind4third> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4third> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Openkind4third openkind4third = FIXED[i];
                if (openkind4third != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Openkind4third> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Openkind4third> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Openkind4thirdDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Openkind4thirdDAO DAO, final String TABLENAME2) {
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
        Openkind4thirdDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Openkind4thirdDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Openkind4thirdDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Openkind4third> getIn(List<Integer> keys) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getIn(List<Integer> keys, Openkind4thirdDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getIn(List<Integer> keys, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Openkind4third> getIn(final List<Integer> keys, final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4third> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Openkind4third> getNoSortIn(List<Integer> keys) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getNoSortIn(List<Integer> keys, Openkind4thirdDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Openkind4third> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Openkind4third> getNoSortIn(final List<Integer> keys, final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Openkind4third> result = newList();
            for (int key : keys) {
                Openkind4third openkind4third = getByKeyFromMemory(key);
                if( openkind4third == null ) continue;
                result.add(openkind4third);
            }
            return result;
        }
    }

    public static List<Openkind4third> getLast(int num) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Openkind4third> getLast(Openkind4thirdDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Openkind4third> getLast(int num, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Openkind4third> getLast(final Openkind4thirdDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Openkind4third> result = newList();
            List<Openkind4third> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Openkind4third last() {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Openkind4third last(Openkind4thirdDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Openkind4third last(String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Openkind4third last(final Openkind4thirdDAO DAO, final String TABLENAME2) {
        Openkind4third result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Openkind4thirdDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Openkind4thirdDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Openkind4thirdDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Openkind4third> getGtKey(int id) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Openkind4third> getGtKey(Openkind4thirdDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Openkind4third> getGtKey(int id, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Openkind4third> getGtKey(final Openkind4thirdDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4third> result = newList();
            List<Openkind4third> openkind4thirds = getAll();
            for (Openkind4third openkind4third : openkind4thirds) {
                if(openkind4third.id <= id) continue;
                result.add(openkind4third);
            }
            return result;
        }
    }

    public static Openkind4third getByKey(int id) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Openkind4third> asyncGetByKey(final int id) {
        Future<Openkind4third> f = Async.exec(new Callable<Openkind4third>() {
            public Openkind4third call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Openkind4third getByKey(Openkind4thirdDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Openkind4third getByKey(int id, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Openkind4third getByKey(final Openkind4thirdDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Openkind4third getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Openkind4third deleteFromMemory(final int id) {
        Openkind4third openkind4third;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            openkind4third = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(openkind4third);
        } else {
            openkind4third = vars.remove(id);
        }
        if(openkind4third == null) return null;

        int lhubid = openkind4third.getLhubid();
        Set m1 = varsByLhubid.get(lhubid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        String phone = openkind4third.getPhone();
        Set m2 = varsByPhone.get(phone);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByPhone.remove(phone);
        }

        int kindid = openkind4third.getKindid();
        Set m3 = varsByKindid.get(kindid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByKindid.remove(kindid);
        }

        { // kindid_lhubid_agentid
            int vkindid = openkind4third.getKindid();
            int vlhubid = openkind4third.getLhubid();
            int vagentid = openkind4third.getAgentid();
            String vkey = com.bowlong.lang.PStr.b().a(vkindid, "-", vlhubid, "-", vagentid).e();
            varsByKindidLhubidAgentid.remove(vkey);
        }

        int agentid = openkind4third.getAgentid();
        Set m4 = varsByAgentid.get(agentid);
        if(m4 != null) {
            m4.remove(id);
            if(m4.isEmpty())
                varsByAgentid.remove(agentid);
        }

        return openkind4third;
    }

    public static List<Openkind4third> getByPage(int page, int size) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByPage(Openkind4thirdDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByPage(int page, int size, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Openkind4third> getByPage(final Openkind4thirdDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Openkind4third> result = newList();
            List<Openkind4third> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Openkind4thirdDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Openkind4thirdDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByLhubid(Integer lhubid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Openkind4thirdDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final Openkind4thirdDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Openkind4third> openkind4thirds = getByLhubid(DAO, lhubid, TABLENAME2);
        return openkind4thirds.size();
    }

    public static List<Openkind4third> getByLhubid(Integer lhubid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByLhubid(Openkind4thirdDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByLhubid(Integer lhubid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Openkind4third> getByLhubid(final Openkind4thirdDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Openkind4third> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Openkind4third e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _lhubid = e.getLhubid();
                if(_lhubid != lhubid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByPhone(String phone) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPhone(DAO, phone, DAO.TABLENAME);
    }

    public static int countByPhone(Openkind4thirdDAO DAO, String phone) {
        return countByPhone(DAO, phone, DAO.TABLENAME);
    }

    public static int countByPhone(String phone, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPhone(DAO, phone, TABLENAME2);
    }

    public static int countByPhone(final Openkind4thirdDAO DAO, final String phone,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPhone(phone, TABLENAME2);
        }
        List<Openkind4third> openkind4thirds = getByPhone(DAO, phone, TABLENAME2);
        return openkind4thirds.size();
    }

    public static List<Openkind4third> getByPhone(String phone) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPhone(DAO, phone, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByPhone(Openkind4thirdDAO DAO, String phone) {
        return getByPhone(DAO, phone, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByPhone(String phone, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPhone(DAO, phone, TABLENAME2);
    }

    public static List<Openkind4third> getByPhone(final Openkind4thirdDAO DAO, final String phone,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPhone(phone, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Openkind4third> result = newList();
            Set<Integer> m1 = varsByPhone.get(phone);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Openkind4third e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _phone = e.getPhone();
                if(!_phone.equals(phone)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikePhone(String phone) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static int countLikePhone(Openkind4thirdDAO DAO, String phone) {
        return countLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static int countLikePhone(String phone, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePhone(DAO, phone, TABLENAME2);
    }

    public static int countLikePhone(final Openkind4thirdDAO DAO, final String phone,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikePhone(phone, TABLENAME2);
        }
        List<Openkind4third> openkind4thirds = getLikePhone(DAO, phone, TABLENAME2);
        return openkind4thirds.size();
    }

    public static List<Openkind4third> getLikePhone(String phone) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static List<Openkind4third> getLikePhone(Openkind4thirdDAO DAO, String phone) {
        return getLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static List<Openkind4third> getLikePhone(String phone, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePhone(DAO, phone, TABLENAME2);
    }

    public static List<Openkind4third> getLikePhone(final Openkind4thirdDAO DAO, final String phone,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikePhone(phone, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Openkind4third> result = newList();
            List<Openkind4third> openkind4thirds = getAll(DAO, TABLENAME2);
            for(Openkind4third e : openkind4thirds){
                String _var = e.getPhone();
                if(_var.indexOf(phone) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByKindid(Integer kindid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Openkind4thirdDAO DAO, Integer kindid) {
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Integer kindid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, TABLENAME2);
    }

    public static int countByKindid(final Openkind4thirdDAO DAO, final Integer kindid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKindid(kindid, TABLENAME2);
        }
        List<Openkind4third> openkind4thirds = getByKindid(DAO, kindid, TABLENAME2);
        return openkind4thirds.size();
    }

    public static List<Openkind4third> getByKindid(Integer kindid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByKindid(Openkind4thirdDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByKindid(Integer kindid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static List<Openkind4third> getByKindid(final Openkind4thirdDAO DAO, final Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Openkind4third> result = newList();
            Set<Integer> m1 = varsByKindid.get(kindid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Openkind4third e = getByKey(DAO, key, TABLENAME2);
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

    public static Openkind4third getByKindidLhubidAgentid(Integer kindid, Integer lhubid, Integer agentid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindidLhubidAgentid(DAO, kindid, lhubid, agentid, DAO.TABLENAME);
    }

    public static Openkind4third getByKindidLhubidAgentid(Openkind4thirdDAO DAO, Integer kindid, Integer lhubid, Integer agentid) {
        return getByKindidLhubidAgentid(DAO, kindid, lhubid, agentid, DAO.TABLENAME);
    }

    public static Openkind4third getByKindidLhubidAgentid(Integer kindid, Integer lhubid, Integer agentid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindidLhubidAgentid(DAO, kindid, lhubid, agentid, TABLENAME2);
    }

    public static Openkind4third getByKindidLhubidAgentid(final Openkind4thirdDAO DAO, Integer kindid, Integer lhubid, Integer agentid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindidLhubidAgentid(kindid, lhubid, agentid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = kindid+"-"+lhubid+"-"+agentid;
            Integer id = varsByKindidLhubidAgentid.get(vkey);
            if(id == null) return null;
            Openkind4third result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getKindid() != kindid){
                varsByKindidLhubidAgentid.remove(vkey);
                return null;
            }
            if(result.getLhubid() != lhubid){
                varsByKindidLhubidAgentid.remove(vkey);
                return null;
            }
            if(result.getAgentid() != agentid){
                varsByKindidLhubidAgentid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static int countByAgentid(Integer agentid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static int countByAgentid(Openkind4thirdDAO DAO, Integer agentid) {
        return countByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static int countByAgentid(Integer agentid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAgentid(DAO, agentid, TABLENAME2);
    }

    public static int countByAgentid(final Openkind4thirdDAO DAO, final Integer agentid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByAgentid(agentid, TABLENAME2);
        }
        List<Openkind4third> openkind4thirds = getByAgentid(DAO, agentid, TABLENAME2);
        return openkind4thirds.size();
    }

    public static List<Openkind4third> getByAgentid(Integer agentid) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByAgentid(Openkind4thirdDAO DAO, Integer agentid) {
        return getByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static List<Openkind4third> getByAgentid(Integer agentid, String TABLENAME2) {
        Openkind4thirdDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAgentid(DAO, agentid, TABLENAME2);
    }

    public static List<Openkind4third> getByAgentid(final Openkind4thirdDAO DAO, final Integer agentid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAgentid(agentid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Openkind4third> result = newList();
            Set<Integer> m1 = varsByAgentid.get(agentid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Openkind4third e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _agentid = e.getAgentid();
                if(_agentid != agentid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Openkind4third update(Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = DAO();
        return update(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third update(Openkind4thirdDAO DAO, Openkind4third openkind4third) {
        return update(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third update(Openkind4third openkind4third, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return update(DAO, openkind4third, TABLENAME2);
    }

    public static Openkind4third update(final Openkind4thirdDAO DAO, final Openkind4third openkind4third,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(openkind4third, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(openkind4third, TABLENAME2);
            if(n == -1) 
                return openkind4third;
            else if(n <= 0) 
                return null;
        }
        return openkind4third;
    }

    public static Openkind4third asyncUpdate(Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = DAO();
        return asyncUpdate(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third asyncUpdate(Openkind4thirdDAO DAO, Openkind4third openkind4third) {
        return asyncUpdate(DAO, openkind4third, DAO.TABLENAME);
    }

    public static Openkind4third asyncUpdate(Openkind4third openkind4third, String TABLENAME2) {
        Openkind4thirdDAO DAO = DAO();
        return asyncUpdate(DAO, openkind4third, TABLENAME2);
    }

    public static Openkind4third asyncUpdate(final Openkind4thirdDAO DAO, final Openkind4third openkind4third,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(openkind4third, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(openkind4third, TABLENAME2);
        }
        return openkind4third;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Openkind4thirdDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Openkind4thirdDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Openkind4thirdDAO DAO, final String TABLENAME2) {
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

        List<Openkind4third> openkind4thirds = getAll();
        for (Openkind4third openkind4third : openkind4thirds) {
            int n = DAO.insert2(openkind4third, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
