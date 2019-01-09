<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>


<head>
        <title>Hello World    !</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <meta name="apple-mobile-web-app-capable" content="yes" />
		<link href="./resources/css/styles.css" type="text/css" rel="stylesheet"/>
		<link href="./resources/css/styles_registeration.css" type="text/css" rel="stylesheet"/>
		<link href="./resources/css/styles_registerationConf.css" type="text/css" rel="stylesheet"/>
		<link type="text/css" href="./resources/css/axure_rp_page.css" rel="Stylesheet" />
	    <link type="text/css" href="./resources/css/jquery-ui-themes.css" rel="Stylesheet" />
	    <script src="./resources/js/jquery-1.7.1.min.js"></script>
	    <script src="./resources/js/jquery-ui-1.8.10.custom.min.js"></script>
    </head>
    
    <body>
   
    		<!-- For login portalUser -->
		<c:url value="/logout" var="logoutUrl" />
		<!--<form action="${logoutUrl}" method="post" id="logoutForm">-->
		<form:form id="hello"  method="post" action="hello"  modelAttribute="formBean" >
		
		
		
	<form:hidden path = "pageName" value = "hello" />
			
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		
		<script>
			function formSubmit() {
				document.getElementById("hello").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
    
   <!--  <form action="@{/logout}">-->
    <div id="base" class="">

      <!-- Unnamed (Rectangle) -->
      <div id="u6" class="ax_default box_1">
        <div id="u6_div" class=""></div>
        <div id="u6_text" class="text ">
          <p style="font-size:25px;"><span style="font-family:'Arial Italic', 'Arial';font-weight:400;font-style:italic;font-size:30px;">&nbsp;Cheque Registeration:</span><span style="font-family:'Arial Bold', 'Arial';font-weight:700;font-style:normal;"> </span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u7" class="ax_default text_field" title="from cheque serial number">
        <input id="u7_input" type="text" value="" title="from cheque serial number"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u8" class="ax_default label">
        <div id="u8_div" class=""></div>
        <div id="u8_text" class="text ">
          <p><span>Cheque Serial number from:</span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u9" class="ax_default text_field" title="to cheque serial number">
        <input id="u9_input" type="text" value="" title="to cheque serial number"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u10" class="ax_default label">
        <div id="u10_div" class=""></div>
        <div id="u10_text" class="text ">
          <p><span>Customer id:</span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u11" class="ax_default text_field" title="Cif Id">
        <input id="u11_input" type="text" value="" title="Cif Id"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u12" class="ax_default label">
        <div id="u12_div" class=""></div>
        <div id="u12_text" class="text ">
          <p><span>Branch ID:</span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u13" class="ax_default text_field" title="branch id">
        <input id="u13_input" type="text" value="" title="branch id"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u14" class="ax_default label">
        <div id="u14_div" class=""></div>
        <div id="u14_text" class="text ">
          <p><span>Currency:</span></p>
        </div>
      </div>

      <!-- Unnamed (Droplist) -->
      <div id="u15" class="ax_default droplist" title="cheque currency">
        <select id="u15_input" title="cheque currency">
          <option value="EGP">EGP</option>
          <option value="USD">USD</option>
          <option value="EUR">EUR</option>
          <option value="INR">INR</option>
          <option value="NOK">NOK</option>
          <option value="CAD">CAD</option>
          <option value="AUD">AUD</option>
          <option value="DKK">DKK</option>
          <option value="IQD">IQD</option>
          <option value="KWD">KWD</option>
          <option value="CHF">CHF</option>
        </select>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u16" class="ax_default button" title="continue registeration">
        <div id="u16_div" class=""></div>
        <div id="u16_text" class="text ">
        <input type="submit" value="Save"/></div>  
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u17" class="ax_default label">
        <div id="u17_div" class=""></div>
        <div id="u17_text" class="text ">
          <p><span>Customer Name:</span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u18" class="ax_default text_field" title="branch id">
        <input id="u18_input" type="text" value="" title="branch id"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u19" class="ax_default label">
        <div id="u19_div" class=""></div>
        <div id="u19_text" class="text ">
           <form:label path="accountnumber"> <p><span>Cheque Serial number to:</span></p></form:label>
        
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u20" class="ax_default label">
        <div id="u20_div" class=""></div>
        <div id="u20_text" class="text ">
          <p><span>Account ID:</span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <div id="u21" class="ax_default text_field" title="branch id">
        <input id="u21_input" type="text" value="" title="branch id"/>
      </div>
    </div>
        
    </form:form>

    </body>














</html>