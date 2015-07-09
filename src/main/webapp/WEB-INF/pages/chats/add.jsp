<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <div class="page-container">
    <H2 class="text-center">Add chat</H2>
      <form:form class="form-horizontal" method="post" action="add" commandName="chat">



        <div class="form-group">
          <label for="name" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <form:input path="name" type="text" class="form-control" id="name" placeholder="Name"/>
          </div>
        </div>

        <form:errors class="text-danger bg-danger" path="name"></form:errors>

        <input type="submit" class="btn btn-success center-block"  value="Add chat" />


      </form:form>
  </div>

</t:template>

