package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - cfgs
@SuppressWarnings({ "static-access" })
public class CfgsEntity extends CfgsInternal{
    static Log log = LogFactory.getLog(CfgsEntity.class);

    public static final CfgsEntity my = new CfgsEntity();

    static CfgsDAO CfgsDAO = null;
    public static CfgsDAO CfgsDAO() {
        if( CfgsDAO == null)
            CfgsDAO = new CfgsDAO(com.learnhall.content.AppContext.dsData());
        return CfgsDAO;
    }


    public static void insertMmTry(final Cfgs cfgs) {
        CfgsDAO DAO = CfgsDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(cfgs, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

