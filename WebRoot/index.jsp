<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <!-- 传递给StringContentServlet.do做处理，传递方式是get -->
    <form action="StringContentServlet.do" method="get">
    	<input name="UserName" type="text" value="" />
    	<input name="PassWord" type="password" value="" />
    	<!-- 这个是在网页浏览器测试用的 -->>
    	<input name="Finish" type="submit" value="Finish" />
    </form>>
    
    <!-- 传递给ByteFileContentServlet.do做处理，传递方式post，后面的参数是上传文件必须要添加的参数 -->
    <form action="ByteFileContentServlet.do" method="post" enctype="multipart/form-data">
    	<input name="File" type="file" value="" />
    	<!-- 这个是在网页浏览器测试用的 -->
    	<input name="Finish" type="submit" value="Finish">
    </form>>
    
    <form action="DownloadMidi.do" method="get">
    	<input name="Download" type="submit" value="Midi" />
    </form>form>
  </body>
</html>
