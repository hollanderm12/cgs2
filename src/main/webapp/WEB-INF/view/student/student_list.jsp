<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Student List</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
    <script src="/cgs2/resources/js/DeleteStudentNotification.js"></script>
</head>
<body class="w3-pale-blue">
    <div class="w3-bar w3-blue">
    <jsp:include page="/resources/top_banners/students_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-blue w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>List of students</h2>
    </div>
    <c:if test="${not empty statusMsg}">
        <div class="statusmsg">
            <c:out value="${statusMsg}"/>
        </div>
    </c:if>
    <jsp:include page="/resources/notifications/delete_student.jsp"/>
    <c:if test="${empty studentList}">
        <div class="w3-container w3-pale-blue w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
            <p>No students to list. Please add a student and reload this page.</p>
        </div>
    </c:if>
    <c:if test="${not empty studentList}">
        <table class="w3-table-all">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Address</th>
                <th>Major</th>
                <th>Edit</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="s" items="${studentList}">
                <tr>
                    <td>${s.studentID}</td>
                    <td>${s.firstName}</td>
                    <td>${s.lastName}</td>
                    <td>${s.email}</td>
                    <td>${s.major}</td>
                    <form action="./student_edit/${s.studentID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-blue w3-border w3-border-black" value="Edit"></td>
                    </form>
                    <form action="./student_details/${s.studentID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-green w3-border w3-border-black" value="Details"></td>
                    </form>
                    <td><button onclick="javascript:confirmDelete('${s.studentID}')"
                                class="w3-button w3-tiny w3-red w3-border w3-border-black">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>               
</body>
</html>