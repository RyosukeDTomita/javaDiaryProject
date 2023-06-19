<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title><c:out value="${loginID}"/> page</title>
</head>
<body>
    <div id="head">
        <jsp:include page="/WEB-INF/jsp/common/header.jsp" /> <%--static include--%>
    </div>
    <div id="main">
        <font color="red">
            <p>${errorMessage}</p>
        </font>
        <form method=post action="main" name="newDiaryForm">
            <input type="submit" value=New>
            <input type="hidden" name="action" value="newDiary.submit">
        </form>
        <table>
            <thead>
                <tr>
                    <th colspan="4"><c:out value="${loginID}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="diaryVO" items="${diaryVOList}">
                    <tr>
                        <td><c:out value="${diaryVO.dateTime}"/></td>
                        <td><c:out value="${diaryVO.loginID}"/></td>
                        <td><c:out value="${diaryVO.sentence}"/></td>
                        <td><input type="checkbox" id="isDelete">
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <form method=post action="main" name="deleteDiaryForm">
            <input type="submit" value=delete>
            <input type="hidden" name="action" value="deleteDiary.submit">
        </form>
    </div>

    <div id="footer">
        <jsp:include page="/WEB-INF/jsp/common/footer.jsp"/> <%--static include--%>
    </div>
</body>
</html>
