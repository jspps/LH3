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

//learnhall3_design - shoppingcart
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Shoppingcart extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1572198508; // com.learnhall.db.bean.Shoppingcart

    public static String TABLENAME = "shoppingcart";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String customerid = "customerid"; public static final String kindid = "kindid"; public static final String agentCode = "agentCode";  }
    public static final class CEn { public static final String id = "id"; public static final String customerid = "customerid"; public static final String kindid = "kindid"; public static final String agentCode = "agentCode";  }
    public static final String[] carrays ={"id", "customerid", "kindid", "agentCode"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TINYTEXT"};


    public int id;
    public int customerid;
    public int kindid;
    public String agentCode;

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

    public Shoppingcart setId(int id){
        this.id = id;
        return this;
    }

    public int getCustomerid(){
        return customerid;
    }

    public Shoppingcart setCustomerid(int customerid){
        int _old = this.customerid;
        this.customerid = customerid;
        changeIt(Col.customerid, _old, customerid);
        return this;
    }

    public Shoppingcart changeCustomerid(int customerid){
        return setCustomerid(this.customerid + customerid);
    }

    public Shoppingcart changeCustomeridWithMin(int customerid, int _min){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public Shoppingcart changeCustomeridWithMax(int customerid, int _max){
        int _v2 = this.customerid + customerid;
        return setCustomerid(_v2 > _max  ? _max : _v2);
    }

    public Shoppingcart changeCustomeridWithMinMax(int customerid, int _min, int _max){
        int _v2 = this.customerid + customerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomerid(_v2 < _min  ? _min : _v2);
    }

    public int getKindid(){
        return kindid;
    }

    public Shoppingcart setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Shoppingcart changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Shoppingcart changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Shoppingcart changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Shoppingcart changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public String getAgentCode(){
        return agentCode;
    }

    public Shoppingcart setAgentCode(String agentCode){
        String _old = this.agentCode;
        this.agentCode = agentCode;
        changeIt(Col.agentCode, _old, agentCode);
        return this;
    }

    public int compareTo(Shoppingcart v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Shoppingcart v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Shoppingcart newShoppingcart(Integer id, Integer customerid, Integer kindid, String agentCode) {
        Shoppingcart result = new Shoppingcart();
        result.id = id;
        result.customerid = customerid;
        result.kindid = kindid;
        result.agentCode = agentCode;
        return result;
    }

    public static Shoppingcart newShoppingcart(Shoppingcart shoppingcart) {
        Shoppingcart result = new Shoppingcart();
        result.id = shoppingcart.id;
        result.customerid = shoppingcart.customerid;
        result.kindid = shoppingcart.kindid;
        result.agentCode = shoppingcart.agentCode;
        return result;
    }

    public Shoppingcart createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getShoppingcart(){
        Shoppingcart shoppingcart = null; // shoppingcart
        { // new and insert Shoppingcart (shoppingcart)
            int id = 0; 	// id
            int customerid = 0; 	// customerid
            int kindid = 0; 	// kindid
            String agentCode = ""; 	// agentCode
            shoppingcart = Shoppingcart.newShoppingcart(id, customerid, kindid, agentCode);
        }
        shoppingcart = shoppingcart.insert();

        int id = shoppingcart.getId(); 	// id
        int customerid = shoppingcart.getCustomerid(); 	// customerid
        int kindid = shoppingcart.getKindid(); 	// kindid
        String agentCode = shoppingcart.getAgentCode(); 	// agentCode
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
        case CEn.customerid:
            return customerid;
        case CEn.kindid:
            return kindid;
        }
        return 0;
    }

    public Shoppingcart setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Shoppingcart setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.customerid:
            return setCustomerid(value2);
        case CEn.kindid:
            return setKindid(value2);
        }
        return this;
    }

    public Shoppingcart changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Shoppingcart changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.customerid:
            return changeCustomerid(value2);
        case CEn.kindid:
            return changeKindid(value2);
        }
        return this;
    }

    public Shoppingcart changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Shoppingcart changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.customerid:
            return changeCustomeridWithMin(value2, _min);
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
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

    public Shoppingcart setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Shoppingcart setDouble(String fieldEn, double value2) {
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
        case CEn.agentCode: 
            return agentCode;
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
        case CEn.customerid:
            return customerid;
        case CEn.kindid:
            return kindid;
        case CEn.agentCode:
            return agentCode;
        }
        return null;
    }

    public Shoppingcart setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Shoppingcart setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Shoppingcart setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Shoppingcart setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.agentCode:
            return setAgentCode(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Shoppingcart setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Shoppingcart setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Shoppingcart");
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("kindid", kindid);
        result.put("agentCode", agentCode);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("kindid", kindid);
        result.put("agentCode", agentCode);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Shoppingcart");
        result.put("id", id);
        result.put("customerid", customerid);
        result.put("kindid", kindid);
        result.put("agentCode", agentCode);
        return result;
    }

    public Shoppingcart mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer kindid = MapEx.getInt(e, "kindid");
        String agentCode = MapEx.getString(e, "agentCode");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(kindid == null) kindid = 0;
        if(agentCode == null) agentCode = "";

        setId(id);
        setCustomerid(customerid);
        setKindid(kindid);
        setAgentCode(agentCode);

        return this;
    }

    public static final Shoppingcart mapTo(Map e){
        Shoppingcart result = new Shoppingcart();

        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer kindid = MapEx.getInt(e, "kindid");
        String agentCode = MapEx.getString(e, "agentCode");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(kindid == null) kindid = 0;
        if(agentCode == null) agentCode = "";

        result.id = id;
        result.customerid = customerid;
        result.kindid = kindid;
        result.agentCode = agentCode;

        return result;
    }

    public static final Shoppingcart originalTo(Map e){
        Shoppingcart result = new Shoppingcart();

        Integer id = MapEx.getInt(e, "id");
        Integer customerid = MapEx.getInt(e, "customerid");
        Integer kindid = MapEx.getInt(e, "kindid");
        String agentCode = MapEx.getString(e, "agentCode");

        if(id == null) id = 0;
        if(customerid == null) customerid = 0;
        if(kindid == null) kindid = 0;
        if(agentCode == null) agentCode = "";

        result.id = id;
        result.customerid = customerid;
        result.kindid = kindid;
        result.agentCode = agentCode;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Shoppingcart createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 4);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "customerid", customerid);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "agentCode", agentCode);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Shoppingcart createFor(byte[] b) throws Exception {
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
    public static final Shoppingcart loadByKey(int id) {
        return ShoppingcartEntity.getByKey(id);
    }

    public static final Future<Shoppingcart> asyncByKey(int id) {
        return ShoppingcartEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Shoppingcart insert() {
        Shoppingcart result = ShoppingcartEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Shoppingcart asyncInsert() {
        Shoppingcart result = ShoppingcartEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Shoppingcart insert2() {
        return ShoppingcartEntity.insert2(this);
    }

    public final Shoppingcart asyncInsert2() {
        Shoppingcart result = ShoppingcartEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Shoppingcart update() {
        return ShoppingcartEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ShoppingcartEntity.asyncUpdate( this );
        return true;
    }

    public final Shoppingcart insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ShoppingcartEntity.delete(id);
    }

    public final void asyncDelete() {
        ShoppingcartEntity.asyncDelete(id);
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

    public Shoppingcart clone2() {
        try{
            return (Shoppingcart) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
