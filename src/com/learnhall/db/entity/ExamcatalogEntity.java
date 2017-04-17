package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.learnhall.content.Svc;
import com.learnhall.db.bean.Examcatalog;
import com.learnhall.db.dao.ExamcatalogDAO;
import com.learnhall.db.internal.ExamcatalogInternal;

//learnhall3_design - examcatalog
@SuppressWarnings({ "static-access" })
public class ExamcatalogEntity extends ExamcatalogInternal{
    static Log log = LogFactory.getLog(ExamcatalogEntity.class);

    public static final ExamcatalogEntity my = new ExamcatalogEntity();

    static ExamcatalogDAO ExamcatalogDAO = null;
    public static ExamcatalogDAO ExamcatalogDAO() {
        if( ExamcatalogDAO == null)
            ExamcatalogDAO = new ExamcatalogDAO(com.learnhall.content.AppContext.dsData());
        return ExamcatalogDAO;
    }


    public static void insertMmTry(final Examcatalog examcatalog) {
        ExamcatalogDAO DAO = ExamcatalogDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(examcatalog, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    /** 取得满足参数的总页数(params的val是一个条件字符串> like,!=的模式) */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		ExamcatalogDAO dao = ExamcatalogDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}
	
	/** 取得满足参数的分页列表对象(params的val是一个条件字符串> like,!=的模式) */
	static public List<Examcatalog> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		ExamcatalogDAO dao = ExamcatalogDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Examcatalog.class);
	}
    // types end

}

