# ๐ ๋ชฉ์ฐจ
- ### [Security ์ธ์ฆ ๊ณผ์ ](#-spring-security-์ธ์ฆ๊ณผ์ )
- ### [Spring Security Custom](#-spring-security-custom)
- ### [์ธ์ฆ ๊ฐ์ฒด ๊ฐ์ ธ์ค๊ธฐ](#-์ธ์ฆ-๊ฐ์ฒด-๊ฐ์ ธ์ค๊ธฐ)
- ### [Remember-Me](#-remember-me)
- ### [ํ์คํธ์์ ์ฌ์ฉ์ ์ธ์ฆ](#-ํ์คํธ์์-์ฌ์ฉ์-์ธ์ฆ)
## ๐ Spring Security ์ธ์ฆ๊ณผ์ 
***
![img.png](img/img.png)

### ๐ค ์ธ์ฆ(Authorization)๊ณผ ์ธ๊ฐ(Authetication)
์คํ๋ง ์ํ๋ฆฌํฐ์์๋ ์ธ์ฆ ๊ณผ์ ์ ๋จผ์  ๊ฑฐ์น ํ์ ์ธ๊ฐ ์ ์ฐจ๋ฅผ ์งํํ๊ฒ ๋๋ค. ์ธ์ฆ ๊ณผ์ ์์๋ ์ฌ์ฉ์์ ์ฌ๋ถ๋ฅผ, ์ธ๊ฐ ๊ณผ์ ์์๋
๋ฆฌ์์ค์ ๋ํ ์ ๊ทผ ๊ถํ์ ํ์ธํ๋ค.

### ๐ค Authetication ๊ฐ์ฒด
Authentication์ ํ์ฌ ์ ๊ทผ ํ๋ ์ฃผ์ฒด์ ์ ๋ณด์ ๊ถํ์ ๋ด๋ ์ธํฐํ์ด์ค ์ด๋ค. ํด๋น ๊ฐ์ฒด๋ Security Context์ ์ ์ฅํ์ฌ ๋ณด๊ดํ๋ค.
getCredentials(), getPrincipal(), setAuthenticated(), isAuthenticated() ๋ฑ์ ๋ฉ์๋๊ฐ ์๋ค.

### ๐ง SpringSecurity์ ๋์ ๊ณผ์ 
#### 1. ๋ก๊ทธ์ธ ์ ๋ณด Http Request
- ์คํ๋ง ์ํ๋ฆฌํฐ๋ ์ฐ๊ฒฐ๋ ์ผ๋ จ์ ํํฐ๋ค์ ๊ฐ์ง๊ณ  ์๋ค. ์์ฒญ์ ์ธ์ฆ๊ณผ ๊ถํ ๋ถ์ฌ๋ฅผ ์ํด ํํฐ๋ค์ ํต๊ณผํ๊ฒ ๋๊ณ ,
ํด๋น ์์ฒญ๊ณผ ๊ด๋ จ๋ ์ธ์ฆ ํํฐ์ ๋์ฐฉํ ๋ ๊น์ง ์งํ๋๋ค.
- ๋ก๊ทธ์ธ ํผ ์๋ธ๋ฐ ์์ฒญ์ UsernamePasswordAuthenticationFiliter์ ๋๋ฌํ  ๋ ๊น์ง ํํฐ์ฒด์ธ์ ํต๊ณผํ๊ฒ ๋๋ค.
- AutheticationFilter ์์๋ ์ฌ์ฉ์์ JSESSIONID๊ฐ Context์ ์๋์ง ํ์ธํ๊ณ  ์๋ค๋ฉด ๋ก์ง์ ์ํํ๋ค.

#### 2. AuthenticationFilter ์์ ์์ฒญ์ ๊ฐ๋ก์ฑ UsernamePasswordAuthenticationToken ์์ฑ.
- ์์ ๋ ์์ฒญ์์ ์์ด๋(Principal)์ ๋น๋ฐ๋ฒํธ(credential)๋ฅผ ์ถ์ถํ๊ณ  ์ด๋ฅผ ๊ธฐ๋ฐ์ผ๋ก UsernamePasswordAuthenticationToken ์ ์์ฑํ๋ค.
- Token์ ์์ฑ์ ๋๊ฐ์ง๋ก ๋๋๋ค. ์์ฒญ ํ ํฐ์ ์์ฑ๊ณผ ์ธ์ฆ์๋ฃ ํ ํ ํฐ์ ์์ฑ(๋ค์ ๋์์ฌ ๋).

#### 3. ProviderManager ์๊ฒ ํ ํฐ ์ ๋ฌ.
- ๋ง๋ค์ด์ง Token์ AuthenticationManager ์ ์ธ์ฆ ๋ฉ์๋๋ฅผ ํธ์ถํ๋๋ฐ ์ฌ์ฉ๋๋ค. ์ด ๋๋ AuthenticationManager ๋ฅผ ๊ตฌํํ
ProviderManager ์ ์ํด ์ฌ์ฉ์ ์ธ์ฆ์ ๊ฑฐ์น๋ค. ProviderManager ๋ AuthenticationProvider ๋ฆฌ์คํธ๋ฅผ ๊ฐ์ง๊ณ  ์์ผ๋ฉฐ, ์ด๋ฅผ ์ํํ๋ฉฐ ์ธ์ฆ์ ์๋ํ๋ค.

#### 4. AuthenticationProvider์ ํ ํฐ ์ ๋ฌ.
์ ๊ณต๋ ์ธ์ฆ ๊ฐ์ฒด๋ก ์ฌ์ฉ์๋ฅผ ์ธ์ฆํ๋ค. ์ธ์ฆ์ ์ Authentication ๊ฐ์ฒด๋ฅผ ๋ฐ์ ์ธ์ฆ์ด ์๋ฃ๋ Authentication ๊ฐ์ฒด๋ฅผ ๋ฐํํ๋ค.
- CasAuthenticationProvider (Central Authentication Service)
- JaasAuthenticationProvider (Java Authentication and Authorization Service)
- **DaoAuthenticationProvider** (Data Access Object)
- OpenIDAuthenticationProvider
- RememberMeAuthenticationProvider
- LdapAuthenticationProvider (Lightweight Directory Access Protocol)
...
  
์ฃผ๋ก ์ฐ๋ฆฌ๊ฐ ๋ง์ด ์ฌ์ฉํ๋ Provider์ DB๋ฅผ ์ด์ฉํ ์ธ์ฆ์ธ DaoAuthenticationProvider ์ผ ๊ฒ์ด๋ค. ๋ค์ ์ฝ๋๋ DaoAuthenticationProvider ์์ ์ฐพ์
์ ์  ๊ฒ์ฆ ๋ถ๋ถ์ด๋ค.
```java
@Override
protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
    prepareTimingAttackProtection();
    try {
        UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
    catch (UsernameNotFoundException ex) {
        mitigateAgainstTimingAttack(authentication);
        throw ex;
    }
    catch (InternalAuthenticationServiceException ex) {
        throw ex;
    }
    catch (Exception ex) {
        throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
    }
}
```
๊ฐ๋ฐ์๋ UserDetails๋ฅผ ์์ํ๊ณ  loadUserByUsername() ์์ username์ผ๋ก DB์ ์ฌ์ฉ์๊ฐ ์๋์ง ์ฐพ๊ฒ ๋๋ค.
๋ง์ฝ ์กด์ฌํ์ง ์๋๋ค๋ฉด UsernameNotFoundException์ ๋ฐ์์ํค๋ฉด ๋๊ณ , Provider์์๋ ์ด๊ฑธ ์ก์ ๋์ง๋ค.
์ฝ๋๋ฅผ ๋ณด๋ฉด prepareTimingAttackProtection()๊ณผ mitigateAgainstTimingAttack()์ ๋ณผ ์ ์๋๋ฐ
์ฝ๋๋ ์๋์ ๊ฐ๋ค.
```java
private void prepareTimingAttackProtection() {
		if (this.userNotFoundEncodedPassword == null) {
			this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
		}
	}

private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
    if (authentication.getCredentials() != null) {
        String presentedPassword = authentication.getCredentials().toString();
        this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
    }
}

private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

```
ํ์ฌ ํจ์ค์๋์ userNotFoundExcodedPassword ๋ฅผ ๋น๊ตํ๋ ์์์ ์ํํ๋๋ฐ ๋ณ ๋ค๋ฅธ ์์์ด๋ผ๊ธฐ ๋ณด๋ค๋ ์์์ ์ผ๋ก
๋ฐ๋ณต์คํ ํ๋๊ฒ์ ๋ง๊ธฐ์ํ ์กฐ์น๊ฐ ์๋๊ฐ ์ถ๋ค.

#### 5. UserDetailsService์ ์ ๋ฌํ๊ณ , ์๋น์ค์์๋ ๋ฐ์ ์ฌ์ฉ์ ์ ๋ณด๋ก DB์์ ์ฌ์ฉ์ ์ ๋ณด๋ฅผ ์ฐพ์ UserDetails ๊ฐ์ฒด ์์ฑ.
- Provider๋ ์ฌ์ฉ์ ์ด๋ฆ์ ๊ธฐ๋ฐ์ผ๋ก ์ธ๋ถ ์ ๋ณด๋ฅผ ๊ฒ์ํ๊ธฐ ์ํด UserDetailsService ๋ฅผ ์ฌ์ฉํ๋ค.
UserDetailsService ์์๋ DB์ ์ ์ฅ๋ ํ์์ ๋น๋ฐ๋ฒํธ๋ฅผ ์กฐํํ์ฌ UserDetails ์ธํฐํ์ด์ค๋ฅผ ๊ตฌํํ ๊ฐ์ฒด(User๊ฐ์ฒด)๋ฅผ ๋ฐํํ๋ค.

- UserDetailsService๋ ์ธํฐํ์ด์ค ์ด๋ฉฐ, ์ด๋ฅผ ๊ตฌํํ ์๋น์ค ํด๋์ค๊ฐ ํ์ํ๋ค.

#### 6. AuthenticationProvider์์ UserDetails ๊ฐ์ฒด๋ฅผ ๋๊ฒจ๋ฐ๊ณ  ์๋ ฅ๋ฐ์ ๋น๋ฐ๋ฒํธ์ ๊ฐ์ฒด์ ๋น๋ฐ๋ฒํธ๋ฅผ ๋น๊ต.
#### 7. ์ธ์ฆ ์ฑ๊ณต์ ๊ถํ, ์ฌ์ฉ์ ์ ๋ณด๋ฅผ ๋ด์ ์์ ํ Authentication ๊ฐ์ฒด๋ฅผ AuthenticationFilter ์ ์ ๋ฌ.
- AuthenticationManager ๋ ์์ ํ ์ธ์ฆ๊ฐ์ฒด๋ฅผ ๊ด๋ จ ์ธ์ฆ ํํฐ๋ก ๋ค์ ๋ฐํํ๋ค.
- ์ธ์ฆ์ ์คํจํ๋ฉด AuthenticationException ์ด ๋ฐ์ํ๊ณ , AuthenticationEntryPoint ์ ์ํด ์ฒ๋ฆฌ๋๋ค.
#### 8. AuthenticationFilter๋ Authentication ๊ฐ์ฒด๋ฅผ SecurityContext์ ์ ์ฅ.


- ### ์์กด์ฑ ์ถ๊ฐ
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

- ### ์คํ๋ง ์ํ๋ฆฌํฐ
    - ์น ์ํ๋ฆฌํฐ
    - ๋ฉ์๋ ์ํ๋ฆฌํฐ
    - ๋ค์ํ ์ธ์ฆ ๋ฐฅ๋ฒ ์ง์ : LDAP, ํผ ์ธ์ฆ, Basic ์ธ์ฆ, OAuth, ...
    
- ### ์คํ๋ง ๋ถํธ ์ํ๋ฆฌํฐ ์๋ ์ค์ 
    - SecurityAutoConfiguration : ์ฌ์ค์ spring boot ์์ ๋ณ๊ฒฝํ ๋ถ๋ถ์ ๋ณ๋ก ์๋ค.
    - UserDetatilsSeviceAutoConfiguration :
      > UserDetailsService.class, AuthenticationManager.class, AuthenticationProvider.class ๊ฐ ์์ ๋ ์ค์  ํ์ผ์ด ์ ์ฉ๋๊ณ ,
      ๋๋คํ ์ ์ ๋ฅผ ์์ฑํด ์ค๋ค.

- ### ๊ธฐ๋ณธ ์ฌ์ฉ์.
    - UserName : user
    - password : application์ ์คํ ํ  ๋ ๋ง๋ค ์์ฑ๋๋ ๋๋ ๊ฐ(console์ ์ถ๋ ฅ๋จ.)
    - spring.security.username
    - spring.security.password
    
- ## ์คํ๋ง ์ํ๋ฆฌํฐ Test
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
> ๊ฐ์ง ์ธ์ฆ ์ ์ ๋ฅผ ๋ฃ์ด์ฃผ์ด, ๊ถํ์ด ์์ด ์คํํ  ์ ์๋ ํ์คํธ๋ฅผ ์คํํ  ์ ์๊ฒ ํด์ค๋ค.

### 2. Annotation
```java
@WithMockUser
class or Method
```
> ์ ์  ์ ๋ณด๋ฅผ ์ง์  ๋ฃ์ด์ฃผ์ด ํ์คํธ.


## ๐ Spring Security Custom
***

## 1. extends WebSecurityConfigurerAdapter
```java
//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/admin").hasRole(Role.ADMIN.name())  // admin URL์ ๊ด๋ฆฌ์ ์ ๊ทผ ๊ฐ๋ฅ.
              .antMatchers("/my").hasRole(Role.STUDENT.name())  // my URL ์ ํ์๋ง ์ ๊ทผ ๊ฐ๋ฅ
              .anyRequest().permitAll() // ๋๋จธ์ง URL์ ์ธ์ฆ ํ์.
              .and()
            .formLogin()// ๋ง๋ค์ด์ฃผ๋ formLogin template ์ฌ์ฉ.
              .and()
            .httpBasic() // accept header์ http๊ฐ ์๋ ๊ฒฝ์ฐ httpBasic ์ฌ์ฉ.
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
- configure ๋ฉ์๋๋ฅผ ์ค๋ฒ๋ผ์ด๋ฉ ํ์ฌ ์ ๊ทผ ๊ถํ, ๋ก๊ทธ์ธ ํผ ๋ฑ ์ค์ . @EnableWebSecurity์ @Configuration์ด ํฌํจ๋์ด ์๋ค. 
- hasRole ์์ ์๋์ผ๋ก ์์ ROLE_ ์ ๋ถ์ฌ์ฃผ๊ธฐ ๋๋ฌธ์ .name()์ผ๋ก ๊ฐ์ ธ์ด.

## 1-1 @EnableGlobalMethodSecurity()
1๋ฒ๊ณผ ๊ฐ์ด HttpSecurity๋ฅผ ์ด์ฉํ์ฌ ํ๋ฒ์ ์ ๊ทผ ๊ถํ์ ์ง์ ํ ์๋ ์์ง๋ง ์ ๋ธํ์ด์์ ์ด์ฉํ์ฌ ๋ฐ๋ก ์ ๊ทผ ๊ถํ์ ์ง์ ํ  ์ ์๋ค.
์ด๋ฅผ ์ํด์๋ Configuration ์์ ```@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true ...)``` ์ ๋ธํ์ด์์ด ํ์ํ๋ค.
```java
@PreAuthorize("hasRole('ROLE_ADMIN')")
public void adminPage(){...}

@PostAythorize("hasRole('ROLE_ADMIN')")
public void adminPage(){...}

@Secured("ROLE_ADMIN")
public void adminPage(){...}
```


## 2. implements UserDetailsService
```java
@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // User ์ ๋ณด๋ฅผ ๋ฐ์ ๊ณ์  ์์ฑ.
    public Account createAccount(String username, String password){
        Account account = new Account();
        account.setUserName(username);
        account.setPassword(passwordEncoder.encode(password));

        return accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
          Optional<Account> byUserName= accountRepository.findByUserName(userName);
          Account account =  byUserName.orElseThrow(()-> new UsernameNotFoundException(userName)); // ์์ผ๋ฉด ์์ธ๋ฅผ ๋์ง๊ณ , ์์ผ๋ฉด Account ๊ฐ์ฒด return
    
          return new User(account.getUserName(), account.getPassword(), authorities(account));
    }
    
    private Collection<? extends GrantedAuthority> authorities(Account account) {
        return Arrays.asList(new SimpleGrantedAuthority(account.getRole().getKey()));
    }
}
```
- UserDetails๋ฅผ ๊ตฌํํ User ํด๋์ค๋ฅผ SpringSecurity์์ ์ง์.
  ์ฌ์ฉ์๊ฐ ์๋ ฅํ ์ ๋ณด์ DB์ ์ ์ฅ๋ ์ ์  ์ ๋ณด๋ฅผ ๋น๊ตํ๊ณ  ์กด์ฌํ๋ฉด ROLE ๋ถ์ฌ.
- ๊ฐ๋ฐ์๋ UserDetailsServicedml loadUserByUsername() ๋ฉ์๋๋ฅผ ๊ตฌํํ์ฌ DB์์ username ์ผ๋ก ์ ๋ณด๋ฅผ ์ฐพ๋๋ค.
    Provider ์์๋ ๋ฐํ๋ฐ์ User ๊ฐ์ฒด๋ฅผ ์ด์ฉํ์ฌ ์ฌ์ฉ์๊ฐ ์๋ ฅํ ํจ์ค์๋์ ๋น๊ต. 

## 3.Password Encode
```java
// config ํด๋์ค์ ์ ์.
@Bean
public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}

// Service ํด๋์ค์์ ์ฌ์ฉ.
@Autowired
private PasswordEncoder passwordEncoder;

account.setPassword(passwordEncoder.encode(password));
```
> - Password ์ธ์ฝ๋๋ฅผ Bean์ผ๋ก ์ ์.
> - Password๋ฅผ DB์ ์ ์ฅํ๊ธฐ ์  ์ํธํํ๊ณ  ์ ์ฅ.
> > ![img_15.png](img/img_1.png)


์คํ๋ง ์ํ๋ฆฌํฐ์์ ๊ถ์ฅํ๋ PasswordEncoder๋ bcrypt ํด์ ์๊ณ ๋ฆฌ์ฆ์ ์ฌ์ฉํ๋ค.
#### ๐ก ์ bcrypt ๋ฅผ ์ฌ์ฉํ ๊น?
bcrypt๋ blowfish ์ํธ๋ฅผ ๊ธฐ๋ฐ์ผ๋ก ์ค๊ณ๋ ์ํธํ ํจ์์ด๋ค. blowfish๋ key setup phase ๋ผ๋ ๋ฌด๊ฑฐ์ด ์ ์ฒ๋ฆฌ๋ฅผ ์๊ตฌํ์ฌ
์ฐ์ฐ์ ๋๋ฆฌ๊ฒ ๋ง๋ ๋ค, ๋
์ฌ๋ฌ๋ฒ ๋ฐ๋ณต์ ์ผ๋ก ์ํธํ๋ฅผ ํ๊ธฐ ๋๋ฌธ์ ๋ฐ๋ณต ํ์๋ฅผ ์กฐ์ ํ์ฌ ์ฐ์ฐ ์๋๋ฅผ ๋ฆ์ถฐ ๋ธ๋ฃจํธ-ํฌ์ค ๊ณต๊ฒฉ์ ๋๋นํ  ์ ์๋ค.  
๋ฐ๋ผ์ ์ผ๋ฐ์ ์ผ๋ก GPU ์ฐ์ฐ์ ์ต์ ํ๋ 32๋นํธ ์ฐ์ฐ์ ์ฌ์ฉํ๋ SHA ์ํธํ ๋ฐฉ์๋ณด๋ค ๋น๊ต์  ๋ณด์์ด ๊ฐ๋ ฅํ๋ค.

๋ ๋ค๋ฅธ ๊ฐ๋ ฅํ ์๊ณ ๋ฆฌ์ฆ์ ๋นํด ๊ตฌํ์ด ๊ฐ๋จํ๋ค๋ ์ฅ์ ์ด ์๋ค.
์กฐ๊ธ ๋ ๊ฐ๋ ฅํ ๋ณด์์ ์ํ๋ค๋ฉด scrypt๋ Argon2id ๋ฑ์ ๊ณ ๋ คํ  ์ ์๋ค.

> - key setup phase: cost(๋ฐ๋ณต ํ์,์๊ฐ), salt, password ํ์.
> - password๋ฅผ ๊ฐ์ง๊ณ  key stretching ์ ํ์ฌ ์ฐ์ฐ ์๋๋ฅผ ๋ฆ์ถ๋ค.
> - ์ํธ(salt) : ํด์ปค๊ฐ ์ด๋ฏธ ์ฌ๋ฌ๊ฐ์ ํด์ฑ ์๊ณ ๋ฆฌ์ฆ์ ์ฌ์ฉํ์ฌ ์ ์ฅํด๋๊ณ , ํด์๊ฐ์์ ๋น๋ฐ๋ฒํธ๋ฅผ ์ถ๋ก ํ  ์ ์๊ธฐ ๋๋ฌธ์ ์ด๋ฅผ ๋ฐฉ์งํ๊ธฐ ์ํด ๊ณ ์.
> > hash(12345678) -> aaaabbbb    
> > hash(12344567+salt)-> aacabaebb    
> > hash(12344567+salt)-> cafcabaekkb
> > ๋งค๋ฒ ๋ค๋ฅธ ๊ฐ์ด ๋์ด.

- password์ ๋น๊ต.
```java
passwordEncoder.matches(String rawPassword, String encodedPassword)
```

## ๐ ์ธ์ฆ ๊ฐ์ฒด ๊ฐ์ ธ์ค๊ธฐ
***
ํ์ฌ ์ธ์ฆ๋์ด์๋ ์ฌ์ฉ์๋ฅผ ๊ฐ์ ธ์ค๊ธฐ ์ํด์๋ Principal ์ด๋ @AuthenticationPrincipal ๋ฅผ ์ฌ์ฉํ  ์ ์๋ค

### ๐ง Principal
```java
@GetMapping("/test")
public String principal(Principal principal){
    String principalName = principal.getName();
    ...
}
```
Principal์ ์๋ฐ ํ์ค ๊ฐ์ฒด์ด๋ค. ํ์ง๋ง ์ฐ๋ฆฌ๊ฐ ํด๋น ๊ฐ์ฒด์์ ๊ฐ์ ธ์ฌ ์ ์๋ ๊ฒ์ name ๋ฐ์ ์๋ค.
 @AuthenticationPrincipal์ ์ด์ฉํ๋ฉด UserDetailsService ์์ ๋ฐํํ๋
๊ฐ์ฒด๋ฅผ ๋ฐ์ ์ฌ์ฉํ  ์ ์๋ค. 

### ๐ง @AuthenticationPrincipal
```java
@GetMapping("/test")
public String principal(@AuthenticationPrincipal User user){
    user.getUsername();
    user.getPassword();
    user.getAuthorities();
    ...
}
```
User ๊ฐ์ฒด๋ฅผ ๊ฐ์ ธ์ค๊ธฐ ๋๋ฌธ์ name ์ด์ธ์ password, ๊ถํ ๋ฑ๋ ๊ฐ์ ธ์ฌ ์ ์๋ค.
ํ์ง๋ง ์ด๋ฐ ์ ๋ณด๋ณด๋ค ๋ณดํต ์ํ๋ ๊ฒ์ DB์ ์ ์ฅ๋ ์ฌ์ฉ์์ ์ ๋ณด์ผ ๊ฒ์ด๋ค.
์ํฐํฐ ๊ฐ์ฒด๋ ๋ค์๊ณผ ๊ฐ์ด ๊ฐ์ ธ์ฌ ์ ์๋ค.


```java
@AuthenticationPrincipal(expression = "#this =='anonymousUser' ? null :account")
```
User์ธ์ฆ์ด ๋์ง ์์ผ๋ฉด Principal ์ "anonymousUser"๋ผ๋ ๋ฌธ์์ด์ด๋ค.
์ธ์ฆ์ด ๋์ด์์ง ์๋ค๋ฉด null์ ์ธ์ฆ์ด ๋์ด์๋ค๋ฉด principal์์ account ๊ฐ์ฒด๋ฅผ ๊บผ๋ด ๋๊ฒจ์ค๋ค.

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
- account ๋ผ๋ ํ๋๋ช์ @AuthenticationPrincipal์ account ์ ๋งคํ๋๋ค.
- @AuthenticationPrincipal์ UserDetailsService์์ ๋ฐํํ๋ ๊ฐ์ฒด๋ UserDetails ํ์์ ๊ฐ์ฒด์ด๋ค.
๋๋ฌธ์ ์ด๋ฅผ ๊ตฌํํ ํด๋์ค์ธ User๋ฅผ ์์๋ฐ๋๋ค.

```java
@Override
public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {
        Account account = accountRepository.findByNameOrEmail(nameOrEmail);
        checkIfAccountExists(nameOrEmail, account);
        return new UserAccount(account);
}
```
userDetailsService์ loadUserByUsername() ์์ ๋ฐํ๋ UserAccount ๊ฐ์ฒด๋ฅผ ๋ฐํํ๋๋ก ๋ณ๊ฒฝํ๋ค.   

Account ๊ฐ์ฒด๋ฅผ ์ฃผ์๋ฐ๊ธฐ์ํ @AuthenticationPrincipal(...)์ด ๋๋ฌด ๊ธธ๋ค. ์ด๋ฅผ ๋ฐ๋ก ์ ๋ธํ์ด์์ผ๋ก ์์ฑํด์ฃผ์.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this =='anonymousUser' ? null :account")
public @interface CurrentUser {

}
```

## ๐ Remember-Me
***
๊ธฐ๋ณธ์ ์ผ๋ก Session์ ํ์ ์์์ 30๋ถ์ผ๋ก ์ค์ ๋์ด ์๋ค.
```properties
server.servlet.session.timeout=30m
```
RememberMe๋ ์ธ์์ด ๋ง๋ฃ ๋๋๋ผ๋ ๋ก๊ทธ์ธ์ ์ ์งํ๊ธฐ ์ํด ์ฌ์ฉํ๋ ๋ฐฉ๋ฒ์ด๋ค.
์ฟ ํค์ ์ธ์ฆ ์ ๋ณด๋ฅผ ๋จ๊ฒจ๋๊ณ  ์ธ์์ด ๋ง๋ฃ๋๋ฉด ์ฟ ํค์ ๋จ์์๋ ์ ๋ณด๋ก ์ธ์ฆ์ ์๋ํ๋ค.

- ํด์ ๊ธฐ๋ฐ์ ์ฟ ํค
> - UserName
> - Password
> - ๋ง๋ฃ๊ธฐ๊ฐ
> - Key
> - ์ฟ ํค๋ฅผ ํ์ทจ๋นํ๋ฉด ๊ทธ ๊ณ์ ์ ํ์ทจ๋นํ ๊ฒ๊ณผ ๊ฐ๋ค.

- ์กฐ๊ธ ๋ ์์ ํ๊ฒ ๊ด๋ฆฌํ๊ธฐ
> - ์ฟ ํค ์์ ๋๋คํ token์ ๋ง๋ค์ด ๊ฐ์ด ์ ์ฅํ๊ณ  ์ธ์ฆ ๋๋ง๋ค ๋ณ๊ฒฝ.
> - Username, ํ ํฐ
> - ํด๋น ๋ฐฉ๋ฒ๋ ์ทจ์ฝ, ํด์ปค๊ฐ ์ฟ ํค๋ก ์ธ์ฆ์ ํ๊ฒ๋๋ฉด ์ ์ฌ์ฉ์๋ ์ธ์ฆํ  ์ ์๊ฒ ๋จ.

- ๊ฐ์ ๋ ๋ฐฉ๋ฒ
> - UserName, Token(๋๋ค, ๋งค๋ฒ ๋ณ๊ฒฝ), ์๋ฆฌ์ฆ(๋๋ค,๊ณ ์ )
> - ์ฟ ํค๋ฅผ ํ์ทจ ๋นํ๋ฉด ์ ์ฌ์ฉ์๋ ์ ํจํ์ง ์์ ํ ํฐ๊ณผ ์ ํจํ ์๋ฆฌ์ฆ,UserName ์ผ๋ก ์ ์ํ๊ฒ ๋๊ณ , ์ด ๊ฒฝ์ฐ, ๋ชจ๋  ํ ํฐ์ ์ญ์ ํ์ฌ ํด์ปค๊ฐ ๋์ด์ ์ฟ ํค๋ฅผ ์ฌ์ฉํ์ง ๋ชปํ๋๋ก ๋ฐฉ์งํ  ์ ์๋ค.


- ### ์คํ๋ง ์ํ๋ฆฌํฐ ์ค์  : ํด์ ๊ธฐ๋ฐ
```java
http.rememberMe().key("๋๋ค ํค๊ฐ");
```

- ### ์คํ๋ง ์ํ๋ฆฌํฐ ์ค์ : ๊ฐ์ ๋ ์์ํ ๊ธฐ๋ฐ ์ค์ .

```html
<div class="form-grop form-check">
    <input type="checkbox" class="form-check-input" id="rememberMe", name="remember-me" checked>
    <label class="form-check-label" for="rememberMe" aria-describedby="rememberMeHelp">๋ก๊ทธ์ธ ์ ์ง</label>
</div>
```
- name ์ remember-me๋ก ์ฃผ๊ณ  check box๊ฐ true ๊ฐ์ด๋ฉด remember-me ๊ธฐ๋ฅ ์คํ.

```java
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) {
        
        http.rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(tokenRepository());
    }

    private PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }
}

```
- RememerMe๋ฅผ ์ฌ์ฉํ๊ธฐ ์ํด์๋ userDetailsService์ TokenRepository๋ฅผ ๋๊ฒจ์ฃผ์ด์ผ ํ๋ค.\
  ์ฌ๊ธฐ์๋ UserDetilasService๋ฅผ ๊ตฌํํ AccountService๋ฅผ ๋๊ฒจ ์ฃผ์๋ค.

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
- JdbcTokenRepositoryImpl์์ ํ ํฐ์ ์ ์ฅํ  PersistentLogin ํ์ด๋ธ์ ์์ฑํด์ค๋ค.
- JdbcTokenRepositoryImpl class ์์ ํ์ธํ  ์ ์๋ค.

![img.png](img.png)     
์๋ ๋ก๊ทธ์ธ์ ์ค์ ํ๋ฉด Persistent_login ํ์ด๋ธ์ ์ ๋ณด๊ฐ ์ ์ฅ๋๋ค.
username, ํ ํฐ, ์๋ฆฌ์ฆ๊ฐ ์ ์ฅ๋ ๊ฒ์ ํ์ธ ํ  ์ ์๋ค. ํ ํฐ์ ํ์ทจ ๋นํ๊ฒ ๋๋ฉด
์ฌ์ฉ์๋ username๊ณผ ์๋ฆฌ์ฆ, ์ ํจํ์ง ์์ ํ ํฐ์ผ๋ก ์ ์์ ์๋ํ๊ณ , ์ด ๋ ๋ชจ๋  ํ ํฐ ์ ๋ณด๋ฅผ ์ ๊ฑฐํด ํด์ปค๊ฐ ์ ์ํ์ง ๋ชปํ๋๋ก ๋ง๋๋ค.

## ๐ ํ์คํธ์์ ์ฌ์ฉ์ ์ธ์ฆ
***
ํ์คํธ์ ์งํ์ ์ํด์ ์ฌ์ฉ์์ ์ธ์ฆ์ด ํ์ํ  ๋๊ฐ ์๋ค ์ด ๋ ์ฌ์ฉ๋๋ ์ด๋ธํ์ด์์ด
```@WithMockUser```์ด๋ค.

```java
@WithMockUser(username = "kimtaejun", password = "123123123", roles = "USER")
```
ํ์ง๋ง ์ด์ธ์ ๋ค๋ฅธ ์ ๋ณด๋ฅผ ์ปค์คํํ๊ฒ ์ค์ ํ๊ณ  ์ถ๋ค๋ฉด ```@WithSecurityContext``` ๋ฅผ ์ฌ์ฉํ  ์ ์๋ค.

```java
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithAccountSecurityContextFactory.class )
public @interface WithAccount {
    String value();
}
```
value : username
```java
@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private final AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {

        // {-- DB์ ์ ๊ณ์  ์ ์ฅ --}
        
        // UserDetails ๊ฐ์ฒด ์์ฑ
        UserDetails principal = accountService.loadUserByUsername(withAccount.value());
        
        // Authetication ๊ฐ์ฒด ์์ฑ
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        
        // Context์ ์ธ์ฆ ๊ฐ์ฒด ์ ์ฅ.(๋ก๊ทธ์ธ)
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
```
- ํ์คํธ๋ฅผ ์งํํ  ๋ ํน์  ์ปจํ์คํธ๊ฐ ๋ฑ๋ก๋์ด ์์ด์ผํ๋ ํ์คํธ์ ๊ฒฝ์ฐ(๋ก๊ทธ์ธ) Securitycontext๋ฅผ ์์ฑํ์ฌ ๋ฑ๋กํ ํ ํ์คํธ๋ฅผ ์งํํ  ์ ์๋ค.
> 1. ๊ณ์  ์์ฑ
> 2. UserDetails ๊ฐ์ฒด ์์ฑ. (๊ตฌํ์ฒด์ธ springSecurity.core.User)
> 3. ์ธ์ฆ ํ ํฐ ์์ฑ(principal, password, Authorities)
> 4. Security Context๋ฅผ ์์ฑํ๊ณ  ์ธ์ฆํ ํฐ์ ๋ฑ๋ก.

class ๋จ์๋ก ```WithMockUser``` , ```WithSecurityContext```๋ฅผ ์ฌ์ฉํ๊ณ  ํน์  ๋ฉ์๋์์๋ ์ธ์ฆ์ด ๋์ง ์์ ์ํ๋ก ํ์คํธ ํ๊ณ  ์ถ๋ค๋ฉด
๋ฉ์๋์์ ```@WithAnonymousUser```๋ฅผ ์ฌ์ฉํ  ์ ์๋ค.


<br><br><br>
> - https://doozi0316.tistory.com/entry/Spring-Security-Spring-Security%EC%9D%98-%EA%B0%9C%EB%85%90%EA%B3%BC-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95
> - https://mangkyu.tistory.com/7
> - https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8#curriculum
> - https://jusths.tistory.com/158
> - https://ncucu.me/137