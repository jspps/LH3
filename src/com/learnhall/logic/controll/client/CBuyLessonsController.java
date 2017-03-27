package com.learnhall.logic.controll.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.bean.Appraise;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.bean.Orders;
import com.learnhall.db.bean.Product;
import com.learnhall.db.entity.AppraiseEntity;
import com.learnhall.db.entity.OrdersEntity;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.PageAppraise;
import com.learnhall.logic.model.PageKinds;
import com.learnhall.logic.model.PageOrders;

/**
 * 前台界面-控制器-课程主页(kind)
 * 
 * @author Canyon
 * 
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/doClient")
public class CBuyLessonsController {
	/*** 购买课程-购买页面 **/
	@RequestMapping("/buyLessonsPage")
	public String kind4Buy(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(mapPars, "kind_kindId");
		modelMap.addAttribute("kind_kindId", kindId);
		if (kindId == 0) {
			return "redirect:home";
		}

		PageKinds pageSieve = Utls.getKinds(session);
		if (pageSieve == null) {
			pageSieve = new PageKinds();
			pageSieve.initListByKindid(kindId);
		}

		Kind kind = pageSieve.getKindById(kindId);
		if (kind == null) {
			return "redirect:home";
		}

		Product product = pageSieve.getProductByKindid(kindId);
		String nmArea = "";
		String nmProduct = kind.getNmKClass();
		Adcourses course = pageSieve.getCourseByKindid(kindId);
		if (course != null) {
			nmArea = course.getNmArea();
			// 专业+层次+科目
			nmProduct = course.getNmMajor() + course.getNmLevel()
					+ course.getNmSub();
		}

		modelMap.addAttribute("product", product);
		modelMap.addAttribute("nmDepart", pageSieve.nmDepart);
		modelMap.addAttribute("nmMajor", pageSieve.nmMajor);
		modelMap.addAttribute("nmProduct", nmProduct);
		modelMap.addAttribute("protection", product.getProtection());
		modelMap.addAttribute("nmArear", nmArea);
		modelMap.addAttribute("curKindId", kindId);
		List<Kind> list = pageSieve.getKindsBy(kindId);
		List<Map> listMap = new ArrayList<Map>();
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Kind enTmp = list.get(i);
				Map tmp = Utls.toBasicMap(enTmp.toBasicMap());
				List<Integer> examtypes = ListEx
						.toListInt(enTmp.getExamtypes());
				List<Map> clist = new ArrayList<Map>();
				for (Integer item : examtypes) {
					Map tb = new HashMap();
					tb.put("clzz", "kczy_list_span");
					switch (item) {
					case 1:
						tb.put("name", "章节练习");
						tb.put("vals", enTmp.numExercises);
						break;
					case 2:
						tb.put("name", "历年真题");
						tb.put("vals", enTmp.numZhenti);
						break;
					case 3:
						tb.put("name", "全真模拟");
						tb.put("vals", enTmp.numSimulation);
						break;
					case 4:
						tb.put("name", "绝胜押题");
						tb.put("vals", enTmp.numVast);
						break;
					case 5:
						tb.put("name", "知识要点");
						tb.put("clzz", "kczy_list_span kczy_list_color");
						tb.put("isBl", true);
						break;
					case 6:
						if (enTmp.getIsHasITMS()) {
							tb.put("name", "ITMS辅助");
							tb.put("clzz", "kczy_list_span kczy_list_color");
						}
						break;
					default:
						break;
					}
					clist.add(tb);
				}
				tmp.put("clist", clist);
				listMap.add(tmp);
			}
		}

		modelMap.addAttribute("list", listMap);
		int lens4Order = 0;
		int lens4Appraise = 0;
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("statusProcess > ", 0);
			params.put("type = ", 0);
			params.put("extra_param like", "'%" + kindId + ",%'");

			lens4Order = OrdersEntity.getCountAllBy(params);

			params.clear();
			params.put("kindid = ", kindId);
			lens4Appraise = AppraiseEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		modelMap.addAttribute("lens4Order", lens4Order);
		modelMap.addAttribute("lens4Appraise", lens4Appraise);

		// 学习中心信息
		String logo = "";
		Learnhub lb = kind.getLearnhubFkLhubid();
		if(lb != null){
			logo = lb.getImg4logo();
		}
//		if(StrEx.isEmpty(logo)){
//			logo = "jsp/imgs/client/63.jpg";
//		}
		modelMap.addAttribute("lhlogo", logo);
		modelMap.addAttribute("lhubid", kind.getLhubid());
		return "client/kind/kindBuy";
	}

	/*** 取得购买记录 **/
	@RequestMapping("/getOrders4Kind")
	public String getOrders4Kind(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(mapPars, "kind_kindId");
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int pageSize = 10;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusProcess > ", 0);
		params.put("type = ", 0);
		params.put("extra_param like", "'%" + kindId + ",%'");

		PageOrders pageOrders = new PageOrders();
		PageEnt<Orders> pageEntOrder = pageOrders.getPage(params, page,
				pageSize);
		modelMap.addAttribute("kind_kindId", kindId);
		modelMap.addAttribute("pageEnt", pageEntOrder);
		return "client/kind/orders4kindBuy";
	}

	/*** 取得评价 **/
	@RequestMapping("/getDiscuss4Kind")
	public String getDiscuss4Kind(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(mapPars, "kind_kindId");
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int pageSize = 10;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("kindid = ", kindId);
		PageAppraise pageAppraise = new PageAppraise();
		PageEnt<Appraise> pageEnt = pageAppraise
				.getPage(params, page, pageSize);

		double rate4Appraise = AppraiseEntity.getAvgRate4Score(kindId);
		double score4Appraise = rate4Appraise * 5;

		// 计算星星数量
		int star = (int) score4Appraise;
		int cell = (int) Math.ceil(score4Appraise);
		int half_star = cell == star ? 0 : 1;
		int no_star = 5 - star - half_star;
		no_star = no_star < 0 ? 0 : no_star;

		modelMap.addAttribute("score4Appraise", score4Appraise);
		modelMap.addAttribute("star", star);
		modelMap.addAttribute("half_star", half_star);
		modelMap.addAttribute("no_star", no_star);

		modelMap.addAttribute("rate4Appraise", rate4Appraise);

		modelMap.addAttribute("kind_kindId", kindId);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "client/kind/appraise4KindBuy";
	}

	/*** 取得消费者保障 **/
	@RequestMapping("/getProtection")
	public String getProtection(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(mapPars, "kind_kindId");
		PageKinds pageSieve = Utls.getKinds(session);
		String protection = "";
		if (pageSieve != null) {
			Product product = pageSieve.getProductByKindid(kindId);
			if (product != null) {
				protection = product.getProtection();
			}
		}
		modelMap.addAttribute("protection", protection);
		return "client/kind/protection4kindBuy";
	}
}
