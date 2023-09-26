<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
	<script>
      window.onload=function(){
    	var login = document.cookie.split("=");
    	if(login[1]){//login키값이 존재한다면
           var myPage_on = document.getElementById('myPage');  // 마이페이지 메뉴
           var log_out = document.getElementById('login2');    // 로그아웃 메뉴
           var sign_up = document.getElementById('register');  // 회원가입 메뉴
           
           log_out.style.display='none';   // 로그인 감추기
           sign_up.style.display='none';   // 회원가입 감추기

           document.getElementById('logout2').style.display='block';   // 로그아웃
           document.getElementById('map1').style.display='block';       // 지도
           document.getElementById('myPage').style.display='block';    // 마이 페이지
        }
      }
    </script>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<!-- main -->
    <main class="loginform-container row align-items-center my-auto">
        <div class="form-signin align-middle text-center col-md-4">
          <!-- 환영 메세지 -->
          <h1>Welcome</h1>
          <!-- 별 -->
          <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
          </div>
          <!-- 회원가입 폼 -->
          <div class="justify-content-center">
            <form name="login-form" class="login-form" action="user" method="post">
              <input type="hidden" id="action" name="action" value="regist">
              <!-- 아이디 -->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="id"
                  name = "userId"
                  placeholder="ID"
                  data-sb-validations="required"
                />
                <label for="id">ID</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">ID를 입력하세요.</div>
              </div>
              <!-- 비밀번호 -->
              <div class="form-floating mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  name = "userPwd"
                  placeholder="password"
                  data-sb-validations="required"
                />
                <label for="password">password</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">암호를 입력하세요.</div>
              </div>
              <!-- 이름 -->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="name"
                  name = "userName"
                  placeholder="name"
                  data-sb-validations="required"
                />
                <label for="name">name</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">이름을 입력하세요.</div>
              </div>
              <!-- 이메일 -->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="email"
                  name = "userEmail"
                  placeholder="email"
                  data-sb-validations="required"
                />
                <label for="email">email</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">이메일을 입력하세요.</div>
              </div>
              <!-- 나이 -->
              <div class="form-floating mb-3">
                <input
                  type="number"
                  class="form-control"
                  id="age"
                  name = "userAge"
                  placeholder="age"
                />
                <label for="age">age</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">나이를 입력하세요.</div>
              </div>
              <!-- 질문 -->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="question"
                  name = "question"
                  placeholder="question"
                />
                <label for="question">question</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">질문을 입력하세요.</div>
              </div>
              <!-- 답변 -->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="answer"
                  name = "answer"
                  placeholder="answer"
                />
                <label for="answer">answer</label>
                <div class="invalid-feedback" data-sb-feedback="name:required">답변을 입력하세요.</div>
              </div>
              <!-- 버튼 -->
              <div class="p-1">
                <button
                  class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                  onclick="regist()"
                  type="button"
                >
                  	회원가입
                </button>
              </div>
            </form>
          </div>
        </div>
    </main>
</body>
<%@ include file="/include/footer.jsp" %>
</html>