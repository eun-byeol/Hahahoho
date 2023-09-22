<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Hahahoho</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="assets/css/styles.css" rel="stylesheet" />
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
                        <img src="assets/img/Hahahoho-logo.png" alt="Avatar Logo" style="width: 250px;" class="rounded-pill"> 
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
        </header>
        <!-- Portfolio Section-->
        <section class="page-section" id="About Us">
            <div class="container-fluid text-center">
                <h2 class="page-section-heading text-center text-uppercase text-black mb-0">Hahahoho Travel</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <h3 class="page-section-heading text-center text-uppercase text-black mb-0">여정의 즐거움을 함께 나누다!</h3>
                <br />
                <!-- Portfolio Section Heading-->
                <img
                    src="assets/img/trip_img.JPG"
                    alt="배경이미지"
                    class="masthead-bg align-content-center"
                    width="1600"
                    height="600"
                    style="max-width:100%; height:auto;"
                />
            </div>
        </section>
        <!-- About Section-->
        <section class="page-section bg-primary-subtle text-white mb-0" id="about">
            <div class="container">
                <!-- About Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-black">About</h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-black">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- About Section Content-->
                <div class="column text-center text-black ">
                    <div class="col-lg ms-auto"><p class="lead fw-bold">&nbsp하하호호 여행사는 여행을 좋아하는 모든 이들을 위한 특별한 장소입니다. 우리는 여행의 마법을 믿으며, 이를 통해 새로운 경험과 모험을 찾고자 하는 분들을 위해 최상의 여행 서비스를 제공하고 있습니다. 저희 여행사는 전 세계에서 최고의 여행 명소와 환상적인 여정을 제공하며, 고객 각각의 요구와 선호에 따라 맞춤 여행을 계획합니다.</p></div>
                    <div class="col-lg me-auto"><p class="lead fw-bold">&nbsp저희와 함께라면, 여행은 단순한 이동이 아니라 삶의 풍요로움을 느끼는 기회가 될 것입니다. 우리는 전문가로서의 경험과 열정을 바탕으로 최상의 여행 경험을 제공하며, 여행을 통해 새로운 문화를 탐험하고, 멋진 풍경을 감상하며, 훌륭한 음식을 맛보며, 새로운 인연을 만나게 해드립니다.</p></div>
                    <br/>
                    <div class="col-lg me-auto"><p class="lead fw-bold">&nbsp우리와 함께하세요. 하하호호 여행사와 함께라면, 즐거움과 경험의 여정이 펼쳐집니다.</p></div>
                </div>
                <!-- About Section Button-->
                <div class="text-center mt-4 ">
                    <a class="btn btn-lg btn-outline-dark p-3" href="attraction.html">
                        <i class="bi bi-rocket-takeoff me-2"></i>
                        이용하기
                    </a>
                </div>
            </div>
        </section>

        <!-- Footer-->
        <footer class="footer text-center">
            <div class="container-fluid">
                <div class="row">
                    <!-- 로고-->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <img src="assets/img/ssafy_logo.png" alt="Ssafy Logo" style="width: 150px;" class="rounded"> 
                    </div>
                    <!-- 연관 사이트-->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h4 class="text-uppercase mb-4">Partners</h4>
                        <a class="website-brand" href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp" target="_blank">
                            <img src="assets/img/ssafy_logo2.png" alt="tour Logo" style="width: 109px;" class="rounded"> 
                        </a>
                        <a class="website-brand" href="https://knto.or.kr/index" target="_blank">
                            <img src="assets/img/tour_logo.png" alt="tour Logo" style="width: 130px;" class="rounded"> 
                        </a>
                        <a class="website-brand" href="https://www.mcst.go.kr/kor/main.jsp" target="_blank">
                            <img src="assets/img/culture_logo.png" alt="culture Logo" style="width: 150px;" class="rounded"> 
                        </a>
                    </div>
                    <!-- 주소 정보 -->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h4 class="text-uppercase mb-4">Location</h4>
                        <p class="lead mb-0">
                            Teheran-ro, Gangnam-gu, Seoul, Republic of Korea
                            <br />
                            대표번호: 1588-3357
                            <br />
                            사업자번호: 123-4567-987
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Copyright Section-->
        <div class="copyright py-4 text-center text-white">
            <div class="container"><small>Copyright &copy; Hahahoho 2023</small></div>
        </div>
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="assets/js/main.js"></script>
        <script src="assets/js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
