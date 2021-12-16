# 📌 Spring MVC
![img.png](../img/img_2.png)     
> Spring MVC의 동작 흐름도.

처리 순서는 다음과 같다
#### 1. DispatcherSevlet 에서 요청을 받음.
> - 요청을 받기전 CORS 등의 Filter 를 먼저 거친다.
#### 2. HandlerMapping 에서 요청을 처리할 핸들러(컨트롤러)를 찾음.
#### 3. 요청을 처리할 수 있는 HandlerAdapter를 찾아 요청을 처리.
#### 4. 핸들러의 리턴값을 보고 처리 판단
> - @ResponseBody 라면 Converter를 사용하여 데이터 반환(REST API) , 아니라면 5번 과정.
#### 5. viewName을 받아 ViewResolver 에게서 해당하는 뷰를 찾아 모델 데이터 랜더링.
#### 6. 예외가 발생한다면 ExeceptionHadler에게 예외 처리 위임.
#### 7. 응답 반환.

***

### ☝️ DispatcherServlet
클라이언트의 요청을 가장 앞에서 처리하는 중앙 서블릿이다. 여러가지 컴포턴트들을 가지고 있으며, 이들에게 처리를 위임한다.
클라이언트의 요청을 받아 HandlerMapping에게 Handler를 찾아줄 것을 요청한다.

### ☝️ HandlerMapping
HandlerMapping 에서는 요청을 처리할 수 있는 컨트롤러를 찾아 오고 미리 등록된 HandlerAdapter 목록을 순회하며, 이를 처리할 HadlerAdapter 를 찾는다. 

### ☝️ HandlerAdapter
핸들러 어댑터는 컨트롤러의 메서드를 실행하여 실제 요청을 처리한다. 이를 위해 다양한 핸들러 어댑터가 서블릿에 미리 등록되어 있다.
- HttpRequestHandlerAdapter
- SimpleControllerHandlerAdapter
- **RequestMappingHandlerAdapter**
- HandlerFunctionAdapter

이중 주로 스프링에서 사용하는 @RequestMapping 애노테이션에서 동작하는 어댑터는 3번쨰 RequestMappingHandlerAdapter 이다.    
@GetMapping, @PostMapping 등의 어노테이션에도 @RequestMapping 이 포함되어 있다.

핸들러 어댑터는 먼저 요청을 받아 HTTP Method값을 확인하여 처리 가능한지 확인하고, 없다면 예외를 발생시킨다.
또한 세션을 이용하는지 확인하여 세션이 존재한다면 mutex 를 이용하여 Thread-Safe하게 처리할 수 있도록 한다.    
마지막으로 가장 핵심인 invokeHandlerMethod 에서 요청을 처리하고 결과를 반환한다.(ModelAndView) 

### ☝️ HandlerInterceptor
컨트롤러의 메서드를 실행하기 전에 핸들러 인터셉터에 지정된 일을 실행한다. HandlerInterceptor 인터페이스를 구현 하여 사용할 수 있다.
- preHandle(HttpServletRequest request, HttpServletResponse response, Object handler): 요청 실행 전.
- postHandle(request, response, handler, ModelAndView modelAndView): 요청이 실행된 후.
- afterCompletion(request, response, handler, Exception ex): view를 렌더링한 후, 요청을 완료, 응답 전

### ☝️ HandlerExceptionResolver
예외가 발생했을 때 DispatcherServlet 에서는 예외를 HandlerExceptionResolver 에게 위임한다.
때문에 @ExceptionHandler 에서 예외를 공통적으로 처리할 수 있다. 아래의 4개의 구현체를 가진다.

#### 1. SimpleMappingExceptionResolver 
> 예외 클래스 이름과 ErrorViewName 을 매핑. 브라우저 응용 프로그램에서 오류 페이지를 렌더링하는데 유용하다.
#### 2. DefaultHandlerExceptionResolver
> Spring MVC에서 발생한 예외를 HTTP 상태 코드에 매핑한다.
#### 3. ResponseStatusExceptionResolver
> @ResponseStatus 주석의 값을 기반으로 HTTP 상태 코드에 매핑한다. ```@ResponseStatus(HttpStatus.BAD_REQUEST)```
#### 4. ExceptionHandlerExceptionResolver
> @ExceptionHandler 에서 메서드를 호출하여 예외를 해결 한다.

### ☝️ @ControllerAdvice
@ExceptionHandler 를 사용하여 예외를 처리하고자 할때 @Controller 보다 더 전역적으로 사용하고 싶을 때 사용한다.
```java
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String handleIllegalArgumentException(IllegalArgumentException exception, @CurrentUser Account account, HttpServletRequest request){
        String username = getUsername(account);
        log.error("[{} Requested {}] But, throw IllegalArgumentException {}",username, request.getRequestURI(),exception.getMessage());

        return "/error/4xx";
    }
    
    private String getUsername(@CurrentUser Account account) {
        String username = "";
        if (account != null) {
            username = account.getUsername();
        }
        return username;
    }
}
```

### ☝️ ViewResolver
실제 뷰를 랜더링 하고 반환한다.

- UrlBasedViewResolver: redirect 등 ViewName이 아닌 URL로 뷰를 설정한다.
- InternalResourceViewResolver: JSP를 뷰 기술로 이용할 경우 등록한다. 가장 마지막에 오는 리졸버. 


<br><br><br>
> - https://codingnotes.tistory.com/28
> - https://velog.io/@jihoson94/Spring-MVC-HandlerAdapter-%EB%B6%84%EC%84%9D%ED%95%98%EA%B8%B0
> - https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-exceptionhandlers