## 🧐 Spring Data JPA의 공통 인터페이

```java
@EnableJpaRepositories(basePackages = "com.datajpa.repository")
```
스프링 부트를 사용한다면 위의 애노테이션 설정이 필요없고, @SpringBootApplication 의 위치에서 부터 탐색하게 된다.

```java
public interface MemberRepository extends JpaRepository<Member, Long> {
}
```
```java

package org.springframework.data.jpa.repository;

public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    @Override
    List<T> findAll();
    T getById(ID id); // 프록시로 조회
    ...
}
```
```java
package org.springframework.data.repository;

public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    void delete(T entity);
    void deleteById(ID id);
    void deleteAll();
    long count();
    Iterable<T> findAll();
}
```
JpaRepository 인터페이스를 상속받으면 바로 해당 메서드들을 사용 가능하다.
스프링에서 자동으로 구현체를 주입해주게 되는데 이때 프록시를 이용하여 생성한 후 주입 해준다.
컴포넌트 스캔을 스프링 데이터 JPA가 자동으로 처리해 주기 때문에 @Repository 애노테이션도 생략이 가능하다. 또한 JPA 예외를 
스프링 예외로 변환하는 과정도 자동으로 처리 된다.

위의 두 인터페이스의 패키지를 보면 JpaRepository는 data.jpa.repository 에, CrudRepository는 data.repository 인 것을 확인 할 수 있다.
JpaRepository는 Jpa에 특화된 인터페이스고, CrudRepository는 spring Data MongoDB 등 에서도 공통으로 사용할 수 있는 Repository 이다.



![img.png](img.png)    
JpaRepository 의 메서드들 뿐만 아니라 그 부모의 메서드들 또한 사용이 가능하다. CrudRepository 를 상속해도 되지만
보통 JpaRepository를 상속받아 사용한다.

QueryByExampleExecutor 에서는 메서드 이름으로 쿼리를 작성할 수 있도록 해준다.