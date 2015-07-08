<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <div class="page-container">
    <form:form class="form-horizontal" method="post" action="registration" commandName="user">

      <H2 class="text-center">Registration</H2>
      <div class="form-group">
        <label for="login" class="col-sm-2 control-label">Login</label>
        <div class="col-sm-10">
          <form:input path="login" type="text" class="form-control" id="login" placeholder="Login"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="login"></form:errors>

      <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-10">
          <form:input path="password" type="password" class="form-control" id="password" placeholder="Password"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="password"></form:errors>

      <div class="form-group">
        <label for="confirmPassword" class="col-sm-2 control-label">Confirm password</label>
        <div class="col-sm-10">
          <form:input path="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="Confirm password"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="confirmPassword"></form:errors>

      <div class="form-group">
        <label for="firstName" class="col-sm-2 control-label">First name</label>
        <div class="col-sm-10">
          <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="First name"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="firstName"></form:errors>

      <div class="form-group">
        <label for="lastName" class="col-sm-2 control-label">Last name</label>
        <div class="col-sm-10">
          <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Last name"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="lastName"></form:errors>

      <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-10">
          <form:input path="email" type="email" class="form-control" id="email" placeholder="Email"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="email"></form:errors>

      <div class="form-group">
        <label for="phone" class="col-sm-2 control-label">Phone</label>
        <div class="col-sm-10">
          <form:input path="phone" type="text" class="form-control" id="email" placeholder="Phone"/>
        </div>
      </div>

      <form:errors class="text-danger bg-danger" path="phone"></form:errors>

      <div class="form-group">
        <label for="comment" class="col-sm-2 control-label">Comment</label>
        <div class="col-sm-10">
          <form:textarea path="comment" type="text" class="form-control" rows="4" id="comment" placeholder="Comment"/>
        </div>
      </div>

      <form:errors  class="text-danger bg-danger" path="comment"></form:errors>



    <input type="submit" class="btn btn-success center-block"  value="Registrate" />
    <%--<a href="sign_in">sign in</a>--%>


    </form:form>
  </div>
</t:template>


