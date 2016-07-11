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

//learnhall3_design - errorfeedback
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Errorfeedback extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -996710399; // com.learnhall.db.bean.Errorfeedback

    public static String TABLENAME = "errorfeedback";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String examid = "examid"; public static final String nmExam = "nmExam"; public static final String optid = "optid"; public static final String opttype = "opttype"; public static final String optgid = "optgid"; public static final String customerid = "customerid"; public static final String nmCust = "nmCust"; public static final String description = "description"; public static final String lhubid = "lhubid"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String examid = "examid"; public static final String nmExam = "nmExam"; public static final String optid = "optid"; public static final String opttype = "opttype"; public static final String optgid = "optgid"; public static final String customerid = "customerid"; public static final String nmCust = "nmCust"; public static final String description = "description"; public static final String lhubid = "lhubid"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "examid", "nmExam", "optid", "opttype", "optgid", "customerid", "nmCust", "description", "lhubid", "status", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "INT", "INT", "INT", "INT", "TINYTEXT", "TEXT", "INT", "INT", "DATETIME"};


    public int id;
    public int examid;
    public String nmExam;
    public int optid;
    public int opttype;
    public int optgid;
    public int customerid;
    public String nmCust;
    public String description;
    public int lhubid;
    public int status;
    public java.util.Date createtime;

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

    public Errorfeedback setId(int id){
        this.id = id;
        return this;
    }

    public int getExamid(){
        return examid;
    }

    public Errorfeedback setExamid(int examid){
        int _old = this.examid;
        this.examid = examid;
        changeIt(Col.examid, _old, examid);
        return this;
    }

    public Errorfeedback changeExamid(int examid){
        return setExamid(this.examid + examid);
    }

    public Errorfeedback changeExamidWithMin(int examid, int _min){
        int _v2 = this.examid + examid;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeExamidWithMax(int examid, int _max){
        int _v2 = this.examid + examid;
        return setExamid(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeExamidWithMinMax(int examid, int _min, int _max){
        int _v2 = this.examid + examid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public String getNmExam(){
        return nmExam;
    }

    public Errorfeedback setNmExam(String nmExam){
        String _old = this.nmExam;
        this.nmExam = nmExam;
        changeIt(Col.nmExam, _old, nmExam);
        return this;
    }

    public int getOptid(){
        return optid;
    }

    public Errorfeedback setOptid(int optid){
        int _old = this.optid;
        this.optid = optid;
        changeIt(Col.optid, _old, optid);
        return this;
    }

    public Errorfeedback changeOptid(int optid){
        return setOptid(this.optid + optid);
    }

    public Errorfeedback changeOptidWithMin(int optid, int _min){
        int _v2 = this.optid + optid;
        return setOptid(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeOptidWithMax(int optid, int _max){
        int _v2 = this.optid + optid;
        return setOptid(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeOptidWithMinMax(int optid, int _min, int _max){
        int _v2 = this.optid + optid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOptid(_v2 < _min  ? _min : _v2);
    }

    public int getOpttype(){
        return opttype;
    }

    public Errorfeedback setOpttype(int opttype){
        int _old = this.opttype;
        this.opttype = opttype;
        changeIt(Col.opttype, _old, opttype);
        return this;
    }

    public Errorfeedback changeOpttype(int opttype){
        return setOpttype(this.opttype + opttype);
    }

    public Errorfeedback changeOpttypeWithMin(int opttype, int _min){
        int _v2 = this.opttype + opttype;
        return setOpttype(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeOpttypeWithMax(int opttype, int _max){
        int _v2 = this.opttype + opttype;
        return setOpttype(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeOpttypeWithMinMax(int opttype, int _min, int _max){
        int _v2 = this.opttype + opttype;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOpttype(_v2 < _min  ? _min : _v2);
    }

    public int getOptgid(){
        return optgid;
    }

    public Errorfeedback setOptgid(int optgid){
        int _old = this.optgid;
        this.optgid = optgid;
        changeIt(Col.optgid, _old, optgid);
        return this;
    }

    public Errorfeedback changeOptgid(int optgid){
        return setOptgid(this.optgid + optgid);
    }

    public Errorfeedback changeOptgidWithMin(int optgid, int _min){
        int _v2 = this.optgid + optgid;
        return setOptgid(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeOptgidWithMax(int optgid, int _max){
        int _v2 = this.optgid + optgid;
        return setOptgid(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeOptgidWithMinMax(int optgid, int _min, int _max){
        int _v2 = this.optgid + optgid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOptgid(_v2 < _min  ? _min : _v2);
    }

    public int getCustomerid(){
        return customerid;
    }

    public Errorfeedback setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Errorfeedback changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Errorfeedback changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public String getNmCust(){
        return nmCust;
    }

    public Errorfeedback setNmCust(String nmCust){
        String _old = this.nmCust;
        this.nmCust = nmCust;
        changeIt(Col.nmCust, _old, nmCust);
        return this;
    }

    public String getDescription(){
        return description;
    }

    public Errorfeedback setDescription(String description){
        String _old = this.description;
        this.description = description;
        changeIt(Col.description, _old, description);
        return this;
    }

    public int getLhubid(){
        return lhubid;
    }

    public Errorfeedback setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Errorfeedback changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Errorfeedback changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Errorfeedback setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Errorfeedback changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Errorfeedback changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Errorfeedback changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Errorfeedback changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Errorfeedback setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Errorfeedback v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Errorfeedback v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Errorfeedback newErrorfeedback(Integer id, Integer examid, String nmExam, Integer optid, Integer opttype, Integer optgid, Integer customerid, String nmCust, String description, Integer lhubid, Integer status, java.util.Date createtime) {
        Errorfeedback result = new Errorfeedback();
        result.id = id;
        result.examid = examid;
        result.nmExam = nmExam;
        result.optid = optid;
        result.opttype = opttype;
        result.optgid = optgid;
        result.customerid = customerid;
        result.nmCust = nmCust;
        result.description = description;
        result.lhubid = lhubid;
        result.status = status;
        result.createtime = createtime;
        return result;
    }

    public static Errorfeedback newErrorfeedback(Errorfeedback errorfeedback) {
        Errorfeedback result = new Errorfeedback();
        result.id = errorfeedback.id;
        result.examid = errorfeedback.examid;
        result.nmExam = errorfeedback.nmExam;
        result.optid = errorfeedback.optid;
        result.opttype = errorfeedback.opttype;
        result.optgid = errorfeedback.optgid;
        result.customerid = errorfeedback.customerid;
        result.nmCust = errorfeedback.nmCust;
        result.description = errorfeedback.description;
        result.lhubid = errorfeedback.lhubid;
        result.status = errorfeedback.status;
        result.createtime = errorfeedback.createtime;
        return result;
    }

    public Errorfeedback createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getErrorfeedback(){
        Errorfeedback errorfeedback = null; // errorfeedback
        { // new and insert Errorfeedback (errorfeedback)
            int id = 0; 	// id
            int examid = 0; 	// examid
            String nmExam = ""; 	// nmExam
            int optid = 0; 	// optid
            int opttype = 0; 	// opttype
            int optgid = 0; 	// optgid
            int customerid = 0; 	// customerid
            String nmCust = ""; 	// nmCust
            String description = ""; 	// description
            int lhubid = 0; 	// lhubid
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            errorfeedback = Errorfeedback.newErrorfeedback(id, examid, nmExam, optid, opttype, optgid, customerid, nmCust, description, lhubid, status, createtime);
        }
        errorfeedback = errorfeedback.insert();

        int id = errorfeedback.getId(); 	// id
        int examid = errorfeedback.getExamid(); 	// examid
        String nmExam = errorfeedback.getNmExam(); 	// nmExam
        int optid = errorfeedback.getOptid(); 	// optid
        int opttype = errorfeedback.getOpttype(); 	// opttype
        int optgid = errorfeedback.getOptgid(); 	// optgid
        int customerid = errorfeedback.getCustomerid(); 	// customerid
        String nmCust = errorfeedback.getNmCust(); 	// nmCust
        String description = errorfeedback.getDescription(); 	// description
        int lhubid = errorfeedback.getLhubid(); 	// lhubid
        int status = errorfeedback.getStatus(); 	// status
        Date createtime = errorfeedback.getCreatetime(); 	// createtime
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
        case CEn.optid:
            return optid;
        case CEn.opttype:
            return opttype;
        case CEn.optgid:
            return optgid;
        case CEn.customerid:
            return customerid;
        case CEn.lhubid:
            return lhubid;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Errorfeedback setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Errorfeedback setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.examid:
            return setExamid(value2);
        case CEn.optid:
            return setOptid(value2);
        case CEn.opttype:
            return setOpttype(value2);
        case CEn.optgid:
            return setOptgid(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Errorfeedback changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Errorfeedback changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.examid:
            return changeExamid(value2);
        case CEn.optid:
            return changeOptid(value2);
        case CEn.opttype:
            return changeOpttype(value2);
        case CEn.optgid:
            return changeOptgid(value2);
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Errorfeedback changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Errorfeedback changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.examid:
            return changeExamidWithMin(value2, _min);
        case CEn.optid:
            return changeOptidWithMin(value2, _min);
        case CEn.opttype:
            return changeOpttypeWithMin(value2, _min);
        case CEn.optgid:
            return changeOptgidWithMin(value2, _min);
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
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

    public Errorfeedback setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Errorfeedback setDouble(String fieldEn, double value2) {
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
        case CEn.nmExam: 
            return nmExam;
        case CEn.nmCust: 
            return nmCust;
        case CEn.description: 
            return description;
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
        case CEn.nmExam:
            return nmExam;
        case CEn.optid:
            return optid;
        case CEn.opttype:
            return opttype;
        case CEn.optgid:
            return optgid;
        case CEn.customerid:
            return customerid;
        case CEn.nmCust:
            return nmCust;
        case CEn.description:
            return description;
        case CEn.lhubid:
            return lhubid;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Errorfeedback setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Errorfeedback setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Errorfeedback setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Errorfeedback setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.nmExam:
            return setNmExam(value2);
        case CEn.nmCust:
            return setNmCust(value2);
        case CEn.description:
            return setDescription(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Errorfeedback setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Errorfeedback setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Errorfeedback");
        result.put("id", id);
        result.put("examid", examid);
        result.put("nmExam", nmExam);
        result.put("optid", optid);
        result.put("opttype", opttype);
        result.put("optgid", optgid);
        result.put("customerid", customerid);
        result.put("nmCust", nmCust);
        result.put("description", description);
        result.put("lhubid", lhubid);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("examid", examid);
        result.put("nmExam", nmExam);
        result.put("optid", optid);
        result.put("opttype", opttype);
        result.put("optgid", optgid);
        result.put("customerid", customerid);
        result.put("nmCust", nmCust);
        result.put("description", description);
        result.put("lhubid", lhubid);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Errorfeedback");
        result.put("id", id);
        result.put("examid", examid);
        result.put("nmExam", nmExam);
        result.put("optid", optid);
        result.put("opttype", opttype);
        result.put("optgid", optgid);
        result.put("customerid", customerid);
        result.put("nmCust", nmCust);
        result.put("description", description);
        result.put("lhubid", lhubid);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Errorfeedback mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String nmExam = MapEx.getString(e, "nmExam");
        Integer optid = MapEx.getInt(e, "optid");
        Integer opttype = MapEx.getInt(e, "opttype");
        Integer optgid = MapEx.getInt(e, "optgid");
        Integer customerid = MapEx.getInt(e, "customerid");
        String nmCust = MapEx.getString(e, "nmCust");
        String description = MapEx.getString(e, "description");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(nmExam == null) nmExam = "";
        if(optid == null) optid = 0;
        if(opttype == null) opttype = 0;
        if(optgid == null) optgid = 0;
        if(customerid == null) customerid = 0;
        if(nmCust == null) nmCust = "";
        if(description == null) description = "";
        if(lhubid == null) lhubid = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setExamid(examid);
        setNmExam(nmExam);
        setOptid(optid);
        setOpttype(opttype);
        setOptgid(optgid);
        setCustomerid(customerid);
        setNmCust(nmCust);
        setDescription(description);
        setLhubid(lhubid);
        setStatus(status);
        setCreatetime(createtime);

        return this;
    }

    public static final Errorfeedback mapTo(Map e){
        Errorfeedback result = new Errorfeedback();

        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String nmExam = MapEx.getString(e, "nmExam");
        Integer optid = MapEx.getInt(e, "optid");
        Integer opttype = MapEx.getInt(e, "opttype");
        Integer optgid = MapEx.getInt(e, "optgid");
        Integer customerid = MapEx.getInt(e, "customerid");
        String nmCust = MapEx.getString(e, "nmCust");
        String description = MapEx.getString(e, "description");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(nmExam == null) nmExam = "";
        if(optid == null) optid = 0;
        if(opttype == null) opttype = 0;
        if(optgid == null) optgid = 0;
        if(customerid == null) customerid = 0;
        if(nmCust == null) nmCust = "";
        if(description == null) description = "";
        if(lhubid == null) lhubid = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.examid = examid;
        result.nmExam = nmExam;
        result.optid = optid;
        result.opttype = opttype;
        result.optgid = optgid;
        result.customerid = customerid;
        result.nmCust = nmCust;
        result.description = description;
        result.lhubid = lhubid;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public static final Errorfeedback originalTo(Map e){
        Errorfeedback result = new Errorfeedback();

        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String nmExam = MapEx.getString(e, "nmExam");
        Integer optid = MapEx.getInt(e, "optid");
        Integer opttype = MapEx.getInt(e, "opttype");
        Integer optgid = MapEx.getInt(e, "optgid");
        Integer customerid = MapEx.getInt(e, "customerid");
        String nmCust = MapEx.getString(e, "nmCust");
        String description = MapEx.getString(e, "description");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(nmExam == null) nmExam = "";
        if(optid == null) optid = 0;
        if(opttype == null) opttype = 0;
        if(optgid == null) optgid = 0;
        if(customerid == null) customerid = 0;
        if(nmCust == null) nmCust = "";
        if(description == null) description = "";
        if(lhubid == null) lhubid = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.examid = examid;
        result.nmExam = nmExam;
        result.optid = optid;
        result.opttype = opttype;
        result.optgid = optgid;
        result.customerid = customerid;
        result.nmCust = nmCust;
        result.description = description;
        result.lhubid = lhubid;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Errorfeedback createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 12);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "examid", examid);
            writeMapEntry(out, "nmExam", nmExam);
            writeMapEntry(out, "optid", optid);
            writeMapEntry(out, "opttype", opttype);
            writeMapEntry(out, "optgid", optgid);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "nmCust", nmCust);
            writeMapEntry(out, "description", description);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Errorfeedback createFor(byte[] b) throws Exception {
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
    public final Optquestion getOptquestionFkOptid() { // optquestion - optid
        return OptquestionEntity.getByKey(optid);
    }

    public final int countOptquestionFkOptid() { // optquestion - optid
        return getOptquestionFkOptid() == null ? 0 : 1;
    }

    public final Customer getCustomerFkCustomerid() { // customer - customerid
        return CustomerEntity.getByKey(customerid);
    }

    public final int countCustomerFkCustomerid() { // customer - customerid
        return getCustomerFkCustomerid() == null ? 0 : 1;
    }

    public final Exam getExamFkExamid() { // exam - examid
        return ExamEntity.getByKey(examid);
    }

    public final int countExamFkExamid() { // exam - examid
        return getExamFkExamid() == null ? 0 : 1;
    }

    public final Learnhub getLearnhubFkLhubid() { // learnhub - lhubid
        return LearnhubEntity.getByKey(lhubid);
    }

    public final int countLearnhubFkLhubid() { // learnhub - lhubid
        return getLearnhubFkLhubid() == null ? 0 : 1;
    }

    public static final Errorfeedback loadByKey(int id) {
        return ErrorfeedbackEntity.getByKey(id);
    }

    public static final Future<Errorfeedback> asyncByKey(int id) {
        return ErrorfeedbackEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Errorfeedback insert() {
        Errorfeedback result = ErrorfeedbackEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Errorfeedback asyncInsert() {
        Errorfeedback result = ErrorfeedbackEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Errorfeedback insert2() {
        return ErrorfeedbackEntity.insert2(this);
    }

    public final Errorfeedback asyncInsert2() {
        Errorfeedback result = ErrorfeedbackEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Errorfeedback update() {
        return ErrorfeedbackEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ErrorfeedbackEntity.asyncUpdate( this );
        return true;
    }

    public final Errorfeedback insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ErrorfeedbackEntity.delete(id);
    }

    public final void asyncDelete() {
        ErrorfeedbackEntity.asyncDelete(id);
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

    public Errorfeedback clone2() {
        try{
            return (Errorfeedback) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
