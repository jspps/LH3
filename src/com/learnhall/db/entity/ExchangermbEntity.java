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
import com.learnhall.db.bean.Exchangermb;
import com.learnhall.db.dao.ExchangermbDAO;
import com.learnhall.db.internal.ExchangermbInternal;

//learnhall3_design - exchangermb
@SuppressWarnings({ "static-access" })
public class ExchangermbEntity extends ExchangermbInternal {
	static Log log = LogFactory.getLog(ExchangermbEntity.class);

	public static final ExchangermbEntity my = new ExchangermbEntity();

	static ExchangermbDAO ExchangermbDAO = null;

	public static ExchangermbDAO ExchangermbDAO() {
		if (ExchangermbDAO == null)
			ExchangermbDAO = new ExchangermbDAO(
					com.learnhall.content.AppContext.dsData());
		return ExchangermbDAO;
	}

	public static void insertMmTry(final Exchangermb exchangermb) {
		ExchangermbDAO DAO = ExchangermbDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(exchangermb, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 批量处理更新对象 */
	static public void updateByKey(List<Exchangermb> exchangermbs) {
		ExchangermbDAO dao = ExchangermbDAO();
		dao.updateByKey(exchangermbs);
	}
	
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		ExchangermbDAO dao = ExchangermbDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Exchangermb> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		ExchangermbDAO dao = ExchangermbDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY lasttime DESC LIMIT " + right;
		return dao.queryForList(sql, Exchangermb.class);
	}
	// types end

}
