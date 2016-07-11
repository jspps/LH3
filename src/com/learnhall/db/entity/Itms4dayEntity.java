package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - itms4day
@SuppressWarnings({ "static-access" })
public class Itms4dayEntity extends Itms4dayInternal{
    static Log log = LogFactory.getLog(Itms4dayEntity.class);

    public static final Itms4dayEntity my = new Itms4dayEntity();

    static Itms4dayDAO Itms4dayDAO = null;
    public static Itms4dayDAO Itms4dayDAO() {
        if( Itms4dayDAO == null)
            Itms4dayDAO = new Itms4dayDAO(com.learnhall.content.AppContext.dsData());
        return Itms4dayDAO;
    }


    public static void insertMmTry(final Itms4day itms4day) {
        Itms4dayDAO DAO = Itms4dayDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(itms4day, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

