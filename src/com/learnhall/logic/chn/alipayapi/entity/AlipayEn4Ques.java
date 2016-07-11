package com.learnhall.logic.chn.alipayapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.lang.StrEx;

/***
 * 即时到帐<br/>
 * 发送请求的参数 此接口只支持 https 请求； <br/>
 * 参数body（商品描述）、subject（商品名称）、extra_common_param（公用回传参数）不能包含特殊字符（如：#、%、&、+）、敏感词汇
 * 
 * 在请求参数列表中，除去 sign、sign_type 两个参数外，其他需要使用到的参数皆是要签名的参数。<br/>
 * （个别接口中参数 sign_type 也需要参与签名。）
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AlipayEn4Ques implements Serializable {

	private static final long serialVersionUID = 1L;

	// 接口名称 [create_direct_pay_by_user ] (不可空)
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

	// 页面跳转同步通知页面路径 支付宝处理完请求后，当前页面自 动跳转到商户网站里指定页面的http路径。(跳转路径)(可空)
	private String return_url;

	// 请求出错时的通知页面路径 (可空)
	private String error_notify_url;

	// 商户网站唯一订单号 64位 (不可空)
	private String out_trade_no;

	// 商品名称 商品的标题/交易标题/订单标题/订单关键字等。该参数最长为 128 个汉字 (不可空)
	private String subject;

	// 支付类型 默认值为：1（商品购买）(不可空)
	private String payment_type;

	// 交易金额 单位(元) (不可空)
	private double total_fee;

	// 卖家支付宝用户号 卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。(不可空)
	private String seller_id;

	// 买家支付宝用户号 卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。(可空)
	private String buyer_id;

	// 卖家支付宝用户号 格式为邮箱或手机号。(可空)
	private String seller_email;

	// 买家支付宝用户号 格式为邮箱或手机号。(可空)
	private String buyer_email;

	// 卖家别名支付宝账号 (可空)
	private String seller_account_name;

	// 买家别名支付宝账号 (可空)
	private String buyer_account_name;

	// 商品单价 单位(元) (可空)
	// 规则：price、quantity 能代替 total_fee。即存在 total_fee，就不能存在 price 和 quantity；
	// 存在price、quantity，就不能存在total_fee。
	private double price;

	// 购买数量 (可空)
	private int quantity;

	// 商品描述 对一笔交易的具体描述信息。 (可空)
	private String body;

	// 商品展示网址 收银台页面上，商品展示的超链接。(可空)
	private String show_url;

	// 默认支付方式 取值范围： creditPay（信用支付）, directPay（余额支付）如果不设置，默认识别为余额支付。(可空)
	private String paymethod;

	// 支付渠道 用于控制收银台支付渠道显示,可支持多种支付渠道显示，以“^”分隔。(可空)
	private String enable_paymethod;

	// 网银支付时是否做CTU 校验 Y,N (可空)
	private String need_ctu_check;

	// 提成类型 目前只支持一种类型：10（卖家给第三方提成）。当传递了参数 royalty_parameters时，提成类型参数不能为空。(可空)
	private String royalty_type;

	/*
	 * 分润账号集 参见“4.4 royalty_parameters参数说明”。(可空) 1. 参数格式说明 royalty_parameters
	 * 参数为分润账号集，支持以下格式（用户 UID 是一串以字母uid 开头，后面跟着以 2088 开头的 16 位数字的字符串）： (1) 平级分润
	 * 收款方Email1^金额1^备注1|收款方 Email2^金额2^备注2
	 * 付款方Email^收款方Email1^金额1^备注1|付款方Email^收款方Email2^金额2^备注2
	 * 收款方UID1^金额1^备注1|收款方UID2^金额2^备注2
	 * 付款方UID^收款方UID1^金额1^备注1|付款方UID^收款方UID2^金额2^备注2 (2) 多级分润
	 * 收款方Email1^金额1^备注1|付款方Email1^收款方Email2^金额2^备注2|付款方Email2^收款方Email3^金额3^备注3
	 * 收款方UID1^金额1^备注1|付款方UID1^收款方UID2^金额2^备注2|付款方UID2^收款方UID3^金额3^备注3
	 * (3)平级多级混合分润
	 * 收款方Email1^金额1^备注1|付款方Email1^收款方Email2^金额2^备注2|收款方Email3^金额3^备注3
	 * 收款方UID1^金额1^备注1|付款方UID1^收款方UID2^金额2^备注2|收款方UID3^金额3^备注3
	 * 
	 * 注意： 参数 royalty_parameters（分润账号集）如果有多条数据，用“|”隔开，最多不能超过 10 条；
	 * 某条分润数据没有付款方，则默认卖家是付款方； 各分润账号必须使用正确的支付宝账号，能正常收款和付款；
	 * 对于平级分润，付款方Email必须与卖家Email 一致；
	 * 同一笔交易的分润处理，必须遵循原则：分润者要先收到钱才能再付给其他被分润者，收到的钱一定要大于等于付出的钱，即先入后出，入要大于等于出；
	 * 在各条分润的“备注”中，不能出现“^”和“|”影响 royalty_parameters 格式的特殊字符；“备注”字段长度不能超过 80
	 * 个字节，即 40 个汉字；分润信息中，“备注”项可以不填，但“^”不可以省略，如：收款方支付宝账号1^金额 1^。
	 * 
	 * 参数样例 maomao1@yahoo.cn^1.00^分你的|maomao2@yahoo.cn^2.00^你也有
	 * uid2088123456789012^1.00^分你的|uid2088123456789013^2.00^你也有
	 */
	private String royalty_parameters;

	// 防钓鱼时间戳 通过时间戳查询接口获取的加密支付宝系统时间戳。如果已申请开通防钓鱼时间戳验证，则此字段必填。(可空)
	private String anti_phishing_key;

	// 客户端 IP 用户在创建交易时，该用户当前所使用机器的 IP。如果商户申请后台开通防钓鱼 IP地址检查选项，此字段必填，校验用。(可空)
	private String exter_invoke_ip;

	// 公用回传参数 透传参数 (可空)
	private String extra_common_param;

	// 公用业务扩展参数 (可空)
	private String extend_param;

	// 超时时间 设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。取值范围：1m～15d (可空)
	// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在 0 点关闭）。该参数数值不接受小数点，如1.5h，可转换为 90m。
	private String it_b_pay;

	// 自动登录标识 Y,N值。该功能需要联系支付宝配置 (可空)
	private String default_login;

	// 商户申请的产品类型 用于针对不同的产品，采取不同的 计费策略。
	// 如果开通了航旅垂直搜索平台产品，请填写CHANNEL_FAST_PAY；如果没有，则为空。 (可空)
	private String product_type;

	// 快捷登录授权令牌 (可空)
	private String token;

	// 商户回传业务参数 如果是 etao 商户则填写；如果不是，则为空。 (可空)
	private String item_orders_info;

	// 商户买家签约号 用于唯一标识商户买家。如果本参数不为空，则sign_name_ext 不能为空。(可空)
	private String sign_id_ext;

	// 商户买家签约名 商户买家唯一标识对应的名字。(可空)
	private String sign_name_ext;

	/*
	 * 扫码支付方式 (可空) 扫码支付的方式，支持前置模式和跳转模式。 前置模式是将二维码前置到商户的订单确认页的模式。需要商户在自己的页面中以
	 * iframe 方式请求支付宝页面。具体分为以下 3 种： 0：订单码-简约前置模式，对应 iframe 宽度不能小于600px，高度不能小于
	 * 300px； 1：订单码-前置模式，对应iframe 宽度不能小于 300px，高度不能小于 600px； 3：订单码-迷你前置模式，对应
	 * iframe 宽度不能小于 75px，高度不能小于 75px。 跳转模式下，用户的扫码界面是由支付宝生成的，不在商户的域名下。
	 * 2：订单码-跳转模式
	 */
	private String qr_pay_mode;

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

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getError_notify_url() {
		return error_notify_url;
	}

	public void setError_notify_url(String error_notify_url) {
		this.error_notify_url = error_notify_url;
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

	public double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
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

	public String getSeller_account_name() {
		return seller_account_name;
	}

	public void setSeller_account_name(String seller_account_name) {
		this.seller_account_name = seller_account_name;
	}

	public String getBuyer_account_name() {
		return buyer_account_name;
	}

	public void setBuyer_account_name(String buyer_account_name) {
		this.buyer_account_name = buyer_account_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getEnable_paymethod() {
		return enable_paymethod;
	}

	public void setEnable_paymethod(String enable_paymethod) {
		this.enable_paymethod = enable_paymethod;
	}

	public String getNeed_ctu_check() {
		return need_ctu_check;
	}

	public void setNeed_ctu_check(String need_ctu_check) {
		this.need_ctu_check = need_ctu_check;
	}

	public String getRoyalty_type() {
		return royalty_type;
	}

	public void setRoyalty_type(String royalty_type) {
		this.royalty_type = royalty_type;
	}

	public String getRoyalty_parameters() {
		return royalty_parameters;
	}

	public void setRoyalty_parameters(String royalty_parameters) {
		this.royalty_parameters = royalty_parameters;
	}

	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}

	public void setAnti_phishing_key(String anti_phishing_key) {
		this.anti_phishing_key = anti_phishing_key;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getDefault_login() {
		return default_login;
	}

	public void setDefault_login(String default_login) {
		this.default_login = default_login;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getItem_orders_info() {
		return item_orders_info;
	}

	public void setItem_orders_info(String item_orders_info) {
		this.item_orders_info = item_orders_info;
	}

	public String getSign_id_ext() {
		return sign_id_ext;
	}

	public void setSign_id_ext(String sign_id_ext) {
		this.sign_id_ext = sign_id_ext;
	}

	public String getSign_name_ext() {
		return sign_name_ext;
	}

	public void setSign_name_ext(String sign_name_ext) {
		this.sign_name_ext = sign_name_ext;
	}

	public String getQr_pay_mode() {
		return qr_pay_mode;
	}

	public void setQr_pay_mode(String qr_pay_mode) {
		this.qr_pay_mode = qr_pay_mode;
	}

	public AlipayEn4Ques() {
		super();
	}

	/*** 默认全构造函数 **/
	public AlipayEn4Ques(String service, String partner, String _input_charset,
			String sign_type, String sign, String notify_url,
			String return_url, String error_notify_url, String out_trade_no,
			String subject, String payment_type, double total_fee,
			String seller_id, String buyer_id, String seller_email,
			String buyer_email, String seller_account_name,
			String buyer_account_name, double price, int quantity, String body,
			String show_url, String paymethod, String enable_paymethod,
			String need_ctu_check, String royalty_type,
			String royalty_parameters, String anti_phishing_key,
			String exter_invoke_ip, String extra_common_param,
			String extend_param, String it_b_pay, String default_login,
			String product_type, String token, String item_orders_info,
			String sign_id_ext, String sign_name_ext, String qr_pay_mode) {
		super();
		this.service = service;
		this.partner = partner;
		this._input_charset = _input_charset;
		this.sign_type = sign_type;
		this.sign = sign;
		this.notify_url = notify_url;
		this.return_url = return_url;
		this.error_notify_url = error_notify_url;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = payment_type;
		this.total_fee = total_fee;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
		this.seller_email = seller_email;
		this.buyer_email = buyer_email;
		this.seller_account_name = seller_account_name;
		this.buyer_account_name = buyer_account_name;
		this.price = price;
		this.quantity = quantity;
		this.body = body;
		this.show_url = show_url;
		this.paymethod = paymethod;
		this.enable_paymethod = enable_paymethod;
		this.need_ctu_check = need_ctu_check;
		this.royalty_type = royalty_type;
		this.royalty_parameters = royalty_parameters;
		this.anti_phishing_key = anti_phishing_key;
		this.exter_invoke_ip = exter_invoke_ip;
		this.extra_common_param = extra_common_param;
		this.extend_param = extend_param;
		this.it_b_pay = it_b_pay;
		this.default_login = default_login;
		this.product_type = product_type;
		this.token = token;
		this.item_orders_info = item_orders_info;
		this.sign_id_ext = sign_id_ext;
		this.sign_name_ext = sign_name_ext;
		this.qr_pay_mode = qr_pay_mode;
	}

	/*** 默认不为空的构造函数 **/
	public AlipayEn4Ques(String service, String partner, String _input_charset,
			String sign_type, String sign, String notify_url,
			String out_trade_no, String subject, String payment_type,
			double total_fee, String seller_id) {
		super();
		this.service = service;
		this.partner = partner;
		this._input_charset = _input_charset;
		this.sign_type = sign_type;
		this.sign = sign;
		this.notify_url = notify_url;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = payment_type;
		this.total_fee = total_fee;
		this.seller_id = seller_id;
		this.qr_pay_mode = "2";
	}

	/*** 初始化帐号 1 **/
	public AlipayEn4Ques(String notify_url, String out_trade_no,
			String subject, double total_fee, String royalty_parameters) {
		super();
		this.service = "create_direct_pay_by_user";
		this._input_charset = "utf-8";
		this.sign_type = "MD5";
		this.notify_url = notify_url;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = "1";
		this.total_fee = total_fee;
		if (!StrEx.isEmptyTrim(royalty_parameters)) {
			this.royalty_type = "10";
			this.royalty_parameters = royalty_parameters;
		}
		this.qr_pay_mode = "2";
	}

	/*** 初始化帐号 2 **/
	public AlipayEn4Ques(String partner, String sign, String notify_url,
			String out_trade_no, String subject, double total_fee,
			String seller_id, String royalty_parameters) {
		super();
		this.service = "create_direct_pay_by_user";
		this.partner = partner;
		this._input_charset = "utf-8";
		this.sign_type = "MD5";
		this.sign = sign;
		this.notify_url = notify_url;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = "1";
		this.total_fee = total_fee;
		this.seller_id = seller_id;
		if (!StrEx.isEmptyTrim(royalty_parameters)) {
			this.royalty_type = "10";
			this.royalty_parameters = royalty_parameters;
		}
		this.qr_pay_mode = "2";
	}

	public Map toBasicMap() {
		Map result = new HashMap();
		result.put("service", service);
		result.put("partner", partner);
		result.put("_input_charset", _input_charset);
		result.put("sign_type", sign_type);
		result.put("sign", sign);
		result.put("notify_url", notify_url);
		result.put("return_url", return_url);
		result.put("error_notify_url", error_notify_url);
		result.put("out_trade_no", out_trade_no);
		result.put("subject", subject);
		result.put("payment_type", payment_type);
		result.put("total_fee", total_fee);
		result.put("seller_id", seller_id);
		result.put("buyer_id", buyer_id);
		result.put("seller_email", seller_email);
		result.put("buyer_email", buyer_email);
		result.put("seller_account_name", seller_account_name);
		result.put("buyer_account_name", buyer_account_name);
		result.put("price", price);
		result.put("quantity", quantity);
		result.put("body", body);
		result.put("show_url", show_url);
		result.put("paymethod", paymethod);
		result.put("enable_paymethod", enable_paymethod);
		result.put("need_ctu_check", need_ctu_check);
		result.put("royalty_type", royalty_type);
		result.put("royalty_parameters", royalty_parameters);
		result.put("anti_phishing_key", anti_phishing_key);
		result.put("exter_invoke_ip", exter_invoke_ip);
		result.put("extra_common_param", extra_common_param);
		result.put("extend_param", extend_param);
		result.put("it_b_pay", it_b_pay);
		result.put("default_login", default_login);
		result.put("product_type", product_type);
		result.put("token", token);
		result.put("item_orders_info", item_orders_info);
		result.put("sign_id_ext", sign_id_ext);
		result.put("sign_name_ext", sign_name_ext);
		result.put("qr_pay_mode", qr_pay_mode);
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
