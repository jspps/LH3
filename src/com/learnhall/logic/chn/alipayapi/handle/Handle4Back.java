package com.learnhall.logic.chn.alipayapi.handle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.bowlong.lang.PStr;
import com.bowlong.net.http.uri.HttpUriPostEx;
import com.bowlong.text.EncodingEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.learnhall.db.bean.Boughtkinds;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Orders;
import com.learnhall.db.entity.BoughtkindsEntity;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.OrdersEntity;
import com.learnhall.db.entity.ShoppingcartEntity;
import com.learnhall.logic.Utls;

/***
 * 充值回调验证，以及验证过后的逻辑处理 <br/>
 * order.type[0买套餐,1学测(1元),2代测(1元),3买考币,4买问答,5学生支付宝验证]
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes" })
public class Handle4Back implements Serializable {

	private static final long serialVersionUID = 1L;

	static final Log logger = Utls.getLog(Handle4Back.class);

	static public final boolean handle(HttpServletRequest request) {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request, "", "");
		return handle(mapPars);
	}

	static public final boolean handle(Map<String, String> mapPars) {
		HandleStatus handleStatus = new HandleStatus();
		Ref<HandleStatus> outStauts = new Ref<HandleStatus>(handleStatus);
		Handle4Alipay.payBack(mapPars, outStauts, true);
		handleStatus = outStauts.val;

		if (handleStatus.verify_result) {
			if (handleStatus.isCanHandle && handleStatus.curOrder != null) {
				// 0买套餐,1学测(1元),2代测(1元),3买考币,4买问答,5学生支付宝验证
				int type = handleStatus.curOrder.getType();
				switch (type) {
				case 0:
					// 购物车处理
					handleShopCutMap(handleStatus.curOrder,
							handleStatus.kindids);
					break;
				case 1:
				case 2:
					handleVerifyAlipay4Agent_Lhub(handleStatus.curOrder,
							handleStatus.buyemail);
					break;
				case 3:
					handleBuyKbi(handleStatus.curOrder);
					break;
				case 4:
					handleAsk(handleStatus.curOrder);
					break;
				case 5:
					handleVerifyAlipay4Customer(handleStatus.curOrder,
							handleStatus.buyemail);
					break;
				default:
					break;
				}
			}
			return true;
		}
		return false;
	}

	// 充值回调回来 -- 购买套餐
	static void handleShopCutMap(Orders order, List<Integer> kindids) {
		if (order.getType() != 0) {
			return;
		}

		Customer customer = CustomerEntity.getByKey(order.getMakerid());
		if (customer == null)
			return;

		// 订单购买成功回调插入
		// 处理逻辑记记录
		int kbi = order.getKbi();
		if (kbi > 0) {
			double realMoney = order.getRealprice();
			// customer.changeMoneyAll(realMoney);
			customer.changeKbiUse(kbi);
			customer.changeKbiAll(kbi);
			customer.update();
			String content = PStr.str("花费:", realMoney, "元,", order.getName(),
					",获得考币:", kbi);
			Utls.recordRmb(customer, 2, realMoney, content, 0);
			Utls.recordKbi(customer, 1, kbi, content);
		}

		if (ListEx.isEmpty(kindids)) {
			return;
		}

		int customerid = customer.getId();

		// 清空购物车
		ShoppingcartEntity.delShopCartBy(customerid, kindids);

		int lens = kindids.size();
		Date validtime = null;
		Date nowtime = DateEx.nowDate();

		for (int i = 0; i < lens; i++) {
			int kindid = kindids.get(i);
			Kind enKind = KindEntity.getByKey(kindid);
			if (enKind == null)
				continue;

			String name = enKind.getNmProduct();
			double price = enKind.getPrice();
			int lhubid = enKind.getLhubid();
			int validDay = enKind.getValidity();

			Boughtkinds buyKind = BoughtkindsEntity.getByCustomeridKindid(
					customerid, kindid);
			if (buyKind == null) {
				validtime = DateEx.addDay(nowtime, validDay);
				buyKind = Boughtkinds.newBoughtkinds(0, name, customerid,
						kindid, price, kbi, 0, 0, nowtime, validtime, lhubid);
				buyKind.insert();
			} else {
				validtime = buyKind.getValidtime();
				if (DateEx.isAfter(validtime, nowtime)) {
					validtime = DateEx.addDay(validtime, validDay);
					buyKind.setValidtime(validtime);
				} else {
					validtime = DateEx.addDay(nowtime, validDay);
					buyKind.setValidtime(validtime);
				}
				buyKind.update();
			}
		}

		order.setStatusProcess(Handle4Alipay.Status_SUCCESS_Handle);
		order.setLasttime(nowtime);
		order.update();
	}

	// 充值回调回来 -- 验证支付宝是否可用
	static void handleVerifyAlipay4Agent_Lhub(Orders order, String buyemail) {
		int type = order.getType();
		if (type != 1 && type != 2) {
			return;
		}
		int orderid = order.getId();
		String host = "http://1010xue.com/LH3Manager/notify_url_test";
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("orderid", String.valueOf(orderid));
		parames.put("buyemail", buyemail);
		HttpResponse httpResponse = HttpUriPostEx.postMap(host, parames,
				EncodingEx.UTF_8);
		String vStr = HttpUriPostEx.readStr(httpResponse, "");
		if (!"success".equalsIgnoreCase(vStr)) {
			logger.info("== 一元回调处理失败 订单 ID ==" + orderid);
		}
	}

	static void handleBuyKbi(Orders order) {
		int type = order.getType();
		if (type != 3) {
			return;
		}
		Customer customer = CustomerEntity.getByKey(order.getMakerid());
		if (customer == null)
			return;

		// 订单购买成功回调插入
		int kbi = order.getKbi();
		if (kbi > 0) {
			double realMoney = order.getRealprice();
			// customer.changeMoneyAll(realMoney);

			customer.changeKbiUse(kbi);
			customer.changeKbiAll(kbi);
			customer.update();
			String content = PStr.str("花费:", realMoney, "元,", order.getName(),
					",获得考币:", kbi);
			Utls.recordRmb(customer, 2, realMoney, content, 0);
			Utls.recordKbi(customer, 1, kbi, content);
		}
		Date nowtime = DateEx.nowDate();
		order.setStatusProcess(Handle4Alipay.Status_SUCCESS_Handle);
		order.setLasttime(nowtime);
		order.update();
	}

	static void handleAsk(Orders order) {
		int type = order.getType();
		if (type != 4) {
			return;
		}
		Customer customer = CustomerEntity.getByKey(order.getMakerid());
		if (customer == null)
			return;
		double realMoney = order.getRealprice();
		String content = PStr.str("花费:", realMoney, "元,", order.getName());
		Utls.recordRmb(customer, 2, realMoney, content, 0);

		Date nowtime = DateEx.nowDate();
		order.setStatusProcess(Handle4Alipay.Status_SUCCESS_Handle);
		order.setLasttime(nowtime);
		order.update();
	}

	// 充值回调回来 -- 验证支付宝是否可用
	static void handleVerifyAlipay4Customer(Orders order, String buyemail) {
		int type = order.getType();
		if (type != 5) {
			return;
		}
		Customer customer = CustomerEntity.getByKey(order.getMakerid());
		if (customer == null)
			return;
		double realMoney = order.getRealprice();
		String content = PStr.str("花费:", realMoney, "元,", order.getName());
		Utls.recordRmb(customer, 2, realMoney, content, 0);

		customer.setAlipay(buyemail);
		customer.setIsVerifyAlipay(true);
		customer.update();

		Date nowtime = DateEx.nowDate();
		order.setStatusProcess(Handle4Alipay.Status_SUCCESS_Handle);
		order.setLasttime(nowtime);
		order.update();
	}

	static public void msgHandleShop(int oderid) {
		if (oderid <= 0) {
			return;
		}

		Orders orders = OrdersEntity.getByKey(oderid);
		if (orders == null) {
			return;
		}

		if (orders.getType() != 0) {
			return;
		}
		
		if (orders.getStatusProcess() == 2) {
			return;
		}
		
		// 处理购买套餐
		List<Integer> ids = ListEx.toListInt(orders.getExtra_param());
		handleShopCutMap(orders, ids);
	}

	public static void main(String[] args) {
		String vl = "{\"buyer_email\":\"gongyanghui1986@163.com\",\"buyer_id\":\"2088202322453595\",\"exterface\":\"create_direct_pay_by_user\",\"is_success\":\"T\",\"notify_id\":\"RqPnCoPT3K9%2Fvwbh3InSP0xoM3mN3jkF2f453bcwLsL9wPrAobAgZFtkZMStli3UUxtq\",\"notify_time\":\"2015-08-10 16:36:21\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"126d11b4d3bfb192b4f526c76ddfce16\",\"payment_type\":\"1\",\"seller_email\":\"scpxmail@aliyun.com\",\"seller_id\":\"2088611773240537\",\"sign\":\"e932c392c2fb98323ba01ea43a759e23\",\"sign_type\":\"MD5\",\"subject\":\"理论,\",\"total_fee\":\"1.00\",\"trade_no\":\"2015081000001000590058320669\",\"trade_status\":\"TRADE_SUCCESS\"}";
		vl = "{\"buyer_email\":\"gongyanghui1986@163.com\",\"buyer_id\":\"2088202322453595\",\"discount\":\"0.00\",\"gmt_create\":\"2015-08-10 17:33:49\",\"gmt_payment\":\"2015-08-10 17:33:55\",\"is_total_fee_adjust\":\"N\",\"notify_id\":\"a9adfb0de81177f03563ad1584ee1ede5a\",\"notify_time\":\"2015-08-10 17:33:56\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"4605cccad42e2203cb73f974ce5ac853\",\"payment_type\":\"1\",\"price\":\"1.00\",\"quantity\":\"1\",\"seller_email\":\"scpxmail@aliyun.com\",\"seller_id\":\"2088611773240537\",\"sign\":\"567d0947eec6dc5dd5121110b29dd4f8\",\"sign_type\":\"MD5\",\"subject\":\"??,\",\"total_fee\":\"1.00\",\"trade_no\":\"2015081000001000590058325399\",\"trade_status\":\"TRADE_SUCCESS\",\"use_coupon\":\"N\"}";
		Map map = (Map) JSON.parseObject(vl);
		Map<String, String> mapPars = MapEx.toMapKV(map);
		handle(mapPars);
	}
}
