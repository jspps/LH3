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

import static com.learnhall.db.bean.Agent.Col;

//learnhall3_design - agent
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AgentInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AgentInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AgentInternal(){}

    public static AgentDAO DAO(){
        return AgentEntity.AgentDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Agent[MAX];
    }
    private static Agent[] FIXED = new Agent[MAX];
    public static final Map<Integer, Agent> vars = newSortedMap();
    public static final Map<String, Integer> varsByCode = newSortedMap();
    public static final Map<Integer, Integer> varsByAccountid = newSortedMap();

    private static final List<Agent> fixedCache = newList();

    public static void put(Agent agent, boolean force){
        if(agent == null || agent.agid <= 0) return ;

        int agid = agent.agid;
        if (cacheModel == STATIC_CACHE) {
            if (agid > 0 && agid <= FIXED.length) {
                if (FIXED[agid - 1] == null || !FIXED[agid - 1].equals(agent))
                	FIXED[agid - 1] = agent;
                if (!fixedCache.contains(agent))
                	fixedCache.add(agent);
            }
        } else {
            vars.put(agid, agent);
        }

        { // 单-唯一索引 remove old index code
          Object ov = agent.oldVal(Col.code);
          if(ov != null)
              varsByCode.remove(ov);
          if(ov != null || force){ // put new index
            String code = agent.getCode();
            varsByCode.put(code, agid);
          }
        }

        { // 单-唯一索引 remove old index accountid
          Object ov = agent.oldVal(Col.accountid);
          if(ov != null)
              varsByAccountid.remove(ov);
          if(ov != null || force){ // put new index
            int accountid = agent.getAccountid();
            varsByAccountid.put(accountid, agid);
          }
        }

        // LASTID = agid > LASTID ? agid : LASTID;
        if (agid > LASTID.get()) LASTID.set(agid);
    }

    public static void clear(){
        varsByCode.clear();
        varsByAccountid.clear();
        FIXED = new Agent[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AgentDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AgentDAO DAO, String TABLENAME2){
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

    public static void relocate(AgentDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AgentDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AgentDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AgentDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AgentDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AgentDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AgentDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AgentDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AgentDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AgentDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AgentDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AgentDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AgentDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AgentDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AgentDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Agent[maxId(DAO)];

        List<Agent> agents = DAO.selectAll();
        for (Agent agent : agents) {
            agent.reset();
            put(agent, true);
        }
    }

    public static Map toMap(Agent agent){
        return agent.toMap();
    }

    public static List<Map> toMap(List<Agent> agents){
        List<Map> ret = new Vector<Map>();
        for (Agent agent : agents){
            Map e = agent.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Agent> sortZh(List<Agent> agents, final String key) {
        Collections.sort(agents, new Comparator<Agent>() {
            public int compare(Agent o1, Agent o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return agents;
    }

    public static List<Agent> sort(List<Agent> agents, final String key) {
        Collections.sort(agents, new Comparator<Agent>() {
            public int compare(Agent o1, Agent o2) {
                return o1.compareTo(o2, key);
            }
        });
        return agents;
    }

    public static List<Agent> sort(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>(){
            public int compare(Agent o1, Agent o2) {
                Object v1 = o1.agid;
                Object v2 = o2.agid;
                return compareTo(v1, v2);
            }
        });
        return agents;
    }

    public static List<Agent> sortReverse(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>(){
            public int compare(Agent o1, Agent o2) {
                Object v1 = o1.agid;
                Object v2 = o2.agid;
                return 0 - compareTo(v1, v2);
            }
        });
        return agents;
    }

    public static List<Agent> sortCode(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>(){
            public int compare(Agent o1, Agent o2) {
                Object v1 = o1.getCode();
                Object v2 = o2.getCode();
                return compareTo(v1, v2);
            }
        });
        return agents;
    }

    public static List<Agent> sortCodeRo(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>(){
            public int compare(Agent o1, Agent o2) {
                Object v1 = o1.getCode();
                Object v2 = o2.getCode();
                return 0 - compareTo(v1, v2);
            };
        });
        return agents;
    }

    public static List<Agent> sortAccountid(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>(){
            public int compare(Agent o1, Agent o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return compareTo(v1, v2);
            }
        });
        return agents;
    }

    public static List<Agent> sortAccountidRo(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>(){
            public int compare(Agent o1, Agent o2) {
                Object v1 = o1.getAccountid();
                Object v2 = o2.getAccountid();
                return 0 - compareTo(v1, v2);
            };
        });
        return agents;
    }

    public static Agent insert(Agent agent) {
        AgentDAO DAO = DAO();
        return insert(DAO, agent, DAO.TABLENAME);
    }

    public static Agent insert(AgentDAO DAO, Agent agent) {
        return insert(DAO, agent, DAO.TABLENAME);
    }

    public static Agent insert(Agent agent, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return insert(DAO, agent, TABLENAME2);
    }

    public static Agent insert(AgentDAO DAO, Agent agent, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(agent, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        agent.agid = n;
        if(cacheModel != NO_CACHE) put(agent, true);

        return agent;
    }

    public static Agent asyncInsert(Agent agent) {
        AgentDAO DAO = DAO();
        return asyncInsert(DAO, agent, DAO.TABLENAME);
    }
    public static Agent asyncInsert(AgentDAO DAO, Agent agent) {
        return asyncInsert(DAO, agent, DAO.TABLENAME);
    }
    public static Agent asyncInsert(Agent agent, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return asyncInsert(DAO, agent, TABLENAME2);
    }
    public static Agent asyncInsert(AgentDAO DAO, Agent agent, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(agent, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        agent.agid = n;
        if(cacheModel != NO_CACHE) put(agent, true);
        return agent;
    }
    public static Agent insert2(Agent agent) {
        AgentDAO DAO = DAO();
        return insert2(DAO, agent, DAO.TABLENAME);
    }

    public static Agent insert2(AgentDAO DAO, Agent agent) {
        return insert2(DAO, agent, DAO.TABLENAME);
    }

    public static Agent insert2(Agent agent, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return insert2(DAO, agent, TABLENAME2);
    }

    public static Agent insert2(AgentDAO DAO, Agent agent, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(agent, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        agent.agid = n;
        if(cacheModel != NO_CACHE) put(agent, true);

        return agent;
    }

    public static Agent asyncInsert2(Agent agent) {
        AgentDAO DAO = DAO();
        return asyncInsert2(DAO, agent, DAO.TABLENAME);
    }
    public static Agent asyncInsert2(AgentDAO DAO, Agent agent) {
        return asyncInsert2(DAO, agent, DAO.TABLENAME);
    }
    public static Agent asyncInsert2(Agent agent, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return asyncInsert2(DAO, agent, TABLENAME2);
    }
    public static Agent asyncInsert2(AgentDAO DAO, Agent agent, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        agent.agid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(agent, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(agent, true);
        return agent;
    }
    public static int[] insert(List<Agent> agents) {
        AgentDAO DAO = DAO();
        return insert(DAO, agents, DAO.TABLENAME);
    }

    public static int[] insert(AgentDAO DAO, List<Agent> agents) {
        return insert(DAO, agents, DAO.TABLENAME);
    }

    public static int[] insert(List<Agent> agents, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return insert(DAO, agents, TABLENAME2);
    }

    public static int[] insert(AgentDAO DAO, List<Agent> agents, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(agents, TABLENAME2);
            int n = 0;
            for(Agent agent : agents){
                agent.agid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[agents.size()];
        int n = 0;
        for(Agent agent : agents){
            agent = insert(DAO, agent, TABLENAME2);
            ret[n++] = (agent == null) ? 0 : (int)agent.agid;
        }
        return ret;
    }

    public static int delete(Agent agent) {
        int agid = agent.agid;
        AgentDAO DAO = DAO();
        return delete(DAO, agid, DAO.TABLENAME);
    }

    public static int delete(int agid) {
        AgentDAO DAO = DAO();
        return delete(DAO, agid, DAO.TABLENAME);
    }

    public static int delete(AgentDAO DAO, int agid) {
        return delete(DAO, agid, DAO.TABLENAME);
    }

    public static int delete(int agid, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return delete(DAO, agid, TABLENAME2);
    }

    public static int delete(AgentDAO DAO, int agid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(agid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(agid);
        }
        return n;
    }

    public static void asyncDelete(Agent agent) {
        int agid = agent.agid;
        AgentDAO DAO = DAO();
        asyncDelete(DAO, agid, DAO.TABLENAME);
    }

    public static void asyncDelete(int agid) {
        AgentDAO DAO = DAO();
        asyncDelete(DAO, agid, DAO.TABLENAME);
    }

    public static void asyncDelete(AgentDAO DAO, int agid) {
        asyncDelete(DAO, agid, DAO.TABLENAME);
    }

    public static void asyncDelete(int agid, String TABLENAME2) {
        AgentDAO DAO = DAO();
        asyncDelete(DAO, agid, TABLENAME2);
    }

    public static void asyncDelete(AgentDAO DAO, int agid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(agid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(agid);
        }
    }

    public static int[] delete(int[] agids) {
        AgentDAO DAO = DAO();
        return delete(DAO, agids, DAO.TABLENAME);
    }

    public static int[] delete(AgentDAO DAO, int[] agids) {
        return delete(DAO, agids, DAO.TABLENAME);
    }

    public static int[] delete(int[] agids,String TABLENAME2) {
        AgentDAO DAO = DAO();
        return delete(DAO, agids, TABLENAME2);
    }

    public static int[] delete(AgentDAO DAO, int[] agids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(agids, TABLENAME2);
        }
        int[] ret = new int[agids.length];
        int n = 0;
        for(int agid : agids){
            ret[n++] = delete(DAO, agid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        AgentDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AgentDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AgentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer agid : keys){
                deleteFromMemory(agid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Agent> beans) {
        AgentDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Agent> beans, AgentDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Agent> beans, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Agent> beans, final AgentDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Agent agent : beans){
                int agid = agent.agid;
                deleteFromMemory(agid);
            }
        }
        return result;
    }

    public static List<Agent> getAll() {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Agent> getAll(AgentDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Agent> getAll(String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Agent> getAll(final AgentDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Agent> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Agent> getNoSortAll() {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Agent> getNoSortAll(AgentDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Agent> getNoSortAll(String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Agent> getNoSortAll(final AgentDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Agent> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Agent> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Agent agent = FIXED[i];
                if (agent != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Agent> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Agent> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AgentDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AgentDAO DAO, final String TABLENAME2) {
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
        AgentDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AgentDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AgentDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AgentDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Agent> getIn(List<Integer> keys) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Agent> getIn(List<Integer> keys, AgentDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Agent> getIn(List<Integer> keys, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Agent> getIn(final List<Integer> keys, final AgentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Agent> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Agent> getNoSortIn(List<Integer> keys) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Agent> getNoSortIn(List<Integer> keys, AgentDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Agent> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Agent> getNoSortIn(final List<Integer> keys, final AgentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Agent> result = newList();
            for (int key : keys) {
                Agent agent = getByKeyFromMemory(key);
                if( agent == null ) continue;
                result.add(agent);
            }
            return result;
        }
    }

    public static List<Agent> getLast(int num) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Agent> getLast(AgentDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Agent> getLast(int num, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Agent> getLast(final AgentDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Agent> result = newList();
            List<Agent> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Agent last() {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Agent last(AgentDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Agent last(String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Agent last(final AgentDAO DAO, final String TABLENAME2) {
        Agent result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AgentDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AgentDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AgentDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AgentDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Agent> getGtKey(int agid) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, agid, DAO.TABLENAME);
    }

    public static List<Agent> getGtKey(AgentDAO DAO, int agid) {
        return getGtKey(DAO, agid, DAO.TABLENAME);
    }

    public static List<Agent> getGtKey(int agid, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, agid, TABLENAME2);
    }

    public static List<Agent> getGtKey(final AgentDAO DAO, final int agid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(agid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Agent> result = newList();
            List<Agent> agents = getAll();
            for (Agent agent : agents) {
                if(agent.agid <= agid) continue;
                result.add(agent);
            }
            return result;
        }
    }

    public static Agent getByKey(int agid) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, agid, DAO.TABLENAME);
    }

    public static Future<Agent> asyncGetByKey(final int agid) {
        Future<Agent> f = Async.exec(new Callable<Agent>() {
            public Agent call() throws Exception {
                return getByKey(agid);
            }
        });
        return f;
    }

    public static Agent getByKey(AgentDAO DAO, int agid) {
        return getByKey(DAO, agid, DAO.TABLENAME);
    }

    public static Agent getByKey(int agid, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, agid, TABLENAME2);
    }

    public static Agent getByKey(final AgentDAO DAO, final int agid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(agid, TABLENAME2);
        }
        return getByKeyFromMemory(agid);
    }

    public static Agent getByKeyFromMemory(final int agid) {
        if (cacheModel == STATIC_CACHE) {
            if (agid < 1 || FIXED == null || FIXED.length < agid) return null;
            return FIXED[agid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(agid);
        }
        return null;
    }

    public static Agent deleteFromMemory(final int agid) {
        Agent agent;
        if (cacheModel == STATIC_CACHE) {
            if (agid < 1 || FIXED == null || FIXED.length < agid || FIXED[agid-1]==null) return null;
            agent = FIXED[agid - 1];
            FIXED[agid - 1] = null;
            fixedCache.remove(agent);
        } else {
            agent = vars.remove(agid);
        }
        if(agent == null) return null;

        String code = agent.getCode();
        varsByCode.remove(code);

        int accountid = agent.getAccountid();
        varsByAccountid.remove(accountid);

        return agent;
    }

    public static List<Agent> getByPage(int page, int size) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Agent> getByPage(AgentDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Agent> getByPage(int page, int size, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Agent> getByPage(final AgentDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Agent> result = newList();
            List<Agent> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AgentDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AgentDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Agent getByCode(String code) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCode(DAO, code, DAO.TABLENAME);
    }

    public static Agent getByCode(AgentDAO DAO, String code) {
        return getByCode(DAO, code, DAO.TABLENAME);
    }

    public static Agent getByCode(String code, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCode(DAO, code, TABLENAME2);
    }

    public static Agent getByCode(final AgentDAO DAO, final String code,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCode(code, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer agid = varsByCode.get(code);
            if(agid == null) return null;
            Agent result = getByKey(DAO, agid, TABLENAME2);
            if(result == null) return null;
            if(!result.getCode().equals(code)){
                varsByCode.remove(code);
                return null;
            }
            return result;
        }
    }

    public static int countLikeCode(String code) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCode(DAO, code, DAO.TABLENAME);
    }

    public static int countLikeCode(AgentDAO DAO, String code) {
        return countLikeCode(DAO, code, DAO.TABLENAME);
    }

    public static int countLikeCode(String code, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCode(DAO, code, TABLENAME2);
    }

    public static int countLikeCode(final AgentDAO DAO, final String code,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeCode(code, TABLENAME2);
        }
        List<Agent> agents = getLikeCode(DAO, code, TABLENAME2);
        return agents.size();
    }

    public static List<Agent> getLikeCode(String code) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCode(DAO, code, DAO.TABLENAME);
    }

    public static List<Agent> getLikeCode(AgentDAO DAO, String code) {
        return getLikeCode(DAO, code, DAO.TABLENAME);
    }

    public static List<Agent> getLikeCode(String code, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCode(DAO, code, TABLENAME2);
    }

    public static List<Agent> getLikeCode(final AgentDAO DAO, final String code,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeCode(code, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Agent> result = newList();
            List<Agent> agents = getAll(DAO, TABLENAME2);
            for(Agent e : agents){
                String _var = e.getCode();
                if(_var.indexOf(code) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Agent getByAccountid(Integer accountid) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Agent getByAccountid(AgentDAO DAO, Integer accountid) {
        return getByAccountid(DAO, accountid, DAO.TABLENAME);
    }

    public static Agent getByAccountid(Integer accountid, String TABLENAME2) {
        AgentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAccountid(DAO, accountid, TABLENAME2);
    }

    public static Agent getByAccountid(final AgentDAO DAO, final int accountid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAccountid(accountid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer agid = varsByAccountid.get(accountid);
            if(agid == null) return null;
            Agent result = getByKey(DAO, agid, TABLENAME2);
            if(result == null) return null;
            if(result.getAccountid() != accountid){
                varsByAccountid.remove(accountid);
                return null;
            }
            return result;
        }
    }

    public static Agent update(Agent agent) {
        AgentDAO DAO = DAO();
        return update(DAO, agent, DAO.TABLENAME);
    }

    public static Agent update(AgentDAO DAO, Agent agent) {
        return update(DAO, agent, DAO.TABLENAME);
    }

    public static Agent update(Agent agent, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return update(DAO, agent, TABLENAME2);
    }

    public static Agent update(final AgentDAO DAO, final Agent agent,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(agent, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(agent, TABLENAME2);
            if(n == -1) 
                return agent;
            else if(n <= 0) 
                return null;
        }
        return agent;
    }

    public static Agent asyncUpdate(Agent agent) {
        AgentDAO DAO = DAO();
        return asyncUpdate(DAO, agent, DAO.TABLENAME);
    }

    public static Agent asyncUpdate(AgentDAO DAO, Agent agent) {
        return asyncUpdate(DAO, agent, DAO.TABLENAME);
    }

    public static Agent asyncUpdate(Agent agent, String TABLENAME2) {
        AgentDAO DAO = DAO();
        return asyncUpdate(DAO, agent, TABLENAME2);
    }

    public static Agent asyncUpdate(final AgentDAO DAO, final Agent agent,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(agent, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(agent, TABLENAME2);
        }
        return agent;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AgentDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AgentDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AgentDAO DAO, final String TABLENAME2) {
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

        List<Agent> agents = getAll();
        for (Agent agent : agents) {
            int n = DAO.insert2(agent, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
