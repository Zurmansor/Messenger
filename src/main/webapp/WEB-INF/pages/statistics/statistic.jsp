<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>

  <H1>Statistic</H1>
  <p>
    <ul>
        <li>
            Number of users: ${userCount}
        </li>
        <li>
            Number of subjects: ${subjectCount}
        </li>
        <li>
            Number of chats: ${chatstCount}
        </li>
        <li>
            Number of messages: ${messagesCount}
        </li>
    </ul>
  </p>


</t:template>


