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

//learnhall3_design - msg
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Msg extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1811965195; // com.learnhall.db.bean.Msg

    public static String TABLENAME = "msg";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String target = "target"; public static final String description = "description"; public static final String num = "num"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String target = "target"; public static final String description = "description"; public static final String num = "num"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "target", "description", "num", "status", "createtime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "TEXT", "INT", "INT", "DATETIME"};


    public int id;
    public String target;
    public String description;
    public int num;
    public int status;
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

    public Msg setId(int id){
        this.id = id;
        return this;
    }

    public String getTarget(){
        return target;
    }

    public Msg setTarget(String target){
        String _old = this.target;
        this.target = target;
        changeIt(Col.target, _old, target);
        return this;
    }

    public String getDescription(){
        return description;
    }

    public Msg setDescription(String description){
        String _old = this.description;
        this.description = description;
        changeIt(Col.description, _old, description);
        return this;
    }

    public int getNum(){
        return num;
    }

    public Msg setNum(int num){
        int _old = this.num;
        this.num = num;
        changeIt(Col.num, _old, num);
        return this;
    }

    public Msg changeNum(int num){
        return setNum(this.num + num);
    }

    public Msg changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Msg changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Msg changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Msg setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Msg changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Msg changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Msg changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Msg changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Msg setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Msg v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Msg v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Msg newMsg(Integer id, String target, String description, Integer num, Integer status, java.util.Date createtime) {
        Msg result = new Msg();
        result.id = id;
        result.target = target;
        result.description = description;
        result.num = num;
        result.status = status;
        result.createtime = createtime;
        return result;
    }

    public static Msg newMsg(Msg msg) {
        Msg result = new Msg();
        result.id = msg.id;
        result.target = msg.target;
        result.description = msg.description;
        result.num = msg.num;
        result.status = msg.status;
        result.createtime = msg.createtime;
        return result;
    }

    public Msg createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getMsg(){
        Msg msg = null; // msg
        { // new and insert Msg (msg)
            int id = 0; 	// id
            String target = ""; 	// target
            String description = ""; 	// description
            int num = 0; 	// num
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            msg = Msg.newMsg(id, target, description, num, status, createtime);
        }
        msg = msg.insert();

        int id = msg.getId(); 	// id
        String target = msg.getTarget(); 	// target
        String description = msg.getDescription(); 	// description
        int num = msg.getNum(); 	// num
        int status = msg.getStatus(); 	// status
        Date createtime = msg.getCreatetime(); 	// createtime
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
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Msg setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Msg setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.num:
            return setNum(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Msg changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Msg changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.num:
            return changeNum(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Msg changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Msg changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
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
        }
        return 0;
    }

    public Msg setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Msg setDouble(String fieldEn, double value2) {
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
        case CEn.target: 
            return target;
        case CEn.description: 
            return description;
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
        case CEn.target:
            return target;
        case CEn.description:
            return description;
        case CEn.num:
            return num;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Msg setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Msg setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Msg setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Msg setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.target:
            return setTarget(value2);
        case CEn.description:
            return setDescription(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Msg setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Msg setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Msg");
        result.put("id", id);
        result.put("target", target);
        result.put("description", description);
        result.put("num", num);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("target", target);
        result.put("description", description);
        result.put("num", num);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Msg");
        result.put("id", id);
        result.put("target", target);
        result.put("description", description);
        result.put("num", num);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Msg mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String target = MapEx.getString(e, "target");
        String description = MapEx.getString(e, "description");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(target == null) target = "";
        if(description == null) description = "";
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setTarget(target);
        setDescription(description);
        setNum(num);
        setStatus(status);
        setCreatetime(createtime);

        return this;
    }

    public static final Msg mapTo(Map e){
        Msg result = new Msg();

        Integer id = MapEx.getInt(e, "id");
        String target = MapEx.getString(e, "target");
        String description = MapEx.getString(e, "description");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(target == null) target = "";
        if(description == null) description = "";
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.target = target;
        result.description = description;
        result.num = num;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public static final Msg originalTo(Map e){
        Msg result = new Msg();

        Integer id = MapEx.getInt(e, "id");
        String target = MapEx.getString(e, "target");
        String description = MapEx.getString(e, "description");
        Integer num = MapEx.getInt(e, "num");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(target == null) target = "";
        if(description == null) description = "";
        if(num == null) num = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.target = target;
        result.description = description;
        result.num = num;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Msg createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 6);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "target", target);
            writeMapEntry(out, "description", description);
            writeMapEntry(out, "num", num);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Msg createFor(byte[] b) throws Exception {
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
    public static final Msg loadByKey(int id) {
        return MsgEntity.getByKey(id);
    }

    public static final Future<Msg> asyncByKey(int id) {
        return MsgEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Msg insert() {
        Msg result = MsgEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Msg asyncInsert() {
        Msg result = MsgEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Msg insert2() {
        return MsgEntity.insert2(this);
    }

    public final Msg asyncInsert2() {
        Msg result = MsgEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Msg update() {
        return MsgEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        MsgEntity.asyncUpdate( this );
        return true;
    }

    public final Msg insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return MsgEntity.delete(id);
    }

    public final void asyncDelete() {
        MsgEntity.asyncDelete(id);
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

    public Msg clone2() {
        try{
            return (Msg) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
