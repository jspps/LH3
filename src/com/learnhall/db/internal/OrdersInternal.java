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

import static com.learnhall.db.bean.Orders.Col;

//learnhall3_design - orders
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class OrdersInternal extends InternalSupport{
    static Log log = LogFactory.getLog(OrdersInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public OrdersInternal(){}

    public static OrdersDAO DAO(){
        return OrdersEntity.OrdersDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Orders[MAX];
    }
    private static Orders[] FIXED = new Orders[MAX];
    public static final Map<Integer, Orders> vars = newSortedMap();
    public static final Map<String, Integer> varsByOrderNo = newSortedMap();
    public static final Map<String, Set<Integer>> varsByExtra_param = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeMakerid = newSortedMap();

    private static final List<Orders> fixedCache = newList();

    public static void put(Orders orders, boolean force){
        if(orders == null || orders.id <= 0) return ;

        int id = orders.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(orders))
                	FIXED[id - 1] = orders;
                if (!fixedCache.contains(orders))
                	fixedCache.add(orders);
            }
        } else {
            vars.put(id, orders);
        }

        { // 单-唯一索引 remove old index orderNo
          Object ov = orders.oldVal(Col.orderNo);
          if(ov != null)
              varsByOrderNo.remove(ov);
          if(ov != null || force){ // put new index
            String orderNo = orders.getOrderNo();
            varsByOrderNo.put(orderNo, id);
          }
        }

        { // 单-非唯一索引 remove old index extra_param
          Object ov = orders.oldVal(Col.extra_param);
          if(ov != null) {
              String _val = (String) ov;
              Set m1 = varsByExtra_param.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByExtra_param.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String extra_param = orders.getExtra_param();
            Set m1 = varsByExtra_param.get(extra_param);
            if(m1 == null){
                m1 = newSortedSet();
                varsByExtra_param.put(extra_param, m1);
            }
            m1.add(id);
          }
        }

        { // type_makeid
          boolean bChanged = orders.inChanged(Col.type, Col.makerid);
          if(bChanged){ // 多-非唯一索引 remove old index
              Object vtype = orders.oldOrNew(Col.type);
              Object vmakerid = orders.oldOrNew(Col.makerid);
              String okey = com.bowlong.lang.PStr.b().a(vtype, "-", vmakerid).e();
              Set m2 = varsByTypeMakerid.get(okey);
              if(m2 != null) {
                  m2.remove(id);
                  if(m2.isEmpty())
                       varsByTypeMakerid.remove(okey);
              }
          }
          if(bChanged || force) { // put new index
              int vtype = orders.getType();
              int vmakerid = orders.getMakerid();
              String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vmakerid).e();
              Set m2 = varsByTypeMakerid.get(vkey);
              if(m2 == null){
                  m2 = newSortedSet();
                  varsByTypeMakerid.put(vkey, m2);
              }
              m2.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByOrderNo.clear();
        varsByExtra_param.clear();
        varsByTypeMakerid.clear();
        FIXED = new Orders[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(OrdersDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(OrdersDAO DAO, String TABLENAME2){
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

    public static void relocate(OrdersDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        OrdersDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(OrdersDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        OrdersDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(OrdersDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        OrdersDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(OrdersDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        OrdersDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(OrdersDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(OrdersDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        OrdersDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(OrdersDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(OrdersDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        OrdersDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(OrdersDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Orders[maxId(DAO)];

        List<Orders> orderss = DAO.selectAll();
        for (Orders orders : orderss) {
            orders.reset();
            put(orders, true);
        }
    }

    public static Map toMap(Orders orders){
        return orders.toMap();
    }

    public static List<Map> toMap(List<Orders> orderss){
        List<Map> ret = new Vector<Map>();
        for (Orders orders : orderss){
            Map e = orders.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Orders> sortZh(List<Orders> orderss, final String key) {
        Collections.sort(orderss, new Comparator<Orders>() {
            public int compare(Orders o1, Orders o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return orderss;
    }

    public static List<Orders> sort(List<Orders> orderss, final String key) {
        Collections.sort(orderss, new Comparator<Orders>() {
            public int compare(Orders o1, Orders o2) {
                return o1.compareTo(o2, key);
            }
        });
        return orderss;
    }

    public static List<Orders> sort(List<Orders> orderss){
        Collections.sort(orderss, new Comparator<Orders>(){
            public int compare(Orders o1, Orders o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return orderss;
    }

    public static List<Orders> sortReverse(List<Orders> orderss){
        Collections.sort(orderss, new Comparator<Orders>(){
            public int compare(Orders o1, Orders o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return orderss;
    }

    public static List<Orders> sortOrderNo(List<Orders> orderss){
        Collections.sort(orderss, new Comparator<Orders>(){
            public int compare(Orders o1, Orders o2) {
                Object v1 = o1.getOrderNo();
                Object v2 = o2.getOrderNo();
                return compareTo(v1, v2);
            }
        });
        return orderss;
    }

    public static List<Orders> sortOrderNoRo(List<Orders> orderss){
        Collections.sort(orderss, new Comparator<Orders>(){
            public int compare(Orders o1, Orders o2) {
                Object v1 = o1.getOrderNo();
                Object v2 = o2.getOrderNo();
                return 0 - compareTo(v1, v2);
            };
        });
        return orderss;
    }

    public static List<Orders> sortExtra_param(List<Orders> orderss){
        Collections.sort(orderss, new Comparator<Orders>(){
            public int compare(Orders o1, Orders o2) {
                Object v1 = o1.getExtra_param();
                Object v2 = o2.getExtra_param();
                return compareTo(v1, v2);
            }
        });
        return orderss;
    }

    public static List<Orders> sortExtra_paramRo(List<Orders> orderss){
        Collections.sort(orderss, new Comparator<Orders>(){
            public int compare(Orders o1, Orders o2) {
                Object v1 = o1.getExtra_param();
                Object v2 = o2.getExtra_param();
                return 0 - compareTo(v1, v2);
            };
        });
        return orderss;
    }

    public static Orders insert(Orders orders) {
        OrdersDAO DAO = DAO();
        return insert(DAO, orders, DAO.TABLENAME);
    }

    public static Orders insert(OrdersDAO DAO, Orders orders) {
        return insert(DAO, orders, DAO.TABLENAME);
    }

    public static Orders insert(Orders orders, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return insert(DAO, orders, TABLENAME2);
    }

    public static Orders insert(OrdersDAO DAO, Orders orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(orders, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        orders.id = n;
        if(cacheModel != NO_CACHE) put(orders, true);

        return orders;
    }

    public static Orders asyncInsert(Orders orders) {
        OrdersDAO DAO = DAO();
        return asyncInsert(DAO, orders, DAO.TABLENAME);
    }
    public static Orders asyncInsert(OrdersDAO DAO, Orders orders) {
        return asyncInsert(DAO, orders, DAO.TABLENAME);
    }
    public static Orders asyncInsert(Orders orders, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return asyncInsert(DAO, orders, TABLENAME2);
    }
    public static Orders asyncInsert(OrdersDAO DAO, Orders orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(orders, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        orders.id = n;
        if(cacheModel != NO_CACHE) put(orders, true);
        return orders;
    }
    public static Orders insert2(Orders orders) {
        OrdersDAO DAO = DAO();
        return insert2(DAO, orders, DAO.TABLENAME);
    }

    public static Orders insert2(OrdersDAO DAO, Orders orders) {
        return insert2(DAO, orders, DAO.TABLENAME);
    }

    public static Orders insert2(Orders orders, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return insert2(DAO, orders, TABLENAME2);
    }

    public static Orders insert2(OrdersDAO DAO, Orders orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(orders, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        orders.id = n;
        if(cacheModel != NO_CACHE) put(orders, true);

        return orders;
    }

    public static Orders asyncInsert2(Orders orders) {
        OrdersDAO DAO = DAO();
        return asyncInsert2(DAO, orders, DAO.TABLENAME);
    }
    public static Orders asyncInsert2(OrdersDAO DAO, Orders orders) {
        return asyncInsert2(DAO, orders, DAO.TABLENAME);
    }
    public static Orders asyncInsert2(Orders orders, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return asyncInsert2(DAO, orders, TABLENAME2);
    }
    public static Orders asyncInsert2(OrdersDAO DAO, Orders orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        orders.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(orders, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(orders, true);
        return orders;
    }
    public static int[] insert(List<Orders> orderss) {
        OrdersDAO DAO = DAO();
        return insert(DAO, orderss, DAO.TABLENAME);
    }

    public static int[] insert(OrdersDAO DAO, List<Orders> orderss) {
        return insert(DAO, orderss, DAO.TABLENAME);
    }

    public static int[] insert(List<Orders> orderss, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return insert(DAO, orderss, TABLENAME2);
    }

    public static int[] insert(OrdersDAO DAO, List<Orders> orderss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(orderss, TABLENAME2);
            int n = 0;
            for(Orders orders : orderss){
                orders.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[orderss.size()];
        int n = 0;
        for(Orders orders : orderss){
            orders = insert(DAO, orders, TABLENAME2);
            ret[n++] = (orders == null) ? 0 : (int)orders.id;
        }
        return ret;
    }

    public static int delete(Orders orders) {
        int id = orders.id;
        OrdersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        OrdersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(OrdersDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(OrdersDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Orders orders) {
        int id = orders.id;
        OrdersDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        OrdersDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(OrdersDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(OrdersDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        OrdersDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(OrdersDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(OrdersDAO DAO, int[] ids,String TABLENAME2) {
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
        OrdersDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, OrdersDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final OrdersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Orders> beans) {
        OrdersDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Orders> beans, OrdersDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Orders> beans, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Orders> beans, final OrdersDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Orders orders : beans){
                int id = orders.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Orders> getAll() {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders> getAll(OrdersDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders> getAll(String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Orders> getAll(final OrdersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Orders> getNoSortAll() {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders> getNoSortAll(OrdersDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Orders> getNoSortAll(String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Orders> getNoSortAll(final OrdersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Orders> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Orders orders = FIXED[i];
                if (orders != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Orders> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Orders> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(OrdersDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final OrdersDAO DAO, final String TABLENAME2) {
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
        OrdersDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(OrdersDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final OrdersDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Orders> getIn(List<Integer> keys) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders> getIn(List<Integer> keys, OrdersDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders> getIn(List<Integer> keys, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Orders> getIn(final List<Integer> keys, final OrdersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Orders> getNoSortIn(List<Integer> keys) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders> getNoSortIn(List<Integer> keys, OrdersDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Orders> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Orders> getNoSortIn(final List<Integer> keys, final OrdersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Orders> result = newList();
            for (int key : keys) {
                Orders orders = getByKeyFromMemory(key);
                if( orders == null ) continue;
                result.add(orders);
            }
            return result;
        }
    }

    public static List<Orders> getLast(int num) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Orders> getLast(OrdersDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Orders> getLast(int num, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Orders> getLast(final OrdersDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Orders> result = newList();
            List<Orders> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Orders last() {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Orders last(OrdersDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Orders last(String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Orders last(final OrdersDAO DAO, final String TABLENAME2) {
        Orders result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        OrdersDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(OrdersDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final OrdersDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Orders> getGtKey(int id) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Orders> getGtKey(OrdersDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Orders> getGtKey(int id, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Orders> getGtKey(final OrdersDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders> result = newList();
            List<Orders> orderss = getAll();
            for (Orders orders : orderss) {
                if(orders.id <= id) continue;
                result.add(orders);
            }
            return result;
        }
    }

    public static Orders getByKey(int id) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Orders> asyncGetByKey(final int id) {
        Future<Orders> f = Async.exec(new Callable<Orders>() {
            public Orders call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Orders getByKey(OrdersDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Orders getByKey(int id, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Orders getByKey(final OrdersDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Orders getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Orders deleteFromMemory(final int id) {
        Orders orders;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            orders = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(orders);
        } else {
            orders = vars.remove(id);
        }
        if(orders == null) return null;

        String orderNo = orders.getOrderNo();
        varsByOrderNo.remove(orderNo);

        String extra_param = orders.getExtra_param();
        Set m1 = varsByExtra_param.get(extra_param);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByExtra_param.remove(extra_param);
        }

        { // type_makeid
            int vtype = orders.getType();
            int vmakerid = orders.getMakerid();
            String vkey = com.bowlong.lang.PStr.b().a(vtype, "-", vmakerid).e();
            Set m2 = varsByTypeMakerid.get(vkey);
            if(m2 != null) { 
                m2.remove(id);
                if(m2.isEmpty())
                    varsByTypeMakerid.remove(vkey);
            }
        }

        return orders;
    }

    public static List<Orders> getByPage(int page, int size) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Orders> getByPage(OrdersDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Orders> getByPage(int page, int size, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Orders> getByPage(final OrdersDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Orders> result = newList();
            List<Orders> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(OrdersDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final OrdersDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Orders getByOrderNo(String orderNo) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static Orders getByOrderNo(OrdersDAO DAO, String orderNo) {
        return getByOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static Orders getByOrderNo(String orderNo, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static Orders getByOrderNo(final OrdersDAO DAO, final String orderNo,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByOrderNo(orderNo, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByOrderNo.get(orderNo);
            if(id == null) return null;
            Orders result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getOrderNo().equals(orderNo)){
                varsByOrderNo.remove(orderNo);
                return null;
            }
            return result;
        }
    }

    public static int countLikeOrderNo(String orderNo) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static int countLikeOrderNo(OrdersDAO DAO, String orderNo) {
        return countLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static int countLikeOrderNo(String orderNo, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static int countLikeOrderNo(final OrdersDAO DAO, final String orderNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeOrderNo(orderNo, TABLENAME2);
        }
        List<Orders> orderss = getLikeOrderNo(DAO, orderNo, TABLENAME2);
        return orderss.size();
    }

    public static List<Orders> getLikeOrderNo(String orderNo) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static List<Orders> getLikeOrderNo(OrdersDAO DAO, String orderNo) {
        return getLikeOrderNo(DAO, orderNo, DAO.TABLENAME);
    }

    public static List<Orders> getLikeOrderNo(String orderNo, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeOrderNo(DAO, orderNo, TABLENAME2);
    }

    public static List<Orders> getLikeOrderNo(final OrdersDAO DAO, final String orderNo,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeOrderNo(orderNo, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Orders> result = newList();
            List<Orders> orderss = getAll(DAO, TABLENAME2);
            for(Orders e : orderss){
                String _var = e.getOrderNo();
                if(_var.indexOf(orderNo) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByExtra_param(String extra_param) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static int countByExtra_param(OrdersDAO DAO, String extra_param) {
        return countByExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static int countByExtra_param(String extra_param, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByExtra_param(DAO, extra_param, TABLENAME2);
    }

    public static int countByExtra_param(final OrdersDAO DAO, final String extra_param,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByExtra_param(extra_param, TABLENAME2);
        }
        List<Orders> orderss = getByExtra_param(DAO, extra_param, TABLENAME2);
        return orderss.size();
    }

    public static List<Orders> getByExtra_param(String extra_param) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static List<Orders> getByExtra_param(OrdersDAO DAO, String extra_param) {
        return getByExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static List<Orders> getByExtra_param(String extra_param, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByExtra_param(DAO, extra_param, TABLENAME2);
    }

    public static List<Orders> getByExtra_param(final OrdersDAO DAO, final String extra_param,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByExtra_param(extra_param, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Orders> result = newList();
            Set<Integer> m1 = varsByExtra_param.get(extra_param);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Orders e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _extra_param = e.getExtra_param();
                if(!_extra_param.equals(extra_param)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeExtra_param(String extra_param) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static int countLikeExtra_param(OrdersDAO DAO, String extra_param) {
        return countLikeExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static int countLikeExtra_param(String extra_param, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeExtra_param(DAO, extra_param, TABLENAME2);
    }

    public static int countLikeExtra_param(final OrdersDAO DAO, final String extra_param,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeExtra_param(extra_param, TABLENAME2);
        }
        List<Orders> orderss = getLikeExtra_param(DAO, extra_param, TABLENAME2);
        return orderss.size();
    }

    public static List<Orders> getLikeExtra_param(String extra_param) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static List<Orders> getLikeExtra_param(OrdersDAO DAO, String extra_param) {
        return getLikeExtra_param(DAO, extra_param, DAO.TABLENAME);
    }

    public static List<Orders> getLikeExtra_param(String extra_param, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeExtra_param(DAO, extra_param, TABLENAME2);
    }

    public static List<Orders> getLikeExtra_param(final OrdersDAO DAO, final String extra_param,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeExtra_param(extra_param, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Orders> result = newList();
            List<Orders> orderss = getAll(DAO, TABLENAME2);
            for(Orders e : orderss){
                String _var = e.getExtra_param();
                if(_var.indexOf(extra_param) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByTypeMakerid(Integer type, Integer makerid) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static int countByTypeMakerid(OrdersDAO DAO, Integer type, Integer makerid) {
        return countByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static int countByTypeMakerid(Integer type, Integer makerid, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeMakerid(DAO, type, makerid, TABLENAME2);
    }

    public static int countByTypeMakerid(final OrdersDAO DAO, Integer type, Integer makerid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeMakerid(type, makerid, TABLENAME2);
        }
        List<Orders> orderss = getByTypeMakerid(type, makerid, TABLENAME2);
        return orderss.size();
    }

    public static List<Orders> getByTypeMakerid(Integer type, Integer makerid) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static List<Orders> getByTypeMakerid(OrdersDAO DAO, Integer type, Integer makerid) {
        return getByTypeMakerid(DAO, type, makerid, DAO.TABLENAME);
    }

    public static List<Orders> getByTypeMakerid(Integer type, Integer makerid, String TABLENAME2) {
        OrdersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeMakerid(DAO, type, makerid, TABLENAME2);
    }

    public static List<Orders> getByTypeMakerid(final OrdersDAO DAO, Integer type, Integer makerid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeMakerid(type, makerid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Orders> result = newList();
            String vkey = type+"-"+makerid;
            Set<Integer> m1 = varsByTypeMakerid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Orders e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                int _makerid = e.getMakerid();
                String _key = com.bowlong.lang.PStr.b().a(_type, "-", _makerid).e();
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Orders update(Orders orders) {
        OrdersDAO DAO = DAO();
        return update(DAO, orders, DAO.TABLENAME);
    }

    public static Orders update(OrdersDAO DAO, Orders orders) {
        return update(DAO, orders, DAO.TABLENAME);
    }

    public static Orders update(Orders orders, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return update(DAO, orders, TABLENAME2);
    }

    public static Orders update(final OrdersDAO DAO, final Orders orders,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(orders, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(orders, TABLENAME2);
            if(n == -1) 
                return orders;
            else if(n <= 0) 
                return null;
        }
        return orders;
    }

    public static Orders asyncUpdate(Orders orders) {
        OrdersDAO DAO = DAO();
        return asyncUpdate(DAO, orders, DAO.TABLENAME);
    }

    public static Orders asyncUpdate(OrdersDAO DAO, Orders orders) {
        return asyncUpdate(DAO, orders, DAO.TABLENAME);
    }

    public static Orders asyncUpdate(Orders orders, String TABLENAME2) {
        OrdersDAO DAO = DAO();
        return asyncUpdate(DAO, orders, TABLENAME2);
    }

    public static Orders asyncUpdate(final OrdersDAO DAO, final Orders orders,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(orders, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(orders, TABLENAME2);
        }
        return orders;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        OrdersDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(OrdersDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final OrdersDAO DAO, final String TABLENAME2) {
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

        List<Orders> orderss = getAll();
        for (Orders orders : orderss) {
            int n = DAO.insert2(orders, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
