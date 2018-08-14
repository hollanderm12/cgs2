<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Student</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
</head>
<body class="w3-pale-blue">
    <div class="w3-bar w3-blue">
        <jsp:include page="/resources/top_banners/students_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-blue w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>Add a student</h2>
    </div>  
    <form:form action="./student_add" method="POST" commandName="command" cssClass="w3-container"> 
        <jsp:include page="/resources/form_display/student_form.jsp"/>
        <div class="w3-row-padding w3-margin-bottom">       
            <div class="w3-col">
                <input type="submit" class="w3-button w3-large w3-blue w3-border w3-border-black" value="Add Student">
            </div>
        </div>
    </form:form>
</body>
</html>
