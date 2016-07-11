package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - openkind4third
@SuppressWarnings({ "static-access" })
public class Openkind4thirdEntity extends Openkind4thirdInternal{
    static Log log = LogFactory.getLog(Openkind4thirdEntity.class);

    public static final Openkind4thirdEntity my = new Openkind4thirdEntity();

    static Openkind4thirdDAO Openkind4thirdDAO = null;
    public static Openkind4thirdDAO Openkind4thirdDAO() {
        if( Openkind4thirdDAO == null)
            Openkind4thirdDAO = new Openkind4thirdDAO(com.learnhall.content.AppContext.dsData());
        return Openkind4thirdDAO;
    }


    public static void insertMmTry(final Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = Openkind4thirdDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(openkind4third, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

