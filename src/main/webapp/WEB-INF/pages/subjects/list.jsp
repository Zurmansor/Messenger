<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:template>

  <div class="list-subject-container">
      <ol class="breadcrumb">
          <li class="active"><a href="#"><spring:message code="title.subjects"/></a></li>
      </ol>

      <H1><spring:message code="title.subjects"/></H1>

      <sec:authorize access="hasRole('admin')">
          <p>
              <a href="/subjects/add" class="btn btn-success">
                  <spring:message code="subject.add_subject"/>
              </a>
          </p>
      </sec:authorize>
  </div>

    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:forEach items="${subjects}" var="subject">
            <div class="panel panel-default">
                <div class="panel-heading clearfix" role="tab" id="subject-${subject.id}">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#description-${subject.id}" aria-expanded="false" aria-controls="description-${subject.id}">
                            <a href="/subjects/${subject.id}/chats"> ${subject.name} </a>
                            <sec:authorize access="hasRole('admin')">
                                <a href="/subjects/edit/${subject.id}" class="btn btn-info btn-xs pull-right"><spring:message code="options.edit"/></a>
                                <a href="/subjects/remove/${subject.id}" class="btn btn-danger btn-xs pull-right"><spring:message code="options.delete"/></a>
                            </sec:authorize>
                        </a>
                    </h4>
                </div>
                <div id="description-${subject.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="subject-${subject.id}">
                    <div class="panel-body">
                        ${subject.description}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</t:template>


