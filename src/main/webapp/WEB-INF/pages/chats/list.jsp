<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>

      <H1>Chats</H1>

      <sec:authorize access="hasRole('admin')">
        <p><a href="chats/add" style="float:right;" class="btn btn-success center-block">
          Add a Chat
        </a></p>
      </sec:authorize>


  <div class="list-config">

   <c:forEach items="${chats}" var="chat">
     <div class="list-config-for">
    <div class="list-config-name">

      <h3 class="bg-success">${chat.name}</h3>
    </div>
    <div class="list-config-remove">


        <sec:authorize access="isAnonymous()">
          <a href="/chats/remove/${chat.id}" class="btn btn-danger">Delete</a>
        </sec:authorize>
    </div>
     </div>
    </c:forEach>

</t:template>

<%--<p class="bg-success">--%>
