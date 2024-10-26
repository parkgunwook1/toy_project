<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./resources/style/css/admin/adminReal.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>관리자 페이지</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>

    <style>
           body {
                    font-family: 'Arial', sans-serif;
                    line-height: 1.6;
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f4;
                }
                .container {
                    display: flex;
                    min-height: 100vh;
                }
                .sidebar {
                    width: 200px;
                    background-color: #333;
                    color: white;
                    padding: 20px;
                }
                .content {
                    flex: 1;
                    padding: 20px;
                }
                h1 {
                    color: #333;
                    text-align: center;
                }
                .section {
                    margin-bottom: 30px;
                    background: white;
                    padding: 20px;
                    border-radius: 5px;
                    box-shadow: 0 0 10px rgba(0,0,0,0.1);
                }
                .section h2 {
                    color: #444;
                    border-bottom: 2px solid #ddd;
                    padding-bottom: 10px;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                }
                th, td {
                    padding: 10px;
                    border: 1px solid #ddd;
                    text-align: left;
                }
                th {
                    background-color: #f2f2f2;
                }
                button {
                    background-color: #4CAF50;
                    color: white;
                    padding: 10px 15px;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                    width: 100%;
                    margin-bottom: 10px;
                }
                button:hover {
                    background-color: #45a049;
                }
                .sidebar button {
                    background-color: #555;
                }
                .sidebar button:hover {
                    background-color: #666;
                }
    </style>
    <div class="container">
        <div class="sidebar">
            <h2>관리자 메뉴</h2>
            <button id="user">회원 관리</button>
            <button id="post">게시글 관리</button>
            <button id="lankPost">인기 게시글</button>
        </div>
        <div class="content">
            <h1>관리자 페이지</h1>

            <div id="members" class="section">
                <h2>회원 관리</h2>
                <table>
                    <thead>
                        <tr>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>가입일</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 여기에 회원 목록이 동적으로 추가됩니다 -->
                        <tr>
                            <td>user1</td>
                            <td>홍길동</td>
                            <td>user1@example.com</td>
                            <td>2023-05-01</td>
                            <td><button onclick="deleteUser('user1')">회원 탈퇴</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div id="posts" class="section" style="display: none;">
                <h2>게시글 관리</h2>
                <table>
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 여기에 게시글 목록이 동적으로 추가됩니다 -->
                        <tr>
                            <td>첫 번째 게시글</td>
                            <td>user1</td>
                            <td>2023-05-10</td>
                            <td>100</td>
                            <td><button onclick="deletePost(1)">게시글 삭제</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div id="popular" class="section" style="display: none;">
                <h2>인기 게시글</h2>
                <table>
                    <thead>
                        <tr>
                            <th>순위</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 여기에 인기 게시글 목록이 동적으로 추가됩니다 -->
                        <tr>
                            <td>1</td>
                            <td>가장 인기있는 게시글</td>
                            <td>user2</td>
                            <td>500</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

   <script>
       $(document).ready(function() {
           // 섹션 초기화: 모든 섹션을 숨깁니다.
           $('#members, #posts, #popular').hide();

           // 회원 관리 버튼 클릭 시
           $('#user').click(function() {
               $.ajax({
                   url: '/admin/userSelect/', // 회원 관리 데이터를 가져오는 URL
                   method: 'GET',
                   success: function(response) {
                       $('#members tbody').html(response); // 가져온 데이터를 회원 관리 섹션에 추가
                       $('#members').show();               // 회원 관리 섹션만 표시
                       $('#posts, #popular').hide();       // 다른 섹션은 숨기기
                   },
                   error: function() {
                       alert('회원 정보를 불러오는 데 실패했습니다.');
                   }
               });
           });

           // 게시글 관리 버튼 클릭 시
           $('#post').click(function() {
               $.ajax({
                   url: '/admin/postSelect/', // 게시글 관리 데이터를 가져오는 URL
                   method: 'GET',
                   success: function(response) {
                       $('#posts tbody').html(response); // 가져온 데이터를 게시글 관리 섹션에 추가
                       $('#posts').show();              // 게시글 관리 섹션만 표시
                       $('#members, #popular').hide();  // 다른 섹션은 숨기기
                   },
                   error: function() {
                       alert('게시글 정보를 불러오는 데 실패했습니다.');
                   }
               });
           });

           // 인기 게시글 버튼 클릭 시
           $('#lankPost').click(function() {
               $.ajax({
                   url: '/admin/popularPost/', // 인기 게시글 데이터를 가져오는 URL
                   method: 'GET',
                   success: function(response) {
                       $('#popular tbody').html(response); // 가져온 데이터를 인기 게시글 섹션에 추가
                       $('#popular').show();              // 인기 게시글 섹션만 표시
                       $('#members, #posts').hide();      // 다른 섹션은 숨기기
                   },
                   error: function() {
                       alert('인기 게시글 정보를 불러오는 데 실패했습니다.');
                   }
               });
           });
       });
   </script>


    <jsp:include page="/WEB-INF/views/main/footer.jsp"/>
</body>


</html>

