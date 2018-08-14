<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>College Grading System Home</title>
    <link rel="stylesheet" type="text/css" href="resources/w3css.css">
</head>
<body>
    <jsp:include page="/resources/top_banners/home_top_banner.jsp"/>
    <div class="w3-container w3-light-blue w3-border-top w3-border-indigo">
        <h2>Welcome!</h2>
    </div>
    <div class="w3-container w3-pale-blue w3-border-top w3-border-bottom w3-border-indigo w3-margin-bottom">
        <p>Welcome to the College Grading System, a site where student administrators, program administrators, and teachers can easily and efficiently enter student, teacher, course, and result information.</p>
        <p>To begin, use the links below, or the menu to the left. You will need to log in to access the appropriate resources.</p>
        <p>Please note that <b>JavaScript must be enabled</b> for full website functionality.</p>
    </div>
    <div class="w3-row-padding w3-margin-top">
        <div class="w3-third">
            <div class="w3-card w3-pale-blue w3-padding-small">
                <h3 class="w3-center">For Student Administrators</h3>
                <div class="w3-container">
                    <button class="w3-button w3-block w3-blue" style="width:100%"><a href="/cgs2/student_add">Add Student</a></button>
                    <button class="w3-button w3-block w3-blue" style="width:100%"><a href="/cgs2/student_list">List Students</a></button>
                    <button class="w3-button w3-block w3-blue" style="width:100%"><a href="/cgs2/student_edit">Edit Student</a></button>
                    <button class="w3-button w3-block w3-blue" style="width:100%"><a href="/cgs2/student_details">Student Details</a></button>
                </div>
            </div>
        </div>
        <div class="w3-third">
            <div class="w3-card w3-khaki w3-padding-small">
                <h3 class="w3-center">For Program Administrators</h3>
                <div class="w3-container">
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/teacher_add">Add Teacher</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/teacher_list">List Teachers</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/teacher_edit">Edit Teacher</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/teacher_details">Teacher Details</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/course_add">Add Course</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/course_list">List Courses</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/course_edit">Edit Course</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/course_details">Course Details</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/course_add_student">Add Student to Course</a></button>
                    <button class="w3-button w3-block w3-amber" style="width:100%"><a href="/cgs2/course_add_teacher">Add Teacher to Course</a></button>
                </div>
            </div>
        </div>
        <div class="w3-third">
            <div class="w3-card w3-pale-green w3-padding-small">
                <h3 class="w3-center">For Teachers</h3>
                <div class="w3-container">
                    <button class="w3-button w3-block w3-green" style="width:100%"><a href="/cgs2/result_add">Add Result</a></button>
                    <button class="w3-button w3-block w3-green" style="width:100%"><a href="/cgs2/result_list">List Results</a></button>
                    <button class="w3-button w3-block w3-green" style="width:100%"><a href="/cgs2/result_edit">Edit Result</a></button>
                </div>
            </div>
        </div>
    </div>
</body>
