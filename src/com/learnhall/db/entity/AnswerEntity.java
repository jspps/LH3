package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.learnhall.content.AppContext;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Answer;
import com.learnhall.db.dao.AnswerDAO;
import com.learnhall.db.internal.AnswerInternal;

//learnhall3_design - answer
@SuppressWarnings({ "static-access" })
public class AnswerEntity extends AnswerInternal{
    static Log log = LogFactory.getLog(AnswerEntity.class);

    public static final AnswerEntity my = new AnswerEntity();

    static AnswerDAO AnswerDAO = null;
    public static AnswerDAO AnswerDAO() {
        if( AnswerDAO == null)
            AnswerDAO = new AnswerDAO(AppContext.dsData());
        return AnswerDAO;
    }


    public static void insertMmTry(final Answer answer) {
        AnswerDAO DAO = AnswerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(answer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    /** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		AnswerDAO dao = AnswerDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Answer> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		AnswerDAO dao = AnswerDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Answer.class);
	}
    // types end

}

