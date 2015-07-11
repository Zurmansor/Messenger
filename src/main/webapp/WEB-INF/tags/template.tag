<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype>
<html>
<head>
    <title>Messenger</title>
    <!-- Latest compiled and minified CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- Optional theme -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />" rel="stylesheet">

    <script src="resources/js/jquery-1.11.3.min.js"></script>
    <script src="/resources/bootstrap/js/transition.js"></script>
    <script src="/resources/bootstrap/js/collapse.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Helix Chat</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-8">
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <li class="active"><a href="/subjects">Subjects list</a></li>
                </sec:authorize>
                <li><a href="#">Link</a></li>
                <li><a href="#">Link</a></li>
            </ul>
            <p class="navbar-text navbar-right">
                <sec:authorize access="isAnonymous()">
                    <a href="/spring_security_login">Sign In</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a href="/j_spring_security_logout">Sign Out</a>
                </sec:authorize>
            </p>
        </div>
    </div>
</nav>

<div class="content">

    <jsp:doBody/>
</div>
</body>
</html>