<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link type="text/css" href="./resources/css/axure_rp_page.css" rel="Stylesheet" />
	<link type="text/css" href="./resources/css/jquery-ui-themes.css" rel="Stylesheet" />
    <link href="resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/styles_search.css" type="text/css" rel="stylesheet"/>
    <script src="resources/js/jquery-1.7.1.min.js"></script>
    <script src="resources/js/jquery-ui-1.8.10.custom.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="base" class="">

	<c:url var="addUrl" value="/chequeClearing/SearchResult"/>
	  <form:form id="searchForm" name="tformestss"   action="/chequeClearing/SearchResult"    method="post"  modelAttribute="formBean" >
	  	<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		
		   <!-- Unnamed (Rectangle) -->
	      <div id="u11" class="ax_default label">
	        <div id="u11_div" class=""></div>
	        <div id="u11_text" class="text ">
	          <p><span>Cheque Serial Number:</span></p>
	        </div>
	      </div>
	  
	      <!-- Unnamed (Text Field) -->
	      <div id="u6" class="ax_default text_field">
	      	<form:input path="chequeserialNO" id="u6_input" type="text" value="" title="Cheque Serial number "/>
	        
	      </div>
	      
	      <!-- Unnamed (Text Field) -->
	      <div id="u12" class="ax_default text_field" title="account number">
	      	<form:input path="accountnumber" id="u12_input" type="text" value="" title="Account number "/>
	      
	      </div>
	
	      <!-- Unnamed (Rectangle) -->
	      <div id="u13" class="ax_default label">
	        <div id="u13_div" class=""></div>
	        <div id="u13_text" class="text ">
	          <p><span>Account Number:</span></p>
	        </div>
	      </div>
	   
	   <!-- Unnamed (Rectangle) -->
      <div id="u9" class="ax_default label">
        <div id="u9_div" class=""></div>
        <div id="u9_text" class="text ">
          <p><span>Bank Code:</span></p>
        </div>
      </div>
      
       <!-- Unnamed (Droplist) -->
      <div id="u10" class="ax_default droplist" title="bank id">
      <form:select id="u10_input" title="Bank id" path="bankid">
           <option value="HSBC">HSBC</option>
          <option value="CIB">CIB</option>
          <option value="MISR">MISR</option>
          <option value="EDBE">EDBE</option>
          <option value="ABK">ABK</option>
        </form:select>
       
      </div>
      
      
      
      
      
            <div id="u0" class="ax_default button" title="continue registeration">
        <div id="u0_text" class="text ">
           <form id="saveForm" name="tformest"  action="<c:url value="/SearchResult" />"  method="post"  >
						<input id="u7_button" type="submit" value="Save" class="u0_div" />
					
		   </form>
      
      </div>  
      </div>
      
      
	          
	
	      
	  </form:form>

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