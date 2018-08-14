function register() {
    var id1 = document.getElementsByName("selection")[0].value;
    var id2 = document.getElementsByName("selection")[0].value;
    document.getElementById("submission").action = "/cgs2/course_add_student/" + id1 + "/" + id2;
}