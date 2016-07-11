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

//learnhall3_design - recordfee4customer
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Recordfee4customer extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1114388439; // com.learnhall.db.bean.Recordfee4customer

    public static String TABLENAME = "recordfee4customer";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String type = "type"; public static final String custid = "custid"; public static final String custname = "custname"; public static final String val = "val"; public static final String cont = "cont"; public static final String kindid = "kindid"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String type = "type"; public static final String custid = "custid"; public static final String custname = "custname"; public static final String val = "val"; public static final String cont = "cont"; public static final String kindid = "kindid"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "type", "custid", "custname", "val", "cont", "kindid", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TINYTEXT", "DOUBLE", "TINYTEXT", "INT", "DATETIME"};


    public int id;
    public int type;
    public int custid;
    public String custname;
    public double val;
    public String cont;
    public int kindid;
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

    public Recordfee4customer setId(int id){
        this.id = id;
        return this;
    }

    public int getType(){
        return type;
    }

    public Recordfee4customer setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Recordfee4customer changeType(int type){
        return setType(this.type + type);
    }

    public Recordfee4customer changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Recordfee4customer changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Recordfee4customer changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int getCustid(){
        return custid;
    }

    public Recordfee4customer setCustid(int custid){
        int _old = this.custid;
        this.custid = custid;
        changeIt(Col.custid, _old, custid);
        return this;
    }

    public Recordfee4customer changeCustid(int custid){
        return setCustid(this.custid + custid);
    }

    public Recordfee4customer changeCustidWithMin(int custid, int _min){
        int _v2 = this.custid + custid;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public Recordfee4customer changeCustidWithMax(int custid, int _max){
        int _v2 = this.custid + custid;
        return setCustid(_v2 > _max  ? _max : _v2);
    }

    public Recordfee4customer changeCustidWithMinMax(int custid, int _min, int _max){
        int _v2 = this.custid + custid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public String getCustname(){
        return custname;
    }

    public Recordfee4customer setCustname(String custname){
        String _old = this.custname;
        this.custname = custname;
        changeIt(Col.custname, _old, custname);
        return this;
    }

    public double getVal(){
        return val;
    }

    public Recordfee4customer setVal(double val){
        double _old = this.val;
        this.val = val;
        changeIt(Col.val, _old, val);
        return this;
    }

    public Recordfee4customer changeVal(double val){
        return setVal(this.val + val);
    }

    public Recordfee4customer changeValWithMin(double val, double _min){
        double _v2 = this.val + val;
        return setVal(_v2 < _min  ? _min : _v2);
    }

    public Recordfee4customer changeValWithMax(double val, double _max){
        double _v2 = this.val + val;
        return setVal(_v2 > _max  ? _max : _v2);
    }

    public Recordfee4customer changeValWithMinMax(double val, double _min, double _max){
        double _v2 = this.val + val;
        _v2 = _v2 > _max  ? _max : _v2;
        return setVal(_v2 < _min  ? _min : _v2);
    }

    public String getCont(){
        return cont;
    }

    public Recordfee4customer setCont(String cont){
        String _old = this.cont;
        this.cont = cont;
        changeIt(Col.cont, _old, cont);
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Recordfee4customer setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Recordfee4customer changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Recordfee4customer changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Recordfee4customer changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Recordfee4customer changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Recordfee4customer setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Recordfee4customer v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Recordfee4customer v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Recordfee4customer newRecordfee4customer(Integer id, Integer type, Integer custid, String custname, Double val, String cont, Integer kindid, java.util.Date createtime) {
        Recordfee4customer result = new Recordfee4customer();
        result.id = id;
        result.type = type;
        result.custid = custid;
        result.custname = custname;
        result.val = val;
        result.cont = cont;
        result.kindid = kindid;
        result.createtime = createtime;
        return result;
    }

    public static Recordfee4customer newRecordfee4customer(Recordfee4customer recordfee4customer) {
        Recordfee4customer result = new Recordfee4customer();
        result.id = recordfee4customer.id;
        result.type = recordfee4customer.type;
        result.custid = recordfee4customer.custid;
        result.custname = recordfee4customer.custname;
        result.val = recordfee4customer.val;
        result.cont = recordfee4customer.cont;
        result.kindid = recordfee4customer.kindid;
        result.createtime = recordfee4customer.createtime;
        return result;
    }

    public Recordfee4customer createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecordfee4customer(){
        Recordfee4customer recordfee4customer = null; // recordfee4customer
        { // new and insert Recordfee4customer (recordfee4customer)
            int id = 0; 	// id
            int type = 0; 	// type
            int custid = 0; 	// custid
            String custname = ""; 	// custname
            double val = 0.0; 	// val
            String cont = ""; 	// cont
            int kindid = 0; 	// kindid
            Date createtime = new Date(); 	// createtime
            recordfee4customer = Recordfee4customer.newRecordfee4customer(id, type, custid, custname, val, cont, kindid, createtime);
        }
        recordfee4customer = recordfee4customer.insert();

        int id = recordfee4customer.getId(); 	// id
        int type = recordfee4customer.getType(); 	// type
        int custid = recordfee4customer.getCustid(); 	// custid
        String custname = recordfee4customer.getCustname(); 	// custname
        double val = recordfee4customer.getVal(); 	// val
        String cont = recordfee4customer.getCont(); 	// cont
        int kindid = recordfee4customer.getKindid(); 	// kindid
        Date createtime = recordfee4customer.getCreatetime(); 	// createtime
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
        case CEn.custid:
            return custid;
        case CEn.kindid:
            return kindid;
        }
        return 0;
    }

    public Recordfee4customer setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Recordfee4customer setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.type:
            return setType(value2);
        case CEn.custid:
            return setCustid(value2);
        case CEn.kindid:
            return setKindid(value2);
        }
        return this;
    }

    public Recordfee4customer changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Recordfee4customer changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.type:
            return changeType(value2);
        case CEn.custid:
            return changeCustid(value2);
        case CEn.kindid:
            return changeKindid(value2);
        }
        return this;
    }

    public Recordfee4customer changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Recordfee4customer changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.custid:
            return changeCustidWithMin(value2, _min);
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.val:
            return val;
        }
        return 0;
    }

    public Recordfee4customer setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Recordfee4customer setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.val:
            return setVal(value2);
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
        case CEn.cont: 
            return cont;
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
        case CEn.custid:
            return custid;
        case CEn.custname:
            return custname;
        case CEn.val:
            return val;
        case CEn.cont:
            return cont;
        case CEn.kindid:
            return kindid;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Recordfee4customer setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Recordfee4customer setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Recordfee4customer setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Recordfee4customer setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.custname:
            return setCustname(value2);
        case CEn.cont:
            return setCont(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Recordfee4customer setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Recordfee4customer setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Recordfee4customer");
        result.put("id", id);
        result.put("type", type);
        result.put("custid", custid);
        result.put("custname", custname);
        result.put("val", val);
        result.put("cont", cont);
        result.put("kindid", kindid);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("type", type);
        result.put("custid", custid);
        result.put("custname", custname);
        result.put("val", val);
        result.put("cont", cont);
        result.put("kindid", kindid);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Recordfee4customer");
        result.put("id", id);
        result.put("type", type);
        result.put("custid", custid);
        result.put("custname", custname);
        result.put("val", val);
        result.put("cont", cont);
        result.put("kindid", kindid);
        result.put("createtime", createtime);
        return result;
    }

    public Recordfee4customer mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        Integer custid = MapEx.getInt(e, "custid");
        String custname = MapEx.getString(e, "custname");
        Double val = MapEx.getDouble(e, "val");
        String cont = MapEx.getString(e, "cont");
        Integer kindid = MapEx.getInt(e, "kindid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(custid == null) custid = 0;
        if(custname == null) custname = "";
        if(val == null) val = 0.0;
        if(cont == null) cont = "";
        if(kindid == null) kindid = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setType(type);
        setCustid(custid);
        setCustname(custname);
        setVal(val);
        setCont(cont);
        setKindid(kindid);
        setCreatetime(createtime);

        return this;
    }

    public static final Recordfee4customer mapTo(Map e){
        Recordfee4customer result = new Recordfee4customer();

        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        Integer custid = MapEx.getInt(e, "custid");
        String custname = MapEx.getString(e, "custname");
        Double val = MapEx.getDouble(e, "val");
        String cont = MapEx.getString(e, "cont");
        Integer kindid = MapEx.getInt(e, "kindid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(custid == null) custid = 0;
        if(custname == null) custname = "";
        if(val == null) val = 0.0;
        if(cont == null) cont = "";
        if(kindid == null) kindid = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.type = type;
        result.custid = custid;
        result.custname = custname;
        result.val = val;
        result.cont = cont;
        result.kindid = kindid;
        result.createtime = createtime;

        return result;
    }

    public static final Recordfee4customer originalTo(Map e){
        Recordfee4customer result = new Recordfee4customer();

        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        Integer custid = MapEx.getInt(e, "custid");
        String custname = MapEx.getString(e, "custname");
        Double val = MapEx.getDouble(e, "val");
        String cont = MapEx.getString(e, "cont");
        Integer kindid = MapEx.getInt(e, "kindid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(custid == null) custid = 0;
        if(custname == null) custname = "";
        if(val == null) val = 0.0;
        if(cont == null) cont = "";
        if(kindid == null) kindid = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.type = type;
        result.custid = custid;
        result.custname = custname;
        result.val = val;
        result.cont = cont;
        result.kindid = kindid;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Recordfee4customer createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 8);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "custid", custid);
            writeMapEntry(out, "custname", custname);
            writeMapEntry(out, "val", val);
            writeMapEntry(out, "cont", cont);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Recordfee4customer createFor(byte[] b) throws Exception {
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
    public static final Recordfee4customer loadByKey(int id) {
        return Recordfee4customerEntity.getByKey(id);
    }

    public static final Future<Recordfee4customer> asyncByKey(int id) {
        return Recordfee4customerEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Recordfee4customer insert() {
        Recordfee4customer result = Recordfee4customerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Recordfee4customer asyncInsert() {
        Recordfee4customer result = Recordfee4customerEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Recordfee4customer insert2() {
        return Recordfee4customerEntity.insert2(this);
    }

    public final Recordfee4customer asyncInsert2() {
        Recordfee4customer result = Recordfee4customerEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Recordfee4customer update() {
        return Recordfee4customerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Recordfee4customerEntity.asyncUpdate( this );
        return true;
    }

    public final Recordfee4customer insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Recordfee4customerEntity.delete(id);
    }

    public final void asyncDelete() {
        Recordfee4customerEntity.asyncDelete(id);
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

    public Recordfee4customer clone2() {
        try{
            return (Recordfee4customer) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
