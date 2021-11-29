# 📌 외부 라이브러리를 사용하지 않고 Rest API 호출
***

##  🧐 Connection 생성
```java
URL url = new URL(String url);
HttpURLConnection con = (HttpURLConnection) url.openConnection();
con.setRequestMethod("GET" | "POST" | "PUT" | "DELETE");
```
- java.net.URL 객체를 생성하고, openConnection으로 커넥션을 얻어온다.
- 요청 메서드를 GET,POST, PUT,DELETE, .. 로 설정.

## 🧐 Header
```java
con.setRequestProperty("Content-Type", "application/json");

con.getHeaderFields("Content-Type");
```
- 헤더 필드를 설정하고, 연결에서 설정된 헤더필드를 읽어올 수 있다.

## 🧐 매개변수 추가
```java
con.setDoOutput(true);
DataOutputStream dos = new DataOutputStream(con.getOutputStream());
dos.writeByte(params);
dos.flush();
dos.close();
```
- 매개변수를 추가하기 위해서는 setDoOutput을 반드시 true로 설정(default는 false)
- params의 형식은 "key1=value&key2=value2"와 같은 형식. 

## 🧐 타임아웃
```java
con.setConnecTimeout(5000);
con.setReadTimeout(5000);
```
- 서버에 대한 연결 시간.
- 데이터를 읽을 수 있을 때까지 대기하는 시간.

## 🧐 쿠키
```java
CookieManager cookieManager = new CookieManager();
String cookiesHeader = con.getHeaderField("Set-Cookie");
if(cookiesHeader != null){
    List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
    // 쿠키 저장소에 요청 쿠키 저장.
    cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
}

// 쿠키 저장소에 쿠키 추가
cookieManager.getCookieStore().add(null, new HttpCookie("username", "kim"));

// 요청에 쿠키 추가
con.disconnect();
con = (HttpURLConnection) url.openConnection();

List<HttpCookie> httpCookies = cookieManager.getCookieStore().getCookies();
String[] cookies = new String[httpCookies.size()];
httpCookies.forEach(httpCookie ->{
    int p = 0;
    cookies[p++] = httpCookie.toString();
});
```
- 쿠키를 추가하기 위해서는 연결을 닫았다가 다시 연 후 추가.

## 🧐 리다이렉션
- 기본적으로 301, 302 코드는 리다이렉션으로 default 는 true
```java
int status = con.getResponseCode();
if (status == HttpURLConnection.HTTP_MOVED_TEMP
        || status == HttpURLConnection.HTTP_MOVED_PERM) {
    String location = con.getHeaderField("Location");
    URL redirectUrl = new URL(location);
    con = (HttpURLConnection) redirectUrl.openConnection();
}
```
- status code가 301, 302 라면 location 헤더에서 변경된 url을 받아 다시 연결한다.

```java
con.setInstanceFollowRedirects(false);
```
- false로 변경하여 리다이렉션을 하지 않도록 설정할 수 있다.

## 🧐 응답 받기
```java
BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
String line;
StringBuffer res = new StringBuffer();
while((line = br.readLine()) !=null){
    res.append(line);
}

System.out.println("Response: "+ con.getResponseCode() +" "+ con.getResponseMessage());
System.out.print(res.toString());
```
- String 형태로 모두 읽어온 후 JSON 으로 파싱하여 데이터를 이용한다.
- ```con.getResponseMessage()```를 이용하여 응답 메시지를 받을 수 있다.
```
Response: 200 OK
{"page":1,"per_page":10,"total":6,"total_pages":1,"data":[{"competition":"UEFA Champions League","year":2011,"round":"GroupH","team1":"Barcelona","team2":"AC Milan","team1goals":"2","team2goals":"2"},{"competition":"UEFA Champions League","year":2011,"round":"GroupH","team1":"Barcelona","team2":"Viktoria Plzen","team1goals":"2","team2goals":"0"},{"competition":"UEFA Champions League","year":2011,"round":"GroupH","team1":"Barcelona","team2":"BATE Borisov","team1goals":"4","team2goals":"0"},{"competition":"UEFA Champions League","year":2011,"round":"R16","team1":"Barcelona","team2":"Bayer Leverkusen","team1goals":"7","team2goals":"1"},{"competition":"UEFA Champions League","year":2011,"round":"QF","team1":"Barcelona","team2":"AC Milan","team1goals":"3","team2goals":"1"},{"competition":"UEFA Champions League","year":2011,"round":"SF","team1":"Barcelona","team2":"Chelsea","team1goals":"2","team2goals":"2"}]}
```


## 🧐 실패 응답
```java
InputStreamReader isr;
if(status > 299){
    isr = new InputStreamReader(con.getErrorStream());
}
else isr = new InputStreamReader(con.getInputStream());
```
- 리다이렉션은 따로 처리하게 되면 4xx, 5xx 대 상태 코드일 때는 ErrorStream을 열어 에러를 읽어온다.


## 🧐 ScriptEngine을 이용한 Json 파싱
> 다른 외부 라이브러리를 사용하지 않고 Json 파싱.
- javascript engine 생성.
```java
ScriptEngineManager engineManager = new ScriptEngineManager();
ScriptEngine engine = engineManager.getEngineByName("javascript");
```

- script를 문자열로 작성
```java
StringBuilder script = new StringBuilder();
script.append("var res = JSON.parse('").append(res.toString()).append("');");
script.append("var totalPage = res.total_pages;");
script.append("var totalGoals = res.data.reduce(function(acc, curr){ return acc + parseInt(curr.")
.append("team1").append("goals);}, 0);");
```
- Script 실행
```java
engine.eval(script.toString());
```

- engine에서 변수값 가져오기.
```java
System.out.println("Total Page: " + engine.get("totalPage"));
System.out.println("Total Goals: " + engine.get("totalGoals"));
```
