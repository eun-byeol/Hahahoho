// 회원 가입 기능
function regist() {
  // 문서에서 id 로 input data 가져오기
  let act = document.getElementById("action").value
  let id = document.getElementById("id").value;
  let password = document.getElementById("password").value;
  let name = document.getElementById("name").value;
  let email = document.getElementById("email").value;
  let age = document.getElementById("age").value;
  let question = document.getElementById("question").value;
  let answer = document.getElementById("answer").value;
  
  // 입력값 검증
  if (!id || !password || !name || !email || !age || !question || !answer) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    // input data로 user 만들기
    const user = {
      action: act,
      userId: id,
      userPwd: password,
      userName: name,
      userEmail: email,
      userAge: age,
      question: question,
      answer: answer
    };
    console.log(user);
//    // user 객체 문자열로 바꿔서 로컬스토리지에 저장
//    localStorage.setItem("user", JSON.stringify(user));
    alert("사용자 등록 성공!");
    
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "user");
    document.charset = "UTF-8";

    for (var key in user) {
      var hiddenField = document.createElement('input');
      hiddenField.setAttribute('type', 'hidden');
      hiddenField.setAttribute('name', key);
      hiddenField.setAttribute('value', user[key]);
      form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form.submit();
  }
}

// 로그인 기능
function login() {
  // 문서에서 id로 input data 가져오기
  let act = document.getElementById("action").value;
  let id = document.getElementById("id").value;
  let password = document.getElementById("password").value;

  if (!id || !password) {
	    alert("빈칸이 없도록 입력해주세요.");
	    return;
  } else {
    // input data로 user 만들기
    const user = {
      action: act,
      userId: id,
      userPwd: password,
    };
    console.log(user);
    
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "user");
    document.charset = "UTF-8";

    for (var key in user) {
      var hiddenField = document.createElement('input');
      hiddenField.setAttribute('type', 'hidden');
      hiddenField.setAttribute('name', key);
      hiddenField.setAttribute('value', user[key]);
      form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form.submit();
  }
}

// 로그아웃 기능
function logout() {
    //CSS선택자를 사용하여 Element정보 얻어오기 
    document.querySelector('#login2').setAttribute("style","display:block");
    document.querySelector('#logout2').setAttribute("style", "display:none");
    alert("로그아웃 성공 !");
    location.replace("/user?action=logout");
}//logout
  
// 회원정보 수정 기능
function memberEdit() {
  // 문서에서 id 로 input data 가져오기
  var act = document.getElementById("action").value;
  var login = document.cookie.split("=");
  var no = login[1];
  var password = document.getElementById("password").value;
  var password2 = document.getElementById("password2").value;
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var age = document.getElementById("age").value;
  var question = document.getElementById("question").value;
  var answer = document.getElementById("answer").value;
  
  // 입력값 검증
  if (!password || !name || !email || !age || !question || !answer) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else if(password != password2){
	alert("비밀번호를 일치 시켜주세요");
	return;
  } 
  else {
    // input data로 user 만들기
	alert("회원 수정 성공");
    const user = {
	  action: act,
      userNo: no,
      userPwd: password,
      userName: name,
      userEmail: email,
      userAge: age,
      question: question,
      answer: answer
    };

    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "user");
    document.charset = "UTF-8";

    for (var key in user) {
      var hiddenField = document.createElement('input');
      hiddenField.setAttribute('type', 'hidden');
      hiddenField.setAttribute('name', key);
      hiddenField.setAttribute('value', user[key]);
      form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form.submit();
    
  }
}
  
// 회원 삭제 기능
function memberDelete() {
  var returnValue = confirm("회원 탈퇴하시겠습니까??");
  if (returnValue) {
	  alert("회원탈퇴 성공");
	  location.replace("/user?action=delete");
  }
}


// 이메일 체크 기능
function emailChecking() {
  // 문서에서 id 로 input data 가져오기
  let email = document.getElementById("email").value;

  // 이메일이 없는 경우 채우기
  if (!email) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
	  location.href = "/user?action=findByQuest";
  }
}

// 비밀번호 바꾸기로 이동하는 기능
function moveChangePassword() {
  // 문서에서 id 로 input data 가져오기
  let question = document.getElementById("question").value;
  let answer = document.getElementById("answer").value;

  // 질문과 답변이 없는 경우 채우기
  if (!question || !answer) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  }
}
