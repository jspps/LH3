package com.learnhall.db.bean;

import java.util.*;
import java.sql.*;
import java.util.concurrent.*;
import com.bowlong.io.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.bowlong.lang.*;
import com.bowlong.util.*;
import com.bowlong.json.MyJson;
import com.learnhall.db.entity.*;

//learnhall3_design - account
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Account extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1845666721; // com.learnhall.db.bean.Account

    public static String TABLENAME = "account";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String lgid = "lgid"; public static final String phone = "phone"; public static final String email = "email"; public static final String lgpwd = "lgpwd"; public static final String type = "type"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime";  }
    public static final class CEn { public static final String id = "id"; public static final String lgid = "lgid"; public static final String phone = "phone"; public static final String email = "email"; public static final String lgpwd = "lgpwd"; public static final String type = "type"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime";  }
    public static final String[] carrays ={"id", "lgid", "phone", "email", "lgpwd", "type", "status", "createtime", "lasttime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "INT", "INT", "DATETIME", "DATETIME"};


    public int id;
    public String lgid;
    public String phone;
    public String email;
    public String lgpwd;
    public int type;
    public int status;
    public java.util.Date createtime;
    public java.util.Date lasttime;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return id;
    }

    public static String _key(int id) {
        return PStr.b(TABLENAME).a("-").e(id);
    }

    public int getId(){
        return id;
    }

    public Account setId(int id){
        this.id = id;
        return this;
    }

    public String getLgid(){
        return lgid;
    }

    public Account setLgid(String lgid){
        String _old = this.lgid;
        this.lgid = lgid;
        changeIt(Col.lgid, _old, lgid);
        return this;
    }

    public String getPhone(){
        return phone;
    }

    public Account setPhone(String phone){
        String _old = this.phone;
        this.phone = phone;
        changeIt(Col.phone, _old, phone);
        return this;
    }

    public String getEmail(){
        return email;
    }

    public Account setEmail(String email){
        String _old = this.email;
        this.email = email;
        changeIt(Col.email, _old, email);
        return this;
    }

    public String getLgpwd(){
        return lgpwd;
    }

    public Account setLgpwd(String lgpwd){
        String _old = this.lgpwd;
        this.lgpwd = lgpwd;
        changeIt(Col.lgpwd, _old, lgpwd);
        return this;
    }

    public int getType(){
        return type;
    }

    public Account setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Account changeType(int type){
        return setType(this.type + type);
    }

    public Account changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Account changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Account changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Account setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Account changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Account changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Account changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Account changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Account setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Account setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public int compareTo(Account v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Account v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Account newAccount(Integer id, String lgid, String phone, String email, String lgpwd, Integer type, Integer status, java.util.Date createtime, java.util.Date lasttime) {
        Account result = new Account();
        result.id = id;
        result.lgid = lgid;
        result.phone = phone;
        result.email = email;
        result.lgpwd = lgpwd;
        result.type = type;
        result.status = status;
        result.createtime = createtime;
        result.lasttime = lasttime;
        return result;
    }

    public static Account newAccount(Account account) {
        Account result = new Account();
        result.id = account.id;
        result.lgid = account.lgid;
        result.phone = account.phone;
        result.email = account.email;
        result.lgpwd = account.lgpwd;
        result.type = account.type;
        result.status = account.status;
        result.createtime = account.createtime;
        result.lasttime = account.lasttime;
        return result;
    }

    public Account createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAccount(){
        Account account = null; // account
        { // new and insert Account (account)
            int id = 0; 	// id
            String lgid = ""; 	// lgid
            String phone = ""; 	// phone
            String email = ""; 	// email
            String lgpwd = ""; 	// lgpwd
            int type = 0; 	// type
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            Date lasttime = new Date(); 	// lasttime
            account = Account.newAccount(id, lgid, phone, email, lgpwd, type, status, createtime, lasttime);
        }
        account = account.insert();

        int id = account.getId(); 	// id
        String lgid = account.getLgid(); 	// lgid
        String phone = account.getPhone(); 	// phone
        String email = account.getEmail(); 	// email
        String lgpwd = account.getLgpwd(); 	// lgpwd
        int type = account.getType(); 	// type
        int status = account.getStatus(); 	// status
        Date createtime = account.getCreatetime(); 	// createtime
        Date lasttime = account.getLasttime(); 	// lasttime
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.id:
            return id;
        case CEn.type:
            return type;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Account setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Account setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.type:
            return setType(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Account changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Account changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.type:
            return changeType(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Account changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Account changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        }
        return 0;
    }

    public Account setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Account setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        }
        return this;
    }

    public ListForInt valueZhListForInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueListForInt(fieldEn);
    }

    public ListForInt valueListForInt(String fieldEn){
        String str = valueStr(fieldEn);
        return ListForInt.create(str);
    }

    public String valueZhStr(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueStr(fieldEn);
    }

    public String valueStr(String fieldEn){
        switch(fieldEn){
        case CEn.lgid: 
            return lgid;
        case CEn.phone: 
            return phone;
        case CEn.email: 
            return email;
        case CEn.lgpwd: 
            return lgpwd;
        }
        return "";
    }

    @Override
    public <T> T vZh(String fieldZh) {
        return (T) valueZh(fieldZh);
    }

    public Object valueZh(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return value(fieldEn);
    }

    public <T> T v(String fieldEn){
        return (T) value(fieldEn);
    }

    public Object value(String fieldEn){
        switch(fieldEn){
        case CEn.id:
            return id;
        case CEn.lgid:
            return lgid;
        case CEn.phone:
            return phone;
        case CEn.email:
            return email;
        case CEn.lgpwd:
            return lgpwd;
        case CEn.type:
            return type;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        case CEn.lasttime:
            return lasttime;
        }
        return null;
    }

    public Account setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Account setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Account setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Account setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.lgid:
            return setLgid(value2);
        case CEn.phone:
            return setPhone(value2);
        case CEn.email:
            return setEmail(value2);
        case CEn.lgpwd:
            return setLgpwd(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Account setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Account setJson(String fieldEn, Object o2) {
        try {
            String value2 = MyJson.toJSONString(o2);
            return setStr(fieldEn, value2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Map toMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Account");
        result.put("id", id);
        result.put("lgid", lgid);
        result.put("phone", phone);
        result.put("email", email);
        result.put("lgpwd", lgpwd);
        result.put("type", type);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("lgid", lgid);
        result.put("phone", phone);
        result.put("email", email);
        result.put("lgpwd", lgpwd);
        result.put("type", type);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Account");
        result.put("id", id);
        result.put("lgid", lgid);
        result.put("phone", phone);
        result.put("email", email);
        result.put("lgpwd", lgpwd);
        result.put("type", type);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        return result;
    }

    public Account mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String lgid = MapEx.getString(e, "lgid");
        String phone = MapEx.getString(e, "phone");
        String email = MapEx.getString(e, "email");
        String lgpwd = MapEx.getString(e, "lgpwd");
        Integer type = MapEx.getInt(e, "type");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(lgid == null) lgid = "";
        if(phone == null) phone = "";
        if(email == null) email = "";
        if(lgpwd == null) lgpwd = "";
        if(type == null) type = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();

        setId(id);
        setLgid(lgid);
        setPhone(phone);
        setEmail(email);
        setLgpwd(lgpwd);
        setType(type);
        setStatus(status);
        setCreatetime(createtime);
        setLasttime(lasttime);

        return this;
    }

    public static final Account mapTo(Map e){
        Account result = new Account();

        Integer id = MapEx.getInt(e, "id");
        String lgid = MapEx.getString(e, "lgid");
        String phone = MapEx.getString(e, "phone");
        String email = MapEx.getString(e, "email");
        String lgpwd = MapEx.getString(e, "lgpwd");
        Integer type = MapEx.getInt(e, "type");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(lgid == null) lgid = "";
        if(phone == null) phone = "";
        if(email == null) email = "";
        if(lgpwd == null) lgpwd = "";
        if(type == null) type = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.lgid = lgid;
        result.phone = phone;
        result.email = email;
        result.lgpwd = lgpwd;
        result.type = type;
        result.status = status;
        result.createtime = createtime;
        result.lasttime = lasttime;

        return result;
    }

    public static final Account originalTo(Map e){
        Account result = new Account();

        Integer id = MapEx.getInt(e, "id");
        String lgid = MapEx.getString(e, "lgid");
        String phone = MapEx.getString(e, "phone");
        String email = MapEx.getString(e, "email");
        String lgpwd = MapEx.getString(e, "lgpwd");
        Integer type = MapEx.getInt(e, "type");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(lgid == null) lgid = "";
        if(phone == null) phone = "";
        if(email == null) email = "";
        if(lgpwd == null) lgpwd = "";
        if(type == null) type = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.lgid = lgid;
        result.phone = phone;
        result.email = email;
        result.lgpwd = lgpwd;
        result.type = type;
        result.status = status;
        result.createtime = createtime;
        result.lasttime = lasttime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Account createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 9);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "lgid", lgid);
            writeMapEntry(out, "phone", phone);
            writeMapEntry(out, "email", email);
            writeMapEntry(out, "lgpwd", lgpwd);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "lasttime", lasttime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Account createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(id);
        return s.hashCode();
    }
    public final Aduser getAduserFkAccountid() { // aduser - accountid
        return AduserEntity.getByAccountid(id);
    }

    public final int countAduserFkAccountid() { // aduser - accountid 
        return getAduserFkAccountid() == null ? 0 : 1;
    }

    public final Agent getAgentFkAccountid() { // agent - accountid
        return AgentEntity.getByAccountid(id);
    }

    public final int countAgentFkAccountid() { // agent - accountid 
        return getAgentFkAccountid() == null ? 0 : 1;
    }

    public final Customer getCustomerFkAccountid() { // customer - accountid
        return CustomerEntity.getByAccountid(id);
    }

    public final int countCustomerFkAccountid() { // customer - accountid 
        return getCustomerFkAccountid() == null ? 0 : 1;
    }

    public final Learnhub getLearnhubFkAccountid() { // learnhub - accountid
        return LearnhubEntity.getByAccountid(id);
    }

    public final int countLearnhubFkAccountid() { // learnhub - accountid 
        return getLearnhubFkAccountid() == null ? 0 : 1;
    }

    public static final Account loadByKey(int id) {
        return AccountEntity.getByKey(id);
    }

    public static final Future<Account> asyncByKey(int id) {
        return AccountEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Account insert() {
        Account result = AccountEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Account asyncInsert() {
        Account result = AccountEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Account insert2() {
        return AccountEntity.insert2(this);
    }

    public final Account asyncInsert2() {
        Account result = AccountEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Account update() {
        return AccountEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        AccountEntity.asyncUpdate( this );
        return true;
    }

    public final Account insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AccountEntity.delete(id);
    }

    public final void asyncDelete() {
        AccountEntity.asyncDelete(id);
    }

    @Override
    public boolean isThis(String str) {
        return this.getClass().getName().equals(str);
    }

    @Override
    public boolean isThis(Map originalMap) {
        String str = MapEx.getString(originalMap, "_CLASSNAME");
        return isThis(str);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Account clone2() {
        try{
            return (Account) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
