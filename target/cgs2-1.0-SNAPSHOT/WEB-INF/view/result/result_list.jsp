<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Result List</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
    <script src="/cgs2/resources/js/DeleteResultNotification.js"></script>
</head>
<body class="w3-pale-green">
    <div class="w3-bar w3-green">
    <jsp:include page="/resources/top_banners/results_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-green w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>List of results</h2>
    </div>
    <c:if test="${not empty statusMsg}">
        <div class="statusmsg">
            <p><c:out value="${statusMsg}"/></p>
        </div>
    </c:if>
    <jsp:include page="/resources/notifications/delete_result.jsp"/>
    <c:if test="${empty resultList}">
        <div class="w3-container w3-pale-green w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
            <p>No results to list. Please add a result and reload this page.</p>
        </div>
    </c:if>
    <c:if test="${not empty resultList}">
        <table class="w3-table-all">
            <tr>
                <th>ID</th>
                <th>Session ID</th>
                <th>Mark</th>
                <th>Student</th>
                <th>Course</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="r" items="${resultList}">
                <tr>
                    <td>${r.resultID}</td>
                    <td>${r.sessionID}</td>
                    <td>${r.mark}</td>
                    <td>${r.studentResult.studentID} - ${r.studentResult.firstName} ${r.studentResult.lastName}</td>
                    <td>${r.courseResult.courseID} - ${r.courseResult.courseName}</td>
                    <form action="./result_edit/${r.resultID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-blue w3-border w3-border-black" value="Edit"></td>
                    </form>
                    <td><button onclick="javascript:confirmDelete('${r.resultID}')"
                                class="w3-button w3-tiny w3-red w3-border w3-border-black">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>               
</body>
</html>