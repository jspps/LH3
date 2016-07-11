package com.learnhall.logic.controll.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.lang.StrEx;
import com.bowlong.security.MD5;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Account;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.bean.Agent;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Shoppingcart;
import com.learnhall.db.entity.AccountEntity;
import com.learnhall.db.entity.AgentEntity;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.ShoppingcartEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.chn.alipayapi.handle.Handle4Alipay;
import com.learnhall.logic.model.PageKinds;

/**
 * 前台界面-控制器-购物车
 * 
 * @author Canyon
 * 
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/doClient")
public class ShopCartController {

	static private Map<Integer, Map> getMap4Kind(HttpSession session) {
		return (Map<Integer, Map>) session.getAttribute(SessionKeys.ShopCutMap);
	}

	static private long getSes4ShopCart(HttpSession session) {
		Object ses = session.getAttribute(SessionKeys.ShopCartOneOrderSession);
		if (ses == null)
			return 0;
		return (long) ses;
	}

	static private String getCode4ShopCart(HttpSession session) {
		Object ses = session.getAttribute(SessionKeys.ShopCartCode);
		if (ses == null)
			return "";
		return ses.toString();
	}

	static private void setCode4ShopCart(HttpSession session, String code) {
		if (StrEx.isEmptyTrim(code)) {
			session.removeAttribute(SessionKeys.ShopCartCode);
		} else {
			session.setAttribute(SessionKeys.ShopCartCode, code);
		}
	}

	static Map<Integer, Map> initMap4KindByDB(HttpSession session) {
		Map<Integer, Map> map = getMap4Kind(session);
		if (map != null) {
			return map;
		}
		map = new HashMap<Integer, Map>();
		int customerid = Utls.getCustomerId(session);
		List<Shoppingcart> list = ShoppingcartEntity
				.getByCustomerid(customerid);

		Map<Integer, Adcourses> mapCourse = new HashMap<Integer, Adcourses>();

		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Shoppingcart en = list.get(i);
				int shopid = en.getId();
				int kindid = en.getKindid();
				Kind ken = KindEntity.getByKey(kindid);
				if (ken == null)
					continue;

				Map map4Base = ken.toBasicMap();
				String nmKind = "";
				Adcourses couses = mapCourse.get(ken.getCoursid());
				if (couses == null) {
					couses = ken.getAdcoursesFkCoursid();
					mapCourse.put(ken.getCoursid(), couses);
				}

				nmKind = couses.getNmMajor() + couses.getNmLevel()
						+ couses.getNmSub();

				map4Base.put("course", couses);
				map4Base.put("nmKind", nmKind);

				// 产品
				map4Base.put("product", ken.getProductFkProductid());

				map.put(shopid, map4Base);
			}
		}

		session.setAttribute(SessionKeys.ShopCutMap, map);
		return map;
	}

	static public void reShopCart(int kindId, HttpSession session,
			ModelMap modelMap, boolean isAdd, String agentCode, int shopcartid,
			long ses4ShopCart) {
		int customerid = Utls.getCustomerId(session);
		Map<Integer, Map> map = initMap4KindByDB(session);

		if (isAdd) {
			if (kindId > 0 && ses4ShopCart > 0) {
				long preSC = getSes4ShopCart(session);
				if (ses4ShopCart != preSC) {
					PageKinds pageSieve = Utls.getKinds(session);
					if (pageSieve != null) {
						Kind kind = pageSieve.getKindById(kindId);
						if (kind != null) {
							Shoppingcart shoppingcart = Shoppingcart
									.newShoppingcart(0, customerid, kindId,
											agentCode);
							shoppingcart = shoppingcart.insert();
							if (shoppingcart != null) {
								Map map4Base = kind.toBasicMap();
								String nmKind = "";

								Adcourses couses = pageSieve
										.getCourseByKindid(kindId);
								if (couses != null) {
									nmKind = couses.getNmMajor();

									// nmKind = PStr.str(couses.getNmMajor(),
									// couses.getNmLevel(),
									// couses.getNmSub());
								}

								map4Base.put("course", couses);
								map4Base.put("nmKind", nmKind);

								// 产品
								map4Base.put("product",
										kind.getProductFkProductid());

								map.put(shoppingcart.getId(), map4Base);

								session.setAttribute(
										SessionKeys.ShopCartOneOrderSession,
										ses4ShopCart);
							}
						}
					}
				}
			}
		} else {
			if (map.containsKey(shopcartid)) {
				map.remove(shopcartid);
				// 删除数据库中的购物车套餐
				ShoppingcartEntity.delete(shopcartid);
			}
		}

		List<Map> list = ListEx.valueToList(map);
		int lens = map.size();
		double sumDis = 0;
		double sumPri = 0;
		for (int i = 0; i < lens; i++) {
			Map en = list.get(i);
			sumDis += MapEx.getDouble(en, "discount");
			sumPri += MapEx.getDouble(en, "price");
		}
		modelMap.put("lens", lens);
		modelMap.put("sumDis", sumDis);
		modelMap.put("sumPri", sumPri);
	}

	/*** 购买课程-购物车 **/
	@RequestMapping("/shopCart")
	public String shopCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(mapPars, "kindid");
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			return login4OldNew(request, response, session, modelMap);
		}

		String agentCode = MapEx.getString(mapPars, "agentCode");
		long ses4ShopCart = MapEx.getLong(mapPars, "ses4ShopCart");
		reShopCart(kindId, session, modelMap, true, agentCode, 0, ses4ShopCart);
		return "client/kind/shopCart";
	}

	/*** 购买课程-全部课程 **/
	@RequestMapping("/submitShopCart2Buy")
	public String submitShopCart2Buy(HttpSession session, ModelMap modelMap) {
		Map<Integer, Map> map = getMap4Kind(session);
		if (MapEx.isEmpty(map)) {
			session.setAttribute("tipUrl", "client");
			session.setAttribute("tip", "你没有选择任何东西！");
			return "/tip";
		}

		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			Utls.setUrlPre(session, "/client/shopCart");
			return "redirect:login";
		}

		List<Map> list = ListEx.valueToList(map);
		String code = getCode4ShopCart(session);
		int lens = list.size();
		List<Kind> list4Kinds = new ArrayList<Kind>();
		for (int i = 0; i < lens; i++) {
			Map en = list.get(i);
			Kind kind = Kind.mapTo(en);
			if (kind == null)
				continue;
			list4Kinds.add(kind);
		}

		Ref<String> ref = new Ref<String>("");
		String v = Handle4Alipay.handle4Kinds(customer, list4Kinds, code, ref);
		if (ref.val.startsWith("error")) {
			return "redirect:/sigle/learnBuy";
		} else {
			modelMap.addAttribute("body", v);
			return "alipayapi";
		}
	}

	/*** 购买课程-购物车-删除 **/
	@RequestMapping("/deleteShopCart")
	public String deleteShopCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int kindid = MapEx.getInt(mapPars, "kindid");
		int shopcartid = MapEx.getInt(mapPars, "shopcartid");
		reShopCart(kindid, session, modelMap, false, "", shopcartid, 0);
		return "client/kind/shopCart";
	}

	/*** 立即购买登录(页面跳转) **/
	@RequestMapping("/login4OldNew")
	public String login4OldNew(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map pars = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(pars, "kindid");
		modelMap.addAttribute("kindId", kindId);
		return "client/kind/login4OldNew";
	}

	/*** 立即购买登录（验证） **/
	@RequestMapping("/doLogin4OldNew")
	public void doLogin4OldNew(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map pars = Svc.getMapAllParams(request);
		String olduserid = MapEx.getString(pars, "olduserid");
		String olduserpwd = MapEx.getString(pars, "olduserpwd");
		String verifyCode = MapEx.getString(pars, "verifyCode");
		String vrfCode = (String) session.getAttribute(Utls.KeyCodeAdminLg);
		if (!verifyCode.equals(vrfCode)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证码错误！");
			Utls.writeAndClose(response, result);
			return;
		}

		Account accEn = AccountEntity.getByLgid(olduserid);
		if (accEn == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "用户不存在！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!accEn.getLgpwd().equals(olduserpwd)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "密码不正确！");
			Utls.writeAndClose(response, result);
			return;
		}

		Customer user = CustomerEntity.getByAccountid(accEn.getId());
		Utls.saveCustomer(session, user);

		result = Utls.tipMap(result, Utls.Status_Success, "登录成功！");
		Utls.writeAndClose(response, result);
	}

	/*** 新学员注册购买(验证) **/
	@RequestMapping("/register4NewCust")
	public void register4NewCust(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map pars = Svc.getMapAllParams(request);
		String newUserId = MapEx.getString(pars, "newUserId");
		String newUserPwd1 = MapEx.getString(pars, "newUserPwd1");
		String newUserPwd2 = MapEx.getString(pars, "newUserPwd2");
		String newUserMail = MapEx.getString(pars, "newUserMail");
		String newVerifyCode = MapEx.getString(pars, "newVerifyCode");
		String vrfCode = (String) session.getAttribute(Utls.KeyCodeAdminLg);
		if (!newVerifyCode.equals(vrfCode)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "验证码错误！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (newUserId.length() < 1) {
			result = Utls.tipMap(result, Utls.Status_Erro, "请输入用户名！");
			Utls.writeAndClose(response, result);
			return;
		}

		Account accEn = AccountEntity.getByLgid(newUserId);
		if (accEn != null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "用户名已存在！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (newUserPwd1.length() < 6) {
			result = Utls.tipMap(result, Utls.Status_Erro, "密码长度不够！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!newUserPwd1.equals(newUserPwd2)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "密码验证错误，请输入相同密码！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (newUserMail.length() < 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "请输入邮箱地址！ ");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!TkitValidateCheck.isEmail(newUserMail)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "邮箱地址错误！ ");
			Utls.writeAndClose(response, result);
			return;
		}

		accEn = AccountEntity.getByEmail(newUserMail);
		if (accEn != null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该邮箱已经注册！ ");
			Utls.writeAndClose(response, result);
			return;
		}

		String md5_32 = MD5.MD5UUIDStime();
		Date nowDate = new Date();
		accEn = Account.newAccount(0, newUserId, md5_32, newUserMail,
				newUserPwd1, 4, 0, nowDate, nowDate);
		accEn = accEn.insert();
		if (accEn == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "数据异常，新增失败！ ");
			Utls.writeAndClose(response, result);
			return;
		}

		double money = 0;
		Customer cusEn = Customer.newCustomer(0, accEn.getId(), md5_32, 0, 0,
				newUserMail, "请选择", "请选择", "请选择",
				"jsp/imgs/onesigle/usermanange/bg.jpg", "", money, money,
				md5_32, "", "", false, "", "");
		cusEn = cusEn.insert();
		Utls.saveCustomer(session, cusEn);

		result = Utls.tipMap(result, Utls.Status_Success, "登录成功！");
		Utls.writeAndClose(response, result);
	}

	/*** 使用推荐号(页面跳转) **/
	@RequestMapping("/goRecommendCodeUI")
	public String goRecommendCodeUI(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map pars = Svc.getMapAllParams(request);
		int shopcartid = MapEx.getInt(pars, "shopcartid");
		modelMap.addAttribute("shopcartid", shopcartid);
		return "client/kind/recommendCode";
	}

	/*** 改变购物车的商品推荐号 **/
	@RequestMapping("/modifyShopCartRdCode")
	public void modifyShopCartRdCode(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map<Integer, Map> map4kind = getMap4Kind(session);
		if (MapEx.isEmpty(map4kind)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "出现错误，购物车商品为空了！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map pars = Svc.getMapAllParams(request);
		String code = MapEx.getString(pars, "code");
		// int shopcartid = MapEx.getInt(pars, "shopcartid");
		boolean isSet = MapEx.getBoolean(pars, "isSet");
		code = isSet ? code : "";

		if (isSet) {
			if (StrEx.isEmptyTrim(code)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "推荐号不存在！");
				Utls.writeAndClose(response, result);
				return;
			}

			Agent agent = AgentEntity.getByCode(code);
			if (agent == null) {
				result = Utls
						.tipMap(result, Utls.Status_Erro, "推荐号所对应的代理商不存在！");
				Utls.writeAndClose(response, result);
				return;
			}

			if (agent.getStatus() == 1) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"推荐号所对应的代理商已经被禁用！");
				Utls.writeAndClose(response, result);
				return;
			}

			if (agent.getExamineStatus() != 3) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"推荐号所对应的代理商尚未通过审核！");
				Utls.writeAndClose(response, result);
				return;
			}

			if (!agent.getIsVerifyAlipay()) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"推荐号所对应的代理商的支付宝尚未验证！");
				Utls.writeAndClose(response, result);
				return;
			}

			boolean isBefore = DateEx.isBefore(agent.getEndtime());
			if (isBefore) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"推荐号所对应的代理商尚已经过期！");
				Utls.writeAndClose(response, result);
				return;
			}
		}

		setCode4ShopCart(session, code);

		result = Utls.tipMap(result, Utls.Status_Success, "成功! ");
		Utls.writeAndClose(response, result);
	}
}
