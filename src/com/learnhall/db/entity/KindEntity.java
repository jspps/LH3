package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.dao.KindDAO;
import com.learnhall.db.internal.KindInternal;

//learnhall3_design - kind
@SuppressWarnings({ "static-access" })
public class KindEntity extends KindInternal {
	static Log log = LogFactory.getLog(KindEntity.class);

	public static final KindEntity my = new KindEntity();

	static KindDAO KindDAO = null;

	public static KindDAO KindDAO() {
		if (KindDAO == null)
			KindDAO = new KindDAO(com.learnhall.content.AppContext.dsData());
		return KindDAO;
	}

	public static void insertMmTry(final Kind kind) {
		KindDAO DAO = KindDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(kind, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// public void loadLinked(final Kind kind) {
	// if(kind == null) return;
	// List<Appraise> appraises = kind.getAppraisesFkKindid(); // appraise
	// List<Exam2kind> exam2kinds = kind.getExam2kindsFkKindid(); // exam2kind
	// List<Order> orders = kind.getOrdersFkKindid(); // order
	// List<Shoppingcart> shoppingcarts = kind.getShoppingcartsFkKindid(); //
	// shoppingcart
	// }

	// types begin

	static public List<Kind> getListByDepardidNmmajor(int departid,
			String nmMajor) {
		KindDAO dao = KindDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT * FROM ").append(dao.TABLENAME)
				.append(" WHERE 1 = 1 ");
		buff.append(" AND coursid IN(SELECT cid FROM adcourses WHERE 1 = 1 ");
		if (departid > 0) {
			buff.append(" AND departid = ").append(departid);
		}
		if (!StrEx.isEmptyTrim(nmMajor)) {
			buff.append(" AND nmMajor = '").append(nmMajor).append("'");
		}
		buff.append(")");

		String sql = buff.toString();
		try {
			return dao.queryForList(sql, Kind.class);
		} catch (Exception e) {
		}
		return new ArrayList<Kind>();
	}

	static public List<Kind> getListByName(String name) {
		KindDAO dao = KindDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT * FROM ").append(dao.TABLENAME)
				.append(" WHERE 1 = 1 ");
		buff.append(" AND coursid IN(SELECT cid FROM adcourses WHERE 1 = 1 ");
		if (!StrEx.isEmptyTrim(name)) {
			buff.append(" AND (");
			buff.append(" nmMajor like '%").append(name).append("%'");
			buff.append(" OR nmSub like '%").append(name).append("%'");
			buff.append(" )");
		}
		buff.append(")");

		String sql = buff.toString();
		try {
			return dao.queryForList(sql, Kind.class);
		} catch (Exception e) {
		}
		return new ArrayList<Kind>();
	}
	// types end

}
