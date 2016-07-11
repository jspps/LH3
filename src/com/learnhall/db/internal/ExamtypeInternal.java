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

import static com.learnhall.db.bean.Examtype.Col;

//learnhall3_design - examtype
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ExamtypeInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ExamtypeInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ExamtypeInternal(){}

    public static ExamtypeDAO DAO(){
        return ExamtypeEntity.ExamtypeDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Examtype[MAX];
    }
    private static Examtype[] FIXED = new Examtype[MAX];
    public static final Map<Integer, Examtype> vars = newSortedMap();
    public static final Map<String, Integer> varsByName = newSortedMap();

    private static final List<Examtype> fixedCache = newList();

    public static void put(Examtype examtype, boolean force){
        if(examtype == null || examtype.id <= 0) return ;

        int id = examtype.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(examtype))
                	FIXED[id - 1] = examtype;
                if (!fixedCache.contains(examtype))
                	fixedCache.add(examtype);
            }
        } else {
            vars.put(id, examtype);
        }

        { // 单-唯一索引 remove old index name
          Object ov = examtype.oldVal(Col.name);
          if(ov != null)
              varsByName.remove(ov);
          if(ov != null || force){ // put new index
            String name = examtype.getName();
            varsByName.put(name, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByName.clear();
        FIXED = new Examtype[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ExamtypeDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ExamtypeDAO DAO, String TABLENAME2){
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

    public static void relocate(ExamtypeDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ExamtypeDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ExamtypeDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ExamtypeDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ExamtypeDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ExamtypeDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ExamtypeDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ExamtypeDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ExamtypeDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ExamtypeDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ExamtypeDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ExamtypeDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ExamtypeDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Examtype[maxId(DAO)];

        List<Examtype> examtypes = DAO.selectAll();
        for (Examtype examtype : examtypes) {
            examtype.reset();
            put(examtype, true);
        }
    }

    public static Map toMap(Examtype examtype){
        return examtype.toMap();
    }

    public static List<Map> toMap(List<Examtype> examtypes){
        List<Map> ret = new Vector<Map>();
        for (Examtype examtype : examtypes){
            Map e = examtype.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Examtype> sortZh(List<Examtype> examtypes, final String key) {
        Collections.sort(examtypes, new Comparator<Examtype>() {
            public int compare(Examtype o1, Examtype o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return examtypes;
    }

    public static List<Examtype> sort(List<Examtype> examtypes, final String key) {
        Collections.sort(examtypes, new Comparator<Examtype>() {
            public int compare(Examtype o1, Examtype o2) {
                return o1.compareTo(o2, key);
            }
        });
        return examtypes;
    }

    public static List<Examtype> sort(List<Examtype> examtypes){
        Collections.sort(examtypes, new Comparator<Examtype>(){
            public int compare(Examtype o1, Examtype o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return examtypes;
    }

    public static List<Examtype> sortReverse(List<Examtype> examtypes){
        Collections.sort(examtypes, new Comparator<Examtype>(){
            public int compare(Examtype o1, Examtype o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return examtypes;
    }

    public static List<Examtype> sortName(List<Examtype> examtypes){
        Collections.sort(examtypes, new Comparator<Examtype>(){
            public int compare(Examtype o1, Examtype o2) {
                Object v1 = o1.getName();
                Object v2 = o2.getName();
                return compareTo(v1, v2);
            }
        });
        return examtypes;
    }

    public static List<Examtype> sortNameRo(List<Examtype> examtypes){
        Collections.sort(examtypes, new Comparator<Examtype>(){
            public int compare(Examtype o1, Examtype o2) {
                Object v1 = o1.getName();
                Object v2 = o2.getName();
                return 0 - compareTo(v1, v2);
            };
        });
        return examtypes;
    }

    public static Examtype insert(Examtype examtype) {
        ExamtypeDAO DAO = DAO();
        return insert(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype insert(ExamtypeDAO DAO, Examtype examtype) {
        return insert(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype insert(Examtype examtype, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return insert(DAO, examtype, TABLENAME2);
    }

    public static Examtype insert(ExamtypeDAO DAO, Examtype examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(examtype, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        examtype.id = n;
        if(cacheModel != NO_CACHE) put(examtype, true);

        return examtype;
    }

    public static Examtype asyncInsert(Examtype examtype) {
        ExamtypeDAO DAO = DAO();
        return asyncInsert(DAO, examtype, DAO.TABLENAME);
    }
    public static Examtype asyncInsert(ExamtypeDAO DAO, Examtype examtype) {
        return asyncInsert(DAO, examtype, DAO.TABLENAME);
    }
    public static Examtype asyncInsert(Examtype examtype, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return asyncInsert(DAO, examtype, TABLENAME2);
    }
    public static Examtype asyncInsert(ExamtypeDAO DAO, Examtype examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(examtype, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        examtype.id = n;
        if(cacheModel != NO_CACHE) put(examtype, true);
        return examtype;
    }
    public static Examtype insert2(Examtype examtype) {
        ExamtypeDAO DAO = DAO();
        return insert2(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype insert2(ExamtypeDAO DAO, Examtype examtype) {
        return insert2(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype insert2(Examtype examtype, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return insert2(DAO, examtype, TABLENAME2);
    }

    public static Examtype insert2(ExamtypeDAO DAO, Examtype examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(examtype, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        examtype.id = n;
        if(cacheModel != NO_CACHE) put(examtype, true);

        return examtype;
    }

    public static Examtype asyncInsert2(Examtype examtype) {
        ExamtypeDAO DAO = DAO();
        return asyncInsert2(DAO, examtype, DAO.TABLENAME);
    }
    public static Examtype asyncInsert2(ExamtypeDAO DAO, Examtype examtype) {
        return asyncInsert2(DAO, examtype, DAO.TABLENAME);
    }
    public static Examtype asyncInsert2(Examtype examtype, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return asyncInsert2(DAO, examtype, TABLENAME2);
    }
    public static Examtype asyncInsert2(ExamtypeDAO DAO, Examtype examtype, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        examtype.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(examtype, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(examtype, true);
        return examtype;
    }
    public static int[] insert(List<Examtype> examtypes) {
        ExamtypeDAO DAO = DAO();
        return insert(DAO, examtypes, DAO.TABLENAME);
    }

    public static int[] insert(ExamtypeDAO DAO, List<Examtype> examtypes) {
        return insert(DAO, examtypes, DAO.TABLENAME);
    }

    public static int[] insert(List<Examtype> examtypes, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return insert(DAO, examtypes, TABLENAME2);
    }

    public static int[] insert(ExamtypeDAO DAO, List<Examtype> examtypes, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(examtypes, TABLENAME2);
            int n = 0;
            for(Examtype examtype : examtypes){
                examtype.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[examtypes.size()];
        int n = 0;
        for(Examtype examtype : examtypes){
            examtype = insert(DAO, examtype, TABLENAME2);
            ret[n++] = (examtype == null) ? 0 : (int)examtype.id;
        }
        return ret;
    }

    public static int delete(Examtype examtype) {
        int id = examtype.id;
        ExamtypeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ExamtypeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ExamtypeDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ExamtypeDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Examtype examtype) {
        int id = examtype.id;
        ExamtypeDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        ExamtypeDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(ExamtypeDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(ExamtypeDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ExamtypeDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ExamtypeDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ExamtypeDAO DAO, int[] ids,String TABLENAME2) {
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
        ExamtypeDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ExamtypeDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ExamtypeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Examtype> beans) {
        ExamtypeDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Examtype> beans, ExamtypeDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Examtype> beans, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Examtype> beans, final ExamtypeDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Examtype examtype : beans){
                int id = examtype.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Examtype> getAll() {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getAll(ExamtypeDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getAll(String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Examtype> getAll(final ExamtypeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examtype> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Examtype> getNoSortAll() {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getNoSortAll(ExamtypeDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getNoSortAll(String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Examtype> getNoSortAll(final ExamtypeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Examtype> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examtype> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Examtype examtype = FIXED[i];
                if (examtype != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Examtype> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Examtype> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ExamtypeDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ExamtypeDAO DAO, final String TABLENAME2) {
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
        ExamtypeDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ExamtypeDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ExamtypeDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Examtype> getIn(List<Integer> keys) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getIn(List<Integer> keys, ExamtypeDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getIn(List<Integer> keys, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Examtype> getIn(final List<Integer> keys, final ExamtypeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examtype> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Examtype> getNoSortIn(List<Integer> keys) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getNoSortIn(List<Integer> keys, ExamtypeDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Examtype> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Examtype> getNoSortIn(final List<Integer> keys, final ExamtypeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Examtype> result = newList();
            for (int key : keys) {
                Examtype examtype = getByKeyFromMemory(key);
                if( examtype == null ) continue;
                result.add(examtype);
            }
            return result;
        }
    }

    public static List<Examtype> getLast(int num) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Examtype> getLast(ExamtypeDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Examtype> getLast(int num, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Examtype> getLast(final ExamtypeDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Examtype> result = newList();
            List<Examtype> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Examtype last() {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Examtype last(ExamtypeDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Examtype last(String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Examtype last(final ExamtypeDAO DAO, final String TABLENAME2) {
        Examtype result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ExamtypeDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ExamtypeDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ExamtypeDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Examtype> getGtKey(int id) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Examtype> getGtKey(ExamtypeDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Examtype> getGtKey(int id, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Examtype> getGtKey(final ExamtypeDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examtype> result = newList();
            List<Examtype> examtypes = getAll();
            for (Examtype examtype : examtypes) {
                if(examtype.id <= id) continue;
                result.add(examtype);
            }
            return result;
        }
    }

    public static Examtype getByKey(int id) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Examtype> asyncGetByKey(final int id) {
        Future<Examtype> f = Async.exec(new Callable<Examtype>() {
            public Examtype call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Examtype getByKey(ExamtypeDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Examtype getByKey(int id, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Examtype getByKey(final ExamtypeDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Examtype getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Examtype deleteFromMemory(final int id) {
        Examtype examtype;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            examtype = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(examtype);
        } else {
            examtype = vars.remove(id);
        }
        if(examtype == null) return null;

        String name = examtype.getName();
        varsByName.remove(name);

        return examtype;
    }

    public static List<Examtype> getByPage(int page, int size) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Examtype> getByPage(ExamtypeDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Examtype> getByPage(int page, int size, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Examtype> getByPage(final ExamtypeDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Examtype> result = newList();
            List<Examtype> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ExamtypeDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ExamtypeDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Examtype getByName(String name) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByName(DAO, name, DAO.TABLENAME);
    }

    public static Examtype getByName(ExamtypeDAO DAO, String name) {
        return getByName(DAO, name, DAO.TABLENAME);
    }

    public static Examtype getByName(String name, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByName(DAO, name, TABLENAME2);
    }

    public static Examtype getByName(final ExamtypeDAO DAO, final String name,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByName(name, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByName.get(name);
            if(id == null) return null;
            Examtype result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getName().equals(name)){
                varsByName.remove(name);
                return null;
            }
            return result;
        }
    }

    public static int countLikeName(String name) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeName(DAO, name, DAO.TABLENAME);
    }

    public static int countLikeName(ExamtypeDAO DAO, String name) {
        return countLikeName(DAO, name, DAO.TABLENAME);
    }

    public static int countLikeName(String name, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeName(DAO, name, TABLENAME2);
    }

    public static int countLikeName(final ExamtypeDAO DAO, final String name,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeName(name, TABLENAME2);
        }
        List<Examtype> examtypes = getLikeName(DAO, name, TABLENAME2);
        return examtypes.size();
    }

    public static List<Examtype> getLikeName(String name) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeName(DAO, name, DAO.TABLENAME);
    }

    public static List<Examtype> getLikeName(ExamtypeDAO DAO, String name) {
        return getLikeName(DAO, name, DAO.TABLENAME);
    }

    public static List<Examtype> getLikeName(String name, String TABLENAME2) {
        ExamtypeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeName(DAO, name, TABLENAME2);
    }

    public static List<Examtype> getLikeName(final ExamtypeDAO DAO, final String name,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeName(name, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Examtype> result = newList();
            List<Examtype> examtypes = getAll(DAO, TABLENAME2);
            for(Examtype e : examtypes){
                String _var = e.getName();
                if(_var.indexOf(name) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Examtype update(Examtype examtype) {
        ExamtypeDAO DAO = DAO();
        return update(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype update(ExamtypeDAO DAO, Examtype examtype) {
        return update(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype update(Examtype examtype, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return update(DAO, examtype, TABLENAME2);
    }

    public static Examtype update(final ExamtypeDAO DAO, final Examtype examtype,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(examtype, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(examtype, TABLENAME2);
            if(n == -1) 
                return examtype;
            else if(n <= 0) 
                return null;
        }
        return examtype;
    }

    public static Examtype asyncUpdate(Examtype examtype) {
        ExamtypeDAO DAO = DAO();
        return asyncUpdate(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype asyncUpdate(ExamtypeDAO DAO, Examtype examtype) {
        return asyncUpdate(DAO, examtype, DAO.TABLENAME);
    }

    public static Examtype asyncUpdate(Examtype examtype, String TABLENAME2) {
        ExamtypeDAO DAO = DAO();
        return asyncUpdate(DAO, examtype, TABLENAME2);
    }

    public static Examtype asyncUpdate(final ExamtypeDAO DAO, final Examtype examtype,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(examtype, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(examtype, TABLENAME2);
        }
        return examtype;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ExamtypeDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ExamtypeDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ExamtypeDAO DAO, final String TABLENAME2) {
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

        List<Examtype> examtypes = getAll();
        for (Examtype examtype : examtypes) {
            int n = DAO.insert2(examtype, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
