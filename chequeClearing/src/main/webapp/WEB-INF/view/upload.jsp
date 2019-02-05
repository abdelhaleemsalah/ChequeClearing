<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/chequeClearing/">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="./resources/js/jquery-3.3.1.min.js"></script>
    <script src="./resources/js/sockjs.min.js"></script>
    <script src="./resources/js/stomp.min.js"></script>
    <script src="./resources/js/app.js"></script>
</head>
<body>
<div>
    <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${merchant}/upload" id="uploadForm">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>
    </form>
</div>

<%--<div>
    <ul>ws
        <li th:each="file : ${files}">
            <a th:href="${file}" th:text="${file}"/>
        </li>
    </ul>
</div>--%>

<c:if test="${not empty files}">
    <h2>Columns</h2>
    <ul>
        <c:forEach var="file" items="${files}">
            <li><a href="${file}">${file}</a></li>
        </c:forEach>
    </ul>

</c:if>
</body>
</html>
