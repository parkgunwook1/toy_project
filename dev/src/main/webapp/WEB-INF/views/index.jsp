<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .board {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 1rem;
            padding: 1rem;
            max-width: 1200px;
            margin: 0 auto;
            flex-grow: 1;
        }
        .board-item {
            border: 1px solid #ccc;
            padding: 1rem;
            background-color: #f9f9f9;
            text-align: center;
        }

        .button-container {
            text-align: center;
            margin: 1rem;
        }
        .button {
            padding: 0.5rem 1rem;
            font-size: 1rem;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
        .board {
            gap: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>

    <div class="button-container">
        <button class="button" onclick="writePost()">글쓰기</button>
    </div>

    <div class="board">
            <c:forEach items= "${posts}" var="post">
                <div class="board-item">
                    <img src="${post.image}" alt="게시물 이미지" onclick="goToPost(${post.id})">
                    <p>${post.image}</p>
                    <p>${post.title}</p>
                </div>
            </c:forEach>
        </div>

    <script>
        function writePost() {
            alert("글쓰기 버튼 클릭됨!");
        }
    </script>

<jsp:include page="/WEB-INF/views/main/footer.jsp"/>
</body>
</html>
