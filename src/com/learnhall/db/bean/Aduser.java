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

//learnhall3_design - aduser
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Aduser extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1463019910; // com.learnhall.db.bean.Aduser

    public static String TABLENAME = "aduser";

    public static final String primary = "uid";

    public static final class Col { public static final String uid = "uid"; public static final String accountid = "accountid"; public static final String uname = "uname"; public static final String powerids = "powerids"; public static final String remarks = "remarks";  }
    public static final class CEn { public static final String uid = "uid"; public static final String accountid = "accountid"; public static final String uname = "uname"; public static final String powerids = "powerids"; public static final String remarks = "remarks";  }
    public static final String[] carrays ={"uid", "accountid", "uname", "powerids", "remarks"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "VARCHAR", "VARCHAR"};


    public int uid;
    public int accountid;
    public String uname;
    public String powerids;
    public String remarks;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return uid;
    }

    public static String _key(int uid) {
        return PStr.b(TABLENAME).a("-").e(uid);
    }

    public int getUid(){
        return uid;
    }

    public Aduser setUid(int uid){
        this.uid = uid;
        return this;
    }

    public int getAccountid(){
        return accountid;
    }

    public Aduser setAccountid(int accountid){
        int _old = this.accountid;
        this.accountid = accountid;
        changeIt(Col.accountid, _old, accountid);
        return this;
    }

    public Aduser changeAccountid(int accountid){
        return setAccountid(this.accountid + accountid);
    }

    public Aduser changeAccountidWithMin(int accountid, int _min){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public Aduser changeAccountidWithMax(int accountid, int _max){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 > _max  ? _max : _v2);
    }

    public Aduser changeAccountidWithMinMax(int accountid, int _min, int _max){
        int _v2 = this.accountid + accountid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public String getUname(){
        return uname;
    }

    public Aduser setUname(String uname){
        String _old = this.uname;
        this.uname = uname;
        changeIt(Col.uname, _old, uname);
        return this;
    }

    public String getPowerids(){
        return powerids;
    }

    public Aduser setPowerids(String powerids){
        String _old = this.powerids;
        this.powerids = powerids;
        changeIt(Col.powerids, _old, powerids);
        return this;
    }

    public String getRemarks(){
        return remarks;
    }

    public Aduser setRemarks(String remarks){
        String _old = this.remarks;
        this.remarks = remarks;
        changeIt(Col.remarks, _old, remarks);
        return this;
    }

    public int compareTo(Aduser v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Aduser v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Aduser newAduser(Integer uid, Integer accountid, String uname, String powerids, String remarks) {
        Aduser result = new Aduser();
        result.uid = uid;
        result.accountid = accountid;
        result.uname = uname;
        result.powerids = powerids;
        result.remarks = remarks;
        return result;
    }

    public static Aduser newAduser(Aduser aduser) {
        Aduser result = new Aduser();
        result.uid = aduser.uid;
        result.accountid = aduser.accountid;
        result.uname = aduser.uname;
        result.powerids = aduser.powerids;
        result.remarks = aduser.remarks;
        return result;
    }

    public Aduser createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAduser(){
        Aduser aduser = null; // aduser
        { // new and insert Aduser (aduser)
            int uid = 0; 	// uid
            int accountid = 0; 	// accountid
            String uname = ""; 	// uname
            String powerids = ""; 	// powerids
            String remarks = ""; 	// remarks
            aduser = Aduser.newAduser(uid, accountid, uname, powerids, remarks);
        }
        aduser = aduser.insert();

        int uid = aduser.getUid(); 	// uid
        int accountid = aduser.getAccountid(); 	// accountid
        String uname = aduser.getUname(); 	// uname
        String powerids = aduser.getPowerids(); 	// powerids
        String remarks = aduser.getRemarks(); 	// remarks
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.uid:
            return uid;
        case CEn.accountid:
            return accountid;
        }
        return 0;
    }

    public Aduser setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Aduser setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.uid:
            return setUid(value2);
        case CEn.accountid:
            return setAccountid(value2);
        }
        return this;
    }

    public Aduser changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Aduser changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.accountid:
            return changeAccountid(value2);
        }
        return this;
    }

    public Aduser changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Aduser changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.accountid:
            return changeAccountidWithMin(value2, _min);
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

    public Aduser setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Aduser setDouble(String fieldEn, double value2) {
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
        case CEn.uname: 
            return uname;
        case CEn.powerids: 
            return powerids;
        case CEn.remarks: 
            return remarks;
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
        case CEn.uid:
            return uid;
        case CEn.accountid:
            return accountid;
        case CEn.uname:
            return uname;
        case CEn.powerids:
            return powerids;
        case CEn.remarks:
            return remarks;
        }
        return null;
    }

    public Aduser setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Aduser setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Aduser setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Aduser setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.uname:
            return setUname(value2);
        case CEn.powerids:
            return setPowerids(value2);
        case CEn.remarks:
            return setRemarks(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Aduser setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Aduser setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Aduser");
        result.put("uid", uid);
        result.put("accountid", accountid);
        result.put("uname", uname);
        result.put("powerids", powerids);
        result.put("remarks", remarks);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("uid", uid);
        result.put("accountid", accountid);
        result.put("uname", uname);
        result.put("powerids", powerids);
        result.put("remarks", remarks);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Aduser");
        result.put("uid", uid);
        result.put("accountid", accountid);
        result.put("uname", uname);
        result.put("powerids", powerids);
        result.put("remarks", remarks);
        return result;
    }

    public Aduser mapToObject(Map e){
        Integer uid = MapEx.getInt(e, "uid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String uname = MapEx.getString(e, "uname");
        String powerids = MapEx.getString(e, "powerids");
        String remarks = MapEx.getString(e, "remarks");

        if(uid == null) uid = 0;
        if(accountid == null) accountid = 0;
        if(uname == null) uname = "";
        if(powerids == null) powerids = "";
        if(remarks == null) remarks = "";

        setUid(uid);
        setAccountid(accountid);
        setUname(uname);
        setPowerids(powerids);
        setRemarks(remarks);

        return this;
    }

    public static final Aduser mapTo(Map e){
        Aduser result = new Aduser();

        Integer uid = MapEx.getInt(e, "uid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String uname = MapEx.getString(e, "uname");
        String powerids = MapEx.getString(e, "powerids");
        String remarks = MapEx.getString(e, "remarks");

        if(uid == null) uid = 0;
        if(accountid == null) accountid = 0;
        if(uname == null) uname = "";
        if(powerids == null) powerids = "";
        if(remarks == null) remarks = "";

        result.uid = uid;
        result.accountid = accountid;
        result.uname = uname;
        result.powerids = powerids;
        result.remarks = remarks;

        return result;
    }

    public static final Aduser originalTo(Map e){
        Aduser result = new Aduser();

        Integer uid = MapEx.getInt(e, "uid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String uname = MapEx.getString(e, "uname");
        String powerids = MapEx.getString(e, "powerids");
        String remarks = MapEx.getString(e, "remarks");

        if(uid == null) uid = 0;
        if(accountid == null) accountid = 0;
        if(uname == null) uname = "";
        if(powerids == null) powerids = "";
        if(remarks == null) remarks = "";

        result.uid = uid;
        result.accountid = accountid;
        result.uname = uname;
        result.powerids = powerids;
        result.remarks = remarks;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Aduser createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 5);
            writeMapEntry(out, "uid", uid);
            writeMapEntry(out, "accountid", accountid);
            writeMapEntry(out, "uname", uname);
            writeMapEntry(out, "powerids", powerids);
            writeMapEntry(out, "remarks", remarks);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Aduser createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(uid);
        return s.hashCode();
    }
    public final Account getAccountFkAccountid() { // account - accountid
        return AccountEntity.getByKey(accountid);
    }

    public final int countAccountFkAccountid() { // account - accountid
        return getAccountFkAccountid() == null ? 0 : 1;
    }

    public static final Aduser loadByKey(int uid) {
        return AduserEntity.getByKey(uid);
    }

    public static final Future<Aduser> asyncByKey(int uid) {
        return AduserEntity.asyncGetByKey(uid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Aduser insert() {
        Aduser result = AduserEntity.insert(this);
        if(result == null) return null;
        // uid = result.uid;
        return result;
    }

    public final Aduser asyncInsert() {
        Aduser result = AduserEntity.asyncInsert(this);
        // uid = result.uid;
        return result;
    }

    public final Aduser insert2() {
        return AduserEntity.insert2(this);
    }

    public final Aduser asyncInsert2() {
        Aduser result = AduserEntity.asyncInsert2(this);
        // uid = result.uid;
        return result;
    }

    public final Aduser update() {
        return AduserEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(uid <= 0) return false;
        AduserEntity.asyncUpdate( this );
        return true;
    }

    public final Aduser insertOrUpdate() {
        if(uid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AduserEntity.delete(uid);
    }

    public final void asyncDelete() {
        AduserEntity.asyncDelete(uid);
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

    public Aduser clone2() {
        try{
            return (Aduser) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
