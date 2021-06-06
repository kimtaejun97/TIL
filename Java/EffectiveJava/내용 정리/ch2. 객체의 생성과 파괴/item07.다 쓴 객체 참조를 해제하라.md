# Item07. 다 쓴 객체 참조를 해제하라.

> 자바의 GC가 모든것을 해결해주지는 않는다.


## 스택 
*****

```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INTTIAL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEFAULT_INTTIAL_CAPACITY];
    }
    
    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop(){
        if(size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    
    public void ensureCapacity(){
        if(elements.length ==size)
            elements = Arrays.copyOf(elements, size*2 +1);
    }
    

}
```
:: 스택이 커졌다 작아질 때, 스택에서 꺼내진 객체들을 GC가 회수하지 않기때문에 메모리의 누수가 발생한다.
:: 객체 참조 하나를 살려두면 GC는 그 객체뿐만 아니라 그 객체가 참조하는 객체, 그 객체가 또 참조하는 객체.. 모두 회수할 수 없다.


```java
 public Object pop(){
        if(size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] =null;
        return result;
    }
```

:: 메모리의 누수는 pop메소드에서 발생하기 때문에 더 이상 필요없어진 원소의 참조를 null처리한다. 잘못된 참조에서 오는 오류또한 덤으로 잡아준다.     
:: 그러나 모든 객체를 사용한 후 null처리하는 방법은 바람직하지 않다. 가장 좋은 방법은 변수의 범위를 최소가 되게 정의하는 것이다.

:: Stack 클래스는 자기 메모리를 직접 관리하기 때문에 이러한 null처리가 필요하다. GC의 입장에서는 똑같이 유효한 객체이기 때문이다.


## 캐시
******
:: 캐시 역시 메모리 누수를 일으키는 주범이다.
:: 캐시 외부에서 키를 참조하는 동안만 엔트리가 살아있는 캐시가 필요한 상황이라면 WeakHashMap을 사용하여 메모리 누수를 방지할 수 있다.
```java
String key = "key1";
Object value = new Object();

Map<String, Object> cache = new WeakHashMap<>();
cache.put(key,value);
```
:: StrongReference인 key가 필요 없어지면 해당 엔트리를 해시에서도 자동으로 비워준다. 즉 StrongReference인 key가 GC의 대상이 되면 이를 참조하는 WeakReference인 객체 또한 GC의 대상이 될 수 있다.    
::  보통은 캐시의 유효기간을 정확히 정의하기 어렵기 때문에 백그라운드 스레드를 사용하여 특정 시간마다 비워준다.

## 콜백 
*****
:: 콜백도 캐시와 마찬가지로 콜백을 지울 수 있는 방법을 제공하지 않는다면 계속 쌓이기만 하여 메모리 누수가 발생한다.    
:: 마찬가지로 WeakHashMap을 사용하여 해결 할 수 있다.



## 🔑 핵심
> - 클래스 자신이 메모리를 직접 관리하는 경우에는 사용하지 않는 객체를 null 처리해주는 것이 필요하다.
> - WeakReference를 사용하여 메모리 누수를 막을 수 있다.