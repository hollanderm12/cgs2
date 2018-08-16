<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Course Details</title>
    <link rel="stylesheet" type="text/css" href="/cgs2/resources/w3css.css">
    <script src="/cgs2/resources/js/IDLookup.js"></script>
</head>
<body class="w3-khaki">
    <div class="w3-bar w3-amber">
    <jsp:include page="/resources/top_banners/programs_top_banner.jsp"/>
    </div>
    <div class="w3-container w3-yellow w3-border-top w3-border-bottom w3-border-indigo">
        <h2>Course details</h2>
    </div>
    <c:if test="${not empty statusMsg}">
        <div class="statusmsg w3-margin-top">
            <c:out value="${statusMsg}"/>
        </div>
    </c:if>
    <c:if test="${lookupError}">
        <div class="errorblock w3-margin-top">
            <p>The course ID specified was not found. Please verify the course ID and try again.</p>
        </div>
    </c:if>
    <div class="w3-container">
        <c:set var="c" scope="page" value="${detailsFound}"/>
        <c:if test="${empty c}">
            <jsp:include page="/resources/form_display/lookup.jsp">
                <jsp:param name="pageName" value="course_details"/>
                <jsp:param name="buttonColor" value="w3-amber"/>
            </jsp:include>
        </c:if>
        <c:if test="${not empty c}">
            <h3>The details for course ID <c:out value="${c.courseID}"/> are below:</h3>    
            <form:form cssClass="w3-container">
                <jsp:include page="/resources/form_display/course_form.jsp">
                    <jsp:param name="readOnly" value="true"/>
                    <jsp:param name="courseName" value="${c.courseName}"/>
                    <jsp:param name="credits" value="${c.credits}"/>
                </jsp:include>
            </form:form>
            <div class="w3-row-padding w3-margin-left">       
                <div class="w3-col">
                    <form>
                        <button type="submit" formaction="../course_edit/${c.courseID}" 
                                class="w3-button w3-large w3-amber w3-border w3-border-black">Edit Course</button>
                    </form>
                </div>
            </div>
            <div class="w3-panel w3-border-top w3-border-indigo">
                <c:if test="${empty studentsRegistered}">
                    <h3>There are no students registered in this course. Click <a href="/cgs2/course_add_student">here</a> to register a student.</h3>
                </c:if>
                <c:if test="${not empty studentsRegistered}">
                    <h3>The following students are registered in this course:</h3>
                    <table class="w3-table-all">
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email Address</th>
                            <th>Major</th>
                            <th>Unregister</th>
                        </tr>
                        <c:forEach var="s" items="${studentsRegistered}">
                            <tr>
                                <td>${s.studentID}</td>
                                <td>${s.firstName}</td>
                                <td>${s.lastName}</td>
                                <td>${s.email}</td>
                                <td>${s.major}</td>                               
                                <form action="../course_remove_student/${c.courseID}/${s.studentID}" method="POST">
                                    <td>
                                        <input type="submit" class="w3-button w3-tiny w3-red w3-border w3-border-black" value="Unregister">
                                    </td>
                                </form>                              
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <div class="w3-panel w3-border-top w3-border-indigo">
                <c:if test="${empty teachersRegistered}">
                    <h3>There are no teachers registered in this course. Click <a href="/cgs2/course_add_teacher">here</a> to register a teacher.</h3>
                </c:if>           
                <c:if test="${not empty teachersRegistered}">
                    <h3>The following teachers are registered in this course:</h3>
                    <table class="w3-table-all">
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email Address</th>
                            <th>Salary</th>
                            <th>Unregister</th>
                        </tr>
                        <c:forEach var="t" items="${teachersRegistered}">
                            <tr>
                                <td>${t.teacherID}</td>
                                <td>${t.firstName}</td>
                                <td>${t.lastName}</td>
                                <td>${t.email}</td>
                                <td>${t.salary}</td>
                                <form action="../course_remove_teacher/${c.courseID}/${t.teacherID}" method="POST">
                                    <td>
                                        <input type="submit" class="w3-button w3-tiny w3-red w3-border w3-border-black" value="Unregister">
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </c:if>
    </div>
</body>
</html>