# 📌 Servlet
****
## 🧐 Servlet 생성
```java
@ServletComponentScan // 서블릿 스캔하여 등록.
@SpringBootApplication
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }
}
```
```@ServletComponentScan```을 사용하여 @ComponentScan처럼 선언된 패키지부터 하위의 모든 패키지에 있는
서블릿을 스캔하여 등록할 수 있다.

```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
```
```@WebServlet()```을 이용하여 서블릿을 생성한다. name은 서블릿의 이름, urlPatterns는 서블릿에 매핑된 URL 이다.
두 값은 유일한 값을 가져야 한다.

클라이언트가 요청하면 서블릿의 service 메서드가 호출되게 된다. request와 response 객체를 출력하면 다음과 같다.    
```org.apache.catalina.connector.RequestFacade@e025e73```
퍼사드란 커다란 코드에 대한 간략화된 인터페이스를 제공하는 객체로, 클라이언트가 더 단순하게 사용할 수 있도록 만들어준다.

요청 과정을 조금 더 상세히 말하자면 WAS에서 요청을 받아 요청, 응답 객체를 생성하고 이를 서블릿 컨테이너에 생성된 서블릿에 전달한다.
서블릿에서는 개발자기 사용하기 쉽도록 요청메시지를 파싱하여 ServletRequest 객체를 만들어주고, service() 메서드를 호출하고, service() 메서드에서는 doGet(), doPost() 와 같은 요청에 해당하는 처리 메서드를 실행한다.
응답으로 Response 객체를 WAS에 넘겨주면 WAS 에서는 이를 기반으로 HTTP Response를 생성하여 클라이언트에게 돌려준다.

다음과 같이 request, response 객체를 사용할 수 있다.
```java
String username = request.getParameter("username");

response.setContentType("text/plain");
response.setCharacterEncoding("utf-8");
response.getWriter().write("hello " + username);  // 바디에 들어간다.
```
- request: 파라미터, URL, 인증여부, 헤더, 메서드, 주소, 프로토콜, 세션, 쿠키 등 요청 정보를 가져올 수 있다. 또한 set, getAttribute()를 이용하여 임시 저장소를 사용할 수 있다.
- response: Content-Type, CharacterEncoding, 상태 코드, 쿠키 등 응답을 설정할 수 있고 바디에 데이터를 추가할 수 있다.


#### 💡 ```logging.level.org.apache.coyote.http11 = debug```를 이용하여 요청 정보를 로깅할 수 있다.


## 🧐 HttpServletRequest

### ☝️ StarLine
```java
System.out.println("request.getMethod() = " + request.getMethod()); //GET
System.out.println("request.getProtocal() = " + request.getProtocol()); // HTTP/1.1
System.out.println("request.getScheme() = " + request.getScheme()); //http
System.out.println("request.getRequestURL() = " + request.getRequestURL()); // http://localhost:8080/request-header
System.out.println("request.getRequestURI() = " + request.getRequestURI());  // /request-test
System.out.println("request.getQueryString() = " + request.getQueryString()); // username=kim&age=25
System.out.println("request.isSecure() = " + request.isSecure()); //https ?
```
메서드, 프로토콜, 스키마, URL, 파라미터 등의 기본적인 요청 정보를 가져올 수 있다.

### ☝️ Header
```java
request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ":" + request.getHeader(headerName)));
```
getHeaderNames()를 이용하여 모든 헤더네임을 가져올 수 있고, getHeader(String name)으로 해당 이름의 헤더값을 가져올 수 있다.    

![img.png](img.png)


```java
// Server, Port
System.out.println("request.getServerName() = " + request.getServerName()); 
System.out.println("request.getServerPort() = " + request.getServerPort());

System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
System.out.println("request.getRemotePort() = " + request.getRemotePort());

System.out.println("request.getLocalName() = " + request.getLocalName());
System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
System.out.println("request.getLocalPort() = " + request.getLocalPort());

// Locale
request.getLocales().asIterator()
        .forEachRemaining(locale -> System.out.println("locale = " + locale));
System.out.println("request.getLocale() = " + request.getLocale()); // 가장 가중치가 높은(앞에 있는) locale

// Cookies
if (request.getCookies() != null) {
    for (Cookie cookie : request.getCookies()) {
        System.out.println(cookie.getName() + ": " + cookie.getValue());
    }
}

// Content
System.out.println("request.getContentType() = " + request.getContentType()); // null, get이기 때문에.
System.out.println("request.getContentLength() = " + request.getContentLength());
System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
System.out.println();
```
Server, Remote, Local 정보를 가져올 수 있고, Locale, 쿠키, Content 관련 값들을 가져올 수 있다.

