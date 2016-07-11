package com.learnhall.logic.controll.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.lang.StrEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.PageKinds;

/**
 * 前台界面-控制器-套餐4专业
 * 
 * @author Canyon
 * 
 */
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/doClient")
public class Kind4MajorController {
	/*** 筛选课程 **/
	@RequestMapping("/chooseLessons")
	public String chooseLessons(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {

		Map pars = Svc.getMapAllParams(request);

		Map<String, Object> fiterPars = (Map<String, Object>) session
				.getAttribute(SessionKeys.Filter4MarjorKind);
		if (fiterPars == null) {
			fiterPars = new HashMap<String, Object>();
			session.setAttribute(SessionKeys.Filter4MarjorKind, fiterPars);
		}

		PageKinds pageSieve = Utls.getKinds(session);

		int departid = MapEx.getInt(pars, "departid");
		if (departid == 0) {
			if (pageSieve != null) {
				departid = pageSieve.departid;
			}
		}

		String nmMajor = MapEx.getString(pars, "nmMajor");
		if (StrEx.isEmptyTrim(nmMajor)) {
			if (pageSieve != null) {
				nmMajor = pageSieve.nmMajor;
			}
		}

		String subName = MapEx.getString(pars, "subname");
		if (StrEx.isEmptyTrim(subName)) {
			if (pageSieve != null) {
				subName = pageSieve.subName;
			}
		}

		if (pageSieve == null) {
			pageSieve = new PageKinds();
		}

		session.setAttribute(SessionKeys.PageSieve, pageSieve);
		if (departid > 0) {
			pageSieve.initList(departid, nmMajor);
		} else {
			pageSieve.initList(subName);
		}

		if (!pageSieve.isInit()) {
			return "redirect:home";
		}

		int page = MapEx.getInt(pars, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int lhubid = MapEx.getInt(pars, "kind_lhubid");
		String nmLevel = MapEx.getString(pars, "kind_levid");
		String nmSub = MapEx.getString(pars, "kind_subid");
		String nmArea = MapEx.getString(pars, "kind_areaid");

		int buycountid = MapEx.getInt(pars, "kind_countid");
		int visitid = MapEx.getInt(pars, "kind_visitid");
		int praiseid = MapEx.getInt(pars, "kind_praiseid");
		int priceid = MapEx.getInt(pars, "kind_priceid");

		fiterPars.put("lhubid", lhubid);
		fiterPars.put("nmLevel", nmLevel);
		fiterPars.put("nmSub", nmSub);
		fiterPars.put("nmArea", nmArea);

		fiterPars.put("price", priceid);
		fiterPars.put("buycount", buycountid);
		fiterPars.put("visit", visitid);
		fiterPars.put("praise", praiseid);

		pageSieve.filter(fiterPars);
		PageEnt<Map> pageEnt = pageSieve.getPage(fiterPars, page, 6);
		pageSieve.resetList(pageEnt.getListPages());

		modelMap.addAttribute("nmDepart", pageSieve.nmDepart);
		modelMap.addAttribute("nmMajor", pageSieve.nmMajor);
		modelMap.addAttribute("mapLhub", pageSieve.mapLhub);
		modelMap.addAttribute("mapLev", pageSieve.mapLev);
		modelMap.addAttribute("mapSub", pageSieve.mapSub);
		modelMap.addAttribute("mapArea", pageSieve.mapArea);
		modelMap.addAttribute("mapFiterPars", fiterPars);

		modelMap.addAttribute("pageEnt", pageEnt);
		return "client/chooseLessons";
	}

	/*** 点赞套餐kind **/
	@RequestMapping("/praiseKind")
	public void praiseKind(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Map pars = Utls.getMapAllParams(request);
		int kindid = MapEx.getInt(pars, "kindid");
		PageKinds pageSieve = Utls.getKinds(session);
		Kind enK = null;
		if (pageSieve != null) {
			enK = pageSieve.getKindById(kindid);
		}

		if (enK == null) {
			enK = KindEntity.getByKey(kindid);
		}

		if (enK == null || enK.getStatus() == 1) {
			result = Utls.tipMap(result, Utls.Status_Erro, "点赞失败！");
		} else {
			enK.changePraise(1);
			enK.update();
			result = Utls.tipMap(result, Utls.Status_Success, "点赞成功！");
		}
		Utls.writeAndClose(response, result);
	}
}
