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

//learnhall3_design - openkind4customer
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Openkind4customer extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -25669272; // com.learnhall.db.bean.Openkind4customer

    public static String TABLENAME = "openkind4customer";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String customerid = "customerid"; public static final String custname = "custname"; public static final String kindid = "kindid"; public static final String lhubid = "lhubid"; public static final String agentid = "agentid"; public static final String kbi = "kbi"; public static final String num = "num"; public static final String validity = "validity"; public static final String createtime = "createtime"; public static final String validtime = "validtime"; public static final String remarks = "remarks"; public static final String status = "status";  }
    public static final class CEn { public static final String id = "id"; public static final String customerid = "customerid"; public static final String custname = "custname"; public static final String kindid = "kindid"; public static final String lhubid = "lhubid"; public static final String agentid = "agentid"; public static final String kbi = "kbi"; public static final String num = "num"; public static final String validity = "validity"; public static final String createtime = "createtime"; public static final String validtime = "validtime"; public static final String remarks = "remarks"; public static final String status = "status";  }
    public static final String[] carrays ={"id", "customerid", "custname", "kindid", "lhubid", "agentid", "kbi", "num", "validity", "createtime", "validtime", "remarks", "status"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "INT", "INT", "INT", "INT", "INT", "INT", "DATETIME", "DATE", "TINYTEXT", "INT"};


    public int id;
    public int customerid;
    public String custname;
    public int kindid;
    public int lhubid;
    public int agentid;
    public int kbi;
    public int num;
    public int validity;
    public java.util.Date createtime;
    public java.util.Date validtime;
    public String remarks;
    public int status;

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

    public Openkind4customer setId(int id){
        this.id = id;
        return this;
    }

    public int getCustomerid(){
        return customerid;
    }

    public Openkind4customer setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Openkind4customer changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Openkind4customer changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public String getCustname(){
        return custname;
    }

    public Openkind4customer setCustname(String custname){
        String _old = this.custname;
        this.custname = custname;
        changeIt(Col.custname, _old, custname);
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Openkind4customer setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Openkind4customer changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Openkind4customer changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getLhubid(){
        return lhubid;
    }

    public Openkind4customer setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Openkind4customer changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Openkind4customer changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getAgentid(){
        return agentid;
    }

    public Openkind4customer setAgentid(int agentid){
        int _old = this.agentid;
        this.agentid = agentid;
        changeIt(Col.agentid, _old, agentid);
        return this;
    }

    public Openkind4customer changeAgentid(int agentid){
        return setAgentid(this.agentid + agentid);
    }

    public Openkind4customer changeAgentidWithMin(int agentid, int _min){
        int _v2 = this.agentid + agentid;
        return setAgentid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeAgentidWithMax(int agentid, int _max){
        int _v2 = this.agentid + agentid;
        return setAgentid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeAgentidWithMinMax(int agentid, int _min, int _max){
        int _v2 = this.agentid + agentid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAgentid(_v2 < _min  ? _min : _v2);
    }

    public int getKbi(){
        return kbi;
    }

    public Openkind4customer setKbi(int kbi){
        int _old = this.kbi;
        this.kbi = kbi;
        changeIt(Col.kbi, _old, kbi);
        return this;
    }

    public Openkind4customer changeKbi(int kbi){
        return setKbi(this.kbi + kbi);
    }

    public Openkind4customer changeKbiWithMin(int kbi, int _min){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeKbiWithMax(int kbi, int _max){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeKbiWithMinMax(int kbi, int _min, int _max){
        int _v2 = this.kbi + kbi;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public int getNum(){
        return num;
    }

    public Openkind4customer setNum(int num){
        int _old = this.num;
        this.num = num;
        changeIt(Col.num, _old, num);
        return this;
    }

    public Openkind4customer changeNum(int num){
        return setNum(this.num + num);
    }

    public Openkind4customer changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getValidity(){
        return validity;
    }

    public Openkind4customer setValidity(int validity){
        int _old = this.validity;
        this.validity = validity;
        changeIt(Col.validity, _old, validity);
        return this;
    }

    public Openkind4customer changeValidity(int validity){
        return setValidity(this.validity + validity);
    }

    public Openkind4customer changeValidityWithMin(int validity, int _min){
        int _v2 = this.validity + validity;
        return setValidity(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeValidityWithMax(int validity, int _max){
        int _v2 = this.validity + validity;
        return setValidity(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeValidityWithMinMax(int validity, int _min, int _max){
        int _v2 = this.validity + validity;
        _v2 = _v2 > _max  ? _max : _v2;
        return setValidity(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Openkind4customer setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getValidtime(){
        return validtime;
    }

    public Openkind4customer setValidtime(java.util.Date validtime){
        java.util.Date _old = this.validtime;
        this.validtime = validtime;
        changeIt(Col.validtime, _old, validtime);
        return this;
    }

    public String getRemarks(){
        return remarks;
    }

    public Openkind4customer setRemarks(String remarks){
        String _old = this.remarks;
        this.remarks = remarks;
        changeIt(Col.remarks, _old, remarks);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Openkind4customer setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Openkind4customer changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Openkind4customer changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Openkind4customer changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Openkind4customer changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Openkind4customer v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Openkind4customer v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Openkind4customer newOpenkind4customer(Integer id, Integer customerid, String custname, Integer kindid, Integer lhubid, Integer agentid, Integer kbi, Integer num, Integer validity, java.util.Date createtime, java.util.Date validtime, String remarks, Integer status) {
        Openkind4customer result = new Openkind4customer();
        result.id = id;
        result.customerid = customerid;
        result.custname = custname;
        result.kindid = kindid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.kbi = kbi;
        result.num = num;
        result.validity = validity;
        result.createtime = createtime;
        result.validtime = validtime;
        result.remarks = remarks;
        result.status = status;
        return result;
    }

    public static Openkind4customer newOpenkind4customer(Openkind4customer openkind4customer) {
        Openkind4customer result = new Openkind4customer();
        result.id = openkind4customer.id;
        result.customerid = openkind4customer.customerid;
        result.custname = openkind4customer.custname;
        result.kindid = openkind4customer.kindid;
        result.lhubid = openkind4customer.lhubid;
        result.agentid = openkind4customer.agentid;
        result.kbi = openkind4customer.kbi;
        result.num = openkind4customer.num;
        result.validity = openkind4customer.validity;
        result.createtime = openkind4customer.createtime;
        result.validtime = openkind4customer.validtime;
        result.remarks = openkind4customer.remarks;
        result.status = openkind4customer.status;
        return result;
    }

    public Openkind4customer createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getOpenkind4customer(){
        Openkind4customer openkind4customer = null; // openkind4customer
        { // new and insert Openkind4customer (openkind4customer)
            int id = 0; 	// id
            int customerid = 0; 	// customerid
            String custname = ""; 	// custname
            int kindid = 0; 	// kindid
            int lhubid = 0; 	// lhubid
            int agentid = 0; 	// agentid
            int kbi = 0; 	// kbi
            int num = 0; 	// num
            int validity = 0; 	// validity
            Date createtime = new Date(); 	// createtime
            Date validtime = new Date(); 	// validtime
            String remarks = ""; 	// remarks
            int status = 0; 	// status
            openkind4customer = Openkind4customer.newOpenkind4customer(id, customerid, custname, kindid, lhubid, agentid, kbi, num, validity, createtime, validtime, remarks, status);
        }
        openkind4customer = openkind4customer.insert();

        int id = openkind4customer.getId(); 	// id
        int customerid = openkind4customer.getCustomerid(); 	// customerid
        String custname = openkind4customer.getCustname(); 	// custname
        int kindid = openkind4customer.getKindid(); 	// kindid
        int lhubid = openkind4customer.getLhubid(); 	// lhubid
        int agentid = openkind4customer.getAgentid(); 	// agentid
        int kbi = openkind4customer.getKbi(); 	// kbi
        int num = openkind4customer.getNum(); 	// num
        int validity = openkind4customer.getValidity(); 	// validity
        Date createtime = openkind4customer.getCreatetime(); 	// createtime
        Date validtime = openkind4customer.getValidtime(); 	// validtime
        String remarks = openkind4customer.getRemarks(); 	// remarks
        int status = openkind4customer.getStatus(); 	// status
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
        case CEn.kindid:
            return kindid;
        case CEn.lhubid:
            return lhubid;
        case CEn.agentid:
            return agentid;
        case CEn.kbi:
            return kbi;
        case CEn.num:
            return num;
        case CEn.validity:
            return validity;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Openkind4customer setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Openkind4customer setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.agentid:
            return setAgentid(value2);
        case CEn.kbi:
            return setKbi(value2);
        case CEn.num:
            return setNum(value2);
        case CEn.validity:
            return setValidity(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Openkind4customer changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Openkind4customer changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.agentid:
            return changeAgentid(value2);
        case CEn.kbi:
            return changeKbi(value2);
        case CEn.num:
            return changeNum(value2);
        case CEn.validity:
            return changeValidity(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Openkind4customer changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Openkind4customer changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.agentid:
            return changeAgentidWithMin(value2, _min);
        case CEn.kbi:
            return changeKbiWithMin(value2, _min);
        case CEn.num:
            return changeNumWithMin(value2, _min);
        case CEn.validity:
            return changeValidityWithMin(value2, _min);
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

    public Openkind4customer setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Openkind4customer setDouble(String fieldEn, double value2) {
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
        case CEn.custname: 
            return custname;
        case CEn.remarks: 
            return remarks;
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
        case CEn.custname:
            return custname;
        case CEn.kindid:
            return kindid;
        case CEn.lhubid:
            return lhubid;
        case CEn.agentid:
            return agentid;
        case CEn.kbi:
            return kbi;
        case CEn.num:
            return num;
        case CEn.validity:
            return validity;
        case CEn.createtime:
            return createtime;
        case CEn.validtime:
            return validtime;
        case CEn.remarks:
            return remarks;
        case CEn.status:
            return status;
        }
        return null;
    }

    public Openkind4customer setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Openkind4customer setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Openkind4customer setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Openkind4customer setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.custname:
            return setCustname(value2);
        case CEn.remarks:
            return setRemarks(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Openkind4customer setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Openkind4customer setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Openkind4customer");
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("custname", custname);
        result.put("kindid", kindid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("kbi", kbi);
        result.put("num", num);
        result.put("validity", validity);
        result.put("createtime", createtime);
        result.put("validtime", validtime);
        result.put("remarks", remarks);
        result.put("status", status);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("custname", custname);
        result.put("kindid", kindid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("kbi", kbi);
        result.put("num", num);
        result.put("validity", validity);
        result.put("createtime", createtime);
        result.put("validtime", validtime);
        result.put("remarks", remarks);
        result.put("status", status);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Openkind4customer");
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("custname", custname);
        result.put("kindid", kindid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("kbi", kbi);
        result.put("num", num);
        result.put("validity", validity);
        result.put("createtime", createtime);
        result.put("validtime", validtime);
        result.put("remarks", remarks);
        result.put("status", status);
        return result;
    }

    public Openkind4customer mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        String custname = MapEx.getString(e, "custname");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer num = MapEx.getInt(e, "num");
        Integer validity = MapEx.getInt(e, "validity");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date validtime = MapEx.getDate(e, "validtime");
        String remarks = MapEx.getString(e, "remarks");
        Integer status = MapEx.getInt(e, "status");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(custname == null) custname = "";
        if(kindid == null) kindid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(kbi == null) kbi = 0;
        if(num == null) num = 0;
        if(validity == null) validity = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(validtime == null) validtime = new java.util.Date();
        if(remarks == null) remarks = "";
        if(status == null) status = 0;

        setId(id);
        setCustomerid(customerid);
        setCustname(custname);
        setKindid(kindid);
        setLhubid(lhubid);
        setAgentid(agentid);
        setKbi(kbi);
        setNum(num);
        setValidity(validity);
        setCreatetime(createtime);
        setValidtime(validtime);
        setRemarks(remarks);
        setStatus(status);

        return this;
    }

    public static final Openkind4customer mapTo(Map e){
        Openkind4customer result = new Openkind4customer();

        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        String custname = MapEx.getString(e, "custname");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer num = MapEx.getInt(e, "num");
        Integer validity = MapEx.getInt(e, "validity");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date validtime = MapEx.getDate(e, "validtime");
        String remarks = MapEx.getString(e, "remarks");
        Integer status = MapEx.getInt(e, "status");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(custname == null) custname = "";
        if(kindid == null) kindid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(kbi == null) kbi = 0;
        if(num == null) num = 0;
        if(validity == null) validity = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(validtime == null) validtime = new java.util.Date();
        if(remarks == null) remarks = "";
        if(status == null) status = 0;

        result.id = id;
        result.customerid = customerid;
        result.custname = custname;
        result.kindid = kindid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.kbi = kbi;
        result.num = num;
        result.validity = validity;
        result.createtime = createtime;
        result.validtime = validtime;
        result.remarks = remarks;
        result.status = status;

        return result;
    }

    public static final Openkind4customer originalTo(Map e){
        Openkind4customer result = new Openkind4customer();

        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        String custname = MapEx.getString(e, "custname");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer num = MapEx.getInt(e, "num");
        Integer validity = MapEx.getInt(e, "validity");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date validtime = MapEx.getDate(e, "validtime");
        String remarks = MapEx.getString(e, "remarks");
        Integer status = MapEx.getInt(e, "status");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(custname == null) custname = "";
        if(kindid == null) kindid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(kbi == null) kbi = 0;
        if(num == null) num = 0;
        if(validity == null) validity = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(validtime == null) validtime = new java.util.Date();
        if(remarks == null) remarks = "";
        if(status == null) status = 0;

        result.id = id;
        result.customerid = customerid;
        result.custname = custname;
        result.kindid = kindid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.kbi = kbi;
        result.num = num;
        result.validity = validity;
        result.createtime = createtime;
        result.validtime = validtime;
        result.remarks = remarks;
        result.status = status;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Openkind4customer createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 13);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "custname", custname);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "agentid", agentid);
            writeMapEntry(out, "kbi", kbi);
            writeMapEntry(out, "num", num);
            writeMapEntry(out, "validity", validity);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "validtime", validtime);
            writeMapEntry(out, "remarks", remarks);
            writeMapEntry(out, "status", status);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Openkind4customer createFor(byte[] b) throws Exception {
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

    public static final Openkind4customer loadByKey(int id) {
        return Openkind4customerEntity.getByKey(id);
    }

    public static final Future<Openkind4customer> asyncByKey(int id) {
        return Openkind4customerEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Openkind4customer insert() {
        Openkind4customer result = Openkind4customerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Openkind4customer asyncInsert() {
        Openkind4customer result = Openkind4customerEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Openkind4customer insert2() {
        return Openkind4customerEntity.insert2(this);
    }

    public final Openkind4customer asyncInsert2() {
        Openkind4customer result = Openkind4customerEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Openkind4customer update() {
        return Openkind4customerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Openkind4customerEntity.asyncUpdate( this );
        return true;
    }

    public final Openkind4customer insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Openkind4customerEntity.delete(id);
    }

    public final void asyncDelete() {
        Openkind4customerEntity.asyncDelete(id);
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

    public Openkind4customer clone2() {
        try{
            return (Openkind4customer) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
