package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - exam
@SuppressWarnings({ "static-access" })
public class ExamEntity extends ExamInternal{
    static Log log = LogFactory.getLog(ExamEntity.class);

    public static final ExamEntity my = new ExamEntity();

    static ExamDAO ExamDAO = null;
    public static ExamDAO ExamDAO() {
        if( ExamDAO == null)
            ExamDAO = new ExamDAO(com.learnhall.content.AppContext.dsData());
        return ExamDAO;
    }


    public static void insertMmTry(final Exam exam) {
        ExamDAO DAO = ExamDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(exam, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Exam exam) {
        // if(exam == null) return;
        // List<Aerrorrecord> aerrorrecords = exam.getAerrorrecordsFkExamid(); // aerrorrecord
        // List<Errorfeedback> errorfeedbacks = exam.getErrorfeedbacksFkExamid(); // errorfeedback
        // List<Exam2kind> exam2kinds = exam.getExam2kindsFkExamid(); // exam2kind
        // List<Optquestion> optquestions = exam.getOptquestionsFkExamid(); // optquestion
    // }

    // types begin
    // types end

}

