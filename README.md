# citObsBlog
스프링부트로 블로그만들기

# blog 제작 시작
### spring boot + rest api + jsp

# 2023.10.14~15 blog DB연결 및 CRUD TEST 제작
    - DB정보 : MYSQL, 포트 3306
    - DB스키마 : blog
    - properties => yml 변경
    - CRUD 최종완성, 익셉션까지 완성

# 화면설계 및 프론트단에 DB데이터 가져오는 중
    - ajax 비동기 처리 완료
    - 기존 slim버전은 ajax를 지원암함. 3.5.1버전으로 다운그레이드
    - <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

# 화면설계 및 적용완료 백단 로그인 회원가입 DB연동 성공
    - ajax에서 데이터연결 및 적용, DB주입완료
    - DTO 생성 및 연결 완료
# 글로벌 에러처리 완료

# 로그인처리 완료 
    - 일단 인증 제외
    - 인증은 Spring Security로 추후 추가 할 예정

# 스프링 시큐리티 적용완료
    - hashcode 적용 
    - 로그인 적용 완료
