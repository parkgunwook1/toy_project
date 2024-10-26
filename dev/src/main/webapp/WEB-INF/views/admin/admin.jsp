<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./resources/style/css/admin/admin.css">
    <title>Admin Password Fetch</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
