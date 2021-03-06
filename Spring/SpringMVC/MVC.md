# ๐ ๋ชฉ์ฐจ
- ### [Spring MVC ๊ตฌ์กฐ](#-spring-mvc-๊ตฌ์กฐ)
- ### [Spring MVC ๊ธฐ๋ณธ๊ธฐ๋ฅ](#-spring-mvc-๊ธฐ๋ณธ๊ธฐ๋ฅ)
    - #### [์์ฒญ ๋งคํ](#-์์ฒญ-๋งคํ)
    - #### [Method Argument](#-method-arguments)


# ๐ Spring MVC ๊ตฌ์กฐ
![img.png](../img/img_2.png)     
> Spring MVC์ ๋์ ํ๋ฆ๋.

์ฒ๋ฆฌ ์์๋ ๋ค์๊ณผ ๊ฐ๋ค
#### 1. DispatcherSevlet ์์ ์์ฒญ์ ๋ฐ์.
> - ์์ฒญ์ ๋ฐ๊ธฐ์  CORS ๋ฑ์ Filter ๋ฅผ ๋จผ์  ๊ฑฐ์น๋ค.
#### 2. HandlerMapping ์์ ์์ฒญ์ ์ฒ๋ฆฌํ  ํธ๋ค๋ฌ(์ปจํธ๋กค๋ฌ)๋ฅผ ์ฐพ์.
#### 3. ์์ฒญ์ ์ฒ๋ฆฌํ  ์ ์๋ HandlerAdapter๋ฅผ ์ฐพ์ ์์ฒญ์ ์ฒ๋ฆฌ.
#### 4. ํธ๋ค๋ฌ์ ๋ฆฌํด๊ฐ์ ๋ณด๊ณ  ์ฒ๋ฆฌ ํ๋จ
> - @ResponseBody ๋ผ๋ฉด Converter๋ฅผ ์ฌ์ฉํ์ฌ ๋ฐ์ดํฐ ๋ฐํ(REST API) , ์๋๋ผ๋ฉด 5๋ฒ ๊ณผ์ .
#### 5. viewName์ ๋ฐ์ ViewResolver ์๊ฒ์ ํด๋นํ๋ ๋ทฐ๋ฅผ ์ฐพ์ ๋ชจ๋ธ ๋ฐ์ดํฐ ๋๋๋ง.
#### 6. ์์ธ๊ฐ ๋ฐ์ํ๋ค๋ฉด ExeceptionHadler์๊ฒ ์์ธ ์ฒ๋ฆฌ ์์.
#### 7. ์๋ต ๋ฐํ.

***

### โ๏ธ DispatcherServlet
ํด๋ผ์ด์ธํธ์ ์์ฒญ์ ๊ฐ์ฅ ์์์ ์ฒ๋ฆฌํ๋ ์ค์ ์๋ธ๋ฆฟ์ด๋ค. ์ฌ๋ฌ๊ฐ์ง ์ปดํฌํดํธ๋ค์ ๊ฐ์ง๊ณ  ์์ผ๋ฉฐ, ์ด๋ค์๊ฒ ์ฒ๋ฆฌ๋ฅผ ์์ํ๋ค.
ํด๋ผ์ด์ธํธ์ ์์ฒญ์ ๋ฐ์ HandlerMapping์๊ฒ Handler๋ฅผ ์ฐพ์์ค ๊ฒ์ ์์ฒญํ๋ค.

DispatcherServlet -> FrameworkSerblet -> HttpServletBen -> HttpServlet ์ ๊ตฌ์กฐ๋ก ์์๋์ด ์๋ค.    
๊ฒฐ๊ตญ ์ค์ํ ๊ฒ์ DispatcherServlet์ HttpServlet์ ์์๋ฐ์๋ค๋ ๊ฒ์ด๋ค. ๋๋ฌธ์ HttpSevlet์ ๊ธฐ๋ฅ์ ๋ชจ๋ ์ฌ์ฉํ  ์ ์๋ค.
๊ธฐ๋ณธ์ ์ผ๋ก ๋ชจ๋  ๊ฒฝ๋ก์ ๋ํด ๋งคํ๋์ด ์๋ค. ๋ค๋ฅธ ์๋ธ๋ฆฟ๋ ํจ๊ป ๋์ํ๋๋ฐ ์ด๋ ์์ธํ ๊ฒฝ๋ก๊ฐ ๋ ์ฐ์ ์์๊ฐ ๋๊ธฐ ๋๋ฌธ์ด๋ค.

์๋ธ๋ฆฟ์ด ํธ์ถ๋๋ฉด HttpServlet์ด ์ ๊ณตํ๋ service()๊ฐ ํธ์ถ๋๋ค. ์ฌ๋ฌ ๋ก์ง๋ค์ด ์คํ๋๋๋ฐ ๊ฐ์ฅ ์ค์ํ ๊ฒ์
doDispatch() ๋ฉ์๋์ด๋ค.
๋ค์์ doDispach() ๋ฉ์๋์ ์ผ๋ถ์ด๋ค.
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
ํฐ ํ๋ฆ๋ง์ ๋ณด๊ธฐ ์ํด ์์ธ ์ฒ๋ฆฌ์ ๊ฐ์ ๋ค๋ฅธ ๋ถ์์ ์ธ ์ฝ๋๋ ์ ๊ฑฐํ์๋ค.   
์ฒ์์ getHandler() ๋ฉ์๋๋ฅผ ์คํํ์ฌ ์์ฒญ์ ๋ง๋ ํธ๋ค๋ฌ๋ฅผ ์ฐพ์์จ๋ค. ๊ทธ ๋ค์์ ํด๋น ํธ๋ค๋ฌ๋ฅผ ์ฒ๋ฆฌํ  ์ ์๋ ์ด๋ํฐ๋ฅผ getHandlerAdapter()๋ฉ์๋๋ฅผ ์ด์ฉํ์ฌ ๊ฐ์ ธ์จ๋ค.
๊ฐ์ ธ์จ ํธ๋ค๋ฌ์ handle() ๋ฉ์๋๋ฅผ ์คํํ์ฌ ํธ๋ค๋ฌ์์ ์์ฒญ์ ์ฒ๋ฆฌํ๋ค. ๊ฒฐ๊ณผ ๊ฐ์ผ๋ก๋ ModelAndView ๊ฐ์ฒด๋ฅผ ๋ฐํ ๋ฐ๋๋ค.
์คํ์ ์ HandlerInterceptor ์ธ PreHandler()๋ฅผ ์คํํ๋ ๊ฒ๋ ํ์ธํ  ์ ์๋ค.

๋ฐํ๋ฐ์ ModelAndView ๊ฐ์ฒด์์ ViewName์ ๊ฐ์ ธ์ค๊ณ  processDispatchResult() ๋ฉ์๋๋ฅผ ์คํํ๋ค.
์คํ์ ์ PostHandler()๋ฅผ ์คํ ํ๋ค. processDispatchResult()๋ฅผ ํ์ธํด๋ณด๋ฉด View๋ฅผ ๋๋๋ง ํ๋ ๋ถ๋ถ์ ํ์ธํ  ์ ์๋ค.    
```render(mv, request, response);```


### โ๏ธ HandlerMapping
HandlerMapping ์์๋ ์์ฒญ์ ์ฒ๋ฆฌํ  ์ ์๋ ์ปจํธ๋กค๋ฌ๋ฅผ ์ฐพ์ ์ค๊ณ  ๋ฏธ๋ฆฌ ๋ฑ๋ก๋ HandlerAdapter ๋ชฉ๋ก์ ์ํํ๋ฉฐ, ์ด๋ฅผ ์ฒ๋ฆฌํ  HadlerAdapter ๋ฅผ ์ฐพ๋๋ค.

DispatcherServlet์ ๋ค์ํ HandlerMapping์ ๋ฆฌ์คํธ๋ก ๊ฐ์ง๊ณ  ์๋ค.

```java
// matchingBeans.values()๋ HandlerMapping Interface ํ์. HandlerMapping ๋ค์ ์ด๋ฅผ ๊ตฌํํ๋ค.
this.handlerMappings = new ArrayList<>(matchingBeans.values());
```
1. **RequestMappingHandlerMapping**
    > - ์คํ๋ง์ ๊ธฐ๋ณธ ํธ๋ค๋ฌ ๋งตํ, @RequestMapping, @Controller ์ ๋ธํ์ด์์ด ๋ถ์ ์ปจํธ๋กค๋ฌ๋ฅผ ์ฒ๋ฆฌํ๋ค.
    ```java
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
                AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
    }
    ```
    > - RequestMappingHandlerMapping ์ ```@Controller``` ๋ ```@Component(๋น ๋ฑ๋ก์ฉ) + @RequestMapping```
    ๊ฐ ํด๋์ค ๋ ๋ฒจ์์ ์ฌ์ฉ๋ ๋ ์ ํ๋๋ค.
2. SimpleUrlHandlerMapping
    > - URL๊ณผ Controller๋ฅผ ์ง์  ๋งตํ
3. BeanNameUrlHandlerMapping
    > - URL๊ณผ Bean ์ด๋ฆ์ ๊ฐ์ง๊ณ  ์ปจํธ๋กค๋ฌ์ ๋งตํ
4. ControllerBeanNameHandlerMapping
    > - Bean์ ์์ด๋๋ ์ด๋ฆ์ ์ด์ฉํด ๋งตํ    
    > - ex) @Component("/test") -> /test ์ ๋งตํ.
5. ControllerClassNameHandlerMapping
    > - URL๊ณผ Controller ๋ช์ ์ผ์ ํ ๊ท์น์ผ๋ก ๋งตํ
    > - ex) main/* -> MainController์์ ์ฒ๋ฆฌ.
6. DefaultAnnotaitonHandlerMapping
    > - @RequestMapping ์ด๋ธํ์ด์์ ์ด์ฉํ์ฌ ์์ฒญ์ ์ฒ๋ฆฌํ  ์ปจํธ๋กค๋ฌ๋ฅผ ๊ตฌํํ๋ค.
    > - ex) RequestMapping("/test")
    > RequestMappingHandlerMapping์ด ๋์ค๋ฉด์ Deprecated ๋์ด๊ฐ๋ค. 


HandlerMapping์ Handler๋ค์ Map์ ๊ฐ์ง๋ค.
```java
// RequestMappingHandlerMapping, prefixe Map์ ๊ฐ์ง๋ค.
private Map<String, Predicate<Class<?>>> pathPrefixes = Collections.emptyMap();

// SimpleUrlHandlerMapping, <URL, Handler>์ Map์ ๊ฐ์ง๋ค.
private final Map<String, Object> urlMap = new LinkedHashMap<>();
```
### โ๏ธ HandlerAdapter
ํธ๋ค๋ฌ ์ด๋ํฐ๋ ์ปจํธ๋กค๋ฌ์ ๋ฉ์๋๋ฅผ ์คํํ์ฌ ์ค์  ์์ฒญ์ ์ฒ๋ฆฌํ๋ค. ์ด๋ฅผ ์ํด ๋ค์ํ ํธ๋ค๋ฌ ์ด๋ํฐ๊ฐ ์๋ธ๋ฆฟ์ ๋ฏธ๋ฆฌ ๋ฑ๋ก๋์ด ์๋ค.
```java
//matchingBeans.values()๋ HandlerAdapters Interface ํ์. Adapter ๋ค์ ํด๋น ์ธํฐํ์ด์ค๋ฅผ ๊ตฌํํ๋ค.
this.handlerAdapters = new ArrayList<>(matchingBeans.values());
```

- **RequestMappingHandlerAdapter**
  > @RequestMapping ์ ๋ธํ์ด์ ์ฒ๋ฆฌ.
- HttpRequestHandlerAdapter
  > HttpRequestHandler Interface ์ฒ๋ฆฌ.
- SimpleControllerHandlerAdapter
  > Controller Interface ์ฒ๋ฆฌ.
- HandlerFunctionAdapter
    > Web Flux ์์ฒญ ์ฒ๋ฆฌ.

์ด์ค ์ฃผ๋ก ์คํ๋ง์์ ์ฌ์ฉํ๋ @RequestMapping ์ ๋ธํ์ด์์์ ๋์ํ๋ ์ด๋ํฐ๋ ์ด๋ฆ์์๋ ์ ์ ์๋ฏ 3๋ฒ์งธ RequestMappingHandlerAdapter ์ด๋ค.    
@GetMapping, @PostMapping ๋ฑ์ ์ด๋ธํ์ด์์๋ @RequestMapping ์ด ํฌํจ๋์ด ์๋ค.

ex) ```@GetMapping``` == ```@RequestMapping(method = RequestMethod.GET)```, ํธ๋ฆฌํ๊ฒ ์ฌ์ฉํ๊ธฐ ์ํ ์ ๋ธํ์ด์์ ์ํ ์ ๋ธํ์ด์.

ํธ๋ค๋ฌ ์ด๋ํฐ๋ ๋จผ์  ์์ฒญ์ ๋ฐ์ HTTP Method๊ฐ์ ํ์ธํ์ฌ ์ฒ๋ฆฌ ๊ฐ๋ฅํ์ง ํ์ธํ๊ณ , ์๋ค๋ฉด ์์ธ๋ฅผ ๋ฐ์์ํจ๋ค.
๋ํ ์ธ์์ ์ด์ฉํ๋์ง ํ์ธํ์ฌ ์ธ์์ด ์กด์ฌํ๋ค๋ฉด mutex ๋ฅผ ์ด์ฉํ์ฌ Thread-Safeํ๊ฒ ์ฒ๋ฆฌํ  ์ ์๋๋ก ํ๋ค.    
๋ง์ง๋ง์ผ๋ก ๊ฐ์ฅ ํต์ฌ์ธ invokeHandlerMethod ์์ ์์ฒญ์ ์ฒ๋ฆฌํ๊ณ  ๊ฒฐ๊ณผ๋ฅผ ๋ฐํํ๋ค.(ModelAndView) 

### ๐ก ArgumentResolver์ ReturnValueHandler

์คํ๋ง์์๋ ๋ค์ํ Method Argument๋ฅผ ์ง์ํ๋ค. ์ง์ํ๋ Method Argument ๋ฅผ ๋ค์์ ๋์ ธ์ฃผ๋ ๊ณณ์ด ์กด์ฌํด์ผ ํ๋ค.
์ด๋ฐ ์ญํ ์ ํ๋ ๊ฒ์ด ```ArgumentResolver``` ์ด๋ค.
```RequestMappingHandlerAdapter```์์๋ ```ArgumentResolver```๋ฅผ ํธ์ถํ์ฌ ์ปจํธ๋กค๋ฌ๊ฐ ํ์๋กํ๋ ํ๋ผ๋ฏธํฐ๋ฅผ ์์ฑํ๊ณ , ๋ชจ๋ ์ค๋น๊ฐ ๋์์๋
์ปจํธ๋กค๋ฌ๋ฅผ ํธ์ถํ์ฌ ์์ฑ๋ ๊ฐ์ ๋๊ฒจ์ค๋ค. ์คํ๋ง์์๋ 30๊ฐ ์ด์์ ArgumentResolver๋ฅผ ๊ธฐ๋ณธ์ผ๋ก ์ ๊ณตํ๊ณ , ๋ฆฌ์คํธ์ ๋ด๊ฒจ์๋ค.
ํด๋น ๋ฆฌ์คํธ๋ฅผ ์ํํ๋ฉฐ ์ ์ ํ ๋ฆฌ์กธ๋ฒ๋ฅผ ์ ํํ๋ค.

```java
public interface HandlerMethodArgumentResolver {
    
	boolean supportsParameter(MethodParameter parameter);
	
	@Nullable
	Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;
}
```
supportsParameter() ๋ฉ์๋๋ก ํด๋น ํ๋ผ๋ฏธํฐ๋ฅผ ์ง์ํ๋์ง ๊ฒ์ฌํ๊ณ , resolveArgument() ๋ฉ์๋์์ ํ๋ผ๋ฏธํฐ๋ฅผ ์์ฑํ์ฌ ๋๊ฒจ์ค๋ค.


๋ํ, ๊ฐ์ ๋ฐํ๋ ๋ค์ํ ํ์์ผ๋ก ํ  ์ ์๋๊ฒ์ ํ์ธ ํ  ์ ์๋๋ฐ, ์ด๋ฅผ ์ํด์ ```ReturnValueHandler```๊ฐ ์กด์ฌํ๋ค.
์๋ต๊ฐ์ ๋ฐ์, ๋ณํํ๋ ๊ณผ์ ์ ๋ด๋นํ๋ค.
```java
public interface HandlerMethodReturnValueHandler {
    
	boolean supportsReturnType(MethodParameter returnType);
	
	void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception;
}
```
๋น์ทํ๊ฒ supportsReturnType() ๋ฉ์๋๋ก ๋ฐํ๊ฐ์ ์ฒ๋ฆฌ ๊ฐ๋ฅํ์ง ํ์ธํ๊ณ ,
handleReturnValue ์์ ๋ฐํ ๊ฐ์ ์์ฑํ๋ค. ์๋ฅผ๋ค์ด ModelAndView ๊ฐ์ ๊ฒฝ์ฐ ํด๋น ๋ฉ์๋์ ๊ตฌํ์์ ModelAndViewResolver ์๊ฒ
๊ฐ์ฒด ์์ฑ์ ์์ํ๋ ๊ฒ์ ํ์ธํ  ์ ์์๋ค.
```java
ModelAndView mav = mavResolver.resolveModelAndView(method, handlerType, returnValue, model, webRequest);
```

<br>

ArgumentResolver์ ReturnValueHandler๋ **HttpMessageConverter**๋ฅผ ์ฌ์ฉํ์ฌ ํ์ํ ๊ฐ์ฒด๋ฅผ ์์ฑํ๋ค.(@RequestBody, @ResponseBody, HttpEntity)
```java
// HttpEntityMethodProcessor.resolveArgument()
Object body = readWithMessageConverters(webRequest, parameter, paramType);

// HttpEntityMethodProcessor.handleReturnValue()
writeWithMessageConverters(responseEntity.getBody(), returnType, inputMessage, outputMessage);
```
ํด๋น ๋ฉ์๋๋ค์์๋ ์ปจ๋ฒํฐ ๋ฆฌ์คํธ๋ฅผ ์ํํ๋ฉฐ ์ ์ ํ ์ปจ๋ฒํฐ๋ฅผ ์ ํํ๋ค.

### โ๏ธ HandlerInterceptor
์ปจํธ๋กค๋ฌ์ ๋ฉ์๋๋ฅผ ์คํํ๊ธฐ ์ ์ ํธ๋ค๋ฌ ์ธํฐ์ํฐ์ ์ง์ ๋ ์ผ์ ์คํํ๋ค. HandlerInterceptor ์ธํฐํ์ด์ค๋ฅผ ๊ตฌํ ํ์ฌ ์ฌ์ฉํ  ์ ์๋ค.
- preHandle(HttpServletRequest request, HttpServletResponse response, Object handler): ์์ฒญ ์คํ ์ .
- postHandle(request, response, handler, ModelAndView modelAndView): ์์ฒญ์ด ์คํ๋ ํ.
- afterCompletion(request, response, handler, Exception ex): view๋ฅผ ๋ ๋๋งํ ํ, ์์ฒญ์ ์๋ฃ, ์๋ต ์ 

### โ๏ธ HandlerExceptionResolver
์์ธ๊ฐ ๋ฐ์ํ์ ๋ DispatcherServlet ์์๋ ์์ธ๋ฅผ HandlerExceptionResolver ์๊ฒ ์์ํ๋ค.
๋๋ฌธ์ @ExceptionHandler ์์ ์์ธ๋ฅผ ๊ณตํต์ ์ผ๋ก ์ฒ๋ฆฌํ  ์ ์๋ค. ์๋์ 4๊ฐ์ ๊ตฌํ์ฒด๋ฅผ ๊ฐ์ง๋ค.

#### 1. SimpleMappingExceptionResolver 
> ์์ธ ํด๋์ค ์ด๋ฆ๊ณผ ErrorViewName ์ ๋งคํ. ๋ธ๋ผ์ฐ์  ์์ฉ ํ๋ก๊ทธ๋จ์์ ์ค๋ฅ ํ์ด์ง๋ฅผ ๋ ๋๋งํ๋๋ฐ ์ ์ฉํ๋ค.
#### 2. DefaultHandlerExceptionResolver
> Spring MVC์์ ๋ฐ์ํ ์์ธ๋ฅผ HTTP ์ํ ์ฝ๋์ ๋งคํํ๋ค.
#### 3. ResponseStatusExceptionResolver
> @ResponseStatus ์ฃผ์์ ๊ฐ์ ๊ธฐ๋ฐ์ผ๋ก HTTP ์ํ ์ฝ๋์ ๋งคํํ๋ค. ```@ResponseStatus(HttpStatus.BAD_REQUEST)```
#### 4. ExceptionHandlerExceptionResolver
> @ExceptionHandler ์์ ๋ฉ์๋๋ฅผ ํธ์ถํ์ฌ ์์ธ๋ฅผ ํด๊ฒฐ ํ๋ค.

### โ๏ธ @ControllerAdvice
@ExceptionHandler ๋ฅผ ์ฌ์ฉํ์ฌ ์์ธ๋ฅผ ์ฒ๋ฆฌํ๊ณ ์ ํ ๋ @Controller ๋ณด๋ค ๋ ์ ์ญ์ ์ผ๋ก ์ฌ์ฉํ๊ณ  ์ถ์ ๋ ์ฌ์ฉํ๋ค.
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

### โ๏ธ ViewResolver
์ค์  ๋ทฐ๋ฅผ ๋๋๋ง ํ๊ณ  ๋ฐํํ๋ค.

- BeanNameViewResolver
  > ๋น ์ด๋ฆ์ผ๋ก ๋ทฐ๋ฅผ ์ฐพ์ ๋ฐํํ๋ค.
- UrlBasedViewResolver
  > redirect ๋ฑ ViewName์ด ์๋ URL๋ก ๋ทฐ๋ฅผ ์ค์ ํ๋ค.
- ThymeleafViewResolver
  > ํ์๋ฆฌํ ์ด์ฉ์ ์ฌ์ฉ๋๋ ๋ฆฌ์กธ๋ฒ.
- InternalResourceViewResolver
  > - JSP๋ฅผ ๋ทฐ ๊ธฐ์ ๋ก ์ด์ฉํ  ๊ฒฝ์ฐ ๋ฑ๋กํ๋ค. ๊ฐ์ฅ ๋ง์ง๋ง์ ์ค๋ ๋ฆฌ์กธ๋ฒ.
  > ```properties
  > # ์๋์ ๊ฐ์ด ์ค์  ์ ๋ณด๋ฅผ ์ฌ์ฉํด์ ๋ฑ๋กํ๋ค.
  > spring.bvc.view.prefix=/WEB-INF/view/
  > spring.mvc.view.suffix=.jsp
  > ```
  > - View Interface๋ฅผ ๊ตฌํํ InternalResourceView ๋ฅผ ๋ฐํํ๋ค. -> forward() ํธ์ถ.


# ๐ Spring MVC ๊ธฐ๋ณธ ๊ธฐ๋ฅ

## ๐ง ์์ฒญ ๋งคํ

### โ๏ธ ๊ธฐ๋ณธ ๋งคํ
```java
@RequestMapping(value = {"/hello", "/hello2"})
```
๊ธฐ๋ณธ ์ ์ธ RequestMapping ```{}``` ๋ฅผ ์ด์ฉํ์ฌ ์ฌ๋ฌ๊ฐ์ url์ ๋งคํํ  ์๋ ์๋ค.

### โ๏ธ ๋ฉ์๋ ์ง์ 
```java
@RequestMapping(value = "/mapping-get", method = RequestMethod.GET)

@GetMapping("/mapping-get")
```
@RequestMapping ์ method ์์ฑ๊ฐ์ ์ด์ฉํ์ฌ ๋ฉ์๋๋ฅผ ์ง์ ํ  ์ ์๋ค.
Spring ์์๋ ์ด๋ฅผ ํฉ์ณ๋ @GetMapping๊ณผ ๊ฐ์ ์ ๋ธํ์ด์์ ์ง์ํด์ค๋ค. ์ค์ ๋ก @GetMapping ์ ๋ธํ์ด์์ ์ ์ธ๋ถ๋ถ์ ๋ณด๋ฉด
๋ค์๊ณผ ๊ฐ์ ๋ถ๋ถ์ ํ์ธ ํ  ์ ์๋ค.```@RequestMapping(method = RequestMethod.GET)```
Post, Delete, Put, Patch ๋ํ ์กด์ฌํ๋ค.

### โ๏ธ PathVariable
```java
@GetMapping("/mapping/{userId}")
public String mappingPath(@PathVariable String userId){
    ...
}
```
๊ฒฝ๋ก์ ```{}``` ์ ๊ฐ์ด ์ฐ๊ณ , ํ๋ผ๋ฏธํฐ ๋ช์ ์ผ์น์ํค๋ฉด ํด๋น ์๋ฆฌ์ ๋ค์ด๊ฐ ๊ฐ์ ํ๋ผ๋ฏธํฐ์ ๋ฃ์ด์ค๋ค.
```@PathVariable("userId") String id``` ์ ๊ฐ์ด ์ฌ์ฉํ์ฌ ํ๋ผ๋ฏธํฐ๋ช์ ๋ค๋ฅด๊ฒ ์ค์ ํ  ์๋ ์๋ค.

### โ๏ธ Params ์กฐ๊ฑด
```java
@GetMapping(value = "/mapping-param", params = "mode=debug")
public String mappingParam(@RequestParam("mode") String mode){
    ...
}
```
```params``` ์์ฑ์ ์ด์ฉํ์ฌ ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ์ ์กฐ๊ฑด์ ์ง์ ํ๋ค. '=', '!=', '!' ์ ๊ฐ์ด ์ง์ ํ  ์ ์์ผ๋ฉฐ, mode์ ๊ฐ์ด ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ์
์ด๋ฆ๋ง์ ์ ์ผ๋ฉด ํด๋น ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ด ์๋์ง๋ฅผ ๋ณธ๋ค. ์กฐ๊ฑด์ ๋ง์ง ์์ผ๋ฉด ์์ฒญ์ ๋ฐ์ง ์๋๋ค.(400 Bad Request ๋ฐ์.)


### โ๏ธ Headers ์กฐ๊ฑด
```java
@GetMapping(value = "/mapping-header", headers = "mode=debug")
public String mappingHeader(@RequestHeader("mode") String mode){
    ...
}
```
Params์ ์ ์ฌํ๋ค. Headers ์์๋ ํค๋์ ์กฐ๊ฑด์ ๊ฒ์ฌํ๋ค.

### โ๏ธ Content-Type, Accept ํค๋ ์กฐ๊ฑด
```java
@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
public String mappingConsumes(){
    ...
}
```
```consumes``` ๋ Content-Type ์, ```produces``` ๋ Accept ํค๋์ ์กฐ๊ฑด์ ๊ฑด๋ค.   
consumes ์ ์ผ์นํ์ง ์์ผ๋ฉด 415 Unsupported Media Type ์๋ฌ๊ฐ, produces์ ์ผ์นํ์ง ์์ผ๋ฉด 406 Not Acceptable ์๋ฌ๊ฐ ๋ฐ์ํ๋ค.



## ๐ง Method Arguments
```java
@GetMapping("/headers")
public String headers(HttpServletRequest request,
                      HttpServletResponse response,
                      HttpMethod httpMethod,
                      Locale locale,
                      @RequestHeader MultiValueMap<String, String> headers, /* MultiValueMap: ํ๋์ ํค์ ์ฌ๋ฌ ๊ฐ์ ๋ฐ์ ์ ์๋ค(๋ฐฐ์ด)*/
                      @RequestHeader("host") String host,
                      @CookieValue(value = "cookieName", required = false) String cookie){

    ...
}
```
HttpMethod, Locale, cookie, Content-Type ๋ฑ์ ํค๋ ์ ๋ณด๋ฅผ ๊ฐ์ ธ์ฌ ์ ์๋ค. MultiValueMap์ ์ด์ฉํ์ฌ ํ๋ฒ์ ๊ฐ์ ธ์ฌ ์๋ ์์ผ๋ฉฐ,
๊ฐ๊ฐ์ ๋ฐ๊ฑฐ๋ ```@RequestHeader("headerName")``` ๋ก ํน์  ํค๋๋ฅผ ๋ฐ์์ฌ ์ ์๋ค.

์คํ๋ง์์๋ Request, Response, HttpSession, Principal ๋ฑ์ ๋ฉ์๋ ์ธ์๋ก ์ฌ์ฉํ  ์ ์๋๋ก ์ง์ํด์ค๋ค.
InputStream, OutputStream ์ ์ด์ฉํ์ฌ ์์ฒญ ๋ณธ๋ฌธ์ ์ ์ฒด๋ฅผ ์ฝ์ ์๋ ์๊ณ , ์๋ต์ ์์ฑํ  ์๋ ์๋ค.
์ด ์ธ์๋ ๋ค์ํ ์ ๋ธํ์ด์๊ณผ ํ๋ผ๋ฏธํฐ, ์๋ต ํ์์ ์ง์ํ๋ค. ์์ธํ ๋ด์ฉ์ ์๋ ์คํ๋ง ๊ณต์ ๋ฌธ์์์ ํ์ธ ํ  ์ ์๋ค.

> - https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments

์ด์ค ์์ฒญ๊ณผ ์๋ต์ ๊ด๋ จํ Argument ๋ค์ ์์๋ณด์.

    - ์์ฒญ
        - ์์ฒญ ํ๋ผ๋ฏธํฐ ์ฒ๋ฆฌ
        - ์์ฒญ ๋ฉ์์ง(Body) ์ฒ๋ฆฌ - String
        - ์์ฒญ ๋ฉ์์ง ์ฒ๋ฆฌ(Body) - JSON
    - ์๋ต
        - ์ ์  ๋ฆฌ์์ค
        - ๋์  ๋ฆฌ์์ค(ํํ๋ฆฟ ๋ฑ)
        - HTTP ๋ฉ์์ง(API)

### โ๏ธ ์์ฒญ ํ๋ผ๋ฏธํฐ ์ฒ๋ฆฌ
GET ๋ฉ์๋ ๋ฐฉ์์์์ ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ, POST ๋ฐฉ์์ HTML Form ์ ์ก์ ๋์ผํ ํ์์ ๊ฐ์ง๊ณ , ์ด๋ฅผ ์์ฒญ ํ๋ผ๋ฏธํฐ๋ผ๊ณ  ํ๋ค.
Spring MVC์์ ์์ฒญ ํ๋ผ๋ฏธํฐ๋ฅผ ๋ฐ๋ ๋ฐฉ๋ฒ์ ์์๋ณด์.

- ### HttpServletRequest
    ```java
    @RequestMapping("/request-param")
    public void requestParam(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username=request.getParameter("username");
        int age=Integer.parseInt(request.getParameter("age"));
    
        ...
    }
    ```
    Servlet ์์์ ๋์ผํ๊ฒ HttpServletRequest ๊ฐ์ฒด์์ ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ผ๋ก ์กฐํํ  ์ ์๋ค.

- ### @RequestParam
    ```java
    @RequestMapping("/request-param")
    public String requestParam(@RequestParam("username") String name,
                               @RequestParam("age") int age){
        ...
    }
    ```
    ```@RequestParam``` ์ ๋ธํ์ด์์ ์ด์ฉํ๋ค. ์์ฒญ ํ๋ผ๋ฏธํฐ์ ์ด๋ฆ๊ณผ ๋ฉ์๋ ํ๋ผ๋ฏธํฐ์ ์ด๋ฆ์ ๋์ผํ๊ฒ ํ๋ค๋ฉด ์์ฒญ ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ ์๋ต์ด ๊ฐ๋ฅํ๊ณ (```@RequestParam String username```),
    @RequestParam ์ ๋ธํ์ด์ ๋ํ ์๋ต์ด ๊ฐ๋ฅํ๋ค.(```String username```)
    
    ```java
    @RequestMapping("/request-param")
    public String requestParam(@RequestParam Map<String, Object> params){
        ...
    }
    ```
    Map์ผ๋ก ๋ฐ์ ์๋ ์๊ณ , MultiValueMap ์ผ๋ก ํ ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ ์ฌ๋ฌ๊ฐ์ ๊ฐ์ ๋ฐ์ ์๋ ์๋ค.   
    ์ฐธ๊ณ ๋ก ```@RequestParam String username``` ์์ ```username=kim1&username=kim2``` ๊ฐ์ ์ฌ๋ฌ๊ฐ์ ๊ฐ์ ๋ฐ์ ์๋ ์๋ค.
    ์ด๋๋ ```kim1,kim2``` ์ ๊ฐ์ String ๊ฐ์ด ๋๋ค.

- ### @RequestParam ์์ฑ(required, defaultValue)
    ```java
    @RequestMapping("/request-param")
    public String requestParamAttribute(@RequestParam(required = true, defaultValue = "GUEST") String username,
                                       @RequestParam(required = false) Integer age){
        ...
    }
    ```
    **required** ์์ฑ์ ๋ฐ๋์ ๋ค์ด์์ผ ํ๋์ง์ ๋ํ ์ง์ ์ด๋ค. ๊ธฐ๋ณธ์ **true**๋ก ์ค์ ๋์ด ์์ผ๋ฉฐ, ๊ฐ์ด ๋ค์ด์ค์ง ์์ผ๋ฉด **400 BadRequest** ๊ฐ ๋ฐ์ํ๋ค.
    ๋น ๋ฌธ์์ด("") ๋ํ ๊ฐ์ด ๋ค์ด์ค์ง ์์ ๊ฒ์ผ๋ก ๊ฐ์ฃผํ๋ ์ฃผ์๊ฐ ํ์ํ๋ค.    
    **false**๋ก ์ค์ ๋์ด ์์ผ๋ฉด ๊ฐ์ด ๋ค์ด์ค์ง ์์๋ ์๋ฌ๊ฐ ๋ฐ์ํ์ง ์๋๋ค. ์ด๋๋ ํ๋ผ๋ฏธํฐ ๊ฐ์ **null**๋ก ์ฑ์ด๋ค.
    ๋๋ฌธ์ ์์ํ ์๋ฃํ์ ์ฌ์ฉํ๋ค๋ฉด null์ ๋ฃ์ ์ ์์ด ์๋ฒ ์๋ฌ๊ฐ ๋ฐ์ํ๋ค. Wrapper ํด๋์ค ํ์์ผ๋ก ์ ์ธํ๋๋ก ํ์.
    
    **defaultValue**๋ ๊ฐ์ด ๋ค์ด์ค์ง ์์์ ๋ ํ๋ผ๋ฏธํฐ์ ๋ค์ด๊ฐ๊ฒ ๋๋ ๊ฐ์ ์ค์ ํ๋ค. ์ด ๋๋ ๋น ๋ฌธ์์ด ๋ํ ๊ธฐ๋ณธ๊ฐ์ผ๋ก ์ธํํด์ค๋ค.


- ### @ModelAttribute
    ```java
    @RequestMapping("/model-attribute")
    public String requestParamMap(@ModelAttribute("data") HelloData helloData, Model model){
        HelloData helloData1 = (HelloData)model.getAttribute("data");
        
        ...
    }
    ```
    @ModelAttribute ๋ฅผ ์ด์ฉํ์ฌ ๋ฐ๋ก ์์ฒญ ํ๋ผ๋ฏธํฐ ๊ฐ์ ๋ด์ ๊ฐ์ฒด๋ฅผ ์์ฑํ  ์ ์๋ค.
    ์คํ๋ง MVC ์์ ๋์ ๊ฐ์ฒด๋ฅผ ์์ฑํ๊ณ , ์์ฒญ ํ๋ผ๋ฏธํฐ์ ์ด๋ฆ์ผ๋ก ๊ฐ์ฒด์์ ํ๋กํผํฐ๋ฅผ ์ฐพ๋๋ค. ๊ทธ๋ฆฌ๊ณ  ํด๋น ํ๋กํผํฐ์ Setter๋ฅผ ํธ์ถํ์ฌ
    ๊ฐ์ ๋ฐ์ธ๋ฉ ํ๋ค.(ํ๋๋ช๊ณผ ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ด ๋์ผํด์ผ ํ๋ค.)   
    
    ์ ๋ธํ์ด์์ ์๋ต์ด ๊ฐ๋ฅํ๋ค. ์ ๋ธํ์ด์์ ์๋ตํ๊ฒ ๋๋ฉด int, String, Integer ๊ณผ ๊ฐ์ ๋จ์ ํ์์ @RequestParam ์ผ๋ก ์ ์ฉ๋๊ณ ,
    ๋๋จธ์ง๋ @ModelAttribute๊ฐ ์ ์ฉ๋๋ค.(argument resolver๋ก ์์ธ ์ง์  ๊ฐ๋ฅ.)
    
    ํ๊ฐ์ง ๊ธฐ๋ฅ์ด ๋ ์๋๋ฐ @ModelAttribute ์ ํ๊ฒ ๊ฐ์ฒด๋ ์๋์ผ๋ก Model ๊ฐ์ฒด์ ๋ค์ด๊ฐ๋ค. ์์ฑ๊ฐ์ ์ฃผ์ง ์์ผ๋ฉด ํด๋์ค์ ์นด๋ฉ์ผ์ด์ค๋ก ์ดํธ๋ฆฌ๋ทฐํธ ์ด๋ฆ์ด ์ง์ ๋๊ณ ,
    ์์ฑ์ผ๋ก ์ด๋ฆ์ ์ง์ ํด์ค ์ ์๋ค.(์์ ์์์์ data์ ๊ฐ์ด)



### โ๏ธ ์์ฒญ ๋ฉ์์ง ์ฒ๋ฆฌ - String
Http Message Body์ ๋ฐ์ดํฐ๋ฅผ ์ง์  ๋ด์์ ์์ฒญํ๋ค. ์ฃผ๋ก HTTP API ์์ ์ฌ์ฉ๋๋ค.
    
- ### HttpServletRequest
    ```java
    @PostMapping("/request-body-string")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        
        // Stream ์ ๋ฐ์ดํธ์ด๊ธฐ ๋๋ฌธ์ ํญ์ ์ธ์ฝ๋ฉ์ ์ง์ ํด ์ฃผ๋ ๊ฒ์ด ์ข๋ค.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); 
    
        response.getWriter().write("OK");
    }
    ```
    Servlet ๋์ ๋์ผํ๊ฒ HttpServletRequest ๊ฐ์ฒด์์ InputStream ์ ์ป์ด์ MessageBody ๋ฅผ ์ฝ์ด์จ๋ค.

- ### InputStream
    ```java
    @PostMapping("/request-body-string")
    public void requestBodyStream(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    
        writer.write("OK");
    }
    ```
    ์คํ๋ง์ ์ง์์ ๋ฐ์ InputStream์ ๋ฐ๋ก ํ๋ผ๋ฏธํฐ๋ก ๋ฐ์ ์ด๋ฅผ ์ฌ์ฉํ๋ค.

- ### HttpEntity
    ```java
    @PostMapping("/request-body-string")
    public HttpEntity<String> requestBodyEntityV1(HttpEntity<String> httpEntity) throws IOException {
    
        String messageBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();
    
        return new HttpEntity<>("OK");
    }
    ```
    HttpEntity๋ฅผ ์ฌ์ฉํ์ฌ ๋ฉ์์ง ๋ณธ๋ฌธ์ด๋ ํค๋๋ฅผ ๊ฐ์ ธ์ฌ ์ ์๋ค. ์๋ต์ ๋ณธ๋ฌธ ๋ํ ์ค์  ๊ฐ๋ฅํ๋ค.
    HttpMessageConverter์ ์ํด ๋์ํ๋ค. Http ๋ฉ์์ง <-> String

- ### RequestEntity
    ```java
    @PostMapping("/request-body-string")
    public HttpEntity<String> requestBodyEntityV2(RequestEntity<String> requestEntity) throws IOException {
    
        String messageBody = requestEntity.getBody();
        HttpHeaders headers = requestEntity.getHeaders();
        String requestMethod = requestEntity.getMethod();
        String requestUrl = requestEntity.getUrl();
        
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    ```
    HttpEntity๋ฅผ ์์๋ฐ๋๋ค. HttpEntity๋ณด๋ค ๋ ๋ง์ ๊ธฐ๋ฅ(ํนํ๋)์ ์ ๊ณตํ๋ค. 

- ### @RequestBody
    ```java
    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyAnnotation(@RequestBody String messageBody, @RequestHeader Map<String, Object> headers) throws IOException {
    
        log.info("message Body = {}", messageBody);
        log.info("headers = {}", headers);
    
        return "OK";
    }
    ```
    ์คํ๋ง์์ ์ง์ํ๋ @RequestBody, @RequestHeader ์ ๋ธํ์ด์์ ์ด์ฉํ์ฌ ๋ณธ๋ฌธ๊ณผ ํค๋์ ๋ด์ฉ์ ๊ฐ์ ธ์จ๋ค.
    

### โ๏ธ ์์ฒญ ๋ฉ์์ง ์ฒ๋ฆฌ - JSON

- ### @RequestBody String
    ```java
    @ResponseBody
    @PostMapping("/request-body-json")
    private String requestBodyJson(@RequestBody String messageBody) throws JsonProcessingException {
    
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    
        return "OK";
    }
    ```
    @RequestBOdy ์ ๋ธํ์ด์์ ์ด์ฉํ์ฌ ๋ฉ์์ง ๋ฐ๋๋ฅผ JsonString ์ผ๋ก ๋ฐ๊ณ , ObjectMapper๋ฅผ ์ด์ฉํ์ฌ ์ด๋ฅผ ๊ฐ์ฒด๋ก ๋ณํํ๋ค.
    
- ### @RequestBody Dto
    ```java
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    private HelloData requestBodyJsonV3(@RequestBody HelloData helloData){
        log.info("helloData = {}", helloData);
    
        return helloData;
    }
    ```
    @RequestBody๋ฅผ ์ด์ฉํ์ฌ ๋ฐ๋ก Dto ๊ฐ์ฒด์ ๋ฐ์ดํฐ๋ฅผ ๋ด ๋๋ค. ์ด๋ MappingJackson2HttpMessageConverter ๊ฐ ๋์ํ์ฌ ์ด๋ฅผ ๋ณํํด์ค๋ค.
    @RequestParam๊ณผ๋ ๋ฌ๋ฆฌ ์ ๋ธํ์ด์์ ์๋ตํ  ์ ์๋ค. ์์์ ์ค๋ช ํ๋ฏ ์ ๋ธํ์ด์์ ์๋ตํ๊ฒ ๋๋ฉด int, String๊ณผ ๊ฐ์ ํ์์ @RequestParam ์ผ๋ก, ๋๋จธ์ง๋ @ModelAttribute ๋ก
    ๋์ํ๊ฒ ๋๋ค.
    
    ๋ฐ๋ผ์ ์ ๋ธํ์ด์์ ์๋ตํ๊ฒ ๋๋ฉด ์์ฒญ ํ๋ผ๋ฏธํฐ๋ฅผ ์ฒ๋ฆฌํ๊ฒ ๋๋ฏ๋ก, ๋ฉ์์ง์ ๋ฐ๋๋ฅผ ์ฒ๋ฆฌํ  ์ ์๊ฒ ๋๋ค.
    ํด๋น ๊ฐ์ฒด์ ํ๋๋ null, 0 ๋ฑ ํ๋์ ๊ธฐ๋ณธ๊ฐ์ด ๋๋ค.
    
    @ResponseBody ์ ๋ธํ์ด์์ String ๋ฟ๋ง ์๋๋ผ ๊ฐ์ฒด๋ ์ปจ๋ฒํฐ๋ฅผ ์ด์ฉํ์ฌ Json ํ์์ผ๋ก ๋ณํํ์ฌ ์๋ต์ ์์ฑํด์ค๋ค.
    
    
- ### HttpEntity<Dto>
    ```java
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    private String requestBodyJsonV4(HttpEntity<HelloData> entity) throws JsonProcessingException {
        log.info("helloData = {}", entity.getBody());
        log.info("headers = {}", entity.getHeaders());
    
        return "OK";
    }
    ```
    ์ ๋ธํ์ด์์ ์ฌ์ฉํ์ง ์๋ ๋ฐฉ๋ฒ์ด ์๋ค. HttpEntity์ ์ ๋ค๋ฆญ ํ์์ผ๋ก Dto๋ฅผ ์ฌ์ฉํ๋ ๋ฐฉ๋ฒ์ด๋ค.
    getBody(), getHeaders()๋ฅผ ์ด์ฉํ์ฌ ๋ฐ์ดํฐ๋ฅผ ๊บผ๋ผ ์ ์๋ค.

### โ๏ธ ์๋ต, ์ ์  ๋ฆฌ์์ค, ๋ทฐ ํํ๋ฆฟ
์์ฒญ์ ๋ํ ์๋ต์ผ๋ก ์ ์ , ๋์  HTML ํ์ผ์ ๋๋ ค์ค ์ ์๋ค.   
์คํ๋ง ๋ถํธ์์๋ ```/static```, ```/public```, ```/resources```, ```/META-INF/resources``` ์ ํด๋์ค ํจ์ค ๋๋ ํ ๋ฆฌ์ ์ ์  ๋ฆฌ์์ค ํจ์ค๋ฅผ ์ ๊ณตํ๋ค.   
> classpath : src/main/resources

์๋ฅผ ๋ค์ด ```/static/hello/hello.html``` ์๋ ๋ค์๊ณผ ๊ฐ์ด ์ ๊ทผํ  ์ ์๋ค.```http://localhost:8080/hello/hello.html```

Thymeleaf ์์๋ ๋ค์๊ณผ ๊ฐ์ ๊ฒฝ๋ก๋ฅผ ์ ๊ณตํ๋ค. ```/resources/template/``` ์ด๋ Thymeleaf ๋ผ์ด๋ธ๋ฌ๋ฆฌ๋ฅผ ์ถ๊ฐํ์ ๋
์๋์ผ๋ก ์ค์ ๋๋ ์๋์ ์ค์ ์ ํตํด ์ง์ ๋๋ค.
```properties
spring.thymeleaf.prefix=classpath:/templates
spring.thymeleaf.sufix=.html
```

- ### ๋ทฐ ํํ๋ฆฟ: ModelAndView ๋ฐํ
  ```java
  @RequestMapping("/response-view")
    public ModelAndView responseView(){
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mv;
    }
  ```
  src/main/resources/template ์์ response/hello.html ์ ์ฐพ๊ฒ ๋๋ค.

- ### ๋ทฐ ํํ๋ฆฟ: String(ViewPath) ๋ฐํ
  ```java
  @RequestMapping("/response-view")
  public String responseView(Model model){
      model.addAttribute("data", "hello!");
      return "response/hello";
  }
  ```
  @ResponseBody ๊ฐ ์๊ธฐ ๋๋ฌธ์ ๋ทฐ ๋ฆฌ์กธ๋ฒ๋ฅผ ์คํํ์ฌ ๋ทฐ๋ฅผ ์ฐพ๊ณ  ๋ ๋๋ง ํ๋ค.
- ### ๋ทฐ ํํ๋ฆฟ: Void ๋ฐํ
  ```java
  @RequestMapping("/response/hello")
  public void responseView(Model model){
      model.addAttribute("data", "hello!");
  }
  ```
  @Controller ์์ Http ๋ฉ์์ง ๋ฐ๋๋ฅผ ์ฒ๋ฆฌํ๋ ํ๋ผ๋ฏธํฐ(response, outputStream ..)๊ฐ ์๊ณ , void๋ฅผ ๋ฐํํ๋ค๋ฉด
  ์์ฒญ URL์ ๋ผ๋ฆฌ ๋ทฐ ์ด๋ฆ์ผ๋ก ์ฌ์ฉํ๋ค. ๊ถ์ฅํ์ง ์๋ ๋ฐฉ๋ฒ.


### โ๏ธ HTTP ๋ฉ์์ง(API)
HTTP API๋ฅผ ์ ๊ณตํ๋ ๊ฒฝ์ฐ์๋ HTML ์๋ต์ด ์๋ ๋ฐ์ดํฐ๋ฅผ ๋๊ฒจ์ค์ผ ํ๋ค.
๋ฐ๋ผ์ ๋ฉ์์ง ๋ฐ๋์ JSON, XML ์ ํ์์ผ๋ก ๋ฐ์ดํฐ ๋ด์ ์ ๋ฌํ๋ค.

- ### HttpServletResponse
  ```java
  @GetMapping("/response-body-string")
  public void responseBody(HttpServletResponse response) throws IOException {
      response.getWriter().write("OK");
  }
  ```
  ์๋ต ๊ฐ์ฒด ์์ฒด์ ๋ฉ์์ง๋ฅผ ๋ฐ๋ก ์์ฑํ๋ค.  

- ### ResponseEntity\<String>
  ```java
  @GetMapping("/response-body-string")
  public ResponseEntity<String> responseBody() {
      return new ResponseEntity<>("ok", HttpStatus.OK);
  }
  ```
  ResponseEntity๋ฅผ ์ด์ฉํ์ฌ ์๋ต ๋ฉ์์ง(String)๊ณผ ์ํ ์ฝ๋๋ฅผ ๋ด๋๋ค.  

- ### @ResponseBody String
  ```java
  @ResponseBody
  @GetMapping("/response-body-string")
  public String responseBody() {
      return "OK";
  }
  ```
  @ResponseBody ์ ๋ธํ์ด์์ ์ด์ฉํ์ฌ String ๋ฐ์ดํฐ๋ฅผ ๋ด๋๋ค.

- ### ResponseEntity\<Dto>
  
  ```java
  @GetMapping("/response-body-json")
  public ResponseEntity<HelloData> responseJson() {
      HelloData helloData = new HelloData();
      helloData.setUsername("kim");
      helloData.setAge(25);
  
      return new ResponseEntity<>(helloData, HttpStatus.OK);
  }
  ```
  ResponseEntity<Dto> ๋ฅผ ์ด์ฉํ์ฌ JSON ํ์์ ๋ฐ์ดํฐ์ ์ํ ์ฝ๋๋ฅผ ๋ด๋๋ค.  

- ### @ResponseBody Dto + @ResponseStatus
  ```java
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @GetMapping("/response-body-json")
  public HelloData responseJson() {
      HelloData helloData = new HelloData();
      helloData.setUsername("kim");
      helloData.setAge(25);
  
      return helloData;
  }
  ```
  @ResponseBody ์ ๋ธํ์ด์์ ์ด์ฉํ์ฌ JSON ํ์์ ๋ฐ์ดํฐ๋ฅผ ๋ด๊ณ , @ResponseStatus ๋ฅผ ์ด์ฉํ์ฌ ์ํ ์ฝ๋๋ฅผ ์ค์ ํ๋ค.
  ์์ ๋ฐฉ์๊ณผ ๋น์ทํ์ง๋ง, ์์ ๋ฐฉ์์ ์ํฉ์ ๋ฐ๋ผ ๋ค๋ฅธ ์ฝ๋๊ฐ ๋ด๊ธฐ๋๋ก if๋ฌธ ๋ฑ์ ์ค์ ์ด ๊ฐ๋ฅํ๋ค(๋์  ์ค์ ) ๋ณ๊ฒฝํ  ์ผ์ด ์๋ค๋ฉด
  ์ ๋ธํ์ด์์ ์ฌ์ฉํ์ฌ ์ค์ ํด๋ ๋ฌด๋ฐฉํ๋ค.
  
## ๐ง๏ธ PRG(POST/Redirect/Get)
POST ๋ก ๋ฐ์ดํฐ๋ฅผ ์ ์กํ์ฌ ์ ์ฅํ๊ฑฐ๋ ์๋ฐ์ดํธ ํ ๊ฒฝ์ฐ ์๋ก๊ณ ์นจ์ ์๋ํ์ ๋ ๋์ผ ํ ์์ฒญ์ด ํ๋ฒ ๋ ์ ์ก๋๊ฒ ๋๋ค. ์ ์ฅ๊ฐ์ ๊ฒฝ์ฐ์๋
๋์ผํ ๋ฐ์ดํฐ๋ฅผ ์์ด๋๋ง ๋ณ๊ฒฝํ์ฌ ํ๋ฒ ๋ ์ ์ฅํ๊ฒ ๋๋ ์ฌ๊ฐํ ๋ฌธ์ ๊ฐ ๋ฐ์ํ๋ค.    

์ด๋ฅผ ํด๊ฒฐํ๊ธฐ ์ํด Redirect๋ฅผ ํตํด POST ์ ์ก์ ๊ฒฐ๊ณผ ํ์ด์ง๋ฅผ ์กฐํํ๋ GET ์์ฒญ์ผ๋ก ๋ณ๊ฒฝํด์ค๋ค.
์ด ํ์๋ ์๋ก๊ณ ์นจ์ ์คํํ์ฌ๋ GET ์์ฒญ์ ํตํ ํ์ด์ง ์กฐํ๊ฐ ์ด๋ฃจ์ด์ง๋ค.


## ๐ง๏ธ RedirectAttribute
๋ฆฌ๋ค์ด๋ ํธ๋ฅผ ํ  ๋์๋ Model์ ์ ๋ฌํ  ๋ฐ์ดํฐ๋ฅผ ๋ด์ ์ ์๋ค. ์ด๋ ์ฌ์ฉํ๋ ๊ฒ์ด RedirectAttribute ์ด๋ค.   

```java
@PostMapping("/add")
public String addItem(SaveItemDto saveItemDto, RedirectAttributes attributes){
    Item saveItem = itemRepository.save(saveItemDto.toEntity());
    attributes.addAttribute("itemId", saveItem.getId());
    attributes.addAttribute("isSaved", true);
    
    return "redirect:/basic/items/{itemId}";
}
```
```addAttribute()``` ๋ฅผ ์ด์ฉํ๋ฉด ```{itemId}``` ์ ๊ฐ์ด ํด๋น ๊ฐ์ ์นํ๋์ด URL์ ๋ค์ด๊ฐ๊ฒ ๋๋ค. ์ด๋ ๊ฐ์ ์๋์ผ๋ก ์ธ์ฝ๋ฉ ๋์ด ๋ค์ด๊ฐ๋ค. ID ๊ฐ ๊ฐ์ ๊ฒฝ์ฐ์๋ ์๊ด ์์ง๋ง ํ๊ธ์ด ๋ค์ด๊ฐ ๊ฒฝ์ฐ
์ธ์ฝ๋ฉ์ ์ง์ ํด์ฃผ๋ ๊ฒ์ ์ค์ํ๋ค. ๋ณดํต ```UrlEncoder.encode()``` ๋ฅผ ์ด์ฉํ์ฌ ์ธ์ฝ๋ฉ ํ๋ค.
URL์ ์ง์ ๋์ง ์์ ๋๋จธ์ง ๊ฐ(isSaved์ ๊ฐ์)์ ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ ํ์์ผ๋ก ๋ค์ด๊ฐ๊ฒ ๋๋ค.    
ํ์๋ฆฌํ ์์๋ ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ ๊ฐ์ ```param.xxx``` ๋ก ๊ฐ์ ธ์ฌ ์ ์๋ค.

์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ ๊ฐ์ผ๋ก ๋๊ธฐ์ง ์๊ณ  ๋ง์น Model๊ณผ ๊ฐ์ด ์ฌ์ฉํ๊ณ  ์ถ๋ค๋ฉด ์๋์ ๊ฐ์ด ์ฌ์ฉํ  ์ ์๋ค.
```java
attributes.addFlashAttribute("isSaved", true);
```
flashAttribute()๋ ๋ง์น ๋ชจ๋ธ๊ณผ ๊ฐ์ด ์ฌ์ฉํ  ์ ์์ผ๋ฉฐ, ์ผ์์ ์ผ๋ก ์ฌ์ฉํ๊ณ  ์ฌ๋ผ์ง๊ฒ ๋๋ค.


<br><br><br>
> - https://codingnotes.tistory.com/28
> - https://velog.io/@jihoson94/Spring-MVC-HandlerAdapter-%EB%B6%84%EC%84%9D%ED%95%98%EA%B8%B0
> - https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-exceptionhandlers
> - http://www.mungchung.com/xe/spring/21278