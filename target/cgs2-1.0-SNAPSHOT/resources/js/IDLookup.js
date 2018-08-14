function lookupID(page)
{
    var id = document.getElementsByName("idToLookup")[0].value;
    if(id % 1 === 0 && id > 0)
        document.location.href = "/cgs2/" + page + "/" + id;
    else
        alert("The ID must be a whole positive number. Please verify your input and try again.");
}


