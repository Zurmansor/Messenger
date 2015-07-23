<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <div class="page-container">

  <form:form class="form-horizontal" method="post" action="add" commandName="subject" accept-charset="UTF-8">

    <H2 class="text-center"><spring:message code="title.add_subject"/></H2>

    <div class="form-group">
      <label for="name" class="col-sm-2 control-label"><spring:message code="edit.name"/></label>
      <div class="col-sm-10">
        <spring:message code="edit.name" var="name"/>
        <form:input path="name" type="text" class="form-control" id="name" placeholder="${name}"/>
      </div>
    </div>

    <form:errors class="text-danger bg-danger" path="name"></form:errors>

    <div class="form-group">
      <label for="description" class="col-sm-2 control-label"><spring:message code="edit.description"/></label>
      <div class="col-sm-10">
        <spring:message code="edit.description" var="description"/>
        <form:input path="description" type="text" class="form-control" id="description" placeholder="${description}"/>
      </div>
    </div>

    <form:errors class="text-danger bg-danger" path="description"></form:errors>
    <spring:message code="subject.add_subject" var="add_subject"/>
    <input type="submit" class="btn btn-success center-block"  value="${add_subject}" />

</div>
  </form:form>
</t:template>


