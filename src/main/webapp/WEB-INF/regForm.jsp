
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 21.07.18
  Time: 23:25
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


<form class="form" method="post" action="${pageContext.request.contextPath}/registration">

    <table border="0">

        <tr>
            <td><label for="login"><fmt:message key="login.label.username" />:</label></td>
            <td><input required type="text" id="login" name="login" value= "${user.login}"/> </td>
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
            <td><label for="very_low"><fmt:message key="input.activity.very_low" />:</label></td>
            <td><input required type="radio" id="very_low" name="lifeActivity" value="1.2"/> </td>
        </tr><tr>
            <td><label for="low"><fmt:message key="input.activity.low" />:</label></td>
            <td><input required type="radio" id="low" name="lifeActivity" value="1.375"/> </td>
        </tr>
        <tr>
            <td><label for="middle"><fmt:message key="input.activity.middle" />:</label></td>
            <td><input required type="radio" id="middle" name="lifeActivity" value="1.55"/> </td>
        </tr>
        <tr>
            <td><label for="hight"><fmt:message key="input.activity.height" />:</label></td>
            <td><input required type="radio" id="hight" name="lifeActivity" value="1.725"/> </td>
        </tr>
        <tr>
            <td><label for="very_hight"><fmt:message key="input.activity.very_height" />:</label></td>
            <td><input required type="radio" id="very_hight" name="lifeActivity" value="1.9"/> </td>
        </tr>
        <tr>
            <td><label for="date"><fmt:message key="input.date" />:</label></td>
            <td><input required type="date" id="date" name="birthDate"/> </td>
        </tr>
        <tr>
            <td><label for="date"><fmt:message key="input.male" />:</label></td>
            <td><input type="radio" name="gender" value="M"/></td>
        </tr>
        <tr>
            <td><label for="date"><fmt:message key="input.female" />:</label></td>
            <td><input type="radio" name="gender" value="F"/></td>
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
