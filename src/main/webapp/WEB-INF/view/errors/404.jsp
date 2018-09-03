<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Page Not Found</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
</head>
<body class="w3-pale-red">
    <div class="w3-bar w3-blue">
        <jsp:include page="/resources/top_banners/error_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-red w3-border-top w3-border-indigo">
        <h2>Page not found</h2>
    </div> 
    <div class="w3-container w3-pale-red w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <p>The page you have tried to access could not be found.</p>
        <p>Please return to the <a href="/cgs2/index">Home Page</a> to access a page available to you.</p>
    </div>
</body>
</html>