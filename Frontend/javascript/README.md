
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
  
  // 참조형 데이터는 return 생략. 소괄호로 감싸주어야 한다.
  name => ({
    name:'kim'
  })
  ```
  
## 🧐 this

### - 화살표 함수: 자신이 **선언된** 함수 범위에서 정의.
```js
function person(){
  this.age = 22
  return {
      name: 'kim',
      age: 44,
      getAge: () => {
          console.log(this.age) // 22
      }
  }
}

const p = person()
p.getAge()
```
화살표 함수가 선언되어 있는 함수에서 정의되기 때문에 this는 person 함수를 가르킨다.    
만약 감싸고 있는 함수가 없다면 전역이 되고, 결국 this는 window가 된다.


### - 일반 함수: **호출 위치에 따라** 정의.
```js
function person(){
  this.age = 22
  return {
      name: 'kim',
      age: 44,
      getAge: function () {
          console.log(this.age) // 44
      }
  }
}

const p = person()
p.getAge()
```
p 에는 현재 반환받은 객체가 들어있고, 해당 객체가 this가 된다. 따라서 age 값은 44이다.    
내장 함수를 사용할때 주의해야 한다. 일반 함수로 선언하게 되면 내장 함수 뒤쪽의 어디선가에서 실행되기 떄문에
this를 사용할 때 undifined 값이 들어올 수 있다.

- #### 💡 .call()
  ```js
  newPerson = person.getAge
  newPerson() // 호출 위치가 전역이기 때문에 undefined
  
  newPerson.call(person) // 22
  ```
  호출 위치, 즉 this를 지정해 준다.


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

## 🧐 내장 API

### 👆 문자 관련
- #### `.length`
  문자의 길이를 반환한다. `str.length`

- #### `.includes(v)`
  - 대상 문자에 주어진 문자가 포함되는지 확인하여 boolean값 반환 한다..
  - 찾기 시작할 위치를 두 번째 인수로 전달 가능하다.

- #### `.indexOf(v)`
  - 대상 문자에 주어진 문자와 일치하는 첫 번째 인덱스를 반환, 없으면 -1을 반환한다.
  - 찾기 시작할 위치를 두 번째 인수로 전달 가능하다.
  
- #### `.match(regex)`
  - 대상 문자에서 주어진 정규식과 일치하는 배열을 반환한다.
  - ` 'hello'.match('[^ab]') ` => hello
    
- #### `.reaplace(regex, new)`
  - 문자, 정규식과 일치하는 부분을 교채한 새로운 문자를 **반환**합니다.(기존 문자는 유지)
  - 처음으로 찾은 문자만 변경. 정규식으로 글로벌 플래그(`\g`)를 붙여주면 모두 바꾸어 준다.
  
- #### `.search(regex)`
  - 대상 문자에서 *정규식*과 일치하는 첫 번째 인덱스를 반환한다.
  
- #### `.slice(start, end)`
  - 대상 문자의 일부(범위로)를 추출해 새로운 문자를 반환한다.
  - 범위는 첫 인수부터 두 번쨰 인수 하나 앞 인덱스 까지이다.
  - `str.slice(5,-1)` 
  
- #### `.split(d)`
  - 주어진 구분자로 나누어 배열로 반환한다.
  
- #### `.toLowerCase()`, `.toUpperCase()`
  - 대상 문자를 모두 소문자, 대문자로 변환해 **반환** 한다.
  
- #### `.trim()`
  - 앞뒤 공백 문자를 제거한 새로운 문자를 반환한다.
  

### 👆 숫자 관련

- #### `.toFixed()`
  - 숫자를 고정 소수점 표기로 **문자**로 반환한다.
  - `3.141592.toFixed(2)` => '3.14'

- #### `Number.isNaN(v)`
  주어진 값이 NaN인지 확인한다.

- #### `Number.parseInt(value ,radix)`
  - 주어진 값(문자, 숫자)를 파싱해 특정 진수(2번째 인수)의 정수로 반환한다.
  - `Number.parseInt(3.14, 10)`
    - '120px' => 120 으로 잘 변환 된다. (숫자로 시작하기 때문에.)
    - '123a4' => 123
    - 'a123'  => NaN

### 👆 Math
- #### `Math.abs(n)`: 절댓값
- #### `Math.ceil(n)`: 올림
- #### `Math.floor(n)`: 내림
- #### `Math.round(n)`: 반올림
- #### `Math.max(val1, val2, val3, ...)`: 최댓값
- #### `Math.min(val1, val2, val3, ...)`: 최솟값
- #### `Math.random()`
  - 0이상 1 미만의 난수를 반환.
  - 보통 정수를 반환하기 위해 Math.floor(Math.random() * (end, start)) + start 로 사용한다.
  
### 👆 Array API

- #### `.length`
  - 배열의 길이를 반환한다.
  
- #### `.concat(arr)`
  - 대상 배열과 주어진 배열을 병합해 새로운 배열을 반환한다. 원본은 바뀌지 않는다.
  
- #### `.every(callback)`
  - 대상 배열의 모든 요소가 콜백 테스트를 통과 하는지 확인하여 boolean값을 반환한다.
    ```js
    const arr = [1,2,3,4]
    const isValid = arr.every(item => item < 5)
    ```
  
- #### `some(callback)`
  - 배열의 요소 한 개라도 콜백 테스트를 통과하는지 확인한다.

- #### `.filter(callback)`
  - 주어진 콜백의 테이트를 통과하는 요소들로만 새로운 배열을 만들어 반환한다.
  - 통과한 요소가 없다면 빈 배열을 반환한다.
  
- #### `.find()`
  - 주어진 콜백 테스트를 통과하는 첫 번쨰 **요소**를 반환한다.
  
- #### `.findIndex()`
  - 주어진 콜백 테스트를 통과하는 첫 번째 **요소의 인덱스**를 반환한다.

- #### `.forEach(callback)`
  - 배열의 **모든 요소**를 순회한다.
  - 첫 번째 매개변수에는 반복 중인 요소가, 두 번째 매개변수에는 index가 들어간다.
  ```js
  let boxEls = document.querySelectorAll('.box');
  
  boxEls.forEach((boxEl, index) => {
      ...
  });
  ```
  
- #### `.includes()`
  - 배열에 특정 요소를 포함하고 있는지 확인한다.
  
- #### `.join(d)`
  - 모든 요소를 구분자를 기준으로 연결해 문자로 반환한다.
  - 구분자를 명시하지 않으면 `,`로 구분한다.

- #### `.map(callback)`
  - 배열의 모든 요소에 대한 콜백의 반환 값을 모아 새로운 배열을 반환한다.
  ```js
  const newArr = [1,2,3].map(e => e * 2) // 2,4,6
  ```
  
- #### `.pop()`
  - 배열의 마지막 요소를 제거하고 해당 요소를 반환한다.
  - 원래의 배열이 변경 된다.

- #### `.shift()`
  - 배열의 첫 번째 요소를 제거하고, 해당 요소를 반환하낟.
  - 원래의 배열이 변경 된다.
  
- #### `.push(e1, e2, e3 ...)`
  - 배열의 마지막에 하나 이상의 요소를 추가하고 배열의 새로운 **길이**를 반환한다.
  - 원래 배열이 변경된다.
  - array를 push 하면 요소에 array가 들어간다. `[1, 2, 3, [4,5,6]]`
  
- #### `unshift(e1, e2, e3 ...)`
  - 배열의 맨 앞에 요소를 추가하고 길이를 반환한다.
  - 원래 배열이 변경 된다.
  
- #### `.splice(idx, numberOfRemoveItem, [newItem])`
  - 배열에 요소를 추가하거나 삭제, 교체 한다.
  - 원본 배열이 변경 된다.
  - 삭제할 수가 1이상 이면 , idx부터 n 개의 요소를 제거하고 그 자리에 새로운 아이템을 넣는다.
  - 추가할 아이템은 생략가능하기 때문에, 특정 인덱스의 요소를 제거하는 것으로 사용할 수도 있다.

- #### `.reduce(callback, initValue)`
  - 요소를 순회하며 해당 요소의 콜백의 반환값을 다음 요소 콜백의 acc로 사용한다.
  - callback 함수의 acc는 누산 값이고, curr는 현재 요소이다.
  - reduce의 두번 째 인자는 초깃값이다.
  ```js
  arr = [100, 200, 300, 400]
  arr.reduce((acc,curr) => {
      return acc + curr
  }, 0)
  ```
  
- #### `.reverse()`
  - 배열의 순서를 뒤집는다. 원본 배열이 변경된다.

## Object API

- #### `Object.assign(target, source1, source2 ...)`
  - 열거할 수 있는 하나 이상의 출처 객체로부터 대상 객체로 속성을 복사한다.
  - target 객체를 반환한다.
  - target 객체가 바뀐다. 새로운 객체를 만들고 싶다면 target에 빈 객체`{}`를 주면 된다.
  ```js
  source = {a :1, b: 2}
  source2 ={c: 3, b: 9}
  target = {d :5, e: 6}
  returnedTarget = Object.assign(target, source, source2)
  
  console.log(target) // {d: 5, e: 6, a: 1, b: 9, c: 3}
  ```
  key의 값이 같다면 더 뒤에오는 인수의 값으로 덮어씌어 진다.
    
- #### `Object.keys(obj)`
  - 지정된 객체의 속성 이름(key)을 배열로 반환한다.

## 🧐 prototype
```js
User.prototype.getFullName = function() {
  return `${this.firstName} ${this.lastName}`
}

const taejunKim = new User('Taejun', 'Kim')
const heropy = new User('HEROPY', 'Park')

console.log(taejunKim.getFullName())
console.log(heropy.getFullName())
console.log(taejunKim.getFullName === heropy.getFullName) // true
```
하나의 함수를 여러 동일한 타입의 객체들이 공유한다.    
실제로 예시의 두 객체의 getFullName을 일치 연산자를 이용하여 비교해 보면 true가 출력되는 것을 확인할 수 있다.

![img.png](img.png)

getFullName 함수는 User객체의 속성으로서 존재하지 않고, prototype 속성으로 존재한다.
- 중복 코드의 감소.
- 자원 절약(같은 함수를 여러개 생성하지 않아도 된다.)

## 🧐 Class
ES6 부터는 프로토타입을 Wrapping 해둔 Class를 지원한다.
```js
class User{
  constructor(firstName, lastName){
    this.firstName = firstName
    this.lastName = lastName
  }

  getFullName(){
    return `${this.firstName} ${this.lastName}`
  }
}

const taejunkim = new User('Taejun', 'Kim')
console.log(taejunKim.getFullName())
```
위의 코드는 prototype의 코드와 동일한 역할을 한다.

- ### 👆 extends
  ```js
  class Car{
    constructor(name, wheel){
      this.name = name
      this.wheel = wheel
    }
  
    start(){
      return true
    }
  }
  
  class Bus extends Car{
    constructor(name, wheel, license){
      super(name, wheel)
      this.license = license
    }
  
    start(){
      if(this.license.isValid){
        return true
      }
      throw '무면허'
    }
  }
  ```
  기존에 만들어져있는 클래스를 상속해 기능을 확장할 수 있다.

- ### 👆 get
  함수나 메서드를 호출 할때에는 `method()`와 같이 호출한다. 그런데 class 에서는 속성처럼 사용할 수 있도록 `get`이라는 키워드를 사용한다.
  ```js
  class User{
    constructor(firstName, lastName){
      this.firstName = firstName
      this.lastName = lastName
    }
  
    get getFullName(){
      return `${this.firstName} ${this.lastName}`
    }
  }
  
  const taejunkim = new User('Taejun', 'Kim')
  console.log(taejunKim.getFullName)
  ```

- ### 👆 static
  ```js
  class User{
   
    ...
  
    static div(fullName) {
        const names = this.fullName.split(' ')
        return {
            firstName: names[0],
            lastName: names[1]
        } 
    }
  }
  
  console.log(User.div('Taejun Kim'))
  ```
  인스턴스를 생성하지 않고 사용이 가능한 정적 메서드, 속성을 생성한다.

## 🧐 etc ..

- ### 👆 전개 연산자
  ```js
  const arr1 = [1, 2, 3]
  const arr2 = [4, 5, 6] 
  
  const arrWrap = [arr1, arr2] // [[1,2,3], [4,5,6]]
  const arrWrap2 = [...arr1, ...arr2] //[1,2,3,4,5,6]
  ```
  즉, `arr1.concat(arr2)` 와 같은 기능을 한다.    
  전개 연산자는 객체에서도 마찬가지로 사용할 수 있고, 함수의 인수로 전달할 때도 사용할 수 있다.

- ### 👆 구조 분해 할당
  ```js
  const arr1 = [1,2,3,4]
  
  const [x, , y, z, t = 20] = arr1
  console.log(x) // 1
  console.log(y) // 3
  console.log(z) // 4
  console.log(t) // 20
  ```
  - 비워두면 건너 뛴다.
  - `= value`를 사용하여 할당 될 값이 없을때 값을 지정할 수 있다.
  - arr1의 요소가 4개이고 할당받을 변수가 [x,y]만 존재한다면 arr1의 앞에서 두개의 요소만 할당되고, 나머지는 할당되지 않는다.

  ```js
  const objA = {
      a: 1,
      b: 2,
      c: 3
  }
  
  const {a, b:myKey} = objA
  console.log(a)
  console.log(myKey)
  ```
  - 객체도 마찬가지로 가져올 수 있고, 다만 키의 이름이 같아야 한다.
  - `:name` 으로 사용할 때의 이름을 지정할 수 있다. 기존의 key 값은 사용할 수 없게 된다.

- ### 👆 깊은 복사
  위에서 보았던 `Object.assign`은 얕은 복사이다. 얕은 복사는 참조되고 있는 객체가 변경되면 함께 변경된다는 점에서 주의가 필요하다.   
  이를 해결하는 것이 깊은 복사이다. javascript에서는 lodash 라이브러리의 `_.cloneDeep()`을 이용하여 간단하게 깊은 복사를 실행할 수 있다.
  
  ```js
  arrOrigin = [1,2,3]
  arrCopy = _.cloneDeep(arrOrigin)
  
  arrOrigin[0] = 10
  console.log(arrCopy[0]) // 1
  ```

## 🧐 DOM API
> Document Object Model, Application Programming Interface. HTML을 제어하는 명령들.

- ### 👆 요소 찾기
  ```js
  let boxEl = document.querySelector('.box'); // 해당되는 요소중 가장 첫 번째 요소
  let boxEls = document.querySelectorAll('.box');
  let boxEl = document.getElementsByxxx(${elm}});
  ```

- ### 👆 부모 형제 탐색
  ```js
  elm.parentNode // 부모
  elm.nextElementSibling // 다음 형제
  ```

- ### 👆 속성값 얻기
  ```js
  elm.id
  elm.className
  elm.getAttribute('attrName')
  elm.setAttribute('placeholder')
  ```

- ### 👆 classList
  ```js
  boxEl.classList.add('active'); // class 추가.
  
  let isContains = boxEl.classList.contains('active'); // 포함 여부.
  
  boxEl.classList.remove('active'); // class 제거.
  ```

- ### 👆 addEventListener('event', handler)
  ```js
  addEventListener('event', function(){
      ...
  });
  ```
  - Event 상황에 실행되는 함수를 **Handler** 라고 한다.
  - 3번째 인수로 `{ capture: true }`를 추가해서 DOM 트리에서 내려올 때 이벤트 캡쳐링이 발생한다.

- ### 👆 .removeEventListener('event', handler)
  ```js
  btn.removeEventListener('click', handler)
  ```

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


## 🧐 비동기
javascript에서 함수의 실행은 비동기이다. 하지만 개발 상황에서는 함수가 순차적으로 실행됨이 보장되어야 할 때가 있는데.
이 때 callback 을 이용할 수 있다.
```js
function a(callback){
    ...
}
function b(callback){
    ...
}
function c(callback){
    ...
}

a(function (){
    b(function (){
        c(function () {
           ... 
        })
    })
})
```
하지만 3개의 함수만 실행하더라도 코드가 매우 복잡해진다. 실제로는 훨씬 많은 함수를 실행 해야할 것이고,   
이러한 방법은 콜백 지옥을 만든다.

- ### 👆 Promise
  콜백 지옥을 벗아나기 위한 방법이 Promise 이다.
  ```js
  function a(){
    return new Promise(function (resolve){
      setTimeout(function(){
        console.log('A')
        resolve("B 실행~")
      }, 1000)
    })
  }
  
  function b(res){
    return new Promise(function(resolve){
      setTimeout(function(){
        console.log('B')
        console.log(res)
        resolve()
      }, 1000)
    })
  }
  
  function c(){
    console.log('C')
  }
  
  a()
    .then(b)
    .then(c)
  ```
  resolve() 가 실행되는 위치에서 다음 함수가 실행되고,
  resolve()로 전달된 데이터는 다음 실행되는 함수에서 매개변수로 받아 사용할 수 있다.

- ### 👆 예외 처리
  ```js
  const url = 'https://www.omdbapi.com/?i=tt3896198&apikey=7035c60c111'
  axios(url)
    .then(res => {
      console.log(res)
    })
    .catch(err => {
      console.log('error!!!!!!!', err)
    })
    .finally(() => {
      console.log('완료')
    })
  ```
  axios 라이브러리를 사용하여 API 요청을 보냈다. 응답이 성공하면 then 부분이 실행되고, 실패하면 catch 블럭이 실행된다.    
  마지막으로 응답의 성공 여부와 상관 없이 finally 블럭이 실행된다.

- ### 👆 async/await
  ```js
  async function template(){
    const res = await axios.get(url)
    await a(res)
    await b()
    await c()
    ...
  }
  ```
  - async로 정의된 함수 안에서 await 키워드를 사용하여 함수가 종료될 때까지 기다리도록 만들 수 있다.   
  - await 키워드를 사용하면 함수의 결과 값을 할당 연산자를 이용하여 반환 받을 수 있다.



