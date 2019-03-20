<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
        <title><spring:message code="welcome.message"/></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <meta name="apple-mobile-web-app-capable" content="yes" />
		<link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
		<link href="./../resources/css/styles_registeration.css" type="text/css" rel="stylesheet"/>
		<link type="text/css" href="./../resources/css/axure_rp_page.css" rel="Stylesheet" />
	    <link type="text/css" href="./../resources/css/jquery-ui-themes.css" rel="Stylesheet" />
	    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
	    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
    </head>

    <body>
   		<spring:message code="search.submit" var="searchSubmit"/>
  	
    		<!-- For login portalUser -->
		<spring:hasBindErrors name="formBean">
			<div class="error">
				<spring:bind path="formBean.*">
					<c:forEach items="${status.errorMessages}" var="error">
						<span><c:out value="${error}"/></span><br>
					</c:forEach>
				</spring:bind>
			</div>
		</spring:hasBindErrors>
			
		<form:form id="hello" action=" ${flowExecutionUrl}"  method="post" modelAttribute="formBean" >
		
		
	<!--<form:hidden path = "pageName" value = "hello" />-->
			
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		
		<script>
			function formSubmit() {
				document.getElementById("hello").submit();
			}
		</script>
    
   <!--  <form action="@{/logout}">-->
    <div id="base" class="">


	<spring:message code="chequeSerialNumberFrom.title" var="chequeSerialFromTitle"/>
      <!-- Unnamed (Text Field) -->
      <div id="u7" class="ax_default text_field">
        <form:input path="chequeSerialNoFrom" id="u7_input" type="text" value="" title="${chequeSerialFromTitle}"/>
      </div>



      <!-- Unnamed (Rectangle) -->
      <div id="u8" class="ax_default label">
        <div id="u8_div" class=""></div>
        <div id="u8_text" class="text ">
          <p><span><spring:message code="chequeSerialNumberFrom.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <spring:message code="chequeSerialNumberTo.title" var="chequeSerialToTitle"/>
      <div id="u9" class="ax_default text_field" >
      	<form:input path="chequeSerialNoTo" id="u9_input" type="text" value="" title="${chequeSerialToTitle}"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u10" class="ax_default label">
        <div id="u10_div" class=""></div>
        <div id="u10_text" class="text ">
          <p><span><spring:message code="customerId.message"/> </span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <spring:message code="customerId.title" var="CustomerIdTitle"/>
      <div id="u11" class="ax_default text_field" >
        <form:input path="customerId" id="u11_input" type="text" value="" title="${CustomerIdTitle}"/>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u14" class="ax_default label">
        <div id="u14_div" class=""></div>
        <div id="u14_text" class="text ">
          <p><span><spring:message code="Currency.message"/>  </span></p>
        </div>
      </div>

      <!-- Unnamed (Droplist) -->
      <spring:message code="Currency.title" var="currencyTitle"/>
      <div id="u15" class="ax_default droplist">
        <form:select id="u15_input" title="${currencyTitle}" path="chequeCurrency">
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
        </form:select>
      </div>
<spring:message code="save.submit" var="saveSubmit"/>
  <spring:message code="continueRegisteration.title" var="continueRegisterationTitle"/>
      <!-- Unnamed (Rectangle) -->
      <div id="u16" class="ax_default button" title="${continueRegisterationTitle}">
        <div id="u16_text" class="text ">
			<input id="submit"  name="_eventId_Submit" type="submit" value="${saveSubmit}" class="u16_div" />
<script type="text/javascript">
						Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'submit', event:'onclick'}));
						Spring.addDecoration(new Spring.AjaxEventDecoration({elementId:'submit',event:'onclick',formId:'formBean'}));
					</script>
      
        
       <!-- 
           <form id="saveForm" name="tformest"  action="<c:url value="/Registeration" />"  method="post"  >
						<input id="requestReaderSubmit" type="submit" value="${saveSubmit}" class="u16_div" />
						<form:hidden path = "pageName" value = "hello" />
		   </form> --> 
      
      </div>  
      </div>
      
      
      
      
      		

      <!-- Unnamed (Rectangle) -->
      <div id="u17" class="ax_default label">
        <div id="u17_div" class=""></div>
        <div id="u17_text" class="text ">
          <p><span><spring:message code="customerName.message"/></span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <spring:message code="customerName.title" var="customerNameTitle"/>
      <div id="u18" class="ax_default text_field" >
      	<form:input path="customerName" id="u18_input" type="text" value="" title="${customerNameTitle}"/>
      	<script type="text/javascript">
							Spring.addDecoration(new Spring.ElementDecoration({
								elementId : "customerName",
								widgetType : "dijit.form.ValidationTextBox",
								widgetAttrs : { required : true }}));
		</script>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u19" class="ax_default label">
        <div id="u19_div" class=""></div>
        <div id="u19_text" class="text ">
           <form:label path="accountNumber"> <p><span><spring:message code="chequeSerialNumberTo.message"/> </span></p></form:label>
        
        </div>
      </div>

      <!-- Unnamed (Rectangle) -->
      <div id="u20" class="ax_default label">
        <div id="u20_div" class=""></div>
        <div id="u20_text" class="text ">
          <p><span><spring:message code="accountId.message"/> </span></p>
        </div>
      </div>

      <!-- Unnamed (Text Field) -->
      <spring:message code="accountId.title" var="accountIdTitle"/> 
      <div id="u21" class="ax_default text_field" title="branch id"> 	
      	<form:input path="accountNumber" id="u21_input" type="text" value="" title="${accountIdTitle}"/>
      </div>
    </div>
        
    </form:form>

    </body>



</html>