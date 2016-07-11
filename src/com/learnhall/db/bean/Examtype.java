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

//learnhall3_design - examtype
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Examtype extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1160983781; // com.learnhall.db.bean.Examtype

    public static String TABLENAME = "examtype";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String name = "name";  }
    public static final class CEn { public static final String id = "id"; public static final String name = "name";  }
    public static final String[] carrays ={"id", "name"};
    public static final String[] dbTypes ={"INT", "VARCHAR"};


    public int id;
    public String name;

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

    public Examtype setId(int id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Examtype setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public int compareTo(Examtype v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Examtype v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Examtype newExamtype(Integer id, String name) {
        Examtype result = new Examtype();
        result.id = id;
        result.name = name;
        return result;
    }

    public static Examtype newExamtype(Examtype examtype) {
        Examtype result = new Examtype();
        result.id = examtype.id;
        result.name = examtype.name;
        return result;
    }

    public Examtype createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getExamtype(){
        Examtype examtype = null; // examtype
        { // new and insert Examtype (examtype)
            int id = 0; 	// id
            String name = ""; 	// name
            examtype = Examtype.newExamtype(id, name);
        }
        examtype = examtype.insert();

        int id = examtype.getId(); 	// id
        String name = examtype.getName(); 	// name
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

    public Examtype setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Examtype setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        }
        return this;
    }

    public Examtype changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Examtype changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Examtype changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Examtype changeIntWithMin(String fieldEn, int value2, int _min) {
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

    public Examtype setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Examtype setDouble(String fieldEn, double value2) {
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
        }
        return null;
    }

    public Examtype setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Examtype setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Examtype setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Examtype setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Examtype setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Examtype setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Examtype");
        result.put("id", id);
        result.put("name", name);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("name", name);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Examtype");
        result.put("id", id);
        result.put("name", name);
        return result;
    }

    public Examtype mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");

        if(id == null) id = 0;
        if(name == null) name = "";

        setId(id);
        setName(name);

        return this;
    }

    public static final Examtype mapTo(Map e){
        Examtype result = new Examtype();

        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");

        if(id == null) id = 0;
        if(name == null) name = "";

        result.id = id;
        result.name = name;

        return result;
    }

    public static final Examtype originalTo(Map e){
        Examtype result = new Examtype();

        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");

        if(id == null) id = 0;
        if(name == null) name = "";

        result.id = id;
        result.name = name;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Examtype createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 2);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "name", name);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Examtype createFor(byte[] b) throws Exception {
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
    public final List<Exam> getExamsFkExamtypeid() { // exam - examtypeid
        return ExamEntity.getByExamtypeid(id);
    }

    public final int countExamsFkExamtypeid() { // exam - examtypeid
        return ExamEntity.countByExamtypeid(id);
    }

    public final List<Product0examtype> getProduct0examtypesFkExamtypeid() { // product0examtype - examtypeid
        return Product0examtypeEntity.getByExamtypeid(id);
    }

    public final int countProduct0examtypesFkExamtypeid() { // product0examtype - examtypeid
        return Product0examtypeEntity.countByExamtypeid(id);
    }

    public static final Examtype loadByKey(int id) {
        return ExamtypeEntity.getByKey(id);
    }

    public static final Future<Examtype> asyncByKey(int id) {
        return ExamtypeEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Examtype insert() {
        Examtype result = ExamtypeEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Examtype asyncInsert() {
        Examtype result = ExamtypeEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Examtype insert2() {
        return ExamtypeEntity.insert2(this);
    }

    public final Examtype asyncInsert2() {
        Examtype result = ExamtypeEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Examtype update() {
        return ExamtypeEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ExamtypeEntity.asyncUpdate( this );
        return true;
    }

    public final Examtype insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ExamtypeEntity.delete(id);
    }

    public final void asyncDelete() {
        ExamtypeEntity.asyncDelete(id);
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

    public Examtype clone2() {
        try{
            return (Examtype) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
