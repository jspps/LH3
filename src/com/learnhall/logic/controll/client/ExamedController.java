package com.learnhall.logic.controll.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.lang.StrEx;
import com.bowlong.util.MapEx;
import com.learnhall.enums.ExamEnum;
import com.learnhall.logic.Utls;

/**
 * 前台界面-控制器-查看答案
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes"})
@Controller
@RequestMapping("/doClient")
public class ExamedController {
	/*** 查看答卷 **/
	@RequestMapping("/seeAnswer4ITMS")
	public String seeAnswer4ITMS(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		ExamingController.initQues(session, modelMap, ExamEnum.SeeAnswer, 0, 10);
		modelMap.addAttribute("see_dj", "1");
		return "client/examed/examed4ITMS";
	}

	/*** 查看答卷 **/
	@RequestMapping("/seeAnswer")
	public String seeAnswer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		boolean isInitExam = ExamingController.initExam(request, session,
				modelMap, ExamEnum.SeeAnswer);
		if (!isInitExam)
			return "redirect:home";

		Map mapPars = Utls.getMapAllParams(request);
		String reqType = MapEx.getString(mapPars, "reqType");
		modelMap.addAttribute("see_dj", reqType);
		
		if(StrEx.isEmptyTrim(reqType) || "0".equals(reqType)){
			return "client/exam4subject/examed";
		}
		return "client/examed/examed";
	}

	/*** 查看题答案 **/
	@RequestMapping("/seeQuestion4Answer")
	public String seeQuestion4Answer(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map mapPars = Utls.getMapAllParams(request);
		String reqType = MapEx.getString(mapPars, "reqType");
		modelMap.addAttribute("see_dj", reqType);
		String v = ExamingController.handleQuestion(request, response,
				modelMap, session);
		return v;
	}
}
