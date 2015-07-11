<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
  <sec:authorize access="isAnonymous()">
      <p><a class="btn btn-primary" href="registration">Registration</a></p>
  </sec:authorize>
</t:template>