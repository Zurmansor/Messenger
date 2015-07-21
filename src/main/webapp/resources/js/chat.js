$(document).ready(function() {
    addEvents();
    setTimeout(getNewMessages, 2000);
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
    var formAddMessage = $("#form-add-message");
    var url = formAddMessage.attr("action");
    var message = $("#text");

    $.ajax({
        url: url,
        data: formAddMessage.serialize(),
        method: "POST"
    }).success(function(data) {
        if (data == "ok") {
            getNewMessages();
            message.val("").focus();
        }
    }).error(function(error) {
        console.error(error);
    });
}

function appendMessage (message, name) {
    if (!name) {
        name = login;
    }
    var messageDesk = $("#message-desk");

    var div = $("<div/>", {
        class: "alert alert-info chat-message"
    });
    var spanLogin = $("<strong/>");
    var spanText = $("<span/>");
    spanLogin.text(name + ": ");
    spanText.text(message);
    div.append(spanLogin);
    div.append(spanText);

    messageDesk.append(div);
}

function scrollToBottom () {
    var panel = $('.panel-body-chat');
    var height = panel[0].scrollHeight;
    panel.scrollTop(height);
}

function getNewMessages () {
    $.ajax({
        url: "messages/get/" + lastMessageTime,
        method: "GET"
    }).success(function(data) {
        var len = data.length;
        if (len > 0) {
            for (i = 0; i < len; i++) {
                appendMessage(data[i].text, data[i].user.login);
            }
            lastMessageTime = data.pop().created;
            console.log(lastMessageTime);
            scrollToBottom();
        }
        setTimeout(getNewMessages, 1000);
    }).error(function(error) {
        console.error(error);
    });
}