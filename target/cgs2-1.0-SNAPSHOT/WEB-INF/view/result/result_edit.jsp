<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Result</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
    <script src="/cgs2/resources/js/IDLookup.js"></script>
    <script src="/cgs2/resources/js/DeleteResultNotification.js"></script>
</head>
<body class="w3-pale-green">
    <div class="w3-bar w3-green">
    <jsp:include page="/resources/top_banners/results_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-light-green w3-border-top w3-border-bottom w3-border-indigo">
        <h2>Edit result</h2>
    </div>
    <jsp:include page="/resources/notifications/status_messages.jsp">
        <jsp:param name="statusMsg" value="${statusMsg}"/>
        <jsp:param name="warningMsg" value="${warningMsg}"/>
    </jsp:include>
    <c:if test="${lookupError}">
        <div class="errorblock w3-margin-top">
            <c:if test="${noCourses}">
                <p>There are no courses present in the system. Please add at least one course.</p>
            </c:if>
            <c:if test="${noStudents}">
                <p>There are no students present in the system. Please add at least one student.</p>
            </c:if>
            <c:if test="${not noCourses && not noStudents}">
                <p>The result ID specified was not found. Please verify the result ID and try again.</p>
            </c:if>
        </div>
    </c:if>
    <c:if test="${not lookupError}">
        <div class="w3-container">
            <c:set var="r" scope="page" value="${detailsFound}"/>
            <c:if test="${empty r}">
                <jsp:include page="/resources/form_display/lookup.jsp">
                    <jsp:param name="pageName" value="result_edit"/>
                    <jsp:param name="buttonColor" value="w3-green"/>
                </jsp:include>
            </c:if>
            <c:if test="${not empty r}">
                <jsp:include page="/resources/notifications/delete_result.jsp"/>
                <h3>The details for result ID <c:out value="${r.resultID}"/> are below:</h3>    
                <form:form action="../result_edit/${r.resultID}" method="POST" commandName="command" cssClass="w3-container">
                    <jsp:include page="/resources/form_display/result_form.jsp">
                        <jsp:param name="sessionID" value="${r.sessionID}"/>
                        <jsp:param name="mark" value="${r.mark}"/>
                    </jsp:include>
                     <div class="w3-row-padding w3-margin-bottom">
                        <div class="w3-quarter">
                            <label>Student: </label>
                            <form:select selected="${r.studentResult.studentID}" path="studentID" cssClass="w3-select w3-border">
                                <c:forEach var="s" items="${studentList}">
                                    <c:if test="${r.studentResult.studentID == s.studentID}">
                                        <form:option selected="true" value="${s.studentID}" label="${s.studentID} - ${s.firstName} ${s.lastName}"/>
                                    </c:if>
                                    <c:if test="${r.studentResult.studentID != s.studentID}">
                                        <form:option value="${s.studentID}" label="${s.studentID} - ${s.firstName} ${s.lastName}"/>
                                    </c:if>
                                </c:forEach>
                            </form:select>
                        </div> 
                        <div class="w3-quarter">
                            <label>Course: </label>
                            <form:select path="courseID" cssClass ="w3-select w3-border">
                               <c:forEach var="c" items="${courseList}">
                                    <c:if test="${r.courseResult.courseID == c.courseID}">
                                       <form:option selected="true" value="${c.courseID}" label="${c.courseID} - ${c.courseName}"/>
                                    </c:if>
                                    <c:if test="${r.courseResult.courseID != c.courseID}">
                                        <form:option value="${c.courseID}" label="${c.courseID} - ${c.courseName}"/>
                                    </c:if>
                               </c:forEach>
                            </form:select>
                        </div>   
                    </div>
                    <div class="w3-row-padding w3-margin-bottom">       
                        <div class="w3-col">
                            <input type="submit" class="w3-button w3-large w3-green w3-border w3-border-black" value="Save Changes">
                            <button type="button" class="w3-button w3-large w3-green w3-border w3-border-black" 
                                onclick="javascript:confirmDelete('${r.resultID}')">Delete Result</button>
                        </div>
                    </div>
                </form:form>           
            </c:if>
        </div>
    </c:if>
</body>
</html>
