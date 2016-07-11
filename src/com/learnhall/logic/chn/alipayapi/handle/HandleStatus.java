package com.learnhall.logic.chn.alipayapi.handle;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.learnhall.db.bean.Orders;

public class HandleStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	// sign签名验证的结果
	public boolean verify_result;
	// 是否可以处理(验证过后的后续的处理)
	public boolean isCanHandle;
	// 是否可以做日志记录(数据库的)
	public boolean isCanRecord4Sql;
	// 是否可以做日志记录(log的日志)
	public boolean isCanRecord4Log;
	// 当前的订单
	public Orders curOrder;
	// 订单产生的套餐ID列表
	public List<Integer> kindids;
	// 付款人支付宝帐号
	public String buyemail;

	public int getOrderid() {
		if (curOrder != null) {
			return curOrder.getId();
		}
		return 0;
	}

	public String getOrderidStr() {
		int id = getOrderid();
		return String.valueOf(id);
	}

	public int getMarkerid() {
		if (curOrder != null) {
			return curOrder.getMakerid();
		}
		return 0;
	}

	public HandleStatus() {
		super();
	}

	@Override
	public String toString() {
		String v = "HandleStatus [verify_result=" + verify_result
				+ ", isCanHandle=" + isCanHandle + ", isCanRecord4Sql="
				+ isCanRecord4Sql + ", isCanRecord4Log=" + isCanRecord4Log
				+ ",buyemail=" + buyemail;

		if (curOrder == null) {
			v += ", curOrder= null";
		} else {
			v += ", curOrder=" + curOrder.toString();
		}
		if (kindids == null) {
			v += ", kindids= null";
		} else {
			v += ", kindids = " + JSON.toJSONString(kindids);
		}
		v += "]";
		return v;
	}

}
