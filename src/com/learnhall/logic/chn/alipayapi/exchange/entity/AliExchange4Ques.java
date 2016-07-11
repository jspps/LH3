package com.learnhall.logic.chn.alipayapi.exchange.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量付款到支付宝账户有密接口-(服务器异步通知): 15个参数 exchangermb
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AliExchange4Ques implements Serializable {

	private static final long serialVersionUID = 1L;

	// 接口名称 [batch_trans_notify] (不可空)
	private String service;

	// 合作者身份 ID 签约的支付宝账号对应的支付宝唯一用户号。以 2088 开头的 16 位纯数字组成。 (不可空)
	private String partner;

	// 商户网站使用的编码格式，如utf-8、gbk、gb2312 等。(不可空)
	private String _input_charset;

	// 签名方式 DSA、RSA、MD5 三个值可选，必须大写。(不可空)
	private String sign_type;

	// 签名(不可空)
	private String sign;

	// 服务器异步通知页面路径 支付宝服务器主动通知商户网站里指定的页面 http路径。 (可空) ,充值回调本地服务数据处理
	private String notify_url;

	// 付款帐号名 付款方的支付宝账户名 (不可空)
	private String account_name;

	/**
	 * 付款详细数据 (不可空) <br/>
	 * 付款的详细数据，最多支持1000 笔。<br/>
	 * 格式为：流水号 1^收款方账号1^收款账号姓名1^付款金额1^备注说明1|流水号 2^收款方账号 2^收款账号姓名2^付款金额 2^备注说明 2。<br/>
	 * 流水号：就是批次号batch_no 收款帐号姓名：实名认证的支付宝帐号名称<br/>
	 * 每条记录以“|”间隔。
	 */
	private String detail_data;

	/*
	 * 批量付款批次号 (不可空) 11～32 位的数字或字母或数字与字母的组合，且区分大小写。 yyyyMMdd+随意(长度:11~32位)
	 */
	private String batch_no;

	// 付款总笔数 最多1000笔 ,值:[1-1000](不可空)
	private String batch_num;

	// 付款总金额 精确到分[0.00] (不可空)
	private String batch_fee;

	// 付款账号 付款方的支付宝账号 (不可空)
	private String email;

	// 支付日期 yyyyMMdd (可空)
	private String pay_date;

	// 付款账号别名 同email 参数，可以使用该参数名代替 email 输入参数；优先级大于 email。 (可空)
	private String buyer_account_name;

	// 业务扩展参数 (可空)
	private String extend_param;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
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

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getDetail_data() {
		return detail_data;
	}

	public void setDetail_data(String detail_data) {
		this.detail_data = detail_data;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getBatch_num() {
		return batch_num;
	}

	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}

	public String getBatch_fee() {
		return batch_fee;
	}

	public void setBatch_fee(String batch_fee) {
		this.batch_fee = batch_fee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public String getBuyer_account_name() {
		return buyer_account_name;
	}

	public void setBuyer_account_name(String buyer_account_name) {
		this.buyer_account_name = buyer_account_name;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public AliExchange4Ques() {
		super();
	}

	public AliExchange4Ques(String service, String partner,
			String _input_charset, String sign_type, String sign,
			String notify_url, String account_name, String detail_data,
			String batch_no, String batch_num, String batch_fee, String email,
			String pay_date, String buyer_account_name, String extend_param) {
		super();
		this.service = service;
		this.partner = partner;
		this._input_charset = _input_charset;
		this.sign_type = sign_type;
		this.sign = sign;
		this.notify_url = notify_url;
		this.account_name = account_name;
		this.detail_data = detail_data;
		this.batch_no = batch_no;
		this.batch_num = batch_num;
		this.batch_fee = batch_fee;
		this.email = email;
		this.pay_date = pay_date;
		this.buyer_account_name = buyer_account_name;
		this.extend_param = extend_param;
	}

	public AliExchange4Ques(String partner, String sign, String notify_url,
			String account_name, String detail_data, String batch_no,
			String batch_num, String batch_fee, String email, String pay_date) {
		super();
		this.service = "batch_trans_notify";
		this.partner = partner;
		this._input_charset = "utf-8";
		this.sign_type = "MD5";
		this.sign = sign;
		this.notify_url = notify_url;
		this.account_name = account_name;
		this.detail_data = detail_data;
		this.batch_no = batch_no;
		this.batch_num = batch_num;
		this.batch_fee = batch_fee;
		this.email = email;
		this.pay_date = pay_date;
	}

	public AliExchange4Ques(String partner, String notify_url,
			String account_name, String detail_data, String batch_no,
			String batch_num, String batch_fee, String email, String pay_date) {
		super();
		this.service = "batch_trans_notify";
		this.partner = partner;
		this._input_charset = "utf-8";
		this.sign_type = "MD5";
		this.notify_url = notify_url;
		this.account_name = account_name;
		this.detail_data = detail_data;
		this.batch_no = batch_no;
		this.batch_num = batch_num;
		this.batch_fee = batch_fee;
		this.email = email;
		this.pay_date = pay_date;
	}

	public Map toBasicMap() {
		Map result = new HashMap();
		result.put("service", service);
		result.put("partner", partner);
		result.put("_input_charset", _input_charset);
		result.put("sign_type", sign_type);
		result.put("sign", sign);
		result.put("notify_url", notify_url);
		result.put("account_name", account_name);
		result.put("detail_data", detail_data);
		result.put("batch_no", batch_no);
		result.put("batch_num", batch_num);
		result.put("batch_fee", batch_fee);
		result.put("email", email);
		result.put("pay_date", pay_date);
		result.put("buyer_account_name", buyer_account_name);
		result.put("extend_param", extend_param);
		return result;
	}

	public Map toMapClear() {
		Map map = toBasicMap();
		List listKeys = new ArrayList();
		listKeys.addAll(map.keySet());
		int lens = listKeys.size();
		for (int i = 0; i < lens; i++) {
			Object key = listKeys.get(i);
			Object val = map.get(key);
			if (val == null) {
				map.remove(key);
				continue;
			}
			String valStr = val.toString().trim();
			if (valStr.isEmpty()) {
				map.remove(key);
			} else {
				if (val instanceof Double) {
					double v = (double) val;
					if (v <= 0) {
						map.remove(key);
					}
				} else if (val instanceof Integer) {
					int v = (int) val;
					if (v <= 0) {
						map.remove(key);
					}
				}
			}
		}
		return map;
	}

	public Map<String, String> toMapKV() {
		Map map = toMapClear();
		Map<String, String> result = new HashMap<String, String>();
		for (Object key : map.keySet()) {
			Object val = map.get(key);
			result.put(key.toString(), val.toString());
		}
		return result;
	}
}
