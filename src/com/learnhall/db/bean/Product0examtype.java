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

//learnhall3_design - product0examtype
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Product0examtype extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1176537350; // com.learnhall.db.bean.Product0examtype

    public static String TABLENAME = "product0examtype";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String lhubid = "lhubid"; public static final String productid = "productid"; public static final String examtypeid = "examtypeid"; public static final String buymoney = "buymoney"; public static final String friend = "friend"; public static final String kbi = "kbi"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String lhubid = "lhubid"; public static final String productid = "productid"; public static final String examtypeid = "examtypeid"; public static final String buymoney = "buymoney"; public static final String friend = "friend"; public static final String kbi = "kbi"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "lhubid", "productid", "examtypeid", "buymoney", "friend", "kbi", "status", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "DOUBLE", "INT", "INT", "INT", "DATETIME"};


    public int id;
    public int lhubid;
    public int productid;
    public int examtypeid;
    public double buymoney;
    public int friend;
    public int kbi;
    public int status;
    public java.util.Date createtime;

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

    public Product0examtype setId(int id){
        this.id = id;
        return this;
    }

    public int getLhubid(){
        return lhubid;
    }

    public Product0examtype setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Product0examtype changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Product0examtype changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getProductid(){
        return productid;
    }

    public Product0examtype setProductid(int productid){
        int _old = this.productid;
        this.productid = productid;
        changeIt(Col.productid, _old, productid);
        return this;
    }

    public Product0examtype changeProductid(int productid){
        return setProductid(this.productid + productid);
    }

    public Product0examtype changeProductidWithMin(int productid, int _min){
        int _v2 = this.productid + productid;
        return setProductid(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeProductidWithMax(int productid, int _max){
        int _v2 = this.productid + productid;
        return setProductid(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeProductidWithMinMax(int productid, int _min, int _max){
        int _v2 = this.productid + productid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProductid(_v2 < _min  ? _min : _v2);
    }

    public int getExamtypeid(){
        return examtypeid;
    }

    public Product0examtype setExamtypeid(int examtypeid){
        int _old = this.examtypeid;
        this.examtypeid = examtypeid;
        changeIt(Col.examtypeid, _old, examtypeid);
        return this;
    }

    public Product0examtype changeExamtypeid(int examtypeid){
        return setExamtypeid(this.examtypeid + examtypeid);
    }

    public Product0examtype changeExamtypeidWithMin(int examtypeid, int _min){
        int _v2 = this.examtypeid + examtypeid;
        return setExamtypeid(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeExamtypeidWithMax(int examtypeid, int _max){
        int _v2 = this.examtypeid + examtypeid;
        return setExamtypeid(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeExamtypeidWithMinMax(int examtypeid, int _min, int _max){
        int _v2 = this.examtypeid + examtypeid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamtypeid(_v2 < _min  ? _min : _v2);
    }

    public double getBuymoney(){
        return buymoney;
    }

    public Product0examtype setBuymoney(double buymoney){
        double _old = this.buymoney;
        this.buymoney = buymoney;
        changeIt(Col.buymoney, _old, buymoney);
        return this;
    }

    public Product0examtype changeBuymoney(double buymoney){
        return setBuymoney(this.buymoney + buymoney);
    }

    public Product0examtype changeBuymoneyWithMin(double buymoney, double _min){
        double _v2 = this.buymoney + buymoney;
        return setBuymoney(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeBuymoneyWithMax(double buymoney, double _max){
        double _v2 = this.buymoney + buymoney;
        return setBuymoney(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeBuymoneyWithMinMax(double buymoney, double _min, double _max){
        double _v2 = this.buymoney + buymoney;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBuymoney(_v2 < _min  ? _min : _v2);
    }

    public int getFriend(){
        return friend;
    }

    public Product0examtype setFriend(int friend){
        int _old = this.friend;
        this.friend = friend;
        changeIt(Col.friend, _old, friend);
        return this;
    }

    public Product0examtype changeFriend(int friend){
        return setFriend(this.friend + friend);
    }

    public Product0examtype changeFriendWithMin(int friend, int _min){
        int _v2 = this.friend + friend;
        return setFriend(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeFriendWithMax(int friend, int _max){
        int _v2 = this.friend + friend;
        return setFriend(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeFriendWithMinMax(int friend, int _min, int _max){
        int _v2 = this.friend + friend;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFriend(_v2 < _min  ? _min : _v2);
    }

    public int getKbi(){
        return kbi;
    }

    public Product0examtype setKbi(int kbi){
        int _old = this.kbi;
        this.kbi = kbi;
        changeIt(Col.kbi, _old, kbi);
        return this;
    }

    public Product0examtype changeKbi(int kbi){
        return setKbi(this.kbi + kbi);
    }

    public Product0examtype changeKbiWithMin(int kbi, int _min){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeKbiWithMax(int kbi, int _max){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeKbiWithMinMax(int kbi, int _min, int _max){
        int _v2 = this.kbi + kbi;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Product0examtype setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Product0examtype changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Product0examtype changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Product0examtype changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Product0examtype changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Product0examtype setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Product0examtype v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Product0examtype v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Product0examtype newProduct0examtype(Integer id, Integer lhubid, Integer productid, Integer examtypeid, Double buymoney, Integer friend, Integer kbi, Integer status, java.util.Date createtime) {
        Product0examtype result = new Product0examtype();
        result.id = id;
        result.lhubid = lhubid;
        result.productid = productid;
        result.examtypeid = examtypeid;
        result.buymoney = buymoney;
        result.friend = friend;
        result.kbi = kbi;
        result.status = status;
        result.createtime = createtime;
        return result;
    }

    public static Product0examtype newProduct0examtype(Product0examtype product0examtype) {
        Product0examtype result = new Product0examtype();
        result.id = product0examtype.id;
        result.lhubid = product0examtype.lhubid;
        result.productid = product0examtype.productid;
        result.examtypeid = product0examtype.examtypeid;
        result.buymoney = product0examtype.buymoney;
        result.friend = product0examtype.friend;
        result.kbi = product0examtype.kbi;
        result.status = product0examtype.status;
        result.createtime = product0examtype.createtime;
        return result;
    }

    public Product0examtype createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getProduct0examtype(){
        Product0examtype product0examtype = null; // product0examtype
        { // new and insert Product0examtype (product0examtype)
            int id = 0; 	// id
            int lhubid = 0; 	// lhubid
            int productid = 0; 	// productid
            int examtypeid = 0; 	// examtypeid
            double buymoney = 0.0; 	// buymoney
            int friend = 0; 	// friend
            int kbi = 0; 	// kbi
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            product0examtype = Product0examtype.newProduct0examtype(id, lhubid, productid, examtypeid, buymoney, friend, kbi, status, createtime);
        }
        product0examtype = product0examtype.insert();

        int id = product0examtype.getId(); 	// id
        int lhubid = product0examtype.getLhubid(); 	// lhubid
        int productid = product0examtype.getProductid(); 	// productid
        int examtypeid = product0examtype.getExamtypeid(); 	// examtypeid
        double buymoney = product0examtype.getBuymoney(); 	// buymoney
        int friend = product0examtype.getFriend(); 	// friend
        int kbi = product0examtype.getKbi(); 	// kbi
        int status = product0examtype.getStatus(); 	// status
        Date createtime = product0examtype.getCreatetime(); 	// createtime
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
        case CEn.lhubid:
            return lhubid;
        case CEn.productid:
            return productid;
        case CEn.examtypeid:
            return examtypeid;
        case CEn.friend:
            return friend;
        case CEn.kbi:
            return kbi;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Product0examtype setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Product0examtype setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.productid:
            return setProductid(value2);
        case CEn.examtypeid:
            return setExamtypeid(value2);
        case CEn.friend:
            return setFriend(value2);
        case CEn.kbi:
            return setKbi(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Product0examtype changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Product0examtype changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.productid:
            return changeProductid(value2);
        case CEn.examtypeid:
            return changeExamtypeid(value2);
        case CEn.friend:
            return changeFriend(value2);
        case CEn.kbi:
            return changeKbi(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Product0examtype changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Product0examtype changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.productid:
            return changeProductidWithMin(value2, _min);
        case CEn.examtypeid:
            return changeExamtypeidWithMin(value2, _min);
        case CEn.friend:
            return changeFriendWithMin(value2, _min);
        case CEn.kbi:
            return changeKbiWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case CEn.buymoney:
            return buymoney;
        }
        return 0;
    }

    public Product0examtype setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Product0examtype setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.buymoney:
            return setBuymoney(value2);
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
        case CEn.lhubid:
            return lhubid;
        case CEn.productid:
            return productid;
        case CEn.examtypeid:
            return examtypeid;
        case CEn.buymoney:
            return buymoney;
        case CEn.friend:
            return friend;
        case CEn.kbi:
            return kbi;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Product0examtype setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Product0examtype setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Product0examtype setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Product0examtype setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Product0examtype setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Product0examtype setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Product0examtype");
        result.put("id", id);
        result.put("lhubid", lhubid);
        result.put("productid", productid);
        result.put("examtypeid", examtypeid);
        result.put("buymoney", buymoney);
        result.put("friend", friend);
        result.put("kbi", kbi);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("lhubid", lhubid);
        result.put("productid", productid);
        result.put("examtypeid", examtypeid);
        result.put("buymoney", buymoney);
        result.put("friend", friend);
        result.put("kbi", kbi);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Product0examtype");
        result.put("id", id);
        result.put("lhubid", lhubid);
        result.put("productid", productid);
        result.put("examtypeid", examtypeid);
        result.put("buymoney", buymoney);
        result.put("friend", friend);
        result.put("kbi", kbi);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Product0examtype mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer productid = MapEx.getInt(e, "productid");
        Integer examtypeid = MapEx.getInt(e, "examtypeid");
        Double buymoney = MapEx.getDouble(e, "buymoney");
        Integer friend = MapEx.getInt(e, "friend");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(lhubid == null) lhubid = 0;
        if(productid == null) productid = 0;
        if(examtypeid == null) examtypeid = 0;
        if(buymoney == null) buymoney = 0.0;
        if(friend == null) friend = 0;
        if(kbi == null) kbi = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setLhubid(lhubid);
        setProductid(productid);
        setExamtypeid(examtypeid);
        setBuymoney(buymoney);
        setFriend(friend);
        setKbi(kbi);
        setStatus(status);
        setCreatetime(createtime);

        return this;
    }

    public static final Product0examtype mapTo(Map e){
        Product0examtype result = new Product0examtype();

        Integer id = MapEx.getInt(e, "id");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer productid = MapEx.getInt(e, "productid");
        Integer examtypeid = MapEx.getInt(e, "examtypeid");
        Double buymoney = MapEx.getDouble(e, "buymoney");
        Integer friend = MapEx.getInt(e, "friend");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(lhubid == null) lhubid = 0;
        if(productid == null) productid = 0;
        if(examtypeid == null) examtypeid = 0;
        if(buymoney == null) buymoney = 0.0;
        if(friend == null) friend = 0;
        if(kbi == null) kbi = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.lhubid = lhubid;
        result.productid = productid;
        result.examtypeid = examtypeid;
        result.buymoney = buymoney;
        result.friend = friend;
        result.kbi = kbi;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public static final Product0examtype originalTo(Map e){
        Product0examtype result = new Product0examtype();

        Integer id = MapEx.getInt(e, "id");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer productid = MapEx.getInt(e, "productid");
        Integer examtypeid = MapEx.getInt(e, "examtypeid");
        Double buymoney = MapEx.getDouble(e, "buymoney");
        Integer friend = MapEx.getInt(e, "friend");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(lhubid == null) lhubid = 0;
        if(productid == null) productid = 0;
        if(examtypeid == null) examtypeid = 0;
        if(buymoney == null) buymoney = 0.0;
        if(friend == null) friend = 0;
        if(kbi == null) kbi = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.lhubid = lhubid;
        result.productid = productid;
        result.examtypeid = examtypeid;
        result.buymoney = buymoney;
        result.friend = friend;
        result.kbi = kbi;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Product0examtype createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 9);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "productid", productid);
            writeMapEntry(out, "examtypeid", examtypeid);
            writeMapEntry(out, "buymoney", buymoney);
            writeMapEntry(out, "friend", friend);
            writeMapEntry(out, "kbi", kbi);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Product0examtype createFor(byte[] b) throws Exception {
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
    public final Examtype getExamtypeFkExamtypeid() { // examtype - examtypeid
        return ExamtypeEntity.getByKey(examtypeid);
    }

    public final int countExamtypeFkExamtypeid() { // examtype - examtypeid
        return getExamtypeFkExamtypeid() == null ? 0 : 1;
    }

    public final Learnhub getLearnhubFkLhubid() { // learnhub - lhubid
        return LearnhubEntity.getByKey(lhubid);
    }

    public final int countLearnhubFkLhubid() { // learnhub - lhubid
        return getLearnhubFkLhubid() == null ? 0 : 1;
    }

    public final Product getProductFkProductid() { // product - productid
        return ProductEntity.getByKey(productid);
    }

    public final int countProductFkProductid() { // product - productid
        return getProductFkProductid() == null ? 0 : 1;
    }

    public static final Product0examtype loadByKey(int id) {
        return Product0examtypeEntity.getByKey(id);
    }

    public static final Future<Product0examtype> asyncByKey(int id) {
        return Product0examtypeEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Product0examtype insert() {
        Product0examtype result = Product0examtypeEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Product0examtype asyncInsert() {
        Product0examtype result = Product0examtypeEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Product0examtype insert2() {
        return Product0examtypeEntity.insert2(this);
    }

    public final Product0examtype asyncInsert2() {
        Product0examtype result = Product0examtypeEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Product0examtype update() {
        return Product0examtypeEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Product0examtypeEntity.asyncUpdate( this );
        return true;
    }

    public final Product0examtype insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Product0examtypeEntity.delete(id);
    }

    public final void asyncDelete() {
        Product0examtypeEntity.asyncDelete(id);
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

    public Product0examtype clone2() {
        try{
            return (Product0examtype) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
