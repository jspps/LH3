package com.learnhall.logic.controll.onesigle;

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
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Account;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.entity.AccountEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;

/**
 * 个人中心-控制器:设置模块
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes" })
@Controller
@RequestMapping("/doSigle")
public class SigleSetController {

	/*** 修改账号 **/
	@RequestMapping("/setAccount")
	public String setAccount() {
		return "onesigle/setAccount";
	}

	/*** 修改账号 **/
	@RequestMapping("/doChageAccount")
	public void doChageAccount(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "session失效！");
		} else {
			Account account = customer.getAccountFkAccountid();
			if (account == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "account帐号为空！");
			} else {
				Map parsMap = Svc.getMapAllParams(request);
				String newlgid = MapEx.getString(parsMap, "newlgid");
				newlgid = newlgid.trim();

				String curpwd = MapEx.getString(parsMap, "curpwd");
				curpwd = curpwd.trim();

				if (StrEx.isEmpty(newlgid)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "登录帐号不能为空！");
				} else {
					if (account.getLgpwd().equals(curpwd)) {
						Account hasAccount = AccountEntity.getByLgid(newlgid);
						if (hasAccount != null) {
							result = Utls.tipMap(result, Utls.Status_Erro,
									"该登录帐号已经存在，不能使用，请重新填写！");
						} else {
							account.setLgid(newlgid);
							account.update();
							result = Utls.tipMap(result, Utls.Status_Success,
									"修改成功！");
						}
					} else {
						result = Utls.tipMap(result, Utls.Status_Erro,
								"当前密码不正确，修改失败！");
					}
				}
			}
		}
		Utls.writeAndClose(response, result);
	}

	/*** 基本资料 **/
	@RequestMapping("/setBinfo")
	public String setBaseInfo(HttpSession session, ModelMap modelMap) {
		Customer customer = Utls.getCustomer(session);
		if (customer == null)
			return "redirect:go2LoginView";

		session.setAttribute(SessionKeys.CurSigleTop, 5);
		Utls.provinces("", modelMap);
		String headImg = customer.getHeadIcon();
		if (StrEx.isEmptyTrim(headImg))
			headImg = "jsp/imgs/onesigle/usermanange/bg.jpg";
		modelMap.addAttribute("customer", customer);
		modelMap.addAttribute("headImg", headImg);
		modelMap.addAttribute("account", customer.getAccountFkAccountid());
		return "onesigle/setBaseInfo";
	}

	/*** json请求 **/
	@RequestMapping("/doChangeBInfo")
	public void doChangeBInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "session失效！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map parsMap = Svc.getMapAllParams(request);
		String cname = MapEx.getString(parsMap, "cname");
		if (StrEx.isEmpty(cname)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "名字为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!cname.equals(customer.getName())) {
			if (cname.length() > 16) {
				result = Utls.tipMap(result, Utls.Status_Erro, "名字过长!");
				Utls.writeAndClose(response, result);
				return;
			}
		}

		String cicon = MapEx.getString(parsMap, "cicon");
		String cemail = MapEx.getString(parsMap, "cemail");
		String cprovince = MapEx.getString(parsMap, "cprovince");
		String ccity = MapEx.getString(parsMap, "ccity");
		String cseat = MapEx.getString(parsMap, "cseat");
		String cdescr = MapEx.getString(parsMap, "cdescr");
		customer.setName(cname);
		customer.setHeadIcon(cicon);
		customer.setEmail(cemail);
		customer.setCity(ccity);
		customer.setDescr(cdescr);
		customer.setProvince(cprovince);
		customer.setSeat(cseat);
		customer.update();
		result = Utls.tipMap(result, Utls.Status_Success, "修改成功");
		Utls.writeAndClose(response, result);
	}

	/*** 重新设置基本资料的头像 **/
	static public void resetImg4BaseInfo(HttpServletRequest request, String url) {
		Map parsMap = Svc.getMapAllParams(request);
		String cmd = MapEx.getString(parsMap, "cmd");
		if (StrEx.isEmptyTrim(cmd) || !cmd.equals("baseInfoHead")) {
			return;
		}

		if (StrEx.isEmpty(url) || url.indexOf("http://") == -1) {
			return;
		}

		HttpSession session = request.getSession();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			return;
		}
		customer.setHeadIcon(url);
		customer.update();
	}

	/*** 密码设置 **/
	@RequestMapping("/setPwd")
	public String setPassWord() {
		return "onesigle/setPassWord";
	}

	/*** 执行密码设置json请求 **/
	@RequestMapping("/doChangePwd")
	public void doChangePwd(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "session失效！");
		} else {
			Account account = customer.getAccountFkAccountid();
			if (account == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "account帐号为空！");
			} else {
				Map parsMap = Svc.getMapAllParams(request);
				String curpwd = MapEx.getString(parsMap, "curpwd");
				curpwd = curpwd.trim();
				String newpwd = MapEx.getString(parsMap, "newpwd");
				newpwd = newpwd.trim();
				if (StrEx.isEmpty(newpwd)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "新密码不能为空！");
				} else {
					if (account.getLgpwd().equals(curpwd)) {
						account.setLgpwd(newpwd);
						account.update();
						result = Utls.tipMap(result, Utls.Status_Success,
								"改密成功！");
					} else {
						result = Utls.tipMap(result, Utls.Status_Erro,
								"当前密码不正确，修改失败！");
					}
				}
			}
		}
		Utls.writeAndClose(response, result);
	}
}
