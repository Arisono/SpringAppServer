<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.util.*"%><!--使用Enumeration导入此包-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 查看请求头信息headers -->
<p> 查看请求头信息headers:</p>
<%
      Enumeration enu=request.getHeaderNames();//取得全部头信息
     while(enu.hasMoreElements()){//以此取出头信息
         String headerName=(String)enu.nextElement();
         String headerValue=request.getHeader(headerName);//取出头信息内容
 %>
         <h5><%=headerName%><font color="red">--></font>
         <font color="blue"><%=headerValue%></font></h5>
 <%        
     }
%>
</body>
</html>