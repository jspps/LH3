package com.learnhall.logic.chn.alipayapi.exchange.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.bowlong.util.MapEx;

/**
 * 批量付款到支付宝账户有密接口-(请求): 11个参数
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AliExchange4Notify implements Serializable {

	private static final long serialVersionUID = 1L;

	// 通知时间 通知时间（支付宝时间）格式为 yyyy-MM-dd HH:mm:ss。(不可空)
	private String notify_time;

	// 通知类型 (不可空)
	private String notify_type;

	// 通知校验ID (不可空)
	private String notify_id;

	// 签名方式 DSA、RSA、MD5 三个值可选，必须大写。(不可空)
	private String sign_type;

	// 签名(不可空)
	private String sign;

	// 转账批次号 (不可空)
	private String batch_no;

	// 付款账号ID (不可空)
	private String pay_user_id;

	// 付款账号姓名 (不可空)
	private String pay_user_name;

	// 付款账号 (不可空)
	private String pay_account_no;

	// 成功详情 (可空)
	private String success_details;

	// 失败详情 (可空)
	private String fail_details;

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getPay_user_id() {
		return pay_user_id;
	}

	public void setPay_user_id(String pay_user_id) {
		this.pay_user_id = pay_user_id;
	}

	public String getPay_user_name() {
		return pay_user_name;
	}

	public void setPay_user_name(String pay_user_name) {
		this.pay_user_name = pay_user_name;
	}

	public String getPay_account_no() {
		return pay_account_no;
	}

	public void setPay_account_no(String pay_account_no) {
		this.pay_account_no = pay_account_no;
	}

	public String getSuccess_details() {
		return success_details;
	}

	public void setSuccess_details(String success_details) {
		this.success_details = success_details;
	}

	public String getFail_details() {
		return fail_details;
	}

	public void setFail_details(String fail_details) {
		this.fail_details = fail_details;
	}

	public AliExchange4Notify() {
		super();
	}

	public Map toBasicMap() {
		Map result = new HashMap();
		result.put("notify_time", notify_time);
		result.put("notify_type", notify_type);
		result.put("notify_id", notify_id);
		result.put("sign_type", sign_type);
		result.put("sign", sign);
		result.put("batch_no", batch_no);
		result.put("pay_user_id", pay_user_id);
		result.put("pay_user_name", pay_user_name);
		result.put("pay_account_no", pay_account_no);
		result.put("success_details", success_details);
		result.put("fail_details", fail_details);
		return result;
	}

	static public final AliExchange4Notify toEntity(Map map) {
		if (map == null)
			return null;
		AliExchange4Notify en = new AliExchange4Notify();
		en.notify_id = MapEx.getString(map, "notify_id");
		en.notify_time = MapEx.getString(map, "notify_time");
		en.notify_type = MapEx.getString(map, "notify_type");
		en.sign = MapEx.getString(map, "sign");
		en.sign_type = MapEx.getString(map, "sign_type");
		en.batch_no = MapEx.getString(map, "batch_no");
		en.pay_account_no = MapEx.getString(map, "pay_account_no");
		en.pay_user_id = MapEx.getString(map, "pay_user_id");
		en.pay_user_name = MapEx.getString(map, "pay_user_name");
		en.success_details = MapEx.getString(map, "success_details");
		en.fail_details = MapEx.getString(map, "fail_details");
		return en;
	}
}
