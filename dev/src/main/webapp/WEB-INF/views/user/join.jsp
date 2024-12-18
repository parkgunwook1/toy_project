<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<div class="container">
    <div class="join_input">
        <form action="/user/join" method="post" name="joinForm">
            <div class="join_input_wrap">
                <table class="join_tbl">
                    <tbody>
                        <tr class="id_checkBox">
                            <td colspan="3"><span id="id_check"></span></td>
                        </tr>
                        <tr>
                            <th><label for="userId">아이디</label></th>
                            <td><input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요."></td>
                            <td><input type="button" value="중복검사" id="checkId" /></td>
                        </tr>
                        <tr>
                            <th><label for="userPassword">비밀번호</label></th>
                            <td colspan="2">
                                <input type="password" name="userPassword" id="userPassword" placeholder="비밀번호를 입력하세요.">
                                <p>영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</p>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="userPw_re">비밀번호 확인</label></th>
                            <td colspan="2"><input type="password" name="userPw_re" id="userPw_re"></td>
                        </tr>
                        <tr>
                            <th><label for="userName">이름</label></th>
                            <td colspan="2"><input type="text" name="userName" id="userName"></td>
                        </tr>
                        <tr>
                            <th><label for="userAge">나이</label></th>
                            <td colspan="2"><input type="text" name="userAge" id="userAge"></td>
                        </tr>
                        <tr>
                            <th>성별</th>
                            <td><input type="radio" id="man" name="userGender" value="M"><label for="man">남자</label> </td>
                            <td><input type="radio" id="woman" name="userGender" value="W"><label for="woman">여자</label> </td>
                        </tr>
                        <tr>
                            <th><label for="userEmail">이메일</label></th>
                            <td colspan="2"><input type="text" name="userEmail" id="userEmail"></td>
                        </tr>
                        <tr>
                            <th rowspan="4"><label for="userZipcode">주소</label></th>
                            <td><input type="text" name="userZipcode" id="userZipcode" placeholder="우편번호" readonly></td>
                            <td><input type="button" value="주소찾기" id="findAddr" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="text" name="userAddress" id="userAddress" placeholder="주소"  readonly></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="text" name="userAddretc" id="userAddretc" placeholder="참고항목"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="text" name="userAddressDetail" id="userAddressDetail" placeholder="상세주소"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn_div">
                <input type="button" value="이전으로" />
                <input type="button" id="sendit" value="회원가입" />
            </div>
            <span>이미 아이디가 있으신가요? <a href="/user/login">로그인</a></span>
        </form>
    </div>
</div>

<script src="/resources/js/user/join.js"></script>
</body>
</html>

