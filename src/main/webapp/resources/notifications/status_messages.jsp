<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<c:if test="${not empty param.statusMsg}">
    <div class="statusmsg w3-margin-top">
        <c:out value="${param.statusMsg}"/>
    </div>
</c:if>
<c:if test="${not empty param.warningMsg}">
    <div class="statusmsg w3-margin-top">
        <c:out value="${param.warningMsg}"/>
    </div>
</c:if>
<c:if test="${not empty param.errorMsg}">
    <div class="errorblock w3-margin-top">
        <c:out value="${param.errorMsg}"/>
    </div>
</c:if>
