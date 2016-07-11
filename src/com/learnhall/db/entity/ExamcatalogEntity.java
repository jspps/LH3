package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - examcatalog
@SuppressWarnings({ "static-access" })
public class ExamcatalogEntity extends ExamcatalogInternal{
    static Log log = LogFactory.getLog(ExamcatalogEntity.class);

    public static final ExamcatalogEntity my = new ExamcatalogEntity();

    static ExamcatalogDAO ExamcatalogDAO = null;
    public static ExamcatalogDAO ExamcatalogDAO() {
        if( ExamcatalogDAO == null)
            ExamcatalogDAO = new ExamcatalogDAO(com.learnhall.content.AppContext.dsData());
        return ExamcatalogDAO;
    }


    public static void insertMmTry(final Examcatalog examcatalog) {
        ExamcatalogDAO DAO = ExamcatalogDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(examcatalog, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

