function lookupID(page)
{
    var id = document.getElementsByName("idToLookup")[0].value;
    document.location.href = "/cgs2/" + page + "/" + id;
}


