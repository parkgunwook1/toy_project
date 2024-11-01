document.addEventListener("DOMContentLoaded", function() {
    console.log("join.js 파일 로드됨");

    // 이벤트 리스너 추가
    document.getElementById("checkId").addEventListener("click", checkId);
    document.getElementById("submitForm").addEventListener("click", sendit);
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

function checkId() {
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
