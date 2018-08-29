<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 24.08.18
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Countries</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="m-3">

<form action="${pageContext.request.contextPath}/product">

    <input type="hidden" name="currentPage" value="1">

    <div class="form-group col-md-2">

        <label for="records">Select records per page:</label>

        <select class="form-control" id="records" name="recordsPerPage">
            <option value="5"  ${recordsPerPage == '5' ? 'selected' : ''}>5</option>
            <option value="10" ${recordsPerPage == '10' ? 'selected' : ''}>10</option>
            <option value="15" ${recordsPerPage == '15' ? 'selected' : ''}>15</option>
        </select>

    </div>

    <button type="submit" class="btn btn-primary">Submit</button>

</form>
<form action="${pageContext.request.contextPath}/product">

<div class="row col-md-4">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th>Name</th>
            <th>Fats</th>
            <th>Proteins</th>
            <th>Carbohydrates</th>
            <th>Calories</th>
            <th>Count</th>
            <th>Edit</th> <%--Admin--%>
            <th>Delete</th><%--Admin--%>
        </tr>

        <c:forEach items="${product}" var="product">
            <tr>
                <td>${product.getName()}</td>
                <td>${product.getFats()}</td>
                <td>${product.getProteins()}</td>
                <td>${product.getCarbohydrates()}</td>
                <td>${product.getCalories()}</td>
                <td><input min="1" type="number" name="count_${product.getId()}"></td>
                <td><a href="product?id=${product.getId()}">Edit</a></td>
                <td><a href="product?id=${product.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
        <%--Add product, Edit product--%>
    </table>
    <tr>
        <td>To eat</td>
        <td><input type="radio" name="action" value="eatProduct"/></td>
    </tr>
    <tr>
        <td>Make a portion</td>
        <td><input type="radio" name="action" value="addProduct"/></td>
    </tr>

</div>
<tr>
    <td colspan ="2">
        <fmt:message key="login.button.submit" var="buttonValue" />
        <button type="submit" class="btn btn-primary" value="${buttonValue}">Submit</button>
    </td>
</tr>
</form>

<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="product?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="product?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="product?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>