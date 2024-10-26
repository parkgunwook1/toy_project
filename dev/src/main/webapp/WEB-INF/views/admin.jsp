<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Admin Password Fetch</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* 밝은 배경 색 */
            color: #333; /* 글자 색 */
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #2c3e50; /* 제목 색 */
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input[type="text"] {
            width: 200px;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 10px 15px;
            background-color: #3498db; /* 버튼 색 */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2980b9; /* 버튼 hover 색 */
        }

        #result {
            margin-top: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>관리자 비밀번호 조회</h1>
    <label for="adminId">Admin ID:</label>
    <input type="text" id="adminId" name="adminId">
    <button id="fetchPassword">비밀번호 조회</button>

    <h2 id="result"></h2>

   <script>
           $(document).ready(function() {
               $('#fetchPassword').click(function() {
                   var adminId = $('#adminId').val();
                   $.ajax({
                       url: '/admin/password/' + adminId, // 수정된 URL
                       method: 'GET',
                       success: function(response) {
                           console.log('응답 데이터:', response);
                           $('#result').text('비밀번호: ' + response);
                       },
                       error: function() {
                           $('#result').text('비밀번호 조회 중 오류가 발생했습니다.');
                       }
                   });
               });
           });
       </script>
</body>
</html>
