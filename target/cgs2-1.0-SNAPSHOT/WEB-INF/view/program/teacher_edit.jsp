<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Teacher</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
    <script src="/cgs2/resources/js/IDLookup.js"></script>
    <script src="/cgs2/resources/js/DeleteTeacherNotification.js"></script>
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo">
        <h2>Edit teacher</h2>
    </div>
    <c:if test="${lookupError}">
        <div class="errorblock w3-margin-top">
            <p>The teacher ID specified was not found. Please verify the teacher ID and try again.</p>
        </div>
    </c:if>
    <div class="w3-container">
        <c:set var="t" scope="page" value="${detailsFound}"/>
        <c:if test="${empty t}">
            <jsp:include page="/resources/form_display/lookup.jsp">
                <jsp:param name="pageName" value="teacher_edit"/>
                <jsp:param name="buttonColor" value="w3-amber"/>
            </jsp:include>
        </c:if>
        <c:if test="${not empty t}">
            <jsp:include page="/resources/notifications/delete_teacher.jsp"/>
            <h3>The details for teacher ID <c:out value="${t.teacherID}"/> are below:</h3>    
            <form:form action="../teacher_edit/${t.teacherID}" method="POST" commandName="command" cssClass="w3-container">
                <jsp:include page="/resources/form_display/teacher_form.jsp">
                    <jsp:param name="firstName" value="${t.firstName}"/>
                    <jsp:param name="lastName" value="${t.lastName}"/>
                    <jsp:param name="address" value="${t.address}"/>
                    <jsp:param name="city" value="${t.city}"/>
                    <jsp:param name="stateProvince" value="${t.stateProvince}"/>
                    <jsp:param name="country" value="${t.country}"/>
                    <jsp:param name="zipPostal" value="${t.zipPostal}"/>
                    <jsp:param name="phoneNum" value="${t.phoneNum}"/>
                    <jsp:param name="email" value="${t.email}"/>
                    <jsp:param name="salary" value="${t.salary}"/>
                </jsp:include>
                <div class="w3-row-padding w3-margin-bottom">       
                    <div class="w3-col">
                        <input type="submit" class="w3-button w3-large w3-amber w3-border w3-border-black" value="Save Changes">
                        <button type="button" class="w3-button w3-large w3-amber w3-border w3-border-black" 
                            onclick="javascript:confirmDelete('${t.teacherID}')">Delete Teacher</button>
                    </div>
                </div>
            </form:form>           
        </c:if>
    </div>
</body>
</html>
