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

import com.bowlong.util.MapEx;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Examcatalog;
import com.learnhall.db.bean.Itms4auto;
import com.learnhall.db.bean.Optquestion;
import com.learnhall.db.entity.RecordanswerEntity;
import com.learnhall.db.entity.Recordques4examEntity;
import com.learnhall.enums.ExamEnum;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.KExamITMS;
import com.learnhall.logic.model.PageKExam;

/**
 * 前台界面-控制器-ITMS辅助
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doClient")
public class ExamITMSController {
	// itmsType : 1 题型练习,2错误,3新题,4知识点,5智能
	// 取得ITMS
	static public KExamITMS getITMSBySession(HttpServletRequest request,
			HttpSession session, boolean isInit) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null)
			return null;

		PageKExam pageExam = KExamController.initPageKExam(request, session,
				isInit);
		if (pageExam == null || pageExam.kind == null)
			return null;

		KExamITMS examITMS = (KExamITMS) session
				.getAttribute(SessionKeys.ExamITMS);
		if (!isInit) {
			isInit = examITMS == null;
		}
		if (examITMS == null) {
			examITMS = new KExamITMS();
		}

		if (isInit) {
			examITMS.init(pageExam, customer.getId());
		}

		session.setAttribute(SessionKeys.ExamITMS, examITMS);
		return examITMS;
	}

	/*** ITMS辅助 **/
	@RequestMapping("/assistITMS")
	public String assistITMS(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		PageKExam pageExam = KExamController.initPageKExam(request, session,
				false);
		if (pageExam == null || pageExam.kind == null)
			return "redirect:home";
		ExamingController.removeSesesion4Exam(session);

		Utls.setUrlPre(session, "/client/assistITMS");
		return "redirect:practiceTopics";
	}

	/*** 题型练习 1 **/
	@RequestMapping("/practiceTopics ")
	public String practiceTopics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS itms = getITMSBySession(request, session, true);
		if (itms == null || itms.kindExams == null
				|| itms.kindExams.kind == null)
			return "redirect:home";
		ExamingController.removeSesesion4Exam(session);
		modelMap.addAttribute("list", itms.getList4Alls());
		modelMap.addAttribute("itmsType", 1);
		modelMap.addAttribute("isBuy", itms.kindExams.isBuyed);
		modelMap.put("kind", itms.kindExams.kind);
		modelMap.put("curMenu4kexam", 6);
		return "client/kexam/ITMS/practiceTopics";
	}

	/*** 错题训练 2 **/
	@RequestMapping("/errorTrains ")
	public String errorTrains(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS itms = getITMSBySession(request, session, false);
		if (itms == null || itms.kindExams == null
				|| itms.kindExams.kind == null)
			return "redirect:home";
		ExamingController.removeSesesion4Exam(session);
		modelMap.addAttribute("list", itms.getList4Errors());
		modelMap.addAttribute("itmsType", 2);
		modelMap.addAttribute("isBuy", itms.kindExams.isBuyed);
		modelMap.put("kind", itms.kindExams.kind);
		modelMap.put("curMenu4kexam", 6);
		return "client/kexam/ITMS/errorTrains";
	}

	/*** 只做新题 3 **/
	@RequestMapping("/doNewTopics ")
	public String doNewTopics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS itms = getITMSBySession(request, session, false);
		if (itms == null || itms.kindExams == null
				|| itms.kindExams.kind == null)
			return "redirect:home";
		ExamingController.removeSesesion4Exam(session);
		modelMap.addAttribute("list", itms.getList4Newes());
		modelMap.addAttribute("itmsType", 3);
		modelMap.addAttribute("isBuy", itms.kindExams.isBuyed);
		modelMap.put("kind", itms.kindExams.kind);
		modelMap.put("curMenu4kexam", 6);
		return "client/kexam/ITMS/doNewTopics";
	}

	/*** 知识点训练 4 **/
	@RequestMapping("/trainKnowledges ")
	public String trainKnowledges(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS itms = getITMSBySession(request, session, false);
		if (itms == null || itms.kindExams == null
				|| itms.kindExams.kind == null)
			return "redirect:home";
		Map mapPars = Utls.getMapAllParams(request);

		String strKnowlegde = MapEx.getString(mapPars, "strKnowlegde");
		ExamingController.removeSesesion4Exam(session);
		modelMap.addAttribute("list", itms.getList4Knowledge(strKnowlegde));
		modelMap.addAttribute("itmsType", 4);
		modelMap.addAttribute("isBuy", itms.kindExams.isBuyed);
		modelMap.addAttribute("strKnowlegde", strKnowlegde);
		modelMap.put("kind", itms.kindExams.kind);
		modelMap.put("curMenu4kexam", 6);
		return "client/kexam/ITMS/trainKnowledges";
	}

	/*** 智能组选 5 **/
	@RequestMapping("/autoSelecTopics ")
	public String autoSelecTopics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS itms = getITMSBySession(request, session, false);
		if (itms == null || itms.kindExams == null
				|| itms.kindExams.kind == null)
			return "redirect:home";

		ExamingController.removeSesesion4Exam(session);
		modelMap.addAttribute("itmsType", 5);
		modelMap.addAttribute("isBuy", itms.kindExams.isBuyed);
		int kindid = itms.kindExams.kind.getId();
		Itms4auto en = itms.itmsAuto.getAutoEntity(kindid);
		int all = (en.getNum4chbox() + en.getNum4fill() + en.getNum4jd()
				+ en.getNum4judge() + en.getNum4luns() + en.getNum4radio());

		modelMap.addAttribute("auto", en);
		modelMap.addAttribute("all", all);
		modelMap.put("kind", itms.kindExams.kind);
		modelMap.put("curMenu4kexam", 6);
		return "client/kexam/ITMS/autoSelecTopics";
	}

	/*** 生成题库 **/
	@RequestMapping("/initITMSQues")
	public String initITMSQues(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS examITMS = (KExamITMS) session
				.getAttribute(SessionKeys.ExamITMS);
		if (examITMS == null)
			return "redirect:home";

		Map mapPars = Utls.getMapAllParams(request);
		int type = MapEx.getInt(mapPars, "itmsType");
		int numRadio2Exam = MapEx.getInt(mapPars, "numRadio2Exam");
		int numChbox2Exam = MapEx.getInt(mapPars, "numChbox2Exam");
		int numJudge2Exam = MapEx.getInt(mapPars, "numJudge2Exam");
		int numFill2Exam = MapEx.getInt(mapPars, "numFill2Exam");
		int numJDa2Exam = MapEx.getInt(mapPars, "numJDa2Exam");
		int numLunsu2Exam = MapEx.getInt(mapPars, "numLunsu2Exam");

		List<Optquestion> listQues = examITMS.getList2Exam(type, numRadio2Exam,
				numChbox2Exam, numJudge2Exam, numFill2Exam, numJDa2Exam,
				numLunsu2Exam);
		int lens = listQues.size();
		if (lens <= 0)
			return "redirect:home";

		Map<Integer, Optquestion> mapQues = new HashMap<Integer, Optquestion>();
		for(int i = 0 ; i < lens ; i ++){
			Optquestion en = listQues.get(i);
			mapQues.put(en.getOptid(), en);
		}
		
		Map<Integer, Examcatalog> mapExamCatalogs = examITMS.kindExams.mapCatalog;
		session.setAttribute(SessionKeys.MapQuestions, mapQues);
		session.setAttribute(SessionKeys.MapExamCatalog, mapExamCatalogs);
		ExamingController.initQues(session, modelMap, ExamEnum.ExamITMS, 0, 10);
		modelMap.addAttribute("name", "ITMS考试");
		modelMap.addAttribute("isITMS", true);
		return "client/examing/examing4ITMS";
	}

	/*** 数据分析 6 **/
	@RequestMapping("/analysisDatas ")
	public String analysisDatas(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		KExamITMS itms = getITMSBySession(request, session, false);
		if (itms == null || itms.kindExams == null
				|| itms.kindExams.kind == null)
			return "redirect:home";
		int custid = Utls.getCustomerId(session);
		int zzlx = RecordanswerEntity.reckon4CustExamType(custid, 1);
		int zzlxH = zzlx * 450 / 100;
		int lnzt = RecordanswerEntity.reckon4CustExamType(custid, 2);
		int lnztH = lnzt * 450 / 100;
		int qzml = RecordanswerEntity.reckon4CustExamType(custid, 3);
		int qzmlH = qzml * 450 / 100;
		int jsyt = RecordanswerEntity.reckon4CustExamType(custid, 4);
		int jsytH = jsyt * 450 / 100;
		modelMap.put("zzlx", zzlx);
		modelMap.put("zzlxH", zzlxH);
		modelMap.put("lnzt", lnzt);
		modelMap.put("lnztH", lnztH);
		modelMap.put("qzml", qzml);
		modelMap.put("qzmlH", qzmlH);
		modelMap.put("jsyt", jsyt);
		modelMap.put("jsytH", jsytH);

		// 类型[1单,2多,3判断,4填空,5简答,6论述,7案例]
		int danx = Recordques4examEntity.reckon4CustCatalog(custid, 1);
		int danxw = 570 * danx / 100;
		int duox = Recordques4examEntity.reckon4CustCatalog(custid, 2);
		int duoxw = 570 * duox / 100;
		int jugde = Recordques4examEntity.reckon4CustCatalog(custid, 3);
		int jugdew = 570 * jugde / 100;
		int fill = Recordques4examEntity.reckon4CustCatalog(custid, 4);
		int fillw = 570 * fill / 100;
		int jd = Recordques4examEntity.reckon4CustCatalog(custid, 5);
		int jdw = 570 * jd / 100;
		modelMap.put("danx", danx);
		modelMap.put("danxw", danxw);
		modelMap.put("duox", duox);
		modelMap.put("duoxw", duoxw);
		modelMap.put("jugde", jugde);
		modelMap.put("jugdew", jugdew);
		modelMap.put("fill", fill);
		modelMap.put("fillw", fillw);
		modelMap.put("jd", jd);
		modelMap.put("jdw", jdw);

		modelMap.addAttribute("itmsType", 6);
		modelMap.addAttribute("isBuy", itms.kindExams.isBuyed);
		modelMap.put("kind", itms.kindExams.kind);
		modelMap.put("curMenu4kexam", 6);
		return "client/kexam/ITMS/analysisDatas";
	}
}
