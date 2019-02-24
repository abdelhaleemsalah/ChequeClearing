
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/styles_login.css" type="text/css" rel="stylesheet"/>
    <script src="resources/js/jquery-1.7.1.min.js"></script>
    <script src="resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<form name='loginForm' action="<c:url value='/login' />" method='POST'>
	<div id="base" class="">
<spring:message code="login.submit" var="labelSubmit"/>
      <!-- Unnamed (Rectangle) -->
      <div id="u0" class="ax_default button" title="login">
        <div id="u0_text" class="text ">
        
          <input name="submit" type="submit" class="u0_div" value="${labelSubmit}" title="${labelSubmit}"/>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u1" class="ax_default heading_1">
        <div id="u1_div" class=""></div>
        <div id="u1_text" class="text ">
          <p><span><spring:message code="welcome.message"/></span></p>
        </div>
      </div>
      
      

      <!-- Unnamed (Rectangle) -->
      <div id="u2" class="ax_default label">
        <div id="u2_div" class=""></div>
        <div id="u2_text" class="text ">
          <p><span><spring:message code="userName.message"/> </span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u3" class="ax_default label">
        <div id="u3_div" class=""></div>
        <div id="u3_text" class="text ">
          <p><span><spring:message code="password.message"/> </span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u4" class="ax_default text_field" title="username">
        <input id="u4_input" type="text" name="username" title="username"/>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u5" class="ax_default text_field" title="password">
        <input id="u5_input" type="password" name="password" title="password" maxlength="15"/>
        
      </div>
   
    </div> 
	</form>
	
	
</body>
</html>