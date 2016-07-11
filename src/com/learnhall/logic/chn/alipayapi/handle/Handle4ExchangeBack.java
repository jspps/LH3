package com.learnhall.logic.chn.alipayapi.handle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;

import com.alibaba.fastjson.JSON;
import com.bowlong.lang.PStr;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.learnhall.db.bean.Exchangermb;
import com.learnhall.db.entity.ExchangermbEntity;
import com.learnhall.logic.Utls;
import com.learnhall.logic.chn.alipayapi.AlipayNotify;
import com.learnhall.logic.chn.alipayapi.exchange.entity.AliExchange4Notify;

/***
 * 批量付款到支付宝账户回调验证，以及验证过后的逻辑处理 <br/>
 * exchangermb.type[0学员,1lhub,2agent] batchNo <br/>
 * exchangermb.statusOpt[0审核中,1取消中,2已取消,3拒绝，4同意,5转成功]
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes" })
public class Handle4ExchangeBack implements Serializable {

	private static final long serialVersionUID = 1L;

	static public boolean isDebug = true;

	static final Log logger = Utls.getLog(Handle4ExchangeBack.class);

	static final int Opt_Success = 5;

	static public final void logger(String info) {
		if (!isDebug)
			return;
		logger.info(info);
	}

	static public final boolean handle(HttpServletRequest request) {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request, "", "");
		return handle(mapPars);
	}

	static public final boolean handle(Map<String, String> mapPars) {
		// 计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(mapPars);

		String content = MapEx.toStr4Json(mapPars);
		content = PStr.str("notify===", content);
		logger(content);

		if (verify_result) {
			AliExchange4Notify en = AliExchange4Notify.toEntity(mapPars);
			String batchNo = en.getBatch_no();
			List<Exchangermb> list = ExchangermbEntity.getByBatchNo(batchNo);

			if (!ListEx.isEmpty(list)) {
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Exchangermb en4Exchange = list.get(i);
					en4Exchange.setContent(content);
					en4Exchange.setStatusOpt(Opt_Success);
					en4Exchange.setLasttime(DateEx.nowDate());
				}

				// 更新里面没有批量(需要添加)
				ExchangermbEntity.updateByKey(list);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String vl = "{\"buyer_email\":\"gongyanghui1986@163.com\",\"buyer_id\":\"2088202322453595\",\"exterface\":\"create_direct_pay_by_user\",\"is_success\":\"T\",\"notify_id\":\"RqPnCoPT3K9%2Fvwbh3InSP0xoM3mN3jkF2f453bcwLsL9wPrAobAgZFtkZMStli3UUxtq\",\"notify_time\":\"2015-08-10 16:36:21\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"126d11b4d3bfb192b4f526c76ddfce16\",\"payment_type\":\"1\",\"seller_email\":\"scpxmail@aliyun.com\",\"seller_id\":\"2088611773240537\",\"sign\":\"e932c392c2fb98323ba01ea43a759e23\",\"sign_type\":\"MD5\",\"subject\":\"理论,\",\"total_fee\":\"1.00\",\"trade_no\":\"2015081000001000590058320669\",\"trade_status\":\"TRADE_SUCCESS\"}";
		vl = "{\"buyer_email\":\"gongyanghui1986@163.com\",\"buyer_id\":\"2088202322453595\",\"discount\":\"0.00\",\"gmt_create\":\"2015-08-10 17:33:49\",\"gmt_payment\":\"2015-08-10 17:33:55\",\"is_total_fee_adjust\":\"N\",\"notify_id\":\"a9adfb0de81177f03563ad1584ee1ede5a\",\"notify_time\":\"2015-08-10 17:33:56\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"4605cccad42e2203cb73f974ce5ac853\",\"payment_type\":\"1\",\"price\":\"1.00\",\"quantity\":\"1\",\"seller_email\":\"scpxmail@aliyun.com\",\"seller_id\":\"2088611773240537\",\"sign\":\"567d0947eec6dc5dd5121110b29dd4f8\",\"sign_type\":\"MD5\",\"subject\":\"??,\",\"total_fee\":\"1.00\",\"trade_no\":\"2015081000001000590058325399\",\"trade_status\":\"TRADE_SUCCESS\",\"use_coupon\":\"N\"}";
		Map map = (Map) JSON.parseObject(vl);
		Map<String, String> mapPars = MapEx.toMapKV(map);
		handle(mapPars);
	}
}
