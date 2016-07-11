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

//learnhall3_design - rnk4profit
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Rnk4profit extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1566958347; // com.learnhall.db.bean.Rnk4profit

    public static String TABLENAME = "rnk4profit";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String indexs = "indexs"; public static final String type = "type"; public static final String ownerid = "ownerid"; public static final String money = "money"; public static final String bonus = "bonus"; public static final String royalty = "royalty";  }
    public static final class CEn { public static final String id = "id"; public static final String indexs = "indexs"; public static final String type = "type"; public static final String ownerid = "ownerid"; public static final String money = "money"; public static final String bonus = "bonus"; public static final String royalty = "royalty";  }
    public static final String[] carrays ={"id", "indexs", "type", "ownerid", "money", "bonus", "royalty"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "DOUBLE", "DOUBLE", "DOUBLE"};


    public int id;
    public int indexs;
    public int type;
    public int ownerid;
    public double money;
    public double bonus;
    public double royalty;

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

    public Rnk4profit setId(int id){
        this.id = id;
        return this;
    }

    public int getIndexs(){
        return indexs;
    }

    public Rnk4profit setIndexs(int indexs){
        int _old = this.indexs;
        this.indexs = indexs;
        changeIt(Col.indexs, _old, indexs);
        return this;
    }

    public Rnk4profit changeIndexs(int indexs){
        return setIndexs(this.indexs + indexs);
    }

    public Rnk4profit changeIndexsWithMin(int indexs, int _min){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public Rnk4profit changeIndexsWithMax(int indexs, int _max){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 > _max  ? _max : _v2);
    }

    public Rnk4profit changeIndexsWithMinMax(int indexs, int _min, int _max){
        int _v2 = this.indexs + indexs;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Rnk4profit setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Rnk4profit changeType(int type){
        return setType(this.type + type);
    }

    public Rnk4profit changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Rnk4profit changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Rnk4profit changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int getOwnerid(){
        return ownerid;
    }

    public Rnk4profit setOwnerid(int ownerid){
        int _old = this.ownerid;
        this.ownerid = ownerid;
        changeIt(Col.ownerid, _old, ownerid);
        return this;
    }

    public Rnk4profit changeOwnerid(int ownerid){
        return setOwnerid(this.ownerid + ownerid);
    }

    public Rnk4profit changeOwneridWithMin(int ownerid, int _min){
        int _v2 = this.ownerid + ownerid;
        return setOwnerid(_v2 < _min  ? _min : _v2);
    }

    public Rnk4profit changeOwneridWithMax(int ownerid, int _max){
        int _v2 = this.ownerid + ownerid;
        return setOwnerid(_v2 > _max  ? _max : _v2);
    }

    public Rnk4profit changeOwneridWithMinMax(int ownerid, int _min, int _max){
        int _v2 = this.ownerid + ownerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOwnerid(_v2 < _min  ? _min : _v2);
    }

    public double getMoney(){
        return money;
    }

    public Rnk4profit setMoney(double money){
        double _old = this.money;
        this.money = money;
        changeIt(Col.money, _old, money);
        return this;
    }

    public Rnk4profit changeMoney(double money){
        return setMoney(this.money + money);
    }

    public Rnk4profit changeMoneyWithMin(double money, double _min){
        double _v2 = this.money + money;
        return setMoney(_v2 < _min  ? _min : _v2);
    }

    public Rnk4profit changeMoneyWithMax(double money, double _max){
        double _v2 = this.money + money;
        return setMoney(_v2 > _max  ? _max : _v2);
    }

    public Rnk4profit changeMoneyWithMinMax(double money, double _min, double _max){
        double _v2 = this.money + money;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoney(_v2 < _min  ? _min : _v2);
    }

    public double getBonus(){
        return bonus;
    }

    public Rnk4profit setBonus(double bonus){
        double _old = this.bonus;
        this.bonus = bonus;
        changeIt(Col.bonus, _old, bonus);
        return this;
    }

    public Rnk4profit changeBonus(double bonus){
        return setBonus(this.bonus + bonus);
    }

    public Rnk4profit changeBonusWithMin(double bonus, double _min){
        double _v2 = this.bonus + bonus;
        return setBonus(_v2 < _min  ? _min : _v2);
    }

    public Rnk4profit changeBonusWithMax(double bonus, double _max){
        double _v2 = this.bonus + bonus;
        return setBonus(_v2 > _max  ? _max : _v2);
    }

    public Rnk4profit changeBonusWithMinMax(double bonus, double _min, double _max){
        double _v2 = this.bonus + bonus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBonus(_v2 < _min  ? _min : _v2);
    }

    public double getRoyalty(){
        return royalty;
    }

    public Rnk4profit setRoyalty(double royalty){
        double _old = this.royalty;
        this.royalty = royalty;
        changeIt(Col.royalty, _old, royalty);
        return this;
    }

    public Rnk4profit changeRoyalty(double royalty){
        return setRoyalty(this.royalty + royalty);
    }

    public Rnk4profit changeRoyaltyWithMin(double royalty, double _min){
        double _v2 = this.royalty + royalty;
        return setRoyalty(_v2 < _min  ? _min : _v2);
    }

    public Rnk4profit changeRoyaltyWithMax(double royalty, double _max){
        double _v2 = this.royalty + royalty;
        return setRoyalty(_v2 > _max  ? _max : _v2);
    }

    public Rnk4profit changeRoyaltyWithMinMax(double royalty, double _min, double _max){
        double _v2 = this.royalty + royalty;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRoyalty(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Rnk4profit v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Rnk4profit v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Rnk4profit newRnk4profit(Integer id, Integer indexs, Integer type, Integer ownerid, Double money, Double bonus, Double royalty) {
        Rnk4profit result = new Rnk4profit();
        result.id = id;
        result.indexs = indexs;
        result.type = type;
        result.ownerid = ownerid;
        result.money = money;
        result.bonus = bonus;
        result.royalty = royalty;
        return result;
    }

    public static Rnk4profit newRnk4profit(Rnk4profit rnk4profit) {
        Rnk4profit result = new Rnk4profit();
        result.id = rnk4profit.id;
        result.indexs = rnk4profit.indexs;
        result.type = rnk4profit.type;
        result.ownerid = rnk4profit.ownerid;
        result.money = rnk4profit.money;
        result.bonus = rnk4profit.bonus;
        result.royalty = rnk4profit.royalty;
        return result;
    }

    public Rnk4profit createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRnk4profit(){
        Rnk4profit rnk4profit = null; // rnk4profit
        { // new and insert Rnk4profit (rnk4profit)
            int id = 0; 	// id
            int indexs = 0; 	// indexs
            int type = 0; 	// type
            int ownerid = 0; 	// ownerid
            double money = 0.0; 	// money
            double bonus = 0.0; 	// bonus
            double royalty = 0.0; 	// royalty
            rnk4profit = Rnk4profit.newRnk4profit(id, indexs, type, ownerid, money, bonus, royalty);
        }
        rnk4profit = rnk4profit.insert();

        int id = rnk4profit.getId(); 	// id
        int indexs = rnk4profit.getIndexs(); 	// indexs
        int type = rnk4profit.getType(); 	// type
        int ownerid = rnk4profit.getOwnerid(); 	// ownerid
        double money = rnk4profit.getMoney(); 	// money
        double bonus = rnk4profit.getBonus(); 	// bonus
        double royalty = rnk4profit.getRoyalty(); 	// royalty
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
        case CEn.indexs:
            return indexs;
        case CEn.type:
            return type;
        case CEn.ownerid:
            return ownerid;
        }
        return 0;
    }

    public Rnk4profit setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Rnk4profit setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.indexs:
            return setIndexs(value2);
        case CEn.type:
            return setType(value2);
        case CEn.ownerid:
            return setOwnerid(value2);
        }
        return this;
    }

    public Rnk4profit changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Rnk4profit changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.indexs:
            return changeIndexs(value2);
        case CEn.type:
            return changeType(value2);
        case CEn.ownerid:
            return changeOwnerid(value2);
        }
        return this;
    }

    public Rnk4profit changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Rnk4profit changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.indexs:
            return changeIndexsWithMin(value2, _min);
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.ownerid:
            return changeOwneridWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.money:
            return money;
        case CEn.bonus:
            return bonus;
        case CEn.royalty:
            return royalty;
        }
        return 0;
    }

    public Rnk4profit setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Rnk4profit setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.money:
            return setMoney(value2);
        case CEn.bonus:
            return setBonus(value2);
        case CEn.royalty:
            return setRoyalty(value2);
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
        case CEn.indexs:
            return indexs;
        case CEn.type:
            return type;
        case CEn.ownerid:
            return ownerid;
        case CEn.money:
            return money;
        case CEn.bonus:
            return bonus;
        case CEn.royalty:
            return royalty;
        }
        return null;
    }

    public Rnk4profit setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Rnk4profit setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Rnk4profit setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Rnk4profit setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Rnk4profit setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Rnk4profit setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Rnk4profit");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("type", type);
        result.put("ownerid", ownerid);
        result.put("money", money);
        result.put("bonus", bonus);
        result.put("royalty", royalty);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("type", type);
        result.put("ownerid", ownerid);
        result.put("money", money);
        result.put("bonus", bonus);
        result.put("royalty", royalty);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Rnk4profit");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("type", type);
        result.put("ownerid", ownerid);
        result.put("money", money);
        result.put("bonus", bonus);
        result.put("royalty", royalty);
        return result;
    }

    public Rnk4profit mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        Integer type = MapEx.getInt(e, "type");
        Integer ownerid = MapEx.getInt(e, "ownerid");
        Double money = MapEx.getDouble(e, "money");
        Double bonus = MapEx.getDouble(e, "bonus");
        Double royalty = MapEx.getDouble(e, "royalty");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(type == null) type = 0;
        if(ownerid == null) ownerid = 0;
        if(money == null) money = 0.0;
        if(bonus == null) bonus = 0.0;
        if(royalty == null) royalty = 0.0;

        setId(id);
        setIndexs(indexs);
        setType(type);
        setOwnerid(ownerid);
        setMoney(money);
        setBonus(bonus);
        setRoyalty(royalty);

        return this;
    }

    public static final Rnk4profit mapTo(Map e){
        Rnk4profit result = new Rnk4profit();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        Integer type = MapEx.getInt(e, "type");
        Integer ownerid = MapEx.getInt(e, "ownerid");
        Double money = MapEx.getDouble(e, "money");
        Double bonus = MapEx.getDouble(e, "bonus");
        Double royalty = MapEx.getDouble(e, "royalty");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(type == null) type = 0;
        if(ownerid == null) ownerid = 0;
        if(money == null) money = 0.0;
        if(bonus == null) bonus = 0.0;
        if(royalty == null) royalty = 0.0;

        result.id = id;
        result.indexs = indexs;
        result.type = type;
        result.ownerid = ownerid;
        result.money = money;
        result.bonus = bonus;
        result.royalty = royalty;

        return result;
    }

    public static final Rnk4profit originalTo(Map e){
        Rnk4profit result = new Rnk4profit();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        Integer type = MapEx.getInt(e, "type");
        Integer ownerid = MapEx.getInt(e, "ownerid");
        Double money = MapEx.getDouble(e, "money");
        Double bonus = MapEx.getDouble(e, "bonus");
        Double royalty = MapEx.getDouble(e, "royalty");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(type == null) type = 0;
        if(ownerid == null) ownerid = 0;
        if(money == null) money = 0.0;
        if(bonus == null) bonus = 0.0;
        if(royalty == null) royalty = 0.0;

        result.id = id;
        result.indexs = indexs;
        result.type = type;
        result.ownerid = ownerid;
        result.money = money;
        result.bonus = bonus;
        result.royalty = royalty;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Rnk4profit createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 7);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "indexs", indexs);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "ownerid", ownerid);
            writeMapEntry(out, "money", money);
            writeMapEntry(out, "bonus", bonus);
            writeMapEntry(out, "royalty", royalty);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Rnk4profit createFor(byte[] b) throws Exception {
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
    public static final Rnk4profit loadByKey(int id) {
        return Rnk4profitEntity.getByKey(id);
    }

    public static final Future<Rnk4profit> asyncByKey(int id) {
        return Rnk4profitEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Rnk4profit insert() {
        Rnk4profit result = Rnk4profitEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Rnk4profit asyncInsert() {
        Rnk4profit result = Rnk4profitEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Rnk4profit insert2() {
        return Rnk4profitEntity.insert2(this);
    }

    public final Rnk4profit asyncInsert2() {
        Rnk4profit result = Rnk4profitEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Rnk4profit update() {
        return Rnk4profitEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Rnk4profitEntity.asyncUpdate( this );
        return true;
    }

    public final Rnk4profit insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Rnk4profitEntity.delete(id);
    }

    public final void asyncDelete() {
        Rnk4profitEntity.asyncDelete(id);
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

    public Rnk4profit clone2() {
        try{
            return (Rnk4profit) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
