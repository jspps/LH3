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

//learnhall3_design - exam
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Exam extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -336579797; // com.learnhall.db.bean.Exam

    public static String TABLENAME = "exam";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String examtypeid = "examtypeid"; public static final String name = "name"; public static final String score = "score"; public static final String lhubid = "lhubid"; public static final String examtime = "examtime"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String productid = "productid"; public static final String pro0etpid = "pro0etpid"; public static final String descstr = "descstr";  }
    public static final class CEn { public static final String id = "id"; public static final String examtypeid = "examtypeid"; public static final String name = "name"; public static final String score = "score"; public static final String lhubid = "lhubid"; public static final String examtime = "examtime"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String productid = "productid"; public static final String pro0etpid = "pro0etpid"; public static final String descstr = "descstr";  }
    public static final String[] carrays ={"id", "examtypeid", "name", "score", "lhubid", "examtime", "status", "createtime", "productid", "pro0etpid", "descstr"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "INT", "INT", "INT", "DATETIME", "INT", "INT", "TEXT"};


    public int id;
    public int examtypeid;
    public String name;
    public int score;
    public int lhubid;
    public int examtime;
    public int status;
    public java.util.Date createtime;
    public int productid;
    public int pro0etpid;
    public String descstr;

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

    public Exam setId(int id){
        this.id = id;
        return this;
    }

    public int getExamtypeid(){
        return examtypeid;
    }

    public Exam setExamtypeid(int examtypeid){
        int _old = this.examtypeid;
        this.examtypeid = examtypeid;
        changeIt(Col.examtypeid, _old, examtypeid);
        return this;
    }

    public Exam changeExamtypeid(int examtypeid){
        return setExamtypeid(this.examtypeid + examtypeid);
    }

    public Exam changeExamtypeidWithMin(int examtypeid, int _min){
        int _v2 = this.examtypeid + examtypeid;
        return setExamtypeid(_v2 < _min  ? _min : _v2);
    }

    public Exam changeExamtypeidWithMax(int examtypeid, int _max){
        int _v2 = this.examtypeid + examtypeid;
        return setExamtypeid(_v2 > _max  ? _max : _v2);
    }

    public Exam changeExamtypeidWithMinMax(int examtypeid, int _min, int _max){
        int _v2 = this.examtypeid + examtypeid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamtypeid(_v2 < _min  ? _min : _v2);
    }

    public String getName(){
        return name;
    }

    public Exam setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public int getScore(){
        return score;
    }

    public Exam setScore(int score){
        int _old = this.score;
        this.score = score;
        changeIt(Col.score, _old, score);
        return this;
    }

    public Exam changeScore(int score){
        return setScore(this.score + score);
    }

    public Exam changeScoreWithMin(int score, int _min){
        int _v2 = this.score + score;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public Exam changeScoreWithMax(int score, int _max){
        int _v2 = this.score + score;
        return setScore(_v2 > _max  ? _max : _v2);
    }

    public Exam changeScoreWithMinMax(int score, int _min, int _max){
        int _v2 = this.score + score;
        _v2 = _v2 > _max  ? _max : _v2;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public int getLhubid(){
        return lhubid;
    }

    public Exam setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Exam changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Exam changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Exam changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Exam changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getExamtime(){
        return examtime;
    }

    public Exam setExamtime(int examtime){
        int _old = this.examtime;
        this.examtime = examtime;
        changeIt(Col.examtime, _old, examtime);
        return this;
    }

    public Exam changeExamtime(int examtime){
        return setExamtime(this.examtime + examtime);
    }

    public Exam changeExamtimeWithMin(int examtime, int _min){
        int _v2 = this.examtime + examtime;
        return setExamtime(_v2 < _min  ? _min : _v2);
    }

    public Exam changeExamtimeWithMax(int examtime, int _max){
        int _v2 = this.examtime + examtime;
        return setExamtime(_v2 > _max  ? _max : _v2);
    }

    public Exam changeExamtimeWithMinMax(int examtime, int _min, int _max){
        int _v2 = this.examtime + examtime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamtime(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Exam setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Exam changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Exam changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Exam changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Exam changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Exam setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getProductid(){
        return productid;
    }

    public Exam setProductid(int productid){
        int _old = this.productid;
        this.productid = productid;
        changeIt(Col.productid, _old, productid);
        return this;
    }

    public Exam changeProductid(int productid){
        return setProductid(this.productid + productid);
    }

    public Exam changeProductidWithMin(int productid, int _min){
        int _v2 = this.productid + productid;
        return setProductid(_v2 < _min  ? _min : _v2);
    }

    public Exam changeProductidWithMax(int productid, int _max){
        int _v2 = this.productid + productid;
        return setProductid(_v2 > _max  ? _max : _v2);
    }

    public Exam changeProductidWithMinMax(int productid, int _min, int _max){
        int _v2 = this.productid + productid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProductid(_v2 < _min  ? _min : _v2);
    }

    public int getPro0etpid(){
        return pro0etpid;
    }

    public Exam setPro0etpid(int pro0etpid){
        int _old = this.pro0etpid;
        this.pro0etpid = pro0etpid;
        changeIt(Col.pro0etpid, _old, pro0etpid);
        return this;
    }

    public Exam changePro0etpid(int pro0etpid){
        return setPro0etpid(this.pro0etpid + pro0etpid);
    }

    public Exam changePro0etpidWithMin(int pro0etpid, int _min){
        int _v2 = this.pro0etpid + pro0etpid;
        return setPro0etpid(_v2 < _min  ? _min : _v2);
    }

    public Exam changePro0etpidWithMax(int pro0etpid, int _max){
        int _v2 = this.pro0etpid + pro0etpid;
        return setPro0etpid(_v2 > _max  ? _max : _v2);
    }

    public Exam changePro0etpidWithMinMax(int pro0etpid, int _min, int _max){
        int _v2 = this.pro0etpid + pro0etpid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPro0etpid(_v2 < _min  ? _min : _v2);
    }

    public String getDescstr(){
        return descstr;
    }

    public Exam setDescstr(String descstr){
        String _old = this.descstr;
        this.descstr = descstr;
        changeIt(Col.descstr, _old, descstr);
        return this;
    }

    public int compareTo(Exam v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Exam v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Exam newExam(Integer id, Integer examtypeid, String name, Integer score, Integer lhubid, Integer examtime, Integer status, java.util.Date createtime, Integer productid, Integer pro0etpid, String descstr) {
        Exam result = new Exam();
        result.id = id;
        result.examtypeid = examtypeid;
        result.name = name;
        result.score = score;
        result.lhubid = lhubid;
        result.examtime = examtime;
        result.status = status;
        result.createtime = createtime;
        result.productid = productid;
        result.pro0etpid = pro0etpid;
        result.descstr = descstr;
        return result;
    }

    public static Exam newExam(Exam exam) {
        Exam result = new Exam();
        result.id = exam.id;
        result.examtypeid = exam.examtypeid;
        result.name = exam.name;
        result.score = exam.score;
        result.lhubid = exam.lhubid;
        result.examtime = exam.examtime;
        result.status = exam.status;
        result.createtime = exam.createtime;
        result.productid = exam.productid;
        result.pro0etpid = exam.pro0etpid;
        result.descstr = exam.descstr;
        return result;
    }

    public Exam createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getExam(){
        Exam exam = null; // exam
        { // new and insert Exam (exam)
            int id = 0; 	// id
            int examtypeid = 0; 	// examtypeid
            String name = ""; 	// name
            int score = 0; 	// score
            int lhubid = 0; 	// lhubid
            int examtime = 0; 	// examtime
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            int productid = 0; 	// productid
            int pro0etpid = 0; 	// pro0etpid
            String descstr = ""; 	// descstr
            exam = Exam.newExam(id, examtypeid, name, score, lhubid, examtime, status, createtime, productid, pro0etpid, descstr);
        }
        exam = exam.insert();

        int id = exam.getId(); 	// id
        int examtypeid = exam.getExamtypeid(); 	// examtypeid
        String name = exam.getName(); 	// name
        int score = exam.getScore(); 	// score
        int lhubid = exam.getLhubid(); 	// lhubid
        int examtime = exam.getExamtime(); 	// examtime
        int status = exam.getStatus(); 	// status
        Date createtime = exam.getCreatetime(); 	// createtime
        int productid = exam.getProductid(); 	// productid
        int pro0etpid = exam.getPro0etpid(); 	// pro0etpid
        String descstr = exam.getDescstr(); 	// descstr
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
        case CEn.examtypeid:
            return examtypeid;
        case CEn.score:
            return score;
        case CEn.lhubid:
            return lhubid;
        case CEn.examtime:
            return examtime;
        case CEn.status:
            return status;
        case CEn.productid:
            return productid;
        case CEn.pro0etpid:
            return pro0etpid;
        }
        return 0;
    }

    public Exam setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Exam setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.examtypeid:
            return setExamtypeid(value2);
        case CEn.score:
            return setScore(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.examtime:
            return setExamtime(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.productid:
            return setProductid(value2);
        case CEn.pro0etpid:
            return setPro0etpid(value2);
        }
        return this;
    }

    public Exam changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Exam changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.examtypeid:
            return changeExamtypeid(value2);
        case CEn.score:
            return changeScore(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.examtime:
            return changeExamtime(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.productid:
            return changeProductid(value2);
        case CEn.pro0etpid:
            return changePro0etpid(value2);
        }
        return this;
    }

    public Exam changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Exam changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.examtypeid:
            return changeExamtypeidWithMin(value2, _min);
        case CEn.score:
            return changeScoreWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.examtime:
            return changeExamtimeWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.productid:
            return changeProductidWithMin(value2, _min);
        case CEn.pro0etpid:
            return changePro0etpidWithMin(value2, _min);
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

    public Exam setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Exam setDouble(String fieldEn, double value2) {
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
        case CEn.descstr: 
            return descstr;
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
        case CEn.examtypeid:
            return examtypeid;
        case CEn.name:
            return name;
        case CEn.score:
            return score;
        case CEn.lhubid:
            return lhubid;
        case CEn.examtime:
            return examtime;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        case CEn.productid:
            return productid;
        case CEn.pro0etpid:
            return pro0etpid;
        case CEn.descstr:
            return descstr;
        }
        return null;
    }

    public Exam setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Exam setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Exam setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Exam setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.descstr:
            return setDescstr(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Exam setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Exam setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Exam");
        result.put("id", id);
        result.put("examtypeid", examtypeid);
        result.put("name", name);
        result.put("score", score);
        result.put("lhubid", lhubid);
        result.put("examtime", examtime);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("productid", productid);
        result.put("pro0etpid", pro0etpid);
        result.put("descstr", descstr);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("examtypeid", examtypeid);
        result.put("name", name);
        result.put("score", score);
        result.put("lhubid", lhubid);
        result.put("examtime", examtime);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("productid", productid);
        result.put("pro0etpid", pro0etpid);
        result.put("descstr", descstr);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Exam");
        result.put("id", id);
        result.put("examtypeid", examtypeid);
        result.put("name", name);
        result.put("score", score);
        result.put("lhubid", lhubid);
        result.put("examtime", examtime);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("productid", productid);
        result.put("pro0etpid", pro0etpid);
        result.put("descstr", descstr);
        return result;
    }

    public Exam mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer examtypeid = MapEx.getInt(e, "examtypeid");
        String name = MapEx.getString(e, "name");
        Integer score = MapEx.getInt(e, "score");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer examtime = MapEx.getInt(e, "examtime");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer productid = MapEx.getInt(e, "productid");
        Integer pro0etpid = MapEx.getInt(e, "pro0etpid");
        String descstr = MapEx.getString(e, "descstr");

        if(id == null) id = 0;
        if(examtypeid == null) examtypeid = 0;
        if(name == null) name = "";
        if(score == null) score = 0;
        if(lhubid == null) lhubid = 0;
        if(examtime == null) examtime = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(productid == null) productid = 0;
        if(pro0etpid == null) pro0etpid = 0;
        if(descstr == null) descstr = "";

        setId(id);
        setExamtypeid(examtypeid);
        setName(name);
        setScore(score);
        setLhubid(lhubid);
        setExamtime(examtime);
        setStatus(status);
        setCreatetime(createtime);
        setProductid(productid);
        setPro0etpid(pro0etpid);
        setDescstr(descstr);

        return this;
    }

    public static final Exam mapTo(Map e){
        Exam result = new Exam();

        Integer id = MapEx.getInt(e, "id");
        Integer examtypeid = MapEx.getInt(e, "examtypeid");
        String name = MapEx.getString(e, "name");
        Integer score = MapEx.getInt(e, "score");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer examtime = MapEx.getInt(e, "examtime");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer productid = MapEx.getInt(e, "productid");
        Integer pro0etpid = MapEx.getInt(e, "pro0etpid");
        String descstr = MapEx.getString(e, "descstr");

        if(id == null) id = 0;
        if(examtypeid == null) examtypeid = 0;
        if(name == null) name = "";
        if(score == null) score = 0;
        if(lhubid == null) lhubid = 0;
        if(examtime == null) examtime = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(productid == null) productid = 0;
        if(pro0etpid == null) pro0etpid = 0;
        if(descstr == null) descstr = "";

        result.id = id;
        result.examtypeid = examtypeid;
        result.name = name;
        result.score = score;
        result.lhubid = lhubid;
        result.examtime = examtime;
        result.status = status;
        result.createtime = createtime;
        result.productid = productid;
        result.pro0etpid = pro0etpid;
        result.descstr = descstr;

        return result;
    }

    public static final Exam originalTo(Map e){
        Exam result = new Exam();

        Integer id = MapEx.getInt(e, "id");
        Integer examtypeid = MapEx.getInt(e, "examtypeid");
        String name = MapEx.getString(e, "name");
        Integer score = MapEx.getInt(e, "score");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer examtime = MapEx.getInt(e, "examtime");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer productid = MapEx.getInt(e, "productid");
        Integer pro0etpid = MapEx.getInt(e, "pro0etpid");
        String descstr = MapEx.getString(e, "descstr");

        if(id == null) id = 0;
        if(examtypeid == null) examtypeid = 0;
        if(name == null) name = "";
        if(score == null) score = 0;
        if(lhubid == null) lhubid = 0;
        if(examtime == null) examtime = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(productid == null) productid = 0;
        if(pro0etpid == null) pro0etpid = 0;
        if(descstr == null) descstr = "";

        result.id = id;
        result.examtypeid = examtypeid;
        result.name = name;
        result.score = score;
        result.lhubid = lhubid;
        result.examtime = examtime;
        result.status = status;
        result.createtime = createtime;
        result.productid = productid;
        result.pro0etpid = pro0etpid;
        result.descstr = descstr;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Exam createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 11);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "examtypeid", examtypeid);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "score", score);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "examtime", examtime);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "productid", productid);
            writeMapEntry(out, "pro0etpid", pro0etpid);
            writeMapEntry(out, "descstr", descstr);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Exam createFor(byte[] b) throws Exception {
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
    public final Examtype getExamtypeFkExamtypeid() { // examtype - examtypeid
        return ExamtypeEntity.getByKey(examtypeid);
    }

    public final int countExamtypeFkExamtypeid() { // examtype - examtypeid
        return getExamtypeFkExamtypeid() == null ? 0 : 1;
    }

    public final List<Errorfeedback> getErrorfeedbacksFkExamid() { // errorfeedback - examid
        return ErrorfeedbackEntity.getByExamid(id);
    }

    public final int countErrorfeedbacksFkExamid() { // errorfeedback - examid
        return ErrorfeedbackEntity.countByExamid(id);
    }

    public final List<Examcatalog> getExamcatalogsFkExamid() { // examcatalog - examid
        return ExamcatalogEntity.getByExamid(id);
    }

    public final int countExamcatalogsFkExamid() { // examcatalog - examid
        return ExamcatalogEntity.countByExamid(id);
    }

    public final List<Recordanswer> getRecordanswersFkExamid() { // recordanswer - examid
        return RecordanswerEntity.getByExamid(id);
    }

    public final int countRecordanswersFkExamid() { // recordanswer - examid
        return RecordanswerEntity.countByExamid(id);
    }

    public static final Exam loadByKey(int id) {
        return ExamEntity.getByKey(id);
    }

    public static final Future<Exam> asyncByKey(int id) {
        return ExamEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Exam insert() {
        Exam result = ExamEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Exam asyncInsert() {
        Exam result = ExamEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Exam insert2() {
        return ExamEntity.insert2(this);
    }

    public final Exam asyncInsert2() {
        Exam result = ExamEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Exam update() {
        return ExamEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ExamEntity.asyncUpdate( this );
        return true;
    }

    public final Exam insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ExamEntity.delete(id);
    }

    public final void asyncDelete() {
        ExamEntity.asyncDelete(id);
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

    public Exam clone2() {
        try{
            return (Exam) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
