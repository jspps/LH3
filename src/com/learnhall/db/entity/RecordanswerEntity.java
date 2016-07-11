package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.objpool.StringBufPool;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Recordanswer;
import com.learnhall.db.dao.RecordanswerDAO;
import com.learnhall.db.internal.RecordanswerInternal;

//learnhall3_design - recordanswer
@SuppressWarnings({ "static-access" })
public class RecordanswerEntity extends RecordanswerInternal {
	static Log log = LogFactory.getLog(RecordanswerEntity.class);
	static RecordanswerDAO DAO = RecordanswerDAO();

	public static final RecordanswerEntity my = new RecordanswerEntity();

	static RecordanswerDAO RecordanswerDAO = null;

	public static RecordanswerDAO RecordanswerDAO() {
		if (RecordanswerDAO == null)
			RecordanswerDAO = new RecordanswerDAO(
					com.learnhall.content.AppContext.dsData());
		return RecordanswerDAO;
	}

	public static void insertMmTry(final Recordanswer recordanswer) {
		RecordanswerDAO DAO = RecordanswerDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(recordanswer, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	/*** 最新模考榜单查询 **/
	@SuppressWarnings("unchecked")
	public static List<Recordanswer> selectByNewTestList(
			final Integer departid, final String nmMajor, final String nmLevel,
			final String nmSub, String nmArea) {

		// String TABLENAME2 = DAO.TABLEMM();
		StringBuffer sql = StringBufPool.borrowObject();
		try {

			sql.append("SELECT * FROM recordanswer");
			sql.append(" WHERE courseid IN(");
			sql.append(" SELECT cid FROM adcourses WHERE 1 = 1 ");

			if (departid > 0) {
				sql.append(" AND departid = ").append(departid);
			}

			if (!StrEx.isEmptyTrim(nmMajor)) {
				sql.append(" AND nmMajor = '").append(nmMajor.trim())
						.append("'");
			}

			if (!StrEx.isEmptyTrim(nmLevel)) {
				sql.append(" AND nmLevel = '").append(nmLevel.trim())
						.append("'");
			}

			if (!StrEx.isEmptyTrim(nmSub)) {
				sql.append(" AND nmSub = '").append(nmSub.trim()).append("'");
			}

			if (!StrEx.isEmptyTrim(nmArea)) {
				sql.append(" AND nmArea = '").append(nmArea.trim()).append("'");
			}
			sql.append(") ");
			sql.append(" ORDER BY score DESC LIMIT 0,10");
			return DAO.queryForList(sql.toString(), Recordanswer.class);
		} catch (Exception e) {
			log.info(e2s(e));
			return newList();
		} finally {
			StringBufPool.returnObject(sql);
		}
	}

	/*** 取得统计的正确率 **/
	static public int reckon4CustExamType(int custid, int examtype) {
		StringBuffer buff = StringBufPool.borrowObject();
		try {
			buff.append("SELECT AVG(avecorrectrate) AS recken FROM ")
					.append(DAO.TABLENAME).append(" ");
			buff.append(" WHERE 1= 1 AND ").append(" customerid = ")
					.append(custid).append(" AND examType = ").append(examtype);
			return DAO.queryForInt(buff.toString());
		} catch (Exception e) {
			return 0;
		} finally {
			StringBufPool.returnObject(buff);
		}
	}

	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		String sql = Svc.getSql4Count(DAO.TABLENAME, params);
		return DAO.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Recordanswer> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		String sql = Svc.getSql4List(DAO.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return DAO.queryForList(sql, Recordanswer.class);
	}
	// types end

}
