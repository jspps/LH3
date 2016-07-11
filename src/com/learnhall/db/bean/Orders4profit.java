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

//learnhall3_design - orders4profit
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Orders4profit extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1606157465; // com.learnhall.db.bean.Orders4profit

    public static String TABLENAME = "orders4profit";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String orderNo = "orderNo"; public static final String kindid = "kindid"; public static final String custid = "custid"; public static final String lhubid = "lhubid"; public static final String agentid = "agentid"; public static final String agentBonus = "agentBonus"; public static final String agentRoyalty = "agentRoyalty"; public static final String lhubRoyalty = "lhubRoyalty"; public static final String lhubDeposit = "lhubDeposit"; public static final String developRoyalty = "developRoyalty"; public static final String artRoyalty = "artRoyalty"; public static final String price = "price"; public static final String discount = "discount"; public static final String realprice = "realprice"; public static final String isProfit4Agent = "isProfit4Agent"; public static final String isProfit4Lhub = "isProfit4Lhub"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String orderNo = "orderNo"; public static final String kindid = "kindid"; public static final String custid = "custid"; public static final String lhubid = "lhubid"; public static final String agentid = "agentid"; public static final String agentBonus = "agentBonus"; public static final String agentRoyalty = "agentRoyalty"; public static final String lhubRoyalty = "lhubRoyalty"; public static final String lhubDeposit = "lhubDeposit"; public static final String developRoyalty = "developRoyalty"; public static final String artRoyalty = "artRoyalty"; public static final String price = "price"; public static final String discount = "discount"; public static final String realprice = "realprice"; public static final String isProfit4Agent = "isProfit4Agent"; public static final String isProfit4Lhub = "isProfit4Lhub"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "orderNo", "kindid", "custid", "lhubid", "agentid", "agentBonus", "agentRoyalty", "lhubRoyalty", "lhubDeposit", "developRoyalty", "artRoyalty", "price", "discount", "realprice", "isProfit4Agent", "isProfit4Lhub", "status", "createtime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "INT", "INT", "INT", "DOUBLE", "DOUBLE", "DOUBLE", "DOUBLE", "DOUBLE", "DOUBLE", "DOUBLE", "DOUBLE", "DOUBLE", "BIT", "BIT", "INT", "DATETIME"};


    public int id;
    public String orderNo;
    public int kindid;
    public int custid;
    public int lhubid;
    public int agentid;
    public double agentBonus;
    public double agentRoyalty;
    public double lhubRoyalty;
    public double lhubDeposit;
    public double developRoyalty;
    public double artRoyalty;
    public double price;
    public double discount;
    public double realprice;
    public boolean isProfit4Agent;
    public boolean isProfit4Lhub;
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

    public Orders4profit setId(int id){
        this.id = id;
        return this;
    }

    public String getOrderNo(){
        return orderNo;
    }

    public Orders4profit setOrderNo(String orderNo){
        String _old = this.orderNo;
        this.orderNo = orderNo;
        changeIt(Col.orderNo, _old, orderNo);
        return this;
    }

    public int getKindid(){
        return kindid;
    }

    public Orders4profit setKindid(int kindid){
        int _old = this.kindid;
        this.kindid = kindid;
        changeIt(Col.kindid, _old, kindid);
        return this;
    }

    public Orders4profit changeKindid(int kindid){
        return setKindid(this.kindid + kindid);
    }

    public Orders4profit changeKindidWithMin(int kindid, int _min){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeKindidWithMax(int kindid, int _max){
        int _v2 = this.kindid + kindid;
        return setKindid(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeKindidWithMinMax(int kindid, int _min, int _max){
        int _v2 = this.kindid + kindid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setKindid(_v2 < _min  ? _min : _v2);
    }

    public int getCustid(){
        return custid;
    }

    public Orders4profit setCustid(int custid){
        int _old = this.custid;
        this.custid = custid;
        changeIt(Col.custid, _old, custid);
        return this;
    }

    public Orders4profit changeCustid(int custid){
        return setCustid(this.custid + custid);
    }

    public Orders4profit changeCustidWithMin(int custid, int _min){
        int _v2 = this.custid + custid;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeCustidWithMax(int custid, int _max){
        int _v2 = this.custid + custid;
        return setCustid(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeCustidWithMinMax(int custid, int _min, int _max){
        int _v2 = this.custid + custid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustid(_v2 < _min  ? _min : _v2);
    }

    public int getLhubid(){
        return lhubid;
    }

    public Orders4profit setLhubid(int lhubid){
        int _old = this.lhubid;
        this.lhubid = lhubid;
        changeIt(Col.lhubid, _old, lhubid);
        return this;
    }

    public Orders4profit changeLhubid(int lhubid){
        return setLhubid(this.lhubid + lhubid);
    }

    public Orders4profit changeLhubidWithMin(int lhubid, int _min){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeLhubidWithMax(int lhubid, int _max){
        int _v2 = this.lhubid + lhubid;
        return setLhubid(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeLhubidWithMinMax(int lhubid, int _min, int _max){
        int _v2 = this.lhubid + lhubid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubid(_v2 < _min  ? _min : _v2);
    }

    public int getAgentid(){
        return agentid;
    }

    public Orders4profit setAgentid(int agentid){
        int _old = this.agentid;
        this.agentid = agentid;
        changeIt(Col.agentid, _old, agentid);
        return this;
    }

    public Orders4profit changeAgentid(int agentid){
        return setAgentid(this.agentid + agentid);
    }

    public Orders4profit changeAgentidWithMin(int agentid, int _min){
        int _v2 = this.agentid + agentid;
        return setAgentid(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeAgentidWithMax(int agentid, int _max){
        int _v2 = this.agentid + agentid;
        return setAgentid(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeAgentidWithMinMax(int agentid, int _min, int _max){
        int _v2 = this.agentid + agentid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAgentid(_v2 < _min  ? _min : _v2);
    }

    public double getAgentBonus(){
        return agentBonus;
    }

    public Orders4profit setAgentBonus(double agentBonus){
        double _old = this.agentBonus;
        this.agentBonus = agentBonus;
        changeIt(Col.agentBonus, _old, agentBonus);
        return this;
    }

    public Orders4profit changeAgentBonus(double agentBonus){
        return setAgentBonus(this.agentBonus + agentBonus);
    }

    public Orders4profit changeAgentBonusWithMin(double agentBonus, double _min){
        double _v2 = this.agentBonus + agentBonus;
        return setAgentBonus(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeAgentBonusWithMax(double agentBonus, double _max){
        double _v2 = this.agentBonus + agentBonus;
        return setAgentBonus(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeAgentBonusWithMinMax(double agentBonus, double _min, double _max){
        double _v2 = this.agentBonus + agentBonus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAgentBonus(_v2 < _min  ? _min : _v2);
    }

    public double getAgentRoyalty(){
        return agentRoyalty;
    }

    public Orders4profit setAgentRoyalty(double agentRoyalty){
        double _old = this.agentRoyalty;
        this.agentRoyalty = agentRoyalty;
        changeIt(Col.agentRoyalty, _old, agentRoyalty);
        return this;
    }

    public Orders4profit changeAgentRoyalty(double agentRoyalty){
        return setAgentRoyalty(this.agentRoyalty + agentRoyalty);
    }

    public Orders4profit changeAgentRoyaltyWithMin(double agentRoyalty, double _min){
        double _v2 = this.agentRoyalty + agentRoyalty;
        return setAgentRoyalty(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeAgentRoyaltyWithMax(double agentRoyalty, double _max){
        double _v2 = this.agentRoyalty + agentRoyalty;
        return setAgentRoyalty(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeAgentRoyaltyWithMinMax(double agentRoyalty, double _min, double _max){
        double _v2 = this.agentRoyalty + agentRoyalty;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAgentRoyalty(_v2 < _min  ? _min : _v2);
    }

    public double getLhubRoyalty(){
        return lhubRoyalty;
    }

    public Orders4profit setLhubRoyalty(double lhubRoyalty){
        double _old = this.lhubRoyalty;
        this.lhubRoyalty = lhubRoyalty;
        changeIt(Col.lhubRoyalty, _old, lhubRoyalty);
        return this;
    }

    public Orders4profit changeLhubRoyalty(double lhubRoyalty){
        return setLhubRoyalty(this.lhubRoyalty + lhubRoyalty);
    }

    public Orders4profit changeLhubRoyaltyWithMin(double lhubRoyalty, double _min){
        double _v2 = this.lhubRoyalty + lhubRoyalty;
        return setLhubRoyalty(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeLhubRoyaltyWithMax(double lhubRoyalty, double _max){
        double _v2 = this.lhubRoyalty + lhubRoyalty;
        return setLhubRoyalty(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeLhubRoyaltyWithMinMax(double lhubRoyalty, double _min, double _max){
        double _v2 = this.lhubRoyalty + lhubRoyalty;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubRoyalty(_v2 < _min  ? _min : _v2);
    }

    public double getLhubDeposit(){
        return lhubDeposit;
    }

    public Orders4profit setLhubDeposit(double lhubDeposit){
        double _old = this.lhubDeposit;
        this.lhubDeposit = lhubDeposit;
        changeIt(Col.lhubDeposit, _old, lhubDeposit);
        return this;
    }

    public Orders4profit changeLhubDeposit(double lhubDeposit){
        return setLhubDeposit(this.lhubDeposit + lhubDeposit);
    }

    public Orders4profit changeLhubDepositWithMin(double lhubDeposit, double _min){
        double _v2 = this.lhubDeposit + lhubDeposit;
        return setLhubDeposit(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeLhubDepositWithMax(double lhubDeposit, double _max){
        double _v2 = this.lhubDeposit + lhubDeposit;
        return setLhubDeposit(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeLhubDepositWithMinMax(double lhubDeposit, double _min, double _max){
        double _v2 = this.lhubDeposit + lhubDeposit;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLhubDeposit(_v2 < _min  ? _min : _v2);
    }

    public double getDevelopRoyalty(){
        return developRoyalty;
    }

    public Orders4profit setDevelopRoyalty(double developRoyalty){
        double _old = this.developRoyalty;
        this.developRoyalty = developRoyalty;
        changeIt(Col.developRoyalty, _old, developRoyalty);
        return this;
    }

    public Orders4profit changeDevelopRoyalty(double developRoyalty){
        return setDevelopRoyalty(this.developRoyalty + developRoyalty);
    }

    public Orders4profit changeDevelopRoyaltyWithMin(double developRoyalty, double _min){
        double _v2 = this.developRoyalty + developRoyalty;
        return setDevelopRoyalty(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeDevelopRoyaltyWithMax(double developRoyalty, double _max){
        double _v2 = this.developRoyalty + developRoyalty;
        return setDevelopRoyalty(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeDevelopRoyaltyWithMinMax(double developRoyalty, double _min, double _max){
        double _v2 = this.developRoyalty + developRoyalty;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDevelopRoyalty(_v2 < _min  ? _min : _v2);
    }

    public double getArtRoyalty(){
        return artRoyalty;
    }

    public Orders4profit setArtRoyalty(double artRoyalty){
        double _old = this.artRoyalty;
        this.artRoyalty = artRoyalty;
        changeIt(Col.artRoyalty, _old, artRoyalty);
        return this;
    }

    public Orders4profit changeArtRoyalty(double artRoyalty){
        return setArtRoyalty(this.artRoyalty + artRoyalty);
    }

    public Orders4profit changeArtRoyaltyWithMin(double artRoyalty, double _min){
        double _v2 = this.artRoyalty + artRoyalty;
        return setArtRoyalty(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeArtRoyaltyWithMax(double artRoyalty, double _max){
        double _v2 = this.artRoyalty + artRoyalty;
        return setArtRoyalty(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeArtRoyaltyWithMinMax(double artRoyalty, double _min, double _max){
        double _v2 = this.artRoyalty + artRoyalty;
        _v2 = _v2 > _max  ? _max : _v2;
        return setArtRoyalty(_v2 < _min  ? _min : _v2);
    }

    public double getPrice(){
        return price;
    }

    public Orders4profit setPrice(double price){
        double _old = this.price;
        this.price = price;
        changeIt(Col.price, _old, price);
        return this;
    }

    public Orders4profit changePrice(double price){
        return setPrice(this.price + price);
    }

    public Orders4profit changePriceWithMin(double price, double _min){
        double _v2 = this.price + price;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changePriceWithMax(double price, double _max){
        double _v2 = this.price + price;
        return setPrice(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changePriceWithMinMax(double price, double _min, double _max){
        double _v2 = this.price + price;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPrice(_v2 < _min  ? _min : _v2);
    }

    public double getDiscount(){
        return discount;
    }

    public Orders4profit setDiscount(double discount){
        double _old = this.discount;
        this.discount = discount;
        changeIt(Col.discount, _old, discount);
        return this;
    }

    public Orders4profit changeDiscount(double discount){
        return setDiscount(this.discount + discount);
    }

    public Orders4profit changeDiscountWithMin(double discount, double _min){
        double _v2 = this.discount + discount;
        return setDiscount(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeDiscountWithMax(double discount, double _max){
        double _v2 = this.discount + discount;
        return setDiscount(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeDiscountWithMinMax(double discount, double _min, double _max){
        double _v2 = this.discount + discount;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDiscount(_v2 < _min  ? _min : _v2);
    }

    public double getRealprice(){
        return realprice;
    }

    public Orders4profit setRealprice(double realprice){
        double _old = this.realprice;
        this.realprice = realprice;
        changeIt(Col.realprice, _old, realprice);
        return this;
    }

    public Orders4profit changeRealprice(double realprice){
        return setRealprice(this.realprice + realprice);
    }

    public Orders4profit changeRealpriceWithMin(double realprice, double _min){
        double _v2 = this.realprice + realprice;
        return setRealprice(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeRealpriceWithMax(double realprice, double _max){
        double _v2 = this.realprice + realprice;
        return setRealprice(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeRealpriceWithMinMax(double realprice, double _min, double _max){
        double _v2 = this.realprice + realprice;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRealprice(_v2 < _min  ? _min : _v2);
    }

    public boolean getIsProfit4Agent(){
        return isProfit4Agent;
    }

    public Orders4profit setIsProfit4Agent(boolean isProfit4Agent){
        boolean _old = this.isProfit4Agent;
        this.isProfit4Agent = isProfit4Agent;
        changeIt(Col.isProfit4Agent, _old, isProfit4Agent);
        return this;
    }

    public boolean getIsProfit4Lhub(){
        return isProfit4Lhub;
    }

    public Orders4profit setIsProfit4Lhub(boolean isProfit4Lhub){
        boolean _old = this.isProfit4Lhub;
        this.isProfit4Lhub = isProfit4Lhub;
        changeIt(Col.isProfit4Lhub, _old, isProfit4Lhub);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Orders4profit setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Orders4profit changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Orders4profit changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Orders4profit changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Orders4profit changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Orders4profit setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Orders4profit v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Orders4profit v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Orders4profit newOrders4profit(Integer id, String orderNo, Integer kindid, Integer custid, Integer lhubid, Integer agentid, Double agentBonus, Double agentRoyalty, Double lhubRoyalty, Double lhubDeposit, Double developRoyalty, Double artRoyalty, Double price, Double discount, Double realprice, Boolean isProfit4Agent, Boolean isProfit4Lhub, Integer status, java.util.Date createtime) {
        Orders4profit result = new Orders4profit();
        result.id = id;
        result.orderNo = orderNo;
        result.kindid = kindid;
        result.custid = custid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.agentBonus = agentBonus;
        result.agentRoyalty = agentRoyalty;
        result.lhubRoyalty = lhubRoyalty;
        result.lhubDeposit = lhubDeposit;
        result.developRoyalty = developRoyalty;
        result.artRoyalty = artRoyalty;
        result.price = price;
        result.discount = discount;
        result.realprice = realprice;
        result.isProfit4Agent = isProfit4Agent;
        result.isProfit4Lhub = isProfit4Lhub;
        result.status = status;
        result.createtime = createtime;
        return result;
    }

    public static Orders4profit newOrders4profit(Orders4profit orders4profit) {
        Orders4profit result = new Orders4profit();
        result.id = orders4profit.id;
        result.orderNo = orders4profit.orderNo;
        result.kindid = orders4profit.kindid;
        result.custid = orders4profit.custid;
        result.lhubid = orders4profit.lhubid;
        result.agentid = orders4profit.agentid;
        result.agentBonus = orders4profit.agentBonus;
        result.agentRoyalty = orders4profit.agentRoyalty;
        result.lhubRoyalty = orders4profit.lhubRoyalty;
        result.lhubDeposit = orders4profit.lhubDeposit;
        result.developRoyalty = orders4profit.developRoyalty;
        result.artRoyalty = orders4profit.artRoyalty;
        result.price = orders4profit.price;
        result.discount = orders4profit.discount;
        result.realprice = orders4profit.realprice;
        result.isProfit4Agent = orders4profit.isProfit4Agent;
        result.isProfit4Lhub = orders4profit.isProfit4Lhub;
        result.status = orders4profit.status;
        result.createtime = orders4profit.createtime;
        return result;
    }

    public Orders4profit createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getOrders4profit(){
        Orders4profit orders4profit = null; // orders4profit
        { // new and insert Orders4profit (orders4profit)
            int id = 0; 	// id
            String orderNo = ""; 	// orderNo
            int kindid = 0; 	// kindid
            int custid = 0; 	// custid
            int lhubid = 0; 	// lhubid
            int agentid = 0; 	// agentid
            double agentBonus = 0.0; 	// agentBonus
            double agentRoyalty = 0.0; 	// agentRoyalty
            double lhubRoyalty = 0.0; 	// lhubRoyalty
            double lhubDeposit = 0.0; 	// lhubDeposit
            double developRoyalty = 0.0; 	// developRoyalty
            double artRoyalty = 0.0; 	// artRoyalty
            double price = 0.0; 	// price
            double discount = 0.0; 	// discount
            double realprice = 0.0; 	// realprice
            boolean isProfit4Agent = true; 	// isProfit4Agent
            boolean isProfit4Lhub = true; 	// isProfit4Lhub
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            orders4profit = Orders4profit.newOrders4profit(id, orderNo, kindid, custid, lhubid, agentid, agentBonus, agentRoyalty, lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty, price, discount, realprice, isProfit4Agent, isProfit4Lhub, status, createtime);
        }
        orders4profit = orders4profit.insert();

        int id = orders4profit.getId(); 	// id
        String orderNo = orders4profit.getOrderNo(); 	// orderNo
        int kindid = orders4profit.getKindid(); 	// kindid
        int custid = orders4profit.getCustid(); 	// custid
        int lhubid = orders4profit.getLhubid(); 	// lhubid
        int agentid = orders4profit.getAgentid(); 	// agentid
        double agentBonus = orders4profit.getAgentBonus(); 	// agentBonus
        double agentRoyalty = orders4profit.getAgentRoyalty(); 	// agentRoyalty
        double lhubRoyalty = orders4profit.getLhubRoyalty(); 	// lhubRoyalty
        double lhubDeposit = orders4profit.getLhubDeposit(); 	// lhubDeposit
        double developRoyalty = orders4profit.getDevelopRoyalty(); 	// developRoyalty
        double artRoyalty = orders4profit.getArtRoyalty(); 	// artRoyalty
        double price = orders4profit.getPrice(); 	// price
        double discount = orders4profit.getDiscount(); 	// discount
        double realprice = orders4profit.getRealprice(); 	// realprice
        boolean isProfit4Agent = orders4profit.getIsProfit4Agent(); 	// isProfit4Agent
        boolean isProfit4Lhub = orders4profit.getIsProfit4Lhub(); 	// isProfit4Lhub
        int status = orders4profit.getStatus(); 	// status
        Date createtime = orders4profit.getCreatetime(); 	// createtime
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
        case CEn.custid:
            return custid;
        case CEn.lhubid:
            return lhubid;
        case CEn.agentid:
            return agentid;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Orders4profit setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Orders4profit setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.kindid:
            return setKindid(value2);
        case CEn.custid:
            return setCustid(value2);
        case CEn.lhubid:
            return setLhubid(value2);
        case CEn.agentid:
            return setAgentid(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Orders4profit changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Orders4profit changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.kindid:
            return changeKindid(value2);
        case CEn.custid:
            return changeCustid(value2);
        case CEn.lhubid:
            return changeLhubid(value2);
        case CEn.agentid:
            return changeAgentid(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Orders4profit changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Orders4profit changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.kindid:
            return changeKindidWithMin(value2, _min);
        case CEn.custid:
            return changeCustidWithMin(value2, _min);
        case CEn.lhubid:
            return changeLhubidWithMin(value2, _min);
        case CEn.agentid:
            return changeAgentidWithMin(value2, _min);
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
        case CEn.agentBonus:
            return agentBonus;
        case CEn.agentRoyalty:
            return agentRoyalty;
        case CEn.lhubRoyalty:
            return lhubRoyalty;
        case CEn.lhubDeposit:
            return lhubDeposit;
        case CEn.developRoyalty:
            return developRoyalty;
        case CEn.artRoyalty:
            return artRoyalty;
        case CEn.price:
            return price;
        case CEn.discount:
            return discount;
        case CEn.realprice:
            return realprice;
        }
        return 0;
    }

    public Orders4profit setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Orders4profit setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case CEn.agentBonus:
            return setAgentBonus(value2);
        case CEn.agentRoyalty:
            return setAgentRoyalty(value2);
        case CEn.lhubRoyalty:
            return setLhubRoyalty(value2);
        case CEn.lhubDeposit:
            return setLhubDeposit(value2);
        case CEn.developRoyalty:
            return setDevelopRoyalty(value2);
        case CEn.artRoyalty:
            return setArtRoyalty(value2);
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
        case CEn.orderNo:
            return orderNo;
        case CEn.kindid:
            return kindid;
        case CEn.custid:
            return custid;
        case CEn.lhubid:
            return lhubid;
        case CEn.agentid:
            return agentid;
        case CEn.agentBonus:
            return agentBonus;
        case CEn.agentRoyalty:
            return agentRoyalty;
        case CEn.lhubRoyalty:
            return lhubRoyalty;
        case CEn.lhubDeposit:
            return lhubDeposit;
        case CEn.developRoyalty:
            return developRoyalty;
        case CEn.artRoyalty:
            return artRoyalty;
        case CEn.price:
            return price;
        case CEn.discount:
            return discount;
        case CEn.realprice:
            return realprice;
        case CEn.isProfit4Agent:
            return isProfit4Agent;
        case CEn.isProfit4Lhub:
            return isProfit4Lhub;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Orders4profit setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Orders4profit setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Orders4profit setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Orders4profit setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.orderNo:
            return setOrderNo(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Orders4profit setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Orders4profit setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Orders4profit");
        result.put("id", id);
        result.put("orderNo", orderNo);
        result.put("kindid", kindid);
        result.put("custid", custid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("agentBonus", agentBonus);
        result.put("agentRoyalty", agentRoyalty);
        result.put("lhubRoyalty", lhubRoyalty);
        result.put("lhubDeposit", lhubDeposit);
        result.put("developRoyalty", developRoyalty);
        result.put("artRoyalty", artRoyalty);
        result.put("price", price);
        result.put("discount", discount);
        result.put("realprice", realprice);
        result.put("isProfit4Agent", isProfit4Agent);
        result.put("isProfit4Lhub", isProfit4Lhub);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("orderNo", orderNo);
        result.put("kindid", kindid);
        result.put("custid", custid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("agentBonus", agentBonus);
        result.put("agentRoyalty", agentRoyalty);
        result.put("lhubRoyalty", lhubRoyalty);
        result.put("lhubDeposit", lhubDeposit);
        result.put("developRoyalty", developRoyalty);
        result.put("artRoyalty", artRoyalty);
        result.put("price", price);
        result.put("discount", discount);
        result.put("realprice", realprice);
        result.put("isProfit4Agent", isProfit4Agent);
        result.put("isProfit4Lhub", isProfit4Lhub);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Orders4profit");
        result.put("id", id);
        result.put("orderNo", orderNo);
        result.put("kindid", kindid);
        result.put("custid", custid);
        result.put("lhubid", lhubid);
        result.put("agentid", agentid);
        result.put("agentBonus", agentBonus);
        result.put("agentRoyalty", agentRoyalty);
        result.put("lhubRoyalty", lhubRoyalty);
        result.put("lhubDeposit", lhubDeposit);
        result.put("developRoyalty", developRoyalty);
        result.put("artRoyalty", artRoyalty);
        result.put("price", price);
        result.put("discount", discount);
        result.put("realprice", realprice);
        result.put("isProfit4Agent", isProfit4Agent);
        result.put("isProfit4Lhub", isProfit4Lhub);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Orders4profit mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String orderNo = MapEx.getString(e, "orderNo");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer custid = MapEx.getInt(e, "custid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Double agentBonus = MapEx.getDouble(e, "agentBonus");
        Double agentRoyalty = MapEx.getDouble(e, "agentRoyalty");
        Double lhubRoyalty = MapEx.getDouble(e, "lhubRoyalty");
        Double lhubDeposit = MapEx.getDouble(e, "lhubDeposit");
        Double developRoyalty = MapEx.getDouble(e, "developRoyalty");
        Double artRoyalty = MapEx.getDouble(e, "artRoyalty");
        Double price = MapEx.getDouble(e, "price");
        Double discount = MapEx.getDouble(e, "discount");
        Double realprice = MapEx.getDouble(e, "realprice");
        Boolean isProfit4Agent = MapEx.getBoolean(e, "isProfit4Agent");
        Boolean isProfit4Lhub = MapEx.getBoolean(e, "isProfit4Lhub");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(orderNo == null) orderNo = "";
        if(kindid == null) kindid = 0;
        if(custid == null) custid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(agentBonus == null) agentBonus = 0.0;
        if(agentRoyalty == null) agentRoyalty = 0.0;
        if(lhubRoyalty == null) lhubRoyalty = 0.0;
        if(lhubDeposit == null) lhubDeposit = 0.0;
        if(developRoyalty == null) developRoyalty = 0.0;
        if(artRoyalty == null) artRoyalty = 0.0;
        if(price == null) price = 0.0;
        if(discount == null) discount = 0.0;
        if(realprice == null) realprice = 0.0;
        if(isProfit4Agent == null) isProfit4Agent = false;
        if(isProfit4Lhub == null) isProfit4Lhub = false;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setOrderNo(orderNo);
        setKindid(kindid);
        setCustid(custid);
        setLhubid(lhubid);
        setAgentid(agentid);
        setAgentBonus(agentBonus);
        setAgentRoyalty(agentRoyalty);
        setLhubRoyalty(lhubRoyalty);
        setLhubDeposit(lhubDeposit);
        setDevelopRoyalty(developRoyalty);
        setArtRoyalty(artRoyalty);
        setPrice(price);
        setDiscount(discount);
        setRealprice(realprice);
        setIsProfit4Agent(isProfit4Agent);
        setIsProfit4Lhub(isProfit4Lhub);
        setStatus(status);
        setCreatetime(createtime);

        return this;
    }

    public static final Orders4profit mapTo(Map e){
        Orders4profit result = new Orders4profit();

        Integer id = MapEx.getInt(e, "id");
        String orderNo = MapEx.getString(e, "orderNo");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer custid = MapEx.getInt(e, "custid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Double agentBonus = MapEx.getDouble(e, "agentBonus");
        Double agentRoyalty = MapEx.getDouble(e, "agentRoyalty");
        Double lhubRoyalty = MapEx.getDouble(e, "lhubRoyalty");
        Double lhubDeposit = MapEx.getDouble(e, "lhubDeposit");
        Double developRoyalty = MapEx.getDouble(e, "developRoyalty");
        Double artRoyalty = MapEx.getDouble(e, "artRoyalty");
        Double price = MapEx.getDouble(e, "price");
        Double discount = MapEx.getDouble(e, "discount");
        Double realprice = MapEx.getDouble(e, "realprice");
        Boolean isProfit4Agent = MapEx.getBoolean(e, "isProfit4Agent");
        Boolean isProfit4Lhub = MapEx.getBoolean(e, "isProfit4Lhub");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(orderNo == null) orderNo = "";
        if(kindid == null) kindid = 0;
        if(custid == null) custid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(agentBonus == null) agentBonus = 0.0;
        if(agentRoyalty == null) agentRoyalty = 0.0;
        if(lhubRoyalty == null) lhubRoyalty = 0.0;
        if(lhubDeposit == null) lhubDeposit = 0.0;
        if(developRoyalty == null) developRoyalty = 0.0;
        if(artRoyalty == null) artRoyalty = 0.0;
        if(price == null) price = 0.0;
        if(discount == null) discount = 0.0;
        if(realprice == null) realprice = 0.0;
        if(isProfit4Agent == null) isProfit4Agent = false;
        if(isProfit4Lhub == null) isProfit4Lhub = false;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.orderNo = orderNo;
        result.kindid = kindid;
        result.custid = custid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.agentBonus = agentBonus;
        result.agentRoyalty = agentRoyalty;
        result.lhubRoyalty = lhubRoyalty;
        result.lhubDeposit = lhubDeposit;
        result.developRoyalty = developRoyalty;
        result.artRoyalty = artRoyalty;
        result.price = price;
        result.discount = discount;
        result.realprice = realprice;
        result.isProfit4Agent = isProfit4Agent;
        result.isProfit4Lhub = isProfit4Lhub;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public static final Orders4profit originalTo(Map e){
        Orders4profit result = new Orders4profit();

        Integer id = MapEx.getInt(e, "id");
        String orderNo = MapEx.getString(e, "orderNo");
        Integer kindid = MapEx.getInt(e, "kindid");
        Integer custid = MapEx.getInt(e, "custid");
        Integer lhubid = MapEx.getInt(e, "lhubid");
        Integer agentid = MapEx.getInt(e, "agentid");
        Double agentBonus = MapEx.getDouble(e, "agentBonus");
        Double agentRoyalty = MapEx.getDouble(e, "agentRoyalty");
        Double lhubRoyalty = MapEx.getDouble(e, "lhubRoyalty");
        Double lhubDeposit = MapEx.getDouble(e, "lhubDeposit");
        Double developRoyalty = MapEx.getDouble(e, "developRoyalty");
        Double artRoyalty = MapEx.getDouble(e, "artRoyalty");
        Double price = MapEx.getDouble(e, "price");
        Double discount = MapEx.getDouble(e, "discount");
        Double realprice = MapEx.getDouble(e, "realprice");
        Boolean isProfit4Agent = MapEx.getBoolean(e, "isProfit4Agent");
        Boolean isProfit4Lhub = MapEx.getBoolean(e, "isProfit4Lhub");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(orderNo == null) orderNo = "";
        if(kindid == null) kindid = 0;
        if(custid == null) custid = 0;
        if(lhubid == null) lhubid = 0;
        if(agentid == null) agentid = 0;
        if(agentBonus == null) agentBonus = 0.0;
        if(agentRoyalty == null) agentRoyalty = 0.0;
        if(lhubRoyalty == null) lhubRoyalty = 0.0;
        if(lhubDeposit == null) lhubDeposit = 0.0;
        if(developRoyalty == null) developRoyalty = 0.0;
        if(artRoyalty == null) artRoyalty = 0.0;
        if(price == null) price = 0.0;
        if(discount == null) discount = 0.0;
        if(realprice == null) realprice = 0.0;
        if(isProfit4Agent == null) isProfit4Agent = false;
        if(isProfit4Lhub == null) isProfit4Lhub = false;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.orderNo = orderNo;
        result.kindid = kindid;
        result.custid = custid;
        result.lhubid = lhubid;
        result.agentid = agentid;
        result.agentBonus = agentBonus;
        result.agentRoyalty = agentRoyalty;
        result.lhubRoyalty = lhubRoyalty;
        result.lhubDeposit = lhubDeposit;
        result.developRoyalty = developRoyalty;
        result.artRoyalty = artRoyalty;
        result.price = price;
        result.discount = discount;
        result.realprice = realprice;
        result.isProfit4Agent = isProfit4Agent;
        result.isProfit4Lhub = isProfit4Lhub;
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

    public static final Orders4profit createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 19);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "orderNo", orderNo);
            writeMapEntry(out, "kindid", kindid);
            writeMapEntry(out, "custid", custid);
            writeMapEntry(out, "lhubid", lhubid);
            writeMapEntry(out, "agentid", agentid);
            writeMapEntry(out, "agentBonus", agentBonus);
            writeMapEntry(out, "agentRoyalty", agentRoyalty);
            writeMapEntry(out, "lhubRoyalty", lhubRoyalty);
            writeMapEntry(out, "lhubDeposit", lhubDeposit);
            writeMapEntry(out, "developRoyalty", developRoyalty);
            writeMapEntry(out, "artRoyalty", artRoyalty);
            writeMapEntry(out, "price", price);
            writeMapEntry(out, "discount", discount);
            writeMapEntry(out, "realprice", realprice);
            writeMapEntry(out, "isProfit4Agent", isProfit4Agent);
            writeMapEntry(out, "isProfit4Lhub", isProfit4Lhub);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Orders4profit createFor(byte[] b) throws Exception {
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
    public final Agent getAgentFkAgentid() { // agent - agentid
        return AgentEntity.getByKey(agentid);
    }

    public final int countAgentFkAgentid() { // agent - agentid
        return getAgentFkAgentid() == null ? 0 : 1;
    }

    public final Customer getCustomerFkCustid() { // customer - custid
        return CustomerEntity.getByKey(custid);
    }

    public final int countCustomerFkCustid() { // customer - custid
        return getCustomerFkCustid() == null ? 0 : 1;
    }

    public final Kind getKindFkKindid() { // kind - kindid
        return KindEntity.getByKey(kindid);
    }

    public final int countKindFkKindid() { // kind - kindid
        return getKindFkKindid() == null ? 0 : 1;
    }

    public final Learnhub getLearnhubFkLhubid() { // learnhub - lhubid
        return LearnhubEntity.getByKey(lhubid);
    }

    public final int countLearnhubFkLhubid() { // learnhub - lhubid
        return getLearnhubFkLhubid() == null ? 0 : 1;
    }

    public static final Orders4profit loadByKey(int id) {
        return Orders4profitEntity.getByKey(id);
    }

    public static final Future<Orders4profit> asyncByKey(int id) {
        return Orders4profitEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Orders4profit insert() {
        Orders4profit result = Orders4profitEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Orders4profit asyncInsert() {
        Orders4profit result = Orders4profitEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Orders4profit insert2() {
        return Orders4profitEntity.insert2(this);
    }

    public final Orders4profit asyncInsert2() {
        Orders4profit result = Orders4profitEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Orders4profit update() {
        return Orders4profitEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Orders4profitEntity.asyncUpdate( this );
        return true;
    }

    public final Orders4profit insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Orders4profitEntity.delete(id);
    }

    public final void asyncDelete() {
        Orders4profitEntity.asyncDelete(id);
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

    public Orders4profit clone2() {
        try{
            return (Orders4profit) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
