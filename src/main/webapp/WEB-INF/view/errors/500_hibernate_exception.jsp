<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
</head>
<body class="w3-pale-red">
    <div class="w3-bar w3-blue">
        <jsp:include page="/resources/top_banners/empty_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-red w3-border-top w3-border-indigo">
        <h2>Error</h2>
    </div> 
    <div class="w3-container w3-pale-red w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <p>An error occurred while creating, updating, or deleting the requested resource. It may have already been deleted from the system. 
            Please see the server logs for more information.</p>
        <p>Please return to the <a href="/cgs2/index">Home Page</a> or use the links in the top banner.</p>
    </div>
</body>
</html>
