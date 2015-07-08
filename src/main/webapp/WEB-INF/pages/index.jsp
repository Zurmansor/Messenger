<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>

  <div class="page-header">
    <h1>Helix Chat</h1>
  </div>

  <sec:authorize access="isAnonymous()">
      <p><a class="btn btn-primary" href="registration">Registration</a></p>
  </sec:authorize>

  <sec:authorize access="isAuthenticated()">
     <p><a class="btn btn-info" href="/subjects">Subjects list</a></p>
  </sec:authorize>

  <%--</form:form>--%>
</t:template>