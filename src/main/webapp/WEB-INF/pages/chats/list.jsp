<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>

      <H1>Chats</H1>

      <sec:authorize access="hasRole('admin')">
        <p><a href="chats/add" style="float:right;" class="btn btn-success center-block">
          Add a Chat
        </a></p>
      </sec:authorize>


<%--  <div class="list-config">

   <c:forEach items="${chats}" var="chat">
     <div class="list-config-for">
    <div class="list-config-name">

      <h3 class="bg-success">${chat.name}</h3>
    </div>
&lt;%&ndash;    <div class="list-config-remove">
        <sec:authorize access="isAnonymous()">
          <a href="/chats/remove/${chat.id}" class="btn btn-danger">Delete</a>
        </sec:authorize>
    </div>&ndash;%&gt;
     </div>
    </c:forEach>--%>


    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:forEach items="${chats}" var="chat">
            <div class="panel panel-default">
                <div class="panel-heading clearfix" role="tab" id="chat-${chat.id}">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#description-${chat.id}" aria-expanded="false" aria-controls="description-${chat.id}">
                            <a href="/subjects/${subjectId}/chats/${chat.id}/messages"> ${chat.name} </a>
    <%--                        <sec:authorize access="hasRole('admin')">
                                <a href="/chats/remove/${chat.id}" class="btn btn-danger btn-xs pull-right">Delete</a>
                            </sec:authorize>--%>
                        </a>
                    </h4>
                </div>
                <div id="description-${chat.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="subject-${chat.id}">
                    <div class="panel-body">
                            ${chat.description}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</t:template>

<%--<p class="bg-success">--%>
