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

//  // 로컬스토리지에 "user" 키로 저장된 item 가져와서 json 객체로 만들기
//  const user = JSON.parse(localStorage.getItem("user"));

//  // 등록된 회원이 없는 경우
//  if (user == undefined) {
//    alert("등록된 회원이 없습니다.");
//    return;
//  }

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
//	    // user 객체 문자열로 바꿔서 로컬스토리지에 저장
//	    localStorage.setItem("user", JSON.stringify(user));
    
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
//  // 입력값 검증
//  if (user.id && user.password &&  //user에 id와 password가 존재하고
//      user.id ===id &&  user.password === password //아이디와 패스워드가 일치한다면
//  ) {
//      alert("로그인 성공 !");
//      // 로그인 성공하면 index 페이지로 이동.
//      sessionStorage.setItem('login', 'success'); //세션에 로그인 여부 저장
//      //localStorage.setItem('login','success');//데이터 저장 
//      location.replace("attraction.html");
//  } else {
//      alert("로그인 실패 !");
//  }
}

// 로그아웃 기능
function logout() {
    //CSS선택자를 사용하여 Element정보 얻어오기 
//    sessionStorage.removeItem('login');
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
    
//    // user 객체 문자열로 바꿔서 로컬스토리지에 저장
//    localStorage.setItem("user", JSON.stringify(user));
//    alert("사용자 정보 변경 성공!");
//    // 로그아웃 후 로그인 화면으로 돌아가기
//    sessionStorage.removeItem('login');
//    location.replace("login.html");
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

// 아이디 확인 기능
function idChecking() {
  // 문서에서 id 로 input data 가져오기
  let id = document.getElementById("ID").value;
  
  // 아이디가 없는 경우 채우기
  if (!id) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    // 로컬스토리지에 "user" 키로 저장된 item 가져와서 json 객체로 만들기
    const user = JSON.parse(localStorage.getItem("user"));

    // 등록된 회원이 없는경우
    if (user == undefined) {
      alert("등록된 회원이 없습니다.");
      return;
    }
  
    // 입력값 검증
    if (user.id && user.id ===id) {
        // 아이디 확인이 완료되면 상태 저장
        sessionStorage.setItem('idCheck', 'success'); //세션에 아이디 확인 여부 저장
    } else {
        alert("해당하는 아이디가 없습니다.");
    }
    // 화면 재설정
    location.href = "findPasswordById.html";
  }
}

// 이메일 검증 단계로 이동하는 기능
function moveNext() {
  // 아이디 검증이 완료 됐는지 확인
  let flag = sessionStorage.getItem('next');

  // 아이디 검증이 완료될때 시행
  if (flag && flag === 'success') {
    alert("다음으로 이동합니다.");
    sessionStorage.removeItem('idCheck');
    sessionStorage.removeItem('next');
    location.replace("findPasswordByQuestion.html");
  }
  else {
    alert("아이디를 확인하세요");
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
    // 로컬스토리지에 "user" 키로 저장된 item 가져와서 json 객체로 만들기
    const user = JSON.parse(localStorage.getItem("user"));

    // 등록된 회원이 없는경우
    if (user == undefined) {
      alert("등록된 회원이 없습니다.");
      return;
    }
  
    // 입력값 검증
    if (user.email && user.email ===email) {
        // 이메일 확인이 완료되면 상태 저장
        sessionStorage.setItem('emailCheck', 'success'); //세션에 이메일 확인 여부 저장
    } else {
        alert("해당하는 이메일이 없습니다.");
    }
    location.replace("findPasswordByQuestion.html");
  }
}

// 비밀번호 바꾸기로 이동하는 기능
function moveChangePassword() {
  // 문서에서 id 로 input data 가져오기
  let question = document.getElementById("question").value;
  let answer = document.getElementById("answer").value;

  // 질문과 답변이 없는 경우 채우기
  if (!question && !answer) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    // 로컬스토리지에 "user" 키로 저장된 item 가져와서 json 객체로 만들기
    const user = JSON.parse(localStorage.getItem("user"));

    // 등록된 회원이 없는경우
    if (user == undefined) {
      alert("등록된 회원이 없습니다.");
      return;
    }
  
    // 입력값 검증
    if (user.question === question && user.answer === answer) {
      // 질문과 답이 맞은 경우 상태 저장
      sessionStorage.removeItem('next');  // 세션 지우기
      sessionStorage.setItem('answerCheck', 'success'); //세션에 답변 확인 여부 저장
      location.replace("changePassword.html");
    } else {
        alert("질문에 대한 답변이 틀렸습니다.");
    }
  }
}

// 비밀번호 수정 기능
function changePassword() {
  // 문서에서 id 로 input data 가져오기
  let password = document.getElementById("password").value;
  let password2 = document.getElementById("password2").value;
  
  // 입력값 검증
  if (!password || !password2) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    // input data로 user 만들기
    const user = {
      id: null,
      password: null,
      name: null,
      email: null,
      age: null,
      question: null,
      answer: null
    };

    //비밀번호와 비밀번호 확인이 같은 경우 갱신
    if (password === password2) {
      var data = JSON.parse(localStorage.getItem('user'));

      user.id = data.id;
      user.password = password;
      user.name = data.name;
      user.email = data.email;
      user.age = data.age;
      user.question = data.question;
      user.answer = data.answer;
      
      // user 객체 문자열로 바꿔서 로컬스토리지에 저장
      localStorage.setItem("user", JSON.stringify(user));
      alert("비밀번호 변경 성공!");
      // 로그아웃 후 로그인 화면으로 돌아가기
      sessionStorage.removeItem('login');
      sessionStorage.removeItem('answerCheck');
      location.replace("login.html");
    }
  }
}