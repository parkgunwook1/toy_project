<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        h1 {
            text-align: center;
            color: #333;
        }

        /* 헤더 스타일 */
        header {
            background-color: #3498db;
            padding: 1.5rem;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-image: url('main-image.jpg');
            background-size: cover;
            background-position: center;
        }
        header h1 {
            margin: 0;
            font-size: 1.5rem;
        }
        nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
            font-size: 1rem;
        }

    </style>
</head>
<body>

    <header>
        <h1>메인 페이지</h1>
        <nav>
            <a href="#" onclick="goHome()">홈</a>
            <a href="#" onclick="login()">로그인</a>
        </nav>
    </header>

</body>
</html>
