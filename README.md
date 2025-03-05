# LawBlocks 프로젝트 SpringBoot 구성
-----------------------------------------------


## 패키지 설명

### common
- **BaseEntity.java**: 모든 엔티티의 기본 클래스. 생성자와 수정자를 자동으로 기록합니다.
- **BaseTimeEntity.java**: 엔티티의 생성 및 수정 시간을 기록하는 클래스입니다.
- **LawblocksException.java**: 커스텀 예외의 기본 클래스입니다.
- **Validator.java**: 입력값 검증을 위한 유틸리티 클래스입니다.
- **exception**: 다양한 예외 클래스와 전역 예외 처리기를 포함합니다.

### config
- **AuthConfig.java**: 인증 관련 설정을 정의합니다.
- **JpaAuditingConfig.java**: JPA Auditing을 활성화합니다.
- **QueryDslConfig.java**: QueryDSL 설정을 정의합니다.
- **WebSecurityConfig.java**: 웹 보안 설정을 정의합니다.

### controller
- **PostController.java**: 게시물 관련 API를 제공합니다.
- **UserController.java**: 사용자 인증 및 관리 관련 API를 제공합니다.

### dto
- 데이터 전송 객체(DTO)들을 포함하며, 클라이언트와 서버 간의 데이터 교환을 위한 클래스들입니다.

### entity
- **Category.java**: 카테고리 엔티티입니다.
- **Certification.java**: 인증 관련 엔티티입니다.

### service
- 비즈니스 로직을 처리하는 서비스 클래스들을 포함합니다.

## 사용 방법

1. 프로젝트를 클론합니다.
2. 필요한 의존성을 설치합니다.
3. SpringBoot를 사용하기 위해 해당 파일을 IntelliJ IDEA또는 다른 IDE로 실행합니다.

📭 Intellij의 경우 아래와 같이 프로젝트를 설정합니다.
- 파일-설정 (Ctrl+Alt+S)에서 Gradle 검색
  - Build and run using: Gradle
  - Run tests using: Gradle
  - Distribution: Gradle wrapper
  - Gradle JVM: temurin-21 Eclipse Temurin 21.0.5
4. 서버를 실행합니다.

### 📌 서버를 실행하기 위해 추가로 필요한 파일(패키지) 목록

- IntelliJ 내부 터미널을 열어 아래 명령을 입력하여 Docker를 활성화합니다.

  (만약 도커가 없을 경우 플러그인 설치 필요)
  - docker-compose up -d

- src/main/resources/security 안에 application-db.yml, application-mail.yml MySQL 및 이메일 인증을 위한 구글 SMTP 설정 파일입니다.

따라서 보안 문제로 업로드 하지 않았으며, 필요한 경우 별도로 설정하여 함께 추가하면 됩니다.
