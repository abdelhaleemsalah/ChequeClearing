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

	<form:form id="searchForm" name="tformestss"   action="${pageContext.request.contextPath}/${user}/ChequeDetailsSearchResult"  method="post"  modelAttribute="formBean" >
	
	<div id="base" class="">

	<c:url var="addUrl" value="/chequeClearing/ChequeDetailsSearchResult"/>
	
	  	<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		
		<c:if test="${not empty error}">
				<div class="error">
					<img src="./../resources/images/error_icon.png" class="img"/>
					<span><c:out value="${error}"/></span><br>
		   		</div>
		</c:if>
		
		
		   <!-- Unnamed (Rectangle) -->
	      <div id="u11" class="ax_default label">
	        <div id="u11_div" class=""></div>
	        <div id="u11_text" class="text ">
	        
	          <p><span><spring:message code="chequeSerialNumber.message"/></span></p>
	        </div>
	      </div>
	  
	  <spring:message code="chequeSerialNumber.title" var="chequeSerialNumberTitle"/>
	      <!-- Unnamed (Text Field) -->
	      <div id="u6" class="ax_default text_field">
	      		<form:input path="chequeSerialNo" id="u6_input" type="text" value="" title="${chequeSerialNumberTitle}"/> 	
	      		<!--<form:errors path="chequeSerialNo" cssClass="error"/>-->
	      </div>
	      
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
	   
	   <!-- Unnamed (Rectangle) -->
      <div id="u9" class="ax_default label">
        <div id="u9_div" class=""></div>
        <div id="u9_text" class="text ">
          <p><span><spring:message code="chequeStatus.message" /></span></p>
        </div>
      </div>
      
      <spring:message code="chequeStatus.title" var="chequeStatusTitle"/>
       <!-- Unnamed (Droplist) -->
      <div id="u10" class="ax_default droplist" title="${chequeStatus.title}">
      <form:select id="u10_input" title="${chequeStatus.title}" path="chequeStatus">
      	  <option value="SELECT">SELECT</option>
          <option value="REGISTERED">REGISTERED</option>
          <option value="PENDING REVIEW">PENDING REVIEW</option>
          <option value="REVIEW REJECTED">REVIEW REJECTED</option>
          <option value="REVIEW APPROVED">REVIEW APPROVED</option>
          <option value="SUBMITTED">SUBMITTED</option>
          <option value="COLLECTION APPROVED">COLLECTION APPROVED</option>
          <option value="COLLECTION REJECTED">COLLECTION REJECTED</option>
          <option value="COLLECTED">COLLECTED</option>
      </form:select>
       
      </div>
      
      <spring:message code="search.submit" var="searchSubmit"/>
      
      <div id="u0" class="ax_default button" >
        <div id="u0_text" class="text ">
        <form id="saveForm" name="tformest"  action="<c:url value="/ChequeDetailsSearchResult" />"  method="post"  >
				<input id="u7_button" type="submit" value="${searchSubmit}" class="u0_div" />		
		</form>
      </div>  
      </div>

      
    </div>
    </form:form>
</body>
</html>