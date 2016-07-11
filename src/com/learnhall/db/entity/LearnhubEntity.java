package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - learnhub
@SuppressWarnings({ "static-access" })
public class LearnhubEntity extends LearnhubInternal{
    static Log log = LogFactory.getLog(LearnhubEntity.class);

    public static final LearnhubEntity my = new LearnhubEntity();

    static LearnhubDAO LearnhubDAO = null;
    public static LearnhubDAO LearnhubDAO() {
        if( LearnhubDAO == null)
            LearnhubDAO = new LearnhubDAO(com.learnhall.content.AppContext.dsData());
        return LearnhubDAO;
    }


    public static void insertMmTry(final Learnhub learnhub) {
        LearnhubDAO DAO = LearnhubDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(learnhub, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Learnhub learnhub) {
        // if(learnhub == null) return;
        // List<Errorfeedback> errorfeedbacks = learnhub.getErrorfeedbacksFkLhubid(); // errorfeedback
        // List<Exam> exams = learnhub.getExamsFkLhubid(); // exam
        // List<Kind> kinds = learnhub.getKindsFkLhubid(); // kind
        // List<Kindmin> kindmins = learnhub.getKindminsFkLhubid(); // kindmin
        // List<Order> orders = learnhub.getOrdersFkLhubid(); // order
        // List<Product> products = learnhub.getProductsFkLhubid(); // product
    // }

    // types begin
    // types end

}

