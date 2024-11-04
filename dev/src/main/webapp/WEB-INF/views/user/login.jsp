<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>

<form action="/user/login" method="post" name="loginForm">
    <div>
        <input type="text" name="userId" placeholder="아이디"/>
        <input type="password" name="userPassword" placeholder="비밀번호"/>
        <button type="submit" id="loginBtn">로그인</button>
    </div>
    <div>
        <a href="/user/searchId">아이디찾기</a>
        <a href="/user/searchPw">비밀번호찾기</a>
        <a href="/user/join">회원가입</a>
    </div>
 </form>

<jsp:include page="/WEB-INF/views/main/footer.jsp"/>

<script>
    <% if (request.getAttribute("loginFailed") != null) { %>
        alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
    <% } %>
</script>
</body>
</html>
