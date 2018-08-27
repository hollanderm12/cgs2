<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<span style="float: right">
    <c:if test="${empty pageContext.request.remoteUser}">
        <a href="<c:url value="/log_in"/>" class="w3-bar-item"><b>Log In</b></a>
    </c:if>
    <c:if test="${not empty pageContext.request.remoteUser}">
        <span class="w3-bar-item"><b>Welcome, <c:out value="${pageContext.request.remoteUser}"/>.</b></span>
        <a href="<c:url value="/logout"/>" class="w3-bar-item">Log Out</a>
    </c:if>   
</span>