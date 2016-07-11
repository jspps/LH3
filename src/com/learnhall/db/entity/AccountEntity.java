package com.learnhall.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.learnhall.db.bean.Account;
import com.learnhall.db.dao.AccountDAO;
import com.learnhall.db.internal.AccountInternal;

//learnhall3_design - account
@SuppressWarnings({ "static-access" })
public class AccountEntity extends AccountInternal {
	static Log log = LogFactory.getLog(AccountEntity.class);

	public static final AccountEntity my = new AccountEntity();

	static AccountDAO AccountDAO = null;

	public static AccountDAO AccountDAO() {
		if (AccountDAO == null)
			AccountDAO = new AccountDAO(
					com.learnhall.content.AppContext.dsData());
		return AccountDAO;
	}

	public static void insertMmTry(final Account account) {
		AccountDAO DAO = AccountDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(account, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// public void loadLinked(final Account account) {
	// if(account == null) return;
	// Aduser adusers = account.getAduserFkAccountid(); // aduser
	// Agent agents = account.getAgentFkAccountid(); // agent
	// Customer customers = account.getCustomerFkAccountid(); // customer
	// Learnhub learnhubs = account.getLearnhubFkAccountid(); // learnhub
	// List<Msg> msgs = account.getMsgsFkAccountid(); // msg
	// List<Tradedetail> tradedetails = account.getTradedetailsFkAccountid(); //
	// tradedetail
	// }

	// types begin
	/*** 取得帐号 **/
	static public Account getAccount(String lgid) {
		Account v = getByLgid(lgid);
		if (v == null) {
			v = getByPhone(lgid);
		}

		if (v == null) {
			v = getByEmail(lgid);
		}
		return v;
	}
	// types end

}
