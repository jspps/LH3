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

//learnhall3_design - agent
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Agent extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1848235655; // com.learnhall.db.bean.Agent

    public static String TABLENAME = "agent";

    public static final String primary = "agid";

    public static final class Col { public static final String agid = "agid"; public static final String accountid = "accountid"; public static final String uname = "uname"; public static final String code = "code"; public static final String province = "province"; public static final String city = "city"; public static final String seat = "seat"; public static final String qq = "qq"; public static final String need = "need"; public static final String goodness = "goodness"; public static final String volume = "volume"; public static final String curmoney = "curmoney"; public static final String allmoney = "allmoney"; public static final String createtime = "createtime"; public static final String endtime = "endtime"; public static final String status = "status"; public static final String examineStatus = "examineStatus"; public static final String examineDes = "examineDes"; public static final String alipay = "alipay"; public static final String isVerifyAlipay = "isVerifyAlipay";  }
    public static final class CEn { public static final String agid = "agid"; public static final String accountid = "accountid"; public static final String uname = "uname"; public static final String code = "code"; public static final String province = "province"; public static final String city = "city"; public static final String seat = "seat"; public static final String qq = "qq"; public static final String need = "need"; public static final String goodness = "goodness"; public static final String volume = "volume"; public static final String curmoney = "curmoney"; public static final String allmoney = "allmoney"; public static final String createtime = "createtime"; public static final String endtime = "endtime"; public static final String status = "status"; public static final String examineStatus = "examineStatus"; public static final String examineDes = "examineDes"; public static final String alipay = "alipay"; public static final String isVerifyAlipay = "isVerifyAlipay";  }
    public static final String[] carrays ={"agid", "accountid", "uname", "code", "province", "city", "seat", "qq", "need", "goodness", "volume", "curmoney", "allmoney", "createtime", "endtime", "status", "examineStatus", "examineDes", "alipay", "isVerifyAlipay"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "TINYTEXT", "VARCHAR", "VARCHAR", "TINYTEXT", "INT", "DOUBLE", "DOUBLE", "DATETIME", "DATETIME", "INT", "INT", "TEXT", "TINYTEXT", "BIT"};


    public int agid;
    public int accountid;
    public String uname;
    public String code;
    public String province;
    public String city;
    public String seat;
    public String qq;
    public String need;
    public String goodness;
    public int volume;
    public double curmoney;
    public double allmoney;
    public java.util.Date createtime;
    public java.util.Date endtime;
    public int status;
    public int examineStatus;
    public String examineDes;
    public String alipay;
    public boolean isVerifyAlipay;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return agid;
    }

    public static String _key(int agid) {
        return PStr.b(TABLENAME).a("-").e(agid);
    }

    public int getAgid(){
        return agid;
    }

    public Agent setAgid(int agid){
        this.agid = agid;
        return this;
    }

    public int getAccountid(){
        return accountid;
    }

    public Agent setAccountid(int accountid){
        int _old = this.accountid;
        this.accountid = accountid;
        changeIt(Col.accountid, _old, accountid);
        return this;
    }

    public Agent changeAccountid(int accountid){
        return setAccountid(this.accountid + accountid);
    }

    public Agent changeAccountidWithMin(int accountid, int _min){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public Agent changeAccountidWithMax(int accountid, int _max){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 > _max  ? _max : _v2);
    }

    public Agent changeAccountidWithMinMax(int accountid, int _min, int _max){
        int _v2 = this.accountid + accountid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public String getUname(){
        return uname;
    }

    public Agent setUname(String uname){
        String _old = this.uname;
        this.uname = uname;
        changeIt(Col.uname, _old, uname);
        return this;
    }

    public String getCode(){
        return code;
    }

    public Agent setCode(String code){
        String _old = this.code;
        this.code = code;
        changeIt(Col.code, _old, code);
        return this;
    }

    public String getProvince(){
        return province;
    }

    public Agent setProvince(String province){
        String _old = this.province;
        this.province = province;
        changeIt(Col.province, _old, province);
        return this;
    }

    public String getCity(){
        return city;
    }

    public Agent setCity(String city){
        String _old = this.city;
        this.city = city;
        changeIt(Col.city, _old, city);
        return this;
    }

    public String getSeat(){
        return seat;
    }

    public Agent setSeat(String seat){
        String _old = this.seat;
        this.seat = seat;
        changeIt(Col.seat, _old, seat);
        return this;
    }

    public String getQq(){
        return qq;
    }

    public Agent setQq(String qq){
        String _old = this.qq;
        this.qq = qq;
        changeIt(Col.qq, _old, qq);
        return this;
    }

    public String getNeed(){
        return need;
    }

    public Agent setNeed(String need){
        String _old = this.need;
        this.need = need;
        changeIt(Col.need, _old, need);
        return this;
    }

    public String getGoodness(){
        return goodness;
    }

    public Agent setGoodness(String goodness){
        String _old = this.goodness;
        this.goodness = goodness;
        changeIt(Col.goodness, _old, goodness);
        return this;
    }

    public int getVolume(){
        return volume;
    }

    public Agent setVolume(int volume){
        int _old = this.volume;
        this.volume = volume;
        changeIt(Col.volume, _old, volume);
        return this;
    }

    public Agent changeVolume(int volume){
        return setVolume(this.volume + volume);
    }

    public Agent changeVolumeWithMin(int volume, int _min){
        int _v2 = this.volume + volume;
        return setVolume(_v2 < _min  ? _min : _v2);
    }

    public Agent changeVolumeWithMax(int volume, int _max){
        int _v2 = this.volume + volume;
        return setVolume(_v2 > _max  ? _max : _v2);
    }

    public Agent changeVolumeWithMinMax(int volume, int _min, int _max){
        int _v2 = this.volume + volume;
        _v2 = _v2 > _max  ? _max : _v2;
        return setVolume(_v2 < _min  ? _min : _v2);
    }

    public double getCurmoney(){
        return curmoney;
    }

    public Agent setCurmoney(double curmoney){
        double _old = this.curmoney;
        this.curmoney = curmoney;
        changeIt(Col.curmoney, _old, curmoney);
        return this;
    }

    public Agent changeCurmoney(double curmoney){
        return setCurmoney(this.curmoney + curmoney);
    }

    public Agent changeCurmoneyWithMin(double curmoney, double _min){
        double _v2 = this.curmoney + curmoney;
        return setCurmoney(_v2 < _min  ? _min : _v2);
    }

    public Agent changeCurmoneyWithMax(double curmoney, double _max){
        double _v2 = this.curmoney + curmoney;
        return setCurmoney(_v2 > _max  ? _max : _v2);
    }

    public Agent changeCurmoneyWithMinMax(double curmoney, double _min, double _max){
        double _v2 = this.curmoney + curmoney;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurmoney(_v2 < _min  ? _min : _v2);
    }

    public double getAllmoney(){
        return allmoney;
    }

    public Agent setAllmoney(double allmoney){
        double _old = this.allmoney;
        this.allmoney = allmoney;
        changeIt(Col.allmoney, _old, allmoney);
        return this;
    }

    public Agent changeAllmoney(double allmoney){
        return setAllmoney(this.allmoney + allmoney);
    }

    public Agent changeAllmoneyWithMin(double allmoney, double _min){
        double _v2 = this.allmoney + allmoney;
        return setAllmoney(_v2 < _min  ? _min : _v2);
    }

    public Agent changeAllmoneyWithMax(double allmoney, double _max){
        double _v2 = this.allmoney + allmoney;
        return setAllmoney(_v2 > _max  ? _max : _v2);
    }

    public Agent changeAllmoneyWithMinMax(double allmoney, double _min, double _max){
        double _v2 = this.allmoney + allmoney;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAllmoney(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Agent setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getEndtime(){
        return endtime;
    }

    public Agent setEndtime(java.util.Date endtime){
        java.util.Date _old = this.endtime;
        this.endtime = endtime;
        changeIt(Col.endtime, _old, endtime);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Agent setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Agent changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Agent changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Agent changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Agent changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int getExamineStatus(){
        return examineStatus;
    }

    public Agent setExamineStatus(int examineStatus){
        int _old = this.examineStatus;
        this.examineStatus = examineStatus;
        changeIt(Col.examineStatus, _old, examineStatus);
        return this;
    }

    public Agent changeExamineStatus(int examineStatus){
        return setExamineStatus(this.examineStatus + examineStatus);
    }

    public Agent changeExamineStatusWithMin(int examineStatus, int _min){
        int _v2 = this.examineStatus + examineStatus;
        return setExamineStatus(_v2 < _min  ? _min : _v2);
    }

    public Agent changeExamineStatusWithMax(int examineStatus, int _max){
        int _v2 = this.examineStatus + examineStatus;
        return setExamineStatus(_v2 > _max  ? _max : _v2);
    }

    public Agent changeExamineStatusWithMinMax(int examineStatus, int _min, int _max){
        int _v2 = this.examineStatus + examineStatus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamineStatus(_v2 < _min  ? _min : _v2);
    }

    public String getExamineDes(){
        return examineDes;
    }

    public Agent setExamineDes(String examineDes){
        String _old = this.examineDes;
        this.examineDes = examineDes;
        changeIt(Col.examineDes, _old, examineDes);
        return this;
    }

    public String getAlipay(){
        return alipay;
    }

    public Agent setAlipay(String alipay){
        String _old = this.alipay;
        this.alipay = alipay;
        changeIt(Col.alipay, _old, alipay);
        return this;
    }

    public boolean getIsVerifyAlipay(){
        return isVerifyAlipay;
    }

    public Agent setIsVerifyAlipay(boolean isVerifyAlipay){
        boolean _old = this.isVerifyAlipay;
        this.isVerifyAlipay = isVerifyAlipay;
        changeIt(Col.isVerifyAlipay, _old, isVerifyAlipay);
        return this;
    }

    public int compareTo(Agent v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Agent v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Agent newAgent(Integer agid, Integer accountid, String uname, String code, String province, String city, String seat, String qq, String need, String goodness, Integer volume, Double curmoney, Double allmoney, java.util.Date createtime, java.util.Date endtime, Integer status, Integer examineStatus, String examineDes, String alipay, Boolean isVerifyAlipay) {
        Agent result = new Agent();
        result.agid = agid;
        result.accountid = accountid;
        result.uname = uname;
        result.code = code;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.qq = qq;
        result.need = need;
        result.goodness = goodness;
        result.volume = volume;
        result.curmoney = curmoney;
        result.allmoney = allmoney;
        result.createtime = createtime;
        result.endtime = endtime;
        result.status = status;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.alipay = alipay;
        result.isVerifyAlipay = isVerifyAlipay;
        return result;
    }

    public static Agent newAgent(Agent agent) {
        Agent result = new Agent();
        result.agid = agent.agid;
        result.accountid = agent.accountid;
        result.uname = agent.uname;
        result.code = agent.code;
        result.province = agent.province;
        result.city = agent.city;
        result.seat = agent.seat;
        result.qq = agent.qq;
        result.need = agent.need;
        result.goodness = agent.goodness;
        result.volume = agent.volume;
        result.curmoney = agent.curmoney;
        result.allmoney = agent.allmoney;
        result.createtime = agent.createtime;
        result.endtime = agent.endtime;
        result.status = agent.status;
        result.examineStatus = agent.examineStatus;
        result.examineDes = agent.examineDes;
        result.alipay = agent.alipay;
        result.isVerifyAlipay = agent.isVerifyAlipay;
        return result;
    }

    public Agent createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAgent(){
        Agent agent = null; // agent
        { // new and insert Agent (agent)
            int agid = 0; 	// agid
            int accountid = 0; 	// accountid
            String uname = ""; 	// uname
            String code = ""; 	// code
            String province = ""; 	// province
            String city = ""; 	// city
            String seat = ""; 	// seat
            String qq = ""; 	// qq
            String need = ""; 	// need
            String goodness = ""; 	// goodness
            int volume = 0; 	// volume
            double curmoney = 0.0; 	// curmoney
            double allmoney = 0.0; 	// allmoney
            Date createtime = new Date(); 	// createtime
            Date endtime = new Date(); 	// endtime
            int status = 0; 	// status
            int examineStatus = 0; 	// examineStatus
            String examineDes = ""; 	// examineDes
            String alipay = ""; 	// alipay
            boolean isVerifyAlipay = true; 	// isVerifyAlipay
            agent = Agent.newAgent(agid, accountid, uname, code, province, city, seat, qq, need, goodness, volume, curmoney, allmoney, createtime, endtime, status, examineStatus, examineDes, alipay, isVerifyAlipay);
        }
        agent = agent.insert();

        int agid = agent.getAgid(); 	// agid
        int accountid = agent.getAccountid(); 	// accountid
        String uname = agent.getUname(); 	// uname
        String code = agent.getCode(); 	// code
        String province = agent.getProvince(); 	// province
        String city = agent.getCity(); 	// city
        String seat = agent.getSeat(); 	// seat
        String qq = agent.getQq(); 	// qq
        String need = agent.getNeed(); 	// need
        String goodness = agent.getGoodness(); 	// goodness
        int volume = agent.getVolume(); 	// volume
        double curmoney = agent.getCurmoney(); 	// curmoney
        double allmoney = agent.getAllmoney(); 	// allmoney
        Date createtime = agent.getCreatetime(); 	// createtime
        Date endtime = agent.getEndtime(); 	// endtime
        int status = agent.getStatus(); 	// status
        int examineStatus = agent.getExamineStatus(); 	// examineStatus
        String examineDes = agent.getExamineDes(); 	// examineDes
        String alipay = agent.getAlipay(); 	// alipay
        boolean isVerifyAlipay = agent.getIsVerifyAlipay(); 	// isVerifyAlipay
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.agid:
            return agid;
        case CEn.accountid:
            return accountid;
        case CEn.volume:
            return volume;
        case CEn.status:
            return status;
        case CEn.examineStatus:
            return examineStatus;
        }
        return 0;
    }

    public Agent setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Agent setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.agid:
            return setAgid(value2);
        case CEn.accountid:
            return setAccountid(value2);
        case CEn.volume:
            return setVolume(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.examineStatus:
            return setExamineStatus(value2);
        }
        return this;
    }

    public Agent changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Agent changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.accountid:
            return changeAccountid(value2);
        case CEn.volume:
            return changeVolume(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.examineStatus:
            return changeExamineStatus(value2);
        }
        return this;
    }

    public Agent changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Agent changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.accountid:
            return changeAccountidWithMin(value2, _min);
        case CEn.volume:
            return changeVolumeWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.examineStatus:
            return changeExamineStatusWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.curmoney:
            return curmoney;
        case CEn.allmoney:
            return allmoney;
        }
        return 0;
    }

    public Agent setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Agent setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.curmoney:
            return setCurmoney(value2);
        case CEn.allmoney:
            return setAllmoney(value2);
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
        case CEn.code: 
            return code;
        case CEn.province: 
            return province;
        case CEn.city: 
            return city;
        case CEn.seat: 
            return seat;
        case CEn.qq: 
            return qq;
        case CEn.need: 
            return need;
        case CEn.goodness: 
            return goodness;
        case CEn.examineDes: 
            return examineDes;
        case CEn.alipay: 
            return alipay;
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
        case CEn.agid:
            return agid;
        case CEn.accountid:
            return accountid;
        case CEn.uname:
            return uname;
        case CEn.code:
            return code;
        case CEn.province:
            return province;
        case CEn.city:
            return city;
        case CEn.seat:
            return seat;
        case CEn.qq:
            return qq;
        case CEn.need:
            return need;
        case CEn.goodness:
            return goodness;
        case CEn.volume:
            return volume;
        case CEn.curmoney:
            return curmoney;
        case CEn.allmoney:
            return allmoney;
        case CEn.createtime:
            return createtime;
        case CEn.endtime:
            return endtime;
        case CEn.status:
            return status;
        case CEn.examineStatus:
            return examineStatus;
        case CEn.examineDes:
            return examineDes;
        case CEn.alipay:
            return alipay;
        case CEn.isVerifyAlipay:
            return isVerifyAlipay;
        }
        return null;
    }

    public Agent setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Agent setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Agent setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Agent setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.uname:
            return setUname(value2);
        case CEn.code:
            return setCode(value2);
        case CEn.province:
            return setProvince(value2);
        case CEn.city:
            return setCity(value2);
        case CEn.seat:
            return setSeat(value2);
        case CEn.qq:
            return setQq(value2);
        case CEn.need:
            return setNeed(value2);
        case CEn.goodness:
            return setGoodness(value2);
        case CEn.examineDes:
            return setExamineDes(value2);
        case CEn.alipay:
            return setAlipay(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Agent setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Agent setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Agent");
        result.put("agid", agid);
        result.put("accountid", accountid);
        result.put("uname", uname);
        result.put("code", code);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("qq", qq);
        result.put("need", need);
        result.put("goodness", goodness);
        result.put("volume", volume);
        result.put("curmoney", curmoney);
        result.put("allmoney", allmoney);
        result.put("createtime", createtime);
        result.put("endtime", endtime);
        result.put("status", status);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("alipay", alipay);
        result.put("isVerifyAlipay", isVerifyAlipay);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("agid", agid);
        result.put("accountid", accountid);
        result.put("uname", uname);
        result.put("code", code);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("qq", qq);
        result.put("need", need);
        result.put("goodness", goodness);
        result.put("volume", volume);
        result.put("curmoney", curmoney);
        result.put("allmoney", allmoney);
        result.put("createtime", createtime);
        result.put("endtime", endtime);
        result.put("status", status);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("alipay", alipay);
        result.put("isVerifyAlipay", isVerifyAlipay);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Agent");
        result.put("agid", agid);
        result.put("accountid", accountid);
        result.put("uname", uname);
        result.put("code", code);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("qq", qq);
        result.put("need", need);
        result.put("goodness", goodness);
        result.put("volume", volume);
        result.put("curmoney", curmoney);
        result.put("allmoney", allmoney);
        result.put("createtime", createtime);
        result.put("endtime", endtime);
        result.put("status", status);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("alipay", alipay);
        result.put("isVerifyAlipay", isVerifyAlipay);
        return result;
    }

    public Agent mapToObject(Map e){
        Integer agid = MapEx.getInt(e, "agid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String uname = MapEx.getString(e, "uname");
        String code = MapEx.getString(e, "code");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String qq = MapEx.getString(e, "qq");
        String need = MapEx.getString(e, "need");
        String goodness = MapEx.getString(e, "goodness");
        Integer volume = MapEx.getInt(e, "volume");
        Double curmoney = MapEx.getDouble(e, "curmoney");
        Double allmoney = MapEx.getDouble(e, "allmoney");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date endtime = MapEx.getDate(e, "endtime");
        Integer status = MapEx.getInt(e, "status");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        String alipay = MapEx.getString(e, "alipay");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");

        if(agid == null) agid = 0;
        if(accountid == null) accountid = 0;
        if(uname == null) uname = "";
        if(code == null) code = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(qq == null) qq = "";
        if(need == null) need = "";
        if(goodness == null) goodness = "";
        if(volume == null) volume = 0;
        if(curmoney == null) curmoney = 0.0;
        if(allmoney == null) allmoney = 0.0;
        if(createtime == null) createtime = new java.util.Date();
        if(endtime == null) endtime = new java.util.Date();
        if(status == null) status = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(alipay == null) alipay = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;

        setAgid(agid);
        setAccountid(accountid);
        setUname(uname);
        setCode(code);
        setProvince(province);
        setCity(city);
        setSeat(seat);
        setQq(qq);
        setNeed(need);
        setGoodness(goodness);
        setVolume(volume);
        setCurmoney(curmoney);
        setAllmoney(allmoney);
        setCreatetime(createtime);
        setEndtime(endtime);
        setStatus(status);
        setExamineStatus(examineStatus);
        setExamineDes(examineDes);
        setAlipay(alipay);
        setIsVerifyAlipay(isVerifyAlipay);

        return this;
    }

    public static final Agent mapTo(Map e){
        Agent result = new Agent();

        Integer agid = MapEx.getInt(e, "agid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String uname = MapEx.getString(e, "uname");
        String code = MapEx.getString(e, "code");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String qq = MapEx.getString(e, "qq");
        String need = MapEx.getString(e, "need");
        String goodness = MapEx.getString(e, "goodness");
        Integer volume = MapEx.getInt(e, "volume");
        Double curmoney = MapEx.getDouble(e, "curmoney");
        Double allmoney = MapEx.getDouble(e, "allmoney");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date endtime = MapEx.getDate(e, "endtime");
        Integer status = MapEx.getInt(e, "status");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        String alipay = MapEx.getString(e, "alipay");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");

        if(agid == null) agid = 0;
        if(accountid == null) accountid = 0;
        if(uname == null) uname = "";
        if(code == null) code = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(qq == null) qq = "";
        if(need == null) need = "";
        if(goodness == null) goodness = "";
        if(volume == null) volume = 0;
        if(curmoney == null) curmoney = 0.0;
        if(allmoney == null) allmoney = 0.0;
        if(createtime == null) createtime = new java.util.Date();
        if(endtime == null) endtime = new java.util.Date();
        if(status == null) status = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(alipay == null) alipay = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;

        result.agid = agid;
        result.accountid = accountid;
        result.uname = uname;
        result.code = code;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.qq = qq;
        result.need = need;
        result.goodness = goodness;
        result.volume = volume;
        result.curmoney = curmoney;
        result.allmoney = allmoney;
        result.createtime = createtime;
        result.endtime = endtime;
        result.status = status;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.alipay = alipay;
        result.isVerifyAlipay = isVerifyAlipay;

        return result;
    }

    public static final Agent originalTo(Map e){
        Agent result = new Agent();

        Integer agid = MapEx.getInt(e, "agid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String uname = MapEx.getString(e, "uname");
        String code = MapEx.getString(e, "code");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String qq = MapEx.getString(e, "qq");
        String need = MapEx.getString(e, "need");
        String goodness = MapEx.getString(e, "goodness");
        Integer volume = MapEx.getInt(e, "volume");
        Double curmoney = MapEx.getDouble(e, "curmoney");
        Double allmoney = MapEx.getDouble(e, "allmoney");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date endtime = MapEx.getDate(e, "endtime");
        Integer status = MapEx.getInt(e, "status");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        String alipay = MapEx.getString(e, "alipay");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");

        if(agid == null) agid = 0;
        if(accountid == null) accountid = 0;
        if(uname == null) uname = "";
        if(code == null) code = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(qq == null) qq = "";
        if(need == null) need = "";
        if(goodness == null) goodness = "";
        if(volume == null) volume = 0;
        if(curmoney == null) curmoney = 0.0;
        if(allmoney == null) allmoney = 0.0;
        if(createtime == null) createtime = new java.util.Date();
        if(endtime == null) endtime = new java.util.Date();
        if(status == null) status = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(alipay == null) alipay = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;

        result.agid = agid;
        result.accountid = accountid;
        result.uname = uname;
        result.code = code;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.qq = qq;
        result.need = need;
        result.goodness = goodness;
        result.volume = volume;
        result.curmoney = curmoney;
        result.allmoney = allmoney;
        result.createtime = createtime;
        result.endtime = endtime;
        result.status = status;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.alipay = alipay;
        result.isVerifyAlipay = isVerifyAlipay;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Agent createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 20);
            writeMapEntry(out, "agid", agid);
            writeMapEntry(out, "accountid", accountid);
            writeMapEntry(out, "uname", uname);
            writeMapEntry(out, "code", code);
            writeMapEntry(out, "province", province);
            writeMapEntry(out, "city", city);
            writeMapEntry(out, "seat", seat);
            writeMapEntry(out, "qq", qq);
            writeMapEntry(out, "need", need);
            writeMapEntry(out, "goodness", goodness);
            writeMapEntry(out, "volume", volume);
            writeMapEntry(out, "curmoney", curmoney);
            writeMapEntry(out, "allmoney", allmoney);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "endtime", endtime);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "examineStatus", examineStatus);
            writeMapEntry(out, "examineDes", examineDes);
            writeMapEntry(out, "alipay", alipay);
            writeMapEntry(out, "isVerifyAlipay", isVerifyAlipay);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Agent createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(agid);
        return s.hashCode();
    }
    public final Account getAccountFkAccountid() { // account - accountid
        return AccountEntity.getByKey(accountid);
    }

    public final int countAccountFkAccountid() { // account - accountid
        return getAccountFkAccountid() == null ? 0 : 1;
    }

    public final List<Openkind4third> getOpenkind4thirdsFkAgentid() { // openkind4third - agentid
        return Openkind4thirdEntity.getByAgentid(agid);
    }

    public final int countOpenkind4thirdsFkAgentid() { // openkind4third - agentid
        return Openkind4thirdEntity.countByAgentid(agid);
    }

    public final List<Orders4profit> getOrders4profitsFkAgentid() { // orders4profit - agentid
        return Orders4profitEntity.getByAgentid(agid);
    }

    public final int countOrders4profitsFkAgentid() { // orders4profit - agentid
        return Orders4profitEntity.countByAgentid(agid);
    }

    public static final Agent loadByKey(int agid) {
        return AgentEntity.getByKey(agid);
    }

    public static final Future<Agent> asyncByKey(int agid) {
        return AgentEntity.asyncGetByKey(agid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Agent insert() {
        Agent result = AgentEntity.insert(this);
        if(result == null) return null;
        // agid = result.agid;
        return result;
    }

    public final Agent asyncInsert() {
        Agent result = AgentEntity.asyncInsert(this);
        // agid = result.agid;
        return result;
    }

    public final Agent insert2() {
        return AgentEntity.insert2(this);
    }

    public final Agent asyncInsert2() {
        Agent result = AgentEntity.asyncInsert2(this);
        // agid = result.agid;
        return result;
    }

    public final Agent update() {
        return AgentEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(agid <= 0) return false;
        AgentEntity.asyncUpdate( this );
        return true;
    }

    public final Agent insertOrUpdate() {
        if(agid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AgentEntity.delete(agid);
    }

    public final void asyncDelete() {
        AgentEntity.asyncDelete(agid);
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

    public Agent clone2() {
        try{
            return (Agent) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
