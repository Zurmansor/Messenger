<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <form:form method="post" action="/j_security_check" commandName="user">

    <H2 class="text-center">Sign In</H2>

    <div class="form-group">
      <label for="j_username" class="col-sm-2 control-label">Login</label>
      <div class="col-sm-10">
        <form:input path="j_username" type="text" class="form-control" id="login" placeholder="Login"/>
      </div>
    </div>

    <form:errors class="text-danger bg-danger" path="j_username"></form:errors>

    <div class="form-group">
      <label for="j_password" class="col-sm-2 control-label">Password</label>
      <div class="col-sm-10">
        <form:input path="j_password" type="text" class="form-control" id="j_password" placeholder="Password"/>
      </div>
    </div>

    <form:errors class="text-danger bg-danger" path="j_password"></form:errors>


    <input type="submit" class="btn btn-success center-block"  value="Login" />

  </form:form>
</t:template>


