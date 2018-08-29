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

<p style="color: red;">${error}</p>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>UA</option>
    </select>
</form>

<form class="form" method="POST" action="${pageContext.request.contextPath}/login">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input required type="text" name="login" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input required type="password" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Remember me</td>
            <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>