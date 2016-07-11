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

//learnhall3_design - boughtkinds
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Boughtkinds extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 791737544; // com.learnhall.db.bean.Boughtkinds

    public static String TABLENAME = "boughtkinds";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String name = "name"; public static final String customerid = "customerid"; public static final String kindid = "kindid"; public static final String price = "price"; public static final String kbi = "kbi"; public static final String num = "num"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String validtime = "validtime"; public static final String lhubid = "lhubid";  }
    public static final class CEn { public static final String id = "id"; public static final String name = "name"; public static final String customerid = "customerid"; public static final String kindid = "kindid"; public static final String price = "price"; public static final String kbi = "kbi"; public static final String num = "num"; public static final String status = "status"; public static final String createtime = "createtime"; public static final String validtime = "validtime"; public static final String lhubid = "lhubid";  }
    public static final String[] carrays ={"id", "name", "customerid", "kindid", "price", "kbi", "num", "status", "createtime", "validtime", "lhubid"};
    public static final String[] dbTypes ={"INT", "TINYTEXT", "INT", "INT", "DOUBLE", "INT", "INT", "INT", "DATETIME", "DATE", "INT"};


    public int id;
    public String name;
    public int customerid;
    public int kindid;
    public double price;
    public int kbi;
    public int num;
    public int status;
    public java.util.Date createtime;
    public java.util.Date validtime;
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

    public Boughtkinds setId(int id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Boughtkinds setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public int getCustomerid(){
        return customerid;
    }

    public Boughtkinds setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Boughtkinds changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Boughtkinds changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public int getKindid(){
        return kindid;
    }

    public Boughtkinds setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Boughtkinds changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Boughtkinds changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public double getPrice(){
        return price;
    }

    public Boughtkinds setPrice(double price){
        double _old = this.price;
        this.price = price;
        changeIt(Col.price, _old, price);
        return this;
    }

    public Boughtkinds changePrice(double price){
        return setPrice(this.price + price);
    }

    public Boughtkinds changePriceWithMin(double price, double _min){
        double _v2 = this.price + price;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changePriceWithMax(double price, double _max){
        double _v2 = this.price + price;
        return setPrice(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changePriceWithMinMax(double price, double _min, double _max){
        double _v2 = this.price + price;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public int getKbi(){
        return kbi;
    }

    public Boughtkinds setKbi(int kbi){
        int _old = this.kbi;
        this.kbi = kbi;
        changeIt(Col.kbi, _old, kbi);
        return this;
    }

    public Boughtkinds changeKbi(int kbi){
        return setKbi(this.kbi + kbi);
    }

    public Boughtkinds changeKbiWithMin(int kbi, int _min){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changeKbiWithMax(int kbi, int _max){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changeKbiWithMinMax(int kbi, int _min, int _max){
        int _v2 = this.kbi + kbi;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public int getNum(){
        return num;
    }

    public Boughtkinds setNum(int num){
        int _old = this.num;
        this.num = num;
        changeIt(Col.num, _old, num);
        return this;
    }

    public Boughtkinds changeNum(int num){
        return setNum(this.num + num);
    }

    public Boughtkinds changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Boughtkinds setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Boughtkinds changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Boughtkinds changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Boughtkinds setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getValidtime(){
        return validtime;
    }

    public Boughtkinds setValidtime(java.util.Date validtime){
        java.util.Date _old = this.validtime;
        this.validtime = validtime;
        changeIt(Col.validtime, _old, validtime);
        return this;
    }

    public int getLhubid(){
        return lhubid;
    }

    public Boughtkinds setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Boughtkinds changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Boughtkinds changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Boughtkinds changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Boughtkinds changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Boughtkinds v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Boughtkinds v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Boughtkinds newBoughtkinds(Integer id, String name, Integer customerid, Integer kindid, Double price, Integer kbi, Integer num, Integer status, java.util.Date createtime, java.util.Date validtime, Integer lhubid) {
        Boughtkinds result = new Boughtkinds();
        result.id = id;
        result.name = name;
        result.customerid = customerid;
        result.kindid = kindid;
        result.price = price;
        result.kbi = kbi;
        result.num = num;
        result.status = status;
        result.createtime = createtime;
        result.validtime = validtime;
        result.lhubid = lhubid;
        return result;
    }

    public static Boughtkinds newBoughtkinds(Boughtkinds boughtkinds) {
        Boughtkinds result = new Boughtkinds();
        result.id = boughtkinds.id;
        result.name = boughtkinds.name;
        result.customerid = boughtkinds.customerid;
        result.kindid = boughtkinds.kindid;
        result.price = boughtkinds.price;
        result.kbi = boughtkinds.kbi;
        result.num = boughtkinds.num;
        result.status = boughtkinds.status;
        result.createtime = boughtkinds.createtime;
        result.validtime = boughtkinds.validtime;
        result.lhubid = boughtkinds.lhubid;
        return result;
    }

    public Boughtkinds createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getBoughtkinds(){
        Boughtkinds boughtkinds = null; // boughtkinds
        { // new and insert Boughtkinds (boughtkinds)
            int id = 0; 	// id
            String name = ""; 	// name
            int customerid = 0; 	// customerid
            int kindid = 0; 	// kindid
            double price = 0.0; 	// price
            int kbi = 0; 	// kbi
            int num = 0; 	// num
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            Date validtime = new Date(); 	// validtime
            int lhubid = 0; 	// lhubid
            boughtkinds = Boughtkinds.newBoughtkinds(id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid);
        }
        boughtkinds = boughtkinds.insert();

        int id = boughtkinds.getId(); 	// id
        String name = boughtkinds.getName(); 	// name
        int customerid = boughtkinds.getCustomerid(); 	// customerid
        int kindid = boughtkinds.getKindid(); 	// kindid
        double price = boughtkinds.getPrice(); 	// price
        int kbi = boughtkinds.getKbi(); 	// kbi
        int num = boughtkinds.getNum(); 	// num
        int status = boughtkinds.getStatus(); 	// status
        Date createtime = boughtkinds.getCreatetime(); 	// createtime
        Date validtime = boughtkinds.getValidtime(); 	// validtime
        int lhubid = boughtkinds.getLhubid(); 	// lhubid
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
        case CEn.kbi:
            return kbi;
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        case CEn.lhubid:
            return lhubid;
        }
        return 0;
    }

    public Boughtkinds setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Boughtkinds setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.kbi:
            return setKbi(value2);
        case CEn.num:
            return setNum(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        }
        return this;
    }

    public Boughtkinds changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Boughtkinds changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.kbi:
            return changeKbi(value2);
        case CEn.num:
            return changeNum(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        }
        return this;
    }

    public Boughtkinds changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Boughtkinds changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.kbi:
            return changeKbiWithMin(value2, _min);
        case CEn.num:
            return changeNumWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
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
        case CEn.price:
            return price;
        }
        return 0;
    }

    public Boughtkinds setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Boughtkinds setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.price:
            return setPrice(value2);
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
        case CEn.name:
            return name;
        case CEn.customerid:
            return customerid;
        case CEn.kindid:
            return kindid;
        case CEn.price:
            return price;
        case CEn.kbi:
            return kbi;
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        case CEn.validtime:
            return validtime;
        case CEn.lhubid:
            return lhubid;
        }
        return null;
    }

    public Boughtkinds setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Boughtkinds setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Boughtkinds setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Boughtkinds setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Boughtkinds setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Boughtkinds setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Boughtkinds");
        result.put("id", id);
        result.put("name", name);
        result.put("customerid", customerid);
        result.put("kindid", kindid);
        result.put("price", price);
        result.put("kbi", kbi);
        result.put("num", num);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("validtime", validtime);
        result.put("lhubid", lhubid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("name", name);
        result.put("customerid", customerid);
        result.put("kindid", kindid);
        result.put("price", price);
        result.put("kbi", kbi);
        result.put("num", num);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("validtime", validtime);
        result.put("lhubid", lhubid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Boughtkinds");
        result.put("id", id);
        result.put("name", name);
        result.put("customerid", customerid);
        result.put("kindid", kindid);
        result.put("price", price);
        result.put("kbi", kbi);
        result.put("num", num);
        result.put("status", status);
        result.put("createtime", createtime);
        result.put("validtime", validtime);
        result.put("lhubid", lhubid);
        return result;
    }

    public Boughtkinds mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer kindid = MapEx.getInt(e, "kindid");
        Double price = MapEx.getDouble(e, "price");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date validtime = MapEx.getDate(e, "validtime");
        Integer lhubid = MapEx.getInt(e, "lhubid");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(customerid == null) customerid = 0;
        if(kindid == null) kindid = 0;
        if(price == null) price = 0.0;
        if(kbi == null) kbi = 0;
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(validtime == null) validtime = new java.util.Date();
        if(lhubid == null) lhubid = 0;

        setId(id);
        setName(name);
        setCustomerid(customerid);
        setKindid(kindid);
        setPrice(price);
        setKbi(kbi);
        setNum(num);
        setStatus(status);
        setCreatetime(createtime);
        setValidtime(validtime);
        setLhubid(lhubid);

        return this;
    }

    public static final Boughtkinds mapTo(Map e){
        Boughtkinds result = new Boughtkinds();

        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer kindid = MapEx.getInt(e, "kindid");
        Double price = MapEx.getDouble(e, "price");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date validtime = MapEx.getDate(e, "validtime");
        Integer lhubid = MapEx.getInt(e, "lhubid");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(customerid == null) customerid = 0;
        if(kindid == null) kindid = 0;
        if(price == null) price = 0.0;
        if(kbi == null) kbi = 0;
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(validtime == null) validtime = new java.util.Date();
        if(lhubid == null) lhubid = 0;

        result.id = id;
        result.name = name;
        result.customerid = customerid;
        result.kindid = kindid;
        result.price = price;
        result.kbi = kbi;
        result.num = num;
        result.status = status;
        result.createtime = createtime;
        result.validtime = validtime;
        result.lhubid = lhubid;

        return result;
    }

    public static final Boughtkinds originalTo(Map e){
        Boughtkinds result = new Boughtkinds();

        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer kindid = MapEx.getInt(e, "kindid");
        Double price = MapEx.getDouble(e, "price");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date validtime = MapEx.getDate(e, "validtime");
        Integer lhubid = MapEx.getInt(e, "lhubid");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(customerid == null) customerid = 0;
        if(kindid == null) kindid = 0;
        if(price == null) price = 0.0;
        if(kbi == null) kbi = 0;
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(validtime == null) validtime = new java.util.Date();
        if(lhubid == null) lhubid = 0;

        result.id = id;
        result.name = name;
        result.customerid = customerid;
        result.kindid = kindid;
        result.price = price;
        result.kbi = kbi;
        result.num = num;
        result.status = status;
        result.createtime = createtime;
        result.validtime = validtime;
        result.lhubid = lhubid;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Boughtkinds createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 11);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "price", price);
            writeMapEntry(out, "kbi", kbi);
            writeMapEntry(out, "num", num);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "validtime", validtime);
            writeMapEntry(out, "lhubid", lhubid);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Boughtkinds createFor(byte[] b) throws Exception {
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

    public static final Boughtkinds loadByKey(int id) {
        return BoughtkindsEntity.getByKey(id);
    }

    public static final Future<Boughtkinds> asyncByKey(int id) {
        return BoughtkindsEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Boughtkinds insert() {
        Boughtkinds result = BoughtkindsEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Boughtkinds asyncInsert() {
        Boughtkinds result = BoughtkindsEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Boughtkinds insert2() {
        return BoughtkindsEntity.insert2(this);
    }

    public final Boughtkinds asyncInsert2() {
        Boughtkinds result = BoughtkindsEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Boughtkinds update() {
        return BoughtkindsEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        BoughtkindsEntity.asyncUpdate( this );
        return true;
    }

    public final Boughtkinds insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return BoughtkindsEntity.delete(id);
    }

    public final void asyncDelete() {
        BoughtkindsEntity.asyncDelete(id);
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

    public Boughtkinds clone2() {
        try{
            return (Boughtkinds) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
