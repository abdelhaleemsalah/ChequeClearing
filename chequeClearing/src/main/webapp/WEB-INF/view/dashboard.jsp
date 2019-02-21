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
	
<div class="tabs">
	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/Registeration" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="label" value="Chequebook Registeration" />				
		  </form>
	</sec:authorize>
	
  	<sec:authorize access="hasRole('ROLE_USER')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/search" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="label" value="Customer Cheque Upload" />				
		  </form>
	</sec:authorize>
	
  	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/ChequeDetailsSearch" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="label" value="Bank Cheque Reviewing" />				
		  </form>
  	</sec:authorize>
  
  	<sec:authorize access="hasRole('ROLE_USER')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/chequeSearchReport" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="label" value="Bank Cheque Report" />				
		  </form>
  	</sec:authorize>
  
  	<sec:authorize access="hasRole('ROLE_SUPER_USER')" >
		  <form id="searchForm" name="tformest"  action="<c:url value="/${username}/bankuserManagement" />"  method="get">
				<input id="requestReaderSubmit" type="submit" class="label" value="Bank Users Management" />				
		  </form>
  	</sec:authorize>
  
</div>
	
</body>
</html>