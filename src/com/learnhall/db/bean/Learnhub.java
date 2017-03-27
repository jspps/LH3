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

//learnhall3_design - learnhub
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Learnhub extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 797476157; // com.learnhall.db.bean.Learnhub

    public static String TABLENAME = "learnhub";

    public static final String primary = "lhid";

    public static final class Col { public static final String lhid = "lhid"; public static final String accountid = "accountid"; public static final String name = "name"; public static final String type = "type"; public static final String codeid = "codeid"; public static final String province = "province"; public static final String city = "city"; public static final String seat = "seat"; public static final String qq = "qq"; public static final String uname = "uname"; public static final String salesmode = "salesmode"; public static final String img4jg = "img4jg"; public static final String volume = "volume"; public static final String moneyAll = "moneyAll"; public static final String moneyCur = "moneyCur"; public static final String isselfadmin = "isselfadmin"; public static final String status = "status"; public static final String tiku = "tiku"; public static final String quality = "quality"; public static final String wrong = "wrong"; public static final String examineStatus = "examineStatus"; public static final String examineDes = "examineDes"; public static final String createtime = "createtime"; public static final String imgr4Cover = "imgr4Cover"; public static final String descr = "descr"; public static final String alipay = "alipay"; public static final String isVerifyAlipay = "isVerifyAlipay"; public static final String img4idface = "img4idface"; public static final String img4idback = "img4idback"; public static final String img4logo = "img4logo";  }
    public static final class CEn { public static final String lhid = "lhid"; public static final String accountid = "accountid"; public static final String name = "name"; public static final String type = "type"; public static final String codeid = "codeid"; public static final String province = "province"; public static final String city = "city"; public static final String seat = "seat"; public static final String qq = "qq"; public static final String uname = "uname"; public static final String salesmode = "salesmode"; public static final String img4jg = "img4jg"; public static final String volume = "volume"; public static final String moneyAll = "moneyAll"; public static final String moneyCur = "moneyCur"; public static final String isselfadmin = "isselfadmin"; public static final String status = "status"; public static final String tiku = "tiku"; public static final String quality = "quality"; public static final String wrong = "wrong"; public static final String examineStatus = "examineStatus"; public static final String examineDes = "examineDes"; public static final String createtime = "createtime"; public static final String imgr4Cover = "imgr4Cover"; public static final String descr = "descr"; public static final String alipay = "alipay"; public static final String isVerifyAlipay = "isVerifyAlipay"; public static final String img4idface = "img4idface"; public static final String img4idback = "img4idback"; public static final String img4logo = "img4logo";  }
    public static final String[] carrays ={"lhid", "accountid", "name", "type", "codeid", "province", "city", "seat", "qq", "uname", "salesmode", "img4jg", "volume", "moneyAll", "moneyCur", "isselfadmin", "status", "tiku", "quality", "wrong", "examineStatus", "examineDes", "createtime", "imgr4Cover", "descr", "alipay", "isVerifyAlipay", "img4idface", "img4idback", "img4logo"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "VARCHAR", "VARCHAR", "TINYTEXT", "VARCHAR", "VARCHAR", "INT", "TINYTEXT", "INT", "DOUBLE", "DOUBLE", "BIT", "INT", "INT", "INT", "INT", "INT", "TEXT", "DATETIME", "TINYTEXT", "TEXT", "TINYTEXT", "BIT", "TINYTEXT", "TINYTEXT", "TINYTEXT"};


    public int lhid;
    public int accountid;
    public String name;
    public int type;
    public String codeid;
    public String province;
    public String city;
    public String seat;
    public String qq;
    public String uname;
    public int salesmode;
    public String img4jg;
    public int volume;
    public double moneyAll;
    public double moneyCur;
    public boolean isselfadmin;
    public int status;
    public int tiku;
    public int quality;
    public int wrong;
    public int examineStatus;
    public String examineDes;
    public java.util.Date createtime;
    public String imgr4Cover;
    public String descr;
    public String alipay;
    public boolean isVerifyAlipay;
    public String img4idface;
    public String img4idback;
    public String img4logo;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return lhid;
    }

    public static String _key(int lhid) {
        return PStr.b(TABLENAME).a("-").e(lhid);
    }

    public int getLhid(){
        return lhid;
    }

    public Learnhub setLhid(int lhid){
        this.lhid = lhid;
        return this;
    }

    public int getAccountid(){
        return accountid;
    }

    public Learnhub setAccountid(int accountid){
        int _old = this.accountid;
        this.accountid = accountid;
        changeIt(Col.accountid, _old, accountid);
        return this;
    }

    public Learnhub changeAccountid(int accountid){
        return setAccountid(this.accountid + accountid);
    }

    public Learnhub changeAccountidWithMin(int accountid, int _min){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeAccountidWithMax(int accountid, int _max){
        int _v2 = this.accountid + accountid;
        return setAccountid(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeAccountidWithMinMax(int accountid, int _min, int _max){
        int _v2 = this.accountid + accountid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAccountid(_v2 < _min  ? _min : _v2);
    }

    public String getName(){
        return name;
    }

    public Learnhub setName(String name){
        String _old = this.name;
        this.name = name;
        changeIt(Col.name, _old, name);
        return this;
    }

    public int getType(){
        return type;
    }

    public Learnhub setType(int type){
        int _old = this.type;
        this.type = type;
        changeIt(Col.type, _old, type);
        return this;
    }

    public Learnhub changeType(int type){
        return setType(this.type + type);
    }

    public Learnhub changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public String getCodeid(){
        return codeid;
    }

    public Learnhub setCodeid(String codeid){
        String _old = this.codeid;
        this.codeid = codeid;
        changeIt(Col.codeid, _old, codeid);
        return this;
    }

    public String getProvince(){
        return province;
    }

    public Learnhub setProvince(String province){
        String _old = this.province;
        this.province = province;
        changeIt(Col.province, _old, province);
        return this;
    }

    public String getCity(){
        return city;
    }

    public Learnhub setCity(String city){
        String _old = this.city;
        this.city = city;
        changeIt(Col.city, _old, city);
        return this;
    }

    public String getSeat(){
        return seat;
    }

    public Learnhub setSeat(String seat){
        String _old = this.seat;
        this.seat = seat;
        changeIt(Col.seat, _old, seat);
        return this;
    }

    public String getQq(){
        return qq;
    }

    public Learnhub setQq(String qq){
        String _old = this.qq;
        this.qq = qq;
        changeIt(Col.qq, _old, qq);
        return this;
    }

    public String getUname(){
        return uname;
    }

    public Learnhub setUname(String uname){
        String _old = this.uname;
        this.uname = uname;
        changeIt(Col.uname, _old, uname);
        return this;
    }

    public int getSalesmode(){
        return salesmode;
    }

    public Learnhub setSalesmode(int salesmode){
        int _old = this.salesmode;
        this.salesmode = salesmode;
        changeIt(Col.salesmode, _old, salesmode);
        return this;
    }

    public Learnhub changeSalesmode(int salesmode){
        return setSalesmode(this.salesmode + salesmode);
    }

    public Learnhub changeSalesmodeWithMin(int salesmode, int _min){
        int _v2 = this.salesmode + salesmode;
        return setSalesmode(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeSalesmodeWithMax(int salesmode, int _max){
        int _v2 = this.salesmode + salesmode;
        return setSalesmode(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeSalesmodeWithMinMax(int salesmode, int _min, int _max){
        int _v2 = this.salesmode + salesmode;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSalesmode(_v2 < _min  ? _min : _v2);
    }

    public String getImg4jg(){
        return img4jg;
    }

    public Learnhub setImg4jg(String img4jg){
        String _old = this.img4jg;
        this.img4jg = img4jg;
        changeIt(Col.img4jg, _old, img4jg);
        return this;
    }

    public int getVolume(){
        return volume;
    }

    public Learnhub setVolume(int volume){
        int _old = this.volume;
        this.volume = volume;
        changeIt(Col.volume, _old, volume);
        return this;
    }

    public Learnhub changeVolume(int volume){
        return setVolume(this.volume + volume);
    }

    public Learnhub changeVolumeWithMin(int volume, int _min){
        int _v2 = this.volume + volume;
        return setVolume(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeVolumeWithMax(int volume, int _max){
        int _v2 = this.volume + volume;
        return setVolume(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeVolumeWithMinMax(int volume, int _min, int _max){
        int _v2 = this.volume + volume;
        _v2 = _v2 > _max  ? _max : _v2;
        return setVolume(_v2 < _min  ? _min : _v2);
    }

    public double getMoneyAll(){
        return moneyAll;
    }

    public Learnhub setMoneyAll(double moneyAll){
        double _old = this.moneyAll;
        this.moneyAll = moneyAll;
        changeIt(Col.moneyAll, _old, moneyAll);
        return this;
    }

    public Learnhub changeMoneyAll(double moneyAll){
        return setMoneyAll(this.moneyAll + moneyAll);
    }

    public Learnhub changeMoneyAllWithMin(double moneyAll, double _min){
        double _v2 = this.moneyAll + moneyAll;
        return setMoneyAll(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeMoneyAllWithMax(double moneyAll, double _max){
        double _v2 = this.moneyAll + moneyAll;
        return setMoneyAll(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeMoneyAllWithMinMax(double moneyAll, double _min, double _max){
        double _v2 = this.moneyAll + moneyAll;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyAll(_v2 < _min  ? _min : _v2);
    }

    public double getMoneyCur(){
        return moneyCur;
    }

    public Learnhub setMoneyCur(double moneyCur){
        double _old = this.moneyCur;
        this.moneyCur = moneyCur;
        changeIt(Col.moneyCur, _old, moneyCur);
        return this;
    }

    public Learnhub changeMoneyCur(double moneyCur){
        return setMoneyCur(this.moneyCur + moneyCur);
    }

    public Learnhub changeMoneyCurWithMin(double moneyCur, double _min){
        double _v2 = this.moneyCur + moneyCur;
        return setMoneyCur(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeMoneyCurWithMax(double moneyCur, double _max){
        double _v2 = this.moneyCur + moneyCur;
        return setMoneyCur(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeMoneyCurWithMinMax(double moneyCur, double _min, double _max){
        double _v2 = this.moneyCur + moneyCur;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyCur(_v2 < _min  ? _min : _v2);
    }

    public boolean getIsselfadmin(){
        return isselfadmin;
    }

    public Learnhub setIsselfadmin(boolean isselfadmin){
        boolean _old = this.isselfadmin;
        this.isselfadmin = isselfadmin;
        changeIt(Col.isselfadmin, _old, isselfadmin);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Learnhub setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Learnhub changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Learnhub changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int getTiku(){
        return tiku;
    }

    public Learnhub setTiku(int tiku){
        int _old = this.tiku;
        this.tiku = tiku;
        changeIt(Col.tiku, _old, tiku);
        return this;
    }

    public Learnhub changeTiku(int tiku){
        return setTiku(this.tiku + tiku);
    }

    public Learnhub changeTikuWithMin(int tiku, int _min){
        int _v2 = this.tiku + tiku;
        return setTiku(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeTikuWithMax(int tiku, int _max){
        int _v2 = this.tiku + tiku;
        return setTiku(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeTikuWithMinMax(int tiku, int _min, int _max){
        int _v2 = this.tiku + tiku;
        _v2 = _v2 > _max  ? _max : _v2;
        return setTiku(_v2 < _min  ? _min : _v2);
    }

    public int getQuality(){
        return quality;
    }

    public Learnhub setQuality(int quality){
        int _old = this.quality;
        this.quality = quality;
        changeIt(Col.quality, _old, quality);
        return this;
    }

    public Learnhub changeQuality(int quality){
        return setQuality(this.quality + quality);
    }

    public Learnhub changeQualityWithMin(int quality, int _min){
        int _v2 = this.quality + quality;
        return setQuality(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeQualityWithMax(int quality, int _max){
        int _v2 = this.quality + quality;
        return setQuality(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeQualityWithMinMax(int quality, int _min, int _max){
        int _v2 = this.quality + quality;
        _v2 = _v2 > _max  ? _max : _v2;
        return setQuality(_v2 < _min  ? _min : _v2);
    }

    public int getWrong(){
        return wrong;
    }

    public Learnhub setWrong(int wrong){
        int _old = this.wrong;
        this.wrong = wrong;
        changeIt(Col.wrong, _old, wrong);
        return this;
    }

    public Learnhub changeWrong(int wrong){
        return setWrong(this.wrong + wrong);
    }

    public Learnhub changeWrongWithMin(int wrong, int _min){
        int _v2 = this.wrong + wrong;
        return setWrong(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeWrongWithMax(int wrong, int _max){
        int _v2 = this.wrong + wrong;
        return setWrong(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeWrongWithMinMax(int wrong, int _min, int _max){
        int _v2 = this.wrong + wrong;
        _v2 = _v2 > _max  ? _max : _v2;
        return setWrong(_v2 < _min  ? _min : _v2);
    }

    public int getExamineStatus(){
        return examineStatus;
    }

    public Learnhub setExamineStatus(int examineStatus){
        int _old = this.examineStatus;
        this.examineStatus = examineStatus;
        changeIt(Col.examineStatus, _old, examineStatus);
        return this;
    }

    public Learnhub changeExamineStatus(int examineStatus){
        return setExamineStatus(this.examineStatus + examineStatus);
    }

    public Learnhub changeExamineStatusWithMin(int examineStatus, int _min){
        int _v2 = this.examineStatus + examineStatus;
        return setExamineStatus(_v2 < _min  ? _min : _v2);
    }

    public Learnhub changeExamineStatusWithMax(int examineStatus, int _max){
        int _v2 = this.examineStatus + examineStatus;
        return setExamineStatus(_v2 > _max  ? _max : _v2);
    }

    public Learnhub changeExamineStatusWithMinMax(int examineStatus, int _min, int _max){
        int _v2 = this.examineStatus + examineStatus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExamineStatus(_v2 < _min  ? _min : _v2);
    }

    public String getExamineDes(){
        return examineDes;
    }

    public Learnhub setExamineDes(String examineDes){
        String _old = this.examineDes;
        this.examineDes = examineDes;
        changeIt(Col.examineDes, _old, examineDes);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Learnhub setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public String getImgr4Cover(){
        return imgr4Cover;
    }

    public Learnhub setImgr4Cover(String imgr4Cover){
        String _old = this.imgr4Cover;
        this.imgr4Cover = imgr4Cover;
        changeIt(Col.imgr4Cover, _old, imgr4Cover);
        return this;
    }

    public String getDescr(){
        return descr;
    }

    public Learnhub setDescr(String descr){
        String _old = this.descr;
        this.descr = descr;
        changeIt(Col.descr, _old, descr);
        return this;
    }

    public String getAlipay(){
        return alipay;
    }

    public Learnhub setAlipay(String alipay){
        String _old = this.alipay;
        this.alipay = alipay;
        changeIt(Col.alipay, _old, alipay);
        return this;
    }

    public boolean getIsVerifyAlipay(){
        return isVerifyAlipay;
    }

    public Learnhub setIsVerifyAlipay(boolean isVerifyAlipay){
        boolean _old = this.isVerifyAlipay;
        this.isVerifyAlipay = isVerifyAlipay;
        changeIt(Col.isVerifyAlipay, _old, isVerifyAlipay);
        return this;
    }

    public String getImg4idface(){
        return img4idface;
    }

    public Learnhub setImg4idface(String img4idface){
        String _old = this.img4idface;
        this.img4idface = img4idface;
        changeIt(Col.img4idface, _old, img4idface);
        return this;
    }

    public String getImg4idback(){
        return img4idback;
    }

    public Learnhub setImg4idback(String img4idback){
        String _old = this.img4idback;
        this.img4idback = img4idback;
        changeIt(Col.img4idback, _old, img4idback);
        return this;
    }

    public String getImg4logo(){
        return img4logo;
    }

    public Learnhub setImg4logo(String img4logo){
        String _old = this.img4logo;
        this.img4logo = img4logo;
        changeIt(Col.img4logo, _old, img4logo);
        return this;
    }

    public int compareTo(Learnhub v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Learnhub v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Learnhub newLearnhub(Integer lhid, Integer accountid, String name, Integer type, String codeid, String province, String city, String seat, String qq, String uname, Integer salesmode, String img4jg, Integer volume, Double moneyAll, Double moneyCur, Boolean isselfadmin, Integer status, Integer tiku, Integer quality, Integer wrong, Integer examineStatus, String examineDes, java.util.Date createtime, String imgr4Cover, String descr, String alipay, Boolean isVerifyAlipay, String img4idface, String img4idback, String img4logo) {
        Learnhub result = new Learnhub();
        result.lhid = lhid;
        result.accountid = accountid;
        result.name = name;
        result.type = type;
        result.codeid = codeid;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.qq = qq;
        result.uname = uname;
        result.salesmode = salesmode;
        result.img4jg = img4jg;
        result.volume = volume;
        result.moneyAll = moneyAll;
        result.moneyCur = moneyCur;
        result.isselfadmin = isselfadmin;
        result.status = status;
        result.tiku = tiku;
        result.quality = quality;
        result.wrong = wrong;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.createtime = createtime;
        result.imgr4Cover = imgr4Cover;
        result.descr = descr;
        result.alipay = alipay;
        result.isVerifyAlipay = isVerifyAlipay;
        result.img4idface = img4idface;
        result.img4idback = img4idback;
        result.img4logo = img4logo;
        return result;
    }

    public static Learnhub newLearnhub(Learnhub learnhub) {
        Learnhub result = new Learnhub();
        result.lhid = learnhub.lhid;
        result.accountid = learnhub.accountid;
        result.name = learnhub.name;
        result.type = learnhub.type;
        result.codeid = learnhub.codeid;
        result.province = learnhub.province;
        result.city = learnhub.city;
        result.seat = learnhub.seat;
        result.qq = learnhub.qq;
        result.uname = learnhub.uname;
        result.salesmode = learnhub.salesmode;
        result.img4jg = learnhub.img4jg;
        result.volume = learnhub.volume;
        result.moneyAll = learnhub.moneyAll;
        result.moneyCur = learnhub.moneyCur;
        result.isselfadmin = learnhub.isselfadmin;
        result.status = learnhub.status;
        result.tiku = learnhub.tiku;
        result.quality = learnhub.quality;
        result.wrong = learnhub.wrong;
        result.examineStatus = learnhub.examineStatus;
        result.examineDes = learnhub.examineDes;
        result.createtime = learnhub.createtime;
        result.imgr4Cover = learnhub.imgr4Cover;
        result.descr = learnhub.descr;
        result.alipay = learnhub.alipay;
        result.isVerifyAlipay = learnhub.isVerifyAlipay;
        result.img4idface = learnhub.img4idface;
        result.img4idback = learnhub.img4idback;
        result.img4logo = learnhub.img4logo;
        return result;
    }

    public Learnhub createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getLearnhub(){
        Learnhub learnhub = null; // learnhub
        { // new and insert Learnhub (learnhub)
            int lhid = 0; 	// lhid
            int accountid = 0; 	// accountid
            String name = ""; 	// name
            int type = 0; 	// type
            String codeid = ""; 	// codeid
            String province = ""; 	// province
            String city = ""; 	// city
            String seat = ""; 	// seat
            String qq = ""; 	// qq
            String uname = ""; 	// uname
            int salesmode = 0; 	// salesmode
            String img4jg = ""; 	// img4jg
            int volume = 0; 	// volume
            double moneyAll = 0.0; 	// moneyAll
            double moneyCur = 0.0; 	// moneyCur
            boolean isselfadmin = true; 	// isselfadmin
            int status = 0; 	// status
            int tiku = 0; 	// tiku
            int quality = 0; 	// quality
            int wrong = 0; 	// wrong
            int examineStatus = 0; 	// examineStatus
            String examineDes = ""; 	// examineDes
            Date createtime = new Date(); 	// createtime
            String imgr4Cover = ""; 	// imgr4Cover
            String descr = ""; 	// descr
            String alipay = ""; 	// alipay
            boolean isVerifyAlipay = true; 	// isVerifyAlipay
            String img4idface = ""; 	// img4idface
            String img4idback = ""; 	// img4idback
            String img4logo = ""; 	// img4logo
            learnhub = Learnhub.newLearnhub(lhid, accountid, name, type, codeid, province, city, seat, qq, uname, salesmode, img4jg, volume, moneyAll, moneyCur, isselfadmin, status, tiku, quality, wrong, examineStatus, examineDes, createtime, imgr4Cover, descr, alipay, isVerifyAlipay, img4idface, img4idback, img4logo);
        }
        learnhub = learnhub.insert();

        int lhid = learnhub.getLhid(); 	// lhid
        int accountid = learnhub.getAccountid(); 	// accountid
        String name = learnhub.getName(); 	// name
        int type = learnhub.getType(); 	// type
        String codeid = learnhub.getCodeid(); 	// codeid
        String province = learnhub.getProvince(); 	// province
        String city = learnhub.getCity(); 	// city
        String seat = learnhub.getSeat(); 	// seat
        String qq = learnhub.getQq(); 	// qq
        String uname = learnhub.getUname(); 	// uname
        int salesmode = learnhub.getSalesmode(); 	// salesmode
        String img4jg = learnhub.getImg4jg(); 	// img4jg
        int volume = learnhub.getVolume(); 	// volume
        double moneyAll = learnhub.getMoneyAll(); 	// moneyAll
        double moneyCur = learnhub.getMoneyCur(); 	// moneyCur
        boolean isselfadmin = learnhub.getIsselfadmin(); 	// isselfadmin
        int status = learnhub.getStatus(); 	// status
        int tiku = learnhub.getTiku(); 	// tiku
        int quality = learnhub.getQuality(); 	// quality
        int wrong = learnhub.getWrong(); 	// wrong
        int examineStatus = learnhub.getExamineStatus(); 	// examineStatus
        String examineDes = learnhub.getExamineDes(); 	// examineDes
        Date createtime = learnhub.getCreatetime(); 	// createtime
        String imgr4Cover = learnhub.getImgr4Cover(); 	// imgr4Cover
        String descr = learnhub.getDescr(); 	// descr
        String alipay = learnhub.getAlipay(); 	// alipay
        boolean isVerifyAlipay = learnhub.getIsVerifyAlipay(); 	// isVerifyAlipay
        String img4idface = learnhub.getImg4idface(); 	// img4idface
        String img4idback = learnhub.getImg4idback(); 	// img4idback
        String img4logo = learnhub.getImg4logo(); 	// img4logo
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.lhid:
            return lhid;
        case CEn.accountid:
            return accountid;
        case CEn.type:
            return type;
        case CEn.salesmode:
            return salesmode;
        case CEn.volume:
            return volume;
        case CEn.status:
            return status;
        case CEn.tiku:
            return tiku;
        case CEn.quality:
            return quality;
        case CEn.wrong:
            return wrong;
        case CEn.examineStatus:
            return examineStatus;
        }
        return 0;
    }

    public Learnhub setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Learnhub setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.lhid:
            return setLhid(value2);
        case CEn.accountid:
            return setAccountid(value2);
        case CEn.type:
            return setType(value2);
        case CEn.salesmode:
            return setSalesmode(value2);
        case CEn.volume:
            return setVolume(value2);
        case CEn.status:
            return setStatus(value2);
        case CEn.tiku:
            return setTiku(value2);
        case CEn.quality:
            return setQuality(value2);
        case CEn.wrong:
            return setWrong(value2);
        case CEn.examineStatus:
            return setExamineStatus(value2);
        }
        return this;
    }

    public Learnhub changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Learnhub changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.accountid:
            return changeAccountid(value2);
        case CEn.type:
            return changeType(value2);
        case CEn.salesmode:
            return changeSalesmode(value2);
        case CEn.volume:
            return changeVolume(value2);
        case CEn.status:
            return changeStatus(value2);
        case CEn.tiku:
            return changeTiku(value2);
        case CEn.quality:
            return changeQuality(value2);
        case CEn.wrong:
            return changeWrong(value2);
        case CEn.examineStatus:
            return changeExamineStatus(value2);
        }
        return this;
    }

    public Learnhub changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Learnhub changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.accountid:
            return changeAccountidWithMin(value2, _min);
        case CEn.type:
            return changeTypeWithMin(value2, _min);
        case CEn.salesmode:
            return changeSalesmodeWithMin(value2, _min);
        case CEn.volume:
            return changeVolumeWithMin(value2, _min);
        case CEn.status:
            return changeStatusWithMin(value2, _min);
        case CEn.tiku:
            return changeTikuWithMin(value2, _min);
        case CEn.quality:
            return changeQualityWithMin(value2, _min);
        case CEn.wrong:
            return changeWrongWithMin(value2, _min);
        case CEn.examineStatus:
            return changeExamineStatusWithMin(value2, _min);
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

    public Learnhub setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Learnhub setDouble(String fieldEn, double value2) {
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
        case CEn.codeid: 
            return codeid;
        case CEn.province: 
            return province;
        case CEn.city: 
            return city;
        case CEn.seat: 
            return seat;
        case CEn.qq: 
            return qq;
        case CEn.uname: 
            return uname;
        case CEn.img4jg: 
            return img4jg;
        case CEn.examineDes: 
            return examineDes;
        case CEn.imgr4Cover: 
            return imgr4Cover;
        case CEn.descr: 
            return descr;
        case CEn.alipay: 
            return alipay;
        case CEn.img4idface: 
            return img4idface;
        case CEn.img4idback: 
            return img4idback;
        case CEn.img4logo: 
            return img4logo;
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
        case CEn.lhid:
            return lhid;
        case CEn.accountid:
            return accountid;
        case CEn.name:
            return name;
        case CEn.type:
            return type;
        case CEn.codeid:
            return codeid;
        case CEn.province:
            return province;
        case CEn.city:
            return city;
        case CEn.seat:
            return seat;
        case CEn.qq:
            return qq;
        case CEn.uname:
            return uname;
        case CEn.salesmode:
            return salesmode;
        case CEn.img4jg:
            return img4jg;
        case CEn.volume:
            return volume;
        case CEn.moneyAll:
            return moneyAll;
        case CEn.moneyCur:
            return moneyCur;
        case CEn.isselfadmin:
            return isselfadmin;
        case CEn.status:
            return status;
        case CEn.tiku:
            return tiku;
        case CEn.quality:
            return quality;
        case CEn.wrong:
            return wrong;
        case CEn.examineStatus:
            return examineStatus;
        case CEn.examineDes:
            return examineDes;
        case CEn.createtime:
            return createtime;
        case CEn.imgr4Cover:
            return imgr4Cover;
        case CEn.descr:
            return descr;
        case CEn.alipay:
            return alipay;
        case CEn.isVerifyAlipay:
            return isVerifyAlipay;
        case CEn.img4idface:
            return img4idface;
        case CEn.img4idback:
            return img4idback;
        case CEn.img4logo:
            return img4logo;
        }
        return null;
    }

    public Learnhub setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Learnhub setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Learnhub setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Learnhub setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.name:
            return setName(value2);
        case CEn.codeid:
            return setCodeid(value2);
        case CEn.province:
            return setProvince(value2);
        case CEn.city:
            return setCity(value2);
        case CEn.seat:
            return setSeat(value2);
        case CEn.qq:
            return setQq(value2);
        case CEn.uname:
            return setUname(value2);
        case CEn.img4jg:
            return setImg4jg(value2);
        case CEn.examineDes:
            return setExamineDes(value2);
        case CEn.imgr4Cover:
            return setImgr4Cover(value2);
        case CEn.descr:
            return setDescr(value2);
        case CEn.alipay:
            return setAlipay(value2);
        case CEn.img4idface:
            return setImg4idface(value2);
        case CEn.img4idback:
            return setImg4idback(value2);
        case CEn.img4logo:
            return setImg4logo(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Learnhub setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Learnhub setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Learnhub");
        result.put("lhid", lhid);
        result.put("accountid", accountid);
        result.put("name", name);
        result.put("type", type);
        result.put("codeid", codeid);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("qq", qq);
        result.put("uname", uname);
        result.put("salesmode", salesmode);
        result.put("img4jg", img4jg);
        result.put("volume", volume);
        result.put("moneyAll", moneyAll);
        result.put("moneyCur", moneyCur);
        result.put("isselfadmin", isselfadmin);
        result.put("status", status);
        result.put("tiku", tiku);
        result.put("quality", quality);
        result.put("wrong", wrong);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("createtime", createtime);
        result.put("imgr4Cover", imgr4Cover);
        result.put("descr", descr);
        result.put("alipay", alipay);
        result.put("isVerifyAlipay", isVerifyAlipay);
        result.put("img4idface", img4idface);
        result.put("img4idback", img4idback);
        result.put("img4logo", img4logo);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("lhid", lhid);
        result.put("accountid", accountid);
        result.put("name", name);
        result.put("type", type);
        result.put("codeid", codeid);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("qq", qq);
        result.put("uname", uname);
        result.put("salesmode", salesmode);
        result.put("img4jg", img4jg);
        result.put("volume", volume);
        result.put("moneyAll", moneyAll);
        result.put("moneyCur", moneyCur);
        result.put("isselfadmin", isselfadmin);
        result.put("status", status);
        result.put("tiku", tiku);
        result.put("quality", quality);
        result.put("wrong", wrong);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("createtime", createtime);
        result.put("imgr4Cover", imgr4Cover);
        result.put("descr", descr);
        result.put("alipay", alipay);
        result.put("isVerifyAlipay", isVerifyAlipay);
        result.put("img4idface", img4idface);
        result.put("img4idback", img4idback);
        result.put("img4logo", img4logo);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Learnhub");
        result.put("lhid", lhid);
        result.put("accountid", accountid);
        result.put("name", name);
        result.put("type", type);
        result.put("codeid", codeid);
        result.put("province", province);
        result.put("city", city);
        result.put("seat", seat);
        result.put("qq", qq);
        result.put("uname", uname);
        result.put("salesmode", salesmode);
        result.put("img4jg", img4jg);
        result.put("volume", volume);
        result.put("moneyAll", moneyAll);
        result.put("moneyCur", moneyCur);
        result.put("isselfadmin", isselfadmin);
        result.put("status", status);
        result.put("tiku", tiku);
        result.put("quality", quality);
        result.put("wrong", wrong);
        result.put("examineStatus", examineStatus);
        result.put("examineDes", examineDes);
        result.put("createtime", createtime);
        result.put("imgr4Cover", imgr4Cover);
        result.put("descr", descr);
        result.put("alipay", alipay);
        result.put("isVerifyAlipay", isVerifyAlipay);
        result.put("img4idface", img4idface);
        result.put("img4idback", img4idback);
        result.put("img4logo", img4logo);
        return result;
    }

    public Learnhub mapToObject(Map e){
        Integer lhid = MapEx.getInt(e, "lhid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String name = MapEx.getString(e, "name");
        Integer type = MapEx.getInt(e, "type");
        String codeid = MapEx.getString(e, "codeid");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String qq = MapEx.getString(e, "qq");
        String uname = MapEx.getString(e, "uname");
        Integer salesmode = MapEx.getInt(e, "salesmode");
        String img4jg = MapEx.getString(e, "img4jg");
        Integer volume = MapEx.getInt(e, "volume");
        Double moneyAll = MapEx.getDouble(e, "moneyAll");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        Boolean isselfadmin = MapEx.getBoolean(e, "isselfadmin");
        Integer status = MapEx.getInt(e, "status");
        Integer tiku = MapEx.getInt(e, "tiku");
        Integer quality = MapEx.getInt(e, "quality");
        Integer wrong = MapEx.getInt(e, "wrong");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String imgr4Cover = MapEx.getString(e, "imgr4Cover");
        String descr = MapEx.getString(e, "descr");
        String alipay = MapEx.getString(e, "alipay");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");
        String img4idface = MapEx.getString(e, "img4idface");
        String img4idback = MapEx.getString(e, "img4idback");
        String img4logo = MapEx.getString(e, "img4logo");

        if(lhid == null) lhid = 0;
        if(accountid == null) accountid = 0;
        if(name == null) name = "";
        if(type == null) type = 0;
        if(codeid == null) codeid = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(qq == null) qq = "";
        if(uname == null) uname = "";
        if(salesmode == null) salesmode = 0;
        if(img4jg == null) img4jg = "";
        if(volume == null) volume = 0;
        if(moneyAll == null) moneyAll = 0.0;
        if(moneyCur == null) moneyCur = 0.0;
        if(isselfadmin == null) isselfadmin = false;
        if(status == null) status = 0;
        if(tiku == null) tiku = 0;
        if(quality == null) quality = 0;
        if(wrong == null) wrong = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(createtime == null) createtime = new java.util.Date();
        if(imgr4Cover == null) imgr4Cover = "";
        if(descr == null) descr = "";
        if(alipay == null) alipay = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;
        if(img4idface == null) img4idface = "";
        if(img4idback == null) img4idback = "";
        if(img4logo == null) img4logo = "";

        setLhid(lhid);
        setAccountid(accountid);
        setName(name);
        setType(type);
        setCodeid(codeid);
        setProvince(province);
        setCity(city);
        setSeat(seat);
        setQq(qq);
        setUname(uname);
        setSalesmode(salesmode);
        setImg4jg(img4jg);
        setVolume(volume);
        setMoneyAll(moneyAll);
        setMoneyCur(moneyCur);
        setIsselfadmin(isselfadmin);
        setStatus(status);
        setTiku(tiku);
        setQuality(quality);
        setWrong(wrong);
        setExamineStatus(examineStatus);
        setExamineDes(examineDes);
        setCreatetime(createtime);
        setImgr4Cover(imgr4Cover);
        setDescr(descr);
        setAlipay(alipay);
        setIsVerifyAlipay(isVerifyAlipay);
        setImg4idface(img4idface);
        setImg4idback(img4idback);
        setImg4logo(img4logo);

        return this;
    }

    public static final Learnhub mapTo(Map e){
        Learnhub result = new Learnhub();

        Integer lhid = MapEx.getInt(e, "lhid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String name = MapEx.getString(e, "name");
        Integer type = MapEx.getInt(e, "type");
        String codeid = MapEx.getString(e, "codeid");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String qq = MapEx.getString(e, "qq");
        String uname = MapEx.getString(e, "uname");
        Integer salesmode = MapEx.getInt(e, "salesmode");
        String img4jg = MapEx.getString(e, "img4jg");
        Integer volume = MapEx.getInt(e, "volume");
        Double moneyAll = MapEx.getDouble(e, "moneyAll");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        Boolean isselfadmin = MapEx.getBoolean(e, "isselfadmin");
        Integer status = MapEx.getInt(e, "status");
        Integer tiku = MapEx.getInt(e, "tiku");
        Integer quality = MapEx.getInt(e, "quality");
        Integer wrong = MapEx.getInt(e, "wrong");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String imgr4Cover = MapEx.getString(e, "imgr4Cover");
        String descr = MapEx.getString(e, "descr");
        String alipay = MapEx.getString(e, "alipay");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");
        String img4idface = MapEx.getString(e, "img4idface");
        String img4idback = MapEx.getString(e, "img4idback");
        String img4logo = MapEx.getString(e, "img4logo");

        if(lhid == null) lhid = 0;
        if(accountid == null) accountid = 0;
        if(name == null) name = "";
        if(type == null) type = 0;
        if(codeid == null) codeid = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(qq == null) qq = "";
        if(uname == null) uname = "";
        if(salesmode == null) salesmode = 0;
        if(img4jg == null) img4jg = "";
        if(volume == null) volume = 0;
        if(moneyAll == null) moneyAll = 0.0;
        if(moneyCur == null) moneyCur = 0.0;
        if(isselfadmin == null) isselfadmin = false;
        if(status == null) status = 0;
        if(tiku == null) tiku = 0;
        if(quality == null) quality = 0;
        if(wrong == null) wrong = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(createtime == null) createtime = new java.util.Date();
        if(imgr4Cover == null) imgr4Cover = "";
        if(descr == null) descr = "";
        if(alipay == null) alipay = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;
        if(img4idface == null) img4idface = "";
        if(img4idback == null) img4idback = "";
        if(img4logo == null) img4logo = "";

        result.lhid = lhid;
        result.accountid = accountid;
        result.name = name;
        result.type = type;
        result.codeid = codeid;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.qq = qq;
        result.uname = uname;
        result.salesmode = salesmode;
        result.img4jg = img4jg;
        result.volume = volume;
        result.moneyAll = moneyAll;
        result.moneyCur = moneyCur;
        result.isselfadmin = isselfadmin;
        result.status = status;
        result.tiku = tiku;
        result.quality = quality;
        result.wrong = wrong;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.createtime = createtime;
        result.imgr4Cover = imgr4Cover;
        result.descr = descr;
        result.alipay = alipay;
        result.isVerifyAlipay = isVerifyAlipay;
        result.img4idface = img4idface;
        result.img4idback = img4idback;
        result.img4logo = img4logo;

        return result;
    }

    public static final Learnhub originalTo(Map e){
        Learnhub result = new Learnhub();

        Integer lhid = MapEx.getInt(e, "lhid");
        Integer accountid = MapEx.getInt(e, "accountid");
        String name = MapEx.getString(e, "name");
        Integer type = MapEx.getInt(e, "type");
        String codeid = MapEx.getString(e, "codeid");
        String province = MapEx.getString(e, "province");
        String city = MapEx.getString(e, "city");
        String seat = MapEx.getString(e, "seat");
        String qq = MapEx.getString(e, "qq");
        String uname = MapEx.getString(e, "uname");
        Integer salesmode = MapEx.getInt(e, "salesmode");
        String img4jg = MapEx.getString(e, "img4jg");
        Integer volume = MapEx.getInt(e, "volume");
        Double moneyAll = MapEx.getDouble(e, "moneyAll");
        Double moneyCur = MapEx.getDouble(e, "moneyCur");
        Boolean isselfadmin = MapEx.getBoolean(e, "isselfadmin");
        Integer status = MapEx.getInt(e, "status");
        Integer tiku = MapEx.getInt(e, "tiku");
        Integer quality = MapEx.getInt(e, "quality");
        Integer wrong = MapEx.getInt(e, "wrong");
        Integer examineStatus = MapEx.getInt(e, "examineStatus");
        String examineDes = MapEx.getString(e, "examineDes");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        String imgr4Cover = MapEx.getString(e, "imgr4Cover");
        String descr = MapEx.getString(e, "descr");
        String alipay = MapEx.getString(e, "alipay");
        Boolean isVerifyAlipay = MapEx.getBoolean(e, "isVerifyAlipay");
        String img4idface = MapEx.getString(e, "img4idface");
        String img4idback = MapEx.getString(e, "img4idback");
        String img4logo = MapEx.getString(e, "img4logo");

        if(lhid == null) lhid = 0;
        if(accountid == null) accountid = 0;
        if(name == null) name = "";
        if(type == null) type = 0;
        if(codeid == null) codeid = "";
        if(province == null) province = "";
        if(city == null) city = "";
        if(seat == null) seat = "";
        if(qq == null) qq = "";
        if(uname == null) uname = "";
        if(salesmode == null) salesmode = 0;
        if(img4jg == null) img4jg = "";
        if(volume == null) volume = 0;
        if(moneyAll == null) moneyAll = 0.0;
        if(moneyCur == null) moneyCur = 0.0;
        if(isselfadmin == null) isselfadmin = false;
        if(status == null) status = 0;
        if(tiku == null) tiku = 0;
        if(quality == null) quality = 0;
        if(wrong == null) wrong = 0;
        if(examineStatus == null) examineStatus = 0;
        if(examineDes == null) examineDes = "";
        if(createtime == null) createtime = new java.util.Date();
        if(imgr4Cover == null) imgr4Cover = "";
        if(descr == null) descr = "";
        if(alipay == null) alipay = "";
        if(isVerifyAlipay == null) isVerifyAlipay = false;
        if(img4idface == null) img4idface = "";
        if(img4idback == null) img4idback = "";
        if(img4logo == null) img4logo = "";

        result.lhid = lhid;
        result.accountid = accountid;
        result.name = name;
        result.type = type;
        result.codeid = codeid;
        result.province = province;
        result.city = city;
        result.seat = seat;
        result.qq = qq;
        result.uname = uname;
        result.salesmode = salesmode;
        result.img4jg = img4jg;
        result.volume = volume;
        result.moneyAll = moneyAll;
        result.moneyCur = moneyCur;
        result.isselfadmin = isselfadmin;
        result.status = status;
        result.tiku = tiku;
        result.quality = quality;
        result.wrong = wrong;
        result.examineStatus = examineStatus;
        result.examineDes = examineDes;
        result.createtime = createtime;
        result.imgr4Cover = imgr4Cover;
        result.descr = descr;
        result.alipay = alipay;
        result.isVerifyAlipay = isVerifyAlipay;
        result.img4idface = img4idface;
        result.img4idback = img4idback;
        result.img4logo = img4logo;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Learnhub createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 30);
            writeMapEntry(out, "lhid", lhid);
            writeMapEntry(out, "accountid", accountid);
            writeMapEntry(out, "name", name);
            writeMapEntry(out, "type", type);
            writeMapEntry(out, "codeid", codeid);
            writeMapEntry(out, "province", province);
            writeMapEntry(out, "city", city);
            writeMapEntry(out, "seat", seat);
            writeMapEntry(out, "qq", qq);
            writeMapEntry(out, "uname", uname);
            writeMapEntry(out, "salesmode", salesmode);
            writeMapEntry(out, "img4jg", img4jg);
            writeMapEntry(out, "volume", volume);
            writeMapEntry(out, "moneyAll", moneyAll);
            writeMapEntry(out, "moneyCur", moneyCur);
            writeMapEntry(out, "isselfadmin", isselfadmin);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "tiku", tiku);
            writeMapEntry(out, "quality", quality);
            writeMapEntry(out, "wrong", wrong);
            writeMapEntry(out, "examineStatus", examineStatus);
            writeMapEntry(out, "examineDes", examineDes);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "imgr4Cover", imgr4Cover);
            writeMapEntry(out, "descr", descr);
            writeMapEntry(out, "alipay", alipay);
            writeMapEntry(out, "isVerifyAlipay", isVerifyAlipay);
            writeMapEntry(out, "img4idface", img4idface);
            writeMapEntry(out, "img4idback", img4idback);
            writeMapEntry(out, "img4logo", img4logo);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Learnhub createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(lhid);
        return s.hashCode();
    }
    public final Account getAccountFkAccountid() { // account - accountid
        return AccountEntity.getByKey(accountid);
    }

    public final int countAccountFkAccountid() { // account - accountid
        return getAccountFkAccountid() == null ? 0 : 1;
    }

    public final List<Errorfeedback> getErrorfeedbacksFkLhubid() { // errorfeedback - lhubid
        return ErrorfeedbackEntity.getByLhubid(lhid);
    }

    public final int countErrorfeedbacksFkLhubid() { // errorfeedback - lhubid
        return ErrorfeedbackEntity.countByLhubid(lhid);
    }

    public final List<Kind> getKindsFkLhubid() { // kind - lhubid
        return KindEntity.getByLhubid(lhid);
    }

    public final int countKindsFkLhubid() { // kind - lhubid
        return KindEntity.countByLhubid(lhid);
    }

    public final List<Openkind4third> getOpenkind4thirdsFkLhubid() { // openkind4third - lhubid
        return Openkind4thirdEntity.getByLhubid(lhid);
    }

    public final int countOpenkind4thirdsFkLhubid() { // openkind4third - lhubid
        return Openkind4thirdEntity.countByLhubid(lhid);
    }

    public final List<Orders4profit> getOrders4profitsFkLhubid() { // orders4profit - lhubid
        return Orders4profitEntity.getByLhubid(lhid);
    }

    public final int countOrders4profitsFkLhubid() { // orders4profit - lhubid
        return Orders4profitEntity.countByLhubid(lhid);
    }

    public final List<Product> getProductsFkLhubid() { // product - lhubid
        return ProductEntity.getByLhubid(lhid);
    }

    public final int countProductsFkLhubid() { // product - lhubid
        return ProductEntity.countByLhubid(lhid);
    }

    public final List<Product0examtype> getProduct0examtypesFkLhubid() { // product0examtype - lhubid
        return Product0examtypeEntity.getByLhubid(lhid);
    }

    public final int countProduct0examtypesFkLhubid() { // product0examtype - lhubid
        return Product0examtypeEntity.countByLhubid(lhid);
    }

    public static final Learnhub loadByKey(int lhid) {
        return LearnhubEntity.getByKey(lhid);
    }

    public static final Future<Learnhub> asyncByKey(int lhid) {
        return LearnhubEntity.asyncGetByKey(lhid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Learnhub insert() {
        Learnhub result = LearnhubEntity.insert(this);
        if(result == null) return null;
        // lhid = result.lhid;
        return result;
    }

    public final Learnhub asyncInsert() {
        Learnhub result = LearnhubEntity.asyncInsert(this);
        // lhid = result.lhid;
        return result;
    }

    public final Learnhub insert2() {
        return LearnhubEntity.insert2(this);
    }

    public final Learnhub asyncInsert2() {
        Learnhub result = LearnhubEntity.asyncInsert2(this);
        // lhid = result.lhid;
        return result;
    }

    public final Learnhub update() {
        return LearnhubEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(lhid <= 0) return false;
        LearnhubEntity.asyncUpdate( this );
        return true;
    }

    public final Learnhub insertOrUpdate() {
        if(lhid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return LearnhubEntity.delete(lhid);
    }

    public final void asyncDelete() {
        LearnhubEntity.asyncDelete(lhid);
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

    public Learnhub clone2() {
        try{
            return (Learnhub) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
