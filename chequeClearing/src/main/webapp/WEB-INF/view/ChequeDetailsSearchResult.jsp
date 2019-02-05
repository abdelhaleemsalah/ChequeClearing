<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${retrievedCheques}" var="ChequeForm">     
	   <c:out value="${ChequeForm.status}"/>
	   <c:out value="${ChequeForm.payToUsername}"/>
	</c:forEach>
</body>
</html>