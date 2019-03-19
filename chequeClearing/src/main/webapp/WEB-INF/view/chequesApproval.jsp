<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="./../resources/css/styles_chequesApproval.css" type="text/css" rel="stylesheet"/>
    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<form:form id="SearchResult"  method="post" enctype="multipart/form-data" action="${flowExecutionUrl}" modelAttribute="formBean" >
	
	<div id="base" class="">

      <!-- Unnamed (Rectangle) -->
      <div id="u59" class="ax_default label">
        <div id="u59_div" class=""></div>
        <div id="u59_text" class="text ">
          <p><span>Serial Number:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u60" class="ax_default label">
        <div id="u60_div" class=""></div>
        <div id="u60_text" class="text ">
          <p><span>Due Date:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u61" class="ax_default label">
        <div id="u61_div" class=""></div>
        <div id="u61_text" class="text ">
          <p><span>Currency:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u62" class="ax_default label">
        <div id="u62_div" class=""></div>
        <div id="u62_text" class="text ">
          <p><span><spring:message code="chequeOwnerAccount.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u63" class="ax_default label">
        <div id="u63_div" class=""></div>
        <div id="u63_text" class="text ">
          <p><span>Amount:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u64" class="ax_default label">
        <div id="u64_div" class=""></div>
        <div id="u64_text" class="text ">
          <p><span>Pay to:</span></p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u48" class="ax_default label">
        <div id="u48_div" class=""></div>
        <div id="u48_text" class="text ">
          <p><span>Pay to Account:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u50" class="ax_default label">
        <div id="u50_div" class=""></div>
        <div id="u50_text" class="text ">
          <p><span>Bank ID:</span></p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u65" class="ax_default label">
        <div id="u65_div" class=""></div>
        <div id="u65_text" class="text ">
          <p><span><spring:message code="fromUserName.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u66" class="ax_default label">
        <div id="u66_div" class=""></div>
        <div id="u66_text" class="text ">
          <p>${formBean.chequeSerialNo}</p>
          <form:hidden path = "chequeSerialNo" value = "${formBean.chequeSerialNo}"/>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u67" class="ax_default label">
        <div id="u67_div" class=""></div>
        <div id="u67_text" class="text ">
          <p>${formBean.accountNumber}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u68" class="ax_default label">
        <div id="u68_div" class=""></div>
        <div id="u68_text" class="text ">
          <p>${formBean.paytoUsername}</p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u49" class="ax_default label">
        <div id="u49_div" class=""></div>
        <div id="u49_text" class="text ">
          <p>${formBean.paytoAccountNumber}</p>
        </div>
      </div>
      

      <!-- Unnamed (Rectangle) -->
      <div id="u51" class="ax_default label">
        <div id="u51_div" class=""></div>
        <div id="u51_text" class="text ">
          <p>${formBean.bankId}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u70" class="ax_default label">
        <div id="u70_div" class=""></div>
        <div id="u70_text" class="text ">
          <p>${formBean.chequeAmount}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u71" class="ax_default label">
        <div id="u71_div" class=""></div>
        <div id="u71_text" class="text ">
          <p>${formBean.chequeDueDate}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u72" class="ax_default label">
        <div id="u72_div" class=""></div>
        <div id="u72_text" class="text ">
          <p>${formBean.chequeCurrency}</p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u69" class="ax_default label">
        <div id="u69_div" class=""></div>
        <div id="u69_text" class="text ">
          <p>${formBean.customerName}</p>
        </div>
      </div>

	 <spring:message code="approve.submit" var="approveSubmit"/>
	 <spring:message code="reject.submit" var="rejectSubmit"/>
	 
      <!-- Unnamed (Rectangle) -->
      <div id="u73" class="ax_default button">
        <div id="u73_text" class="text">
           <input id="u7_button" type="submit" value="${approveSubmit}" class="u73_div" name="_eventId_Approve" 
		   <c:if test="${submitVisiablity == true}"><c:out value="disabled='disabled'"/></c:if> />
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u74" class="ax_default button">
        <div id="u74_text" class="text">
	       <input id="u7_button" type="submit" value="${rejectSubmit}" class="u74_div" name="_eventId_Reject"   
		   <c:if test="${submitVisiablity == true}"><c:out value="disabled='disabled'"/></c:if> />	
        </div>
      </div>

	 <div id="u79" class="ax_default button">
        <div id="u79_text" class="text ">
         	<!-- <input id="u7_button" type="submit" value="Submit" class="u79_div" name="_eventId_Submit" disabled="${enableSubmit}"/>	 -->
         	<input id="u7_button" type="submit" value="Submit" class="u79_div" name="_eventId_Submit" 
		    <c:if test="${submitVisiablity == false}"><c:out value="disabled='disabled'"/></c:if> />	
        </div>
      </div>
    
       <ul>
        <c:forEach var="file" items="${files}">
            <li id="u77_text"><a href="${file}" class="u77_text" >${file}</a></li>
        </c:forEach>
      </ul>
    
    </form:form>
    
</body>
</html>