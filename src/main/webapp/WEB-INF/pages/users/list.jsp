<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:template>
  <script language="javascript" type="text/javascript">
    tr.object = "<spring:message code="js.user"/>"
  </script>

  <H1><spring:message code="title.users"/></H1>
  <div class="list-subject-container">
    <sec:authorize access="hasRole('admin')">
      <p>
        <a href="/registration" class="btn btn-success">
          <spring:message code="user.add_user"/>
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
                <span class="object-name">${user.login}</span>
                <sec:authorize access="hasRole('admin')">
                    <div class="pull-right">
                      <a href="/users/edit/${user.id}" class="btn btn-default btn-xs"><spring:message code="options.edit"/></a>
                      <a href="/users/remove/${user.id}" class="btn btn-default btn-xs delete-btn"><spring:message code="options.delete"/></a>
                    </div>
                </sec:authorize>
            </a>
          </h4>
        </div>
        <div id="description-${user.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="user-${user.id}">
          <div class="panel-body">
              ${user.comment}
          </div>
        </div>
      </div>
    </c:forEach>
  </div>


</t:template>
