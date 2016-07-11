package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.*;

import com.bowlong.lang.StrEx;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - product
@SuppressWarnings({ "static-access" })
public class ProductEntity extends ProductInternal {
	static Log log = LogFactory.getLog(ProductEntity.class);

	public static final ProductEntity my = new ProductEntity();

	static ProductDAO ProductDAO = null;

	public static ProductDAO ProductDAO() {
		if (ProductDAO == null)
			ProductDAO = new ProductDAO(
					com.learnhall.content.AppContext.dsData());
		return ProductDAO;
	}

	public static void insertMmTry(final Product product) {
		ProductDAO DAO = ProductDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(product, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// public void loadLinked(final Product product) {
	// if(product == null) return;
	// List<Kind> kinds = product.getKindsFkProductid(); // kind
	// List<Kindmin> kindmins = product.getKindminsFkProductid(); // kindmin
	// }

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		ProductDAO dao = ProductDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Product> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		ProductDAO dao = ProductDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Product.class);
	}
	// types end

}
