package com.learnhall.logic.controll.client;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.bowlong.lang.NumFmtEx;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.security.Encrypt;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.CalendarEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Account;
import com.learnhall.db.bean.Agent;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.bean.Rnk4profit;
import com.learnhall.db.entity.AccountEntity;
import com.learnhall.db.entity.AgentEntity;
import com.learnhall.db.entity.Rnk4profitEntity;
import com.learnhall.logic.Utls;

/**
 * 前台界面-注册学习中心和代理商
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doClient")
public class AccountController {
	/*** 学习中心注册登录页面 **/
	@RequestMapping("/xxzx")
	public String xxzx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		return "client/xxzx_dls/dlzc_xxzx";
	}

	/*** 注册为学习中心页面 **/
	@RequestMapping("/regLhub")
	public String regLhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Utls.provinces("", modelMap);
		return "client/xxzx_dls/zcxxzx";
	}

	/*** 注册为学习中心 **/
	@RequestMapping("/saveRegLhub")
	public void saveRegLhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		String lgid = MapEx.getString(map, "phone"); // '登陆账号',
		String phone = MapEx.getString(map, "phone");// '手机号码11位',
		String lgpwd = MapEx.getString(map, "lgpwd");// '登录密码',
		String alipay = MapEx.getString(map, "alipay"); // '支付宝帐号',
		String codeid = MapEx.getString(map, "codeid");// 身份证号码
		String img4idface = MapEx.getString(map, "img4idface");// 身份证正面
		String img4idback = MapEx.getString(map, "img4idback");// 身份证背面
		String qqemail = MapEx.getString(map, "qq"); // QQ邮箱

		if (StrEx.isEmpty(img4idface)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "身份证正面图片不能为空！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (StrEx.isEmpty(img4idback)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "身份证背面图片不能为空！");
			Utls.writeAndClose(response, result);
			return;
		}

		boolean isIdCode = TkitValidateCheck.isIDCard(codeid);
		if (!isIdCode) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您输入的身份证号有误！");
			Utls.writeAndClose(response, result);
			return;
		}

		boolean isMobile = TkitValidateCheck.isMobile(phone);
		if (!isMobile) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您输入的手机号码有误！");
			Utls.writeAndClose(response, result);
			return;
		}

		// 验证邮箱
		boolean isEmail = TkitValidateCheck.isEmail(qqemail);
		if (!isEmail) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您的QQ邮箱格式不对！");
			Utls.writeAndClose(response, result);
			return;
		}

		Account account = AccountEntity.getByPhone(phone);
		// 验证手机号码
		if (account != null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您的手机号码已被注册了！");
			Utls.writeAndClose(response, result);
			return;
		}

		account = AccountEntity.getByEmail(qqemail);
		// 验证邮箱
		if (account != null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您的QQ邮箱已被注册了！");
			Utls.writeAndClose(response, result);
			return;
		}

		account = AccountEntity.getByLgid(lgid);
		if (account != null) {
			lgid = PStr.str(DateEx.now(), "_", phone);
		}

		int type = MapEx.getInt(map, "type"); // 类型(1个人,2机构)
		String img4jg = MapEx.getString(map, "img4jg");// 机构图片
		if (type == 2) {
			if (StrEx.isEmpty(img4jg)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "机构图片不能为空！");
				Utls.writeAndClose(response, result);
				return;
			}
		}

		Date createtime = new Date();// '创建时间'

		// 'type类型[1管理员,2学习中心,3代理商,4学生,5程序,6美工]',
		account = Account.newAccount(0, lgid, phone, qqemail, lgpwd, 2, 0,
				createtime, createtime);
		// 创建账户
		account = account.insert();

		if (account == null || account.getId() == 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "插入数据库异常,失败！");
			Utls.writeAndClose(response, result);
			return;
		}

		int accountid = account.getId();
		String name = MapEx.getString(map, "name"); // 学习中心名称
		String province = MapEx.getString(map, "province");// 省份
		String city = MapEx.getString(map, "city");// 市
		String seat = MapEx.getString(map, "seat");// 地址
		String uname = MapEx.getString(map, "uname");// 联系人名称
		int salesmode = MapEx.getInt(map, "salesmode");// '销售模式(1代理,2自行)
		int volume = 0;// 成交量
		Double money = 0d;// 成交金额
		Boolean isselfadmin = false;// 是否自主管理权限
		int status = 0;
		int tiku = 0;
		int quality = 0;
		int wrong = 0;
		int examineStatus = 0;
		String examineDes = "";
		Learnhub learnhub = Learnhub.newLearnhub(0, accountid, name, type,
				codeid, province, city, seat, qqemail, uname, salesmode,
				img4jg, volume, money, money, isselfadmin, status, tiku,
				quality, wrong, examineStatus, examineDes, createtime, "",
				"请填写描述信息!", alipay, false, img4idface, img4idback);
		// 创建学习中心
		learnhub = learnhub.insert();
		if (learnhub == null || learnhub.getLhid() == 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		} else {
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 代理商 **/
	@RequestMapping("/dls")
	public String dls(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ModelMap modelMap) {
		return "client/xxzx_dls/dlzc_dls";
	}

	/*** 注册为代理商 **/
	@RequestMapping("/regAgent")
	public String regAgent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Utls.provinces("", modelMap);

		Calendar now_date = CalendarEx.nowCalendar();
		int h = CalendarEx.hour(now_date);
		if (h < 4) {
			now_date = CalendarEx.addDay(now_date, -1);
		}
		String yyyy = DateEx.format(now_date, DateEx.fmt_yyyy);

		String dataStr = DateEx.format(now_date, DateEx.fmt_yyyyMMdd);

		List<Rnk4profit> rnkList = Rnk4profitEntity
				.getListBy(0, 1, 20, dataStr);
		List<Map> rnkLMap = new ArrayList<Map>();
		if (!ListEx.isEmpty(rnkList)) {
			int lens = rnkList.size();
			for (int i = 0; i < lens; i++) {
				Rnk4profit en = rnkList.get(i);
				if (en == null)
					continue;
				Map tmpMap = en.toBasicMap();
				Agent agent = AgentEntity.getByKey(en.getOwnerid());
				if (agent != null) {
					tmpMap.put("name", agent.getUname());
				}
				tmpMap.put("money", NumFmtEx.formatDouble(en.getMoney()));
				tmpMap.put("bonus", NumFmtEx.formatDouble(en.getBonus()));
				tmpMap.put("royalty", NumFmtEx.formatDouble(en.getRoyalty()));
				rnkLMap.add(tmpMap);
			}
		}

		double sumBonus = Rnk4profitEntity.getSumBonus(0, 0, dataStr);

		modelMap.addAttribute("rnkList", rnkLMap);
		modelMap.addAttribute("rnkDate", yyyy);
		modelMap.addAttribute("sumBonus", NumFmtEx.formatDouble(sumBonus));

		return "client/xxzx_dls/zcwdls";
	}

	/*** 新的代理商 **/
	@RequestMapping("/newAgent")
	public void newAgent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			String uname = MapEx.getString(map, "agent_uname");
			String phone = MapEx.getString(map, "agent_phone");
			String code = MapEx.getString(map, "agent_code");
			String province = MapEx.getString(map, "agent_province");
			String city = MapEx.getString(map, "agent_city");
			String seat = MapEx.getString(map, "agent_seat");
			String qqemail = MapEx.getString(map, "agent_qq");
			String lgid = MapEx.getString(map, "agent_lgid");
			String lgpwd = MapEx.getString(map, "agent_lgpwd");
			String lgpwd2 = MapEx.getString(map, "agent_lgpwd2");
			String need = MapEx.getString(map, "agent_need");
			String goodness = MapEx.getString(map, "agent_goodness");
			String alipay = MapEx.getString(map, "agent_alipay");
			String alipay2 = MapEx.getString(map, "agent_alipay2");

			if (StrEx.isEmptyTrim(lgid)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "登录帐号为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			if (StrEx.isEmptyTrim(phone) || StrEx.isEmptyTrim(code)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "手机号码为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			if (StrEx.isEmptyTrim(lgpwd) || !lgpwd.equals(lgpwd2)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "两次密码不同!");
				Utls.writeAndClose(response, result);
				return;
			}

			if (StrEx.isEmptyTrim(alipay) || !alipay.equals(alipay2)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "两次支付宝帐号不同!");
				Utls.writeAndClose(response, result);
				return;
			}

			boolean isMobile = TkitValidateCheck.isMobile(phone);
			if (!isMobile) {
				result = Utls.tipMap(result, Utls.Status_Erro, "您输入的手机号码有误！");
				Utls.writeAndClose(response, result);
				return;
			}

			// 验证邮箱
			boolean isEmail = TkitValidateCheck.isEmail(qqemail);
			if (!isEmail) {
				result = Utls.tipMap(result, Utls.Status_Erro, "您的QQ邮箱格式不对！");
				Utls.writeAndClose(response, result);
				return;
			}

			Account newAccount = AccountEntity.getByPhone(phone);
			if (newAccount != null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "该手机号码已经注册了!");
				Utls.writeAndClose(response, result);
				return;
			}

			newAccount = AccountEntity.getByEmail(qqemail);
			// 验证邮箱
			if (newAccount != null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "您的QQ邮箱已被注册了！");
				Utls.writeAndClose(response, result);
				return;
			}

			newAccount = AccountEntity.getByLgid(lgid);
			if (newAccount != null) {
				lgid = PStr.str(DateEx.now(), "_", phone);
			}

			int type = 3;
			int status = 0;
			Date createtime = DateEx.nowDate();
			newAccount = Account.newAccount(0, lgid, phone, qqemail, lgpwd2,
					type, status, createtime, createtime);
			newAccount = newAccount.insert();
			if (newAccount == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "插入帐号数据失败!");
				Utls.writeAndClose(response, result);
				return;
			}

			int volume = 0;
			double money = 0;
			Date endtime = DateEx.addDay(createtime, 7);
			Agent agent = Agent.newAgent(0, newAccount.getId(), uname, code,
					province, city, seat, qqemail, need, goodness, volume,
					money, money, createtime, endtime, status, status, "",
					alipay, false);
			agent.insert();
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "数据异常!");
		}
		Utls.writeAndClose(response, result);
	}

	// ==== 登录验证
	private Account verify_login(HttpServletResponse response,
			Map<String, String> mapPars, Map result) {
		String lgid = MapEx.getString(mapPars, "lgid");
		String lgpwd = MapEx.getString(mapPars, "lgpwd");
		if (StrEx.isEmpty(lgid)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "帐号不能为空!");
			Utls.writeAndClose(response, result);
			return null;
		}

		if (StrEx.isEmpty(lgpwd)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "密码不能为空!");
			Utls.writeAndClose(response, result);
			return null;
		}

		Account v = AccountEntity.getAccount(lgid);

		if (v == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "帐号不存在!");
			Utls.writeAndClose(response, result);
			return null;
		}

		if (!v.getLgpwd().equals(lgpwd)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "密码不正确!");
			Utls.writeAndClose(response, result);
			return null;
		}
		return v;
	}

	/*** 学习中心登录 type:2 **/
	@RequestMapping("/loginLhub")
	public void loginLhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request);
		Account v = verify_login(response, mapPars, result);
		if (v == null)
			return;
		if (v.getType() != 2) {
			result = Utls.tipMap(result, Utls.Status_Erro, "帐号不存在!");
			Utls.writeAndClose(response, result);
			return;
		}
		String code = "";
		try {
			code = Encrypt.encodeInt(v.getId());
		} catch (Exception e) {
		}
		result = Utls.tipMap(result, Utls.Status_Success, "", code);
		Utls.writeAndClose(response, result);
	}

	/*** 代理商登录 type:3 **/
	@RequestMapping("/loginAgent")
	public void loginAgent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request);
		Account v = verify_login(response, mapPars, result);
		if (v == null)
			return;

		if (v.getType() != 3) {
			result = Utls.tipMap(result, Utls.Status_Erro, "帐号不存在!");
			Utls.writeAndClose(response, result);
			return;
		}

		String code = "";
		try {
			code = Encrypt.encodeInt(v.getId());
		} catch (Exception e) {
		}
		result = Utls.tipMap(result, Utls.Status_Success, "", code);
		Utls.writeAndClose(response, result);
	}
}
