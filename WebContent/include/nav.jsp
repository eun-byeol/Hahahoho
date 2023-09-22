<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="register" href="register.html">Sign Up</a></li>
                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="map1" style="display: none;" href="attraction.html">Map</a></li>
                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded text-secondary" id="myPage" style="display: none;" href="memberControl.html">Mypage</a></li>
            </ul>
        </div>
    </div>
</nav>
</html>