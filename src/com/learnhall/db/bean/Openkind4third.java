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

//learnhall3_design - openkind4third
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Openkind4third extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -84463587; // com.learnhall.db.bean.Openkind4third

    public static String TABLENAME = "openkind4third";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String kindid = "kindid"; public static final String lhubid = "lhubid"; public static final String agentid = "agentid"; public static final String num = "num"; public static final String money = "money"; public static final String nmThird = "nmThird"; public static final String nmContact = "nmContact"; public static final String phone = "phone"; public static final String createtime = "createtime"; public static final String status = "status";  }
    public static final class CEn { public static final String id = "id"; public static final String kindid = "kindid"; public static final String lhubid = "lhubid"; public static final String agentid = "agentid"; public static final String num = "num"; public static final String money = "money"; public static final String nmThird = "nmThird"; public static final String nmContact = "nmContact"; public static final String phone = "phone"; public static final String createtime = "createtime"; public static final String status = "status";  }
    public static final String[] carrays ={"id", "kindid", "lhubid", "agentid", "num", "money", "nmThird", "nmContact", "phone", "createtime", "status"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "INT", "DOUBLE", "TINYTEXT", "TINYTEXT", "VARCHAR", "DATETIME", "INT"};


    public int id;
    public int kindid;
    public int lhubid;
    public int agentid;
    public int num;
    public double money;
    public String nmThird;
    public String nmContact;
    public String phone;
    public java.util.Date createtime;
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

    public Openkind4third setId(int id){
        this.id = id;
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Openkind4third setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Openkind4third changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Openkind4third changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4third changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4third changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getLhubid(){
        return lhubid;
    }

    public Openkind4third setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Openkind4third changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Openkind4third changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4third changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4third changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getAgentid(){
        return agentid;
    }

    public Openkind4third setAgentid(int agentid){
        int _old = this.agentid;
        this.agentid = agentid;
        changeIt(Col.agentid, _old, agentid);
        return this;
    }

    public Openkind4third changeAgentid(int agentid){
        return setAgentid(this.agentid + agentid);
    }

    public Openkind4third changeAgentidWithMin(int agentid, int _min){
        int _v2 = this.agentid + agentid;
        return setAgentid(_v2 < _min  ? _min : _v2);
    }

    public Openkind4third changeAgentidWithMax(int agentid, int _max){
        int _v2 = this.agentid + agentid;
        return setAgentid(_v2 > _max  ? _max : _v2);
    }

    public Openkind4third changeAgentidWithMinMax(int agentid, int _min, int _max){
        int _v2 = this.agentid + agentid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAgentid(_v2 < _min  ? _min : _v2);
    }

    public int getNum(){
        return num;
    }

    public Openkind4third setNum(int num){
        int _old = this.num;
        this.num = num;
        changeIt(Col.num, _old, num);
        return this;
    }

    public Openkind4third changeNum(int num){
        return setNum(this.num + num);
    }

    public Openkind4third changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Openkind4third changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Openkind4third changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public double getMoney(){
        return money;
    }

    public Openkind4third setMoney(double money){
        double _old = this.money;
        this.money = money;
        changeIt(Col.money, _old, money);
        return this;
    }

    public Openkind4third changeMoney(double money){
        return setMoney(this.money + money);
    }

    public Openkind4third changeMoneyWithMin(double money, double _min){
        double _v2 = this.money + money;
        return setMoney(_v2 < _min  ? _min : _v2);
    }

    public Openkind4third changeMoneyWithMax(double money, double _max){
        double _v2 = this.money + money;
        return setMoney(_v2 > _max  ? _max : _v2);
    }

    public Openkind4third changeMoneyWithMinMax(double money, double _min, double _max){
        double _v2 = this.money + money;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoney(_v2 < _min  ? _min : _v2);
    }

    public String getNmThird(){
        return nmThird;
    }

    public Openkind4third setNmThird(String nmThird){
        String _old = this.nmThird;
        this.nmThird = nmThird;
        changeIt(Col.nmThird, _old, nmThird);
        return this;
    }

    public String getNmContact(){
        return nmContact;
    }

    public Openkind4third setNmContact(String nmContact){
        String _old = this.nmContact;
        this.nmContact = nmContact;
        changeIt(Col.nmContact, _old, nmContact);
        return this;
    }

    public String getPhone(){
        return phone;
    }

    public Openkind4third setPhone(String phone){
        String _old = this.phone;
        this.phone = phone;
        changeIt(Col.phone, _old, phone);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Openkind4third setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Openkind4third setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Openkind4third changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Openkind4third changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Openkind4third changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Openkind4third changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Openkind4third v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Openkind4third v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Openkind4third newOpenkind4third(Integer id, Integer kindid, Integer lhubid, Integer agentid, Integer num, Double money, String nmThird, String nmContact, String phone, java.util.Date createtime, Integer status) {
        Openkind4third result = new Openkind4third();
        result.id = id;
        result.kindid = kindid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.num = num;
        result.money = money;
        result.nmThird = nmThird;
        result.nmContact = nmContact;
        result.phone = phone;
        result.createtime = createtime;
        result.status = status;
        return result;
    }

    public static Openkind4third newOpenkind4third(Openkind4third openkind4third) {
        Openkind4third result = new Openkind4third();
        result.id = openkind4third.id;
        result.kindid = openkind4third.kindid;
        result.lhubid = openkind4third.lhubid;
        result.agentid = openkind4third.agentid;
        result.num = openkind4third.num;
        result.money = openkind4third.money;
        result.nmThird = openkind4third.nmThird;
        result.nmContact = openkind4third.nmContact;
        result.phone = openkind4third.phone;
        result.createtime = openkind4third.createtime;
        result.status = openkind4third.status;
        return result;
    }

    public Openkind4third createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getOpenkind4third(){
        Openkind4third openkind4third = null; // openkind4third
        { // new and insert Openkind4third (openkind4third)
            int id = 0; 	// id
            int kindid = 0; 	// kindid
            int lhubid = 0; 	// lhubid
            int agentid = 0; 	// agentid
            int num = 0; 	// num
            double money = 0.0; 	// money
            String nmThird = ""; 	// nmThird
            String nmContact = ""; 	// nmContact
            String phone = ""; 	// phone
            Date createtime = new Date(); 	// createtime
            int status = 0; 	// status
            openkind4third = Openkind4third.newOpenkind4third(id, kindid, lhubid, agentid, num, money, nmThird, nmContact, phone, createtime, status);
        }
        openkind4third = openkind4third.insert();

        int id = openkind4third.getId(); 	// id
        int kindid = openkind4third.getKindid(); 	// kindid
        int lhubid = openkind4third.getLhubid(); 	// lhubid
        int agentid = openkind4third.getAgentid(); 	// agentid
        int num = openkind4third.getNum(); 	// num
        double money = openkind4third.getMoney(); 	// money
        String nmThird = openkind4third.getNmThird(); 	// nmThird
        String nmContact = openkind4third.getNmContact(); 	// nmContact
        String phone = openkind4third.getPhone(); 	// phone
        Date createtime = openkind4third.getCreatetime(); 	// createtime
        int status = openkind4third.getStatus(); 	// status
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
        case CEn.lhubid:
            return lhubid;
        case CEn.agentid:
            return agentid;
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Openkind4third setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Openkind4third setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.agentid:
            return setAgentid(value2);
        case CEn.num:
            return setNum(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Openkind4third changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Openkind4third changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.agentid:
            return changeAgentid(value2);
        case CEn.num:
            return changeNum(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Openkind4third changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Openkind4third changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.agentid:
            return changeAgentidWithMin(value2, _min);
        case CEn.num:
            return changeNumWithMin(value2, _min);
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
        case CEn.money:
            return money;
        }
        return 0;
    }

    public Openkind4third setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Openkind4third setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.money:
            return setMoney(value2);
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
        case CEn.nmThird: 
            return nmThird;
        case CEn.nmContact: 
            return nmContact;
        case CEn.phone: 
            return phone;
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
        case CEn.kindid:
            return kindid;
        case CEn.lhubid:
            return lhubid;
        case CEn.agentid:
            return agentid;
        case CEn.num:
            return num;
        case CEn.money:
            return money;
        case CEn.nmThird:
            return nmThird;
        case CEn.nmContact:
            return nmContact;
        case CEn.phone:
            return phone;
        case CEn.createtime:
            return createtime;
        case CEn.status:
            return status;
        }
        return null;
    }

    public Openkind4third setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Openkind4third setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Openkind4third setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Openkind4third setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.nmThird:
            return setNmThird(value2);
        case CEn.nmContact:
            return setNmContact(value2);
        case CEn.phone:
            return setPhone(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Openkind4third setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Openkind4third setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Openkind4third");
        result.put("id", id);
        result.put("kindid", kindid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("num", num);
        result.put("money", money);
        result.put("nmThird", nmThird);
        result.put("nmContact", nmContact);
        result.put("phone", phone);
        result.put("createtime", createtime);
        result.put("status", status);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("kindid", kindid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("num", num);
        result.put("money", money);
        result.put("nmThird", nmThird);
        result.put("nmContact", nmContact);
        result.put("phone", phone);
        result.put("createtime", createtime);
        result.put("status", status);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Openkind4third");
        result.put("id", id);
        result.put("kindid", kindid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("num", num);
        result.put("money", money);
        result.put("nmThird", nmThird);
        result.put("nmContact", nmContact);
        result.put("phone", phone);
        result.put("createtime", createtime);
        result.put("status", status);
        return result;
    }

    public Openkind4third mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Integer num = MapEx.getInt(e, "num");
        Double money = MapEx.getDouble(e, "money");
        String nmThird = MapEx.getString(e, "nmThird");
        String nmContact = MapEx.getString(e, "nmContact");
        String phone = MapEx.getString(e, "phone");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status = MapEx.getInt(e, "status");

        if(id == null) id = 0;
        if(kindid == null) kindid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(num == null) num = 0;
        if(money == null) money = 0.0;
        if(nmThird == null) nmThird = "";
        if(nmContact == null) nmContact = "";
        if(phone == null) phone = "";
        if(createtime == null) createtime = new java.util.Date();
        if(status == null) status = 0;

        setId(id);
        setKindid(kindid);
        setLhubid(lhubid);
        setAgentid(agentid);
        setNum(num);
        setMoney(money);
        setNmThird(nmThird);
        setNmContact(nmContact);
        setPhone(phone);
        setCreatetime(createtime);
        setStatus(status);

        return this;
    }

    public static final Openkind4third mapTo(Map e){
        Openkind4third result = new Openkind4third();

        Integer id = MapEx.getInt(e, "id");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Integer num = MapEx.getInt(e, "num");
        Double money = MapEx.getDouble(e, "money");
        String nmThird = MapEx.getString(e, "nmThird");
        String nmContact = MapEx.getString(e, "nmContact");
        String phone = MapEx.getString(e, "phone");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status = MapEx.getInt(e, "status");

        if(id == null) id = 0;
        if(kindid == null) kindid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(num == null) num = 0;
        if(money == null) money = 0.0;
        if(nmThird == null) nmThird = "";
        if(nmContact == null) nmContact = "";
        if(phone == null) phone = "";
        if(createtime == null) createtime = new java.util.Date();
        if(status == null) status = 0;

        result.id = id;
        result.kindid = kindid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.num = num;
        result.money = money;
        result.nmThird = nmThird;
        result.nmContact = nmContact;
        result.phone = phone;
        result.createtime = createtime;
        result.status = status;

        return result;
    }

    public static final Openkind4third originalTo(Map e){
        Openkind4third result = new Openkind4third();

        Integer id = MapEx.getInt(e, "id");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Integer num = MapEx.getInt(e, "num");
        Double money = MapEx.getDouble(e, "money");
        String nmThird = MapEx.getString(e, "nmThird");
        String nmContact = MapEx.getString(e, "nmContact");
        String phone = MapEx.getString(e, "phone");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status = MapEx.getInt(e, "status");

        if(id == null) id = 0;
        if(kindid == null) kindid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(num == null) num = 0;
        if(money == null) money = 0.0;
        if(nmThird == null) nmThird = "";
        if(nmContact == null) nmContact = "";
        if(phone == null) phone = "";
        if(createtime == null) createtime = new java.util.Date();
        if(status == null) status = 0;

        result.id = id;
        result.kindid = kindid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.num = num;
        result.money = money;
        result.nmThird = nmThird;
        result.nmContact = nmContact;
        result.phone = phone;
        result.createtime = createtime;
        result.status = status;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Openkind4third createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 11);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "agentid", agentid);
            writeMapEntry(out, "num", num);
            writeMapEntry(out, "money", money);
            writeMapEntry(out, "nmThird", nmThird);
            writeMapEntry(out, "nmContact", nmContact);
            writeMapEntry(out, "phone", phone);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "status", status);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Openkind4third createFor(byte[] b) throws Exception {
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
    public final Agent getAgentFkAgentid() { // agent - agentid
        return AgentEntity.getByKey(agentid);
    }

    public final int countAgentFkAgentid() { // agent - agentid
        return getAgentFkAgentid() == null ? 0 : 1;
    }

    public final Kind getKindFkKindid() { // kind - kindid
        return KindEntity.getByKey(kindid);
    }

    public final int countKindFkKindid() { // kind - kindid
        return getKindFkKindid() == null ? 0 : 1;
    }

    public final Learnhub getLearnhubFkLhubid() { // learnhub - lhubid
        return LearnhubEntity.getByKey(lhubid);
    }

    public final int countLearnhubFkLhubid() { // learnhub - lhubid
        return getLearnhubFkLhubid() == null ? 0 : 1;
    }

    public static final Openkind4third loadByKey(int id) {
        return Openkind4thirdEntity.getByKey(id);
    }

    public static final Future<Openkind4third> asyncByKey(int id) {
        return Openkind4thirdEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Openkind4third insert() {
        Openkind4third result = Openkind4thirdEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Openkind4third asyncInsert() {
        Openkind4third result = Openkind4thirdEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Openkind4third insert2() {
        return Openkind4thirdEntity.insert2(this);
    }

    public final Openkind4third asyncInsert2() {
        Openkind4third result = Openkind4thirdEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Openkind4third update() {
        return Openkind4thirdEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Openkind4thirdEntity.asyncUpdate( this );
        return true;
    }

    public final Openkind4third insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Openkind4thirdEntity.delete(id);
    }

    public final void asyncDelete() {
        Openkind4thirdEntity.asyncDelete(id);
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

    public Openkind4third clone2() {
        try{
            return (Openkind4third) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
