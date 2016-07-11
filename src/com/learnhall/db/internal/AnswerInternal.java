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

import static com.learnhall.db.bean.Answer.Col;

//learnhall3_design - answer
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AnswerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AnswerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AnswerInternal(){}

    public static AnswerDAO DAO(){
        return AnswerEntity.AnswerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Answer[MAX];
    }
    private static Answer[] FIXED = new Answer[MAX];
    public static final Map<Integer, Answer> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustomerid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByAskid = newSortedMap();
    public static final Map<String, Integer> varsByAskidCustomerid = newSortedMap();

    private static final List<Answer> fixedCache = newList();

    public static void put(Answer answer, boolean force){
        if(answer == null || answer.id <= 0) return ;

        int id = answer.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(answer))
                	FIXED[id - 1] = answer;
                if (!fixedCache.contains(answer))
                	fixedCache.add(answer);
            }
        } else {
            vars.put(id, answer);
        }

        { // 单-非唯一索引 remove old index customerid
          Object ov = answer.oldVal(Col.customerid);
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
            int customerid = answer.getCustomerid();
            Set m1 = varsByCustomerid.get(customerid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByCustomerid.put(customerid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index askid
          Object ov = answer.oldVal(Col.askid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m2 = varsByAskid.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByAskid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int askid = answer.getAskid();
            Set m2 = varsByAskid.get(askid);
            if(m2 == null){
                m2 = newSortedSet();
                varsByAskid.put(askid, m2);
            }
            m2.add(id);
          }
        }

        { // askid_custid
          boolean bChanged = answer.inChanged(Col.askid, Col.customerid);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vaskid = answer.oldOrNew(Col.askid);
            Object vcustomerid = answer.oldOrNew(Col.customerid);
            String okey = com.bowlong.lang.PStr.b().a(vaskid, "-", vcustomerid).e();
            varsByAskidCustomerid.remove(okey);
          }
          if(bChanged || force) { // put new index
              int vaskid = answer.getAskid();
              int vcustomerid = answer.getCustomerid();
              String vkey = com.bowlong.lang.PStr.b().a(vaskid, "-", vcustomerid).e();
              varsByAskidCustomerid.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCustomerid.clear();
        varsByAskid.clear();
        varsByAskidCustomerid.clear();
        FIXED = new Answer[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AnswerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AnswerDAO DAO, String TABLENAME2){
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

    public static void relocate(AnswerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AnswerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AnswerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AnswerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AnswerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AnswerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AnswerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AnswerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AnswerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AnswerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AnswerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AnswerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AnswerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AnswerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AnswerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Answer[maxId(DAO)];

        List<Answer> answers = DAO.selectAll();
        for (Answer answer : answers) {
            answer.reset();
            put(answer, true);
        }
    }

    public static Map toMap(Answer answer){
        return answer.toMap();
    }

    public static List<Map> toMap(List<Answer> answers){
        List<Map> ret = new Vector<Map>();
        for (Answer answer : answers){
            Map e = answer.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Answer> sortZh(List<Answer> answers, final String key) {
        Collections.sort(answers, new Comparator<Answer>() {
            public int compare(Answer o1, Answer o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return answers;
    }

    public static List<Answer> sort(List<Answer> answers, final String key) {
        Collections.sort(answers, new Comparator<Answer>() {
            public int compare(Answer o1, Answer o2) {
                return o1.compareTo(o2, key);
            }
        });
        return answers;
    }

    public static List<Answer> sort(List<Answer> answers){
        Collections.sort(answers, new Comparator<Answer>(){
            public int compare(Answer o1, Answer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return answers;
    }

    public static List<Answer> sortReverse(List<Answer> answers){
        Collections.sort(answers, new Comparator<Answer>(){
            public int compare(Answer o1, Answer o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return answers;
    }

    public static List<Answer> sortCustomerid(List<Answer> answers){
        Collections.sort(answers, new Comparator<Answer>(){
            public int compare(Answer o1, Answer o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return compareTo(v1, v2);
            }
        });
        return answers;
    }

    public static List<Answer> sortCustomeridRo(List<Answer> answers){
        Collections.sort(answers, new Comparator<Answer>(){
            public int compare(Answer o1, Answer o2) {
                Object v1 = o1.getCustomerid();
                Object v2 = o2.getCustomerid();
                return 0 - compareTo(v1, v2);
            };
        });
        return answers;
    }

    public static List<Answer> sortAskid(List<Answer> answers){
        Collections.sort(answers, new Comparator<Answer>(){
            public int compare(Answer o1, Answer o2) {
                Object v1 = o1.getAskid();
                Object v2 = o2.getAskid();
                return compareTo(v1, v2);
            }
        });
        return answers;
    }

    public static List<Answer> sortAskidRo(List<Answer> answers){
        Collections.sort(answers, new Comparator<Answer>(){
            public int compare(Answer o1, Answer o2) {
                Object v1 = o1.getAskid();
                Object v2 = o2.getAskid();
                return 0 - compareTo(v1, v2);
            };
        });
        return answers;
    }

    public static Answer insert(Answer answer) {
        AnswerDAO DAO = DAO();
        return insert(DAO, answer, DAO.TABLENAME);
    }

    public static Answer insert(AnswerDAO DAO, Answer answer) {
        return insert(DAO, answer, DAO.TABLENAME);
    }

    public static Answer insert(Answer answer, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return insert(DAO, answer, TABLENAME2);
    }

    public static Answer insert(AnswerDAO DAO, Answer answer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(answer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        answer.id = n;
        if(cacheModel != NO_CACHE) put(answer, true);

        return answer;
    }

    public static Answer asyncInsert(Answer answer) {
        AnswerDAO DAO = DAO();
        return asyncInsert(DAO, answer, DAO.TABLENAME);
    }
    public static Answer asyncInsert(AnswerDAO DAO, Answer answer) {
        return asyncInsert(DAO, answer, DAO.TABLENAME);
    }
    public static Answer asyncInsert(Answer answer, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return asyncInsert(DAO, answer, TABLENAME2);
    }
    public static Answer asyncInsert(AnswerDAO DAO, Answer answer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(answer, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        answer.id = n;
        if(cacheModel != NO_CACHE) put(answer, true);
        return answer;
    }
    public static Answer insert2(Answer answer) {
        AnswerDAO DAO = DAO();
        return insert2(DAO, answer, DAO.TABLENAME);
    }

    public static Answer insert2(AnswerDAO DAO, Answer answer) {
        return insert2(DAO, answer, DAO.TABLENAME);
    }

    public static Answer insert2(Answer answer, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return insert2(DAO, answer, TABLENAME2);
    }

    public static Answer insert2(AnswerDAO DAO, Answer answer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(answer, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        answer.id = n;
        if(cacheModel != NO_CACHE) put(answer, true);

        return answer;
    }

    public static Answer asyncInsert2(Answer answer) {
        AnswerDAO DAO = DAO();
        return asyncInsert2(DAO, answer, DAO.TABLENAME);
    }
    public static Answer asyncInsert2(AnswerDAO DAO, Answer answer) {
        return asyncInsert2(DAO, answer, DAO.TABLENAME);
    }
    public static Answer asyncInsert2(Answer answer, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return asyncInsert2(DAO, answer, TABLENAME2);
    }
    public static Answer asyncInsert2(AnswerDAO DAO, Answer answer, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        answer.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(answer, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(answer, true);
        return answer;
    }
    public static int[] insert(List<Answer> answers) {
        AnswerDAO DAO = DAO();
        return insert(DAO, answers, DAO.TABLENAME);
    }

    public static int[] insert(AnswerDAO DAO, List<Answer> answers) {
        return insert(DAO, answers, DAO.TABLENAME);
    }

    public static int[] insert(List<Answer> answers, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return insert(DAO, answers, TABLENAME2);
    }

    public static int[] insert(AnswerDAO DAO, List<Answer> answers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(answers, TABLENAME2);
            int n = 0;
            for(Answer answer : answers){
                answer.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[answers.size()];
        int n = 0;
        for(Answer answer : answers){
            answer = insert(DAO, answer, TABLENAME2);
            ret[n++] = (answer == null) ? 0 : (int)answer.id;
        }
        return ret;
    }

    public static int delete(Answer answer) {
        int id = answer.id;
        AnswerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        AnswerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(AnswerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(AnswerDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Answer answer) {
        int id = answer.id;
        AnswerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        AnswerDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(AnswerDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(AnswerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        AnswerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(AnswerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(AnswerDAO DAO, int[] ids,String TABLENAME2) {
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
        AnswerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AnswerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AnswerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Answer> beans) {
        AnswerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Answer> beans, AnswerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Answer> beans, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Answer> beans, final AnswerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Answer answer : beans){
                int id = answer.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Answer> getAll() {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Answer> getAll(AnswerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Answer> getAll(String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Answer> getAll(final AnswerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Answer> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Answer> getNoSortAll() {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Answer> getNoSortAll(AnswerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Answer> getNoSortAll(String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Answer> getNoSortAll(final AnswerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Answer> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Answer> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Answer answer = FIXED[i];
                if (answer != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Answer> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Answer> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AnswerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AnswerDAO DAO, final String TABLENAME2) {
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
        AnswerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AnswerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AnswerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Answer> getIn(List<Integer> keys) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Answer> getIn(List<Integer> keys, AnswerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Answer> getIn(List<Integer> keys, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Answer> getIn(final List<Integer> keys, final AnswerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Answer> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Answer> getNoSortIn(List<Integer> keys) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Answer> getNoSortIn(List<Integer> keys, AnswerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Answer> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Answer> getNoSortIn(final List<Integer> keys, final AnswerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Answer> result = newList();
            for (int key : keys) {
                Answer answer = getByKeyFromMemory(key);
                if( answer == null ) continue;
                result.add(answer);
            }
            return result;
        }
    }

    public static List<Answer> getLast(int num) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Answer> getLast(AnswerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Answer> getLast(int num, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Answer> getLast(final AnswerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Answer> result = newList();
            List<Answer> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Answer last() {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Answer last(AnswerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Answer last(String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Answer last(final AnswerDAO DAO, final String TABLENAME2) {
        Answer result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AnswerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AnswerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AnswerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Answer> getGtKey(int id) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Answer> getGtKey(AnswerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Answer> getGtKey(int id, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Answer> getGtKey(final AnswerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Answer> result = newList();
            List<Answer> answers = getAll();
            for (Answer answer : answers) {
                if(answer.id <= id) continue;
                result.add(answer);
            }
            return result;
        }
    }

    public static Answer getByKey(int id) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Answer> asyncGetByKey(final int id) {
        Future<Answer> f = Async.exec(new Callable<Answer>() {
            public Answer call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Answer getByKey(AnswerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Answer getByKey(int id, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Answer getByKey(final AnswerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Answer getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Answer deleteFromMemory(final int id) {
        Answer answer;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            answer = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(answer);
        } else {
            answer = vars.remove(id);
        }
        if(answer == null) return null;

        int customerid = answer.getCustomerid();
        Set m1 = varsByCustomerid.get(customerid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByCustomerid.remove(customerid);
        }

        int askid = answer.getAskid();
        Set m2 = varsByAskid.get(askid);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByAskid.remove(askid);
        }

        { // askid_custid
            int vaskid = answer.getAskid();
            int vcustomerid = answer.getCustomerid();
            String vkey = com.bowlong.lang.PStr.b().a(vaskid, "-", vcustomerid).e();
            varsByAskidCustomerid.remove(vkey);
        }

        return answer;
    }

    public static List<Answer> getByPage(int page, int size) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Answer> getByPage(AnswerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Answer> getByPage(int page, int size, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Answer> getByPage(final AnswerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Answer> result = newList();
            List<Answer> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AnswerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AnswerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByCustomerid(Integer customerid) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(AnswerDAO DAO, Integer customerid) {
        return countByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static int countByCustomerid(Integer customerid, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static int countByCustomerid(final AnswerDAO DAO, final Integer customerid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustomerid(customerid, TABLENAME2);
        }
        List<Answer> answers = getByCustomerid(DAO, customerid, TABLENAME2);
        return answers.size();
    }

    public static List<Answer> getByCustomerid(Integer customerid) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Answer> getByCustomerid(AnswerDAO DAO, Integer customerid) {
        return getByCustomerid(DAO, customerid, DAO.TABLENAME);
    }

    public static List<Answer> getByCustomerid(Integer customerid, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustomerid(DAO, customerid, TABLENAME2);
    }

    public static List<Answer> getByCustomerid(final AnswerDAO DAO, final Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustomerid(customerid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Answer> result = newList();
            Set<Integer> m1 = varsByCustomerid.get(customerid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Answer e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByAskid(Integer askid) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAskid(DAO, askid, DAO.TABLENAME);
    }

    public static int countByAskid(AnswerDAO DAO, Integer askid) {
        return countByAskid(DAO, askid, DAO.TABLENAME);
    }

    public static int countByAskid(Integer askid, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAskid(DAO, askid, TABLENAME2);
    }

    public static int countByAskid(final AnswerDAO DAO, final Integer askid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByAskid(askid, TABLENAME2);
        }
        List<Answer> answers = getByAskid(DAO, askid, TABLENAME2);
        return answers.size();
    }

    public static List<Answer> getByAskid(Integer askid) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAskid(DAO, askid, DAO.TABLENAME);
    }

    public static List<Answer> getByAskid(AnswerDAO DAO, Integer askid) {
        return getByAskid(DAO, askid, DAO.TABLENAME);
    }

    public static List<Answer> getByAskid(Integer askid, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAskid(DAO, askid, TABLENAME2);
    }

    public static List<Answer> getByAskid(final AnswerDAO DAO, final Integer askid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAskid(askid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Answer> result = newList();
            Set<Integer> m1 = varsByAskid.get(askid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Answer e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _askid = e.getAskid();
                if(_askid != askid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Answer getByAskidCustomerid(Integer askid, Integer customerid) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAskidCustomerid(DAO, askid, customerid, DAO.TABLENAME);
    }

    public static Answer getByAskidCustomerid(AnswerDAO DAO, Integer askid, Integer customerid) {
        return getByAskidCustomerid(DAO, askid, customerid, DAO.TABLENAME);
    }

    public static Answer getByAskidCustomerid(Integer askid, Integer customerid, String TABLENAME2) {
        AnswerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAskidCustomerid(DAO, askid, customerid, TABLENAME2);
    }

    public static Answer getByAskidCustomerid(final AnswerDAO DAO, Integer askid, Integer customerid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAskidCustomerid(askid, customerid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = askid+"-"+customerid;
            Integer id = varsByAskidCustomerid.get(vkey);
            if(id == null) return null;
            Answer result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getAskid() != askid){
                varsByAskidCustomerid.remove(vkey);
                return null;
            }
            if(result.getCustomerid() != customerid){
                varsByAskidCustomerid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Answer update(Answer answer) {
        AnswerDAO DAO = DAO();
        return update(DAO, answer, DAO.TABLENAME);
    }

    public static Answer update(AnswerDAO DAO, Answer answer) {
        return update(DAO, answer, DAO.TABLENAME);
    }

    public static Answer update(Answer answer, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return update(DAO, answer, TABLENAME2);
    }

    public static Answer update(final AnswerDAO DAO, final Answer answer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(answer, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(answer, TABLENAME2);
            if(n == -1) 
                return answer;
            else if(n <= 0) 
                return null;
        }
        return answer;
    }

    public static Answer asyncUpdate(Answer answer) {
        AnswerDAO DAO = DAO();
        return asyncUpdate(DAO, answer, DAO.TABLENAME);
    }

    public static Answer asyncUpdate(AnswerDAO DAO, Answer answer) {
        return asyncUpdate(DAO, answer, DAO.TABLENAME);
    }

    public static Answer asyncUpdate(Answer answer, String TABLENAME2) {
        AnswerDAO DAO = DAO();
        return asyncUpdate(DAO, answer, TABLENAME2);
    }

    public static Answer asyncUpdate(final AnswerDAO DAO, final Answer answer,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(answer, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(answer, TABLENAME2);
        }
        return answer;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AnswerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AnswerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AnswerDAO DAO, final String TABLENAME2) {
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

        List<Answer> answers = getAll();
        for (Answer answer : answers) {
            int n = DAO.insert2(answer, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
