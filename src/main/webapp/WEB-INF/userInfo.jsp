
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 24.08.18
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />



<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Login</title>



</head>
<body>
<p style="color: red;">${error}</p>

<jsp:include page="/WEB-INF/_menu.jsp"/>
|
<a style="color: #000000;font-weight: bold;" href="/"><fmt:message key="input.start_page"/></a>
<form class="form" method="post" action="${pageContext.request.contextPath}/userInfo">

    <table border="0">

        <tr>
            <td><label for="login"><fmt:message key="login.label.username" />:</label></td>
            <td id="login">${user.getLogin()}</td>
        </tr>
        <tr>
            <td><label for="mail"><fmt:message key="login.label.username" />:</label></td>
            <td id="mail">${user.getLogin()}</td>
        </tr>
        <tr>
            <td><label for="secondName"><fmt:message key="input.second.name.data" />:</label></td>
            <td id="secondName">${user.secondName}</td>
        </tr>
        <tr>
            <td><label for="firstName"><fmt:message key="input.first.name.data" />:</label></td>
            <td id="firstName">${user.firstName}</td>
        </tr>
        <tr>
            <td><label for="middleName"><fmt:message key="input.middle.name.data" />:</label></td>
            <td id="middleName">${user.middleName}</td>
        </tr>
        <tr>
            <td><label for="height"><fmt:message key="input.height" />:</label></td>
            <td id="height">${user.height}</td>
        </tr>
        <tr>
            <td><label for="weight"><fmt:message key="input.weight" />:</label></td>
            <td id="weight">${user.weight} </td>
        </tr>

        <tr>
            <td><label for="activity"><fmt:message key="input.activity" />:</label></td>
            <td id="activity">${user.lifeActivity}</td>
        </tr>
        <tr>
            <td><label for="date"><fmt:message key="input.date" />:</label></td>
            <td id="date">${user.birthDate} </td>
        </tr>
        <tr>
            <td><label for="gender"><fmt:message key="input.gender" />:</label></td>
            <td id="gender"> ${user.gender} </td>
        </tr>

        <tr>
            <td><label for="norm"><fmt:message key="input.norm" />:</label></td>
            <td id="norm"> ${user.normCalories} </td>
        </tr>
        <tr>
            <td colspan ="2">
                <fmt:message key="login.button.submit" var="buttonValue" />

                <input type="submit" name="submit" value="${buttonValue}">
                <input hidden name="lang" value="${language}">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
