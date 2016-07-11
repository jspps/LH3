package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - recordfee4customer
@SuppressWarnings({ "static-access" })
public class Recordfee4customerEntity extends Recordfee4customerInternal{
    static Log log = LogFactory.getLog(Recordfee4customerEntity.class);

    public static final Recordfee4customerEntity my = new Recordfee4customerEntity();

    static Recordfee4customerDAO Recordfee4customerDAO = null;
    public static Recordfee4customerDAO Recordfee4customerDAO() {
        if( Recordfee4customerDAO == null)
            Recordfee4customerDAO = new Recordfee4customerDAO(com.learnhall.content.AppContext.dsData());
        return Recordfee4customerDAO;
    }


    public static void insertMmTry(final Recordfee4customer recordfee4customer) {
        Recordfee4customerDAO DAO = Recordfee4customerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(recordfee4customer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

