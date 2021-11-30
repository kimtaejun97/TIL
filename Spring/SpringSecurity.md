# 📌 Spring Security
****
![img.png](img/img.png)

### 🤔 인증(Authorization)과 인가(Authetication)
스프링 시큐리티에서는 인증 과정을 먼저 거친 후에 인가 절차를 진행하게 된다. 인증 과정에서는 사용자의 여부를, 인가 과정에서는
리소스에 대한 접근 권한을 확인한다.

### 🤔 Authetication
Authentication은 현재 접근 하는 주체의 정보와 권한을 담는 인터페이스 이다. 해당 객체는 Security Context에 저장하여 보관한다.
getCredentials(), getPrincipal(), setAuthenticated(), isAuthenticated() 등의 메소드가 있다.

### 🧐 SpringSecurity의 동작 과정
#### 1. 로그인 정보 Http Request
- 스프링 시큐리티는 연결된 일련의 필터들을 가지고 있다. 요청은 인증과 권한 부여를 위해 필터들을 통과하게 되고,
해당 요청과 관련된 인증 필터에 도착할때 까지 진행된다.
- 로그인 폼 서브밋 요청은 UsernamePasswordAuthenticationFiliter에 도달할 대까지 필터체인을 통과하게 된다.
- AutheticationFilter 에서는 사용자의 JSESSIONID가 Context에 있는지 확인하고 없다면 로직을 수행한다.

#### 2. AuthenticationFilter 에서 요청을 가로채 UsernamePasswordAuthenticationToken 생성.
- 수신된 요청에서 아이디(Principal)와 비밀번호(credential)를 추출하고 이를 기반으로 UsernamePasswordAuthenticationToken 을 생성한다.
- Token의 생성은 두가지로 나뉜다. 요청 토큰의 생성과 인증완료 후 토큰의 생성(다시 돌아올 때).

#### 3. ProviderManager 에게 토큰 전달.
- 만들어진 Token은 AuthenticationManager 의 인증 메서드를 호출하는데 사용된다. 이 때는 ProviderManager을 구현한
ProviderManager에 의해 사용자 인증을 거친다. ProviderManager 는 AuthenticationManager 리스트를 가지고 있으며, 이를 순회하며
인증을 시도한다.

#### 4. AuthenticationProvider에 토큰 전달.
제공된 인증 개체로 사용자를 인증한다. 인증전의 Authentication 객체를 받아 인증이 완료된 Authentication 객체를 반환한다.
- CasAuthenticationProvider (Central Authentication Service)
- JaasAuthenticationProvider (Java Authentication and Authorization Service)
- DaoAuthenticationProvider (Data Access Object)
- OpenIDAuthenticationProvider
- RememberMeAuthenticationProvider
- LdapAuthenticationProvider (Lightweight Directory Access Protocol)
...

#### 5. UserDetailsService에 전달하고, 서비스에서는 받은 사용자 정보로 DB에서 사용자 정보를 찾아 UserDetails 객체 생성.
- Provider는 사용자 이름을 기반으로 세부 정보를 검색하기 위해 UserDetailsService 를 사용한다.
UserDetailsService 에서는 DB에 저장된 회원의 비밀번호를 조회하여 UserDetails 인터페이스를 구현한 객체(User객체)를 반환한다.

- UserDetailsService는 인터페이스 이며, 이를 구현한 서비스 클래스가 필요하다.

#### 6. AuthenticationProvider에서 UserDetails 객체를 넘겨받고 입력받은 비밀번호와 객체의 비밀번호를 비교.
#### 7. 인증 성공시 권한, 사용자 정보를 담은 완전한 Authentication 객체를 AuthenticationFilter 에 전달.
- AuthenticationManager 는 완전한 인증객체를 관련 인증 필터로 다시 반환한다.
- 인증에 실패하면 AuthenticationException 이 발생하고, AuthenticationEntryPoint 에 의해 처리된다.
#### 8. AuthenticationFilter는 Authentication 객체를 SecurityContext에 저장.
- ### 의존성 추가
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

- ### 스프링 시큐리티
    - 웹 시큐리티
    - 메소드 시큐리티
    - 다양한 인증 밥법 지원 : LDAP, 폼 인증, Basic 인증, OAuth, ...


- ### 스프링 부트 시큐리티 자동 설정
    - SecurityAutoConfiguration : 사실상 spring boot 에서 변경한 부분은 별로 없다.
    - UserDetatilsSeviceAutoConfiguration :
      > UserDetailsService.class, AuthenticationManager.class, AuthenticationProvider.class 가 없을 때 설정 파일이 적용되고,
      랜덤한 유저를 생성해 준다.

- ### 기본 사용자.
    - UserName : user
    - password : application을 실행 할 때 마다 생성되는 랜던 값(console에 출력됨.)
    - spring.security.username
    - spring.security.password


- ## 스프링 시큐리티 Test
```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
</dependency>
```

### 1. method
```java
mockMvc.perform(get("/hello")
                .with(user("kim").password("1234"))
```
> 가짜 인증 유저를 넣어주어, 권한이 없어 실행할 수 없는 테스트를 실행할 수 있게 해준다.

### 2. Annotation
```java
@WithMockUser
class or Method
```
> 유저 정보를 직접 넣어주어 테스트.


# 📌 Spring Security Custom
***

## 1. extends WebSecurityConfigurerAdapter
```java
//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/admin").hasRole(Role.ADMIN.name())  // admin URL은 관리자 접근 가능.
              .antMatchers("/my").hasRole(Role.STUDENT.name())  // my URL 은 학생만 접근 가능
              .anyRequest().permitAll() // 나머지 URL은 인증 필요.
              .and()
            .formLogin()// 만들어주는 formLogin template 사용.
              .and()
            .httpBasic() // accept header에 http가 없는 경우 httpBasic 사용.
              .and()
            .logout()
              .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
```
> - confugure 메소드를 오버라이딩 하여 접근 권한, 로그인 폼 등 설정.을
> > hasRole 에서 자동으로 앞에 ROLE_ 을 붙여주기 때문에 .name()으로 가져옴.

## 1-1 @EnableGlobalMethodSecurity()
1번과 같이 HttpSecurity를 이용하여 한번에 접근 권한을 지정할수도 있지만 애노테이션을 이용하여 따로 접근 권한을 지정할 수 있다.
이를 위해서는 Configuration 에서 ```@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true ...)``` 애노테이션이 필요하다.
```java
@PreAuthorize("hasRole('ROLE_ADMIN')")
public void adminPage(){...}

@PostAythorize("hasRole('ROLE_ADMIN')")
public void adminPage(){...}

@Secured("ROLE_ADMIN")
public void adminPage(){...}

```


## 2. implements UserDatailsService
```java
@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // User 정보를 받아 계정 생성.
    public Account createAccount(String username, String password){
        Account account = new Account();
        account.setUserName(username);
        account.setPassword(passwordEncoder.encode(password));

        return accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
          Optional<Account> byUserName= accountRepository.findByUserName(userName);
          Account account =  byUserName.orElseThrow(()-> new UsernameNotFoundException(userName)); // 없으면 예외를 던지고, 있으면 Account 객체 return
    
          return new User(account.getUserName(), account.getPassword(), authorities(account));
    }
    
    private Collection<? extends GrantedAuthority> authorities(Account account) {
        return Arrays.asList(new SimpleGrantedAuthority(account.getRole().getKey()));
    }
}
```
> - 유저에 대한 클래스인 User을 Spring Security에서 지원. 사용자가 입력한 정보와 DB에 저장된 유저 정보를 비교하고 존재하면 ROLE 부여.
> - getKey()값으로 권한을 생성.

## 3.Password Encode
```java
// config 클래스에 정의.
@Bean
public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}

// Service 클래스에서 사용.
@Autowired
private PasswordEncoder passwordEncoder;
account.setPassword(passwordEncoder.encode(password));
```
> - Password 인코더를 Bean으로 정의.
> - Password를 DB에 저장하기 전 암호화하고 저장.
> > ![img_15.png](img/img_1.png)

<br><br><br>
> - https://doozi0316.tistory.com/entry/Spring-Security-Spring-Security%EC%9D%98-%EA%B0%9C%EB%85%90%EA%B3%BC-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95
> - https://mangkyu.tistory.com/7
> -  인프런 백기선님 SpringBoot 강의.