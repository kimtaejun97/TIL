> heroku remote ๋ฑ๋ก
```heroku git:remote -a {appname}```


## ๐ invalid source release: 11
***
![img_5.png](img_5.png)
- heroku๋ก push ํ ๋น๋์ค์ ๋ฐ์ํ ์๋ฌ.
- runtime java version์ ๋ช์ํด์ฃผ๊ธฐ ์ํด ์ต์์ ๋๋ ํ ๋ฆฌ์ system.properties ์์ฑ.

```properties
java.runtime.version=11
```

## ๐ no main manifest attribute
****

no main manifest attribute, in build/libs/webrtc-0.0.1-SNAPSHOT-plain.jar

- ์ต ์์์ Procfile ์์ฑ.
```
web: java -Dspring.server.port=8080 $JAVA_OPTS -jar build/libs/webrtc-0.0.1-SNAPSHOT.jar
```
- ํฌํธ ๋ฐ build ํ์ผ ์ง์ .
- property ์ต์ ๋ฑ ์ง์  ๊ฐ๋ฅ.

## ๐ Web process failed to bind to $PORT within 90 seconds of launch
****
![img_6.png](img_6.png)
- ์ง์ ํด์ค ํฌํธ์ ์คํ ํฌํธ๊ฐ ๋ฌ๋ผ์ ๋ฐ์
- application.properties์ 
```properties
 server.port=${port:8080}
```