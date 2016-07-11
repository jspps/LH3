package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - recordkbi4customer
@SuppressWarnings({ "static-access" })
public class Recordkbi4customerEntity extends Recordkbi4customerInternal{
    static Log log = LogFactory.getLog(Recordkbi4customerEntity.class);

    public static final Recordkbi4customerEntity my = new Recordkbi4customerEntity();

    static Recordkbi4customerDAO Recordkbi4customerDAO = null;
    public static Recordkbi4customerDAO Recordkbi4customerDAO() {
        if( Recordkbi4customerDAO == null)
            Recordkbi4customerDAO = new Recordkbi4customerDAO(com.learnhall.content.AppContext.dsData());
        return Recordkbi4customerDAO;
    }


    public static void insertMmTry(final Recordkbi4customer recordkbi4customer) {
        Recordkbi4customerDAO DAO = Recordkbi4customerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(recordkbi4customer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

