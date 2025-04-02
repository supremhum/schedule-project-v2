# Lv3 Scheduler

## 📌 프로젝트 개요
일정관리 Web Application의 **BACKEND** 영역을 구현해 봅니다

실습을 통해 Spring Framwork 와 Spring Boot 를 이해하고

HTTP 요청과 응답, 코드의 기본 구조를 배워봅니다

## ✨기능


<details><summary><span style="font-weight:BOLD; font-size:medium">API 명세서 포스트맨 사용</span></summary>

https://documenter.getpostman.com/view/43159046/2sAYkKJxer
</details>


- 일정 생성(일정 작성하기)
  - 멤버식별자, 작성자명(멤버 이름과는 다름), 제목, 내용, 작성/수정일을 저장
- 멤버 생성
  - 이름, 이메일(unique), 비밀번호, 생성/수정일을 저장
- 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
- 일정:멤버 = many to one 구조
- 최초 입력 시, 수정일은 작성일과 동일
- 선택한 일정 단건(ID를 통한)의 정보를 조회할 수 있습니다.
- 멤버 식별자로 생성한 일정을 조회할 수 있습니다.

- PUT,PATCH,DELETE 의 수정,삭제의 경우 비밀번호가 동일해야 기능을 수행합니다

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
    └── main
        ├── java
        │   └── com.example.schedulev2
        │       ├── ScheduleV2Application.java  # 메인 실행 파일
        │       ├── controller  # Http 요청 응답 컨트롤러
        │       │   ├── MemberController.java
        │       │   └── ScheduleController.java
        │       ├── dto
        │       │   ├── member  # member CRUD 관련 Dto
        │       │   │   ├── MemberRequestDto.java
        │       │   │   ├── MemberResponseDto.java
        │       │   │   ├── MemberSearchRequestDto.java
        │       │   │   ├── MemberUpdateRequestDto.java
        │       │   │   └── PasswordUpdateRequestDto.java
        │       │   └── schedule  # schdule CRUD 관련 Dto
        │       │       ├── CreateScheduleRequestDto.java
        │       │       ├── ScheduleDeleteRequestDto.java
        │       │       ├── ScheduleRequestDto.java
        │       │       ├── ScheduleResponseDto.java
        │       │       └── ScheduleUpdateRequestDto.java
        │       ├── entity  # DB 정보의 객체화
        │       │   ├── BaseEntity.java  # 공통관심사항인 생성일과 수정일
        │       │   ├── Member.java
        │       │   └── Schedule.java 
        │       ├── repository  # Jpa 인터페이스를 상속
        │       │   ├── MemberRepository.java
        │       │   └── ScheduleRepository.java
        │       └── service  # 로직 수행단
        │           ├── MemberService.java
        │           ├── MemberServiceImpl.java
        │           ├── ScheduleService.java
        │           └── ScheduleServiceImpl.java
        └── resources
            ├── application.properties  # DB 연동 설정과 Jpa hibernate 설정 
            ├── static
            └── templates
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
- ~~수정과 삭제시 비밀번호검증 기능 추가~~
- ~~updatdDate 는 수정 완료시 자동 변경~~
- ~~DB에서 작성자를 분리하여 관리하기~~
- 페이지네이션
- 예외 발생과 상황을 협업친화적으로 작성하기

## 🐞트러블슈팅
- DB에서 설정한 varchar 길이 넘어가면 500 error 가 뜨는것 유효성 검사하기
  - 한글과 영어의 byte 크기가 달라서 varchar에 맞추려면 바이트크기를 검사하는 코드를짜야하고
  - 또는 DB에서 TYPE을 TEXT로 바꿔 입력시 @size 체크로 제한 할 수 있다
- ~~서비스레이어에서의 코드 흐름 파악 못해서 엉뚱한 대답 받음~~
- ~~jdbc 쿼리를 작성할 때 ? 라는 prepared Statement 을 무시하면 DB에 1=1공격, drop 공격 가능~~