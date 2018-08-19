<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Teacher List</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
    <script src="/cgs2/resources/js/DeleteTeacherNotification.js"></script>
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>List of teachers</h2>
    </div>
    <jsp:include page="/resources/notifications/status_messages.jsp">
        <jsp:param name="statusMsg" value="${statusMsg}"/>
    </jsp:include>
    <jsp:include page="/resources/notifications/delete_teacher.jsp"/>
    <c:if test="${empty teacherList}">
        <div class="w3-container w3-khaki w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
            <p>No teachers to list. Please add a teacher and reload this page.</p>
        </div>
    </c:if>
    <c:if test="${not empty teacherList}">
        <table class="w3-table-all">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Address</th>
                <th>Salary</th>
                <th>Edit</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="t" items="${teacherList}">
                <tr>
                    <td>${t.teacherID}</td>
                    <td>${t.firstName}</td>
                    <td>${t.lastName}</td>
                    <td>${t.email}</td>
                    <td>$${t.salary}</td>
                    <form action="./teacher_edit/${t.teacherID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-blue w3-border w3-border-black" value="Edit"></td>
                    </form>
                    <form action="./teacher_details/${t.teacherID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-green w3-border w3-border-black" value="Details"></td>
                    </form>
                    <td><button onclick="javascript:confirmDelete('${t.teacherID}')"
                                class="w3-button w3-tiny w3-red w3-border w3-border-black">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>               
</body>
</html>