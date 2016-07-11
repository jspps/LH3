package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - record4orders
@SuppressWarnings({ "static-access" })
public class Record4ordersEntity extends Record4ordersInternal {
	static Log log = LogFactory.getLog(Record4ordersEntity.class);

	public static final Record4ordersEntity my = new Record4ordersEntity();

	static Record4ordersDAO Record4ordersDAO = null;

	public static Record4ordersDAO Record4ordersDAO() {
		if (Record4ordersDAO == null)
			Record4ordersDAO = new Record4ordersDAO(
					com.learnhall.content.AppContext.dsData());
		return Record4ordersDAO;
	}

	public static void insertMmTry(final Record4orders record4orders) {
		Record4ordersDAO DAO = Record4ordersDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(record4orders, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	static Record4ordersDAO logDao = null;

	public static Record4ordersDAO logDao() {
		if (logDao == null)
			logDao = new Record4ordersDAO(
					com.learnhall.content.AppContext.dsLog());
		return logDao;
	}

	public static void insertAsyn2Log(final Record4orders record4orders) {
		Record4ordersDAO DAO = logDao();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(record4orders, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}
	// types end

}
