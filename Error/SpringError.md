
## 📌 @RunWith(SpringRunner.class) --> No runnable methods
***
> java.lang.Exception: No runnable methods

@RunWith(SpringRunner.class)를 추가하고 테스트를 진행하던 중 발생한 에러.
@RunWith, SpringRunner import문을 살펴보았지만 문제를 찾을 수 없었다.

#### 👍 해결 : @Test의 import가 org.junit.jupiter.api.Test 로 되어 있었고 이를 org.junit.Test로 변경하여 해결.


## 📌 Maven surefire plugin 2.22.2 build fails
***
maven 프로젝트에서 test를 진행하던 중 Maven surefire plugin 2.22.2 build fails 가 발생하였다.    
mvn -v로 버전을 확인해보니 
```
(base) kimtaejun@gimtaejun-ui-MacBookAir static % mvn -v
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home: /Library/apache-maven-3.8.1
Java version: 15.0.2, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home
Default locale: ko_KR, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
```
와 같이 출력되었고, 여기서 자바의버전은 15.0.2임을 확인 할 수 있었다.
그러나 프로젝트 자바 버전은 11 이었고, 이를 15로 변경해주고 다시 실행 하자 정상적으로 실행 되었다.


## 📌 Failed to connect to service endpoint
***
> - EC2 환경이 아닌곳에서 spring-cloud-starter-awsd의존성 주입시 발생.

- 프로젝트 vm option
``` 
-Dcom.amazonaws.sdk.disableEc2Metadata=true
```


- application.properties 에 
```
logging.level.com.amazonaws.util.EC2MetadataUtils: error
```


## 📌 QueryDSL Cannot find symbol
***

- 생성된 Q클래스가 중복되어 cannot find symbol이 발생.
가장 첫 테스크에 폴더를 지워주는 코드를 추가하여 해결

- 처음에는 compileQeuryDsl의 실행전에 삭제하도록 하였으나 항상 실행되는 것이 아닌 task이기 때문에 삭제되지 않는 경우가 발생하였고, 항상 처음에 실행하는 task의 시작 전에 삭제하는것으로 변경.

```java
initQuerydslSourcesDir.doFirst {
    if(file(generatedDir).exists() ) delete(file(generatedDir))
}
```