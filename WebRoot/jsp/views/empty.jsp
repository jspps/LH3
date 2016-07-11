<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div style="clear: both;"></div>
<div style="text-align: center;width: 96%;height: 96%;">
	${empty_msg}
</div>
<div style="clear: both;"></div>