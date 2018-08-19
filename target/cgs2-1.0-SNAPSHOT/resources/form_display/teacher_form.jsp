<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:errors path="*" cssClass="errorblock" element="div" />
<div class="w3-row-padding w3-margin-bottom">
    <div class="w3-half">
        <label>First Name</label>
        <form:input path="firstName" required="true"  maxlength="30" cssClass="w3-input w3-border" value="${param.firstName}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-half">
        <label>Last Name</label>
        <form:input path="lastName" required="true"  maxlength="30" cssClass="w3-input w3-border" value="${param.lastName}" readonly="${param.readOnly}"/>
    </div>
</div>
<div class="w3-row-padding w3-margin-bottom">
    <div class="w3-third">
        <label>Address</label>
        <form:input path="address" required="true"  maxlength="40" cssClass="w3-input w3-border" value="${param.address}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>City</label>
        <form:input path="city" required="true"  maxlength="30" cssClass="w3-input w3-border" value="${param.city}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>State/Province</label>
        <form:input path="stateProvince" required="true"  maxlength="25" cssClass="w3-input w3-border" value="${param.stateProvince}" readonly="${param.readOnly}"/>
    </div>
</div>
<div class="w3-row-padding w3-margin-bottom">       
    <div class="w3-third">
        <label>Country</label>
        <form:input path="country" required="true"  maxlength="30" cssClass="w3-input w3-border" value="${param.country}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>ZIP/Postal Code</label>
        <form:input path="zipPostal" required="true"  maxlength="12" cssClass="w3-input w3-border" value="${param.zipPostal}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>Phone Number</label>
        <form:input path="phoneNum" required="true"  maxlength="15" cssClass="w3-input w3-border" value="${param.phoneNum}" readonly="${param.readOnly}"/>
    </div>         
</div>     
<div class="w3-row-padding w3-margin-bottom">       
    <div class="w3-third">
        <label>Email Address</label>
        <form:input path="email" required="true"  maxlength="50" cssClass="w3-input w3-border" value="${param.email}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>Salary</label>
        <form:input type="number" required="true"  min="1" max="9999999999" path="salary" maxlength="10" cssClass="w3-input w3-border" value="${param.salary}" readonly="${param.readOnly}"/>
    </div>
</div>