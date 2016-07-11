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

//learnhall3_design - appraise
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Appraise extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 408088439; // com.learnhall.db.bean.Appraise

    public static String TABLENAME = "appraise";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String appraisetext = "appraisetext"; public static final String kindid = "kindid"; public static final String customerid = "customerid"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String custname = "custname"; public static final String kindname = "kindname"; public static final String reback = "reback"; public static final String lhubid = "lhubid"; public static final String score = "score";  }
    public static final class CEn { public static final String id = "id"; public static final String appraisetext = "appraisetext"; public static final String kindid = "kindid"; public static final String customerid = "customerid"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String custname = "custname"; public static final String kindname = "kindname"; public static final String reback = "reback"; public static final String lhubid = "lhubid"; public static final String score = "score";  }
    public static final String[] carrays ={"id", "appraisetext", "kindid", "customerid", "status", "createtime", "custname", "kindname", "reback", "lhubid", "score"};
    public static final String[] dbTypes ={"INT", "TEXT", "INT", "INT", "INT", "DATETIME", "TINYTEXT", "TINYTEXT", "TEXT", "INT", "INT"};


    public int id;
    public String appraisetext;
    public int kindid;
    public int customerid;
    public int status;
    public java.util.Date createtime;
    public String custname;
    public String kindname;
    public String reback;
    public int lhubid;
    public int score;

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

    public Appraise setId(int id){
        this.id = id;
        return this;
    }

    public String getAppraisetext(){
        return appraisetext;
    }

    public Appraise setAppraisetext(String appraisetext){
        String _old = this.appraisetext;
        this.appraisetext = appraisetext;
        changeIt(Col.appraisetext, _old, appraisetext);
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Appraise setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Appraise changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Appraise changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Appraise changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Appraise changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getCustomerid(){
        return customerid;
    }

    public Appraise setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Appraise changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Appraise changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Appraise changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Appraise changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Appraise setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Appraise changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Appraise changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Appraise changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Appraise changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Appraise setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public String getCustname(){
        return custname;
    }

    public Appraise setCustname(String custname){
        String _old = this.custname;
        this.custname = custname;
        changeIt(Col.custname, _old, custname);
        return this;
    }

    public String getKindname(){
        return kindname;
    }

    public Appraise setKindname(String kindname){
        String _old = this.kindname;
        this.kindname = kindname;
        changeIt(Col.kindname, _old, kindname);
        return this;
    }

    public String getReback(){
        return reback;
    }

    public Appraise setReback(String reback){
        String _old = this.reback;
        this.reback = reback;
        changeIt(Col.reback, _old, reback);
        return this;
    }

    public int getLhubid(){
        return lhubid;
    }

    public Appraise setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Appraise changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Appraise changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Appraise changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Appraise changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getScore(){
        return score;
    }

    public Appraise setScore(int score){
        int _old = this.score;
        this.score = score;
        changeIt(Col.score, _old, score);
        return this;
    }

    public Appraise changeScore(int score){
        return setScore(this.score + score);
    }

    public Appraise changeScoreWithMin(int score, int _min){
        int _v2 = this.score + score;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public Appraise changeScoreWithMax(int score, int _max){
        int _v2 = this.score + score;
        return setScore(_v2 > _max  ? _max : _v2);
    }

    public Appraise changeScoreWithMinMax(int score, int _min, int _max){
        int _v2 = this.score + score;
        _v2 = _v2 > _max  ? _max : _v2;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Appraise v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Appraise v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Appraise newAppraise(Integer id, String appraisetext, Integer kindid, Integer customerid, Integer status, java.util.Date createtime, String custname, String kindname, String reback, Integer lhubid, Integer score) {
        Appraise result = new Appraise();
        result.id = id;
        result.appraisetext = appraisetext;
        result.kindid = kindid;
        result.customerid = customerid;
        result.status = status;
        result.createtime = createtime;
        result.custname = custname;
        result.kindname = kindname;
        result.reback = reback;
        result.lhubid = lhubid;
        result.score = score;
        return result;
    }

    public static Appraise newAppraise(Appraise appraise) {
        Appraise result = new Appraise();
        result.id = appraise.id;
        result.appraisetext = appraise.appraisetext;
        result.kindid = appraise.kindid;
        result.customerid = appraise.customerid;
        result.status = appraise.status;
        result.createtime = appraise.createtime;
        result.custname = appraise.custname;
        result.kindname = appraise.kindname;
        result.reback = appraise.reback;
        result.lhubid = appraise.lhubid;
        result.score = appraise.score;
        return result;
    }

    public Appraise createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAppraise(){
        Appraise appraise = null; // appraise
        { // new and insert Appraise (appraise)
            int id = 0; 	// id
            String appraisetext = ""; 	// appraisetext
            int kindid = 0; 	// kindid
            int customerid = 0; 	// customerid
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            String custname = ""; 	// custname
            String kindname = ""; 	// kindname
            String reback = ""; 	// reback
            int lhubid = 0; 	// lhubid
            int score = 0; 	// score
            appraise = Appraise.newAppraise(id, appraisetext, kindid, customerid, status, createtime, custname, kindname, reback, lhubid, score);
        }
        appraise = appraise.insert();

        int id = appraise.getId(); 	// id
        String appraisetext = appraise.getAppraisetext(); 	// appraisetext
        int kindid = appraise.getKindid(); 	// kindid
        int customerid = appraise.getCustomerid(); 	// customerid
        int status = appraise.getStatus(); 	// status
        Date createtime = appraise.getCreatetime(); 	// createtime
        String custname = appraise.getCustname(); 	// custname
        String kindname = appraise.getKindname(); 	// kindname
        String reback = appraise.getReback(); 	// reback
        int lhubid = appraise.getLhubid(); 	// lhubid
        int score = appraise.getScore(); 	// score
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
        case CEn.kindid:
            return kindid;
        case CEn.customerid:
            return customerid;
        case CEn.status:
            return status;
        case CEn.lhubid:
            return lhubid;
        case CEn.score:
            return score;
        }
        return 0;
    }

    public Appraise setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Appraise setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.score:
            return setScore(value2);
        }
        return this;
    }

    public Appraise changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Appraise changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.score:
            return changeScore(value2);
        }
        return this;
    }

    public Appraise changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Appraise changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.score:
            return changeScoreWithMin(value2, _min);
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

    public Appraise setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Appraise setDouble(String fieldEn, double value2) {
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
        case CEn.appraisetext: 
            return appraisetext;
        case CEn.custname: 
            return custname;
        case CEn.kindname: 
            return kindname;
        case CEn.reback: 
            return reback;
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
        case CEn.appraisetext:
            return appraisetext;
        case CEn.kindid:
            return kindid;
        case CEn.customerid:
            return customerid;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        case CEn.custname:
            return custname;
        case CEn.kindname:
            return kindname;
        case CEn.reback:
            return reback;
        case CEn.lhubid:
            return lhubid;
        case CEn.score:
            return score;
        }
        return null;
    }

    public Appraise setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Appraise setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Appraise setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Appraise setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.appraisetext:
            return setAppraisetext(value2);
        case CEn.custname:
            return setCustname(value2);
        case CEn.kindname:
            return setKindname(value2);
        case CEn.reback:
            return setReback(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Appraise setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Appraise setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Appraise");
        result.put("id", id);
        result.put("appraisetext", appraisetext);
        result.put("kindid", kindid);
        result.put("customerid", customerid);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("custname", custname);
        result.put("kindname", kindname);
        result.put("reback", reback);
        result.put("lhubid", lhubid);
        result.put("score", score);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("appraisetext", appraisetext);
        result.put("kindid", kindid);
        result.put("customerid", customerid);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("custname", custname);
        result.put("kindname", kindname);
        result.put("reback", reback);
        result.put("lhubid", lhubid);
        result.put("score", score);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Appraise");
        result.put("id", id);
        result.put("appraisetext", appraisetext);
        result.put("kindid", kindid);
        result.put("customerid", customerid);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("custname", custname);
        result.put("kindname", kindname);
        result.put("reback", reback);
        result.put("lhubid", lhubid);
        result.put("score", score);
        return result;
    }

    public Appraise mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String appraisetext = MapEx.getString(e, "appraisetext");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String custname = MapEx.getString(e, "custname");
        String kindname = MapEx.getString(e, "kindname");
        String reback = MapEx.getString(e, "reback");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer score = MapEx.getInt(e, "score");

        if(id == null) id = 0;
        if(appraisetext == null) appraisetext = "";
        if(kindid == null) kindid = 0;
        if(customerid == null) customerid = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(custname == null) custname = "";
        if(kindname == null) kindname = "";
        if(reback == null) reback = "";
        if(lhubid == null) lhubid = 0;
        if(score == null) score = 0;

        setId(id);
        setAppraisetext(appraisetext);
        setKindid(kindid);
        setCustomerid(customerid);
        setStatus(status);
        setCreatetime(createtime);
        setCustname(custname);
        setKindname(kindname);
        setReback(reback);
        setLhubid(lhubid);
        setScore(score);

        return this;
    }

    public static final Appraise mapTo(Map e){
        Appraise result = new Appraise();

        Integer id = MapEx.getInt(e, "id");
        String appraisetext = MapEx.getString(e, "appraisetext");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String custname = MapEx.getString(e, "custname");
        String kindname = MapEx.getString(e, "kindname");
        String reback = MapEx.getString(e, "reback");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer score = MapEx.getInt(e, "score");

        if(id == null) id = 0;
        if(appraisetext == null) appraisetext = "";
        if(kindid == null) kindid = 0;
        if(customerid == null) customerid = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(custname == null) custname = "";
        if(kindname == null) kindname = "";
        if(reback == null) reback = "";
        if(lhubid == null) lhubid = 0;
        if(score == null) score = 0;

        result.id = id;
        result.appraisetext = appraisetext;
        result.kindid = kindid;
        result.customerid = customerid;
        result.status = status;
        result.createtime = createtime;
        result.custname = custname;
        result.kindname = kindname;
        result.reback = reback;
        result.lhubid = lhubid;
        result.score = score;

        return result;
    }

    public static final Appraise originalTo(Map e){
        Appraise result = new Appraise();

        Integer id = MapEx.getInt(e, "id");
        String appraisetext = MapEx.getString(e, "appraisetext");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String custname = MapEx.getString(e, "custname");
        String kindname = MapEx.getString(e, "kindname");
        String reback = MapEx.getString(e, "reback");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer score = MapEx.getInt(e, "score");

        if(id == null) id = 0;
        if(appraisetext == null) appraisetext = "";
        if(kindid == null) kindid = 0;
        if(customerid == null) customerid = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(custname == null) custname = "";
        if(kindname == null) kindname = "";
        if(reback == null) reback = "";
        if(lhubid == null) lhubid = 0;
        if(score == null) score = 0;

        result.id = id;
        result.appraisetext = appraisetext;
        result.kindid = kindid;
        result.customerid = customerid;
        result.status = status;
        result.createtime = createtime;
        result.custname = custname;
        result.kindname = kindname;
        result.reback = reback;
        result.lhubid = lhubid;
        result.score = score;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Appraise createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 11);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "appraisetext", appraisetext);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "custname", custname);
            writeMapEntry(out, "kindname", kindname);
            writeMapEntry(out, "reback", reback);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "score", score);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Appraise createFor(byte[] b) throws Exception {
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
    public final Customer getCustomerFkCustomerid() { // customer - customerid
        return CustomerEntity.getByKey(customerid);
    }

    public final int countCustomerFkCustomerid() { // customer - customerid
        return getCustomerFkCustomerid() == null ? 0 : 1;
    }

    public final Kind getKindFkKindid() { // kind - kindid
        return KindEntity.getByKey(kindid);
    }

    public final int countKindFkKindid() { // kind - kindid
        return getKindFkKindid() == null ? 0 : 1;
    }

    public static final Appraise loadByKey(int id) {
        return AppraiseEntity.getByKey(id);
    }

    public static final Future<Appraise> asyncByKey(int id) {
        return AppraiseEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Appraise insert() {
        Appraise result = AppraiseEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Appraise asyncInsert() {
        Appraise result = AppraiseEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Appraise insert2() {
        return AppraiseEntity.insert2(this);
    }

    public final Appraise asyncInsert2() {
        Appraise result = AppraiseEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Appraise update() {
        return AppraiseEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        AppraiseEntity.asyncUpdate( this );
        return true;
    }

    public final Appraise insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AppraiseEntity.delete(id);
    }

    public final void asyncDelete() {
        AppraiseEntity.asyncDelete(id);
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

    public Appraise clone2() {
        try{
            return (Appraise) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
