<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
	<script>
        window.onload=function(){
            var login = sessionStorage.getItem('login');
            var idCheck = sessionStorage.getItem('idCheck');

            // login키값이 존재한다면
            if(login){  
                if(login== 'success'){
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

            // ID 확인이 완료된 경우
            if(idCheck){
                if(idCheck== 'success'){
                    var user = JSON.parse(localStorage.getItem('user'));
                    document.getElementById('ID').value = user.id;
                    document.getElementById('name').value = user.name;
                    sessionStorage.removeItem('idCheck');
                    sessionStorage.setItem('next', 'success');
                }
            }
        }  
    </script>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<!-- main -->
    <main class="loginform-container row align-items-center">
        <div class="form-signin align-middle text-center col-md-4">
          <h1>Find Password</h1>
          <div class="justify-content-center">
            <!-- 비밀번호 찾기 폼 -->
            <form name="login-form" class="login-form">
                <!-- 아이디 -->
                <div class="form-floating  mb-2" id="findPassword">
                    <input
                        type="text"
                        class="form-control"
                        id="ID"
                        placeholder="ID"
                    />
                    <label for="password">ID</label>
                    <button 
                        type="submit" 
                        class="w-30 btn bg-secondary fw-bold fs-5 text-white my-1 mb-2 p-2"
                        onclick="idChecking()"
                    >
                    ID 확인
                    </button> 
                </div>
                <!-- 암호 -->
                <div class="form-floating mb-3" id="findPassword">
                    <input
                        type="text"
                        class="form-control"
                        id="name"
                        placeholder="name"
                    />
                    <label for="name">name</label>
                </div>
                <!-- 다음으로 이동버튼-->
                <div class="p-1">
                    <button
                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
                        onclick="moveNext()"
                        type="button"
                    >
                    다음
                    </button>
                </div>
            </form>
          </div>
        </div>
      </main>
</body>
<%@ include file="/include/footer.jsp" %>
</html>