## π§ graphQLμ΄λ?
APIλ₯Ό μν μΏΌλ¦¬ μΈμ΄λ‘ μΏΌλ¦¬λ₯Ό λ°νμμΌλ‘ μννκΈ° μν μΈμ΄.   
νλμ μλν¬μΈνΈ(/graphql)λ‘ μνλ λ°μ΄ν°λ§μ κ°μ Έμ¬ μ μλ€. (10κ°μ νλκ° μλ€λ©΄ νλμ μλν¬μΈνΈλ‘ 2^10 κ°μ§ λ°©λ²μΌλ‘ λ°μ΄ν°λ₯Ό κ°μ Έμ¬ μ μλ€)

#### - μμ²­
```
{
    user {
        name
        age
    }
}
```

#### - μλ΅
```
{
    user {
        name: "kim"
        age: 26
    }
}
```

λμΌν μλν¬μΈνΈμ name λ§μ μ§μ νλ€λ©΄ nameλ§μ λλ €μ€λ€. 


## π Spring Boot + graphQL

- μμ‘΄μ± μΆκ°
    ```groovy
    implementation 'com.graphql-java-kickstart:graphql-spring-boot-starter:12.0.0'
    testImplementation 'com.graphql-java-kickstart:graphql-spring-boot-starter-test:12.0.0'
    ```

- #### Schema(src/main/resources)
  ```graphql
  type dataLog {
      ordNo: String
      data: String
     
   }
    
  type dataLogs {
      logs: [dataLog]
  }
  
  type Query {
      getLogs(ordNo: String!): dataLogs
  }
  ```
  - νμΌμ νμ₯μλ `.graphqls`
  - typeμ μ μνλ€. νλλͺκ³Ό λ§€νλλ©° νμμ μ μνλ€.
  - λ¦¬μ€νΈ νμμ μ μνκ³  μΆλ€λ©΄ `[]`
  - Non nullμ μ μνκ³  μΆλ€λ©΄ `data!`
  - Queryλ₯Ό μ μνμ¬ λ©μλμ λ§€νν  μ μλ€(λ©μλ λͺκ³Ό λ°ν νμ)
    - λ°ν νμμ μ΄λ¦μ typeμ μ΄λ¦μ΄ μλ typeμ νλμ κ΅¬μ±κ³Ό μ΄λ¦μ λ°λ₯Έλ€.
  

- #### DataLog
  ```java
  @AllArgsConstructor
  public class DataLog {
  
    private String ordNo;
    private String data;
  }
  ```

- #### DataLogResponse (dataLogs νμ)
  ```java
  @AllArgsConstructor
  public class DataLogsResponse {
  
      private List<DataLog> logs;
  }
  ```

- #### GraphQLQueryResolver
  ```java
  @RequiredArgsConstructor
  @Component
  public class DataLogGraphQueryResolver implements GraphQLQueryResolver {
  
      private final DataLogService dataLogService;
  
      public DataLogsResponse getLogs(String ordNo) {
          return new DataLogsResponse(dataLogService.findByOrderNumber(ordNo));
      }
  }
  ```


#### - μμ²­
```graphql
query {
    getLogs(ordNo: "202203211234556789") {
        logs {
            dataSeq
            data
            ordNo
        }
    }
}
```

#### - μλ΅
```json
{
  "data": {
    "getLogs": {
      "logs": [
        {
          "data": "test data",
          "ordNo": "202203211234556789"
        },
        {
          "data": "test data",
          "ordNo": "202203211234556789"
        },
        {
          "data": "test data",
          "ordNo": "202203211234556789"
        }
      ]
    }
  }
}
```
<br><br>


λμΌν μλ ν¬μΈνΈλ‘ λ€μκ³Ό κ°μ΄ μμ²­μ λ³΄λ΄κ² λλ©΄
```graphql
query {
    getLogs(ordNo: "202203211234556789") {
        logs {
            data
        }
    }
}
```

```json
{
    "data": {
        "getLogs": {
            "logs": [
                {
                    "data": "test data"
                },
                {
                    "data": "test data"
                },
                {
                    "data": "test data"
                }
            ]
        }
    }
}
```
ν΄λΉνλ λ°μ΄ν°λ§μ λ°νν΄μ£Όλ κ²μ λ³Ό μ μλ€.   
κ·Έλ¬λ λ¨μ μΌλ‘λ λͺ¨λ  νλλ₯Ό λ°νλ°κ³  μΆμ΄λ μμΌλμΉ΄λκ° μκ³ , λͺ¨λ  νλλ₯Ό λͺμν΄ μ£Όμ΄μΌ νλ€.
> https://github.com/graphql/graphql-spec/issues/127


## π Spring Boot μμ GraphQLλ‘ API μμ²­ λ³΄λ΄κΈ°
- μμ‘΄μ±
```groovy
implementation 'com.graphql-java-kickstart:graphql-webclient-spring-boot-starter:1.0.0'
```



- ### resourceλ‘ μΏΌλ¦¬ μμ± (.graphql)

  ```graphql
  #logDataSchema.graphqls
  type dataLog {
      dataSeq: String
      dataType: String
      dataTypeDtls: String
      data: [String]
      ordNo: String
      prdNo: String
      mallClf: String
      mallClfDtlCd: String
      createIp: String
      createNo: String
      createDt: String
  }
  
  type Query {
      findDataLogByOrdNo(ordNo: String!, requiredDataFields: [String]): [dataLog]
  }
  ```
  
  ```graphql
  # dataLogQuery.graphql
  query findDataLogByOrdNo($ordNo : String!, $requiredDataFields : [String]) {
      findDataLogByOrdNo(ordNo : $ordNo, requiredDataFields : $requiredDataFields) {
          dataSeq
          dataType
          data
          ordNo
          prdNo
         
      }
  }
  ```
  
  ```java
  WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/graphql")
            .build();
  
  GraphQLRequest resourceRequest = GraphQLRequest.builder()
      .resource("dataLogQuery.graphql")
      .variables(new HashMap<>() {
          {
              put("ordNo", "20220322399038900");
              put("requiredDataFields", new String[] {"orderProductBO", "updateDt"});
          }
      })
      .build();
  
  GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, new ObjectMapper());
  GraphQLResponse response = graphQLWebClient.post(resourceRequest)
      .block();
  
  List<DataLogREsponseDto> dataLogs = response.getList("findDataLogByOrdNo", DataLogResponseDto.class);
  ```
  Mapμ μ΄μ©νμ¬ νλΌλ―Έν°μ κ°μ λ°μΈλ© ν΄μ€λ€.

- ### StringμΌλ‘ μΏΌλ¦¬ μμ± (.graphql)
  String μΌλ‘ μμ±νκ² λλ©΄ μ»κ³  μΆμ νλλ₯Ό λμ μΌλ‘ μ»λκ² κ°λ₯ν΄μ§λ€.   
  graphql νμΌλ€μ΄ νμμκ³  GraphQLRequestλ₯Ό λ§λλ κ² λΉΌκ³ λ resourceλ₯Ό μ΄μ©ν΄ μμ²­μ λ³΄λΌλ μ½λμ λμΌνλ€.
  ```java
  GraphQLRequest stringRequest = GraphQLRequest.builder()
  .query("query {"
  + "     findDataLogByOrdNo(ordNo : \"20220322399038900\", requiredDataFields : [\"orderProductBO\", \"updateDt\"]) {"
  + "         dataSeq"
  + "         dataType "
  + "         data "
  + "         ordNo "
  + "         prdNo "
  + "         mallClf "
  + "         createIp "
  + "         createDt "
  + "         createNo"
  + "     }"
  + "}")
  .build();
  ```
  

### π μ°Έμ‘°
> - https://graphql-kr.github.io/
> - https://graphql.org/learn/
> - https://jinhokwon.github.io/springboot/springboot-graphql/
> - https://github.com/graphql-java-kickstart/graphql-spring-boot#using-gradle
