$(document).ready(function() {
    addEvents();
});

function addEvents () {
    var formAddMessage = $("#form-add-message");
    formAddMessage.submit(onSubmitForm);
}

function onSubmitForm () {
    addMessage();
    return false;
}

function addMessage () {
    console.log(1234567890);
    var formAddMessage = $("#form-add-message");
    var url = formAddMessage.attr("action");
    var message = $("#text");

    $.ajax({
        url: url,
        data: formAddMessage.serialize(),
        method: "POST"
    }).success(function(data) {
        if (data == "ok") {
            appendMessage(message.val());
            message.val("");
        }
    }).error(function(error) {
        console.error(error);
    });
}

function appendMessage (message) {
    var messageDesk = $("#message-desk");

    var div = $("<div/>");
    var spanLogin = $("<span/>");
    var spanText = $("<span/>");
    spanLogin.text(login + ": ");
    spanText.text(message);
    div.append(spanLogin);
    div.append(spanText);

    messageDesk.append(div);
    //console.log(message);
}