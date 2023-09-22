const serviceKey = "yw3U7M2x0oh47wPnt3y4Z2TX3ALnUJmXpYCHc0HdsT%2BGxhs8Cwiop22qhI6CATxjUl2GVcULe7%2B%2F725aD4L3gg%3D%3D";

/*
시도 정보 - index page 로딩 후 전국의 시도 설정
*/
let areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`;

fetch(areaUrl, { method: "GET" })
    .then((response) => response.json())
    .then((data) => makeAreaOption(data));

function makeAreaOption(data) {
    let areas = data.response.body.items.item;
    // console.log(areas);
    let sel = document.getElementById("search-province-area");
    let opt = '';
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));

        sel.appendChild(opt);
    });
}

/*
군구 정보 - 시도 option 선택 시, 군구 정보 설정
*/
var selectedArea = document.getElementById("search-province-area");
selectedArea.addEventListener("click", () => {
    if (selectedArea.value != 0) {
        let sigunguUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=40&MobileOS=ETC&MobileApp=AppTest&areaCode=${selectedArea.value}&_type=json`;
        // console.log(selectedArea.value);
        fetch(sigunguUrl, { method: "GET" })
            .then((response) => response.json())
            .then((data) => makeSigundoOption(data));
    }
})

function makeSigundoOption(data) {
    let areas = data.response.body.items.item;
    // console.log(areas);
    let sel = document.getElementById("search-city-area");
    let opt = '<option value="0" selected>군구 선택</option>';
    areas.forEach((area) => {
        opt += `<option value=${area.code}>${area.name}</option>`;
    });
    sel.innerHTML = opt;
}


/*
시도, 군구, 관광지 유형별 조회
*/
document.getElementById("btn-search").addEventListener("click", () => {
    var searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

    let areaCode = document.getElementById("search-province-area").value;
    let sigunguCode = document.getElementById("search-city-area").value;
    let contentTypeId = document.getElementById("search-content-id").value;

    if (parseInt(contentTypeId)) searchUrl += `&contentTypeId=${contentTypeId}`;
    if (parseInt(areaCode)) searchUrl += `&areaCode=${areaCode}`;
    if (parseInt(sigunguCode)) searchUrl += `&sigunguCode=${sigunguCode}`;

    fetch(searchUrl)
      .then((response) => response.json())
      .then((data) => makeList(data)); // 검색 후 마커 찍기
});



/*
지도 그리기
*/

const startX = 37.49794117894531;
const startY = 127.02840167832981;
const startLevel = 3;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(startX, startY), // 지도의 중심좌표
        level: startLevel // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption);



/*
지도 타입 컨트롤
*/

var mapTypeControl = new kakao.maps.MapTypeControl();

map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);



/*
여러 마커 표시
*/
var positions; // marker 배열
function makeList(data) {
    let trips = data.response.body.items.item;
    // console.log(trips);
    positions = [];
    
    trips.forEach((area) => {
        // console.log(area);
        let markerInfo = {
            title: area.title,
            latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
            image: area.firstimage,
            addr1: area.addr1,
            addr2: area.addr2
        };
        positions.push(markerInfo);
    });
    // console.log(positions);
    displayMarker();
    if (positions.length != 0) {
        map.setCenter(new kakao.maps.LatLng(positions[0].latlng.Ma, positions[0].latlng.La));
        map.setLevel(4);
    }
}

function displayMarker() {
    for (var i = 0; i < positions.length; i ++) {

        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng // 마커를 표시할 위치
        });

        kakao.maps.event.addListener(marker, 'click', makeOverlay(map, marker, positions[i]));
    }
}

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
        info.image = 'assets/img/no_image.jpg';
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