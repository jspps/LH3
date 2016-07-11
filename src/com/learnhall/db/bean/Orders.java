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

//learnhall3_design - orders
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Orders extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1049801999; // com.learnhall.db.bean.Orders

    public static String TABLENAME = "orders";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String type = "type"; public static final String name = "name"; public static final String extra_param = "extra_param"; public static final String makerid = "makerid"; public static final String price = "price"; public static final String kbi = "kbi"; public static final String createtime = "createtime"; public static final String status = "status"; public static final String statusProcess = "statusProcess"; public static final String nmMaker = "nmMaker"; public static final String discount = "discount"; public static final String recommendCode = "recommendCode"; public static final String lasttime = "lasttime"; public static final String orderNo = "orderNo"; public static final String realprice = "realprice";  }
    public static final class CEn { public static final String id = "id"; public static final String type = "type"; public static final String name = "name"; public static final String extra_param = "extra_param"; public static final String makerid = "makerid"; public static final String price = "price"; public static final String kbi = "kbi"; public static final String createtime = "createtime"; public static final String status = "status"; public static final String statusProcess = "statusProcess"; public static final String nmMaker = "nmMaker"; public static final String discount = "discount"; public static final String recommendCode = "recommendCode"; public static final String lasttime = "lasttime"; public static final String orderNo = "orderNo"; public static final String realprice = "realprice";  }
    public static final String[] carrays ={"id", "type", "name", "extra_param", "makerid", "price", "kbi", "createtime", "status", "statusProcess", "nmMaker", "discount", "recommendCode", "lasttime", "orderNo", "realprice"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "TINYTEXT", "INT", "DOUBLE", "INT", "DATETIME", "INT", "INT", "TINYTEXT", "DOUBLE", "VARCHAR", "DATETIME", "VARCHAR", "DOUBLE"};


    public int id;
    public int type;
    public String name;
    public String extra_param;
    public int makerid;
    public double price;
    public int kbi;
    public java.util.Date createtime;
    public int status;
    public int statusProcess;
    public String nmMaker;
    public double discount;
    public String recommendCode;
    public java.util.Date lasttime;
    public String orderNo;
    public double realprice;

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

    public Orders setId(int id){
        this.id = id;
        return this;
    }

    public int getType(){
        return type;
    }

    public Orders setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Orders changeType(int type){
        return setType(this.type + type);
    }

    public Orders changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Orders changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Orders changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public String getName(){
        return name;
    }

    public Orders setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public String getExtra_param(){
        return extra_param;
    }

    public Orders setExtra_param(String extra_param){
        String _old = this.extra_param;
        this.extra_param = extra_param;
        changeIt(Col.extra_param, _old, extra_param);
        return this;
    }

    public int getMakerid(){
        return makerid;
    }

    public Orders setMakerid(int makerid){
        int _old = this.makerid;
        this.makerid = makerid;
        changeIt(Col.makerid, _old, makerid);
        return this;
    }

    public Orders changeMakerid(int makerid){
        return setMakerid(this.makerid + makerid);
    }

    public Orders changeMakeridWithMin(int makerid, int _min){
        int _v2 = this.makerid + makerid;
        return setMakerid(_v2 < _min  ? _min : _v2);
    }

    public Orders changeMakeridWithMax(int makerid, int _max){
        int _v2 = this.makerid + makerid;
        return setMakerid(_v2 > _max  ? _max : _v2);
    }

    public Orders changeMakeridWithMinMax(int makerid, int _min, int _max){
        int _v2 = this.makerid + makerid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMakerid(_v2 < _min  ? _min : _v2);
    }

    public double getPrice(){
        return price;
    }

    public Orders setPrice(double price){
        double _old = this.price;
        this.price = price;
        changeIt(Col.price, _old, price);
        return this;
    }

    public Orders changePrice(double price){
        return setPrice(this.price + price);
    }

    public Orders changePriceWithMin(double price, double _min){
        double _v2 = this.price + price;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public Orders changePriceWithMax(double price, double _max){
        double _v2 = this.price + price;
        return setPrice(_v2 > _max  ? _max : _v2);
    }

    public Orders changePriceWithMinMax(double price, double _min, double _max){
        double _v2 = this.price + price;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public int getKbi(){
        return kbi;
    }

    public Orders setKbi(int kbi){
        int _old = this.kbi;
        this.kbi = kbi;
        changeIt(Col.kbi, _old, kbi);
        return this;
    }

    public Orders changeKbi(int kbi){
        return setKbi(this.kbi + kbi);
    }

    public Orders changeKbiWithMin(int kbi, int _min){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public Orders changeKbiWithMax(int kbi, int _max){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 > _max  ? _max : _v2);
    }

    public Orders changeKbiWithMinMax(int kbi, int _min, int _max){
        int _v2 = this.kbi + kbi;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Orders setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Orders setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Orders changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Orders changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Orders changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Orders changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int getStatusProcess(){
        return statusProcess;
    }

    public Orders setStatusProcess(int statusProcess){
        int _old = this.statusProcess;
        this.statusProcess = statusProcess;
        changeIt(Col.statusProcess, _old, statusProcess);
        return this;
    }

    public Orders changeStatusProcess(int statusProcess){
        return setStatusProcess(this.statusProcess + statusProcess);
    }

    public Orders changeStatusProcessWithMin(int statusProcess, int _min){
        int _v2 = this.statusProcess + statusProcess;
        return setStatusProcess(_v2 < _min  ? _min : _v2);
    }

    public Orders changeStatusProcessWithMax(int statusProcess, int _max){
        int _v2 = this.statusProcess + statusProcess;
        return setStatusProcess(_v2 > _max  ? _max : _v2);
    }

    public Orders changeStatusProcessWithMinMax(int statusProcess, int _min, int _max){
        int _v2 = this.statusProcess + statusProcess;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatusProcess(_v2 < _min  ? _min : _v2);
    }

    public String getNmMaker(){
        return nmMaker;
    }

    public Orders setNmMaker(String nmMaker){
        String _old = this.nmMaker;
        this.nmMaker = nmMaker;
        changeIt(Col.nmMaker, _old, nmMaker);
        return this;
    }

    public double getDiscount(){
        return discount;
    }

    public Orders setDiscount(double discount){
        double _old = this.discount;
        this.discount = discount;
        changeIt(Col.discount, _old, discount);
        return this;
    }

    public Orders changeDiscount(double discount){
        return setDiscount(this.discount + discount);
    }

    public Orders changeDiscountWithMin(double discount, double _min){
        double _v2 = this.discount + discount;
        return setDiscount(_v2 < _min  ? _min : _v2);
    }

    public Orders changeDiscountWithMax(double discount, double _max){
        double _v2 = this.discount + discount;
        return setDiscount(_v2 > _max  ? _max : _v2);
    }

    public Orders changeDiscountWithMinMax(double discount, double _min, double _max){
        double _v2 = this.discount + discount;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDiscount(_v2 < _min  ? _min : _v2);
    }

    public String getRecommendCode(){
        return recommendCode;
    }

    public Orders setRecommendCode(String recommendCode){
        String _old = this.recommendCode;
        this.recommendCode = recommendCode;
        changeIt(Col.recommendCode, _old, recommendCode);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Orders setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public String getOrderNo(){
        return orderNo;
    }

    public Orders setOrderNo(String orderNo){
        String _old = this.orderNo;
        this.orderNo = orderNo;
        changeIt(Col.orderNo, _old, orderNo);
        return this;
    }

    public double getRealprice(){
        return realprice;
    }

    public Orders setRealprice(double realprice){
        double _old = this.realprice;
        this.realprice = realprice;
        changeIt(Col.realprice, _old, realprice);
        return this;
    }

    public Orders changeRealprice(double realprice){
        return setRealprice(this.realprice + realprice);
    }

    public Orders changeRealpriceWithMin(double realprice, double _min){
        double _v2 = this.realprice + realprice;
        return setRealprice(_v2 < _min  ? _min : _v2);
    }

    public Orders changeRealpriceWithMax(double realprice, double _max){
        double _v2 = this.realprice + realprice;
        return setRealprice(_v2 > _max  ? _max : _v2);
    }

    public Orders changeRealpriceWithMinMax(double realprice, double _min, double _max){
        double _v2 = this.realprice + realprice;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRealprice(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Orders v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Orders v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Orders newOrders(Integer id, Integer type, String name, String extra_param, Integer makerid, Double price, Integer kbi, java.util.Date createtime, Integer status, Integer statusProcess, String nmMaker, Double discount, String recommendCode, java.util.Date lasttime, String orderNo, Double realprice) {
        Orders result = new Orders();
        result.id = id;
        result.type = type;
        result.name = name;
        result.extra_param = extra_param;
        result.makerid = makerid;
        result.price = price;
        result.kbi = kbi;
        result.createtime = createtime;
        result.status = status;
        result.statusProcess = statusProcess;
        result.nmMaker = nmMaker;
        result.discount = discount;
        result.recommendCode = recommendCode;
        result.lasttime = lasttime;
        result.orderNo = orderNo;
        result.realprice = realprice;
        return result;
    }

    public static Orders newOrders(Orders orders) {
        Orders result = new Orders();
        result.id = orders.id;
        result.type = orders.type;
        result.name = orders.name;
        result.extra_param = orders.extra_param;
        result.makerid = orders.makerid;
        result.price = orders.price;
        result.kbi = orders.kbi;
        result.createtime = orders.createtime;
        result.status = orders.status;
        result.statusProcess = orders.statusProcess;
        result.nmMaker = orders.nmMaker;
        result.discount = orders.discount;
        result.recommendCode = orders.recommendCode;
        result.lasttime = orders.lasttime;
        result.orderNo = orders.orderNo;
        result.realprice = orders.realprice;
        return result;
    }

    public Orders createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getOrders(){
        Orders orders = null; // orders
        { // new and insert Orders (orders)
            int id = 0; 	// id
            int type = 0; 	// type
            String name = ""; 	// name
            String extra_param = ""; 	// extra_param
            int makerid = 0; 	// makerid
            double price = 0.0; 	// price
            int kbi = 0; 	// kbi
            Date createtime = new Date(); 	// createtime
            int status = 0; 	// status
            int statusProcess = 0; 	// statusProcess
            String nmMaker = ""; 	// nmMaker
            double discount = 0.0; 	// discount
            String recommendCode = ""; 	// recommendCode
            Date lasttime = new Date(); 	// lasttime
            String orderNo = ""; 	// orderNo
            double realprice = 0.0; 	// realprice
            orders = Orders.newOrders(id, type, name, extra_param, makerid, price, kbi, createtime, status, statusProcess, nmMaker, discount, recommendCode, lasttime, orderNo, realprice);
        }
        orders = orders.insert();

        int id = orders.getId(); 	// id
        int type = orders.getType(); 	// type
        String name = orders.getName(); 	// name
        String extra_param = orders.getExtra_param(); 	// extra_param
        int makerid = orders.getMakerid(); 	// makerid
        double price = orders.getPrice(); 	// price
        int kbi = orders.getKbi(); 	// kbi
        Date createtime = orders.getCreatetime(); 	// createtime
        int status = orders.getStatus(); 	// status
        int statusProcess = orders.getStatusProcess(); 	// statusProcess
        String nmMaker = orders.getNmMaker(); 	// nmMaker
        double discount = orders.getDiscount(); 	// discount
        String recommendCode = orders.getRecommendCode(); 	// recommendCode
        Date lasttime = orders.getLasttime(); 	// lasttime
        String orderNo = orders.getOrderNo(); 	// orderNo
        double realprice = orders.getRealprice(); 	// realprice
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
        case CEn.type:
            return type;
        case CEn.makerid:
            return makerid;
        case CEn.kbi:
            return kbi;
        case CEn.status:
            return status;
        case CEn.statusProcess:
            return statusProcess;
        }
        return 0;
    }

    public Orders setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Orders setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.type:
            return setType(value2);
        case CEn.makerid:
            return setMakerid(value2);
        case CEn.kbi:
            return setKbi(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.statusProcess:
            return setStatusProcess(value2);
        }
        return this;
    }

    public Orders changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Orders changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.type:
            return changeType(value2);
        case CEn.makerid:
            return changeMakerid(value2);
        case CEn.kbi:
            return changeKbi(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.statusProcess:
            return changeStatusProcess(value2);
        }
        return this;
    }

    public Orders changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Orders changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.makerid:
            return changeMakeridWithMin(value2, _min);
        case CEn.kbi:
            return changeKbiWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.statusProcess:
            return changeStatusProcessWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.price:
            return price;
        case CEn.discount:
            return discount;
        case CEn.realprice:
            return realprice;
        }
        return 0;
    }

    public Orders setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Orders setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.price:
            return setPrice(value2);
        case CEn.discount:
            return setDiscount(value2);
        case CEn.realprice:
            return setRealprice(value2);
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
        case CEn.extra_param: 
            return extra_param;
        case CEn.nmMaker: 
            return nmMaker;
        case CEn.recommendCode: 
            return recommendCode;
        case CEn.orderNo: 
            return orderNo;
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
        case CEn.type:
            return type;
        case CEn.name:
            return name;
        case CEn.extra_param:
            return extra_param;
        case CEn.makerid:
            return makerid;
        case CEn.price:
            return price;
        case CEn.kbi:
            return kbi;
        case CEn.createtime:
            return createtime;
        case CEn.status:
            return status;
        case CEn.statusProcess:
            return statusProcess;
        case CEn.nmMaker:
            return nmMaker;
        case CEn.discount:
            return discount;
        case CEn.recommendCode:
            return recommendCode;
        case CEn.lasttime:
            return lasttime;
        case CEn.orderNo:
            return orderNo;
        case CEn.realprice:
            return realprice;
        }
        return null;
    }

    public Orders setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Orders setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Orders setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Orders setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.extra_param:
            return setExtra_param(value2);
        case CEn.nmMaker:
            return setNmMaker(value2);
        case CEn.recommendCode:
            return setRecommendCode(value2);
        case CEn.orderNo:
            return setOrderNo(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Orders setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Orders setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Orders");
        result.put("id", id);
        result.put("type", type);
        result.put("name", name);
        result.put("extra_param", extra_param);
        result.put("makerid", makerid);
        result.put("price", price);
        result.put("kbi", kbi);
        result.put("createtime", createtime);
        result.put("status", status);
        result.put("statusProcess", statusProcess);
        result.put("nmMaker", nmMaker);
        result.put("discount", discount);
        result.put("recommendCode", recommendCode);
        result.put("lasttime", lasttime);
        result.put("orderNo", orderNo);
        result.put("realprice", realprice);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("type", type);
        result.put("name", name);
        result.put("extra_param", extra_param);
        result.put("makerid", makerid);
        result.put("price", price);
        result.put("kbi", kbi);
        result.put("createtime", createtime);
        result.put("status", status);
        result.put("statusProcess", statusProcess);
        result.put("nmMaker", nmMaker);
        result.put("discount", discount);
        result.put("recommendCode", recommendCode);
        result.put("lasttime", lasttime);
        result.put("orderNo", orderNo);
        result.put("realprice", realprice);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Orders");
        result.put("id", id);
        result.put("type", type);
        result.put("name", name);
        result.put("extra_param", extra_param);
        result.put("makerid", makerid);
        result.put("price", price);
        result.put("kbi", kbi);
        result.put("createtime", createtime);
        result.put("status", status);
        result.put("statusProcess", statusProcess);
        result.put("nmMaker", nmMaker);
        result.put("discount", discount);
        result.put("recommendCode", recommendCode);
        result.put("lasttime", lasttime);
        result.put("orderNo", orderNo);
        result.put("realprice", realprice);
        return result;
    }

    public Orders mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        String name = MapEx.getString(e, "name");
        String extra_param = MapEx.getString(e, "extra_param");
        Integer makerid = MapEx.getInt(e, "makerid");
        Double price = MapEx.getDouble(e, "price");
        Integer kbi = MapEx.getInt(e, "kbi");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status = MapEx.getInt(e, "status");
        Integer statusProcess = MapEx.getInt(e, "statusProcess");
        String nmMaker = MapEx.getString(e, "nmMaker");
        Double discount = MapEx.getDouble(e, "discount");
        String recommendCode = MapEx.getString(e, "recommendCode");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        String orderNo = MapEx.getString(e, "orderNo");
        Double realprice = MapEx.getDouble(e, "realprice");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(name == null) name = "";
        if(extra_param == null) extra_param = "";
        if(makerid == null) makerid = 0;
        if(price == null) price = 0.0;
        if(kbi == null) kbi = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(status == null) status = 0;
        if(statusProcess == null) statusProcess = 0;
        if(nmMaker == null) nmMaker = "";
        if(discount == null) discount = 0.0;
        if(recommendCode == null) recommendCode = "";
        if(lasttime == null) lasttime = new java.util.Date();
        if(orderNo == null) orderNo = "";
        if(realprice == null) realprice = 0.0;

        setId(id);
        setType(type);
        setName(name);
        setExtra_param(extra_param);
        setMakerid(makerid);
        setPrice(price);
        setKbi(kbi);
        setCreatetime(createtime);
        setStatus(status);
        setStatusProcess(statusProcess);
        setNmMaker(nmMaker);
        setDiscount(discount);
        setRecommendCode(recommendCode);
        setLasttime(lasttime);
        setOrderNo(orderNo);
        setRealprice(realprice);

        return this;
    }

    public static final Orders mapTo(Map e){
        Orders result = new Orders();

        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        String name = MapEx.getString(e, "name");
        String extra_param = MapEx.getString(e, "extra_param");
        Integer makerid = MapEx.getInt(e, "makerid");
        Double price = MapEx.getDouble(e, "price");
        Integer kbi = MapEx.getInt(e, "kbi");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status = MapEx.getInt(e, "status");
        Integer statusProcess = MapEx.getInt(e, "statusProcess");
        String nmMaker = MapEx.getString(e, "nmMaker");
        Double discount = MapEx.getDouble(e, "discount");
        String recommendCode = MapEx.getString(e, "recommendCode");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        String orderNo = MapEx.getString(e, "orderNo");
        Double realprice = MapEx.getDouble(e, "realprice");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(name == null) name = "";
        if(extra_param == null) extra_param = "";
        if(makerid == null) makerid = 0;
        if(price == null) price = 0.0;
        if(kbi == null) kbi = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(status == null) status = 0;
        if(statusProcess == null) statusProcess = 0;
        if(nmMaker == null) nmMaker = "";
        if(discount == null) discount = 0.0;
        if(recommendCode == null) recommendCode = "";
        if(lasttime == null) lasttime = new java.util.Date();
        if(orderNo == null) orderNo = "";
        if(realprice == null) realprice = 0.0;

        result.id = id;
        result.type = type;
        result.name = name;
        result.extra_param = extra_param;
        result.makerid = makerid;
        result.price = price;
        result.kbi = kbi;
        result.createtime = createtime;
        result.status = status;
        result.statusProcess = statusProcess;
        result.nmMaker = nmMaker;
        result.discount = discount;
        result.recommendCode = recommendCode;
        result.lasttime = lasttime;
        result.orderNo = orderNo;
        result.realprice = realprice;

        return result;
    }

    public static final Orders originalTo(Map e){
        Orders result = new Orders();

        Integer id = MapEx.getInt(e, "id");
        Integer type = MapEx.getInt(e, "type");
        String name = MapEx.getString(e, "name");
        String extra_param = MapEx.getString(e, "extra_param");
        Integer makerid = MapEx.getInt(e, "makerid");
        Double price = MapEx.getDouble(e, "price");
        Integer kbi = MapEx.getInt(e, "kbi");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Integer status = MapEx.getInt(e, "status");
        Integer statusProcess = MapEx.getInt(e, "statusProcess");
        String nmMaker = MapEx.getString(e, "nmMaker");
        Double discount = MapEx.getDouble(e, "discount");
        String recommendCode = MapEx.getString(e, "recommendCode");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        String orderNo = MapEx.getString(e, "orderNo");
        Double realprice = MapEx.getDouble(e, "realprice");

        if(id == null) id = 0;
        if(type == null) type = 0;
        if(name == null) name = "";
        if(extra_param == null) extra_param = "";
        if(makerid == null) makerid = 0;
        if(price == null) price = 0.0;
        if(kbi == null) kbi = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(status == null) status = 0;
        if(statusProcess == null) statusProcess = 0;
        if(nmMaker == null) nmMaker = "";
        if(discount == null) discount = 0.0;
        if(recommendCode == null) recommendCode = "";
        if(lasttime == null) lasttime = new java.util.Date();
        if(orderNo == null) orderNo = "";
        if(realprice == null) realprice = 0.0;

        result.id = id;
        result.type = type;
        result.name = name;
        result.extra_param = extra_param;
        result.makerid = makerid;
        result.price = price;
        result.kbi = kbi;
        result.createtime = createtime;
        result.status = status;
        result.statusProcess = statusProcess;
        result.nmMaker = nmMaker;
        result.discount = discount;
        result.recommendCode = recommendCode;
        result.lasttime = lasttime;
        result.orderNo = orderNo;
        result.realprice = realprice;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Orders createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 16);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "extra_param", extra_param);
            writeMapEntry(out, "makerid", makerid);
            writeMapEntry(out, "price", price);
            writeMapEntry(out, "kbi", kbi);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "statusProcess", statusProcess);
            writeMapEntry(out, "nmMaker", nmMaker);
            writeMapEntry(out, "discount", discount);
            writeMapEntry(out, "recommendCode", recommendCode);
            writeMapEntry(out, "lasttime", lasttime);
            writeMapEntry(out, "orderNo", orderNo);
            writeMapEntry(out, "realprice", realprice);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Orders createFor(byte[] b) throws Exception {
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
    public static final Orders loadByKey(int id) {
        return OrdersEntity.getByKey(id);
    }

    public static final Future<Orders> asyncByKey(int id) {
        return OrdersEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Orders insert() {
        Orders result = OrdersEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Orders asyncInsert() {
        Orders result = OrdersEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Orders insert2() {
        return OrdersEntity.insert2(this);
    }

    public final Orders asyncInsert2() {
        Orders result = OrdersEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Orders update() {
        return OrdersEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        OrdersEntity.asyncUpdate( this );
        return true;
    }

    public final Orders insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return OrdersEntity.delete(id);
    }

    public final void asyncDelete() {
        OrdersEntity.asyncDelete(id);
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

    public Orders clone2() {
        try{
            return (Orders) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
