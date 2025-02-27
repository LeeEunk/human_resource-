# 📂 프로젝트 디렉토리 구조
```
📦 your-project-name
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┗ 📂 com.example.annualleave
 ┃ ┃ ┃   ┣ 📂 controller
 ┃ ┃ ┃   ┃  ┗ 📜 AnnualLeaveController.java   # 연차 조회 & 등록 컨트롤러
 ┃ ┃ ┃   ┣ 📂 service
 ┃ ┃ ┃   ┃  ┗ 📜 EmployeeService.java         # 연차 조회 & 등록 서비스 로직
 ┃ ┃ ┃   ┣ 📂 repository
 ┃ ┃ ┃   ┃  ┣ 📜 EmployeeRepository.java      # 직원 정보 Repository
 ┃ ┃ ┃   ┃  ┗ 📜 AnnualLeaveRepository.java   # 연차 기록 Repository
 ┃ ┃ ┃   ┣ 📂 model
 ┃ ┃ ┃   ┃  ┣ 📜 Employee.java                # 직원 엔티티
 ┃ ┃ ┃   ┃  ┗ 📜 AnnualLeave.java             # 연차 엔티티
 ┃ ┃ ┃   ┗ 📜 AnnualLeaveApplication.java     # Spring Boot 메인 실행 파일
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃  ┣ 📂 templates
 ┃ ┃ ┃  ┃  ┗ 📜 annual_leave.html             # Thymeleaf 연차 조회 & 등록 화면
 ┃ ┃ ┃  ┣ 📂 static
 ┃ ┃ ┃  ┃  ┗ 📜 styles.css                    # (선택) CSS 스타일 파일
 ┃ ┃ ┃  ┣ 📜 application.properties           # Spring Boot 설정 (MySQL 연결 포함)
 ┃ ┃ ┃  ┗ 📜 schema.sql                       # (선택) 초기 테이블 생성 SQL
 ┃ ┣ 📂 test
 ┃ ┃ ┗ 📂 java
 ┃ ┃ ┃  ┗ 📂 com.example.annualleave
 ┃ ┃ ┃    ┗ 📜 EmployeeServiceTest.java       # 연차 서비스 테스트 코드
 ┣ 📜 pom.xml                                  # Maven 의존성 관리
 ┣ 📜 README.md                                # 프로젝트 설명 파일
```


# 프로젝트 셋업
Spring Initializr에서 다음 설정으로 프로젝트 생성:

- Spring Boot 3
- MySQL Driver (8.0)
- Spring Data JPA
- Thymeleaf
- Spring Web
- Lombok
