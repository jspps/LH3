package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Appraise;
import com.learnhall.db.dao.AppraiseDAO;
import com.learnhall.db.internal.AppraiseInternal;

//learnhall3_design - appraise
@SuppressWarnings({ "static-access" })
public class AppraiseEntity extends AppraiseInternal {
	static Log log = LogFactory.getLog(AppraiseEntity.class);

	public static final AppraiseEntity my = new AppraiseEntity();

	static AppraiseDAO AppraiseDAO = null;

	public static AppraiseDAO AppraiseDAO() {
		if (AppraiseDAO == null)
			AppraiseDAO = new AppraiseDAO(
					com.learnhall.content.AppContext.dsData());
		return AppraiseDAO;
	}

	public static void insertMmTry(final Appraise appraise) {
		AppraiseDAO DAO = AppraiseDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(appraise, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		AppraiseDAO dao = AppraiseDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Appraise> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		AppraiseDAO dao = AppraiseDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Appraise.class);
	}

	/** 取得平均分数率 = (总得的评分/总共的评分 ) */
	static public double getAvgRate4Score(int kindid) {
		AppraiseDAO dao = AppraiseDAO();

		int count = 0;
		String sql4Count = PStr.str("SELECT COUNT(*) FROM ", dao.TABLENAME,
				" WHERE 1 = 1 AND kindid = ", kindid);

		try {
			count = dao.queryForInt(sql4Count);
		} catch (SQLException e) {
		}

		int scoreCur = 0;
		String sql4SumScore = PStr.str("SELECT SUM(score) FROM ",
				dao.TABLENAME, " WHERE 1 = 1 AND kindid = ", kindid);

		try {
			scoreCur = dao.queryForInt(sql4SumScore);
		} catch (SQLException e) {
		}

		double scoreAll = count * 5;
		if (scoreAll <= 0) {
			return 0;
		}

		return scoreCur / scoreAll;
	}
	// types end

}
