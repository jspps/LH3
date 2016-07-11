package com.learnhall.logic.controll.onesigle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Appraise;
import com.learnhall.db.bean.Boughtkinds;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Recordanswer;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.controll.client.KExamController;
import com.learnhall.logic.model.PageAppraise;
import com.learnhall.logic.model.PageBuyCourses;
import com.learnhall.logic.model.PageOpenKind4Cust;
import com.learnhall.logic.model.PageProduct;
import com.learnhall.logic.model.PageRecordAnswer;

/**
 * 个人中心-控制器:学习模块
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes" })
@Controller
@RequestMapping("/doSigle")
public class SigleLearnController {
	/*** 模考记录 1 **/
	@RequestMapping("/examRecord")
	public String learnExamRecord(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurSigleTop, 1);
		Map paramsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(paramsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;

		int custid = Utls.getCustomerId(session);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerid", custid);

		PageRecordAnswer pageWrap = new PageRecordAnswer();
		PageEnt<Recordanswer> pageEnt = pageWrap.getPage(params, page, 10);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("menuChild", 1);
		return "onesigle/learnExamRecord";
	}

	/*** 模考记录 -- 进入考试 **/
	@RequestMapping("/go2Exam")
	public String go2Exam(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		Map mapParms = Svc.getMapAllParams(request);
		KExamController.initPageKExam(request, session, true);
		int examid = MapEx.getInt(mapParms, "examid");
		return "redirect:/client/examing?examid=" + examid;
	}

	/*** 已购课程 2 **/
	@RequestMapping("/learnBuy")
	public String learnBuy(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {

		session.setAttribute(SessionKeys.CurSigleTop, 1);

		Map paramsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(paramsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int custid = Utls.getCustomerId(session);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerid", custid);

		PageBuyCourses buyCourse = new PageBuyCourses();
		PageEnt<Boughtkinds> pageEnt = buyCourse.getPage(params, page, 10);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("menuChild", 2);

		// 添加是否清楚购物车
		boolean isClearShopCut = MapEx.getBoolean(paramsMap, "isClearShopCut");
		if (isClearShopCut) {
			session.removeAttribute(SessionKeys.ShopCutMap);
		}
		return "onesigle/learnBuy";
	}

	/*** 我的点评 4 **/
	@RequestMapping("/comment")
	public String learnComment(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		modelMap.addAttribute("menuChild", 4);
		Map mapPars = Svc.getMapAllParams(request);
		int custid = Utls.getCustomerId(session);
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int pageSize = 10;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerid", " = " + custid);
		PageAppraise pageAppraise = new PageAppraise();
		PageEnt<Appraise> pageEnt = pageAppraise
				.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "onesigle/learnComment";
	}

	/*** 点评套餐 **/
	@RequestMapping("/appraise4Kind")
	public void appraise4Kind(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Customer customer = Utls.getCustomer(session);
		Map result = new HashMap();
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "帐号为空");
		} else {
			Map parsMap = Utls.getMapAllParams(request);
			String appraisetext = MapEx.getString(parsMap, "txt4Appraise");
			int kindid = MapEx.getInt(parsMap, "kindid");
			String nameKind = MapEx.getString(parsMap, "name");
			int lhubid = MapEx.getInt(parsMap, "lhubid");
			int score_4_kind = MapEx.getInt(parsMap, "score_4_kind");
			String custname = customer.getName();
			int customerid = customer.getId();
			int status = 0;
			Date createtime = DateEx.nowDate();

			int lens = StrEx.getLens(custname);
			if (lens >= 16) {
				custname = "匿名";
			}

			Appraise appraise = Appraise.newAppraise(0, appraisetext, kindid,
					customerid, status, createtime, custname, nameKind, "",
					lhubid, score_4_kind);
			appraise.insert();

			result = Utls.tipMap(result, Utls.Status_Success, "点评成功!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 推荐课程 3 **/
	@RequestMapping("/learnRecommend")
	public String learnRecommend(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		Map paramsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(paramsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRecommend", true);
		params.put("examineStatus", 3);

		PageProduct pageWrap = new PageProduct();
		PageEnt<Map> pageEnt = pageWrap.getPage(params, page, 10);
		pageWrap.resetList4CouresKinds(pageEnt.getListPages());

		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("menuChild", 3);
		return "onesigle/learnRecommend";
	}

	/*** 我的体验课程 5 **/
	@RequestMapping("/learnExperience")
	public String learnExperience(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		modelMap.addAttribute("menuChild", 5);
		Map mapPars = Svc.getMapAllParams(request);
		int custid = Utls.getCustomerId(session);
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		int pageSize = 10;
		page = page < 1 ? 1 : page;
		Map<String, Object> params = new HashMap<String, Object>();

		params.clear();
		params.put("status != ", 1);
		params.put("customerid = ", custid);

		PageOpenKind4Cust pageCust = new PageOpenKind4Cust();
		PageEnt<Map> pageEnt = pageCust.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "onesigle/learnExperience";
	}
}
