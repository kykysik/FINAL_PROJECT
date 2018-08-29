<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 12.08.18
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a style="color: #000000;font-weight: bold;" href="/home/registration">regForm</a>
<a style="color: #000000;font-weight: bold;" href="/home/userInfo">userInfo</a>
<a style="color: #000000;font-weight: bold;" href="/home/statistics">statistics</a>
<a style="color: #000000;font-weight: bold;" href="/home/menu">menu</a>
<c:if test="${user != null}">
    <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/logout">LogOut</a>
</c:if>
<c:if test="${user == null}">
    <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/login">LogIn</a>
</c:if>

<form class="form" method="POST" action="${pageContext.request.contextPath}/startPage">

</form>
</body>
</html>
