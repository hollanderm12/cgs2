<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:errors path="*" cssClass="errorblock" element="div" />
<div class="w3-row-padding w3-margin-bottom">
    <div class="w3-half">
        <label>Course Name</label>
        <form:input path="courseName" maxlength="50" cssClass="w3-input w3-border" value="${param.courseName}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-quarter">
        <label>Credits</label>
        <form:input type="number" min="1" max="8" path="credits" maxlength="1" cssClass="w3-input w3-border" value="${param.credits}" readonly="${param.readOnly}"/>
    </div>
</div>