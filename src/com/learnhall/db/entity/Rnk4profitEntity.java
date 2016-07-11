package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.learnhall.content.AppContext;
import com.learnhall.db.bean.Rnk4profit;
import com.learnhall.db.dao.Rnk4profitDAO;
import com.learnhall.db.internal.Rnk4profitInternal;

//learnhall3_design - rnk4profit
@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
public class Rnk4profitEntity extends Rnk4profitInternal {
	static Log log = LogFactory.getLog(Rnk4profitEntity.class);

	public static final Rnk4profitEntity my = new Rnk4profitEntity();

	static Rnk4profitDAO Rnk4profitDAO = null;

	public static Rnk4profitDAO Rnk4profitDAO() {
		if (Rnk4profitDAO == null)
			Rnk4profitDAO = new Rnk4profitDAO(AppContext.dsData());
		return Rnk4profitDAO;
	}

	public static void insertMmTry(final Rnk4profit rnk4profit) {
		Rnk4profitDAO DAO = Rnk4profitDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(rnk4profit, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/*** 执行 储存过程 process [type:1lhub,0:agent] **/
	static public void exceProcess(int parsType) throws Exception {
		Rnk4profitDAO dao = Rnk4profitDAO();
		String sql = "call pro_rnk4profit(:parsType);";
		Map params = new HashMap();
		params.put("parsType", parsType);
		dao.call(sql, params);

		// sql = "call pro_rnk4profit(?);";
		// dao.call(sql, parsType);
	}

	// /////////////// 日志数据 ///////////////
	/*** 日志数据 **/
	static Rnk4profitDAO logDao = null;

	public static Rnk4profitDAO logDao() {
		if (logDao == null)
			logDao = new Rnk4profitDAO(AppContext.dsLog());
		return logDao;
	}

	/*** 清空日志 **/
	static public void clearLog4Call() throws SQLException {
		Rnk4profitDAO logDao = logDao();
		String sql = "call pro_rnkProfit4Del();";
		logDao.call(sql);
	}

	/*** 取得某个人的排名对象 **/
	static public Rnk4profit getEnBy(int type, int unqid) {
		return getEnBy(type, unqid, "");
	}

	/*** 取得(代理奖金/学中押金)之和 **/
	static public double getSumBonus(int type, int unqid, String dataStr) {
		type = type > 1 ? 1 : 0;
		Rnk4profitDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = PStr.str(logDao.TABLENAME, dataStr,
				(type == 1 ? "_lhub" : "_agent"));
		String sql = "";
		if (unqid > 0) {
			sql = PStr.str("SELECT SUM(bonus) FROM ", TABLENAME2,
					" WHERE ownerid = ", unqid);
		} else {
			sql = PStr.str("SELECT SUM(bonus) FROM ", TABLENAME2);
		}
		try {
			return logDao.queryForDouble(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*** 取得某个人的排名对象 type[1lhub,0agent] **/
	static public Rnk4profit getEnBy(int type, int unqid, String dataStr) {
		type = type > 1 ? 1 : 0;
		Rnk4profitDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = PStr.str(logDao.TABLENAME, dataStr,
				(type == 1 ? "_lhub" : "_agent"));
		return logDao.selectByTypeOwnerid(type, unqid, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rnk4profit> getListBy(int type, int page, int size) {
		return getListBy(type, page, size, "");
	}

	/*** 取得排名列表 **/
	static public List<Rnk4profit> getListBy(int type, int page, int size,
			String dataStr) {
		Rnk4profitDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = PStr.str(logDao.TABLENAME, dataStr,
				(type == 1 ? "_lhub" : "_agent"));
		int begin = (page - 1) * size;
		begin = begin < 0 ? 0 : begin;
		return logDao.selectByPage(begin, size, TABLENAME2);
	}
	// types end

}
