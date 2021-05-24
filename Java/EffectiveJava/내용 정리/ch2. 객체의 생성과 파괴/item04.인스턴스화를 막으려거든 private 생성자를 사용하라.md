# Item04.인스턴스화를 막으려거든 private 생성자를 사용하라.

> Utility 클래스들과 같이 정적 메서드와 정적 필드만을 담은 클래스를 만들 때 인스턴스를 생성하여 사용하도록 설계하지 않는다. 그렇다고 생성자를 작성하지 않는다고 해도, 컴파일러가 자동으로 기본 생성자를 만들어준다.

## 방법1. Abstract Class
```java
public abstract class UtilityClass {
    public static String getName(){
        return "kim";
    }

    public static void main(String[] args) {
        UtilityClass.getName();

//         추상 클래스 인스턴스 생성 불가능
        UtilityClass utilityClass = new UtilityClass(); (x)

    }
}
```
:: abstract Class 로 만들면 해당 클래스의 인스턴스를 생성하지 못하게 할 수 있다.    
```java
public class AnotherClass extends UtilityClass{
    public static void main(String[] args) {
        AnotherClass anotherClass = new AnotherClass();
//        메소드 사용 불가능.
//        anotherClass.getName() (x);
    }
}
```
:: 하지만 해당 클래스를 상속받은 클래스의 인스턴스는 생성될수 있다, 그러나 메소드의 사용이 불가능한 아무의미 없는 인스턴스가 된다.     
:: 때문에 방법 2를 제안한다.

## 방법2. private 생성자
```java
public class UtilityClass {
    public static String getName(){
        return "kim";
    }
    //인스턴스 생성 방지.
    private UtilityClass(){
        throw new AssertionError();
    }
}
```

:: 생성자를 private로 생성하게 되면 외부에서 인스턴스를 생성할수도 없고, 컴파일러 또한 public 기본 생성자를 생성하지 않는다.    
:: 상속 또한 private 생성자이기 때문에 불가능하디.      
:: 생성자가 존재하는데 호출을 할수 없어 직관적이지 못하기 때문에 적절한 주석이 필요하다.

