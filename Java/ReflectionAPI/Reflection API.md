## Reflection API?
> 구체적인 클래스 타입을 알지 못해도 해당 클래스의 정보에 접근할 수 있게 해주는 java API
> 
**********


```java
public class People {
    private String name;
    private int age;
    private int nprogram;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
        nprogram = 0;
    }

    public void coding() {
        this.nprogram++;
    }

    public int getNprogram() {
        return nprogram;
    }
}
```

```java
 Object objPeople = new People("kim", 25);

//Object형의 People은 coding을 하지 못함.
objPeople.coding (x)
```
자바는 컴파일 타임에 타입이 결정되기 때문에 객체는 이미 컴파일 타임에 Object타입으로 결정이 되어 People의 메소드를 사용할 수 없다. Object 타입의 정보만 사용 가능.

```java
Class PeopleClass = People.class;
Method coding= PeopleClass.getMethod("coding");
coding.invoke(objPeople, null);

System.out.println(PeopleClass.getMethod("getNprogram")
        .invoke(objPeople,null));
```
:: Reflection API를 이용하여 People클래스를 얻어오고, 해당 클래스에서 메소드를 얻어올 수 있다.
invoke의 인자로 실행할 객체와 args를 넘겨준다. getNprogram 실행 결과 0으로 초기화되어 있던 값이 1로 증가하였음을 확인할 수 있다.


```java
String objPeopleName =PeopleClass.getDeclaredField("name").get(objPeople).toString();
System.out.println(objPeopleName);

Field[] objPeoplefields =PeopleClass.getDeclaredFields();
for(Field f: objPeoplefields){
    System.out.println(f.get(objPeople));
}
```
:: 심지어 private로 선언된 필드에도 접근이 가능했다. 정상적으로 objPeople을 생성할때 인자로 전달해 주었던 값이 출력되었다.


## 어떻게?
****
자바코드는 컴파일러를 거쳐 바이트 코드로 변환되어 Static 영역에 저장된다. Reflection API는 이 영역에 접근하여 해당 정보들을 활용할 수 있다.    
수 많은 라이브러리나 프레임워크에서 Reflection을 사용한다. 스프링 프레임워크에서도 빈 팩토리에서 Reflection을 이용하여 Bean을 관리하고, Hibernate에서 또한 Refelction을 이용하여 엔티티 클래스에 데이터를 주입한다. 때문에 default constructor만 생성할 수 있으면 필드의 값을 주입해줄 수 있다.


    - Hibernate에서 어떻게 엔티티에 필드값을 주입시켜주는가를 알아보다가 Reflection에 대해 알아보게 되었다.    
    - 상이당히 유용한 API이지만 성능면에서 조금 단점이 있고, 아까 보았듯 private에도 접근이 가능하기 때문에 주의가 필요할 것 같다.