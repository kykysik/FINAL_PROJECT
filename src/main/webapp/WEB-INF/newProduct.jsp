<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 02.09.18
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<p style="color: red;">${error}</p>

<jsp:include page="/WEB-INF/_menu.jsp"/>

<form action="${pageContext.request.contextPath}/menu/product/add">
    <table>
        <tr>
            <th>Name</th>
            <td><input required type="text" name="nameProduct"></td>
        </tr>
        <tr>
            <th>Fats</th>
            <td><input type="number" required id="fats" name="fats"></td>
        </tr>
        <tr>
            <th>Proteins</th>
            <td><input type="number" required id="proteins" name="proteins"></td>
        </tr>
        <tr>
            <th>Carbohydrates</th>
            <td><input type="number" required id="carbohydrates" name="carbohydrates"></td>
        </tr>
    </table>
    <td><fmt:message key="login.button.submit" var="buttonValue"/>
        <input type="submit" name="submit" value="${buttonValue}"></td>
</form>
</body>
</html>
