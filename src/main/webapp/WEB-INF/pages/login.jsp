<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <form:form method="post" action="/j_spring_security_check" commandName="user">

    <H2 class="text-center">Sign In</H2>

    <div class="form-group">
      <label for="username" class="col-sm-2 control-label">Login</label>
      <div class="col-sm-10">
        <input type='text' class="form-control" name='username' id="username" value=''>
      </div>
    </div>

    <div class="form-group">
      <label for="password" class="col-sm-2 control-label">Password</label>
      <div class="col-sm-10">
        <input type='password' class="form-control" name='password' id="password" />
      </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />

    <div class="form-group">
      <input type="submit" class="btn btn-success center-block"  value="Login" />
    </div>


  </form:form>
</t:template>


