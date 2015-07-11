<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <div class="page-container">

  <form:form class="form-horizontal" method="post" action="add" commandName="subject">

    <H2 class="text-center">Add subject</H2>

    <div class="form-group">
      <label for="name" class="col-sm-2 control-label">Name</label>
      <div class="col-sm-10">
        <form:input path="name" type="text" class="form-control" id="name" placeholder="Name"/>
      </div>
    </div>

    <form:errors class="text-danger bg-danger" path="name"></form:errors>

    <div class="form-group">
      <label for="description" class="col-sm-2 control-label">Description</label>
      <div class="col-sm-10">
        <form:input path="description" type="text" class="form-control" id="description" placeholder="Description"/>
      </div>
    </div>

    <form:errors class="text-danger bg-danger" path="description"></form:errors>

    <input type="submit" class="btn btn-success center-block"  value="Add subject" />

</div>
  </form:form>
</t:template>


