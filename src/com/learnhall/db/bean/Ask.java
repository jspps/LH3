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

//learnhall3_design - ask
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Ask extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1811976723; // com.learnhall.db.bean.Ask

    public static String TABLENAME = "ask";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String customerid = "customerid"; public static final String title = "title"; public static final String rewardamount = "rewardamount"; public static final String expirationtime = "expirationtime"; public static final String status = "status"; public static final String tag = "tag"; public static final String answerid = "answerid"; public static final String createtime = "createtime"; public static final String status_opt = "status_opt";  }
    public static final class CEn { public static final String id = "id"; public static final String customerid = "customerid"; public static final String title = "title"; public static final String rewardamount = "rewardamount"; public static final String expirationtime = "expirationtime"; public static final String status = "status"; public static final String tag = "tag"; public static final String answerid = "answerid"; public static final String createtime = "createtime"; public static final String status_opt = "status_opt";  }
    public static final String[] carrays ={"id", "customerid", "title", "rewardamount", "expirationtime", "status", "tag", "answerid", "createtime", "status_opt"};
    public static final String[] dbTypes ={"INT", "INT", "TEXT", "DOUBLE", "DATETIME", "INT", "TINYTEXT", "INT", "DATETIME", "INT"};


    public int id;
    public int customerid;
    public String title;
    public double rewardamount;
    public java.util.Date expirationtime;
    public int status;
    public String tag;
    public int answerid;
    public java.util.Date createtime;
    public int status_opt;

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

    public Ask setId(int id){
        this.id = id;
        return this;
    }

    public int getCustomerid(){
        return customerid;
    }

    public Ask setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Ask changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Ask changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Ask changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Ask changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public String getTitle(){
        return title;
    }

    public Ask setTitle(String title){
        String _old = this.title;
        this.title = title;
        changeIt(Col.title, _old, title);
        return this;
    }

    public double getRewardamount(){
        return rewardamount;
    }

    public Ask setRewardamount(double rewardamount){
        double _old = this.rewardamount;
        this.rewardamount = rewardamount;
        changeIt(Col.rewardamount, _old, rewardamount);
        return this;
    }

    public Ask changeRewardamount(double rewardamount){
        return setRewardamount(this.rewardamount + rewardamount);
    }

    public Ask changeRewardamountWithMin(double rewardamount, double _min){
        double _v2 = this.rewardamount + rewardamount;
        return setRewardamount(_v2 < _min  ? _min : _v2);
    }

    public Ask changeRewardamountWithMax(double rewardamount, double _max){
        double _v2 = this.rewardamount + rewardamount;
        return setRewardamount(_v2 > _max  ? _max : _v2);
    }

    public Ask changeRewardamountWithMinMax(double rewardamount, double _min, double _max){
        double _v2 = this.rewardamount + rewardamount;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRewardamount(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getExpirationtime(){
        return expirationtime;
    }

    public Ask setExpirationtime(java.util.Date expirationtime){
        java.util.Date _old = this.expirationtime;
        this.expirationtime = expirationtime;
        changeIt(Col.expirationtime, _old, expirationtime);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Ask setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Ask changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Ask changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Ask changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Ask changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public String getTag(){
        return tag;
    }

    public Ask setTag(String tag){
        String _old = this.tag;
        this.tag = tag;
        changeIt(Col.tag, _old, tag);
        return this;
    }

    public int getAnswerid(){
        return answerid;
    }

    public Ask setAnswerid(int answerid){
        int _old = this.answerid;
        this.answerid = answerid;
        changeIt(Col.answerid, _old, answerid);
        return this;
    }

    public Ask changeAnswerid(int answerid){
        return setAnswerid(this.answerid + answerid);
    }

    public Ask changeAnsweridWithMin(int answerid, int _min){
        int _v2 = this.answerid + answerid;
        return setAnswerid(_v2 < _min  ? _min : _v2);
    }

    public Ask changeAnsweridWithMax(int answerid, int _max){
        int _v2 = this.answerid + answerid;
        return setAnswerid(_v2 > _max  ? _max : _v2);
    }

    public Ask changeAnsweridWithMinMax(int answerid, int _min, int _max){
        int _v2 = this.answerid + answerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAnswerid(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Ask setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getStatus_opt(){
        return status_opt;
    }

    public Ask setStatus_opt(int status_opt){
        int _old = this.status_opt;
        this.status_opt = status_opt;
        changeIt(Col.status_opt, _old, status_opt);
        return this;
    }

    public Ask changeStatus_opt(int status_opt){
        return setStatus_opt(this.status_opt + status_opt);
    }

    public Ask changeStatus_optWithMin(int status_opt, int _min){
        int _v2 = this.status_opt + status_opt;
        return setStatus_opt(_v2 < _min  ? _min : _v2);
    }

    public Ask changeStatus_optWithMax(int status_opt, int _max){
        int _v2 = this.status_opt + status_opt;
        return setStatus_opt(_v2 > _max  ? _max : _v2);
    }

    public Ask changeStatus_optWithMinMax(int status_opt, int _min, int _max){
        int _v2 = this.status_opt + status_opt;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus_opt(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Ask v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Ask v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Ask newAsk(Integer id, Integer customerid, String title, Double rewardamount, java.util.Date expirationtime, Integer status, String tag, Integer answerid, java.util.Date createtime, Integer status_opt) {
        Ask result = new Ask();
        result.id = id;
        result.customerid = customerid;
        result.title = title;
        result.rewardamount = rewardamount;
        result.expirationtime = expirationtime;
        result.status = status;
        result.tag = tag;
        result.answerid = answerid;
        result.createtime = createtime;
        result.status_opt = status_opt;
        return result;
    }

    public static Ask newAsk(Ask ask) {
        Ask result = new Ask();
        result.id = ask.id;
        result.customerid = ask.customerid;
        result.title = ask.title;
        result.rewardamount = ask.rewardamount;
        result.expirationtime = ask.expirationtime;
        result.status = ask.status;
        result.tag = ask.tag;
        result.answerid = ask.answerid;
        result.createtime = ask.createtime;
        result.status_opt = ask.status_opt;
        return result;
    }

    public Ask createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAsk(){
        Ask ask = null; // ask
        { // new and insert Ask (ask)
            int id = 0; 	// id
            int customerid = 0; 	// customerid
            String title = ""; 	// title
            double rewardamount = 0.0; 	// rewardamount
            Date expirationtime = new Date(); 	// expirationtime
            int status = 0; 	// status
            String tag = ""; 	// tag
            int answerid = 0; 	// answerid
            Date createtime = new Date(); 	// createtime
            int status_opt = 0; 	// status_opt
            ask = Ask.newAsk(id, customerid, title, rewardamount, expirationtime, status, tag, answerid, createtime, status_opt);
        }
        ask = ask.insert();

        int id = ask.getId(); 	// id
        int customerid = ask.getCustomerid(); 	// customerid
        String title = ask.getTitle(); 	// title
        double rewardamount = ask.getRewardamount(); 	// rewardamount
        Date expirationtime = ask.getExpirationtime(); 	// expirationtime
        int status = ask.getStatus(); 	// status
        String tag = ask.getTag(); 	// tag
        int answerid = ask.getAnswerid(); 	// answerid
        Date createtime = ask.getCreatetime(); 	// createtime
        int status_opt = ask.getStatus_opt(); 	// status_opt
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
        case CEn.customerid:
            return customerid;
        case CEn.status:
            return status;
        case CEn.answerid:
            return answerid;
        case CEn.status_opt:
            return status_opt;
        }
        return 0;
    }

    public Ask setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Ask setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.answerid:
            return setAnswerid(value2);
        case CEn.status_opt:
            return setStatus_opt(value2);
        }
        return this;
    }

    public Ask changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Ask changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.answerid:
            return changeAnswerid(value2);
        case CEn.status_opt:
            return changeStatus_opt(value2);
        }
        return this;
    }

    public Ask changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Ask changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.answerid:
            return changeAnsweridWithMin(value2, _min);
        case CEn.status_opt:
            return changeStatus_optWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.rewardamount:
            return rewardamount;
        }
        return 0;
    }

    public Ask setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Ask setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.rewardamount:
            return setRewardamount(value2);
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
        case CEn.title: 
            return title;
        case CEn.tag: 
            return tag;
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
        case CEn.customerid:
            return customerid;
        case CEn.title:
            return title;
        case CEn.rewardamount:
            return rewardamount;
        case CEn.expirationtime:
            return expirationtime;
        case CEn.status:
            return status;
        case CEn.tag:
            return tag;
        case CEn.answerid:
            return answerid;
        case CEn.createtime:
            return createtime;
        case CEn.status_opt:
            return status_opt;
        }
        return null;
    }

    public Ask setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Ask setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Ask setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Ask setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.title:
            return setTitle(value2);
        case CEn.tag:
            return setTag(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Ask setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Ask setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Ask");
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("title", title);
        result.put("rewardamount", rewardamount);
        result.put("expirationtime", expirationtime);
        result.put("status", status);
        result.put("tag", tag);
        result.put("answerid", answerid);
        result.put("createtime", createtime);
        result.put("status_opt", status_opt);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("title", title);
        result.put("rewardamount", rewardamount);
        result.put("expirationtime", expirationtime);
        result.put("status", status);
        result.put("tag", tag);
        result.put("answerid", answerid);
        result.put("createtime", createtime);
        result.put("status_opt", status_opt);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Ask");
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("title", title);
        result.put("rewardamount", rewardamount);
        result.put("expirationtime", expirationtime);
        result.put("status", status);
        result.put("tag", tag);
        result.put("answerid", answerid);
        result.put("createtime", createtime);
        result.put("status_opt", status_opt);
        return result;
    }

    public Ask mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        String title = MapEx.getString(e, "title");
        Double rewardamount = MapEx.getDouble(e, "rewardamount");
        java.util.Date expirationtime = MapEx.getDate(e, "expirationtime");
        Integer status = MapEx.getInt(e, "status");
        String tag = MapEx.getString(e, "tag");
        Integer answerid = MapEx.getInt(e, "answerid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status_opt = MapEx.getInt(e, "status_opt");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(title == null) title = "";
        if(rewardamount == null) rewardamount = 0.0;
        if(expirationtime == null) expirationtime = new java.util.Date();
        if(status == null) status = 0;
        if(tag == null) tag = "";
        if(answerid == null) answerid = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(status_opt == null) status_opt = 0;

        setId(id);
        setCustomerid(customerid);
        setTitle(title);
        setRewardamount(rewardamount);
        setExpirationtime(expirationtime);
        setStatus(status);
        setTag(tag);
        setAnswerid(answerid);
        setCreatetime(createtime);
        setStatus_opt(status_opt);

        return this;
    }

    public static final Ask mapTo(Map e){
        Ask result = new Ask();

        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        String title = MapEx.getString(e, "title");
        Double rewardamount = MapEx.getDouble(e, "rewardamount");
        java.util.Date expirationtime = MapEx.getDate(e, "expirationtime");
        Integer status = MapEx.getInt(e, "status");
        String tag = MapEx.getString(e, "tag");
        Integer answerid = MapEx.getInt(e, "answerid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status_opt = MapEx.getInt(e, "status_opt");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(title == null) title = "";
        if(rewardamount == null) rewardamount = 0.0;
        if(expirationtime == null) expirationtime = new java.util.Date();
        if(status == null) status = 0;
        if(tag == null) tag = "";
        if(answerid == null) answerid = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(status_opt == null) status_opt = 0;

        result.id = id;
        result.customerid = customerid;
        result.title = title;
        result.rewardamount = rewardamount;
        result.expirationtime = expirationtime;
        result.status = status;
        result.tag = tag;
        result.answerid = answerid;
        result.createtime = createtime;
        result.status_opt = status_opt;

        return result;
    }

    public static final Ask originalTo(Map e){
        Ask result = new Ask();

        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        String title = MapEx.getString(e, "title");
        Double rewardamount = MapEx.getDouble(e, "rewardamount");
        java.util.Date expirationtime = MapEx.getDate(e, "expirationtime");
        Integer status = MapEx.getInt(e, "status");
        String tag = MapEx.getString(e, "tag");
        Integer answerid = MapEx.getInt(e, "answerid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status_opt = MapEx.getInt(e, "status_opt");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(title == null) title = "";
        if(rewardamount == null) rewardamount = 0.0;
        if(expirationtime == null) expirationtime = new java.util.Date();
        if(status == null) status = 0;
        if(tag == null) tag = "";
        if(answerid == null) answerid = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(status_opt == null) status_opt = 0;

        result.id = id;
        result.customerid = customerid;
        result.title = title;
        result.rewardamount = rewardamount;
        result.expirationtime = expirationtime;
        result.status = status;
        result.tag = tag;
        result.answerid = answerid;
        result.createtime = createtime;
        result.status_opt = status_opt;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Ask createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 10);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "title", title);
            writeMapEntry(out, "rewardamount", rewardamount);
            writeMapEntry(out, "expirationtime", expirationtime);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "tag", tag);
            writeMapEntry(out, "answerid", answerid);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "status_opt", status_opt);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Ask createFor(byte[] b) throws Exception {
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
    public static final Ask loadByKey(int id) {
        return AskEntity.getByKey(id);
    }

    public static final Future<Ask> asyncByKey(int id) {
        return AskEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Ask insert() {
        Ask result = AskEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Ask asyncInsert() {
        Ask result = AskEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Ask insert2() {
        return AskEntity.insert2(this);
    }

    public final Ask asyncInsert2() {
        Ask result = AskEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Ask update() {
        return AskEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        AskEntity.asyncUpdate( this );
        return true;
    }

    public final Ask insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AskEntity.delete(id);
    }

    public final void asyncDelete() {
        AskEntity.asyncDelete(id);
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

    public Ask clone2() {
        try{
            return (Ask) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
