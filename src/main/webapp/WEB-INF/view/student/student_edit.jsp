<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Student</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
    <script src="/cgs2/resources/js/IDLookup.js"></script>
    <script src="/cgs2/resources/js/DeleteStudentNotification.js"></script>
</head>
<body class="w3-pale-blue">
    <div class="w3-bar w3-blue">
    <jsp:include page="/resources/top_banners/students_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-blue w3-border-top w3-border-bottom w3-border-indigo">
        <h2>Edit student</h2>
    </div>
    <jsp:include page="/resources/notifications/status_messages.jsp">
        <jsp:param name="errorMsg" value="${errorMsg}"/>
    </jsp:include>
    <div class="w3-container">
        <c:set var="s" scope="page" value="${detailsFound}"/>
        <c:if test="${empty s}">
            <jsp:include page="/resources/form_display/lookup.jsp">
                <jsp:param name="pageName" value="student_edit"/>
                <jsp:param name="buttonColor" value="w3-blue"/>
            </jsp:include>
        </c:if>
        <c:if test="${not empty s}">
            <jsp:include page="/resources/notifications/delete_student.jsp"/>
            <h3>The details for student ID <c:out value="${s.studentID}"/> are below:</h3>    
            <form:form action="../student_edit/${s.studentID}" method="POST" commandName="command" cssClass="w3-container">
                <jsp:include page="/resources/form_display/student_form.jsp">
                    <jsp:param name="firstName" value="${s.firstName}"/>
                    <jsp:param name="lastName" value="${s.lastName}"/>
                    <jsp:param name="address" value="${s.address}"/>
                    <jsp:param name="city" value="${s.city}"/>
                    <jsp:param name="stateProvince" value="${s.stateProvince}"/>
                    <jsp:param name="country" value="${s.country}"/>
                    <jsp:param name="zipPostal" value="${s.zipPostal}"/>
                    <jsp:param name="phoneNum" value="${s.phoneNum}"/>
                    <jsp:param name="email" value="${s.email}"/>
                    <jsp:param name="major" value="${s.major}"/>
                </jsp:include>
                <div class="w3-row-padding w3-margin-bottom">       
                    <div class="w3-col">
                        <input type="submit" class="w3-button w3-large w3-blue w3-border w3-border-black" value="Save Changes">
                        <button type="button" class="w3-button w3-large w3-blue w3-border w3-border-black" 
                            onclick="javascript:confirmDelete('${s.studentID}')">Delete Student</button>
                    </div>
                </div>
            </form:form>           
        </c:if>
    </div>
</body>
</html>
