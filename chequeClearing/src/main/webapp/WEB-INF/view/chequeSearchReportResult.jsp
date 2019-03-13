<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link type="text/css" href="./../resources/css/axure_rp_page.css" rel="Stylesheet" />
	<link type="text/css" href="./../resources/css/jquery-ui-themes.css" rel="Stylesheet" />
    <link href="./../resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./../resources/css/styles_chequeDetailsSearchResult.css" type="text/css" rel="stylesheet"/>
    <script src="./../resources/js/jquery-1.7.1.min.js"></script>
    <script src="./../resources/js/jquery-ui-1.8.10.custom.min.js"></script>
</head>
<body>
	
	<form id="searchForm" name="tformest"  method="post">
	
		<table class="table">
		  <tr>
		    <th class="th">Cheque Serial number</th>
		    <th class="th">Cheque Owner Name</th>
		    <th class="th">Cheque Amount</th>
		    <th class="th">Cheque Status</th>
		  </tr>
		  <c:forEach items="${retrievedCheques}" var="ChequeForm">
		  <tr>
		    <td class="td" style="text-decoration: underline;">
		    <a href="chequeSearchReportResultDetails?chequeSerialNo=${ChequeForm.chequeSrNo}">${ChequeForm.chequeSrNo}</a></td>
		    
		    <td class="td">${ChequeForm.fromUsername}</td>
		   	<td class="td">${ChequeForm.chequeAmount}</td>
		   	<td class="td">${ChequeForm.status}</td>
		  </tr>
		  </c:forEach>
		</table>
	</form>
</body>
</html>