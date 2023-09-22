<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Hahahoho</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../assets/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <!-- 로그인 여부에 따른 네비게이션 바 -->
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
                    document.getElementById('map').style.display='block';       // 지도
                    document.getElementById('myPage').style.display='block';    // 마이 페이지
                }
            }
        }
        </script>
    </head>
    <body id="page-top">
        <header>
            <!-- 네비게이션 바-->
            <nav class="navbar navbar-expand-lg bg-primary-subtle text-uppercase" id="mainNav">
                <div class="container">
                    <a class="navbar-brand" href="index.html">
                        <img src="../assets/img/Hahahoho-logo.png" alt="Avatar Logo" style="width: 250px;" class="rounded-pill"> 
                    </a>
                    <button class="navbar-toggler text-uppercase font-weight-bold bg-primary-subtle text-black rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                        Menu
                        <i class="fas fa-bars"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="login2" href="login.html">Login</a></li>
                            <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="logout2" style="display: none;" onclick="logout()" href="index.html">Logout</a></li>
                            <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" href="#about">About</a></li>
                            <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="register" href="register.html">Sign Up</a></li>
                            <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="map" style="display: none;" href="attraction.html">Map</a></li>
                            <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="myPage" style="display: none;" href="memberControl.html">Mypage</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
</head>
</html>