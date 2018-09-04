<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 12.08.18
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<p style="color: red;">${errorString}</p>

<jsp:include page="/WEB-INF/_menu.jsp"/>
|
<a style="color: #000000;font-weight: bold;" href="/"><fmt:message key="input.start_page"/></a>

<form class="form" method="POST" action="${pageContext.request.contextPath}/login">
    <table border="0">
        <tr>
            <td><label for="login"><fmt:message key="login.label.username" />:</label></td>
            <td><input required type="text" name="login" value= "${user.login}" id="login" /> </td>
        </tr>
        <tr>
            <td><label for="password"><fmt:message key="login.label.password" />:</label></td>
            <td><input required type="password" name="password" value= "${user.password}" id="password" /> </td>
        </tr>
        <tr>
            <td><label for="remember"><fmt:message key="login.label.remember" />:</label></td>
            <td><input type="checkbox" name="rememberMe" value= "Y" id="remember" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <fmt:message key="login.button.submit" var="buttonValue" />
                <input type="submit" value= "${buttonValue}"  />
            </td>
        </tr>
    </table>
</form>
</body>
</html>