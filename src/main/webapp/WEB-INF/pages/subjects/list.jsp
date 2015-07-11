<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>

  <div class="list-subject-container">
      <H1>Subjects</H1>

      <sec:authorize access="hasRole('admin')">
        <p><a href="/subjects/add" style="float:right;" class="btn btn-success center-block">
          Add a subject
        </a></p>
      </sec:authorize>
  </div>

  <div class="list-config">

   <c:forEach items="${subjects}" var="subject">
     <div class="list-config-for">
    <div class="list-config-name">

      <h3 class="bg-success">${subject.name}</h3>
    </div>
    <div class="list-config-remove">

        <sec:authorize access="hasRole('admin')">
          <a href="/subjects/remove/${subject.id}" class="btn btn-danger">Delete</a>
        </sec:authorize>
    </div>
     </div>
    </c:forEach>

  </div>

</t:template>

<%--<p class="bg-success">--%>
