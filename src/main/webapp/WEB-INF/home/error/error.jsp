<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  UserMapImpl: kykysik
  Date: 12.08.18
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />

<html>
<head>
    <title>Oops</title>
</head>
<body>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>UA</option>
    </select>

    <h1><fmt:message key="input.error"/></h1>
    <h2><a style="color: #000000;font-weight: bold;" href="/">Click</a></h2>

</body>
</html>
