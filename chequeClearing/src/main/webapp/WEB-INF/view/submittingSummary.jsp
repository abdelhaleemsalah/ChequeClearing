<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="./../resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles_submittingSummary.css" type="text/css" rel="stylesheet"/>
    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
	   <title><spring:message code="saveChequeSummary.message"/></title>
</head>
<body>

	<div id="base" class="">

      <!-- Unnamed (Rectangle) -->
      <div id="u39" class="ax_default label">
        <div id="u39_div" class=""></div>
        <div id="u39_text" class="text ">
          <p><span><spring:message code="chequeAmount.message"/> </span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u40" class="ax_default label">
        <div id="u40_div" class=""></div>
        <div id="u40_text" class="text ">
          <p><span><spring:message code="chequeDueDate.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u41" class="ax_default label">
        <div id="u41_div" class=""></div>
        <div id="u41_text" class="text ">
          <p><span><spring:message code="fromUserName.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u42" class="ax_default label">
        <div id="u42_div" class=""></div>
        <div id="u42_text" class="text ">
          <p>${formBean.customerName}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u43" class="ax_default label">
        <div id="u43_div" class=""></div>
        <div id="u43_text" class="text ">
          <p><span><spring:message code="accountId.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u44" class="ax_default label">
        <div id="u44_div" class=""></div>
        <div id="u44_text" class="text ">
          <p>${formBean.accountNumber}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u45" class="ax_default label">
        <div id="u45_div" class=""></div>
        <div id="u45_text" class="text ">
          <p><span><spring:message code="chequeCurrency.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u46" class="ax_default label">
        <div id="u46_div" class=""></div>
        <div id="u46_text" class="text ">
          <p>${formBean.chequeCurrency}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u47" class="ax_default label">
        <div id="u47_div" class=""></div>
        <div id="u47_text" class="text ">
          <p>${formBean.chequeAmount}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u48" class="ax_default label">
        <div id="u48_div" class=""></div>
        <div id="u48_text" class="text ">
          <p>${formBean.chequeDueDate}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u49" class="ax_default heading_3">
        <div id="u49_div" class=""></div>
        <div id="u49_text" class="text ">
          <p style="font-size:20px;"><span style="font-family:'Arial Italic', 'Arial';font-weight:400;font-style:italic;color:#003366;">&nbsp;<spring:message code="chequeIsSavedSuccefully.message"/></span><span style="font-family:'Arial Bold', 'Arial';font-weight:700;font-style:normal;font-size:25px;"> </span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <spring:message code="register.submit" var="registerSubmit"/>
      <div id="u50" class="ax_default button">
        <div id="u50_text" class="text ">
        	<form id="searchsaveForm" name="ttformest" action="<c:url value="/search" />" method="get">
          		<input id="search" type="submit" value="Cheque Search" class="u50_div" />
       		</form>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u51" class="ax_default label">
        <div id="u51_div" class=""></div>
        <div id="u51_text" class="text ">
          <p><span><spring:message code="crossedCheque.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u52" class="ax_default label">
        <div id="u52_div" class=""></div>
        <div id="u52_text" class="text ">
          <p>${formBean.crossed}</p>
        </div>
      </div>
    </div>
    
</body>
</html>