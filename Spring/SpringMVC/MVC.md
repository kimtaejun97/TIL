
# 📌 Spring MVC 구조
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

DispatcherServlet -> FrameworkSerblet -> HttpServletBen -> HttpServlet 의 구조로 상속되어 있다.    
결국 중요한 것은 DispatcherServlet은 HttpServlet을 상속받았다는 것이다. 때문에 HttpSevlet의 기능을 모두 사용할 수 있다.
기본적으로 모든 경로에 대해 매핑되어 있다. 다른 서블릿도 함께 동작하는데 이는 자세한 경로가 더 우선순위가 높기 때문이다.

서블릿이 호출되면 HttpServlet이 제공하는 service()가 호출된다. 여러 로직들이 실행되는데 가장 중요한 것은
doDispatch() 메서드이다.
다음은 doDispach() 메서드의 일부이다.
```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
        // Determine handler for the current request.
        mappedHandler = getHandler(processedRequest);
        if (mappedHandler == null) {
            noHandlerFound(processedRequest, response);
            return;
        }

        // Determine handler adapter for the current request.
        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
        

        if (!mappedHandler.applyPreHandle(processedRequest, response)) {
            return;
        }

        // Actually invoke the handler.
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

        if (asyncManager.isConcurrentHandlingStarted()) {
            return;
        }

        mappedHandler.applyPostHandle(processedRequest, response, mv);
        
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }
}
```
큰 흐름만을 보기 위해 예외 처리와 같은 다른 부수적인 코드는 제거하였다.   
처음에 getHandler() 메서드를 실행하여 요청에 맞는 핸들러를 찾아온다. 그 다음에 해당 핸들러를 처리할 수 있는 어댑터를 getHandlerAdapter()메서드를 이용하여 가져온다.
가져온 핸들러의 handle() 메서드를 실행하여 핸들러에서 요청을 처리한다. 결과 값으로는 ModelAndView 객체를 반환 받는다.
실행전에 HandlerInterceptor 인 PreHandler()를 실행하는 것도 확인할 수 있다.

반환받은 ModelAndView 객체에서 ViewName을 가져오고 processDispatchResult() 메서드를 실행한다.
실행전에 PostHandler()를 실행 한다. processDispatchResult()를 확인해보면 View를 랜더링 하는 부분을 확인할 수 있다.    
```render(mv, request, response);```


### ☝️ HandlerMapping
HandlerMapping 에서는 요청을 처리할 수 있는 컨트롤러를 찾아 오고 미리 등록된 HandlerAdapter 목록을 순회하며, 이를 처리할 HadlerAdapter 를 찾는다.

DispatcherServlet은 다양한 HandlerMapping을 리스트로 가지고 있다.

```java
// matchingBeans.values()는 HandlerMapping Interface 타입. HandlerMapping 들은 이를 구현한다.
this.handlerMappings = new ArrayList<>(matchingBeans.values());
```
1. **RequestMappingHandlerMapping**
    > - 스프링의 기본 핸들러 맵핑, @RequestMapping, @Controller 애노테이션이 붙은 컨트롤러를 처리한다.
    ```java
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
                AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
    }
    ```
    > - RequestMappingHandlerMapping 은 ```@Controller``` 나 ```@Component(빈 등록용) + @RequestMapping```
    가 클래스 레벨에서 사용될때 선택된다.
2. SimpleUrlHandlerMapping
    > - URL과 Controller를 직접 맵핑
3. BeanNameUrlHandlerMapping
    > - URL과 Bean 이름을 가지고 컨트롤러와 맵핑
4. ControllerBeanNameHandlerMapping
    > - Bean의 아이디나 이름을 이용해 맵핑    
    > - ex) @Component("/test") -> /test 와 맵핑.
5. ControllerClassNameHandlerMapping
    > - URL과 Controller 명을 일정한 규칙으로 맵핑
    > - ex) main/* -> MainController에서 처리.
6. DefaultAnnotaitonHandlerMapping
    > - @RequestMapping 어노테이션을 이용하여 요청을 처리할 컨트롤러를 구현한다.
    > - ex) RequestMapping("/test")
    > RequestMappingHandlerMapping이 나오면서 Deprecated 되어간다. 


HandlerMapping은 Handler들의 Map을 가진다.
```java
// RequestMappingHandlerMapping, prefixe Map을 가진다.
private Map<String, Predicate<Class<?>>> pathPrefixes = Collections.emptyMap();

// SimpleUrlHandlerMapping, <URL, Handler>의 Map을 가진다.
private final Map<String, Object> urlMap = new LinkedHashMap<>();
```
### ☝️ HandlerAdapter
핸들러 어댑터는 컨트롤러의 메서드를 실행하여 실제 요청을 처리한다. 이를 위해 다양한 핸들러 어댑터가 서블릿에 미리 등록되어 있다.
```java
//matchingBeans.values()는 HandlerAdapters Interface 타입. Adapter 들은 해당 인터페이스를 구현한다.
this.handlerAdapters = new ArrayList<>(matchingBeans.values());
```

- **RequestMappingHandlerAdapter**
  > @RequestMapping 애노테이션 처리.
- HttpRequestHandlerAdapter
  > HttpRequestHandler Interface 처리.
- SimpleControllerHandlerAdapter
  > Controller Interface 처리.
- HandlerFunctionAdapter
    > Web Flux 요청 처리.

이중 주로 스프링에서 사용하는 @RequestMapping 애노테이션에서 동작하는 어댑터는 이름에서도 알 수 있듯 3번째 RequestMappingHandlerAdapter 이다.    
@GetMapping, @PostMapping 등의 어노테이션에도 @RequestMapping 이 포함되어 있다.

ex) ```@GetMapping``` == ```@RequestMapping(method = RequestMethod.GET)```, 편리하게 사용하기 위한 애노테이션을 위한 애노테이션.

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

- BeanNameViewResolver
  > 빈 이름으로 뷰를 찾아 반환한다.
- UrlBasedViewResolver
  > redirect 등 ViewName이 아닌 URL로 뷰를 설정한다.
- ThymeleafViewResolver
  > 타임리프 이용시 사용되는 리졸버.
- InternalResourceViewResolver
  > - JSP를 뷰 기술로 이용할 경우 등록한다. 가장 마지막에 오는 리졸버.
  > ```properties
  > # 아래와 같이 설정 정보를 사용해서 등록한다.
  > spring.bvc.view.prefix=/WEB-INF/view/
  > spring.mvc.view.suffix=.jsp
  > ```
  > - View Interface를 구현한 InternalResourceView 를 반환한다. -> forward() 호출.


# 📌 Spring MVC 기본 기능

## 🧐 요청 매핑

### ☝️ 기본 매핑
```java
@RequestMapping(value = {"/hello", "/hello2"})
```
기본 적인 RequestMapping ```{}``` 를 이용하여 여러개의 url을 매핑할 수도 있다.

### ☝️ 메서드 지정
```java
@RequestMapping(value = "/mapping-get", method = RequestMethod.GET)

@GetMapping("/mapping-get")
```
@RequestMapping 의 method 속성값을 이용하여 메서드를 지정할 수 있다.
Spring 에서는 이를 합쳐둔 @GetMapping과 같은 애노테이션을 지원해준다. 실제로 @GetMapping 애노테이션의 선언부분을 보면
다음과 같은 부분을 확인 할 수 있다.```@RequestMapping(method = RequestMethod.GET)```
Post, Delete, Put, Patch 또한 존재한다.

### ☝️ PathVariable
```java
@GetMapping("/mapping/{userId}")
public String mappingPath(@PathVariable String userId){
    ...
}
```
경로에 ```{}``` 와 같이 쓰고, 파라미터 명을 일치시키면 해당 자리에 들어간 값을 파라미터에 넣어준다.
```@PathVariable("userId") String id``` 와 같이 사용하여 파라미터명을 다르게 설정할 수도 있다.

### ☝️ Params 조건
```java
@GetMapping(value = "/mapping-param", params = "mode=debug")
public String mappingParam(@RequestParam("mode") String mode){
    ...
}
```
```params``` 속성을 이용하여 쿼리 파라미터의 조건을 지정한다. '=', '!=', '!' 와 같이 지정할 수 있으며, mode와 같이 쿼리 파라미터의
이름만을 적으면 해당 파라미터 이름이 있는지를 본다. 조건에 맞지 않으면 요청을 받지 않는다.(400 Bad Request 발생.)


### ☝️ Headers 조건
```java
@GetMapping(value = "/mapping-header", headers = "mode=debug")
public String mappingHeader(@RequestHeader("mode") String mode){
    ...
}
```
Params와 유사하다. Headers 에서는 헤더의 조건을 검사한다.

### ☝️ Content-Type, Accept 헤더 조건
```java
@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
public String mappingConsumes(){
    ...
}
```
```consumes``` 는 Content-Type 을, ```produces``` 는 Accept 헤더에 조건을 건다.   
consumes 와 일치하지 않으면 415 Unsupported Media Type 에러가, produces와 일치하지 않으면 406 Not Acceptable 에러가 발생한다.



<br><br><br>
> - https://codingnotes.tistory.com/28
> - https://velog.io/@jihoson94/Spring-MVC-HandlerAdapter-%EB%B6%84%EC%84%9D%ED%95%98%EA%B8%B0
> - https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-exceptionhandlers
> - http://www.mungchung.com/xe/spring/21278