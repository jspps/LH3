package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.*;

import com.bowlong.util.ListEx;
import com.learnhall.db.bean.*;
import com.learnhall.db.dao.*;
import com.learnhall.db.internal.*;
//import com.learnhall.content.AppContext;

//learnhall3_design - shoppingcart
@SuppressWarnings({ "static-access" })
public class ShoppingcartEntity extends ShoppingcartInternal {
	static Log log = LogFactory.getLog(ShoppingcartEntity.class);

	public static final ShoppingcartEntity my = new ShoppingcartEntity();

	static ShoppingcartDAO ShoppingcartDAO = null;

	public static ShoppingcartDAO ShoppingcartDAO() {
		if (ShoppingcartDAO == null)
			ShoppingcartDAO = new ShoppingcartDAO(
					com.learnhall.content.AppContext.dsData());
		return ShoppingcartDAO;
	}

	public static void insertMmTry(final Shoppingcart shoppingcart) {
		ShoppingcartDAO DAO = ShoppingcartDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(shoppingcart, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/*** 删除购物车列表 **/
	static public void delShopCartBy(int custid, int kindid) {
		if (custid <= 0 && kindid <= 0) {
			return;
		}

		ShoppingcartDAO dao = ShoppingcartDAO();
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM ").append(dao.TABLENAME);
		buffer.append(" WHERE 1 = 1 AND ");
		if (custid > 0) {
			buffer.append(" customerid = ").append(custid);
		}
		if (kindid > 0) {
			buffer.append(" AND kindid = ").append(kindid);
		}
		try {
			dao.update(buffer.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*** 删除购物车列表 **/
	static public void delShopCartBy(int custid, List<Integer> kindids) {
		if (custid <= 0 || ListEx.isEmpty(kindids)) {
			return;
		}

		ShoppingcartDAO dao = ShoppingcartDAO();
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM ").append(dao.TABLENAME);
		buffer.append(" WHERE 1 = 1 AND ");
		buffer.append(" customerid = ").append(custid);
		buffer.append(" AND kindid IN(");
		int lens = kindids.size();
		for (int i = 0; i < lens; i++) {
			buffer.append(kindids.get(i));
			if (i < lens - 1) {
				buffer.append(",");
			}
		}
		buffer.append(")");
		try {
			String sql = buffer.toString();
			System.out.println(sql);
			dao.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// types end

}
