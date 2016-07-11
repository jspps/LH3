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

//learnhall3_design - cfgs
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Cfgs extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -336656485; // com.learnhall.db.bean.Cfgs

    public static String TABLENAME = "cfgs";

    public static final String primary = "cfgid";

    public static final class Col { public static final String cfgid = "cfgid"; public static final String name = "name"; public static final String valStr = "valStr"; public static final String valInt = "valInt";  }
    public static final class CEn { public static final String cfgid = "cfgid"; public static final String name = "name"; public static final String valStr = "valStr"; public static final String valInt = "valInt";  }
    public static final String[] carrays ={"cfgid", "name", "valStr", "valInt"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "TEXT", "INT"};


    public int cfgid;
    public String name;
    public String valStr;
    public int valInt;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return cfgid;
    }

    public static String _key(int cfgid) {
        return PStr.b(TABLENAME).a("-").e(cfgid);
    }

    public int getCfgid(){
        return cfgid;
    }

    public Cfgs setCfgid(int cfgid){
        this.cfgid = cfgid;
        return this;
    }

    public String getName(){
        return name;
    }

    public Cfgs setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public String getValStr(){
        return valStr;
    }

    public Cfgs setValStr(String valStr){
        String _old = this.valStr;
        this.valStr = valStr;
        changeIt(Col.valStr, _old, valStr);
        return this;
    }

    public int getValInt(){
        return valInt;
    }

    public Cfgs setValInt(int valInt){
        int _old = this.valInt;
        this.valInt = valInt;
        changeIt(Col.valInt, _old, valInt);
        return this;
    }

    public Cfgs changeValInt(int valInt){
        return setValInt(this.valInt + valInt);
    }

    public Cfgs changeValIntWithMin(int valInt, int _min){
        int _v2 = this.valInt + valInt;
        return setValInt(_v2 < _min  ? _min : _v2);
    }

    public Cfgs changeValIntWithMax(int valInt, int _max){
        int _v2 = this.valInt + valInt;
        return setValInt(_v2 > _max  ? _max : _v2);
    }

    public Cfgs changeValIntWithMinMax(int valInt, int _min, int _max){
        int _v2 = this.valInt + valInt;
        _v2 = _v2 > _max  ? _max : _v2;
        return setValInt(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Cfgs v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Cfgs v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Cfgs newCfgs(Integer cfgid, String name, String valStr, Integer valInt) {
        Cfgs result = new Cfgs();
        result.cfgid = cfgid;
        result.name = name;
        result.valStr = valStr;
        result.valInt = valInt;
        return result;
    }

    public static Cfgs newCfgs(Cfgs cfgs) {
        Cfgs result = new Cfgs();
        result.cfgid = cfgs.cfgid;
        result.name = cfgs.name;
        result.valStr = cfgs.valStr;
        result.valInt = cfgs.valInt;
        return result;
    }

    public Cfgs createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getCfgs(){
        Cfgs cfgs = null; // cfgs
        { // new and insert Cfgs (cfgs)
            int cfgid = 0; 	// cfgid
            String name = ""; 	// name
            String valStr = ""; 	// valStr
            int valInt = 0; 	// valInt
            cfgs = Cfgs.newCfgs(cfgid, name, valStr, valInt);
        }
        cfgs = cfgs.insert();

        int cfgid = cfgs.getCfgid(); 	// cfgid
        String name = cfgs.getName(); 	// name
        String valStr = cfgs.getValStr(); 	// valStr
        int valInt = cfgs.getValInt(); 	// valInt
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.cfgid:
            return cfgid;
        case CEn.valInt:
            return valInt;
        }
        return 0;
    }

    public Cfgs setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Cfgs setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.cfgid:
            return setCfgid(value2);
        case CEn.valInt:
            return setValInt(value2);
        }
        return this;
    }

    public Cfgs changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Cfgs changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.valInt:
            return changeValInt(value2);
        }
        return this;
    }

    public Cfgs changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Cfgs changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.valInt:
            return changeValIntWithMin(value2, _min);
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

    public Cfgs setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Cfgs setDouble(String fieldEn, double value2) {
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
        case CEn.valStr: 
            return valStr;
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
        case CEn.cfgid:
            return cfgid;
        case CEn.name:
            return name;
        case CEn.valStr:
            return valStr;
        case CEn.valInt:
            return valInt;
        }
        return null;
    }

    public Cfgs setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Cfgs setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Cfgs setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Cfgs setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.valStr:
            return setValStr(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Cfgs setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Cfgs setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Cfgs");
        result.put("cfgid", cfgid);
        result.put("name", name);
        result.put("valStr", valStr);
        result.put("valInt", valInt);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("cfgid", cfgid);
        result.put("name", name);
        result.put("valStr", valStr);
        result.put("valInt", valInt);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Cfgs");
        result.put("cfgid", cfgid);
        result.put("name", name);
        result.put("valStr", valStr);
        result.put("valInt", valInt);
        return result;
    }

    public Cfgs mapToObject(Map e){
        Integer cfgid = MapEx.getInt(e, "cfgid");
        String name = MapEx.getString(e, "name");
        String valStr = MapEx.getString(e, "valStr");
        Integer valInt = MapEx.getInt(e, "valInt");

        if(cfgid == null) cfgid = 0;
        if(name == null) name = "";
        if(valStr == null) valStr = "";
        if(valInt == null) valInt = 0;

        setCfgid(cfgid);
        setName(name);
        setValStr(valStr);
        setValInt(valInt);

        return this;
    }

    public static final Cfgs mapTo(Map e){
        Cfgs result = new Cfgs();

        Integer cfgid = MapEx.getInt(e, "cfgid");
        String name = MapEx.getString(e, "name");
        String valStr = MapEx.getString(e, "valStr");
        Integer valInt = MapEx.getInt(e, "valInt");

        if(cfgid == null) cfgid = 0;
        if(name == null) name = "";
        if(valStr == null) valStr = "";
        if(valInt == null) valInt = 0;

        result.cfgid = cfgid;
        result.name = name;
        result.valStr = valStr;
        result.valInt = valInt;

        return result;
    }

    public static final Cfgs originalTo(Map e){
        Cfgs result = new Cfgs();

        Integer cfgid = MapEx.getInt(e, "cfgid");
        String name = MapEx.getString(e, "name");
        String valStr = MapEx.getString(e, "valStr");
        Integer valInt = MapEx.getInt(e, "valInt");

        if(cfgid == null) cfgid = 0;
        if(name == null) name = "";
        if(valStr == null) valStr = "";
        if(valInt == null) valInt = 0;

        result.cfgid = cfgid;
        result.name = name;
        result.valStr = valStr;
        result.valInt = valInt;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Cfgs createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 4);
            writeMapEntry(out, "cfgid", cfgid);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "valStr", valStr);
            writeMapEntry(out, "valInt", valInt);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Cfgs createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(cfgid);
        return s.hashCode();
    }
    public static final Cfgs loadByKey(int cfgid) {
        return CfgsEntity.getByKey(cfgid);
    }

    public static final Future<Cfgs> asyncByKey(int cfgid) {
        return CfgsEntity.asyncGetByKey(cfgid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Cfgs insert() {
        Cfgs result = CfgsEntity.insert(this);
        if(result == null) return null;
        // cfgid = result.cfgid;
        return result;
    }

    public final Cfgs asyncInsert() {
        Cfgs result = CfgsEntity.asyncInsert(this);
        // cfgid = result.cfgid;
        return result;
    }

    public final Cfgs insert2() {
        return CfgsEntity.insert2(this);
    }

    public final Cfgs asyncInsert2() {
        Cfgs result = CfgsEntity.asyncInsert2(this);
        // cfgid = result.cfgid;
        return result;
    }

    public final Cfgs update() {
        return CfgsEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(cfgid <= 0) return false;
        CfgsEntity.asyncUpdate( this );
        return true;
    }

    public final Cfgs insertOrUpdate() {
        if(cfgid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return CfgsEntity.delete(cfgid);
    }

    public final void asyncDelete() {
        CfgsEntity.asyncDelete(cfgid);
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

    public Cfgs clone2() {
        try{
            return (Cfgs) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
