<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Result</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
</head>
<body class="w3-pale-green">
    <div class="w3-bar w3-green">
        <jsp:include page="/resources/top_banners/results_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-green w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>Add a result</h2>
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
        <c:if test="${studentNotRegistered}">
            <div class="errorblock">
                <p>Student ID <c:out value="${badStudentID}"/> is not registered in course ID <c:out value="${badCourseID}"/>. 
                    Please verify the student's course registrations and try again.</p>
            </div>
        </c:if>
        <form:form action="./result_add" method="POST" commandName="command" cssClass="w3-container"> 
            <jsp:include page="/resources/form_display/result_form.jsp"/>
             <div class="w3-row-padding w3-margin-bottom">
                <div class="w3-quarter">
                    <label>Student: </label>
                    <form:select path="studentID" cssClass="w3-select w3-border">
                        <c:forEach var="s" items="${studentList}">
                            <form:option value="${s.studentID}" label="${s.studentID} - ${s.firstName} ${s.lastName}"/>
                        </c:forEach>
                    </form:select>
                </div> 
                <div class="w3-quarter">
                    <label>Course: </label>
                    <form:select path="courseID" cssClass ="w3-select w3-border">
                        <c:forEach var="c" items="${courseList}">
                            <form:option value="${c.courseID}" label="${c.courseID} - ${c.courseName}"/>
                        </c:forEach>
                    </form:select>
                </div>   
            </div>
            <div class="w3-row-padding w3-margin-bottom">       
                <div class="w3-col">
                    <input type="submit" class="w3-button w3-large w3-green w3-border w3-border-black" value="Add Result">
                </div>
            </div>
        </form:form>
    </c:if>
</body>
</html>
