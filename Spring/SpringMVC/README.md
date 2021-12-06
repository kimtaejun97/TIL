- ### [Servlet](#-servlet)
  - #### [HttpServletRequest](#-httpservletrequest)
  - #### [HttpServletResponse](#-httpservletresponse)
 
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
서블릿에서는 개발자기 사용하기 쉽도록 요청메시지를 파싱하여 ServletRequest 객체를 만들어주고, service() 메서드를 호출한다.
service() 메서드에서는 doGet(), doPost() 와 같은 요청에 해당하는 처리 메서드를 실행한다.
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

### ☝️ GET 쿼리 파라미터
#### - getParameterNames()
```java
request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
```
getParameterNames()로 파라미터이름을 모두 가져올 수 있고, getParameter(String name)으로 값을 가져올 수 있다.
반복문을 이용하여 모든 파라미터를 조회할 수 있다. 하지만 실제로는 이렇게 모든 파라미터를 가져오는 경우는 거의 없을 것이다.
때문에 아래와 같이 단일 파라미터를 조회하는 방법을 주로 사용한다.

#### - getParameter(String name)
```java
request.getParameter("username");
```
 하지만 ```?username=kim&username=kim2``` 처럼 username 이라는 이름으로 여러개의 값이 요청으로 들어올 수 도 있다.
 이 때 위와 같은 방식으로 조회하게 되면 첫 값만 가져오게 된다. 모든 값을 가져오고 싶다면 아래의 방법을 사용한다
 
#### - getParameterValues(String name)
```java
String[] usernames = request.getParameterValues("username");
for (String username : usernames) {
    System.out.println("username = " + username);
}
```
파라미터 이름에 해당하는 값을 배열로 가져온다. 키와 값(배열)로 모든 파라미터로 조회하는 방법도 있는데 아래와 같다.

#### - getParameterMap()
```java
Map<String, String[]> parameterMap = request.getParameterMap();
    for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
        for(String value : entry.getValue()){
            System.out.println(entry.getKey() + " = " + value);
        }
    }
```
Map으로 모든 파라미터를 가져온다. 이때 value는 String[] 배열 형태로 이름에 해당하는 값을 getParameterValues()와 같이 모두 가져온다.

### ☝️ POST HTML Form 파라미터
HTML의 Form 에서 POST 메서드로 전송되는 데이터는 요청의 Body에 담겨 쿼리 파라미터 형식으로 전송된다. 이 때 Content-Type은
application/x-www-form-urlencoded 가 된다.

클라이언트에서는 구분되지만 서버 입장에서는 똑같이 위의 GET방식의 쿼리 파라미터와 동일한 메서드로 값을 얻을 수 있다.

### ☝️ API 메시지 바디
쿼리 파라미터 형식으로 전송되는 경우도 있지만 API 형식으로 사용되는 서버의 경우
XML, TEXT, JSON 특히 JSON 형태의 데이터가 바디에 담겨오는 경우가 많다. 이를 어떻게 수신하고, 파싱할 수 있을지 알아보자.
```java
ServletInputStream inputStream = req.getInputStream();
String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
```
일반 text/plain 데이터의 조회는 InputStream과 StreamUtils의 메서드를 이용하여 쉽게 String으로 변환이 가능하다.
그렇다면 JSON은 어떨까? 
```java
ServletInputStream inputStream = req.getInputStream();
String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

// Convert to Dto
HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
```
데이터를 String으로 변환하는 것 까지는 일반 text/plain과 동일하다 하지만 Json 데이터는 사용하기 쉽게 객체로 변환하여 많이 사용한다.
이때 사용되는 것이 Jackson 라이브러리의 ObjectMapper 이다. readValue() 메서드를 사용하여 JSON 형식의 데이터를 객체로 변환할 수 있다.


## 🧐 HttpServletResponse
response 객체는 응답 코드, 헤더 생성, 바디 생성의 역할을 담당한다.

#### - Status
```java
// import javax.servlet.http.HttpServletResponse;
response.setStatus(HttpServletResponse.SC_OK);
```
200, 302 등으로 적어도 되지만 더 명시적으로 값을 넣기 위해 사용한다.

#### - Header
```java
// import org.springframework.http.HttpHeaders;

response.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain;charset=utf-8");
response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
response.setHeader(HttpHeaders.PRAGMA, "no-cache");
response.setHeader("custom-Header", "my Header");
```
setHeader를 이용하여 Header를 추가할 수 있다. 마찬가지로 Content-Type 과 같이 문자열로 적어도 되지만 Spring framework에서 지원하는
HttpHeaders 를 이용하여 오타 걱정없이 헤더를 지정할 수 있다. 이 외에도 Cookie, Redirect 도 동일한 방식으로 지정 가능하지만
setHeader()를 이용한 방법 말고도 더 쉽게 값을 설정할 수 있는 편의 메서드를 제공한다.

#### - Content
```java
response.setContentType("text/plain");
response.setCharacterEncoding("utf-8");
```
Encoding 형식을 명시하지 않으면 Tomcat에서 자동으로 ISO-8859-1 로 지정해준다.    
Content-Length는 지정하지 않으면 자동으로 넣어준다.
#### - Cookie
```java
// response.setHeader("Set-Cookie", "myCookie=Umm it's delicious; Max-Age=600");

Cookie cookie = new Cookie("myCookie",  "delicious");
cookie.setMaxAge(600);
response.addCookie(cookie); 
```

#### - Redirect
```java
response.sendRedirect("/basic/hello-form.html");
```
상태 코드를 변경해주지 않아도 자동으로 302 FOUND로 변경되고 Location 헤더에 url값이 들어가게 된다.    
```Location: localhost:8080/basic/hello-form.html```

#### - 응답 메세지
```java
response.getWriter().write(message);
response.getWriter().println(message);
response.getWriter().append(message);

ServletOutputStream outputStream = response.getOutputStream();
outputStream.println(message);
```
response 객체는 바디에 응답 메세지를 추가하는 다양한 메서드를 지원한다. 이를 이용해서 일반 텍스트, HTML, JSON 등의 응답을
추가할 수 있다.

- HTML
```java
response.setContentType("text/html");
response.setCharacterEncoding("utf-8");;

PrintWriter writer = response.getWriter();
writer.println("<html>");
writer.println("<body>");
writer.println("    <div> Hello </div>");
writer.println("</body>");
writer.append("</html>");
```
Content-Type을 text/html 으로 설정하고, HTML 문법에 맞게 String 으로 응답을 돌려주면 브라우저에서 이를 렌더링해서 보여준다.



- JSON
```java
response.setContentType("application/json");
// response.setCharacterEncoding("utf-8");

HelloData helloData = new HelloData();
helloData.setUsername("kim");
helloData.setAge(25);

String result = objectMapper.writeValueAsString(helloData);
ServletOutputStream outputStream = response.getOutputStream();
outputStream.println(result);
```
응답 객체를 생성하고 Jackson 라이브러리의 ObjectMapper.writeValueAsString() 을 이용하여 객체를 JSON 형식의 String으로 변환해준다.
JSON에서는 스펙상 utf-8 형식을 사용하도록 정의되어 있어, 인코딩을 지정해주는 추가 파라미터가 필요없다.
하지만 ```response.getWriter().write()``` 를 이용한다면 자동으로 CharacterEncoding 을 넣어주게 된다.   
```OutputStream``` 을 사용해 데이터를 추가한다면 자동으로 추가하지 않는다.
