package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - adprivilege
@SuppressWarnings({ "static-access" })
public class AdprivilegeEntity extends AdprivilegeInternal{
    static Log log = LogFactory.getLog(AdprivilegeEntity.class);

    public static final AdprivilegeEntity my = new AdprivilegeEntity();

    static AdprivilegeDAO AdprivilegeDAO = null;
    public static AdprivilegeDAO AdprivilegeDAO() {
        if( AdprivilegeDAO == null)
            AdprivilegeDAO = new AdprivilegeDAO(com.learnhall.content.AppContext.dsData());
        return AdprivilegeDAO;
    }


    public static void insertMmTry(final Adprivilege adprivilege) {
        AdprivilegeDAO DAO = AdprivilegeDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(adprivilege, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Adprivilege adprivilege) {
        // if(adprivilege == null) return;
        // List<Adprivilege> adprivileges = adprivilege.getAdprivilegesFkParentid(); // adprivilege
    // }

    // types begin
    // types end

}

