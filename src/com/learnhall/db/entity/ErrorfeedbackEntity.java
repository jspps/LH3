package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - errorfeedback
@SuppressWarnings({ "static-access" })
public class ErrorfeedbackEntity extends ErrorfeedbackInternal{
    static Log log = LogFactory.getLog(ErrorfeedbackEntity.class);

    public static final ErrorfeedbackEntity my = new ErrorfeedbackEntity();

    static ErrorfeedbackDAO ErrorfeedbackDAO = null;
    public static ErrorfeedbackDAO ErrorfeedbackDAO() {
        if( ErrorfeedbackDAO == null)
            ErrorfeedbackDAO = new ErrorfeedbackDAO(com.learnhall.content.AppContext.dsData());
        return ErrorfeedbackDAO;
    }


    public static void insertMmTry(final Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = ErrorfeedbackDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(errorfeedback, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

