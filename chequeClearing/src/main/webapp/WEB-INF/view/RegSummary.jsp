<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="./../resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles_regsummary.css" type="text/css" rel="stylesheet"/>
    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<title>Cheque Registeration Summary</title>
</head>
<body>
	<div id="base" class="">

      <!-- Unnamed (Rectangle) -->
      <div id="u58" class="ax_default heading_2">
        <div id="u58_div" class=""></div>
        <div id="u58_text" class="text ">
          <p><span>Cheque is registered sucessfully</span></p>
        </div>
      </div>
      
      <form:form id="searchForm" name="tformest"   action="${flowExecutionUrl}"  method="get"   modelAttribute="formBean" >
      
	      <div id="u4" class="ax_default button" title="Chequebook Registeration">
		        <div id="u4_text" class="text ">
		    
		          <input id="u7_button" name="_eventId_Reg" type="submit" value="Chequebook Registeration" class="u4_div" />
		        </div>
		  </div>
		  
	 </form:form>
      
    </div>
</body>
</html>