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

//learnhall3_design - adcourses
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Adcourses extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1607157417; // com.learnhall.db.bean.Adcourses

    public static String TABLENAME = "adcourses";

    public static final String primary = "cid";

    public static final class Col { public static final String cid = "cid"; public static final String departid = "departid"; public static final String nmMajor = "nmMajor"; public static final String nmLevel = "nmLevel"; public static final String nmSub = "nmSub"; public static final String nmArea = "nmArea"; public static final String profitAgent = "profitAgent"; public static final String profitOwner = "profitOwner"; public static final String deposit = "deposit"; public static final String bonus = "bonus"; public static final String wrong = "wrong"; public static final String program = "program"; public static final String art = "art"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String cid = "cid"; public static final String departid = "departid"; public static final String nmMajor = "nmMajor"; public static final String nmLevel = "nmLevel"; public static final String nmSub = "nmSub"; public static final String nmArea = "nmArea"; public static final String profitAgent = "profitAgent"; public static final String profitOwner = "profitOwner"; public static final String deposit = "deposit"; public static final String bonus = "bonus"; public static final String wrong = "wrong"; public static final String program = "program"; public static final String art = "art"; public static final String status = "status"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"cid", "departid", "nmMajor", "nmLevel", "nmSub", "nmArea", "profitAgent", "profitOwner", "deposit", "bonus", "wrong", "program", "art", "status", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "TINYTEXT", "TINYTEXT", "TINYTEXT", "TINYTEXT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "DATETIME"};


    public int cid;
    public int departid;
    public String nmMajor;
    public String nmLevel;
    public String nmSub;
    public String nmArea;
    public int profitAgent;
    public int profitOwner;
    public int deposit;
    public int bonus;
    public int wrong;
    public int program;
    public int art;
    public int status;
    public java.util.Date createtime;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return cid;
    }

    public static String _key(int cid) {
        return PStr.b(TABLENAME).a("-").e(cid);
    }

    public int getCid(){
        return cid;
    }

    public Adcourses setCid(int cid){
        this.cid = cid;
        return this;
    }

    public int getDepartid(){
        return departid;
    }

    public Adcourses setDepartid(int departid){
        int _old = this.departid;
        this.departid = departid;
        changeIt(Col.departid, _old, departid);
        return this;
    }

    public Adcourses changeDepartid(int departid){
        return setDepartid(this.departid + departid);
    }

    public Adcourses changeDepartidWithMin(int departid, int _min){
        int _v2 = this.departid + departid;
        return setDepartid(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeDepartidWithMax(int departid, int _max){
        int _v2 = this.departid + departid;
        return setDepartid(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeDepartidWithMinMax(int departid, int _min, int _max){
        int _v2 = this.departid + departid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDepartid(_v2 < _min  ? _min : _v2);
    }

    public String getNmMajor(){
        return nmMajor;
    }

    public Adcourses setNmMajor(String nmMajor){
        String _old = this.nmMajor;
        this.nmMajor = nmMajor;
        changeIt(Col.nmMajor, _old, nmMajor);
        return this;
    }

    public String getNmLevel(){
        return nmLevel;
    }

    public Adcourses setNmLevel(String nmLevel){
        String _old = this.nmLevel;
        this.nmLevel = nmLevel;
        changeIt(Col.nmLevel, _old, nmLevel);
        return this;
    }

    public String getNmSub(){
        return nmSub;
    }

    public Adcourses setNmSub(String nmSub){
        String _old = this.nmSub;
        this.nmSub = nmSub;
        changeIt(Col.nmSub, _old, nmSub);
        return this;
    }

    public String getNmArea(){
        return nmArea;
    }

    public Adcourses setNmArea(String nmArea){
        String _old = this.nmArea;
        this.nmArea = nmArea;
        changeIt(Col.nmArea, _old, nmArea);
        return this;
    }

    public int getProfitAgent(){
        return profitAgent;
    }

    public Adcourses setProfitAgent(int profitAgent){
        int _old = this.profitAgent;
        this.profitAgent = profitAgent;
        changeIt(Col.profitAgent, _old, profitAgent);
        return this;
    }

    public Adcourses changeProfitAgent(int profitAgent){
        return setProfitAgent(this.profitAgent + profitAgent);
    }

    public Adcourses changeProfitAgentWithMin(int profitAgent, int _min){
        int _v2 = this.profitAgent + profitAgent;
        return setProfitAgent(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeProfitAgentWithMax(int profitAgent, int _max){
        int _v2 = this.profitAgent + profitAgent;
        return setProfitAgent(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeProfitAgentWithMinMax(int profitAgent, int _min, int _max){
        int _v2 = this.profitAgent + profitAgent;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProfitAgent(_v2 < _min  ? _min : _v2);
    }

    public int getProfitOwner(){
        return profitOwner;
    }

    public Adcourses setProfitOwner(int profitOwner){
        int _old = this.profitOwner;
        this.profitOwner = profitOwner;
        changeIt(Col.profitOwner, _old, profitOwner);
        return this;
    }

    public Adcourses changeProfitOwner(int profitOwner){
        return setProfitOwner(this.profitOwner + profitOwner);
    }

    public Adcourses changeProfitOwnerWithMin(int profitOwner, int _min){
        int _v2 = this.profitOwner + profitOwner;
        return setProfitOwner(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeProfitOwnerWithMax(int profitOwner, int _max){
        int _v2 = this.profitOwner + profitOwner;
        return setProfitOwner(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeProfitOwnerWithMinMax(int profitOwner, int _min, int _max){
        int _v2 = this.profitOwner + profitOwner;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProfitOwner(_v2 < _min  ? _min : _v2);
    }

    public int getDeposit(){
        return deposit;
    }

    public Adcourses setDeposit(int deposit){
        int _old = this.deposit;
        this.deposit = deposit;
        changeIt(Col.deposit, _old, deposit);
        return this;
    }

    public Adcourses changeDeposit(int deposit){
        return setDeposit(this.deposit + deposit);
    }

    public Adcourses changeDepositWithMin(int deposit, int _min){
        int _v2 = this.deposit + deposit;
        return setDeposit(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeDepositWithMax(int deposit, int _max){
        int _v2 = this.deposit + deposit;
        return setDeposit(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeDepositWithMinMax(int deposit, int _min, int _max){
        int _v2 = this.deposit + deposit;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDeposit(_v2 < _min  ? _min : _v2);
    }

    public int getBonus(){
        return bonus;
    }

    public Adcourses setBonus(int bonus){
        int _old = this.bonus;
        this.bonus = bonus;
        changeIt(Col.bonus, _old, bonus);
        return this;
    }

    public Adcourses changeBonus(int bonus){
        return setBonus(this.bonus + bonus);
    }

    public Adcourses changeBonusWithMin(int bonus, int _min){
        int _v2 = this.bonus + bonus;
        return setBonus(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeBonusWithMax(int bonus, int _max){
        int _v2 = this.bonus + bonus;
        return setBonus(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeBonusWithMinMax(int bonus, int _min, int _max){
        int _v2 = this.bonus + bonus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBonus(_v2 < _min  ? _min : _v2);
    }

    public int getWrong(){
        return wrong;
    }

    public Adcourses setWrong(int wrong){
        int _old = this.wrong;
        this.wrong = wrong;
        changeIt(Col.wrong, _old, wrong);
        return this;
    }

    public Adcourses changeWrong(int wrong){
        return setWrong(this.wrong + wrong);
    }

    public Adcourses changeWrongWithMin(int wrong, int _min){
        int _v2 = this.wrong + wrong;
        return setWrong(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeWrongWithMax(int wrong, int _max){
        int _v2 = this.wrong + wrong;
        return setWrong(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeWrongWithMinMax(int wrong, int _min, int _max){
        int _v2 = this.wrong + wrong;
        _v2 = _v2 > _max  ? _max : _v2;
        return setWrong(_v2 < _min  ? _min : _v2);
    }

    public int getProgram(){
        return program;
    }

    public Adcourses setProgram(int program){
        int _old = this.program;
        this.program = program;
        changeIt(Col.program, _old, program);
        return this;
    }

    public Adcourses changeProgram(int program){
        return setProgram(this.program + program);
    }

    public Adcourses changeProgramWithMin(int program, int _min){
        int _v2 = this.program + program;
        return setProgram(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeProgramWithMax(int program, int _max){
        int _v2 = this.program + program;
        return setProgram(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeProgramWithMinMax(int program, int _min, int _max){
        int _v2 = this.program + program;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProgram(_v2 < _min  ? _min : _v2);
    }

    public int getArt(){
        return art;
    }

    public Adcourses setArt(int art){
        int _old = this.art;
        this.art = art;
        changeIt(Col.art, _old, art);
        return this;
    }

    public Adcourses changeArt(int art){
        return setArt(this.art + art);
    }

    public Adcourses changeArtWithMin(int art, int _min){
        int _v2 = this.art + art;
        return setArt(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeArtWithMax(int art, int _max){
        int _v2 = this.art + art;
        return setArt(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeArtWithMinMax(int art, int _min, int _max){
        int _v2 = this.art + art;
        _v2 = _v2 > _max  ? _max : _v2;
        return setArt(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Adcourses setStatus(int status){
        int _old = this.status;
        this.status = status;
        changeIt(Col.status, _old, status);
        return this;
    }

    public Adcourses changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Adcourses changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Adcourses changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Adcourses changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Adcourses setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Adcourses v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Adcourses v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Adcourses newAdcourses(Integer cid, Integer departid, String nmMajor, String nmLevel, String nmSub, String nmArea, Integer profitAgent, Integer profitOwner, Integer deposit, Integer bonus, Integer wrong, Integer program, Integer art, Integer status, java.util.Date createtime) {
        Adcourses result = new Adcourses();
        result.cid = cid;
        result.departid = departid;
        result.nmMajor = nmMajor;
        result.nmLevel = nmLevel;
        result.nmSub = nmSub;
        result.nmArea = nmArea;
        result.profitAgent = profitAgent;
        result.profitOwner = profitOwner;
        result.deposit = deposit;
        result.bonus = bonus;
        result.wrong = wrong;
        result.program = program;
        result.art = art;
        result.status = status;
        result.createtime = createtime;
        return result;
    }

    public static Adcourses newAdcourses(Adcourses adcourses) {
        Adcourses result = new Adcourses();
        result.cid = adcourses.cid;
        result.departid = adcourses.departid;
        result.nmMajor = adcourses.nmMajor;
        result.nmLevel = adcourses.nmLevel;
        result.nmSub = adcourses.nmSub;
        result.nmArea = adcourses.nmArea;
        result.profitAgent = adcourses.profitAgent;
        result.profitOwner = adcourses.profitOwner;
        result.deposit = adcourses.deposit;
        result.bonus = adcourses.bonus;
        result.wrong = adcourses.wrong;
        result.program = adcourses.program;
        result.art = adcourses.art;
        result.status = adcourses.status;
        result.createtime = adcourses.createtime;
        return result;
    }

    public Adcourses createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getAdcourses(){
        Adcourses adcourses = null; // adcourses
        { // new and insert Adcourses (adcourses)
            int cid = 0; 	// cid
            int departid = 0; 	// departid
            String nmMajor = ""; 	// nmMajor
            String nmLevel = ""; 	// nmLevel
            String nmSub = ""; 	// nmSub
            String nmArea = ""; 	// nmArea
            int profitAgent = 0; 	// profitAgent
            int profitOwner = 0; 	// profitOwner
            int deposit = 0; 	// deposit
            int bonus = 0; 	// bonus
            int wrong = 0; 	// wrong
            int program = 0; 	// program
            int art = 0; 	// art
            int status = 0; 	// status
            Date createtime = new Date(); 	// createtime
            adcourses = Adcourses.newAdcourses(cid, departid, nmMajor, nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit, bonus, wrong, program, art, status, createtime);
        }
        adcourses = adcourses.insert();

        int cid = adcourses.getCid(); 	// cid
        int departid = adcourses.getDepartid(); 	// departid
        String nmMajor = adcourses.getNmMajor(); 	// nmMajor
        String nmLevel = adcourses.getNmLevel(); 	// nmLevel
        String nmSub = adcourses.getNmSub(); 	// nmSub
        String nmArea = adcourses.getNmArea(); 	// nmArea
        int profitAgent = adcourses.getProfitAgent(); 	// profitAgent
        int profitOwner = adcourses.getProfitOwner(); 	// profitOwner
        int deposit = adcourses.getDeposit(); 	// deposit
        int bonus = adcourses.getBonus(); 	// bonus
        int wrong = adcourses.getWrong(); 	// wrong
        int program = adcourses.getProgram(); 	// program
        int art = adcourses.getArt(); 	// art
        int status = adcourses.getStatus(); 	// status
        Date createtime = adcourses.getCreatetime(); 	// createtime
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.cid:
            return cid;
        case CEn.departid:
            return departid;
        case CEn.profitAgent:
            return profitAgent;
        case CEn.profitOwner:
            return profitOwner;
        case CEn.deposit:
            return deposit;
        case CEn.bonus:
            return bonus;
        case CEn.wrong:
            return wrong;
        case CEn.program:
            return program;
        case CEn.art:
            return art;
        case CEn.status:
            return status;
        }
        return 0;
    }

    public Adcourses setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Adcourses setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.cid:
            return setCid(value2);
        case CEn.departid:
            return setDepartid(value2);
        case CEn.profitAgent:
            return setProfitAgent(value2);
        case CEn.profitOwner:
            return setProfitOwner(value2);
        case CEn.deposit:
            return setDeposit(value2);
        case CEn.bonus:
            return setBonus(value2);
        case CEn.wrong:
            return setWrong(value2);
        case CEn.program:
            return setProgram(value2);
        case CEn.art:
            return setArt(value2);
        case CEn.status:
            return setStatus(value2);
        }
        return this;
    }

    public Adcourses changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Adcourses changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.departid:
            return changeDepartid(value2);
        case CEn.profitAgent:
            return changeProfitAgent(value2);
        case CEn.profitOwner:
            return changeProfitOwner(value2);
        case CEn.deposit:
            return changeDeposit(value2);
        case CEn.bonus:
            return changeBonus(value2);
        case CEn.wrong:
            return changeWrong(value2);
        case CEn.program:
            return changeProgram(value2);
        case CEn.art:
            return changeArt(value2);
        case CEn.status:
            return changeStatus(value2);
        }
        return this;
    }

    public Adcourses changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Adcourses changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.departid:
            return changeDepartidWithMin(value2, _min);
        case CEn.profitAgent:
            return changeProfitAgentWithMin(value2, _min);
        case CEn.profitOwner:
            return changeProfitOwnerWithMin(value2, _min);
        case CEn.deposit:
            return changeDepositWithMin(value2, _min);
        case CEn.bonus:
            return changeBonusWithMin(value2, _min);
        case CEn.wrong:
            return changeWrongWithMin(value2, _min);
        case CEn.program:
            return changeProgramWithMin(value2, _min);
        case CEn.art:
            return changeArtWithMin(value2, _min);
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
        }
        return 0;
    }

    public Adcourses setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Adcourses setDouble(String fieldEn, double value2) {
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
        case CEn.nmMajor: 
            return nmMajor;
        case CEn.nmLevel: 
            return nmLevel;
        case CEn.nmSub: 
            return nmSub;
        case CEn.nmArea: 
            return nmArea;
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
        case CEn.cid:
            return cid;
        case CEn.departid:
            return departid;
        case CEn.nmMajor:
            return nmMajor;
        case CEn.nmLevel:
            return nmLevel;
        case CEn.nmSub:
            return nmSub;
        case CEn.nmArea:
            return nmArea;
        case CEn.profitAgent:
            return profitAgent;
        case CEn.profitOwner:
            return profitOwner;
        case CEn.deposit:
            return deposit;
        case CEn.bonus:
            return bonus;
        case CEn.wrong:
            return wrong;
        case CEn.program:
            return program;
        case CEn.art:
            return art;
        case CEn.status:
            return status;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Adcourses setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Adcourses setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Adcourses setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Adcourses setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.nmMajor:
            return setNmMajor(value2);
        case CEn.nmLevel:
            return setNmLevel(value2);
        case CEn.nmSub:
            return setNmSub(value2);
        case CEn.nmArea:
            return setNmArea(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Adcourses setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Adcourses setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Adcourses");
        result.put("cid", cid);
        result.put("departid", departid);
        result.put("nmMajor", nmMajor);
        result.put("nmLevel", nmLevel);
        result.put("nmSub", nmSub);
        result.put("nmArea", nmArea);
        result.put("profitAgent", profitAgent);
        result.put("profitOwner", profitOwner);
        result.put("deposit", deposit);
        result.put("bonus", bonus);
        result.put("wrong", wrong);
        result.put("program", program);
        result.put("art", art);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("cid", cid);
        result.put("departid", departid);
        result.put("nmMajor", nmMajor);
        result.put("nmLevel", nmLevel);
        result.put("nmSub", nmSub);
        result.put("nmArea", nmArea);
        result.put("profitAgent", profitAgent);
        result.put("profitOwner", profitOwner);
        result.put("deposit", deposit);
        result.put("bonus", bonus);
        result.put("wrong", wrong);
        result.put("program", program);
        result.put("art", art);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Adcourses");
        result.put("cid", cid);
        result.put("departid", departid);
        result.put("nmMajor", nmMajor);
        result.put("nmLevel", nmLevel);
        result.put("nmSub", nmSub);
        result.put("nmArea", nmArea);
        result.put("profitAgent", profitAgent);
        result.put("profitOwner", profitOwner);
        result.put("deposit", deposit);
        result.put("bonus", bonus);
        result.put("wrong", wrong);
        result.put("program", program);
        result.put("art", art);
        result.put("status", status);
        result.put("createtime", createtime);
        return result;
    }

    public Adcourses mapToObject(Map e){
        Integer cid = MapEx.getInt(e, "cid");
        Integer departid = MapEx.getInt(e, "departid");
        String nmMajor = MapEx.getString(e, "nmMajor");
        String nmLevel = MapEx.getString(e, "nmLevel");
        String nmSub = MapEx.getString(e, "nmSub");
        String nmArea = MapEx.getString(e, "nmArea");
        Integer profitAgent = MapEx.getInt(e, "profitAgent");
        Integer profitOwner = MapEx.getInt(e, "profitOwner");
        Integer deposit = MapEx.getInt(e, "deposit");
        Integer bonus = MapEx.getInt(e, "bonus");
        Integer wrong = MapEx.getInt(e, "wrong");
        Integer program = MapEx.getInt(e, "program");
        Integer art = MapEx.getInt(e, "art");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(cid == null) cid = 0;
        if(departid == null) departid = 0;
        if(nmMajor == null) nmMajor = "";
        if(nmLevel == null) nmLevel = "";
        if(nmSub == null) nmSub = "";
        if(nmArea == null) nmArea = "";
        if(profitAgent == null) profitAgent = 0;
        if(profitOwner == null) profitOwner = 0;
        if(deposit == null) deposit = 0;
        if(bonus == null) bonus = 0;
        if(wrong == null) wrong = 0;
        if(program == null) program = 0;
        if(art == null) art = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        setCid(cid);
        setDepartid(departid);
        setNmMajor(nmMajor);
        setNmLevel(nmLevel);
        setNmSub(nmSub);
        setNmArea(nmArea);
        setProfitAgent(profitAgent);
        setProfitOwner(profitOwner);
        setDeposit(deposit);
        setBonus(bonus);
        setWrong(wrong);
        setProgram(program);
        setArt(art);
        setStatus(status);
        setCreatetime(createtime);

        return this;
    }

    public static final Adcourses mapTo(Map e){
        Adcourses result = new Adcourses();

        Integer cid = MapEx.getInt(e, "cid");
        Integer departid = MapEx.getInt(e, "departid");
        String nmMajor = MapEx.getString(e, "nmMajor");
        String nmLevel = MapEx.getString(e, "nmLevel");
        String nmSub = MapEx.getString(e, "nmSub");
        String nmArea = MapEx.getString(e, "nmArea");
        Integer profitAgent = MapEx.getInt(e, "profitAgent");
        Integer profitOwner = MapEx.getInt(e, "profitOwner");
        Integer deposit = MapEx.getInt(e, "deposit");
        Integer bonus = MapEx.getInt(e, "bonus");
        Integer wrong = MapEx.getInt(e, "wrong");
        Integer program = MapEx.getInt(e, "program");
        Integer art = MapEx.getInt(e, "art");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(cid == null) cid = 0;
        if(departid == null) departid = 0;
        if(nmMajor == null) nmMajor = "";
        if(nmLevel == null) nmLevel = "";
        if(nmSub == null) nmSub = "";
        if(nmArea == null) nmArea = "";
        if(profitAgent == null) profitAgent = 0;
        if(profitOwner == null) profitOwner = 0;
        if(deposit == null) deposit = 0;
        if(bonus == null) bonus = 0;
        if(wrong == null) wrong = 0;
        if(program == null) program = 0;
        if(art == null) art = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.cid = cid;
        result.departid = departid;
        result.nmMajor = nmMajor;
        result.nmLevel = nmLevel;
        result.nmSub = nmSub;
        result.nmArea = nmArea;
        result.profitAgent = profitAgent;
        result.profitOwner = profitOwner;
        result.deposit = deposit;
        result.bonus = bonus;
        result.wrong = wrong;
        result.program = program;
        result.art = art;
        result.status = status;
        result.createtime = createtime;

        return result;
    }

    public static final Adcourses originalTo(Map e){
        Adcourses result = new Adcourses();

        Integer cid = MapEx.getInt(e, "cid");
        Integer departid = MapEx.getInt(e, "departid");
        String nmMajor = MapEx.getString(e, "nmMajor");
        String nmLevel = MapEx.getString(e, "nmLevel");
        String nmSub = MapEx.getString(e, "nmSub");
        String nmArea = MapEx.getString(e, "nmArea");
        Integer profitAgent = MapEx.getInt(e, "profitAgent");
        Integer profitOwner = MapEx.getInt(e, "profitOwner");
        Integer deposit = MapEx.getInt(e, "deposit");
        Integer bonus = MapEx.getInt(e, "bonus");
        Integer wrong = MapEx.getInt(e, "wrong");
        Integer program = MapEx.getInt(e, "program");
        Integer art = MapEx.getInt(e, "art");
        Integer status = MapEx.getInt(e, "status");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(cid == null) cid = 0;
        if(departid == null) departid = 0;
        if(nmMajor == null) nmMajor = "";
        if(nmLevel == null) nmLevel = "";
        if(nmSub == null) nmSub = "";
        if(nmArea == null) nmArea = "";
        if(profitAgent == null) profitAgent = 0;
        if(profitOwner == null) profitOwner = 0;
        if(deposit == null) deposit = 0;
        if(bonus == null) bonus = 0;
        if(wrong == null) wrong = 0;
        if(program == null) program = 0;
        if(art == null) art = 0;
        if(status == null) status = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.cid = cid;
        result.departid = departid;
        result.nmMajor = nmMajor;
        result.nmLevel = nmLevel;
        result.nmSub = nmSub;
        result.nmArea = nmArea;
        result.profitAgent = profitAgent;
        result.profitOwner = profitOwner;
        result.deposit = deposit;
        result.bonus = bonus;
        result.wrong = wrong;
        result.program = program;
        result.art = art;
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

    public static final Adcourses createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 15);
            writeMapEntry(out, "cid", cid);
            writeMapEntry(out, "departid", departid);
            writeMapEntry(out, "nmMajor", nmMajor);
            writeMapEntry(out, "nmLevel", nmLevel);
            writeMapEntry(out, "nmSub", nmSub);
            writeMapEntry(out, "nmArea", nmArea);
            writeMapEntry(out, "profitAgent", profitAgent);
            writeMapEntry(out, "profitOwner", profitOwner);
            writeMapEntry(out, "deposit", deposit);
            writeMapEntry(out, "bonus", bonus);
            writeMapEntry(out, "wrong", wrong);
            writeMapEntry(out, "program", program);
            writeMapEntry(out, "art", art);
            writeMapEntry(out, "status", status);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Adcourses createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(cid);
        return s.hashCode();
    }
    public final Adqdepartment getAdqdepartmentFkDepartid() { // adqdepartment - departid
        return AdqdepartmentEntity.getByKey(departid);
    }

    public final int countAdqdepartmentFkDepartid() { // adqdepartment - departid
        return getAdqdepartmentFkDepartid() == null ? 0 : 1;
    }

    public final List<Kind> getKindsFkCoursid() { // kind - coursid
        return KindEntity.getByCoursid(cid);
    }

    public final int countKindsFkCoursid() { // kind - coursid
        return KindEntity.countByCoursid(cid);
    }

    public final List<Product> getProductsFkCoursesid() { // product - coursesid
        return ProductEntity.getByCoursesid(cid);
    }

    public final int countProductsFkCoursesid() { // product - coursesid
        return ProductEntity.countByCoursesid(cid);
    }

    public static final Adcourses loadByKey(int cid) {
        return AdcoursesEntity.getByKey(cid);
    }

    public static final Future<Adcourses> asyncByKey(int cid) {
        return AdcoursesEntity.asyncGetByKey(cid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Adcourses insert() {
        Adcourses result = AdcoursesEntity.insert(this);
        if(result == null) return null;
        // cid = result.cid;
        return result;
    }

    public final Adcourses asyncInsert() {
        Adcourses result = AdcoursesEntity.asyncInsert(this);
        // cid = result.cid;
        return result;
    }

    public final Adcourses insert2() {
        return AdcoursesEntity.insert2(this);
    }

    public final Adcourses asyncInsert2() {
        Adcourses result = AdcoursesEntity.asyncInsert2(this);
        // cid = result.cid;
        return result;
    }

    public final Adcourses update() {
        return AdcoursesEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(cid <= 0) return false;
        AdcoursesEntity.asyncUpdate( this );
        return true;
    }

    public final Adcourses insertOrUpdate() {
        if(cid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AdcoursesEntity.delete(cid);
    }

    public final void asyncDelete() {
        AdcoursesEntity.asyncDelete(cid);
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

    public Adcourses clone2() {
        try{
            return (Adcourses) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
