<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jsp" %>
<script>
        window.onload=function(){
        	var login = document.cookie.split("=");
            //console.log(login[1]);

            if(login[1]){//login키값이 존재한다면
                
                var myPage_on = document.getElementById('myPage');  // 마이페이지 메뉴
                var log_out = document.getElementById('login2');    // 로그아웃 메뉴
                var sign_up = document.getElementById('register');  // 회원가입 메뉴
                
                log_out.style.display='none';   // 로그인 감추기
                sign_up.style.display='none';   // 회원가입 감추기

                document.getElementById('logout2').style.display='block';   // 로그아웃
                document.getElementById('map1').style.display='block';       // 지도
                document.getElementById('myPage').style.display='block';    // 마이 페이지
                

                //var user = JSON.parse(localStorage.getItem('user'));

                //document.getElementById('password').value = user.password;
                //document.getElementById('password2').value = user.password;
                //document.getElementById('name').value = user.name;
                //document.getElementById('email').value = user.email;
                //document.getElementById('age').value = user.age;
                //document.getElementById("question").value = user.question;
                //document.getElementById("answer").value = user.answer;
            }
        }  
</script>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<!-- main -->
    <main class="loginform-container row align-items-center my-auto">
        <div class="form-signin align-middle text-center col-md-4">
          <h1>My Page</h1>
          <div class="justify-content-center">
            <!-- 마이페이지 폼 -->
            <form name="login-form" class="login-form" action="user" method="post">
            	<input type="hidden" id="action" name="action" value="modify">
                <!-- 비밀번호 -->
                <div class="form-floating mb-3">
                    <input
                        type="password"
                        class="form-control"
                        id="password"
                        name = "userPwd"
                        value = "${userInfo.userPwd}"
                        placeholder="password"
                    />
                    <label for="password">password</label>
                </div>
                <!-- 비밀번호 확인 -->
                <div class="form-floating mb-3">
                    <input
                        type="text"
                        class="form-control"
                        id="password2"
                        name = "userPwd2"
                        value = "${userInfo.userPwd}"
                        placeholder="password check"
                    />
                </div>
                <!-- 이름 -->
                <div class="form-floating mb-3">
                    <input
                        type="text"
                        class="form-control"
                        id="name"
                        name = "userName"
                        value = "${userInfo.userName}"
                        placeholder="name"
                    />
                    <label for="name">name</label>
                </div>
                <!-- 이메일 -->
                <div class="form-floating mb-3">
                    <input
                        type="email"
                        class="form-control"
                        id="email"
                        name = "userEmail"
                        value = "${userInfo.userEmail}"
                        placeholder="email"
                    />
                    <label for="email">email</label>
                </div>
                <!-- 나이 -->
                <div class="form-floating mb-3">
                    <input
                        type="number"
                        class="form-control"
                        id="age"
                        name = "userAge"
                        value = "${userInfo.userAge}"
                        placeholder="age"
                    />
                    <label for="age">age</label>
                </div>
                <!-- 질문 -->
                <div class="form-floating mb-3">
                    <input
                        type="text"
                        class="form-control"
                        id="question"
                        name = "question"
                        value = "${userInfo.question}"
                        placeholder="question"
                    />
                    <label for="question">question</label>
                </div>
                <!-- 답변 -->
                <div class="form-floating mb-3">
                    <input
                        type="text"
                        class="form-control"
                        id="answer"
                        name = "answer"
                        value = "${userInfo.answer}"
                        placeholder="answer"
                    />
                    <label for="answer">answer</label>
                </div>
                <!-- 회원정보 수정 -->
                <div class="p-1">
                    <button
                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                        onclick="memberEdit()"
                        type="button"
                    >
                    	회원정보 수정
                    </button>
                </div>
                <!-- 회원 탈퇴 -->
                <div class="p-1">
                    <button
                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                        onclick="memberDelete()"
                        type="button"
                    >
                        	회원탈퇴
                    </button>
                </div>
            </form>
          </div>
        </div>
      </main>
</body>
<%@ include file="/include/footer.jsp" %>
</html>