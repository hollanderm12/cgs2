<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Course List</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
    <script src="/cgs2/resources/js/DeleteCourseNotification.js"></script>
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>List of courses</h2>
    </div>
    <jsp:include page="/resources/notifications/delete_course.jsp"/>
    <c:if test="${empty courseList}">
        <div class="w3-container w3-khaki w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
            <p>No courses to list. Please add a course and reload this page.</p>
        </div>
    </c:if>
    <c:if test="${not empty courseList}">
        <table class="w3-table-all">
            <tr>
                <th>ID</th>
                <th>Course Name</th>
                <th>Credits</th>
                <th>Edit</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="c" items="${courseList}">
                <tr>
                    <td>${c.courseID}</td>
                    <td>${c.courseName}</td>
                    <td>${c.credits}</td>
                    <form action="./course_edit/${c.courseID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-blue w3-border w3-border-black" value="Edit"></td>
                    </form>
                    <form action="./course_details/${c.courseID}" method="GET">
                        <td><input type="submit" class="w3-button w3-tiny w3-green w3-border w3-border-black" value="Details"></td>
                    </form>
                    <td><button onclick="javascript:confirmDelete('${c.courseID}')"
                                class="w3-button w3-tiny w3-red w3-border w3-border-black">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>               
</body>
</html>