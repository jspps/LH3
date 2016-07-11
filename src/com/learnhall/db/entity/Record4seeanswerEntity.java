package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
import com.learnhall.content.AppContext;

//learnhall3_design - record4seeanswer
@SuppressWarnings({ "static-access" })
public class Record4seeanswerEntity extends Record4seeanswerInternal{
    static Log log = LogFactory.getLog(Record4seeanswerEntity.class);

    public static final Record4seeanswerEntity my = new Record4seeanswerEntity();

    static Record4seeanswerDAO Record4seeanswerDAO = null;
    public static Record4seeanswerDAO Record4seeanswerDAO() {
        if( Record4seeanswerDAO == null)
            Record4seeanswerDAO = new Record4seeanswerDAO(AppContext.dsData());
        return Record4seeanswerDAO;
    }


    public static void insertMmTry(final Record4seeanswer record4seeanswer) {
        Record4seeanswerDAO DAO = Record4seeanswerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(record4seeanswer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

