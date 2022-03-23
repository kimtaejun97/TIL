# 📔 목차
- ### [Batch 란?](#-batch-란)
- ### [Spring Batch?](#-spring-batch)
- ### [Spring Batch 아키텍쳐](#-spring-batch-아키텍쳐)
- ### [Meta Date Schema](#-meta-data-schema)
- ### [Spring Boot와 Spring Batch](#-spring-boot와-spring-batch)
  - ### [Tasklet 방식을 사용한 간단한 배치 프로그램](#-tasklet-방식을-사용한-간단한-배치-프로그램)
<br>

****
### 🤔 Batch 란?

정해진 시간에 일괄적으로 작업을 처리할 수 있도록 해주 프로그램으로 주로 대용량 데이터를 다룬다.    
필요한 데이터를 모아서 처리하거나, 일정 시간 뒤에 처리하고자 할 때 사용할 수 있고,대용량 데이터를 다룰 때 트래픽이 적은 시간대에 서버 리소스를 사용하기 위해 사용한다.    
(주로 ETL:Extract-Transform-Load, 대용량 데이터를 데이터 웨어하우스에 저장.)

### 🤔 Spring Batch?

자바 기반 표준 배치 기술의 부재로 배치 처리에서 요구하는 재사용 가능한 자바 기반 배치 아키텍처 표준의 필요성이 대두되었고,   
Accenture에서 소유하고 있던 배치 처리 아키텍처 프레임웤르를 스프링 배치 프로젝트에 기증하였다.

가볍고 다양한 기능을 가진 배치 프레임워크로, 견고한 배치 어플리케이션 개발이 가능하도록 디자인 되어있다.   
최근 기업 시스템 운영에 필수적이라고 할 수 있다.

기존 Spring 프로젝트의 모듈을 활용할 수 있다는 장점을 가진다.(새로운 언어로 처리를 새로 구현하지 않아도 된다.)   
배치 처리를 위한 로지을 새로 만들지 않고 스프링 배치에서 제공하는 기능을 사용할 수 있다.

- ### 👆 배치의 핵심 패턴
  - **Read**: DB, 파일, 큐 등에서 다량의 데이터를 읽는다.
  - **Process**: 데이터를 가공한다.
  - **Write**: 가공된 데이터를 다시 저장한다.


- ### 👆 배치 시나리오
  - 배치 프로세스를 주기적으로 커밋한다.(효율적인 커밋 전략.)
  - 동시 다발적인 Job 의 배치 처리, 병렬 처리.
  - 실패 후 스케줄링에 의해 재시작된다.
  - 의존관계가 있는 step들을 순차적으로 처리한다.
  - 조건에 따라 흐름을 구성하는 등 체계적이고 유연한 배치 모델을 구성한다.
  - 반복하거나, 재시도, Skip 처리(중요하지 않은 예외를 스킵, 계속 실행될 수 있도록)등..

<br>

## 📌 Spring Batch 아키텍쳐

![img_1.png](img/img_1.png)

- `JobLauncher`: Job을 실행시키는 컴포넌트
- `Job`: 배치 작업.
- `JobRepository`: Job의 실행과 Job, Step을 저장.
- `Step`: 배치 작업의 단계. ItemReader, ItemProcessor, ItemWriter는 데이터를 읽고, 처리하고, 쓰는 구성을 하나씩 가진다.

![img_2.png](img/img_2.png)

- **Application**
  > 비즈니스, 서비스 로직, Core, Infrastructure을 이용하여 배치 기능을 만든다.     
  > 개발자는 업무 로직의 구현에만 집중하고 공통적인 기술 기반은 프레임 워크가 담당하도록 한다.
- Core
  > 배치 작업을 시작하고 제어하는 필수 클래스(Job, Step, JobLauncher, Flow)    
  > Job을 실행하고 모니터링, 관리하는 API로 구성되어 있다.
- Infrastructure
  > 외부와 상호작용하는 레이어, (ItemReader, ItemWriter, RetryTemplate, Skip)    
  > Application, Core 모두 공통 Infrastructure 위에서 빌드한다. Job 실행의 흐름과 처리를 위한 틀을 제공한다.

  실제로 패키지 구조를 열어 확인해 볼 수 있다.

- ### 🧐 Job
  ![img_3.png](img/img_3.png)
  - 전체 배치 프로세스를 캡슐화한 도메인으로, Step의 순서를 정의한다.
  - `JobParameters` 를 받는다.
  - JobParameters를 받아 JobInstance가 생성되고, JobExecution으로 나누어져 실행된다.

- ### 🧐 Step
  ![img_4.png](img/img_4.png)
  - 작업의 처리 단위.
  - Chunk | Tasklet 기반으로 하나의 트랜잭션에서 데이터를 처리한다.
  - commitInterval 만큼 데이터를 읽고, 데이터를 처리한 뒤, ChunkSize 만큼 한번에 Write 한다.

<br>

## 📌 Meta Data Schema
  ![img_5.png](img/img_5.png)    

  스프링 배치가 실행 및 관리를 위한 목적으로 여러 도메인(Job, Step, Execution, Instance JobParams ...) 의 정보를 저장할 수 있는 스키마를 제공한다.    
  Job 의 이력(성공, 실패), 파라미터 등 실행 결과를 조회할 수 있다. -> 리스크 발생시 빠른 대처 가능.    

  DB와 연동할 경우 필수적으로 메타 테이블이 생성되어야 하며 스키마 파일의 위치는 /org/springframework/batch/core/schema-*.sql 이다.(DB 유형별로 제공)

- ### 🧐 테이블
  - BATCH_JOB_INSTANCE
    >  Job 이 실행될 때 JobInstance 정보가 저장되며, job_name과 job_key로 하여 하나의 데이터가 저장된다 (인스턴스는 유일)
      - version: 업데이트 마다 1씩 증가하는 값
      - job_name: job을 구성할 때 부여한 이름.
      - job_key: name 과 parmas를 합쳐 해싱한 값
  
  - BATCH_JOB_EXECUTION
    > Job의 실행 정보(생성, 시작, 종료 시간, 실행 상태, 종료 코드, 실패 원인 메시지, 마지막 실행 시점 등)
  - BATCH_JOB_EXECUTION_PARAMS
    > Job과 함께 실행되는 JobParams 정보를 저장.
      - type_cd : String, Long, Date 등의 타입 정보
      - key_name: 파라미터 키 값.
      - string_val: 파라미터 문자 값
      - data_val: 파라미터 날짜 값.
      - long_val
      - double_val
      - identifying: 식별 여부 (boolean)
  - BATCH_JOB_EXECUTION_CONTEXT
    > Job의 실행동안 여러가지 상태정보, 공유 데이터를 JSON 형식으로 직렬화하여 저장한다. Step간의 공유가 가능하다.
      - short_context: job의 실행 상태정보, 공유데이터 등의 정보를 **문자열**로 저장
      - serialized_context: 직렬화 된 전체 컨텍스트
  - BATCH_STEP_EXECUTION
    > - Step의 실행 정보(생성, 시작, 종료 시간, 실행 상태, 종료 코드, 실패 원인 메시지, 마지막 실행 시점 등)
    > - 부모(Job)의 ID
    > - 트랜잭션당 Commit, Read, Write, Filter, Read skip, Write skip, ProcessSkip, Rollback 수
  - BATCH_STEP_EXECUTION_CONTEXT
    > Job의 경우와 동일하지만, Step 별로 저장되며 Step간 공유할 수 없다. 
    
  테이블간의 관계(1:N) 에 주의하여 살펴보자.

- ### 🧐 스키마 생성 설정
  - 수동 생성: 쿼리 복사 후 직접 생성.
  - 자동 생성: properties 에서 spring.batch.jdbc.initialize-schema 설정.
    - ALWAYS
      > 스크립트 항상 실행, RDBMS 설정이 되어있을 경우 내장 DB보다 우선적으로 실행한다.
    - EMBEDDED
      > 내장 DB 일때만 실행된다. (기본값)
    - NEVER
      > - 스크립트를 항상 실행하지 않는다. 테이블이 없다거나 내장 DB 라면 오류 발생.
      > - 운영에서 수동으로 스크립트 생성 후 설정하는 것을 권장한다.

 

<br>

## 📌 Spring Boot로 Spring Batch 시작하기

- #### 의존성 추가
  ```groovy
  implementation 'org.springframework.boot:spring-boot-starter-batch'
  testImplementation 'org.springframework.batch:spring-batch-test'
  ```

- #### @EnableBatchProcessing
  ```java
  @EnableBatchProcessing
  @SpringBootApplication
  public class SpringBatchApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(SpringBatchApplication.class, args);
      }
  
  }
  ```
  - 스프링 배치를 작동시키기 위해 선언하는 애노테이션으로, 총 4개의 설정 클래스를 실행시키며 스프링 배치의 모든 초기화 및 실행 구성이 이루어진다.
  - 스프링 부트 배치의 자동설정 클래스가 실행되어 등록된 모든 Job을 검색하여 초기화하고 동시에 Job 을 수행하도록 구성한다.
  
  - ### 👆 스프링 배치 설정 클래스
    - BatchAutoConfiguration
      > 스프링 배치가 초기화 될 때 자동으로 실행, Job을 수행하는 JobLauncherApplicationRunner 빈을 생성한다.(ApplicationRunner를 구현했기 떄문에 스프링이 실행시킨다.)
    
    - SimpleBatchConfiguration
      > - JobBuilderFactory 와 StepBuilderFactory를 생성한다.    
      > - 스프링 배치의 주요 구성 요소를 생성한다.(프록시 객체로 생성된다.) - jobRepository, jobLauncher, hobRegistry, jobExplorer
    
    - BatchConfigurerConfiguration
      - BasicBatchConfigurer
        > SimpleBatchConfiguration 에서 생성한 프록시 객체의 실제 타겟을 생성하는 설정 클래스.
      - JpaBatchConfigurer
        > JPA 관련 객체를 생성하는 설정 클래스.
  
    
    
- ### 🧐 Tasklet 방식을 사용한 간단한 배치 프로그램
  ```java
  @RequiredArgsConstructor
  @Configuration
  public class JobConfig {
  
      // #1
      private final JobBuilderFactory jobBuilderFactory; 
      private final StepBuilderFactory stepBuilderFactory;
  
      @Bean
      public Job myJob() {
          return jobBuilderFactory.get("myJob") // #2
              .start(myStep())
              .next(myStep2())
              .build();
      }
  
      @Bean
      public Step myStep() {
          return stepBuilderFactory.get("myStep1") // #2
              .tasklet((contribution, chunkContext) -> {
                  System.out.println("================ My Step1 =============");
                  return RepeatStatus.FINISHED; // #3
              })
              .build();
      }
  
      @Bean
      public Step myStep2() {
          return stepBuilderFactory.get("muStep2")
              .tasklet((contribution, chunkContext) -> {
                  System.out.println("================ My Step2 =============");
                  return RepeatStatus.FINISHED;
              })
              .build();
      }
  }
  ```
  - 모든 Job과 Step은 빈으로 등록되어야 한다.
  - **(#1)**: Job, Step을 생성하는 빌더 팩톹리
  - **(#2)**: Job, Step의 이름을 지정해준다.
  - **(#3)**: tasklet은 기본적으로 무한반복한다. 때문에 이와 같은 값을 반환하여 한번 실행 후 종료할 수 있도록 한다.(반복 false)
  
  - 결과
  ![img.png](img.png)
    

<br><br>

### 🔑 참조

> - https://fastcampus.app/course-detail/206067
> - https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B0%B0%EC%B9%98
