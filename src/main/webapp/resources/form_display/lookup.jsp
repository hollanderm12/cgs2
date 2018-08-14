<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix ="fn"%>
<c:set var="page" value="${param.pageName}"/>
<c:set var="splitPage" value="${fn:split(page, '_')}"/>
<c:set var="capitalSplit" value="${fn:toUpperCase(fn:substring(splitPage[0], 0, 1))}${fn:substring(splitPage[0], 1, fn:length(splitPage[0]))}"/>
<p>
    To display the details of a <c:out value="${splitPage[0]}"/>, please find the <c:out value="${splitPage[0]}"/> on the 
    <a href="/cgs2/${splitPage[0]}_list"><c:out value="${capitalSplit}"/> List</a> page.
</p>
<p>
    If you know the course's ID, enter it here and click on the Find <c:out value="${capitalSplit}"/> button:
    <input type="number" min="1" max="9999999999" name="idToLookup" size="10" maxlength="10">
</p>
<button type="button" onclick="javascript:lookupID('${page}');" 
    class="w3-button w3-large ${param.buttonColor} w3-border w3-border-black">Find <c:out value="${capitalSplit}"/></button>