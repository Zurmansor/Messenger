<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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
            <h3 class="panel-title">Helix Chat</h3>
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
            <div class="form-group">
                <label for="text" class=" control-label">Your message:</label>
                <form:input path="text" type="text" class="form-control" id="text" placeholder="Text"/>
                <%--<input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">--%>
            </div>
            <input type="submit" id="btn-add-message" class="btn btn-success"  value="Add message" />
        </form:form>
    </div>
</t:template>