<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Messenger</title>
    <!-- Latest compiled and minified CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- Optional theme -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />" rel="stylesheet">

    <script src="/resources/js/jquery-1.11.3.min.js"></script>
    <script src="/resources/bootstrap/js/transition.js"></script>
    <script src="/resources/bootstrap/js/collapse.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
                <a class="navbar-brand" href="/">
                    <img style="max-width:100px; margin-top: -10px; height: 36px; display: inline" src="/resources/img/logo.png"/>
                    Helix Chat
                </a>
         </div>
        <nav id="bs-navbar" class="navbar-collapse collapse in" aria-expanded="true">

            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/subjects">Subjects list</a></li>
                </sec:authorize>

                <li><a href="/users"><spring:message code="nav.users"/></a></li>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="/statistic">Statistic</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <%--    <p class="navbar-text">--%>
                <li>
                    <sec:authorize access="isAnonymous()">
                        <a href="registration" st>Registration</a>
                    </sec:authorize>
                </li>
                <li>

                    <sec:authorize access="isAnonymous()">
                        <a href="/login">Sign In</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="/j_spring_security_logout">Sign Out</a>
                    </sec:authorize>
                    <%--</p>--%>
                </li>

            </ul>
           <%-- <p class="navbar-text navbar-right">
                <sec:authorize access="isAnonymous()">
                    <a href="/login">Sign In</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a href="/j_spring_security_logout">Sign Out</a>
                </sec:authorize>
            </p>--%>

        </nav>
    </div>
</nav>

<div class="content" style="width:77%;margin:0 auto;">
    <jsp:doBody/>
</div>
</body>
</html>