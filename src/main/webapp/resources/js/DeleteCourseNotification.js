function confirmDelete(id) {
    document.getElementById("coursedel").style.display = "block";
    document.getElementById("delid").innerHTML = id;
    document.getElementById("delaction").action = "/cgs2/course_delete/" + id;
}

function cancelDelete() {
    document.getElementById("coursedel").style.display = "none";
    document.getElementById("delid").innerHTML = "?ID?";
    document.getElementById("delaction").action = "/";
}