package com.learnhall.logic.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.learnhall.content.AppContext;
import com.learnhall.content.Svc;
import com.learnhall.logic.SessionKeys;

/*** 拦截器 - 个人中心 - 验证登录 **/
@Repository
public class SigleLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String[] noFilters = new String[] { "doSigle/login", "doSigle/welcome",
				"doSigle/go2LoginView", "doSigle/doLogin" };
		String uri = request.getRequestURI();
		int index = uri.indexOf("/doSigle");
		try {
			AppContext.loadAll();
		} catch (Exception e) {
		}
		if (index != -1) {
			boolean beFilter = true;
			for (String s : noFilters) {
				if (uri.indexOf(s) != -1) {
					beFilter = false;
					break;
				}
			}

			if (beFilter) {
				HttpSession session = request.getSession();
				Object objUser = session.getAttribute(SessionKeys.Customer);
				if (objUser == null) {
					// PrintWriter out = response.getWriter();
					// StringBuilder builder = new StringBuilder();
					// builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
					// builder.append("alert(\"页面过期，请重新登录\");");
					// builder.append("window.top.location.href=\"");
					// builder.append("login");
					// builder.append("\";</script>");
					// out.print(builder.toString());
					// out.close();

					// TkitJsp.clearHttpSession(session);
					String path = request.getContextPath();
					response.sendRedirect(path + "/overdue");
					return false;
				}
			}
		}

		debugLog(false, request);
		return super.preHandle(request, response, handler);
	}

	private void debugLog(boolean isDebug, HttpServletRequest request) {
		// 操作日志(可以添加日志记录)
		if (isDebug) {
			String method = request.getPathInfo();
			Map<String, String> parsMap = Svc.getMapAllParams(request);
			System.out.println(method + "=" + parsMap.toString());
		}
	}

}
