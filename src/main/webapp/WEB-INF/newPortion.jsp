<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 01.09.18
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">

</head>
<body>
<p style="color: red;">${error}</p>

<jsp:include page="/WEB-INF/_menu.jsp"/>

<form action="${pageContext.request.contextPath}/menu/portion">
    <div class="row col-md-2">

        <table>
            <tr>
                <th>Portion Name</th>
            </tr>
            <tr>
            <tr><td><input required name="namePortion" type="text"></td></tr>
            </tr>


            <tr>
                <th>Product Name</th>
                <th>Product Calories</th>
                <th>Amount</th>
            </tr>

            <tr>
                <td>
                    <table class="table table-striped table-bordered table-sm">
                        <c:forEach items="${listProduct}" var="listProduct">
                            <tr><td>${listProduct.getName()}</td></tr>
                        </c:forEach>

                    </table>
                </td>
                <td>
                    <table class="table table-striped table-bordered table-sm">
                        <c:forEach items="${listProduct}" var="listProduct">
                            <tr><td>${listProduct.getCalories()}</td></tr>
                        </c:forEach>

                    </table>
                </td>
                <td>
                    <table class="table table-striped table-bordered table-sm">

                    <c:forEach items="${count}" var="count">
                            <tr><td>${count}</td></tr>
                        </c:forEach>
                    </table>

                </td>

            </tr>
            <td><fmt:message key="login.button.submit" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}">
        </table>
    </div>
</form>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>
