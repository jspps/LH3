package com.learnhall.logic.controll.client;

import java.io.OutputStream;
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

import com.bowlong.image.ImgEx;
import com.bowlong.third.FastJSON;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Account;
import com.learnhall.db.bean.Adqdepartment;
import com.learnhall.db.bean.Cfgs;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Recordanswer;
import com.learnhall.db.entity.AccountEntity;
import com.learnhall.db.entity.AdcoursesEntity;
import com.learnhall.db.entity.AdqdepartmentEntity;
import com.learnhall.db.entity.CfgsEntity;
import com.learnhall.db.entity.RecordanswerEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.sendPswdByMail;
import com.learnhall.logic.chn.alipayapi.handle.Handle4Alipay;

/**
 * 前台界面-控制器-首页
 * 
 * @author Canyon
 * 
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/doClient")
public class CHomeController {

	/*** 取得图片，并记录图片字 **/
	@RequestMapping("/checkCode")
	public void checkCode(HttpServletResponse response, HttpSession session) {
		try {
			OutputStream out = response.getOutputStream();
			String code = ImgEx.outImgBy(out, 90, 33, 4);
			session.setAttribute(Utls.KeyCodeAdminLg, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*** 取得专业-大分类 **/
	static public void major(ModelMap modelMap) {
		List<Map> listJson = new ArrayList<Map>();
		List<Map> listDepart = new ArrayList<Map>();
		String clzz = "qbkcfl_ico,qbkcfl_ico_b,qbkcfl_ico_c,qbkcfl_ico_d,qbkcfl_ico_e,qbkcfl_ico_f,qbkcfl_ico_g,qbkcfl_ico_h,qbkcfl_ico_i";
		List<String> listClzz = ListEx.toListByComma(clzz, true);
		int lens4Clzz = listClzz.size();
		List<Adqdepartment> list = AdqdepartmentEntity.getAll();

		if (!ListEx.isEmpty(list)) {
			int len = list.size();
			for (int i = 0; i < len; i++) {
				Adqdepartment en = list.get(i);
				String tmpClass = "";
				if (i < lens4Clzz) {
					tmpClass = listClzz.get(i);
				} else {
					tmpClass = listClzz.get(0);
				}
				Map v = en.toBasicMap();
				// 主页上面的大分类的小图标
				v.put("icon", "d0" + (i + 1) + ".png");
				v.put("class1", tmpClass);
				v.put("class2", tmpClass + "_hover");
				listDepart.add(v);

				int departid = en.getDid();
				List<Map> listChilds = AdcoursesEntity.getNmmajors(departid);
				List<Map> retChilds = new ArrayList<Map>();
				if (!ListEx.isEmpty(listChilds)) {
					int lens = listChilds.size();
					for (int j = 0; j < lens; j++) {
						Map tmpMap = listChilds.get(j);
						tmpMap.put("departid", departid);
						retChilds.add(tmpMap);
					}
				}
				v.put("childs", retChilds);
				listJson.add(v);
			}
		}
		String v = "{}";
		try {
			v = FastJSON.toJSONString(listJson);
		} catch (Exception e) {
			e.printStackTrace();
		}

		modelMap.put("departs", listDepart);
		modelMap.put("json", v);
	}

	/*** 首页 **/
	@RequestMapping("/home")
	public String home(HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurTop, 1);
		major(modelMap);
		return "client/home";
	}

	/*** 新手指导 **/
	@RequestMapping("/help")
	public String help(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurTop, 1);
		Cfgs en = CfgsEntity.getByKey(1);
		String v = "";
		if (en == null) {
			v = "还有一种错误就是新手指引花了太多时间在解释交互上（用户又不是产品经理，谁关心你的菜单是抽屉式还是折叠式设计），而不是解释产品的价值。前者是站在自己的角度，后者则是从用户的视角出发。"
					+ "错误三：非常规的交互元素或者动作指令"
					+ "在移动应用上，很多手势和对应的交互效果都是约定俗成了的，比如左右滑动换页、用两个手指的收聚控制图片的缩放；比如在IM软件里，对方的消息出现在左边，自己的出现在右边，等等。"
					+ "偏偏有一些产品经理自作聪明地\"过度设计\"了app，采用一些不常见的自以为独特的手势指令，藏在不知道哪儿的菜单，一些很酷的却没有承载任何功能的元素。这些设计，统统都是\"扰民\"。";
		} else {
			v = en.getValStr();
		}
		modelMap.addAttribute("strval", v);
		return "client/help_guide";
	}

	/*** 服务条款 **/
	@RequestMapping("/clause")
	public String clause(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurTop, 1);
		Cfgs en = CfgsEntity.getByKey(4);
		String v = "";
		if (en == null) {
			v = "还有一种错误就是新手指引花了太多时间在解释交互上（用户又不是产品经理，谁关心你的菜单是抽屉式还是折叠式设计），而不是解释产品的价值。前者是站在自己的角度，后者则是从用户的视角出发。"
					+ "错误三：非常规的交互元素或者动作指令"
					+ "在移动应用上，很多手势和对应的交互效果都是约定俗成了的，比如左右滑动换页、用两个手指的收聚控制图片的缩放；比如在IM软件里，对方的消息出现在左边，自己的出现在右边，等等。"
					+ "偏偏有一些产品经理自作聪明地\"过度设计\"了app，采用一些不常见的自以为独特的手势指令，藏在不知道哪儿的菜单，一些很酷的却没有承载任何功能的元素。这些设计，统统都是\"扰民\"。";
		} else {
			v = en.getValStr();
		}
		modelMap.addAttribute("strval", v);
		return "client/help_clause";
	}

	/*** 学习保障 **/
	@RequestMapping("/ensure")
	public String ensure(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurTop, 1);
		Cfgs en = CfgsEntity.getByKey(2);
		String v = "";
		if (en == null) {
			v = "还有一种错误就是新手指引花了太多时间在解释交互上（用户又不是产品经理，谁关心你的菜单是抽屉式还是折叠式设计），而不是解释产品的价值。前者是站在自己的角度，后者则是从用户的视角出发。"
					+ "错误三：非常规的交互元素或者动作指令"
					+ "在移动应用上，很多手势和对应的交互效果都是约定俗成了的，比如左右滑动换页、用两个手指的收聚控制图片的缩放；比如在IM软件里，对方的消息出现在左边，自己的出现在右边，等等。"
					+ "偏偏有一些产品经理自作聪明地\"过度设计\"了app，采用一些不常见的自以为独特的手势指令，藏在不知道哪儿的菜单，一些很酷的却没有承载任何功能的元素。这些设计，统统都是\"扰民\"。";
		} else {
			v = en.getValStr();
		}
		modelMap.addAttribute("strval", v);
		return "client/help_ensure";
	}

	/*** 支付方式 **/
	@RequestMapping("/helpPay")
	public String helpPay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurTop, 1);
		Cfgs en = CfgsEntity.getByKey(3);
		String v = "";
		if (en == null) {
			v = "还有一种错误就是新手指引花了太多时间在解释交互上（用户又不是产品经理，谁关心你的菜单是抽屉式还是折叠式设计），而不是解释产品的价值。前者是站在自己的角度，后者则是从用户的视角出发。"
					+ "错误三：非常规的交互元素或者动作指令"
					+ "在移动应用上，很多手势和对应的交互效果都是约定俗成了的，比如左右滑动换页、用两个手指的收聚控制图片的缩放；比如在IM软件里，对方的消息出现在左边，自己的出现在右边，等等。"
					+ "偏偏有一些产品经理自作聪明地\"过度设计\"了app，采用一些不常见的自以为独特的手势指令，藏在不知道哪儿的菜单，一些很酷的却没有承载任何功能的元素。这些设计，统统都是\"扰民\"。";
		} else {
			v = en.getValStr();
		}
		modelMap.addAttribute("strval", v);
		return "client/help_pay";
	}

	/*** 全部课程分类 **/
	@RequestMapping("/allClass")
	public String allClass(HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurTop, 3);
		major(modelMap);
		return "client/allClass";
	}

	/*** 登录 **/
	@RequestMapping("/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "redirect:/sigle/login";
	}

	/*** 忘记密码 **/
	@RequestMapping("/forgetPswd")
	public String forgetPasswd(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "client/forgetPassword";
	}

	/*** 通过邮件发送密码 **/
	@RequestMapping("/sendPWByMail")
	public void sendPWByMail(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map pars = Svc.getMapAllParams(request);
		String toEmail = Svc.getString(pars, "toEmail");
		boolean isEmail = TkitValidateCheck.isEmail(toEmail);
		if (isEmail) {
			// 通过邮件名查询出来账号密码
			Account accEn = AccountEntity.getByEmail(toEmail);
			if (accEn != null) {
				String lgid = accEn.getLgid();
				String lgpwd = accEn.getLgpwd();
				sendPswdByMail.sendMail(toEmail, lgid, lgpwd);
				result = Utls
						.tipMap(result, Utls.Status_Success, "发送成功,请注意查收!");
				Utls.writeAndClose(response, result);
				return;
			}
		}

		result = Utls.tipMap(result, Utls.Status_Erro, "邮箱地址不存在! ");
		Utls.writeAndClose(response, result);
	}

	/*** 取得登录界面用着弹出界面来 **/
	@RequestMapping("/login4Alert")
	public String login4Alert(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map mapPars = Utls.getMapAllParams(request);
		String loginedUrl = MapEx.getString(mapPars, "loginedUrl");
		session.setAttribute(SessionKeys.UrlGo2, loginedUrl);
		return "client/login4alert";
	}

	/*** 主界面单击搜索-得到专业的json **/
	@RequestMapping("/req4Search")
	public void req4Search(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map mapPars = Utls.getMapAllParams(request);
		String name = MapEx.getString(mapPars, "name");
		List<Map> list = AdcoursesEntity.getNmmajors(name);
		Map result = new HashMap();
		List<Map> dataList = new ArrayList<Map>();
		if (!ListEx.isEmpty(list)) {
			dataList.addAll(list);
		}
		result = Utls.tipMap(result, Utls.Status_Success, "logining", dataList);
		Utls.writeAndClose(response, result);
	}

	/*** 主界面单击购买考币 **/
	@RequestMapping("/req4BuyKbi")
	public String req4BuyKbi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		int lessKbi = 0;
		if (customer != null) {
			lessKbi = customer.getKbiUse();
		}
		modelMap.addAttribute("kbi", lessKbi);

		Cfgs cfg = CfgsEntity.getByKey(6);
		int kbiRate = 100;
		if (cfg != null) {
			kbiRate = cfg.getValInt();
		}
		Map<Integer, Integer> mapRmbKbi = new HashMap<Integer, Integer>();
		mapRmbKbi.put(10, 10 * kbiRate);
		mapRmbKbi.put(30, 30 * kbiRate);
		mapRmbKbi.put(50, 50 * kbiRate);
		mapRmbKbi.put(100, 100 * kbiRate);
		modelMap.addAttribute("mapRmbKbi", mapRmbKbi);

		return "buyKbi";
	}

	/*** 主界面单击购买考币 **/
	@RequestMapping("/buyKbi")
	public void buyKbi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Customer customer = Utls.getCustomer(session);
		Map result = new HashMap();
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_notLogin, "logining");
		} else {
			Map mapPars = Utls.getMapAllParams(request);
			int rmb = MapEx.getInt(mapPars, "rmb");
			Ref<String> ref = new Ref<String>("");
			String v = Handle4Alipay.handle4Kbi0Ask(3, customer, rmb, "", ref);
			if (ref.val.startsWith("error")) {
				result = Utls.tipMap(result, Utls.Status_Erro, ref.val);
			} else {
				result = Utls.tipMap(result, Utls.Status_Success, "添加中,请支付金额！",
						v);
			}
		}
		Utls.writeAndClose(response, result);
	}

	/*** 最新模考榜单 **/
	@RequestMapping("/newTestList")
	public String newTestList(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		// 查找出并返回到界面上的数据
		List getList = new ArrayList();
		session.setAttribute(SessionKeys.CurTop, 2);

		Map pars = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(pars, "departid");
		String nmMajor = MapEx.getString(pars, "nmMajor");
		String nmLevel = MapEx.getString(pars, "nmLevel");
		String nmSub = MapEx.getString(pars, "nmSub");
		String nmArea = MapEx.getString(pars, "nmArea");

		// 查询出来的Recordanswer排名前10的list
		List<Recordanswer> list = RecordanswerEntity.selectByNewTestList(
				departid, nmMajor, nmLevel, nmSub, nmArea);

		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map enMap = new HashMap();
				Recordanswer en = list.get(i);
				String custName = en.getCustname();
				if (custName.length() >= 16) {
					custName = "匿名";
				}

				int score = en.getScore();
				Date lasttime = en.getLasttime();
				String examTypeName = en.getExamFkExamid().getName();
				if (examTypeName != null) {
					enMap.put("exTpName", examTypeName);
				}

				enMap.put("subName", nmSub + nmLevel);
				enMap.put("custName", custName);
				enMap.put("score", score);
				enMap.put("lastime",
						DateEx.format(lasttime, DateEx.fmt_yyyy_MM_dd_HH_mm_ss));

				if (enMap != null) {
					getList.add(enMap);
				}
			}
		}

		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();// 分类
		modelMap.addAttribute("adqdepartments", adqdepartments);

		modelMap.addAttribute("getList", getList);
		return "client/newTestList";
	}
}
