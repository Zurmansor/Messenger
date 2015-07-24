<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<t:template>
    <script language="javascript" type="text/javascript">
        var login = "${login}";
        var lastMessageTime = "1000-01-01 00:00:00.0";
        <c:if test="${messages.size()>0}">
            lastMessageTime = "${messages.get(0).created}";
        </c:if>

    </script>
    <script language="javascript" type="text/javascript" src="/resources/js/chat.js"></script>

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">${chatName}</h3>
        </div>
        <div id="message-desk" class="panel-body panel-body-chat">
            <c:forEach items="${messages}" var="message">
              <div class="alert alert-info chat-message">
                <strong>${message.user.login}: </strong>
                <span>${message.text}</span>
              </div>
            </c:forEach>
        </div>
    </div>

    <div class="page-container">

        <div class="form-group">
            <div class="input-group">
            </div>
        </div>

        <form:form id="form-add-message" class="form-inline" method="post" action="messages/add" commandName="message">
            <div class="input-group col-lg-12">
                <spring:message code="message.your_message" var="your_message"/>
                <form:input path="text" type="text" class="form-control col-lg-12" id="text" placeholder="${your_message}"/>
                <span class="input-group-btn">
                    <spring:message code="message.send_message_btn" var="send_message"/>
                    <input type="submit" id="btn-add-message" class="btn btn-success"  value="${send_message}" />
                </span>
            </div>
        </form:form>
    </div>
</t:template>