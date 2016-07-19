<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习导航</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
  
  <link href="<c:url value="/resources/jsonview/jquery.jsonview.css" />" rel="stylesheet" type="text/css"/>
  <script src="<c:url value="/resources/jsonview/jquery.min.js" />"></script>
  <script src="<c:url value="/resources/jsonview/jquery.jsonview.js" />"></script>
 
  <script>
  	$( function() {
   		 $( "#tabs" ).tabs();
   } );
   $( function() {
     	$( "#accordion_left" ).accordion();
     	$( "#accordion_center" ).accordion();
        $( "#accordion_right" ).accordion();
        $( "#accordion_right_center" ).accordion();
  	} );
  	
  	var json = {"hey": "guy","anumber": 243,"anobject": {"whoa": "nuts","anarray": [1,2,"thr<h1>ee"], "more":"stuff"},"awesome": true,"bogus": false,"meaning": null, "japanese":"明日がある。", "link": "http://jsonview.com", "notLink": "http://jsonview.com is great"};

	$(function() {
	  $("#json_login").JSONView(json);
	  // with options
	  //$("#json-collasped").JSONView(json, { collapsed: true });
	});
  </script>
  
</head>
<body>
 <%
      String sessionId = (String)request.getSession().getAttribute("sessionId");
  %>
<div id="header">
<a class="ui-button ui-widget ui-corner-all" href="<c:url value="/" />">进入英文界面</a>
<div id="header_right" style="float: right;">
<a style="float: left; margin: 5px" href="">用户名：<%=sessionId%></a>
<a style="float: left;" class="ui-button ui-widget ui-corner-all" href="<c:url value="/client/login" />">登陆</a>
<a style="float: right;" class="ui-button ui-widget ui-corner-all" href="<c:url value="/client/logout" />">注销</a>
</div>
<p>
</div>

<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Cookie与Session</a></li>
    <li><a href="#tabs-2">移动客户端Token机制</a></li>
    <li><a href="#tabs-3">Spring MVC入门</a></li>
    <li><a href="#tabs-4">Spring MVC接口设计</a></li>
    <li><a href="#tabs-5">Spring 日志处理</a></li>
    <li><a href="#tabs-6">Spring 事物处理</a></li>
    <li><a href="#tabs-7">Spring 安全框架</a></li>
    <li><a href="#tabs-8">Spring 定时任务</a></li>
    <li><a href="#tabs-9">Spring 开源项目</a></li>
    <li><a href="#tabs-10">Spring 书签</a></li>
    <li><a href="#tabs-11">加入我们</a></li>
  </ul>
  
  <div id="tabs-1" style="margin:0; padding:0;overflow:hidden;zoom:1;">
  <div id="accordion_left" style="width:30%; float:left;padding-right: 5px">
  <h3>查看登陆状态</h3>
  <div id="json_login">
    <p>
    Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer
    ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit
    amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut
    odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.
    </p>
  </div>
  <h3>Section 2</h3>
  <div>
    <p>
    Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet
    purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor
    velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In
    suscipit faucibus urna.
    </p>
  </div>
  <h3>Section 3</h3>
  <div>
    <p>
    Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
    Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero
    ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis
    lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.
    </p>
    <ul>
      <li>List item one</li>
      <li>List item two</li>
      <li>List item three</li>
    </ul>
  </div>
  <h3>Section 4</h3>
  <div>
    <p>
    Cras dictum. Pellentesque habitant morbi tristique senectus et netus
    et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in
    faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia
    mauris vel est.
    </p>
    <p>
    Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per
    inceptos himenaeos.
    </p>
  </div>
</div>
   <div id="accordion_center" style="width:39%;float:left;">
   <h3>cookies知识</h3>
   <div>
   <p>
    Cookie是在浏览器缓存的数据
   </p>
   </div>
  </div>
   <div id="accordion_right" style="width:30%; float:right;padding-left: 5px">
   <h3>cookies知识</h3>
   <div>
   <p>
    Cookie是在浏览器缓存的数据
   </p>
   </div>
  </div>
   <div id="accordion_right_center" style="width:30%; float:right;padding-left:5px">
   <h3>文献</h3>
  </div>
  </div>
  <div id="tabs-2">
    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
  </div>
  <div id="tabs-3">
    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
  </div>
</div>
</body>
</html>