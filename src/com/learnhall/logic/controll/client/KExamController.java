package com.learnhall.logic.controll.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Boughtkinds;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Exam;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.bean.Openkind4customer;
import com.learnhall.db.entity.BoughtkindsEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.LearnhubEntity;
import com.learnhall.db.entity.Openkind4customerEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.PageKExam;

/**
 * 前台界面-控制器-套餐,产品
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doClient")
public class KExamController {
	Map<String, Object> paramsReq = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 12;

	static public PageKExam getKExam(HttpSession session) {
		return (PageKExam) session.getAttribute(SessionKeys.PageKExam);
	}

	/*** 初始化分页的试卷对象 **/
	public static PageKExam initPageKExam(HttpServletRequest request,
			HttpSession session, boolean isReInit) {
		session.removeAttribute(SessionKeys.IsTestExam);
		boolean isNew = false;
		PageKExam pageExam = getKExam(session);
		if (pageExam == null) {
			pageExam = new PageKExam();
			isNew = true;
		}

		Map map = Svc.getMapAllParams(request);
		int kindId = MapEx.getInt(map, "examin_kindId");
		if (isNew && kindId == 0) {
			session.setAttribute(SessionKeys.PageKExam, pageExam);
			return pageExam;
		}

		Kind kind = pageExam.kind;
		
		boolean isReLoadKind = (kind == null && kindId > 0)
				|| (kind != null && kindId != kind.getId());

		if (isReLoadKind) {
			kind = KindEntity.getByKey(kindId);
		}

		boolean isInit = pageExam.initList(kind, isReInit);
		session.setAttribute(SessionKeys.PageKExam, pageExam);

		boolean isBuyed = pageExam.isBuyed;

		if (isInit && kind != null) {
			Customer customer = Utls.getCustomer(session);
			if (customer != null) {
				int cusid = customer.getId();
				kindId = kind.getId();
				Boughtkinds enbouk = BoughtkindsEntity.getByCustomeridKindid(
						cusid, kindId);
				if (enbouk != null) {
					isBuyed = DateEx.isAfter(enbouk.getValidtime(),
							DateEx.nowDate());
				}

				if (!isBuyed) {
					// 线下开通操作
					Openkind4customer enCust = Openkind4customerEntity
							.getByCustomeridKindid(cusid, kindId);
					if (enCust != null) {
						// 判断是否开通了
						int custStatus = enCust.getStatus();
						Date nwDate = DateEx.nowDate();
						switch (custStatus) {
						case 0:
							int v = enCust.getValidity();
							if (v == 0) {
								v = kind.getValidity();
							}
							nwDate = DateEx.addDay(nwDate, v);
							enCust.setValidtime(nwDate);
							enCust.setStatus(2);
							enCust.update();
							isBuyed = true;
							break;
						case 2:
							isBuyed = DateEx.isBefore(nwDate,
									enCust.getValidtime());
							break;
						default:
							break;
						}
					}
				}
			}
		}

		pageExam.isBuyed = isBuyed;
		Utls.setIsTestExam(session, !isBuyed);
		return pageExam;
	}

	/*** 知识要点 1 **/
	@RequestMapping("/knowledgePoints")
	public String knowledgePoints(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		PageKExam pageExam = initPageKExam(request, session, false);
		if (pageExam.kind == null)
			return "redirect:home";

		modelMap.put("kind", pageExam.kind);
		modelMap.put("isBuy", pageExam.isBuyed);
		modelMap.put("curMenu4kexam", 1);

		Utls.setUrlPre(session, "/client/knowledgePoints");
		return "client/kexam/knowledgePoints";
	}

	/*** 章节练习 2 **/
	@RequestMapping("/practiceChapters")
	public String practiceChapters(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		paramsReq.put("type", PageKExam.zjlx);
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		if (page == 0) {
			page = 1;
		}
		PageKExam pageExam = initPageKExam(request, session, false);
		if (pageExam.kind == null)
			return "redirect:home";
		PageEnt<Exam> pageEnt = pageExam.getPage(paramsReq, page, pageSize);

		modelMap.put("kind", pageExam.kind);
		modelMap.put("isBuy", pageExam.isBuyed);
		modelMap.put("pageEnt", pageEnt);
		modelMap.put("curMenu4kexam", 2);

		modelMap.put("map4num", pageExam.map4Num);

		Utls.setUrlPre(session, "/client/practiceChapters");
		return "client/kexam/practiceChapters";
	}

	/*** 历年真题 3 **/
	@RequestMapping("/historyTopics")
	public String historyTopics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		paramsReq.put("type", PageKExam.lnzt);
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page <= 0 ? 1 : page;

		PageKExam pageExam = initPageKExam(request, session, true);
		if (pageExam.kind == null)
			return "redirect:home";
		PageEnt<Exam> pageEnt = pageExam.getPage(paramsReq, page, pageSize);

		modelMap.put("kind", pageExam.kind);
		modelMap.put("isBuy", pageExam.isBuyed);
		modelMap.put("pageEnt", pageEnt);
		modelMap.put("curMenu4kexam", 3);

		modelMap.put("map4num", pageExam.map4Num);

		Utls.setUrlPre(session, "/client/historyTopics");
		return "client/kexam/historyTopics";
	}

	/*** 全真模拟 4 **/
	@RequestMapping("/realSimulations")
	public String realSimulations(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		paramsReq.put("type", PageKExam.qzmn);
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		if (page == 0) {
			page = 1;
		}
		PageKExam pageExam = initPageKExam(request, session, false);
		if (pageExam.kind == null)
			return "redirect:home";
		PageEnt<Exam> pageEnt = pageExam.getPage(paramsReq, page, pageSize);

		modelMap.put("kind", pageExam.kind);
		modelMap.put("isBuy", pageExam.isBuyed);
		modelMap.put("pageEnt", pageEnt);
		modelMap.put("curMenu4kexam", 4);

		modelMap.put("map4num", pageExam.map4Num);

		Utls.setUrlPre(session, "/client/realSimulations");
		return "client/kexam/realSimulations";
	}

	/*** 绝胜押题 5 **/
	@RequestMapping("/winTopics")
	public String winTopics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		paramsReq.put("type", PageKExam.jsyt);
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		if (page == 0) {
			page = 1;
		}
		PageKExam pageExam = initPageKExam(request, session, false);
		if (pageExam.kind == null)
			return "redirect:home";
		PageEnt<Exam> pageEnt = pageExam.getPage(paramsReq, page, pageSize);

		modelMap.put("kind", pageExam.kind);
		modelMap.put("isBuy", pageExam.isBuyed);
		modelMap.put("pageEnt", pageEnt);
		modelMap.put("curMenu4kexam", 5);

		modelMap.put("map4num", pageExam.map4Num);

		Utls.setUrlPre(session, "/client/winTopics");
		return "client/kexam/winTopics";
	}
	
	/*** 打印界面 **/
	@RequestMapping("/printView")
	public String printView(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session)
			throws Exception {
		
		PageKExam pageExam = initPageKExam(request, session, false);
		if (pageExam.kind == null)
			return "redirect:home";
		
		Map map = Svc.getMapAllParams(request);
		
		int examid = MapEx.getInt(map, "unqid");
		Exam en = pageExam.getExam(examid);
		if (en == null) {
			return "redirect:home";
		}
		
		Learnhub lhub = LearnhubEntity.getByKey(en.getLhubid());
		modelMap.addAttribute("exam", en);
		modelMap.addAttribute("lhub", lhub);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("examid", "=" + examid);
		params.put("status", "!= 1");
		params.put("parentid", "= 0");
		int lens = ExamCatalogAndListQuestion.getExamCatalogs(en, modelMap);
		modelMap.addAttribute("lens", lens);
		
		modelMap.addAttribute("details", ExamCatalogAndListQuestion.getExamDetailsByExamid(examid));
		return "client/printView";
	}

}
