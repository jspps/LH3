package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.objpool.StringBufPool;
import com.learnhall.db.bean.Recordques4exam;
import com.learnhall.db.dao.Recordques4examDAO;
import com.learnhall.db.internal.Recordques4examInternal;

//learnhall3_design - recordques4exam
@SuppressWarnings({ "static-access" })
public class Recordques4examEntity extends Recordques4examInternal {
	static Log log = LogFactory.getLog(Recordques4examEntity.class);

	public static final Recordques4examEntity my = new Recordques4examEntity();

	static Recordques4examDAO Recordques4examDAO = null;

	public static Recordques4examDAO Recordques4examDAO() {
		if (Recordques4examDAO == null)
			Recordques4examDAO = new Recordques4examDAO(
					com.learnhall.content.AppContext.dsData());
		return Recordques4examDAO;
	}

	public static void insertMmTry(final Recordques4exam recordques4exam) {
		Recordques4examDAO DAO = Recordques4examDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(recordques4exam, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	/*** 取得统计的正确率 **/
	static public int reckon4CustCatalog(int custid, int quesCatalog) {
		StringBuffer buff = StringBufPool.borrowObject();
		try {
			Recordques4examDAO dao = Recordques4examDAO();
			buff.append("SELECT COUNT(*) AS recken FROM ")
					.append(dao.TABLENAME).append(" ");
			buff.append(" WHERE 1= 1 AND numError = 0 AND ")
					.append(" customid = ").append(custid)
					.append(" AND catalog4Exam = ").append(quesCatalog);
			String sql = buff.toString();
			int sure = dao.queryForInt(sql);

			buff.setLength(0);
			buff.append("SELECT COUNT(*) AS recken FROM ")
					.append(dao.TABLENAME).append(" ");
			buff.append(" WHERE 1= 1 AND ").append(" customid = ")
					.append(custid).append(" AND catalog4Exam = ")
					.append(quesCatalog);

			sql = buff.toString();
			int all = dao.queryForInt(sql);
			return sure * 100 / all;
		} catch (Exception e) {
			return 0;
		} finally {
			StringBufPool.returnObject(buff);
		}
	}
	// types end

}
