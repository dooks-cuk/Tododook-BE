## 🧑🏻‍💻 Contributor

| ![](https://images.weserv.nl/?url=https://avatars.githubusercontent.com/teadmoo?v=4&h=250&w=250&fit=cover&mask=circle&maxage=7d)  | ![](https://images.weserv.nl/?url=https://avatars.githubusercontent.com/sor999?v=4&h=250&w=250&fit=cover&mask=circle&maxage=7d) |
| :-------------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------: |
| [강능요](https://github.com/teadmoo) | [박현제](https://github.com/sor999) |
| Full Stack | Full Stack |




## ✍️ 기능 소개
### 기본 기능
- [x] JWT 로그인, 회원가입, 로그아웃
- [x] TODO LIST 추가, 조회, 수정, 삭제

### 추가 기능
- 로그인
  - [x] JWT 인증/인가 리팩토링 및 개선
    - 권한 handler 추가
  - [x] 소셜 로그인
- 커스텀 예외처리
  - 백엔드
    - [x] CommonException과 ErrorCode를 정의하고, 각 도메인마다 예외 개별 정의
    - [x] GlobalExceptionHandler를 이용한 전역 예외처리
  - 프론트엔드
    - [x] 로그인, 회원가입에서 잘못된 정보 입력시 검증메시지 출력
- todo 리스트
  - [x] 선택 삭제 기능
  - [x] 일괄 삭제 기능
  - [x] todo 진척도 표시 기능
    - 진척도에 따라 0~100 사이의 상태바 UI 바뀜
  - [x] 카테고리 기능
    - 개인 일상, 업무, 학습, 취미, 금융, 기타 카테고리 있음
    - 각 카테고리 마다 다른 색으로 표시됨
  - [x] 우선 순위 선택 기능
    - High, Medium, Low 중 선택
  - [x] 우선 순위 정렬 기능
    - 기본순(추가 순서), 중요도순 중 선택
