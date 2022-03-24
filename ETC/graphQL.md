## 🧐 graphQL이란?
API를 위한 쿼리 언어로 쿼리를 런타임으로 수행하기 위한 언어.   
하나의 엔드포인트(/graphql)로 원하는 데이터만을 가져올 수 있다. (10개의 필드가 있다면 하나의 엔드포인트로 2^10 가지 방법으로 데이터를 가져올 수 있다)

#### - 요청
```
{
    user {
        name
        age
    }
}
```

#### - 응답
```
{
    user {
        name: "kim"
        age: 26
    }
}
```

동일한 엔드포인트에 name 만을 질의 한다면 name만을 돌려준다. 


## 📌 Spring Boot + graphQL

- 의존성 추가
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
  - 파일의 확장자는 `.graphqls`
  - type을 정의한다. 필드명과 매핑되며 타입을 정의한다.
  - 리스트 타입을 정의하고 싶다면 `[]`
  - Non null을 정의하고 싶다면 `data!`
  - Query를 정의하여 메서드와 매핑할 수 있다(메서드 명과 반환 타입)
    - 반환 타입의 이름은 type의 이름이 아닌 type의 필드의 구성과 이름을 따른다.
  

- #### DataLog
  ```java
  @AllArgsConstructor
  public class DataLog {
  
    private String ordNo;
    private String data;
  }
  ```

- #### DataLogResponse (dataLogs 타입)
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


#### - 요청
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

#### - 응답
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


동일한 엔드 포인트로 다음과 같이 요청을 보내게 되면
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
해당하는 데이터만을 반환해주는 것을 볼 수 있다.   
그러나 단점으로는 모든 필드를 반환받고 싶어도 와일드카드가 없고, 모든 필드를 명시해 주어야 한다.
> https://github.com/graphql/graphql-spec/issues/127

  

### 🔑 참조
> - https://graphql-kr.github.io/
> - https://graphql.org/learn/
> - https://jinhokwon.github.io/springboot/springboot-graphql/
> - https://github.com/graphql-java-kickstart/graphql-spring-boot#using-gradle
