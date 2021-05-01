## '=='과 'equals'
*******

  '=='은 주소를 비교, equals는 데이터의 값을 비교한다.

  ```java
  String str1 = "kimtaejun";
  String str2 = "kimtaejun";
  String str3 = new String("kimtaejun");

  System.out.println(str1 == str2);
  System.out.println(str1 == str3);
  System.out.println(str1.equals(str3));

  System.out.println(System.identityHashCode(str1));
  System.out.println(System.identityHashCode(str2));
  System.out.println(System.identityHashCode(str3));
  ```

  위와 같은 코드에서 str1, str2, str3 는 동일한 문자열 "kimtaejun"이 들어가 있지만. str1==str2 에서는 true를, str1.equals(str2)에서도 true를 반환하지만, 

  new String으로 생성된 str3에대해 str1 ==str3를 하게되면 false를 반환한다.

  이유는 주소값을 출력해 보면 알 수 있는데.

  str1과 str2의 주소는 같지만, str3의 주소는 다른것을 확인할 수 있었다. 

  '=='연산자는 주소값을 비교하기 때문에 str1 ==str2에서는 true가 나오고, str1==str3에서는 false를 반환한다.

  str1.equals(str3)에서는 주소값을 먼저 비교한 후 같으면 true를 반환, 아니라면 다음으로 데이터의 값 자체(재정의된 hashcode())를 비교하기 때문에 데이터의 값이 "kimtaejun"으로 같아 true를 반환한다.(equals의 재정의)

  - String.java의 equals()

      ```java
      public boolean equals(Object anObject) {
          if (this == anObject) {
              return true;
          }
          if (anObject instanceof String) {
              String aString = (String)anObject;
              if (!COMPACT_STRINGS || this.coder == aString.coder) {
                  return StringLatin1.equals(value, aString.value);
              }
          }
          return false;
      }
      ```

  - String.hashcode()

      ```java
      public int hashCode() {

          int h = hash;
          if (h == 0 && !hashIsZero) {
              h = isLatin1() ? StringLatin1.hashCode(value)
                             : StringUTF16.hashCode(value);
              if (h == 0) {
                  hashIsZero = true;
              } else {
                  hash = h;
              }
          }
          return h;
      }
      ```

  String 데이터의 값이 같다는 것은 변경사항이 없었다면 hashcode의 값이 같다는 것이다.

  예를 들면 "kimtaejun"이라는 문자열은 284720968이라는 주소를 가지는 공간에 저장 되었다면, 다른 변수에 "kimtaejun"을 할당해도 해당 변수는 동일한 주소를 가르키게 된다. 

  객체의 hashcode는 객체를 식별할 수 있는 유니크한 값이며, 메모리에 생성된 객체의 주소를 정수로 변환한 형태이다. (Person객체의 주소값과 hashcode값이 모두 다른 이유. →equals비교시 false가 나옴)

  String hashcode는 이를 오버라이딩하여 문자열을 기준으로 생성되기 때문에 문자열의 내용이 같으면 hashcode 또한 동일하다. (결국 문자열이 저장된 메모리 주소가 기준)

  → 때문에 str1과 str3는 주소는 다르지만 hashcode의 값은 같다.

  ```java
  //원래의 hashcode 값을 알고싶다면
  System.identityhashCode(st1)
  System.identityhashCode(st2) 
  ```

  - String.java의 hashcode()

  ```java
  Person ps = new Person("taejun");
  Person ps2 = new Person("taejun");
  String str1 = "kimtaejun";
  String str2 = "kimtaejun";
  String str3 = new String("kimtaejun");

  System.out.println(str1.hashCode());
  System.out.println(str2.hashCode());
  System.out.println(str3.hashCode());

  System.out.println(ps.getName().hashCode());
  System.out.println(ps2.getName().hashCode());
  ```
![image](https://user-images.githubusercontent.com/61380786/116780737-68de4180-aab9-11eb-88aa-11be7fb163d7.png)


### 객체의 비교

![image](https://user-images.githubusercontent.com/61380786/116780719-595ef880-aab9-11eb-8f56-116df7a20c2f.png)

