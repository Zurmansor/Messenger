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
            lastMessageTime = formatDate(new Date(data.pop().created), "%Y-%M-%d %H:%m:%s.%S");
            scrollToBottom();
        }
        setTimeout(getNewMessages, 1000);
    }).error(function(error) {
        console.error(error);
    });
}

function formatDate(date, fmt) {
    function pad(value) {
        return (value.toString().length < 2) ? '0' + value : value;
    }
    return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
        switch (fmtCode) {
            case 'Y':
                return date.getUTCFullYear();
            case 'M':
                return pad(date.getUTCMonth() + 1);
            case 'd':
                return pad(date.getUTCDate());
            case 'H':
                return pad(date.getUTCHours() + 3);
            case 'm':
                return pad(date.getUTCMinutes());
            case 's':
                return pad(date.getUTCSeconds());
            case 'S':
                return pad(date.getUTCMilliseconds());
            default:
                throw new Error('Unsupported format code: ' + fmtCode);
        }
    });
}