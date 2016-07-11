package com.learnhall.logic.controll.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台界面-控制器-查看答案
 * 
 * @author Canyon
 * 
 */
@Controller
@RequestMapping("/doClient")
public class CSeeDJController {
	/*** 历史成绩弹出窗 **/
	@RequestMapping("/historyTotalWindow")
	public String historyTotalWindow(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "client/historyTotalWindow";
	}
	
	/*** 最新模考榜单 **/
	@RequestMapping("/resultLists")
	public String resultLists(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "client/resultLists";
	}
}
