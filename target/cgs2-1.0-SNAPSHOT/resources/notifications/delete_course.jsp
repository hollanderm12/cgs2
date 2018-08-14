<div id="coursedel" class="w3-panel w3-pale-red w3-padding" style="display: none">
    <b>Are you sure you want to delete course <span id="delid">?ID?</span>? 
        This will also delete all associations of the students and teachers registered into this course.</b>
    <br>
    <form id="delaction" action="/" method="POST">
        <button type="submit" class="w3-button w3-large w3-red">Confirm Delete</button>
        <button type="button" class="w3-button w3-large w3-red" onclick="javascript:cancelDelete()">Cancel</button>
    </form>
</div>