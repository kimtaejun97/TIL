
# 📌 Javascript

## 🧐 자료형
- `String`: 문자 데이터, `''` or `""` or ` `` `
  > - ` `` ` 을 사용할 때에는 ${var}로 변수를 집어넣을 수 있다.
  > ```js
  > let name = 'kim'
  > let hello = `Hello ${name}`;
  > ```
- `Number`: 정수 및 부동소수점 숫자.
  - tofix() 메서드를 이용하여 소수점 자릿수 명시 가능.
- `Boolean`: ture, false
- `Undefined`: 값이 할당되지 않은 상태.
- `Null`: 어떤 값이 의도적으로 비어있음을 의미. null을 할당한 상태.
- `Object`: 여러 데이터를 key:value 쌍으로 저장. 참조형 데이터.
  ```js
  let obj = {
    name: 'kim',
    age: 26
  };
  
  obj.name // kim
  ```
- `Array`: 배열 데이터, 참조형 데이터 ['elm1', 'elm2' ...]
- `Symbol('설명')`: 유일한 식별자를 만들고 싶을 때 사용.
- `BigInt` : 123412351231231235n 과 같이 맨 뒤에 n이라는 키워드가 붙는다. 출력시에도 똑같이 n이 붙는다.
  > 숫자 데이터로 변환(Number(num))하여 n 제거 가능.

- ### 👆 자료형의 확인
  - `typeof ${data}` : string, number .. 등 구분 가능. 하지만 array와 object는 모두 object 타입이기 때문에 구분할 수 없다.
  - `&{data}.constructor`: String, Number, Array, Object
  - 두 방법 모두 `null`, `undefined` 는 TypeError 가 발생한다.
  
  - `Object.prototype.toString.call(${data})` 를 이용하여 모든 데이터 타입을 알아낼 수 있다.
    ```js
    Object.prototype.toString.call(${data}) // Object dataType
    Object.prototype.toString.call(${data}).slice(8,-1) // dataType
    ```

## 🧐 변수
데이터를 저장하고 참조하는 데이터의 이름.
- ### 👆var(지양)
  - 함수 레벨 유효범위
  - 재할당 가능
  - 호이스팅 발생: 특정 선언이 유효한 범위의 최상단으로 끌어올려져 선언되는 것.
  - 중복 선언 가능
  - 전역 선언시 전역 객체의 속성으로 등록 가능.(window.xxx)
- ### 👆 let 
  - 블록 레벨 유효 범위.
  - 값의 재할당 가능.
  - 중복 선언, 호이스팅, 전역 객체의 속성으로의 등록 x
- ### 👆 const
  - 블록 레벨 유효 범위.
  - 값의 재할당 불가.(TypeError: Assignment to constant variable.)
  - 중복 선언, 호이스팅, 전역 객체의 속성으로의 등록 x
  
먼저 const로 선언하고 재할당이 필요하면 let으로 변경하는 것을 권장한다.(재할당이 분명하면 let 선언.)



## 🧐 함수
1급 객체(First-class object)로 하나의 값으로 변수나 인수 혹은 반환으로 사용가능한 참조형 데이터.
- ### 👆 기명(이름이 있는) 함수 선언
  ```js
  function funcName(param1, param2, ...) {
      
      ...
    
    return value;
  }
  ```
  `function` 키워드로 시작하면 함수 **선언**, 호이스팅이 된다. 즉, 선언이 아래에 있어도 함수 선언의 위에서 함수를 호출할 수 있다.
- ### 👆 익명 함수 표현
  ```js
  let funcName = function(){
      ...
  }
  ```
  변수에 할당하면 함수 `표현식`, 호이스팅이 되지 않는다.

- ### 👆 함수 호출
  ```js
  const returnValue = funcName(agrs1, args2);
  ```

- ### 👆 객체 데이터로서의 함수(함수 표현) - 메서드
  ```js
  const hello = {
      name: 'kim';
      getName: function(){
          return this.name;
      }
  }
  ```

- ### 👆 콜백 함수
  함수의 인수로 사용되는 함수.
    ```js
    function add(a,b, callbackFunc) {
        callbackFunc(a + b);
        return a + b;
    }
    
    add(1,3, function(value){
        console.log(value)
    });
    ```
  
- ### 👆 화살표 함수
  간결한 문법으로 함수를 사용할 수 있다.(익명 함수)
  ```js
  const sum = (a, b) => return a + b
  
  // 매개변수가 한 개 이면 () 생략 가능. return 생략 가능. 
  x => x * x 
  
  // 매개변수가 없음. return 생략 불가능.(중괄호가 있거나 다른 로직이 있는 경우)
  () => {
    const x = 10;
    return x * x;
  }
  ```
  
  
## 🧐 형변환
함수와 연산자에 전달되는 값은 자동 형변환이 일어난다.
- 예를들어 동등 연산자 `==`를 사용할때 타입이 서로 다르다면 비교를 위해 강제 형변환이 일어난다.(123 == '123' => true)
- 일치 연산자 `===`는 타입의 변환없이 두 피연산자의 값과 타입이 모두 같아야 true 를 반환한다.(형변환 없음.)

## 🧐 Truthy & Falsy

### 👆 거짓으로 판단되는 값(Falsy)
- `false`
- `null`
- `undefined`
- `0`
- `-0`
- `NaN`
- `0n`
- ``

### 👆 참으로 판단되는 값(Truthy)
- `true`, `123`, `-42`, `{}`, `[]` ...
## 🧐 DOM API
> Document Object Model, Application Programming Interface. HTML을 제어하는 명령들.

- ### 👆 요소 찾기
  ```js
  let boxEl = document.querySelector('.box'); // 해당되는 요소중 가장 첫 번째 요소
  let boxEls = document.querySelectorAll('.box');
  let boxEl = document.getElementsByxxx(${elm}});
  ```

- ### 👆 addEventListener()
  ```js
  addEventListener('event', function(){
      ...
  });
  ```
  Event 상황에 실행되는 함수를 **Handler** 라고 한다.

- ### 👆 classList
  ```js
  boxEl.classList.add('active'); // class 추가.
  
  let isContains = boxEl.classList.contains('active'); // 포함 여부.
  
  boxEl.classList.remove('active'); // class 제거.
  ```

- ### 👆 forEach
  ```js
  let boxEls = document.querySelectorAll('.box');
  
  boxEls.forEach(function(boxEl, index){
      ...
  });
  ```
  첫 번째 매개변수에는 반복 중인 요소가, 두 번째 매개변수에는 index가 들어간다.

- ### 👆 textContent
  ```js
  // getter
  let content = boxEl.textContent;
  
  //setter
  boxEl.textContent = 'box!!';
  ```
  텍스트로 이루어진 값을 가져오거나 지정.


💡 script 태그가 js 문서에서 찾고자 하는 요소보다 앞에 오게되면 해당 요소를 아직 읽지 않았기 때문에
js 문서에서 이를 찾지 못하는 문제가 발생한다.

이를 해결하기 위해서 body 태그의 맨 마지막에 script 태그가 위차하게 변경해도 되지만
`<script defer src="...">"`와 같은 방법으로 해결할 수 있다.
