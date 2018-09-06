<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  UserMapImpl: kykysik
  Date: 30.08.18
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<form>
    <select id="language" name="language"   onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>UA</option>
    </select>
</form>

<div class="jumbotron text-center">
    <c:if test="${user == null}">
        <a style="color: #000000;font-weight: bold;" href="/registration"><fmt:message key="input.reg_form"/></a>
        |
    </c:if>
    <a style="color: #000000;font-weight: bold;" href="/userInfo"><fmt:message key="input.user_info"/></a>
    |
    <c:if test="${user != null}">
        <a style="color: #000000;font-weight: bold;" href="/statistics"><fmt:message key="input.statistics"/></a>
        |
    </c:if>
    <a style="color: #000000;font-weight: bold;" href="/menu"><fmt:message key="input.menu"/></a>
    <c:if test="${user != null}">
        |
        <a style="color: #000000;font-weight: bold;" href="/logout"><fmt:message key="input.logout"/></a>
    </c:if>
    <c:if test="${user == null}">
        |
        <a style="color: #000000;font-weight: bold;" href="/login"><fmt:message key="input.login"/></a>
    </c:if>
</div>

