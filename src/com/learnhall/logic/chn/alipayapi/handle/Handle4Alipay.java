package com.learnhall.logic.chn.alipayapi.handle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;

import com.bowlong.lang.NumFmtEx;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.objpool.StringBufPool;
import com.bowlong.security.MD5;
import com.bowlong.tool.TkitJsp;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.learnhall.db.bean.Agent;
import com.learnhall.db.bean.Cfgs;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Exchangermb;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.bean.Orders;
import com.learnhall.db.bean.Record4orders;
import com.learnhall.db.bean.Shoppingcart;
import com.learnhall.db.entity.AgentEntity;
import com.learnhall.db.entity.CfgsEntity;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.db.entity.ExchangermbEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.LearnhubEntity;
import com.learnhall.db.entity.OrdersEntity;
import com.learnhall.db.entity.Record4ordersEntity;
import com.learnhall.logic.chn.alipayapi.AlipayConfig;
import com.learnhall.logic.chn.alipayapi.AlipayCore;
import com.learnhall.logic.chn.alipayapi.AlipayNotify;
import com.learnhall.logic.chn.alipayapi.AlipaySubmit;
import com.learnhall.logic.chn.alipayapi.entity.AlipayEn4Notify;
import com.learnhall.logic.chn.alipayapi.entity.AlipayEn4Ques;
import com.learnhall.logic.chn.alipayapi.entity.AlipayEn4ReturnUrl;
import com.learnhall.logic.chn.alipayapi.exchange.entity.AliExchange4Ques;

/***
 * 验证充值回调，并插入数据库记录
 * 
 * @author Canyon
 * 
 */
public class Handle4Alipay {

	static public final String notify_url = "http://1010xue.com/notify_url";
	static public final String return_url = "http://1010xue.com/return_url";

	static public final String return_url_valipay4agen_lhub = "http://1010xue.com/LH3Manager/return_url_test";
	static public final String return_url_valipay4customer = "http://1010xue.com/return_url_4_customer";

	static public final String notify_url_exchange = "http://1010xue.com/notify_url_exchange";

	static public final String SUCCESS = "success";
	static public final String ERROR = "error";

	static public final int Status_DEFAULT = 0;
	static public final int Status_SUCCESS = 1;
	static public final int Status_SUCCESS_Handle = 2;

	static public String handle4ShopCarts(Customer cust,
			List<Shoppingcart> list, String recommendCode, Ref<String> status) {
		if (cust == null || ListEx.isEmpty(list)) {
			status.val = "error,出现错误，对象为空!";
			return "出现错误，对象为空!";
		}
		if (StrEx.isEmptyTrim(recommendCode))
			recommendCode = list.get(0).getAgentCode();
		List<Kind> listKind = new ArrayList<Kind>();
		int lens = list.size();
		for (int i = 0; i < lens; i++) {
			Shoppingcart shop = list.get(i);
			Kind e = KindEntity.getByKey(shop.getKindid());
			if (e == null)
				continue;
			listKind.add(e);
		}
		return handle4Kinds(cust, listKind, recommendCode, status);
	}

	static public String handle4Kinds(Customer cust, List<Kind> list,
			String recommendCode, Ref<String> status4Out) {
		if (cust == null || ListEx.isEmpty(list)) {
			status4Out.val = "error,出现错误，对象为空!";
			return "出现错误，对象为空!";
		}

		StringBuffer buff = StringBufPool.borrowObject();
		try {
			// 产生订单
			int custid = cust.getId();
			String custname = cust.getName();
			Date createtime = DateEx.nowDate();
			int status = 0;
			int statusProcess = 0;
			Date lasttime = createtime;
			String out_trade_no = MD5.MD5UUIDStime();

			HandleProfit profit = HandleProfit.getDealWith(custid,
					out_trade_no, recommendCode, list);

			Orders orders = Orders.newOrders(0, 0, profit.name, profit.kindids,
					custid, profit.allPrice, profit.allKbi, createtime, status,
					statusProcess, custname, profit.allDiscount, recommendCode,
					lasttime, out_trade_no, profit.allRealPrice);
			orders = orders.insert();

			String royalty_parameters = profit.royalty_parameters;

			AlipayEn4Ques quesEntity = new AlipayEn4Ques(notify_url,
					out_trade_no, profit.name, profit.allRealPrice,
					royalty_parameters);
			quesEntity.setPartner(AlipayConfig.partner);
			quesEntity.setSeller_email(AlipayConfig.seller_email);
			quesEntity.setReturn_url(return_url);
			// 设置不是扫描支付
			quesEntity.setQr_pay_mode("");

			Map<String, String> mapBuild = AlipayCore
					.buildRequestPara(quesEntity);
			String vRes = AlipaySubmit.buildRequest(mapBuild, "post", "确定");
			status4Out.val = "success";
			return vRes;
		} catch (Exception e) {
			String erinfo = TkitJsp.e2s(e);
			status4Out.val = "error," + erinfo;
			return erinfo;
		} finally {
			StringBufPool.returnObject(buff);
		}

	}

	/*** 取得人民币兑换考币值 **/
	static int getKbiRate() {
		int kbiRate = 100;
		Cfgs cfg = CfgsEntity.getByKey(6);
		if (cfg != null) {
			kbiRate = cfg.getValInt();
		}
		return kbiRate;
	}

	/*** type[0买套餐,1学测(1元),2代测(1元),3买考币,4买问答,5学员支付宝验证] **/
	static public String handle4Kbi0Ask(int type, Customer cust, double money,
			String extra_param, Ref<String> status4Out) {
		if (cust == null) {
			status4Out.val = "error,出现错误，对象为空!";
			return "出现错误，对象为空!";
		}

		if (money <= 0) {
			status4Out.val = "error,出现错误，金额不能小于0!";
			return "出现错误，对象为空!";
		}

		if (type != 3 && type != 4) {
			status4Out.val = "error,出现错误，类型type出错!";
			return "出现错误，对象为空!";
		}

		// 产生订单
		String name = "";
		String return_url_4_Kbi_Ask = "";
		int kbi = 0;
		if (type == 3) {
			name = "购买考币";
			return_url_4_Kbi_Ask = "http://1010xue.com/return_url_buykbi";
			kbi = (int) (money * getKbiRate());
		} else {
			name = "学生提问";
			return_url_4_Kbi_Ask = "http://1010xue.com/return_url_ask";
		}

		int custid = cust.getId();
		String custname = cust.getName();
		double price = money;

		Date createtime = DateEx.nowDate();
		int status = 0;
		int statusProcess = 0;
		double discount = 0;
		Date lasttime = createtime;
		String out_trade_no = MD5.MD5UUIDStime();

		double total_fee = money;

		Orders orders = Orders.newOrders(0, type, name, extra_param, custid,
				price, kbi, createtime, status, statusProcess, custname,
				discount, "", lasttime, out_trade_no, total_fee);
		orders = orders.insert();

		String royalty_parameters = "";

		AlipayEn4Ques quesEntity = new AlipayEn4Ques(notify_url, out_trade_no,
				name, total_fee, royalty_parameters);
		quesEntity.setPartner(AlipayConfig.partner);
		quesEntity.setSeller_email(AlipayConfig.seller_email);
		quesEntity.setReturn_url(return_url_4_Kbi_Ask);
		// 设置不是扫描支付
		quesEntity.setQr_pay_mode("");

		Map<String, String> mapBuild = AlipayCore.buildRequestPara(quesEntity);
		return AlipaySubmit.buildRequest(mapBuild, "post", "确定");
	}

	/*** type[0买套餐,1学测(1元),2代测(1元),3买考币,4买问答,5学员支付宝验证] **/
	static public String handle4VerifyAlipay(int type, int makerid,
			String makername, double money, String notify_url_str,
			String return_url_str) {
		// 产生订单
		String name = PStr.str("进行支付宝状态(是否可用)验证!");
		double price = 0;
		int kbi = 0;
		Date createtime = DateEx.nowDate();
		int status = 0;
		int statusProcess = 0;
		double discount = 0;
		Date lasttime = createtime;
		String out_trade_no = MD5.MD5UUIDStime();

		double total_fee = money;

		Orders orders = Orders.newOrders(0, type, name, "", makerid, price,
				kbi, createtime, status, statusProcess, makername, discount,
				"", lasttime, out_trade_no, total_fee);
		orders = orders.insert();

		String royalty_parameters = "";

		AlipayEn4Ques quesEntity = new AlipayEn4Ques(notify_url_str,
				out_trade_no, name, total_fee, royalty_parameters);
		quesEntity.setPartner(AlipayConfig.partner);
		quesEntity.setSeller_email(AlipayConfig.seller_email);
		quesEntity.setReturn_url(return_url_str);
		// 设置不是扫描支付
		quesEntity.setQr_pay_mode("");

		Map<String, String> mapBuild = AlipayCore.buildRequestPara(quesEntity);
		return AlipaySubmit.buildRequest(mapBuild, "post", "确定");
	}

	/*** type[0买套餐,1学测(1元),2代测(1元),3买考币,4买问答,5学员支付宝验证] **/
	static public String handle4VerifyAlipay(int type, int makerid,
			String makername, double money) {
		switch (type) {
		case 1:
		case 2:
			return handle4VerifyAlipay(type, makerid, makername, money,
					notify_url, return_url_valipay4agen_lhub);
		case 5:
			return handle4VerifyAlipay(type, makerid, makername, money,
					notify_url, return_url_valipay4customer);
		default:
			break;
		}
		return PStr.str("出现错误，类型不正确,type=", type);
	}

	/*** 创建(兑现-提取现金) 等待管理者审核,type[0学员,1lhub,2agent] **/
	static public String createExchangeRmb(int type, int makerid,
			double monyeApply, String reason) {
		if (type < 0 || type > 2) {
			return PStr.str("出现错误，类型不正确,type=", type);
		}

		if (monyeApply < 20) {
			return PStr.str("出现错误，申请金额低于了20元,monyeApply=", monyeApply);
		}

		int max = 300;

		if (monyeApply > max) {
			return PStr.str("出现错误，申请金额超过了", max, "元,monyeApply=", monyeApply);
		}

		String nmMaker = "";
		String alipay = "";
		String alipayName = "";
		double moneyCur = 0;
		double monyeReal = 0;
		Date createtime = DateEx.nowDate();
		Date lasttime = createtime;

		switch (type) {
		case 0:
			// 学员
			Customer enCust = CustomerEntity.getByKey(makerid);
			nmMaker = enCust.getName();
			if (enCust.getIsVerifyAlipay()) {
				alipay = enCust.getAlipay();
				alipayName = enCust.getAlipayRealName();
			}
			moneyCur = enCust.getMoneyCur();
			if (moneyCur < monyeApply) {
				return PStr.str("出现错误，学员:", nmMaker, ",当前金额不足,moneyCur=",
						moneyCur);
			}
			enCust.changeMoneyCur(-monyeApply);
			enCust.update();
			break;
		case 1:
			// 学习中心
			Learnhub enLhub = LearnhubEntity.getByKey(makerid);
			nmMaker = enLhub.getName();
			if (enLhub.getIsVerifyAlipay()) {
				alipay = enLhub.getAlipay();
			}
			moneyCur = enLhub.getMoneyCur();
			if (moneyCur < monyeApply) {
				return PStr.str("出现错误，学习中心:", nmMaker, ",当前金额不足,moneyCur=",
						moneyCur);
			}
			enLhub.changeMoneyCur(-monyeApply);
			enLhub.update();
			break;
		case 2:
			// 代理商
			Agent enAgent = AgentEntity.getByKey(makerid);
			nmMaker = enAgent.getUname();
			if (enAgent.getIsVerifyAlipay()) {
				alipay = enAgent.getAlipay();
			}
			moneyCur = enAgent.getCurmoney();
			if (moneyCur < monyeApply) {
				return PStr.str("出现错误，代理商:", nmMaker, ",当前金额不足,moneyCur=",
						moneyCur);
			}
			enAgent.changeCurmoney(-monyeApply);
			enAgent.update();
			break;
		default:
			break;
		}

		if (StrEx.isEmpty(alipay) || StrEx.isEmpty(alipayName)) {
			return PStr.str("出现错误，支付宝帐号或者帐号名称为空", "操作标识Type:", type, ",操作者ID:",
					makerid, ",操作者:", nmMaker);
		}

		monyeReal = monyeApply * (1 - 0.05);

		Exchangermb exchangermb = Exchangermb.newExchangermb(0, type, makerid,
				nmMaker, alipay, alipayName, reason, moneyCur, monyeApply,
				monyeReal, 0, "", 0, createtime, "", lasttime);
		exchangermb.insert();

		return SUCCESS;
	}

	/*** 兑现-提取现金 管理者通过审核,type[0学员,1lhub,2agent] **/
	static public String handle4ExchangeRmb(List<Integer> listIds) {
		if (ListEx.isEmpty(listIds)) {
			return "出现错误，申请提款为空!";
		}

		String pay_date = DateEx.nowStr5();
		String batchNo = PStr.str(pay_date, MD5.MD5UUIDStimeF16());
		int batch_num = 0;
		String detail_data = "";
		double batch_fee = 0;

		StringBuffer buff = StringBufPool.borrowObject();
		try {
			int lens = listIds.size();
			double realMoney = 0;

			// 相同支付宝帐号的,金额相加处理
			ConcurrentHashMap<String, Double> map4Exchange = new ConcurrentHashMap<String, Double>();

			for (int i = 0; i < lens; i++) {
				int tmpid = listIds.get(i);
				if (tmpid <= 0) {
					continue;
				}

				Exchangermb en = ExchangermbEntity.getByKey(tmpid);
				if (en == null || en.getStatus() != 0 || en.getStatusOpt() != 0
						|| en.getMonyeReal() <= 0) {
					continue;
				}

				en.setBatchNo(batchNo);
				en.changeStatusOpt(3);
				en.update();

				// 清空
				buff.setLength(0);

				// 流水号
				buff.append(batchNo).append("^");
				// 提现支付宝帐号
				buff.append(en.getAlipay()).append("^");
				// 提现支付宝账户姓名
				buff.append(en.getAlipayName()).append("^");

				detail_data = buff.toString();
				realMoney = en.getMonyeReal();

				if (map4Exchange.containsKey(detail_data)) {
					realMoney += map4Exchange.get(detail_data);
				}

				map4Exchange.put(detail_data, realMoney);
			}

			lens = map4Exchange.size();
			buff.setLength(0);

			for (Entry<String, Double> entry : map4Exchange.entrySet()) {

				// 清空
				buff.setLength(0);

				buff.append(entry.getKey());

				realMoney = entry.getValue();

				// 提取金额
				String mnStr = NumFmtEx.formatDouble(realMoney);
				if (mnStr.indexOf(".") == 0) {
					mnStr = PStr.str("0", mnStr);
				}
				buff.append(mnStr).append("^");
				// 备注
				buff.append("check-cashing:").append(mnStr);
				if (batch_num < lens - 1) {
					buff.append("|");
				}

				batch_fee += realMoney;
				batch_num++;
			}

			detail_data = buff.toString();
		} catch (Exception e) {
		} finally {
			StringBufPool.returnObject(buff);
		}

		if (batch_num <= 0) {
			return "出现错误，申请提款batch_num为零!";
		}

		AliExchange4Ques aliExchange = new AliExchange4Ques(
				AlipayConfig.partner, notify_url_exchange,
				AlipayConfig.alipay_name, detail_data, batchNo,
				String.valueOf(batch_num), String.valueOf(batch_fee),
				AlipayConfig.seller_email, pay_date);

		Map<String, String> mapBuild = AlipayCore.buildRequestPara(aliExchange);
		return AlipaySubmit.buildRequest(mapBuild, "post", "确定");
	}

	@SuppressWarnings("unchecked")
	static public String handle4ExchangeRmb(int... exchangeid) {
		if (ListEx.isEmpty(exchangeid)) {
			return "出现错误，申请提款ids为空!";
		}
		List<Integer> ids = ListEx.toList(exchangeid);
		ids = ListEx.fitlerList(ids, Integer.valueOf(0));

		if (ListEx.isEmpty(ids)) {
			return "出现错误，申请提款ids为空!";
		}
		return handle4ExchangeRmb(ids);
	}

	// ////////////=== 以下是处理验证处理 ===////////////

	static public final void payBack(Map<String, String> mapPars,
			Ref<HandleStatus> refHandleStatus, boolean isNoitfy) {
		PayBack4Alipay.handleBack(mapPars, refHandleStatus, isNoitfy);
	}

	static public final void isDebug(boolean isDebug) {
		PayBack4Alipay.isDebug = isDebug;
	}

	static class PayBack4Alipay {

		static public boolean isDebug = false;

		static final Log log = TkitJsp.getLog(PayBack4Alipay.class);

		static public final void logger(String info) {
			if (!isDebug)
				return;
			log.info(info);
		}

		static public final void handleBack(Map<String, String> mapPars,
				Ref<HandleStatus> refHandleStatus, boolean isNoitfy) {

			// 是否可以记录日志(充值回调的日志)
			boolean isCanRecord = false;

			// 处理状态
			boolean isCanHandle = false;

			// 计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(mapPars);

			refHandleStatus.val.verify_result = verify_result;

			String content = MapEx.toStr4Json(mapPars);
			if (isNoitfy) {
				content = PStr.str("notify===", content);
			} else {
				content = PStr.str("return===", content);
			}

			logger(content);

			String tmpStr = "";

			if (verify_result) {
				String orderNo = "";
				String tradeNo = "";
				if (isNoitfy) {
					AlipayEn4Notify en = AlipayEn4Notify.toEntity(mapPars);
					if (en != null) {
						orderNo = en.getOut_trade_no();
						tradeNo = en.getTrade_no();
						refHandleStatus.val.buyemail = en.getBuyer_email();
						tmpStr = PStr.str("AlipayEn4Notify = ",
								MapEx.toStr4Json(en.toMapKV()));
						logger(tmpStr);
					}
				} else {
					AlipayEn4ReturnUrl en = AlipayEn4ReturnUrl
							.toEntity(mapPars);
					if (en != null) {
						orderNo = en.getOut_trade_no();
						tradeNo = en.getTrade_no();
						refHandleStatus.val.buyemail = en.getBuyer_email();

						tmpStr = PStr.str("AlipayEn4ReturnUrl = ",
								MapEx.toStr4Json(en.toMapKV()));
						logger(tmpStr);
					}
				}

				tmpStr = PStr.str("orderNo = ", orderNo);
				logger(tmpStr);

				if (!StrEx.isEmptyTrim(orderNo)) {
					Orders order = OrdersEntity.getByOrderNo(orderNo);
					if (order != null) {
						// 还没处理充值
						int status4Process = order.getStatusProcess();
						switch (status4Process) {
						case Status_DEFAULT:
							order.setStatusProcess(Status_SUCCESS);
							isCanHandle = true;
							isCanRecord = true;
							break;
						case Status_SUCCESS:
							isCanHandle = true;
							break;
						default:
							break;
						}

						if (isCanHandle) {
							logger("isCanHandle = true");
							if (order.getType() == 0) {
								// 处理购买套餐
								List<Integer> ids = ListEx.toListInt(order
										.getExtra_param());
								if (!ListEx.isEmpty(ids)) {
									refHandleStatus.val.kindids = ids;
								}
							}
							order.setStatusProcess(Status_SUCCESS);
							Date lasttime = DateEx.nowDate();
							order.setLasttime(lasttime);
							order = order.update();
						}

						refHandleStatus.val.curOrder = order;
					}

					// 记录日志
					if (isCanRecord) {

						logger("isCanRecord = true");

						Record4orders record = Record4orders.newRecord4orders(
								0, orderNo, tradeNo, content, DateEx.nowDate());
						Record4ordersEntity.insertAsyn2Log(record);
					}
				}

			}

			refHandleStatus.val.isCanHandle = isCanHandle;
			refHandleStatus.val.isCanRecord4Sql = isCanRecord;
			refHandleStatus.val.isCanRecord4Log = !verify_result
					|| !isCanHandle || !isCanRecord;
		}
	}
}