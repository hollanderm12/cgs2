<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log In</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
</head>
<body class="w3-pale-blue" onload="document.loginForm.username.focus();">
    <div class="w3-bar w3-blue">
        <jsp:include page="/resources/top_banners/empty_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-blue w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <h2>Log in</h2>
    </div>  
    <c:if test="${not empty error}">
        <div class="errorblock w3-margin-top">
            <c:out value="${error}"/>
        </div>
    </c:if>
    <div class="w3-container">
        <form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
            <div class="w3-row-padding w3-margin-bottom">
                <div class="w3-quarter">
                    <label>Username</label>
                    <input class="w3-input w3-border" type='text' name='username' value=''>
                </div>
            </div>
            <div class="w3-row-padding w3-margin-bottom">
                <div class="w3-quarter">
                    <label>Password</label>
                    <input class="w3-input w3-border" type='password' name='password' />
                </div>
            </div>
            <div class="w3-row-padding w3-margin-bottom">
                <div class="w3-quarter">
                    <input class="w3-button w3-large w3-blue w3-border w3-border-black" name="submit" type="submit" value="Submit" />
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         </form>
    </div>
</body>
</html>
