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

//learnhall3_design - product
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Product extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1581455773; // com.learnhall.db.bean.Product

    public static String TABLENAME = "product";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String coursesid = "coursesid"; public static final String name = "name"; public static final String imgurl = "imgurl"; public static final String descr = "descr"; public static final String lhubid = "lhubid"; public static final String status = "status"; public static final String examineStatus = "examineStatus"; public static final String examineDes = "examineDes"; public static final String complete = "complete"; public static final String createtime = "createtime"; public static final String cruces = "cruces"; public static final String isRecommend = "isRecommend"; public static final String lastTime4Recommend = "lastTime4Recommend"; public static final String protection = "protection"; public static final String lasttime = "lasttime";  }
    public static final class CEn { public static final String id = "id"; public static final String coursesid = "coursesid"; public static final String name = "name"; public static final String imgurl = "imgurl"; public static final String descr = "descr"; public static final String lhubid = "lhubid"; public static final String status = "status"; public static final String examineStatus = "examineStatus"; public static final String examineDes = "examineDes"; public static final String complete = "complete"; public static final String createtime = "createtime"; public static final String cruces = "cruces"; public static final String isRecommend = "isRecommend"; public static final String lastTime4Recommend = "lastTime4Recommend"; public static final String protection = "protection"; public static final String lasttime = "lasttime";  }
    public static final String[] carrays ={"id", "coursesid", "name", "imgurl", "descr", "lhubid", "status", "examineStatus", "examineDes", "complete", "createtime", "cruces", "isRecommend", "lastTime4Recommend", "protection", "lasttime"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "VARCHAR", "TEXT", "INT", "INT", "INT", "TEXT", "INT", "DATETIME", "TEXT", "BIT", "DATETIME", "TEXT", "DATETIME"};


    public int id;
    public int coursesid;
    public String name;
    public String imgurl;
    public String descr;
    public int lhubid;
    public int status;
    public int examineStatus;
    public String examineDes;
    public int complete;
    public java.util.Date createtime;
    public String cruces;
    public boolean isRecommend;
    public java.util.Date lastTime4Recommend;
    public String protection;
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

    public Product setId(int id){
        this.id = id;
        return this;
    }

    public int getCoursesid(){
        return coursesid;
    }

    public Product setCoursesid(int coursesid){
        int _old = this.coursesid;
        this.coursesid = coursesid;
        changeIt(Col.coursesid, _old, coursesid);
        return this;
    }

    public Product changeCoursesid(int coursesid){
        return setCoursesid(this.coursesid + coursesid);
    }

    public Product changeCoursesidWithMin(int coursesid, int _min){
        int _v2 = this.coursesid + coursesid;
        return setCoursesid(_v2 < _min  ? _min : _v2);
    }

    public Product changeCoursesidWithMax(int coursesid, int _max){
        int _v2 = this.coursesid + coursesid;
        return setCoursesid(_v2 > _max  ? _max : _v2);
    }

    public Product changeCoursesidWithMinMax(int coursesid, int _min, int _max){
        int _v2 = this.coursesid + coursesid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCoursesid(_v2 < _min  ? _min : _v2);
    }

    public String getName(){
        return name;
    }

    public Product setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public String getImgurl(){
        return imgurl;
    }

    public Product setImgurl(String imgurl){
        String _old = this.imgurl;
        this.imgurl = imgurl;
        changeIt(Col.imgurl, _old, imgurl);
        return this;
    }

    public String getDescr(){
        return descr;
    }

    public Product setDescr(String descr){
        String _old = this.descr;
        this.descr = descr;
        changeIt(Col.descr, _old, descr);
        return this;
    }

    public int getLhubid(){
        return lhubid;
    }

    public Product setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Product changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Product changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Product changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Product changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Product setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Product changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Product changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Product changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Product changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int getExamineStatus(){
        return examineStatus;
    }

    public Product setExamineStatus(int examineStatus){
        int _old = this.examineStatus;
        this.examineStatus = examineStatus;
        changeIt(Col.examineStatus, _old, examineStatus);
        return this;
    }

    public Product changeExamineStatus(int examineStatus){
        return setExamineStatus(this.examineStatus + examineStatus);
    }

    public Product changeExamineStatusWithMin(int examineStatus, int _min){
        int _v2 = this.examineStatus + examineStatus;
        return setExamineStatus(_v2 < _min  ? _min : _v2);
    }

    public Product changeExamineStatusWithMax(int examineStatus, int _max){
        int _v2 = this.examineStatus + examineStatus;
        return setExamineStatus(_v2 > _max  ? _max : _v2);
    }

    public Product changeExamineStatusWithMinMax(int examineStatus, int _min, int _max){
        int _v2 = this.examineStatus + examineStatus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamineStatus(_v2 < _min  ? _min : _v2);
    }

    public String getExamineDes(){
        return examineDes;
    }

    public Product setExamineDes(String examineDes){
        String _old = this.examineDes;
        this.examineDes = examineDes;
        changeIt(Col.examineDes, _old, examineDes);
        return this;
    }

    public int getComplete(){
        return complete;
    }

    public Product setComplete(int complete){
        int _old = this.complete;
        this.complete = complete;
        changeIt(Col.complete, _old, complete);
        return this;
    }

    public Product changeComplete(int complete){
        return setComplete(this.complete + complete);
    }

    public Product changeCompleteWithMin(int complete, int _min){
        int _v2 = this.complete + complete;
        return setComplete(_v2 < _min  ? _min : _v2);
    }

    public Product changeCompleteWithMax(int complete, int _max){
        int _v2 = this.complete + complete;
        return setComplete(_v2 > _max  ? _max : _v2);
    }

    public Product changeCompleteWithMinMax(int complete, int _min, int _max){
        int _v2 = this.complete + complete;
        _v2 = _v2 > _max  ? _max : _v2;
        return setComplete(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Product setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public String getCruces(){
        return cruces;
    }

    public Product setCruces(String cruces){
        String _old = this.cruces;
        this.cruces = cruces;
        changeIt(Col.cruces, _old, cruces);
        return this;
    }

    public boolean getIsRecommend(){
        return isRecommend;
    }

    public Product setIsRecommend(boolean isRecommend){
        boolean _old = this.isRecommend;
        this.isRecommend = isRecommend;
        changeIt(Col.isRecommend, _old, isRecommend);
        return this;
    }

    public java.util.Date getLastTime4Recommend(){
        return lastTime4Recommend;
    }

    public Product setLastTime4Recommend(java.util.Date lastTime4Recommend){
        java.util.Date _old = this.lastTime4Recommend;
        this.lastTime4Recommend = lastTime4Recommend;
        changeIt(Col.lastTime4Recommend, _old, lastTime4Recommend);
        return this;
    }

    public String getProtection(){
        return protection;
    }

    public Product setProtection(String protection){
        String _old = this.protection;
        this.protection = protection;
        changeIt(Col.protection, _old, protection);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Product setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public int compareTo(Product v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Product v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Product newProduct(Integer id, Integer coursesid, String name, String imgurl, String descr, Integer lhubid, Integer status, Integer examineStatus, String examineDes, Integer complete, java.util.Date createtime, String cruces, Boolean isRecommend, java.util.Date lastTime4Recommend, String protection, java.util.Date lasttime) {
        Product result = new Product();
        result.id = id;
        result.coursesid = coursesid;
        result.name = name;
        result.imgurl = imgurl;
        result.descr = descr;
        result.lhubid = lhubid;
        result.status = status;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.complete = complete;
        result.createtime = createtime;
        result.cruces = cruces;
        result.isRecommend = isRecommend;
        result.lastTime4Recommend = lastTime4Recommend;
        result.protection = protection;
        result.lasttime = lasttime;
        return result;
    }

    public static Product newProduct(Product product) {
        Product result = new Product();
        result.id = product.id;
        result.coursesid = product.coursesid;
        result.name = product.name;
        result.imgurl = product.imgurl;
        result.descr = product.descr;
        result.lhubid = product.lhubid;
        result.status = product.status;
        result.examineStatus = product.examineStatus;
        result.examineDes = product.examineDes;
        result.complete = product.complete;
        result.createtime = product.createtime;
        result.cruces = product.cruces;
        result.isRecommend = product.isRecommend;
        result.lastTime4Recommend = product.lastTime4Recommend;
        result.protection = product.protection;
        result.lasttime = product.lasttime;
        return result;
    }

    public Product createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getProduct(){
        Product product = null; // product
        { // new and insert Product (product)
            int id = 0; 	// id
            int coursesid = 0; 	// coursesid
            String name = ""; 	// name
            String imgurl = ""; 	// imgurl
            String descr = ""; 	// descr
            int lhubid = 0; 	// lhubid
            int status = 0; 	// status
            int examineStatus = 0; 	// examineStatus
            String examineDes = ""; 	// examineDes
            int complete = 0; 	// complete
            Date createtime = new Date(); 	// createtime
            String cruces = ""; 	// cruces
            boolean isRecommend = true; 	// isRecommend
            Date lastTime4Recommend = new Date(); 	// lastTime4Recommend
            String protection = ""; 	// protection
            Date lasttime = new Date(); 	// lasttime
            product = Product.newProduct(id, coursesid, name, imgurl, descr, lhubid, status, examineStatus, examineDes, complete, createtime, cruces, isRecommend, lastTime4Recommend, protection, lasttime);
        }
        product = product.insert();

        int id = product.getId(); 	// id
        int coursesid = product.getCoursesid(); 	// coursesid
        String name = product.getName(); 	// name
        String imgurl = product.getImgurl(); 	// imgurl
        String descr = product.getDescr(); 	// descr
        int lhubid = product.getLhubid(); 	// lhubid
        int status = product.getStatus(); 	// status
        int examineStatus = product.getExamineStatus(); 	// examineStatus
        String examineDes = product.getExamineDes(); 	// examineDes
        int complete = product.getComplete(); 	// complete
        Date createtime = product.getCreatetime(); 	// createtime
        String cruces = product.getCruces(); 	// cruces
        boolean isRecommend = product.getIsRecommend(); 	// isRecommend
        Date lastTime4Recommend = product.getLastTime4Recommend(); 	// lastTime4Recommend
        String protection = product.getProtection(); 	// protection
        Date lasttime = product.getLasttime(); 	// lasttime
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
        case CEn.coursesid:
            return coursesid;
        case CEn.lhubid:
            return lhubid;
        case CEn.status:
            return status;
        case CEn.examineStatus:
            return examineStatus;
        case CEn.complete:
            return complete;
        }
        return 0;
    }

    public Product setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Product setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.coursesid:
            return setCoursesid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.examineStatus:
            return setExamineStatus(value2);
        case CEn.complete:
            return setComplete(value2);
        }
        return this;
    }

    public Product changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Product changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.coursesid:
            return changeCoursesid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.examineStatus:
            return changeExamineStatus(value2);
        case CEn.complete:
            return changeComplete(value2);
        }
        return this;
    }

    public Product changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Product changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.coursesid:
            return changeCoursesidWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.examineStatus:
            return changeExamineStatusWithMin(value2, _min);
        case CEn.complete:
            return changeCompleteWithMin(value2, _min);
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

    public Product setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Product setDouble(String fieldEn, double value2) {
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
        case CEn.name: 
            return name;
        case CEn.imgurl: 
            return imgurl;
        case CEn.descr: 
            return descr;
        case CEn.examineDes: 
            return examineDes;
        case CEn.cruces: 
            return cruces;
        case CEn.protection: 
            return protection;
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
        case CEn.coursesid:
            return coursesid;
        case CEn.name:
            return name;
        case CEn.imgurl:
            return imgurl;
        case CEn.descr:
            return descr;
        case CEn.lhubid:
            return lhubid;
        case CEn.status:
            return status;
        case CEn.examineStatus:
            return examineStatus;
        case CEn.examineDes:
            return examineDes;
        case CEn.complete:
            return complete;
        case CEn.createtime:
            return createtime;
        case CEn.cruces:
            return cruces;
        case CEn.isRecommend:
            return isRecommend;
        case CEn.lastTime4Recommend:
            return lastTime4Recommend;
        case CEn.protection:
            return protection;
        case CEn.lasttime:
            return lasttime;
        }
        return null;
    }

    public Product setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Product setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Product setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Product setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.imgurl:
            return setImgurl(value2);
        case CEn.descr:
            return setDescr(value2);
        case CEn.examineDes:
            return setExamineDes(value2);
        case CEn.cruces:
            return setCruces(value2);
        case CEn.protection:
            return setProtection(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Product setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Product setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Product");
        result.put("id", id);
        result.put("coursesid", coursesid);
        result.put("name", name);
        result.put("imgurl", imgurl);
        result.put("descr", descr);
        result.put("lhubid", lhubid);
        result.put("status", status);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("complete", complete);
        result.put("createtime", createtime);
        result.put("cruces", cruces);
        result.put("isRecommend", isRecommend);
        result.put("lastTime4Recommend", lastTime4Recommend);
        result.put("protection", protection);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("coursesid", coursesid);
        result.put("name", name);
        result.put("imgurl", imgurl);
        result.put("descr", descr);
        result.put("lhubid", lhubid);
        result.put("status", status);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("complete", complete);
        result.put("createtime", createtime);
        result.put("cruces", cruces);
        result.put("isRecommend", isRecommend);
        result.put("lastTime4Recommend", lastTime4Recommend);
        result.put("protection", protection);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Product");
        result.put("id", id);
        result.put("coursesid", coursesid);
        result.put("name", name);
        result.put("imgurl", imgurl);
        result.put("descr", descr);
        result.put("lhubid", lhubid);
        result.put("status", status);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("complete", complete);
        result.put("createtime", createtime);
        result.put("cruces", cruces);
        result.put("isRecommend", isRecommend);
        result.put("lastTime4Recommend", lastTime4Recommend);
        result.put("protection", protection);
        result.put("lasttime", lasttime);
        return result;
    }

    public Product mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer coursesid = MapEx.getInt(e, "coursesid");
        String name = MapEx.getString(e, "name");
        String imgurl = MapEx.getString(e, "imgurl");
        String descr = MapEx.getString(e, "descr");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer status = MapEx.getInt(e, "status");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        Integer complete = MapEx.getInt(e, "complete");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String cruces = MapEx.getString(e, "cruces");
        Boolean isRecommend = MapEx.getBoolean(e, "isRecommend");
        java.util.Date lastTime4Recommend = MapEx.getDate(e, "lastTime4Recommend");
        String protection = MapEx.getString(e, "protection");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(coursesid == null) coursesid = 0;
        if(name == null) name = "";
        if(imgurl == null) imgurl = "";
        if(descr == null) descr = "";
        if(lhubid == null) lhubid = 0;
        if(status == null) status = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(complete == null) complete = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(cruces == null) cruces = "";
        if(isRecommend == null) isRecommend = false;
        if(lastTime4Recommend == null) lastTime4Recommend = new java.util.Date();
        if(protection == null) protection = "";
        if(lasttime == null) lasttime = new java.util.Date();

        setId(id);
        setCoursesid(coursesid);
        setName(name);
        setImgurl(imgurl);
        setDescr(descr);
        setLhubid(lhubid);
        setStatus(status);
        setExamineStatus(examineStatus);
        setExamineDes(examineDes);
        setComplete(complete);
        setCreatetime(createtime);
        setCruces(cruces);
        setIsRecommend(isRecommend);
        setLastTime4Recommend(lastTime4Recommend);
        setProtection(protection);
        setLasttime(lasttime);

        return this;
    }

    public static final Product mapTo(Map e){
        Product result = new Product();

        Integer id = MapEx.getInt(e, "id");
        Integer coursesid = MapEx.getInt(e, "coursesid");
        String name = MapEx.getString(e, "name");
        String imgurl = MapEx.getString(e, "imgurl");
        String descr = MapEx.getString(e, "descr");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer status = MapEx.getInt(e, "status");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        Integer complete = MapEx.getInt(e, "complete");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String cruces = MapEx.getString(e, "cruces");
        Boolean isRecommend = MapEx.getBoolean(e, "isRecommend");
        java.util.Date lastTime4Recommend = MapEx.getDate(e, "lastTime4Recommend");
        String protection = MapEx.getString(e, "protection");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(coursesid == null) coursesid = 0;
        if(name == null) name = "";
        if(imgurl == null) imgurl = "";
        if(descr == null) descr = "";
        if(lhubid == null) lhubid = 0;
        if(status == null) status = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(complete == null) complete = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(cruces == null) cruces = "";
        if(isRecommend == null) isRecommend = false;
        if(lastTime4Recommend == null) lastTime4Recommend = new java.util.Date();
        if(protection == null) protection = "";
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.coursesid = coursesid;
        result.name = name;
        result.imgurl = imgurl;
        result.descr = descr;
        result.lhubid = lhubid;
        result.status = status;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.complete = complete;
        result.createtime = createtime;
        result.cruces = cruces;
        result.isRecommend = isRecommend;
        result.lastTime4Recommend = lastTime4Recommend;
        result.protection = protection;
        result.lasttime = lasttime;

        return result;
    }

    public static final Product originalTo(Map e){
        Product result = new Product();

        Integer id = MapEx.getInt(e, "id");
        Integer coursesid = MapEx.getInt(e, "coursesid");
        String name = MapEx.getString(e, "name");
        String imgurl = MapEx.getString(e, "imgurl");
        String descr = MapEx.getString(e, "descr");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer status = MapEx.getInt(e, "status");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        Integer complete = MapEx.getInt(e, "complete");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String cruces = MapEx.getString(e, "cruces");
        Boolean isRecommend = MapEx.getBoolean(e, "isRecommend");
        java.util.Date lastTime4Recommend = MapEx.getDate(e, "lastTime4Recommend");
        String protection = MapEx.getString(e, "protection");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(coursesid == null) coursesid = 0;
        if(name == null) name = "";
        if(imgurl == null) imgurl = "";
        if(descr == null) descr = "";
        if(lhubid == null) lhubid = 0;
        if(status == null) status = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(complete == null) complete = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(cruces == null) cruces = "";
        if(isRecommend == null) isRecommend = false;
        if(lastTime4Recommend == null) lastTime4Recommend = new java.util.Date();
        if(protection == null) protection = "";
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.coursesid = coursesid;
        result.name = name;
        result.imgurl = imgurl;
        result.descr = descr;
        result.lhubid = lhubid;
        result.status = status;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.complete = complete;
        result.createtime = createtime;
        result.cruces = cruces;
        result.isRecommend = isRecommend;
        result.lastTime4Recommend = lastTime4Recommend;
        result.protection = protection;
        result.lasttime = lasttime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Product createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 16);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "coursesid", coursesid);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "imgurl", imgurl);
            writeMapEntry(out, "descr", descr);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "examineStatus", examineStatus);
            writeMapEntry(out, "examineDes", examineDes);
            writeMapEntry(out, "complete", complete);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "cruces", cruces);
            writeMapEntry(out, "isRecommend", isRecommend);
            writeMapEntry(out, "lastTime4Recommend", lastTime4Recommend);
            writeMapEntry(out, "protection", protection);
            writeMapEntry(out, "lasttime", lasttime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Product createFor(byte[] b) throws Exception {
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
    public final Adcourses getAdcoursesFkCoursesid() { // adcourses - coursesid
        return AdcoursesEntity.getByKey(coursesid);
    }

    public final int countAdcoursesFkCoursesid() { // adcourses - coursesid
        return getAdcoursesFkCoursesid() == null ? 0 : 1;
    }

    public final Learnhub getLearnhubFkLhubid() { // learnhub - lhubid
        return LearnhubEntity.getByKey(lhubid);
    }

    public final int countLearnhubFkLhubid() { // learnhub - lhubid
        return getLearnhubFkLhubid() == null ? 0 : 1;
    }

    public final List<Kind> getKindsFkProductid() { // kind - productid
        return KindEntity.getByProductid(id);
    }

    public final int countKindsFkProductid() { // kind - productid
        return KindEntity.countByProductid(id);
    }

    public final List<Product0examtype> getProduct0examtypesFkProductid() { // product0examtype - productid
        return Product0examtypeEntity.getByProductid(id);
    }

    public final int countProduct0examtypesFkProductid() { // product0examtype - productid
        return Product0examtypeEntity.countByProductid(id);
    }

    public static final Product loadByKey(int id) {
        return ProductEntity.getByKey(id);
    }

    public static final Future<Product> asyncByKey(int id) {
        return ProductEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Product insert() {
        Product result = ProductEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Product asyncInsert() {
        Product result = ProductEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Product insert2() {
        return ProductEntity.insert2(this);
    }

    public final Product asyncInsert2() {
        Product result = ProductEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Product update() {
        return ProductEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ProductEntity.asyncUpdate( this );
        return true;
    }

    public final Product insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ProductEntity.delete(id);
    }

    public final void asyncDelete() {
        ProductEntity.asyncDelete(id);
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

    public Product clone2() {
        try{
            return (Product) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
