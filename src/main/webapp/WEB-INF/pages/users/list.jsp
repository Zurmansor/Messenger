<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>

  <H1>Users</H1>
  <div class="list-subject-container">
    <sec:authorize access="hasRole('admin')">
      <p>
        <a href="/registration" class="btn btn-success">
          Add user
        </a>
      </p>
    </sec:authorize>
  </div>

  <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <c:forEach items="${users}" var="user">
      <div class="panel panel-default">
        <div class="panel-heading clearfix" role="tab" id="user-${user.id}">
          <h4 class="panel-title">
            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#description-${user.id}" aria-expanded="false" aria-controls="description-${user.id}">
                <%--              <a href="/subjects/${user.id}/chats"> ${subject.name} </a>--%>
                ${user.login}
              <sec:authorize access="hasRole('admin')">
                <a href="/users/edit/${user.id}" class="btn btn-info btn-xs pull-right">Edit</a>
                <a href="/users/remove/${user.id}" class="btn btn-danger btn-xs pull-right">Delete</a>
              </sec:authorize>
            </a>
          </h4>
        </div>
        <div id="description-${user.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="user-${user.id}">
          <div class="panel-body">
              ${user.created}
          </div>
        </div>
      </div>
    </c:forEach>
  </div>


</t:template>

<%--<p class="bg-success">--%>
