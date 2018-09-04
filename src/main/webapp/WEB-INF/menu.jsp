<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
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

</head>
<body>


<a style="color: #000000;font-weight: bold;" href="/"><fmt:message key="input.start_page"/></a>
|
<a style="color: #000000;font-weight: bold;" href="/menu/portion"><fmt:message key="input.portion"/></a>
|
<a style="color: #000000;font-weight: bold;" href="/menu/product"><fmt:message key="input.product"/></a>

</body>
</html>