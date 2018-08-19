<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:errors path="*" cssClass="errorblock" element="div" />
<div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
        <label>Session ID</label>
        <form:input path="sessionID" type="number" required="true" min="101" max="1299" maxlength="4" cssClass="w3-input w3-border" value="${param.sessionID}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-quarter">
        <label>Mark</label>
        <form:input path="mark" type="number" required="true" min="0" max="100" maxlength="3" cssClass="w3-input w3-border" value="${param.mark}" readonly="${param.readOnly}"/>
    </div>
</div>