<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:errors path="*" cssClass="errorblock" element="div" />
<div class="w3-row-padding w3-margin-bottom">
    <div class="w3-half">
        <label>First Name</label>
        <form:input path="firstName" maxlength="30" cssClass="w3-input w3-border" value="${param.firstName}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-half">
        <label>Last Name</label>
        <form:input path="lastName" maxlength="30" cssClass="w3-input w3-border" value="${param.lastName}" readonly="${param.readOnly}"/>
    </div>
</div>
<div class="w3-row-padding w3-margin-bottom">
    <div class="w3-third">
        <label>Address</label>
        <form:input path="address" maxlength="40" cssClass="w3-input w3-border" value="${param.address}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>City</label>
        <form:input path="city" maxlength="30" cssClass="w3-input w3-border" value="${param.city}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>State/Province</label>
        <form:input path="stateProvince" maxlength="25" cssClass="w3-input w3-border" value="${param.stateProvince}" readonly="${param.readOnly}"/>
    </div>
</div>
<div class="w3-row-padding w3-margin-bottom">       
    <div class="w3-third">
        <label>Country</label>
        <form:input path="country" maxlength="30" cssClass="w3-input w3-border" value="${param.country}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>ZIP/Postal Code</label>
        <form:input path="zipPostal" maxlength="12" cssClass="w3-input w3-border" value="${param.zipPostal}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-third">
        <label>Phone Number</label>
        <form:input path="phoneNum" maxlength="15" cssClass="w3-input w3-border" value="${param.phoneNum}" readonly="${param.readOnly}"/>
    </div>         
</div>     
<div class="w3-row-padding w3-margin-bottom">       
    <div class="w3-half">
        <label>Email Address</label>
        <form:input path="email" maxlength="50" cssClass="w3-input w3-border" value="${param.email}" readonly="${param.readOnly}"/>
    </div>
    <div class="w3-half">
        <label>Major</label>
        <form:input path="major" maxlength="100" cssClass="w3-input w3-border" value="${param.major}" readonly="${param.readOnly}"/>
    </div>
</div>