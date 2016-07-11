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

//learnhall3_design - kindclass
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Kindclass extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1677465304; // com.learnhall.db.bean.Kindclass

    public static String TABLENAME = "kindclass";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String name = "name"; public static final String imgurl = "imgurl";  }
    public static final class CEn { public static final String id = "id"; public static final String name = "name"; public static final String imgurl = "imgurl";  }
    public static final String[] carrays ={"id", "name", "imgurl"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR"};


    public int id;
    public String name;
    public String imgurl;

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

    public Kindclass setId(int id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Kindclass setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public String getImgurl(){
        return imgurl;
    }

    public Kindclass setImgurl(String imgurl){
        String _old = this.imgurl;
        this.imgurl = imgurl;
        changeIt(Col.imgurl, _old, imgurl);
        return this;
    }

    public int compareTo(Kindclass v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Kindclass v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Kindclass newKindclass(Integer id, String name, String imgurl) {
        Kindclass result = new Kindclass();
        result.id = id;
        result.name = name;
        result.imgurl = imgurl;
        return result;
    }

    public static Kindclass newKindclass(Kindclass kindclass) {
        Kindclass result = new Kindclass();
        result.id = kindclass.id;
        result.name = kindclass.name;
        result.imgurl = kindclass.imgurl;
        return result;
    }

    public Kindclass createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getKindclass(){
        Kindclass kindclass = null; // kindclass
        { // new and insert Kindclass (kindclass)
            int id = 0; 	// id
            String name = ""; 	// name
            String imgurl = ""; 	// imgurl
            kindclass = Kindclass.newKindclass(id, name, imgurl);
        }
        kindclass = kindclass.insert();

        int id = kindclass.getId(); 	// id
        String name = kindclass.getName(); 	// name
        String imgurl = kindclass.getImgurl(); 	// imgurl
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

    public Kindclass setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Kindclass setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        }
        return this;
    }

    public Kindclass changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Kindclass changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Kindclass changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Kindclass changeIntWithMin(String fieldEn, int value2, int _min) {
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

    public Kindclass setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Kindclass setDouble(String fieldEn, double value2) {
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
        case CEn.imgurl: 
            return imgurl;
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
        case CEn.imgurl:
            return imgurl;
        }
        return null;
    }

    public Kindclass setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Kindclass setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Kindclass setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Kindclass setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.imgurl:
            return setImgurl(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Kindclass setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Kindclass setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Kindclass");
        result.put("id", id);
        result.put("name", name);
        result.put("imgurl", imgurl);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("name", name);
        result.put("imgurl", imgurl);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Kindclass");
        result.put("id", id);
        result.put("name", name);
        result.put("imgurl", imgurl);
        return result;
    }

    public Kindclass mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");
        String imgurl = MapEx.getString(e, "imgurl");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(imgurl == null) imgurl = "";

        setId(id);
        setName(name);
        setImgurl(imgurl);

        return this;
    }

    public static final Kindclass mapTo(Map e){
        Kindclass result = new Kindclass();

        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");
        String imgurl = MapEx.getString(e, "imgurl");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(imgurl == null) imgurl = "";

        result.id = id;
        result.name = name;
        result.imgurl = imgurl;

        return result;
    }

    public static final Kindclass originalTo(Map e){
        Kindclass result = new Kindclass();

        Integer id = MapEx.getInt(e, "id");
        String name = MapEx.getString(e, "name");
        String imgurl = MapEx.getString(e, "imgurl");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(imgurl == null) imgurl = "";

        result.id = id;
        result.name = name;
        result.imgurl = imgurl;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Kindclass createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 3);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "imgurl", imgurl);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Kindclass createFor(byte[] b) throws Exception {
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
    public final List<Kind> getKindsFkKclassid() { // kind - kclassid
        return KindEntity.getByKclassid(id);
    }

    public final int countKindsFkKclassid() { // kind - kclassid
        return KindEntity.countByKclassid(id);
    }

    public static final Kindclass loadByKey(int id) {
        return KindclassEntity.getByKey(id);
    }

    public static final Future<Kindclass> asyncByKey(int id) {
        return KindclassEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Kindclass insert() {
        Kindclass result = KindclassEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Kindclass asyncInsert() {
        Kindclass result = KindclassEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Kindclass insert2() {
        return KindclassEntity.insert2(this);
    }

    public final Kindclass asyncInsert2() {
        Kindclass result = KindclassEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Kindclass update() {
        return KindclassEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        KindclassEntity.asyncUpdate( this );
        return true;
    }

    public final Kindclass insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return KindclassEntity.delete(id);
    }

    public final void asyncDelete() {
        KindclassEntity.asyncDelete(id);
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

    public Kindclass clone2() {
        try{
            return (Kindclass) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
