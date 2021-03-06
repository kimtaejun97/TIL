# ๐ ์ธ๋ถ ๋ผ์ด๋ธ๋ฌ๋ฆฌ๋ฅผ ์ฌ์ฉํ์ง ์๊ณ  Rest API ํธ์ถ
***

##  ๐ง Connection ์์ฑ
```java
URL url = new URL(String url);
HttpURLConnection con = (HttpURLConnection) url.openConnection();
con.setRequestMethod("GET" | "POST" | "PUT" | "DELETE");
```
- java.net.URL ๊ฐ์ฒด๋ฅผ ์์ฑํ๊ณ , openConnection์ผ๋ก ์ปค๋ฅ์์ ์ป์ด์จ๋ค.
- ์์ฒญ ๋ฉ์๋๋ฅผ GET,POST, PUT,DELETE, .. ๋ก ์ค์ .

## ๐ง Header
```java
con.setRequestProperty("Content-Type", "application/json");

con.getHeaderFields("Content-Type");
```
- ํค๋ ํ๋๋ฅผ ์ค์ ํ๊ณ , ์ฐ๊ฒฐ์์ ์ค์ ๋ ํค๋ํ๋๋ฅผ ์ฝ์ด์ฌ ์ ์๋ค.

## ๐ง ๋งค๊ฐ๋ณ์ ์ถ๊ฐ
```java
con.setDoOutput(true);
DataOutputStream dos = new DataOutputStream(con.getOutputStream());
dos.writeByte(params);
dos.flush();
dos.close();
```
- ๋งค๊ฐ๋ณ์๋ฅผ ์ถ๊ฐํ๊ธฐ ์ํด์๋ setDoOutput์ ๋ฐ๋์ true๋ก ์ค์ (default๋ false)
- params์ ํ์์ "key1=value&key2=value2"์ ๊ฐ์ ํ์. 

## ๐ง ํ์์์
```java
con.setConnecTimeout(5000);
con.setReadTimeout(5000);
```
- ์๋ฒ์ ๋ํ ์ฐ๊ฒฐ ์๊ฐ.
- ๋ฐ์ดํฐ๋ฅผ ์ฝ์ ์ ์์ ๋๊น์ง ๋๊ธฐํ๋ ์๊ฐ.

## ๐ง ์ฟ ํค
```java
CookieManager cookieManager = new CookieManager();
String cookiesHeader = con.getHeaderField("Set-Cookie");
if(cookiesHeader != null){
    List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
    // ์ฟ ํค ์ ์ฅ์์ ์์ฒญ ์ฟ ํค ์ ์ฅ.
    cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
}

// ์ฟ ํค ์ ์ฅ์์ ์ฟ ํค ์ถ๊ฐ
cookieManager.getCookieStore().add(null, new HttpCookie("username", "kim"));

// ์์ฒญ์ ์ฟ ํค ์ถ๊ฐ
con.disconnect();
con = (HttpURLConnection) url.openConnection();

List<HttpCookie> httpCookies = cookieManager.getCookieStore().getCookies();
String[] cookies = new String[httpCookies.size()];
httpCookies.forEach(httpCookie ->{
    int p = 0;
    cookies[p++] = httpCookie.toString();
});
```
- ์ฟ ํค๋ฅผ ์ถ๊ฐํ๊ธฐ ์ํด์๋ ์ฐ๊ฒฐ์ ๋ซ์๋ค๊ฐ ๋ค์ ์ฐ ํ ์ถ๊ฐ.

## ๐ง ๋ฆฌ๋ค์ด๋ ์
- ๊ธฐ๋ณธ์ ์ผ๋ก 301, 302 ์ฝ๋๋ ๋ฆฌ๋ค์ด๋ ์์ผ๋ก default ๋ true
```java
int status = con.getResponseCode();
if (status == HttpURLConnection.HTTP_MOVED_TEMP
        || status == HttpURLConnection.HTTP_MOVED_PERM) {
    String location = con.getHeaderField("Location");
    URL redirectUrl = new URL(location);
    con = (HttpURLConnection) redirectUrl.openConnection();
}
```
- status code๊ฐ 301, 302 ๋ผ๋ฉด location ํค๋์์ ๋ณ๊ฒฝ๋ url์ ๋ฐ์ ๋ค์ ์ฐ๊ฒฐํ๋ค.

```java
con.setInstanceFollowRedirects(false);
```
- false๋ก ๋ณ๊ฒฝํ์ฌ ๋ฆฌ๋ค์ด๋ ์์ ํ์ง ์๋๋ก ์ค์ ํ  ์ ์๋ค.

## ๐ง ์๋ต ๋ฐ๊ธฐ
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
- String ํํ๋ก ๋ชจ๋ ์ฝ์ด์จ ํ JSON ์ผ๋ก ํ์ฑํ์ฌ ๋ฐ์ดํฐ๋ฅผ ์ด์ฉํ๋ค.
- ```con.getResponseMessage()```๋ฅผ ์ด์ฉํ์ฌ ์๋ต ๋ฉ์์ง๋ฅผ ๋ฐ์ ์ ์๋ค.
```
Response: 200 OK
{"page":1,"per_page":10,"total":6,"total_pages":1,"data":[{"competition":"UEFA Champions League","year":2011,"round":"GroupH","team1":"Barcelona","team2":"AC Milan","team1goals":"2","team2goals":"2"},{"competition":"UEFA Champions League","year":2011,"round":"GroupH","team1":"Barcelona","team2":"Viktoria Plzen","team1goals":"2","team2goals":"0"},{"competition":"UEFA Champions League","year":2011,"round":"GroupH","team1":"Barcelona","team2":"BATE Borisov","team1goals":"4","team2goals":"0"},{"competition":"UEFA Champions League","year":2011,"round":"R16","team1":"Barcelona","team2":"Bayer Leverkusen","team1goals":"7","team2goals":"1"},{"competition":"UEFA Champions League","year":2011,"round":"QF","team1":"Barcelona","team2":"AC Milan","team1goals":"3","team2goals":"1"},{"competition":"UEFA Champions League","year":2011,"round":"SF","team1":"Barcelona","team2":"Chelsea","team1goals":"2","team2goals":"2"}]}
```


## ๐ง ์คํจ ์๋ต
```java
InputStreamReader isr;
if(status > 299){
    isr = new InputStreamReader(con.getErrorStream());
}
else isr = new InputStreamReader(con.getInputStream());
```
- ๋ฆฌ๋ค์ด๋ ์์ ๋ฐ๋ก ์ฒ๋ฆฌํ๊ฒ ๋๋ฉด 4xx, 5xx ๋ ์ํ ์ฝ๋์ผ ๋๋ ErrorStream์ ์ด์ด ์๋ฌ๋ฅผ ์ฝ์ด์จ๋ค.


## ๐ง ScriptEngine์ ์ด์ฉํ Json ํ์ฑ
> ๋ค๋ฅธ ์ธ๋ถ ๋ผ์ด๋ธ๋ฌ๋ฆฌ๋ฅผ ์ฌ์ฉํ์ง ์๊ณ  Json ํ์ฑ.
- javascript engine ์์ฑ.
```java
ScriptEngineManager engineManager = new ScriptEngineManager();
ScriptEngine engine = engineManager.getEngineByName("javascript");
```

- script๋ฅผ ๋ฌธ์์ด๋ก ์์ฑ
```java
StringBuilder script = new StringBuilder();
script.append("var res = JSON.parse('").append(res.toString()).append("');");
script.append("var totalPage = res.total_pages;");
script.append("var totalGoals = res.data.reduce(function(acc, curr){ return acc + parseInt(curr.")
.append("team1").append("goals);}, 0);");
```
- Script ์คํ
```java
engine.eval(script.toString());
```

- engine์์ ๋ณ์๊ฐ ๊ฐ์ ธ์ค๊ธฐ.
```java
System.out.println("Total Page: " + engine.get("totalPage"));
System.out.println("Total Goals: " + engine.get("totalGoals"));
```
