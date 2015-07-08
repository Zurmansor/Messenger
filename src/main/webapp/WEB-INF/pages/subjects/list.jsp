<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
  <H1>Subjects</H1>
  <ul>

    <c:forEach items="${subjects}" var="subject">
      <%--<li>--%><h3 class="bg-success">${subject.name}</h3>
        <sec:authorize access="hasRole('admin')">
          <a href="/subjects/remove/${subject.id}">Delete</a>
        </sec:authorize>
     <%-- </li>--%>
    </c:forEach>

  </ul>
  <sec:authorize access="hasRole('admin')">
      <p><a href="/subjects/add">Add a subject</a></p>
  </sec:authorize>
</t:template>

<%--<p class="bg-success">--%>
