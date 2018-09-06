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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</head>

<body>

<jsp:include page="/WEB-INF/home/header.jsp"/>

<form action="${pageContext.request.contextPath}/menu/portion">

    <input type="hidden" name="currentPage" value="1">

    <div class="form-group col-md-2">

        <label for="records"><fmt:message key="padg.select" /></label>

        <select class="form-control" id="records" name="recordsPerPage">
            <option value="5"  ${recordsPerPage == '5' ? 'selected' : ''}>5</option>
            <option value="10" ${recordsPerPage == '10' ? 'selected' : ''}>10</option>
            <option value="15" ${recordsPerPage == '15' ? 'selected' : ''}>15</option>
        </select>

    </div>

    <button type="submit" class="btn btn-primary"><fmt:message key="login.button.submit" /></button>

</form>

<form action="${pageContext.request.contextPath}/menu/portion">
<div class="row col-md-4">
    <table class="table table-striped table-bordered table-sm">
        <tr>
                <th><fmt:message key="login.portion.name" /></th>
                <th><fmt:message key="login.portion.calories" /></th>

            <c:if test="${role == 'USER' || role == 'ADMIN'}">
                <th><fmt:message key="login.product.amount" /></th>
            </c:if>

            <c:if test="${role == 'ADMIN'}">
                <th><fmt:message key="login.product.edit" /></th><%--Admin--%>
                <th><fmt:message key="login.product.delete" /></th>
            </c:if>

        </tr>

        <c:forEach items="${portion}" var="portion">

            <tr>
                <td>${portion.getName()}</td>
                <td>${portion.getCalories()}</td>

                <c:if test="${role == 'USER' || role == 'ADMIN'}">
                <td><input min="1" type="number" name="count_${portion.getId()}"></td>
                </c:if>

                <c:if test="${role == 'ADMIN'}">
                <td><a href="portion/edit?pId=${portion.getId()}"><fmt:message key="login.product.edit" /></a></td>
                <td><a href="portion?pId=${portion.getId()}"><fmt:message key="login.product.delete" /></a></td>
                </c:if>

            </tr>
        </c:forEach>

        <c:if test="${role != 'UNKNOWN'}">
        <th><a href="/menu/product"><fmt:message key="login.portion.new" /></a></th>
        </c:if>

    </table>
</div>

    <c:if test="${role != 'UNKNOWN'}">
    <tr>
        <td colspan ="2">

            <button type="submit" class="btn btn-primary"><fmt:message key="login.button.submit" /></button>
        </td>
    </tr>
    </c:if>

</form>

<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="portion?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="login.previous" /></a>
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
                                             href="portion?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="portion?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="login.next" /></a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>