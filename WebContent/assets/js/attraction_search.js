getSidoInfo();

const startX = 37.49794117894531;
const startY = 127.02840167832981;
const startLevel = 3;

var map = mapInit(startX, startY, startLevel);

/*
시도 정보 - index page 로딩 후 전국의 시도 설정
*/
function getSidoInfo() {
	const url = `/attraction?action=searchSido`;
	
	fetch(url)
	    .then((response) => response.json())
	    .then((data) => makeSidoOption(data));
}

function makeSidoOption(data) {
    let sidoInfos = data.infos;
    let sel = document.getElementById("search-province-area");
    
    sidoInfos.forEach((sido) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", sido.sidoCode);
        opt.appendChild(document.createTextNode(sido.sidoName));

        sel.appendChild(opt);
    });
}

/*
구군 정보 - 시도 option 선택 시, 구군 정보 설정
*/
var selectedSido = document.getElementById("search-province-area");
selectedSido.addEventListener("click", () => {
    if (selectedSido.value != 0) {
		
		const url = `/attraction?action=searchGugun&sidoCode=${selectedSido.value}`;
      
        fetch(url)
            .then((response) => response.json())
            .then((data) => makeGugunOption(data));
    }
    else {
    	let sel = document.getElementById("search-city-area");
    	sel.innerHTML = '<option value="0" selected>군구 선택</option>';
    }
})

function makeGugunOption(data) {
    let gugunInfos = data.infos;
    let sel = document.getElementById("search-city-area");
    let opt = '<option value="0" selected>군구 선택</option>';
    
    gugunInfos.forEach((gugun) => {
        opt += `<option value=${gugun.gugunCode}>${gugun.gugunName}</option>`;
    });
    sel.innerHTML = opt;
}


/*
관광지 조회
*/

document.getElementById("btn-search").addEventListener("click", () => {
    getAttractionInfo();
});


/*
시도, 구, 관광지 유형별 조회
*/
function getAttractionInfo() {
	const sidoCode = document.getElementById("search-province-area").value;
    const gugunCode = document.getElementById("search-city-area").value;
    const contentTypeId = document.getElementById("search-content-id").value;
    
	const url = `/attraction?action=search&sidoCode=${sidoCode}&gugunCode=${gugunCode}&contentTypeId=${contentTypeId}`;
    fetch(url)
      .then((response) => response.json())
      .then((data) => makeList(data)); // 검색 후 마커 찍기
}


/*
지도 초기화
*/
function mapInit(x, y, level) {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
		center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
		level: startLevel // 지도의 확대 레벨
	};
	
	map = new kakao.maps.Map(mapContainer, mapOption);

	/*
	지도 타입 컨트롤
	*/
	var mapTypeControl = new kakao.maps.MapTypeControl();
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	return map;
}

/*
여러 마커 표시
*/
var positions = []; //관광지 정보 배열
var markers = []; //마커 배열

function makeList(data) {
    let trips = data.infos;
//    console.log(trips);
    
    positions = [];
    trips.forEach((area) => {
        let markerInfo = {
            title: area.title,
            contentTypeId: area.contentTypeId,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
            image: area.firstImage,
            addr1: area.addr1,
            addr2: area.addr2
        };
        positions.push(markerInfo);
    });
    
    closeMarker();
    displayMarker();
    if (positions.length != 0) {
        map.setCenter(new kakao.maps.LatLng(positions[0].latlng.Ma, positions[0].latlng.La));
        map.setLevel(4);
    }
}


function closeMarker() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
    }
	markers = [];
}


/*
아이콘 이미지 출처 : <a href="https://www.flaticon.com/kr/free-icons/-" title="별표 표시 아이콘">별표 표시 아이콘  제작자: juicy_fish - Flaticon</a>
 */
const markerImageSrc = new Map();
markerImageSrc.set(12, '/assets/img/marker/picture.png'); // 관광지
markerImageSrc.set(14, '/assets/img/marker/offices.png'); // 문화시설
markerImageSrc.set(15, '/assets/img/marker/starred.png'); // 축제공연행사
markerImageSrc.set(25, '/assets/img/marker/flagged.png'); // 여행코스
markerImageSrc.set(28, '/assets/img/marker/camping-tent.png'); // 레포츠
markerImageSrc.set(32, '/assets/img/marker/home.png'); // 숙박
markerImageSrc.set(38, '/assets/img/marker/shopping.png'); // 쇼핑
markerImageSrc.set(39, '/assets/img/marker/restaurant.png'); // 음식점

function displayMarker() {
    for (var i = 0; i < positions.length; i ++) {
    	var imageSrc = markerImageSrc.get(positions[i].contentTypeId), // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(44, 48), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            image: markerImage
        });
        
        markers.push(marker);

        kakao.maps.event.addListener(marker, 'click', makeOverlay(map, marker, positions[i]));
    }
}

/*
오버레이 생성
 */
var overlayArr = [];

function makeOverlay(map, marker, content) {
  return function() {
    if(overlayArr.length != 0){
      for(var i = 0; i < overlayArr.length; i++){
        overlayArr[i].setMap(null);
      }
      overlayArr = [];
    }
    
    var overlay = new kakao.maps.CustomOverlay({
        map: map,
        content: createContent(content),
        position: marker.getPosition()
    });
    
    overlayArr.push(overlay);
    overlay.setMap(map);
  };
}


/*
오버레이 닫기
*/
function closeOverlay() {
    if(overlayArr.length != 0){
        for(var i = 0; i < overlayArr.length; i++){
            overlayArr[i].setMap(null);
        }
        overlayArr = [];
    }
}


/*
오버레이 생성 - 컨텐츠
*/
function createContent(info) {
    if (info.image == '') {
        info.image = '../assets/img/no_image.jpg';
    }
    return '<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class="title">' + 
            '            '+ info.title + 
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
            '        </div>' + 
            '        <div class="body">' + 
            '            <div class="img">' +
            '                <img src=' + info.image + ' width="73" height="70">' +
            '           </div>' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">' + info.addr1+ '</div>' + 
            '                <div class="jibun ellipsis">' + info.addr2 + '</div>' +
            '            </div>' + 
            '        </div>' + 
            '    </div>' +    
            '</div>';
}