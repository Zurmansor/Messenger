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

    <link href="css/font-awesome.css" rel="stylesheet">

    <%--<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">--%>
</head>
<body>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-8" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Helix Chat</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-8">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
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