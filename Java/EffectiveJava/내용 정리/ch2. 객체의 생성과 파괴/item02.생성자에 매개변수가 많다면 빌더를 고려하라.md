# Item 02. 생성자에 매개변수가 많다면 빌더를 고려하라.

### - 정적 팩터리와 생성자의 제약
> 선택적 매개변수가 많을 때 적절히 대응하기 어렵다.

## 해결1: 점층적 생성자
*****

:: 점층적 생성자를 이용하여 선택적 매개변수를 받는 생성자를 점점 늘려가며 생성자를 구성하고, 원하는 매개변수가 모두 포함된 생성자중 가장 작은것을 골라 호출하게 된다.    
ex)A,B,C,D의 매개변수가 존재할 때  A,B를 매개변수로, A,C를 매개변수로, A,D를 매개변수로 등...     
         
    - 매개변수가 많아지면 클라이언트 코드를 작성하기 어려워지고, 읽기도 힘들어진다. 값의 의미와, 매개변수의 갯수에 주의를 기울여야 하며,
    매개 변수 값을 잘못 넘겨주어도 오류가 발생하지 않는다. 

## 해결2: JavaBeans Pattern
*****

:: 매개 변수가 없는 생성자를 만들고, Setter 메서드를 호출하여 원하는 매개변수의 값을 설정한다.

    - 코드는 길어지지만 인스턴스의 생성이 쉽고, 더 읽기 쉬운 코드가 만들어진다.
    - 하지만 객체 하나를 생성하기 위해 많은 메서드를 호출해야하고, 객체가 완전히 생성되기 전까지 일관성이 무너진 상태에 놓인다.(중간에 호출될 경우 잘못 사용될 수 있음.)
    - Setter를 사용하기 때문에 불변 클래스로 만들 수 없고, 마찬가지로 변경가능하기 때문에 쓰레드 안정성을 얻으려면 추가 작업이 필요하다(lock)


## 해결3 : 빌더 패턴
*****
:: 점층적 생성자 패턴의 안정성과 자바 빈즈 패턴의 가독성을 겸비.     
:: 필수 매개변수 만으로 생성자를 호출해 빌더 객체를 얻고, 빌더 객체가 제공하는 세터 메서드들로 선택 매개변수들의 값을 설정한다.    
마지막으로 build()메서드를 호출하여 필요한 객체를 얻는다. 빌더는 생성할 클래스 안에 정적 멤버클래스로 만들어 두는것이 일반적이다.
````java
public class NutritionFacts {
    private final int servings;
    private final int servingSize;
    private final int sodium;
    private final int fat;
    private final int carbohydrate;

    public static class Builder {
        private final int servings;
        private final int servingSize;

        private int sodium = 0;
        private int fat = 0;
        private int carbohydrate = 0;

        public Builder(int servings, int servingSize) {
            this.servings = servings;
            this.servingSize = servingSize;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder fat(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }

    }

    public NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
````

````java
public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(10,100)
                .fat(200)
                .carbohydrate(300)
                .sodium(150)
                .build();
    }
````
:: 각 메소드에서 유효성을 검사할 수 있다.    
:: 여러 매개변수를 혼합하여 검사해야할 경우 build 메서드에서 호출하는 생성자에서 이를 검사할 수 있다.    
:: 세터 메서드는 빌더 자신을 반환하기 때문에 연쇄적으로 호출이 가능하다. 코드를 쓰고 읽기 쉽다.


### 계층적으로 설계된 클래스에서의 빌더 패턴
*****
:: 각 계층의 클래스에 관련 빌더를 멤버로 정의, 추상 클래스는 추상 빌더를, 구체 클래스는 구체 빌더를 갖게 한다.
````java
public class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION;}
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract Pizza build();
        protected  abstract T self();
    }
    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
````

```java
import java.util.Objects;

public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}

```
*****
    - Objects.requireNonNull(obj) : obj가 null인지 확인. 두번째 인자로 메세지를 설정할 수도 있음.
    - Objects.requireNonNullElse(obj, obj2) : obj가 null이라면 obj2로 대체 가능.

:: 추상 메서드 self를 이용하여 하위 클래스에서 형변환을 거치치 않고도 메서드 연쇄를 지원할 수 있다.(simulated self-type)
:: 하위 클래스의 빌더가 정의한 build() 메서드는 해당하는 구체 하위클래스를 반환한다. 때문에 클라이언트는 형변환을 신경쓰지 않고 빌더를 사용할 수 있다 (공변반환 타이핑)


## 빌더 패턴의 단점
> 점층적 생성자 패턴보다는 코드가 장화해 매개변수가 4개 이상은 되어야 값어치를 한다. 그러나 API는 시간이 지날 수록 매개변수가 많아지는 경향이 있기 때문에 애초에 빌더로 시작하는 편이 나을때가 많다.



