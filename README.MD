# Lv4 SchedulerV2

## 📌 프로젝트 개요
**JPA**를 활용한 데이터베이스 관리와 

**Cookie/Session을 활용한 인증/인가** 를 연습해 봅니다

기본 **CRUD**에 대한 구조 이해도 끌어올리고

**보안 개념과 인증 메커니즘**을 이해해 봅니다

## ✨기능


<details><summary><span style="font-weight:BOLD; font-size:medium">API 명세서 포스트맨 사용</span></summary>

https://documenter.getpostman.com/view/43159046/2sB2cSiPqi
</details>

- 기본적으로 **회원가입**과 **로그인**이 되어야 다른 API요청을 할 수 있습니다 
  - 세션 로그인 방식을 권장합니다
- 일정 생성(일정 작성하기)
  - 이메일, 작성자명, 제목, 내용, 작성/수정일을 저장
- 멤버 생성
  - 이름, 이메일(unique), 비밀번호, 생성/수정일을 저장

- 일정:멤버 = many to one 구조

- 수정,삭제의 경우 비밀번호가 필요합니다

## 🗄데이터베이스 설계

<details><summary><span style="font-weight:BOLD; font-size:medium">ERD</span></summary>

![onealog](/assets/erdlv3.png)

</details>

## 📁 프로젝트 구조

<details><summary><span style="font-weight:BOLD; font-size:medium">구조</span></summary>

```
scheduleV2
├── HELP.md
├── README.MD
├── schedule.sql
├── settings.gradle
└── src
    ├── main/java/com/example/schedulev2
    │   │   ├── ScheduleV2Application.java        # 메인 실행 파일
    │   │   ├── config
    │   │   │   └── WebConfig.java                # 웹 설정
    │   │   ├── controller                        
    │   │   │   ├── HomeController.java           # 쿠키로 로그인 등 처리
    │   │   │   ├── MemberController.java         # 회원 관련 요청 처리
    │   │   │   ├── ScheduleController.java       # 스케줄 관련 요청 처리
    │   │   │   └── SessionController.java        # 세션으로 로그인 등 처리
    │   │   ├── dto                               
    │   │   │   ├── member
    │   │   │   │   ├── MemberByIdResponseDto.java       # 회원 ID로 조회한 응답 DTO
    │   │   │   │   ├── MemberDeleteRequestDto.java      # 회원 삭제 요청 DTO
    │   │   │   │   ├── MemberResponseDto.java           # 회원 응답 DTO (공통 응답 형태)
    │   │   │   │   ├── MemberSearchRequestDto.java      # 회원 검색 요청 DTO
    │   │   │   │   ├── MemberUpdateRequestDto.java      # 회원 정보 수정 요청 DTO
    │   │   │   │   ├── PasswordUpdateRequestDto.java    # 비밀번호 수정 요청 DTO
    │   │   │   │   ├── SignInRequestDto.java            # 로그인 요청 DTO
    │   │   │   │   ├── SignInResponseDto.java           # 로그인 응답 DTO
    │   │   │   │   ├── SignUpRequestDto.java            # 회원가입 요청 DTO
    │   │   │   │   └── SignUpResponseDto.java           # 회원가입 응답 DTO
    │   │   │   └── schedule
    │   │   │       ├── ScheduleCreateRequestDto.java    # 스케줄 생성 요청 DTO
    │   │   │       ├── ScheduleDeleteRequestDto.java    # 스케줄 삭제 요청 DTO
    │   │   │       ├── ScheduleDetailResponseDto.java   # 스케줄 상세 응답 DTO
    │   │   │       ├── ScheduleResponseDto.java         # 스케줄 공통 응답 DTO
    │   │   │       └── ScheduleUpdateRequestDto.java    # 스케줄 수정 요청 DTO
    │   │   ├── entity                            
    │   │   │   ├── BaseEntity.java               # 생성일/수정일 등을 가진 부모 엔티티
    │   │   │   ├── Member.java                   # 회원 엔티티
    │   │   │   └── Schedule.java                 # 스케줄 엔티티
    │   │   ├── filter
    │   │   │   ├── CustomFilter.java             # 연습용 시작 필터 
    │   │   │   └── LoginFilter.java              # 로그인 여부 체크 필터
    │   │   ├── repository                        
    │   │   │   ├── HomeRepository.java           # 로그인 관련 쿼리 정의
    │   │   │   ├── MemberRepository.java         # 회원 관련 쿼리 정의
    │   │   │   └── ScheduleRepository.java       # 스케줄 관련 쿼리 정의
    │   │   └── service                           
    │   │       ├── HomeService.java              # 로그인 관련 서비스 인터페이스
    │   │       ├── HomeServiceImpl.java          # 로그인 관련 서비스 구현체
    │   │       ├── MemberService.java            # 회원 서비스 인터페이스
    │   │       ├── MemberServiceImpl.java        # 회원 서비스 구현체
    │   │       ├── ScheduleService.java          # 스케줄 서비스 인터페이스
    │   │       ├── ScheduleServiceImpl.java      # 스케줄 서비스 구현체
    │   │       ├── SessionService.java           
    │   │       └── SessionServiceImpl.java       
    │   └── resources
    │       ├── application.properties            # 스프링 부트 설정 파일 (DB, JPA 등)
    │       ├── static                            # 정적 파일 위치 (이미지, JS, CSS 등)
    │       └── templates                         # Thymeleaf 템플릿 위치 (html 파일 등)

```
</details>


## 🛠️🚀🧪 기술 스택
- **Java**: 애플리케이션 구현 언어 - 17.x버전
- **IntelliJ IDEA**: 개발 환경
- **Spring**: Java 웹 어플리케이션 프레임워크
- **mySQL**: DataBase 구축 및 관리 - 8.x 버전
- **Git**: 버전 관리 및 브랜치 사용
- **PostMan**: HTTP 요청 및 응답 테스트 도구 

## ⚠️예외 처리 방식
- Index 접근이 있을 때 Optional 로 null 을 안정화

## 📝향후 개선 사항
- 로그인 기능이 있으니 해당 유저의 정보들만 볼 수 있게끔 해야함
  - 일정생성은 로그인 필수라 이메일 기입이 필수가 아니게 됨
  - 작성명도 받지말고 멤버 이름으로 자동 기입
- 예외 발생과 상황을 협업친화적으로 작성하기
- 페이지네이션

## 🐞트러블슈팅
- 동적 쿼리 작성 고민
- SQL 문법 (ALTER와 INSERT혼동)
- @Min 으로 문자검증
- 비밀번호 업데이트에서 세터 고민
- SQL 과 자바간 상이한 대소문자 구분
- DB에서 설정한 varchar 길이 넘어가면 500 error 가 뜨는것 유효성 검사하기
  - 한글과 영어의 byte 크기가 달라서 varchar에 맞추려면 바이트크기를 검사하는 코드를짜야하고
  - 또는 DB에서 TYPE을 TEXT로 바꿔 입력시 @size 체크로 제한 할 수 있다