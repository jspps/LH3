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

//learnhall3_design - customer
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Customer extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -170552086; // com.learnhall.db.bean.Customer

    public static String TABLENAME = "customer";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String accountid = "accountid"; public static final String name = "name"; public static final String kbiAll = "kbiAll"; public static final String kbiUse = "kbiUse"; public static final String email = "email"; public static final String province = "province"; public static final String city = "city"; public static final String seat = "seat"; public static final String headIcon = "headIcon"; public static final String descr = "descr"; public static final String moneyAll = "moneyAll"; public static final String moneyCur = "moneyCur"; public static final String recommendCode = "recommendCode"; public static final String alipay = "alipay"; public static final String alipayRealName = "alipayRealName"; public static final String isVerifyAlipay = "isVerifyAlipay"; public static final String backAlipay = "backAlipay"; public static final String backAlipayName = "backAlipayName";  }
    public static final class CEn { public static final String id = "id"; public static final String accountid = "accountid"; public static final String name = "name"; public static final String kbiAll = "kbiAll"; public static final String kbiUse = "kbiUse"; public static final String email = "email"; public static final String province = "province"; public static final String city = "city"; public static final String seat = "seat"; public static final String headIcon = "headIcon"; public static final String descr = "descr"; public static final String moneyAll = "moneyAll"; public static final String moneyCur = "moneyCur"; public static final String recommendCode = "recommendCode"; public static final String alipay = "alipay"; public static final String alipayRealName = "alipayRealName"; public static final String isVerifyAlipay = "isVerifyAlipay"; public static final String backAlipay = "backAlipay"; public static final String backAlipayName = "backAlipayName";  }
    public static final String[] carrays ={"id", "accountid", "name", "kbiAll", "kbiUse", "email", "province", "city", "seat", "headIcon", "descr", "moneyAll", "moneyCur", "recommendCode", "alipay", "alipayRealName", "isVerifyAlipay", "backAlipay", "backAlipayName"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "INT", "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "TEXT", "DOUBLE", "DOUBLE", "VARCHAR", "TINYTEXT", "TINYTEXT", "BIT", "TINYTEXT", "TINYTEXT"};


    public int id;
    public int accountid;
    public String name;
    public int kbiAll;
    public int kbiUse;
    public String email;
    public String province;
    public String city;
    public String seat;
    public String headIcon;
    public String descr;
    public double moneyAll;
    public double moneyCur;
    public String recommendCode;
    public String alipay;
    public String alipayRealName;
    public boolean isVerifyAlipay;
    public String backAlipay;
    public String backAlipayName;

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

    public Customer setId(int id){
        this.id = id;
        return this;
    }

    public int getAccountid(){
        return accountid;
    }

    public Customer setAccountid(int accountid){
        int _old = this.accountid;
        this.accountid = accountid;
        changeIt(Col.accountid, _old, accountid);
        return this;
    }

    public Customer changeAccountid(int accountid){
        return setAccountid(this.accountid + accountid);
    }

    public Customer changeAccountidWithMin(int accountid, int _min){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public Customer changeAccountidWithMax(int accountid, int _max){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 > _max  ? _max : _v2);
    }

    public Customer changeAccountidWithMinMax(int accountid, int _min, int _max){
        int _v2 = this.accountid + accountid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public String getName(){
        return name;
    }

    public Customer setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public int getKbiAll(){
        return kbiAll;
    }

    public Customer setKbiAll(int kbiAll){
        int _old = this.kbiAll;
        this.kbiAll = kbiAll;
        changeIt(Col.kbiAll, _old, kbiAll);
        return this;
    }

    public Customer changeKbiAll(int kbiAll){
        return setKbiAll(this.kbiAll + kbiAll);
    }

    public Customer changeKbiAllWithMin(int kbiAll, int _min){
        int _v2 = this.kbiAll + kbiAll;
        return setKbiAll(_v2 < _min  ? _min : _v2);
    }

    public Customer changeKbiAllWithMax(int kbiAll, int _max){
        int _v2 = this.kbiAll + kbiAll;
        return setKbiAll(_v2 > _max  ? _max : _v2);
    }

    public Customer changeKbiAllWithMinMax(int kbiAll, int _min, int _max){
        int _v2 = this.kbiAll + kbiAll;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbiAll(_v2 < _min  ? _min : _v2);
    }

    public int getKbiUse(){
        return kbiUse;
    }

    public Customer setKbiUse(int kbiUse){
        int _old = this.kbiUse;
        this.kbiUse = kbiUse;
        changeIt(Col.kbiUse, _old, kbiUse);
        return this;
    }

    public Customer changeKbiUse(int kbiUse){
        return setKbiUse(this.kbiUse + kbiUse);
    }

    public Customer changeKbiUseWithMin(int kbiUse, int _min){
        int _v2 = this.kbiUse + kbiUse;
        return setKbiUse(_v2 < _min  ? _min : _v2);
    }

    public Customer changeKbiUseWithMax(int kbiUse, int _max){
        int _v2 = this.kbiUse + kbiUse;
        return setKbiUse(_v2 > _max  ? _max : _v2);
    }

    public Customer changeKbiUseWithMinMax(int kbiUse, int _min, int _max){
        int _v2 = this.kbiUse + kbiUse;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbiUse(_v2 < _min  ? _min : _v2);
    }

    public String getEmail(){
        return email;
    }

    public Customer setEmail(String email){
        String _old = this.email;
        this.email = email;
        changeIt(Col.email, _old, email);
        return this;
    }

    public String getProvince(){
        return province;
    }

    public Customer setProvince(String province){
        String _old = this.province;
        this.province = province;
        changeIt(Col.province, _old, province);
        return this;
    }

    public String getCity(){
        return city;
    }

    public Customer setCity(String city){
        String _old = this.city;
        this.city = city;
        changeIt(Col.city, _old, city);
        return this;
    }

    public String getSeat(){
        return seat;
    }

    public Customer setSeat(String seat){
        String _old = this.seat;
        this.seat = seat;
        changeIt(Col.seat, _old, seat);
        return this;
    }

    public String getHeadIcon(){
        return headIcon;
    }

    public Customer setHeadIcon(String headIcon){
        String _old = this.headIcon;
        this.headIcon = headIcon;
        changeIt(Col.headIcon, _old, headIcon);
        return this;
    }

    public String getDescr(){
        return descr;
    }

    public Customer setDescr(String descr){
        String _old = this.descr;
        this.descr = descr;
        changeIt(Col.descr, _old, descr);
        return this;
    }

    public double getMoneyAll(){
        return moneyAll;
    }

    public Customer setMoneyAll(double moneyAll){
        double _old = this.moneyAll;
        this.moneyAll = moneyAll;
        changeIt(Col.moneyAll, _old, moneyAll);
        return this;
    }

    public Customer changeMoneyAll(double moneyAll){
        return setMoneyAll(this.moneyAll + moneyAll);
    }

    public Customer changeMoneyAllWithMin(double moneyAll, double _min){
        double _v2 = this.moneyAll + moneyAll;
        return setMoneyAll(_v2 < _min  ? _min : _v2);
    }

    public Customer changeMoneyAllWithMax(double moneyAll, double _max){
        double _v2 = this.moneyAll + moneyAll;
        return setMoneyAll(_v2 > _max  ? _max : _v2);
    }

    public Customer changeMoneyAllWithMinMax(double moneyAll, double _min, double _max){
        double _v2 = this.moneyAll + moneyAll;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyAll(_v2 < _min  ? _min : _v2);
    }

    public double getMoneyCur(){
        return moneyCur;
    }

    public Customer setMoneyCur(double moneyCur){
        double _old = this.moneyCur;
        this.moneyCur = moneyCur;
        changeIt(Col.moneyCur, _old, moneyCur);
        return this;
    }

    public Customer changeMoneyCur(double moneyCur){
        return setMoneyCur(this.moneyCur + moneyCur);
    }

    public Customer changeMoneyCurWithMin(double moneyCur, double _min){
        double _v2 = this.moneyCur + moneyCur;
        return setMoneyCur(_v2 < _min  ? _min : _v2);
    }

    public Customer changeMoneyCurWithMax(double moneyCur, double _max){
        double _v2 = this.moneyCur + moneyCur;
        return setMoneyCur(_v2 > _max  ? _max : _v2);
    }

    public Customer changeMoneyCurWithMinMax(double moneyCur, double _min, double _max){
        double _v2 = this.moneyCur + moneyCur;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyCur(_v2 < _min  ? _min : _v2);
    }

    public String getRecommendCode(){
        return recommendCode;
    }

    public Customer setRecommendCode(String recommendCode){
        String _old = this.recommendCode;
        this.recommendCode = recommendCode;
        changeIt(Col.recommendCode, _old, recommendCode);
        return this;
    }

    public String getAlipay(){
        return alipay;
    }

    public Customer setAlipay(String alipay){
        String _old = this.alipay;
        this.alipay = alipay;
        changeIt(Col.alipay, _old, alipay);
        return this;
    }

    public String getAlipayRealName(){
        return alipayRealName;
    }

    public Customer setAlipayRealName(String alipayRealName){
        String _old = this.alipayRealName;
        this.alipayRealName = alipayRealName;
        changeIt(Col.alipayRealName, _old, alipayRealName);
        return this;
    }

    public boolean getIsVerifyAlipay(){
        return isVerifyAlipay;
    }

    public Customer setIsVerifyAlipay(boolean isVerifyAlipay){
        boolean _old = this.isVerifyAlipay;
        this.isVerifyAlipay = isVerifyAlipay;
        changeIt(Col.isVerifyAlipay, _old, isVerifyAlipay);
        return this;
    }

    public String getBackAlipay(){
        return backAlipay;
    }

    public Customer setBackAlipay(String backAlipay){
        String _old = this.backAlipay;
        this.backAlipay = backAlipay;
        changeIt(Col.backAlipay, _old, backAlipay);
        return this;
    }

    public String getBackAlipayName(){
        return backAlipayName;
    }

    public Customer setBackAlipayName(String backAlipayName){
        String _old = this.backAlipayName;
        this.backAlipayName = backAlipayName;
        changeIt(Col.backAlipayName, _old, backAlipayName);
        return this;
    }

    public int compareTo(Customer v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Customer v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Customer newCustomer(Integer id, Integer accountid, String name, Integer kbiAll, Integer kbiUse, String email, String province, String city, String seat, String headIcon, String descr, Double moneyAll, Double moneyCur, String recommendCode, String alipay, String alipayRealName, Boolean isVerifyAlipay, String backAlipay, String backAlipayName) {
        Customer result = new Customer();
        result.id = id;
        result.accountid = accountid;
        result.name = name;
        result.kbiAll = kbiAll;
        result.kbiUse = kbiUse;
        result.email = email;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.headIcon = headIcon;
        result.descr = descr;
        result.moneyAll = moneyAll;
        result.moneyCur = moneyCur;
        result.recommendCode = recommendCode;
        result.alipay = alipay;
        result.alipayRealName = alipayRealName;
        result.isVerifyAlipay = isVerifyAlipay;
        result.backAlipay = backAlipay;
        result.backAlipayName = backAlipayName;
        return result;
    }

    public static Customer newCustomer(Customer customer) {
        Customer result = new Customer();
        result.id = customer.id;
        result.accountid = customer.accountid;
        result.name = customer.name;
        result.kbiAll = customer.kbiAll;
        result.kbiUse = customer.kbiUse;
        result.email = customer.email;
        result.province = customer.province;
        result.city = customer.city;
        result.seat = customer.seat;
        result.headIcon = customer.headIcon;
        result.descr = customer.descr;
        result.moneyAll = customer.moneyAll;
        result.moneyCur = customer.moneyCur;
        result.recommendCode = customer.recommendCode;
        result.alipay = customer.alipay;
        result.alipayRealName = customer.alipayRealName;
        result.isVerifyAlipay = customer.isVerifyAlipay;
        result.backAlipay = customer.backAlipay;
        result.backAlipayName = customer.backAlipayName;
        return result;
    }

    public Customer createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getCustomer(){
        Customer customer = null; // customer
        { // new and insert Customer (customer)
            int id = 0; 	// id
            int accountid = 0; 	// accountid
            String name = ""; 	// name
            int kbiAll = 0; 	// kbiAll
            int kbiUse = 0; 	// kbiUse
            String email = ""; 	// email
            String province = ""; 	// province
            String city = ""; 	// city
            String seat = ""; 	// seat
            String headIcon = ""; 	// headIcon
            String descr = ""; 	// descr
            double moneyAll = 0.0; 	// moneyAll
            double moneyCur = 0.0; 	// moneyCur
            String recommendCode = ""; 	// recommendCode
            String alipay = ""; 	// alipay
            String alipayRealName = ""; 	// alipayRealName
            boolean isVerifyAlipay = true; 	// isVerifyAlipay
            String backAlipay = ""; 	// backAlipay
            String backAlipayName = ""; 	// backAlipayName
            customer = Customer.newCustomer(id, accountid, name, kbiAll, kbiUse, email, province, city, seat, headIcon, descr, moneyAll, moneyCur, recommendCode, alipay, alipayRealName, isVerifyAlipay, backAlipay, backAlipayName);
        }
        customer = customer.insert();

        int id = customer.getId(); 	// id
        int accountid = customer.getAccountid(); 	// accountid
        String name = customer.getName(); 	// name
        int kbiAll = customer.getKbiAll(); 	// kbiAll
        int kbiUse = customer.getKbiUse(); 	// kbiUse
        String email = customer.getEmail(); 	// email
        String province = customer.getProvince(); 	// province
        String city = customer.getCity(); 	// city
        String seat = customer.getSeat(); 	// seat
        String headIcon = customer.getHeadIcon(); 	// headIcon
        String descr = customer.getDescr(); 	// descr
        double moneyAll = customer.getMoneyAll(); 	// moneyAll
        double moneyCur = customer.getMoneyCur(); 	// moneyCur
        String recommendCode = customer.getRecommendCode(); 	// recommendCode
        String alipay = customer.getAlipay(); 	// alipay
        String alipayRealName = customer.getAlipayRealName(); 	// alipayRealName
        boolean isVerifyAlipay = customer.getIsVerifyAlipay(); 	// isVerifyAlipay
        String backAlipay = customer.getBackAlipay(); 	// backAlipay
        String backAlipayName = customer.getBackAlipayName(); 	// backAlipayName
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
        case CEn.accountid:
            return accountid;
        case CEn.kbiAll:
            return kbiAll;
        case CEn.kbiUse:
            return kbiUse;
        }
        return 0;
    }

    public Customer setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Customer setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.accountid:
            return setAccountid(value2);
        case CEn.kbiAll:
            return setKbiAll(value2);
        case CEn.kbiUse:
            return setKbiUse(value2);
        }
        return this;
    }

    public Customer changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Customer changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.accountid:
            return changeAccountid(value2);
        case CEn.kbiAll:
            return changeKbiAll(value2);
        case CEn.kbiUse:
            return changeKbiUse(value2);
        }
        return this;
    }

    public Customer changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Customer changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.accountid:
            return changeAccountidWithMin(value2, _min);
        case CEn.kbiAll:
            return changeKbiAllWithMin(value2, _min);
        case CEn.kbiUse:
            return changeKbiUseWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.moneyAll:
            return moneyAll;
        case CEn.moneyCur:
            return moneyCur;
        }
        return 0;
    }

    public Customer setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Customer setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.moneyAll:
            return setMoneyAll(value2);
        case CEn.moneyCur:
            return setMoneyCur(value2);
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
        case CEn.email: 
            return email;
        case CEn.province: 
            return province;
        case CEn.city: 
            return city;
        case CEn.seat: 
            return seat;
        case CEn.headIcon: 
            return headIcon;
        case CEn.descr: 
            return descr;
        case CEn.recommendCode: 
            return recommendCode;
        case CEn.alipay: 
            return alipay;
        case CEn.alipayRealName: 
            return alipayRealName;
        case CEn.backAlipay: 
            return backAlipay;
        case CEn.backAlipayName: 
            return backAlipayName;
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
        case CEn.accountid:
            return accountid;
        case CEn.name:
            return name;
        case CEn.kbiAll:
            return kbiAll;
        case CEn.kbiUse:
            return kbiUse;
        case CEn.email:
            return email;
        case CEn.province:
            return province;
        case CEn.city:
            return city;
        case CEn.seat:
            return seat;
        case CEn.headIcon:
            return headIcon;
        case CEn.descr:
            return descr;
        case CEn.moneyAll:
            return moneyAll;
        case CEn.moneyCur:
            return moneyCur;
        case CEn.recommendCode:
            return recommendCode;
        case CEn.alipay:
            return alipay;
        case CEn.alipayRealName:
            return alipayRealName;
        case CEn.isVerifyAlipay:
            return isVerifyAlipay;
        case CEn.backAlipay:
            return backAlipay;
        case CEn.backAlipayName:
            return backAlipayName;
        }
        return null;
    }

    public Customer setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Customer setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Customer setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Customer setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.email:
            return setEmail(value2);
        case CEn.province:
            return setProvince(value2);
        case CEn.city:
            return setCity(value2);
        case CEn.seat:
            return setSeat(value2);
        case CEn.headIcon:
            return setHeadIcon(value2);
        case CEn.descr:
            return setDescr(value2);
        case CEn.recommendCode:
            return setRecommendCode(value2);
        case CEn.alipay:
            return setAlipay(value2);
        case CEn.alipayRealName:
            return setAlipayRealName(value2);
        case CEn.backAlipay:
            return setBackAlipay(value2);
        case CEn.backAlipayName:
            return setBackAlipayName(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Customer setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Customer setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Customer");
        result.put("id", id);
        result.put("accountid", accountid);
        result.put("name", name);
        result.put("kbiAll", kbiAll);
        result.put("kbiUse", kbiUse);
        result.put("email", email);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("headIcon", headIcon);
        result.put("descr", descr);
        result.put("moneyAll", moneyAll);
        result.put("moneyCur", moneyCur);
        result.put("recommendCode", recommendCode);
        result.put("alipay", alipay);
        result.put("alipayRealName", alipayRealName);
        result.put("isVerifyAlipay", isVerifyAlipay);
        result.put("backAlipay", backAlipay);
        result.put("backAlipayName", backAlipayName);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("accountid", accountid);
        result.put("name", name);
        result.put("kbiAll", kbiAll);
        result.put("kbiUse", kbiUse);
        result.put("email", email);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("headIcon", headIcon);
        result.put("descr", descr);
        result.put("moneyAll", moneyAll);
        result.put("moneyCur", moneyCur);
        result.put("recommendCode", recommendCode);
        result.put("alipay", alipay);
        result.put("alipayRealName", alipayRealName);
        result.put("isVerifyAlipay", isVerifyAlipay);
        result.put("backAlipay", backAlipay);
        result.put("backAlipayName", backAlipayName);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Customer");
        result.put("id", id);
        result.put("accountid", accountid);
        result.put("name", name);
        result.put("kbiAll", kbiAll);
        result.put("kbiUse", kbiUse);
        result.put("email", email);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("headIcon", headIcon);
        result.put("descr", descr);
        result.put("moneyAll", moneyAll);
        result.put("moneyCur", moneyCur);
        result.put("recommendCode", recommendCode);
        result.put("alipay", alipay);
        result.put("alipayRealName", alipayRealName);
        result.put("isVerifyAlipay", isVerifyAlipay);
        result.put("backAlipay", backAlipay);
        result.put("backAlipayName", backAlipayName);
        return result;
    }

    public Customer mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer accountid = MapEx.getInt(e, "accountid");
        String name = MapEx.getString(e, "name");
        Integer kbiAll = MapEx.getInt(e, "kbiAll");
        Integer kbiUse = MapEx.getInt(e, "kbiUse");
        String email = MapEx.getString(e, "email");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String headIcon = MapEx.getString(e, "headIcon");
        String descr = MapEx.getString(e, "descr");
        Double moneyAll = MapEx.getDouble(e, "moneyAll");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        String recommendCode = MapEx.getString(e, "recommendCode");
        String alipay = MapEx.getString(e, "alipay");
        String alipayRealName = MapEx.getString(e, "alipayRealName");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");
        String backAlipay = MapEx.getString(e, "backAlipay");
        String backAlipayName = MapEx.getString(e, "backAlipayName");

        if(id == null) id = 0;
        if(accountid == null) accountid = 0;
        if(name == null) name = "";
        if(kbiAll == null) kbiAll = 0;
        if(kbiUse == null) kbiUse = 0;
        if(email == null) email = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(headIcon == null) headIcon = "";
        if(descr == null) descr = "";
        if(moneyAll == null) moneyAll = 0.0;
        if(moneyCur == null) moneyCur = 0.0;
        if(recommendCode == null) recommendCode = "";
        if(alipay == null) alipay = "";
        if(alipayRealName == null) alipayRealName = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;
        if(backAlipay == null) backAlipay = "";
        if(backAlipayName == null) backAlipayName = "";

        setId(id);
        setAccountid(accountid);
        setName(name);
        setKbiAll(kbiAll);
        setKbiUse(kbiUse);
        setEmail(email);
        setProvince(province);
        setCity(city);
        setSeat(seat);
        setHeadIcon(headIcon);
        setDescr(descr);
        setMoneyAll(moneyAll);
        setMoneyCur(moneyCur);
        setRecommendCode(recommendCode);
        setAlipay(alipay);
        setAlipayRealName(alipayRealName);
        setIsVerifyAlipay(isVerifyAlipay);
        setBackAlipay(backAlipay);
        setBackAlipayName(backAlipayName);

        return this;
    }

    public static final Customer mapTo(Map e){
        Customer result = new Customer();

        Integer id = MapEx.getInt(e, "id");
        Integer accountid = MapEx.getInt(e, "accountid");
        String name = MapEx.getString(e, "name");
        Integer kbiAll = MapEx.getInt(e, "kbiAll");
        Integer kbiUse = MapEx.getInt(e, "kbiUse");
        String email = MapEx.getString(e, "email");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String headIcon = MapEx.getString(e, "headIcon");
        String descr = MapEx.getString(e, "descr");
        Double moneyAll = MapEx.getDouble(e, "moneyAll");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        String recommendCode = MapEx.getString(e, "recommendCode");
        String alipay = MapEx.getString(e, "alipay");
        String alipayRealName = MapEx.getString(e, "alipayRealName");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");
        String backAlipay = MapEx.getString(e, "backAlipay");
        String backAlipayName = MapEx.getString(e, "backAlipayName");

        if(id == null) id = 0;
        if(accountid == null) accountid = 0;
        if(name == null) name = "";
        if(kbiAll == null) kbiAll = 0;
        if(kbiUse == null) kbiUse = 0;
        if(email == null) email = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(headIcon == null) headIcon = "";
        if(descr == null) descr = "";
        if(moneyAll == null) moneyAll = 0.0;
        if(moneyCur == null) moneyCur = 0.0;
        if(recommendCode == null) recommendCode = "";
        if(alipay == null) alipay = "";
        if(alipayRealName == null) alipayRealName = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;
        if(backAlipay == null) backAlipay = "";
        if(backAlipayName == null) backAlipayName = "";

        result.id = id;
        result.accountid = accountid;
        result.name = name;
        result.kbiAll = kbiAll;
        result.kbiUse = kbiUse;
        result.email = email;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.headIcon = headIcon;
        result.descr = descr;
        result.moneyAll = moneyAll;
        result.moneyCur = moneyCur;
        result.recommendCode = recommendCode;
        result.alipay = alipay;
        result.alipayRealName = alipayRealName;
        result.isVerifyAlipay = isVerifyAlipay;
        result.backAlipay = backAlipay;
        result.backAlipayName = backAlipayName;

        return result;
    }

    public static final Customer originalTo(Map e){
        Customer result = new Customer();

        Integer id = MapEx.getInt(e, "id");
        Integer accountid = MapEx.getInt(e, "accountid");
        String name = MapEx.getString(e, "name");
        Integer kbiAll = MapEx.getInt(e, "kbiAll");
        Integer kbiUse = MapEx.getInt(e, "kbiUse");
        String email = MapEx.getString(e, "email");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String headIcon = MapEx.getString(e, "headIcon");
        String descr = MapEx.getString(e, "descr");
        Double moneyAll = MapEx.getDouble(e, "moneyAll");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        String recommendCode = MapEx.getString(e, "recommendCode");
        String alipay = MapEx.getString(e, "alipay");
        String alipayRealName = MapEx.getString(e, "alipayRealName");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");
        String backAlipay = MapEx.getString(e, "backAlipay");
        String backAlipayName = MapEx.getString(e, "backAlipayName");

        if(id == null) id = 0;
        if(accountid == null) accountid = 0;
        if(name == null) name = "";
        if(kbiAll == null) kbiAll = 0;
        if(kbiUse == null) kbiUse = 0;
        if(email == null) email = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(headIcon == null) headIcon = "";
        if(descr == null) descr = "";
        if(moneyAll == null) moneyAll = 0.0;
        if(moneyCur == null) moneyCur = 0.0;
        if(recommendCode == null) recommendCode = "";
        if(alipay == null) alipay = "";
        if(alipayRealName == null) alipayRealName = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;
        if(backAlipay == null) backAlipay = "";
        if(backAlipayName == null) backAlipayName = "";

        result.id = id;
        result.accountid = accountid;
        result.name = name;
        result.kbiAll = kbiAll;
        result.kbiUse = kbiUse;
        result.email = email;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.headIcon = headIcon;
        result.descr = descr;
        result.moneyAll = moneyAll;
        result.moneyCur = moneyCur;
        result.recommendCode = recommendCode;
        result.alipay = alipay;
        result.alipayRealName = alipayRealName;
        result.isVerifyAlipay = isVerifyAlipay;
        result.backAlipay = backAlipay;
        result.backAlipayName = backAlipayName;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Customer createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 19);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "accountid", accountid);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "kbiAll", kbiAll);
            writeMapEntry(out, "kbiUse", kbiUse);
            writeMapEntry(out, "email", email);
            writeMapEntry(out, "province", province);
            writeMapEntry(out, "city", city);
            writeMapEntry(out, "seat", seat);
            writeMapEntry(out, "headIcon", headIcon);
            writeMapEntry(out, "descr", descr);
            writeMapEntry(out, "moneyAll", moneyAll);
            writeMapEntry(out, "moneyCur", moneyCur);
            writeMapEntry(out, "recommendCode", recommendCode);
            writeMapEntry(out, "alipay", alipay);
            writeMapEntry(out, "alipayRealName", alipayRealName);
            writeMapEntry(out, "isVerifyAlipay", isVerifyAlipay);
            writeMapEntry(out, "backAlipay", backAlipay);
            writeMapEntry(out, "backAlipayName", backAlipayName);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Customer createFor(byte[] b) throws Exception {
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
    public final Account getAccountFkAccountid() { // account - accountid
        return AccountEntity.getByKey(accountid);
    }

    public final int countAccountFkAccountid() { // account - accountid
        return getAccountFkAccountid() == null ? 0 : 1;
    }

    public final List<Appraise> getAppraisesFkCustomerid() { // appraise - customerid
        return AppraiseEntity.getByCustomerid(id);
    }

    public final int countAppraisesFkCustomerid() { // appraise - customerid
        return AppraiseEntity.countByCustomerid(id);
    }

    public final List<Boughtkinds> getBoughtkindssFkCustomerid() { // boughtkinds - customerid
        return BoughtkindsEntity.getByCustomerid(id);
    }

    public final int countBoughtkindssFkCustomerid() { // boughtkinds - customerid
        return BoughtkindsEntity.countByCustomerid(id);
    }

    public final List<Errorfeedback> getErrorfeedbacksFkCustomerid() { // errorfeedback - customerid
        return ErrorfeedbackEntity.getByCustomerid(id);
    }

    public final int countErrorfeedbacksFkCustomerid() { // errorfeedback - customerid
        return ErrorfeedbackEntity.countByCustomerid(id);
    }

    public final List<Openkind4customer> getOpenkind4customersFkCustomerid() { // openkind4customer - customerid
        return Openkind4customerEntity.getByCustomerid(id);
    }

    public final int countOpenkind4customersFkCustomerid() { // openkind4customer - customerid
        return Openkind4customerEntity.countByCustomerid(id);
    }

    public final List<Orders4profit> getOrders4profitsFkCustid() { // orders4profit - custid
        return Orders4profitEntity.getByCustid(id);
    }

    public final int countOrders4profitsFkCustid() { // orders4profit - custid
        return Orders4profitEntity.countByCustid(id);
    }

    public final List<Recordques4exam> getRecordques4examsFkCustomid() { // recordques4exam - customid
        return Recordques4examEntity.getByCustomid(id);
    }

    public final int countRecordques4examsFkCustomid() { // recordques4exam - customid
        return Recordques4examEntity.countByCustomid(id);
    }

    public static final Customer loadByKey(int id) {
        return CustomerEntity.getByKey(id);
    }

    public static final Future<Customer> asyncByKey(int id) {
        return CustomerEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Customer insert() {
        Customer result = CustomerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Customer asyncInsert() {
        Customer result = CustomerEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Customer insert2() {
        return CustomerEntity.insert2(this);
    }

    public final Customer asyncInsert2() {
        Customer result = CustomerEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Customer update() {
        return CustomerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        CustomerEntity.asyncUpdate( this );
        return true;
    }

    public final Customer insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return CustomerEntity.delete(id);
    }

    public final void asyncDelete() {
        CustomerEntity.asyncDelete(id);
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

    public Customer clone2() {
        try{
            return (Customer) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
