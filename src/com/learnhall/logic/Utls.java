package com.learnhall.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bowlong.bio2.B2Helper;
import com.bowlong.lang.ByteEx;
import com.bowlong.tool.TkitJsp;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Recordfee4customer;
import com.learnhall.db.bean.Recordkbi4customer;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.logic.model.PageKinds;

/*** 工程工具类 **/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Utls extends TkitJsp {

	// ================ 常量 ================
	static public final String KeyCodeAdminLg = "Code4AdminLogin";

	static public final int Status_Success = 1;
	static public final int Status_Erro = -1;
	static public final int Status_notLogin = -99;
	/*** 空的界面提示内容的东西 **/
	static public String Msg4Empty = "";

	static public final Map tipMap(Map map, int result, String msg, Object obj) {
		if (MapEx.isEmpty(map))
			map = new HashMap();
		map.put("msg", msg);
		map.put("status", result);
		map.put("data", obj);
		return map;
	}

	static public final byte[] map2Bytes(Map map) {
		if (MapEx.isEmpty(map))
			return new byte[0];
		try {
			return B2Helper.toBytes(map);
		} catch (Exception e) {
			return new byte[0];
		}
	}

	static public final Map bytes2Map(byte[] bytes) {
		if (ByteEx.isEmpty(bytes))
			return new HashMap();
		try {
			return B2Helper.toMap(bytes);
		} catch (Exception e) {
			return new HashMap();
		}
	}

	static public final byte[] list2Bytes(List list) {
		if (ListEx.isEmpty(list))
			return new byte[0];
		try {
			return B2Helper.toBytes(list);
		} catch (Exception e) {
			return new byte[0];
		}
	}

	static public final List bytes2List(byte[] bytes) {
		if (ByteEx.isEmpty(bytes))
			return new ArrayList();
		try {
			return B2Helper.toList(bytes);
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	/*** 取得用户对象 **/
	static public Customer getCustomer(HttpSession session) {
		return (Customer) session.getAttribute(SessionKeys.Customer);
	}

	/*** 保存用户对象 **/
	static public void saveCustomer(HttpSession session, Customer customer) {
		session.setAttribute(SessionKeys.Customer, customer);
		session.setMaxInactiveInterval(3600);// 单位：秒
	}

	/*** 取得用户对象ID **/
	static public int getCustomerId(HttpSession session) {
		Customer customer = getCustomer(session);
		if (customer == null)
			return 0;
		return customer.getId();
	}

	/*** 重新设置Customer对象 **/
	static public void resetCustomer(HttpSession session, Customer old) {
		if (old == null) {
			return;
		}
		int id = old.getId();
		Customer customer = CustomerEntity.getByKey(id);
		saveCustomer(session, customer);
	}

	/*** 记录消耗人民币 type[1收入,2支出] **/
	static public void recordRmb(Customer customer, int type, double val,
			String cont, int kindid) {
		if (customer == null)
			return;
		int custid = customer.getId();
		String custname = customer.getName();
		Date createtime = DateEx.nowDate();
		Recordfee4customer fee = Recordfee4customer.newRecordfee4customer(0,
				type, custid, custname, val, cont, kindid, createtime);
		fee = fee.insert();
	}

	/*** 记录消耗人民币 type[1收入,2支出] **/
	static public void recordKbi(Customer customer, int type, double val,
			String cont) {
		if (customer == null)
			return;
		int custid = customer.getId();
		String custname = customer.getName();
		Date createtime = DateEx.nowDate();
		Recordkbi4customer fee = Recordkbi4customer.newRecordkbi4customer(0,
				type, custid, custname, val, cont, createtime);
		fee = fee.insert();
	}

	/*** 取得是否为体验测试 **/
	static public boolean getIsTestExam(HttpSession session) {
		Object obj = session.getAttribute(SessionKeys.IsTestExam);
		if (obj == null)
			return false;
		return Boolean.valueOf(obj.toString());
	}

	/*** 设置为体验测试 **/
	static public void setIsTestExam(HttpSession session, boolean isTest) {
		session.setAttribute(SessionKeys.IsTestExam, isTest);
	}

	/*** 设置前一个访问的地址 **/
	static public void setUrlPre(HttpSession session, String urlPre) {
		session.setAttribute(SessionKeys.UrlPre, urlPre);
	}

	/*** 取得PageKinds套餐 **/
	static public PageKinds getKinds(HttpSession session) {
		return (PageKinds) session.getAttribute(SessionKeys.PageSieve);
	}
}
