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

//learnhall3_design - exchangermb
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Exchangermb extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 46306296; // com.learnhall.db.bean.Exchangermb

    public static String TABLENAME = "exchangermb";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String type = "type"; public static final String makerid = "makerid"; public static final String nmMaker = "nmMaker"; public static final String alipay = "alipay"; public static final String alipayName = "alipayName"; public static final String reason = "reason"; public static final String moneyCur = "moneyCur"; public static final String monyeApply = "monyeApply"; public static final String monyeReal = "monyeReal"; public static final String status = "status"; public static final String batchNo = "batchNo"; public static final String statusOpt = "statusOpt"; public static final String createtime = "createtime"; public static final String content = "content"; public static final String lasttime = "lasttime";  }
    public static final class CEn { public static final String id = "id"; public static final String type = "type"; public static final String makerid = "makerid"; public static final String nmMaker = "nmMaker"; public static final String alipay = "alipay"; public static final String alipayName = "alipayName"; public static final String reason = "reason"; public static final String moneyCur = "moneyCur"; public static final String monyeApply = "monyeApply"; public static final String monyeReal = "monyeReal"; public static final String status = "status"; public static final String batchNo = "batchNo"; public static final String statusOpt = "statusOpt"; public static final String createtime = "createtime"; public static final String content = "content"; public static final String lasttime = "lasttime";  }
    public static final String[] carrays ={"id", "type", "makerid", "nmMaker", "alipay", "alipayName", "reason", "moneyCur", "monyeApply", "monyeReal", "status", "batchNo", "statusOpt", "createtime", "content", "lasttime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TINYTEXT", "TINYTEXT", "TINYTEXT", "TEXT", "DOUBLE", "DOUBLE", "DOUBLE", "INT", "VARCHAR", "INT", "DATETIME", "TEXT", "DATETIME"};


    public int id;
    public int type;
    public int makerid;
    public String nmMaker;
    public String alipay;
    public String alipayName;
    public String reason;
    public double moneyCur;
    public double monyeApply;
    public double monyeReal;
    public int status;
    public String batchNo;
    public int statusOpt;
    public java.util.Date createtime;
    public String content;
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

    public Exchangermb setId(int id){
        this.id = id;
        return this;
    }

    public int getType(){
        return type;
    }

    public Exchangermb setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Exchangermb changeType(int type){
        return setType(this.type + type);
    }

    public Exchangermb changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int getMakerid(){
        return makerid;
    }

    public Exchangermb setMakerid(int makerid){
        int _old = this.makerid;
        this.makerid = makerid;
        changeIt(Col.makerid, _old, makerid);
        return this;
    }

    public Exchangermb changeMakerid(int makerid){
        return setMakerid(this.makerid + makerid);
    }

    public Exchangermb changeMakeridWithMin(int makerid, int _min){
        int _v2 = this.makerid + makerid;
        return setMakerid(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeMakeridWithMax(int makerid, int _max){
        int _v2 = this.makerid + makerid;
        return setMakerid(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeMakeridWithMinMax(int makerid, int _min, int _max){
        int _v2 = this.makerid + makerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMakerid(_v2 < _min  ? _min : _v2);
    }

    public String getNmMaker(){
        return nmMaker;
    }

    public Exchangermb setNmMaker(String nmMaker){
        String _old = this.nmMaker;
        this.nmMaker = nmMaker;
        changeIt(Col.nmMaker, _old, nmMaker);
        return this;
    }

    public String getAlipay(){
        return alipay;
    }

    public Exchangermb setAlipay(String alipay){
        String _old = this.alipay;
        this.alipay = alipay;
        changeIt(Col.alipay, _old, alipay);
        return this;
    }

    public String getAlipayName(){
        return alipayName;
    }

    public Exchangermb setAlipayName(String alipayName){
        String _old = this.alipayName;
        this.alipayName = alipayName;
        changeIt(Col.alipayName, _old, alipayName);
        return this;
    }

    public String getReason(){
        return reason;
    }

    public Exchangermb setReason(String reason){
        String _old = this.reason;
        this.reason = reason;
        changeIt(Col.reason, _old, reason);
        return this;
    }

    public double getMoneyCur(){
        return moneyCur;
    }

    public Exchangermb setMoneyCur(double moneyCur){
        double _old = this.moneyCur;
        this.moneyCur = moneyCur;
        changeIt(Col.moneyCur, _old, moneyCur);
        return this;
    }

    public Exchangermb changeMoneyCur(double moneyCur){
        return setMoneyCur(this.moneyCur + moneyCur);
    }

    public Exchangermb changeMoneyCurWithMin(double moneyCur, double _min){
        double _v2 = this.moneyCur + moneyCur;
        return setMoneyCur(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeMoneyCurWithMax(double moneyCur, double _max){
        double _v2 = this.moneyCur + moneyCur;
        return setMoneyCur(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeMoneyCurWithMinMax(double moneyCur, double _min, double _max){
        double _v2 = this.moneyCur + moneyCur;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyCur(_v2 < _min  ? _min : _v2);
    }

    public double getMonyeApply(){
        return monyeApply;
    }

    public Exchangermb setMonyeApply(double monyeApply){
        double _old = this.monyeApply;
        this.monyeApply = monyeApply;
        changeIt(Col.monyeApply, _old, monyeApply);
        return this;
    }

    public Exchangermb changeMonyeApply(double monyeApply){
        return setMonyeApply(this.monyeApply + monyeApply);
    }

    public Exchangermb changeMonyeApplyWithMin(double monyeApply, double _min){
        double _v2 = this.monyeApply + monyeApply;
        return setMonyeApply(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeMonyeApplyWithMax(double monyeApply, double _max){
        double _v2 = this.monyeApply + monyeApply;
        return setMonyeApply(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeMonyeApplyWithMinMax(double monyeApply, double _min, double _max){
        double _v2 = this.monyeApply + monyeApply;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMonyeApply(_v2 < _min  ? _min : _v2);
    }

    public double getMonyeReal(){
        return monyeReal;
    }

    public Exchangermb setMonyeReal(double monyeReal){
        double _old = this.monyeReal;
        this.monyeReal = monyeReal;
        changeIt(Col.monyeReal, _old, monyeReal);
        return this;
    }

    public Exchangermb changeMonyeReal(double monyeReal){
        return setMonyeReal(this.monyeReal + monyeReal);
    }

    public Exchangermb changeMonyeRealWithMin(double monyeReal, double _min){
        double _v2 = this.monyeReal + monyeReal;
        return setMonyeReal(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeMonyeRealWithMax(double monyeReal, double _max){
        double _v2 = this.monyeReal + monyeReal;
        return setMonyeReal(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeMonyeRealWithMinMax(double monyeReal, double _min, double _max){
        double _v2 = this.monyeReal + monyeReal;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMonyeReal(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Exchangermb setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Exchangermb changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Exchangermb changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public String getBatchNo(){
        return batchNo;
    }

    public Exchangermb setBatchNo(String batchNo){
        String _old = this.batchNo;
        this.batchNo = batchNo;
        changeIt(Col.batchNo, _old, batchNo);
        return this;
    }

    public int getStatusOpt(){
        return statusOpt;
    }

    public Exchangermb setStatusOpt(int statusOpt){
        int _old = this.statusOpt;
        this.statusOpt = statusOpt;
        changeIt(Col.statusOpt, _old, statusOpt);
        return this;
    }

    public Exchangermb changeStatusOpt(int statusOpt){
        return setStatusOpt(this.statusOpt + statusOpt);
    }

    public Exchangermb changeStatusOptWithMin(int statusOpt, int _min){
        int _v2 = this.statusOpt + statusOpt;
        return setStatusOpt(_v2 < _min  ? _min : _v2);
    }

    public Exchangermb changeStatusOptWithMax(int statusOpt, int _max){
        int _v2 = this.statusOpt + statusOpt;
        return setStatusOpt(_v2 > _max  ? _max : _v2);
    }

    public Exchangermb changeStatusOptWithMinMax(int statusOpt, int _min, int _max){
        int _v2 = this.statusOpt + statusOpt;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatusOpt(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Exchangermb setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public String getContent(){
        return content;
    }

    public Exchangermb setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Exchangermb setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public int compareTo(Exchangermb v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Exchangermb v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Exchangermb newExchangermb(Integer id, Integer type, Integer makerid, String nmMaker, String alipay, String alipayName, String reason, Double moneyCur, Double monyeApply, Double monyeReal, Integer status, String batchNo, Integer statusOpt, java.util.Date createtime, String content, java.util.Date lasttime) {
        Exchangermb result = new Exchangermb();
        result.id = id;
        result.type = type;
        result.makerid = makerid;
        result.nmMaker = nmMaker;
        result.alipay = alipay;
        result.alipayName = alipayName;
        result.reason = reason;
        result.moneyCur = moneyCur;
        result.monyeApply = monyeApply;
        result.monyeReal = monyeReal;
        result.status = status;
        result.batchNo = batchNo;
        result.statusOpt = statusOpt;
        result.createtime = createtime;
        result.content = content;
        result.lasttime = lasttime;
        return result;
    }

    public static Exchangermb newExchangermb(Exchangermb exchangermb) {
        Exchangermb result = new Exchangermb();
        result.id = exchangermb.id;
        result.type = exchangermb.type;
        result.makerid = exchangermb.makerid;
        result.nmMaker = exchangermb.nmMaker;
        result.alipay = exchangermb.alipay;
        result.alipayName = exchangermb.alipayName;
        result.reason = exchangermb.reason;
        result.moneyCur = exchangermb.moneyCur;
        result.monyeApply = exchangermb.monyeApply;
        result.monyeReal = exchangermb.monyeReal;
        result.status = exchangermb.status;
        result.batchNo = exchangermb.batchNo;
        result.statusOpt = exchangermb.statusOpt;
        result.createtime = exchangermb.createtime;
        result.content = exchangermb.content;
        result.lasttime = exchangermb.lasttime;
        return result;
    }

    public Exchangermb createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getExchangermb(){
        Exchangermb exchangermb = null; // exchangermb
        { // new and insert Exchangermb (exchangermb)
            int id = 0; 	// id
            int type = 0; 	// type
            int makerid = 0; 	// makerid
            String nmMaker = ""; 	// nmMaker
            String alipay = ""; 	// alipay
            String alipayName = ""; 	// alipayName
            String reason = ""; 	// reason
            double moneyCur = 0.0; 	// moneyCur
            double monyeApply = 0.0; 	// monyeApply
            double monyeReal = 0.0; 	// monyeReal
            int status = 0; 	// status
            String batchNo = ""; 	// batchNo
            int statusOpt = 0; 	// statusOpt
            Date createtime = new Date(); 	// createtime
            String content = ""; 	// content
            Date lasttime = new Date(); 	// lasttime
            exchangermb = Exchangermb.newExchangermb(id, type, makerid, nmMaker, alipay, alipayName, reason, moneyCur, monyeApply, monyeReal, status, batchNo, statusOpt, createtime, content, lasttime);
        }
        exchangermb = exchangermb.insert();

        int id = exchangermb.getId(); 	// id
        int type = exchangermb.getType(); 	// type
        int makerid = exchangermb.getMakerid(); 	// makerid
        String nmMaker = exchangermb.getNmMaker(); 	// nmMaker
        String alipay = exchangermb.getAlipay(); 	// alipay
        String alipayName = exchangermb.getAlipayName(); 	// alipayName
        String reason = exchangermb.getReason(); 	// reason
        double moneyCur = exchangermb.getMoneyCur(); 	// moneyCur
        double monyeApply = exchangermb.getMonyeApply(); 	// monyeApply
        double monyeReal = exchangermb.getMonyeReal(); 	// monyeReal
        int status = exchangermb.getStatus(); 	// status
        String batchNo = exchangermb.getBatchNo(); 	// batchNo
        int statusOpt = exchangermb.getStatusOpt(); 	// statusOpt
        Date createtime = exchangermb.getCreatetime(); 	// createtime
        String content = exchangermb.getContent(); 	// content
        Date lasttime = exchangermb.getLasttime(); 	// lasttime
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
        case CEn.type:
            return type;
        case CEn.makerid:
            return makerid;
        case CEn.status:
            return status;
        case CEn.statusOpt:
            return statusOpt;
        }
        return 0;
    }

    public Exchangermb setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Exchangermb setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.type:
            return setType(value2);
        case CEn.makerid:
            return setMakerid(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.statusOpt:
            return setStatusOpt(value2);
        }
        return this;
    }

    public Exchangermb changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Exchangermb changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.type:
            return changeType(value2);
        case CEn.makerid:
            return changeMakerid(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.statusOpt:
            return changeStatusOpt(value2);
        }
        return this;
    }

    public Exchangermb changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Exchangermb changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.makerid:
            return changeMakeridWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.statusOpt:
            return changeStatusOptWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.moneyCur:
            return moneyCur;
        case CEn.monyeApply:
            return monyeApply;
        case CEn.monyeReal:
            return monyeReal;
        }
        return 0;
    }

    public Exchangermb setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Exchangermb setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.moneyCur:
            return setMoneyCur(value2);
        case CEn.monyeApply:
            return setMonyeApply(value2);
        case CEn.monyeReal:
            return setMonyeReal(value2);
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
        case CEn.nmMaker: 
            return nmMaker;
        case CEn.alipay: 
            return alipay;
        case CEn.alipayName: 
            return alipayName;
        case CEn.reason: 
            return reason;
        case CEn.batchNo: 
            return batchNo;
        case CEn.content: 
            return content;
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
        case CEn.type:
            return type;
        case CEn.makerid:
            return makerid;
        case CEn.nmMaker:
            return nmMaker;
        case CEn.alipay:
            return alipay;
        case CEn.alipayName:
            return alipayName;
        case CEn.reason:
            return reason;
        case CEn.moneyCur:
            return moneyCur;
        case CEn.monyeApply:
            return monyeApply;
        case CEn.monyeReal:
            return monyeReal;
        case CEn.status:
            return status;
        case CEn.batchNo:
            return batchNo;
        case CEn.statusOpt:
            return statusOpt;
        case CEn.createtime:
            return createtime;
        case CEn.content:
            return content;
        case CEn.lasttime:
            return lasttime;
        }
        return null;
    }

    public Exchangermb setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Exchangermb setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Exchangermb setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Exchangermb setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.nmMaker:
            return setNmMaker(value2);
        case CEn.alipay:
            return setAlipay(value2);
        case CEn.alipayName:
            return setAlipayName(value2);
        case CEn.reason:
            return setReason(value2);
        case CEn.batchNo:
            return setBatchNo(value2);
        case CEn.content:
            return setContent(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Exchangermb setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Exchangermb setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Exchangermb");
        result.put("id", id);
        result.put("type", type);
        result.put("makerid", makerid);
        result.put("nmMaker", nmMaker);
        result.put("alipay", alipay);
        result.put("alipayName", alipayName);
        result.put("reason", reason);
        result.put("moneyCur", moneyCur);
        result.put("monyeApply", monyeApply);
        result.put("monyeReal", monyeReal);
        result.put("status", status);
        result.put("batchNo", batchNo);
        result.put("statusOpt", statusOpt);
        result.put("createtime", createtime);
        result.put("content", content);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("type", type);
        result.put("makerid", makerid);
        result.put("nmMaker", nmMaker);
        result.put("alipay", alipay);
        result.put("alipayName", alipayName);
        result.put("reason", reason);
        result.put("moneyCur", moneyCur);
        result.put("monyeApply", monyeApply);
        result.put("monyeReal", monyeReal);
        result.put("status", status);
        result.put("batchNo", batchNo);
        result.put("statusOpt", statusOpt);
        result.put("createtime", createtime);
        result.put("content", content);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Exchangermb");
        result.put("id", id);
        result.put("type", type);
        result.put("makerid", makerid);
        result.put("nmMaker", nmMaker);
        result.put("alipay", alipay);
        result.put("alipayName", alipayName);
        result.put("reason", reason);
        result.put("moneyCur", moneyCur);
        result.put("monyeApply", monyeApply);
        result.put("monyeReal", monyeReal);
        result.put("status", status);
        result.put("batchNo", batchNo);
        result.put("statusOpt", statusOpt);
        result.put("createtime", createtime);
        result.put("content", content);
        result.put("lasttime", lasttime);
        return result;
    }

    public Exchangermb mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        Integer makerid = MapEx.getInt(e, "makerid");
        String nmMaker = MapEx.getString(e, "nmMaker");
        String alipay = MapEx.getString(e, "alipay");
        String alipayName = MapEx.getString(e, "alipayName");
        String reason = MapEx.getString(e, "reason");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        Double monyeApply = MapEx.getDouble(e, "monyeApply");
        Double monyeReal = MapEx.getDouble(e, "monyeReal");
        Integer status = MapEx.getInt(e, "status");
        String batchNo = MapEx.getString(e, "batchNo");
        Integer statusOpt = MapEx.getInt(e, "statusOpt");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String content = MapEx.getString(e, "content");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(makerid == null) makerid = 0;
        if(nmMaker == null) nmMaker = "";
        if(alipay == null) alipay = "";
        if(alipayName == null) alipayName = "";
        if(reason == null) reason = "";
        if(moneyCur == null) moneyCur = 0.0;
        if(monyeApply == null) monyeApply = 0.0;
        if(monyeReal == null) monyeReal = 0.0;
        if(status == null) status = 0;
        if(batchNo == null) batchNo = "";
        if(statusOpt == null) statusOpt = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(content == null) content = "";
        if(lasttime == null) lasttime = new java.util.Date();

        setId(id);
        setType(type);
        setMakerid(makerid);
        setNmMaker(nmMaker);
        setAlipay(alipay);
        setAlipayName(alipayName);
        setReason(reason);
        setMoneyCur(moneyCur);
        setMonyeApply(monyeApply);
        setMonyeReal(monyeReal);
        setStatus(status);
        setBatchNo(batchNo);
        setStatusOpt(statusOpt);
        setCreatetime(createtime);
        setContent(content);
        setLasttime(lasttime);

        return this;
    }

    public static final Exchangermb mapTo(Map e){
        Exchangermb result = new Exchangermb();

        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        Integer makerid = MapEx.getInt(e, "makerid");
        String nmMaker = MapEx.getString(e, "nmMaker");
        String alipay = MapEx.getString(e, "alipay");
        String alipayName = MapEx.getString(e, "alipayName");
        String reason = MapEx.getString(e, "reason");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        Double monyeApply = MapEx.getDouble(e, "monyeApply");
        Double monyeReal = MapEx.getDouble(e, "monyeReal");
        Integer status = MapEx.getInt(e, "status");
        String batchNo = MapEx.getString(e, "batchNo");
        Integer statusOpt = MapEx.getInt(e, "statusOpt");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String content = MapEx.getString(e, "content");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(makerid == null) makerid = 0;
        if(nmMaker == null) nmMaker = "";
        if(alipay == null) alipay = "";
        if(alipayName == null) alipayName = "";
        if(reason == null) reason = "";
        if(moneyCur == null) moneyCur = 0.0;
        if(monyeApply == null) monyeApply = 0.0;
        if(monyeReal == null) monyeReal = 0.0;
        if(status == null) status = 0;
        if(batchNo == null) batchNo = "";
        if(statusOpt == null) statusOpt = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(content == null) content = "";
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.type = type;
        result.makerid = makerid;
        result.nmMaker = nmMaker;
        result.alipay = alipay;
        result.alipayName = alipayName;
        result.reason = reason;
        result.moneyCur = moneyCur;
        result.monyeApply = monyeApply;
        result.monyeReal = monyeReal;
        result.status = status;
        result.batchNo = batchNo;
        result.statusOpt = statusOpt;
        result.createtime = createtime;
        result.content = content;
        result.lasttime = lasttime;

        return result;
    }

    public static final Exchangermb originalTo(Map e){
        Exchangermb result = new Exchangermb();

        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        Integer makerid = MapEx.getInt(e, "makerid");
        String nmMaker = MapEx.getString(e, "nmMaker");
        String alipay = MapEx.getString(e, "alipay");
        String alipayName = MapEx.getString(e, "alipayName");
        String reason = MapEx.getString(e, "reason");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        Double monyeApply = MapEx.getDouble(e, "monyeApply");
        Double monyeReal = MapEx.getDouble(e, "monyeReal");
        Integer status = MapEx.getInt(e, "status");
        String batchNo = MapEx.getString(e, "batchNo");
        Integer statusOpt = MapEx.getInt(e, "statusOpt");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String content = MapEx.getString(e, "content");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(makerid == null) makerid = 0;
        if(nmMaker == null) nmMaker = "";
        if(alipay == null) alipay = "";
        if(alipayName == null) alipayName = "";
        if(reason == null) reason = "";
        if(moneyCur == null) moneyCur = 0.0;
        if(monyeApply == null) monyeApply = 0.0;
        if(monyeReal == null) monyeReal = 0.0;
        if(status == null) status = 0;
        if(batchNo == null) batchNo = "";
        if(statusOpt == null) statusOpt = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(content == null) content = "";
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.type = type;
        result.makerid = makerid;
        result.nmMaker = nmMaker;
        result.alipay = alipay;
        result.alipayName = alipayName;
        result.reason = reason;
        result.moneyCur = moneyCur;
        result.monyeApply = monyeApply;
        result.monyeReal = monyeReal;
        result.status = status;
        result.batchNo = batchNo;
        result.statusOpt = statusOpt;
        result.createtime = createtime;
        result.content = content;
        result.lasttime = lasttime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Exchangermb createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 16);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "makerid", makerid);
            writeMapEntry(out, "nmMaker", nmMaker);
            writeMapEntry(out, "alipay", alipay);
            writeMapEntry(out, "alipayName", alipayName);
            writeMapEntry(out, "reason", reason);
            writeMapEntry(out, "moneyCur", moneyCur);
            writeMapEntry(out, "monyeApply", monyeApply);
            writeMapEntry(out, "monyeReal", monyeReal);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "batchNo", batchNo);
            writeMapEntry(out, "statusOpt", statusOpt);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "lasttime", lasttime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Exchangermb createFor(byte[] b) throws Exception {
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
    public static final Exchangermb loadByKey(int id) {
        return ExchangermbEntity.getByKey(id);
    }

    public static final Future<Exchangermb> asyncByKey(int id) {
        return ExchangermbEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Exchangermb insert() {
        Exchangermb result = ExchangermbEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Exchangermb asyncInsert() {
        Exchangermb result = ExchangermbEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Exchangermb insert2() {
        return ExchangermbEntity.insert2(this);
    }

    public final Exchangermb asyncInsert2() {
        Exchangermb result = ExchangermbEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Exchangermb update() {
        return ExchangermbEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ExchangermbEntity.asyncUpdate( this );
        return true;
    }

    public final Exchangermb insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ExchangermbEntity.delete(id);
    }

    public final void asyncDelete() {
        ExchangermbEntity.asyncDelete(id);
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

    public Exchangermb clone2() {
        try{
            return (Exchangermb) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
