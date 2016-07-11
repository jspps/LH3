package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - aduser
@SuppressWarnings({ "static-access" })
public class AduserEntity extends AduserInternal{
    static Log log = LogFactory.getLog(AduserEntity.class);

    public static final AduserEntity my = new AduserEntity();

    static AduserDAO AduserDAO = null;
    public static AduserDAO AduserDAO() {
        if( AduserDAO == null)
            AduserDAO = new AduserDAO(com.learnhall.content.AppContext.dsData());
        return AduserDAO;
    }


    public static void insertMmTry(final Aduser aduser) {
        AduserDAO DAO = AduserDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(aduser, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

