package com.learnhall.logic.chn.alipayapi.handle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bowlong.lang.NumFmtEx;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.bean.Agent;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.bean.Orders4profit;
import com.learnhall.db.entity.AgentEntity;
import com.learnhall.db.entity.Orders4profitEntity;

/***
 * 分润出来
 * 
 * @author Canyon
 * 
 *         createtime:2015-8-15 下午4:16:33
 */
public class HandleProfit implements Serializable {

	private static final long serialVersionUID = 1L;
	// 总价
	public double allPrice = 0;
	// 总得考币值
	public int allKbi = 0;
	// 总优惠价
	public double allDiscount = 0;
	// 总优实际
	public double allRealPrice = 0;
	// 订单名字
	public String name = "";
	// 订单ID列表
	public String kindids = "";

	// 分润字符串
	public String royalty_parameters = "";

	@Override
	public String toString() {
		return "HandleProfit [allPrice=" + allPrice + ", allKbi=" + allKbi
				+ ", allDiscount=" + allDiscount + ", allRealPrice="
				+ allRealPrice + ", name=" + name + ", kindids=" + kindids
				+ ", royalty_parameters=" + royalty_parameters + "]";
	}

	static public final String alipay4Developer = "gongyanghui1986@163.com";
	static public String licheng = "623167974@qq.com";
	static public final String pattern = "${1}^${2}^${3}";
	static public final String split = "|";

	static public HandleProfit getDealWith(int custid, String orderNo,
			String recommendCode, List<Kind> list) {
		HandleProfit profit = new HandleProfit();
		if (ListEx.isEmpty(list))
			return profit;

		//
		StringBuffer buff4KindIds = new StringBuffer();
		StringBuffer buff4Names = new StringBuffer();
		// 分润alipay_email --> money
		Map<String, Double> map4Royalty = new HashMap<String, Double>();

		// 程序
		double tc4Dev = 0;
		// 代理商
		double tc4Agent = 0;
		// 美工
		double tc4Art = 0;
		Date createtime = DateEx.nowDate();

		Agent agent = AgentEntity.getByCode(recommendCode);
		boolean isDiscount = false;
		String alipay4Agent = "";
		boolean isProfit4Agent = false;
		int agentid = 0;
		if (agent != null) {
			agentid = agent.getAgid();
			// 判断是否有效
			isDiscount = DateEx.isAfter(agent.getEndtime(), createtime);
			if (isDiscount) {
				isProfit4Agent = agent.getIsVerifyAlipay();
				if (isProfit4Agent) {
					alipay4Agent = agent.getAlipay();
				}
			}
		}

		Map<Integer, Adcourses> map4Course = new HashMap<Integer, Adcourses>();
		Map<Integer, Learnhub> map4Lhub = new HashMap<Integer, Learnhub>();
		List<Orders4profit> profitList = new ArrayList<Orders4profit>();
		int lens = list.size();
		for (int i = 0; i < lens; i++) {
			Kind kindEn = list.get(i);

			int kindid = kindEn.getId();
			double price = kindEn.getPrice();
			double discount = kindEn.getDiscount();
			int kbi = kindEn.getKbi();

			buff4KindIds.append(kindid).append(",");
			buff4Names.append(kindEn.getNmProduct());
			if (i < lens - 1) {
				buff4Names.append(",");
			}

			double realprice = 0;
			if (isDiscount) {
				realprice += discount;
			} else {
				realprice += price;
			}

			profit.allPrice += price;
			profit.allKbi += kbi;
			profit.allDiscount += discount;
			profit.allRealPrice += realprice;

			// 提成计算
			int coursid = kindEn.getCoursid();
			// 代理商奖金
			double agentBonus = 0;
			// 代理商提成
			double agentRoyalty = 0;
			// 学习中心题库提成
			double lhubRoyalty = 0;
			// 学习中心质量押金
			double lhubDeposit = 0;
			// 开发提成
			double developRoyalty = 0;
			// 美工提成
			double artRoyalty = 0;

			Adcourses course = map4Course.get(coursid);
			if (course == null) {
				course = kindEn.getAdcoursesFkCoursid();
				map4Course.put(coursid, course);
			}

			if (course != null) {
				agentBonus = course.getBonus() * realprice * 0.01;
				agentRoyalty = course.getProfitAgent() * realprice * 0.01;
				lhubRoyalty = course.getProfitOwner() * realprice * 0.01;
				lhubDeposit = course.getDeposit() * realprice * 0.01;
				developRoyalty = course.getProgram();
				artRoyalty = course.getArt();
			}

			int lhubid = kindEn.getLhubid();
			boolean isProfit4Lhub = false;
			Learnhub lhub = map4Lhub.get(lhubid);
			if (lhub == null) {
				lhub = kindEn.getLearnhubFkLhubid();
				map4Lhub.put(lhubid, lhub);
				if (lhub != null) {
					isProfit4Lhub = lhub.getIsVerifyAlipay();
				}
			}

			// 程序提成
			tc4Dev += developRoyalty;
			tc4Art += artRoyalty;
			tc4Agent += agentRoyalty;

			if (isProfit4Lhub) {
				String alipay4Lhub = lhub.getAlipay();
				if (!StrEx.isEmptyTrim(alipay4Lhub)) {
					double tc4lub = lhubRoyalty;
					if (map4Royalty.containsKey(alipay4Lhub)) {
						tc4lub += map4Royalty.get(alipay4Lhub);
					}
					map4Royalty.put(alipay4Lhub, tc4lub);
				}
			}

			// 产生订单分红记录表
			Orders4profit profitEn = Orders4profit.newOrders4profit(0, orderNo,
					kindid, custid, lhubid, agentid, agentBonus, agentRoyalty,
					lhubRoyalty, lhubDeposit, developRoyalty, artRoyalty,
					price, discount, realprice, isProfit4Agent, isProfit4Lhub,
					0, createtime);
			profitList.add(profitEn);
		}

		profit.kindids = buff4KindIds.toString();
		profit.name = buff4Names.toString();
		profit.name = StrEx.ellipsis(profit.name, 120);

		// 分润字符串的拼接
		StringBuffer buff4pattern = new StringBuffer();
		// 备注
		String tmpBonus = "";
		
		// /////////////// 分润 /////////////// 
		// 开发者提成
		tc4Dev = tc4Dev < 1 ? 1 : tc4Dev;
		String str4Tc = NumFmtEx.formatDouble(tc4Dev);
		if (str4Tc.indexOf(".") == 0) {
			str4Tc = PStr.str("0", str4Tc);
		}

		tmpBonus = PStr.str("bonus:", str4Tc);
		String rpars1 = StrEx.fmt(pattern, alipay4Developer, str4Tc, tmpBonus);
		buff4pattern.append(rpars1);

		// 美工提成
		if (!StrEx.isEmpty(licheng)) {
			str4Tc = NumFmtEx.formatDouble(tc4Art);
			if (str4Tc.indexOf(".") == 0) {
				str4Tc = PStr.str("0", str4Tc);
			}
			tmpBonus = PStr.str("bonus:", str4Tc);
			rpars1 = StrEx.fmt(pattern, licheng, str4Tc, tmpBonus);

			buff4pattern.append(split);
			buff4pattern.append(rpars1);
		}

		// 学习中心
		for (Entry<String, Double> entry : map4Royalty.entrySet()) {
			String ali = entry.getKey();
			double val = entry.getValue();
			// 判断是否代理商也是学习中心
			if (isProfit4Agent) {
				if (!StrEx.isEmptyTrim(alipay4Agent)
						&& ali.equals(alipay4Agent)) {
					// 代理商提成
					val += tc4Agent;
					isProfit4Agent = false;
				}
			}
			str4Tc = NumFmtEx.formatDouble(val);
			if (str4Tc.indexOf(".") == 0) {
				str4Tc = PStr.str("0", str4Tc);
			}
			tmpBonus = PStr.str("bonus:", str4Tc);
			rpars1 = StrEx.fmt(pattern, ali, str4Tc, tmpBonus);

			buff4pattern.append(split);
			buff4pattern.append(rpars1);
		}

		// 代理商提成
		if (isProfit4Agent) {
			if (!StrEx.isEmptyTrim(alipay4Agent)) {
				str4Tc = NumFmtEx.formatDouble(tc4Agent);
				if (str4Tc.indexOf(".") == 0) {
					str4Tc = PStr.str("0", str4Tc);
				}
				tmpBonus = PStr.str("bonus:", str4Tc);
				rpars1 = StrEx.fmt(pattern, alipay4Agent, str4Tc, tmpBonus);

				buff4pattern.append(split);
				buff4pattern.append(rpars1);
			}
		}

		profit.royalty_parameters = buff4pattern.toString();

		// 插入数据库
		Orders4profitEntity.insert(profitList);
		return profit;
	}
}
