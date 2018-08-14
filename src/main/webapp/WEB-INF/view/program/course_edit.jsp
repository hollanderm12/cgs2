<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Course</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
    <script src="/cgs2/resources/js/IDLookup.js"></script>
    <script src="/cgs2/resources/js/DeleteCourseNotification.js"></script>
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo">
        <h2>Edit course</h2>
    </div>
    <c:if test="${lookupError}">
        <div class="errorblock w3-margin-top">
            <p>The course ID specified was not found. Please verify the course ID and try again.</p>
        </div>
    </c:if>
    <div class="w3-container">
        <c:set var="c" scope="page" value="${detailsFound}"/>
        <c:if test="${empty c}">
            <jsp:include page="/resources/form_display/lookup.jsp">
                <jsp:param name="pageName" value="course_edit"/>
                <jsp:param name="buttonColor" value="w3-amber"/>
            </jsp:include>
        </c:if>
        <c:if test="${not empty c}">
            <jsp:include page="/resources/notifications/delete_course.jsp"/>
            <h3>The details for course ID <c:out value="${c.courseID}"/> are below:</h3>    
            <form:form action="../course_edit/${c.courseID}" method="POST" commandName="command" cssClass="w3-container">
                <jsp:include page="/resources/form_display/course_form.jsp">
                    <jsp:param name="courseName" value="${c.courseName}"/>
                    <jsp:param name="credits" value="${c.credits}"/>
                </jsp:include>
                <div class="w3-row-padding w3-margin-bottom">       
                    <div class="w3-col">
                        <input type="submit" class="w3-button w3-large w3-amber w3-border w3-border-black" value="Save Changes">
                        <button type="button" class="w3-button w3-large w3-amber w3-border w3-border-black" 
                            onclick="javascript:confirmDelete('${c.courseID}')">Delete Course</button>
                    </div>
                </div>
            </form:form>           
        </c:if>
    </div>
</body>
</html>
