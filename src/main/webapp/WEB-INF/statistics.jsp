<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 24.08.18
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="m-3">
<p style="color: red;">${error}</p>

<a href="/"><fmt:message key="input.start_page"/></a>
|
<a style="color: #000000;font-weight: bold;" href="/"><fmt:message key="input.start_page"/></a>

<form action="${pageContext.request.contextPath}/statistics">

    <input type="hidden" name="currentPage" value="1">

    <div class="form-group col-md-2">

        <label for="records">Select records per page:</label>

        <select class="form-control" id="records" name="recordsPerPage">
            <option value="5"  ${recordsPerPage == '5' ? 'selected' : ''}>5</option>
            <option value="10" ${recordsPerPage == '10' ? 'selected' : ''}>10</option>
            <option value="15" ${recordsPerPage == '15' ? 'selected' : ''}>15</option>
        </select>

    </div>
    <div class="form-group col-md-2">
        <input name="date" type="date" value="${date}">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<form action="${pageContext.request.contextPath}/statistics">
    <div class="row col-md-4">
        <table class="table table-striped table-bordered table-sm">

            <tr>
                <th>Name</th>
                <th>Calories</th>
                <th>Count</th>
                <th>Date</th>
                <c:if test="${statistics[0].getType() != 'statistics'}">
                <th>Delete</th>
                </c:if>
            </tr>

            <c:forEach items="${statistics}" var="statistics">
                <tr>
                    <td>${statistics.getName()}</td>
                    <td>${statistics.getCalories()}</td>
                    <td>${statistics.getAmount()}"</td>
                    <td>${statistics.getDate()}"</td>
                    <c:if test="${statistics.getType() != 'statistics'}">
                    <td><a href="statistics?id=${statistics.getId()}&type=${statistics.getType()}">Delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>

    <c:if test="${role != 'UNKNOWN'}">
        <tr>
            <td colspan ="2">
                <fmt:message key="login.button.submit" var="buttonValue" />

                <button type="submit" class="btn btn-primary" value="${buttonValue}">Submit</button>
            </td>
        </tr>
    </c:if>

</form>

<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="statistics?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&date=${date}">Previous</a>
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
                                             href="statistics?recordsPerPage=${recordsPerPage}&currentPage=${i}&date=${date}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="statistics?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&date=${date}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>