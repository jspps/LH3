package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - orders4profit
@SuppressWarnings({ "static-access" })
public class Orders4profitEntity extends Orders4profitInternal{
    static Log log = LogFactory.getLog(Orders4profitEntity.class);

    public static final Orders4profitEntity my = new Orders4profitEntity();

    static Orders4profitDAO Orders4profitDAO = null;
    public static Orders4profitDAO Orders4profitDAO() {
        if( Orders4profitDAO == null)
            Orders4profitDAO = new Orders4profitDAO(com.learnhall.content.AppContext.dsData());
        return Orders4profitDAO;
    }


    public static void insertMmTry(final Orders4profit orders4profit) {
        Orders4profitDAO DAO = Orders4profitDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(orders4profit, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

