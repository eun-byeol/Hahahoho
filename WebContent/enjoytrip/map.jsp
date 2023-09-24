<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
	console.log(${pageContext.request.contextPath});
</script>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="../assets/css/overlay_style.css">
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<body id="page-top">
        <!-- Section-->
        <section class="page-section portfolio" id="portfolio">
            <div class="container-fluid">
                <!-- Section Heading -->
                <div style="height: 70px"></div>
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">우리동네 구석구석</h2>

                <!-- 메인 지도 -->
                <div class="container">
                    <div style="height: 70px"></div>
                    <div class="row">
                        <form class="d-flex my-3" onsubmit="return false;" role="search">
                            <select id="search-province-area" class="form-select me-2">
                              <option value="0" selected>시도 선택</option>
                            </select>
                            <select id="search-city-area" class="form-select me-2">
                                <option value="0" selected>군구 선택</option>
                              </select>
                            <select id="search-content-id" class="form-select me-2">
                              <option value="0" selected>관광지 유형</option>
                              <option value="12">관광지</option>
                              <option value="14">문화시설</option>
                              <option value="15">축제공연행사</option>
                              <option value="25">여행코스</option>
                              <option value="28">레포츠</option>
                              <option value="32">숙박</option>
                              <option value="38">쇼핑</option>
                              <option value="39">음식점</option>
                            </select>
                            <button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
                        </form>
                    </div>
                    <div class="row">
                        <!-- kakao map start -->
                        <div id="map" class="mt-3" style="width: 100%; height: 600px"></div>
                        <script type="text/javascript" src='//dapi.kakao.com/v2/maps/sdk.js?appkey=7606e79faf3ca3bb2c216248d76f18ca'></script>
                    </div>
                </div>
            </div>
        </section>
</body>
<%@ include file="/include/footer.jsp" %>
<script src="../assets/js/attraction_search.js"></script>
</html>