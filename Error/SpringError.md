
## ๐ @RunWith(SpringRunner.class) --> No runnable methods
***
> java.lang.Exception: No runnable methods

@RunWith(SpringRunner.class)๋ฅผ ์ถ๊ฐํ๊ณ  ํ์คํธ๋ฅผ ์งํํ๋ ์ค ๋ฐ์ํ ์๋ฌ.
@RunWith, SpringRunner import๋ฌธ์ ์ดํด๋ณด์์ง๋ง ๋ฌธ์ ๋ฅผ ์ฐพ์ ์ ์์๋ค.

#### ๐ ํด๊ฒฐ : @Test์ import๊ฐ org.junit.jupiter.api.Test ๋ก ๋์ด ์์๊ณ  ์ด๋ฅผ org.junit.Test๋ก ๋ณ๊ฒฝํ์ฌ ํด๊ฒฐ.


## ๐ Maven surefire plugin 2.22.2 build fails
***
maven ํ๋ก์ ํธ์์ test๋ฅผ ์งํํ๋ ์ค Maven surefire plugin 2.22.2 build fails ๊ฐ ๋ฐ์ํ์๋ค.    
mvn -v๋ก ๋ฒ์ ์ ํ์ธํด๋ณด๋ 
```
(base) kimtaejun@gimtaejun-ui-MacBookAir static % mvn -v
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home: /Library/apache-maven-3.8.1
Java version: 15.0.2, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home
Default locale: ko_KR, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
```
์ ๊ฐ์ด ์ถ๋ ฅ๋์๊ณ , ์ฌ๊ธฐ์ ์๋ฐ์๋ฒ์ ์ 15.0.2์์ ํ์ธ ํ  ์ ์์๋ค.
๊ทธ๋ฌ๋ ํ๋ก์ ํธ ์๋ฐ ๋ฒ์ ์ 11 ์ด์๊ณ , ์ด๋ฅผ 15๋ก ๋ณ๊ฒฝํด์ฃผ๊ณ  ๋ค์ ์คํ ํ์ ์ ์์ ์ผ๋ก ์คํ ๋์๋ค.


## ๐ Failed to connect to service endpoint
***
> - EC2 ํ๊ฒฝ์ด ์๋๊ณณ์์ spring-cloud-starter-awsd์์กด์ฑ ์ฃผ์์ ๋ฐ์.

- ํ๋ก์ ํธ vm option
``` 
-Dcom.amazonaws.sdk.disableEc2Metadata=true
```


- application.properties ์ 
```
logging.level.com.amazonaws.util.EC2MetadataUtils: error
```


## ๐ QueryDSL Cannot find symbol
***

- ์์ฑ๋ Qํด๋์ค๊ฐ ์ค๋ณต๋์ด cannot find symbol์ด ๋ฐ์.
๊ฐ์ฅ ์ฒซ ํ์คํฌ์ ํด๋๋ฅผ ์ง์์ฃผ๋ ์ฝ๋๋ฅผ ์ถ๊ฐํ์ฌ ํด๊ฒฐ

- ์ฒ์์๋ compileQeuryDsl์ ์คํ์ ์ ์ญ์ ํ๋๋ก ํ์์ผ๋ ํญ์ ์คํ๋๋ ๊ฒ์ด ์๋ task์ด๊ธฐ ๋๋ฌธ์ ์ญ์ ๋์ง ์๋ ๊ฒฝ์ฐ๊ฐ ๋ฐ์ํ์๊ณ , ํญ์ ์ฒ์์ ์คํํ๋ task์ ์์ ์ ์ ์ญ์ ํ๋๊ฒ์ผ๋ก ๋ณ๊ฒฝ.

```java
initQuerydslSourcesDir.doFirst {
    if(file(generatedDir).exists() ) delete(file(generatedDir))
}
```