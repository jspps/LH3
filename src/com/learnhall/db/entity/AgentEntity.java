package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - agent
@SuppressWarnings({ "static-access" })
public class AgentEntity extends AgentInternal{
    static Log log = LogFactory.getLog(AgentEntity.class);

    public static final AgentEntity my = new AgentEntity();

    static AgentDAO AgentDAO = null;
    public static AgentDAO AgentDAO() {
        if( AgentDAO == null)
            AgentDAO = new AgentDAO(com.learnhall.content.AppContext.dsData());
        return AgentDAO;
    }


    public static void insertMmTry(final Agent agent) {
        AgentDAO DAO = AgentDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(agent, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Agent agent) {
        // if(agent == null) return;
        // List<Order> orders = agent.getOrdersFkAgentid(); // order
    // }

    // types begin
    // types end

}

