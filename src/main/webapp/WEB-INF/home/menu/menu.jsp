<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  UserMapImpl: kykysik
  Date: 24.08.18
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>UA</option>
    </select>
</form>
<div class="jumbotron text-center">
<a style="color: #000000;font-weight: bold;" href="/"><fmt:message key="input.start_page"/></a>
|
<a style="color: #000000;font-weight: bold;" href="/menu/portion"><fmt:message key="input.portion"/></a>
|
<a style="color: #000000;font-weight: bold;" href="/menu/product"><fmt:message key="input.product"/></a>
</div>

</body>
</html>