function confirmDelete(id) {
    document.getElementById("teacherdel").style.display = "block";
    document.getElementById("delid").innerHTML = id;
    document.getElementById("delaction").action = "/cgs2/teacher_delete/" + id;
}

function cancelDelete() {
    document.getElementById("teacherdel").style.display = "none";
    document.getElementById("delid").innerHTML = "?ID?";
    document.getElementById("delaction").action = "/";
}