# 📌 Java

### 🧐 인스턴스 변수, 클래스 변수, 상수
```java
    /**
     * 클래스 변수는 공유 자원이기 때문에 동시 접근이 이루어지면 값을 예측할 수 없다.
     * Race Condition 발생. 때문에 final로 선언하여 사용한다.
     */
    
    public static final int BIRTHDAY = 509; // 상수

    public static int shareResult = 0; // 클래스 변수, 정적 변수.

    public int result = 0; // 인스턴스 변수
```

### 🧐 객체지향 프로그래밍?

- 기능을 가지고 있는 클래스를 인스턴스화 한다.
- 필요한 기능을 가지고 잇는 인스턴스의 메
  서드를 호출한다.
- 호출한 결과를 조합(협력)한다.


### 🧐 학습 테스트
자바 라이브러리 등에 대한 학습이 필요할 때 사용할 수 있다.    
라이브러리에 변경사항이 생겼을 때 가장 먼저 알 수 있도록 해준다.


### 🧐 Test
```java
@BeforeAll // 클래스 단위로 테스트가 시작하기 전 처리.
@AfterAll // 클래스 단위로 테스트가 시작하기 후 처리.

@BeforeEach // 메서드 단위로 테스트가 시작하기 전 처리.
@AfterEach // 메서드 단위로 테스트가 시작하기 후 처리.
```

#### 👆 ParameterizedTest
```java
@ValueSource(ints = {4,5,6,7,8,9})
@ParameterizedTest
void 원볼_원스트라이크(int num){

    // given
    Balls player = new Balls((Arrays.asList(1, num, 2)));

    // when
    String result = judgement.compare(computer, player);

    // then
    assertThat(result).isEqualTo("1스트라이크 1볼");
}
```