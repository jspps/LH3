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

//learnhall3_design - itms4auto
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Itms4auto extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1808296218; // com.learnhall.db.bean.Itms4auto

    public static String TABLENAME = "itms4auto";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String kindid = "kindid"; public static final String num4radio = "num4radio"; public static final String num4chbox = "num4chbox"; public static final String num4judge = "num4judge"; public static final String num4fill = "num4fill"; public static final String num4jd = "num4jd"; public static final String num4luns = "num4luns";  }
    public static final class CEn { public static final String id = "id"; public static final String kindid = "kindid"; public static final String num4radio = "num4radio"; public static final String num4chbox = "num4chbox"; public static final String num4judge = "num4judge"; public static final String num4fill = "num4fill"; public static final String num4jd = "num4jd"; public static final String num4luns = "num4luns";  }
    public static final String[] carrays ={"id", "kindid", "num4radio", "num4chbox", "num4judge", "num4fill", "num4jd", "num4luns"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT"};


    public int id;
    public int kindid;
    public int num4radio;
    public int num4chbox;
    public int num4judge;
    public int num4fill;
    public int num4jd;
    public int num4luns;

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

    public Itms4auto setId(int id){
        this.id = id;
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Itms4auto setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Itms4auto changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Itms4auto changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getNum4radio(){
        return num4radio;
    }

    public Itms4auto setNum4radio(int num4radio){
        int _old = this.num4radio;
        this.num4radio = num4radio;
        changeIt(Col.num4radio, _old, num4radio);
        return this;
    }

    public Itms4auto changeNum4radio(int num4radio){
        return setNum4radio(this.num4radio + num4radio);
    }

    public Itms4auto changeNum4radioWithMin(int num4radio, int _min){
        int _v2 = this.num4radio + num4radio;
        return setNum4radio(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeNum4radioWithMax(int num4radio, int _max){
        int _v2 = this.num4radio + num4radio;
        return setNum4radio(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeNum4radioWithMinMax(int num4radio, int _min, int _max){
        int _v2 = this.num4radio + num4radio;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum4radio(_v2 < _min  ? _min : _v2);
    }

    public int getNum4chbox(){
        return num4chbox;
    }

    public Itms4auto setNum4chbox(int num4chbox){
        int _old = this.num4chbox;
        this.num4chbox = num4chbox;
        changeIt(Col.num4chbox, _old, num4chbox);
        return this;
    }

    public Itms4auto changeNum4chbox(int num4chbox){
        return setNum4chbox(this.num4chbox + num4chbox);
    }

    public Itms4auto changeNum4chboxWithMin(int num4chbox, int _min){
        int _v2 = this.num4chbox + num4chbox;
        return setNum4chbox(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeNum4chboxWithMax(int num4chbox, int _max){
        int _v2 = this.num4chbox + num4chbox;
        return setNum4chbox(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeNum4chboxWithMinMax(int num4chbox, int _min, int _max){
        int _v2 = this.num4chbox + num4chbox;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum4chbox(_v2 < _min  ? _min : _v2);
    }

    public int getNum4judge(){
        return num4judge;
    }

    public Itms4auto setNum4judge(int num4judge){
        int _old = this.num4judge;
        this.num4judge = num4judge;
        changeIt(Col.num4judge, _old, num4judge);
        return this;
    }

    public Itms4auto changeNum4judge(int num4judge){
        return setNum4judge(this.num4judge + num4judge);
    }

    public Itms4auto changeNum4judgeWithMin(int num4judge, int _min){
        int _v2 = this.num4judge + num4judge;
        return setNum4judge(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeNum4judgeWithMax(int num4judge, int _max){
        int _v2 = this.num4judge + num4judge;
        return setNum4judge(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeNum4judgeWithMinMax(int num4judge, int _min, int _max){
        int _v2 = this.num4judge + num4judge;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum4judge(_v2 < _min  ? _min : _v2);
    }

    public int getNum4fill(){
        return num4fill;
    }

    public Itms4auto setNum4fill(int num4fill){
        int _old = this.num4fill;
        this.num4fill = num4fill;
        changeIt(Col.num4fill, _old, num4fill);
        return this;
    }

    public Itms4auto changeNum4fill(int num4fill){
        return setNum4fill(this.num4fill + num4fill);
    }

    public Itms4auto changeNum4fillWithMin(int num4fill, int _min){
        int _v2 = this.num4fill + num4fill;
        return setNum4fill(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeNum4fillWithMax(int num4fill, int _max){
        int _v2 = this.num4fill + num4fill;
        return setNum4fill(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeNum4fillWithMinMax(int num4fill, int _min, int _max){
        int _v2 = this.num4fill + num4fill;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum4fill(_v2 < _min  ? _min : _v2);
    }

    public int getNum4jd(){
        return num4jd;
    }

    public Itms4auto setNum4jd(int num4jd){
        int _old = this.num4jd;
        this.num4jd = num4jd;
        changeIt(Col.num4jd, _old, num4jd);
        return this;
    }

    public Itms4auto changeNum4jd(int num4jd){
        return setNum4jd(this.num4jd + num4jd);
    }

    public Itms4auto changeNum4jdWithMin(int num4jd, int _min){
        int _v2 = this.num4jd + num4jd;
        return setNum4jd(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeNum4jdWithMax(int num4jd, int _max){
        int _v2 = this.num4jd + num4jd;
        return setNum4jd(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeNum4jdWithMinMax(int num4jd, int _min, int _max){
        int _v2 = this.num4jd + num4jd;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum4jd(_v2 < _min  ? _min : _v2);
    }

    public int getNum4luns(){
        return num4luns;
    }

    public Itms4auto setNum4luns(int num4luns){
        int _old = this.num4luns;
        this.num4luns = num4luns;
        changeIt(Col.num4luns, _old, num4luns);
        return this;
    }

    public Itms4auto changeNum4luns(int num4luns){
        return setNum4luns(this.num4luns + num4luns);
    }

    public Itms4auto changeNum4lunsWithMin(int num4luns, int _min){
        int _v2 = this.num4luns + num4luns;
        return setNum4luns(_v2 < _min  ? _min : _v2);
    }

    public Itms4auto changeNum4lunsWithMax(int num4luns, int _max){
        int _v2 = this.num4luns + num4luns;
        return setNum4luns(_v2 > _max  ? _max : _v2);
    }

    public Itms4auto changeNum4lunsWithMinMax(int num4luns, int _min, int _max){
        int _v2 = this.num4luns + num4luns;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum4luns(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Itms4auto v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Itms4auto v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Itms4auto newItms4auto(Integer id, Integer kindid, Integer num4radio, Integer num4chbox, Integer num4judge, Integer num4fill, Integer num4jd, Integer num4luns) {
        Itms4auto result = new Itms4auto();
        result.id = id;
        result.kindid = kindid;
        result.num4radio = num4radio;
        result.num4chbox = num4chbox;
        result.num4judge = num4judge;
        result.num4fill = num4fill;
        result.num4jd = num4jd;
        result.num4luns = num4luns;
        return result;
    }

    public static Itms4auto newItms4auto(Itms4auto itms4auto) {
        Itms4auto result = new Itms4auto();
        result.id = itms4auto.id;
        result.kindid = itms4auto.kindid;
        result.num4radio = itms4auto.num4radio;
        result.num4chbox = itms4auto.num4chbox;
        result.num4judge = itms4auto.num4judge;
        result.num4fill = itms4auto.num4fill;
        result.num4jd = itms4auto.num4jd;
        result.num4luns = itms4auto.num4luns;
        return result;
    }

    public Itms4auto createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getItms4auto(){
        Itms4auto itms4auto = null; // itms4auto
        { // new and insert Itms4auto (itms4auto)
            int id = 0; 	// id
            int kindid = 0; 	// kindid
            int num4radio = 0; 	// num4radio
            int num4chbox = 0; 	// num4chbox
            int num4judge = 0; 	// num4judge
            int num4fill = 0; 	// num4fill
            int num4jd = 0; 	// num4jd
            int num4luns = 0; 	// num4luns
            itms4auto = Itms4auto.newItms4auto(id, kindid, num4radio, num4chbox, num4judge, num4fill, num4jd, num4luns);
        }
        itms4auto = itms4auto.insert();

        int id = itms4auto.getId(); 	// id
        int kindid = itms4auto.getKindid(); 	// kindid
        int num4radio = itms4auto.getNum4radio(); 	// num4radio
        int num4chbox = itms4auto.getNum4chbox(); 	// num4chbox
        int num4judge = itms4auto.getNum4judge(); 	// num4judge
        int num4fill = itms4auto.getNum4fill(); 	// num4fill
        int num4jd = itms4auto.getNum4jd(); 	// num4jd
        int num4luns = itms4auto.getNum4luns(); 	// num4luns
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
        case CEn.kindid:
            return kindid;
        case CEn.num4radio:
            return num4radio;
        case CEn.num4chbox:
            return num4chbox;
        case CEn.num4judge:
            return num4judge;
        case CEn.num4fill:
            return num4fill;
        case CEn.num4jd:
            return num4jd;
        case CEn.num4luns:
            return num4luns;
        }
        return 0;
    }

    public Itms4auto setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Itms4auto setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.num4radio:
            return setNum4radio(value2);
        case CEn.num4chbox:
            return setNum4chbox(value2);
        case CEn.num4judge:
            return setNum4judge(value2);
        case CEn.num4fill:
            return setNum4fill(value2);
        case CEn.num4jd:
            return setNum4jd(value2);
        case CEn.num4luns:
            return setNum4luns(value2);
        }
        return this;
    }

    public Itms4auto changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Itms4auto changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.num4radio:
            return changeNum4radio(value2);
        case CEn.num4chbox:
            return changeNum4chbox(value2);
        case CEn.num4judge:
            return changeNum4judge(value2);
        case CEn.num4fill:
            return changeNum4fill(value2);
        case CEn.num4jd:
            return changeNum4jd(value2);
        case CEn.num4luns:
            return changeNum4luns(value2);
        }
        return this;
    }

    public Itms4auto changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Itms4auto changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.num4radio:
            return changeNum4radioWithMin(value2, _min);
        case CEn.num4chbox:
            return changeNum4chboxWithMin(value2, _min);
        case CEn.num4judge:
            return changeNum4judgeWithMin(value2, _min);
        case CEn.num4fill:
            return changeNum4fillWithMin(value2, _min);
        case CEn.num4jd:
            return changeNum4jdWithMin(value2, _min);
        case CEn.num4luns:
            return changeNum4lunsWithMin(value2, _min);
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

    public Itms4auto setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Itms4auto setDouble(String fieldEn, double value2) {
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
        case CEn.kindid:
            return kindid;
        case CEn.num4radio:
            return num4radio;
        case CEn.num4chbox:
            return num4chbox;
        case CEn.num4judge:
            return num4judge;
        case CEn.num4fill:
            return num4fill;
        case CEn.num4jd:
            return num4jd;
        case CEn.num4luns:
            return num4luns;
        }
        return null;
    }

    public Itms4auto setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Itms4auto setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Itms4auto setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Itms4auto setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Itms4auto setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Itms4auto setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Itms4auto");
        result.put("id", id);
        result.put("kindid", kindid);
        result.put("num4radio", num4radio);
        result.put("num4chbox", num4chbox);
        result.put("num4judge", num4judge);
        result.put("num4fill", num4fill);
        result.put("num4jd", num4jd);
        result.put("num4luns", num4luns);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("kindid", kindid);
        result.put("num4radio", num4radio);
        result.put("num4chbox", num4chbox);
        result.put("num4judge", num4judge);
        result.put("num4fill", num4fill);
        result.put("num4jd", num4jd);
        result.put("num4luns", num4luns);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Itms4auto");
        result.put("id", id);
        result.put("kindid", kindid);
        result.put("num4radio", num4radio);
        result.put("num4chbox", num4chbox);
        result.put("num4judge", num4judge);
        result.put("num4fill", num4fill);
        result.put("num4jd", num4jd);
        result.put("num4luns", num4luns);
        return result;
    }

    public Itms4auto mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer num4radio = MapEx.getInt(e, "num4radio");
        Integer num4chbox = MapEx.getInt(e, "num4chbox");
        Integer num4judge = MapEx.getInt(e, "num4judge");
        Integer num4fill = MapEx.getInt(e, "num4fill");
        Integer num4jd = MapEx.getInt(e, "num4jd");
        Integer num4luns = MapEx.getInt(e, "num4luns");

        if(id == null) id = 0;
        if(kindid == null) kindid = 0;
        if(num4radio == null) num4radio = 0;
        if(num4chbox == null) num4chbox = 0;
        if(num4judge == null) num4judge = 0;
        if(num4fill == null) num4fill = 0;
        if(num4jd == null) num4jd = 0;
        if(num4luns == null) num4luns = 0;

        setId(id);
        setKindid(kindid);
        setNum4radio(num4radio);
        setNum4chbox(num4chbox);
        setNum4judge(num4judge);
        setNum4fill(num4fill);
        setNum4jd(num4jd);
        setNum4luns(num4luns);

        return this;
    }

    public static final Itms4auto mapTo(Map e){
        Itms4auto result = new Itms4auto();

        Integer id = MapEx.getInt(e, "id");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer num4radio = MapEx.getInt(e, "num4radio");
        Integer num4chbox = MapEx.getInt(e, "num4chbox");
        Integer num4judge = MapEx.getInt(e, "num4judge");
        Integer num4fill = MapEx.getInt(e, "num4fill");
        Integer num4jd = MapEx.getInt(e, "num4jd");
        Integer num4luns = MapEx.getInt(e, "num4luns");

        if(id == null) id = 0;
        if(kindid == null) kindid = 0;
        if(num4radio == null) num4radio = 0;
        if(num4chbox == null) num4chbox = 0;
        if(num4judge == null) num4judge = 0;
        if(num4fill == null) num4fill = 0;
        if(num4jd == null) num4jd = 0;
        if(num4luns == null) num4luns = 0;

        result.id = id;
        result.kindid = kindid;
        result.num4radio = num4radio;
        result.num4chbox = num4chbox;
        result.num4judge = num4judge;
        result.num4fill = num4fill;
        result.num4jd = num4jd;
        result.num4luns = num4luns;

        return result;
    }

    public static final Itms4auto originalTo(Map e){
        Itms4auto result = new Itms4auto();

        Integer id = MapEx.getInt(e, "id");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer num4radio = MapEx.getInt(e, "num4radio");
        Integer num4chbox = MapEx.getInt(e, "num4chbox");
        Integer num4judge = MapEx.getInt(e, "num4judge");
        Integer num4fill = MapEx.getInt(e, "num4fill");
        Integer num4jd = MapEx.getInt(e, "num4jd");
        Integer num4luns = MapEx.getInt(e, "num4luns");

        if(id == null) id = 0;
        if(kindid == null) kindid = 0;
        if(num4radio == null) num4radio = 0;
        if(num4chbox == null) num4chbox = 0;
        if(num4judge == null) num4judge = 0;
        if(num4fill == null) num4fill = 0;
        if(num4jd == null) num4jd = 0;
        if(num4luns == null) num4luns = 0;

        result.id = id;
        result.kindid = kindid;
        result.num4radio = num4radio;
        result.num4chbox = num4chbox;
        result.num4judge = num4judge;
        result.num4fill = num4fill;
        result.num4jd = num4jd;
        result.num4luns = num4luns;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Itms4auto createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 8);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "num4radio", num4radio);
            writeMapEntry(out, "num4chbox", num4chbox);
            writeMapEntry(out, "num4judge", num4judge);
            writeMapEntry(out, "num4fill", num4fill);
            writeMapEntry(out, "num4jd", num4jd);
            writeMapEntry(out, "num4luns", num4luns);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Itms4auto createFor(byte[] b) throws Exception {
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
    public static final Itms4auto loadByKey(int id) {
        return Itms4autoEntity.getByKey(id);
    }

    public static final Future<Itms4auto> asyncByKey(int id) {
        return Itms4autoEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Itms4auto insert() {
        Itms4auto result = Itms4autoEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Itms4auto asyncInsert() {
        Itms4auto result = Itms4autoEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Itms4auto insert2() {
        return Itms4autoEntity.insert2(this);
    }

    public final Itms4auto asyncInsert2() {
        Itms4auto result = Itms4autoEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Itms4auto update() {
        return Itms4autoEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Itms4autoEntity.asyncUpdate( this );
        return true;
    }

    public final Itms4auto insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Itms4autoEntity.delete(id);
    }

    public final void asyncDelete() {
        Itms4autoEntity.asyncDelete(id);
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

    public Itms4auto clone2() {
        try{
            return (Itms4auto) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
