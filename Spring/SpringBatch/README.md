# π λͺ©μ°¨
- ### [Batch λ?](#-batch-λ)
- ### [Spring Batch?](#-spring-batch)
- ### [Spring Batch μν€νμ³](#-spring-batch-μν€νμ³)
- ### [Meta Date Schema](#-meta-data-schema)
- ### [Spring Bootμ Spring Batch](#-spring-bootμ-spring-batch)
  - #### [Tasklet λ°©μμ μ¬μ©ν κ°λ¨ν λ°°μΉ νλ‘κ·Έλ¨](#-tasklet-λ°©μμ-μ¬μ©ν-κ°λ¨ν-λ°°μΉ-νλ‘κ·Έλ¨)
- ### [Batch λλ©μΈ](#-λλ©μΈμ-μ΄ν΄)
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
- ### [λ°°μΉ μ€μ ](#-λ°°μΉ-μ€μ )
- ### [Jobμ μ€ν](#-jobμ-μ€ν)
  - #### [JobBuilderFactory](#-jobbuilderfactory)
  - #### [SimpleJob API](#-simplejob-api)
  - #### [SimpleJob μν€νμ²](#-simplejob-μν€νμ²)
- ### [Stepμ μ€ν](#-stepμ-μ€ν)
  - #### [StepBuilderFactory](#-stepbuilderfactory)
  - #### [TaskletStep](#-taskletstep)
  - #### [TaskletStep μν€νμ²](#-tasklet-μν€νμ²)
  - #### [JobStep](#-jobstep)
- ### [FLow](#-flow)
  - #### [FlowJob](#-flowjob)
  - #### [Trasition](#-transition)
  - #### [μ¬μ©μ μ μ ExitStatus](#-μ¬μ©μ-μ μ-exitstatus)
  - #### [JobExecutionDecider](#-jobexecutiondecider)
  - #### [FlowJob μν€νμ²](#-flowjob-μν€νμ²)
  - #### [SimpleFlow](#-simpleflow)
  - #### [FlowStep](#-flowstep)
- ### [@JobScope, @StepScope](#-jobscope-stepscope)
- ### [Chunk Process](#-chunk-process)
- ### [ItemReader κ΅¬νμ²΄](#-itemreader-κ΅¬νμ²΄)
  - #### [FlatFileItemReader](#-flatfileitemreader)
  - #### [XML-StaxEventItemReader](#-xml-staxeventitemreader)
  - #### [JsonItemReader](#-jsonitemreader)
  - #### [JdbcCursorItemReader](#-jdbccursoritemreader)
  - #### [JpaCursorItemReader](#-jpacursoritemreader)
  - #### [JdbcPagingItemReader](#-jdbcpagingitemreader)
  - #### [JpaPagingItemReader](#-jpapagingitemreader)
  - #### [ItemReaderAdapter](#-itemreaderadapter)
- ### [ItemWriter κ΅¬νμ²΄](#-itemwriter-κ΅¬νμ²΄)
  - #### [FlatFileItemWriter](#-flatfileitemwriter)
  - #### [XML-StaxEventItemWriter](#-xml-staxeventitemwriter)
  - #### [JsonFileItemWriter](#-jsonfileitemwriter)
  - #### [JdbcBatchItemWriter](#-jdbcbatchitemwriter)
  - #### [JpaItemWriter](#-jpaitemwriter)
  - #### [ItemWriterAdapter](#-itemwriteradapter)
- ### [ItemProcessor κ΅¬νμ²΄](#-itemprocessor-κ΅¬νμ²΄)
  - #### [CompositeItemProcessor](#-compositeitemprocessor)
  - #### [ClassifierCompsiteProcessor](#-classifiercompositeitemprocessor)

- ### [μ°Έμ‘°](#-μ°Έμ‘°)
<br>

****
### π€ Batch λ?

μ ν΄μ§ μκ°μ μΌκ΄μ μΌλ‘ μμμ μ²λ¦¬ν  μ μλλ‘ ν΄μ£Ό νλ‘κ·Έλ¨μΌλ‘ μ£Όλ‘ λμ©λ λ°μ΄ν°λ₯Ό λ€λ£¬λ€.    
νμν λ°μ΄ν°λ₯Ό λͺ¨μμ μ²λ¦¬νκ±°λ, μΌμ  μκ° λ€μ μ²λ¦¬νκ³ μ ν  λ μ¬μ©ν  μ μκ³ ,λμ©λ λ°μ΄ν°λ₯Ό λ€λ£° λ νΈλν½μ΄ μ μ μκ°λμ μλ² λ¦¬μμ€λ₯Ό μ¬μ©νκΈ° μν΄ μ¬μ©νλ€.    
(μ£Όλ‘ ETL:Extract-Transform-Load, λμ©λ λ°μ΄ν°λ₯Ό λ°μ΄ν° μ¨μ΄νμ°μ€μ μ μ₯.)

### π€ Spring Batch?

μλ° κΈ°λ° νμ€ λ°°μΉ κΈ°μ μ λΆμ¬λ‘ λ°°μΉ μ²λ¦¬μμ μκ΅¬νλ μ¬μ¬μ© κ°λ₯ν μλ° κΈ°λ° λ°°μΉ μν€νμ² νμ€μ νμμ±μ΄ λλλμκ³ ,   
Accentureμμ μμ νκ³  μλ λ°°μΉ μ²λ¦¬ μν€νμ² νλ μμ€λ₯΄λ₯Ό μ€νλ§ λ°°μΉ νλ‘μ νΈμ κΈ°μ¦νμλ€.

κ°λ³κ³  λ€μν κΈ°λ₯μ κ°μ§ λ°°μΉ νλ μμν¬λ‘, κ²¬κ³ ν λ°°μΉ μ΄νλ¦¬μΌμ΄μ κ°λ°μ΄ κ°λ₯νλλ‘ λμμΈ λμ΄μλ€.   
μ΅κ·Ό κΈ°μ μμ€ν μ΄μμ νμμ μ΄λΌκ³  ν  μ μλ€.

κΈ°μ‘΄ Spring νλ‘μ νΈμ λͺ¨λμ νμ©ν  μ μλ€λ μ₯μ μ κ°μ§λ€.(μλ‘μ΄ μΈμ΄λ‘ μ²λ¦¬λ₯Ό μλ‘ κ΅¬ννμ§ μμλ λλ€.)   
λ°°μΉ μ²λ¦¬λ₯Ό μν λ‘μ§μ μλ‘ λ§λ€μ§ μκ³  μ€νλ§ λ°°μΉμμ μ κ³΅νλ κΈ°λ₯μ μ¬μ©ν  μ μλ€.

- ### π λ°°μΉμ ν΅μ¬ ν¨ν΄
  - **Read**: DB, νμΌ, ν λ±μμ λ€λμ λ°μ΄ν°λ₯Ό μ½λλ€.
  - **Process**: λ°μ΄ν°λ₯Ό κ°κ³΅νλ€.
  - **Write**: κ°κ³΅λ λ°μ΄ν°λ₯Ό λ€μ μ μ₯νλ€.


- ### π λ°°μΉ μλλ¦¬μ€
  - λ°°μΉ νλ‘μΈμ€λ₯Ό μ£ΌκΈ°μ μΌλ‘ μ»€λ°νλ€.(ν¨μ¨μ μΈ μ»€λ° μ λ΅.)
  - λμ λ€λ°μ μΈ Job μ λ°°μΉ μ²λ¦¬, λ³λ ¬ μ²λ¦¬.
  - μ€ν¨ ν μ€μΌμ€λ§μ μν΄ μ¬μμλλ€.
  - μμ‘΄κ΄κ³κ° μλ stepλ€μ μμ°¨μ μΌλ‘ μ²λ¦¬νλ€.
  - μ‘°κ±΄μ λ°λΌ νλ¦μ κ΅¬μ±νλ λ± μ²΄κ³μ μ΄κ³  μ μ°ν λ°°μΉ λͺ¨λΈμ κ΅¬μ±νλ€.
  - λ°λ³΅νκ±°λ, μ¬μλ, Skip μ²λ¦¬(μ€μνμ§ μμ μμΈλ₯Ό μ€ν΅, κ³μ μ€νλ  μ μλλ‘)λ±..

<br>

# π Spring Batch μν€νμ³

![img_1.png](img/img_1.png)

- `JobLauncher`: Jobμ μ€νμν€λ μ»΄ν¬λνΈ
- `Job`: λ°°μΉ μμ.
- `JobRepository`: Jobμ μ€νκ³Ό Job, Stepμ μ μ₯.
- `Step`: λ°°μΉ μμμ λ¨κ³. ItemReader, ItemProcessor, ItemWriterλ λ°μ΄ν°λ₯Ό μ½κ³ , μ²λ¦¬νκ³ , μ°λ κ΅¬μ±μ νλμ© κ°μ§λ€.

![img_2.png](img/img_2.png)

- **Application**
  > λΉμ¦λμ€, μλΉμ€ λ‘μ§, Core, Infrastructureμ μ΄μ©νμ¬ λ°°μΉ κΈ°λ₯μ λ§λ λ€.     
  > κ°λ°μλ μλ¬΄ λ‘μ§μ κ΅¬νμλ§ μ§μ€νκ³  κ³΅ν΅μ μΈ κΈ°μ  κΈ°λ°μ νλ μ μν¬κ° λ΄λΉνλλ‘ νλ€.
- Core
  > λ°°μΉ μμμ μμνκ³  μ μ΄νλ νμ ν΄λμ€(Job, Step, JobLauncher, Flow)    
  > Jobμ μ€ννκ³  λͺ¨λν°λ§, κ΄λ¦¬νλ APIλ‘ κ΅¬μ±λμ΄ μλ€.
- Infrastructure
  > μΈλΆμ μνΈμμ©νλ λ μ΄μ΄, (ItemReader, ItemWriter, RetryTemplate, Skip)    
  > Application, Core λͺ¨λ κ³΅ν΅ Infrastructure μμμ λΉλνλ€. Job μ€νμ νλ¦κ³Ό μ²λ¦¬λ₯Ό μν νμ μ κ³΅νλ€.

  μ€μ λ‘ ν¨ν€μ§ κ΅¬μ‘°λ₯Ό μ΄μ΄ νμΈν΄ λ³Ό μ μλ€.

- ### π§ Job
  ![img_3.png](img/img_3.png)
  - μ μ²΄ λ°°μΉ νλ‘μΈμ€λ₯Ό μΊ‘μνν λλ©μΈμΌλ‘, Stepμ μμλ₯Ό μ μνλ€.
  - `JobParameters` λ₯Ό λ°λλ€.
  - JobParametersλ₯Ό λ°μ JobInstanceκ° μμ±λκ³ , JobExecutionμΌλ‘ λλμ΄μ Έ μ€νλλ€.

- ### π§ Step
  ![img_4.png](img/img_4.png)
  - μμμ μ²λ¦¬ λ¨μ.
  - Chunk | Tasklet κΈ°λ°μΌλ‘ νλμ νΈλμ­μμμ λ°μ΄ν°λ₯Ό μ²λ¦¬νλ€.
  - commitInterval λ§νΌ λ°μ΄ν°λ₯Ό μ½κ³ , λ°μ΄ν°λ₯Ό μ²λ¦¬ν λ€, ChunkSize λ§νΌ νλ²μ Write νλ€.

<br>

# π Meta Data Schema
  ![img_5.png](img/img_5.png)    

  μ€νλ§ λ°°μΉκ° μ€ν λ° κ΄λ¦¬λ₯Ό μν λͺ©μ μΌλ‘ μ¬λ¬ λλ©μΈ(Job, Step, Execution, Instance JobParams ...) μ μ λ³΄λ₯Ό μ μ₯ν  μ μλ μ€ν€λ§λ₯Ό μ κ³΅νλ€.    
  Job μ μ΄λ ₯(μ±κ³΅, μ€ν¨), νλΌλ―Έν° λ± μ€ν κ²°κ³Όλ₯Ό μ‘°νν  μ μλ€. -> λ¦¬μ€ν¬ λ°μμ λΉ λ₯Έ λμ² κ°λ₯.    

  DBμ μ°λν  κ²½μ° νμμ μΌλ‘ λ©ν νμ΄λΈμ΄ μμ±λμ΄μΌ νλ©° μ€ν€λ§ νμΌμ μμΉλ /org/springframework/batch/core/schema-*.sql μ΄λ€.(DB μ νλ³λ‘ μ κ³΅)

- ### π§ νμ΄λΈ
  - BATCH_JOB_INSTANCE
    >  Job μ΄ μ€νλ  λ JobInstance μ λ³΄κ° μ μ₯λλ©°, job_nameκ³Ό job_keyλ‘ νμ¬ νλμ λ°μ΄ν°κ° μ μ₯λλ€ (μΈμ€ν΄μ€λ μ μΌ)
      - version: μλ°μ΄νΈ λ§λ€ 1μ© μ¦κ°νλ κ°
      - job_name: jobμ κ΅¬μ±ν  λ λΆμ¬ν μ΄λ¦.
      - job_key: name κ³Ό parmasλ₯Ό ν©μ³ ν΄μ±ν κ°
  
  - BATCH_JOB_EXECUTION
    > Jobμ μ€ν μ λ³΄(μμ±, μμ, μ’λ£ μκ°, μ€ν μν, μ’λ£ μ½λ, μ€ν¨ μμΈ λ©μμ§, λ§μ§λ§ μ€ν μμ  λ±)
  - BATCH_JOB_EXECUTION_PARAMS
    > Jobκ³Ό ν¨κ» μ€νλλ JobParams μ λ³΄λ₯Ό μ μ₯.
      - type_cd : String, Long, Date λ±μ νμ μ λ³΄
      - key_name: νλΌλ―Έν° ν€ κ°.
      - string_val: νλΌλ―Έν° λ¬Έμ κ°
      - data_val: νλΌλ―Έν° λ μ§ κ°.
      - long_val
      - double_val
      - identifying: μλ³ μ¬λΆ (boolean)
  - BATCH_JOB_EXECUTION_Cμ ONTEXT
    > Jobμ μ€νλμ μ¬λ¬κ°μ§ μνμ λ³΄, κ³΅μ  λ°μ΄ν°λ₯Ό JSON νμμΌλ‘ μ§λ ¬ννμ¬ μ μ₯νλ€. Stepκ°μ κ³΅μ κ° κ°λ₯νλ€.
      - short_context: jobμ μ€ν μνμ λ³΄, κ³΅μ λ°μ΄ν° λ±μ μ λ³΄λ₯Ό **λ¬Έμμ΄**λ‘ μ μ₯
      - serialized_context: μ§λ ¬ν λ μ μ²΄ μ»¨νμ€νΈ
  - BATCH_STEP_EXECUTION
    > - Stepμ μ€ν μ λ³΄(μμ±, μμ, μ’λ£ μκ°, μ€ν μν, μ’λ£ μ½λ, μ€ν¨ μμΈ λ©μμ§, λ§μ§λ§ μ€ν μμ  λ±)
    > - λΆλͺ¨(Job)μ ID
    > - νΈλμ­μλΉ Commit, Read, Write, Filter, Read skip, Write skip, ProcessSkip, Rollback μ
  - BATCH_STEP_EXECUTION_CONTEXT
    > Jobμ κ²½μ°μ λμΌνμ§λ§, Step λ³λ‘ μ μ₯λλ©° Stepκ° κ³΅μ ν  μ μλ€. 
    
  νμ΄λΈκ°μ κ΄κ³(1:N) μ μ£Όμνμ¬ μ΄ν΄λ³΄μ.

- ### π§ μ€ν€λ§ μμ± μ€μ 
  - μλ μμ±: μΏΌλ¦¬ λ³΅μ¬ ν μ§μ  μμ±.
  - μλ μμ±: properties μμ spring.batch.jdbc.initialize-schema μ€μ .
    - ALWAYS
      > μ€ν¬λ¦½νΈ ν­μ μ€ν, RDBMS μ€μ μ΄ λμ΄μμ κ²½μ° λ΄μ₯ DBλ³΄λ€ μ°μ μ μΌλ‘ μ€ννλ€.
    - EMBEDDED
      > λ΄μ₯ DB μΌλλ§ μ€νλλ€. (κΈ°λ³Έκ°)
    - NEVER
      > - μ€ν¬λ¦½νΈλ₯Ό ν­μ μ€ννμ§ μλλ€. νμ΄λΈμ΄ μλ€κ±°λ λ΄μ₯ DB λΌλ©΄ μ€λ₯ λ°μ.
      > - μ΄μμμ μλμΌλ‘ μ€ν¬λ¦½νΈ μμ± ν μ€μ νλ κ²μ κΆμ₯νλ€.

 

<br>

# π Spring Bootλ‘ Spring Batch μμνκΈ°

- #### μμ‘΄μ± μΆκ°
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
  - μ€νλ§ λ°°μΉλ₯Ό μλμν€κΈ° μν΄ μ μΈνλ μ λΈνμ΄μμΌλ‘, μ΄ 4κ°μ μ€μ  ν΄λμ€λ₯Ό μ€νμν€λ©° μ€νλ§ λ°°μΉμ λͺ¨λ  μ΄κΈ°ν λ° μ€ν κ΅¬μ±μ΄ μ΄λ£¨μ΄μ§λ€.
  - μ€νλ§ λΆνΈ λ°°μΉμ μλμ€μ  ν΄λμ€κ° μ€νλμ΄ λ±λ‘λ λͺ¨λ  Jobμ κ²μνμ¬ μ΄κΈ°ννκ³  λμμ Job μ μννλλ‘ κ΅¬μ±νλ€.
  
  - ### π§ μ€νλ§ λ°°μΉ μ€μ  ν΄λμ€
    - ### BatchAutoConfiguration
      > μ€νλ§ λ°°μΉκ° μ΄κΈ°ν λ  λ μλμΌλ‘ μ€ν, Jobμ μννλ JobLauncherApplicationRunner λΉμ μμ±νλ€.(ApplicationRunnerλ₯Ό κ΅¬ννκΈ° λλ¬Έμ μ€νλ§μ΄ μ€νμν¨λ€.)
    
    - ### SimpleBatchConfiguration
      > - JobBuilderFactory μ StepBuilderFactoryλ₯Ό μμ±νλ€.    
      > - μ€νλ§ λ°°μΉμ μ£Όμ κ΅¬μ± μμλ₯Ό μμ±νλ€.(νλ‘μ κ°μ²΄λ‘ μμ±λλ€.) - jobRepository, jobLauncher, hobRegistry, jobExplorer
    
    - ### BatchConfigurerConfiguration
      - BasicBatchConfigurer
        > SimpleBatchConfiguration μμ μμ±ν νλ‘μ κ°μ²΄μ μ€μ  νκ²μ μμ±νλ μ€μ  ν΄λμ€.
      - JpaBatchConfigurer
        > JPA κ΄λ ¨ κ°μ²΄λ₯Ό μμ±νλ μ€μ  ν΄λμ€.
  
        
- ### π§ Tasklet λ°©μμ μ¬μ©ν κ°λ¨ν λ°°μΉ νλ‘κ·Έλ¨
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
  - λͺ¨λ  Jobκ³Ό Stepμ λΉμΌλ‘ λ±λ‘λμ΄μΌ νλ€.
  - **(#1)**: Job, Stepμ μμ±νλ λΉλ ν©νΉλ¦¬
  - **(#2)**: Job, Stepμ μ΄λ¦μ μ§μ ν΄μ€λ€.
  - **(#3)**: taskletμ κΈ°λ³Έμ μΌλ‘ λ¬΄νλ°λ³΅νλ€. λλ¬Έμ μ΄μ κ°μ κ°μ λ°ννμ¬ νλ² μ€ν ν μ’λ£ν  μ μλλ‘ νλ€.(λ°λ³΅ false)
  
  - κ²°κ³Ό
  ![img.png](img/img.png)
    

<br>

# π λλ©μΈμ μ΄ν΄

## π§ Job
Job Configurationμ μν΄ μμ±λλ κ°μ²΄ λ¨μλ‘, λ°°μΉ κ³μΈ΅ κ΅¬μ‘°μμ κ°μ₯ μμμ μλ κ°λμ΄λ©° νλμ λ°°μΉμμ μμ²΄μ ν΄λΉνλ€.(μ΅μμ μΈν°νμ΄μ€)    
λ°°μΉ μμμ μ΄λ»κ² κ΅¬μ±νκ³  μ€νν μ§λ₯Ό μ€μ νκ³  λͺμΈν΄ λμ κ°μ²΄λ‘ μ¬λ¬ stepμ ν¬ν¨νλ μ»¨νμ΄λ λ‘μμ μ­ν μ νλ€. (1κ° μ΄μμ Step)

- ### π κ΅¬νμ²΄ (AbstractJabμ κ΅¬ν)
  ```
  - name : Job μ΄λ¦
  - restartable: μ¬μμ μ¬λΆ κΈ°λ³Έκ° true
  - JobRepository: λ©νλ°μ΄λ μ μ₯μ
  - JobExecutionListener: Job μ΄λ²€νΈ λ¦¬μ€λ
  - JobParametersIncrementer: JobParameter μ¦κ°κΈ°
  - JobParametersValidator: JobParameter κ²μ¦κΈ°
  - SimpleStepHandler: Stepμ μ€ννλ νΈλ€λ¬.
  ```  

  - SimpleJob
    > - μμ°¨μ μΌλ‘ Stepμ μ€νμν€λ JobμΌλ‘, νμ€ κΈ°λ₯μ κ°μ§κ³  μλ€.(stepsλ₯Ό κ°μ§κ³  μμ)
  - FlowJob
    > - νΉμ  μ‘°κ±΄κ³Ό νλ¦μ λ°λΌ Stepμ κ΅¬μ±νλ JobμΌλ‘, Flow κ°μ²΄λ₯Ό μ€νμμΌ μμμ μ§ννλ€.
    
  JobLauncherμ run(job, jobParameters) λ©μλμμ jobμ λ°μ μ€νμν€κ² λλλ°, job.execute(execution)λ‘ stepμ νλνλ μ€νμν¨λ€.   
  κ΅¬νμ²΄μΈ SimpleJobLauncher μ½λλ₯Ό λ³΄λ©΄ jobRepositoryμμ ν΄λΉ μ‘μ λ§μ§λ§ Executionμ κ°μ Έμ μνλ₯Ό νμΈν ν μλ‘μ΄ JobExecutionμ μμ±νκ³ ,μμ±λ JobExecutionμΌλ‘ Jobμ μ€ννλ€.
  Jobμ execute(AbstractJob μ) μμλ κ΅¬νμ²΄μ doExecute()λ₯Ό νΈμΆνκ³ , ν΄λΉ λ©μλμμ handleStep(step, jobExecution)μ μ€νμν¨λ€. 
  
  handleStep μμλ λ§μ°¬κ°μ§λ‘ AbstractStep μ execute λ₯Ό νΈμΆνκ³ , κ΅¬νμ²΄μ doExecuteκ° νΈμΆλλ€.   

## π§ JobInstance
![img_3.png](img_3.png)

Jobμ΄ μ€νλ  λ μμ±λλ λΌλ¦¬μ  μ€ν λ¨μ κ°μ²΄λ‘ κ³ μ νκ² μλ³ κ°λ₯ν μμ μ€νμ λνλΈλ€.   
λ©νλ°μ΄ν°λ₯Ό λ°μ΄ν°λ² μ΄μ€(BATCH_JOB_INSTANCE)μ μ μ₯νκΈ° μν΄ μμ±λλ μΈμ€ν΄μ€μ΄λ€.

μ²μ μμνλ Job + JobParameterμ κ΅¬μ±μΌ κ²½μ° μλ‘μ΄ JobInstanceλ₯Ό μμ±νκ³ , μ΄μ κ³Ό λμΌν κ΅¬μ±μ΄λΌλ©΄ μ΄λ―Έ μ‘΄μ¬νλ JobInstanceλ₯Ό λ¦¬ν΄νλ€.   
(λμΌν κ΅¬μ±μΌλ‘ μ€νν  μ μμ΄ μμΈκ° λ°μνκ³  Jobμ μ€νμ μ€λ¨νλ€ ) `A job instance already exists and is complete for parameters={ ... }`    
μ€νλ νλΌλ―Έν°λ BATCH_JOB_EXECUTION_PARAMSμμ νμΈν  μ μμΌλ©° λ΄λΆμ μΌλ‘λ job_name + params_key μ ν΄μκ°μ κ°μ§κ³  μΈμ€ν΄μ€ κ°μ²΄λ₯Ό μλ³νλ€. 

## π§ JobParameter
Jobμ μ€νν  λ ν¨κ» μ¬μ©λλ νλΌλ―Έν°λ₯Ό κ°μ§ λλ©μΈ κ°μ²΄λ‘, νλμ JobInstanceλ₯Ό κ΅¬λΆνκΈ° μν μ©λλ‘ μ¬μ©λλ€.
- JobParameters: `LinkedHashMap<String, Parameter>`λ₯Ό λ©€λ²λ³μλ‘ κ°μ§λ Wrapper ν΄λμ€.
- JobParameter: `Object parameter`, `ParameterType parameterType`, `boolean identifying`
- ParameterType: `String`, `Date`, `Long`, `Double`

- #### JobParameterμ μμ±κ³Ό λ°μΈλ©
  - μ΄νλ¦¬μΌμ΄μ μ€νμ μ΅μμΌλ‘ μ£Όμ.
    - `Java -jar batch.jar name=user1 seq(long)=2L date(date)=2022/03/28 weight(double)=70.5`
  - μ½λμμ μμ±
    - `JobParameterBuilder`, `DefaultJobParametersConverter`
      ```java
      JobParameters jobParameters = new JobParametersBuilder()
      .addString("name", "kim2")
      .addLong("seq", 1L)
      .addDate("data", new Date())
      .addDouble("weight", 70.5)
      .toJobParameters();
      ```
  - SpEL μ΄μ©
    - @Value("#{jobParameter[requestDate]}")
  
- #### JobParameter κΊΌλ΄κΈ°
    ```java
    // StepContributionμμ κΊΌλ΄κΈ°
    JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
    jobParameters.getParameters() // Map<String, parameter>
    jobParameters.getString("key");
    jobParameters.getDate("key");
    jobParameters.getLong("key");
    jobParameters.getDouble("key");
        
    // ChunkContext μμ κΊΌλ΄κΈ°
    Map<String, Object> chunkJobParameters = chunkContext.getStepContext().getJobParameters();
    ```
  

## π§ JobExecution
JobInstanceμ λν νλ²μ μλλ₯Ό μλ―Ένλ κ°μ²΄λ‘, μ€ν μ€μ λ°μν μ λ³΄λ€μ μ μ₯νκ³  μλ κ°μ²΄μ΄λ€.
  - μμ μκ°, μ’λ£μκ°, μν(μμ?, μλ£?, μ€ν¨?), μ’λ£μν

JobExecutionμ μ€ν κ²°κ³Όκ° `COMPLETED` μ΄λ©΄ μΈμ€ν΄μ€μ μ€νμ΄ μλ£λ κ²μΌλ‘ κ°μ£Όν΄μ μ¬ μ€νν  μ μλ€.    
`FAILED`λΌλ©΄, μ€νμ΄ μλ£λμ§ μμ κ²μ΄λ―λ‘ μ¬μ€νμ΄ κ°λ₯νλ€.(JobParameterκ° κ°λλΌλ) μ¦, μ€ν κ²½κ³Όκ° `COMPLETED`κ° λ  λκΉμ§ μ€νμ΄ κ°λ₯νλ€.    
(ν Instance λ΄μμ μ¬λ¬λ²μ μλκ° λ°μν  μ μμ, JobInstanceμ N:1)

![img_2.png](img_2.png)

λμΌν Job Instanceμ λν΄ μ±κ³»ν  λκΉμ§ Executionμ΄ μμ±λ¨μ νμΈν  μ μλ€.
  
  
## π§ Step
Batch Jobμ κ΅¬μ±νλ λλ¦½μ μΈ νλμ λ¨κ³λ‘, μ€μ  λ°°μΉλ΄ μ²λ¦¬νλ λͺ¨λ  μ λ³΄λ₯Ό κ°μ§κ³  μλ λλ©μΈ κ°μ²΄μ΄λ€.      
λ°°μΉμμμ μ΄λ»κ² κ΅¬μ±νκ³  μ€νν  κ²μΈμ§ μΈλΆμμμ Task κΈ°λ°μΌλ‘ μ€μ νκ³  λͺμΈν΄ λμ κ°μ²΄.

- ### π νλ
  - name
  - startLimit: μ€ν μ ν νμ.
  - allowStartIfComplete: μλ£ ν μ¬μ€ν κ°λ₯μ¬λΆ.
  - stepExecutionListener: μ΄λ²€νΈ λ¦¬μ€λ.
  - jobRepository: λ©νλ°μ΄ν° μ μ₯.

- ### π κ΅¬νμ²΄
  - TaskletStep: κ°μ₯ κΈ°λ³Έμ μΈ κ΅¬νμ²΄, Taklet νμμ κ΅¬νμ²΄λ₯Ό μ μ΄νλ€.
  - PartitionStep: λ©ν° μ€λ λ λ°©μμΌλ‘ μ€νμ μ¬λ¬κ°λ‘ λΆλ¦¬ μ€ννλ€.
  - JobStep: Step λ΄μμ Jobμ μ€ννλ€.( Job -> Step -> Job .. )
  - FlowStep: Step λ΄μμ Flowλ₯Ό μ€ννλλ‘ νλ€.
  
Stepμ μ€νμν€λ execute(StepExecution)κ° μκ³ , StepExecutionμλ μ€ν κ²°κ³Όμ μνκ° μ μ₯λλ€.    

- ### π API
  - Tasklet μ§μ  μμ±
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

## π§ StepExecution
![img_1.png](img_1.png)
- Stepμ λν νλ²μ μλλ₯Ό μλ―Ένλ κ°μ²΄λ‘ μ€νμ€ λ°μν μ λ³΄λ€μ μ μ₯νκ³  μλ κ°μ²΄. (μμ,μ’λ£ μκ°, μν, commit count, rollback count ...)    
- Jobμ΄ μ¬μμ λλλΌλ μ΄λ―Έ μ±κ³΅μ μΌλ‘ μλ£λ Stepμ skip νκ³ , μ€ν¨νλ Stepλ§ μ€νλλ€.(allowStartIfComplete λ‘ μ€μ  κ°λ₯.)   
- λͺ¨λ  StepExecutionμ΄ μ±κ³΅ν΄μΌ JobExecutionλ μ±κ³΅μΌλ‘ λλλ€.

## π§ StepContribution
- μ²­ν¬ νλ‘μΈμ€μ λ³κ²½ μ¬ν­μ μ μ₯ν΄λλ€κ° StepExecutionμ μνλ₯Ό μλ°μ΄νΈ νλ λλ©μΈ κ°μ²΄μ΄λ€.
- μ²­ν¬ μ»€λ° μ§μ μ StepExecutionμ apply()λ₯Ό νΈμΆνμ¬ μνλ₯Ό μλ°μ΄νΈ νλ€.
- μ¬μ©μ μ μ ExitStatusλ₯Ό μ§μ ν  μ μλ€.

- ### π νλ
  - stepExecution
  - read, write, filter(ItemProcessorμ μν΄ νν°λ§λ) count
  - parent(StepExecution), read, write, process SkipCount
  - ExitStatus
  
  
  TaskletStep -> StepExecution -> StepContribution μμΌλ‘ μμ±λκ³ ,   
  chunkOrientedTaskletκ³Ό κ°μ κ΅¬νμ²΄μμ μ€νλ ItemReader, Processor, Writer μ μνλ€μ΄ StepContributionμ μ μ₯λλ€.    
  κ·Έλ¦¬κ³ , μ΅μ’μ μΌλ‘ μ»€λ°λκΈ° μ μ StepExecutionμ μ μ₯ν΄λλ μνλ₯Ό μλ°μ΄νΈ νλ€.


## π§ ExecutionContext
Step, Job Execution κ°μ²΄μ μνλ₯Ό μ μ₯νλ κ³΅μ  κ°μ²΄λ‘ key:value μμΌλ‘ λ μ»¬λ μμ΄λ©° DBμ μ§λ ¬ν ν κ°μΌλ‘ μ μ₯λκ² λλ€.

- StepExecution μ κ°μ Step κ° κ³΅μ  λΆκ°λ₯.
- JobExecution μ κ°μ Job κ° κ³΅μ λ μλμ§λ§, Jobμ Stepκ° κ³΅μ λ κ°λ₯νλ€.(νμν μ λ³΄λ₯Ό μ μ₯ν΄λλ€ κΊΌλ΄μ°κΈ°μ μ μ©ν  κ² κ°λ€)
  > Job μ¬μμμ μ΄λ―Έ μ²λ¦¬ν λ°μ΄ν°λ₯Ό Skipνκ³  μνν  λ ν΄λΉ μν μ λ³΄λ₯Ό νμ©νλ€. 

- ExecutionContext κ°μ Έμ€κΈ°.
  ```java
  ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
  ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
  ```
  ChunkContext, Contribution κ°μ²΄ λλ€μμ κ°μ Έμ€λ κ²μ΄ κ°λ₯νλ€.    
  get, put λ©μλλ ExecutionContextμ Map<String,Object> μμ κ°μ λ£κ³ , κ°μ Έμ€λ λ©μλμ΄λ€. μ»€λ° μμ μ DBμ λ°μ΄ν°λ₯Ό μ μ₯νλ€.    
  JobInstance κ° λμΌνκ³ , μ΄μ  μ€νμ΄ COMPLETED μνκ° μλλΌλ©΄ μ΄μ κΉμ§μ ExecutionContextμ μ μ₯λ κ°μ λΆλ¬μ¨ ν, λλ¨Έμ§ Stepμ λ€μ μ€ννλ€.

- getJob(Step)ExecutionContext?
  ```java
  Map<String, Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
  Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
  ```
μκΈ°μ getJobExecutionContext, getStepExecutionContextλ ExecutionContextλ₯Ό κ°μ Έμ€λ κ²μ΄ μλ μ μ₯λμ΄ μλ κ°μ λ³΅μ¬ν΄ λλ €μ£Όλ λ©μλμ΄λ€.   
μ€μ λ‘ λ©μλλ₯Ό μ΄ν΄λ³΄μμ λ Mapμ λ§λ€μ΄ λ΄μ©μ λ³΅μ¬νκ³  μ΄λ₯Ό unmodifiableMap μΌλ‘ λλ €μ€μ νμΈν  μ μμλ€.


## π§ JobRepository
λ°°μΉ μμ μ€μ μ λ³΄λ₯Ό μ μ₯νλ μ μ₯μλ‘, λ°°μΉ μμμ μνκ³Ό κ΄λ ¨λ λͺ¨λ  λ©νλ°μ΄ν°λ₯Ό μ μ₯νλ€.   
JobLauncher, Job, Step κ΅¬νμ²΄ λ΄λΆμμ CRUD κΈ°λ₯μ μ²λ¦¬νλ€.   

- ### π μ£Όμ λ©μλ
  - isJobInstanceExist(jobName, jobParameters)
  - createJobExecution(jobName, jobParameters)
  - getLastJobExecution(jobName, jobParameters)
  - getLastStepExecution(jobInstance, stepName)
  - update(jobExecution): Jobμ μ€ν μ λ³΄ μλ°μ΄νΈ
  - update(stepExecution)
  - add(stepExecution): μ€ν μ€μΈ Stepμ μλ‘μ΄ stepExecution μ μ₯.
  - updateExecutionContext(jobExecution)
  - updateExecutionContext(stepExecution)
  
@EnableBatchProcessing μ λΈνμ΄μμ μ μΈνλ©΄ JobRepositoryκ° μλμΌλ‘ λΉμΌλ‘ λ±λ‘λλ€.    
BatchConfigurer μΈν°νμ΄μ€λ κ΅¬νμ΄λ€ BasicBatchConfigurerλ₯Ό μμνμ¬ jobRepositoryλ₯Ό μ»€μ€ν νλ κ²μ΄ κ°λ₯νλ€.

- ### JDBC
  JDBC λ°©μμΌλ‘ μ€μ νκΈ° μν΄μλ `JobRepositoryFactoryBean`μ μ¬μ©νλλ°, AOP λ°©μμΌλ‘ νΈλμ­μ μ²λ¦¬κ° μ΄λ£¨μ΄μ§λ€. κ²©λ¦¬ λ λ²¨μ κΈ°λ³Έμ μΌλ‘`SERIALIZEBLE`μ΄κ³ , λ€λ₯Έ λ λ²¨λ‘ λ³κ²½ κ°λ₯νλ€.      
  νμ΄λΈμ κΈ°λ³Έ prefixλ "BATCH_"μ΄λ©° λ³κ²½ κ°λ₯νλ€.
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
          factoryBean.setDataSource(dataSource); // μ€μ νμ§ μμλ κΈ°λ³Έμ μΌλ‘ μ€μ  λ¨.
          factoryBean.setTransactionManager(getTransactionManager()); // BasicBatchConfigurerμ μλ λ©μλ
          factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
          factoryBean.setTablePrefix("LOG_BATCH");
  
          return factoryBean.getObject();
      }
  }
  ```

- ### In Memory
  DBμ μ μ₯κΉμ§λ νμκ° μλ€λ©΄ `MapJobRepositoryFactoryBean`μ μ¬μ©νμ¬ μΈλ©λͺ¨λ¦¬λ‘ μ¬μ©ν  μλ μλ€.

- ### JobRepository μμ κ° μ‘°ν
  ```java
  JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
  if(lastJobExecution != null) {
      lastJobExecution.getStepExecutions()
          .forEach(s -> System.out.println(s.getExitStatus()));
  }
  ```
  
## π§ JobLauncher
Jobκ³Ό νλΌλ―Έν°λ₯Ό μΈμλ‘ λ°μΌλ©° λ°°μΉ μμμ μ€νμν¨ ν ν΄λΌμ΄μΈνΈμκ² JobExecutionμ λ°ννλ€.   
μ€νλ§ λΆνΈ λ°°μΉκ° κ΅¬λλλ©΄ μλμΌλ‘ λΉμ΄ μμ±λκΈ° λλ¬Έμ λ°λ‘ λ§λ€μ΄μ£Όμ§ μμλ λλ€.

ApplicationRunnerλ₯Ό κ΅¬νν JobLauncherApplicationRunnerκ° JobLauncherλ₯Ό μλμΌλ‘ μ€νμν€κ² λλ€.    
λκΈ°μ (SyncTaskExecutor), λΉλκΈ°μ (SimpleAsyncExecutor) μ€νμ΄ κ°λ₯νλ©° κΈ°λ³Έκ°μ λκΈ°μ  μ€νμ΄λ€.       
λ λ°©μμ μ°¨μ΄λ μΈμ  JobExecutionμ ν΄λΌμ΄μΈνΈμκ² λ°ννλλμ΄λ€. λκΈ°μ  λ°©μμ λ°°μΉ μ²λ¦¬κ° μ΅μ’μ μΌλ‘ μλ£λλ©΄ ν΄λΌμ΄μΈνΈμκ² λ°ννμ§λ§,      
λΉλκΈ°μ  μ€νμμλ JobExecutionμ νλνλ©΄ λ°λ‘ ν΄λΌμ΄μΈνΈμκ² λ°ννλ€.(ExitStatus.UNKNOWN)   
λκΈ°μ  μ€νμ μ€μΌμ€λ¬μ μν λ°°μΉμ²λ¦¬μ κ°μ΄ λ°°μΉμ²λ¦¬μκ°μ΄ κΈΈμ΄λ μκ΄ μλ κ²½μ°μ μ ν©νκ³ , λΉ λκΈ°μ  μ€νμ HTTPμμ²­μ μν λ°°μΉ μ²λ¦¬μ μ ν©νλ€.


- λΉ λκΈ°μ  μ€ν
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
setTaskExecutorλ JobLauncherμ λ©μλκ° μλ SimpleJobLauncherμ λ©μλμ΄κΈ° λλ¬Έμ λΉμΌλ‘ μ£Όμλ°μ μ¬μ©ν  μ μλ€.   
JobLauncher μΈν°νμ΄μ€λ‘ μ£Όμ λ°λλΌλ νλ‘μ κ°μ²΄μ΄κΈ° λλ¬Έμ SimpleJobLauncher λ‘μ κ°μ  νλ³ν λν λΆκ°λ₯νλ€.    
λλ¬Έμ BasicBatchConfigurerμμ νλ‘μκ° μλ μ€μ  κ°μ²΄λ₯Ό κ°μ Έμ νμ μΊμ€νμ ν΄μ€λ€.

<br>

# π λ°°μΉ μ€μ 

### π JobLauncherApplicationRunner
  - ApplicationRunnerμ κ΅¬νμ²΄λ‘ BatchAutoConfifurationμμ μμ±λλ€.
  - κΈ°λ³Έμ μΌλ‘ λΉμΌλ‘ λ±λ‘λ λͺ¨λ  jobμ μ€νμν¨λ€.(νΉμ  jobλ§ μ€ννλλ‘ μ€μ λ κ°λ₯.)

### π BatchProperties
  - νκ²½ μ€μ  ν΄λμ€λ‘ job μ΄λ¦, μ€ν€λ§ μ΄κΈ°ν μ€μ , νμ΄λΈ prefix λ± μ€μ  κ°λ₯.
  - properties | yml νμΌμ μ€μ  κ°λ₯νλ€.
    - `batch.job.names`, `batch.initialize-schema: never | always | embedded`, `batch.tablePrefix: ` ... 

### π Job μ€ν μ΅μ
  - `Spring.batch.jhob.names: ${job.name:NONE}` μ§μ ν Jobλ§ μ€ννλλ‘ νλ€.
    - NONEλ μμμ λ¬Έμ.
    - `--job.name=name1, name2`
    - propertiesλ₯Ό μ€μ ν΄λκ³  μ΅μμ μ£Όμ§ μμΌλ©΄ μλ¬΄ Jobλ μ€νλμ§ μλλ€.
<br><br>

<br>

# π Jobμ μ€ν

## π§ JobBuilderFactory
Jobμ μ½κ² μμ±νκ³  μ€μ ν  μ μλλ‘ util μ±κ²©μ λΉλ ν΄λμ€μΈ `JobBuilderFactory`λ₯Ό μ κ³΅νλ€.    
JobBuilderFactory μμλ JobBuilder(SimpleJobBuilder, FlowBuilder)λ₯Ό μμ±νμ¬ Jobμ μμ±μ μμνλ€.    

![img_5.png](img_5.png)
- #### SimpleJobμ μμ±    
  JobBuilderFactoryλ₯Ό ν΅ν΄μ JobBuilderλ₯Ό μμ±νκ³  `start(step)` λ©μλλ₯Ό νΈμΆνλ©΄ SimpleJobBuilderκ° μμ±λκ³  μ΅μ’μ μΌλ‘ `SimpleJob`μ΄ μμ±λλ€.    

- #### FlowJob, Flowμ μμ±
  JobBuilderμμ `start(flow)` λλ `flow(step)`μ μ€ννλ©΄ FlowJobBuilderλ₯Ό μμ±νκ³ , μ΅μ’μ μΌλ‘ `FlowJob`μ΄ μμ±λλ€.    
  FlowJobBuilder μμλ λ λ΄λΆμ μΌλ‘ JobFlowBuilder -> FlowBuilderλ₯Ό μμ±νκ³ , μ¬κΈ°μ `Flow`λ₯Ό μμ±νκ² λλ€.

```java
public JobBuilder get(String name) {
    JobBuilder builder = new JobBuilder(name).repository(jobRepository);
    return builder;
}
```
SimpleJobBuilderμ FlowJobBuilderλ JobBuilderHelper ν΄λμ€λ₯Ό μμνλ©°, ν΄λΉ ν΄λμ€λ€μμ λ§λ€μ΄μ§ Jobλ€μ SimpleJobRepositoryκ° μ λ¬λμ΄
CRUDλ₯Ό ν΅ν΄ λ©νμ λ³΄λ€μ κΈ°λ‘νκ² λλ€.


## π§ SimpleJob API
- ### .start(), next()
  - start() μμ μ²μ μ€νν  stepμ μ€μ νκ³  SImpleJobBuilderλ₯Ό μμ±, λ°ννλ€, ν next() μμλ μμ°¨μ μΌλ‘ μ€νν  stepμ λ±λ‘νλ€.
- ### .incrementer(JobParametersIncrementer): νλΌλ―Έν°μ κ°μ μλμΌλ‘ μ¦κ°ν΄μ£Όλ μ€μ .
  - JobParametersμ κ°μ μ¦κ°μμΌ λ€μμ μ¬μ©λ  κ° κ°μ²΄λ₯Ό λ°ννλ€.
      - Long κ°μ λ£μ΄μ£Όκ³ , μ€νν λ λ§λ€ ν΄λΉ κ°μ μ¦κ°μν¨λ€.
      - κΈ°μ‘΄μ νλΌλ―Έν° κ°μλ λ³νκ° μμ§λ§ λ£μ΄μ€ Long κ°μ΄ λ³νκΈ° λλ¬Έμ μ¬λ¬λ² μ€ν κ°λ₯νλ€.
  - RunIdIncrementer κ΅¬νμ²΄λ₯Ό μ§μνλ©°, νμνλ€λ©΄ μΈν°νμ΄μ€λ₯Ό μ§μ  κ΅¬ννμ¬ μ μν  μ μλ€.
    ```java
    @Override
    public JobParameters getNext(JobParameters parameters) {
        String date = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date());
        return new JobParametersBuilder(parameters).addString("run.date", date).toJobParameters();
    }
    ```
    - π‘ incrementer.getNext()κ° μ μ©λ ν ApplicationRunnerλ€μ μ€ννκΈ° λλ¬Έμ Runner ν΄λμ€μμ jobParametersλ₯Ό λ£μ΄μ€λ€λ©΄   
      incrementerμ΄ μ μ©λμ§ μμ μ μλ€.(λ?μ΄μμ μ§)
      
- ### .preventRestart(): Jobμ μ¬μμ κ°λ₯ μ¬λΆ μ€μ .
  - restartable μ default κ°μ true, .preventRestart() νλ©΄ falseλ‘ λ³κ²½λ¨.
  - ν΄λΉ μ΅μμ falseλ‘ μ£Όκ² λλ©΄ jobμ μ€νμ΄ μ€ν¨ν΄λ μ¬μμμ΄ λΆκ°λ₯νλ€. (JobRestartException λ°μ)
  - SimpleJobLaunchμμ lastJobExecutionμ κ°μ Έμ¨ λ€ μ‘°κ±΄μ νμΈνλ€.
- ### .validator(jobParameterValidator): νλΌλ―Έν° κ΅¬μ± κ²μ¦.
  - DefaultJobParametersValidator κ΅¬νμ²΄λ₯Ό μ§μνλ€.
    - `public DefaultJobParametersValidator(String[] requiredKeys, String[] optionalKeys)`
    - νμν€κ° μκ±°λ, νμν€, μ΅μν€ λλ€μ μλ νλΌλ―Έν°κ° λ€μ΄μ€λ©΄ μμΈκ° λ°μνλ€.
  - μ»€μ€νν μ μ½ μ‘°κ±΄μ μμ±νκ³  μΆλ€λ©΄ μΈν°νμ΄μ€λ₯Ό μ§μ  κ΅¬νν  μλ μλ€.
    ```java
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
      if(parameters.getString("name") == null) {
          throw  new JobParametersInvalidException("name parameters is null");
      }
    }
    ```
- #### .listener(JobExecutionListener): Jobμ μ€ν μ , νμ μ½λ°±μ μ€μ .
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

## π§ SimpleJob μν€νμ²
![img.png](img.png)
1. JobLauncher μμ Job, JobParameterλ₯Ό κ°μ§κ³  JobInstanceλ₯Ό μμ±
2. JobExecutionμ μμ±νκ³ , ExecutionContext ν λΉ.
3. JobExecutionListener.beforeJob()
4. κ° Stepμ΄ μ€νλλ©° StepExecution,ExecutionContext μμ±
5. StepExecutionμ μ΅μ’ μν μλ°μ΄νΈ.
6. JobListener.afterJob() νΈμΆ
7. JobExecutionμ μ΅μ’ μν μλ°μ΄νΈ.(Status, ExitStatus)
8. JobLauncherμ λ°ν.

<br>

# π Stepμ μ€ν

## π§ StepBuilderFactory
StepBuilderλ₯Ό μμ±νλ ν©ν λ¦¬ ν΄λμ€. κ΅¬μ‘°λ JobBuilderFactoryμ μ μ¬νλ€.

- ### π StepBuilder κ΅¬νμ²΄
  - #### TaskletStepBuilder
    - API: tasklet(tasklet())
  - #### SimpleStepBuilder
    - TaskletStepBuilderμ λ§μ°¬κ°μ§λ‘ TaskletStepμ μμ±νμ§λ§, λ΄λΆμ μΌλ‘ μ²­ν¬κΈ°λ°μ μμμ μ²λ¦¬νλ ChunkOrientedTaskletμ μμ±νλ€.
    - API: chunk(chunkSize) | chunk(completionPolicy)
  - #### PartitionStepBuilder
    - PartitionStepμ μμ±νλ©° λ©ν° μ€λ λ λ°©μμΌλ‘ Jobμ μ€ννλ€.
    - API: partitioner(stepName, partitioner) | partitioner(step) 
  - #### JobStepBuilder
    - JobStepμ μμ±νκ³ , Stepμμμ Jobμ μ€ννλ€.
    - API: job(job)
  - #### FlowStepBuilder
    - FlowStepμ μμ±νκ³ , Stepμμμ Flowλ₯Ό μ€ννλ€.
    - API: flow(flow)

  TaskletStepBuilderμ SimpleStepBuilderλ StepBuilderHelperλ₯Ό μμλ°μ AbstractTaskletStepBuilderλ₯Ό μμλ°κ³ ,   
  λλ¨Έμ§ λΉλλ€μ StepBuilderHelperλ₯Ό μ§μ  μμ λ°λλ€.
  
  
## π§ TaskletStep
Taskletμ μ€νλ§ λ°°μΉμμ μ κ³΅νλ Stepμ κ΅¬νμ²΄λ‘ Taskletμ μ€νμν¨λ€.    
Task κΈ°λ°κ³Ό Chunk κΈ°λ°μ΄ μμΌλ©°, RepeatTEmplateλ₯Ό μ¬μ©νμ¬ Tasklet κ΅¬λ¬Έμ νΈλμ­μ λ΄μμ λ°λ³΅ μ€ννλ€.


- #### Task κΈ°λ°
  - λ¨μΌ μμμΌλ‘ μ²λ¦¬λλ κ²μ΄ λ λμ κ²½μ° μ¬μ©νλ€.
  - Tasklet κ΅¬νμ²΄λ₯Ό μμ±νμ¬ μ¬μ©νλ€.
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

- #### chunk κΈ°λ°
  - nκ°μ μ‘°κ°μΌλ‘ λλμ΄ μ€ννλ€, λλ μ²λ¦¬μ ν¨κ³Όμ μΌλ‘ λμ²ν  μ μλλ‘ μ€κ³ λμλ€.
  - ChunkOrientedTasklet κ΅¬νμ²΄κ° μ κ³΅λλ©°, ItemReader, ItemProcessor, ItemWriterμ μ¬μ©νλ€.
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

- ### π API
  - #### .tasklet(Tasklet), chunk(int size)
    - λ°λ³΅μ μΌλ‘ μνλλ Tasklet νμμ ν΄λμ€λ₯Ό μ€μ νλ€.
    - λ°ν κ°μ λ°λΌ λ°λ³΅ μ¬λΆλ λ³κ²½ κ°λ₯, `RepeatStatus.FINISHED`, `RepeatStatus.CONTINUABLE`
    - νκ°λ§ μ€μ μ΄ κ°λ₯νκ³ , μ¬λ¬κ° μ€μ μ λ§μ§λ§ μ€μ λ§ μ€νλλ€.
    - μ΅λͺ λλ Taskletμ κ΅¬ννλ€.
    - execute()λ StepContribution, ChunkContextλ₯Ό μΈμλ‘ λ°λλ€.
  - #### .startLimit(int)
    - Stepμ μ΅λ μ€ν νμ μ€μ , κΈ°λ³Έκ°μ INTEGER.MAX_VALUE, μ΄κ³Όνλ©΄ μμΈκ° λ°μνλ€.
    - Step λ§λ€ κ°λ³λ‘ μ€μ νλ€.
    - λμΌν JobInstanceμμ μ€νλλ λμΌν Stepμ λν μ€ν νμ μ νμ΄λ€. 
  - #### .allowStartIfComplete(true)
    - Jobμ μ¬μμν  λ Stepμ μ΄μ  μ€νμ μ±κ³΅ μ¬λΆμ μκ΄ μμ΄ ν­μ Stepμ μ€ννκΈ° μν μ€μ μ΄λ€.
    - Step 1~4μ€μ 1,2κΉμ§ μ±κ³΅νκ³  μ€ν¨νμ¬ Jobμ μ¬μ€νν  μ μμ λ κΈ°λ³Έμ μΌλ‘λ 3λΆν° λ€μ μμνλ€.    
      νμ§λ§ 1,2μ μμμ΄ λ¬΄μ‘°κ±΄ μ νλμ΄μΌ νλ FlowλΌλ©΄, ν΄λΉ μ΅μμ νμ±ννμ¬ 1λΆν° μμνλλ‘ λ³κ²½ν  μ μλ€.
  - #### listener(StepExecutionListener)
    - Stepμ μ€ν μ  νμ μ½λ°±.
  

## π§ TaskletStep μν€νμ³
![img_8.png](img_8.png)

1. ExecutionContextλ₯Ό κ°μ§λ StepExecutionμ΄ μμ±λλ€.
2. TaskletStepμμ StepExecutionμ λ°μ Stepμ μ€νμν¨λ€.
3. StepExecutionListener.beforeStep()μ νΈμΆνλ€.
4. RepeatTemplate μμ Taskletμ λ°λ³΅ μ€ννλ€.    
   loop μμλ RepeatStatusλ₯Ό νμΈνμ¬ FINISHED λΌλ©΄ λ£¨νλ₯Ό λΉ μ Έλμ€κ³  CONTINUABLE μ΄λΌλ©΄ λ€μ RepeatTemplateμμ Taskletμ λ°λ³΅μν¨λ€.
5. StepExecutionμ Statusλ₯Ό μλ°μ΄νΈ νλ€.
6. StepExecutionListener.afterStep()μ νΈμΆνλ€.
7. StepExecutionμ ExitStatusλ₯Ό μλ°μ΄νΈ νλ€.

## π§ JobStep
λ λ€λ₯Έ Jobμ μ€νμν€λ StepμΌλ‘, μμ€νμ μμ λͺ¨λλ‘ μͺΌκ° Jobμ νλ¦μ λλκ³ μ ν  λ μ¬μ©νλ€.

- ### π API
  - #### .job(Job)
    - μ€νν  Jobμ μ€μ νλ€.  
    - μΆκ°ν Jobλ Bean μΌλ‘ λ±λ‘νλ©΄ μλμΌλ‘ μ€νλμ΄ 2λ² μ€νλ  μ μμΌλ―λ‘ μ€μ μ΄ νμνλ€. 
  - #### .launcher(JobLauncher)
    - Jobμ μ€νν  JobLauncherλ₯Ό μ€μ νλ€.
    - nullμ λκ²¨ μ£Όλ©΄ SimpleJobLauncherλ‘ μ€ννλ€.
      ```java
      // JobBuilder
      if (jobLauncher == null){
          SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
          ...
      }
      ```
  - #### .parametersExtractor(JobParametersExtractor)
    - Stepμ ExecutionContextμμ κ°μ μΆμΆν΄ JobParametersλ‘ λ³ννλ€.
    - μ κ³΅λλ DefaultJobParametersExtractorλ `JobParametersExtractor`λ₯Ό κ΅¬νν κ΅¬νμ²΄λ₯Ό μ¬μ©νλ€.
    - λΆλͺ¨μ JobParameterλ€μ κΈ°λ³Έμ μΌλ‘ μΆκ°λκ³ , setKeys() λ₯Ό μ΄μ©ν΄ StepExecutionμ ExecutionContextμμ κ°μ μ°Ύμ νλΌλ―Έν°μ μΆκ°ν  μ μλ€.
      ```java
      // ν΄λΉ Stepμ ExecutionContextμ κ° μΆκ°.
      .listener(new StepExecutionListener() {
          @Override
          public void beforeStep(StepExecution stepExecution) {
              stepExecution.getExecutionContext().put("date", new Date());
          }
        
          @Override
          public ExitStatus afterStep(StepExecution stepExecution) {
              return null;
          }
      })
      ```  
      ```java
      private JobParametersExtractor jobParametersExtractor() {
          DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
          extractor.setKeys(new String[] {"date"});
  
          return extractor;
      }
      ```
      ![img_9.png](img_9.png)
      
      λΆλͺ¨ Job(7), jobStepμ Job(8)

<br>

# π Flow

## π§ FlowJob
Stepμ μμ°¨μ  μ€νμ΄ μλλΌ μνμ λ°λΌ νλ¦μ μ ννλλ‘ κ΅¬μ±ν  μ μλ€.    
 
  - Stepμ΄ μ€ν¨νλλΌλ Jobμ μ€ν¨νμ§ μκ² ν  μ μλ€.
  - λ€μμ μ€νν  stepμ κ΅¬λΆνμ¬ μ€νν  μ μλ€.
    - ex) μ±κ³΅μ Step2, μ€ν¨μ Step3 ...
  - νΉμ  Stepμ μ€νλμ§ μκ² κ΅¬μ±ν  μ μλ€.

`JobBuilderFactory` βΆ `JobBuilder` βΆ `JobFlowBuilder` βΆ `FlowBuilder` βΆ `FlowJob`

- ### π API
  
  - #### .start(Step), .next(Step)
  - #### .from(Step)
    - μ΄μ μ μ μν Stepμ flowλ₯Ό μΆκ°μ μΌλ‘ μ μνλ€.(Transitionμ μλ‘­κ² μ μ.)
    
  - #### .on(String pattern)
    - Stepμ ExitStatusλ₯Ό μΊμΉνκ³  ν¨ν΄κ³Ό λ§€μΉ­λλ©΄, `TransitionBuilder` λ₯Ό λ°ννλ€.
    - Stepμ ExitStatusκ° on()μ μ΄λ€ Pattern κ³Όλ λ§€μΉ­μ΄ λμ§ μλλ€λ©΄ μμΈκ° λ°μνκ³  Jobμ μ€ν¨νκ² λλ€.
      > -  `*`: μμΌλ μΉ΄λ, λͺ¨λ  ExitStatusμ λ§€μΉ­(C*, F*, *), `?`: μ νν 1κ°μ λ¬Έμμ λ§€μΉ­ (C?T)
      > - μμΌλ μΉ΄λλ `else` μ²λΌ μ¬μ©λ  μ μλ€.  step1μ on("COMPLETED") to(step2())λ₯Ό μ€ννλ€κ³  μ§μ ν΄λκ³ ,    
         from(step1()).on("*") μ¬μ©νλ©΄ COMPLETEDλ₯Ό μ μΈν λͺ¨λ  ν¨ν΄μ λ»νκ² λλ€.
    - TransitionBuilderλ μλμ APIλ₯Ό κ°μ§λ€.
    - #### .to(Step | Flow | JobExecutionDecider)
      - λ€μμΌλ‘ μ€νν  κ²μ μ§μ νλ€.
    - #### .stop(), .fail(), .end(), .stopAndRestart()
      - flowλ₯Ό μ€μ§, μ€ν¨, μ’λ£νλλ‘ νλ€.
      - FlowExecutionStatusκ° κ°κ° `STOPPED`, `FAILED`, `COMPLETED` λ‘ μ’λ£λλ€.
      - stopAndRestart() λ νμ¬κΉμ§μ Stepμ COMPLETEDλ‘, μ΄νλ μ€ννμ§ μκ³  STOPPED μνλ‘ Jobμ μ’λ£νλ€.(μ΄ν μ¬μμμ COMPLETEDλ Skip)
      - μ€μ  Stepμ΄ FAILEDλ‘ μ’λ£λμλλΌλ Jobμ BatchStatusλ₯Ό COMLETEDλ‘ μ’λ£νλλ‘ ν  μ μλ€.(μ¬μμ λΆκ°λ₯ ν΄μ§)
    
  - #### end()
    - FlowBuilder λ₯Ό μ’λ£νκ³  SimpleFlow κ°μ²΄λ₯Ό μμ±νλ€.
    - FlowJobBuilderμμλ flowJobμ μμ±νκ³  Simpleflowλ₯Ό μ€νμν¨λ€.
  
`start, next, from` μ flowλ₯Ό μ μνκ³ , `on, to, stop, fail, end, stopAndRestart`λ μ‘°κ±΄μ λ°λΌ νλ¦μ μ νμν¨λ€.   
on()μ νΈμΆνλ©΄ TransitionBuilderκ° μμ±λκ³ , `to, stop, fail, end, stopAndRestart`λ₯Ό μ€μ ν  μ μλ€.
```java
@Bean
public Job flowJob() {
    return jobBuilderFactory.get("flowJob")
        .start(myStep1())
        .on("COMPLETED").to(myStep3())
        .from(myStep1())
        .on("FAILED").to(myStep2())
        .end()
        .build();
}
```
FlowJobμ λ€μκ³Ό κ°μ΄ κ΅¬μ±νλ©΄ myStep1μ΄ μ±κ³΅νλ©΄ myStep3λ‘, μ€ν¨νλ©΄ myStep2λ₯Ό μ€ννλ€λ νλ¦μ΄ λ§λ€μ΄μ§λ€.    

![img_10.png](img_10.png)   
DBμ μ μ₯λ λ©ν λ°μ΄ν°λ₯Ό νμΈνλ©΄ myStep 1κ³Ό 3μ΄ μ€νλ κ²μ νμΈ ν  μ μλ€.

μ΄λ²μλ myStep1 μμ μμΈλ₯Ό λ°μμμΌ μΌλΆλ¬ μ€ν¨ν ν λ©νλ°μ΄ν° κ°μ μ΄ν΄λ³΄κ² λ€.     
![img_11.png](img_11.png)        
onμ `FAILED` ν¨ν΄κ³Ό λ§€μΉ­λμ΄ myStep2κ° μ€νλ κ²μ νμΈν  μ μλ€.   
ν κ°μ§ λ νΉμ΄ μ¬ν­μ΄ μλ€λ©΄, FlowJobμμλ Stepμ μ€ν¨κ° Jobμ μ€ν¨λ‘ μ°κ²°λμ§ μλλ€λ κ²μ΄λ€. μμ μν©μμ JobExecutionμ νμΈν΄ λ³΄μλ€.     
![img_12.png](img_12.png)    
λΆλͺ myStep1μ μ€ν¨μμΌ°μ§λ§ Jobμ μ±κ³΅μ μΌλ‘ λλ κ²μ νμΈν  μ μλ€.
λͺ¨λ  μν©μμ μ΄λ° κ²μ μλκ³ , μ€ν¨νμ κ²½μ° μ΄λ€ κ²μ νλμ§ μ μκ° λμ΄μμ λλ§ ν΄λΉνλ€.    
μ€μ λ‘ COMPLETEDμ μ‘°κ±΄λ§μ μ£Όκ³  Stepμ μ€ν¨μμΌ°μ λμλ Job λν μ€ν¨νλ€.


## π§ Transition
Flow λ΄ Stepμ μ‘°κ±΄λΆ μ νμ μ μνλ€. on()μ νΈμΆνλ©΄ TransitionBuilde κ° λ°νλκ³ , ν΄λΉ κ°μ²΄μ APIλ₯Ό νΈμΆνμ¬ Transition Flowλ₯Ό κ΅¬μ±ν  μ μλ€.

- ### π λ°°μΉ μν
  - #### BatchStatus
    Job, Stepμ μ’λ£ ν μ΅μ’ κ²°κ³Ό μνλ‘, SimpleJob μμλ κ°μ₯ λ§μ§λ§μ μ€ν λ Stepμ μνκ°λκ³ ,    
    FlowJob μμλ λ§μ§λ§ Flowμ FlowExecutionStatus κ°μ΄ λλ€.
    > COMPLETE, STARTING, STARTED, STOPPED, FAILED, ABANDONED(μ€ν¨, κ·Έλ¬λ μ¬μμμ κ±΄λ λ°μ΄μΌνλ λ¨κ³), UNKOWN
  - #### ExitStatus
    μ΄λ€ μνλ‘ μ’λ£λμλμ§λ₯Ό μλ―Ένλ€. κΈ°λ³Έμ μΌλ‘λ BatchStatusμ λμΌν κ°μΌλ‘ μ€μ λμ§λ§, μμλ‘ λ³κ²½ν  μ μλ€.(contribution.setExitStatus()    
    SimpleJob, FlowJobμμμ κ°μ μ€μ μ BatchStatusμ κ°λ€.
    > COMPLETED, FAILED, STOPPED, EXECUTING, UNKNOWN
  - #### FlowExecutionStatus
    FlowExecutionμ μμ±μΌλ‘ FLow μ€ν ν κ²°κ³Ό μνλ₯Ό κ°μ§κ³  μλ€.    
    Flow λ΄μ Stepμ ExitStatus κ°μ FlowExecutionStatus κ°μΌλ‘ μ μ₯νλ©° FlowJobμ λ°°μΉ κ²°κ³Ό μνμ κ΄μ¬νλ€.(Stepμλ μν₯μ μ£Όμ§ μλλ€)
    > COMPLETED, STOPPED, FAILED, UNKNOWN


## π§ μ¬μ©μ μ μ ExitStatus
κΈ°λ³Έμ μΌλ‘ μ μλμ΄ μλ ExitStatus μ΄μΈμ exitCodeλ₯Ό μλ‘­κ² μ μ ν  μ μλ€.    
StepExecutionListenerμ `afterStep()` μμ μμ±ν νμ λ§λ€μ΄μ§ ExitStatusλ₯Ό λ°νν  μ μλ€.
```java
new ExitStatus("CUSTOM_STATUS")
```

afterStep()) μμ μλ‘μ΄ ExitStatusλ₯Ό λ°ννλ©΄ TaskletStepμ exitStatusλ₯Ό μΈννλ λΆλΆμμ μ΄λ₯Ό λ°μνλ€.    
μλμ ExitStatusλ₯Ό μ€μ ν ν afterStep()μ νΈμΆνμ¬ λ€μ ExitStatusλ₯Ό κ°μ Έμ€κΈ° λλ¬Έμ λ?μ΄ μμμ§λ€.


```java
@Bean
public Flow flowA() {
    FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");
    
    return flowBuilder
        .start(myStep1())
        .on("COMPLETED")
        .to(myStep2())
        .on("PASS")
        .stop()
        .next(myStep3())
        .end();
}
```

```java
@Bean
public Step myStep2() {
    return stepBuilderFactory.get("myStep2")
        .tasklet(new MyTasklet("myStep2"))
        .listener(new PasscheckingListener())
        .build();
}
```

```java
public class PasscheckingListener implements StepExecutionListener {

    ...
    
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        ExitStatus exitCode = stepExecution.getExitStatus();

        if(!exitCode.getExitCode().equals(ExitStatus.FAILED.getExitCode())) {
            return new ExitStatus("PASS");
        }
        return exitCode;
    }
}
```
![img_14.png](img_14.png)

myStep2μ EXIT_CODEκ° PASSλ‘ λ³κ²½λμλ€.

![img_13.png](img_13.png)

myStep1μ΄ COMPLETEDλ‘ λλ myStep2κ° μ€νλκ³  λ§μ°¬κ°μ§λ‘ COMPLETEDλ‘ λλκΈ° λλ¬Έμ afterStep() μμ ExitStatusκ° `PASS` λ‘ λ³κ²½λλ€.   
`on("PASS")` ν¨ν΄μ λ§€μΉ­λμ΄ `.stop()`μ΄ νΈμΆλκ³ , Jobμ STOPPED μνλ‘ λ§μΉκ² λλ€.


## π§ JobExecutionDecider
ExitStatusμ μ‘°μμ΄λ StepEcecutionListenerμ λ±λ‘μμ΄ Transition μ²λ¦¬λ₯Ό μν ν΄λμ€λ‘Stepκ³Ό Transitionμ μ­ν μ λͺννκ² λΆλ¦¬ν  μ μκ² ν΄μ€λ€.

κΈ°μ‘΄μλ Stepμ ExitStatusκ° JobExecutionStatusμ μν κ°μ λ°μλκ³ , μ΄ κ°μ΄ JobFlowμ λ°μνλ κ²κ³Ό λ¬
JobExecutionDeciderμμ FlowExecutionStatus μνκ°μ μλ‘­κ² μμ±ν΄μ λ°ννλ€.

```java
@Bean
public Job job() {
    return jobBuilderFactory.get("job")
        .incrementer(new RunIdIncrementer())
        .start(firstStep())
        .next((decider()))
        .on("ODD").to(oddStep())
        .on("EVEN").to(evenStep())
        .end()
        .build();
}

@Bean
public JobExecutionDecider decider() {
    return new CustomDecider();
}
```

```java
public class CustomDecider implements JobExecutionDecider {

    private int count = 0;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (++count % 2 == 0) {
            return new FlowExecutionStatus("EVEN");
        }
        return new FlowExecutionStatus("ODD");
    }
}
```
μ΄μ μ APIλ₯Ό μ΄μ©νμ¬ ExitStatus μ½λμ λ°λΌ flowλ₯Ό μ§ννλ λ°©μκ³Ό λμΌνκ² λμνλ€. Jobμ κ΅¬μ±νλ μν©μ λ°λΌ λ μλ§λ€κ³  μκ°λλ λ°©λ²μ μ ννλ©΄ λκ² λ€.


## π§ FlowJob μν€νμ²
![img_15.png](img_15.png)

λλΆλΆμ SimpleJobκ³Ό λμΌνλ€.   
λ€λ₯Έ μ μ SimpleFlow μμ StateλΌλ μμ±μ κ°μ§λ€λ κ²κ³Ό, μμμ΄ μ’λ£λμμ λ StepExecutionμ μνλ₯Ό λ°μνλ κ²μ΄ μλλΌ `FlowExecutionStatus`   
μ μνλ‘ μλ°μ΄νΈ νλ€λ κ²μ΄λ€.


## π§ SimpleFlow
Flowμ κ΅¬νμ²΄λ‘ Step, Flow, JobExecutionDeciderμ λ΄κ³  μλ Stateλ₯Ό μ€νμν€λ λλ©μΈ κ°μ²΄λ‘, FlowBuilderλ₯Ό ν΅ν΄ μμ±λλ€.    
Flowλ μ€μ²©λ  μ μλ€

```java
@Bean
public Job flowJob() {
    return jobBuilderFactory.get("flowJob")
        .start(flowA()) // SimpleFlowA
        .end() // SimpleFlow μμ±
        .build();
}
```
κ²°κ³Όμ μΌλ‘ FlowJob ( SimpleFlow( SimpleFlowA ) )μ κ°μ ννκ° λλ€.
- ### Flow
  - `getName()`
  - `getStatus(stateName)`
  - `FlowExecution start(flowExcecutor)` : Flowλ₯Ό μ€ν.
  - `resume(stateName, flowExecutor)` : λ€μμ μ€νν  Stateλ₯Ό κ΅¬ν΄ FlowExecutor μκ² μ€νμ μμνλ€.
  - `getStates()` : Flowκ° κ°μ§κ³  μλ λͺ¨λ  Stateλ₯Ό Collection μΌλ‘ λ°ν.
  
- ### SimpleFlow implements Flow
  - `String name`
  - `State startState`: κ°μ₯ μ²μμΌλ‘ μμν  State(StepState, FlowState, DecisionState, SplitState) 
  - `Map<String, Set<StateTransition>> transitionMap` : State μ΄λ¦μΌλ‘ λ§€ν State λ³ Transition Set
  - `Map<String, State> stateMap`: μ΄λ¦μΌλ‘ λ§€νλμ΄ μλ State Map
  - `List<StateTransition> stateTransitions` : State + Transition μ λ³΄λ₯Ό κ°μ§ κ°μ²΄μ λ¦¬μ€νΈ.    
    StateTransition μ νμ¬ State μ(state) on()μ λ§€μΉ­λλ ν¨ν΄(pattern), λ€μ State(next) μ μμ±μΌλ‘ μ΄λ£¨μ΄μ Έ μλ€.
  

- ### SimpleFlow μμ±
  ```java
  @Bean
  public Flow flow() {
      FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
      return flowBuilder
          .start(myStep1())
          .next(myStep())
          .end();
  }
  ```
  λλ flowBuilder.build()λ₯Ό return ν΄λ λλ€. (end() κ° λ΄λΆμ μΌλ‘λ build()λ₯Ό νΈμΆνμ¬ SimpleFlow κ°μ²΄λ₯Ό μμ±νλ€.)
  

## π§ SimpleFlow μν€νμ²
![img_16.png](img_16.png)

start(), next(), from() μ λ¬λλ κ°μ²΄μ λ°λΌ State κ°μ²΄λ₯Ό μμ±νμ¬ μ λ¬λ κ°μ²΄λ₯Ό μ μ₯νλ€.     
μ΄λ κ² μμ±λ Stateλ SimpleFlow μμ StateTransition κ°μ²΄λ‘ κ΄λ¦¬λλ©°, ν΄λΉ κ°μ²΄λ₯Ό ν λλ‘ SimpleFlowμ λ€λ₯Έ μμ±λ€μ κ°μ μ€μ νκ² λλ€.

![img_17.png](img_17.png)

SimpleFlowκ° `State` λ₯Ό μ€νμμΉΈλ€.(StateTransition μ μ°Έκ³ νμ¬ currentStateλ₯Ό μ€ννλ€. Mapμ μ μ₯λ λͺ¨λ  Stateλ₯Ό μννλ©° μ€ν.)    
`State` μμλ Step, Flow, JobExecutionDecider μμλ€μ μ μ₯νλ©°,Flowλ₯Ό κ΅¬μ±νλ©΄ μλμΌλ‘ Stateκ° μμ±λλ©° Transitionκ³Ό μ°λλλ€.   
handle() λ©μλλ₯Ό ν΅ν΄ μ€ν ν FlowExecutionStatusλ₯Ό λ°ννλ€. λ§μ§λ§ μ€ν μνκ° FlowJobμ μ΅μ’ μνκ° λλ€.

- SimpleFlowλ λ SimpleFlowλ₯Ό κ°μ§ μ μκΈ° λλ¬Έμ μ€μ²©λμ΄ κ°μ²΄κ° μμ±λλ©° μ€νλλ€.
- SplitState λ μ¬λ¬κ°μ SimpleFlowλ₯Ό κ°μ§κ³  λ³λ ¬μ μΌλ‘ μ€νμν¬ μ μλ€.

### SimpleFlowμ μ€ν
  ```
  1. SimpleFlowμ start() λ©μλλ₯Ό νΈμΆνμ¬ μ²« Stateλ₯Ό μ€νμν¨λ€.   
  2. κ·Έ μ΄ν resume() λ©μλ μμλ loopλ₯Ό λλ©° λ€μμ μ€νν  Stateκ° μλ€λ©΄ μ€νμν€κ³ , nullμ΄κ±°λ μ€ν λΆκ°λ₯ν μνλΌλ©΄ μ’λ£νλ€.   
  3. nextStateλ₯Ό νΈμΆνμ¬ StateMapμμ λ€μ Stateλ₯Ό μ€ννλ€.
  ```
  
## π§ FlowStep
Step λ΄μ Flowλ₯Ό κ°μ§κ³  μλ λλ©μΈ κ°μ²΄. FlowStepμ Status λ€μ Flowμ μ΅μ’ μνκ°μ λ°λΌ κ²°μ λλ€.    

`StepBuilderFactory` βΆ `StepBuilder` βΆ `FlowStepBuilder` βΆ `FlowStep`
```java
@Bean
public Step flowStep() {
    return stepBuilderFactory.get("flowStep")
        .flow(flowA()) // FlowStepBuilder λ°ν.
        .build(); // FlowStep λ°ν.
}
```

<br>

# π @JobScope, @StepScope
@JobScope μ @StepScopeλ λΉμ μμ±κ³Ό μ€νμ κ΄μ¬νλ©°, λΉμ μμ± μμ μ μ‘°μνλ€.(κ΅¬λμμ  -> λΉμ μ€ν μμ )       
λ Scope μ λΈνμ΄μμ λ€μκ³Ό κ°μ΄ μ μλμ΄ μλ€. `@Scope(value="job | step", proxyMode = ScopedProxyMode.TARGET_CLASS`     
μ μμμ λ³Ό μ μλ― ν΄λΉ μ λΈνμ΄μμ μ¬μ©νλ©΄ κ΅¬λμμ μλ νλ‘μ κ°μ²΄λ‘ μμ±λκ³ , μ€ν μμ μ μ€μ  λΉμ νΈμΆνμ¬ λ©μλλ₯Ό μ€ννλ€.

- #### @Values λ₯Ό μ£Όμν΄μ λΉμ μ€ν μμ μ νΉμ  κ°μ μ°Έμ‘°νλκ² κ°λ₯ν΄μ§λ€.(Lazy Binding, νλ λλ νλΌλ―Έν°λ‘ μ£Όμλ°λλ€)
  - `@Values("#{jobParameters[paramName]}")`, `@Values("#{jobExecutionContext[paramName]}")`, `@Values("#{stepExecutionContext[paramName]}")`
- #### μ€νλ§μ Bean μ κΈ°λ³Έμ μΌλ‘ Singleton μ΄κΈ° λλ¬Έμ μ€λ λ μΈμ΄ν νμ§ μμλ°, ν΄λΉ μ λΈνμ΄μλ€μ μ¬μ©νλ©΄ κ° μ€λ λλ§λ€ μ€μ½ν λΉμ΄ ν λΉλκΈ° λλ¬Έμ μ€λ λ μΈμ΄ννκ² μ€νμ΄ κ°λ₯ν΄μ§λ€.


- ### @JobScope
  - Stepμ μ μΈλ¬Έμ μ μ
  - jobParameter, jobExecutionContext κ°μ λ°μΈλ© ν  μ μλ€.
  ```java
  @JobScope
  @Bean
  public Step myStep1(@Value("#{jobParameters['message']}") String message) {
      System.out.println("Parameter[message]: " + message);
      return stepBuilderFactory.get("myStep1")
          .tasklet(tasklet(null, null))
          .build();
  }
  ```
  λ°νμ μμ μ κ°μ΄ λ°μΈλ© λκΈ° λλ¬Έμ nullμ λκ²¨μ£Όμ΄ μ»΄νμΌ μλ¬λ₯Ό λ°©μ§ν΄μ€λ€.
- ### @StepScope
  - Tasklet, Item Reader, Writer, Processor μ μΈλ¬Έμ μ μνλ€.
  - jobParameter, jobExecutionContext, stepExecutionContext κ°μ λ°μΈλ© ν  μ μλ€.
  ```java
  @Bean
  @StepScope
  public Tasklet tasklet(@Value("#{jobExecutionContext['name']}") String jobName,
      @Value("#{stepExecutionContext['name']}") String stepName) {
      return ((contribution, chunkContext) -> {
          System.out.println("tasklet has execute");
          System.out.println("jobName: " + jobName + ", " + "stepName: " + stepName);
          return RepeatStatus.FINISHED;
      });
  }
  ```
  job, stepExecutionContextμ κ°μ κ° ExecutionListenerμμ λ£μ΄μ€ μ μλ€.

## π§ Scope μν€νμ²
Proxy κ°μ²΄μ μ€μ  λμμ΄ λλ Beanμ λ±λ‘νκ³ , ν΄μ νλ μ­ν μ νλ `JobScope`, `StepScope` ν΄λμ€κ° μ‘΄μ¬νλ€.    
ν΄λΉ ν΄λμ€λ€μ  μ€μ  λΉμ μ μ₯νκ³  μλ `JobContext`μ `StepContext`λ₯Ό κ°μ§κ³  μλ€. (λ§μΉ Springμ ApplicationContextμ κ°μ΄)

![img_18.png](img_18.png)

`μ΄νλ¦¬μΌμ΄μ κ΅¬λ` βΆ `ApplicationContextμμ λΉμ μμ±` βΆ `@JobScope, StepScopeκ° μλκ°?` βΆ `μμΌλ©΄ proxy, μμΌλ©΄ Singleton Bean μμ±`    
`μ€νλ§ μ΄κΈ°μ μλ£, Jobμ€ν` βΆ `Job μμ Proxy νΈμΆ` βΆ `proxyμμ μ€μ  Step Bean μ°Έμ‘°` βΆ `Step Bean μ΄ μλ€λ©΄ κΊΌλ΄μ£Όκ³  μλ€λ©΄ beanFactory μμ μμ±(@Value λ°μΈλ©λ μ΄λ)`    
βΆ`JobScope ν΄λμ€μμ μ€μ  Beanμ JobContextμ λ±λ‘, κ΄λ¦¬`

<br>

# π Chunk Process

## π§ Chunk?
![img_19.png](img_19.png)

Chunk λ μ¬λ¬κ°μ μμ΄νμ λ¬Άμ λ©μ΄λ¦¬ λΈλ‘μΌλ‘, μμ΄νμ μλ ₯λ°μ λ©μ΄λ¦¬λ‘ λ§λ  ν Chunk λ¨μλ‘ νΈλμ­μμ μ²λ¦¬νλ€.   
μΌλ°μ μΌλ‘ λμ©ν₯ λ°μ΄ν°λ₯Ό νλ²μ μ²λ¦¬νλ κ²μ΄ μλ chunk λ¨μλ‘ μͺΌκ°μ΄ λ°λ³΅ μμΆλ ₯ ν  λ μ¬μ©λλ€.

![img_20.png](img_20.png)

- `Chunk<I>` λ `ItemReader` λ‘λΆν° μ½μ μμ΄νμ `Chunk Size` λ§νΌ λ°λ³΅ν΄μ μ μ₯νλ€.
- `Chunk<O>` λ `ItemReader`λ‘ λΆν° μ λ¬λ°μ `Chunk<I>`λ₯Ό μ°Έμ‘°νμ¬ `ItemProcessor`μμ κ°κ³΅λ μμ΄νλ€μ `ItemWriter` μκ² μ λ¬νλ€.
- ItemReader μ Processor λ μμ΄νμ κ°λ³μ μΌλ‘ μ²λ¦¬νμ§λ§ ItemWriter λ μΌκ΄μ μΌλ‘ μ²λ¦¬νλ€.(List λ₯Ό λ°μ)

```java
@Bean
public Step chunkStep() {
    return stepBuilderFactory.get("chunkStep")
        .<String, String>chunk(5)
        .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4", "item5")))
        .processor(new ItemProcessor<String, String>() {
            @Override
            public String process(String item) throws Exception {
                // Do Something
                return ... 
            }
        })
        .writer(new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> items) throws Exception {
                // Do Something (μΆλ ₯, DB μ μ₯, νμΌ μ°κΈ° λ±..)
            }
        })
        .build();
}
```
- ### μμ±
  - List Items
  - List<SkipWrapper> skips: μ€λ₯ λ°μμΌλ‘ μ€ν΅λ μμ΄ν
  - List<Exception> errors
  - iterator()
    > Inner ClassμΈ ChunkIteratorκ° λ°νλλ€.

## π§ ChunkOrientedTasklet
μ€νλ§ λ°°μΉμμ μ κ³΅νλ Taskletμ κ΅¬νμ²΄λ‘, Chunk κΈ°λ° νλ‘μΈμ±μ λ΄λΉνλ λλ©μΈ κ°μ²΄μ΄λ€.    
Taskletμ μν΄ λ°λ³΅μ μΌλ‘ μ€νλλ©° μ€νλ  λλ§λ€ μλ‘μ΄ νΈλμ­μμ΄ μμ±λμ΄ μ²λ¦¬κ° μ΄λ£¨μ΄μ§λ€. λλ¬Έμ μμΈ λ°μμΌλ‘ λ‘€λ°±μ΄ μ΄λ£¨μ΄μ Έλ μ΄μ μ μ»€λ°ν Chunkλ λ‘€λ°±λμ§ μλλ€.

λ΄λΆμλ ItemReader λ₯Ό νΈλ€λ§νλ `ChunkProvider` μ ItemProcessor, ItemWriter λ₯Ό νΈλ€λ§νλ `ChunkProcessor` νμμ κ΅¬νμ²΄κ° μ‘΄μ¬νλ€.

`TaskletStep` βΆ `ChunkOrientedTasklet.execute()` βΆ `ChunkProvider` βΆ `ItemReaderλ₯Ό ν΅ν΄ read(chunkSize λ§νΌ λ°λ³΅)` βΆ `ChunkProcessorλ₯Ό ν΅ν΄ process(inputs)`    
βΆ `ItemProcessorμκ² μ²λ¦¬ μμ Iteratorλ‘ μννλ©° μ²λ¦¬` βΆ `ItemWriter` βΆ `ChunkOrientedTasklet μΌλ‘ λμκ° μμ΄νμ΄ μμ λ κΉμ§ λ°λ³΅`

Chunkλ₯Ό μ§ννλ©° `ChunkContext` μ item λ€μ μΊμ±νλ€. κ·Έλ¦¬κ³  μμΈκ° λ°μνμ¬ **μ¬ μλν  κ²½μ° μμ΄νμ λ€μ μ½μ΄μ€λ κ²μ΄ μλλΌ μΊμ±λ μμ΄νμ κΊΌλ΄ λ€μ μ²λ¦¬νλ€.**    
μΊμ±λ λ°μ΄ν°λ ν΄λΉ Chunkκ° λͺ¨λ μνλμμ κ²½μ° μ κ±°νκ² λλ€.

### π API
  - #### .<I, O>chunk(size)
      - input, output μ λ€λ¦­ νμμ μ€μ , commit interval μ§μ .
      - SimpleStepBuilder λ₯Ό λ°ννλ€.
  - #### .<I, O>chunk(CompletionPolicy)
    - Chunk νλ‘μΈμ€λ₯Ό μλ£νκΈ° μν μ μ±μ μ€μ νλ ν΄λμ€.
  - #### .reader(ItemReader), .processor(ItemProcessor), .writer(ItemWriter))
    - Processorλ νμμ μΌλ‘ μ¬μ©νμ§ μμλ λλ€.
  - #### .stream(ItemStream)
    - μ¬μμ λ°μ΄ν°λ₯Ό κ΄λ¦¬νλ μ½λ°±μ λν μ€νΈλ¦Ό.
  - #### .readerIsTransactionalQueue()
    - MQS, JMS κ°μ΄ νΈλμ­μ μΈλΆμμ μ½κ³ , μΊμν  κ²μΈμ§μ μ¬λΆ, κΈ°λ³Έμ false μ΄λ€.
  - #### .listener(CHunkListener)


## π§ ChunkProvider / ChunkProcessor

- ### ChunkProvider 
  ItemReaderλ₯Ό μ¬μ©ν΄μ μμ€λ‘λΆν° μμ΄νμ chunk size λ§ν΄ μ½μ΄ μ κ³΅νλ λλ©μΈ κ°μ²΄μ΄λ€.   
  `Chunk<I>`λ₯Ό λ§λ€κ³  λ°λ³΅λ¬Έμ μ¬μ©ν΄ ItemReader.read()λ₯Ό νΈμΆνλ©° μμ΄νμ chunkμ μκ³ , μ¬μ΄μ¦λ§νΌ μμ΄ν μ½κΈ°λ₯Ό λ§μΉλ©΄ ChunkProcessorλ‘ λμ΄κ°λ€.     
  λ§μ½ λμ΄μ μ½μ μμ΄νμ΄ μλκ²½μ°(null) chunk νλ‘μΈμ€λ₯Ό μ’λ£νλ€.
  
  κΈ°λ³Έ κ΅¬νμ²΄λ‘ SimpleChunkProvider, FaultTolerantChunkProvider(μμΈ λ°μμ skip, retry) μ΄ μλ€.

- ### ChunkProcessor
  ItemProcessorλ₯Ό μ¬μ©ν΄μ Itemμ κ°κ³΅νκ³ , ItemWriterλ₯Ό μ¬μ©ν΄μ Chunk λ°μ΄ν°λ₯Ό μ μ₯, μΆλ ₯νλ€.     
  `Chunk<O>`λ₯Ό μμ±νκ³  λμ΄μ¨ `Chunk<I>` μμ μμ΄νμ ν κ±΄μ© κΊΌλ΄ μ²λ¦¬ν ν `Chunk<O>` μ κ²°κ³Όλ₯Ό μ μ₯νλ€.      
  ItemProcessorλ νμ μ¬ν­μ΄ μλκΈ° λλ¬Έμ μλ€λ©΄ μλ¬΄μ²λ¦¬ μμ΄ κ·Έλλ‘ `Chunk<O>`μ μ μ₯λκ² λλ€.   
  ItemWriter κΉμ§μ μ²λ¦¬κ° μλ£λλ©΄ ν΄λΉ Chunk νΈλμ­μμ΄ μ’λ£λκ³ , λ€μ ChunkOrientedTaskletμ΄ μ€νλλ€.
  
  κΈ°λ³Έ κ΅¬νμ²΄λ‘λ SimpleChunkProcessor μ FaultTolerantChunkProcessorκ° μλ€.

## π§ ItemReader, ItemWriter, ItemProcessor

- ### ItemReader
  - csvm txt λ±μ νλ« νμΌ 
  - XML, JSON
  - DB
  - JMSμ κ°μ Message Queuing μλΉμ€
  - Custom Reader
  
  λ±μ λ€μν μμ€μμ λ°μ΄ν°λ₯Ό μ½μ΄ μ κ³΅νλ μΈν°νμ΄μ€λ‘ μμ΄νμ νλμ©μ½μ΄ λ°ννκ³ , λ μ΄μ μλ€λ©΄ nullμ λ°ννλ€.     
  ExecutionContextμ readμ κ΄λ ¨λ μ¬λ¬ μν μ λ³΄λ₯Ό μ μ₯ν΄ μ¬μμμ λ€μ μ°Έμ‘°νλλ‘ μ§μνλ€.

- ### ItemWriter
  ItemReader μμ μ½μ μμ΄νλ€μ λ¦¬μ€νΈλ‘ μ λ¬λ°μ μΆλ ₯νλ€. μΆλ ₯μ΄ μλ£λκ³  νΈλμ­μμ΄ μ’λ£λλ©΄ μλ‘μ΄ Chunk λ¨μ νλ‘μΈμ€λ₯Ό μ§ννλ€.

- ### ItemProcessor
  λ°μ΄ν°λ₯Ό μΆλ ₯νκΈ°μ  λ°μ΄ν°λ₯Ό κ°κ³΅, λ³ν, νν°λ§(nullμ λ°ννλ©΄ νν°λ§ λλ€) νλ€.
  ItemReader μ ItemWriter μ λλ¦½λμ΄ λΉμ¦λμ€ λ‘μ§μ κ΅¬ννλ€. Reader μμ λ°μ μμ΄νμ νΉμ  νμμΌλ‘ λ³ννμ¬ Wirterμ λκ²¨μ€λ€.   
  μ€κ° μ²λ¦¬μ μ­ν μ΄κΈ° λλ¬Έμ νμμμκ° μλκ³ , Processor κ° μμΌλ©΄ μμ΄νμ κ·Έλλ‘ Writerμ μ λ¬λλ€.
  
λλΆλΆ ItemReaderμ ItemWriterλ μ€νλ§μμ μ κ³΅νλ κ΅¬νμ²΄λ₯Ό μ¬μ©νλ κ²½μ°κ° λ§κ³ , ItemProcessorλ λΉμ¦λμ€ λ‘μ§μ λ΄κΈ° λλ¬Έμ
μ§μ  κ΅¬ννλ€.


## π§ ItemStream
ExecutionContext λ₯Ό λ§€κ°λ³μλ‘ λ°μ ItemReader, ItemWriter μ²λ¦¬μ μνλ₯Ό μ μ₯νκ³ , μ€λ₯κ° λ°μνλ©΄ ν΄λΉ μνλ₯Ό μ°Έμ‘°νμ¬ μ¬μμ νλλ‘ μ§μνλ€.    
ItemReader, ItemWriter μ κ΅¬νμ²΄λ ItemSteam μ κ΅¬νν΄μΌ νλ€.(ItemStreamReader, ItemStreamWriter μ κ΅¬ννλ©΄ λλ€.)   

- #### open(ExecutionContext)
  - read(), write() μ μ νμΌμ΄λ μ»€λ₯μμ΄νμν λ¦¬μμ€μ  μ κ·Όνλλ‘ μ΄κΈ°ννλ μμ.
- #### update(ExecutionContext) 
  - νμ¬κΉμ§μ μνλ₯Ό μ μ₯
- #### close(ExecutionContext) 
  - μ΄λ €μλ λ¦¬μμ€ ν΄μ . (μμΈκ° λ°μνμ λλ νΈμΆλμ΄ λ¦¬μμ€λ₯Ό ν΄μ νλ€)
  
ItemReader, ItemWriter κ° λμνκΈ° μ μ ItemStreamμμ open() μ ν΅ν΄ λ¦¬μμ€λ₯Ό μ΄κ³  μ΄κΈ°ν νλ€.    
κ·Έ ν μμ΄νμ μ½μ΄μ¬ λ, μΈ λ chunk λ§λ€ update()λ₯Ό νΈμΆνμ¬ DBμ μ μ₯νλ€.

```java
public class CustomItemReader implements ItemStreamReader<Member> {

    private final List<Member> items;
    private int index;
    private boolean restartable;

    public CustomItemReader(List<Member> items) {
        this.items = new ArrayList<>(items);
        this.index = 0;
        this. restartable = false;
    }

    @Override
    public Member read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Member item = null;

        if(this.index < this.items.size()) {
            item = this.items.get(index++);
        }

        if(index == 8 && !restartable) {
            throw  new RuntimeException("Restart is required");
        }

        return item;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        if(executionContext.containsKey("index")) {
            index = executionContext.getInt("index");
            this.restartable = true;
        }
        else {
            executionContext.put("index", index);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("index", index);
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.printf("λ¦¬μμ€ ν΄μ .");
    }
}
```
- open() μμ μ΄μ μ μ€νν΄ indexκ° μ‘΄μ¬νλ€λ©΄ ν΄λΉ index κ°μ λΆλ¬μ κ·Έ μμΉλΆν° μ€ννλ€.
- close(): μμΈκ° λ°μνμ λ νΈμΆλμ΄, λ¦¬μμ€λ₯Ό ν΄μ μν¨λ€.

Chunk Sizeλ₯Ό 2λ‘ μ£Όκ³ , λ¦¬μμ€λ‘ 10κ°μ μμ΄νμ μ£Όμλ€.    
8λ²μ§Έ μμ΄νμ μ½μ νμ μμΈκ° λ°μνλλ‘ μ€μ ν΄ λ³΄μλ€. μλ μ΄λ―Έμ§λ νμ€νΈμ κ²°κ³Όμ΄λ€.

![img_21.png](img_21.png)

μ΅μ΄μ Readerμ Writerμ Streamμ΄ Open λκ³ , Update κ° νλ² νΈμΆ λλ€.    
κ·Έ λ€μ μμ΄νμ μ²­ν¬ μ¬μ΄μ¦λ§νΌ μ½κ³ , Processorκ° λμν ν Writeκ° μ΄λ£¨μ΄ μ§λ€.(USER1, USER2 μ κ°μ΄ μΆλ ₯νλλ‘ ν¨)     
read(), process(), write() κ° ν chunkμ λν΄ λͺ¨λ μ€νλλ©΄ Reaader, Writerμ Streamμμ Update()κ° νΈμΆλμ΄ μνλ₯Ό μ μ₯νλ€.

![img_22.png](img_22.png)

8λ²μ§Έ μμ΄νμμ μμΈκ° λ°μνλ©΄ Close()λ₯Ό νΈμΆνμ¬ λ¦¬μμ€λ₯Ό ν΄μ νκ³  μ’λ£νλ€.    
λ¬Όλ‘  μμΈκ° λ°μνμ§ μμλ Close()λ₯Ό νΈμΆνμ¬ λ¦¬μμ€λ₯Ό ν΄μ νλ€.

λ€μμ ν΄λΉ μΈμ€ν΄μ€λ₯Ό λ€μ μ€ννκ² λλ©΄ ExecutionContext μμ μΈλ±μ€λ₯Ό κ°μ Έμ€κ³ (8μ μ€ννλ€ λ‘€λ°± λμμΌλ―λ‘ μ΄μ  ChunkμΈ 6κΉμ§ μ μ₯λμλ€.)   
restartableμ΄ trueλ‘ λ°λκΈ° λλ¬Έμ item 10 κΉμ§ μ μμ μΌλ‘ μ€νλλ€.


## π§ Chunk Process μν€νμ²
<img alt="img_23.png" height="500" src="img_23.png" width="1000"/>    

μ€λͺμ μμμ κ³μ νμΌλ μλ΅νλ€.

<br>

# π ItemReader κ΅¬νμ²΄

## π§ FlatFileItemReader
νμ κ°μ 2μ°¨μ λ°μ΄ν°λ‘ ννλ μ νμ νμΌμ μ²λ¦¬νλ€. μΌλ°μ μΌλ‘ κ³ μ μμΉλ‘ μ μλ λ°μ΄ν°λ, νΉμ λ¬Έμμ μν€ κ΅¬λ³λ λ°μ΄ν°μ νμ μ½λλ€.    
Resource(μ½μ΄μΌν  λ°μ΄ν°)μ LineMapper(Line String to Object) κ° νμνλ€.


- ### π μμ±
  - #### String encoding
  - #### int linesToSkip
    - νμΌ μλ¨λΆν° λ¬΄μν  λΌμΈ μ (ν€λ λ±μ μ€ν΅ν λ μ¬μ©)
    - LineCallbackHandler λ₯Ό νΈμΆνμ¬ κ±΄λλ΄λ€.
  - #### String[] comments
    - ν΄λΉ λ¬Έμκ° μλ λΌμΈμ λ¬΄μνλ€.
  - #### Resource resource
    - FileSystemResource, ClassPathResource ...
  - #### LineMapper\<T> lineMapper
    - Lineμ μ½μ΄ κ°μ²΄λ‘ λ³ννλ€.
    - `LineTokenizer`
      - λΌμΈμ FieldSet μΌλ‘ λ³ννλ€. νμΌ νμμ λ§μΆ° FieldSet μΌλ‘ λ³ννλ μμμ μΆμνν΄μΌνλ€.
      - κ΅¬λΆμλ₯Ό μ΄μ©νλ DelimitedLineTokenizer, κ³ μ κΈΈμ΄ λ°©μμ FixedLengthTokenizer κ° μλ€.
    - `FieldSet`
      - λΌμΈμ κ΅¬λΆμλ‘ κ΅¬λΆν΄μ  ν ν° λ°°μ΄μ μμ±νλ€.
    - `FieldSetMapper`
      - FieldSetμ κ°μ²΄μ λ§€ννμ¬ λ°ννλ€.(κ°μ²΄μ νλλͺκ³Ό λ§€ν, BeanWrapperFieldSetMapperλ₯Ό μ¬μ©νλ€.)

- ### π API
  - .name(String name)
    - ExecutionContext λ΄μμ κ΅¬λΆνκΈ° μν keyλ‘ μ μ₯λλ€.
  - .resource(Resource)
  - .delimited().delimiter()
  - .fixedLength()
    - κΈΈμ΄λ₯Ό κΈ°μ€μΌλ‘ νμΌμ μ½μ
  - .addColumns(Range)
    - κ³ μ  κΈΈμ΄μ λ²μ
  - .names(String[] fieldNames)
    - λ§€νλ  κ°μ²΄μ νλλͺ
  - .targetType(Class)
  - .addComment(String comment)
    - λ¬΄μν  λΌμΈμ κΈ°νΈ μ€μ .
  - .stric(false)
    - λΌμΈμ μ½μ λ νμ± μμΈκ° λ°μνμ§ μλλ‘ κ²μ¦ μλ΅ μ€μ . κΈ°λ³Έμ true
  - .encoding(String encoding)
  - .lineToSkip(num)
  - .saveState(false)
    - μν μ λ³΄λ₯Ό μ μ₯ν  κ²μΈμ§, κΈ°λ³Έμ true
  - .setLineMapper(LineMapper)
  - .setFieldSetMapper(FieldSetMapper)
  - .setLineTokenizer(LineTokenizer)
  
```java
@Bean
public ItemReader<? extends Member> itemReader() {
    FlatFileItemReader<Member> itemReader = new FlatFileItemReader<>();

    DefaultLineMapper<Member> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(new DelimitedLineTokenizer()); // κΈ°λ³Έ κ΅¬λΆμ ','
    lineMapper.setFieldSetMapper(new MemberFieldSetMapper());

    itemReader.setLineMapper(lineMapper);
    itemReader.setResource(new ClassPathResource("/member.csv"));
    itemReader.setLinesToSkip(1);

    return itemReader;
}
```

- LineMapper
```java
@Setter
public class DefaultLineMapper<T> implements LineMapper<T> {

    private LineTokenizer lineTokenizer;
    private FieldSetMapper<T> fieldSetMapper;

    @Override
    public T mapLine(String line, int lineNumber) throws Exception {
        return fieldSetMapper.mapFieldSet(lineTokenizer.tokenize(line));
    }
}
```

- FieldSetMapper
```java
public class MemberFieldSetMapper implements FieldSetMapper<Member> {

    @Override
    public Member mapFieldSet(FieldSet fieldSet) throws BindException {
        if(fieldSet == null){
            return null;
        }

        Member member = new Member();
        member.setName(fieldSet.readString(0));
        member.setId(fieldSet.readString(1));

        return member;
    }
}
```
`νμΌμμ ν μ€μ μ½μ΄μ΄` βΆ `LineTokenizer μμ νμ±ν΄ ν ν° λ°°μ΄ μμ±(DefaultFieldSet)` βΆ `FieldSetMapper μμ fieldSetμ ν λλ‘ κ°μ²΄ μμ±, λ°ν`   
βΆ `νμΌμ λκΉμ§ λ°λ³΅`

namesλ₯Ό λ£μ΄μ£Όμ§ μμκΈ° λλ¬Έμ μΈλ±μ€λ‘ κ°μ κ°μ Έμλ€. LineTokenizerμ setNames()λ₯Ό μ€μ ν΄μ£Όλ©΄ νλλͺμΌλ‘ κ°μ Έμ¬ μ μλ€.

νκΈ°μ κ°μ΄ Builderλ₯Ό μ¬μ©νμ¬ λ κΉλνκ² κ΅¬μ±ν  μ μλ€.
```java
@Bean
public ItemReader itemReader() {
    return new FlatFileItemReaderBuilder<Member>()
    .name("flatFile")
    .resource(new ClassPathResource("/member.csv"))
    .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
    .targetType(Member.class)
    .delimited().delimiter(",")
    .names("name", "id")
    .linesToSkip(1)
    .build();
    }
```
μ½λλ₯Ό λ³΄λ©΄ LineMapperλ₯Ό μ€μ νλ λΆλΆμ΄ λΉ μ‘λ€λ κ²μ μ μ μλλ°, μ¬μ€ λ°λ‘ μμ±ν΄μ λ£μ΄μ£Όμ§ μμλ, μ€νλ§μμ μ κ³΅νλ DefaultLineMapperκ° μ‘΄μ¬νκ³ ,μ΄λ₯Ό μ¬μ©νλ€.   
λ§μ°¬κ°μ§λ‘ FieldSetMapper λν μ€νλ§μμ μ κ³΅νλ BeanWrapperFieldSetMapperλ₯Ό μ¬μ©νκ³ , νκ² ν΄λμ€λ₯Ό μ§μ ν΄ μ£Όλ©΄ νλλͺμ λ§κ² λ§€νν΄μ€λ€.

- ### κ³ μ  κΈΈμ΄λ‘ κ΅¬λΆ
  ```java
  .fixedLength() // fixedLengthBuilder λ°ν
  .names("name", "id")
  .addColumns(new Range(1-5))
  .addCloumns(new Range(6-10))
  ```
  λ¬Έμμ΄μ΄ μλμ μ£Όμνμ.

### π Exception Handling
  - `IncorrectTokenCountException`
    - λ£μ΄μ€ ν ν° νλμ μ΄λ¦(names)μ μλ³΄λ€ μ½μ΄λ€μΈ ν ν°μ μκ° λ€λ₯Ό λ λ°μνλ€.
  - `IncorrectLineLengthException`
    - μ§μ ν΄μ€ μ»¬λΌλ€μ κΈΈμ΄λ³΄λ€ λΌμΈ μ μ²΄ κΈΈμ΄κ° μΌμΉνμ§ μμ λ λ°μνλ€.
  
  κΈ°λ³Έμ μΌλ‘λ `stric` μ΅μμ΄ `true` μ΄κΈ° λλ¬Έμ ν ν°νλ₯Ό μνν  λ μ΄λ₯Ό κ²μ¦νκ² λκ³ , μμΈλ₯Ό λ°μμν¨λ€. νμ§λ§ ν΄λΉ μ΅μμ `false`λ‘ μ£Όκ² λλ€λ©΄    
  λΌμΈ κΈΈμ΄λ μ»¬λΌλͺμ κ²μ¦νμ§ μκ²λκΈ° λλ¬Έμ μμΈλ₯Ό λ°μμν€μ§ μκ³ , λ²μλ μ΄λ¦μ λ§μ§ μλ μ»¬λΌμ λΉ ν ν°μ κ°μ§κ² λλ€.


## π§ XML-StaxEventItemReader

### StAX ?
Streaming API for XML, DOM κ³Ό SAX μ μ₯, λ¨μ μ λ³΄μν API λͺ¨λΈλ‘ PUSH, PULL λ°©μμ λͺ¨λ μ κ³΅νλ€.      
XNL νμΌμ ν­λͺ©μ μ§μ  μ΄λνλ©΄μ Stax νμκΈ°λ₯Ό ν΅ν΄ κ΅¬λ¬Έμ λΆμνλ€.

- Iterator API λ°©μ
  - XMLEventReaderμ nextEvent()λ₯Ό νΈμΆν΄ μ΄λ²€νΈ κ°μ²΄λ₯Ό κ°μ Έμ¨λ€.
- Cursor API λ°©μ
  - JDBC Resultset μ²λΌ λμ, XMLStreamReaderλ XML λ¬Έμμ λ€μ μμλ‘ μ»€μλ₯Ό μ΄λνλ€.
  - μ»€μμμ λ©μλλ₯Ό νΈμΆνμ¬ νμ¬ μ΄λ²€νΈμ μ λ³΄λ₯Ό μ»λλ€.
  

μ€νλ§ λ°°μΉμμλ XML λ°μΈλ©μ Spring OXMμκ² μμνκ³ , λ°μΈλ© κΈ°μ μ μ κ³΅νλ κ΅¬νμ²΄λ₯Ό μ νν΄μ μ²λ¦¬νλλ‘ νλ€.     
`Marshaller`(κ°μ²΄ -> XML), `UnMarchaller`(XML -> κ°μ²΄)λ₯Ό μ§μνλ μ€νμμ€λ‘λ JaxB2, Castor, XmlBeans, Xstream ... μ΄ μλ€. 

μ€νλ§ λ°°μΉλ StAX λ°©μμΌλ‘ λ¬Έμλ₯Ό μ²λ¦¬νλ StaxEventItemReaderλ₯Ό μ κ³΅νλ€.

![img_24.png](img_24.png)
XML λ¬Έμλ₯Ό μ‘°κ°(fragment) λ¨μλ‘ λΆμνμ¬ μ²λ¦¬νλ€.(root element λ₯Ό νλμ μ‘°κ°μΌλ‘)     
μ‘°κ°μ μ½μ λλ DOMμ Pull λ°©μμ μ¬μ©νκ³ , μ΄λ₯Ό κ°μ²΄λ‘ λ°μΈλ© ν λλ SAXμ Push λ°©μμ μ¬μ©νλ€.    
fragment λ¨μλ‘ μ½μ΄λ€μΈ ν SpringOXM μκ² κ°μ²΄ λ§€νμ μμνλ€.

λ£¨νΈ μλ¦¬λ¨ΌνΈλ₯Ό κ°μ²΄λ‘, λ΄λΆμ μμ μλ¦¬λ¨ΌνΈλ€μ λ§€νλ  κ°μ²΄μ νλλ‘ λ§€ννλ€.

- ### π μμ±
  - FragmentEventReader
    - XML μ‘°κ°μ λλ¦½ν XML λ¬Έμλ‘ μ²λ¦¬νλ μ΄λ²€νΈ νλκΈ°
  - XMLEventReader
    - XML μ΄λ²€νΈ κ΅¬λ¬Έ λΆμμ μν μ΅μμ μΈν°νμ΄μ€
  - Unmarshaller
    - XML to Object
  - Resource
  - List<QName> fragmentRootElementNames
    - μ‘°κ° λ¨μμ λ£¨νΈ μλ¦¬λ¨ΌνΈλͺμ λ΄μ λ¦¬μ€νΈ.


- ### π API
  StaxEventItemRedaderBuilder\<T> λ₯Ό μ¬μ©νλ€. 
  - .name(String)
  - .resource(Resource)
  - .addFragmentRootElements(String ...)
    - root Elemnetλ₯Ό μ§μ νλ€.
  - .unmarshaller(Unmarshaller)
    - νκ² κ°μ²΄ μ€μ .
  - .saveState(false)
    - μν μ λ³΄ μ μ₯μ μ¬λΆ, κΈ°λ³Έκ°μ true μ΄λ€.
  


### μμ‘΄μ± μΆκ°
```groovy
implementation 'com.thoughtworks.xstream:xstream:1.4.19'
implementation 'org.springframework:spring-oxm:5.3.16'
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<members>
  <member id="1">
    <id>1</id>
    <name>user1</name>
  </member>
  <member>
    <id>2</id>
    <name>user2</name>
  </member>
  
   ...
  
</members>

```

```java
@Bean
public ItemReader<? extends Member> itemReader() {
    return new StaxEventItemReaderBuilder<Member>()
        .name("staXml")
        .resource(new ClassPathResource("/member.xml"))
        .addFragmentRootElements("member")
        .unmarshaller(itemUnmarshaller())
        .build();
}

@Bean
public Unmarshaller itemUnmarshaller() {
    Map<String, Class<?>> aliases = new HashMap<>();
    aliases.put("member", Member.class);
    aliases.put("id", String.class);
    aliases.put("name", String.class);

    XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
    xStreamMarshaller.setAliases(aliases);

    return xStreamMarshaller;
}
```
Map μ μ²μμΌλ‘ λ€μ΄κ°λ μμλ RootElementμ ν΄λΉνλ κ²μΌλ‘ κ°μ²΄μ λ§€νλκ³ 
κ·Έ λ€μμ μμλ€μ κ°κ° κ°μ²΄μ νλμ λ§€νλλ€.


## π§ JsonItemReader
![img_25.png](img_25.png)

Json λ°μ΄ν°μ νμ±, λ°μΈλ©μ JsonObjectReader κ΅¬νμ²΄μκ² μμνμ¬ μ²λ¦¬νλ€.

### JsonObjectReader κ΅¬νμ²΄
- GsonJsonObjectReader
- JacksonJsonObjectReader
  - itemType: Json λ°μ΄ν°λ₯Ό λ§€νν  κ°μ²΄μ νμ
  - JsonParser
  - ObjectMapper
  - InputStream : Json νμΌλ‘λΆν° λ°μ΄ν°λ₯Ό μ½μ΄μ€λ μλ ₯ μ€νΈλ¦Ό.

```java
@Bean
public ItemReader<? extends Member> itemReader() {
    return new JsonItemReaderBuilder<Member>()
        .name("JsonReader")
        .resource(new ClassPathResource("/member.json"))
        .jsonObjectReader(new JacksonJsonObjectReader<>(Member.class))
        .build();
}
```

```json
[
  {
    "name": "user1",
    "id": "1"
  },
  {
    "name": "user2",
    "id": "2"
  },
  ...
]
```

## π§ DB-ItemReader

### Cursor Based μ²λ¦¬
JDBC ResultSetμ λ©μ»€λμ¦μ μ¬μ©νλ€. νμ¬ νμ μ»€μλ₯Ό μ μ§νλ©° λ°μ΄ν°λ₯Ό νΈμΆνλ©΄ λ€μ νμΌλ‘ μ»€μλ₯Ό μ΄λνμ¬ λ°μ΄ν°λ₯Ό λ°ννλ Streaming λ°©μμ I/O μ΄λ€.    
DB Connectionμ΄ μ°κ²°λλ©΄ λ°°μΉκ° μλ£λ  λ κΉμ§ λ°μ΄ν°λ₯Ό μ½μ΄μ€κΈ° λλ¬Έμ μμΌ νμμμμ μ΄μ λ§κ² μ€μ νμΌ νλ€.
- λͺ¨λ  κ²°κ³Όλ₯Ό λ©λͺ¨λ¦¬μ ν λΉνκΈ° λλ¬Έμ λ©λͺ¨λ¦¬ μ¬μ©λμ΄ λ§λ€.
- Connection μ°κ²° μ μ§ μκ°κ³Ό λ©λͺ¨λ¦¬ κ³΅κ°μ΄ μΆ©λΆνλ€λ©΄ λμ©λ λ°μ΄ν°μ μ²λ¦¬μ μ ν©νλ€.(fatchSizeλ‘ νλ²μ κ°μ Έμ€λ μ μ€μ  κ°λ₯)
  
### Paging Based μ²λ¦¬
νμ΄μ§ λ¨μλ‘ λ°μ΄ν°λ₯Ό μ‘°ν, PageSize λ§νΌ νλ²μ λ©λͺ¨λ¦¬μ μ¬λ €λκ³ , ν κ°μ© μ½λλ€.    
Cursorμ λ¬λ¦¬ ν νμ΄μ§λ₯Ό μ½μ λ λ§λ€ Connectionμ μ¬μ°κ²° νλ€.
- νμ΄μ§ λ¨μμ κ²°κ³Όλ§ λ©λͺ¨λ¦¬μ ν λΉνκΈ° λλ¬Έμ λ©λͺ¨λ¦¬ μ¬μ©λμ΄ λ μ μ μ μλ€.
- μ»€λ€κ²¬ μ°κ²° μ μ§μκ°μ΄ μ κ³ , λ©λͺ¨λ¦¬ κ³΅κ°μ ν¨μ¨μ μΌλ‘ μ¬μ©ν΄μΌ νλ κ²½μ°μ μ ν©νλ€.
  

- ### π JdbcCursorItemReader

  <img alt="img_26.png" height="400" src="img_26.png" width="900"/>
  
  μ»€μ κΈ°λ°μ JDBC κ΅¬νμ²΄λ‘ ResultSetκ³Ό ν¨κ» μ¬μ©λλ©°, Datasourceμμ Connectionμ μ»μ΄μ SQLμ μ€ννλ€.    
  Thread-safe νμ§ μκΈ° λλ¬Έμ λ©ν° μ€λ λ νκ²½μμ λκΈ°ν μ²λ¦¬κ° νμνλ€.
  
  Stepμμ read() κ° νΈμΆ λλ©΄, JdbcCursorItemReader μμ fetchSize(chunkSize) λ§νΌ μ½μ΄μ¨ ν λλ €μ€λ€.
  
  - ### API
    JdbcCursorItemReaderBuilde<T>() λ₯Ό μ¬μ©νλ€.
    - .name(name)
    - .fetchSize(size)
    - .dataSource(DataSource)
    - .rowMapper(RowMapper)
      - λ°νλλ λ°μ΄ν°λ₯Ό κ°μ²΄μ λ§€ννκΈ° μν μ€μ .
    - .beanRowMapper()
      - RowMapper λμ  ν΄λμ€ νμμΌλ‘ μ€μ νλ€.
    - .sql(sql)
    - .queryArguments(Object ...)
      - μΏΌλ¦¬ νλΌλ―Έν° μ€μ 
    - .maxItemCount(int)
    - .currentItemCount(int)
        - μ‘°ν itemμ μμ μ§μ .
    - maxRows(int)
      - ResultSet μ€λΈμ νΈκ° ν¬ν¨ ν  μ μλ μ΅λ νμ μ.
    
    ```java
    @Bean
    public ItemReader<Member> itemReader() {
        return new JdbcCursorItemReaderBuilder<Member>()
            .name("jdbcCursorItemReader")
            .fetchSize(chunkSize)
            .sql("select id, name from member where name like ? order by id")
            .queryArguments("user%")
            .beanRowMapper(Member.class)
            .dataSource(dataSource)
            .build();
    }
    ```


- ### π JpaCursorItemReader
  <img alt="img_27.png" height="400" src="img_27.png" width="900"/>
  
  SpringBatch 4.3 λΆν° μ§μνλ€. `EntityManagerFactory` κ°μ²΄λ₯Ό νμλ‘νλ©° μΏΌλ¦¬λ `JPQL`λ‘ μμ±νλ€.   
  ItemStreamμμ Queryλ₯Ό ν΅ν΄ μμ±λ κ²°κ³Όλ₯Ό ResultStream μΌλ‘ κ°μ Έμ¨λ€. κ·Έ ν JpaCursorItemReader μμ Iteratorλ‘ ResultStreamμμ κ²°κ³Όλ₯Ό λ½μλΈλ€.
  
  - ### API
    JpaCursorItemReaderBuilder<T>() λ₯Ό μ¬μ©νλ©° κΈ°λ³Έμ μΈ APIλ JDBC λ°©μκ³Ό λΉμ·νλ€.
    - .queryString(String JPQL)
    - .EntityManagerFactory(EMF)
    - .parameterValue(Map<String, Object>)     
    ...
  
    ```java
    @Bean
    public ItemReader<Member> itemReader() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "user%");
    
        return new JpaCursorItemReaderBuilder<Member>()
            .name("jpaCursorItemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("select m from Member m where name like :name")
            .parameterValues(params)
            .build();
    }
    ```
  

- ### π JdbcPagingItemReader
  νμ΄μ§ κΈ°λ°μ κ΅¬νμ²΄λ‘ μμ rowμ λ²νΈ(offset)μ λ°νν  rowμ μ(limit)λ₯Ό μ§μ νμ¬ μ€ννλ€.    
  νμ΄μ§ λ¨μλ‘ λ°μ΄ν°λ₯Ό μ‘°νν  λλ§λ€ μλ‘μ΄ μΏΌλ¦¬κ° μ€νλκ³ , μ»€λ₯μμ λ§Ίλλ€. Thread-safe νλ€.
  
  μλ νλ‘μΈμ€λ Cursor μ μ μ¬νμ§λ§ λ€λ₯Έ μ μ΄λΌλ©΄ λ°μ΄ν°λ₯Ό κ°μ Έμ¬ λ νμ΄μ§ λ¨μλ‘ κ°μ Έμ€κΈ° λλ¬Έμ Mapperμ μν΄ κ°μ²΄μ Listκ° μμ±λλ€.
  
  #### PagingQueryProvider
  - μΏΌλ¦¬λ¬Έμ ItemReaderμκ² μ κ³΅νλ ν΄λμ€, λ°μ΄ν°λ² μ΄μ€ λ§λ€ λ€λ₯Έ μ’λ₯λ₯Ό μ¬μ©νλ€.    
  
  - ### API
    JdbcPagingItemReaderBuilder<T>() λ₯Ό μ¬μ©νλ€.
    - name(String)
    - pageSize(int)
    - dataSource(DataSource)
    - queryProvider(PagingQueryProvider)
    - rowMapper(Class<T>)
    - parameterValues(Map<String, Object>)
  
  - ### PagingQueryProviderμ κ° μ€μ 
    - selectClause(String), fromClause(String), whereClause(String), groupClause(String)
      - select, from, where, group μ .
    - sortKeys(Map<String, Order>)
      - μ λ ¬μ μν ν€ μ€μ . νμλ‘ μ§μ ν΄ μ£Όμ΄μΌ νλ€.
    ...
    
    ```java
    @Bean
    public ItemReader<Member> itemReader() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "user%");
  
        return new JdbcPagingItemReaderBuilder<Member>()
            .name("jdbcPagingItemReader")
            .pageSize(5)
            .dataSource(dataSource)
            .rowMapper(new BeanPropertyRowMapper<>(Member.class))
            .queryProvider(createQueryProvider())
            .parameterValues(params)
            .build();
    }
  
    @Bean
    public PagingQueryProvider createQueryProvider() throws Exception {
  
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
  
        SqlPagingQueryProviderFactoryBean providerFactoryBean = new SqlPagingQueryProviderFactoryBean();
        providerFactoryBean.setDataSource(dataSource);
        providerFactoryBean.setSelectClause("id, name");
        providerFactoryBean.setFromClause("member");
        providerFactoryBean.setWhereClause("name like :name");
        providerFactoryBean.setSortKeys(sortKeys);
  
        return providerFactoryBean.getObject();
    }
    ```
  
- ### π JpaPagingItemReader
  νμ΄μ§ κΈ°λ°μ JPA κ΅¬νμ²΄λ‘ JpaCursor λ°©μκ³Ό APIλ pageSize μ€μ μ μ μΈνκ³ λ λμΌνλ€.
  ```java
  @Bean
  public ItemReader<Member> itemReader() {
  Map<String, Object> params = new HashMap<>();
  params.put("name", "user%");
  
  return new JpaPagingItemReaderBuilder<Member>()
      .name("jpaCursorItemReader")
      .pageSize(5)
      .entityManagerFactory(entityManagerFactory)
      .queryString("select m from Member m where name like :name")
      .parameterValues(params)
      .build();
  }
  ```

## π§ ItemReaderAdapter
λ°°μΉ Job μμμ μ΄λ―Έ μλ κΈ°μ‘΄μ DAOλ λ€λ₯Έ μλΉμ€λ₯Ό ItemReader μμμ μ¬μ©νκ³ μ ν λ μ€νμ μμνλ μ­ν μ νλ€.    
(MemberService μ joinMember() λ©μλμ νΈμΆκ³Ό κ°μ΄), Javaμ λ¦¬νλ μ κΈ°μ μ μ¬μ©νλ€.

```java
@Bean
public ItemReader<Member> itemReader() {
    ItemReaderAdapter<Member> reader = new ItemReaderAdapter<>();
    reader.setTargetObject(MemberService());
    reader.setTargetMethod("readMember");

    return reader;
}
```

```java
public class MemberService {

    private long id= 0;

    public Member readMember() {
        if(id < 10) {
            return new Member(String.valueOf(++id), "user");
        }
        return null;
    }
}
```
κΈ°μ‘΄μ MemberServiceμ readMember() λ©μλλ₯Ό νΈμΆνμ¬ λ°μ΄ν°λ₯Ό νκ±΄μ© μ½μ΄μ¬ μ μλ€.    
10 λ²λ§ μ½κΈ° μν΄ `id < 10` μ‘°κ±΄μ μΆκ°νμλ€. μ½μ λ°μ΄ν°λ₯Ό κ°μ Έμ¨ ν ν΄λΉ λ¦¬μ€νΈλ₯Ό remove() νλ©° λ°μ΄ν°λ₯Ό μ½μ μ μλ€.

<br>

# π ItemWriter

## π§ FlatFileItemWriter
κ³ μ  μμΉ λλ νΉμ λ¬Έμμ μν΄ κ΅¬λ³λ λ°μ΄ν°μ νμ κΈ°λ‘νλ€.    
μμ±ν΄μΌν  Resourceμ Objectλ₯Ό StringμΌλ‘ λ³νν΄μ£Όλ LineAggregatorκ° νμνλ€.

### μμ±
- encoding
- append
  - νμΌμ΄ μ΄λ―Έ μ‘΄μ¬νλ κ²½μ° λ°μ΄ν°λ₯Ό μΆκ°ν  κ²μΈμ§ μ¬λΆ (κΈ°λ³Έκ° false, λ?μ΄μμ)
- Resource
- LineAggregator
  - Item μ λ°μ String μΌλ‘ λ³ννλ€.
    - **κ΅¬νμ²΄**
    - PassThroughLineAggregator: λ¨μ λ¬Έμμ΄λ‘ λ°ν
    - DelimitedLineAggregator: κ΅¬λΆμλ₯Ό μΆκ°νμ¬ λ¬Έμμ΄μ μμ±.
      - κΈ°λ³Έκ°μ `,` μ΄λ©°, `delimited().delimiter(String)` μΌλ‘ κ΅¬λΆμλ₯Ό μ§μ ν  μ μλ€.
    - FormatterLineAggregator: κ³ μ κΈΈμ΄λ‘ κ΅¬λΆνμ¬ λ¬Έμμ΄ μμ±
      - `formatted().format("%-3s|%-2s")` μ κ°μ΄ νμμ μ§μ νλ€.
  -  λ΄λΆμ μΌλ‘ `FieldExtractor` λ₯Ό μ¬μ©νλ€.
     - κ°μ²΄μ νλλ₯Ό μΆμΆν΄μ λ°°μ΄λ‘ λ§λ€μ΄ λ°ννλ€.
     - BeanWrapperFieldExtractor: κ°μ²΄μ νλλ₯Ό λ°°μ΄λ‘ λ°ν.
     - PassThroughFieldExtractor: μ λ¬λ°μ Collectionμ λ°°μ΄λ‘ λ°ν.
 
- FlatFileHeaderCallback, FlatFileFooterCallback
  - ν€λ, νΈν°λ₯Ό νμΌμ μ°κΈ°μν μΈν°νμ΄μ€.
  
![img_28.png](img_28.png)

FieldExtractor μμ νλλ₯Ό μΆμΆν΄ λ°°μ΄μ μμ±ν΄ λκ²¨μ£Όλ©΄ LineAggregator μμ κ΅¬λΆμλ₯Ό μΆκ°νμ¬ λ¬Έμμ΄μ μμ±νλ€.


### DelimitedLineAggregator
```java
@Bean
public ItemWriter<? super Member> itemWriter() {
    return new FlatFileItemWriterBuilder<>()
        .name("flatFileWriter")
        .resource(new FileSystemResource("/Users/a1101720/IdeaProjects/TIL/Spring/SpringBatch/src/main/resources/memberOut.csv"))
        .append(true)
        .delimited()
        .delimiter("|")
        .names(new String[] {"name", "id"})
        .build();
}
```

### FormatterLineAggregator
```java
@Bean
public ItemWriter<? super Member> itemWriter() {
    return new FlatFileItemWriterBuilder<>()
        .name("flatFileWriter")
        .resource(new FileSystemResource("/Users/a1101720/IdeaProjects/TIL/Spring/SpringBatch/src/main/resources/memberOut.csv"))
        .append(true)
        .formatted()
        .format("%-5s|%-2s")
        .names(new String[] {"name", "id"})
        .build();
}
```

## π§ XML-StaxEventItemWriter
 μ½μ λμ λμΌνκ² Resource, marshaller, rootTagName(μ‘°κ°λ¨μμ λ£¨νΈκ° λ  μ΄λ¦) μ΄ νμνλ€.

Marshallerμ μν΄ κ°μ²΄κ° XML μμλ‘ λ³νλλ€.

```java
@Bean
public ItemWriter<? super Member> itemWriter() {
    return new StaxEventItemWriterBuilder<>()
        .name("staxItemWriter")
        .resource(new FileSystemResource("/Users/a1101720/IdeaProjects/TIL/Spring/SpringBatch/src/main/resources/memberout.xml"))
        .rootTagName("member")
        .marshaller(itemMarshaller())
        .overwriteOutput(true) // λ?μ΄ μμ°κΈ°.
        .build();
}

@Bean
public Marshaller itemMarshaller() {
    Map<String, Class<?>> aliases = new HashMap<>();
    aliases.put("member", Member.class);
    aliases.put("name", String.class);
    aliases.put("id", String.class);
    
    XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
    xStreamMarshaller.setAliases(aliases);
    
    return xStreamMarshaller;
}
```
Marshaller μ μ€μ μ μ΄μ μ Readerμμμ λμΌνλ€.


## π§ JsonFileItemWriter
jsonObjectMarshaller μ μν΄ κ°μ²΄κ° Json νμμΌλ‘ λ³νλλ€. jsonObjectMarshaller λ λ΄λΆμ μΌλ‘ ObjectMapperλ₯Ό κ°μ§κ³  λ°μ΄ν°λ₯Ό λ§€ννλ€.

```java
@Bean
public ItemWriter<? super Member> itemWriter() {
    return new JsonFileItemWriterBuilder<>()
        .name("jsonFileItemWriter")
        .resource(new FileSystemResource("/Users/a1101720/IdeaProjects/TIL/Spring/SpringBatch/src/main/resources/memberout.json"))
        .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
        .append(true)
        .build();
}
```

## π§ JdbcBatchItemWriter
JDBCμ Batch κΈ°λ₯μ μ¬μ©νμ¬ bulk Insert, update, delete λ°©μμΌλ‘ μ²λ¦¬νκΈ° λλ¬Έμ μ±λ₯μ  μ΄μ μ κ°μ§λ€.

### API
- .dataSource(dataSource)
- .sql("insert into ")
- .assertUpdates(true)
  - νΈλμ­μ μ΄ν μ μ΄λ νλμ νμ΄ λ³κ²½λμ§ μμ κ²½μ° μμΈ λ°μ.  
- .beanMapped()
  - Pojo κΈ°λ°μΌλ‘ Insert SQLμ Values λ₯Ό λ§€ν
  - BeanPropertyItemSqlParameterSourceProvider κ° μ¬μ©λλ€.
- .columnMapped()
  - Key, Value κΈ°λ°μΌλ‘ Insert SQLμ valuesλ₯Ό λ§€ν
  - ColumnMapItemPreparedStatementSetter κ° μ¬μ©λλ€.


<img alt="img_29.png" height="400" src="img_29.png" width="900"/>

μΈ λμμ νμμ΄ Pojo νμμ κ°μ²΄λΌλ©΄ beanMapped()λ₯Ό, Map κ³Ό κ°μ΄ key, valueμ μμ΄λΌλ©΄ columnMapped()λ₯Ό μ¬μ©νλ€.

```java
@Bean
public ItemWriter<? super Member> itemWriter() {
    return new JdbcBatchItemWriterBuilder<>()
        .dataSource(dataSource)
        .sql("insert into member2(name, id) values(:name, :id)")
        .assertUpdates(true)
        .beanMapped()
        .build();
}
```

## π§ JpaItemWriter
JPA μν°ν° κΈ°λ°μΌλ‘ λ°μ΄ν°λ₯Ό μ²λ¦¬νλ€. μν°ν°λ₯Ό chunk ν¬κΈ°λ§νΌ insert, merge ν λ€μ flush νλ€.

### API
- .userPersist(false)
  - μν°ν°λ₯Ό persiste ν  κ²μΈμ§ μ¬λΆ. false μ΄λ©΄ merge μ²λ¦¬ νλ€.
- EntityManageFactory()

```java
@Bean
public ItemWriter<? super Member2> itemWriter() {
    return new JpaItemWriterBuilder<>()
        .usePersist(true) // default
        .entityManagerFactory(entityManagerFactory)
        .build();
}
```

## π§ ItemWriterAdapter
λ°°μΉ Job μμμ μ΄λ―Έ μλ μλΉμ€λ₯Ό ItemWriter μμΈμ μ¬μ©ν  λ μ΄λ₯Ό μμνλ€.

```java
@Bean
public ItemWriter<? super Member> itemWriter() {
    ItemWriterAdapter<Member> writerAdapter = new ItemWriterAdapter<>();
    writerAdapter.setTargetObject(MemberService());
    writerAdapter.setTargetMethod("writeMember");

    return new CustomItemWriter();
}
```
νκ² ν΄λμ€μ λ©μλλ₯Ό μ§μ ν΄μ€λ€. λ¦¬νλ μμ μ΄μ©ν΄ μ€νΈλλ€.

```java
public class MemberService {

    public void writeMember(Member member) {
        System.out.println(member);
    }
}
```
κΈ°μ‘΄μ ItemWriter μ κ΅¬νμ²΄μ λ¬λΌ μμ΄νμ νλμ© λκ²¨λ°μ μ²λ¦¬νλ€.

# π ItemProcessor κ΅¬νμ²΄

## π§ CompositeItemProcessor
<img alt="img_30.png" src="img_30.png" width="900"/>

ItemProcessor λ€μ μ°κ²°ν΄μ μμνλ©΄ κ° ItemProcessorλ₯Ό μ€νμν¨λ€.   
μ΄μ  ItemProcessor λ°ν κ°μ λ€μ ItemProcessor κ°μΌλ‘ μ°κ²°λλ€.

```java
@Bean
public ItemProcessor<? super Member, Member> itemProcessor() {
    List itemProcessors = new ArrayList<>();
    itemProcessors.add(new CustomItemProcessor());
    itemProcessors.add(new CustomItemProcessor2());

    return new CompositeItemProcessorBuilder<>()
        .delegates(itemProcessors)
        .build();
}
```
List λ‘ μ λ¬ν  μλ μκ³ , νλμ© μ²΄μ΄λμΌλ‘ μ λ¬ν  μ λ μλ€.

## π§ ClassifierCompositeItemProcessor

Classifierλ‘ λΌμ°ν ν¨ν΄μ κ΅¬νν΄μ λΆλ₯ μ‘°κ±΄μ λ°λΌ μ¬λ¬κ°μ ItemProcessor μ€ νλλ₯Ό νΈμΆνλ€.


```java
@Bean
public ItemProcessor<? super ProcessorInfo, ProcessorInfo> itemProcessor() {
    ClassifierCompositeItemProcessor processor = new ClassifierCompositeItemProcessor();
    ProcessorClassifier<ItemProcessor, ItemProcessor<?, ? extends ProcessorInfo>> processorClassifier = new ProcessorClassifier();

    Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap = new HashMap<>();
    processorMap.put(1, new ClassifiableItemProcessor());
    processorMap.put(2, new ClassifiableItemProcessor2());
    processorMap.put(3, new ClassifiableItemProcessor3());

    processorClassifier.setProcessorMap(processorMap);
    processor.setClassifier(processorClassifier);

    return processor;
}
```
μμ μ΄κΈ° λλ¬Έμ κ΅¬νλ μ½λμ μ§μ€νμ§ μμλ λλ€.   
`ClassifierCompositeItemProcessor` λ₯Ό μμ±νκ³ , μ»€μ€ννκ² μ μ ν Classifierλ₯Ό μ€μ ν΄ μ€ λ€ λ°ννλ€ μ λλ‘ λ³΄λ©΄ λκ² λ€.   
Classifier μμλ Classify() λ©μλμμ μ€νν  νλ‘μΈμ€λ₯Ό κ²°μ νλ€.



### π μ°Έμ‘°

> - https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B0%B0%EC%B9%98
> - https://fastcampus.app/course-detail/206067
