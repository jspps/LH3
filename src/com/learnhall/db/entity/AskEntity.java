package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Ask;
import com.learnhall.db.dao.AskDAO;
import com.learnhall.db.internal.AskInternal;

//learnhall3_design - ask
@SuppressWarnings({ "static-access" })
public class AskEntity extends AskInternal {
	static Log log = LogFactory.getLog(AskEntity.class);

	public static final AskEntity my = new AskEntity();

	static AskDAO AskDAO = null;

	public static AskDAO AskDAO() {
		if (AskDAO == null)
			AskDAO = new AskDAO(com.learnhall.content.AppContext.dsData());
		return AskDAO;
	}

	public static void insertMmTry(final Ask ask) {
		AskDAO DAO = AskDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(ask, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		AskDAO dao = AskDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Ask> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		AskDAO dao = AskDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Ask.class);
	}
	// types end

}
