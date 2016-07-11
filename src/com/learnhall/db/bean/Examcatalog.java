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

//learnhall3_design - examcatalog
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Examcatalog extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 905074318; // com.learnhall.db.bean.Examcatalog

    public static String TABLENAME = "examcatalog";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String examid = "examid"; public static final String serial = "serial"; public static final String catalogType = "catalogType"; public static final String gid = "gid"; public static final String bigtypes = "bigtypes"; public static final String isSubjective = "isSubjective"; public static final String num = "num"; public static final String everyScore = "everyScore"; public static final String title = "title"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String parentid = "parentid";  }
    public static final class CEn { public static final String id = "id"; public static final String examid = "examid"; public static final String serial = "serial"; public static final String catalogType = "catalogType"; public static final String gid = "gid"; public static final String bigtypes = "bigtypes"; public static final String isSubjective = "isSubjective"; public static final String num = "num"; public static final String everyScore = "everyScore"; public static final String title = "title"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String parentid = "parentid";  }
    public static final String[] carrays ={"id", "examid", "serial", "catalogType", "gid", "bigtypes", "isSubjective", "num", "everyScore", "title", "status", "createtime", "parentid"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "INT", "TINYTEXT", "BIT", "INT", "INT", "TEXT", "INT", "DATETIME", "INT"};


    public int id;
    public int examid;
    public String serial;
    public int catalogType;
    public int gid;
    public String bigtypes;
    public boolean isSubjective;
    public int num;
    public int everyScore;
    public String title;
    public int status;
    public java.util.Date createtime;
    public int parentid;

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

    public Examcatalog setId(int id){
        this.id = id;
        return this;
    }

    public int getExamid(){
        return examid;
    }

    public Examcatalog setExamid(int examid){
        int _old = this.examid;
        this.examid = examid;
        changeIt(Col.examid, _old, examid);
        return this;
    }

    public Examcatalog changeExamid(int examid){
        return setExamid(this.examid + examid);
    }

    public Examcatalog changeExamidWithMin(int examid, int _min){
        int _v2 = this.examid + examid;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeExamidWithMax(int examid, int _max){
        int _v2 = this.examid + examid;
        return setExamid(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeExamidWithMinMax(int examid, int _min, int _max){
        int _v2 = this.examid + examid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public String getSerial(){
        return serial;
    }

    public Examcatalog setSerial(String serial){
        String _old = this.serial;
        this.serial = serial;
        changeIt(Col.serial, _old, serial);
        return this;
    }

    public int getCatalogType(){
        return catalogType;
    }

    public Examcatalog setCatalogType(int catalogType){
        int _old = this.catalogType;
        this.catalogType = catalogType;
        changeIt(Col.catalogType, _old, catalogType);
        return this;
    }

    public Examcatalog changeCatalogType(int catalogType){
        return setCatalogType(this.catalogType + catalogType);
    }

    public Examcatalog changeCatalogTypeWithMin(int catalogType, int _min){
        int _v2 = this.catalogType + catalogType;
        return setCatalogType(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeCatalogTypeWithMax(int catalogType, int _max){
        int _v2 = this.catalogType + catalogType;
        return setCatalogType(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeCatalogTypeWithMinMax(int catalogType, int _min, int _max){
        int _v2 = this.catalogType + catalogType;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCatalogType(_v2 < _min  ? _min : _v2);
    }

    public int getGid(){
        return gid;
    }

    public Examcatalog setGid(int gid){
        int _old = this.gid;
        this.gid = gid;
        changeIt(Col.gid, _old, gid);
        return this;
    }

    public Examcatalog changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Examcatalog changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public String getBigtypes(){
        return bigtypes;
    }

    public Examcatalog setBigtypes(String bigtypes){
        String _old = this.bigtypes;
        this.bigtypes = bigtypes;
        changeIt(Col.bigtypes, _old, bigtypes);
        return this;
    }

    public boolean getIsSubjective(){
        return isSubjective;
    }

    public Examcatalog setIsSubjective(boolean isSubjective){
        boolean _old = this.isSubjective;
        this.isSubjective = isSubjective;
        changeIt(Col.isSubjective, _old, isSubjective);
        return this;
    }

    public int getNum(){
        return num;
    }

    public Examcatalog setNum(int num){
        int _old = this.num;
        this.num = num;
        changeIt(Col.num, _old, num);
        return this;
    }

    public Examcatalog changeNum(int num){
        return setNum(this.num + num);
    }

    public Examcatalog changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getEveryScore(){
        return everyScore;
    }

    public Examcatalog setEveryScore(int everyScore){
        int _old = this.everyScore;
        this.everyScore = everyScore;
        changeIt(Col.everyScore, _old, everyScore);
        return this;
    }

    public Examcatalog changeEveryScore(int everyScore){
        return setEveryScore(this.everyScore + everyScore);
    }

    public Examcatalog changeEveryScoreWithMin(int everyScore, int _min){
        int _v2 = this.everyScore + everyScore;
        return setEveryScore(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeEveryScoreWithMax(int everyScore, int _max){
        int _v2 = this.everyScore + everyScore;
        return setEveryScore(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeEveryScoreWithMinMax(int everyScore, int _min, int _max){
        int _v2 = this.everyScore + everyScore;
        _v2 = _v2 > _max  ? _max : _v2;
        return setEveryScore(_v2 < _min  ? _min : _v2);
    }

    public String getTitle(){
        return title;
    }

    public Examcatalog setTitle(String title){
        String _old = this.title;
        this.title = title;
        changeIt(Col.title, _old, title);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Examcatalog setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Examcatalog changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Examcatalog changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Examcatalog setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getParentid(){
        return parentid;
    }

    public Examcatalog setParentid(int parentid){
        int _old = this.parentid;
        this.parentid = parentid;
        changeIt(Col.parentid, _old, parentid);
        return this;
    }

    public Examcatalog changeParentid(int parentid){
        return setParentid(this.parentid + parentid);
    }

    public Examcatalog changeParentidWithMin(int parentid, int _min){
        int _v2 = this.parentid + parentid;
        return setParentid(_v2 < _min  ? _min : _v2);
    }

    public Examcatalog changeParentidWithMax(int parentid, int _max){
        int _v2 = this.parentid + parentid;
        return setParentid(_v2 > _max  ? _max : _v2);
    }

    public Examcatalog changeParentidWithMinMax(int parentid, int _min, int _max){
        int _v2 = this.parentid + parentid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setParentid(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Examcatalog v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Examcatalog v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Examcatalog newExamcatalog(Integer id, Integer examid, String serial, Integer catalogType, Integer gid, String bigtypes, Boolean isSubjective, Integer num, Integer everyScore, String title, Integer status, java.util.Date createtime, Integer parentid) {
        Examcatalog result = new Examcatalog();
        result.id = id;
        result.examid = examid;
        result.serial = serial;
        result.catalogType = catalogType;
        result.gid = gid;
        result.bigtypes = bigtypes;
        result.isSubjective = isSubjective;
        result.num = num;
        result.everyScore = everyScore;
        result.title = title;
        result.status = status;
        result.createtime = createtime;
        result.parentid = parentid;
        return result;
    }

    public static Examcatalog newExamcatalog(Examcatalog examcatalog) {
        Examcatalog result = new Examcatalog();
        result.id = examcatalog.id;
        result.examid = examcatalog.examid;
        result.serial = examcatalog.serial;
        result.catalogType = examcatalog.catalogType;
        result.gid = examcatalog.gid;
        result.bigtypes = examcatalog.bigtypes;
        result.isSubjective = examcatalog.isSubjective;
        result.num = examcatalog.num;
        result.everyScore = examcatalog.everyScore;
        result.title = examcatalog.title;
        result.status = examcatalog.status;
        result.createtime = examcatalog.createtime;
        result.parentid = examcatalog.parentid;
        return result;
    }

    public Examcatalog createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getExamcatalog(){
        Examcatalog examcatalog = null; // examcatalog
        { // new and insert Examcatalog (examcatalog)
            int id = 0; 	// id
            int examid = 0; 	// examid
            String serial = ""; 	// serial
            int catalogType = 0; 	// catalogType
            int gid = 0; 	// gid
            String bigtypes = ""; 	// bigtypes
            boolean isSubjective = true; 	// isSubjective
            int num = 0; 	// num
            int everyScore = 0; 	// everyScore
            String title = ""; 	// title
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            int parentid = 0; 	// parentid
            examcatalog = Examcatalog.newExamcatalog(id, examid, serial, catalogType, gid, bigtypes, isSubjective, num, everyScore, title, status, createtime, parentid);
        }
        examcatalog = examcatalog.insert();

        int id = examcatalog.getId(); 	// id
        int examid = examcatalog.getExamid(); 	// examid
        String serial = examcatalog.getSerial(); 	// serial
        int catalogType = examcatalog.getCatalogType(); 	// catalogType
        int gid = examcatalog.getGid(); 	// gid
        String bigtypes = examcatalog.getBigtypes(); 	// bigtypes
        boolean isSubjective = examcatalog.getIsSubjective(); 	// isSubjective
        int num = examcatalog.getNum(); 	// num
        int everyScore = examcatalog.getEveryScore(); 	// everyScore
        String title = examcatalog.getTitle(); 	// title
        int status = examcatalog.getStatus(); 	// status
        Date createtime = examcatalog.getCreatetime(); 	// createtime
        int parentid = examcatalog.getParentid(); 	// parentid
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
        case CEn.examid:
            return examid;
        case CEn.catalogType:
            return catalogType;
        case CEn.gid:
            return gid;
        case CEn.num:
            return num;
        case CEn.everyScore:
            return everyScore;
        case CEn.status:
            return status;
        case CEn.parentid:
            return parentid;
        }
        return 0;
    }

    public Examcatalog setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Examcatalog setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.examid:
            return setExamid(value2);
        case CEn.catalogType:
            return setCatalogType(value2);
        case CEn.gid:
            return setGid(value2);
        case CEn.num:
            return setNum(value2);
        case CEn.everyScore:
            return setEveryScore(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.parentid:
            return setParentid(value2);
        }
        return this;
    }

    public Examcatalog changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Examcatalog changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.examid:
            return changeExamid(value2);
        case CEn.catalogType:
            return changeCatalogType(value2);
        case CEn.gid:
            return changeGid(value2);
        case CEn.num:
            return changeNum(value2);
        case CEn.everyScore:
            return changeEveryScore(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.parentid:
            return changeParentid(value2);
        }
        return this;
    }

    public Examcatalog changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Examcatalog changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.examid:
            return changeExamidWithMin(value2, _min);
        case CEn.catalogType:
            return changeCatalogTypeWithMin(value2, _min);
        case CEn.gid:
            return changeGidWithMin(value2, _min);
        case CEn.num:
            return changeNumWithMin(value2, _min);
        case CEn.everyScore:
            return changeEveryScoreWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.parentid:
            return changeParentidWithMin(value2, _min);
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

    public Examcatalog setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Examcatalog setDouble(String fieldEn, double value2) {
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
        case CEn.serial: 
            return serial;
        case CEn.bigtypes: 
            return bigtypes;
        case CEn.title: 
            return title;
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
        case CEn.examid:
            return examid;
        case CEn.serial:
            return serial;
        case CEn.catalogType:
            return catalogType;
        case CEn.gid:
            return gid;
        case CEn.bigtypes:
            return bigtypes;
        case CEn.isSubjective:
            return isSubjective;
        case CEn.num:
            return num;
        case CEn.everyScore:
            return everyScore;
        case CEn.title:
            return title;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        case CEn.parentid:
            return parentid;
        }
        return null;
    }

    public Examcatalog setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Examcatalog setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Examcatalog setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Examcatalog setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.serial:
            return setSerial(value2);
        case CEn.bigtypes:
            return setBigtypes(value2);
        case CEn.title:
            return setTitle(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Examcatalog setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Examcatalog setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Examcatalog");
        result.put("id", id);
        result.put("examid", examid);
        result.put("serial", serial);
        result.put("catalogType", catalogType);
        result.put("gid", gid);
        result.put("bigtypes", bigtypes);
        result.put("isSubjective", isSubjective);
        result.put("num", num);
        result.put("everyScore", everyScore);
        result.put("title", title);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("parentid", parentid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("examid", examid);
        result.put("serial", serial);
        result.put("catalogType", catalogType);
        result.put("gid", gid);
        result.put("bigtypes", bigtypes);
        result.put("isSubjective", isSubjective);
        result.put("num", num);
        result.put("everyScore", everyScore);
        result.put("title", title);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("parentid", parentid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Examcatalog");
        result.put("id", id);
        result.put("examid", examid);
        result.put("serial", serial);
        result.put("catalogType", catalogType);
        result.put("gid", gid);
        result.put("bigtypes", bigtypes);
        result.put("isSubjective", isSubjective);
        result.put("num", num);
        result.put("everyScore", everyScore);
        result.put("title", title);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("parentid", parentid);
        return result;
    }

    public Examcatalog mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String serial = MapEx.getString(e, "serial");
        Integer catalogType = MapEx.getInt(e, "catalogType");
        Integer gid = MapEx.getInt(e, "gid");
        String bigtypes = MapEx.getString(e, "bigtypes");
        Boolean isSubjective = MapEx.getBoolean(e, "isSubjective");
        Integer num = MapEx.getInt(e, "num");
        Integer everyScore = MapEx.getInt(e, "everyScore");
        String title = MapEx.getString(e, "title");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer parentid = MapEx.getInt(e, "parentid");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(serial == null) serial = "";
        if(catalogType == null) catalogType = 0;
        if(gid == null) gid = 0;
        if(bigtypes == null) bigtypes = "";
        if(isSubjective == null) isSubjective = false;
        if(num == null) num = 0;
        if(everyScore == null) everyScore = 0;
        if(title == null) title = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(parentid == null) parentid = 0;

        setId(id);
        setExamid(examid);
        setSerial(serial);
        setCatalogType(catalogType);
        setGid(gid);
        setBigtypes(bigtypes);
        setIsSubjective(isSubjective);
        setNum(num);
        setEveryScore(everyScore);
        setTitle(title);
        setStatus(status);
        setCreatetime(createtime);
        setParentid(parentid);

        return this;
    }

    public static final Examcatalog mapTo(Map e){
        Examcatalog result = new Examcatalog();

        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String serial = MapEx.getString(e, "serial");
        Integer catalogType = MapEx.getInt(e, "catalogType");
        Integer gid = MapEx.getInt(e, "gid");
        String bigtypes = MapEx.getString(e, "bigtypes");
        Boolean isSubjective = MapEx.getBoolean(e, "isSubjective");
        Integer num = MapEx.getInt(e, "num");
        Integer everyScore = MapEx.getInt(e, "everyScore");
        String title = MapEx.getString(e, "title");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer parentid = MapEx.getInt(e, "parentid");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(serial == null) serial = "";
        if(catalogType == null) catalogType = 0;
        if(gid == null) gid = 0;
        if(bigtypes == null) bigtypes = "";
        if(isSubjective == null) isSubjective = false;
        if(num == null) num = 0;
        if(everyScore == null) everyScore = 0;
        if(title == null) title = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(parentid == null) parentid = 0;

        result.id = id;
        result.examid = examid;
        result.serial = serial;
        result.catalogType = catalogType;
        result.gid = gid;
        result.bigtypes = bigtypes;
        result.isSubjective = isSubjective;
        result.num = num;
        result.everyScore = everyScore;
        result.title = title;
        result.status = status;
        result.createtime = createtime;
        result.parentid = parentid;

        return result;
    }

    public static final Examcatalog originalTo(Map e){
        Examcatalog result = new Examcatalog();

        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String serial = MapEx.getString(e, "serial");
        Integer catalogType = MapEx.getInt(e, "catalogType");
        Integer gid = MapEx.getInt(e, "gid");
        String bigtypes = MapEx.getString(e, "bigtypes");
        Boolean isSubjective = MapEx.getBoolean(e, "isSubjective");
        Integer num = MapEx.getInt(e, "num");
        Integer everyScore = MapEx.getInt(e, "everyScore");
        String title = MapEx.getString(e, "title");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer parentid = MapEx.getInt(e, "parentid");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(serial == null) serial = "";
        if(catalogType == null) catalogType = 0;
        if(gid == null) gid = 0;
        if(bigtypes == null) bigtypes = "";
        if(isSubjective == null) isSubjective = false;
        if(num == null) num = 0;
        if(everyScore == null) everyScore = 0;
        if(title == null) title = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(parentid == null) parentid = 0;

        result.id = id;
        result.examid = examid;
        result.serial = serial;
        result.catalogType = catalogType;
        result.gid = gid;
        result.bigtypes = bigtypes;
        result.isSubjective = isSubjective;
        result.num = num;
        result.everyScore = everyScore;
        result.title = title;
        result.status = status;
        result.createtime = createtime;
        result.parentid = parentid;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Examcatalog createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 13);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "examid", examid);
            writeMapEntry(out, "serial", serial);
            writeMapEntry(out, "catalogType", catalogType);
            writeMapEntry(out, "gid", gid);
            writeMapEntry(out, "bigtypes", bigtypes);
            writeMapEntry(out, "isSubjective", isSubjective);
            writeMapEntry(out, "num", num);
            writeMapEntry(out, "everyScore", everyScore);
            writeMapEntry(out, "title", title);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "parentid", parentid);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Examcatalog createFor(byte[] b) throws Exception {
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
    public final Exam getExamFkExamid() { // exam - examid
        return ExamEntity.getByKey(examid);
    }

    public final int countExamFkExamid() { // exam - examid
        return getExamFkExamid() == null ? 0 : 1;
    }

    public final Examcatalog getExamcatalogFkParentid() { // examcatalog - parentid
        return ExamcatalogEntity.getByKey(parentid);
    }

    public final int countExamcatalogFkParentid() { // examcatalog - parentid
        return getExamcatalogFkParentid() == null ? 0 : 1;
    }

    public final List<Examcatalog> getExamcatalogsFkParentid() { // examcatalog - parentid
        return ExamcatalogEntity.getByParentid(id);
    }

    public final int countExamcatalogsFkParentid() { // examcatalog - parentid
        return ExamcatalogEntity.countByParentid(id);
    }

    public final List<Optquestion> getOptquestionsFkExamcatalogid() { // optquestion - examcatalogid
        return OptquestionEntity.getByExamcatalogid(id);
    }

    public final int countOptquestionsFkExamcatalogid() { // optquestion - examcatalogid
        return OptquestionEntity.countByExamcatalogid(id);
    }

    public static final Examcatalog loadByKey(int id) {
        return ExamcatalogEntity.getByKey(id);
    }

    public static final Future<Examcatalog> asyncByKey(int id) {
        return ExamcatalogEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Examcatalog insert() {
        Examcatalog result = ExamcatalogEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Examcatalog asyncInsert() {
        Examcatalog result = ExamcatalogEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Examcatalog insert2() {
        return ExamcatalogEntity.insert2(this);
    }

    public final Examcatalog asyncInsert2() {
        Examcatalog result = ExamcatalogEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Examcatalog update() {
        return ExamcatalogEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ExamcatalogEntity.asyncUpdate( this );
        return true;
    }

    public final Examcatalog insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ExamcatalogEntity.delete(id);
    }

    public final void asyncDelete() {
        ExamcatalogEntity.asyncDelete(id);
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

    public Examcatalog clone2() {
        try{
            return (Examcatalog) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
