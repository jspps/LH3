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

import static com.learnhall.db.bean.Account.Col;

//learnhall3_design - account
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AccountInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AccountInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AccountInternal(){}

    public static AccountDAO DAO(){
        return AccountEntity.AccountDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Account[MAX];
    }
    private static Account[] FIXED = new Account[MAX];
    public static final Map<Integer, Account> vars = newSortedMap();
    public static final Map<String, Integer> varsByPhone = newSortedMap();
    public static final Map<String, Integer> varsByEmail = newSortedMap();
    public static final Map<String, Integer> varsByLgid = newSortedMap();

    private static final List<Account> fixedCache = newList();

    public static void put(Account account, boolean force){
        if(account == null || account.id <= 0) return ;

        int id = account.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(account))
                	FIXED[id - 1] = account;
                if (!fixedCache.contains(account))
                	fixedCache.add(account);
            }
        } else {
            vars.put(id, account);
        }

        { // 单-唯一索引 remove old index phone
          Object ov = account.oldVal(Col.phone);
          if(ov != null)
              varsByPhone.remove(ov);
          if(ov != null || force){ // put new index
            String phone = account.getPhone();
            varsByPhone.put(phone, id);
          }
        }

        { // 单-唯一索引 remove old index email
          Object ov = account.oldVal(Col.email);
          if(ov != null)
              varsByEmail.remove(ov);
          if(ov != null || force){ // put new index
            String email = account.getEmail();
            varsByEmail.put(email, id);
          }
        }

        { // 单-唯一索引 remove old index lgid
          Object ov = account.oldVal(Col.lgid);
          if(ov != null)
              varsByLgid.remove(ov);
          if(ov != null || force){ // put new index
            String lgid = account.getLgid();
            varsByLgid.put(lgid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByPhone.clear();
        varsByEmail.clear();
        varsByLgid.clear();
        FIXED = new Account[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AccountDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AccountDAO DAO, String TABLENAME2){
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

    public static void relocate(AccountDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AccountDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AccountDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AccountDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AccountDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AccountDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AccountDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AccountDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AccountDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AccountDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AccountDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AccountDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AccountDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AccountDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AccountDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Account[maxId(DAO)];

        List<Account> accounts = DAO.selectAll();
        for (Account account : accounts) {
            account.reset();
            put(account, true);
        }
    }

    public static Map toMap(Account account){
        return account.toMap();
    }

    public static List<Map> toMap(List<Account> accounts){
        List<Map> ret = new Vector<Map>();
        for (Account account : accounts){
            Map e = account.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Account> sortZh(List<Account> accounts, final String key) {
        Collections.sort(accounts, new Comparator<Account>() {
            public int compare(Account o1, Account o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return accounts;
    }

    public static List<Account> sort(List<Account> accounts, final String key) {
        Collections.sort(accounts, new Comparator<Account>() {
            public int compare(Account o1, Account o2) {
                return o1.compareTo(o2, key);
            }
        });
        return accounts;
    }

    public static List<Account> sort(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return accounts;
    }

    public static List<Account> sortReverse(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return accounts;
    }

    public static List<Account> sortPhone(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.getPhone();
                Object v2 = o2.getPhone();
                return compareTo(v1, v2);
            }
        });
        return accounts;
    }

    public static List<Account> sortPhoneRo(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.getPhone();
                Object v2 = o2.getPhone();
                return 0 - compareTo(v1, v2);
            };
        });
        return accounts;
    }

    public static List<Account> sortEmail(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.getEmail();
                Object v2 = o2.getEmail();
                return compareTo(v1, v2);
            }
        });
        return accounts;
    }

    public static List<Account> sortEmailRo(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.getEmail();
                Object v2 = o2.getEmail();
                return 0 - compareTo(v1, v2);
            };
        });
        return accounts;
    }

    public static List<Account> sortLgid(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.getLgid();
                Object v2 = o2.getLgid();
                return compareTo(v1, v2);
            }
        });
        return accounts;
    }

    public static List<Account> sortLgidRo(List<Account> accounts){
        Collections.sort(accounts, new Comparator<Account>(){
            public int compare(Account o1, Account o2) {
                Object v1 = o1.getLgid();
                Object v2 = o2.getLgid();
                return 0 - compareTo(v1, v2);
            };
        });
        return accounts;
    }

    public static Account insert(Account account) {
        AccountDAO DAO = DAO();
        return insert(DAO, account, DAO.TABLENAME);
    }

    public static Account insert(AccountDAO DAO, Account account) {
        return insert(DAO, account, DAO.TABLENAME);
    }

    public static Account insert(Account account, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return insert(DAO, account, TABLENAME2);
    }

    public static Account insert(AccountDAO DAO, Account account, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(account, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        account.id = n;
        if(cacheModel != NO_CACHE) put(account, true);

        return account;
    }

    public static Account asyncInsert(Account account) {
        AccountDAO DAO = DAO();
        return asyncInsert(DAO, account, DAO.TABLENAME);
    }
    public static Account asyncInsert(AccountDAO DAO, Account account) {
        return asyncInsert(DAO, account, DAO.TABLENAME);
    }
    public static Account asyncInsert(Account account, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return asyncInsert(DAO, account, TABLENAME2);
    }
    public static Account asyncInsert(AccountDAO DAO, Account account, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(account, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        account.id = n;
        if(cacheModel != NO_CACHE) put(account, true);
        return account;
    }
    public static Account insert2(Account account) {
        AccountDAO DAO = DAO();
        return insert2(DAO, account, DAO.TABLENAME);
    }

    public static Account insert2(AccountDAO DAO, Account account) {
        return insert2(DAO, account, DAO.TABLENAME);
    }

    public static Account insert2(Account account, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return insert2(DAO, account, TABLENAME2);
    }

    public static Account insert2(AccountDAO DAO, Account account, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(account, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        account.id = n;
        if(cacheModel != NO_CACHE) put(account, true);

        return account;
    }

    public static Account asyncInsert2(Account account) {
        AccountDAO DAO = DAO();
        return asyncInsert2(DAO, account, DAO.TABLENAME);
    }
    public static Account asyncInsert2(AccountDAO DAO, Account account) {
        return asyncInsert2(DAO, account, DAO.TABLENAME);
    }
    public static Account asyncInsert2(Account account, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return asyncInsert2(DAO, account, TABLENAME2);
    }
    public static Account asyncInsert2(AccountDAO DAO, Account account, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        account.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(account, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(account, true);
        return account;
    }
    public static int[] insert(List<Account> accounts) {
        AccountDAO DAO = DAO();
        return insert(DAO, accounts, DAO.TABLENAME);
    }

    public static int[] insert(AccountDAO DAO, List<Account> accounts) {
        return insert(DAO, accounts, DAO.TABLENAME);
    }

    public static int[] insert(List<Account> accounts, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return insert(DAO, accounts, TABLENAME2);
    }

    public static int[] insert(AccountDAO DAO, List<Account> accounts, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(accounts, TABLENAME2);
            int n = 0;
            for(Account account : accounts){
                account.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[accounts.size()];
        int n = 0;
        for(Account account : accounts){
            account = insert(DAO, account, TABLENAME2);
            ret[n++] = (account == null) ? 0 : (int)account.id;
        }
        return ret;
    }

    public static int delete(Account account) {
        int id = account.id;
        AccountDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        AccountDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(AccountDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(AccountDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Account account) {
        int id = account.id;
        AccountDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        AccountDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(AccountDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        AccountDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(AccountDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        AccountDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(AccountDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        AccountDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(AccountDAO DAO, int[] ids,String TABLENAME2) {
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
        AccountDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AccountDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AccountDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Account> beans) {
        AccountDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Account> beans, AccountDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Account> beans, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Account> beans, final AccountDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Account account : beans){
                int id = account.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Account> getAll() {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Account> getAll(AccountDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Account> getAll(String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Account> getAll(final AccountDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Account> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Account> getNoSortAll() {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Account> getNoSortAll(AccountDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Account> getNoSortAll(String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Account> getNoSortAll(final AccountDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Account> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Account> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Account account = FIXED[i];
                if (account != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Account> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Account> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AccountDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AccountDAO DAO, final String TABLENAME2) {
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
        AccountDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AccountDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AccountDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AccountDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Account> getIn(List<Integer> keys) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Account> getIn(List<Integer> keys, AccountDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Account> getIn(List<Integer> keys, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Account> getIn(final List<Integer> keys, final AccountDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Account> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Account> getNoSortIn(List<Integer> keys) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Account> getNoSortIn(List<Integer> keys, AccountDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Account> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Account> getNoSortIn(final List<Integer> keys, final AccountDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Account> result = newList();
            for (int key : keys) {
                Account account = getByKeyFromMemory(key);
                if( account == null ) continue;
                result.add(account);
            }
            return result;
        }
    }

    public static List<Account> getLast(int num) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Account> getLast(AccountDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Account> getLast(int num, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Account> getLast(final AccountDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Account> result = newList();
            List<Account> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Account last() {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Account last(AccountDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Account last(String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Account last(final AccountDAO DAO, final String TABLENAME2) {
        Account result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AccountDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AccountDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AccountDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AccountDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Account> getGtKey(int id) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Account> getGtKey(AccountDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Account> getGtKey(int id, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Account> getGtKey(final AccountDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Account> result = newList();
            List<Account> accounts = getAll();
            for (Account account : accounts) {
                if(account.id <= id) continue;
                result.add(account);
            }
            return result;
        }
    }

    public static Account getByKey(int id) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Account> asyncGetByKey(final int id) {
        Future<Account> f = Async.exec(new Callable<Account>() {
            public Account call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Account getByKey(AccountDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Account getByKey(int id, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Account getByKey(final AccountDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Account getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Account deleteFromMemory(final int id) {
        Account account;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            account = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(account);
        } else {
            account = vars.remove(id);
        }
        if(account == null) return null;

        String phone = account.getPhone();
        varsByPhone.remove(phone);

        String email = account.getEmail();
        varsByEmail.remove(email);

        String lgid = account.getLgid();
        varsByLgid.remove(lgid);

        return account;
    }

    public static List<Account> getByPage(int page, int size) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Account> getByPage(AccountDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Account> getByPage(int page, int size, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Account> getByPage(final AccountDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Account> result = newList();
            List<Account> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AccountDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AccountDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Account getByPhone(String phone) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPhone(DAO, phone, DAO.TABLENAME);
    }

    public static Account getByPhone(AccountDAO DAO, String phone) {
        return getByPhone(DAO, phone, DAO.TABLENAME);
    }

    public static Account getByPhone(String phone, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPhone(DAO, phone, TABLENAME2);
    }

    public static Account getByPhone(final AccountDAO DAO, final String phone,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPhone(phone, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByPhone.get(phone);
            if(id == null) return null;
            Account result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getPhone().equals(phone)){
                varsByPhone.remove(phone);
                return null;
            }
            return result;
        }
    }

    public static int countLikePhone(String phone) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static int countLikePhone(AccountDAO DAO, String phone) {
        return countLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static int countLikePhone(String phone, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePhone(DAO, phone, TABLENAME2);
    }

    public static int countLikePhone(final AccountDAO DAO, final String phone,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikePhone(phone, TABLENAME2);
        }
        List<Account> accounts = getLikePhone(DAO, phone, TABLENAME2);
        return accounts.size();
    }

    public static List<Account> getLikePhone(String phone) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static List<Account> getLikePhone(AccountDAO DAO, String phone) {
        return getLikePhone(DAO, phone, DAO.TABLENAME);
    }

    public static List<Account> getLikePhone(String phone, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePhone(DAO, phone, TABLENAME2);
    }

    public static List<Account> getLikePhone(final AccountDAO DAO, final String phone,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikePhone(phone, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Account> result = newList();
            List<Account> accounts = getAll(DAO, TABLENAME2);
            for(Account e : accounts){
                String _var = e.getPhone();
                if(_var.indexOf(phone) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Account getByEmail(String email) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByEmail(DAO, email, DAO.TABLENAME);
    }

    public static Account getByEmail(AccountDAO DAO, String email) {
        return getByEmail(DAO, email, DAO.TABLENAME);
    }

    public static Account getByEmail(String email, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByEmail(DAO, email, TABLENAME2);
    }

    public static Account getByEmail(final AccountDAO DAO, final String email,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByEmail(email, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByEmail.get(email);
            if(id == null) return null;
            Account result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getEmail().equals(email)){
                varsByEmail.remove(email);
                return null;
            }
            return result;
        }
    }

    public static int countLikeEmail(String email) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeEmail(DAO, email, DAO.TABLENAME);
    }

    public static int countLikeEmail(AccountDAO DAO, String email) {
        return countLikeEmail(DAO, email, DAO.TABLENAME);
    }

    public static int countLikeEmail(String email, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeEmail(DAO, email, TABLENAME2);
    }

    public static int countLikeEmail(final AccountDAO DAO, final String email,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeEmail(email, TABLENAME2);
        }
        List<Account> accounts = getLikeEmail(DAO, email, TABLENAME2);
        return accounts.size();
    }

    public static List<Account> getLikeEmail(String email) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeEmail(DAO, email, DAO.TABLENAME);
    }

    public static List<Account> getLikeEmail(AccountDAO DAO, String email) {
        return getLikeEmail(DAO, email, DAO.TABLENAME);
    }

    public static List<Account> getLikeEmail(String email, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeEmail(DAO, email, TABLENAME2);
    }

    public static List<Account> getLikeEmail(final AccountDAO DAO, final String email,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeEmail(email, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Account> result = newList();
            List<Account> accounts = getAll(DAO, TABLENAME2);
            for(Account e : accounts){
                String _var = e.getEmail();
                if(_var.indexOf(email) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Account getByLgid(String lgid) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLgid(DAO, lgid, DAO.TABLENAME);
    }

    public static Account getByLgid(AccountDAO DAO, String lgid) {
        return getByLgid(DAO, lgid, DAO.TABLENAME);
    }

    public static Account getByLgid(String lgid, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLgid(DAO, lgid, TABLENAME2);
    }

    public static Account getByLgid(final AccountDAO DAO, final String lgid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLgid(lgid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByLgid.get(lgid);
            if(id == null) return null;
            Account result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getLgid().equals(lgid)){
                varsByLgid.remove(lgid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeLgid(String lgid) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeLgid(DAO, lgid, DAO.TABLENAME);
    }

    public static int countLikeLgid(AccountDAO DAO, String lgid) {
        return countLikeLgid(DAO, lgid, DAO.TABLENAME);
    }

    public static int countLikeLgid(String lgid, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeLgid(DAO, lgid, TABLENAME2);
    }

    public static int countLikeLgid(final AccountDAO DAO, final String lgid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeLgid(lgid, TABLENAME2);
        }
        List<Account> accounts = getLikeLgid(DAO, lgid, TABLENAME2);
        return accounts.size();
    }

    public static List<Account> getLikeLgid(String lgid) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeLgid(DAO, lgid, DAO.TABLENAME);
    }

    public static List<Account> getLikeLgid(AccountDAO DAO, String lgid) {
        return getLikeLgid(DAO, lgid, DAO.TABLENAME);
    }

    public static List<Account> getLikeLgid(String lgid, String TABLENAME2) {
        AccountDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeLgid(DAO, lgid, TABLENAME2);
    }

    public static List<Account> getLikeLgid(final AccountDAO DAO, final String lgid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeLgid(lgid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Account> result = newList();
            List<Account> accounts = getAll(DAO, TABLENAME2);
            for(Account e : accounts){
                String _var = e.getLgid();
                if(_var.indexOf(lgid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Account update(Account account) {
        AccountDAO DAO = DAO();
        return update(DAO, account, DAO.TABLENAME);
    }

    public static Account update(AccountDAO DAO, Account account) {
        return update(DAO, account, DAO.TABLENAME);
    }

    public static Account update(Account account, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return update(DAO, account, TABLENAME2);
    }

    public static Account update(final AccountDAO DAO, final Account account,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(account, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(account, TABLENAME2);
            if(n == -1) 
                return account;
            else if(n <= 0) 
                return null;
        }
        return account;
    }

    public static Account asyncUpdate(Account account) {
        AccountDAO DAO = DAO();
        return asyncUpdate(DAO, account, DAO.TABLENAME);
    }

    public static Account asyncUpdate(AccountDAO DAO, Account account) {
        return asyncUpdate(DAO, account, DAO.TABLENAME);
    }

    public static Account asyncUpdate(Account account, String TABLENAME2) {
        AccountDAO DAO = DAO();
        return asyncUpdate(DAO, account, TABLENAME2);
    }

    public static Account asyncUpdate(final AccountDAO DAO, final Account account,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(account, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(account, TABLENAME2);
        }
        return account;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AccountDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AccountDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AccountDAO DAO, final String TABLENAME2) {
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

        List<Account> accounts = getAll();
        for (Account account : accounts) {
            int n = DAO.insert2(account, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
