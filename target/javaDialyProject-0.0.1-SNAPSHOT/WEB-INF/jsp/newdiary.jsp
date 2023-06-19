<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/get-datetime.js"></script>
<title><c:out value="${account.loginID}"/> page</title>
</head>
<body onload="getNowDate()">
    <div id="head">
        <jsp:include page="/WEB-INF/jsp/common/header.jsp" /> <%--static include--%>
    </div>
    <div id="main">
        Welcome ${id}.
        <font color="red">
            ${errorMessage}
        </font>
        <form method=post action="main" name="newDiaryForm">
            <input type="date" name="dateTime" id="dateTime">
            <%-- <input type="text" name="sentence" placeholder="new diary"> --%>
            <textarea id="sentence" name="sentence" rows="5" cols="30">new diary</textarea>
            <input type="submit" value=save id="submitbutton">
            <input type="hidden" name="action" value="newDiary.submit"> <!--action.propertiesのnewDiary.submitの値をCreateDiaryBeanに渡す。-->
        </form>
        <form method=post action="main" name="userDiaryForm">
            <input type="submit" value=Archive id="submitbutton">
            <input type="hidden" name="action" value="userDiary.submit"> <!--action.propertiesのnewDiary.submitの値をCreateDiaryBeanに渡す。-->
        </form>
    </div>

    <div id="footer">
        <jsp:include page="/WEB-INF/jsp/common/footer.jsp" /> <%--static include--%>
    </div>
</body>
</html>
