# item05.자원을 직접 명시하지 말고 의존객체 주입을 사용하라

> 많은 클래스가 하나 이상의 자원에 의존함.
> 예를 들어 맞춤법 검사기는 사전에 의존하게 되고 이런 클래스를 잘못된 방식으로 구현한 것을 흔히 볼 수 있다. 


## 잘못된 구현
********

### 정적 유틸리티 클래스(아이템4)

```java
public class SpellChecker1 {
    private static final Lexicon dictionary = new KoreanDictionary();

    //객체 생성 방지
    private SpellChecker1() {
    }

    public static boolean isValid(String word) {
        // check
        return true;
    }
}
```
:: 유연하지 못하다. 사전의 교체가 어려움.

### 싱글톤으로 구현(아이템3)
```java
public class SpellChecker2 {
    private final Lexicon dictionary =new KoreanDictionary();
    
    private SpellChecker2(){}

    public static final SpellChecker2 INSTANCE = new SpellChecker2();

    public boolean isValid(String word){
        // check
        return true;
    }

}
```

:: 마찬가지로 유연하지 못하고, 사전의 교체가 어렵다.

    -위의 두가지 방식에서는 새로운 사전의로 교채하기 위해서는 메서드를 추가해야 한다. 하지만 이러한 방식은 오류를 내기 쉬우며,     
    멀티 쓰레드 환경에서 사용하기에 적합하지않다.

## 적절한 구현
*******
```java
public class SpellChecker3 {
    private final Lexicon dictionary;
    
    public SpellChecker3(Lexicon dictionary) {
        this.dictionary =  Objects.requireNonNull(dictionary);
    }
    public boolean isValid(String word){
        //check
        return true;
    }
}

```
:: 인스턴스를 생성할 때 생성자에 필요한 자원(사전을 넘겨준다.) 이는 의존 객체 주입의 한 형태이다.     
:: 정적 팩터리, 빌더에도 적용 가능하다. 

### 변형 : 자원이 아닌 자원 팩터리 넘겨주기.
```java
public class SpellChecker4 {
    private final Lexicon dictionary;

    public SpellChecker4(Supplier<Lexicon> dictionary) {
        this.dictionary =  Objects.requireNonNull(dictionary.get());
    }
    public boolean isValid(String word){
        //check
        return true;
    }
}
```
```java
Lexicon lexicon = new KoreanDictionary();
SpellChecker4 spellChecker4 = new SpellChecker4(new Supplier<Lexicon>() {
    @Override
    public Lexicon get() {
        return lexicon;
    }
});
```

## 🔑 핵심
> - 클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 해당 자원들을 클래스가 직접 만들게 해서는 안된다.    
> - 대신 필요한 자원(또는 팩터리)을 생성자(또는 정적 팩터리, 빌더)에 넘겨준다.    
> - 이런 의존객체 주입은 클래스의 유연성, 재사용성, 테스트 용이성을 개선해준다
