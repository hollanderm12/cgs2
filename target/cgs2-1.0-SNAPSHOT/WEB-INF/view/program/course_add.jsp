<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Course</title>
    <link rel="stylesheet" type="text/css" href="./resources/w3css.css">
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>Add a course</h2>
    </div>  
    <form:form action="./course_add" method="POST" commandName="command" cssClass="w3-container"> 
        <jsp:include page="/resources/form_display/course_form.jsp"/>
        <div class="w3-row-padding w3-margin-bottom">       
            <div class="w3-col">
                <input type="submit" class="w3-button w3-large w3-amber w3-border w3-border-black" value="Add Course">
            </div>
        </div>
    </form:form>
</body>
</html>