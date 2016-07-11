package com.learnhall.logic.chn.alipayapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;

/***
 * 异步通知服务器回调参数 支付宝是用 POST
 * 方式发送通知信息，因此该页面中获取参数的方式，如：request.Form("out_trade_no")、$_POST['out_trade_no']；
 * 程序执行完后必须打印输出“success”（不包含引号）。 如果商户反馈给支付宝的字符不是 success 这 7
 * 个字符，支付宝服务器会不断重发通知，直到超过 24 小时 22 分钟。 一般情况下，25 小时以内完成 8
 * 次通知（通知的间隔频率一般是：2m,10m,10m,1h,2h,6h,15h）；
 * 
 * 在支付宝的业务通知中，只有交易通知状态为 TRADE_SUCCESS 或 TRADE_FINISHED 时，支付宝才会认定为买家付款成功。
 * 
 * 在通知返回参数列表中，除去 sign、sign_type 两个参数外，凡是通知返回回来的参数皆是要签名的参数。
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AlipayEn4Notify implements Serializable {

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

	// 商户网站唯一订单号 64位 (可空)
	private String out_trade_no;

	// 商品名称 (可空)
	private String subject;

	// 支付类型 默认值为：1（商品购买）(可空)
	private String payment_type;

	// 支付宝交易号 该交易在支付宝系统中的交易 流水号。最长 64 位。(可空)
	private String trade_no;

	/*
	 * 交易状态(可空) TRADE_FINISHED（普通即时到账的交易成功状态）
	 * TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
	 */
	private String trade_status;

	// 交易创建时间 格式为 yyyy-MM-dd HH:mm:ss。(可空)
	private String gmt_create;

	// 交易付款时间 格式为 yyyy-MM-dd HH:mm:ss。(可空)
	private String gmt_payment;

	// 交易关闭时间 格式为 yyyy-MM-dd HH:mm:ss。(可空)
	private String gmt_close;

	// 退款状态 (可空)
	private String refund_status;

	// 退款时间 (可空)
	private String gmt_refund;

	// 卖家支付宝账号 可以是 Email或手机号码。(可空)
	private String seller_email;

	// 买家支付宝账号 可以是 Email或手机号码。(可空)
	private String buyer_email;

	// 卖家支付宝用户号 卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。(可空)
	private String seller_id;

	// 买家支付宝用户号 卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。(可空)
	private String buyer_id;

	// 商品单价 单位(元) (可空)
	private double price;

	// 交易金额 单位(元) (可空)
	private double total_fee;

	// 购买数量 (可空)
	private int quantity;

	// 商品描述 对一笔交易的具体描述信息。 (可空)
	private String body;

	// 折扣(可空)
	private double discount;

	// 是否调整总价 (可空)
	private String is_total_fee_adjust;

	// 是否使用红包买家 是否在交易过程中使用了红包。(可空)
	private String use_coupon;

	// 公用回传参数 透传参数 (可空)
	private String extra_common_param;

	// 支付渠道组合信息 该笔交易所使用的支付渠道。(可空)
	private String out_channel_type;

	// 支付金额组合信息 该笔交易通过使用各支付渠道所支付的金额。(可空)
	private String out_channel_amount;

	// 实际支付渠道 (可空)
	private String out_channel_inst;

	// 是否扫描支付 目前只有 qrpay 一种回传值。非扫码支付方式下，目前不会返回该参数。(可空)
	private String business_scene;

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

	public String getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}

	public String getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getGmt_close() {
		return gmt_close;
	}

	public void setGmt_close(String gmt_close) {
		this.gmt_close = gmt_close;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

	public String getGmt_refund() {
		return gmt_refund;
	}

	public void setGmt_refund(String gmt_refund) {
		this.gmt_refund = gmt_refund;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}

	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}

	public String getUse_coupon() {
		return use_coupon;
	}

	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getOut_channel_type() {
		return out_channel_type;
	}

	public void setOut_channel_type(String out_channel_type) {
		this.out_channel_type = out_channel_type;
	}

	public String getOut_channel_amount() {
		return out_channel_amount;
	}

	public void setOut_channel_amount(String out_channel_amount) {
		this.out_channel_amount = out_channel_amount;
	}

	public String getOut_channel_inst() {
		return out_channel_inst;
	}

	public void setOut_channel_inst(String out_channel_inst) {
		this.out_channel_inst = out_channel_inst;
	}

	public String getBusiness_scene() {
		return business_scene;
	}

	public void setBusiness_scene(String business_scene) {
		this.business_scene = business_scene;
	}

	public AlipayEn4Notify() {
		super();
	}

	public AlipayEn4Notify(String notify_time, String notify_type,
			String notify_id, String sign_type, String sign,
			String out_trade_no, String subject, String payment_type,
			String trade_no, String trade_status, String gmt_create,
			String gmt_payment, String gmt_close, String refund_status,
			String gmt_refund, String seller_email, String buyer_email,
			String seller_id, String buyer_id, double price, double total_fee,
			int quantity, String body, double discount,
			String is_total_fee_adjust, String use_coupon,
			String extra_common_param, String out_channel_type,
			String out_channel_amount, String out_channel_inst,
			String business_scene) {
		super();
		this.notify_time = notify_time;
		this.notify_type = notify_type;
		this.notify_id = notify_id;
		this.sign_type = sign_type;
		this.sign = sign;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = payment_type;
		this.trade_no = trade_no;
		this.trade_status = trade_status;
		this.gmt_create = gmt_create;
		this.gmt_payment = gmt_payment;
		this.gmt_close = gmt_close;
		this.refund_status = refund_status;
		this.gmt_refund = gmt_refund;
		this.seller_email = seller_email;
		this.buyer_email = buyer_email;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
		this.price = price;
		this.total_fee = total_fee;
		this.quantity = quantity;
		this.body = body;
		this.discount = discount;
		this.is_total_fee_adjust = is_total_fee_adjust;
		this.use_coupon = use_coupon;
		this.extra_common_param = extra_common_param;
		this.out_channel_type = out_channel_type;
		this.out_channel_amount = out_channel_amount;
		this.out_channel_inst = out_channel_inst;
		this.business_scene = business_scene;
	}

	public AlipayEn4Notify(String notify_time, String notify_type,
			String notify_id, String sign_type, String sign) {
		super();
		this.notify_time = notify_time;
		this.notify_type = notify_type;
		this.notify_id = notify_id;
		this.sign_type = sign_type;
		this.sign = sign;
	}

	public Map toBasicMap() {
		Map result = new HashMap();
		result.put("notify_time", notify_time);
		result.put("notify_type", notify_type);
		result.put("notify_id", notify_id);
		result.put("sign_type", sign_type);
		result.put("sign", sign);
		result.put("out_trade_no", out_trade_no);
		result.put("subject", subject);
		result.put("payment_type", payment_type);
		result.put("trade_no", trade_no);
		result.put("trade_status", trade_status);
		result.put("gmt_create", gmt_create);
		result.put("gmt_payment", gmt_payment);
		result.put("gmt_close", gmt_close);
		result.put("refund_status", refund_status);
		result.put("gmt_refund", gmt_refund);
		result.put("seller_email", seller_email);
		result.put("buyer_email", buyer_email);
		result.put("seller_id", seller_id);
		result.put("buyer_id", buyer_id);
		result.put("price", price);
		result.put("total_fee", total_fee);
		result.put("quantity", quantity);
		result.put("body", body);
		result.put("discount", discount);
		result.put("is_total_fee_adjust", is_total_fee_adjust);
		result.put("use_coupon", use_coupon);
		result.put("extra_common_param", extra_common_param);
		result.put("out_channel_type", out_channel_type);
		result.put("out_channel_amount", out_channel_amount);
		result.put("out_channel_inst", out_channel_inst);
		result.put("business_scene", business_scene);
		return result;
	}

	static public final AlipayEn4Notify toEntity(Map map) {
		if (map == null)
			return null;
		AlipayEn4Notify en = new AlipayEn4Notify();
		en.body = MapEx.getString(map, "body");
		en.business_scene = MapEx.getString(map, "business_scene");
		en.buyer_email = MapEx.getString(map, "buyer_email");
		en.buyer_id = MapEx.getString(map, "buyer_id");
		en.discount = MapEx.getDouble(map, "discount");
		en.extra_common_param = MapEx.getString(map, "extra_common_param");
		en.gmt_close = MapEx.getString(map, "gmt_close");
		en.gmt_create = MapEx.getString(map, "gmt_create");
		en.gmt_payment = MapEx.getString(map, "gmt_payment");
		en.gmt_refund = MapEx.getString(map, "gmt_refund");
		en.is_total_fee_adjust = MapEx.getString(map, "is_total_fee_adjust");
		en.notify_id = MapEx.getString(map, "notify_id");
		en.notify_time = MapEx.getString(map, "notify_time");
		en.notify_type = MapEx.getString(map, "notify_type");
		en.out_channel_amount = MapEx.getString(map, "out_channel_amount");
		en.out_channel_inst = MapEx.getString(map, "out_channel_inst");
		en.out_channel_type = MapEx.getString(map, "out_channel_type");
		en.out_trade_no = MapEx.getString(map, "out_trade_no");
		en.payment_type = MapEx.getString(map, "payment_type");
		en.price = MapEx.getDouble(map, "price");
		en.quantity = MapEx.getInt(map, "quantity");
		en.refund_status = MapEx.getString(map, "refund_status");
		en.seller_email = MapEx.getString(map, "seller_email");
		en.seller_id = MapEx.getString(map, "seller_id");
		en.sign = MapEx.getString(map, "sign");
		en.sign_type = MapEx.getString(map, "sign_type");
		en.subject = MapEx.getString(map, "subject");
		en.total_fee = MapEx.getDouble(map, "total_fee");
		en.trade_no = MapEx.getString(map, "trade_no");
		en.trade_status = MapEx.getString(map, "trade_status");
		en.use_coupon = MapEx.getString(map, "use_coupon");
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
