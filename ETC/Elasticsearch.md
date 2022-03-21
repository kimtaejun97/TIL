


## 🤔 Docker 로 시작하기

- install docker image
    ```
    docker pull docker.elastic.co/elasticsearch/elasticsearch:8.1.0
    ```
    현재 Spring Boot 2.6.4 기준으로 spring-data-elasticsearch는 4.3.2가 추가되고, 해당 버전에 맞는 엘라스틱서치 버번은 7.15.2 이다.
    > 호환성 확인: https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#new-features

- Create new docker network
    ```
    docker network create elastic
    ```

- Start Elasticsearch
    ```
    docker run --name cxdatalogES -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.0
    ```
    9200번 포트는 http 통신신을 위해, 9300은 노드간의 통신을 위해 사용된다.    


## 🧐 Spring Data Elasticsearch

- #### 의존성 추가
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
    - @EnableElasticsearchRepositories 로 뒤에서 사용한 ElasticsearchRepository의 사용을 활성화 시켜준다.
    - 일반적으로 사용하는 RestHighLevelClient를 사용한다.
    - 엘라스틱 서치를 띄운 9200포트를 지정해준다.
    
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
    - JPA의 엔티티와 유사하다.
    - 그러나 Id 는 기본적으로 String 타입으로 자동생성된다.
    - @PersistenceConstructor: ES에서 데이터를 객체화 할 때 사용할 생성자, 변수 이름에 의해 매핑된다.
    
- ### Repository
    ```java
    public interface DataLogRepository extends ElasticsearchRepository<DataLog, String> {
    
        List<DataLog> findByOrdNo(String ordNo);
    }
    ```
    - JPA와 유사하게 사용할 수 있다.
    - 그러나 @Query 를 이용한 쿼리의 작성은 JSON 형식이다.



<br><br>
### 🔑 참조
> - https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html
> - https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#new-features
> - https://tecoble.techcourse.co.kr/post/2021-10-19-elasticsearch/
