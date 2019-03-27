<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link type="text/css" href="./../resources/css/axure_rp_page.css" rel="Stylesheet" />
	<link type="text/css" href="./../resources/css/jquery-ui-themes.css" rel="Stylesheet" />
    <link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles_search.css" type="text/css" rel="stylesheet"/>
    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<title><spring:message code="searchCheque.title"/></title>
</head>
<body>

	<form:form id="searchForm" name="tformestss"   action="${pageContext.request.contextPath}/${user}/submittedChequesSearchResult"  method="post"  modelAttribute="formBean" >
	
	<div id="base" class="">

	<c:url var="addUrl" value="/chequeClearing/ChequeDetailsSearchResult"/>
	
	  	<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		
	      
	       <!-- Unnamed (Text Field) -->
	      <div id="u12" class="ax_default text_field" >
	      	<form:input path="chequeDueDate" id="u16_input" type="date" value="" title="cheque duedate"/>
	      
	      </div>
	
	      <!-- Unnamed (Rectangle) -->
	      <div id="u13" class="ax_default label">
	        <div id="u13_div" class=""></div>
	        <div id="u13_text" class="text ">
	          <p><span>  <spring:message code="chequeDueDate.message" /></span></p>
	        </div>
	      </div>
      
      
      <spring:message code="search.submit" var="searchSubmit"/>
      
      <div id="u0" class="ax_default button" >
        <div id="u0_text" class="text ">
        <form id="saveForm" name="tformest"  action="<c:url value="/submittedChequesSearchResult" />"  method="post"  >
				<input id="u7_button" type="submit" value="${searchSubmit}" class="u0_div" />		
		</form>
      
      </div>  
      </div>

      
    </div>
    </form:form>
</body>
</html>