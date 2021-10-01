# 📌 JPA와 DB 설정
****
### 🧐 application 설정
```yaml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/jpashop
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      #      show_sql: true
      hibernate.format_sql: true

logging:
  level:
    org.hibernate:
      SQL: DEBUG
      type: TRACE
```
- show_sql 과 logging level DEBUG 는 둘다 쿼리를 보여주는 역할을 하지만, show sql은 print이기 때문에 logging을 이용하는 것이 일반적으로 더 좋다.
- format_sql 은 발생된 sql을 줄바꿈을 통해 보기 좋게 정렬해준다.
- ```logging.level.org.hibernate.type: TRACE``` 는 바인딩된 파라미터의 값을 보여준다.
  - 바인딩 값을 보여주는 라이브러리 :p6spy 등을 사용해도 된다.
### 🧐 Repository
```java
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);

    }
}
```
- #### @PersistenceContext : EntityManagerFactory 를 생성할 필요없이 Spring Boot 에서 관리해준다. 

### 🧐 Test Code
```java
@Transactional
@SpringBootTest
//@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("Member 저장")
    @Test
    void saveTest(){
        // given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long saveId = memberRepository.save(member);

        //then
        assertThat(memberRepository.find(saveId)).isEqualTo(member);
    }
}
```
- #### @Transactional: EntityManager를 통한 모든 데이터 변경은 Transaction 안에서 이루어 져야 한다.
    - Test 환경에서 @Transactional을 사용하게 되면, Test가 끝난 뒤 DB를 Rollback 시킨다.
- #### @Rollback(false): 테스트를 종료한 후 Rollback하지 않고 Commit
- asserThat에서는 member와 find로 가져온 member을 '=='비교를 실행하게 되는데, 같은 transaction 안에서 이루어졌기 때문에 두 엔티티는 같음이 보장된다.
    - 영속성 컨텍스트에서 가져오기 때문에 같다.(실제로 select 쿼리가 발생하지 않는다.)
    
