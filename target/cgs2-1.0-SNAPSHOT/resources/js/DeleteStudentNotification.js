function confirmDelete(id) {
    document.getElementById("studentdel").style.display = "block";
    document.getElementById("delid").innerHTML = id;
    document.getElementById("delaction").action = "/cgs2/student_delete/" + id;
}

function cancelDelete() {
    document.getElementById("studentdel").style.display = "none";
    document.getElementById("delid").innerHTML = "?ID?";
    document.getElementById("delaction").action = "/";
}