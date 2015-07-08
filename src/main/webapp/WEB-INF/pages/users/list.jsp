<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
  <H1>Users</H1>
  <ul>

    <c:forEach items="${users}" var="user">
      <li><h3 class="bg-success">${user.login}</h3>
        <sec:authorize access="hasRole('admin')">
          <a href="/users/remove/${user.id}" class="btn btn-danger">Delete</a>
        </sec:authorize>
      </li>
    </c:forEach>

  </ul>
  <sec:authorize access="hasRole('admin')">
      <p><a href="/registration" class="btn btn-success <%--center-block--%>">Add user</a></p>
  </sec:authorize>
</t:template>

<%--<p class="bg-success">--%>
