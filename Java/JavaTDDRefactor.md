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