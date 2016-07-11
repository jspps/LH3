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

//learnhall3_design - adqdepartment
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Adqdepartment extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1435921652; // com.learnhall.db.bean.Adqdepartment

    public static String TABLENAME = "adqdepartment";

    public static final String primary = "did";

    public static final class Col { public static final String did = "did"; public static final String name = "name";  }
    public static final class CEn { public static final String did = "did"; public static final String name = "name";  }
    public static final String[] carrays ={"did", "name"};
    public static final String[] dbTypes ={"INT", "VARCHAR"};


    public int did;
    public String name;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return did;
    }

    public static String _key(int did) {
        return PStr.b(TABLENAME).a("-").e(did);
    }

    public int getDid(){
        return did;
    }

    public Adqdepartment setDid(int did){
        this.did = did;
        return this;
    }

    public String getName(){
        return name;
    }

    public Adqdepartment setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public int compareTo(Adqdepartment v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Adqdepartment v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Adqdepartment newAdqdepartment(Integer did, String name) {
        Adqdepartment result = new Adqdepartment();
        result.did = did;
        result.name = name;
        return result;
    }

    public static Adqdepartment newAdqdepartment(Adqdepartment adqdepartment) {
        Adqdepartment result = new Adqdepartment();
        result.did = adqdepartment.did;
        result.name = adqdepartment.name;
        return result;
    }

    public Adqdepartment createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAdqdepartment(){
        Adqdepartment adqdepartment = null; // adqdepartment
        { // new and insert Adqdepartment (adqdepartment)
            int did = 0; 	// did
            String name = ""; 	// name
            adqdepartment = Adqdepartment.newAdqdepartment(did, name);
        }
        adqdepartment = adqdepartment.insert();

        int did = adqdepartment.getDid(); 	// did
        String name = adqdepartment.getName(); 	// name
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.did:
            return did;
        }
        return 0;
    }

    public Adqdepartment setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Adqdepartment setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.did:
            return setDid(value2);
        }
        return this;
    }

    public Adqdepartment changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Adqdepartment changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Adqdepartment changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Adqdepartment changeIntWithMin(String fieldEn, int value2, int _min) {
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

    public Adqdepartment setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Adqdepartment setDouble(String fieldEn, double value2) {
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
        case CEn.did:
            return did;
        case CEn.name:
            return name;
        }
        return null;
    }

    public Adqdepartment setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Adqdepartment setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Adqdepartment setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Adqdepartment setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Adqdepartment setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Adqdepartment setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Adqdepartment");
        result.put("did", did);
        result.put("name", name);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("did", did);
        result.put("name", name);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Adqdepartment");
        result.put("did", did);
        result.put("name", name);
        return result;
    }

    public Adqdepartment mapToObject(Map e){
        Integer did = MapEx.getInt(e, "did");
        String name = MapEx.getString(e, "name");

        if(did == null) did = 0;
        if(name == null) name = "";

        setDid(did);
        setName(name);

        return this;
    }

    public static final Adqdepartment mapTo(Map e){
        Adqdepartment result = new Adqdepartment();

        Integer did = MapEx.getInt(e, "did");
        String name = MapEx.getString(e, "name");

        if(did == null) did = 0;
        if(name == null) name = "";

        result.did = did;
        result.name = name;

        return result;
    }

    public static final Adqdepartment originalTo(Map e){
        Adqdepartment result = new Adqdepartment();

        Integer did = MapEx.getInt(e, "did");
        String name = MapEx.getString(e, "name");

        if(did == null) did = 0;
        if(name == null) name = "";

        result.did = did;
        result.name = name;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Adqdepartment createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 2);
            writeMapEntry(out, "did", did);
            writeMapEntry(out, "name", name);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Adqdepartment createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(did);
        return s.hashCode();
    }
    public final List<Adcourses> getAdcoursessFkDepartid() { // adcourses - departid
        return AdcoursesEntity.getByDepartid(did);
    }

    public final int countAdcoursessFkDepartid() { // adcourses - departid
        return AdcoursesEntity.countByDepartid(did);
    }

    public static final Adqdepartment loadByKey(int did) {
        return AdqdepartmentEntity.getByKey(did);
    }

    public static final Future<Adqdepartment> asyncByKey(int did) {
        return AdqdepartmentEntity.asyncGetByKey(did);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Adqdepartment insert() {
        Adqdepartment result = AdqdepartmentEntity.insert(this);
        if(result == null) return null;
        // did = result.did;
        return result;
    }

    public final Adqdepartment asyncInsert() {
        Adqdepartment result = AdqdepartmentEntity.asyncInsert(this);
        // did = result.did;
        return result;
    }

    public final Adqdepartment insert2() {
        return AdqdepartmentEntity.insert2(this);
    }

    public final Adqdepartment asyncInsert2() {
        Adqdepartment result = AdqdepartmentEntity.asyncInsert2(this);
        // did = result.did;
        return result;
    }

    public final Adqdepartment update() {
        return AdqdepartmentEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(did <= 0) return false;
        AdqdepartmentEntity.asyncUpdate( this );
        return true;
    }

    public final Adqdepartment insertOrUpdate() {
        if(did <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AdqdepartmentEntity.delete(did);
    }

    public final void asyncDelete() {
        AdqdepartmentEntity.asyncDelete(did);
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

    public Adqdepartment clone2() {
        try{
            return (Adqdepartment) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
