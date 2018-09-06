<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserMapImpl: kykysik
  Date: 30.08.18
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/home/header.jsp"/>
<form action="${pageContext.request.contextPath}/menu/product/edit">
<table>
    <tr><td>${product.getId()}</td></tr>
    <tr><td>${product.getName()}</td></tr>
    <tr><td><input id="fats" name="fats" value="${product.getFats()}"></td></tr>
    <tr><td><input id="proteins" name="proteins" value="${product.getProteins()}"></td></tr>
    <tr><td><input id="carbohydrates" name="carbohydrates" value="${product.getCarbohydrates()}"></td></tr>
    <tr><td>${product.getCalories()}</td></tr>

    <td><fmt:message key="login.button.submit" var="buttonValue"/>
    <input type="submit" name="submit" value="${buttonValue}">
</table>
</form>
</body>
</html>
