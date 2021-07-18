- 라이브러리
> - Lombok
> - Spring Boot Devtools
> - Spring Configuration processor : Custom properties 자동완성 지원
> - Thymeleaf
> - Spring Security
> - Srping Data JPA
> - PostgreSQL, H2
> - Java mail sender (Spring mail)
> - QueryDSL
> - Spring Validation



## 📃 목차
***
- #### [회원가입 뷰](#-회원가입-뷰)
- #### [패스워드 인코딩](#-패스워드-인코딩)
- #### [인증 메일 확인](#-인증-메일-확인)
- #### [회원가입, 인증 후 자동 로그인](#-회원가입,-인증-후-자동-로그인)
- #### [인증 상태에 따른 View](#-인증-상태에-따른-view)
- #### [프론트엔드 라이브러리 설정](#-프론트엔드-라이브러리-설정)
- #### [프로필 이미지 및 아이콘](#-프로필-이미지-및-아이콘)
- #### [이메일 인증 경고창](#-이메일-인증-경고창)
- #### [인증 이메일 재전송](#-인증-이메일-재전송)
- #### [로그인, 로그아웃](#-로그인,-로그아웃)
- #### [로그인 기억하](#-로그인-기억하기)
- #### [](#-)
- #### [](#-)
- #### [](ㅍ)




# 📌 회원가입 뷰
***
    - 타임리프 : 객체를 폼 객체로 설정하기

```html
th:object="${signUpForm}"

th:field="*{nickName}"
th:field="*{email}"
th:field="*{password}"
```        

```java
model.addAttribute(new SignUpForm());
```
- #### attribute name을 생략하면 객체의 카멜 케이스로 이름이 지정된다. ex)signUpForm


- ## 제약 검증 기능.
```html
<form class="needs-validation col-sm-6"
      
required minlength="3" maxlength="20"
input type email, password

<small class="invalid-feedback">닉네임을 입력하세요.</small>
<small class="form-text text-danger" th:if="${#fields.hasErrors('nickName')}" th:errors="*{nickName}">Nickname Error</small>
```

```javascript
<script type="application/javascript" th:fragment="form-validation">
    (function () {
        'use strict';

        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');

            Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated')
                }, false)
            })
        }, false)
    }())
</script>
```
> form.checkValidity 에서 html에서 선언한 검증 체크. 유효하지 않을 경우 class ="invalid-feedback"에 해당하는 값 출력.


# 📌 폼 서브밋 검증
***
### -  회원 가입 폼 검증.
> - #### JSR 303 에노테이션 검증
>    - 값 길이, 필수 값
>  > @NotBlank    
    @Length(min=3, max=20)    
    @Pattern(regexp = "([ㄱ-ㅎ가-힣-a-z0-9_-]{3,20}$)")    
    @Email
> - #### 커스텀 검증
>    - 중복 이메일, 닉네임 여부 확인.
> > implements Validator
> - #### 폼 에러 발생 시 폼 다시 보여주기.
> ```java
> @PostMapping("/sign-up")
> public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
>   if(errors.hasErrors()){
>   return "account/sign-up";
> }
>   return "redirect:/";
> }
> ```

- 커스텀 검증
```java
@Override
public boolean supports(Class<?> aClass) {
    return aClass.isAssignableFrom(SignUpForm.class);
}

@Override
public void validate(Object o, Errors errors) {
    //TODO email, nickname 중복 여부.
    SignUpForm signUpForm = (SignUpForm) errors;
    if(accountRepository.existsByEmail(signUpForm.getEmail())){
        errors.rejectValue("email","invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일 입니다.");
    }
    if(accountRepository.existsNickName(signUpForm.getNickName())){
        errors.rejectValue("nickName","invalid.nickName", new Object[]{signUpForm.getNickName()}, "이미 사용중인 닉네 입니다.");
    }

}
```

```java
//객체의 카멜케이스.
@InitBinder("signUpForm")
public void initBinder(WebDataBinder webDataBinder){
    webDataBinder.addValidators(signUpFormValidator);
}
```

# 📌 회원 가입 처리
****
### 1. 회원 정보 저장.
```java
private Account saveNewAccount(SignUpForm signUpForm) {
        Account account = Account.builder()
                .email((signUpForm.getEmail()))
                .nickName(signUpForm.getNickName())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .studyCreatedByWeb(true)
                .studyEnrollmentResultByWeb(true)
                .studyUpdatedByWeb(true)
                .build();

        Account newAccount = accountRepository.save(account);
        return newAccount;
    }
```
### 2. 인증 이메일 발송.
```java
newAccount.generateEmailCheckToken();

private void sendSignUpConfirmEmail(Account newAccount) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setSubject("회원 가입 인증");
        //check-email-token 에서 token이 유효한지 확인.
        mailMessage.setText("/check-email-token?token="+ newAccount.getEmailCheckToken()
                + "&email=" + newAccount.getEmail());

        javaMailSender.send(mailMessage);
    }
```
### 3. 완료 후 웰컴 페이지로 리다이렉트.


### - 테스트
- CSRF Token : 타 사이트에서 폼 데이터를 전송하는 것을 방지.
- 입력값이 정상 일 경우, 잘못 된 입력 값일 경우 테스트.

```java
@MockBean
JavaMailSender javaMailSender;

@DisplayName("회원 가입 처리 - 입력값 정상")
    @Test
    void signUpSubmit_with_correct_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("nickName","bigave")
                .param("email", "correct@email.com")
                .param("password","12345678")
                .with(csrf()))  //csrf token을 넣어줌.
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        Account account =accountRepository.findByEmail("correct@email.com");
        assertNotNull(account);
        assertNotNull(account.getEmailCheckToken());
        
        //send 메서드가 호출 되었는지. org.mockito
        then(javaMailSender).should().send(any(SimpleMailMessage.class));
    }
```

# 📌 패스워드 인코딩
****
> - 스프링 시큐리티에서 권장하는 PasswordEncoder는 bycrypt 해시 알고리즘을 사용.
> - 솔트(salt) : 해커가 이미 여러개의 해싱 알고리즘을 사용하여 저장해놓고, 해시값에서 비밀번호를 추론할 수 있기 때문에 이를 방지. 
> > hash(12345678) -> aaaabbbb    
> > hash(12344567+salt)-> aacabaebb    
> > hash(12344567+salt)-> cafcabaekkb
> > 매번 다른 값이 나옴.
```java
@Bean
public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
```
- Test
```java
Account account =accountRepository.findByEmail("correct@email.com");

assertNotEquals(account.getPassword(), "12345678");
```


# 📌 인증 메일 확인
*****
```java
@GetMapping("/check-email-token")
public String checkEmailToken(String token, String email, Model model){
    Account account = accountRepository.findByEmail(email);
    String view = "account/checked-email";

    if(account == null){
        model.addAttribute("error", "wrong.email");
        return view;
    }

    if (!account.getEmailCheckToken().equals(token)){
        model.addAttribute("error", "wrong.token");
        return view;
    }

    account.setEmailVerified(true);
    account.setJoinedAt(LocalDateTime.now());
    model.addAttribute("numberOfUser", accountRepository.count());

    return view;
}
```
> - 인증 메일 정보가 올바르지 않다면 error를 담고, 올바르면 이메일 인증표시를 하고 가입 날짜를 계정정보에 추가.
> - checked-email 페이지에서 error의 여부에 따라 메시지를 보여준다.


# 📌 회원 가입, 인증 후 자동 로그인
****
> - 스프링 시큐리티에서 로그인 : SecurityContext에 Authentication(token)이 존재 하는가.
> - UsernamePasswordAuthenticationToken 으로 token을 생성하고 SecuriryContext에 넣어준다.
```java
public void login(Account account) {

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            account.getNickName(),
            account.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
    );

    SecurityContext context = SecurityContextHolder.getContext();
    context.setAuthentication(token);
}
```

# 📌 인증 상태에 따른 View
```html
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>


<html xmlns:sec="http://www.thymeleaf.org/extras/spring-security"></html>

<li class="nav-item" sec:authorize="!isAuthenticated()">
    <a class="nav-link" href="#" th:href="@{/login}">로그인</a>
</li>
```
- isAuthenticated()를 이용하여 인증 상태인지 여부를 가져올 수 있다.
```html
${#authentication.name} 로 이름 참조도 가능.
```


# 📌 프론트엔드 라이브러리 설정
> - Web Jar , NPM
> - WebJar는 라이브러리 업데이트가 느리고, 올라오지 않은 라이브러리도 흔하다.

- ### 스프링 부트와 NPM
> - src/main/resource/stati 이하에서는 정적 리소스로 제공(스프링 부트)
> - package.json에 프론트엔드 라이브러리를 제공
> - static 디렉토리 아래에 package.json을 위치. -> 정적 리소스로 프론트엔드 라이브러리 사용.
> > static 디렉토리로 이동.    
> > npm init   
> > npm install bootstrap   
> > npm install jquery
> - .ginignore에 node_modules, node 추가.

```html
<link rel="stylesheet" href="/node_modules/bootstrap/dist/css/bootstrap.css">

<script src="/node_modules/jquery/dist/jquery.js"></script>
<script src="/node_modules/bootstrap/dist/js/bootstrap.bundle.js"></script>
```
- ### 빌드
> - pom.xml을 빌드할 때 static 아래 package.json도 빌드하도록 설정.
> 

```xml
<plugin>
    <groupId>com.github.eirslett</groupId>
    <artifactId>frontend-maven-plugin</artifactId>
    <version>1.8.0</version>
    <configuration>
        <nodeVersion>v4.6.0</nodeVersion>
        <workingDirectory>src/main/resources/static</workingDirectory>
    </configuration>
    <executions>
        <execution>
            <id>install node and npm</id>
            <goals>
                <goal>install-node-and-npm</goal>
            </goals>
            <phase>generate-resources</phase>
        </execution>
        <execution>
            <id>npm install</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <phase>generate-resources</phase>
            <configuration>
                <arguments>install</arguments>
            </configuration>
        </execution>
    </executions>
</plugin>
```


- ### 시큐리티 설정
```java
 @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }
```
- CommonLocation에서는 node_modules를 포함하지 않기 때문에 따로 추가해 주어야 한다.


# 📌 프로필 이미지 및 아이콘
*****
> - npm install jdenticon
> - npm install font-awesome


```html
<link rel="stylesheet" href="/node_modules/font-awesome/css/font-awesome.css">
<script src="/node_modules/jdenticon/dist/jdenticon.js"></script>

<i class="fa fa-bell-o"> </i>
<svg data-jdenticon-value="user127" th:data-jdenticon-value="${#authentication.name}" width="24" height="24" class="rounded border bg-light"></svg>

```
- font-awesome : fa {docs 참조해서 아이콘 id}
- jdenticon : name에 따라 다른 값이 들어가도록 설정 함.


# 📌 이메일 인증 경고창.
****
```html
<div class ="alert alert-warning" role="alert" th:if="${account != null && !account.emailVerified}" >
    가입을 완료하려면 <a href="#" th:href="@{/check-email}" class="alert-link">계정 인증 이메일을 확인 하세요.</a>
</div>
```

```java
@GetMapping("/")
public String home(@CurrentUser Account account, Model model){
    if(account != null){
        model.addAttribute(account);
    }

    return "index";
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this =='anonymousUser' ? null :account")
public @interface CurrentUser {

}
```
- User인증이 되지 않으면 Principal 은 "anonymousUser"라는 문자열. 인증이 되어있지 않다면 null을 인증이 되어있다면 principal에서 account 객체를 꺼내 넘겨준다.

```java
UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
```
- login에서 AuthenticationPrincipal을 닉네임이 아닌 UserAccount로 변경.

```java
@Getter
public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {
        super(account.getNickName(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account =account;
    }
}
```
- account 라는 필드명은 @CurrentUser Account account 의 account 와 매핑된다.


# 📌 인증 이메일 재전송
***
```java
@GetMapping("/resend-confirm-email")
public String resendPage(@CurrentUser Account account, Model model){

    if(!account.canConfirmEmail()){
        model.addAttribute("error", "인증 이메일은 10분에 한번만 전송할 수 있습니다.");
        model.addAttribute("email",account.getEmail());
        return "account/check-email";
    }

    accountService.resendConfirmEmail(account.getNickName());
    return "redirect:/";
}
```

```java
@Transactional
public void resendConfirmEmail(String nickName) {
        Account account = accountRepository.findByNickName(nickName);
        account.generateEmailCheckToken();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(account.getEmail());
        simpleMailMessage.setSubject("스터디웹 회원 인증");
        simpleMailMessage.setText("/check-email-token?token="+account.getEmailCheckToken()
        +"&email="+account.getEmail());

        javaMailSender.send(simpleMailMessage);
}
```
- canConfirmEmail()에서는 현재 시간과 마지막으로 checkToken을 생성한 시간을 비교하여 10이 지나지 않았다면 전송하지 않고 경고창.
- 토큰을 새로 만들어 이메일 전송.


# 📌 로그인, 로그아웃
***
```java
http.formLogin()
            .loginPage("/login").permitAll()
            .and()
        .logout().logoutSuccessUrl("/");
```

```java
@Override
public UserDetails loadUserByUsername(String emailOrNickName) throws UsernameNotFoundException {
    Account account =  accountRepository.findByEmail(emailOrNickName);
    if(account == null){
        account = accountRepository.findByNickName(emailOrNickName);
    }
    if(account == null){
        throw new UsernameNotFoundException(emailOrNickName);
    }

    return new UserAccount(account);

}
```
```java
private Account account;

    public UserAccount(Account account) {
        super(account.getNickName(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account =account;
    }
```
- 기본으로 사용되는 formLogin()이 아닌 새로 login.html을 만들어 매핑.
- Spring Security의 User을 상속받는 클래스인 UserAccount를 이용하여 인증.
- return User() 로도 가능.

# 📌 로그인 기억하기
***
- 기본적으로 Session의 타임 아웃은 30분.
```properties
server.servlet.session.timeout=30m
```
- 세션이 만료 되더라도 로그인을 유지하기 위해 사용하는 방법(RememberMe)
    > 쿠키에 인증 정보를 남겨두고 세션이 만료 됐을 때 쿠키에 남아있는 정보로 인증. 
    
- 해시 기반의 쿠키
> - UserName
> - Password
> - 만료기간
> - Key
> - 쿠키를 탈취당하면 그 계정을 탈취당한 것과 같다.

- 조금 더 안전하게 관리하기
> - 쿠키 안에 랜덤한 token을 만들어 같이 저장하고 인증 때마다 변경.
> - Username, 토큰
> - 해당 방법도 취약, 해커가 쿠키로 인증을 하게되면 원 사용자는 인증할 수 없게 됨.

- 개선된 방법
> - UserName, Token(랜덤, 매번 변경), 시리즈(랜덤,고정)
> - 쿠키를 탈취 당하면 원 사용자는 유효하지 않은 토큰과 유효한 시리즈,UserName 으로 접속하게 되고, 이 경우, 모든 토큰을 삭제하여 해커가 더이상 쿠키를 사용하지 못하도록 방지할 수 있다.


- ### 스프링 시큐리티 설정 : 해시 기반
```java
http.rememberMe().key("랜덤 키값");
```

- ### 스프링 시큐리티 설정: 개선된 영속화 기반 설정.

```html
<div class="form-grop form-check">
    <input type="checkbox" class="form-check-input" id="rememberMe", name="remember-me" checked>
    <label class="form-check-label" for="rememberMe" aria-describedby="rememberMeHelp">로그인 유지</label>
</div>
```
- name 을 remember-me로 주고 check box가 true 값이면 remember-me 기능 실행.

```java
private final AccountService accountService;
private final DataSource dataSource;

http.rememberMe()
        .userDetailsService(accountService)
        .tokenRepository(tokenRepository());

@Bean
public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;

        }
```
- userDetailsService와 TokenRepository를 넘겨줌.
- JdbcTokenRepositoryImpl에서 만드는 테이블 엔티티를 매핑 시켜준다.

```java
@Getter @Setter
@Table(name = "persistent_logins")
@Entity
public class PersistentLogins {

    @Id
    @Column(length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String token;

    @Column(name = "last_used",nullable = false, length = 64)
    private LocalDateTime lastUsed;
}
```

# 📌 profileView
****
```java
   @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, @CurrentUser Account account, Model model){
        Account byNickName = accountRepository.findByNickName(nickname);

        if(byNickName == null){
            throw new IllegalArgumentException(nickname+"에 해당하는 사용자가 없습니다");
        }

        //account
        model.addAttribute(byNickName);
        model.addAttribute("isOwner", byNickName.equals(account));

        return "account/profile";

    }
```

```html
<div class="row mt-5 justify-content-center">
    <div class="col-2">
        <!-- image -->
        <svg th:if="${#strings.isEmpty(account.profileImage)}" class="img-fluid float-left rounded img-thumbnail"
            th:data-jdenticon-value="${account.nickName}" width="125", height="125"></svg>
        <img th:if="${!#strings.isEmpty(account.profileImage)}" class="img-fluid float-left rounded img-thumbnail"
             th:src="${account.profileImage}" width="125", height="125"/>
    </div>
    <div class="col-8">
        <h1 class="display-4" th:text="${account.nickName}"></h1>
        <p class="lead" th:if="${!#strings.isEmpty(account.bio)}" th:text="${account.bio}"></p>
        <p class="lead" th:if="${#strings.isEmpty(account.bio) && isOwner}" >
            <span>한 줄 소개를 추가하세요.</span>
        </p>
    </div>
</div>
```

