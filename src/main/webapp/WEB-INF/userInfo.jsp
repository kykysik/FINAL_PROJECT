
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

    <a href="/home/startPage">startPage</a>

</head>
<body>
<p style="color: red;">${error}</p>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>UA</option>
    </select>
</form>

<form class="form" method="post" action="${pageContext.request.contextPath}/userInfo">

    <table border="0">

        <tr>
            <td><label for="login"><fmt:message key="login.label.username" />:</label></td>
            <td><input required type="text" id="login" name="login" value= "${user.getLogin()}"/> </td>
        </tr>
        <tr>
            <td><label for="password"><fmt:message key="login.label.password" />:</label></td>
            <td><input required type="password" id="password" name="password" value="${user.password}"/> </td>
        </tr>
        <tr>
            <td><label for="secondName"><fmt:message key="input.second.name.data" />:</label></td>
            <td><input required type="text" id="secondName" name="secondName" value= "${user.secondName}"/> </td>
        </tr>
        <tr>
            <td><label for="firstName"><fmt:message key="input.first.name.data" />:</label></td>
            <td><input required type="text" id="firstName" name="firstName" value="${user.firstName}"/> </td>
        </tr>
        <tr>
            <td><label for="middleName"><fmt:message key="input.middle.name.data" />:</label></td>
            <td><input required type="text" id="middleName" name="middleName" value="${user.middleName}"/> </td>
        </tr>
        <tr>
            <td><label for="height"><fmt:message key="input.height" />:</label></td>
            <td><input required type="number" id="height" name="height" value="${user.height}"/> </td>
        </tr>
        <tr>
            <td><label for="weight"><fmt:message key="input.weight" />:</label></td>
            <td><input required type="number" id="weight" name="weight" value="${user.weight}"/> </td>
        </tr>

        <tr>
<%--
            <td><label for="low"><fmt:message key="input.activity.low" />:</label></td>
--%>        <td>Activity</td>

            <td>${user.lifeActivity}</td>
        </tr>
        <tr>
            <td><label for="date"><fmt:message key="input.date" />:</label></td>
            <td id="date">${user.birthDate} </td>
        </tr>
        <tr>
            <td>Gender</td>
            <td> ${user.gender} </td>
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
