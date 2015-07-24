<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <div class="page-container">
    <form:form class="form-horizontal" method="post" action="registration" commandName="user">

      <H2 class="text-center"><spring:message code="title.registration"/></H2>
      <div class="form-group">
        <spring:message code="registration.login" var="login"/>
        <label for="login" class="col-sm-2 control-label">${login}</label>
        <div class="col-sm-10">
          <form:input path="login" type="text" class="form-control" id="login" placeholder="${login}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="login"></form:errors>

      <div class="form-group">
        <spring:message code="registration.password" var="password"/>
        <label for="password" class="col-sm-2 control-label">${password}</label>
        <div class="col-sm-10">
          <form:input path="password" type="password" class="form-control" id="password" placeholder="${password}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="password"></form:errors>

      <div class="form-group">
        <spring:message code="registration.confirmPassword" var="confirmPassword"/>
        <label for="confirmPassword" class="col-sm-2 control-label">${confirmPassword}</label>
        <div class="col-sm-10">
          <form:input path="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="${confirmPassword}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="confirmPassword"></form:errors>

      <div class="form-group">
        <spring:message code="registration.first_name" var="first_name"/>
        <label for="firstName" class="col-sm-2 control-label">${first_name}</label>
        <div class="col-sm-10">
          <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="${first_name}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="firstName"></form:errors>

      <div class="form-group">
        <spring:message code="registration.last_name" var="last_name"/>
        <label for="lastName" class="col-sm-2 control-label">${last_name}</label>
        <div class="col-sm-10">
          <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="${last_name}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="lastName"></form:errors>

      <div class="form-group">
        <spring:message code="registration.email" var="email"/>
        <label for="email" class="col-sm-2 control-label">${email}</label>
        <div class="col-sm-10">
          <form:input path="email" type="email" class="form-control" id="email" placeholder="${email}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="email"></form:errors>

      <div class="form-group">
        <spring:message code="registration.phone" var="phone"/>
        <label for="phone" class="col-sm-2 control-label">${phone}</label>
        <div class="col-sm-10">
          <form:input path="phone" type="text" class="form-control" id="phone" placeholder="${phone}"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="phone"></form:errors>

      <div class="form-group">
        <spring:message code="registration.comment" var="comment"/>
        <label for="comment" class="col-sm-2 control-label">${comment}</label>
        <div class="col-sm-10">
          <form:textarea path="comment" type="text" class="form-control" rows="4" id="comment" placeholder="${comment}"/>
        </div>
      </div>

      <form:errors  class="text-danger bg-danger" path="comment"></form:errors>

      <spring:message code="registration.registration_btn" var="button"/>
      <input type="submit" class="btn btn-success center-block"  value="${button}" />

    </form:form>
  </div>
</t:template>


