package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.learnhall.content.AppContext;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.dao.AdcoursesDAO;
import com.learnhall.db.internal.AdcoursesInternal;

//learnhall3_design - adcourses
@SuppressWarnings({ "static-access", "rawtypes" })
public class AdcoursesEntity extends AdcoursesInternal {
	static Log log = LogFactory.getLog(AdcoursesEntity.class);

	public static final AdcoursesEntity my = new AdcoursesEntity();

	static AdcoursesDAO AdcoursesDAO = null;

	public static AdcoursesDAO AdcoursesDAO() {
		if (AdcoursesDAO == null)
			AdcoursesDAO = new AdcoursesDAO(AppContext.dsData());
		return AdcoursesDAO;
	}

	public static void insertMmTry(final Adcourses adcourses) {
		AdcoursesDAO DAO = AdcoursesDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(adcourses, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/**
	 * 取得满足参数的总页数
	 * 
	 * @throws SQLException
	 */
	public static int getCountAllBy(Map<String, Object> params)
			throws SQLException {
		AdcoursesDAO DAO = AdcoursesDAO();
		String sql = Svc.getSql4Count(DAO.TABLENAME, params);
		return DAO.queryForInt(sql);
	}

	/**
	 * 取得满足参数的分页列表对象
	 * 
	 * @throws Exception
	 */
	public static List<Adcourses> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		AdcoursesDAO DAO = AdcoursesDAO();
		String sql = Svc.getSql4List(DAO.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = PStr.str(left, " ORDER BY createtime DESC LIMIT ", right);
		return DAO.queryForList(sql, Adcourses.class);
	}

	/*** 取得专业名列表 */
	public static List<Map> getNmmajors(int departid) {
		AdcoursesDAO DAO = AdcoursesDAO();
		PStr pStr = PStr.b();
		String sql = "";
		pStr.a("SELECT DISTINCT nmMajor FROM (");
		pStr.a("SELECT nmMajor FROM ", DAO.TABLENAME,
				" WHERE 1 = 1 ");
		if (departid > 0) {
			pStr.a(" AND departid = ", departid);
		}
		pStr.a(" GROUP BY nmMajor,createtime ");
		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" ) AS Tmp");
		List<Map> result = new ArrayList<Map>();
		try {
			sql = pStr.e();
			List<Map> list = DAO.queryForList(sql);
			if (!ListEx.isEmpty(list)) {
				result.addAll(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*** 取得专业名列表 */
	public static List<Map> getNmmajors(String nmMajor) {
		AdcoursesDAO DAO = AdcoursesDAO();
		PStr pStr = PStr.b();
		String sql = "";
		pStr.a("SELECT DISTINCT nmMajor FROM (");
		pStr.a("SELECT nmMajor FROM ", DAO.TABLENAME,
				" WHERE 1 = 1 ");
		if (!StrEx.isEmptyTrim(nmMajor)) {
			pStr.a(" AND nmMajor like '%", nmMajor, "%'");
		}

		pStr.a(" GROUP BY nmMajor,createtime ");
		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" ) AS Tmp");
		List<Map> result = new ArrayList<Map>();
		try {
			sql = pStr.e();
			List<Map> list = DAO.queryForList(sql);
			if (!ListEx.isEmpty(list)) {
				result.addAll(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*** 取得层级名列表 */
	public static List<String> getNmlevels(int departid, String nmmajor) {
		AdcoursesDAO DAO = AdcoursesDAO();
		PStr pStr = PStr.b();
		String sql = "";
		pStr.a("SELECT DISTINCT nmLevel FROM (");
		pStr.a("SELECT nmLevel FROM ", DAO.TABLENAME, " WHERE 1 = 1 ");
		if (departid > 0) {
			pStr.a(" AND departid = ", departid);
		}

		if (!StrEx.isEmptyTrim(nmmajor)) {
			pStr.a(" AND nmMajor = '", nmmajor + "'");
		}

		pStr.a(" GROUP BY nmLevel,createtime ");

		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" ) AS Tmp");
		List<String> result = new ArrayList<String>();
		try {
			sql = pStr.e();
			List<Map> list = DAO.queryForList(sql);
			if (!ListEx.isEmpty(list)) {
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Map map = list.get(i);
					result.add(MapEx.getString(map, "nmLevel"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*** 取得专业列表 */
	public static List<String> getNmsubs(int departid, String nmmajor,
			String nmLevel) {
		AdcoursesDAO DAO = AdcoursesDAO();
		PStr pStr = PStr.b();
		String sql = "";
		pStr.a("SELECT DISTINCT nmSub FROM (");
		pStr.a("SELECT nmSub FROM ", DAO.TABLENAME, " WHERE 1 = 1 ");
		if (departid > 0) {
			pStr.a(" AND departid = ", departid);
		}

		if (!StrEx.isEmptyTrim(nmmajor)) {
			pStr.a(" AND nmMajor = '", nmmajor + "'");
		}

		if (!StrEx.isEmptyTrim(nmLevel)) {
			pStr.a(" AND nmLevel = '", nmLevel + "'");
		}

		pStr.a(" GROUP BY nmSub,createtime ");

		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" ) AS Tmp");
		List<String> result = new ArrayList<String>();
		try {
			sql = pStr.e();
			List<Map> list = DAO.queryForList(sql);
			if (!ListEx.isEmpty(list)) {
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Map map = list.get(i);
					result.add(MapEx.getString(map, "nmSub"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*** 取得考试范围列表 */
	public static List<String> getNmAreas(int departid, String nmmajor,
			String nmLevel, String nmSub) {
		AdcoursesDAO DAO = AdcoursesDAO();
		PStr pStr = PStr.b();
		String sql = "";
		pStr.a("SELECT DISTINCT nmArea FROM (");
		pStr.a("SELECT nmArea FROM ", DAO.TABLENAME, " WHERE 1 = 1 ");
		if (departid > 0) {
			pStr.a(" AND departid = ", departid);
		}

		if (!StrEx.isEmptyTrim(nmmajor)) {
			pStr.a(" AND nmMajor = '", nmmajor, "'");
		}

		if (!StrEx.isEmptyTrim(nmLevel)) {
			pStr.a(" AND nmLevel = '", nmLevel, "'");
		}

		if (!StrEx.isEmptyTrim(nmSub)) {
			pStr.a(" AND nmSub = '", nmSub, "'");
		}

		pStr.a(" GROUP BY nmArea,createtime ");

		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" ) AS Tmp");
		List<String> result = new ArrayList<String>();
		try {
			sql = pStr.e();
			List<Map> list = DAO.queryForList(sql);
			if (!ListEx.isEmpty(list)) {
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Map map = list.get(i);
					result.add(MapEx.getString(map, "nmArea"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean isHasCourses(int departid, String nmMajor,
			String nmLevel, String nmSub, String nmArea) {
		AdcoursesDAO DAO = AdcoursesDAO();
		PStr pStr = PStr.b();
		String sql = "";
		pStr.a("SELECT COUNT(*) FROM ", DAO.TABLENAME, " WHERE 1 = 1 ");
		if (departid > 0) {
			pStr.a(" AND departid = ", departid);
		}

		if (!StrEx.isEmptyTrim(nmMajor)) {
			pStr.a(" AND nmMajor = '", nmMajor.trim(), "'");
		}

		if (!StrEx.isEmptyTrim(nmLevel)) {
			pStr.a(" AND nmLevel = '", nmLevel.trim(), "'");
		}

		if (!StrEx.isEmptyTrim(nmSub)) {
			pStr.a(" AND nmSub = '", nmSub.trim(), "'");
		}

		if (!StrEx.isEmptyTrim(nmArea)) {
			pStr.a(" AND nmArea = '", nmArea.trim(), "'");
		}

		pStr.a(" ORDER BY createtime DESC ");
		try {
			sql = pStr.e();
			int val = DAO.queryForInt(sql);
			return val > 0;
		} catch (SQLException e) {

		}
		return false;
	}

	public static Adcourses getCourse(int departid, String nmMajor,
			String nmLevel, String nmSub, String nmArea) {
		AdcoursesDAO DAO = AdcoursesDAO();
		String sql = "";
		PStr pStr = PStr.b();
		pStr.a("SELECT * FROM ", DAO.TABLENAME, " WHERE 1 = 1 ");
		if (departid > 0) {
			pStr.a(" AND departid = ", departid);
		}

		if (!StrEx.isEmptyTrim(nmMajor)) {
			pStr.a(" AND nmMajor = '", nmMajor.trim(), "'");
		}

		if (!StrEx.isEmptyTrim(nmLevel)) {
			pStr.a(" AND nmLevel = '", nmLevel.trim(), "'");
		}

		if (!StrEx.isEmptyTrim(nmSub)) {
			pStr.a(" AND nmSub = '", nmSub.trim(), "'");
		}

		if (!StrEx.isEmptyTrim(nmArea)) {
			pStr.a(" AND nmArea = '", nmArea.trim(), "'");
		}

		pStr.a(" ORDER BY createtime DESC LIMIT 0,1");
		try {
			sql = pStr.e();
			return DAO.queryForObject(sql, Adcourses.class);
		} catch (Exception e) {
		}
		return null;
	}
	// types end

}
