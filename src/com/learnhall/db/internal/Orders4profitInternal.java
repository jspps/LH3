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

import static com.learnhall.db.bean.Orders4profit.Col;

//learnhall3_design - orders4profit
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Orders4profitInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Orders4profitInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Orders4profitInternal(){}

    public static Orders4profitDAO DAO(){
        return Orders4profitEntity.Orders4profitDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Orders4profit[MAX];
    }
    private static Orders4profit[] FIXED = new Orders4profit[MAX];
    public static final Map<Integer, Orders4profit> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByLhubid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByOrderNo = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByKindid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByCustid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByAgentid = newSortedMap();

    private static final List<Orders4profit> fixedCache = newList();

    public static void put(Orders4profit orders4profit, boolean force){
        if(orders4profit == null || orders4profit.id <= 0) return ;

        int id = orders4profit.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(orders4profit))
                	FIXED[id - 1] = orders4profit;
                if (!fixedCache.contains(orders4profit))
                	fixedCache.add(orders4profit);
            }
        } else {
            vars.put(id, orders4profit);
        }

        { // 单-非唯一索引 remove old index lhubid
          Object ov = orders4profit.oldVal(Col.lhubid);
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
            int lhubid = orders4profit.getLhubid();
            Set m1 = varsByLhubid.get(lhubid);
            if(m1 == null){
                m1 = newSortedSet();
                varsByLhubid.put(lhubid, m1);
            }
            m1.add(id);
          }
        }

        { // 单-非唯一索引 remove old index orderNo
          Object ov = orders4profit.oldVal(Col.orderNo);
          if(ov != null) {
              String _val = (String) ov;
              Set m2 = varsByOrderNo.get(_val);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                      varsByOrderNo.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String orderNo = orders4profit.getOrderNo();
            Set m2 = varsByOrderNo.get(orderNo);
            if(m2 == null){
                m2 = newSortedSet();
                varsByOrderNo.put(orderNo, m2);
            }
            m2.add(id);
          }
        }

        { // 单-非唯一索引 remove old index kindid
          Object ov = orders4profit.oldVal(Col.kindid);
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
            int kindid = orders4profit.getKindid();
            Set m3 = varsByKindid.get(kindid);
            if(m3 == null){
                m3 = newSortedSet();
                varsByKindid.put(kindid, m3);
            }
            m3.add(id);
          }
        }

        { // 单-非唯一索引 remove old index custid
          Object ov = orders4profit.oldVal(Col.custid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m4 = varsByCustid.get(_val);
              if(m4 != null) {
                  m4.remove(id);
                  if(m4.isEmpty())
                      varsByCustid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int custid = orders4profit.getCustid();
            Set m4 = varsByCustid.get(custid);
            if(m4 == null){
                m4 = newSortedSet();
                varsByCustid.put(custid, m4);
            }
            m4.add(id);
          }
        }

        { // 单-非唯一索引 remove old index agentid
          Object ov = orders4profit.oldVal(Col.agentid);
          if(ov != null) {
              Integer _val = (Integer) ov;
              Set m5 = varsByAgentid.get(_val);
              if(m5 != null) {
                  m5.remove(id);
                  if(m5.isEmpty())
                      varsByAgentid.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            int agentid = orders4profit.getAgentid();
            Set m5 = varsByAgentid.get(agentid);
            if(m5 == null){
                m5 = newSortedSet();
                varsByAgentid.put(agentid, m5);
            }
            m5.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLhubid.clear();
        varsByOrderNo.clear();
        varsByKindid.clear();
        varsByCustid.clear();
        varsByAgentid.clear();
        FIXED = new Orders4profit[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Orders4profitDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Orders4profitDAO DAO, String TABLENAME2){
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

    public static void relocate(Orders4profitDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Orders4profitDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Orders4profitDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Orders4profitDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Orders4profitDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Orders4profitDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Orders4profitDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Orders4profitDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Orders4profitDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Orders4profitDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Orders4profitDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Orders4profitDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Orders4profitDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Orders4profit[maxId(DAO)];

        List<Orders4profit> orders4profits = DAO.selectAll();
        for (Orders4profit orders4profit : orders4profits) {
            orders4profit.reset();
            put(orders4profit, true);
        }
    }

    public static Map toMap(Orders4profit orders4profit){
        return orders4profit.toMap();
    }

    public static List<Map> toMap(List<Orders4profit> orders4profits){
        List<Map> ret = new Vector<Map>();
        for (Orders4profit orders4profit : orders4profits){
            Map e = orders4profit.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Orders4profit> sortZh(List<Orders4profit> orders4profits, final String key) {
        Collections.sort(orders4profits, new Comparator<Orders4profit>() {
            public int compare(Orders4profit o1, Orders4profit o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sort(List<Orders4profit> orders4profits, final String key) {
        Collections.sort(orders4profits, new Comparator<Orders4profit>() {
            public int compare(Orders4profit o1, Orders4profit o2) {
                return o1.compareTo(o2, key);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sort(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortReverse(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortLhubid(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortLhubidRo(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getLhubid();
                Object v2 = o2.getLhubid();
                return 0 - compareTo(v1, v2);
            };
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortOrderNo(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getOrderNo();
                Object v2 = o2.getOrderNo();
                return compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortOrderNoRo(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getOrderNo();
                Object v2 = o2.getOrderNo();
                return 0 - compareTo(v1, v2);
            };
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortKindid(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortKindidRo(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getKindid();
                Object v2 = o2.getKindid();
                return 0 - compareTo(v1, v2);
            };
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortCustid(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getCustid();
                Object v2 = o2.getCustid();
                return compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortCustidRo(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getCustid();
                Object v2 = o2.getCustid();
                return 0 - compareTo(v1, v2);
            };
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortAgentid(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getAgentid();
                Object v2 = o2.getAgentid();
                return compareTo(v1, v2);
            }
        });
        return orders4profits;
    }

    public static List<Orders4profit> sortAgentidRo(List<Orders4profit> orders4profits){
        Collections.sort(orders4profits, new Comparator<Orders4profit>(){
            public int compare(Orders4profit o1, Orders4profit o2) {
                Object v1 = o1.getAgentid();
                Object v2 = o2.getAgentid();
                return 0 - compareTo(v1, v2);
            };
        });
        return orders4profits;
    }

    public static Orders4profit insert(Orders4profit orders4profit) {
        Orders4profitDAO DAO = DAO();
        return insert(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit insert(Orders4profitDAO DAO, Orders4profit orders4profit) {
        return insert(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit insert(Orders4profit orders4profit, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return insert(DAO, orders4profit, TABLENAME2);
    }

    public static Orders4profit insert(Orders4profitDAO DAO, Orders4profit orders4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(orders4profit, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        orders4profit.id = n;
        if(cacheModel != NO_CACHE) put(orders4profit, true);

        return orders4profit;
    }

    public static Orders4profit asyncInsert(Orders4profit orders4profit) {
        Orders4profitDAO DAO = DAO();
        return asyncInsert(DAO, orders4profit, DAO.TABLENAME);
    }
    public static Orders4profit asyncInsert(Orders4profitDAO DAO, Orders4profit orders4profit) {
        return asyncInsert(DAO, orders4profit, DAO.TABLENAME);
    }
    public static Orders4profit asyncInsert(Orders4profit orders4profit, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return asyncInsert(DAO, orders4profit, TABLENAME2);
    }
    public static Orders4profit asyncInsert(Orders4profitDAO DAO, Orders4profit orders4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(orders4profit, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        orders4profit.id = n;
        if(cacheModel != NO_CACHE) put(orders4profit, true);
        return orders4profit;
    }
    public static Orders4profit insert2(Orders4profit orders4profit) {
        Orders4profitDAO DAO = DAO();
        return insert2(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit insert2(Orders4profitDAO DAO, Orders4profit orders4profit) {
        return insert2(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit insert2(Orders4profit orders4profit, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return insert2(DAO, orders4profit, TABLENAME2);
    }

    public static Orders4profit insert2(Orders4profitDAO DAO, Orders4profit orders4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(orders4profit, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        orders4profit.id = n;
        if(cacheModel != NO_CACHE) put(orders4profit, true);

        return orders4profit;
    }

    public static Orders4profit asyncInsert2(Orders4profit orders4profit) {
        Orders4profitDAO DAO = DAO();
        return asyncInsert2(DAO, orders4profit, DAO.TABLENAME);
    }
    public static Orders4profit asyncInsert2(Orders4profitDAO DAO, Orders4profit orders4profit) {
        return asyncInsert2(DAO, orders4profit, DAO.TABLENAME);
    }
    public static Orders4profit asyncInsert2(Orders4profit orders4profit, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return asyncInsert2(DAO, orders4profit, TABLENAME2);
    }
    public static Orders4profit asyncInsert2(Orders4profitDAO DAO, Orders4profit orders4profit, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        orders4profit.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(orders4profit, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(orders4profit, true);
        return orders4profit;
    }
    public static int[] insert(List<Orders4profit> orders4profits) {
        Orders4profitDAO DAO = DAO();
        return insert(DAO, orders4profits, DAO.TABLENAME);
    }

    public static int[] insert(Orders4profitDAO DAO, List<Orders4profit> orders4profits) {
        return insert(DAO, orders4profits, DAO.TABLENAME);
    }

    public static int[] insert(List<Orders4profit> orders4profits, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return insert(DAO, orders4profits, TABLENAME2);
    }

    public static int[] insert(Orders4profitDAO DAO, List<Orders4profit> orders4profits, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(orders4profits, TABLENAME2);
            int n = 0;
            for(Orders4profit orders4profit : orders4profits){
                orders4profit.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[orders4profits.size()];
        int n = 0;
        for(Orders4profit orders4profit : orders4profits){
            orders4profit = insert(DAO, orders4profit, TABLENAME2);
            ret[n++] = (orders4profit == null) ? 0 : (int)orders4profit.id;
        }
        return ret;
    }

    public static int delete(Orders4profit orders4profit) {
        int id = orders4profit.id;
        Orders4profitDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Orders4profitDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Orders4profitDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Orders4profitDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Orders4profit orders4profit) {
        int id = orders4profit.id;
        Orders4profitDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Orders4profitDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Orders4profitDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Orders4profitDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Orders4profitDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Orders4profitDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Orders4profitDAO DAO, int[] ids,String TABLENAME2) {
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
        Orders4profitDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Orders4profitDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Orders4profitDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Orders4profit> beans) {
        Orders4profitDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Orders4profit> beans, Orders4profitDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Orders4profit> beans, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Orders4profit> beans, final Orders4profitDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Orders4profit orders4profit : beans){
                int id = orders4profit.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Orders4profit> getAll() {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getAll(Orders4profitDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getAll(String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Orders4profit> getAll(final Orders4profitDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders4profit> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Orders4profit> getNoSortAll() {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getNoSortAll(Orders4profitDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getNoSortAll(String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Orders4profit> getNoSortAll(final Orders4profitDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Orders4profit> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders4profit> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Orders4profit orders4profit = FIXED[i];
                if (orders4profit != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Orders4profit> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Orders4profit> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Orders4profitDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Orders4profitDAO DAO, final String TABLENAME2) {
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
        Orders4profitDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Orders4profitDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Orders4profitDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Orders4profit> getIn(List<Integer> keys) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getIn(List<Integer> keys, Orders4profitDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getIn(List<Integer> keys, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Orders4profit> getIn(final List<Integer> keys, final Orders4profitDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders4profit> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Orders4profit> getNoSortIn(List<Integer> keys) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getNoSortIn(List<Integer> keys, Orders4profitDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders4profit> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Orders4profit> getNoSortIn(final List<Integer> keys, final Orders4profitDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Orders4profit> result = newList();
            for (int key : keys) {
                Orders4profit orders4profit = getByKeyFromMemory(key);
                if( orders4profit == null ) continue;
                result.add(orders4profit);
            }
            return result;
        }
    }

    public static List<Orders4profit> getLast(int num) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Orders4profit> getLast(Orders4profitDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Orders4profit> getLast(int num, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Orders4profit> getLast(final Orders4profitDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Orders4profit> result = newList();
            List<Orders4profit> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Orders4profit last() {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Orders4profit last(Orders4profitDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Orders4profit last(String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Orders4profit last(final Orders4profitDAO DAO, final String TABLENAME2) {
        Orders4profit result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Orders4profitDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Orders4profitDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Orders4profitDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Orders4profit> getGtKey(int id) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Orders4profit> getGtKey(Orders4profitDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Orders4profit> getGtKey(int id, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Orders4profit> getGtKey(final Orders4profitDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders4profit> result = newList();
            List<Orders4profit> orders4profits = getAll();
            for (Orders4profit orders4profit : orders4profits) {
                if(orders4profit.id <= id) continue;
                result.add(orders4profit);
            }
            return result;
        }
    }

    public static Orders4profit getByKey(int id) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Orders4profit> asyncGetByKey(final int id) {
        Future<Orders4profit> f = Async.exec(new Callable<Orders4profit>() {
            public Orders4profit call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Orders4profit getByKey(Orders4profitDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Orders4profit getByKey(int id, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Orders4profit getByKey(final Orders4profitDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Orders4profit getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Orders4profit deleteFromMemory(final int id) {
        Orders4profit orders4profit;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            orders4profit = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(orders4profit);
        } else {
            orders4profit = vars.remove(id);
        }
        if(orders4profit == null) return null;

        int lhubid = orders4profit.getLhubid();
        Set m1 = varsByLhubid.get(lhubid);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByLhubid.remove(lhubid);
        }

        String orderNo = orders4profit.getOrderNo();
        Set m2 = varsByOrderNo.get(orderNo);
        if(m2 != null) {
            m2.remove(id);
            if(m2.isEmpty())
                varsByOrderNo.remove(orderNo);
        }

        int kindid = orders4profit.getKindid();
        Set m3 = varsByKindid.get(kindid);
        if(m3 != null) {
            m3.remove(id);
            if(m3.isEmpty())
                varsByKindid.remove(kindid);
        }

        int custid = orders4profit.getCustid();
        Set m4 = varsByCustid.get(custid);
        if(m4 != null) {
            m4.remove(id);
            if(m4.isEmpty())
                varsByCustid.remove(custid);
        }

        int agentid = orders4profit.getAgentid();
        Set m5 = varsByAgentid.get(agentid);
        if(m5 != null) {
            m5.remove(id);
            if(m5.isEmpty())
                varsByAgentid.remove(agentid);
        }

        return orders4profit;
    }

    public static List<Orders4profit> getByPage(int page, int size) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByPage(Orders4profitDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByPage(int page, int size, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Orders4profit> getByPage(final Orders4profitDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders4profit> result = newList();
            List<Orders4profit> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Orders4profitDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Orders4profitDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByLhubid(Integer lhubid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Orders4profitDAO DAO, Integer lhubid) {
        return countByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static int countByLhubid(Integer lhubid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static int countByLhubid(final Orders4profitDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByLhubid(lhubid, TABLENAME2);
        }
        List<Orders4profit> orders4profits = getByLhubid(DAO, lhubid, TABLENAME2);
        return orders4profits.size();
    }

    public static List<Orders4profit> getByLhubid(Integer lhubid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByLhubid(Orders4profitDAO DAO, Integer lhubid) {
        return getByLhubid(DAO, lhubid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByLhubid(Integer lhubid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLhubid(DAO, lhubid, TABLENAME2);
    }

    public static List<Orders4profit> getByLhubid(final Orders4profitDAO DAO, final Integer lhubid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLhubid(lhubid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Orders4profit> result = newList();
            Set<Integer> m1 = varsByLhubid.get(lhubid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Orders4profit e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByOrderNo(String orderNo) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static int countByOrderNo(Orders4profitDAO DAO, String orderNo) {
        return countByOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static int countByOrderNo(String orderNo, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static int countByOrderNo(final Orders4profitDAO DAO, final String orderNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByOrderNo(orderNo, TABLENAME2);
        }
        List<Orders4profit> orders4profits = getByOrderNo(DAO, orderNo, TABLENAME2);
        return orders4profits.size();
    }

    public static List<Orders4profit> getByOrderNo(String orderNo) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByOrderNo(Orders4profitDAO DAO, String orderNo) {
        return getByOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByOrderNo(String orderNo, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static List<Orders4profit> getByOrderNo(final Orders4profitDAO DAO, final String orderNo,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByOrderNo(orderNo, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Orders4profit> result = newList();
            Set<Integer> m1 = varsByOrderNo.get(orderNo);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Orders4profit e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _orderNo = e.getOrderNo();
                if(!_orderNo.equals(orderNo)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeOrderNo(String orderNo) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static int countLikeOrderNo(Orders4profitDAO DAO, String orderNo) {
        return countLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static int countLikeOrderNo(String orderNo, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static int countLikeOrderNo(final Orders4profitDAO DAO, final String orderNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeOrderNo(orderNo, TABLENAME2);
        }
        List<Orders4profit> orders4profits = getLikeOrderNo(DAO, orderNo, TABLENAME2);
        return orders4profits.size();
    }

    public static List<Orders4profit> getLikeOrderNo(String orderNo) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static List<Orders4profit> getLikeOrderNo(Orders4profitDAO DAO, String orderNo) {
        return getLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static List<Orders4profit> getLikeOrderNo(String orderNo, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static List<Orders4profit> getLikeOrderNo(final Orders4profitDAO DAO, final String orderNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeOrderNo(orderNo, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Orders4profit> result = newList();
            List<Orders4profit> orders4profits = getAll(DAO, TABLENAME2);
            for(Orders4profit e : orders4profits){
                String _var = e.getOrderNo();
                if(_var.indexOf(orderNo) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByKindid(Integer kindid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Orders4profitDAO DAO, Integer kindid) {
        return countByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static int countByKindid(Integer kindid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByKindid(DAO, kindid, TABLENAME2);
    }

    public static int countByKindid(final Orders4profitDAO DAO, final Integer kindid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByKindid(kindid, TABLENAME2);
        }
        List<Orders4profit> orders4profits = getByKindid(DAO, kindid, TABLENAME2);
        return orders4profits.size();
    }

    public static List<Orders4profit> getByKindid(Integer kindid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByKindid(Orders4profitDAO DAO, Integer kindid) {
        return getByKindid(DAO, kindid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByKindid(Integer kindid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKindid(DAO, kindid, TABLENAME2);
    }

    public static List<Orders4profit> getByKindid(final Orders4profitDAO DAO, final Integer kindid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByKindid(kindid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Orders4profit> result = newList();
            Set<Integer> m1 = varsByKindid.get(kindid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Orders4profit e = getByKey(DAO, key, TABLENAME2);
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

    public static int countByCustid(Integer custid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static int countByCustid(Orders4profitDAO DAO, Integer custid) {
        return countByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static int countByCustid(Integer custid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCustid(DAO, custid, TABLENAME2);
    }

    public static int countByCustid(final Orders4profitDAO DAO, final Integer custid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCustid(custid, TABLENAME2);
        }
        List<Orders4profit> orders4profits = getByCustid(DAO, custid, TABLENAME2);
        return orders4profits.size();
    }

    public static List<Orders4profit> getByCustid(Integer custid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByCustid(Orders4profitDAO DAO, Integer custid) {
        return getByCustid(DAO, custid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByCustid(Integer custid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCustid(DAO, custid, TABLENAME2);
    }

    public static List<Orders4profit> getByCustid(final Orders4profitDAO DAO, final Integer custid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCustid(custid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Orders4profit> result = newList();
            Set<Integer> m1 = varsByCustid.get(custid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Orders4profit e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _custid = e.getCustid();
                if(_custid != custid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByAgentid(Integer agentid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static int countByAgentid(Orders4profitDAO DAO, Integer agentid) {
        return countByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static int countByAgentid(Integer agentid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAgentid(DAO, agentid, TABLENAME2);
    }

    public static int countByAgentid(final Orders4profitDAO DAO, final Integer agentid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByAgentid(agentid, TABLENAME2);
        }
        List<Orders4profit> orders4profits = getByAgentid(DAO, agentid, TABLENAME2);
        return orders4profits.size();
    }

    public static List<Orders4profit> getByAgentid(Integer agentid) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByAgentid(Orders4profitDAO DAO, Integer agentid) {
        return getByAgentid(DAO, agentid, DAO.TABLENAME);
    }

    public static List<Orders4profit> getByAgentid(Integer agentid, String TABLENAME2) {
        Orders4profitDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAgentid(DAO, agentid, TABLENAME2);
    }

    public static List<Orders4profit> getByAgentid(final Orders4profitDAO DAO, final Integer agentid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAgentid(agentid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Orders4profit> result = newList();
            Set<Integer> m1 = varsByAgentid.get(agentid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Orders4profit e = getByKey(DAO, key, TABLENAME2);
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

    public static Orders4profit update(Orders4profit orders4profit) {
        Orders4profitDAO DAO = DAO();
        return update(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit update(Orders4profitDAO DAO, Orders4profit orders4profit) {
        return update(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit update(Orders4profit orders4profit, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return update(DAO, orders4profit, TABLENAME2);
    }

    public static Orders4profit update(final Orders4profitDAO DAO, final Orders4profit orders4profit,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(orders4profit, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(orders4profit, TABLENAME2);
            if(n == -1) 
                return orders4profit;
            else if(n <= 0) 
                return null;
        }
        return orders4profit;
    }

    public static Orders4profit asyncUpdate(Orders4profit orders4profit) {
        Orders4profitDAO DAO = DAO();
        return asyncUpdate(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit asyncUpdate(Orders4profitDAO DAO, Orders4profit orders4profit) {
        return asyncUpdate(DAO, orders4profit, DAO.TABLENAME);
    }

    public static Orders4profit asyncUpdate(Orders4profit orders4profit, String TABLENAME2) {
        Orders4profitDAO DAO = DAO();
        return asyncUpdate(DAO, orders4profit, TABLENAME2);
    }

    public static Orders4profit asyncUpdate(final Orders4profitDAO DAO, final Orders4profit orders4profit,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(orders4profit, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(orders4profit, TABLENAME2);
        }
        return orders4profit;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Orders4profitDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Orders4profitDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Orders4profitDAO DAO, final String TABLENAME2) {
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

        List<Orders4profit> orders4profits = getAll();
        for (Orders4profit orders4profit : orders4profits) {
            int n = DAO.insert2(orders4profit, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
