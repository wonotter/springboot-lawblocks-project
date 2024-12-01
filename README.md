## SpringBoot를 이용한 LawBlocks 프로젝트 서버 구성
-----------------------------------------------

### 1. SpringBoot 사용 설정법
- SpringBoot를 사용하기 위해 해당 파일을 IntelliJ IDEA로 연다.
- 파일-설정 (Ctrl+Alt+S)에서 Gradle 검색 후 아래와 같이 설정한다.
    - Build and run using: Gradle
    - Run tests using: Gradle
    - Distribution: Gradle wrapper
    - Gradle JVM: temurin-21 Eclipse Temurin 21.0.5

### 2. 서버를 실행하기 위해 추가로 필요한 파일(패키지) 목록
- IntelliJ 내부 터미널을 열어 아래 명령을 입력하여 Docker를 활성화한다.

   (만약 도커가 없을 경우 플러그인 설치 필요)
    - docker-compose up -d

- src/main/resources/security 안에 application-db.yml, application-mail.yml 파일은 보안 문제로 push하지 않음
  
   (해당 파일은 담당 개발자인 Wonho Kim에게 문의)