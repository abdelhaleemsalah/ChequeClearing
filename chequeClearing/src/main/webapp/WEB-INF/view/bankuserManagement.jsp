<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
        <title><spring:message code="welcome.message"/></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <meta name="apple-mobile-web-app-capable" content="yes" />
		<link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
		<link href="./../resources/css/styles_bankuserManagement.css" type="text/css" rel="stylesheet"/>
		<link type="text/css" href="./../resources/css/axure_rp_page.css" rel="Stylesheet" />
	    <link type="text/css" href="./../resources/css/jquery-ui-themes.css" rel="Stylesheet" />
	    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
	    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
    </head>

    <body>
   		<spring:message code="search.submit" var="searchSubmit"/>
   																
	<form:form id="SearchResult"  method="post" enctype="multipart/form-data"  action="${pageContext.request.contextPath}/${username}/userCreationSummary"  modelAttribute="userform" >

      <!-- Unnamed (Rectangle) -->
      <div id="u10" class="ax_default label">
        <div id="u10_div" class=""></div>
        <div id="u10_text" class="text ">
          <p><span><spring:message code="userName.message"/> </span></p>
        </div>
      </div>

	<!-- Unnamed (Rectangle) -->
      <div id="u14" class="ax_default label">
        <div id="u14_div" class=""></div>
        <div id="u14_text" class="text ">
          <p><span><spring:message code="password.message"/> </span></p>
        </div>
      </div>

      
       <!-- Unnamed (Text Field) -->
      <spring:message code="password.title" var="passwordTitle"/>
      <div id="u18" class="ax_default text_field" >
      	<form:input path="password" id="u18_input" type="password" value="" title="${passwordTitle}"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u12" class="ax_default label">
        <div id="u12_div" class=""></div>
        <div id="u12_text" class="text ">
          <p><span><spring:message code="userRole.message"/></span></p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u15" class="ax_default label">
        <div id="u15_div" class=""></div>
        <div id="u15_text" class="text ">
          <p><span><spring:message code="customerId.message"/></span></p>
        </div>
      </div>
      
      
      <!-- Unnamed (Text Field) -->
      <spring:message code="userName.title" var="usernameTitle"/>
      <div id="u11" class="ax_default text_field" >
        <form:input path="username" id="u11_input" type="text" value="" title="${usernameTitle}"/>
      </div>

       <spring:message code="userRole.title" var="userRoleTitle"/>
       <!-- Unnamed (Droplist) -->
      <div id="u13" class="ax_default droplist" >
        <form:select id="u13_input" title="${userRoleTitle}" path="userRole">
          <option value="BANKUSER">BANKUSER</option>
          <option value="ENDUSER">ENDUSER</option>
        </form:select>
      </div>


       <spring:message code="nationalID.title" var="nationalIDTitle"/>
      <!-- Unnamed (Text Field) -->
      <div id="u7" class="ax_default text_field">
        <form:input path="userNationalId" id="u7_input" type="text" value="" title="${nationalIDTitle}"/>
      </div>
      
       <spring:message code="customerId.title" var="customerIdTitle"/>
      <!-- Unnamed (Text Field) -->
      <div id="u9" class="ax_default text_field">
        <form:input path="userId" id="u9_input" type="text" value="" title="${customerIdTitle}"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u8" class="ax_default label">
        <div id="u8_div" class=""></div>
        <div id="u8_text" class="text ">
          <p><span><spring:message code="nationalID.message"/></span></p>
        </div>
      </div>
        
		  
	<spring:message code="save.submit" var="saveSubmit"/>
      <div id="u16" class="ax_default button" >
        <div id="u16_text" class="text">
        <form id="saveForm" name="tformest"  action="<c:url value="/${username}/userCreationSummary" />" method="post"  >
        	<input id="registerSubmit" type="submit" value="${saveSubmit}" class="u16_div" />
    
        </form>
        </div>
      </div>
      
    </form:form>

    </body>

</html>