<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<c:set var="isAdmin" value="${pageContext.request.isUserInRole('ROLE_ADMIN')}"/>
<c:set var="isStudent" value="${pageContext.request.isUserInRole('ROLE_STUDENT')}"/>
<c:set var="isProgram" value="${pageContext.request.isUserInRole('ROLE_PROGRAM')}"/>
<c:set var="isTeacher" value="${pageContext.request.isUserInRole('ROLE_TEACHER')}"/>
<div class="w3-bar w3-blue-grey">
    <a href="/cgs2/index" class="w3-bar-item w3-button"><b>CGS Home</b></a>
    <c:if test="${isAdmin || isStudent}"><a href="/cgs2/students" class="w3-bar-item w3-button">Students</a></c:if>
    <c:if test="${isAdmin || isProgram}"><a href="/cgs2/programs" class="w3-bar-item w3-button">Programs</a></c:if>
    <c:if test="${isAdmin || isTeacher}"><a href="/cgs2/teachers" class="w3-bar-item w3-button">Teachers</a></c:if>
    <jsp:include page="./logout_item.jsp"/> 
</div>