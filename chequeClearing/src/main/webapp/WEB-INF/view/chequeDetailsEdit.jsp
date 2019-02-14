<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="./../resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles_searchresult.css" type="text/css" rel="stylesheet"/>
    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<title><spring:message code="searchCheque.title"/></title>
</head>
<body>
	
	
	<form:form id="SearchResult"  method="post" enctype="multipart/form-data"  action="${pageContext.request.contextPath}/${user}/chequeDetailsEditSummary"  modelAttribute="formBean" >
	<div id="base" class="">


  	<table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>

      <!-- Unnamed (Rectangle) -->
	      <div id="u5" class="ax_default label">
	        <div id="u5_div" class=""></div>
	        <div id="u5_text" class="text ">
	          <p><span><spring:message code="chequeSerialNumber.message"/></span></p>
	        </div>
	      </div>
	  
	      <!-- Unnamed (Text Field) -->
	      <div id="u6" class="ax_default text_field">
	           <div id="u6_div" class=""></div>
        	<div id="u6_text" class="text ">
	        <p>${formBean.chequeSerialNo}</p>
	        </div>
	      </div>
	      
	      <!-- Unnamed (Rectangle) -->
	      <div id="u8" class="ax_default label">
	        <div id="u8_div" class=""></div>
	        <div id="u8_text" class="text ">
	          <p>Pay to username:</p>
	        </div>
	      </div>
	  
	      <!-- Unnamed (Text Field) -->
      <div id="u26" class="ax_default text_field">
     	<form:input path="paytoUsername" id="u26_input" type="text" value="${formBean.paytoUsername}"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u11" class="ax_default box_1">
        <div id="u11_div" class=""></div>
        <div id="u11_text" class="text ">
          <p style="font-size:25px;"><span style="font-family:'Arial Italic', 'Arial';font-weight:400;font-style:italic;font-size:30px;color:#003366;">&nbsp;Cheque Search:</span><span style="font-family:'Arial Bold', 'Arial';font-weight:700;font-style:normal;"> </span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u12" class="ax_default heading_3">
        <div id="u12_div" class=""></div>
        <div id="u12_text" class="text ">
          <p style="font-size:20px;"><span style="font-family:'Arial Italic', 'Arial';font-weight:400;font-style:italic;color:#003366;">&nbsp;<spring:message code="chequeIsRegistered.message"/>&nbsp; </span><span style="font-family:'Arial Bold', 'Arial';font-weight:700;font-style:normal;font-size:25px;"> </span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u13" class="ax_default text_field">
        <form:input path="chequeAmount" id="u13_input" type="text" value="${formBean.chequeAmount}"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u14" class="ax_default label">
        <div id="u14_div" class=""></div>
        <div id="u14_text" class="text ">
          <p><span><spring:message code="chequeAmount.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u15" class="ax_default label">
        <div id="u15_div" class=""></div>
        <div id="u15_text" class="text ">
          <p><span><spring:message code="chequeDueDate.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u16" class="ax_default text_field">
        <form:input path="chequeDueDate" id="u16_input" type="date" value=""  />
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u17" class="ax_default label">
        <div id="u17_div" class=""></div>
        <div id="u17_text" class="text ">
          <p><span><spring:message code="fromUserName.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u18" class="ax_default label">
        <div id="u18_div" class=""></div>
        <div id="u18_text" class="text ">
            <p>${formBean.customerName}</p>
        </div>
      </div>

		
      <!-- Unnamed (Rectangle) -->
      <div id="u19" class="ax_default label">
        <div id="u19_div" class=""></div>
        <div id="u19_text" class="text ">
          <p><span><spring:message code="accountId.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u20" class="ax_default label">
        <div id="u20_div" class=""></div>
        <div id="u20_text" class="text ">
          <p>${formBean.accountNumber}</p>
        
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u21" class="ax_default label">
        <div id="u21_div" class=""></div>
        <div id="u21_text" class="text ">
          <p><span><spring:message code="chequeCurrency.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u22" class="ax_default label">
        <div id="u22_div" class=""></div>
        <div id="u22_text" class="text ">
          <p>${formBean.chequeCurrency}</p>
         
        </div>
      </div>

<spring:message code="save.submit" var="saveSubmit"/>
      <div id="u0" class="ax_default button" >
        <div id="u0_text" class="text">
        <form id="searchsaveForm" name="ttformest" action="<c:url value="/chequeDetailsEditSummary" />" method="post">
        <input id="registerSubmit" type="submit" value="${saveSubmit}" class="u0_div" />
    
        </form>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u24" class="ax_default label">
        <div id="u24_div" class=""></div>
      </div>

      <!-- Unnamed (Radio Button) -->
      <div id="u25" class="ax_default radio_button">
        <form:checkbox path="crossed" id="u25_input" value="false"/><spring:message code="crossedCheque.message" />
      </div>
    </div>
    
    </form:form>
</body>
</html>