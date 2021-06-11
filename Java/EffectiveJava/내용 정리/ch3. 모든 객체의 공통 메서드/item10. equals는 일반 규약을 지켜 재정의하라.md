# Item10. equals는 일반 규약을 지켜 재정의하라.

## 재정의하지 않는 것이  좋을 때.
*****
> equals는 기본적으로 재정의 하지 않으면 그 클래스의 인스턴스는 오직 자기 자신과만 같다.

### 1. 각 인스턴스가 본질적으로 고유하다.
    - 값을 표현하느 것이 아니라 동작하는 개체를 표현하는 클래스 ex)Thread

### 2. 인스턴스의 '논리적 동치성'을 검사할 일이 없다.
    - java.util.regex.Pattern 에서 equals를 재정의 하여 정규식이 같은지 논리적 동치성을 검사할 수 있다.     
    하지만 이러한 검사가 필요없다고 판단되면 기본 equals만으로 충분하다.

### 3. 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.
    - Set,List, Map 구현체들은 Abst클래스로부터 구현한 equals를 상속받아 그대로 사용한다.
```java
public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Map))
            return false;
        Map<?,?> m = (Map<?,?>) o;
        if (m.size() != size())
            return false;

        try {
            for (Entry<K, V> e : entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                if (value == null) {
                    if (!(m.get(key) == null && m.containsKey(key)))
                        return false;
                } else {
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }
```

:: AbstractMap에서 구햔한 equals, 같은 객체가 아니더라도 <key,value> 쌍이 모두 동일하다면 ture,   
구현체인 HashMap등에서도 동일하게 사용할 수 있기 때문에 재정의하지 않는다.

### 4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.
    - 호출할 일이 없으면 당연히 재정의할 필요도 없다. 혹시 equals가 실수로라도 호출되는걸 막고 싶다면 다음과 같이 구현해둔다.
```java
@Override
public boolean equals(Object o ){
    throw new AssertionError();
}
```

## equlas를 재정의 해야할 때.
********
> 객체 식별성(두 객체가 물리적으로 같은가)이 아니라 논리적 동치성을 확인해야 하고, 상위클래스에서 이러한 기능을 하도록 재정의되지 않았을 때. (주로 값 클래스)      
> - 그러나 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 클래스라면 equlas를 재정의 하지 않아도 논리적 동치성과 객체 식별성이 사실상 같은 의미를 가진다.


### equlas 메서드를 재정의 할 때의 일반 규약.(Object 명세에 적힌 규약)
    - 아래 규약은 모두 참조 값이 null이 아님을 전제로 한다.
    - 반사성(reflexivity): x.equals(x)는 true.
    - 대칭성(symmentry) : x.equals(y) 와 y.equals(x)의 결과는 같다.
    - 추이성(transitivity) : x.equals(y) =true, y.equals(z) =true이면, x.equals(z) =true.
    - 일관성(consistency) : x.equals(y)를 반복해서 호출해도 항상 같은 값을 반환한다.
    - null-아님 : x.equals(null)은 false이다.


## equals 메서드 구현.
**********
### 1. ==연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
    - 자기 자신이라면 true를 반환한다.
```java
if (o == this)
    return true;
```

### 2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
    - 그렇지 않다면 false를 반환한다. 보통 equasl가 정의된 클래스 타입인지 확인하지만 가끔은 그 클래스가 구현한 특정 인터페이스가 될 수도 있다.
    - 자신을 구현한 서로다른 클래스 끼리도 비교할 수 있도록 수정하기도 한다. (Set, List, Map ...) 
```java
if (!(o instanceof Map))
    return false;
```

### 3. 입력을 올바른 타입으로 형변환 한다.
    - 앞서 instanceof로 검사를 했기 때문에 문제가 발생하지 않는다.
```java
Map<?,?> m = (Map<?,?>) o;
```

### 4. 입력 객체와 자기 자신이 대응되는 '핵심'필드들이 모두 일치하는지 하나씩 검사한다.
    - 하나라도 다르다면 false를 반환한다. 2단계에서 인터페이스를 사용했다면 필드값을 가져올 때도 해당 인터페이스의 메서드를 사용해야 한다.
```java
try {
    for (Entry<K, V> e : entrySet()) {
        K key = e.getKey();
        V value = e.getValue();
        if (value == null) {
            if (!(m.get(key) == null && m.containsKey(key)))
                return false;
        } else {
            if (!value.equals(m.get(key)))
                return false;
        }
}
```

> - float ,double을 제외한 기본 타입 필드는 '==' 연산자로 비교.      
> - 참조 타입 필드는 equals 메서드로.    
> - float와 double은 각각 정적 메서드인 Float.compare, Double.compare로 비교한다. (특수한 부동 소수값 등을 다뤄야 함.)equals도 가능하나 오토 박싱을 수반할 수 있어 성능이 좋지않다.
> - null 값을 정상 값으로 취급하는 참조 타입 필드는 Object.equlas(Object, Object)정적 메소드를 사용하자.(NullPointException 발생 방지.)


## 📌 주의 사항
*****
    - equals를 재정의 할 땐 hashCode도 반드시 재정의 하자.(아이템 11)
    - 너무 복잡하게 해결하려 하지 말자, 필드의 동치성만 검사해도 규약을 어렵지 않게 지킬 수 있다.
    - Object타입 이외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자 ->오버라이딩이 아닌 오버로딩.

## 🔑 핵심
> - 꼭 필요한 경우가 아니면 equals를 재정의 하지 않아도 원하는 비교를 정확히 수행한다.
> - 재정의해야 할 때는 해당 클래스의 핵심 필드를 모두 빠짐없이 규약을 지켜가며 비교한다.


