<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:template>
    <script language="javascript" type="text/javascript">
        tr.object = "<spring:message code="js.chat"/>"
    </script>

    <ol class="breadcrumb">
        <li><a href="/subjects">Subjects: ${subjectName}</a></li>
        <li class="active"><a href="#">Chats</a></li>
    </ol>
      <H1>Chats</H1>

      <sec:authorize access="isAuthenticated()">
        <p><a href="chats/add" class="btn btn-success">Add a Chat</a></p>
      </sec:authorize>

    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:forEach items="${chats}" var="chat">
            <div class="panel panel-default">
                <div class="panel-heading clearfix" role="tab" id="chat-${chat.id}">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#description-${chat.id}" aria-expanded="false" aria-controls="description-${chat.id}">
                            <a class="object-name" href="/subjects/${subjectId}/chats/${chat.id}/messages">${chat.name}</a>
                            <div class="pull-right">
                                <c:if test="${chat.description.length() > 0}">
                                    <a data-toggle="collapse" href="#description-${chat.id}" data-parent="#accordion"
                                       aria-expanded="true" aria-controls="description-${chat.id}"><small><i class="text-info">description</i></small></a>
                                </c:if>
                                <sec:authorize access="hasRole('admin')">
                                    <a href="/subjects/${subjectId}/chats/edit/${chat.id}" class="btn btn-default btn-xs">Edit</a>
                                    <a href="/subjects/${subjectId}/chats/remove/${chat.id}" class="btn btn-default btn-xs delete-btn">Delete</a>
                                </sec:authorize>
                                <sec:authorize access="hasRole('user')">
                                    <c:if test="${user.id eq chat.createdBy}">
                                        <a href="/subjects/${subjectId}/chats/edit/${chat.id}" class="btn btn-default btn-xs">Edit</a>
                                        <a href="/subjects/${subjectId}/chats/remove/${chat.id}" class="btn btn-default btn-xs delete-btn">Delete</a>
                                    </c:if>
                                </sec:authorize>
                            </div>
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
