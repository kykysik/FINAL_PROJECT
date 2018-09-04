<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 31.08.18
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>

<jsp:include page="/WEB-INF/_menu.jsp"/>

<form action="${pageContext.request.contextPath}/menu/portion">
    <div class="row col-md-4">

        <table>
            <tr>
                <th>Portion Name</th>
            </tr>
            <tr>
                <th>${map.get("name")}</th>
            </tr>


                <tr>
                    <th>Product Name</th>
                    <th>Product Calories</th>
                    <th>Amount</th>
                    <th>Delete</th>
                </tr>

            <tr>
                    <td>
                    <table class="table table-striped table-bordered table-sm">
                        <c:forEach items="${productName}" var="productName">
                            <tr><td>${productName}</td></tr>
                        </c:forEach>

                    </table>
                    </td>
                <td>
                <table class="table table-striped table-bordered table-sm">
                    <c:forEach items="${productCalories}" var="productCalories">
                     <tr><td>${productCalories}</td></tr>
                    </c:forEach>
                </table>
                    </td>

                    <td>
                        <table class="table table-striped table-bordered table-sm">
                            <c:forEach items="${amount}" var="amount">
                                <tr><td>${amount}</td></tr>
                            </c:forEach>
                        </table>
                    </td>

                    <td>
                <table class="table table-striped table-bordered table-sm">
                    <c:forEach items="${productId}" var="productId">
               <tr><td><a href="edit?id=${productId}&portId=${map.get("id")}">Delete</a></td></tr>
                    </c:forEach>
                </table>
                    </td>



            </tr>

            <tr>
                <td>
            <a href="edit/product?mapId=${map.get("id")}">Add new Product</a>

            </td>
            </tr>

            <tr>
                <th>Portion Calories</th>
            </tr>
            <tr>
                <th>${map.get("calories")}</th>
            </tr>
        </table>
    </div>
</form>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>
