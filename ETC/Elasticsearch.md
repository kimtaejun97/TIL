
## π Elasticsearch?
*Apache Lucene κΈ°λ°μ Java μ€νμμ€ λΆμ° κ²μ μμ§μΌλ‘, λ°©λν μμ λ°μ΄ν°μμμ λΉ λ₯Έ μλλ‘ μ μ₯, κ²μ, λΆμμ΄ κ°λ₯ν μμ§μ΄λ€.
> π‘ Apache Lucene? μλ° μΈμ΄λ‘ μ΄λ£¨μ΄μ§ μ λ³΄ κ²μ λΌμ΄λΈλ¬λ¦¬ μμ -μ€ν μμ€ μννΈμ¨μ΄ 

inverted index λ°©μμ μ¬μ©νμ¬ λΉ λ₯Έ μλλ‘ κ²μν  μ μλ€.    
κ·Έλ₯ index λ°©μκ³Όλ λ¬΄μμ΄ λ€λ₯Έκ°? λ³΄λμ RDBμμλ νμ΄λΈ κ΅¬μ‘°λ‘ λ°μ΄ν°λ₯Ό μ μ₯νκΈ° λλ¬Έμ νΉμ  ν€μλλ₯Ό μ°Ύκ³ μ νλ€λ©΄, λͺ¨λ  λ¬Έμλ€μ νμνλ©° ν΄λΉ ν€μλκ°
λ€μ΄μλ λ¬Έμλ€μ μ°Ύμ κ°μ Έμ¬ κ²μ΄λ€. μ΄κ²μ μΈλ±μ€λ₯Ό μ¬μ©νλλΌλ νμνλ λ¬Έμκ° μ€μ΄λ€ λΏ λ§μ°¬κ°μ§μ΄λλ€.     
νμ§λ§ μ­μμΈ λ°©μμμλ textλ€μ μΆμΆνμ¬ ν΄λΉ νμ€νΈκ° μ΄λ€ λ¬Έμμ μ μ₯λμ΄μλμ§λ₯Ό λνλ΄λ λ°©μμΌλ‘ λ°μ΄ν°λ₯Ό μ μ₯νκΈ° λλ¬Έμ νΉμ  λ°μ΄ν°λ₯Ό νμν  λ λΉ λ₯Έ μλλ₯Ό λ³΄μΈλ€.

> ESλ μ΄λ€ νμ€νΈκ° μ΄λ€ λ¬Έμμ μλμ§, RDBλ μ΄λ€ λ¬Έμμ μ΄λ€ λ°μ΄ν°κ° μλμ§.

- #### RDBMS
  | ID | Text |   
  |-----|-----|
  |doc1|AB|
  |doc2|A|
  |doc3|BC|

- #### Elasticsearch
  | Text | ID |
  |------|----|
  |A|doc1, doc2|
  |B|doc1, doc3|
  |C|doc3|

- ### π RDB μμ λΉκ΅
  | RDB | ES |
  |----|----|
  | Database | Index |
  | Table | Type |
  | Row | Document |
  | Column | Field |
  | Schema | Mapping |
  | Pysical partition | Shard |


## π§ ν΄λ¬μ€ν°μ μ€λ
ν΄λ¬μ€ν°λ νλ μ΄μμ λΈλκ° λͺ¨μΈ κ²μΌλ‘, ν¬ν¨λ λΈλμμ ν΅ν©λ μμΈν, κ²μμ κ°λ₯νκ² νλ€.     
λμ€μ»€λ²λ¦¬ κ³Όμ μ μν΄ λΈλλ€μ ν΄λ¬μ€ν°λ‘ λ°μΈλ© λλ€.(ν΄λ¬μ€ν°λͺμ κΈ°μ€μΌλ‘)       
λΈλκ°μ ν΅μ μ 93xx ν¬νΈμμ μ΄λ£¨μ΄μ§λ€. μΌλ°μ μΌλ‘λ 1κ°μ λ¬Όλ¦¬ μλ²μ νλμ λΈλλ₯Ό μ€ννλ κ²μ κΆμ₯νλ, νλμ μλ²μμ μ¬λ¬κ°μ λΈλλ₯Ό μ€ννλ κ² λν κ°λ₯νλ€.

μ€λλ λ°μ΄ν°λ₯Ό μν λΆν ν μ‘°κ°μΌλ‘, μ¬λ¬ μ€λμ λ°μ΄ν°λ₯Ό λΆμ°νκ³ , λ³λ ¬μ²λ¦¬νμ¬ κ²μ μ²λ¦¬λμ λλ¦΄ μ μλ€.   
μΈλ±μ€λ₯Ό μμ±ν λμ μ€λμ μμ± μμ κΈ°λ³Έκ°μ 1μΈλ€. (6.x μ΄νλ 5κ°), ν΄λ¬μ€ν°μ λΈλλ₯Ό μΆκ°νλ©΄ μ€λλ€μ΄ κ° λΈλλ‘ λΆμ°λκ² λκ³ ,    
κΈ°λ³Έμ μΌλ‘ 1κ°μ `Replica`λ₯Ό μμ±νλ€. λ νλ¦¬μΉ΄λ μλ³ΈμΈ μ€λμ λ€λ₯Έ λΈλμ μ μ₯λκ² λλ€. λ νλ¦¬μΉ΄λ μλ³Έ μ€λμ μ μ€μ΄ μΌμ΄λ¬μ λ μ΄λ₯Ό λμ²΄νλ€.    
λ€μκ³Ό κ°μ΄ μ€λμ λ νλ¦¬μΉ΄ μλ₯Ό μΈνν  μ μλ€. 
```
curl -XPUT "http://localhost:9200/users" -d'
{
  "settings": {
    "number_of_shards": 4,
    "number_of_replicas": 1
  }
}'
```
> π‘ Spring Boot μμ ESλ₯Ό μ¬μ©νκ² λλ©΄ μ€λμ λ νλ¦¬μΉ΄ μμ μΈν μ λΈνμ΄μμ Deprecated λμ΄μλ€.

- ## π§ CRUD
  Elasticsearch λ Http API κΈ°λ°μΌλ‘ λμνλ©° λ°μ΄ν°μ μ½μμ `PUT`, μ‘°νλ `GET`, μμ μ `POST` μ­μ λ `DELETE`λ‘ μ΄λ£¨μ΄μ§λ€.

  - ### π PUT
    - 6.x μ΄μ  λ²μ  κΉμ§λ `http://HOST:PORT/${index}/{doc type}/${doc_id}`  
    - 7.0 λΆν°λ `Type`μ κ°λμ΄ μ κ±°λκ³  `_doc` μΌλ‘ κ³ μ  μ κ·Όνλ€. `http://HOST:PORT/${index}/_doc/${doc_id}`
    ```
    curl -XPUT http://localhost:9200/users/_dox/1 -H 'Content-Type: application/json' -d '
    {
        "name": "kim",
        "age": 26
    }'
    ```
      - `-H`: ν€λ μΆκ°.
      - `-d`: json νμμΌλ‘
      - μ²μ PUTμ λ³΄λ΄λ©΄ resultμ createdλ‘ νμλκ³ , λμΌν urlμ λ€λ₯Έ documentλ₯Ό λ€μ μλ ₯νλ©΄ κΈ°μ‘΄ λ¬Έμλ₯Ό λ?μ΄μμ°κ² λλ€. μ΄λλ updateλ‘ νκΈ°λλ€.
      - κ³ μ μ `_doc` λμ  `_create`λ₯Ό μ¬μ©νμ¬ κΈ°μ‘΄μ λ¬Έμκ° μμ λλ¬Έ μλ‘ μΆκ°νλ κ²μ νμ©νλλ‘ ν  μ μλ€.
  
  - ### π POST
    - PUTκ³Ό μ μ¬νκ² λ°μ΄ν° μλ ₯μ μ¬μ©κ°λ₯νλ€. PUT κ³Όλ λ¬λ¦¬ IDμ μλ μμ±μ΄ κ°λ₯νλ€. μ΄ λλ IDλ₯Ό λͺμν΄μ£Όμ§ μμΌλ©΄ λλ€.    
    `http://HOST:PORT/${index}/_doc` κΈ°λ³Έμ μΌλ‘ String νμμ λλ€ν κ°μ΄ idλ‘ μ§μ λλ€.
    
    - `_updata`λ₯Ό μ¬μ©νμ¬ κΈ°μ‘΄μ λ¬Έμλ₯Ό λ?μ΄ μμ°μ§ μκ³  μΌλΆλΆλ§μ μμ ν  μ μλ€.
    ```
    POST http://localhost:9200/users/_update/1 -d '
    {
        "doc": {
           "age": 27
        }
    }'
    ```
  
  - ### π GET
    - #### idλ‘ μ‘°ν.
      - `curl -XGET http://HOST:PORT/${index}/_doc/${doc_id}`
      
    - #### index λ¬Έμ μ μ²΄ μ‘°ν.(_search)
      - `curl -XGET http://HOST:PORT/${index}/_doc/_search?pretty}`
    - #### νΉμ  νλκ°μΌλ‘ μ‘°ν.(_search)
      - `curl -XGET http://HOST:PORT/${index}/_doc/_search?q=name:kim&pretty}`
      - `curl -XGET http://HOST:PORT/${index}/_doc/_search?q=name:kim AND park&pretty}`
    - #### νΉμ  κ°μΌλ‘ μ‘°ν.(_search)
      - `curl -XGET http://HOST:PORT/${index}/_doc/_search?q=kim AND park&pretty}`
        - AND, OR, NOT μ λΌλ¦¬ μ°μ°μ΄ μ¬μ©κ°λ₯νλ©° λλ¬Έμλ‘ μ¬μ©.
  
    - #### λ°μ΄ν° λ³Έλ¬ΈμΌλ‘ μ‘°ν (match μΏΌλ¦¬)
      ```
      curl -XGET http://HOST:PORT/${index}/_search
      {
        "query": {
          "match": {
            "name": "kim"
          }
        }
      }
      ```
      
    - #### μ¬λ¬ μΈλ±μ€λ₯Ό μ‘°ν
      - `curl -XGET http://HOST:PORT/${index1}, ${index2}/_search}`
      - `curl -XGET http://HOST:PORT/*/_search}` (μμΌλμΉ΄λ μ¬μ©)
      

  - ### π DELETE
    - μμ΄λλ‘ λ¬Έμ μ­μ 
      - `curl -XDELETE http://HOST:PORT/${index}/_doc/${doc_id}`
    - μΈλ±μ€ μ μ²΄ μ­μ 
      - `curl -XDELETE http://HOST:PORT/${index}`


- ### π Bulk
  λ°λ‘ λ°λ‘ μννλ κ²λ³΄λ€ νλ²μ μ€ννκΈ° λλ¬Έμ μλκ° ν¨μ¬ λΉ λ₯΄λ€. κ·Έλ¬λ Elasticsearchμλ μ»€λ°, λ‘€λ°±μ νΈλμ­μ κ°λμ΄ μκΈ° λλ¬Έμ
  λ²ν¬ μμμ€ μλμΉ μκ² μ€λ¨λμλ€λ©΄ μ΄λκΉμ§ μ°μ°μ΄ μ§νλμλμ§ νμΈν  μ μλ€. λ°λΌμ μ μ²΄ μΈλ±μ€λ₯Ό μ­μ νκ³  λ€μ νλ κ²μ΄ μμ νλ€.
  - `POST _bulk` λλ `POST ${index}/_bulk`
  - νμΌμ λ΄μ©μ μ μ₯νκΈ°
    - POST --data-ninary@*.json
    
  
  
      
    
  
  
## π Docker λ‘ μμνκΈ°

- install docker image
    ```
    docker pull docker.elastic.co/elasticsearch/elasticsearch:8.1.0
    ```
    νμ¬ Spring Boot 2.6.4 κΈ°μ€μΌλ‘ spring-data-elasticsearchλ 4.3.2κ° μΆκ°λκ³ , ν΄λΉ λ²μ μ λ§λ μλΌμ€ν±μμΉ λ²λ²μ 7.15.2 μ΄λ€.
    > νΈνμ± νμΈ: https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#new-features

- Create new docker network
    ```
    docker network create elastic
    ```

- Start Elasticsearch
    ```
    docker run --name cxdatalogES -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.0
    ```
    9200λ² ν¬νΈλ http ν΅μ μ μ μν΄, 9300μ λΈλκ°μ ν΅μ μ μν΄ μ¬μ©λλ€.    


## π Spring Data Elasticsearch

- #### μμ‘΄μ± μΆκ°
    ```
    implementation 'org.springframework.boot:spring-boot-starter-data-elasticsearch'
    ```

- ### Configuration
    ```java
    @EnableElasticsearchRepositories
    @Configuration
    public class ElasticsearchConfig extends ElasticsearchConfigurationSupport {
    
        @Bean
        public RestHighLevelClient elasticsearchClient() {
            ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
    
            return RestClients.create(clientConfiguration).rest();
        }
    }
    ```
    - @EnableElasticsearchRepositories λ‘ λ€μμ μ¬μ©ν ElasticsearchRepositoryμ μ¬μ©μ νμ±ν μμΌμ€λ€.
    - μΌλ°μ μΌλ‘ μ¬μ©νλ RestHighLevelClientλ₯Ό μ¬μ©νλ€.
    - μλΌμ€ν± μμΉλ₯Ό λμ΄ 9200ν¬νΈλ₯Ό μ§μ ν΄μ€λ€.
    
- ### Document
    ```java
    @NoArgsConstructor
    @Getter
    @Document(indexName = "data_logs")
    public class DataLog {
    
        @Id
        private String id;
        
        private String ordNo;
        ...
    
        @PersistenceConstructor
        public DataLog(final String ordNo) {
            this.ordNo = ordNo;
        }
    }
    ```
    - JPAμ μν°ν°μ μ μ¬νλ€.
    - κ·Έλ¬λ Id λ κΈ°λ³Έμ μΌλ‘ String νμμΌλ‘ μλμμ±λλ€.
    - @PersistenceConstructor: ESμμ λ°μ΄ν°λ₯Ό κ°μ²΄ν ν  λ μ¬μ©ν  μμ±μ, λ³μ μ΄λ¦μ μν΄ λ§€νλλ€.
    
- ### Repository
    ```java
    public interface DataLogRepository extends ElasticsearchRepository<DataLog, String> {
    
        List<DataLog> findByOrdNo(String ordNo);
    }
    ```
    - JPAμ μ μ¬νκ² μ¬μ©ν  μ μλ€.
    - κ·Έλ¬λ @Query λ₯Ό μ΄μ©ν μΏΌλ¦¬μ μμ±μ JSON νμμ΄λ€.



### π§ Elasticsearch TestContainer

- μμ‘΄μ± μΆκ°.
```groovy
testImplementation 'org.testcontainers:elasticsearch:1.16.3'
testImplementation 'org.testcontainers:junit-jupiter:1.16.3'
```
dockerλ κΈ°λ³Έμ μΌλ‘ μ€μΉλμ΄μμ΄μΌ νλ€.

- Config
```java
@EnableElasticsearchRepositories
@TestConfiguration
public class TestConfig {

    public ElasticsearchContainer elasticsearchContainer() {
        ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer(
            "docker.elastic.co/elasticsearch/elasticsearch:7.10.0");
        elasticsearchContainer.start();

        return elasticsearchContainer;
    }

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(elasticsearchContainer().getHttpHostAddress())
            .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
```
config νμΌμ μμ±νλ€. μ¬μ©ν  μλΌμ€ν±μμΉμ λ²μ μ λͺμ.


```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestConfig.class})
class ElasticsearchTest {
    ...
}
```
RANDOM_PORT λ‘ ν¬νΈλ₯Ό μ€μ νμ¬ λμμ£Όκ³ , μ΄μ μ λ§λ€μ΄μ€ Config νμΌμ μ½λλ‘ μ€μ νλ€.

#### κΈ°μ‘΄μ Elasticsearch Config μ μΆ©λ
```yml
spring:
  profiles:
    active: test
```
κΈ°μ‘΄μ Elasticsearch Config μ μ€νμ profileμ΄ Testκ° μλλλ§ μ€ννλλ‘ νλ€λμ§ νκ³ , Testλ₯Ό μ§νν  λμλ Test profileλ‘ μ€ννλλ‘ μ€μ νλ€.
- λμ»€ μ»¨νμ΄λκ° λμμ§κ³ , νμ€νΈκ° μ’λ£λλ©΄ λ΄λ €κ°λ€.

![img.png](img.png)


<br><br>
### π μ°Έμ‘°
> - https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html
> - https://esbook.kimjmin.net
> - https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#new-features
> - https://tecoble.techcourse.co.kr/post/2021-10-19-elasticsearch/
