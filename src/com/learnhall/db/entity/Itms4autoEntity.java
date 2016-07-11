package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - itms4auto
@SuppressWarnings({ "static-access" })
public class Itms4autoEntity extends Itms4autoInternal{
    static Log log = LogFactory.getLog(Itms4autoEntity.class);

    public static final Itms4autoEntity my = new Itms4autoEntity();

    static Itms4autoDAO Itms4autoDAO = null;
    public static Itms4autoDAO Itms4autoDAO() {
        if( Itms4autoDAO == null)
            Itms4autoDAO = new Itms4autoDAO(com.learnhall.content.AppContext.dsData());
        return Itms4autoDAO;
    }


    public static void insertMmTry(final Itms4auto itms4auto) {
        Itms4autoDAO DAO = Itms4autoDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(itms4auto, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

