package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - adqdepartment
@SuppressWarnings({ "static-access" })
public class AdqdepartmentEntity extends AdqdepartmentInternal{
    static Log log = LogFactory.getLog(AdqdepartmentEntity.class);

    public static final AdqdepartmentEntity my = new AdqdepartmentEntity();

    static AdqdepartmentDAO AdqdepartmentDAO = null;
    public static AdqdepartmentDAO AdqdepartmentDAO() {
        if( AdqdepartmentDAO == null)
            AdqdepartmentDAO = new AdqdepartmentDAO(com.learnhall.content.AppContext.dsData());
        return AdqdepartmentDAO;
    }


    public static void insertMmTry(final Adqdepartment adqdepartment) {
        AdqdepartmentDAO DAO = AdqdepartmentDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(adqdepartment, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Adqdepartment adqdepartment) {
        // if(adqdepartment == null) return;
        // List<Adcourses> adcoursess = adqdepartment.getAdcoursessFkDepartid(); // adcourses
        // List<Adqmajor> adqmajors = adqdepartment.getAdqmajorsFkDepartid(); // adqmajor
    // }

    // types begin
    // types end

}

