document.addEventListener("DOMContentLoaded", function() {
    console.log("join.js 파일 로드됨");

    // 이벤트 리스너 추가
    document.getElementById("checkId").addEventListener("click", checkId);
    document.getElementById("findAddr").addEventListener("click", findAddr);
    document.getElementById("sendit").addEventListener("click", sendit);
});

let resultId = document.querySelector("#id_check");

function sendit() {
    const joinForm = document.joinForm;
    console.log("sendit 함수 호출됨");

    // 아이디
    const userId = joinForm.userId;
    if (userId.value == "") {
        alert("아이디를 입력하세요!");
        userId.focus();
        return;
    }

    // 아이디 중복체크
    if (resultId.innerHTML == "") {
        alert("아이디 중복검사를 진행해주세요!");
        const checkId = joinForm.checkId;
        checkId.focus();
        return;
    }
    if (resultId.innerHTML == "중복된 아이디가 있습니다!") {
        alert("중복체크 통과 후 가입이 가능합니다!");
        userId.focus();
        return;
    }

    // 비밀번호
    const userPassword = joinForm.userPassword;
    if (userPassword.value == "") {
        alert("비밀번호를 입력하세요!");
        userPassword.focus();
        return;
    }

    // 비밀번호 확인
    const userPw_re = joinForm.userPw_re;
    if (userPw_re.value == "") {
        alert("비밀번호 확인을 입력하세요!");
        userPw_re.focus();
        return;
    }

    // '비밀번호' 와 '비밀번호 확인' 동일한지 체크
    if (userPassword.value != userPw_re.value) {
        alert("'비밀번호'와 '비밀번호 확인'이 일치하지 않습니다.");
        userPassword.focus();
        return;
    }

    // 이름
    const userName = joinForm.userName;
    const exp_name = /^([가-힣]+|[a-zA-Z]+)$/; // 정규 표현식(한글만 or 영어만)
    if (userName.value == "") {
        alert("이름을 입력하세요!");
        userName.focus();
        return;
    }
    if (!exp_name.test(userName.value)) {
        alert("이름에는 한글 혹은 영어만 입력하세요!");
        userName.focus();
        return false;
    }

    // 이메일
    const userEmail = joinForm.userEmail;
    const exp_email = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (userEmail.value == "") {
        alert("메일을 입력하세요!");
        userEmail.focus();
        return;
    }
    if (!exp_email.test(userEmail.value)) {
        alert("이메일을 올바른 형식으로 입력하세요!");
        userEmail.value = "";
        userEmail.focus();
        return;
    }

    alert("회원가입을 축하드립니다.");
    joinForm.submit();
}

function checkId() { // 아이디 중복 체크 ajax
    console.log("checkId 함수 호출됨");
    const xhr = new XMLHttpRequest();
    const userId = document.joinForm.userId;
    if (userId.value == "") {
        alert("아이디를 입력하세요!");
        userId.focus();
        return;
    }
    if (userId.value.length < 5 || userId.value.length > 12) {
        alert("아이디는 5자 이상 12자 이하로 입력하세요!");
        userId.focus();
        return;
    }

    xhr.onreadystatechange = function () {
        console.log("현재 상태:", xhr.readyState, "응답 코드:", xhr.status);  // 상태와 응답 코드 출력
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                let txt = xhr.responseText.trim();
                console.log("서버 응답:", txt);  // 서버 응답 확인
                console.log("resultId 요소:", resultId);

                if (txt == "O") {
                    resultId.classList.add("visible");
                    resultId.style.color = "rgb(79,148,111)";
                    resultId.innerHTML = "사용할 수 있는 아이디입니다!";
                } else {
                    resultId.classList.add("visible");
                    resultId.style.color = "red";
                    resultId.innerHTML = "중복된 아이디가 있습니다!";
                }
            } else {
                console.error("오류 응답 코드:", xhr.status);
            }
        }
    };


    xhr.open("GET", "/user/checkId?userId=" + userId.value);
    xhr.send();
}

function findAddr() { // '주소찾기' 버튼 클릭시
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("userAddretc").value = extraAddr;

            } else {
                document.getElementById("userAddretc").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('userZipcode').value = data.zonecode;
            document.getElementById("userAddress").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("userAddressDetail").focus();
        }
    }).open();
}
