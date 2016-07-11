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
import com.learnhall.db.bean.Openkind4customer;
import com.learnhall.db.dao.Openkind4customerDAO;
import com.learnhall.db.internal.Openkind4customerInternal;

//learnhall3_design - openkind4customer
@SuppressWarnings({ "static-access" })
public class Openkind4customerEntity extends Openkind4customerInternal {
	static Log log = LogFactory.getLog(Openkind4customerEntity.class);

	public static final Openkind4customerEntity my = new Openkind4customerEntity();

	static Openkind4customerDAO Openkind4customerDAO = null;

	public static Openkind4customerDAO Openkind4customerDAO() {
		if (Openkind4customerDAO == null)
			Openkind4customerDAO = new Openkind4customerDAO(
					com.learnhall.content.AppContext.dsData());
		return Openkind4customerDAO;
	}

	public static void insertMmTry(final Openkind4customer openkind4customer) {
		Openkind4customerDAO DAO = Openkind4customerDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(openkind4customer, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		Openkind4customerDAO dao = Openkind4customerDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Openkind4customer> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		Openkind4customerDAO dao = Openkind4customerDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Openkind4customer.class);
	}
	// types end

}
