package com.learnhall.logic.controll.onesigle;

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

import com.bowlong.lang.NumEx;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Account;
import com.learnhall.db.bean.Adqdepartment;
import com.learnhall.db.bean.Answer;
import com.learnhall.db.bean.Ask;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Orders;
import com.learnhall.db.bean.Record4seeanswer;
import com.learnhall.db.entity.AdqdepartmentEntity;
import com.learnhall.db.entity.AnswerEntity;
import com.learnhall.db.entity.AskEntity;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.db.entity.OrdersEntity;
import com.learnhall.db.entity.Record4seeanswerEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.chn.alipayapi.handle.Handle4Alipay;
import com.learnhall.logic.model.PageQuestion;
import com.learnhall.logic.model.PageQuestionAnswer;

/**
 * 个人中心-控制器:问答模块
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doSigle")
public class SigleAskController {

	/*** 我的提问(问题) **/
	@RequestMapping("/question")
	public String question(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurSigleTop, 2);

		Map mapPars = Svc.getMapAllParams(request);
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int type = MapEx.getInt(mapPars, "type");
		type = type < 1 ? 1 : type;

		boolean isMyQuestion = MapEx.getBoolean(mapPars, "isMyQuestion");
		int menuChild = 1;

		PageQuestion pageWrap = new PageQuestion();
		int custid = Utls.getCustomerId(session);

		Map<String, Object> params = new HashMap<String, Object>();
		String txtPars4Cust = "";

		String txtPars4Status = "";
		String txtPars4StatusOpt = " = 2";
		String txtPars4Expirationtime = "";
		// 查看别人答案的时候,是否显示自己回答的
		boolean isShowAnswer = false;

		// 0新/旧，1已回复，2已采纳
		int status = 0;
		String nowStr = DateEx.nowStr1();

		if (isMyQuestion) {
			txtPars4Cust = PStr.str(" = ", custid);
			// 自己的问题type[1新问题,2已过期,3已回复,4已采纳]
			switch (type) {
			case 3:
				status = 1;
				break;
			case 4:
				status = 2;
				break;
			default:
				status = 0;
				if (type == 1) {
					txtPars4Expirationtime = PStr.str(" > '", nowStr, "'");
				} else {
					txtPars4Expirationtime = PStr.str(" < '", nowStr, "'");
				}
				break;
			}
			txtPars4Status = PStr.str(" = ", status);
		} else {
			menuChild = 2;
			// 别人的问题type[1新问题,2已回复]
			type = type > 3 ? 1 : type;
			txtPars4Cust = PStr.str(" != ", custid);
			status = type == 1 ? 0 : 1;
			if (status == 0) {
				txtPars4Expirationtime = PStr.str(" > '", nowStr, "'");
				txtPars4Status = PStr.str(" = ", status);
			} else {
				isShowAnswer = true;
				txtPars4Status = PStr.str(" >= ", status);
			}

		}

		params.put("customerid", txtPars4Cust);
		params.put("status", txtPars4Status);
		params.put("status_opt", txtPars4StatusOpt);
		if (!StrEx.isEmpty(txtPars4Expirationtime)) {
			params.put("expirationtime", txtPars4Expirationtime);
		}

		PageEnt<Map> pageEnt = pageWrap.getPage(params, page, 10);

		if (isShowAnswer) {
			pageWrap.init4CustAnswer(custid, pageEnt.getListPages());
		}

		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("menuChild", menuChild);
		modelMap.addAttribute("type", type);

		// 分类
		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();
		modelMap.addAttribute("adqdepartments", adqdepartments);

		if (isMyQuestion) {
			return "onesigle/aqMQuestion";
		} else {
			return "onesigle/aqOQuestion";
		}
	}

	/*** 添加新问题 json请求 **/
	@RequestMapping("/doSendQuestion")
	public void doSendQuestion(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "session失效！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map parsMap = Svc.getMapAllParams(request);
		int money = MapEx.getInt(parsMap, "money");
		if (money < 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "金额小于零！");
			Utls.writeAndClose(response, result);
			return;
		}

		String tag = MapEx.getString(parsMap, "tag");
		tag = tag.trim();
		String tit = MapEx.getString(parsMap, "tit");
		tit = tit.trim();
		if (StrEx.isEmpty(tit)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "问题不能为空！");
			Utls.writeAndClose(response, result);
			return;
		}

		Date createtime = DateEx.nowDate();
		Date expirationtime = DateEx.addDay(createtime, 7);
		Ask ques = Ask.newAsk(0, customer.getId(), tit, (double) money,
				expirationtime, 0, tag, 0, createtime, 0);

		boolean isRmb = money > 0;
		if (isRmb) {
			if (customer.getMoneyCur() < money) {
				isRmb = true;
			} else {
				isRmb = false;
				String content = PStr.str("花费:", money, "元,", tit);
				Utls.recordRmb(customer, 2, money, content, 0);
				customer.changeMoneyCur(-money);
				customer.update();
			}
		}

		if (isRmb) {
			Ref<String> ref = new Ref<String>("");
			ques = ques.insert();
			String extra_param = "";
			if (ques != null) {
				extra_param = String.valueOf(ques.getId());
			}
			String v = Handle4Alipay.handle4Kbi0Ask(4, customer, money,
					extra_param, ref);
			if (ref.val.startsWith("error")) {
				result = Utls.tipMap(result, Utls.Status_Erro, ref.val);
			} else {

				result = Utls.tipMap(result, Utls.Status_Success, "添加中,请支付金额！",
						v);
			}
		} else {
			ques.setStatus_opt(2);
			ques = ques.insert();
			result = Utls.tipMap(result, Utls.Status_Success, "添加成功！");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 取得回答问题 **/
	@RequestMapping("/getAnswers")
	public String getAnswers(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		Map parsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(parsMap, "inp_fm_page");
		if (page == 0) {
			page = 1;
		}
		int type = MapEx.getInt(parsMap, "type");
		if (type == 0) {
			type = 1;
		}

		int questionid = MapEx.getInt(parsMap, "questionid");
		PageQuestionAnswer pageWrap = new PageQuestionAnswer();

		pageWrap.init(questionid);
		PageEnt<Answer> pageEnt = pageWrap.getPage(null, page, 5);

		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("quesid", questionid);
		modelMap.addAttribute("type", type);

		if (type == 4) {
			Ask quesEn = AskEntity.getByKey(questionid);
			if (quesEn != null) {
				modelMap.addAttribute("answerid", quesEn.getAnswerid());
			} else {
				modelMap.addAttribute("answerid", 0);
			}
		}

		return "onesigle/question/answer";
	}

	/*** 回答问题 **/
	@RequestMapping("/doAnswer")
	public void doAnswer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "操作失败,Session失效!");
			Utls.writeAndClose(response, result);
			return;
		}

		Map parsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(parsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int type = MapEx.getInt(parsMap, "type");
		type = type < 1 ? 1 : type;
		String answer = MapEx.getString(parsMap, "answer");
		int customerid = customer.getId();
		if (StrEx.isEmpty(answer)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "内容不能为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		int askid = MapEx.getInt(parsMap, "questionid");
		Ask quesEn = AskEntity.getByKey(askid);
		if (quesEn == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "askid不能为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (quesEn.getAnswerid() > 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该问题已经采纳回复了!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (DateEx.isBefore(quesEn.getExpirationtime())) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该问题已过有效期了!");
			Utls.writeAndClose(response, result);
			return;
		}

		Answer ask = AnswerEntity.getByAskidCustomerid(askid, customerid);
		if (ask == null) {
			ask = Answer.newAnswer(0, askid, customerid, answer, 0,
					DateEx.nowDate());
			ask.insert();
			if (quesEn.getStatus() == 0) {
				quesEn.setStatus(1);
				quesEn.update();
			}
		} else {
			ask.setContent(answer);
			ask.update();
		}

		result = Utls.tipMap(result, Utls.Status_Success, "修改成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 采纳回答 **/
	@RequestMapping("/doAdopt")
	public void doAdopt(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "session失效！");
			Utls.writeAndClose(response, result);
			return;
		}

		Account account = customer.getAccountFkAccountid();
		if (account == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "account帐号为空！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map parsMap = Svc.getMapAllParams(request);
		int answerid = MapEx.getInt(parsMap, "answerid");
		int quesid = MapEx.getInt(parsMap, "quesid");
		if (answerid <= 0 || quesid <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"数据异常，answerid或者quesid为0！");
			Utls.writeAndClose(response, result);
			return;
		}

		Ask ques = AskEntity.getByKey(quesid);
		Answer askEn = AnswerEntity.getByKey(answerid);
		if (ques == null || askEn == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "不纯在！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (ques.getAnswerid() == 0) {
			ques.setAnswerid(answerid);
			ques.setStatus(2);
			ques.update();

			double mmoney = ques.getRewardamount();
			if (mmoney > 0) {

				// //////////////// 金额分配 ////////////////
				String cont = "";
				// 自己
				double allot4Self = mmoney * 0.1;
				customer.changeMoneyAll(allot4Self);
				customer.changeMoneyCur(allot4Self);
				customer.update();

				cont = PStr.str("采纳别人的问题返还金额", allot4Self, "元");
				Utls.recordRmb(customer, 1, allot4Self, cont, 0);

				// 回答问题,被采纳的人获得金额
				double allot4Adoptor = mmoney * 0.7;
				int acustid = askEn.getCustomerid();
				Customer acustEn = CustomerEntity.getByKey(acustid);
				acustEn.changeMoneyAll(allot4Adoptor);
				acustEn.changeMoneyCur(allot4Adoptor);
				acustEn.update();

				cont = PStr.str("回答问题被采纳,获得", allot4Adoptor, "元");
				Utls.recordRmb(acustEn, 1, allot4Adoptor, cont, 0);
			}

			result = Utls.tipMap(result, Utls.Status_Success, "添加成功！");
		} else {
			result = Utls.tipMap(result, Utls.Status_Erro, "该问题已经采纳了答案！");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 满意答案 3 **/
	@RequestMapping("/answer4Satisfied")
	public String answer4Satisfied(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		Map paramsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(paramsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;

		Customer cust = Utls.getCustomer(session);
		int custid = cust.getId();
		int kbi = cust.getKbiUse();
		PageQuestion pageWrap = new PageQuestion();
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("customerid", " != " + custid);
		params.put("status", " = 2");
		params.put("status_opt", " = 2");
		params.put("answerid", " > 0");
		PageEnt<Map> pageEnt = pageWrap.getPage(params, page, 10);
		pageWrap.init4SatisfiedAnswer(custid, pageEnt.getListPages());

		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("menuChild", 3);
		modelMap.addAttribute("kbi", kbi);

		// 分类
		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();
		modelMap.addAttribute("adqdepartments", adqdepartments);

		return "onesigle/aqAnswerSatisfied";
	}

	/*** 查看满意答案 **/
	@RequestMapping("/doSeeStatisfiedAnswer")
	public void doSeeStatisfiedAnswer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "session失效！");
			Utls.writeAndClose(response, result);
			return;
		}

		int costKbi = 10;
		if (customer.getKbiUse() < costKbi) {
			result = Utls.tipMap(result, Utls.Status_Erro, "所剩考币不足10个，请购买！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map parsMap = Svc.getMapAllParams(request);
		int askid = MapEx.getInt(parsMap, "askid");
		if (askid <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "数据异常，askid为0！");
			Utls.writeAndClose(response, result);
			return;
		}

		int answerid = MapEx.getInt(parsMap, "answerid");
		if (answerid <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "数据异常，answerid为0！");
			Utls.writeAndClose(response, result);
			return;
		}

		int custid = customer.getId();
		Record4seeanswer record = Record4seeanswerEntity.getByAskidCustid(
				askid, custid);
		if (record != null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "查看过该答案了,无需再查看");
			Utls.writeAndClose(response, result);
			return;
		}

		Ask ask = AskEntity.getByKey(askid);
		if (ask == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "数据不存在，askid为"
					+ askid);
			Utls.writeAndClose(response, result);
			return;
		}

		if (ask.getAnswerid() != answerid) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"数据异常，查看问题的答案与问题采纳的答案有误！");
			Utls.writeAndClose(response, result);
			return;
		}

		Answer answer = AnswerEntity.getByKey(answerid);
		if (answer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "数据不存在，answerid为"
					+ answerid);
			Utls.writeAndClose(response, result);
			return;
		}

		customer.changeKbiUse(-costKbi);
		customer.update();

		String desc4Rec = PStr.str("花费10个考币，查看满意答案 ", answerid, "!");
		Utls.recordKbi(customer, 2, 10, desc4Rec);
		result = Utls.tipMap(result, Utls.Status_Success, "成功！",
				answer.getContent());
		Utls.writeAndClose(response, result);

		// 记录查看日志
		record = Record4seeanswer.newRecord4seeanswer(0, askid, custid,
				DateEx.nowDate());
		record.insert();
	}

	/*** 处理回调提问 **/
	@RequestMapping("/handleNewQuesition")
	public String handleNewQuesition(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map parsMap = Svc.getMapAllParams(request);
		int orderid = MapEx.getInt(parsMap, "orderid");
		Customer customer = Utls.getCustomer(session);
		int putquesid = 0;
		double money = 0;
		if (orderid > 0) {
			Orders order = OrdersEntity.getByKey(orderid);
			putquesid = NumEx.stringToInt(order.getExtra_param());
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

		if (putquesid <= 0 || money <= 0 || customer == null) {
			return "redirect:go2LoginView";
		}

		Ask ques = AskEntity.getByKey(putquesid);
		if (ques != null) {
			ques.setStatus_opt(2);
			ques.update();
		}
		return "redirect:question";
	}

	/*** 尚学提问 4 **/
	@RequestMapping("/info4question")
	public String info4question(HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		modelMap.addAttribute("menuChild", 4);

		// 分类
		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();
		modelMap.addAttribute("adqdepartments", adqdepartments);
		return "onesigle/aqInfo";
	}
}
