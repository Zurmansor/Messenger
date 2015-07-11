<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>

      <H1>Messages</H1>

<%--      <sec:authorize access="hasRole('admin')">
        <p><a href="/messages/add" style="float:right;" class="btn btn-success center-block">
          Add a Massage
        </a></p>
      </sec:authorize>--%>


  <div class="list-config">

       <c:forEach items="${messages}" var="message">
            <div class="list-config-for">
                <div class="list-config-name">

                    <h3 class="bg-success">${message.text}</h3>
                </div>
                    <div class="list-config-remove">


                        <sec:authorize access="isAnonymous()">
                            <a href="/massages/remove/${message.id}" class="btn btn-danger">Delete</a>
                        </sec:authorize>
                    </div>
                </div>
            </div>
       </c:forEach>

  </div>

    <div class="page-container">
        <H2 class="text-center">Add message</H2>
        <form:form class="form-horizontal" method="post" action="messages/add" commandName="message">

            <div class="form-group">
                <label for="text" class="col-sm-2 control-label">Text</label>

                <div class="col-sm-10">
                    <form:input path="text" type="text" class="form-control" id="text" placeholder="Text"/>
                </div>
            </div>

            <form:errors class="text-danger bg-danger" path="text"></form:errors>

            <input type="submit" class="btn btn-success center-block"  value="Add message" />


        </form:form>
    </div>
</t:template>

<%--<p class="bg-success">--%>
