package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - customer
@SuppressWarnings({ "static-access" })
public class CustomerEntity extends CustomerInternal{
    static Log log = LogFactory.getLog(CustomerEntity.class);

    public static final CustomerEntity my = new CustomerEntity();

    static CustomerDAO CustomerDAO = null;
    public static CustomerDAO CustomerDAO() {
        if( CustomerDAO == null)
            CustomerDAO = new CustomerDAO(com.learnhall.content.AppContext.dsData());
        return CustomerDAO;
    }


    public static void insertMmTry(final Customer customer) {
        CustomerDAO DAO = CustomerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(customer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Customer customer) {
        // if(customer == null) return;
        // List<Appraise> appraises = customer.getAppraisesFkCustomerid(); // appraise
        // List<Ask> asks = customer.getAsksFkCustomerid(); // ask
        // List<Errorfeedback> errorfeedbacks = customer.getErrorfeedbacksFkCustomerid(); // errorfeedback
        // List<Order> orders = customer.getOrdersFkCustomerid(); // order
        // List<Product> products = customer.getProductsFkCoursesid(); // product
        // List<Putquestion> putquestions = customer.getPutquestionsFkCustomerid(); // putquestion
        // List<Shoppingcart> shoppingcarts = customer.getShoppingcartsFkCustomerid(); // shoppingcart
    // }

    // types begin
    // types end

}

