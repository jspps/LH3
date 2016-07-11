package com.learnhall.logic.controll;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bowlong.image.ImgEx;
import com.bowlong.text.EncodingEx;
import com.bowlong.tool.TkitJsp;
import com.bowlong.util.ExceptionEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.learnhall.content.Svc;
import com.learnhall.db.entity.AdcoursesEntity;
import com.learnhall.logic.Utls;
import com.learnhall.logic.chn.alipayapi.handle.Handle4Alipay;
import com.learnhall.logic.chn.alipayapi.handle.Handle4Back;
import com.learnhall.logic.chn.alipayapi.handle.Handle4ExchangeBack;
import com.learnhall.logic.chn.alipayapi.handle.HandleStatus;
import com.learnhall.logic.json.Json4Upload;
import com.learnhall.timer.TimerNight;

@SuppressWarnings("rawtypes")
@Controller
public class HomeController {

	static final Log logger = Utls.getLog(HomeController.class);

	@RequestMapping("/home")
	public void home(ModelMap modelMap) {
		System.out.println("=====home======");
	}

	@RequestMapping("/overdue")
	public void expire(HttpServletRequest request, ModelMap modelMap) {
		String uri = request.getRequestURI();
		System.out.println(uri);
		modelMap.addAttribute("action_url", "sigle/login");
		System.out.println("=====overdue======");
	}

	/*** 取得城市列表的方法 **/
	@RequestMapping("/getCityJson")
	public void getCityJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		Map result = new HashMap();
		Map pars = Utls.getMapAllParams(request);
		int provincid = MapEx.getInt(pars, "provincid");
		Utls.getCitiesByPid("", provincid, modelMap);
		boolean isHas = modelMap.containsKey("cities");
		if (isHas) {
			result = Utls.tipMap(result, Utls.Status_Success, "cities",
					modelMap.get("cities"));
		} else {
			result = Utls.tipMap(result, Utls.Status_Erro, "没有城市！");
		}
		Utls.writeAndClose(response, result);
	}

	@RequestMapping("/upimg")
	public void getUploadUI(ModelMap modelMap) {
		modelMap.addAttribute("upUrl", "client/uploadImg");
	}

	/*** 设置上传文件的保存位置 **/
	@RequestMapping("/change4Load")
	public void change4Load(HttpServletRequest request, ModelMap modelMap) {
		Map map = Utls.getMapAllParams(request);
		boolean isWindows = MapEx.getBoolean(map, "isWindows");
		Json4Upload.isWindows = isWindows;
		StringBuffer buff = new StringBuffer();
		buff.append(TkitJsp.getUrlIPProject(request));
		if (map.containsKey("isInitMap")) {
			Json4Upload.isInitMap = MapEx.getBoolean(map, "isInitMap");
			buff.append(",初始化Map = ").append(Json4Upload.isInitMap);
		}
		buff.append(",windows = ").append(isWindows);
		buff.append(",getServletPath = ").append(request.getServletPath());
		modelMap.addAttribute("msg", buff.toString());
	}

	@RequestMapping("/msgEmpty")
	public String msgHtml(ModelMap modelMap) {
		modelMap.addAttribute("empty_msg", Utls.Msg4Empty);
		return "empty";
	}

	/*** 取得专业名字列表 **/
	@RequestMapping("/getNmMajors")
	public void getNmMajors(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		List<Map> list = AdcoursesEntity.getNmmajors(departid);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得层级列表 **/
	@RequestMapping("/getNmLevels")
	public void getNmLevels(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		String nmmajor = MapEx.getString(map, "nmMajor");
		List<String> list = AdcoursesEntity.getNmlevels(departid, nmmajor);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得专业列表 **/
	@RequestMapping("/getNmSubs")
	public void getNmSubs(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		String nmmajor = MapEx.getString(map, "nmMajor");
		String nmLevel = MapEx.getString(map, "nmLevel");
		List<String> list = AdcoursesEntity.getNmsubs(departid, nmmajor,
				nmLevel);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得考试范围列表 **/
	@RequestMapping("/getNmAreas")
	public void getNmAreas(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		String nmmajor = MapEx.getString(map, "nmMajor");
		String nmLevel = MapEx.getString(map, "nmLevel");
		String nmSub = MapEx.getString(map, "nmSub");
		List<String> list = AdcoursesEntity.getNmAreas(departid, nmmajor,
				nmLevel, nmSub);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得二维码 **/
	@RequestMapping("/getQRCode")
	public void getQRCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			ImgEx.outImg4QRCode(out, "http://1010xue.com/LH3/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*** 登出帐号 **/
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		TkitJsp.clearHttpSession(session);
		return "redirect:/";
	}

	/*** 支付宝充值回调 **/
	@RequestMapping("/notify_url")
	public void notify_url(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		boolean isHandle_Okey = Handle4Back.handle(request);
		if (isHandle_Okey) {
			Utls.writeAndClose(response, Handle4Alipay.SUCCESS, "");
		} else {
			Utls.writeAndClose(response, Handle4Alipay.ERROR, "");
		}
	}

	/*** 支付宝充值 跳转界面:购买套餐 **/
	@RequestMapping("/return_url")
	public String return_url(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			RedirectAttributes attr) {
		Map<String, String> mapPars = Utls.getMapAllParamsByHandle(request, "",
				"");

		Ref<HandleStatus> handleStatus = new Ref<HandleStatus>(
				new HandleStatus());

		Handle4Alipay.payBack(mapPars, handleStatus, false);

		if (handleStatus.val.verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码.
			String trade_status = MapEx.getString(mapPars, "trade_status");

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				attr.addAttribute("isClearShopCut", true);
			}
		}
		return "redirect:/sigle/learnBuy";
	}

	/*** 支付宝充值 跳转界面:购买考币 **/
	@RequestMapping("/return_url_buykbi")
	public String return_url_buykbi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			RedirectAttributes attr) {
		Map<String, String> mapPars = Utls.getMapAllParamsByHandle(request, "",
				"");

		Ref<HandleStatus> handleStatus = new Ref<HandleStatus>(
				new HandleStatus());

		Handle4Alipay.payBack(mapPars, handleStatus, false);

		if (handleStatus.val.verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码.
			String trade_status = MapEx.getString(mapPars, "trade_status");

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				attr.addAttribute("isReLogin", true);
				attr.addAttribute("curtid", handleStatus.val.getMarkerid());
			}
		}
		return "redirect:/sigle/accFunds";
	}

	/*** 支付宝充值 跳转界面:提问 **/
	@RequestMapping("/return_url_ask")
	public String return_url_ask(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			RedirectAttributes attr) {
		Map<String, String> mapPars = Utls.getMapAllParamsByHandle(request, "",
				"");

		Ref<HandleStatus> handleStatus = new Ref<HandleStatus>(
				new HandleStatus());

		Handle4Alipay.payBack(mapPars, handleStatus, false);

		if (handleStatus.val.verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码.
			String trade_status = MapEx.getString(mapPars, "trade_status");

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				attr.addAttribute("orderid", handleStatus.val.getOrderid());
			}
		}
		return "redirect:/sigle/handleNewQuesition";
	}

	/*** 支付宝充值 跳转界面:验证支付宝状态4学员 **/
	@RequestMapping("/return_url_4_customer")
	public String return_url_4_customer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			RedirectAttributes attr) {
		Map<String, String> mapPars = Utls.getMapAllParamsByHandle(request, "",
				"");

		Ref<HandleStatus> handleStatus = new Ref<HandleStatus>(
				new HandleStatus());

		Handle4Alipay.payBack(mapPars, handleStatus, false);

		if (handleStatus.val.verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码.
			String trade_status = MapEx.getString(mapPars, "trade_status");

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				attr.addAttribute("orderid", handleStatus.val.getOrderid());
			}
		}
		return "redirect:/sigle/handleAlipayBack";
	}

	/*** 支付宝批量转账-回调 **/
	@RequestMapping("/notify_url_exchange")
	public void notify_url_exchange(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		boolean isHandle_Okey = Handle4ExchangeBack.handle(request);
		if (isHandle_Okey) {
			Utls.writeAndClose(response, Handle4Alipay.SUCCESS, "");
		} else {
			Utls.writeAndClose(response, Handle4Alipay.ERROR, "");
		}
	}

	/*** 支付宝充值回调 是否记录日志 **/
	@RequestMapping("/notify_isdebug")
	public void notify_isdebug(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String, String> mapPars = Utls.getMapAllParams(request);
		boolean isDebug = MapEx.getBoolean(mapPars, "isdebug");
		Handle4Alipay.isDebug(isDebug);
		Utls.writeAndClose(response, Handle4Alipay.SUCCESS, "");
	}

	/*** 取得-转账的支付宝语句 **/
	@RequestMapping("/getHtml4ExchangeRmb")
	public void getHtml4ExchangeRmb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request);
		int exchangeid = MapEx.getInt(mapPars, "pars_id");
		String v = Handle4Alipay.handle4ExchangeRmb(exchangeid);
		Utls.writeAndClose(response, v, "");
	}

	/*** 排行榜-年份-排行榜 **/
	@RequestMapping("/rnk4Profit")
	public void rnk4Profit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request);
		int type = MapEx.getInt(mapPars, "type");
		boolean isClear = MapEx.getBoolean(mapPars, "isClear");
		try {
			TimerNight.getInstance().exce(isClear, type);
			Utls.writeAndClose(response, "排行榜-生成成功!", EncodingEx.UTF_8);
		} catch (Exception e) {
			Utls.writeAndClose(response, ExceptionEx.e2s(e), "");
		}
	}
	
	/*** 客户端特殊处理,购买套餐 **/
	@RequestMapping("/msgHandleShop")
	public void msgHandleShop(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request);
		int orderid = MapEx.getInt(mapPars, "orderid");
		Handle4Back.msgHandleShop(orderid);
		Utls.writeAndClose(response, "处理中,请登录查看", "UTF-8");
	}
}
