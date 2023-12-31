# 04_EnjoyTrip_Back

### 로고 이미지

![로고](./WebContent/assets/img/Hahahoho-logo.png)

## 1. 메인 페이지

하하호호 여행사에 대한 서비스 설명과 대문 이미지를 제공합니다. 로그인 여부에 따라 네비게이션 바 메뉴가 달라집니다. 비로그인 시에는 `LOGIN`, `ABOUT`, `SIGNIN` 메뉴를 제공하고, 로그인 시에는 `LOGOUT`, `ABOUT`, `MAP`, `MYPAGE` 메뉴를 제공합니다.

해당 메뉴들은 아래의 기능을 합니다.

- LOGIN : 로그인 페이지로 이동합니다.
- ABOUT : 메인 페이지의 서비스 설명으로 이동합니다.
- SIGNIN : 회원가입 페이지로 이동합니다.
- LOGOUT : 로그아웃됩니다.
- MYPAGE : 마이페이지로 이동합니다.

### 비 로그인시 네비바(default)

![비로그인](./WebContent/assets/img/indexpage_default.png)

### 로그인시 네비바

![로그인](./WebContent/assets/img/indexpage_login.png)

### 반응형 네비바

네비게이션 바는 반응형으로 구현되었습니다. 화면의 폭을 줄이면 햄버거 버튼으로 바뀝니다.

![반응형](./WebContent/assets/img/indexpage_small.png)

## 2. 관광지 조회

최대 세 가지 정보를 통해 관광지를 조회합니다. `시도` 선택 시 해당하는 `군구` 를 선택할 수 있습니다. 8개의 `관광지 유형` 선택이 가능하고, 미 선택 시 전체 유형이 검색됩니다.

검색 결과는 하단의 지도에 마커로 표시됩니다. 마커를 클릭하면 해당 관광지의 정보가 오버레이 형태로 나타납니다. 관광지 정보는 닫기 버튼을 클릭하여 없앨 수 있습니다.

### 지역별 관광지 조회(전체)
![전체](./WebContent/assets/img/capture/조회_전체.png)

### 지역별 관광지 조회(관광지)
![전체](./WebContent/assets/img/capture/조회_관광지.png)

### 지역별 관광지 조회(문화시설)
![전체](./WebContent/assets/img/capture/조회_문화시설.png)

### 지역별 관광지 조회(행사/공연/축제)
![전체](./WebContent/assets/img/capture/조회_축제공연행사.png)

### 지역별 관광지 조회(여행코스)
![전체](./WebContent/assets/img/capture/조회_여행코스.png)

### 지역별 관광지 조회(레포츠)
![전체](./WebContent/assets/img/capture/조회_레포츠.png)

### 지역별 관광지 조회(숙박)
![전체](./WebContent/assets/img/capture/조회_숙박.png)

### 지역별 관광지 조회(쇼핑)
![전체](./WebContent/assets/img/capture/조회_쇼핑.png)

### 지역별 관광지 조회(음식점)
![전체](./WebContent/assets/img/capture/조회_음식점.png)

## 3. 회원 가입

2가지 방법으로 회원 가입 페이지로 이동이 가능합니다.

### 네비게이션 바

네비게이션 바에 있는 `Sign up`를 선택하는 경우 회원 가입 페이지로 이동이 가능합니다.

![네비게이션 바](./WebContent/assets/img/signuppage_login1.jpg)

### 로그인 페이지

로그인 페이지의 `회원가입` 버튼을 선택시 회원 가입 페이지로 이동이 가능합니다.

![로그인 페이지](./WebContent/assets/img/signuppage_login2.jpg)

### 회원가입 페이지

회원가입 페이지는 화면 중앙에 폼 형태로 나타납니다. 회원가입에 필요한 정보를 입력받아 사용자의 정보를 저장할 수 있습니다.<br>
사용자의 정보를 통해 `로그인`, `비밀번호 찾기`, `회원정보 수정`, `회원 삭제`가 가능합니다.

![로그인 페이지](./WebContent/assets/img/singuppage.jpg)

## 4. 마이페이지 - 회원 정보 조회, 수정, 삭제

마이페이지는 로그인 후 네비게이션 바에 있는 Mypage를 통해 이동이 가능합니다. <br>

### 회원 정보 조회, 수정

마이페이지에서 회원 정보 조회가 가능하며, 회원정보 수정 후 `회원정보 수정` 버튼을 통해 수정이 가능합니다.

![마이페이지](./WebContent/assets/img/mypage.jpg)

### 회원 삭제

마이페이지에서 `회원탈퇴` 버튼을 클릭하면 회원 탈퇴가 가능합니다. <br>
잘못 클릭시 바로 회원탈퇴가 되지 않도록 한번 더 확인하는 과정이 있습니다.

![회원 탈퇴](./WebContent/assets/img/mypage_delete.jpg)

## 5. 로그인, 로그아웃

로그인은 네비게이션 바에 있는 LOGIN를 통해 로그인 페이지로 이동이 가능합니다. <br>
로그아웃은 네비게이션 바에 있는 LOGOUT를 통해 로그아웃이 가능하고 메인 페이지로 이동합니다.

### 로그인

등록된 아이디에 해당하는 비밀번호를 입력한 경우 로그인이 됩니다. <br>
로그인이 성공하면 관광지 조회 페이지로 넘어갑니다. <br>
없는 아이디인 경우 아이디가 없습니다.라는 알림창이 나옵니다. <br>
잘못된 비밀번호를 넣는 경우 비밀번호가 틀립니다.라는 알림창이 나옵니다.

![로그인 실패1](./WebContent/assets/img/loginpage_fail1.jpg)

![로그인 실패2](./WebContent/assets/img/loginpage_fail2.jpg)

### 로그아웃

네비게이션 바에 있는 `LOGOUT` 버튼을 클릭하면 로그아웃이 가능합니다. <br>
로그아웃 시 로그인 세션을 제거하며, 메인페이지로 이동합니다.

![로그아웃](./WebContent/assets/img/logout.jpg)

## 6. 비밀번호 찾기

비밀번호 찾기는 로그인 페이지의 `비밀번호 찾기` 버튼을 통해 가능합니다.
비밀번호를 찾기 위해서는 사용자의 `아이디`, `이메일`, `사용자 질문의 답변`이 필요합니다.

### 아이디로 회원 이름 확인

아이디 입력 후 `ID 확인` 버튼을 통해 사용자의 이름을 확인할 수 있습니다. <br>
이름 확인 후 다음으로 넘어갑니다. 아이디가 없는 경우 `해당하는 아이디가 없습니다.`라는 알림창이 나옵니다.

![아이디 확인](./WebContent/assets/img/findpasswordbyid.jpg)

![아이디 확인 실패](./WebContent/assets/img/findpasswordbyid2.jpg)

### 이메일로 회원의 질문 및 질문에 대한 답변 확인

이메일 입력 후 `Email 확인` 버튼을 통해 사용자의 질문을 조회 가능합니다. <br>
이메일 확인 후 비밀번호 재설정 페이지로 넘어갑니다.

![이메일 확인](./WebContent/assets/img/findpasswordbyemail.jpg)

이메일이 다른 경우 `해당하는 이메일이 없습니다.`라는 알림창이 나옵니다.

![이메일 확인 실패](./WebContent/assets/img/findpasswordbyemail2.jpg)

질문의 답이 틀린 경우에는 `정답이 아닙니다.`라는 알림창이 나옵니다.

![답변 틀림](./WebContent/assets/img/findpasswordbyemail_fail.jpg)

### 비밀번호 재설정

비밀번호 재설정 페이지에서 비밀번호 재설정이 가능하며, 새로운 비밀번호와 비밀번호 체크를 다시 한번 확인하여 잘못된 비밀번호가 설정되지 않도록 합니다.

![비밀번호 재설정](./WebContent/assets/img/changepasswordpage1.jpg)

![비밀번호 재설정](./WebContent/assets/img/changepasswordpage2.jpg)

사용자 데이터 변경점을 확인하면 다음과 같습니다.

![기존 비밀번호](./WebContent/assets/img/userinform.jpg)

![새로운 비밀번호](./WebContent/assets/img/usernewinform.jpg)
