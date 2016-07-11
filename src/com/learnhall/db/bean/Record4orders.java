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

//learnhall3_design - record4orders
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Record4orders extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -438967012; // com.learnhall.db.bean.Record4orders

    public static String TABLENAME = "record4orders";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String orderNo = "orderNo"; public static final String tradeNo = "tradeNo"; public static final String content = "content"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String orderNo = "orderNo"; public static final String tradeNo = "tradeNo"; public static final String content = "content"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "orderNo", "tradeNo", "content", "createtime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "TEXT", "DATETIME"};


    public int id;
    public String orderNo;
    public String tradeNo;
    public String content;
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

    public Record4orders setId(int id){
        this.id = id;
        return this;
    }

    public String getOrderNo(){
        return orderNo;
    }

    public Record4orders setOrderNo(String orderNo){
        String _old = this.orderNo;
        this.orderNo = orderNo;
        changeIt(Col.orderNo, _old, orderNo);
        return this;
    }

    public String getTradeNo(){
        return tradeNo;
    }

    public Record4orders setTradeNo(String tradeNo){
        String _old = this.tradeNo;
        this.tradeNo = tradeNo;
        changeIt(Col.tradeNo, _old, tradeNo);
        return this;
    }

    public String getContent(){
        return content;
    }

    public Record4orders setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Record4orders setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Record4orders v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Record4orders v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Record4orders newRecord4orders(Integer id, String orderNo, String tradeNo, String content, java.util.Date createtime) {
        Record4orders result = new Record4orders();
        result.id = id;
        result.orderNo = orderNo;
        result.tradeNo = tradeNo;
        result.content = content;
        result.createtime = createtime;
        return result;
    }

    public static Record4orders newRecord4orders(Record4orders record4orders) {
        Record4orders result = new Record4orders();
        result.id = record4orders.id;
        result.orderNo = record4orders.orderNo;
        result.tradeNo = record4orders.tradeNo;
        result.content = record4orders.content;
        result.createtime = record4orders.createtime;
        return result;
    }

    public Record4orders createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecord4orders(){
        Record4orders record4orders = null; // record4orders
        { // new and insert Record4orders (record4orders)
            int id = 0; 	// id
            String orderNo = ""; 	// orderNo
            String tradeNo = ""; 	// tradeNo
            String content = ""; 	// content
            Date createtime = new Date(); 	// createtime
            record4orders = Record4orders.newRecord4orders(id, orderNo, tradeNo, content, createtime);
        }
        record4orders = record4orders.insert();

        int id = record4orders.getId(); 	// id
        String orderNo = record4orders.getOrderNo(); 	// orderNo
        String tradeNo = record4orders.getTradeNo(); 	// tradeNo
        String content = record4orders.getContent(); 	// content
        Date createtime = record4orders.getCreatetime(); 	// createtime
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
        }
        return 0;
    }

    public Record4orders setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Record4orders setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        }
        return this;
    }

    public Record4orders changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Record4orders changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Record4orders changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Record4orders changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
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

    public Record4orders setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Record4orders setDouble(String fieldEn, double value2) {
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
        case CEn.orderNo: 
            return orderNo;
        case CEn.tradeNo: 
            return tradeNo;
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
        case CEn.orderNo:
            return orderNo;
        case CEn.tradeNo:
            return tradeNo;
        case CEn.content:
            return content;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Record4orders setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Record4orders setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Record4orders setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Record4orders setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.orderNo:
            return setOrderNo(value2);
        case CEn.tradeNo:
            return setTradeNo(value2);
        case CEn.content:
            return setContent(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Record4orders setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Record4orders setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Record4orders");
        result.put("id", id);
        result.put("orderNo", orderNo);
        result.put("tradeNo", tradeNo);
        result.put("content", content);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("orderNo", orderNo);
        result.put("tradeNo", tradeNo);
        result.put("content", content);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Record4orders");
        result.put("id", id);
        result.put("orderNo", orderNo);
        result.put("tradeNo", tradeNo);
        result.put("content", content);
        result.put("createtime", createtime);
        return result;
    }

    public Record4orders mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String orderNo = MapEx.getString(e, "orderNo");
        String tradeNo = MapEx.getString(e, "tradeNo");
        String content = MapEx.getString(e, "content");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(orderNo == null) orderNo = "";
        if(tradeNo == null) tradeNo = "";
        if(content == null) content = "";
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setOrderNo(orderNo);
        setTradeNo(tradeNo);
        setContent(content);
        setCreatetime(createtime);

        return this;
    }

    public static final Record4orders mapTo(Map e){
        Record4orders result = new Record4orders();

        Integer id = MapEx.getInt(e, "id");
        String orderNo = MapEx.getString(e, "orderNo");
        String tradeNo = MapEx.getString(e, "tradeNo");
        String content = MapEx.getString(e, "content");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(orderNo == null) orderNo = "";
        if(tradeNo == null) tradeNo = "";
        if(content == null) content = "";
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.orderNo = orderNo;
        result.tradeNo = tradeNo;
        result.content = content;
        result.createtime = createtime;

        return result;
    }

    public static final Record4orders originalTo(Map e){
        Record4orders result = new Record4orders();

        Integer id = MapEx.getInt(e, "id");
        String orderNo = MapEx.getString(e, "orderNo");
        String tradeNo = MapEx.getString(e, "tradeNo");
        String content = MapEx.getString(e, "content");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(orderNo == null) orderNo = "";
        if(tradeNo == null) tradeNo = "";
        if(content == null) content = "";
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.orderNo = orderNo;
        result.tradeNo = tradeNo;
        result.content = content;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Record4orders createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 5);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "orderNo", orderNo);
            writeMapEntry(out, "tradeNo", tradeNo);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Record4orders createFor(byte[] b) throws Exception {
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
    public static final Record4orders loadByKey(int id) {
        return Record4ordersEntity.getByKey(id);
    }

    public static final Future<Record4orders> asyncByKey(int id) {
        return Record4ordersEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Record4orders insert() {
        Record4orders result = Record4ordersEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Record4orders asyncInsert() {
        Record4orders result = Record4ordersEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Record4orders insert2() {
        return Record4ordersEntity.insert2(this);
    }

    public final Record4orders asyncInsert2() {
        Record4orders result = Record4ordersEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Record4orders update() {
        return Record4ordersEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Record4ordersEntity.asyncUpdate( this );
        return true;
    }

    public final Record4orders insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Record4ordersEntity.delete(id);
    }

    public final void asyncDelete() {
        Record4ordersEntity.asyncDelete(id);
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

    public Record4orders clone2() {
        try{
            return (Record4orders) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
