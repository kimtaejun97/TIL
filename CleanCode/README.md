> - 🖍 소프트웨어 개발의 대부분은 유지보수! 빠른 개발을 위해서는 정돈된 코드가 필요하다.
> - 중복을 피하고, 한 기능만 수행하며, 제대로 표현하고, 작게 추상화한다.

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