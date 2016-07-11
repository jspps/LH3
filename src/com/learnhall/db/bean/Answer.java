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

//learnhall3_design - answer
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Answer extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1453840438; // com.learnhall.db.bean.Answer

    public static String TABLENAME = "answer";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String askid = "askid"; public static final String customerid = "customerid"; public static final String content = "content"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String askid = "askid"; public static final String customerid = "customerid"; public static final String content = "content"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "askid", "customerid", "content", "status", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TEXT", "INT", "DATETIME"};


    public int id;
    public int askid;
    public int customerid;
    public String content;
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

    public Answer setId(int id){
        this.id = id;
        return this;
    }

    public int getAskid(){
        return askid;
    }

    public Answer setAskid(int askid){
        int _old = this.askid;
        this.askid = askid;
        changeIt(Col.askid, _old, askid);
        return this;
    }

    public Answer changeAskid(int askid){
        return setAskid(this.askid + askid);
    }

    public Answer changeAskidWithMin(int askid, int _min){
        int _v2 = this.askid + askid;
        return setAskid(_v2 < _min  ? _min : _v2);
    }

    public Answer changeAskidWithMax(int askid, int _max){
        int _v2 = this.askid + askid;
        return setAskid(_v2 > _max  ? _max : _v2);
    }

    public Answer changeAskidWithMinMax(int askid, int _min, int _max){
        int _v2 = this.askid + askid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAskid(_v2 < _min  ? _min : _v2);
    }

    public int getCustomerid(){
        return customerid;
    }

    public Answer setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Answer changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Answer changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Answer changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Answer changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public String getContent(){
        return content;
    }

    public Answer setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Answer setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Answer changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Answer changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Answer changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Answer changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Answer setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Answer v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Answer v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Answer newAnswer(Integer id, Integer askid, Integer customerid, String content, Integer status, java.util.Date createtime) {
        Answer result = new Answer();
        result.id = id;
        result.askid = askid;
        result.customerid = customerid;
        result.content = content;
        result.status = status;
        result.createtime = createtime;
        return result;
    }

    public static Answer newAnswer(Answer answer) {
        Answer result = new Answer();
        result.id = answer.id;
        result.askid = answer.askid;
        result.customerid = answer.customerid;
        result.content = answer.content;
        result.status = answer.status;
        result.createtime = answer.createtime;
        return result;
    }

    public Answer createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAnswer(){
        Answer answer = null; // answer
        { // new and insert Answer (answer)
            int id = 0; 	// id
            int askid = 0; 	// askid
            int customerid = 0; 	// customerid
            String content = ""; 	// content
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            answer = Answer.newAnswer(id, askid, customerid, content, status, createtime);
        }
        answer = answer.insert();

        int id = answer.getId(); 	// id
        int askid = answer.getAskid(); 	// askid
        int customerid = answer.getCustomerid(); 	// customerid
        String content = answer.getContent(); 	// content
        int status = answer.getStatus(); 	// status
        Date createtime = answer.getCreatetime(); 	// createtime
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
        case CEn.customerid:
            return customerid;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Answer setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Answer setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.askid:
            return setAskid(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Answer changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Answer changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.askid:
            return changeAskid(value2);
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Answer changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Answer changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.askid:
            return changeAskidWithMin(value2, _min);
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
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

    public Answer setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Answer setDouble(String fieldEn, double value2) {
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
        case CEn.askid:
            return askid;
        case CEn.customerid:
            return customerid;
        case CEn.content:
            return content;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Answer setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Answer setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Answer setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Answer setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.content:
            return setContent(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Answer setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Answer setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Answer");
        result.put("id", id);
        result.put("askid", askid);
        result.put("customerid", customerid);
        result.put("content", content);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("askid", askid);
        result.put("customerid", customerid);
        result.put("content", content);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Answer");
        result.put("id", id);
        result.put("askid", askid);
        result.put("customerid", customerid);
        result.put("content", content);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Answer mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer askid = MapEx.getInt(e, "askid");
        Integer customerid = MapEx.getInt(e, "customerid");
        String content = MapEx.getString(e, "content");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(askid == null) askid = 0;
        if(customerid == null) customerid = 0;
        if(content == null) content = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setAskid(askid);
        setCustomerid(customerid);
        setContent(content);
        setStatus(status);
        setCreatetime(createtime);

        return this;
    }

    public static final Answer mapTo(Map e){
        Answer result = new Answer();

        Integer id = MapEx.getInt(e, "id");
        Integer askid = MapEx.getInt(e, "askid");
        Integer customerid = MapEx.getInt(e, "customerid");
        String content = MapEx.getString(e, "content");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(askid == null) askid = 0;
        if(customerid == null) customerid = 0;
        if(content == null) content = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.askid = askid;
        result.customerid = customerid;
        result.content = content;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public static final Answer originalTo(Map e){
        Answer result = new Answer();

        Integer id = MapEx.getInt(e, "id");
        Integer askid = MapEx.getInt(e, "askid");
        Integer customerid = MapEx.getInt(e, "customerid");
        String content = MapEx.getString(e, "content");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(askid == null) askid = 0;
        if(customerid == null) customerid = 0;
        if(content == null) content = "";
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.askid = askid;
        result.customerid = customerid;
        result.content = content;
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

    public static final Answer createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 6);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "askid", askid);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Answer createFor(byte[] b) throws Exception {
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
    public static final Answer loadByKey(int id) {
        return AnswerEntity.getByKey(id);
    }

    public static final Future<Answer> asyncByKey(int id) {
        return AnswerEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Answer insert() {
        Answer result = AnswerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Answer asyncInsert() {
        Answer result = AnswerEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Answer insert2() {
        return AnswerEntity.insert2(this);
    }

    public final Answer asyncInsert2() {
        Answer result = AnswerEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Answer update() {
        return AnswerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        AnswerEntity.asyncUpdate( this );
        return true;
    }

    public final Answer insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AnswerEntity.delete(id);
    }

    public final void asyncDelete() {
        AnswerEntity.asyncDelete(id);
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

    public Answer clone2() {
        try{
            return (Answer) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
