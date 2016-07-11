package com.learnhall.logic.controll.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.entity.LearnhubEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.PageKinds;

/**
 * 前台界面-控制器-学习中心(learnhub)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doClient")
public class LhubController {

	/*** 学习中心首页 **/
	@RequestMapping("/home4Lhub")
	public String home4Lhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Utls.getMapAllParams(request);
		int lhubid = MapEx.getInt(mapPars, "lhubid");
		Learnhub enLhub = LearnhubEntity.getByKey(lhubid);
		if (enLhub == null) {
			return "redirect:home";
		}

		List<String> list = ListEx.toListByComma(enLhub.getImgr4Cover(), true);
		modelMap.addAttribute("lhub", enLhub);
		modelMap.addAttribute("imgs", list);

		session.removeAttribute(SessionKeys.Filter4MarjorKind);
		return "client/lhub/xxzxsy";
	}

	/*** 学习中心拥有的套餐界面 **/
	@RequestMapping("/kind4Lhub")
	public String kind4Lhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		PageKinds pageSieve = Utls.getKinds(session);
		Map mapPars = Utls.getMapAllParams(request);
		int lhubid = MapEx.getInt(mapPars, "lhubid");
		if (lhubid == 0) {
			if (pageSieve != null) {
				lhubid = pageSieve.curLhubid;
			}
		}

		if (pageSieve == null) {
			pageSieve = new PageKinds();
		}

		pageSieve.initListByLhubid(lhubid);
		session.setAttribute(SessionKeys.PageSieve, pageSieve);
		Map<String, Object> fiterPars = (Map<String, Object>) session
				.getAttribute(SessionKeys.Filter4MarjorKind);
		if (fiterPars == null) {
			fiterPars = new HashMap<String, Object>();
			session.setAttribute(SessionKeys.Filter4MarjorKind, fiterPars);
		}

		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;

		String nmArea = MapEx.getString(mapPars, "kind_nmArea");
		String nmMajor = MapEx.getString(mapPars, "kind_nmMajor");
		int departid = MapEx.getInt(mapPars, "kind_departid");

		int buycountid = MapEx.getInt(mapPars, "kind_countid");
		int visitid = MapEx.getInt(mapPars, "kind_visitid");
		int praiseid = MapEx.getInt(mapPars, "kind_praiseid");
		int priceid = MapEx.getInt(mapPars, "kind_priceid");

		fiterPars.put("nmArea", nmArea);
		fiterPars.put("nmMajor", nmMajor);
		fiterPars.put("departid", departid);

		fiterPars.put("price", priceid);
		fiterPars.put("buycount", buycountid);
		fiterPars.put("visit", visitid);
		fiterPars.put("praise", praiseid);

		pageSieve.filter(fiterPars);
		PageEnt<Map> pageEnt = pageSieve.getPage(fiterPars, page, 6);
		pageSieve.resetList(pageEnt.getListPages());

		CHomeController.major(modelMap);
		Learnhub enLhub = LearnhubEntity.getByKey(lhubid);

		modelMap.addAttribute("lhub", enLhub);
		modelMap.addAttribute("lhubid", lhubid);
		modelMap.addAttribute("mapFiterPars", fiterPars);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("mapArea", pageSieve.mapArea);
		return "client/lhub/kind4Lhub";
	}
}
