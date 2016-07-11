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
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Account;
import com.learnhall.db.bean.Boughtkinds;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Msg;
import com.learnhall.db.entity.AccountEntity;
import com.learnhall.db.entity.CustomerEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.PageBuyCourses;
import com.learnhall.logic.model.PageMsg;

/**
 * 个人中心-控制器:帐户模块
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doSigle")
public class OSigleController {

	/*** 登录 **/
	@RequestMapping("/go2LoginView")
	public String jumpLogin(HttpSession session) {
		return "onesigle/login";
	}

	/*** 登录 **/
	@RequestMapping("/login")
	public String login(HttpSession session) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			return "redirect:go2LoginView";
		} else {
			return "redirect:examRecord";
		}
	}

	/*** 欢迎页面 **/
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request, HttpSession session) {
		Map map = Svc.getMapAllParams(request);
		String lgid = MapEx.getString(map, "login_id");
		String lgpwd = MapEx.getString(map, "login_pwd");
		Account account = AccountEntity.getByLgid(lgid);
		if (account == null) {
			account = AccountEntity.getByPhone(lgid);
		}

		if (account == null) {
			session.setAttribute("tipUrl", "sigle/login");
			session.setAttribute("tip", "登录帐号(account)不存在,可能是你的登录ID不正确！");
			return "/tip";
		}
		if (!account.getLgpwd().equals(lgpwd)) {
			session.setAttribute("tipUrl", "sigle/login");
			session.setAttribute("tip", "密码不正确！！");
			return "/tip";
		}

		Customer user = CustomerEntity.getByAccountid(account.getId());
		if (user == null) {
			session.setAttribute("tipUrl", "sigle/login");
			session.setAttribute("tip", "学员帐号为空(Customer)!!");
			return "/tip";
		}

		account.setLasttime(new Date());
		account.update();
		
		Utls.saveCustomer(session, user);
		String reUrl = (String) session.getAttribute(SessionKeys.UrlPre);
		if (StrEx.isEmpty(reUrl))
			reUrl = "examRecord";
		return "redirect:" + reUrl;
	}

	/*** 消息 **/
	@RequestMapping("/info")
	public String info(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		session.setAttribute(SessionKeys.CurSigleTop, 4);
		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "page");
		int pageSize = MapEx.getInt(map, "pageSize");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("target", "%1%");
		PageMsg pageMsg = new PageMsg();
		PageEnt<Msg> pageEnt = pageMsg.getPage(params, page == 0 ? 1 : page,
				pageSize == 0 ? 10 : pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "onesigle/info";
	}

	/*** 登录Json **/
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map map = Svc.getMapAllParams(request);
		String lgid = MapEx.getString(map, "login_id");
		String lgpwd = MapEx.getString(map, "login_pwd");
		Account account = AccountEntity.getByLgid(lgid);
		Map result = new HashMap();
		if (account == null) {
			account = AccountEntity.getByPhone(lgid);
		}

		if (account == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"登录帐号(account)不存在,可能是你的登录ID不正确！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!account.getLgpwd().equals(lgpwd)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "密码不正确！");
			Utls.writeAndClose(response, result);
			return;
		}

		Customer user = CustomerEntity.getByAccountid(account.getId());
		if (user == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "学员帐号为空(Customer)！");
			Utls.writeAndClose(response, result);
			return;
		}

		Utls.saveCustomer(session, user);
		result = Utls.tipMap(result, Utls.Status_Success, "登录成功!");
		Utls.writeAndClose(response, result);
	}
	
	/*** 搜索课程 **/
	@RequestMapping("/searchBuySub")
	public String searchBuySub(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		Map paramsMap = Svc.getMapAllParams(request);
		int page = MapEx.getInt(paramsMap, "inp_fm_page");
		page = page < 1 ? 1 : page;
		int custid = Utls.getCustomerId(session);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerid", custid);
		params.put("name", MapEx.getString(paramsMap, "searchname"));
		
		PageBuyCourses buyCourse = new PageBuyCourses();
		PageEnt<Boughtkinds> pageEnt = buyCourse.getPage(params, page, 10);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("menuChild", 2);
		session.setAttribute(SessionKeys.CurSigleTop, 1);
		return "onesigle/learnBuy";
	}
}
