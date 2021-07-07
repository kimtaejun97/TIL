
### 📌 @RunWith(SpringRunner.class) --> No runnable methods
***
> java.lang.Exception: No runnable methods

@RunWith(SpringRunner.class)를 추가하고 테스트를 진행하던 중 발생한 에러.
@RunWith, SpringRunner import문을 살펴보았지만 문제를 찾을 수 없었다.

#### 👍 해결 : @Test의 import가 org.junit.jupiter.api.Test 로 되어 있었고 이를 org.junit.Test로 변경하여 해결.

