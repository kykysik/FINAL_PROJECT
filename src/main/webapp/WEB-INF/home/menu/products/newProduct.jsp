<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserMapImpl: kykysik
  Date: 02.09.18
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script></head>
<body>
<p style="color: red;">${error}</p>

<jsp:include page="/WEB-INF/home/header.jsp"/>

<form action="${pageContext.request.contextPath}/menu/product/add">
    <table>
        <tr>
            <th><fmt:message key="login.product.name" /></th>
            <td><input required type="text" name="nameProduct"></td>
        </tr>
        <tr>
            <th><fmt:message key="login.product.fats" /></th>
            <td><input type="number" required id="fats" name="fats"></td>
        </tr>
        <tr>
            <th><fmt:message key="login.product.proteins" /></th>
            <td><input type="number" required id="proteins" name="proteins"></td>
        </tr>
        <tr>
            <th><fmt:message key="login.product.carbohydrates" /></th>
            <td><input type="number" required id="carbohydrates" name="carbohydrates"></td>
        </tr>
    </table>
    <td><fmt:message key="login.button.submit" var="buttonValue"/>
        <input type="submit" name="submit" value="${buttonValue}"></td>
</form>
</body>
</html>
