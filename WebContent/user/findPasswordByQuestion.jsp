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

            if(login[1]){ 
               var myPage_on = document.getElementById('myPage');  // 마이페이지 메뉴
               var log_out = document.getElementById('login2');    // 로그아웃 메뉴
               var sign_up = document.getElementById('register');  // 회원가입 메뉴
               
               log_out.style.display='none';   // 로그인 감추기
               sign_up.style.display='none';   // 회원가입 감추기

               document.getElementById('logout2').style.display='block';   // 로그아웃
               document.getElementById('map1').style.display='block';       // 지도
               document.getElementById('myPage').style.display='block';    // 마이 페이지
                
            }
			/*
            // 이메일 확인이 완료된 경우
            if(emailCheck){
                if(emailCheck== 'success'){
                    var user = JSON.parse(localStorage.getItem('user'));
                    document.getElementById('email').value = user.email;
                    document.getElementById('question').value = user.question;
                    sessionStorage.removeItem('emailCheck');
                }
            }
			*/
        }  
    </script>
</head>
	<body>
		<%@ include file="/include/nav.jsp" %>
		<!-- main -->
	    <main class="loginform-container row align-items-center my-auto">
	        <div class="form-signin align-middle text-center col-md-4">
	            <h1>Find Password</h1>
	          <div class="justify-content-center">
	            <!-- 비밀번호 찾기 폼 -->
	            <form name="login-form" class="login-form" action="user" method="post">
	            	<input type="hidden" id="action" name="action" value="findByQuest">
	                <!-- 이메일 -->
	                <div class="form-floating mb-3" id="findPassword">
	                    <input
	                        type="email"
	                        class="form-control"
	                        id="email"
	                        name="userEmail"
	                        value = "${userEmail }"
	                        placeholder="email"
	                    />
	                    <label for="email">email</label>
	                    <button 
	                        type="submit" 
	                        class="w-30 btn bg-secondary fw-bold fs-5 text-white my-1 mb-2 p-2"
	                        onclick="emailChecking()"
	                    >
	                    Email 확인
	                    </button> 
	                </div>
	                <!-- 질문 -->
	                <div class="form-floating mb-3">
	                    <input
	                        type="text"
	                        class="form-control"
	                        id="question"
	                        name="question"
	                        value = "${question }"
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
	                        name="answer"
	                        value = "${answer }"
	                        placeholder="answer"
	                    />
	                    <label for="answer">answer</label>
	                </div>
	                <!-- 비밀번호 재설정 버튼 -->
	                <div class="p-1">
	                    <button
	                    	type="submit"
	                        class="w-100 btn bg-secondary fw-bold fs-5 text-white"
	                        onclick="moveChangePassword()"
	                    >
	                   	 비밀번호 재설정
	                    </button>
	                </div>
	            </form>
	          </div>
	        </div>
	      </main>
	</body>
<%@ include file="/include/footer.jsp" %>
</html>