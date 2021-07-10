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




