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

//learnhall3_design - kind
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Kind extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -336415072; // com.learnhall.db.bean.Kind

    public static String TABLENAME = "kind";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String kclassid = "kclassid"; public static final String nmKClass = "nmKClass"; public static final String coursid = "coursid"; public static final String productid = "productid"; public static final String nmProduct = "nmProduct"; public static final String lhubid = "lhubid"; public static final String nmLhub = "nmLhub"; public static final String price = "price"; public static final String discount = "discount"; public static final String kbi = "kbi"; public static final String validity = "validity"; public static final String imgurl = "imgurl"; public static final String buycount = "buycount"; public static final String visit = "visit"; public static final String praise = "praise"; public static final String examtypes = "examtypes"; public static final String status = "status"; public static final String numExercises = "numExercises"; public static final String numZhenti = "numZhenti"; public static final String numSimulation = "numSimulation"; public static final String numVast = "numVast"; public static final String createtime = "createtime"; public static final String isHasITMS = "isHasITMS"; public static final String examids = "examids";  }
    public static final class CEn { public static final String id = "id"; public static final String kclassid = "kclassid"; public static final String nmKClass = "nmKClass"; public static final String coursid = "coursid"; public static final String productid = "productid"; public static final String nmProduct = "nmProduct"; public static final String lhubid = "lhubid"; public static final String nmLhub = "nmLhub"; public static final String price = "price"; public static final String discount = "discount"; public static final String kbi = "kbi"; public static final String validity = "validity"; public static final String imgurl = "imgurl"; public static final String buycount = "buycount"; public static final String visit = "visit"; public static final String praise = "praise"; public static final String examtypes = "examtypes"; public static final String status = "status"; public static final String numExercises = "numExercises"; public static final String numZhenti = "numZhenti"; public static final String numSimulation = "numSimulation"; public static final String numVast = "numVast"; public static final String createtime = "createtime"; public static final String isHasITMS = "isHasITMS"; public static final String examids = "examids";  }
    public static final String[] carrays ={"id", "kclassid", "nmKClass", "coursid", "productid", "nmProduct", "lhubid", "nmLhub", "price", "discount", "kbi", "validity", "imgurl", "buycount", "visit", "praise", "examtypes", "status", "numExercises", "numZhenti", "numSimulation", "numVast", "createtime", "isHasITMS", "examids"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "INT", "INT", "TINYTEXT", "INT", "TINYTEXT", "DOUBLE", "DOUBLE", "INT", "INT", "VARCHAR", "INT", "INT", "INT", "TINYTEXT", "INT", "INT", "INT", "INT", "INT", "DATETIME", "BIT", "TEXT"};


    public int id;
    public int kclassid;
    public String nmKClass;
    public int coursid;
    public int productid;
    public String nmProduct;
    public int lhubid;
    public String nmLhub;
    public double price;
    public double discount;
    public int kbi;
    public int validity;
    public String imgurl;
    public int buycount;
    public int visit;
    public int praise;
    public String examtypes;
    public int status;
    public int numExercises;
    public int numZhenti;
    public int numSimulation;
    public int numVast;
    public java.util.Date createtime;
    public boolean isHasITMS;
    public String examids;

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

    public Kind setId(int id){
        this.id = id;
        return this;
    }

    public int getKclassid(){
        return kclassid;
    }

    public Kind setKclassid(int kclassid){
        int _old = this.kclassid;
        this.kclassid = kclassid;
        changeIt(Col.kclassid, _old, kclassid);
        return this;
    }

    public Kind changeKclassid(int kclassid){
        return setKclassid(this.kclassid + kclassid);
    }

    public Kind changeKclassidWithMin(int kclassid, int _min){
        int _v2 = this.kclassid + kclassid;
        return setKclassid(_v2 < _min  ? _min : _v2);
    }

    public Kind changeKclassidWithMax(int kclassid, int _max){
        int _v2 = this.kclassid + kclassid;
        return setKclassid(_v2 > _max  ? _max : _v2);
    }

    public Kind changeKclassidWithMinMax(int kclassid, int _min, int _max){
        int _v2 = this.kclassid + kclassid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKclassid(_v2 < _min  ? _min : _v2);
    }

    public String getNmKClass(){
        return nmKClass;
    }

    public Kind setNmKClass(String nmKClass){
        String _old = this.nmKClass;
        this.nmKClass = nmKClass;
        changeIt(Col.nmKClass, _old, nmKClass);
        return this;
    }

    public int getCoursid(){
        return coursid;
    }

    public Kind setCoursid(int coursid){
        int _old = this.coursid;
        this.coursid = coursid;
        changeIt(Col.coursid, _old, coursid);
        return this;
    }

    public Kind changeCoursid(int coursid){
        return setCoursid(this.coursid + coursid);
    }

    public Kind changeCoursidWithMin(int coursid, int _min){
        int _v2 = this.coursid + coursid;
        return setCoursid(_v2 < _min  ? _min : _v2);
    }

    public Kind changeCoursidWithMax(int coursid, int _max){
        int _v2 = this.coursid + coursid;
        return setCoursid(_v2 > _max  ? _max : _v2);
    }

    public Kind changeCoursidWithMinMax(int coursid, int _min, int _max){
        int _v2 = this.coursid + coursid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCoursid(_v2 < _min  ? _min : _v2);
    }

    public int getProductid(){
        return productid;
    }

    public Kind setProductid(int productid){
        int _old = this.productid;
        this.productid = productid;
        changeIt(Col.productid, _old, productid);
        return this;
    }

    public Kind changeProductid(int productid){
        return setProductid(this.productid + productid);
    }

    public Kind changeProductidWithMin(int productid, int _min){
        int _v2 = this.productid + productid;
        return setProductid(_v2 < _min  ? _min : _v2);
    }

    public Kind changeProductidWithMax(int productid, int _max){
        int _v2 = this.productid + productid;
        return setProductid(_v2 > _max  ? _max : _v2);
    }

    public Kind changeProductidWithMinMax(int productid, int _min, int _max){
        int _v2 = this.productid + productid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProductid(_v2 < _min  ? _min : _v2);
    }

    public String getNmProduct(){
        return nmProduct;
    }

    public Kind setNmProduct(String nmProduct){
        String _old = this.nmProduct;
        this.nmProduct = nmProduct;
        changeIt(Col.nmProduct, _old, nmProduct);
        return this;
    }

    public int getLhubid(){
        return lhubid;
    }

    public Kind setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Kind changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Kind changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Kind changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Kind changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public String getNmLhub(){
        return nmLhub;
    }

    public Kind setNmLhub(String nmLhub){
        String _old = this.nmLhub;
        this.nmLhub = nmLhub;
        changeIt(Col.nmLhub, _old, nmLhub);
        return this;
    }

    public double getPrice(){
        return price;
    }

    public Kind setPrice(double price){
        double _old = this.price;
        this.price = price;
        changeIt(Col.price, _old, price);
        return this;
    }

    public Kind changePrice(double price){
        return setPrice(this.price + price);
    }

    public Kind changePriceWithMin(double price, double _min){
        double _v2 = this.price + price;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public Kind changePriceWithMax(double price, double _max){
        double _v2 = this.price + price;
        return setPrice(_v2 > _max  ? _max : _v2);
    }

    public Kind changePriceWithMinMax(double price, double _min, double _max){
        double _v2 = this.price + price;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public double getDiscount(){
        return discount;
    }

    public Kind setDiscount(double discount){
        double _old = this.discount;
        this.discount = discount;
        changeIt(Col.discount, _old, discount);
        return this;
    }

    public Kind changeDiscount(double discount){
        return setDiscount(this.discount + discount);
    }

    public Kind changeDiscountWithMin(double discount, double _min){
        double _v2 = this.discount + discount;
        return setDiscount(_v2 < _min  ? _min : _v2);
    }

    public Kind changeDiscountWithMax(double discount, double _max){
        double _v2 = this.discount + discount;
        return setDiscount(_v2 > _max  ? _max : _v2);
    }

    public Kind changeDiscountWithMinMax(double discount, double _min, double _max){
        double _v2 = this.discount + discount;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDiscount(_v2 < _min  ? _min : _v2);
    }

    public int getKbi(){
        return kbi;
    }

    public Kind setKbi(int kbi){
        int _old = this.kbi;
        this.kbi = kbi;
        changeIt(Col.kbi, _old, kbi);
        return this;
    }

    public Kind changeKbi(int kbi){
        return setKbi(this.kbi + kbi);
    }

    public Kind changeKbiWithMin(int kbi, int _min){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public Kind changeKbiWithMax(int kbi, int _max){
        int _v2 = this.kbi + kbi;
        return setKbi(_v2 > _max  ? _max : _v2);
    }

    public Kind changeKbiWithMinMax(int kbi, int _min, int _max){
        int _v2 = this.kbi + kbi;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKbi(_v2 < _min  ? _min : _v2);
    }

    public int getValidity(){
        return validity;
    }

    public Kind setValidity(int validity){
        int _old = this.validity;
        this.validity = validity;
        changeIt(Col.validity, _old, validity);
        return this;
    }

    public Kind changeValidity(int validity){
        return setValidity(this.validity + validity);
    }

    public Kind changeValidityWithMin(int validity, int _min){
        int _v2 = this.validity + validity;
        return setValidity(_v2 < _min  ? _min : _v2);
    }

    public Kind changeValidityWithMax(int validity, int _max){
        int _v2 = this.validity + validity;
        return setValidity(_v2 > _max  ? _max : _v2);
    }

    public Kind changeValidityWithMinMax(int validity, int _min, int _max){
        int _v2 = this.validity + validity;
        _v2 = _v2 > _max  ? _max : _v2;
        return setValidity(_v2 < _min  ? _min : _v2);
    }

    public String getImgurl(){
        return imgurl;
    }

    public Kind setImgurl(String imgurl){
        String _old = this.imgurl;
        this.imgurl = imgurl;
        changeIt(Col.imgurl, _old, imgurl);
        return this;
    }

    public int getBuycount(){
        return buycount;
    }

    public Kind setBuycount(int buycount){
        int _old = this.buycount;
        this.buycount = buycount;
        changeIt(Col.buycount, _old, buycount);
        return this;
    }

    public Kind changeBuycount(int buycount){
        return setBuycount(this.buycount + buycount);
    }

    public Kind changeBuycountWithMin(int buycount, int _min){
        int _v2 = this.buycount + buycount;
        return setBuycount(_v2 < _min  ? _min : _v2);
    }

    public Kind changeBuycountWithMax(int buycount, int _max){
        int _v2 = this.buycount + buycount;
        return setBuycount(_v2 > _max  ? _max : _v2);
    }

    public Kind changeBuycountWithMinMax(int buycount, int _min, int _max){
        int _v2 = this.buycount + buycount;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBuycount(_v2 < _min  ? _min : _v2);
    }

    public int getVisit(){
        return visit;
    }

    public Kind setVisit(int visit){
        int _old = this.visit;
        this.visit = visit;
        changeIt(Col.visit, _old, visit);
        return this;
    }

    public Kind changeVisit(int visit){
        return setVisit(this.visit + visit);
    }

    public Kind changeVisitWithMin(int visit, int _min){
        int _v2 = this.visit + visit;
        return setVisit(_v2 < _min  ? _min : _v2);
    }

    public Kind changeVisitWithMax(int visit, int _max){
        int _v2 = this.visit + visit;
        return setVisit(_v2 > _max  ? _max : _v2);
    }

    public Kind changeVisitWithMinMax(int visit, int _min, int _max){
        int _v2 = this.visit + visit;
        _v2 = _v2 > _max  ? _max : _v2;
        return setVisit(_v2 < _min  ? _min : _v2);
    }

    public int getPraise(){
        return praise;
    }

    public Kind setPraise(int praise){
        int _old = this.praise;
        this.praise = praise;
        changeIt(Col.praise, _old, praise);
        return this;
    }

    public Kind changePraise(int praise){
        return setPraise(this.praise + praise);
    }

    public Kind changePraiseWithMin(int praise, int _min){
        int _v2 = this.praise + praise;
        return setPraise(_v2 < _min  ? _min : _v2);
    }

    public Kind changePraiseWithMax(int praise, int _max){
        int _v2 = this.praise + praise;
        return setPraise(_v2 > _max  ? _max : _v2);
    }

    public Kind changePraiseWithMinMax(int praise, int _min, int _max){
        int _v2 = this.praise + praise;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPraise(_v2 < _min  ? _min : _v2);
    }

    public String getExamtypes(){
        return examtypes;
    }

    public Kind setExamtypes(String examtypes){
        String _old = this.examtypes;
        this.examtypes = examtypes;
        changeIt(Col.examtypes, _old, examtypes);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Kind setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Kind changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Kind changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Kind changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Kind changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int getNumExercises(){
        return numExercises;
    }

    public Kind setNumExercises(int numExercises){
        int _old = this.numExercises;
        this.numExercises = numExercises;
        changeIt(Col.numExercises, _old, numExercises);
        return this;
    }

    public Kind changeNumExercises(int numExercises){
        return setNumExercises(this.numExercises + numExercises);
    }

    public Kind changeNumExercisesWithMin(int numExercises, int _min){
        int _v2 = this.numExercises + numExercises;
        return setNumExercises(_v2 < _min  ? _min : _v2);
    }

    public Kind changeNumExercisesWithMax(int numExercises, int _max){
        int _v2 = this.numExercises + numExercises;
        return setNumExercises(_v2 > _max  ? _max : _v2);
    }

    public Kind changeNumExercisesWithMinMax(int numExercises, int _min, int _max){
        int _v2 = this.numExercises + numExercises;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNumExercises(_v2 < _min  ? _min : _v2);
    }

    public int getNumZhenti(){
        return numZhenti;
    }

    public Kind setNumZhenti(int numZhenti){
        int _old = this.numZhenti;
        this.numZhenti = numZhenti;
        changeIt(Col.numZhenti, _old, numZhenti);
        return this;
    }

    public Kind changeNumZhenti(int numZhenti){
        return setNumZhenti(this.numZhenti + numZhenti);
    }

    public Kind changeNumZhentiWithMin(int numZhenti, int _min){
        int _v2 = this.numZhenti + numZhenti;
        return setNumZhenti(_v2 < _min  ? _min : _v2);
    }

    public Kind changeNumZhentiWithMax(int numZhenti, int _max){
        int _v2 = this.numZhenti + numZhenti;
        return setNumZhenti(_v2 > _max  ? _max : _v2);
    }

    public Kind changeNumZhentiWithMinMax(int numZhenti, int _min, int _max){
        int _v2 = this.numZhenti + numZhenti;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNumZhenti(_v2 < _min  ? _min : _v2);
    }

    public int getNumSimulation(){
        return numSimulation;
    }

    public Kind setNumSimulation(int numSimulation){
        int _old = this.numSimulation;
        this.numSimulation = numSimulation;
        changeIt(Col.numSimulation, _old, numSimulation);
        return this;
    }

    public Kind changeNumSimulation(int numSimulation){
        return setNumSimulation(this.numSimulation + numSimulation);
    }

    public Kind changeNumSimulationWithMin(int numSimulation, int _min){
        int _v2 = this.numSimulation + numSimulation;
        return setNumSimulation(_v2 < _min  ? _min : _v2);
    }

    public Kind changeNumSimulationWithMax(int numSimulation, int _max){
        int _v2 = this.numSimulation + numSimulation;
        return setNumSimulation(_v2 > _max  ? _max : _v2);
    }

    public Kind changeNumSimulationWithMinMax(int numSimulation, int _min, int _max){
        int _v2 = this.numSimulation + numSimulation;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNumSimulation(_v2 < _min  ? _min : _v2);
    }

    public int getNumVast(){
        return numVast;
    }

    public Kind setNumVast(int numVast){
        int _old = this.numVast;
        this.numVast = numVast;
        changeIt(Col.numVast, _old, numVast);
        return this;
    }

    public Kind changeNumVast(int numVast){
        return setNumVast(this.numVast + numVast);
    }

    public Kind changeNumVastWithMin(int numVast, int _min){
        int _v2 = this.numVast + numVast;
        return setNumVast(_v2 < _min  ? _min : _v2);
    }

    public Kind changeNumVastWithMax(int numVast, int _max){
        int _v2 = this.numVast + numVast;
        return setNumVast(_v2 > _max  ? _max : _v2);
    }

    public Kind changeNumVastWithMinMax(int numVast, int _min, int _max){
        int _v2 = this.numVast + numVast;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNumVast(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Kind setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public boolean getIsHasITMS(){
        return isHasITMS;
    }

    public Kind setIsHasITMS(boolean isHasITMS){
        boolean _old = this.isHasITMS;
        this.isHasITMS = isHasITMS;
        changeIt(Col.isHasITMS, _old, isHasITMS);
        return this;
    }

    public String getExamids(){
        return examids;
    }

    public Kind setExamids(String examids){
        String _old = this.examids;
        this.examids = examids;
        changeIt(Col.examids, _old, examids);
        return this;
    }

    public int compareTo(Kind v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Kind v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Kind newKind(Integer id, Integer kclassid, String nmKClass, Integer coursid, Integer productid, String nmProduct, Integer lhubid, String nmLhub, Double price, Double discount, Integer kbi, Integer validity, String imgurl, Integer buycount, Integer visit, Integer praise, String examtypes, Integer status, Integer numExercises, Integer numZhenti, Integer numSimulation, Integer numVast, java.util.Date createtime, Boolean isHasITMS, String examids) {
        Kind result = new Kind();
        result.id = id;
        result.kclassid = kclassid;
        result.nmKClass = nmKClass;
        result.coursid = coursid;
        result.productid = productid;
        result.nmProduct = nmProduct;
        result.lhubid = lhubid;
        result.nmLhub = nmLhub;
        result.price = price;
        result.discount = discount;
        result.kbi = kbi;
        result.validity = validity;
        result.imgurl = imgurl;
        result.buycount = buycount;
        result.visit = visit;
        result.praise = praise;
        result.examtypes = examtypes;
        result.status = status;
        result.numExercises = numExercises;
        result.numZhenti = numZhenti;
        result.numSimulation = numSimulation;
        result.numVast = numVast;
        result.createtime = createtime;
        result.isHasITMS = isHasITMS;
        result.examids = examids;
        return result;
    }

    public static Kind newKind(Kind kind) {
        Kind result = new Kind();
        result.id = kind.id;
        result.kclassid = kind.kclassid;
        result.nmKClass = kind.nmKClass;
        result.coursid = kind.coursid;
        result.productid = kind.productid;
        result.nmProduct = kind.nmProduct;
        result.lhubid = kind.lhubid;
        result.nmLhub = kind.nmLhub;
        result.price = kind.price;
        result.discount = kind.discount;
        result.kbi = kind.kbi;
        result.validity = kind.validity;
        result.imgurl = kind.imgurl;
        result.buycount = kind.buycount;
        result.visit = kind.visit;
        result.praise = kind.praise;
        result.examtypes = kind.examtypes;
        result.status = kind.status;
        result.numExercises = kind.numExercises;
        result.numZhenti = kind.numZhenti;
        result.numSimulation = kind.numSimulation;
        result.numVast = kind.numVast;
        result.createtime = kind.createtime;
        result.isHasITMS = kind.isHasITMS;
        result.examids = kind.examids;
        return result;
    }

    public Kind createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getKind(){
        Kind kind = null; // kind
        { // new and insert Kind (kind)
            int id = 0; 	// id
            int kclassid = 0; 	// kclassid
            String nmKClass = ""; 	// nmKClass
            int coursid = 0; 	// coursid
            int productid = 0; 	// productid
            String nmProduct = ""; 	// nmProduct
            int lhubid = 0; 	// lhubid
            String nmLhub = ""; 	// nmLhub
            double price = 0.0; 	// price
            double discount = 0.0; 	// discount
            int kbi = 0; 	// kbi
            int validity = 0; 	// validity
            String imgurl = ""; 	// imgurl
            int buycount = 0; 	// buycount
            int visit = 0; 	// visit
            int praise = 0; 	// praise
            String examtypes = ""; 	// examtypes
            int status = 0; 	// status
            int numExercises = 0; 	// numExercises
            int numZhenti = 0; 	// numZhenti
            int numSimulation = 0; 	// numSimulation
            int numVast = 0; 	// numVast
            Date createtime = new Date(); 	// createtime
            boolean isHasITMS = true; 	// isHasITMS
            String examids = ""; 	// examids
            kind = Kind.newKind(id, kclassid, nmKClass, coursid, productid, nmProduct, lhubid, nmLhub, price, discount, kbi, validity, imgurl, buycount, visit, praise, examtypes, status, numExercises, numZhenti, numSimulation, numVast, createtime, isHasITMS, examids);
        }
        kind = kind.insert();

        int id = kind.getId(); 	// id
        int kclassid = kind.getKclassid(); 	// kclassid
        String nmKClass = kind.getNmKClass(); 	// nmKClass
        int coursid = kind.getCoursid(); 	// coursid
        int productid = kind.getProductid(); 	// productid
        String nmProduct = kind.getNmProduct(); 	// nmProduct
        int lhubid = kind.getLhubid(); 	// lhubid
        String nmLhub = kind.getNmLhub(); 	// nmLhub
        double price = kind.getPrice(); 	// price
        double discount = kind.getDiscount(); 	// discount
        int kbi = kind.getKbi(); 	// kbi
        int validity = kind.getValidity(); 	// validity
        String imgurl = kind.getImgurl(); 	// imgurl
        int buycount = kind.getBuycount(); 	// buycount
        int visit = kind.getVisit(); 	// visit
        int praise = kind.getPraise(); 	// praise
        String examtypes = kind.getExamtypes(); 	// examtypes
        int status = kind.getStatus(); 	// status
        int numExercises = kind.getNumExercises(); 	// numExercises
        int numZhenti = kind.getNumZhenti(); 	// numZhenti
        int numSimulation = kind.getNumSimulation(); 	// numSimulation
        int numVast = kind.getNumVast(); 	// numVast
        Date createtime = kind.getCreatetime(); 	// createtime
        boolean isHasITMS = kind.getIsHasITMS(); 	// isHasITMS
        String examids = kind.getExamids(); 	// examids
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
        case CEn.kclassid:
            return kclassid;
        case CEn.coursid:
            return coursid;
        case CEn.productid:
            return productid;
        case CEn.lhubid:
            return lhubid;
        case CEn.kbi:
            return kbi;
        case CEn.validity:
            return validity;
        case CEn.buycount:
            return buycount;
        case CEn.visit:
            return visit;
        case CEn.praise:
            return praise;
        case CEn.status:
            return status;
        case CEn.numExercises:
            return numExercises;
        case CEn.numZhenti:
            return numZhenti;
        case CEn.numSimulation:
            return numSimulation;
        case CEn.numVast:
            return numVast;
        }
        return 0;
    }

    public Kind setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Kind setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.kclassid:
            return setKclassid(value2);
        case CEn.coursid:
            return setCoursid(value2);
        case CEn.productid:
            return setProductid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.kbi:
            return setKbi(value2);
        case CEn.validity:
            return setValidity(value2);
        case CEn.buycount:
            return setBuycount(value2);
        case CEn.visit:
            return setVisit(value2);
        case CEn.praise:
            return setPraise(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.numExercises:
            return setNumExercises(value2);
        case CEn.numZhenti:
            return setNumZhenti(value2);
        case CEn.numSimulation:
            return setNumSimulation(value2);
        case CEn.numVast:
            return setNumVast(value2);
        }
        return this;
    }

    public Kind changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Kind changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.kclassid:
            return changeKclassid(value2);
        case CEn.coursid:
            return changeCoursid(value2);
        case CEn.productid:
            return changeProductid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.kbi:
            return changeKbi(value2);
        case CEn.validity:
            return changeValidity(value2);
        case CEn.buycount:
            return changeBuycount(value2);
        case CEn.visit:
            return changeVisit(value2);
        case CEn.praise:
            return changePraise(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.numExercises:
            return changeNumExercises(value2);
        case CEn.numZhenti:
            return changeNumZhenti(value2);
        case CEn.numSimulation:
            return changeNumSimulation(value2);
        case CEn.numVast:
            return changeNumVast(value2);
        }
        return this;
    }

    public Kind changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Kind changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.kclassid:
            return changeKclassidWithMin(value2, _min);
        case CEn.coursid:
            return changeCoursidWithMin(value2, _min);
        case CEn.productid:
            return changeProductidWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.kbi:
            return changeKbiWithMin(value2, _min);
        case CEn.validity:
            return changeValidityWithMin(value2, _min);
        case CEn.buycount:
            return changeBuycountWithMin(value2, _min);
        case CEn.visit:
            return changeVisitWithMin(value2, _min);
        case CEn.praise:
            return changePraiseWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.numExercises:
            return changeNumExercisesWithMin(value2, _min);
        case CEn.numZhenti:
            return changeNumZhentiWithMin(value2, _min);
        case CEn.numSimulation:
            return changeNumSimulationWithMin(value2, _min);
        case CEn.numVast:
            return changeNumVastWithMin(value2, _min);
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
        }
        return 0;
    }

    public Kind setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Kind setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.price:
            return setPrice(value2);
        case CEn.discount:
            return setDiscount(value2);
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
        case CEn.nmKClass: 
            return nmKClass;
        case CEn.nmProduct: 
            return nmProduct;
        case CEn.nmLhub: 
            return nmLhub;
        case CEn.imgurl: 
            return imgurl;
        case CEn.examtypes: 
            return examtypes;
        case CEn.examids: 
            return examids;
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
        case CEn.kclassid:
            return kclassid;
        case CEn.nmKClass:
            return nmKClass;
        case CEn.coursid:
            return coursid;
        case CEn.productid:
            return productid;
        case CEn.nmProduct:
            return nmProduct;
        case CEn.lhubid:
            return lhubid;
        case CEn.nmLhub:
            return nmLhub;
        case CEn.price:
            return price;
        case CEn.discount:
            return discount;
        case CEn.kbi:
            return kbi;
        case CEn.validity:
            return validity;
        case CEn.imgurl:
            return imgurl;
        case CEn.buycount:
            return buycount;
        case CEn.visit:
            return visit;
        case CEn.praise:
            return praise;
        case CEn.examtypes:
            return examtypes;
        case CEn.status:
            return status;
        case CEn.numExercises:
            return numExercises;
        case CEn.numZhenti:
            return numZhenti;
        case CEn.numSimulation:
            return numSimulation;
        case CEn.numVast:
            return numVast;
        case CEn.createtime:
            return createtime;
        case CEn.isHasITMS:
            return isHasITMS;
        case CEn.examids:
            return examids;
        }
        return null;
    }

    public Kind setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Kind setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Kind setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Kind setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.nmKClass:
            return setNmKClass(value2);
        case CEn.nmProduct:
            return setNmProduct(value2);
        case CEn.nmLhub:
            return setNmLhub(value2);
        case CEn.imgurl:
            return setImgurl(value2);
        case CEn.examtypes:
            return setExamtypes(value2);
        case CEn.examids:
            return setExamids(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Kind setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Kind setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Kind");
        result.put("id", id);
        result.put("kclassid", kclassid);
        result.put("nmKClass", nmKClass);
        result.put("coursid", coursid);
        result.put("productid", productid);
        result.put("nmProduct", nmProduct);
        result.put("lhubid", lhubid);
        result.put("nmLhub", nmLhub);
        result.put("price", price);
        result.put("discount", discount);
        result.put("kbi", kbi);
        result.put("validity", validity);
        result.put("imgurl", imgurl);
        result.put("buycount", buycount);
        result.put("visit", visit);
        result.put("praise", praise);
        result.put("examtypes", examtypes);
        result.put("status", status);
        result.put("numExercises", numExercises);
        result.put("numZhenti", numZhenti);
        result.put("numSimulation", numSimulation);
        result.put("numVast", numVast);
        result.put("createtime", createtime);
        result.put("isHasITMS", isHasITMS);
        result.put("examids", examids);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("kclassid", kclassid);
        result.put("nmKClass", nmKClass);
        result.put("coursid", coursid);
        result.put("productid", productid);
        result.put("nmProduct", nmProduct);
        result.put("lhubid", lhubid);
        result.put("nmLhub", nmLhub);
        result.put("price", price);
        result.put("discount", discount);
        result.put("kbi", kbi);
        result.put("validity", validity);
        result.put("imgurl", imgurl);
        result.put("buycount", buycount);
        result.put("visit", visit);
        result.put("praise", praise);
        result.put("examtypes", examtypes);
        result.put("status", status);
        result.put("numExercises", numExercises);
        result.put("numZhenti", numZhenti);
        result.put("numSimulation", numSimulation);
        result.put("numVast", numVast);
        result.put("createtime", createtime);
        result.put("isHasITMS", isHasITMS);
        result.put("examids", examids);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Kind");
        result.put("id", id);
        result.put("kclassid", kclassid);
        result.put("nmKClass", nmKClass);
        result.put("coursid", coursid);
        result.put("productid", productid);
        result.put("nmProduct", nmProduct);
        result.put("lhubid", lhubid);
        result.put("nmLhub", nmLhub);
        result.put("price", price);
        result.put("discount", discount);
        result.put("kbi", kbi);
        result.put("validity", validity);
        result.put("imgurl", imgurl);
        result.put("buycount", buycount);
        result.put("visit", visit);
        result.put("praise", praise);
        result.put("examtypes", examtypes);
        result.put("status", status);
        result.put("numExercises", numExercises);
        result.put("numZhenti", numZhenti);
        result.put("numSimulation", numSimulation);
        result.put("numVast", numVast);
        result.put("createtime", createtime);
        result.put("isHasITMS", isHasITMS);
        result.put("examids", examids);
        return result;
    }

    public Kind mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer kclassid = MapEx.getInt(e, "kclassid");
        String nmKClass = MapEx.getString(e, "nmKClass");
        Integer coursid = MapEx.getInt(e, "coursid");
        Integer productid = MapEx.getInt(e, "productid");
        String nmProduct = MapEx.getString(e, "nmProduct");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        String nmLhub = MapEx.getString(e, "nmLhub");
        Double price = MapEx.getDouble(e, "price");
        Double discount = MapEx.getDouble(e, "discount");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer validity = MapEx.getInt(e, "validity");
        String imgurl = MapEx.getString(e, "imgurl");
        Integer buycount = MapEx.getInt(e, "buycount");
        Integer visit = MapEx.getInt(e, "visit");
        Integer praise = MapEx.getInt(e, "praise");
        String examtypes = MapEx.getString(e, "examtypes");
        Integer status = MapEx.getInt(e, "status");
        Integer numExercises = MapEx.getInt(e, "numExercises");
        Integer numZhenti = MapEx.getInt(e, "numZhenti");
        Integer numSimulation = MapEx.getInt(e, "numSimulation");
        Integer numVast = MapEx.getInt(e, "numVast");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Boolean isHasITMS = MapEx.getBoolean(e, "isHasITMS");
        String examids = MapEx.getString(e, "examids");

        if(id == null) id = 0;
        if(kclassid == null) kclassid = 0;
        if(nmKClass == null) nmKClass = "";
        if(coursid == null) coursid = 0;
        if(productid == null) productid = 0;
        if(nmProduct == null) nmProduct = "";
        if(lhubid == null) lhubid = 0;
        if(nmLhub == null) nmLhub = "";
        if(price == null) price = 0.0;
        if(discount == null) discount = 0.0;
        if(kbi == null) kbi = 0;
        if(validity == null) validity = 0;
        if(imgurl == null) imgurl = "";
        if(buycount == null) buycount = 0;
        if(visit == null) visit = 0;
        if(praise == null) praise = 0;
        if(examtypes == null) examtypes = "";
        if(status == null) status = 0;
        if(numExercises == null) numExercises = 0;
        if(numZhenti == null) numZhenti = 0;
        if(numSimulation == null) numSimulation = 0;
        if(numVast == null) numVast = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(isHasITMS == null) isHasITMS = false;
        if(examids == null) examids = "";

        setId(id);
        setKclassid(kclassid);
        setNmKClass(nmKClass);
        setCoursid(coursid);
        setProductid(productid);
        setNmProduct(nmProduct);
        setLhubid(lhubid);
        setNmLhub(nmLhub);
        setPrice(price);
        setDiscount(discount);
        setKbi(kbi);
        setValidity(validity);
        setImgurl(imgurl);
        setBuycount(buycount);
        setVisit(visit);
        setPraise(praise);
        setExamtypes(examtypes);
        setStatus(status);
        setNumExercises(numExercises);
        setNumZhenti(numZhenti);
        setNumSimulation(numSimulation);
        setNumVast(numVast);
        setCreatetime(createtime);
        setIsHasITMS(isHasITMS);
        setExamids(examids);

        return this;
    }

    public static final Kind mapTo(Map e){
        Kind result = new Kind();

        Integer id = MapEx.getInt(e, "id");
        Integer kclassid = MapEx.getInt(e, "kclassid");
        String nmKClass = MapEx.getString(e, "nmKClass");
        Integer coursid = MapEx.getInt(e, "coursid");
        Integer productid = MapEx.getInt(e, "productid");
        String nmProduct = MapEx.getString(e, "nmProduct");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        String nmLhub = MapEx.getString(e, "nmLhub");
        Double price = MapEx.getDouble(e, "price");
        Double discount = MapEx.getDouble(e, "discount");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer validity = MapEx.getInt(e, "validity");
        String imgurl = MapEx.getString(e, "imgurl");
        Integer buycount = MapEx.getInt(e, "buycount");
        Integer visit = MapEx.getInt(e, "visit");
        Integer praise = MapEx.getInt(e, "praise");
        String examtypes = MapEx.getString(e, "examtypes");
        Integer status = MapEx.getInt(e, "status");
        Integer numExercises = MapEx.getInt(e, "numExercises");
        Integer numZhenti = MapEx.getInt(e, "numZhenti");
        Integer numSimulation = MapEx.getInt(e, "numSimulation");
        Integer numVast = MapEx.getInt(e, "numVast");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Boolean isHasITMS = MapEx.getBoolean(e, "isHasITMS");
        String examids = MapEx.getString(e, "examids");

        if(id == null) id = 0;
        if(kclassid == null) kclassid = 0;
        if(nmKClass == null) nmKClass = "";
        if(coursid == null) coursid = 0;
        if(productid == null) productid = 0;
        if(nmProduct == null) nmProduct = "";
        if(lhubid == null) lhubid = 0;
        if(nmLhub == null) nmLhub = "";
        if(price == null) price = 0.0;
        if(discount == null) discount = 0.0;
        if(kbi == null) kbi = 0;
        if(validity == null) validity = 0;
        if(imgurl == null) imgurl = "";
        if(buycount == null) buycount = 0;
        if(visit == null) visit = 0;
        if(praise == null) praise = 0;
        if(examtypes == null) examtypes = "";
        if(status == null) status = 0;
        if(numExercises == null) numExercises = 0;
        if(numZhenti == null) numZhenti = 0;
        if(numSimulation == null) numSimulation = 0;
        if(numVast == null) numVast = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(isHasITMS == null) isHasITMS = false;
        if(examids == null) examids = "";

        result.id = id;
        result.kclassid = kclassid;
        result.nmKClass = nmKClass;
        result.coursid = coursid;
        result.productid = productid;
        result.nmProduct = nmProduct;
        result.lhubid = lhubid;
        result.nmLhub = nmLhub;
        result.price = price;
        result.discount = discount;
        result.kbi = kbi;
        result.validity = validity;
        result.imgurl = imgurl;
        result.buycount = buycount;
        result.visit = visit;
        result.praise = praise;
        result.examtypes = examtypes;
        result.status = status;
        result.numExercises = numExercises;
        result.numZhenti = numZhenti;
        result.numSimulation = numSimulation;
        result.numVast = numVast;
        result.createtime = createtime;
        result.isHasITMS = isHasITMS;
        result.examids = examids;

        return result;
    }

    public static final Kind originalTo(Map e){
        Kind result = new Kind();

        Integer id = MapEx.getInt(e, "id");
        Integer kclassid = MapEx.getInt(e, "kclassid");
        String nmKClass = MapEx.getString(e, "nmKClass");
        Integer coursid = MapEx.getInt(e, "coursid");
        Integer productid = MapEx.getInt(e, "productid");
        String nmProduct = MapEx.getString(e, "nmProduct");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        String nmLhub = MapEx.getString(e, "nmLhub");
        Double price = MapEx.getDouble(e, "price");
        Double discount = MapEx.getDouble(e, "discount");
        Integer kbi = MapEx.getInt(e, "kbi");
        Integer validity = MapEx.getInt(e, "validity");
        String imgurl = MapEx.getString(e, "imgurl");
        Integer buycount = MapEx.getInt(e, "buycount");
        Integer visit = MapEx.getInt(e, "visit");
        Integer praise = MapEx.getInt(e, "praise");
        String examtypes = MapEx.getString(e, "examtypes");
        Integer status = MapEx.getInt(e, "status");
        Integer numExercises = MapEx.getInt(e, "numExercises");
        Integer numZhenti = MapEx.getInt(e, "numZhenti");
        Integer numSimulation = MapEx.getInt(e, "numSimulation");
        Integer numVast = MapEx.getInt(e, "numVast");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        Boolean isHasITMS = MapEx.getBoolean(e, "isHasITMS");
        String examids = MapEx.getString(e, "examids");

        if(id == null) id = 0;
        if(kclassid == null) kclassid = 0;
        if(nmKClass == null) nmKClass = "";
        if(coursid == null) coursid = 0;
        if(productid == null) productid = 0;
        if(nmProduct == null) nmProduct = "";
        if(lhubid == null) lhubid = 0;
        if(nmLhub == null) nmLhub = "";
        if(price == null) price = 0.0;
        if(discount == null) discount = 0.0;
        if(kbi == null) kbi = 0;
        if(validity == null) validity = 0;
        if(imgurl == null) imgurl = "";
        if(buycount == null) buycount = 0;
        if(visit == null) visit = 0;
        if(praise == null) praise = 0;
        if(examtypes == null) examtypes = "";
        if(status == null) status = 0;
        if(numExercises == null) numExercises = 0;
        if(numZhenti == null) numZhenti = 0;
        if(numSimulation == null) numSimulation = 0;
        if(numVast == null) numVast = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(isHasITMS == null) isHasITMS = false;
        if(examids == null) examids = "";

        result.id = id;
        result.kclassid = kclassid;
        result.nmKClass = nmKClass;
        result.coursid = coursid;
        result.productid = productid;
        result.nmProduct = nmProduct;
        result.lhubid = lhubid;
        result.nmLhub = nmLhub;
        result.price = price;
        result.discount = discount;
        result.kbi = kbi;
        result.validity = validity;
        result.imgurl = imgurl;
        result.buycount = buycount;
        result.visit = visit;
        result.praise = praise;
        result.examtypes = examtypes;
        result.status = status;
        result.numExercises = numExercises;
        result.numZhenti = numZhenti;
        result.numSimulation = numSimulation;
        result.numVast = numVast;
        result.createtime = createtime;
        result.isHasITMS = isHasITMS;
        result.examids = examids;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Kind createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 25);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "kclassid", kclassid);
            writeMapEntry(out, "nmKClass", nmKClass);
            writeMapEntry(out, "coursid", coursid);
            writeMapEntry(out, "productid", productid);
            writeMapEntry(out, "nmProduct", nmProduct);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "nmLhub", nmLhub);
            writeMapEntry(out, "price", price);
            writeMapEntry(out, "discount", discount);
            writeMapEntry(out, "kbi", kbi);
            writeMapEntry(out, "validity", validity);
            writeMapEntry(out, "imgurl", imgurl);
            writeMapEntry(out, "buycount", buycount);
            writeMapEntry(out, "visit", visit);
            writeMapEntry(out, "praise", praise);
            writeMapEntry(out, "examtypes", examtypes);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "numExercises", numExercises);
            writeMapEntry(out, "numZhenti", numZhenti);
            writeMapEntry(out, "numSimulation", numSimulation);
            writeMapEntry(out, "numVast", numVast);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "isHasITMS", isHasITMS);
            writeMapEntry(out, "examids", examids);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Kind createFor(byte[] b) throws Exception {
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
    public final Adcourses getAdcoursesFkCoursid() { // adcourses - coursid
        return AdcoursesEntity.getByKey(coursid);
    }

    public final int countAdcoursesFkCoursid() { // adcourses - coursid
        return getAdcoursesFkCoursid() == null ? 0 : 1;
    }

    public final Kindclass getKindclassFkKclassid() { // kindclass - kclassid
        return KindclassEntity.getByKey(kclassid);
    }

    public final int countKindclassFkKclassid() { // kindclass - kclassid
        return getKindclassFkKclassid() == null ? 0 : 1;
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

    public final List<Appraise> getAppraisesFkKindid() { // appraise - kindid
        return AppraiseEntity.getByKindid(id);
    }

    public final int countAppraisesFkKindid() { // appraise - kindid
        return AppraiseEntity.countByKindid(id);
    }

    public final List<Boughtkinds> getBoughtkindssFkKindid() { // boughtkinds - kindid
        return BoughtkindsEntity.getByKindid(id);
    }

    public final int countBoughtkindssFkKindid() { // boughtkinds - kindid
        return BoughtkindsEntity.countByKindid(id);
    }

    public final List<Openkind4customer> getOpenkind4customersFkKindid() { // openkind4customer - kindid
        return Openkind4customerEntity.getByKindid(id);
    }

    public final int countOpenkind4customersFkKindid() { // openkind4customer - kindid
        return Openkind4customerEntity.countByKindid(id);
    }

    public final List<Openkind4third> getOpenkind4thirdsFkKindid() { // openkind4third - kindid
        return Openkind4thirdEntity.getByKindid(id);
    }

    public final int countOpenkind4thirdsFkKindid() { // openkind4third - kindid
        return Openkind4thirdEntity.countByKindid(id);
    }

    public final List<Orders4profit> getOrders4profitsFkKindid() { // orders4profit - kindid
        return Orders4profitEntity.getByKindid(id);
    }

    public final int countOrders4profitsFkKindid() { // orders4profit - kindid
        return Orders4profitEntity.countByKindid(id);
    }

    public static final Kind loadByKey(int id) {
        return KindEntity.getByKey(id);
    }

    public static final Future<Kind> asyncByKey(int id) {
        return KindEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Kind insert() {
        Kind result = KindEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Kind asyncInsert() {
        Kind result = KindEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Kind insert2() {
        return KindEntity.insert2(this);
    }

    public final Kind asyncInsert2() {
        Kind result = KindEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Kind update() {
        return KindEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        KindEntity.asyncUpdate( this );
        return true;
    }

    public final Kind insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return KindEntity.delete(id);
    }

    public final void asyncDelete() {
        KindEntity.asyncDelete(id);
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

    public Kind clone2() {
        try{
            return (Kind) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
