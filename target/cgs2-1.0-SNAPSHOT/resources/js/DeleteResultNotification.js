function confirmDelete(id) {
    document.getElementById("resultdel").style.display = "block";
    document.getElementById("delid").innerHTML = id;
    document.getElementById("delaction").action = "/cgs2/result_delete/" + id;
}

function cancelDelete() {
    document.getElementById("resultdel").style.display = "none";
    document.getElementById("delid").innerHTML = "?ID?";
    document.getElementById("delaction").action = "/";
}