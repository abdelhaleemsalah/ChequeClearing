<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
<link href="./../resources/css/styles_dashboard.css" type="text/css" rel="stylesheet"/>

<title>Insert title here</title>
</head>
<body>


	<form action="<c:url value="/logout" />" method="post" class="inline">
		<div class="span-4 welcomelabel">
			<c:if test="${pageContext.request.userPrincipal != null}">
				Welcome, ${pageContext.request.userPrincipal.name}
			</c:if>
		</div>
		<div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<button id="submit" type="submit" class="u90_div">Logout</button>
		</div>
	</form>

  
<div class="tabs">
	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/Registeration" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="tablabel" value="Chequebook Registeration" />
				 <input type="hidden" name="username" value="${username}" /> 				
		  </form>
	</sec:authorize>
	
  	<sec:authorize access="hasRole('ROLE_USER')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/search" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="tablabel" value="Customer Cheque Upload" />				
		  </form>
	</sec:authorize>
	
	
  	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/ChequeDetailsSearch" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="tablabel" value="Bank Cheque Reviewing" />				
		  </form>
  	</sec:authorize>
  
  	<sec:authorize access="hasRole('ROLE_USER')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/chequeSearchReport" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="tablabel" value="Bank Cheque Report" />				
		  </form>
  	</sec:authorize>
  
  	<sec:authorize access="hasRole('ROLE_SUPER_USER')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/bankuserManagement" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="tablabel" value="Bank Users Management" />				
		  </form>
  	</sec:authorize>
  
</div>
	
</body>
</html>