<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 80%;
            max-width: 1200px;
            margin: 20px auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2.5rem;
            color: #333;
            margin-bottom: 20px;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }

        p {
            font-size: 1.1rem;
            line-height: 1.6;
            color: #555;
        }

        .info {
            margin-bottom: 20px;
        }

        .info span {
            font-weight: bold;
        }

        .image-container {
            margin-top: 30px;
            text-align: center;
        }

        .image-container img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: white;
        }

        .button-container {
            text-align: center;
            margin-top: 30px;
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .back-button, .edit-button, .delete-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
        }

        .back-button:hover, .edit-button:hover, .delete-button:hover {
            background-color: #45a049;
        }

        .delete-button {
            background-color: #f44336;
        }

        .delete-button:hover {
            background-color: #e53935;
        }

        /* 모달 스타일 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: #fefefe;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            border-radius: 5px;
        }

        .modal-content input, .modal-content textarea {
            width: 95%;
            padding: 10px;
            margin-bottom: 10px;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .modal-content textarea {
            height: 250px;  /* 높이를 크게 설정 */
            resize: none;   /* 크기 조정 비활성화 */
        }

        .modal-content button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
        }

        .modal-content button:hover {
            background-color: #45a049;
        }

        /* 모달 닫기 버튼 */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover, .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>

<div class="container">
    <h1>${post.title}</h1>

    <div class="info">
        <p><span>작성자:</span> ${post.author}</p>
        <p><span>작성일:</span> ${post.createDate}</p>
        <c:if test="${post.modifiedDate != null}">
            <p><span>수정일:</span> ${post.modifiedDate}</p>
        </c:if>
        <p><span>조회수:</span> ${post.viewCount}</p>
    </div>

    <div class="content">
        <p>${post.content}</p>
    </div>

    <!-- 버튼들 (뒤로가기, 수정, 삭제) -->
    <div class="button-container">
        <button class="back-button" onclick="window.history.back()">뒤로가기</button>
        <button class="edit-button" onclick="openModal()">수정하기</button>
        <form action="/post/delete" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
            <input type="hidden" name="postId" value="${post.postId}">
            <button type="submit" class="delete-button">삭제하기</button>
        </form>
    </div>
</div>

<!-- 수정 모달 -->
<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>게시물 수정</h2>
        <form id="editForm">
            <input type="hidden" name="postId" value="${post.postId}">
            <input type="text" name="title" value="${post.title}" placeholder="제목" required>
            <textarea name="content" placeholder="내용" required>${post.content}</textarea>
            <button type="button" onclick="submitForm()">수정하기</button>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/main/footer.jsp"/>

<script>
    function openModal() {
        document.getElementById("editModal").style.display = "flex";
    }

    function closeModal() {
        document.getElementById("editModal").style.display = "none";
    }
    function submitForm() {
        const postId = document.querySelector("input[name='postId']").value;
        const title = document.querySelector("input[name='title']").value;
        const content = document.querySelector("textarea[name='content']").value;

        const postData = {
            postId: postId,
            title: title,
            content: content
        };

        fetch('/post/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'  // JSON 형식으로 데이터 전송
            },
            body: JSON.stringify(postData)  // PostDto 형태로 전송
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('수정 완료!');
                closeModal();  // 수정 완료 후 모달 닫기
            } else {
                alert('수정 실패');
            }
        })
        .catch(error => {
            alert('에러 발생');
        });
    }
</script>
</body>
</html>
