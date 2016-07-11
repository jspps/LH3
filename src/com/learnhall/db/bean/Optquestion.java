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

//learnhall3_design - optquestion
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Optquestion extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1003698419; // com.learnhall.db.bean.Optquestion

    public static String TABLENAME = "optquestion";

    public static final String primary = "optid";

    public static final class Col { public static final String optid = "optid"; public static final String examid = "examid"; public static final String type = "type"; public static final String content = "content"; public static final String right_2 = "right_2"; public static final String analyse = "analyse"; public static final String voiceurl = "voiceurl"; public static final String videourl = "videourl"; public static final String position = "position"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String examcatalogid = "examcatalogid"; public static final String answernum = "answernum"; public static final String gid = "gid"; public static final String imgPic = "imgPic";  }
    public static final class CEn { public static final String optid = "optid"; public static final String examid = "examid"; public static final String type = "type"; public static final String content = "content"; public static final String right_2 = "right_2"; public static final String analyse = "analyse"; public static final String voiceurl = "voiceurl"; public static final String videourl = "videourl"; public static final String position = "position"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String examcatalogid = "examcatalogid"; public static final String answernum = "answernum"; public static final String gid = "gid"; public static final String imgPic = "imgPic";  }
    public static final String[] carrays ={"optid", "examid", "type", "content", "right_2", "analyse", "voiceurl", "videourl", "position", "status", "createtime", "examcatalogid", "answernum", "gid", "imgPic"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TEXT", "TEXT", "TEXT", "VARCHAR", "VARCHAR", "TINYTEXT", "INT", "DATETIME", "INT", "INT", "INT", "TINYTEXT"};


    public int optid;
    public int examid;
    public int type;
    public String content;
    public String right_2;
    public String analyse;
    public String voiceurl;
    public String videourl;
    public String position;
    public int status;
    public java.util.Date createtime;
    public int examcatalogid;
    public int answernum;
    public int gid;
    public String imgPic;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return optid;
    }

    public static String _key(int optid) {
        return PStr.b(TABLENAME).a("-").e(optid);
    }

    public int getOptid(){
        return optid;
    }

    public Optquestion setOptid(int optid){
        this.optid = optid;
        return this;
    }

    public int getExamid(){
        return examid;
    }

    public Optquestion setExamid(int examid){
        int _old = this.examid;
        this.examid = examid;
        changeIt(Col.examid, _old, examid);
        return this;
    }

    public Optquestion changeExamid(int examid){
        return setExamid(this.examid + examid);
    }

    public Optquestion changeExamidWithMin(int examid, int _min){
        int _v2 = this.examid + examid;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public Optquestion changeExamidWithMax(int examid, int _max){
        int _v2 = this.examid + examid;
        return setExamid(_v2 > _max  ? _max : _v2);
    }

    public Optquestion changeExamidWithMinMax(int examid, int _min, int _max){
        int _v2 = this.examid + examid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamid(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Optquestion setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Optquestion changeType(int type){
        return setType(this.type + type);
    }

    public Optquestion changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Optquestion changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Optquestion changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public String getContent(){
        return content;
    }

    public Optquestion setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public String getRight_2(){
        return right_2;
    }

    public Optquestion setRight_2(String right_2){
        String _old = this.right_2;
        this.right_2 = right_2;
        changeIt(Col.right_2, _old, right_2);
        return this;
    }

    public String getAnalyse(){
        return analyse;
    }

    public Optquestion setAnalyse(String analyse){
        String _old = this.analyse;
        this.analyse = analyse;
        changeIt(Col.analyse, _old, analyse);
        return this;
    }

    public String getVoiceurl(){
        return voiceurl;
    }

    public Optquestion setVoiceurl(String voiceurl){
        String _old = this.voiceurl;
        this.voiceurl = voiceurl;
        changeIt(Col.voiceurl, _old, voiceurl);
        return this;
    }

    public String getVideourl(){
        return videourl;
    }

    public Optquestion setVideourl(String videourl){
        String _old = this.videourl;
        this.videourl = videourl;
        changeIt(Col.videourl, _old, videourl);
        return this;
    }

    public String getPosition(){
        return position;
    }

    public Optquestion setPosition(String position){
        String _old = this.position;
        this.position = position;
        changeIt(Col.position, _old, position);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Optquestion setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Optquestion changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Optquestion changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Optquestion changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Optquestion changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Optquestion setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getExamcatalogid(){
        return examcatalogid;
    }

    public Optquestion setExamcatalogid(int examcatalogid){
        int _old = this.examcatalogid;
        this.examcatalogid = examcatalogid;
        changeIt(Col.examcatalogid, _old, examcatalogid);
        return this;
    }

    public Optquestion changeExamcatalogid(int examcatalogid){
        return setExamcatalogid(this.examcatalogid + examcatalogid);
    }

    public Optquestion changeExamcatalogidWithMin(int examcatalogid, int _min){
        int _v2 = this.examcatalogid + examcatalogid;
        return setExamcatalogid(_v2 < _min  ? _min : _v2);
    }

    public Optquestion changeExamcatalogidWithMax(int examcatalogid, int _max){
        int _v2 = this.examcatalogid + examcatalogid;
        return setExamcatalogid(_v2 > _max  ? _max : _v2);
    }

    public Optquestion changeExamcatalogidWithMinMax(int examcatalogid, int _min, int _max){
        int _v2 = this.examcatalogid + examcatalogid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamcatalogid(_v2 < _min  ? _min : _v2);
    }

    public int getAnswernum(){
        return answernum;
    }

    public Optquestion setAnswernum(int answernum){
        int _old = this.answernum;
        this.answernum = answernum;
        changeIt(Col.answernum, _old, answernum);
        return this;
    }

    public Optquestion changeAnswernum(int answernum){
        return setAnswernum(this.answernum + answernum);
    }

    public Optquestion changeAnswernumWithMin(int answernum, int _min){
        int _v2 = this.answernum + answernum;
        return setAnswernum(_v2 < _min  ? _min : _v2);
    }

    public Optquestion changeAnswernumWithMax(int answernum, int _max){
        int _v2 = this.answernum + answernum;
        return setAnswernum(_v2 > _max  ? _max : _v2);
    }

    public Optquestion changeAnswernumWithMinMax(int answernum, int _min, int _max){
        int _v2 = this.answernum + answernum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAnswernum(_v2 < _min  ? _min : _v2);
    }

    public int getGid(){
        return gid;
    }

    public Optquestion setGid(int gid){
        int _old = this.gid;
        this.gid = gid;
        changeIt(Col.gid, _old, gid);
        return this;
    }

    public Optquestion changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Optquestion changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Optquestion changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Optquestion changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public String getImgPic(){
        return imgPic;
    }

    public Optquestion setImgPic(String imgPic){
        String _old = this.imgPic;
        this.imgPic = imgPic;
        changeIt(Col.imgPic, _old, imgPic);
        return this;
    }

    public int compareTo(Optquestion v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Optquestion v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Optquestion newOptquestion(Integer optid, Integer examid, Integer type, String content, String right_2, String analyse, String voiceurl, String videourl, String position, Integer status, java.util.Date createtime, Integer examcatalogid, Integer answernum, Integer gid, String imgPic) {
        Optquestion result = new Optquestion();
        result.optid = optid;
        result.examid = examid;
        result.type = type;
        result.content = content;
        result.right_2 = right_2;
        result.analyse = analyse;
        result.voiceurl = voiceurl;
        result.videourl = videourl;
        result.position = position;
        result.status = status;
        result.createtime = createtime;
        result.examcatalogid = examcatalogid;
        result.answernum = answernum;
        result.gid = gid;
        result.imgPic = imgPic;
        return result;
    }

    public static Optquestion newOptquestion(Optquestion optquestion) {
        Optquestion result = new Optquestion();
        result.optid = optquestion.optid;
        result.examid = optquestion.examid;
        result.type = optquestion.type;
        result.content = optquestion.content;
        result.right_2 = optquestion.right_2;
        result.analyse = optquestion.analyse;
        result.voiceurl = optquestion.voiceurl;
        result.videourl = optquestion.videourl;
        result.position = optquestion.position;
        result.status = optquestion.status;
        result.createtime = optquestion.createtime;
        result.examcatalogid = optquestion.examcatalogid;
        result.answernum = optquestion.answernum;
        result.gid = optquestion.gid;
        result.imgPic = optquestion.imgPic;
        return result;
    }

    public Optquestion createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getOptquestion(){
        Optquestion optquestion = null; // optquestion
        { // new and insert Optquestion (optquestion)
            int optid = 0; 	// optid
            int examid = 0; 	// examid
            int type = 0; 	// type
            String content = ""; 	// content
            String right_2 = ""; 	// right_2
            String analyse = ""; 	// analyse
            String voiceurl = ""; 	// voiceurl
            String videourl = ""; 	// videourl
            String position = ""; 	// position
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            int examcatalogid = 0; 	// examcatalogid
            int answernum = 0; 	// answernum
            int gid = 0; 	// gid
            String imgPic = ""; 	// imgPic
            optquestion = Optquestion.newOptquestion(optid, examid, type, content, right_2, analyse, voiceurl, videourl, position, status, createtime, examcatalogid, answernum, gid, imgPic);
        }
        optquestion = optquestion.insert();

        int optid = optquestion.getOptid(); 	// optid
        int examid = optquestion.getExamid(); 	// examid
        int type = optquestion.getType(); 	// type
        String content = optquestion.getContent(); 	// content
        String right_2 = optquestion.getRight_2(); 	// right_2
        String analyse = optquestion.getAnalyse(); 	// analyse
        String voiceurl = optquestion.getVoiceurl(); 	// voiceurl
        String videourl = optquestion.getVideourl(); 	// videourl
        String position = optquestion.getPosition(); 	// position
        int status = optquestion.getStatus(); 	// status
        Date createtime = optquestion.getCreatetime(); 	// createtime
        int examcatalogid = optquestion.getExamcatalogid(); 	// examcatalogid
        int answernum = optquestion.getAnswernum(); 	// answernum
        int gid = optquestion.getGid(); 	// gid
        String imgPic = optquestion.getImgPic(); 	// imgPic
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.optid:
            return optid;
        case CEn.examid:
            return examid;
        case CEn.type:
            return type;
        case CEn.status:
            return status;
        case CEn.examcatalogid:
            return examcatalogid;
        case CEn.answernum:
            return answernum;
        case CEn.gid:
            return gid;
        }
        return 0;
    }

    public Optquestion setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Optquestion setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.optid:
            return setOptid(value2);
        case CEn.examid:
            return setExamid(value2);
        case CEn.type:
            return setType(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.examcatalogid:
            return setExamcatalogid(value2);
        case CEn.answernum:
            return setAnswernum(value2);
        case CEn.gid:
            return setGid(value2);
        }
        return this;
    }

    public Optquestion changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Optquestion changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.examid:
            return changeExamid(value2);
        case CEn.type:
            return changeType(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.examcatalogid:
            return changeExamcatalogid(value2);
        case CEn.answernum:
            return changeAnswernum(value2);
        case CEn.gid:
            return changeGid(value2);
        }
        return this;
    }

    public Optquestion changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Optquestion changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.examid:
            return changeExamidWithMin(value2, _min);
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.examcatalogid:
            return changeExamcatalogidWithMin(value2, _min);
        case CEn.answernum:
            return changeAnswernumWithMin(value2, _min);
        case CEn.gid:
            return changeGidWithMin(value2, _min);
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

    public Optquestion setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Optquestion setDouble(String fieldEn, double value2) {
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
        case CEn.content: 
            return content;
        case CEn.right_2: 
            return right_2;
        case CEn.analyse: 
            return analyse;
        case CEn.voiceurl: 
            return voiceurl;
        case CEn.videourl: 
            return videourl;
        case CEn.position: 
            return position;
        case CEn.imgPic: 
            return imgPic;
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
        case CEn.optid:
            return optid;
        case CEn.examid:
            return examid;
        case CEn.type:
            return type;
        case CEn.content:
            return content;
        case CEn.right_2:
            return right_2;
        case CEn.analyse:
            return analyse;
        case CEn.voiceurl:
            return voiceurl;
        case CEn.videourl:
            return videourl;
        case CEn.position:
            return position;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        case CEn.examcatalogid:
            return examcatalogid;
        case CEn.answernum:
            return answernum;
        case CEn.gid:
            return gid;
        case CEn.imgPic:
            return imgPic;
        }
        return null;
    }

    public Optquestion setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Optquestion setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Optquestion setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Optquestion setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.content:
            return setContent(value2);
        case CEn.right_2:
            return setRight_2(value2);
        case CEn.analyse:
            return setAnalyse(value2);
        case CEn.voiceurl:
            return setVoiceurl(value2);
        case CEn.videourl:
            return setVideourl(value2);
        case CEn.position:
            return setPosition(value2);
        case CEn.imgPic:
            return setImgPic(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Optquestion setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Optquestion setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Optquestion");
        result.put("optid", optid);
        result.put("examid", examid);
        result.put("type", type);
        result.put("content", content);
        result.put("right_2", right_2);
        result.put("analyse", analyse);
        result.put("voiceurl", voiceurl);
        result.put("videourl", videourl);
        result.put("position", position);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("examcatalogid", examcatalogid);
        result.put("answernum", answernum);
        result.put("gid", gid);
        result.put("imgPic", imgPic);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("optid", optid);
        result.put("examid", examid);
        result.put("type", type);
        result.put("content", content);
        result.put("right_2", right_2);
        result.put("analyse", analyse);
        result.put("voiceurl", voiceurl);
        result.put("videourl", videourl);
        result.put("position", position);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("examcatalogid", examcatalogid);
        result.put("answernum", answernum);
        result.put("gid", gid);
        result.put("imgPic", imgPic);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Optquestion");
        result.put("optid", optid);
        result.put("examid", examid);
        result.put("type", type);
        result.put("content", content);
        result.put("right_2", right_2);
        result.put("analyse", analyse);
        result.put("voiceurl", voiceurl);
        result.put("videourl", videourl);
        result.put("position", position);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("examcatalogid", examcatalogid);
        result.put("answernum", answernum);
        result.put("gid", gid);
        result.put("imgPic", imgPic);
        return result;
    }

    public Optquestion mapToObject(Map e){
        Integer optid = MapEx.getInt(e, "optid");
        Integer examid = MapEx.getInt(e, "examid");
        Integer type = MapEx.getInt(e, "type");
        String content = MapEx.getString(e, "content");
        String right_2 = MapEx.getString(e, "right_2");
        String analyse = MapEx.getString(e, "analyse");
        String voiceurl = MapEx.getString(e, "voiceurl");
        String videourl = MapEx.getString(e, "videourl");
        String position = MapEx.getString(e, "position");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer examcatalogid = MapEx.getInt(e, "examcatalogid");
        Integer answernum = MapEx.getInt(e, "answernum");
        Integer gid = MapEx.getInt(e, "gid");
        String imgPic = MapEx.getString(e, "imgPic");

        if(optid == null) optid = 0;
        if(examid == null) examid = 0;
        if(type == null) type = 0;
        if(content == null) content = "";
        if(right_2 == null) right_2 = "";
        if(analyse == null) analyse = "";
        if(voiceurl == null) voiceurl = "";
        if(videourl == null) videourl = "";
        if(position == null) position = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(examcatalogid == null) examcatalogid = 0;
        if(answernum == null) answernum = 0;
        if(gid == null) gid = 0;
        if(imgPic == null) imgPic = "";

        setOptid(optid);
        setExamid(examid);
        setType(type);
        setContent(content);
        setRight_2(right_2);
        setAnalyse(analyse);
        setVoiceurl(voiceurl);
        setVideourl(videourl);
        setPosition(position);
        setStatus(status);
        setCreatetime(createtime);
        setExamcatalogid(examcatalogid);
        setAnswernum(answernum);
        setGid(gid);
        setImgPic(imgPic);

        return this;
    }

    public static final Optquestion mapTo(Map e){
        Optquestion result = new Optquestion();

        Integer optid = MapEx.getInt(e, "optid");
        Integer examid = MapEx.getInt(e, "examid");
        Integer type = MapEx.getInt(e, "type");
        String content = MapEx.getString(e, "content");
        String right_2 = MapEx.getString(e, "right_2");
        String analyse = MapEx.getString(e, "analyse");
        String voiceurl = MapEx.getString(e, "voiceurl");
        String videourl = MapEx.getString(e, "videourl");
        String position = MapEx.getString(e, "position");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer examcatalogid = MapEx.getInt(e, "examcatalogid");
        Integer answernum = MapEx.getInt(e, "answernum");
        Integer gid = MapEx.getInt(e, "gid");
        String imgPic = MapEx.getString(e, "imgPic");

        if(optid == null) optid = 0;
        if(examid == null) examid = 0;
        if(type == null) type = 0;
        if(content == null) content = "";
        if(right_2 == null) right_2 = "";
        if(analyse == null) analyse = "";
        if(voiceurl == null) voiceurl = "";
        if(videourl == null) videourl = "";
        if(position == null) position = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(examcatalogid == null) examcatalogid = 0;
        if(answernum == null) answernum = 0;
        if(gid == null) gid = 0;
        if(imgPic == null) imgPic = "";

        result.optid = optid;
        result.examid = examid;
        result.type = type;
        result.content = content;
        result.right_2 = right_2;
        result.analyse = analyse;
        result.voiceurl = voiceurl;
        result.videourl = videourl;
        result.position = position;
        result.status = status;
        result.createtime = createtime;
        result.examcatalogid = examcatalogid;
        result.answernum = answernum;
        result.gid = gid;
        result.imgPic = imgPic;

        return result;
    }

    public static final Optquestion originalTo(Map e){
        Optquestion result = new Optquestion();

        Integer optid = MapEx.getInt(e, "optid");
        Integer examid = MapEx.getInt(e, "examid");
        Integer type = MapEx.getInt(e, "type");
        String content = MapEx.getString(e, "content");
        String right_2 = MapEx.getString(e, "right_2");
        String analyse = MapEx.getString(e, "analyse");
        String voiceurl = MapEx.getString(e, "voiceurl");
        String videourl = MapEx.getString(e, "videourl");
        String position = MapEx.getString(e, "position");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer examcatalogid = MapEx.getInt(e, "examcatalogid");
        Integer answernum = MapEx.getInt(e, "answernum");
        Integer gid = MapEx.getInt(e, "gid");
        String imgPic = MapEx.getString(e, "imgPic");

        if(optid == null) optid = 0;
        if(examid == null) examid = 0;
        if(type == null) type = 0;
        if(content == null) content = "";
        if(right_2 == null) right_2 = "";
        if(analyse == null) analyse = "";
        if(voiceurl == null) voiceurl = "";
        if(videourl == null) videourl = "";
        if(position == null) position = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(examcatalogid == null) examcatalogid = 0;
        if(answernum == null) answernum = 0;
        if(gid == null) gid = 0;
        if(imgPic == null) imgPic = "";

        result.optid = optid;
        result.examid = examid;
        result.type = type;
        result.content = content;
        result.right_2 = right_2;
        result.analyse = analyse;
        result.voiceurl = voiceurl;
        result.videourl = videourl;
        result.position = position;
        result.status = status;
        result.createtime = createtime;
        result.examcatalogid = examcatalogid;
        result.answernum = answernum;
        result.gid = gid;
        result.imgPic = imgPic;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Optquestion createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 15);
            writeMapEntry(out, "optid", optid);
            writeMapEntry(out, "examid", examid);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "right_2", right_2);
            writeMapEntry(out, "analyse", analyse);
            writeMapEntry(out, "voiceurl", voiceurl);
            writeMapEntry(out, "videourl", videourl);
            writeMapEntry(out, "position", position);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "examcatalogid", examcatalogid);
            writeMapEntry(out, "answernum", answernum);
            writeMapEntry(out, "gid", gid);
            writeMapEntry(out, "imgPic", imgPic);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Optquestion createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(optid);
        return s.hashCode();
    }
    public final Examcatalog getExamcatalogFkExamcatalogid() { // examcatalog - examcatalogid
        return ExamcatalogEntity.getByKey(examcatalogid);
    }

    public final int countExamcatalogFkExamcatalogid() { // examcatalog - examcatalogid
        return getExamcatalogFkExamcatalogid() == null ? 0 : 1;
    }

    public final List<Errorfeedback> getErrorfeedbacksFkOptid() { // errorfeedback - optid
        return ErrorfeedbackEntity.getByOptid(optid);
    }

    public final int countErrorfeedbacksFkOptid() { // errorfeedback - optid
        return ErrorfeedbackEntity.countByOptid(optid);
    }

    public final List<Recordques4exam> getRecordques4examsFkQuestionid() { // recordques4exam - questionid
        return Recordques4examEntity.getByQuestionid(optid);
    }

    public final int countRecordques4examsFkQuestionid() { // recordques4exam - questionid
        return Recordques4examEntity.countByQuestionid(optid);
    }

    public static final Optquestion loadByKey(int optid) {
        return OptquestionEntity.getByKey(optid);
    }

    public static final Future<Optquestion> asyncByKey(int optid) {
        return OptquestionEntity.asyncGetByKey(optid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Optquestion insert() {
        Optquestion result = OptquestionEntity.insert(this);
        if(result == null) return null;
        // optid = result.optid;
        return result;
    }

    public final Optquestion asyncInsert() {
        Optquestion result = OptquestionEntity.asyncInsert(this);
        // optid = result.optid;
        return result;
    }

    public final Optquestion insert2() {
        return OptquestionEntity.insert2(this);
    }

    public final Optquestion asyncInsert2() {
        Optquestion result = OptquestionEntity.asyncInsert2(this);
        // optid = result.optid;
        return result;
    }

    public final Optquestion update() {
        return OptquestionEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(optid <= 0) return false;
        OptquestionEntity.asyncUpdate( this );
        return true;
    }

    public final Optquestion insertOrUpdate() {
        if(optid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return OptquestionEntity.delete(optid);
    }

    public final void asyncDelete() {
        OptquestionEntity.asyncDelete(optid);
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

    public Optquestion clone2() {
        try{
            return (Optquestion) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
