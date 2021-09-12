> 🖍 소프트웨어 개발의 대부분은 유지보수! 빠른 개발을 위해서는 정돈된 코드가 필요하다.    
> 🖍 중복을 피하고, 한 기능만 수행하며, 제대로 표현하고, 작게 추상화한다.   
> 🖍 읽기 좋은, 읽기 쉬운 코드.
# 🖥 목차
***
- ### [2. 의미있는 이름](#-의미있는-이름)
- ### [3. 함수](#-함수)

# 📌 의미있는 이름
****
> 🔑 분명한 의도, 올바른 정보전달, 의미있는 구분...


- ### ☝️ 의도를 분명하게 밝히자 !
    - 좋은 이름은 시간을 절약하게 해준다 (해석하느 시간을 줄여주기 떄문에.)
    - 수행 기능을 명확히 하는 메소드 명, 
    - x 와 같은 의미없는 변수이름이 아닌 변수의 역할을 명사로 정확하게 명시.
    - 4 와 같은 상수가 어떤의미인지 명확하게 표기하기 위해 선언. 
    ```java
        if(cell[0] == 4) // 코드를 봤을때 어떤 의미인지 파악하기 힘들다.
  
        if(cell[STATUS_VALUE] = FLAGGED) // 각 상수의 의미 정확하게 명시.
  
        if(cell.isFlagged()) // 메소드를 이용해 상수를 감춤.   
    ```

- ### ☝️ 올바른 정보를 전달하자 !
    - List가 아닌데 List라고 표현하는 방식은 그릇된 정보를 전달한다.
    - 실제 컨테이너가 List라고 하더라도, 변수명에 이를 명시하지 않는 것이 더 바람직 하다.
    - 복수형, Group등의 작명을 사용한다.
    
- ### ☝️ 의미있게 구분하라 !
    - 연속된 숫자, 불용어의 사용을 지양하라.
    - name과 nameString과 같은 구분은 좋지않다. 대부분의 name은 String이므로 뒤에 붙은 String은 의미가 없다.
    - 마찬가지로 customer, customerInfo, account,AccountData 또한 의미상의 구분이 되지 않는다.
    - 배열을 복사하는 메소드라면 a1,a2와 같은 매개변수 이를을 사용할 것이 아니라 source, destination 과 같이 의미가 분명하게 들어나는 작명이 좋다.

- ### ☝️ 발음하기 쉬운 이름을 사용하라 !
    - 이름이 길어지더라도 약어 형태의 변수명은 좋지 않다.
    - ex) genymdhms(generate date year,month,hour,minute,seconds) -> generationTimestamp

- ### ☝️ 검색하기 쉬운 이름을 사용하라!
    - a와 같은 변수명을 검색하게 되면 무수하게 많은 결과가 나오기 때문에 찾기 힘들다.
    - 간단한 변수명은 로컬 변수에서 사용하는 것이 바람직하다.
    - 이름을 의미있게 짓게 되면 그만큼 이름이 길어진다. 하지만 검색하기에 매우 좋다.
    - ex) NUMBER_OF_TASKS와 같은 이름은 다른 결과가 검색될 걱정이 거의 없다.
    
- ### ☝️ 인코딩을 피하라 !
    - 인코딩 방식은 옛날에 타입을 명시하기 위한 레거시 코드.
    - 멤버 변수를 뜻하는 mName, 타입을 명시한 nameString
    - AccountFactoryInterface, IAccountFactory -> AccountFactory
    - 인코딩을 사용한다면 구현체에서 AccountFactoryImpl처럼 사용할 수 있다.
    
- ### ☝️ 적합한 이름
    - 클래스 이름, 객체이름 : 명사, 명사구 (Account,AddressParser ...)
    - 메서드 이름 : 동사, 동사구 (getName, setName, isPublic ...)
    - 기발한 이름 대신 분명한 이름.

- ### ☝️ 정적 팩토리 메소드를 사용하라 !
    ```java
    Account account = new Account("kim");
  
    Account account = Account.CreateAccountWithName("kim");
    ```

- ### ☝️ 한 개념에 한 단어를 사용하라 !
    - 한 개념에 여러 단어를 사용한다면 기억하기 어렵고, 혼란을 줄 수 있다.
    - ex) controller, manager, driver 는 하는 역할은 같지만 다르게 표기할 수 있는 예 이다.
    - 동일한 역할을 한다면 같은 단어르 사용하도록 한다.
    
- ### ☝️ 한 단어는 한가지 목적으로 사용해라.
    - 구현되어 있는 add* 메소드가 값을 받아 더하는 역할이라고 해보자. 이 때 새로 add 메서드를 만들 때 해당 메서드가 컬렉션에 요소를 추가하는 역할을 한다면 이는 바람직하지 않다.

- ### ☝️ 의미있는 맥락. 
    - 주소의 앞 이름이라면 firstName 대신 addrFirstName과 같이 사용할 수 이ㅣㅆ다.
    - 메서드에서의 맥락.
  
    👎 맥락이 불분명한 메서드

  ```java
  private void printGuessStatistics(char candidate, int count){
      String number;
      String verb;
      String pluralModifier;
      
      if(count == 0){
          number = "no";
          verb = "are";
          pluralModifier = "s";
      } else if(count == 1){
          number = "1";
          verb = "is";
          pluralModifier = "";
      }else{
          number = Integer.toString(count);
          verb = "are";
          pluralModifier = "s";
      }
      
      String guessMessage = String.format(
            "There %s %s %s%s", verb, number, candidate, pluralModifier
      );
      System.out.println(guessMessage);
  }
  ```
  
    👍 맥락이 분명하게 수정한 메서드
    ```java
    public class MeaningfulContext {
        private String number;
        private String verb;
        private String pluralModifier;
    
        public String make(char candidate, int count){
            createPluralDependentMessageParts(count);
            
            return String.format(
                    "There %s %s %s%s", verb, number, candidate, pluralModifier
            );
        }
    
        private void createPluralDependentMessageParts(int count) {
            if(count ==0){
                thereAreNoLetters();
            }else if(count == 1){
                thereIsOneLetter();
            }else{
                thereAreManyLetters(count);
            }
        }
    
        private void thereAreManyLetters(int count) {
            this.number = Integer.toString(count);
            this.verb = "are";
            this.pluralModifier = "s";
        }
    
        private void thereIsOneLetter() {
            this.number = "1";
            this.verb = "is";
            this.pluralModifier = "";
        }
    
        private void thereAreNoLetters() {
            this.number = "no";
            this.verb = "are";
            this.pluralModifier = "s";
        }
    }
    ```
    > - 메서드를 분리하기 위해 클래스를 생성하고, 변수를 클래스의 멤버 변수로 넣었다.
    > - 변수는 확실하게 속하게 되고, 변수의 의미가 명확해진다.
    > - 알고리즘을 읽기가 수월해진다.


# 📌 함수 
****
> 🖍 작게, 더 작게. -> 읽기 좋은 함수. (함수의 분리)
> 🖍 각 함수는 하나의 작은 역할만을 담당한다.

### ☝️ 작게 만들어라 !
  - 각 함수는 작은 하나의 역할을 담당한다.
  - if 문, else문, while문에 들어가는 블록은 한줄이어야 마땅하다. 즉, 함수를 호출하는 부분만 있어야 한다.
  - 함수의 이름은 그만큼 적절하게 기능을 설명할 수 있어야 한다.
  - 함수의 들여쓰기 수준은 1단 또는 2단을 넘어서면 안된다.

### ☝️ 한 가지만 해라 !
  - 함수는 한 가지를 하고, 그 한가지를 잘 해야한다.
  - 🤔 한 가지 역할? 지정된 함수 이름 아래서 추상화 수준이 하나.
  - ex) 페이지가 테스트 페이지 인지 확인 한 후 테스트 페이지라면 설정 페이지, 해제 페이지를 등록, 테스트 페이지인가와 상관 없이 HTML로 렌더링. -> RenderPageWithSetupsAndTeardowns()
  - 의미 있는 이름으로 다른 함수를 더이상 추출 할 수 없다면 그 함수는 한가지 역할을 하는 것. 

### ☝️ 한 함수에서 추상화 수주은 하나로 통일 하자 !
  > - getHtml()
  > - PathParser.render(pagePath);
  > - .append({String});
  - 위의 세 문장은 추상화 수준이 각각 다르다. 위에서 부터 아래로 갈수록 추상화 수준이 낮아진다.
  - 한 함수 내에 추상화 수준이 섞여있으면 코드를 읽을 때 헷갈릴 수 있다. 또한 계속해서 이러한 방식으로 추가하게 된다.
  
  #### ✏️ 위에서 아래로 코드 읽기: 내려가기 규칙
    - 한 함수 다음에는 추상화 수준이 한단계 낮은 함수가 온다.
    - 데이터 저장
    - > 데이터를 저장하려면 데이터를 정렬하고 수정해야 한다.
    -   >  데이터 정렬 하려면 ...

  
### ☝️ Switch문
  - switch 문은 작게 만들기 어렵지만, 완전히 피할 방법은 없다.
  - 때문에 switch문을 저차원 클래스에 숨기는 식으로 해결 한다. (다형성을 사용한다)
  - ex) 직원을 매개로 받아 직원의 타입에 따라 월급을 계산하는 스위치문 -> 직원 레코드를 받아 직원을 생성하는 스위치문으로 변경

    👎 변경 전 스위치문을 사용한 페이 계산  
    ```java
    public Money calculatePay(Employee e){
        switch(e.type){
            case COMMISSIONED:
                return calulateCommissionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
        }    
    }
    ```
    
    👍 스위치문을 사용한 다형성 객체 생성.
    ```java
    public abstract class Employee {
        public abstract long calculatePay();
    }
    ```
    ```java
    
    public interface EmployeeFactory {
        public Employee makeEmployee(EmployeeRecord r) throws Exception;
    }
    ```
    ```java
    
    public Employee makeEmployee(EmployeeRecord r) throws Exception {
        switch (r.getType()){
            case COMMISSIONED:
              return new CommissionedEmployee(r);
            case HOURLY:
              return new HourlyEmployee(r);
            case SALARIED:
              return new SalariedEmployee(r);
            default:
              throw new Exception();
        }
    }
    ```
    > 각각의 월급을 구하는 메서드를 생성하는 것이 아닌. 각 타입에 맞게 Employee를 생성하고, 인터페이스를 거쳐 해당 파생 클래스의 월급계산 메서드를 사용한다.



### ☝️ 서술적인 이름을 사용하라 !
- 길고 서술적인 함수 이름이 길고 서술적인 주석보다 낫다.
- 코드를 읽고 짐작한 내용대로 기능이 수행된다면 깨끗한 코드이다.
- 서술적인 이름을 사용하면 개발자 머릿속에서도 설계가 뚜렷해 진다.

  🖍 이름을 붙일 때는 일관성이 있어야 한다. 같은 문구, 명사, 동사를 사용한다.


### ☝️️ 함수 인수
- 가장 이상적인 인수 개수는 0개이다.
- 3개 이상은 피하는 것이 좋다.
- 플래그 인수를 사용하지 말자.(플래그에 따라 여러가지 역할을 하게 되기 때문에.)
  
  #### 🧐 많이 사용하는 단항 형식
    - 인수에 질문을 던지는 경우 (is**, exists ...)
    - 인수를 변환하여 결과를 반환하는 경우.
    - 함수 형식이 이벤트인 경우. (시스템 상태를 변경)
  
  #### 🧐 이항 함수
    - 어쩔 수 없이 2개의 항이 무조건 필요한 함수도 있다(점의 좌표와 같이)
    - 당연하게 보이는 assertEquals(expected, actual) 조차 바람직하지는 않다. (순서를 기억해야한다. 위험 부담.)
    - 하나의 인수를 클래스 구성변수로 만들어 인수로 넘기지 않도록 변경할 수 있다. A.method(B);
  
  #### 🧐 인수 객체
    - 인수가 2-3개 필요하다면 일부를 독자적인 클래스 변수로 선언할 수 있는 가능성을 짚어보자.
      ```java
      Circle makeCircle(double x, double y, doyble radius);
    
      Circle makeCircle(Point center, doyble radius);
      ```
    
  #### 🧐 가변 적인 인수 개수.
    - 인수를 동등하게 취급하면 List형 인수 하나로 취급할 수 있다.
    - String.format(String format, Object... args) 사실상 이항 함수이다.
  
  #### 🧐 동사와 키워드
    - 단항 함수는 함수 이름과 인수 이름이 동사/명사 쌍을 이루는 것이 좋다 (write(name)...)
    - 여기서 더 나아가 writeField(name)이라면 name이 필드라는 사실이 더욱 명확하게 드러난다.
    - 함수에 키워드를 추가. 즉 함수에 인수의 이름을 넣는다
      - ex) assertEquals(expected,actual) -> assertExpectedEqualsActual(expected,actual) 로 변경하면 인수 순서를 기억할 필요가 없어진다.