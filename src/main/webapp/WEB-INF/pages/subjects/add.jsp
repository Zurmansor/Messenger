<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <form:form method="post" action="add" commandName="subject">

    <table>

      <tr>
        <td>
          <form:label path="name">Name</form:label>
        </td>
        <td>
          <form:input path="name" />
        </td>
        <td>
          <form:errors cssClass="error" path="name"></form:errors>
        </td>
      <tr>

      <tr>
        <td>
          <form:label path="description">Description</form:label>
        </td>
        <td>
          <form:textarea path="description" />
        </td>
        <td>
          <form:errors cssClass="error" path="description"></form:errors>
        </td>
      </tr>

        <td><input type="submit" value="Add subject" /></td>
      </tr>
    </table>
  </form:form>
</t:template>


