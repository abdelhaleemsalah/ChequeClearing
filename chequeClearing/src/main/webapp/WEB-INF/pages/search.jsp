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
    <link href="resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/styles_search.css" type="text/css" rel="stylesheet"/>
    <script src="resources/js/jquery-1.7.1.min.js"></script>
    <script src="resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="base" class="">

	
	  <form id="searchForm" name="tformest"  action="<c:url value="/SearchResult" />"  method="post"  >
	      <!-- Unnamed (Text Field) -->
	      <div id="u6" class="ax_default text_field">
	        <input id="u6_input" type="number" name="chequeSRno" value="" maxlength="14"/>
	      </div>

	      <div id="u0" class="ax_default button" title="search">
	        <div id="u0_div" class=""></div>
	        <div id="u0_text" class="text ">
	          <input id="u7_button" name="search" type="submit" value="search" />
	        </div>
	      </div>
	  </form>

      <!-- Unnamed (Rectangle) -->
      <div id="u8" class="ax_default box_1">
        <div id="u8_div" class=""></div>
        <div id="u8_text" class="text ">
          <p style="font-size:25px;"><span style="font-family:'Arial Italic', 'Arial';font-weight:400;font-style:italic;font-size:30px;color:#003366;">&nbsp;Cheque Search:</span><span style="font-family:'Arial Bold', 'Arial';font-weight:700;font-style:normal;"> </span></p>
        </div>
      </div>
    </div>
</body>
</html>