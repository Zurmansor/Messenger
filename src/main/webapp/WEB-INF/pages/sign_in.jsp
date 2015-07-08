<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
  <form:form method="post" action="registration" commandName="user">
    <head>Sign In </head>
    <table>
      <tr>
        <td>
          <form:label path="login">Login</form:label>
        </td>
        <td>
          <form:input path="login" />
        </td>
        <td>
          <form:errors cssClass="error" path="login"></form:errors>
        </td>
      </tr>
      <tr>
        <td>
          <form:label path="password">Password</form:label>
        </td>
        <td>
          <form:input type="password" path="password" />
        </td>
        <td>
          <form:errors cssClass="error" path="password"></form:errors>
        </td>
      </tr>

      <tr>
        <td><input type="submit" value="Sign in" /></td>
        <td><a href="registration">sign up</a> </td>
      </tr>
    </table>
  </form:form>
</t:template>


