## ๐ง Spring Data JPA์ ๊ณตํต ์ธํฐํ์ด์ค

```java
@EnableJpaRepositories(basePackages = "com.datajpa.repository")
```
์คํ๋ง ๋ถํธ๋ฅผ ์ฌ์ฉํ๋ค๋ฉด ์์ ์ ๋ธํ์ด์ ์ค์ ์ด ํ์์๊ณ , @SpringBootApplication ์ ์์น์์ ๋ถํฐ ํ์ํ๊ฒ ๋๋ค.

```java
public interface MemberRepository extends JpaRepository<Member, Long> {
}
```
```java

package org.springframework.data.jpa.repository;

public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    @Override
    List<T> findAll();
    T getById(ID id); // ํ๋ก์๋ก ์กฐํ
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
JpaRepository ์ธํฐํ์ด์ค๋ฅผ ์์๋ฐ์ผ๋ฉด ๋ฐ๋ก ํด๋น ๋ฉ์๋๋ค์ ์ฌ์ฉ ๊ฐ๋ฅํ๋ค.
์คํ๋ง์์ ์๋์ผ๋ก ๊ตฌํ์ฒด๋ฅผ ์ฃผ์ํด์ฃผ๊ฒ ๋๋๋ฐ ์ด๋ ํ๋ก์๋ฅผ ์ด์ฉํ์ฌ ์์ฑํ ํ ์ฃผ์ ํด์ค๋ค.
์ปดํฌ๋ํธ ์ค์บ์ ์คํ๋ง ๋ฐ์ดํฐ JPA๊ฐ ์๋์ผ๋ก ์ฒ๋ฆฌํด ์ฃผ๊ธฐ ๋๋ฌธ์ @Repository ์ ๋ธํ์ด์๋ ์๋ต์ด ๊ฐ๋ฅํ๋ค. ๋ํ JPA ์์ธ๋ฅผ 
์คํ๋ง ์์ธ๋ก ๋ณํํ๋ ๊ณผ์ ๋ ์๋์ผ๋ก ์ฒ๋ฆฌ ๋๋ค.

์์ ๋ ์ธํฐํ์ด์ค์ ํจํค์ง๋ฅผ ๋ณด๋ฉด JpaRepository๋ data.jpa.repository ์, CrudRepository๋ data.repository ์ธ ๊ฒ์ ํ์ธ ํ  ์ ์๋ค.
JpaRepository๋ Jpa์ ํนํ๋ ์ธํฐํ์ด์ค๊ณ , CrudRepository๋ spring Data MongoDB ๋ฑ ์์๋ ๊ณตํต์ผ๋ก ์ฌ์ฉํ  ์ ์๋ Repository ์ด๋ค.



![img.png](img.png)    
JpaRepository ์ ๋ฉ์๋๋ค ๋ฟ๋ง ์๋๋ผ ๊ทธ ๋ถ๋ชจ์ ๋ฉ์๋๋ค ๋ํ ์ฌ์ฉ์ด ๊ฐ๋ฅํ๋ค. CrudRepository ๋ฅผ ์์ํด๋ ๋์ง๋ง
๋ณดํต JpaRepository๋ฅผ ์์๋ฐ์ ์ฌ์ฉํ๋ค.



## ๐ง ์ฟผ๋ฆฌ ๋ฉ์๋
์คํ๋ง ๋ฐ์ดํฐ Jpa ์์ ์ฟผ๋ฆฌ ๋ฉ์๋๋ฅผ ์ฌ์ฉํ๋ ๋ฐฉ๋ฒ์ 3๊ฐ์ง๊ฐ ์๋ค.
- ๋ฉ์๋ ์ด๋ฆ์ผ๋ก ์ฟผ๋ฆฌ ์์ฑ
- ๋ฉ์๋ ์ด๋ฆ์ผ๋ก JPA NamedQuery ํธ์ถ
- @Query ์ ๋ธํ์ด์

### โ๏ธ ๋ฉ์๋ ์ด๋ฆ์ผ๋ก ์ฟผ๋ฆฌ ์์ฑ
๋ฉ์๋ ์ด๋ฆ์ ๋ถ์ํด์ JPQL ์ฟผ๋ฆฌ๋ฅผ ์คํํ๋ค.
```java
List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
```
```find..By``` : find ์ By ์ฌ์ด์๋ ํด๋น ๋ฉ์๋๋ฅผ ์ค๋ชํด์ค ์ ์๋ ๋ด์ฉ์ด ๋ค์ด๊ฐ๋ ๋๋ค.
์ด์ธ์ existBy, countBy, deleteBy, removeBy ๋ฑ..

```limit``` : findFirst5, findTop5, findFirst .. ๋ฑ ์ฒ๋ผ ์ฌ์ฉํ  ์ ์๋ค. 


์ฟผ๋ฆฌ ๋ฉ์๋ ํํฐ ์กฐ๊ฑด
์คํ๋ง ๋ฐ์ดํฐ JPA ๊ณต์ ๋ฌธ์ ์ฐธ๊ณ : (https://docs.spring.io/spring-data/jpa/docs/current/ reference/html/#jpa.query-methods.query-creation)


### โ๏ธ NamedQuery
```java
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
```
๋ฏธ๋ฆฌ ์ฟผ๋ฆฌ๋ฅผ ์ด๋ฆ์ผ๋ก ์ ์ํด๋๊ณ  ์ฌ์ฉํ๋ค. createQuery()๋ฅผ ์ด์ฉํ์ฌ JPQL์ String์ผ๋ก ์์ฑํ ๋์ ๋ฌ๋ฆฌ
์ ํ๋ฆฌ์ผ์ด์ ๋ก๋ฉ์์ ์ ์ค๋ฅ๋ฅผ ๊ฒ์ฌํ  ์ ์๋ค๋ ์ฅ์ ์ด ์๊ณ , ๋ฏธ๋ฆฌ ๋ถ๋ฌ์ง ์ฟผ๋ฆฌ๋ฅผ ์ฌ๋ฌ๋ฒ ์ฌ์ฌ์ฉํ  ์ ์๊ฒ ๋๋ค.

- ์์ JPA
```java
public Member findByUsername(String username){
    return em.createNamedQuery("Member.findByUsername", Member.class)
            .setParameter("username", username)
            .getSingleResult();
}
```
์์ฑ๋ NamedQuery์ ์ด๋ฆ์ ๊ฐ์ง๊ณ  ์ฟผ๋ฆฌ๋ฅผ ๋ฐ๋ก ์์ฑํ  ์ ์๋ค.

- JpaRepository
```java
//    @Query(name = "Member.findByUsername")
    Member findByUsername(@Param("username") String username);
```
๋ง์ฐฌ๊ฐ์ง๋ก ์์ฑ๋ ๋ค์๋ ์ฟผ๋ฆฌ์ ์ด๋ฆ์ ๊ฐ์ง๊ณ  ์ฌ์ฉ ๊ฐ๋ฅํ๋ค. ํ์ง๋ง ์ด ๋ ๋ค์๋ ์ฟผ๋ฆฌ์ ์ด๋ฆ์ด
 ```{์ํฐํฐ ํด๋์ค}.{๋ค์๋์ฟผ๋ฆฌ ์ด๋ฆ}``` ์ด๋ผ๋ฉด ๋ช์ํ์ง ์์๋ ์๋์ผ๋ก ์ฐพ์ ์ฌ์ฉํ๋ค.

### โ๏ธ Query
```java
@Query("select m from Member m where m.username = :username and m.age = :age")
List<Member> findByUsernameAndAge(@Param("username") String username, @Param("age") ing age);
```
`@Query` ์ ๋ธํ์ด์์ ์ฌ์ฉํ์ฌ ๋ฐ๋ก ๋ฉ์๋์ JPQL ์ฟผ๋ฆฌ๋ฅผ ์์ฑํ  ์ ์๋ค.
ํ๋ผ๋ฏธํฐ๋ `@Param()` ๋๋ ํ๋ผ๋ฏธํฐ์ ์์(?1, ?2 ..) ๋ก ๋ฉ์๋์ ๋ค์ด์จ ํ๋ผ๋ฏธํฐ๋ฅผ ๊ฐ์ผ๋ก ์ง์ ํ  ์ ์๋ค.

๋ง์ฐฌ๊ฐ์ง๋ก ๋ฌธ์์ด๋ก ์ด๋ฃจ์ด์ง Query ์ง๋ง. ์ ํ๋ฆฌ์ผ์ด์ ๋ก๋ฉ์์ ์ ํ์ฑ์ ํ๊ธฐ ๋๋ฌธ์ ๋ฌธ๋ฒ ์ค๋ฅ๋ฅผ ๋ฐ๊ฒฌํ  ์ ์๋ค.