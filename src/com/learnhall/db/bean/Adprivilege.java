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

//learnhall3_design - adprivilege
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Adprivilege extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -458219166; // com.learnhall.db.bean.Adprivilege

    public static String TABLENAME = "adprivilege";

    public static final String primary = "prid";

    public static final class Col { public static final String prid = "prid"; public static final String name = "name"; public static final String pdesc = "pdesc"; public static final String url = "url"; public static final String parentid = "parentid";  }
    public static final class CEn { public static final String prid = "prid"; public static final String name = "name"; public static final String pdesc = "pdesc"; public static final String url = "url"; public static final String parentid = "parentid";  }
    public static final String[] carrays ={"prid", "name", "pdesc", "url", "parentid"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "VARCHAR", "INT"};


    public int prid;
    public String name;
    public String pdesc;
    public String url;
    public int parentid;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return prid;
    }

    public static String _key(int prid) {
        return PStr.b(TABLENAME).a("-").e(prid);
    }

    public int getPrid(){
        return prid;
    }

    public Adprivilege setPrid(int prid){
        this.prid = prid;
        return this;
    }

    public String getName(){
        return name;
    }

    public Adprivilege setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public String getPdesc(){
        return pdesc;
    }

    public Adprivilege setPdesc(String pdesc){
        String _old = this.pdesc;
        this.pdesc = pdesc;
        changeIt(Col.pdesc, _old, pdesc);
        return this;
    }

    public String getUrl(){
        return url;
    }

    public Adprivilege setUrl(String url){
        String _old = this.url;
        this.url = url;
        changeIt(Col.url, _old, url);
        return this;
    }

    public int getParentid(){
        return parentid;
    }

    public Adprivilege setParentid(int parentid){
        int _old = this.parentid;
        this.parentid = parentid;
        changeIt(Col.parentid, _old, parentid);
        return this;
    }

    public Adprivilege changeParentid(int parentid){
        return setParentid(this.parentid + parentid);
    }

    public Adprivilege changeParentidWithMin(int parentid, int _min){
        int _v2 = this.parentid + parentid;
        return setParentid(_v2 < _min  ? _min : _v2);
    }

    public Adprivilege changeParentidWithMax(int parentid, int _max){
        int _v2 = this.parentid + parentid;
        return setParentid(_v2 > _max  ? _max : _v2);
    }

    public Adprivilege changeParentidWithMinMax(int parentid, int _min, int _max){
        int _v2 = this.parentid + parentid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setParentid(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Adprivilege v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Adprivilege v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Adprivilege newAdprivilege(Integer prid, String name, String pdesc, String url, Integer parentid) {
        Adprivilege result = new Adprivilege();
        result.prid = prid;
        result.name = name;
        result.pdesc = pdesc;
        result.url = url;
        result.parentid = parentid;
        return result;
    }

    public static Adprivilege newAdprivilege(Adprivilege adprivilege) {
        Adprivilege result = new Adprivilege();
        result.prid = adprivilege.prid;
        result.name = adprivilege.name;
        result.pdesc = adprivilege.pdesc;
        result.url = adprivilege.url;
        result.parentid = adprivilege.parentid;
        return result;
    }

    public Adprivilege createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAdprivilege(){
        Adprivilege adprivilege = null; // adprivilege
        { // new and insert Adprivilege (adprivilege)
            int prid = 0; 	// prid
            String name = ""; 	// name
            String pdesc = ""; 	// pdesc
            String url = ""; 	// url
            int parentid = 0; 	// parentid
            adprivilege = Adprivilege.newAdprivilege(prid, name, pdesc, url, parentid);
        }
        adprivilege = adprivilege.insert();

        int prid = adprivilege.getPrid(); 	// prid
        String name = adprivilege.getName(); 	// name
        String pdesc = adprivilege.getPdesc(); 	// pdesc
        String url = adprivilege.getUrl(); 	// url
        int parentid = adprivilege.getParentid(); 	// parentid
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.prid:
            return prid;
        case CEn.parentid:
            return parentid;
        }
        return 0;
    }

    public Adprivilege setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Adprivilege setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.prid:
            return setPrid(value2);
        case CEn.parentid:
            return setParentid(value2);
        }
        return this;
    }

    public Adprivilege changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Adprivilege changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.parentid:
            return changeParentid(value2);
        }
        return this;
    }

    public Adprivilege changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Adprivilege changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.parentid:
            return changeParentidWithMin(value2, _min);
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

    public Adprivilege setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Adprivilege setDouble(String fieldEn, double value2) {
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
        case CEn.pdesc: 
            return pdesc;
        case CEn.url: 
            return url;
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
        case CEn.prid:
            return prid;
        case CEn.name:
            return name;
        case CEn.pdesc:
            return pdesc;
        case CEn.url:
            return url;
        case CEn.parentid:
            return parentid;
        }
        return null;
    }

    public Adprivilege setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Adprivilege setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Adprivilege setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Adprivilege setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.pdesc:
            return setPdesc(value2);
        case CEn.url:
            return setUrl(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Adprivilege setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Adprivilege setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Adprivilege");
        result.put("prid", prid);
        result.put("name", name);
        result.put("pdesc", pdesc);
        result.put("url", url);
        result.put("parentid", parentid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("prid", prid);
        result.put("name", name);
        result.put("pdesc", pdesc);
        result.put("url", url);
        result.put("parentid", parentid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Adprivilege");
        result.put("prid", prid);
        result.put("name", name);
        result.put("pdesc", pdesc);
        result.put("url", url);
        result.put("parentid", parentid);
        return result;
    }

    public Adprivilege mapToObject(Map e){
        Integer prid = MapEx.getInt(e, "prid");
        String name = MapEx.getString(e, "name");
        String pdesc = MapEx.getString(e, "pdesc");
        String url = MapEx.getString(e, "url");
        Integer parentid = MapEx.getInt(e, "parentid");

        if(prid == null) prid = 0;
        if(name == null) name = "";
        if(pdesc == null) pdesc = "";
        if(url == null) url = "";
        if(parentid == null) parentid = 0;

        setPrid(prid);
        setName(name);
        setPdesc(pdesc);
        setUrl(url);
        setParentid(parentid);

        return this;
    }

    public static final Adprivilege mapTo(Map e){
        Adprivilege result = new Adprivilege();

        Integer prid = MapEx.getInt(e, "prid");
        String name = MapEx.getString(e, "name");
        String pdesc = MapEx.getString(e, "pdesc");
        String url = MapEx.getString(e, "url");
        Integer parentid = MapEx.getInt(e, "parentid");

        if(prid == null) prid = 0;
        if(name == null) name = "";
        if(pdesc == null) pdesc = "";
        if(url == null) url = "";
        if(parentid == null) parentid = 0;

        result.prid = prid;
        result.name = name;
        result.pdesc = pdesc;
        result.url = url;
        result.parentid = parentid;

        return result;
    }

    public static final Adprivilege originalTo(Map e){
        Adprivilege result = new Adprivilege();

        Integer prid = MapEx.getInt(e, "prid");
        String name = MapEx.getString(e, "name");
        String pdesc = MapEx.getString(e, "pdesc");
        String url = MapEx.getString(e, "url");
        Integer parentid = MapEx.getInt(e, "parentid");

        if(prid == null) prid = 0;
        if(name == null) name = "";
        if(pdesc == null) pdesc = "";
        if(url == null) url = "";
        if(parentid == null) parentid = 0;

        result.prid = prid;
        result.name = name;
        result.pdesc = pdesc;
        result.url = url;
        result.parentid = parentid;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Adprivilege createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 5);
            writeMapEntry(out, "prid", prid);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "pdesc", pdesc);
            writeMapEntry(out, "url", url);
            writeMapEntry(out, "parentid", parentid);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Adprivilege createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(prid);
        return s.hashCode();
    }
    public final Adprivilege getAdprivilegeFkParentid() { // adprivilege - parentid
        return AdprivilegeEntity.getByKey(parentid);
    }

    public final int countAdprivilegeFkParentid() { // adprivilege - parentid
        return getAdprivilegeFkParentid() == null ? 0 : 1;
    }

    public final List<Adprivilege> getAdprivilegesFkParentid() { // adprivilege - parentid
        return AdprivilegeEntity.getByParentid(prid);
    }

    public final int countAdprivilegesFkParentid() { // adprivilege - parentid
        return AdprivilegeEntity.countByParentid(prid);
    }

    public static final Adprivilege loadByKey(int prid) {
        return AdprivilegeEntity.getByKey(prid);
    }

    public static final Future<Adprivilege> asyncByKey(int prid) {
        return AdprivilegeEntity.asyncGetByKey(prid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Adprivilege insert() {
        Adprivilege result = AdprivilegeEntity.insert(this);
        if(result == null) return null;
        // prid = result.prid;
        return result;
    }

    public final Adprivilege asyncInsert() {
        Adprivilege result = AdprivilegeEntity.asyncInsert(this);
        // prid = result.prid;
        return result;
    }

    public final Adprivilege insert2() {
        return AdprivilegeEntity.insert2(this);
    }

    public final Adprivilege asyncInsert2() {
        Adprivilege result = AdprivilegeEntity.asyncInsert2(this);
        // prid = result.prid;
        return result;
    }

    public final Adprivilege update() {
        return AdprivilegeEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(prid <= 0) return false;
        AdprivilegeEntity.asyncUpdate( this );
        return true;
    }

    public final Adprivilege insertOrUpdate() {
        if(prid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AdprivilegeEntity.delete(prid);
    }

    public final void asyncDelete() {
        AdprivilegeEntity.asyncDelete(prid);
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

    public Adprivilege clone2() {
        try{
            return (Adprivilege) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
