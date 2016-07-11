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

//learnhall3_design - recordques4exam
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Recordques4exam extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -222956540; // com.learnhall.db.bean.Recordques4exam

    public static String TABLENAME = "recordques4exam";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String customid = "customid"; public static final String questionid = "questionid"; public static final String catalog4Exam = "catalog4Exam"; public static final String numError = "numError"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String customid = "customid"; public static final String questionid = "questionid"; public static final String catalog4Exam = "catalog4Exam"; public static final String numError = "numError"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "customid", "questionid", "catalog4Exam", "numError", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "INT", "DATETIME"};


    public int id;
    public int customid;
    public int questionid;
    public int catalog4Exam;
    public int numError;
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

    public Recordques4exam setId(int id){
        this.id = id;
        return this;
    }

    public int getCustomid(){
        return customid;
    }

    public Recordques4exam setCustomid(int customid){
        int _old = this.customid;
        this.customid = customid;
        changeIt(Col.customid, _old, customid);
        return this;
    }

    public Recordques4exam changeCustomid(int customid){
        return setCustomid(this.customid + customid);
    }

    public Recordques4exam changeCustomidWithMin(int customid, int _min){
        int _v2 = this.customid + customid;
        return setCustomid(_v2 < _min  ? _min : _v2);
    }

    public Recordques4exam changeCustomidWithMax(int customid, int _max){
        int _v2 = this.customid + customid;
        return setCustomid(_v2 > _max  ? _max : _v2);
    }

    public Recordques4exam changeCustomidWithMinMax(int customid, int _min, int _max){
        int _v2 = this.customid + customid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCustomid(_v2 < _min  ? _min : _v2);
    }

    public int getQuestionid(){
        return questionid;
    }

    public Recordques4exam setQuestionid(int questionid){
        int _old = this.questionid;
        this.questionid = questionid;
        changeIt(Col.questionid, _old, questionid);
        return this;
    }

    public Recordques4exam changeQuestionid(int questionid){
        return setQuestionid(this.questionid + questionid);
    }

    public Recordques4exam changeQuestionidWithMin(int questionid, int _min){
        int _v2 = this.questionid + questionid;
        return setQuestionid(_v2 < _min  ? _min : _v2);
    }

    public Recordques4exam changeQuestionidWithMax(int questionid, int _max){
        int _v2 = this.questionid + questionid;
        return setQuestionid(_v2 > _max  ? _max : _v2);
    }

    public Recordques4exam changeQuestionidWithMinMax(int questionid, int _min, int _max){
        int _v2 = this.questionid + questionid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setQuestionid(_v2 < _min  ? _min : _v2);
    }

    public int getCatalog4Exam(){
        return catalog4Exam;
    }

    public Recordques4exam setCatalog4Exam(int catalog4Exam){
        int _old = this.catalog4Exam;
        this.catalog4Exam = catalog4Exam;
        changeIt(Col.catalog4Exam, _old, catalog4Exam);
        return this;
    }

    public Recordques4exam changeCatalog4Exam(int catalog4Exam){
        return setCatalog4Exam(this.catalog4Exam + catalog4Exam);
    }

    public Recordques4exam changeCatalog4ExamWithMin(int catalog4Exam, int _min){
        int _v2 = this.catalog4Exam + catalog4Exam;
        return setCatalog4Exam(_v2 < _min  ? _min : _v2);
    }

    public Recordques4exam changeCatalog4ExamWithMax(int catalog4Exam, int _max){
        int _v2 = this.catalog4Exam + catalog4Exam;
        return setCatalog4Exam(_v2 > _max  ? _max : _v2);
    }

    public Recordques4exam changeCatalog4ExamWithMinMax(int catalog4Exam, int _min, int _max){
        int _v2 = this.catalog4Exam + catalog4Exam;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCatalog4Exam(_v2 < _min  ? _min : _v2);
    }

    public int getNumError(){
        return numError;
    }

    public Recordques4exam setNumError(int numError){
        int _old = this.numError;
        this.numError = numError;
        changeIt(Col.numError, _old, numError);
        return this;
    }

    public Recordques4exam changeNumError(int numError){
        return setNumError(this.numError + numError);
    }

    public Recordques4exam changeNumErrorWithMin(int numError, int _min){
        int _v2 = this.numError + numError;
        return setNumError(_v2 < _min  ? _min : _v2);
    }

    public Recordques4exam changeNumErrorWithMax(int numError, int _max){
        int _v2 = this.numError + numError;
        return setNumError(_v2 > _max  ? _max : _v2);
    }

    public Recordques4exam changeNumErrorWithMinMax(int numError, int _min, int _max){
        int _v2 = this.numError + numError;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNumError(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Recordques4exam setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Recordques4exam v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Recordques4exam v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Recordques4exam newRecordques4exam(Integer id, Integer customid, Integer questionid, Integer catalog4Exam, Integer numError, java.util.Date createtime) {
        Recordques4exam result = new Recordques4exam();
        result.id = id;
        result.customid = customid;
        result.questionid = questionid;
        result.catalog4Exam = catalog4Exam;
        result.numError = numError;
        result.createtime = createtime;
        return result;
    }

    public static Recordques4exam newRecordques4exam(Recordques4exam recordques4exam) {
        Recordques4exam result = new Recordques4exam();
        result.id = recordques4exam.id;
        result.customid = recordques4exam.customid;
        result.questionid = recordques4exam.questionid;
        result.catalog4Exam = recordques4exam.catalog4Exam;
        result.numError = recordques4exam.numError;
        result.createtime = recordques4exam.createtime;
        return result;
    }

    public Recordques4exam createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecordques4exam(){
        Recordques4exam recordques4exam = null; // recordques4exam
        { // new and insert Recordques4exam (recordques4exam)
            int id = 0; 	// id
            int customid = 0; 	// customid
            int questionid = 0; 	// questionid
            int catalog4Exam = 0; 	// catalog4Exam
            int numError = 0; 	// numError
            Date createtime = new Date(); 	// createtime
            recordques4exam = Recordques4exam.newRecordques4exam(id, customid, questionid, catalog4Exam, numError, createtime);
        }
        recordques4exam = recordques4exam.insert();

        int id = recordques4exam.getId(); 	// id
        int customid = recordques4exam.getCustomid(); 	// customid
        int questionid = recordques4exam.getQuestionid(); 	// questionid
        int catalog4Exam = recordques4exam.getCatalog4Exam(); 	// catalog4Exam
        int numError = recordques4exam.getNumError(); 	// numError
        Date createtime = recordques4exam.getCreatetime(); 	// createtime
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
        case CEn.customid:
            return customid;
        case CEn.questionid:
            return questionid;
        case CEn.catalog4Exam:
            return catalog4Exam;
        case CEn.numError:
            return numError;
        }
        return 0;
    }

    public Recordques4exam setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Recordques4exam setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.customid:
            return setCustomid(value2);
        case CEn.questionid:
            return setQuestionid(value2);
        case CEn.catalog4Exam:
            return setCatalog4Exam(value2);
        case CEn.numError:
            return setNumError(value2);
        }
        return this;
    }

    public Recordques4exam changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Recordques4exam changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.customid:
            return changeCustomid(value2);
        case CEn.questionid:
            return changeQuestionid(value2);
        case CEn.catalog4Exam:
            return changeCatalog4Exam(value2);
        case CEn.numError:
            return changeNumError(value2);
        }
        return this;
    }

    public Recordques4exam changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Recordques4exam changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.customid:
            return changeCustomidWithMin(value2, _min);
        case CEn.questionid:
            return changeQuestionidWithMin(value2, _min);
        case CEn.catalog4Exam:
            return changeCatalog4ExamWithMin(value2, _min);
        case CEn.numError:
            return changeNumErrorWithMin(value2, _min);
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

    public Recordques4exam setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Recordques4exam setDouble(String fieldEn, double value2) {
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
        case CEn.customid:
            return customid;
        case CEn.questionid:
            return questionid;
        case CEn.catalog4Exam:
            return catalog4Exam;
        case CEn.numError:
            return numError;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Recordques4exam setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Recordques4exam setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Recordques4exam setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Recordques4exam setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Recordques4exam setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Recordques4exam setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.learnhall.db.bean.Recordques4exam");
        result.put("id", id);
        result.put("customid", customid);
        result.put("questionid", questionid);
        result.put("catalog4Exam", catalog4Exam);
        result.put("numError", numError);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("customid", customid);
        result.put("questionid", questionid);
        result.put("catalog4Exam", catalog4Exam);
        result.put("numError", numError);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.learnhall.db.bean.Recordques4exam");
        result.put("id", id);
        result.put("customid", customid);
        result.put("questionid", questionid);
        result.put("catalog4Exam", catalog4Exam);
        result.put("numError", numError);
        result.put("createtime", createtime);
        return result;
    }

    public Recordques4exam mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer customid = MapEx.getInt(e, "customid");
        Integer questionid = MapEx.getInt(e, "questionid");
        Integer catalog4Exam = MapEx.getInt(e, "catalog4Exam");
        Integer numError = MapEx.getInt(e, "numError");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(customid == null) customid = 0;
        if(questionid == null) questionid = 0;
        if(catalog4Exam == null) catalog4Exam = 0;
        if(numError == null) numError = 0;
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setCustomid(customid);
        setQuestionid(questionid);
        setCatalog4Exam(catalog4Exam);
        setNumError(numError);
        setCreatetime(createtime);

        return this;
    }

    public static final Recordques4exam mapTo(Map e){
        Recordques4exam result = new Recordques4exam();

        Integer id = MapEx.getInt(e, "id");
        Integer customid = MapEx.getInt(e, "customid");
        Integer questionid = MapEx.getInt(e, "questionid");
        Integer catalog4Exam = MapEx.getInt(e, "catalog4Exam");
        Integer numError = MapEx.getInt(e, "numError");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(customid == null) customid = 0;
        if(questionid == null) questionid = 0;
        if(catalog4Exam == null) catalog4Exam = 0;
        if(numError == null) numError = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.customid = customid;
        result.questionid = questionid;
        result.catalog4Exam = catalog4Exam;
        result.numError = numError;
        result.createtime = createtime;

        return result;
    }

    public static final Recordques4exam originalTo(Map e){
        Recordques4exam result = new Recordques4exam();

        Integer id = MapEx.getInt(e, "id");
        Integer customid = MapEx.getInt(e, "customid");
        Integer questionid = MapEx.getInt(e, "questionid");
        Integer catalog4Exam = MapEx.getInt(e, "catalog4Exam");
        Integer numError = MapEx.getInt(e, "numError");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0;
        if(customid == null) customid = 0;
        if(questionid == null) questionid = 0;
        if(catalog4Exam == null) catalog4Exam = 0;
        if(numError == null) numError = 0;
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.customid = customid;
        result.questionid = questionid;
        result.catalog4Exam = catalog4Exam;
        result.numError = numError;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Recordques4exam createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 6);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "customid", customid);
            writeMapEntry(out, "questionid", questionid);
            writeMapEntry(out, "catalog4Exam", catalog4Exam);
            writeMapEntry(out, "numError", numError);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Recordques4exam createFor(byte[] b) throws Exception {
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
    public final Customer getCustomerFkCustomid() { // customer - customid
        return CustomerEntity.getByKey(customid);
    }

    public final int countCustomerFkCustomid() { // customer - customid
        return getCustomerFkCustomid() == null ? 0 : 1;
    }

    public final Optquestion getOptquestionFkQuestionid() { // optquestion - questionid
        return OptquestionEntity.getByKey(questionid);
    }

    public final int countOptquestionFkQuestionid() { // optquestion - questionid
        return getOptquestionFkQuestionid() == null ? 0 : 1;
    }

    public static final Recordques4exam loadByKey(int id) {
        return Recordques4examEntity.getByKey(id);
    }

    public static final Future<Recordques4exam> asyncByKey(int id) {
        return Recordques4examEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Recordques4exam insert() {
        Recordques4exam result = Recordques4examEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Recordques4exam asyncInsert() {
        Recordques4exam result = Recordques4examEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Recordques4exam insert2() {
        return Recordques4examEntity.insert2(this);
    }

    public final Recordques4exam asyncInsert2() {
        Recordques4exam result = Recordques4examEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Recordques4exam update() {
        return Recordques4examEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Recordques4examEntity.asyncUpdate( this );
        return true;
    }

    public final Recordques4exam insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Recordques4examEntity.delete(id);
    }

    public final void asyncDelete() {
        Recordques4examEntity.asyncDelete(id);
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

    public Recordques4exam clone2() {
        try{
            return (Recordques4exam) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
