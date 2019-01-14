<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
        <title>Cheque Registeration Confirmation</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <meta name="apple-mobile-web-app-capable" content="yes" />
		<link href="./resources/css/styles.css" type="text/css" rel="stylesheet"/>
		<link href="./resources/css/styles_registeration.css" type="text/css" rel="stylesheet"/>
	     <link href="./resources/css/styles_regconfirmation.css" type="text/css" rel="stylesheet"/>
		<link type="text/css" href="./resources/css/axure_rp_page.css" rel="Stylesheet" />
	    <link type="text/css" href="./resources/css/jquery-ui-themes.css" rel="Stylesheet" />
	    <script src="./resources/js/jquery-1.7.1.min.js"></script>
	    <script src="./resources/js/jquery-ui-1.8.10.custom.min.js"></script>
    </head>
    
    <body>
    
    		<!-- For login portalUser -->
		<c:url value="/logout" var="logoutUrl" />
		<!--<form action="${logoutUrl}" method="post" id="logoutForm">-->
		<form:form id="RegConfirmation"  method="post" modelAttribute="formBean" >
		
			<div id="base" class="">
      <!-- Unnamed (Rectangle) -->
      <div id="u38" class="ax_default heading_2">
        <div id="u38_div" class=""></div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u39" class="ax_default box_1">
        <div id="u39_div" class=""></div>
        <div id="u39_text" class="text ">
          <p style="font-size:25px;"><span style="font-family:'Arial Italic', 'Arial';font-weight:400;font-style:italic;font-size:30px;">&nbsp;Cheque Registeration&nbsp; Confirmation:</span><span style="font-family:'Arial Bold', 'Arial';font-weight:700;font-style:normal;"> </span></p>
        </div>
      </div>
      
      <!--  Unnamed (Rectangle) -->
      <div id="u40" class="ax_default label">
        <div id="u40_div" class=""></div>
        <div id="u40_text" class="text ">
          <p><span>Cheque Serial number from:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u41" class="ax_default label">
        <div id="u41_div" class=""></div>
        <div id="u41_text" class="text ">
          <p><span>Customer id:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u42" class="ax_default label">
        <div id="u42_div" class=""></div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u43" class="ax_default label">
        <div id="u43_div" class=""></div>
        <div id="u43_text" class="text ">
          <p><span>Currency:</span></p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u44" class="ax_default label">
        <div id="u44_div" class=""></div>
        <div id="u44_text" class="text ">
          <p><span>Customer Name:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u45" class="ax_default label">
        <div id="u45_div" class=""></div>
        <div id="u45_text" class="text ">
          <p><span>Cheque Serial number to:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u46" class="ax_default button" title="continue registeration">
        <div id="u46_text" class="text ">
        <form id="saveForm" name="tformest"  action="<c:url value="/hello" />"  method="get"  >
          <input id="requestReaderSubmit" type="submit" value="Register" class="u46_div" />
          <form:hidden path = "pageName" value = "RegSummary" />
        </form>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u47" class="ax_default button" title="continue registeration">
        <div id="u47_text" class="text ">
        <form id="requestReader" name="tformest"  action="<c:url value="/hello" />"  method="post"  >
			<input id="requestReaderSubmit" type="submit" value="Back" class="u47_div" />
			<form:hidden path = "pageName" value = "RegConfirmation" />
		</form>
        
        
   
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <!-- ID -->
      <div id="u48" class="ax_default label">
        <div id="u48_div" class=""></div>
        <div id="u48_text" class="text ">
          <p>${formBean.customerid}<br></span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u49" class="ax_default label">
        <div id="u49_div" class=""></div>
        <div id="u49_text" class="text ">
          <p>${formBean.customername}</p><p><span><br></span></p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u50" class="ax_default paragraph">
        <div id="u50_div" class=""></div>
        <div id="u50_text" class="text ">
          <p>${formBean.bankid}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u51" class="ax_default paragraph">
        <div id="u51_div" class=""></div>
        <div id="u51_text" class="text ">
          <p>${formBean.chequeserialNOfrom}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u52" class="ax_default paragraph">
        <div id="u52_div" class=""></div>
        <div id="u52_text" class="text ">
          <p>${formBean.chequeserialNOto}</p>
        </div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u53" class="ax_default paragraph">
        <div id="u53_div" class=""></div>
        <div id="u53_text" class="text ">
          <p>${formBean.chequecurrency}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u54" class="ax_default label">
        <div id="u54_div" class=""></div>
        <div id="u54_text" class="text ">
          <p><span>Branch Id:</span></p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u55" class="ax_default label">
        <div id="u55_div" class=""></div>
      </div>
      
      <!-- Unnamed (Rectangle) -->
      <div id="u56" class="ax_default paragraph">
        <div id="u56_div" class=""></div>
        <div id="u56_text" class="text ">
          <p>${formBean.accountnumber}</p>
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u57" class="ax_default label">
        <div id="u57_div" class=""></div>
        <div id="u57_text" class="text ">
          <p><span>Account ID:</span></p>
        </div>
      </div>
    </div>
      
        
 </form:form> 
    
    
    </body>
</html>