<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:template>
    <h2>
        --> <spring:message code="label.title"/>
    </h2>


    <span style="float: right">
        <a href="?lang=en">en</a>
        |
        <a href="?lang=ru">ru</a>
    </span>
</t:template>