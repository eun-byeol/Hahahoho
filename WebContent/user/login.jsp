<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
	<script>
	        window.onload=function() {
	            var login = sessionStorage.getItem('login');
	            
	            if(login){ // login키값이 존재한다면
	                if(login== 'success') {
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
	        }  
	</script>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<!-- main -->
    <main class="login-container row align-items-center">
        <div class="form-signin align-middle text-center col-md-5">
          <h1>Welcome</h1>
          <div class="justify-content-center">
            <!-- 로그인 폼 -->
            <form name="login-form" class="login-form">
                <!-- 아이디 -->
                <div class="form-floating mb-3">
                    <input
                        type="text"
                        class="form-control"
                        id="id"
                        placeholder="ID"
                    />
                    <label for="id">ID</label>
                </div>
                <!-- 암호 -->
                <div class="form-floating mb-3">
                    <input
                        type="password"
                        class="form-control"
                        id="password"
                        placeholder="password"
                    />
                    <label for="password">password</label>
                </div>
                <!-- 로그인 버튼 -->
                <div class="p-1">
                    <button
                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                        onclick="login()"
                        type="button"
                    >로그인</button>
                </div>
                <!-- 회원가입 버튼 -->
                <div class="p-1">
                    <a
                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                        href="register.html"
                        role="button"
                        >회원가입</a
                    >
                </div>
                <!-- 비밀번호 찾기 버튼 -->
                <div class="p-1">
                    <a
                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                        href="findPasswordById.html"
                        role="button"
                        >비밀번호 찾기</a
                    >
                </div>
            </form>
          </div>
        </div>
    </main>
</body>
<%@ include file="/include/footer.jsp" %>
</html>