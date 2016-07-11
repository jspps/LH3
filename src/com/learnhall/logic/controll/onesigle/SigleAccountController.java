package com.learnhall.logic.controll.onesigle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Exchangermb;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Orders;
import com.learnhall.db.bean.Recordfee4customer;
import com.learnhall.db.bean.Recordkbi4customer;
import com.learnhall.db.bean.Shoppingcart;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.db.entity.ExchangermbEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.OrdersEntity;
import com.learnhall.db.entity.ShoppingcartEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.chn.alipayapi.handle.Handle4Alipay;
import com.learnhall.logic.controll.client.ShopCartController;
import com.learnhall.logic.model.PageExchangeRmb;
import com.learnhall.logic.model.PageKbiModel;
import com.learnhall.logic.model.PageRmbModel;

/**
 * 个人中心-控制器:帐户模块
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doSigle")
public class SigleAccountController {

	/*** 我的考币 1 **/
	@RequestMapping("/accCoin")
	public String accCoin(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null)
			return "redirect:go2LoginView";
		session.setAttribute(SessionKeys.CurSigleTop, 3);
		PageKbiModel pageKbi = (PageKbiModel) session
				.getAttribute(SessionKeys.PageMyKbi);
		if (pageKbi == null) {
			pageKbi = new PageKbiModel();
		}

		int curCustomerId = customer.getId();
		pageKbi.initDataByCid(curCustomerId);
		session.setAttribute(SessionKeys.PageMyKbi, pageKbi);

		Map paramsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(paramsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int type = MapEx.getInt(paramsMap, "type");
		type = type < 1 ? 1 : type;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		PageEnt<Recordkbi4customer> pageEnt = pageKbi.getPage(params, page, 10);
		modelMap.addAttribute("cus", customer);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("menuChild", 1);
		return "onesigle/accCoin";
	}

	/*** 资金流水 2 **/
	@RequestMapping("/accFunds")
	public String accFunds(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		Map mapPars = Svc.getMapAllParams(request);
		boolean isReLogin = MapEx.getBoolean(mapPars, "isReLogin");
		int curtid = Utls.getCustomerId(session);
		if (isReLogin) {
			curtid = MapEx.getInt(mapPars, "curtid");
			customer = CustomerEntity.getByKey(curtid);
			if (customer != null) {
				Utls.saveCustomer(session, customer);
			}
		}

		if (customer == null)
			return "redirect:go2LoginView";

		PageRmbModel pageAcc = (PageRmbModel) session
				.getAttribute(SessionKeys.PageMyRMB);
		if (pageAcc == null) {
			pageAcc = new PageRmbModel();
		}

		pageAcc.initDataByCid(curtid);
		session.setAttribute(SessionKeys.PageMyRMB, pageAcc);

		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int type = MapEx.getInt(mapPars, "type");
		type = type < 1 ? 1 : type;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		PageEnt<Recordfee4customer> pageEnt = pageAcc.getPage(params, page, 10);
		modelMap.addAttribute("cus", customer);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("menuChild", 2);
		return "onesigle/accFunds";
	}

	/*** 推荐号成交 3 **/
	@RequestMapping("/accRecommend")
	public String accRecommend(ModelMap modelMap) {
		modelMap.addAttribute("menuChild", 3);
		return "onesigle/accRecommend";
	}

	/*** 我的购物车 4 **/
	@RequestMapping("/accShopCart")
	public String accShopCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		ShopCartController.reShopCart(0, session, modelMap, true, "", 0, 0);
		modelMap.addAttribute("menuChild", 4);
		return "onesigle/accShopCart";
	}

	@RequestMapping("/deleteShopCart")
	public String deleteShopCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int shopcartid = MapEx.getInt(mapPars, "shopcartid");
		ShopCartController.reShopCart(0, session, modelMap, false, "",
				shopcartid, 0);
		return "onesigle/accShopCart";
	}

	@RequestMapping("/subOneShopCart")
	public void subOneShopCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int shopcartid = MapEx.getInt(mapPars, "shopcartid");
		Shoppingcart enShop = ShoppingcartEntity.getByKey(shopcartid);
		if (enShop != null) {
			Kind en = KindEntity.getByKey(enShop.getKindid());
			Customer customer = Utls.getCustomer(session);

			List<Kind> list4Kinds = new ArrayList<Kind>();
			list4Kinds.add(en);
			String code = enShop.getAgentCode();

			Ref<String> ref = new Ref<String>("");
			String v = Handle4Alipay.handle4Kinds(customer, list4Kinds, code,
					ref);

			result = Utls.tipMap(result, Utls.Status_Success, "购买成功", v);
		} else {
			result = Utls.tipMap(result, Utls.Status_Erro, "购买商品失败！");
		}
		ShopCartController.reShopCart(0, session, modelMap, false, "",
				shopcartid, 0);
		Utls.writeAndClose(response, result);
	}

	/*** 登记支付宝账号 5 **/
	@RequestMapping("/accRecordAlipay")
	public String accRecordAlipay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null)
			return "redirect:go2LoginView";

		modelMap.addAttribute("customer", customer);
		modelMap.addAttribute("menuChild", 5);
		return "onesigle/accRecordAlipay";
	}

	/*** 验证支付宝帐号 **/
	@RequestMapping("/accVerifyAliapy")
	public void accVerifyAliapy(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"验证失败，Session失效，请重新登录！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map mapPars = Svc.getMapAllParams(request);

		String alipay = MapEx.getString(mapPars, "alipay");
		String alipay_name = MapEx.getString(mapPars, "alipay_name");
		double alipay_money = MapEx.getDouble(mapPars, "alipay_money");
		if (alipay_money <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证失败，金额小于0.1元！");
			Utls.writeAndClose(response, result);
			return;
		}
		if (StrEx.isEmpty(alipay)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证失败，支付宝帐号为空！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (StrEx.isEmpty(alipay_name)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证失败，真实姓名为空！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (customer.getIsVerifyAlipay()) {
			customer.setBackAlipay(customer.getAlipay());
			customer.setBackAlipayName(customer.getAlipayRealName());
		}
		
		customer.setAlipay(alipay);
		customer.setAlipayRealName(alipay_name);
		customer.setIsVerifyAlipay(false);
		customer.update();

		String v = Handle4Alipay.handle4VerifyAlipay(5, customer.getId(),
				customer.getName(), alipay_money);

		result = Utls.tipMap(result, Utls.Status_Success, "界面跳转中", v);
		Utls.writeAndClose(response, result);
	}

	/*** 处理支付宝验证回调 **/
	@RequestMapping("/handleAlipayBack")
	public String handleAlipayBack(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int orderid = MapEx.getInt(mapPars, "orderid");
		double money = 0;
		Customer customer = Utls.getCustomer(session);
		if (orderid > 0) {
			Orders order = OrdersEntity.getByKey(orderid);
			if (order != null) {
				money = order.getRealprice();
				if (customer == null) {
					customer = CustomerEntity.getByKey(order.getMakerid());
					if (customer != null) {
						Utls.saveCustomer(session, customer);
					}
				}
			}
		}

		if (money <= 0 || customer == null) {
			return "redirect:go2LoginView";
		}

		return "redirect:/sigle/accRecordAlipay";
	}

	/*** 提现 6 - 提现界面 1 **/
	@RequestMapping("/accCash")
	public String accCash(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null)
			return "redirect:go2LoginView";

		modelMap.addAttribute("menuChild", 6);
		modelMap.addAttribute("customer", customer);
		return "onesigle/accCash";
	}

	/*** 申请-提取现金 **/
	@RequestMapping("/accAppayExchangeRmb")
	public void accAppayExchangeRmb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"验证失败，Session失效，请重新登录！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!customer.getIsVerifyAlipay()) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"验证失败，该帐号的支付宝尚未通过测试！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (StrEx.isEmpty(customer.getAlipayRealName())) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"验证失败，该帐号的支付宝真实姓名为空了！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map mapPars = Svc.getMapAllParams(request);

		String reason = MapEx.getString(mapPars, "reason");
		double monyeApply = MapEx.getDouble(mapPars, "monyeApply");
		if (monyeApply < 20) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证失败，金额小于20元！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (monyeApply > 300) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证失败，金额超过300元！");
			Utls.writeAndClose(response, result);
			return;
		}

		String status = Handle4Alipay.createExchangeRmb(0, customer.getId(),
				monyeApply, reason);
		if (StrEx.isSame(status, Handle4Alipay.SUCCESS)) {
			Utls.resetCustomer(session, customer);
			result = Utls.tipMap(result, Utls.Status_Success, "申请成功");
		} else {
			result = Utls.tipMap(result, Utls.Status_Erro, status);
		}
		Utls.writeAndClose(response, result);
	}

	/*** 提现 6 - 提现记录 2 **/
	@RequestMapping("/accCashRecord")
	public String accCashRecord(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null)
			return "redirect:go2LoginView";

		Map mapPars = Svc.getMapAllParams(request);

		PageExchangeRmb pageWrap = new PageExchangeRmb();
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", " = 0");
		params.put("makerid", PStr.str(" = ", customer.getId()));

		PageEnt<Exchangermb> pageEnt = pageWrap.getPage(params, page, 10);
		modelMap.addAttribute("menuChild", 6);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "onesigle/accCashRecord";
	}
	
	/*** 取消-申请提取现金 **/
	@RequestMapping("/cancelAppayERmb")
	public void cancelAppayERmb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map mapPars = Svc.getMapAllParams(request);

		int pars_id = MapEx.getInt(mapPars, "pars_id");
		if (pars_id <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "取消申请失败，参数小于0了！");
			Utls.writeAndClose(response, result);
			return;
		}
		
		Exchangermb exchangermb = ExchangermbEntity.getByKey(pars_id);
		if (exchangermb == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "取消申请失败,对象为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (exchangermb.getStatusOpt() != 0) {
			result = Utls
					.tipMap(result, Utls.Status_Erro, "取消申请失败,对象状态已经改变过了!");
			Utls.writeAndClose(response, result);
			return;
		}
		
		String desc = exchangermb.getReason();
		desc = PStr.str("取消提现:",desc);
		
		exchangermb.setReason(desc);
		exchangermb.setStatusOpt(1);
		exchangermb.setLasttime(DateEx.nowDate());
		exchangermb.update();
		result = Utls.tipMap(result, Utls.Status_Success, "取消申请提现成功,请等待处理!");
		Utls.writeAndClose(response, result);
	}
}
