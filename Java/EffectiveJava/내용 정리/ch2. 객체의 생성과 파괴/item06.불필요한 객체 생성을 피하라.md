# Item06.불필요한 객체 생성을 피하라.

> 똑같은 기능의 객체를 매번 생성하기 보다는 하나의 객체를 재사용하는 편이 나을때가 많다.


예를 들어 아래와 같은 상황이다.

## 문자열 객체 생성.
****

```java
String s = new String("test");

String s = "test";
```
:: 생성자에 넘겨진 "test"자체가 생성자로 만들어 내는 객체의 역할과 완전히 동일하다.     
:: 1번의 코드에서는 매번 새로운 객체를 생성하지만 2번에서는 하나의 객체를 재사용하여 불필요한 생성을 하지 않는다.

```java
String s1 = new String("test");
String s2 = new String("test");

System.out.println(s1 == s2 );  //false
```


## 정적 팩토리 메서드의 사용.
*****
```java
boolean b1 = Boolean.valueOf("true");
boolean b2 = Boolean.valueOf("true");

System.out.println(b1 == b2);   //true
System.out.println(b1 == Boolean.TRUE); //true

```

:: Boolean(String) 생성자를 사용하기 보다는 팩터리 메서드를 사용한다. 동일한 객체가 반환됨을 확인할 수 있다. 반환되는 객체는 static field인 Boolean.TRUE이다. 


## 생산 비용이 비싼 객체.
********
:: 비싼 객체가 반복해서 필요하다면 캐싱하여 재사용하는 것이 권장된다.   
다음은 문자열이 유효한 로마숫자인지 확인하는 메서드이다
```java
static boolean isRomanNumeral(String s){
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}

```
:: 메서드의 내부에서 생성되는 정규표현식용 Pattern 인스턴스는 한번 사용하고 버려지는데, Pattern은 입력받은 정규표현식에 해당하는 유한 상태 머신을 그리기 만들기 때문에 생성 비용이 높다.
:: 때문에 클래스를 초기화하는 과정에서 직접 생성해 캐싱해두고 호출될 때 해당 인스턴스를 반환하는 것이 효율적이다.

```java
static boolean isRomanNumeral(String s){
    return ROMAN.matcher(s).matches();
}
```
:: 성능의 개선뿐만 아니라 코드의 의미또한 명확해졌다. (Pattern 인스턴스가 드러남.)

## 어댑터
********
:: 객체가 불변이라면 재사용해도 안전함이 명백하다. 그러나 덜 명확하거나, 심지어 직관에 반대되는 상황도 있다.   
:: 어댑터는 실제 작업은 뒷단 객체에 위임하고 자신은 인터페이스의 역할을 수행한다. 때문에 뒷단 객체 하나당 하나의 어댑터만 생성하면 충분하다.
```java
Map<String, Integer> students = new HashMap<>();

students.put("kim",23);
students.put("park",25);

Set<String> names = students.keySet();
Set<String> names2 = students.keySet();

names.remove("kim");

System.out.println(names.size());   // 1
System.out.println(names2.size());  // 1
```
:: Map 의 keySet()메서드는 키를 담은 Set을 반환하고 이들은 모두 같은 객체를 참조한다. 따라서 하나의 내용이 변하면 모든 Set과 Map이 변경된다.


## 오토박싱
*****
```java
Long sum =0l;
for(long i =0l; i<Integer.MAX_VALUE; i++)
    sum+= i;
```
:: sum 변수는 Long으로 선언되어 있고, 여기에 long 타입의 i 를 더한다. 이때 i가 sum에 더해질때 마다 Long 인스턴스를 생성하게 되고,      
여기서는 약 2^31개의 인스턴스가 생성된다.
:: Long 타입에 long을 더할때 걸린 시간은 3229ms 였고, long타입에 long타입을 더할 때 걸린 시간은 729ms로 약 4배 이상의 성능차이가 발생했다.



## 🔑 핵심
> - 불필요한 객체의 생성을 피하자 (재사용)     
> - "객체의 생성은 비싸니 피하자" 라는 말이 아니다. 비싸고, 재사용이 안전한 객체의 생성을 줄이자는 것이다.      
> - 무분별한 객체 재사용은 오히려 버그와 보안상의 문제로 이어진다.
