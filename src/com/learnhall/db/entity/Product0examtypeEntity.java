package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - product0examtype
@SuppressWarnings({ "static-access" })
public class Product0examtypeEntity extends Product0examtypeInternal{
    static Log log = LogFactory.getLog(Product0examtypeEntity.class);

    public static final Product0examtypeEntity my = new Product0examtypeEntity();

    static Product0examtypeDAO Product0examtypeDAO = null;
    public static Product0examtypeDAO Product0examtypeDAO() {
        if( Product0examtypeDAO == null)
            Product0examtypeDAO = new Product0examtypeDAO(com.learnhall.content.AppContext.dsData());
        return Product0examtypeDAO;
    }


    public static void insertMmTry(final Product0examtype product0examtype) {
        Product0examtypeDAO DAO = Product0examtypeDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(product0examtype, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

