<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="false" %> <%-- not use session --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript" src="js/clear-button.js"></script>
<title>Login page</title>
</head>
<body>
    <div id="head">
        <jsp:include page="/WEB-INF/jsp/common/header.jsp" /> <%--static include--%>
        <h1>Login</h1>
    </div>
    <div id="main">
        <font color="red">
            ${errorMessage}
        </font>
        <form method=post action="main" name="loginForm">
            <input type="text" name="loginID" placeholder="Enter login id">
            <input type="password" name="password" placeholder="Enter password">
            <input type="submit" value=Login>
            <input type="hidden" name="action" value="login.submit">
            <input type="button" value=clear onclick=loginClearAll();>
        </form>
    </div>

    <div id="footer">
        <jsp:include page="/WEB-INF/jsp/common/footer.jsp" /> <%--static include--%>
    </div>
</body>
</html>
