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

//learnhall3_design - recordanswer
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Recordanswer extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -2051859877; // com.learnhall.db.bean.Recordanswer

    public static String TABLENAME = "recordanswer";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String examid = "examid"; public static final String nmExam = "nmExam"; public static final String customerid = "customerid"; public static final String custname = "custname"; public static final String score = "score"; public static final String avecorrectrate = "avecorrectrate"; public static final String lasttime = "lasttime"; public static final String num = "num"; public static final String status = "status"; public static final String anwers = "anwers"; public static final String createtime = "createtime"; public static final String costTimes = "costTimes"; public static final String catalog = "catalog"; public static final String examType = "examType"; public static final String score4ques = "score4ques"; public static final String kindid = "kindid"; public static final String lens4exam = "lens4exam"; public static final String lens4right = "lens4right"; public static final String courseid = "courseid"; public static final String lhubid = "lhubid";  }
    public static final class CEn { public static final String id = "id"; public static final String examid = "examid"; public static final String nmExam = "nmExam"; public static final String customerid = "customerid"; public static final String custname = "custname"; public static final String score = "score"; public static final String avecorrectrate = "avecorrectrate"; public static final String lasttime = "lasttime"; public static final String num = "num"; public static final String status = "status"; public static final String anwers = "anwers"; public static final String createtime = "createtime"; public static final String costTimes = "costTimes"; public static final String catalog = "catalog"; public static final String examType = "examType"; public static final String score4ques = "score4ques"; public static final String kindid = "kindid"; public static final String lens4exam = "lens4exam"; public static final String lens4right = "lens4right"; public static final String courseid = "courseid"; public static final String lhubid = "lhubid";  }
    public static final String[] carrays ={"id", "examid", "nmExam", "customerid", "custname", "score", "avecorrectrate", "lasttime", "num", "status", "anwers", "createtime", "costTimes", "catalog", "examType", "score4ques", "kindid", "lens4exam", "lens4right", "courseid", "lhubid"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "INT", "VARCHAR", "INT", "INT", "DATETIME", "INT", "INT", "BLOB", "DATETIME", "INT", "BLOB", "INT", "MEDIUMBLOB", "INT", "INT", "INT", "INT", "INT"};


    public int id;
    public int examid;
    public String nmExam;
    public int customerid;
    public String custname;
    public int score;
    public int avecorrectrate;
    public java.util.Date lasttime;
    public int num;
    public int status;
    public byte[] anwers;
    public java.util.Date createtime;
    public int costTimes;
    public byte[] catalog;
    public int examType;
    public byte[] score4ques;
    public int kindid;
    public int lens4exam;
    public int lens4right;
    public int courseid;
    public int lhubid;

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

    public Recordanswer setId(int id){
        this.id = id;
        return this;
    }

    public int getExamid(){
        return examid;
    }

    public Recordanswer setExamid(int examid){
        int _old = this.examid;
        this.examid = examid;
        changeIt(Col.examid, _old, examid);
        return this;
    }

    public Recordanswer changeExamid(int examid){
        return setExamid(this.examid + examid);
    }

    public Recordanswer changeExamidWithMin(int examid, int _min){
        int _v2 = this.examid + examid;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeExamidWithMax(int examid, int _max){
        int _v2 = this.examid + examid;
        return setExamid(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeExamidWithMinMax(int examid, int _min, int _max){
        int _v2 = this.examid + examid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public String getNmExam(){
        return nmExam;
    }

    public Recordanswer setNmExam(String nmExam){
        String _old = this.nmExam;
        this.nmExam = nmExam;
        changeIt(Col.nmExam, _old, nmExam);
        return this;
    }

    public int getCustomerid(){
        return customerid;
    }

    public Recordanswer setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Recordanswer changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Recordanswer changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public String getCustname(){
        return custname;
    }

    public Recordanswer setCustname(String custname){
        String _old = this.custname;
        this.custname = custname;
        changeIt(Col.custname, _old, custname);
        return this;
    }

    public int getScore(){
        return score;
    }

    public Recordanswer setScore(int score){
        int _old = this.score;
        this.score = score;
        changeIt(Col.score, _old, score);
        return this;
    }

    public Recordanswer changeScore(int score){
        return setScore(this.score + score);
    }

    public Recordanswer changeScoreWithMin(int score, int _min){
        int _v2 = this.score + score;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeScoreWithMax(int score, int _max){
        int _v2 = this.score + score;
        return setScore(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeScoreWithMinMax(int score, int _min, int _max){
        int _v2 = this.score + score;
        _v2 = _v2 > _max  ? _max : _v2;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public int getAvecorrectrate(){
        return avecorrectrate;
    }

    public Recordanswer setAvecorrectrate(int avecorrectrate){
        int _old = this.avecorrectrate;
        this.avecorrectrate = avecorrectrate;
        changeIt(Col.avecorrectrate, _old, avecorrectrate);
        return this;
    }

    public Recordanswer changeAvecorrectrate(int avecorrectrate){
        return setAvecorrectrate(this.avecorrectrate + avecorrectrate);
    }

    public Recordanswer changeAvecorrectrateWithMin(int avecorrectrate, int _min){
        int _v2 = this.avecorrectrate + avecorrectrate;
        return setAvecorrectrate(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeAvecorrectrateWithMax(int avecorrectrate, int _max){
        int _v2 = this.avecorrectrate + avecorrectrate;
        return setAvecorrectrate(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeAvecorrectrateWithMinMax(int avecorrectrate, int _min, int _max){
        int _v2 = this.avecorrectrate + avecorrectrate;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAvecorrectrate(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Recordanswer setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public int getNum(){
        return num;
    }

    public Recordanswer setNum(int num){
        int _old = this.num;
        this.num = num;
        changeIt(Col.num, _old, num);
        return this;
    }

    public Recordanswer changeNum(int num){
        return setNum(this.num + num);
    }

    public Recordanswer changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Recordanswer setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Recordanswer changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Recordanswer changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public byte[] getAnwers(){
        return anwers;
    }

    public Recordanswer setAnwers(byte[] anwers){
        byte[] _old = this.anwers;
        this.anwers = anwers;
        changeIt(Col.anwers, _old, anwers);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Recordanswer setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getCostTimes(){
        return costTimes;
    }

    public Recordanswer setCostTimes(int costTimes){
        int _old = this.costTimes;
        this.costTimes = costTimes;
        changeIt(Col.costTimes, _old, costTimes);
        return this;
    }

    public Recordanswer changeCostTimes(int costTimes){
        return setCostTimes(this.costTimes + costTimes);
    }

    public Recordanswer changeCostTimesWithMin(int costTimes, int _min){
        int _v2 = this.costTimes + costTimes;
        return setCostTimes(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeCostTimesWithMax(int costTimes, int _max){
        int _v2 = this.costTimes + costTimes;
        return setCostTimes(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeCostTimesWithMinMax(int costTimes, int _min, int _max){
        int _v2 = this.costTimes + costTimes;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCostTimes(_v2 < _min  ? _min : _v2);
    }

    public byte[] getCatalog(){
        return catalog;
    }

    public Recordanswer setCatalog(byte[] catalog){
        byte[] _old = this.catalog;
        this.catalog = catalog;
        changeIt(Col.catalog, _old, catalog);
        return this;
    }

    public int getExamType(){
        return examType;
    }

    public Recordanswer setExamType(int examType){
        int _old = this.examType;
        this.examType = examType;
        changeIt(Col.examType, _old, examType);
        return this;
    }

    public Recordanswer changeExamType(int examType){
        return setExamType(this.examType + examType);
    }

    public Recordanswer changeExamTypeWithMin(int examType, int _min){
        int _v2 = this.examType + examType;
        return setExamType(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeExamTypeWithMax(int examType, int _max){
        int _v2 = this.examType + examType;
        return setExamType(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeExamTypeWithMinMax(int examType, int _min, int _max){
        int _v2 = this.examType + examType;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamType(_v2 < _min  ? _min : _v2);
    }

    public byte[] getScore4ques(){
        return score4ques;
    }

    public Recordanswer setScore4ques(byte[] score4ques){
        byte[] _old = this.score4ques;
        this.score4ques = score4ques;
        changeIt(Col.score4ques, _old, score4ques);
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Recordanswer setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Recordanswer changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Recordanswer changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getLens4exam(){
        return lens4exam;
    }

    public Recordanswer setLens4exam(int lens4exam){
        int _old = this.lens4exam;
        this.lens4exam = lens4exam;
        changeIt(Col.lens4exam, _old, lens4exam);
        return this;
    }

    public Recordanswer changeLens4exam(int lens4exam){
        return setLens4exam(this.lens4exam + lens4exam);
    }

    public Recordanswer changeLens4examWithMin(int lens4exam, int _min){
        int _v2 = this.lens4exam + lens4exam;
        return setLens4exam(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeLens4examWithMax(int lens4exam, int _max){
        int _v2 = this.lens4exam + lens4exam;
        return setLens4exam(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeLens4examWithMinMax(int lens4exam, int _min, int _max){
        int _v2 = this.lens4exam + lens4exam;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLens4exam(_v2 < _min  ? _min : _v2);
    }

    public int getLens4right(){
        return lens4right;
    }

    public Recordanswer setLens4right(int lens4right){
        int _old = this.lens4right;
        this.lens4right = lens4right;
        changeIt(Col.lens4right, _old, lens4right);
        return this;
    }

    public Recordanswer changeLens4right(int lens4right){
        return setLens4right(this.lens4right + lens4right);
    }

    public Recordanswer changeLens4rightWithMin(int lens4right, int _min){
        int _v2 = this.lens4right + lens4right;
        return setLens4right(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeLens4rightWithMax(int lens4right, int _max){
        int _v2 = this.lens4right + lens4right;
        return setLens4right(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeLens4rightWithMinMax(int lens4right, int _min, int _max){
        int _v2 = this.lens4right + lens4right;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLens4right(_v2 < _min  ? _min : _v2);
    }

    public int getCourseid(){
        return courseid;
    }

    public Recordanswer setCourseid(int courseid){
        int _old = this.courseid;
        this.courseid = courseid;
        changeIt(Col.courseid, _old, courseid);
        return this;
    }

    public Recordanswer changeCourseid(int courseid){
        return setCourseid(this.courseid + courseid);
    }

    public Recordanswer changeCourseidWithMin(int courseid, int _min){
        int _v2 = this.courseid + courseid;
        return setCourseid(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeCourseidWithMax(int courseid, int _max){
        int _v2 = this.courseid + courseid;
        return setCourseid(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeCourseidWithMinMax(int courseid, int _min, int _max){
        int _v2 = this.courseid + courseid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCourseid(_v2 < _min  ? _min : _v2);
    }

    public int getLhubid(){
        return lhubid;
    }

    public Recordanswer setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Recordanswer changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Recordanswer changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Recordanswer changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Recordanswer changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Recordanswer v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Recordanswer v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Recordanswer newRecordanswer(Integer id, Integer examid, String nmExam, Integer customerid, String custname, Integer score, Integer avecorrectrate, java.util.Date lasttime, Integer num, Integer status, byte[] anwers, java.util.Date createtime, Integer costTimes, byte[] catalog, Integer examType, byte[] score4ques, Integer kindid, Integer lens4exam, Integer lens4right, Integer courseid, Integer lhubid) {
        Recordanswer result = new Recordanswer();
        result.id = id;
        result.examid = examid;
        result.nmExam = nmExam;
        result.customerid = customerid;
        result.custname = custname;
        result.score = score;
        result.avecorrectrate = avecorrectrate;
        result.lasttime = lasttime;
        result.num = num;
        result.status = status;
        result.anwers = anwers;
        result.createtime = createtime;
        result.costTimes = costTimes;
        result.catalog = catalog;
        result.examType = examType;
        result.score4ques = score4ques;
        result.kindid = kindid;
        result.lens4exam = lens4exam;
        result.lens4right = lens4right;
        result.courseid = courseid;
        result.lhubid = lhubid;
        return result;
    }

    public static Recordanswer newRecordanswer(Recordanswer recordanswer) {
        Recordanswer result = new Recordanswer();
        result.id = recordanswer.id;
        result.examid = recordanswer.examid;
        result.nmExam = recordanswer.nmExam;
        result.customerid = recordanswer.customerid;
        result.custname = recordanswer.custname;
        result.score = recordanswer.score;
        result.avecorrectrate = recordanswer.avecorrectrate;
        result.lasttime = recordanswer.lasttime;
        result.num = recordanswer.num;
        result.status = recordanswer.status;
        result.anwers = recordanswer.anwers;
        result.createtime = recordanswer.createtime;
        result.costTimes = recordanswer.costTimes;
        result.catalog = recordanswer.catalog;
        result.examType = recordanswer.examType;
        result.score4ques = recordanswer.score4ques;
        result.kindid = recordanswer.kindid;
        result.lens4exam = recordanswer.lens4exam;
        result.lens4right = recordanswer.lens4right;
        result.courseid = recordanswer.courseid;
        result.lhubid = recordanswer.lhubid;
        return result;
    }

    public Recordanswer createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecordanswer(){
        Recordanswer recordanswer = null; // recordanswer
        { // new and insert Recordanswer (recordanswer)
            int id = 0; 	// id
            int examid = 0; 	// examid
            String nmExam = ""; 	// nmExam
            int customerid = 0; 	// customerid
            String custname = ""; 	// custname
            int score = 0; 	// score
            int avecorrectrate = 0; 	// avecorrectrate
            Date lasttime = new Date(); 	// lasttime
            int num = 0; 	// num
            int status = 0; 	// status
            byte[] anwers = new byte[0]; 	// anwers
            Date createtime = new Date(); 	// createtime
            int costTimes = 0; 	// costTimes
            byte[] catalog = new byte[0]; 	// catalog
            int examType = 0; 	// examType
            byte[] score4ques = new byte[0]; 	// score4ques
            int kindid = 0; 	// kindid
            int lens4exam = 0; 	// lens4exam
            int lens4right = 0; 	// lens4right
            int courseid = 0; 	// courseid
            int lhubid = 0; 	// lhubid
            recordanswer = Recordanswer.newRecordanswer(id, examid, nmExam, customerid, custname, score, avecorrectrate, lasttime, num, status, anwers, createtime, costTimes, catalog, examType, score4ques, kindid, lens4exam, lens4right, courseid, lhubid);
        }
        recordanswer = recordanswer.insert();

        int id = recordanswer.getId(); 	// id
        int examid = recordanswer.getExamid(); 	// examid
        String nmExam = recordanswer.getNmExam(); 	// nmExam
        int customerid = recordanswer.getCustomerid(); 	// customerid
        String custname = recordanswer.getCustname(); 	// custname
        int score = recordanswer.getScore(); 	// score
        int avecorrectrate = recordanswer.getAvecorrectrate(); 	// avecorrectrate
        Date lasttime = recordanswer.getLasttime(); 	// lasttime
        int num = recordanswer.getNum(); 	// num
        int status = recordanswer.getStatus(); 	// status
        byte[] anwers = recordanswer.getAnwers(); 	// anwers
        Date createtime = recordanswer.getCreatetime(); 	// createtime
        int costTimes = recordanswer.getCostTimes(); 	// costTimes
        byte[] catalog = recordanswer.getCatalog(); 	// catalog
        int examType = recordanswer.getExamType(); 	// examType
        byte[] score4ques = recordanswer.getScore4ques(); 	// score4ques
        int kindid = recordanswer.getKindid(); 	// kindid
        int lens4exam = recordanswer.getLens4exam(); 	// lens4exam
        int lens4right = recordanswer.getLens4right(); 	// lens4right
        int courseid = recordanswer.getCourseid(); 	// courseid
        int lhubid = recordanswer.getLhubid(); 	// lhubid
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
        case CEn.customerid:
            return customerid;
        case CEn.score:
            return score;
        case CEn.avecorrectrate:
            return avecorrectrate;
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        case CEn.costTimes:
            return costTimes;
        case CEn.examType:
            return examType;
        case CEn.kindid:
            return kindid;
        case CEn.lens4exam:
            return lens4exam;
        case CEn.lens4right:
            return lens4right;
        case CEn.courseid:
            return courseid;
        case CEn.lhubid:
            return lhubid;
        }
        return 0;
    }

    public Recordanswer setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Recordanswer setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.examid:
            return setExamid(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.score:
            return setScore(value2);
        case CEn.avecorrectrate:
            return setAvecorrectrate(value2);
        case CEn.num:
            return setNum(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.costTimes:
            return setCostTimes(value2);
        case CEn.examType:
            return setExamType(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.lens4exam:
            return setLens4exam(value2);
        case CEn.lens4right:
            return setLens4right(value2);
        case CEn.courseid:
            return setCourseid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        }
        return this;
    }

    public Recordanswer changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Recordanswer changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.examid:
            return changeExamid(value2);
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.score:
            return changeScore(value2);
        case CEn.avecorrectrate:
            return changeAvecorrectrate(value2);
        case CEn.num:
            return changeNum(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.costTimes:
            return changeCostTimes(value2);
        case CEn.examType:
            return changeExamType(value2);
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.lens4exam:
            return changeLens4exam(value2);
        case CEn.lens4right:
            return changeLens4right(value2);
        case CEn.courseid:
            return changeCourseid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        }
        return this;
    }

    public Recordanswer changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Recordanswer changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.examid:
            return changeExamidWithMin(value2, _min);
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.score:
            return changeScoreWithMin(value2, _min);
        case CEn.avecorrectrate:
            return changeAvecorrectrateWithMin(value2, _min);
        case CEn.num:
            return changeNumWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.costTimes:
            return changeCostTimesWithMin(value2, _min);
        case CEn.examType:
            return changeExamTypeWithMin(value2, _min);
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.lens4exam:
            return changeLens4examWithMin(value2, _min);
        case CEn.lens4right:
            return changeLens4rightWithMin(value2, _min);
        case CEn.courseid:
            return changeCourseidWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
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

    public Recordanswer setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Recordanswer setDouble(String fieldEn, double value2) {
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
        case CEn.custname: 
            return custname;
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
        case CEn.customerid:
            return customerid;
        case CEn.custname:
            return custname;
        case CEn.score:
            return score;
        case CEn.avecorrectrate:
            return avecorrectrate;
        case CEn.lasttime:
            return lasttime;
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        case CEn.anwers:
            return anwers;
        case CEn.createtime:
            return createtime;
        case CEn.costTimes:
            return costTimes;
        case CEn.catalog:
            return catalog;
        case CEn.examType:
            return examType;
        case CEn.score4ques:
            return score4ques;
        case CEn.kindid:
            return kindid;
        case CEn.lens4exam:
            return lens4exam;
        case CEn.lens4right:
            return lens4right;
        case CEn.courseid:
            return courseid;
        case CEn.lhubid:
            return lhubid;
        }
        return null;
    }

    public Recordanswer setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Recordanswer setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Recordanswer setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Recordanswer setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.nmExam:
            return setNmExam(value2);
        case CEn.custname:
            return setCustname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Recordanswer setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Recordanswer setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Recordanswer");
        result.put("id", id);
        result.put("examid", examid);
        result.put("nmExam", nmExam);
        result.put("customerid", customerid);
        result.put("custname", custname);
        result.put("score", score);
        result.put("avecorrectrate", avecorrectrate);
        result.put("lasttime", lasttime);
        result.put("num", num);
        result.put("status", status);
        result.put("anwers", anwers);
        result.put("createtime", createtime);
        result.put("costTimes", costTimes);
        result.put("catalog", catalog);
        result.put("examType", examType);
        result.put("score4ques", score4ques);
        result.put("kindid", kindid);
        result.put("lens4exam", lens4exam);
        result.put("lens4right", lens4right);
        result.put("courseid", courseid);
        result.put("lhubid", lhubid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("examid", examid);
        result.put("nmExam", nmExam);
        result.put("customerid", customerid);
        result.put("custname", custname);
        result.put("score", score);
        result.put("avecorrectrate", avecorrectrate);
        result.put("lasttime", lasttime);
        result.put("num", num);
        result.put("status", status);
        result.put("anwers", anwers);
        result.put("createtime", createtime);
        result.put("costTimes", costTimes);
        result.put("catalog", catalog);
        result.put("examType", examType);
        result.put("score4ques", score4ques);
        result.put("kindid", kindid);
        result.put("lens4exam", lens4exam);
        result.put("lens4right", lens4right);
        result.put("courseid", courseid);
        result.put("lhubid", lhubid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Recordanswer");
        result.put("id", id);
        result.put("examid", examid);
        result.put("nmExam", nmExam);
        result.put("customerid", customerid);
        result.put("custname", custname);
        result.put("score", score);
        result.put("avecorrectrate", avecorrectrate);
        result.put("lasttime", lasttime);
        result.put("num", num);
        result.put("status", status);
        result.put("anwers", anwers);
        result.put("createtime", createtime);
        result.put("costTimes", costTimes);
        result.put("catalog", catalog);
        result.put("examType", examType);
        result.put("score4ques", score4ques);
        result.put("kindid", kindid);
        result.put("lens4exam", lens4exam);
        result.put("lens4right", lens4right);
        result.put("courseid", courseid);
        result.put("lhubid", lhubid);
        return result;
    }

    public Recordanswer mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String nmExam = MapEx.getString(e, "nmExam");
        Integer customerid = MapEx.getInt(e, "customerid");
        String custname = MapEx.getString(e, "custname");
        Integer score = MapEx.getInt(e, "score");
        Integer avecorrectrate = MapEx.getInt(e, "avecorrectrate");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        byte[] anwers = MapEx.getByteArray(e, "anwers");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer costTimes = MapEx.getInt(e, "costTimes");
        byte[] catalog = MapEx.getByteArray(e, "catalog");
        Integer examType = MapEx.getInt(e, "examType");
        byte[] score4ques = MapEx.getByteArray(e, "score4ques");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lens4exam = MapEx.getInt(e, "lens4exam");
        Integer lens4right = MapEx.getInt(e, "lens4right");
        Integer courseid = MapEx.getInt(e, "courseid");
        Integer lhubid = MapEx.getInt(e, "lhubid");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(nmExam == null) nmExam = "";
        if(customerid == null) customerid = 0;
        if(custname == null) custname = "";
        if(score == null) score = 0;
        if(avecorrectrate == null) avecorrectrate = 0;
        if(lasttime == null) lasttime = new java.util.Date();
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(anwers == null) anwers = new byte[0];
        if(createtime == null) createtime = new java.util.Date();
        if(costTimes == null) costTimes = 0;
        if(catalog == null) catalog = new byte[0];
        if(examType == null) examType = 0;
        if(score4ques == null) score4ques = new byte[0];
        if(kindid == null) kindid = 0;
        if(lens4exam == null) lens4exam = 0;
        if(lens4right == null) lens4right = 0;
        if(courseid == null) courseid = 0;
        if(lhubid == null) lhubid = 0;

        setId(id);
        setExamid(examid);
        setNmExam(nmExam);
        setCustomerid(customerid);
        setCustname(custname);
        setScore(score);
        setAvecorrectrate(avecorrectrate);
        setLasttime(lasttime);
        setNum(num);
        setStatus(status);
        setAnwers(anwers);
        setCreatetime(createtime);
        setCostTimes(costTimes);
        setCatalog(catalog);
        setExamType(examType);
        setScore4ques(score4ques);
        setKindid(kindid);
        setLens4exam(lens4exam);
        setLens4right(lens4right);
        setCourseid(courseid);
        setLhubid(lhubid);

        return this;
    }

    public static final Recordanswer mapTo(Map e){
        Recordanswer result = new Recordanswer();

        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String nmExam = MapEx.getString(e, "nmExam");
        Integer customerid = MapEx.getInt(e, "customerid");
        String custname = MapEx.getString(e, "custname");
        Integer score = MapEx.getInt(e, "score");
        Integer avecorrectrate = MapEx.getInt(e, "avecorrectrate");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        byte[] anwers = MapEx.getByteArray(e, "anwers");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer costTimes = MapEx.getInt(e, "costTimes");
        byte[] catalog = MapEx.getByteArray(e, "catalog");
        Integer examType = MapEx.getInt(e, "examType");
        byte[] score4ques = MapEx.getByteArray(e, "score4ques");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lens4exam = MapEx.getInt(e, "lens4exam");
        Integer lens4right = MapEx.getInt(e, "lens4right");
        Integer courseid = MapEx.getInt(e, "courseid");
        Integer lhubid = MapEx.getInt(e, "lhubid");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(nmExam == null) nmExam = "";
        if(customerid == null) customerid = 0;
        if(custname == null) custname = "";
        if(score == null) score = 0;
        if(avecorrectrate == null) avecorrectrate = 0;
        if(lasttime == null) lasttime = new java.util.Date();
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(anwers == null) anwers = new byte[0];
        if(createtime == null) createtime = new java.util.Date();
        if(costTimes == null) costTimes = 0;
        if(catalog == null) catalog = new byte[0];
        if(examType == null) examType = 0;
        if(score4ques == null) score4ques = new byte[0];
        if(kindid == null) kindid = 0;
        if(lens4exam == null) lens4exam = 0;
        if(lens4right == null) lens4right = 0;
        if(courseid == null) courseid = 0;
        if(lhubid == null) lhubid = 0;

        result.id = id;
        result.examid = examid;
        result.nmExam = nmExam;
        result.customerid = customerid;
        result.custname = custname;
        result.score = score;
        result.avecorrectrate = avecorrectrate;
        result.lasttime = lasttime;
        result.num = num;
        result.status = status;
        result.anwers = anwers;
        result.createtime = createtime;
        result.costTimes = costTimes;
        result.catalog = catalog;
        result.examType = examType;
        result.score4ques = score4ques;
        result.kindid = kindid;
        result.lens4exam = lens4exam;
        result.lens4right = lens4right;
        result.courseid = courseid;
        result.lhubid = lhubid;

        return result;
    }

    public static final Recordanswer originalTo(Map e){
        Recordanswer result = new Recordanswer();

        Integer id = MapEx.getInt(e, "id");
        Integer examid = MapEx.getInt(e, "examid");
        String nmExam = MapEx.getString(e, "nmExam");
        Integer customerid = MapEx.getInt(e, "customerid");
        String custname = MapEx.getString(e, "custname");
        Integer score = MapEx.getInt(e, "score");
        Integer avecorrectrate = MapEx.getInt(e, "avecorrectrate");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        byte[] anwers = MapEx.getByteArray(e, "anwers");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer costTimes = MapEx.getInt(e, "costTimes");
        byte[] catalog = MapEx.getByteArray(e, "catalog");
        Integer examType = MapEx.getInt(e, "examType");
        byte[] score4ques = MapEx.getByteArray(e, "score4ques");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lens4exam = MapEx.getInt(e, "lens4exam");
        Integer lens4right = MapEx.getInt(e, "lens4right");
        Integer courseid = MapEx.getInt(e, "courseid");
        Integer lhubid = MapEx.getInt(e, "lhubid");

        if(id == null) id = 0;
        if(examid == null) examid = 0;
        if(nmExam == null) nmExam = "";
        if(customerid == null) customerid = 0;
        if(custname == null) custname = "";
        if(score == null) score = 0;
        if(avecorrectrate == null) avecorrectrate = 0;
        if(lasttime == null) lasttime = new java.util.Date();
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(anwers == null) anwers = new byte[0];
        if(createtime == null) createtime = new java.util.Date();
        if(costTimes == null) costTimes = 0;
        if(catalog == null) catalog = new byte[0];
        if(examType == null) examType = 0;
        if(score4ques == null) score4ques = new byte[0];
        if(kindid == null) kindid = 0;
        if(lens4exam == null) lens4exam = 0;
        if(lens4right == null) lens4right = 0;
        if(courseid == null) courseid = 0;
        if(lhubid == null) lhubid = 0;

        result.id = id;
        result.examid = examid;
        result.nmExam = nmExam;
        result.customerid = customerid;
        result.custname = custname;
        result.score = score;
        result.avecorrectrate = avecorrectrate;
        result.lasttime = lasttime;
        result.num = num;
        result.status = status;
        result.anwers = anwers;
        result.createtime = createtime;
        result.costTimes = costTimes;
        result.catalog = catalog;
        result.examType = examType;
        result.score4ques = score4ques;
        result.kindid = kindid;
        result.lens4exam = lens4exam;
        result.lens4right = lens4right;
        result.courseid = courseid;
        result.lhubid = lhubid;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Recordanswer createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 21);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "examid", examid);
            writeMapEntry(out, "nmExam", nmExam);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "custname", custname);
            writeMapEntry(out, "score", score);
            writeMapEntry(out, "avecorrectrate", avecorrectrate);
            writeMapEntry(out, "lasttime", lasttime);
            writeMapEntry(out, "num", num);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "anwers", anwers);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "costTimes", costTimes);
            writeMapEntry(out, "catalog", catalog);
            writeMapEntry(out, "examType", examType);
            writeMapEntry(out, "score4ques", score4ques);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "lens4exam", lens4exam);
            writeMapEntry(out, "lens4right", lens4right);
            writeMapEntry(out, "courseid", courseid);
            writeMapEntry(out, "lhubid", lhubid);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Recordanswer createFor(byte[] b) throws Exception {
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

    public static final Recordanswer loadByKey(int id) {
        return RecordanswerEntity.getByKey(id);
    }

    public static final Future<Recordanswer> asyncByKey(int id) {
        return RecordanswerEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Recordanswer insert() {
        Recordanswer result = RecordanswerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Recordanswer asyncInsert() {
        Recordanswer result = RecordanswerEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Recordanswer insert2() {
        return RecordanswerEntity.insert2(this);
    }

    public final Recordanswer asyncInsert2() {
        Recordanswer result = RecordanswerEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Recordanswer update() {
        return RecordanswerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        RecordanswerEntity.asyncUpdate( this );
        return true;
    }

    public final Recordanswer insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return RecordanswerEntity.delete(id);
    }

    public final void asyncDelete() {
        RecordanswerEntity.asyncDelete(id);
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

    public Recordanswer clone2() {
        try{
            return (Recordanswer) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
