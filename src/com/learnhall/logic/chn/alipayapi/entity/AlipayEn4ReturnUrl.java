package com.learnhall.logic.chn.alipayapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;

/***
 * 同步跳转Return Url参数 该页面中获得参数的方式，需要使用 GET
 * 方式获取，如request.QueryString("out_trade_no")、$_GET['out_trade_no']。 返回 URL
 * 只有一分钟的有效期，超过一分钟该链接地址会失效，验证则会失败。 在支付宝的业务通知中，只有交易通知状态为 TRADE_SUCCESS 或
 * TRADE_FINISHED 时，支付宝才会认定为买家付款成功。
 * 
 * 在通知返回参数列表中，除去 sign、sign_type 两个参数外，凡是通知返回回来的参数皆是要签名的参数。
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AlipayEn4ReturnUrl implements Serializable {

	private static final long serialVersionUID = 1L;

	// 成功标识 表示接口调用是否成功，并不表明业务处理结果。(不可空)
	private String is_success;

	// 签名方式 DSA、RSA、MD5 三个值可选，必须大写。(不可空)
	private String sign_type;

	// 签名(不可空)
	private String sign;

	// 商户网站唯一订单号 原样返回(可空)
	private String out_trade_no;

	// 商品名称 原样返回(可空)
	private String subject;

	// 支付类型 原样返回(可空)
	private String payment_type;

	// 接口名称 标志调用哪个接口返回的链接。create_direct_pay_by_user(可空)
	private String exterface;

	// 支付宝交易号 该交易在支付宝系统中的交易 流水号。最长 64 位。(可空)
	private String trade_no;

	/*
	 * 交易状态(可空) TRADE_FINISHED（普通即时到账的交易成功状态）
	 * TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
	 */
	private String trade_status;

	// 通知校验ID 支付宝通知校验 ID，商户可以用这个流水号询问支付宝该条 通知的合法性。(可空)
	private String notify_id;

	// 通知时间 通知时间（支付宝时间）格式为 yyyy-MM-dd HH:mm:ss。(可空)
	private String notify_time;

	// 通知类型 (可空)
	private String notify_type;

	// 卖家支付宝账号 可以是 Email或手机号码。(可空)
	private String seller_email;

	// 买家支付宝账号 可以是 Email或手机号码。(可空)
	private String buyer_email;

	// 卖家支付宝用户号 卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。(可空)
	private String seller_id;

	// 买家支付宝用户号 卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。(可空)
	private String buyer_id;

	// 交易金额 单位(元) (可空)
	private double total_fee;

	// 商品描述 对一笔交易的具体描述信息。 (可空)
	private String body;

	// 公用回传参数 透传参数 (可空)
	private String extra_common_param;

	// 信用支付购票员的代理人 ID 本参数用于信用支付。 (可空)
	private String agent_user_id;

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getExterface() {
		return exterface;
	}

	public void setExterface(String exterface) {
		this.exterface = exterface;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

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

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getAgent_user_id() {
		return agent_user_id;
	}

	public void setAgent_user_id(String agent_user_id) {
		this.agent_user_id = agent_user_id;
	}

	public AlipayEn4ReturnUrl() {
		super();
	}

	public AlipayEn4ReturnUrl(String is_success, String sign_type, String sign,
			String out_trade_no, String subject, String payment_type,
			String exterface, String trade_no, String trade_status,
			String notify_id, String notify_time, String notify_type,
			String seller_email, String buyer_email, String seller_id,
			String buyer_id, double total_fee, String body,
			String extra_common_param, String agent_user_id) {
		super();
		this.is_success = is_success;
		this.sign_type = sign_type;
		this.sign = sign;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = payment_type;
		this.exterface = exterface;
		this.trade_no = trade_no;
		this.trade_status = trade_status;
		this.notify_id = notify_id;
		this.notify_time = notify_time;
		this.notify_type = notify_type;
		this.seller_email = seller_email;
		this.buyer_email = buyer_email;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
		this.total_fee = total_fee;
		this.body = body;
		this.extra_common_param = extra_common_param;
		this.agent_user_id = agent_user_id;
	}

	public AlipayEn4ReturnUrl(String is_success, String sign_type, String sign) {
		super();
		this.is_success = is_success;
		this.sign_type = sign_type;
		this.sign = sign;
	}

	public Map toBasicMap() {
		Map result = new HashMap();
		result.put("is_success", is_success);
		result.put("sign_type", sign_type);
		result.put("sign", sign);
		result.put("out_trade_no", out_trade_no);
		result.put("subject", subject);
		result.put("payment_type", payment_type);
		result.put("exterface", exterface);
		result.put("trade_no", trade_no);
		result.put("trade_status", trade_status);
		result.put("notify_id", notify_id);
		result.put("notify_time", notify_time);
		result.put("notify_type", notify_type);
		result.put("seller_email", seller_email);
		result.put("buyer_email", buyer_email);
		result.put("seller_id", seller_id);
		result.put("buyer_id", buyer_id);
		result.put("total_fee", total_fee);
		result.put("body", body);
		result.put("extra_common_param", extra_common_param);
		result.put("agent_user_id", agent_user_id);
		return result;
	}

	static public final AlipayEn4ReturnUrl toEntity(Map map) {
		if (map == null)
			return null;
		AlipayEn4ReturnUrl en = new AlipayEn4ReturnUrl();
		en.agent_user_id = MapEx.getString(map, "agent_user_id");
		en.body = MapEx.getString(map, "body");
		en.buyer_email = MapEx.getString(map, "buyer_email");
		en.buyer_id = MapEx.getString(map, "buyer_id");
		en.exterface = MapEx.getString(map, "exterface");
		en.extra_common_param = MapEx.getString(map, "extra_common_param");
		en.is_success = MapEx.getString(map, "is_success");
		en.notify_id = MapEx.getString(map, "notify_id");
		en.notify_time = MapEx.getString(map, "notify_time");
		en.notify_type = MapEx.getString(map, "notify_type");
		en.out_trade_no = MapEx.getString(map, "out_trade_no");
		en.payment_type = MapEx.getString(map, "payment_type");
		en.seller_email = MapEx.getString(map, "seller_email");
		en.seller_id = MapEx.getString(map, "seller_id");
		en.sign = MapEx.getString(map, "sign");
		en.sign_type = MapEx.getString(map, "sign_type");
		en.subject = MapEx.getString(map, "subject");
		en.total_fee = MapEx.getDouble(map, "total_fee");
		en.trade_no = MapEx.getString(map, "trade_no");
		en.trade_status = MapEx.getString(map, "trade_status");
		return en;
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
