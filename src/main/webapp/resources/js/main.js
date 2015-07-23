$(document).ready(function() {
    addEvents();
});

function addEvents () {
    $('.delete-btn').click(onDeleteClick);
}

function onDeleteClick () {
    var href = $(this).attr("href");
    var name = $(this).parents('.panel-title').find('.object-name').text();
    var message = tr.youAreGoing + " " + tr.object + " \"" + name + "\". " + tr.areYouSure;
    if (confirm(message)) {
        location.href = href;
    }
    return false;
}