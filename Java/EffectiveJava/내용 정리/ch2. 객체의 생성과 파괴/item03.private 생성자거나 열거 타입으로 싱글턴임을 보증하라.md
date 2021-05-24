# private 생성자거나 열거 타입으로 싱글턴임을 보증하라.

> 싱글턴(singleton) : 인스턴스를 오직 하나만 생성할 수 있는 클래스. 함수와 같은 무상태 객체나 설계상 유일해야 하는 시스템 컴포넌트가 그 예이다.

> 싱글턴 클래스는 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다.    
> 타입을 인터페이스로 정의한 다음 이를 구현해서 만든 싱글턴이 아니라면 싱글턴 인스턴스를 가짜(mock) 구현으로 대체할 수 없기 때문이다.


## 방법 1. public static final 필드 싱글턴
******
```java
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();
    
    private Singleton1(){}
}
```
```java
//생성 불가능
Singleton1 singleton1 = new Singleton1();  (x)
//인스턴스 가져옴.
Singleton1 singleton1 = Singleton1.INSTANCE;
```
:: 생성자가 private로 선언되기 때문에 클라이언트가 새로운 인스턴스를 생성할 수 없다, 따라서 인스턴스가 전체 시스템에서 하나뿐임이 보장된다.      
:: 작성이 간결하고, 싱글턴임이 명백하게 드러난다.   

:: 하지만 예외로 리플렉션 API의 AccessibleObject.setAccesible(true)를 사용해 private 생성자를 호출하여 새로운 인스턴스가 생성될 수 있다.
따라서 이를 방지하기 위해서는 다음과같은 작성이 필요하다.
```java
int count =0;

private Singleton1() {
        count++;
        if(count != 1){
            throw new IllegalStateException("this Object should be Singleton");
        }
}
```


## 방법 2. 정적 팩터리 방식의 싱글턴
*********
```java
public class Singleton2 {
    private static final Singleton2 INSTANCE = new Singleton2();
    
    private Singleton2(){};
    
    public static Singleton2 getInstance(){
        return INSTANCE;
    }
}
```
```java
Singleton2 singleton2 = Singleton2.getInstance();
```
:: INSTANCE 필드도 private로 처리하고 인스턴스를 얻기위한 정적 메소드를 추가하여 이를 통해 인스턴스를 얻는다.    

- ### 장점
        1. API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.(메소드에서 new로 인스턴스를 생성해서 반환하면 된다.)
        2. 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.(아이템 30)
        3. 정적 팩터리의 메서드를 참조를 공급자(supplier)로 할 수 있다.
```java
Supplier<Singleton2> singleton2Supplier= Singleton2::getInstance;
```

## 방법 1과 2의 직렬화 문제.
*******
> 두 방법으로 구현된 싱글턴 클래스는 직렬화, 역 직렬화 과정에서 새로운 인스턴스가 생성되게 된다.     
> 이를 방지하기 위해서는 두 가지 처리가 필요하다.

    1. 모든 인스턴스 필드의 transient 선언.
    2. readResolve 메서드의 제공.
- 방법 2에서의 변경.(Serializable)
```java
private static final transient Singleton2 INSTANCE = new Singleton2();

private Object readResolve(){
        return INSTANCE;
}

```


## 방법3. enum 타입 방식의 싱글턴
```java
public enum Singleton3 implements Serializable {
    INSTANCE;
}
```
```java
Singleton3 singleton3 =Singleton3.INSTANCE;
```

:: 방법1과 유사하지만 간결하고, 리플랙션의 방어와 직렬화하는데 있어 추가적인 처리가 필요하지 않다.    
:: 그러나 싱글턴 클래스가 클래스를 상속해야 한다면 이 방법은 사용할 수 없다. (인터페이스 구현만 가능.)