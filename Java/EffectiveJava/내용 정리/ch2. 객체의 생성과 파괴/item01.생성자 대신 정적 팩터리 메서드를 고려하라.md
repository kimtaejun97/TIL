#Item 01 생성자 대신 정적 팩터리 메서드를 고려하라.

## 장점 1. 이름을 가질 수 있다.
*********
:: 생성자에 넘기는 매개변수와 생성자 자체 만으로는 반환될 객체에 대한 특성을 제대로 설명할 수 없다.    
하지만 정적 팩터리 메소드의 이름만 잘 짓는다면 객체의 특성을 잘 묘사할 수 있다.
````java
public static People student(String name, String number){
        People people =new People(name);
        people.number = number;
        return people;
    }
````
ex) BigInteger.probablePrime -> 값이 소수인 BIgInteger를 반환.
<br>
<br>



## 장점 2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
********
:: 미리 만들어 놓은 인스턴스나 새로 생성한 인스턴스를 캐싱하여 재활용 하는식으로 불필요한 객체 생성을 피할 수 있다.    
:: 반복되는 요청에 같은 객체를 반환하는 식으로 인스턴스르 통제 할 수 있다 -> 싱글턴, 인스턴스화 불가로 만들 수 있다.
:: 불변 값 클래스에서 동치인 인스턴스가 하나임을 보장. 인스턴스 통제는 플라이웨이트 패턴의 근간이 된다.
````java
private static final People staticPeople = new People("kim");

public static People getStaticPeople(){
        return staticPeople;
}
````



## 장점3. 반환 타입의 하위 타입 객체를 반활할 수 있는 능력이 있다.
********

:: 유연성이 생긴다. API를 만들 때 이러한 유연성을 응용하면 구현 클래스를 공개하지 않고도 객체를 반환할 수 있어 API를 작게 유지할 수 있다.
````java
public interface PeopleInterface {
    public static People getPeople(){
        return new People();
    }
}
````
:: java 8부터 interface에서의 public static method 선언을 , java 9부터 private static method선언을 허용한다. 그러나 정적 필드와 정적 멤버 클래스는 public이어야 한다.   
:: 구현 클래스를 숨기는 것 개념적 무게 즉, 프로그래머가 이를 다루기 위한 개념의 수와 난이도를 낮춰준다.


## 장점 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
********

:: 반환 타입의 하위 타입이기만 하면 어떤 클래스 객체든 반환할 수 있다.    
:: Man 과 Girl class가 People class 를 상속할때. 아래와 같은 반환이 가능하다.
````java
public static People isMan(Boolean flag){
        return flag ? new Man(): new Girl();
    }
````
:: ex) Enumset 클래스에서는 원소가 64개 이하면 RegularEnumSet을 ,그보다 많다면 JumboEnumSet의 인스턴스를 반환한다.


## 장점 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
********
:: 이러한 유연성은 서비스 제공자 프레임워크를 만드는 근간이다.    
:: 서비스 제공자 프레임워크는 구현체의 동작을 정의하는 서비스 인터페이스, 구현체를 등록할때 사용하는 제공자 등록 API, 클라이언트가 서비스의 인스턴스를 얻을 때 사용하는 서비스 접근 API가 있다.   
ex) JDBC에서는 Connection이 서비스 인터페이스, registerDirver가 제공자 등록 API, getConnection이 서비스 접근 API역할을 한다.
````java
public static People getPeople(){
        People people = new People();
        // TODO people = 풀 패키지 경로로부터 현 클래스의 하위클래스를 읽어옴.
        return people;
    }
````
:: 클라이언트는 서비스 접근 API를 사용할 떄 원하는 구현체의 조건을 명시할 수 있다.     
예를들어 JDBC프레임 워크에서 DriverManager.getConnection을 수행할 때 Mysql 등 다양한 driver마다 다른 인스턴스를 반환해준다. 


## 단점 1. 상속을 하려면 public이나 protected 생성자 없이, 정적 팩터리 메소드만 제공하면 하위클래스를 만들 수 없다.
:: 그러나 상속보다 컴포지션(item18)을 사용하도록 유도하고 불변 타입(item17)으로 만들려면 이 제약을 지켜하 한다는 점에서 단점이라고 보기엔 어렵다.

## 단점 2. 정적 팩토리 메소드는 프로그래머가 찾기 어렵다.
:: 생성자처럼 API설명에 명확이 드러나지 않으므로 문서의 상단에 잘 써놓거나 메서드의 이름을 널리 알려진 규약에 따라 지어 문제를 완화해줘야 한다.

> from : 매개 변수를 받아 해당 타입의 인스턴스를 반환하는 형변환 메소드   
> of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환.   
> instancce, getInstance : 매개변수로 명시한 인스턴스 반환, 같은 인스턴스임을 보장하진 않음.   
> creat, newInstance : 매번 새로운 인스턴스 생상 보장.   
> getType : 생성할 클래스가 아닌 다른 클래스에 팩토리 메서드를 정의. Type은 팩토리 메소드가 반환할 객체의 타입.

