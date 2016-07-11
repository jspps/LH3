package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;

import org.apache.commons.logging.*;

import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - optquestion
@SuppressWarnings({ "static-access" })
public class OptquestionEntity extends OptquestionInternal {
	static Log log = LogFactory.getLog(OptquestionEntity.class);

	public static final OptquestionEntity my = new OptquestionEntity();

	static OptquestionDAO OptquestionDAO = null;

	public static OptquestionDAO OptquestionDAO() {
		if (OptquestionDAO == null)
			OptquestionDAO = new OptquestionDAO(
					com.learnhall.content.AppContext.dsData());
		return OptquestionDAO;
	}

	public static void insertMmTry(final Optquestion optquestion) {
		OptquestionDAO DAO = OptquestionDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(optquestion, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/*** 根据试卷id,取得录入的题数 **/
	static public int getCountByExamid(int examid) {
		String sql = "SELECT COUNT(*) FROM  optquestion WHERE examid = "
				+ examid;
		OptquestionDAO dao = OptquestionDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}
	// types end

}
