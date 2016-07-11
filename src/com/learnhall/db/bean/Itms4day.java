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

//learnhall3_design - itms4day
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Itms4day extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 634406789; // com.learnhall.db.bean.Itms4day

    public static String TABLENAME = "itms4day";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String custid = "custid"; public static final String kindid = "kindid"; public static final String rightrate = "rightrate"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String custid = "custid"; public static final String kindid = "kindid"; public static final String rightrate = "rightrate"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "custid", "kindid", "rightrate", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "DATE"};


    public int id;
    public int custid;
    public int kindid;
    public int rightrate;
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

    public Itms4day setId(int id){
        this.id = id;
        return this;
    }

    public int getCustid(){
        return custid;
    }

    public Itms4day setCustid(int custid){
        int _old = this.custid;
        this.custid = custid;
        changeIt(Col.custid, _old, custid);
        return this;
    }

    public Itms4day changeCustid(int custid){
        return setCustid(this.custid + custid);
    }

    public Itms4day changeCustidWithMin(int custid, int _min){
        int _v2 = this.custid + custid;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public Itms4day changeCustidWithMax(int custid, int _max){
        int _v2 = this.custid + custid;
        return setCustid(_v2 > _max  ? _max : _v2);
    }

    public Itms4day changeCustidWithMinMax(int custid, int _min, int _max){
        int _v2 = this.custid + custid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public int getKindid(){
        return kindid;
    }

    public Itms4day setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Itms4day changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Itms4day changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Itms4day changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Itms4day changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getRightrate(){
        return rightrate;
    }

    public Itms4day setRightrate(int rightrate){
        int _old = this.rightrate;
        this.rightrate = rightrate;
        changeIt(Col.rightrate, _old, rightrate);
        return this;
    }

    public Itms4day changeRightrate(int rightrate){
        return setRightrate(this.rightrate + rightrate);
    }

    public Itms4day changeRightrateWithMin(int rightrate, int _min){
        int _v2 = this.rightrate + rightrate;
        return setRightrate(_v2 < _min  ? _min : _v2);
    }

    public Itms4day changeRightrateWithMax(int rightrate, int _max){
        int _v2 = this.rightrate + rightrate;
        return setRightrate(_v2 > _max  ? _max : _v2);
    }

    public Itms4day changeRightrateWithMinMax(int rightrate, int _min, int _max){
        int _v2 = this.rightrate + rightrate;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRightrate(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Itms4day setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Itms4day v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Itms4day v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Itms4day newItms4day(Integer id, Integer custid, Integer kindid, Integer rightrate, java.util.Date createtime) {
        Itms4day result = new Itms4day();
        result.id = id;
        result.custid = custid;
        result.kindid = kindid;
        result.rightrate = rightrate;
        result.createtime = createtime;
        return result;
    }

    public static Itms4day newItms4day(Itms4day itms4day) {
        Itms4day result = new Itms4day();
        result.id = itms4day.id;
        result.custid = itms4day.custid;
        result.kindid = itms4day.kindid;
        result.rightrate = itms4day.rightrate;
        result.createtime = itms4day.createtime;
        return result;
    }

    public Itms4day createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getItms4day(){
        Itms4day itms4day = null; // itms4day
        { // new and insert Itms4day (itms4day)
            int id = 0; 	// id
            int custid = 0; 	// custid
            int kindid = 0; 	// kindid
            int rightrate = 0; 	// rightrate
            Date createtime = new Date(); 	// createtime
            itms4day = Itms4day.newItms4day(id, custid, kindid, rightrate, createtime);
        }
        itms4day = itms4day.insert();

        int id = itms4day.getId(); 	// id
        int custid = itms4day.getCustid(); 	// custid
        int kindid = itms4day.getKindid(); 	// kindid
        int rightrate = itms4day.getRightrate(); 	// rightrate
        Date createtime = itms4day.getCreatetime(); 	// createtime
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
        case CEn.custid:
            return custid;
        case CEn.kindid:
            return kindid;
        case CEn.rightrate:
            return rightrate;
        }
        return 0;
    }

    public Itms4day setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Itms4day setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.custid:
            return setCustid(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.rightrate:
            return setRightrate(value2);
        }
        return this;
    }

    public Itms4day changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Itms4day changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.custid:
            return changeCustid(value2);
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.rightrate:
            return changeRightrate(value2);
        }
        return this;
    }

    public Itms4day changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Itms4day changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.custid:
            return changeCustidWithMin(value2, _min);
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.rightrate:
            return changeRightrateWithMin(value2, _min);
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

    public Itms4day setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Itms4day setDouble(String fieldEn, double value2) {
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
        case CEn.custid:
            return custid;
        case CEn.kindid:
            return kindid;
        case CEn.rightrate:
            return rightrate;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Itms4day setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Itms4day setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Itms4day setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Itms4day setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Itms4day setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Itms4day setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Itms4day");
        result.put("id", id);
        result.put("custid", custid);
        result.put("kindid", kindid);
        result.put("rightrate", rightrate);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("custid", custid);
        result.put("kindid", kindid);
        result.put("rightrate", rightrate);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Itms4day");
        result.put("id", id);
        result.put("custid", custid);
        result.put("kindid", kindid);
        result.put("rightrate", rightrate);
        result.put("createtime", createtime);
        return result;
    }

    public Itms4day mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer custid = MapEx.getInt(e, "custid");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer rightrate = MapEx.getInt(e, "rightrate");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(custid == null) custid = 0;
        if(kindid == null) kindid = 0;
        if(rightrate == null) rightrate = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setCustid(custid);
        setKindid(kindid);
        setRightrate(rightrate);
        setCreatetime(createtime);

        return this;
    }

    public static final Itms4day mapTo(Map e){
        Itms4day result = new Itms4day();

        Integer id = MapEx.getInt(e, "id");
        Integer custid = MapEx.getInt(e, "custid");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer rightrate = MapEx.getInt(e, "rightrate");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(custid == null) custid = 0;
        if(kindid == null) kindid = 0;
        if(rightrate == null) rightrate = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.custid = custid;
        result.kindid = kindid;
        result.rightrate = rightrate;
        result.createtime = createtime;

        return result;
    }

    public static final Itms4day originalTo(Map e){
        Itms4day result = new Itms4day();

        Integer id = MapEx.getInt(e, "id");
        Integer custid = MapEx.getInt(e, "custid");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer rightrate = MapEx.getInt(e, "rightrate");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(custid == null) custid = 0;
        if(kindid == null) kindid = 0;
        if(rightrate == null) rightrate = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.custid = custid;
        result.kindid = kindid;
        result.rightrate = rightrate;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Itms4day createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 5);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "custid", custid);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "rightrate", rightrate);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Itms4day createFor(byte[] b) throws Exception {
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
    public static final Itms4day loadByKey(int id) {
        return Itms4dayEntity.getByKey(id);
    }

    public static final Future<Itms4day> asyncByKey(int id) {
        return Itms4dayEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Itms4day insert() {
        Itms4day result = Itms4dayEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Itms4day asyncInsert() {
        Itms4day result = Itms4dayEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Itms4day insert2() {
        return Itms4dayEntity.insert2(this);
    }

    public final Itms4day asyncInsert2() {
        Itms4day result = Itms4dayEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Itms4day update() {
        return Itms4dayEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Itms4dayEntity.asyncUpdate( this );
        return true;
    }

    public final Itms4day insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Itms4dayEntity.delete(id);
    }

    public final void asyncDelete() {
        Itms4dayEntity.asyncDelete(id);
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

    public Itms4day clone2() {
        try{
            return (Itms4day) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
