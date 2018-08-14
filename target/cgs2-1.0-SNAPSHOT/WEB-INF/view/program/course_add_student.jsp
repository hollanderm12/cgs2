<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Student to Course</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
    <script src="/cgs2/resources/js/Registration.js"></script>
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>Add a student to a course</h2>
    </div>
    <c:if test="${lookupError}">
        <c:if test="${noCourses}">
            <p>There are no courses present in the system. Please add at least one course.<p>
        </c:if>
        <c:if test="${noStudents}">
            <p>There are no students present in the system. Please add at least one student.<p>
        </c:if>
    </c:if>
    <c:if test="${not lookupError}">
        <form:form action="./course_add_student" method="POST" commandName="register">
            <div class="w3-row-padding w3-margin-bottom">  
                <div class="w3-third">
                    <b>Course:</b>
                    <form:select path="id1" cssClass ="w3-select w3-border">
                        <c:forEach var="c" items="${courseList}">
                            <form:option value="${c.courseID}" label="${c.courseID} - ${c.courseName}"/>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="w3-third">
                    <b>Student:</b>
                    <form:select path="id2" cssClass ="w3-select w3-border">
                        <c:forEach var="s" items="${studentList}">
                            <form:option value="${s.studentID}" label="${s.studentID} - ${s.firstName} ${s.lastName}"/>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="w3-row-padding w3-margin-bottom">
                <div class="w3-col">
                    <input type="submit" class="w3-button w3-large w3-amber w3-border w3-border-black" value="Register Student">
                </div>
            </div>
        </form:form>
    </c:if>
</body>
</html>