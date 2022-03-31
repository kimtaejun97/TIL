# 📔 목차
- ### [Batch 란?](#-batch-란)
- ### [Spring Batch?](#-spring-batch)
- ### [Spring Batch 아키텍쳐](#-spring-batch-아키텍쳐)
- ### [Meta Date Schema](#-meta-data-schema)
- ### [Spring Boot와 Spring Batch](#-spring-boot와-spring-batch)
  - #### [Tasklet 방식을 사용한 간단한 배치 프로그램](#-tasklet-방식을-사용한-간단한-배치-프로그램)
- ### [Batch 도메인](#-도메인의-이해)
  - #### [Job](#-job)
  - #### [JobInstance](#-jobinstance)
  - #### [JobParameter](#-jobparameter)
  - #### [JobExecution](#-jobexecution)
  - #### [Step](#-step)
  - #### [StepExecution](#-stepexecution)
  - #### [StepContribution](#-stepcontribution)
  - #### [ExecutionContext](#-executioncontext)
  - #### [JobRepository](#-jobrepository)
  - #### [JobLauncher](#-joblauncher)
- ### [배치 설정](#-배치-설정)
- ### [Job의 실행](#-job의-실행)
  - #### [JobBuilderFactory](#-jobbuilderfactory)
  - #### [SimpleJob API](#-simplejob-api)
  - #### [SimpleJob 아키텍처](#-simplejob-아키텍처)
- ### [Step의 실행](#-step의-실행)
- ### [참조](#-참조)
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

# 📌 Spring Batch 아키텍쳐

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

# 📌 Meta Data Schema
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

# 📌 Spring Boot로 Spring Batch 시작하기

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
  
  - ### 🧐 스프링 배치 설정 클래스
    - ### BatchAutoConfiguration
      > 스프링 배치가 초기화 될 때 자동으로 실행, Job을 수행하는 JobLauncherApplicationRunner 빈을 생성한다.(ApplicationRunner를 구현했기 떄문에 스프링이 실행시킨다.)
    
    - ### SimpleBatchConfiguration
      > - JobBuilderFactory 와 StepBuilderFactory를 생성한다.    
      > - 스프링 배치의 주요 구성 요소를 생성한다.(프록시 객체로 생성된다.) - jobRepository, jobLauncher, hobRegistry, jobExplorer
    
    - ### BatchConfigurerConfiguration
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
  ![img.png](img/img.png)
    

<br>

# 📌 도메인의 이해

## 🧐 Job
Job Configuration에 의해 생성되는 객체 단위로, 배치 계층 구조에서 가장 상위에 있는 개념이며 하나의 배치작업 자체에 해당한다.(최상위 인터페이스)    
배치 작업을 어떻게 구성하고 실행할지를 설정하고 명세해 놓은 객체로 여러 step을 포함하는 컨테이너 로서의 역할을 한다. (1개 이상의 Step)

- ### 👆 구현체 (AbstractJab을 구현)
  ```
  - name : Job 이름
  - restartable: 재시작 여부 기본값 true
  - JobRepository: 메타데이더 저장소
  - JobExecutionListener: Job 이벤트 리스너
  - JobParametersIncrementer: JobParameter 증가기
  - JobParametersValidator: JobParameter 검증기
  - SimpleStepHandler: Step을 실행하는 핸들러.
  ```  

  - SimpleJob
    > - 순차적으로 Step을 실행시키는 Job으로, 표준 기능을 가지고 있다.(steps를 가지고 있음)
  - FlowJob
    > - 특정 조건과 흐름에 따라 Step을 구성하는 Job으로, Flow 객체를 실행시켜 작업을 진행한다.
    
  JobLauncher의 run(job, jobParameters) 메서드에서 job을 받아 실행시키게 되는데, job.execute(execution)로 step을 하나하나 실행시킨다.   
  구현체인 SimpleJobLauncher 코드를 보면 jobRepository에서 해당 잡의 마지막 Execution을 가져와 상태를 확인한 후 새로운 JobExecution을 생성하고,생성된 JobExecution으로 Job을 실행한다.
  Job의 execute(AbstractJob 의) 에서는 구현체의 doExecute()를 호출하고, 해당 메서드에서 handleStep(step, jobExecution)을 실행시킨다. 
  
  handleStep 에서도 마찬가지로 AbstractStep 의 execute 를 호출하고, 구현체의 doExecute가 호출된다.   

## 🧐 JobInstance
![img_3.png](img_3.png)

Job이 실행될 때 생성되는 논리적 실행 단위 객체로 고유하게 식별 가능한 작업 실행을 나타낸다.   
메타데이터를 데이터베이스(BATCH_JOB_INSTANCE)에 저장하기 위해 생성되는 인스턴스이다.

처음 시작하는 Job + JobParameter의 구성일 경우 새로운 JobInstance를 생성하고, 이전과 동일한 구성이라면 이미 존재하는 JobInstance를 리턴한다.   
(동일한 구성으로 실행할 수 없어 예외가 발생하고 Job의 실행을 중단한다 ) `A job instance already exists and is complete for parameters={ ... }`    
실행된 파라미터는 BATCH_JOB_EXECUTION_PARAMS에서 확인할 수 있으며 내부적으로는 job_name + params_key 의 해시값을 가지고 인스턴스 객체를 식별한다. 

## 🧐 JobParameter
Job을 실행할 때 함께 사용되는 파라미터를 가진 도메인 객체로, 하나의 JobInstance를 구분하기 위한 용도로 사용된다.
- JobParameters: `LinkedHashMap<String, Parameter>`를 멤버변수로 가지는 Wrapper 클래스.
- JobParameter: `Object parameter`, `ParameterType parameterType`, `boolean identifying`
- ParameterType: `String`, `Date`, `Long`, `Double`

- #### JobParameter의 생성과 바인딩
  - 어플리케이션 실행시 옵션으로 주입.
    - `Java -jar batch.jar name=user1 seq(long)=2L date(date)=2022/03/28 weight(double)=70.5`
  - 코드에서 생성
    - `JobParameterBuilder`, `DefaultJobParametersConverter`
      ```java
      JobParameters jobParameters = new JobParametersBuilder()
      .addString("name", "kim2")
      .addLong("seq", 1L)
      .addDate("data", new Date())
      .addDouble("weight", 70.5)
      .toJobParameters();
      ```
  - SpEL 이용
    - @Value("#{jobParameter[requestDate]}")
  
- #### JobParameter 꺼내기
    ```java
    // StepContribution에서 꺼내기
    JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
    jobParameters.getParameters() // Map<String, parameter>
    jobParameters.getString("key");
    jobParameters.getDate("key");
    jobParameters.getLong("key");
    jobParameters.getDouble("key");
        
    // ChunkContext 에서 꺼내기
    Map<String, Object> chunkJobParameters = chunkContext.getStepContext().getJobParameters();
    ```
  

## 🧐 JobExecution
JobInstance에 대한 한번의 시도를 의미하는 객체로, 실행 중에 발생한 정보들을 저장하고 있는 객체이다.
  - 시작 시간, 종료시간, 상태(시작?, 완료?, 실패?), 종료상태

JobExecution의 실행 결과가 `COMPLETED` 이면 인스턴스의 실행이 완료된 것으로 간주해서 재 실행할 수 없다.    
`FAILED`라면, 실행이 완료되지 않은 것이므로 재실행이 가능하다.(JobParameter가 같더라도) 즉, 실행 경과가 `COMPLETED`가 될 때까지 실행이 가능하다.    
(한 Instance 내에서 여러번의 시도가 발생할 수 있음, JobInstance와 N:1)

![img_2.png](img_2.png)

동일한 Job Instance에 대해 성곻할 때까지 Execution이 생성됨을 확인할 수 있다.
  
  
## 🧐 Step
Batch Job을 구성하는 독립적인 하나의 단계로, 실제 배치럴 처리하는 모든 정보를 가지고 있는 도메인 객체이다.      
배치작업을 어떻게 구성하고 실행할 것인지 세부작업을 Task 기반으로 설정하고 명세해 놓은 객체.

- ### 👆 필드
  - name
  - startLimit: 실행 제한 횟수.
  - allowStartIfComplete: 완료 후 재실행 가능여부.
  - stepExecutionListener: 이벤트 리스너.
  - jobRepository: 메타데이터 저장.

- ### 👆 구현체
  - TaskletStep: 가장 기본적인 구현체, Taklet 타입의 구현체를 제어한다.
  - PartitionStep: 멀티 스레드 방식으로 스텝을 여러개로 분리 실행한다.
  - JobStep: Step 내에서 Job을 실행한다.( Job -> Step -> Job .. )
  - FlowStep: Step 내에서 Flow를 실행하도록 한다.
  
Step을 실행시키는 execute(StepExecution)가 있고, StepExecution에는 실행 결과의 상태가 저장된다.    

- ### 👆 API
  - Tasklet 직접 생성
    ```java
    stepBuilderFactory.get("myStep1")
             .tasklet(myTasklet())
             .build();
    ```
  - ChunkOrientedTasklet
    ```java
    stepBuilderFactory.get("myStep3")
            .<String, String>chunk(100) // <input, output>
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
    ``` 
  - JobStep
    ```java
    stepBuilderFactory.get("jobStep")
            .job(myJob())
            .launcher(jobLauncher)
            .parametersExtractor(jobParametersExtractor())2
            .build();
    ```
  - FlowStep
    ```java
    stepBuilderFactory.get("jobStep")
            .flow(myFlow())
            .build();
    ```

## 🧐 StepExecution
![img_1.png](img_1.png)
- Step에 대한 한번의 시도를 의미하는 객체로 실행중 발생한 정보들을 저장하고 있는 객체. (시작,종료 시간, 상태, commit count, rollback count ...)    
- Job이 재시작 되더라도 이미 성공적으로 완료된 Step은 skip 하고, 실패했던 Step만 실행된다.(allowStartIfComplete 로 설정 가능.)   
- 모든 StepExecution이 성공해야 JobExecution도 성공으로 끝난다.

## 🧐 StepContribution
- 청크 프로세스의 변경 사항을 저장해뒀다가 StepExecution의 상태를 업데이트 하는 도메인 객체이다.
- 청크 커밋 직전에 StepExecution의 apply()를 호출하여 상태를 업데이트 한다.
- 사용자 정의 ExitStatus를 지정할 수 있다.

- ### 👆 필드
  - stepExecution
  - read, write, filter(ItemProcessor에 의해 필터링된) count
  - parent(StepExecution), read, write, process SkipCount
  - ExitStatus
  
  
  TaskletStep -> StepExecution -> StepContribution 순으로 생성되고,   
  chunkOrientedTasklet과 같은 구현체에서 실행된 ItemReader, Processor, Writer 의 상태들이 StepContribution에 저장된다.    
  그리고, 최종적으로 커밋되기 전에 StepExecution에 저장해뒀던 상태를 업데이트 한다.


## 🧐 ExecutionContext
Step, Job Execution 객체의 상태를 저장하는 공유 객체로 key:value 쌍으로 된 컬렉션이며 DB에 직렬화 한 값으로 저장되게 된다.

- StepExecution 의 값은 Step 간 공유 불가능.
- JobExecution 의 값은 Job 간 공유는 안되지만, Job의 Step간 공유는 가능하다.(필요한 정보를 저장해뒀다 꺼내쓰기에 유용할 것 같다)
  > Job 재시작시 이미 처리한 데이터를 Skip하고 수행할 때 해당 상태 정보를 활용한다. 

- ExecutionContext 가져오기.
  ```java
  ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
  ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
  ```
  ChunkContext, Contribution 객체 둘다에서 가져오는 것이 가능하다.    
  get, put 메서드는 ExecutionContext의 Map<String,Object> 에서 값을 넣고, 가져오는 메서드이다. 커밋 시점에 DB에 데이터를 저장한다.    
  JobInstance 가 동일하고, 이전 실행이 COMPLETED 상태가 아니라면 이전까지의 ExecutionContext에 저장된 값을 불러온 후, 나머지 Step을 다시 실행한다.

- getJob(Step)ExecutionContext?
  ```java
  Map<String, Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
  Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
  ```
상기의 getJobExecutionContext, getStepExecutionContext는 ExecutionContext를 가져오는 것이 아닌 저장되어 있는 값을 복사해 돌려주는 메서드이다.   
실제로 메서드를 살펴보았을 때 Map을 만들어 내용을 복사하고 이를 unmodifiableMap 으로 돌려줌을 확인할 수 있었다.


## 🧐 JobRepository
배치 작업 중의 정보를 저장하는 저장소로, 배치 작업의 수행과 관련된 모든 메타데이터를 저장한다.   
JobLauncher, Job, Step 구현체 내부에서 CRUD 기능을 처리한다.   

- ### 👆 주요 메서드
  - isJobInstanceExist(jobName, jobParameters)
  - createJobExecution(jobName, jobParameters)
  - getLastJobExecution(jobName, jobParameters)
  - getLastStepExecution(jobInstance, stepName)
  - update(jobExecution): Job의 실행 정보 업데이트
  - update(stepExecution)
  - add(stepExecution): 실행 중인 Step의 새로운 stepExecution 저장.
  - updateExecutionContext(jobExecution)
  - updateExecutionContext(stepExecution)
  
@EnableBatchProcessing 애노테이션을 선언하면 JobRepository가 자동으로 빈으로 등록된다.    
BatchConfigurer 인터페이스나 구현이다 BasicBatchConfigurer를 상속하여 jobRepository를 커스텀 하는 것이 가능하다.

- ### JDBC
  JDBC 방식으로 설정하기 위해서는 `JobRepositoryFactoryBean`을 사용하는데, AOP 방식으로 트랜잭션 처리가 이루어진다. 격리 레벨은 기본적으로`SERIALIZEBLE`이고, 다른 레벨로 변경 가능하다.      
  테이블의 기본 prefix는 "BATCH_"이며 변경 가능하다.
  ```java
  @Configuration
  public class CustomBatchConfigurer extends BasicBatchConfigurer {
  
      private final DataSource dataSource;
  
      protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource,
          TransactionManagerCustomizers transactionManagerCustomizers) {
          super(properties, dataSource, transactionManagerCustomizers);
          this.dataSource = dataSource;
      }
  
      @Override
      protected JobRepository createJobRepository() throws Exception {
          JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
          factoryBean.setDataSource(dataSource); // 설정하지 않아도 기본적으로 설정 됨.
          factoryBean.setTransactionManager(getTransactionManager()); // BasicBatchConfigurer에 있는 메서드
          factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
          factoryBean.setTablePrefix("LOG_BATCH");
  
          return factoryBean.getObject();
      }
  }
  ```

- ### In Memory
  DB의 저장까지는 필요가 없다면 `MapJobRepositoryFactoryBean`을 사용하여 인메모리로 사용할 수도 있다.

- ### JobRepository 에서 값 조회
  ```java
  JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
  if(lastJobExecution != null) {
      lastJobExecution.getStepExecutions()
          .forEach(s -> System.out.println(s.getExitStatus()));
  }
  ```
  
## 🧐 JobLauncher
Job과 파라미터를 인자로 받으며 배치 작업을 실행시킨 후 클라이언트에게 JobExecution을 반환한다.   
스프링 부트 배치가 구동되면 자동으로 빈이 생성되기 때문에 따로 만들어주지 않아도 된다.

ApplicationRunner를 구현한 JobLauncherApplicationRunner가 JobLauncher를 자동으로 실행시키게 된다.    
동기적(SyncTaskExecutor), 비동기적(SimpleAsyncExecutor) 실행이 가능하며 기본값은 동기적 실행이다.       
두 방식의 차이는 언제 JobExecution을 클라이언트에게 반환하느냐이다. 동기적 방식은 배치 처리가 최종적으로 완료되면 클라이언트에게 반환하지만,      
비동기적 실행에서는 JobExecution을 획득하면 바로 클라이언트에게 반환한다.(ExitStatus.UNKNOWN)   
동기적 실행은 스케줄러에 의한 배치처리와 같이 배치처리시간이 길어도 상관 없는 경우에 적합하고, 비 동기적 실행은 HTTP요청에 의한 배치 처리에 적합하다.


- 비 동기적 실행
```java

@RequiredArgsConstructor
@RestController
public class JobLauncherController {

private final Job job;
private final BasicBatchConfigurer basicBatchConfigurer;


@PostMapping("/batch")
public String launch(@RequestBody Member member)
    throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

    SimpleJobLauncher jobLauncher = (SimpleJobLauncher) basicBatchConfigurer.getJobLauncher();
    jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());

    jobLauncher.run(job, new JobParametersBuilder()
        .addString("id", member.getId())
        .addDate("date", new Date())
        .toJobParameters());

    return "batch completed";
}
```
setTaskExecutor는 JobLauncher의 메서드가 아닌 SimpleJobLauncher의 메서드이기 때문에 빈으로 주입받아 사용할 수 없다.   
JobLauncher 인터페이스로 주입 받더라도 프록시 객체이기 떄문에 SimpleJobLauncher 로의 강제 형변환 또한 불가능하다.    
때문에 BasicBatchConfigurer에서 프록시가 아닌 실제 객체를 가져와 타입 캐스팅을 해준다.


# 📌 배치 설정

### 👆 JobLauncherApplicationRunner
  - ApplicationRunner의 구현체로 BatchAutoConfifuration에서 생성된다.
  - 기본적으로 빈으로 등록된 모든 job을 실행시킨다.(특정 job만 실행하도록 설정도 가능.)

### 👆 BatchProperties
  - 환경 설정 클래스로 job 이름, 스키마 초기화 설정, 테이블 prefix 등 설정 가능.
  - properties | yml 파일에 설정 가능하다.
    - `batch.job.names`, `batch.initialize-schema: never | always | embedded`, `batch.tablePrefix: ` ... 

### 👆 Job 실행 옵션
  - `Spring.batch.jhob.names: ${job.name:NONE}` 지정한 Job만 실행하도록 한다.
    - NONE는 임의의 문자.
    - `--job.name=name1, name2`
    - properties를 설정해두고 옵션을 주지 않으면 아무 Job도 실행되지 않는다.
<br><br>
      
# 📌 Job의 실행

## 🧐 JobBuilderFactory
Job을 쉽게 생성하고 설정할 수 있도록 util 성격의 빌더 클래스인 `JobBuilderFactory`를 제공한다.    
JobBuilderFactory 에서는 JobBuilder(SimpleJobBuilder, FlowBuilder)를 생성하여 Job의 생성을 위임한다.    

![img_5.png](img_5.png)
- #### SimpleJob의 생성    
  JobBuilderFactory를 통해서 JobBuilder를 생성하고 `start(step)` 메서드를 호출하면 SimpleJobBuilder가 생성되고 최종적으로 `SimpleJob`이 생성된다.    

- #### FlowJob, Flow의 생성
  JobBuilder에서 `start(flow)` 또는 `flow(step)`을 실행하면 FlowJobBuilder를 생성하고, 최종적으로 `FlowJob`이 생성된다.    
  FlowJobBuilder 에서는 또 내부적으로 JobFlowBuilder -> FlowBuilder를 생성하고, 여기서 `Flow`를 생성하게 된다.

```java
public JobBuilder get(String name) {
    JobBuilder builder = new JobBuilder(name).repository(jobRepository);
    return builder;
}
```
SimpleJobBuilder와 FlowJobBuilder는 JobBuilderHelper 클래스를 상속하며, 해당 클래스들에서 만들어진 Job들에 SimpleJobRepository가 전달되어
CRUD를 통해 메타정보들을 기록하게 된다.


## 🧐 SimpleJob API
- ### .start(), next()
  - start() 에서 처음 실행할 step을 설정하고 SImpleJobBuilder를 생성, 반환한다, 후 next() 에서는 순차적으로 실행할 step을 등록한다.
- ### .incrementer(JobParametersIncrementer): 파라미터의 값을 자동으로 증가해주는 설정.
  - JobParameters의 값을 증가시켜 다음에 사용될 값 객체를 반환한다.
      - Long 값을 넣어주고, 실행할때 마다 해당 값을 증가시킨다.
      - 기존의 파라미터 값에는 변화가 없지만 넣어준 Long 값이 변하기 떄문에 여러번 실행 가능하다.
  - RunIdIncrementer 구현체를 지원하며, 필요하다면 인터페이스를 직접 구현하여 정의할 수 있다.
    ```java
    @Override
    public JobParameters getNext(JobParameters parameters) {
        String date = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date());
        return new JobParametersBuilder(parameters).addString("run.date", date).toJobParameters();
    }
    ```
    - 💡 incrementer.getNext()가 적용된 후 ApplicationRunner들을 실행하기 때문에 Runner 클래스에서 jobParameters를 넣어준다면   
      incrementer이 적용되지 않을 수 있다.(덮어씌워 짐)
      
- ### .preventRestart(): Job의 재시작 가능 여부 설정.
  - restartable 의 default 값은 true, .preventRestart() 하면 false로 변경됨.
  - 해당 옵션을 false로 주게 되면 job의 실행이 실패해도 재시작이 불가능하다. (JobRestartException 발생)
  - SimpleJobLaunch에서 lastJobExecution을 가져온 뒤 조건을 확인한다.
- ### .validator(jobParameterValidator): 파라미터 구성 검증.
  - DefaultJobParametersValidator 구현체를 지원한다.
    - `public DefaultJobParametersValidator(String[] requiredKeys, String[] optionalKeys)`
    - 필수키가 없거나, 필수키, 옵션키 둘다에 없는 파라미터가 들어오면 예외가 발생한다.
  - 커스텀한 제약 조건을 생성하고 싶다면 인터페이스를 직접 구현할 수도 있다.
    ```java
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
      if(parameters.getString("name") == null) {
          throw  new JobParametersInvalidException("name parameters is null");
      }
    }
    ```
- #### .listener(JobExecutionListener): Job의 실행 전, 후에 콜백을 설정.
  ```java
  @Component
  public class JobListener implements JobExecutionListener {
  
      @Override
      public void beforeJob(JobExecution jobExecution) {
      }
  
      @Override
      public void afterJob(JobExecution jobExecution) {
      }
  }
  ```

## 🧐 SimpleJob 아키텍처
![img.png](img.png)
1. JobLauncher 에서 Job, JobParameter를 가지고 JobInstance를 생성
2. JobExecution을 생성하고, ExecutionContext 할당.
3. JobExecutionListener.beforeJob()
4. 각 Step이 실행되며 StepExecution,ExecutionContext 생성
5. StepExecution에 최종 상태 업데이트.
6. JobListener.afterJob() 호출
7. JobExecution에 최종 상태 업데이트.(Status, ExitStatus)
8. JobLauncher에 반환.


# 📌 Step의 실행

## 🧐 StepBuilderFactory
StepBuilder를 생성하는 팩토리 클래스. 구조는 JobBuilderFactory와 유사하다.

- ### 👆 StepBuilder 구현체
  - #### TaskletStepBuilder
    - API: tasklet(tasklet())
  - #### SimpleStepBuilder
    - TaskletStepBuilder와 마찬가지로 TaskletStep을 생성하지만, 내부적으로 청크기반의 작업을 처리하는 ChunkOrientedTasklet을 생성한다.
    - API: chunk(chunkSize) | chunk(completionPolicy)
  - #### PartitionStepBuilder
    - PartitionStep을 생성하며 멀티 스레드 방식으로 Job을 실행한다.
    - API: partitioner(stepName, partitioner) | partitioner(step) 
  - #### JobStepBuilder
    - JobStep을 생성하고, Step안에서 Job을 실행한다.
    - API: job(job)
  - #### FlowStepBuilder
    - FlowStep을 생성하고, Step안에서 Flow를 실행한다.
    - API: flow(flow)

  TaskletStepBuilder와 SimpleStepBuilder는 StepBuilderHelper를 상속받은 AbstractTaskletStepBuilder를 상속받고,   
  나머지 빌더들은 StepBuilderHelper를 직접 상속 받는다.
  
  
## 🧐 TaskletStep
Tasklet은 스프링 배치에서 제공하는 Step의 구현체로 Tasklet을 실행시킨다.    
Task 기반과 Chunk 기반이 있으며, RepeatTEmplate를 사용하여 Tasklet 구문을 트랜잭션 내에서 반복 실행한다.


- #### Task 기반
  - 단일 작업으로 처리되는 것이 더 나은 경우 사용한다.
  - Tasklet 구현체를 생성하여 사용한다.
    ```java
    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
        .tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
        })
        .build();
        }
    ```

- #### chunk 기반
  - n개의 조각으로 나누어 실행한다, 대량 처리에 효과적으로 대처할 수 있도록 설계 되었다.
  - ChunkOrientedTasklet 구현체가 제공되며, ItemReader, ItemProcessor, ItemWriter을 사용한다.
    ```java
    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
            .<String, String>chunk(3)
            .reader(new ItemReader<String>() {
                @Override
                public String read()
                    throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                    if(!strings.isEmpty()) {
                        return strings.remove(0);
                    }
                    return null;
                }
            })
            .processor(new ItemProcessor<String, String>() {
                @Override
                public String process(String item) throws Exception {
                    return item.toUpperCase(Locale.ROOT);
                }
            })
            .writer(new ItemWriter<String>() {
                @Override
                public void write(List<? extends String> items) throws Exception {
                    items.forEach(System.out::println);
                }
            })
            .build();
    }
    ```

- ### 👆 API
  - #### .tasklet(Tasklet), chunk(int size)
    - 반복적으로 수행되는 Tasklet 타입의 클래스를 설정한다.
    - 반환 값에 따라 반복 여부는 변경 가능, `RepeatStatus.FINISHED`, `RepeatStatus.CONTINUABLE`
    - 한개만 설정이 가능하고, 여러개 설정시 마지막 설정만 실행된다.
    - 익명 또는 Tasklet을 구현한다.
    - execute()는 StepContribution, ChunkContext를 인자로 받는다.
  - #### .startLimit(int)
    - Step의 최대 실행 횟수 설정, 기본값은 INTEGER.MAX_VALUE, 초과하면 예외가 발생한다.
    - Step 마다 개별로 설정한다.
    - 동일한 JobInstance에서 실행되는 동일한 Step에 대한 실행 횟수 제한이다. 
  - #### .allowStartIfComplete(true)
    - Job을 재시작할 때 Step의 이전 실행의 성공 여부와 상관 없이 항상 Step을 실행하기 위한 설정이다.
    - Step 1~4중에 1,2까지 성공하고 실패하여 Job을 재실행할 수 있을 때 기본적으로는 3부터 다시 시작한다.    
      하지만 1,2의 작업이 무조건 선행되어야 하는 Flow라면, 해당 옵션을 활성화하여 1부터 시작하도록 변경할 수 있다.
  - #### listener(StepExecutionListener)
    - Step의 실행 전 후의 콜백.
  

## 🧐 TaskletStep 아키텍쳐
![img_8.png](img_8.png)

1. ExecutionContext를 가지는 StepExecution이 생성된다.
2. TaskletStep에서 StepExecution을 받아 Step을 실행시킨다.
3. StepExecutionListener.beforeStep()을 호출한다.
4. RepeatTemplate 에서 Tasklet을 반복 실행한다.    
   loop 에서는 RepeatStatus를 확인하여 FINISHED 라면 루프를 빠져나오고 CONTINUABLE 이라면 다시 RepeatTemplate에서 Tasklet을 반복시킨다.
5. StepExecution의 Status를 업데이트 한다.
6. StepExecutionListener.afterStep()을 호출한다.
7. StepExecution의 ExitStatus를 업데이트 한다.


### 🔑 참조

> - https://fastcampus.app/course-detail/206067
> - https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B0%B0%EC%B9%98
