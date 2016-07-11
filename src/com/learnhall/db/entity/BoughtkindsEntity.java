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
import com.learnhall.db.bean.Boughtkinds;
import com.learnhall.db.dao.BoughtkindsDAO;
import com.learnhall.db.internal.BoughtkindsInternal;

//learnhall3_design - boughtkinds
@SuppressWarnings({ "static-access" })
public class BoughtkindsEntity extends BoughtkindsInternal {
	static Log log = LogFactory.getLog(BoughtkindsEntity.class);

	public static final BoughtkindsEntity my = new BoughtkindsEntity();

	static BoughtkindsDAO BoughtkindsDAO = null;

	public static BoughtkindsDAO BoughtkindsDAO() {
		if (BoughtkindsDAO == null)
			BoughtkindsDAO = new BoughtkindsDAO(
					com.learnhall.content.AppContext.dsData());
		return BoughtkindsDAO;
	}

	public static void insertMmTry(final Boughtkinds boughtkinds) {
		BoughtkindsDAO DAO = BoughtkindsDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(boughtkinds, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		BoughtkindsDAO dao = BoughtkindsDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Boughtkinds> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		BoughtkindsDAO dao = BoughtkindsDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Boughtkinds.class);
	}
	// types end

}
