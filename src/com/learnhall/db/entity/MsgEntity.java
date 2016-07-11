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
import com.learnhall.db.bean.Msg;
import com.learnhall.db.dao.MsgDAO;
import com.learnhall.db.internal.MsgInternal;

//learnhall3_design - msg
@SuppressWarnings({ "static-access" })
public class MsgEntity extends MsgInternal {
	static Log log = LogFactory.getLog(MsgEntity.class);

	public static final MsgEntity my = new MsgEntity();

	static MsgDAO MsgDAO = null;

	public static MsgDAO MsgDAO() {
		if (MsgDAO == null)
			MsgDAO = new MsgDAO(com.learnhall.content.AppContext.dsData());
		return MsgDAO;
	}

	public static void insertMmTry(final Msg msg) {
		MsgDAO DAO = MsgDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(msg, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		MsgDAO dao = MsgDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Msg> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		MsgDAO dao = MsgDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Msg.class);
	}
	// types end

}
