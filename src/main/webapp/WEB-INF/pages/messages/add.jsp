<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <div class="page-container">
    <H2 class="text-center">Add message</H2>
      <form:form class="form-horizontal" method="post" action="add" commandName="message">



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


