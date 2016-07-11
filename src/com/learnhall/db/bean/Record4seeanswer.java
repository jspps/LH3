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

//learnhall3_design - record4seeanswer
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Record4seeanswer extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1856991290; // com.learnhall.db.bean.Record4seeanswer

    public static String TABLENAME = "record4seeanswer";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String askid = "askid"; public static final String custid = "custid"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String askid = "askid"; public static final String custid = "custid"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "askid", "custid", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "DATETIME"};


    public int id;
    public int askid;
    public int custid;
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

    public Record4seeanswer setId(int id){
        this.id = id;
        return this;
    }

    public int getAskid(){
        return askid;
    }

    public Record4seeanswer setAskid(int askid){
        int _old = this.askid;
        this.askid = askid;
        changeIt(Col.askid, _old, askid);
        return this;
    }

    public Record4seeanswer changeAskid(int askid){
        return setAskid(this.askid + askid);
    }

    public Record4seeanswer changeAskidWithMin(int askid, int _min){
        int _v2 = this.askid + askid;
        return setAskid(_v2 < _min  ? _min : _v2);
    }

    public Record4seeanswer changeAskidWithMax(int askid, int _max){
        int _v2 = this.askid + askid;
        return setAskid(_v2 > _max  ? _max : _v2);
    }

    public Record4seeanswer changeAskidWithMinMax(int askid, int _min, int _max){
        int _v2 = this.askid + askid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAskid(_v2 < _min  ? _min : _v2);
    }

    public int getCustid(){
        return custid;
    }

    public Record4seeanswer setCustid(int custid){
        int _old = this.custid;
        this.custid = custid;
        changeIt(Col.custid, _old, custid);
        return this;
    }

    public Record4seeanswer changeCustid(int custid){
        return setCustid(this.custid + custid);
    }

    public Record4seeanswer changeCustidWithMin(int custid, int _min){
        int _v2 = this.custid + custid;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public Record4seeanswer changeCustidWithMax(int custid, int _max){
        int _v2 = this.custid + custid;
        return setCustid(_v2 > _max  ? _max : _v2);
    }

    public Record4seeanswer changeCustidWithMinMax(int custid, int _min, int _max){
        int _v2 = this.custid + custid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Record4seeanswer setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Record4seeanswer v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Record4seeanswer v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Record4seeanswer newRecord4seeanswer(Integer id, Integer askid, Integer custid, java.util.Date createtime) {
        Record4seeanswer result = new Record4seeanswer();
        result.id = id;
        result.askid = askid;
        result.custid = custid;
        result.createtime = createtime;
        return result;
    }

    public static Record4seeanswer newRecord4seeanswer(Record4seeanswer record4seeanswer) {
        Record4seeanswer result = new Record4seeanswer();
        result.id = record4seeanswer.id;
        result.askid = record4seeanswer.askid;
        result.custid = record4seeanswer.custid;
        result.createtime = record4seeanswer.createtime;
        return result;
    }

    public Record4seeanswer createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecord4seeanswer(){
        Record4seeanswer record4seeanswer = null; // record4seeanswer
        { // new and insert Record4seeanswer (record4seeanswer)
            int id = 0; 	// id
            int askid = 0; 	// askid
            int custid = 0; 	// custid
            Date createtime = new Date(); 	// createtime
            record4seeanswer = Record4seeanswer.newRecord4seeanswer(id, askid, custid, createtime);
        }
        record4seeanswer = record4seeanswer.insert();

        int id = record4seeanswer.getId(); 	// id
        int askid = record4seeanswer.getAskid(); 	// askid
        int custid = record4seeanswer.getCustid(); 	// custid
        Date createtime = record4seeanswer.getCreatetime(); 	// createtime
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
        case CEn.askid:
            return askid;
        case CEn.custid:
            return custid;
        }
        return 0;
    }

    public Record4seeanswer setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Record4seeanswer setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.askid:
            return setAskid(value2);
        case CEn.custid:
            return setCustid(value2);
        }
        return this;
    }

    public Record4seeanswer changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Record4seeanswer changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.askid:
            return changeAskid(value2);
        case CEn.custid:
            return changeCustid(value2);
        }
        return this;
    }

    public Record4seeanswer changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Record4seeanswer changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.askid:
            return changeAskidWithMin(value2, _min);
        case CEn.custid:
            return changeCustidWithMin(value2, _min);
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

    public Record4seeanswer setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Record4seeanswer setDouble(String fieldEn, double value2) {
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
        case CEn.askid:
            return askid;
        case CEn.custid:
            return custid;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Record4seeanswer setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Record4seeanswer setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Record4seeanswer setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Record4seeanswer setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Record4seeanswer setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Record4seeanswer setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Record4seeanswer");
        result.put("id", id);
        result.put("askid", askid);
        result.put("custid", custid);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("askid", askid);
        result.put("custid", custid);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Record4seeanswer");
        result.put("id", id);
        result.put("askid", askid);
        result.put("custid", custid);
        result.put("createtime", createtime);
        return result;
    }

    public Record4seeanswer mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer askid = MapEx.getInt(e, "askid");
        Integer custid = MapEx.getInt(e, "custid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(askid == null) askid = 0;
        if(custid == null) custid = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setAskid(askid);
        setCustid(custid);
        setCreatetime(createtime);

        return this;
    }

    public static final Record4seeanswer mapTo(Map e){
        Record4seeanswer result = new Record4seeanswer();

        Integer id = MapEx.getInt(e, "id");
        Integer askid = MapEx.getInt(e, "askid");
        Integer custid = MapEx.getInt(e, "custid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(askid == null) askid = 0;
        if(custid == null) custid = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.askid = askid;
        result.custid = custid;
        result.createtime = createtime;

        return result;
    }

    public static final Record4seeanswer originalTo(Map e){
        Record4seeanswer result = new Record4seeanswer();

        Integer id = MapEx.getInt(e, "id");
        Integer askid = MapEx.getInt(e, "askid");
        Integer custid = MapEx.getInt(e, "custid");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(askid == null) askid = 0;
        if(custid == null) custid = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.askid = askid;
        result.custid = custid;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Record4seeanswer createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 4);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "askid", askid);
            writeMapEntry(out, "custid", custid);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Record4seeanswer createFor(byte[] b) throws Exception {
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
    public static final Record4seeanswer loadByKey(int id) {
        return Record4seeanswerEntity.getByKey(id);
    }

    public static final Future<Record4seeanswer> asyncByKey(int id) {
        return Record4seeanswerEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Record4seeanswer insert() {
        Record4seeanswer result = Record4seeanswerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Record4seeanswer asyncInsert() {
        Record4seeanswer result = Record4seeanswerEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Record4seeanswer insert2() {
        return Record4seeanswerEntity.insert2(this);
    }

    public final Record4seeanswer asyncInsert2() {
        Record4seeanswer result = Record4seeanswerEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Record4seeanswer update() {
        return Record4seeanswerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Record4seeanswerEntity.asyncUpdate( this );
        return true;
    }

    public final Record4seeanswer insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Record4seeanswerEntity.delete(id);
    }

    public final void asyncDelete() {
        Record4seeanswerEntity.asyncDelete(id);
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

    public Record4seeanswer clone2() {
        try{
            return (Record4seeanswer) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
