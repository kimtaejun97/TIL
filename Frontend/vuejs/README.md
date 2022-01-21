
## 🧐 시작하기
> https://v3.ko.vuejs.org/guide/introduction.html

```html
<script src="https://unpkg.com/vue@next"></script>
```

### 👆 선언적 렌더링과 입력 핸들링

```js
const app = Vue.createApp({
  data() {
    return {
      counter: 100,
      tooltip: "wheel up and down ~~ "
    }
  },
  methods: {
    increase() {
      this.counter +=1
    }
  }
})
app.mount('#bigave')
```

```html
<div id="bigave">
    <h1 @wheel = "increase" :title="tooltip"> {{ counter }}</h1>
</div>
```

- `Vue.createApp`: Vue app을 생성한다. 이렇게 생성된 앱은 특정 html 요소에 mount 되어 사용된다.
- `mount`: html 요소에 vue app을 연결한다. 해당 영역에서만 vue 관련 문법을 사용할 수 있다.
- `data()`: 데이터 옵션, `{{ }}` 처럼 텍스트 보간으로 사용할 수도 있고, `v-bind:attr` 과 같이 속성 값으로 바인드하여 사용할 수도 있다. v-bind는 생략 가능하다.
- `methods()`: 메서드 옵션, `v-on:event` 로 메서드를 언제 실행할 것인지 지정 가능하다. `@event` 로 축약 가능하다.


## 🧐 라이프사이클 
![img_2.png](img_2.png)
### 👆 라이프사이클 훅
```js
const app = Vue.createApp({
    data() {
        return {
            message: 'Hello',
        }
    },
    // 라이프사이클 훅
    beforeCreate() {
        console.log(this.message) // 생성되기 전이라 undefined
    },
    created() {
        // vue app이 생성된 직후
        console.log(this.message)
    },
    beforeMount() {
        console.log(document.querySelector('button')) // html 구조와 연결되기 전이라 null
    },
    mounted() {
        // html 구조에 연결된 후
        console.log(document.querySelector('button')) // <button> reverse </button>
    },
})
app.mount('#bigave')
```
- `beforeCreate`: app이 생성되기 전. data를 아직 가져올 수 없는 상태.
- `created`: vue app이 생성된 직후, data에 접근할 수 있다.
- `beforeMount`: html 구조와 연결되기 전. html 요소를 아직 알지 못한다.
- `mounted`: html 구조와 연결된 후. html 요소에 접근할 수 있다.


## 🧐 템플릿 문법
- ### 👆 보간법
    - 이중 중괄호 구문 `{{ }}`
    - `v-once`: 한 번만 데이터를 가져오고, 반응형 연결을 끊는다. 때문에 데이터의 갱신이 이루어져도 반영되지 않는다.(메모리 절약)
        > 데이터의 갱신은 이루어짐에 유의.
    - `v-html=""`: html이 작성된 데이터를 html 구조로 변환해준다.
        > 웹 사이트에서 HTML을 동적으로 렌더링 하는 것은 XSS에 취약하므로 주의가 필요하다.

- ### 👆 속성
    - `v-bind:${attr}`: HTML 속성을 넣어준다.
        ```js
        myStyle: 'color: red; font-size: 100px;',
        ```
        ```html
        <h1 :style="myStyle">MyStyle~</h1>
        ```
        v-bind는 생략할 수 있다.
    
- ### 👆 동적 전달인자.
    `[ ]`를 사용하여 동적으로 인자를 전달할 수 있다.
    ```js
    data() {
        return {
            abc: 'click'
        }
    }
    ```
    ```html
    <h1 @[abc]="method">{{ counter }}</h1> // 클릭할때 method 실행.
    ```
        
## 🧐 Computed 속성
```js
computed: {
    //getter
    newMessage() {
      return this.message + '!!'
    }
    
  }
```
```html
<div>{{ newMessage }}</div>
```
- 기존의 데이터를 기반으로 계산한 값을 반환하기 때문에, 기존 데이터가 변경되면 함께 변경된다. (유지 보수)
- 한번 계산된 값을 캐싱하고 있기 때문에 여러번 사용할 때 비용이 절약된다.

예를 들어 메서드로도 동일한 기능을 구현할 수 있다.
```js
methods: {
    reversedMessage() {
        return this.message + '!!'
    }
}
```
위의 메서드도 동일한 기능을 가진다. 하지만 메서드가 여러번 호출된다면, 호출 될 때마다 연산을 진행하게 된다.    
이런 간단한 메서드가 아니라 비용이 비싼 연산이라면 많은 부담이 생길 것이다.    
Computed는 캐싱을 이용하여 여러번 호출하더라도 한번만 연산한다는 장점을 가진다.

```js
computedReversedMessage: {
  get() {
    return this.message
                  .split('')
                  .reverse()
                  .join('')
  },
  set(value) {
    console.log(value) //1234
  }
}햐««
```
```html
<button @click="computedReversedMessage = 1234">
    setter
</button>
```
이전에 작성된 코드에서는 기본적인 getter의 기능만을 가진다.   
위의 코드처럼 변경하여 getter와 setter의 기능을 모두 가질 수도 있다. 할당 연산자를 이용하여 값을 전달할 수 있다.



## 🧐 watch
```js
watch: {
    message(newValue, oldValue) {
      console.log(`message 데이터 ${oldValue}에서 ${newValue}로 변경됨.`)

    }
}
```
watch는 해당 값이 변경되는지를 감시하고 있다가 변경되면 실행된다. watch 안에서 대상 값을 다시 변경한다면 무한루프에 빠지게 되니 주의하자.
- 첫 번째 인자로는 변경된 후의 값을 가져올 수 있다.
- 두 번째 인자로는 변경 전의 값을 가져올 수 있다.

## 🧐 클래스와 스타일 바인딩
- ### 👆 :class
    HTML 클래스의 동적 전환.
    ```html
    <h1 :class="{ active: isActive}"
            @click = "isActive = !isActive">Hello world!</h1>
    ```
    - isActive라는 data의 값에 따라 `active` 클래스가 추가 되거나 추가되지 않거나 한다.
    - css를 적용 여부로 응용이 가능하다.
    - 클래스 이름에 대시기호를 넣기 위해서는 `'class-name'`과 같이 사용해야 한다. 아래의 예시에서 함께 본다. 
    
    ```js
    computed: {
        myClasses () {
            return {
                active: isActive,
                'dash-class': isDash,
            }
        }
    }
    ```
    와 같이 작성하고 
    ```html
    <h1 :class="myClasses"
            @click = "isActive = !isActive">Hello world!</h1>
    ```
    로 사용하여도 된다.

- ### 👆 :style
    ```html
    <h1
      :style = "{
        color: myColor,
        fontSize: '16px',
        backgroundColor: 'orange'
      }"
      @click = "myColor = 'blue'">
      Hello Style~
    </h1>
    ```
    - font-size 이지만 vue에서 이를 카멜케이스로 적을 수 있도록 지원해주기 때문에 fontSize와 같이 적어도 된다.(그렇지 않으면 `'font-size'`로 적어야 한다.)
    - 스타일 속성의 값으로 data 값을 사용 가능 하다(myColor와 같이)
    - style을 담은 여러개의 객체 data를 적용하기 위해서는 배열 구문 `[ ]`을 사용할 수 있다.
        ```html
        <h1 :style="[myStyle1, myStyle2]"></h1>
        ```
      

## 🧐 조건부 렌더링
- ### 👆 v-if
    ```html
    <div v-if="showMessage1">message1: {{ myName }}</div>
    <div v-else-if="showMessage2">message2: {{ myName }}</div>
    <div v-else="showMessage3">message3: {{ myName }}</div>
    ```
    - 조건문은 `v-if` 디렉티브를 사용할 수 있다. 값으로는 boolean 값이 들어온다.
    - 일반 적인 프로그래밍과 같이 else if, else 구문을 사용할 수 있다.
    - v-if 는 html 에서 보면 `<!--v-if-->` 라는 주석만을 남기고 요소 자체를 삭제한다. => 전환 비용이 높다.

- ### 👆 v-show
    - `v-if`와 달리 실제로 사라지는 것이 아니라 css style의 `display` 속성을 `none`으로 변경한다.
    - css style 만을 변경하기 때문에 전환 비용이 더 저렴하다.
    - 그러나 필요 없는데도 렌더링을 하기 때문에 초기 렌더링 비용이 높다.



## 🧐 리스트 렌더링 v-for
- ### 👆 배열의 순회
  ```js
  const app = Vue.createApp({
    data() {
      return {
        fruits: ['Apple', 'Banana', 'Cherry']
      }
    }
  })
  app.mount('#bigave')
  ```
  ```html
  <ul>
    <li v-for="fruit in fruits"> {{ fruit }} </li>
  </ul>
  ```
  - 반복문은 `v-for` 디렉티브를 사용한다. 배열 등 반복 가능한 데이터를 순회하며 HTML 요소를 생성할 수 있다.
    - 두번 째 매개변수로 index를 받을 수 있다.

- ### 👆 Object의 순회
  ```js
  user: {
        name: 'kim',
        age: '26',
        email: 'asd@gmail.com'
      }
  ```
  ```html
  <ul>
    <li v-for="(value, key, index) in user">
        <!-- (0): name = kim -->
      ({{ index }}): {{ key }} = {{ value }} 
    </li>
  </ul>
  ```
  - 값만 받을 수도 있지만, key, value, index 를 받는 것이 가능하다.(value, key, index 순서임에 유의)
      
- ### 💡 :key
  v-for 에서 데이터 항목의 순서가 변경되거나 재사용하는 등의 경우 key 속성을 이용하여 vue가 고유한 데이터를 구분할 수 있도록 이를 알려줄 수 있다.   
  v-for를 사용할때에는 항상 명시해주는 것이 좋다(유일한 값으로). key의 값은 항상 기본 타입(문자열이나 숫자 )로 사용한다.
  ```html
  <ul>
    <li v-for="(value, name, index) in user"
        :key="name">
      ({{ index }}): {{ name }} = {{ value }}  
    </li>
  </ul>
  ```
  key는 고유한 값이어야 하기 때문에 대부분의 경우에 배열을 순회하는 경우에도 객체로 묶어내어 사용한다.
  ```js
  fruits: [
    { id: '123', name:'apple'}
            ...
  ]
  ```

- ### 👆 정수 순회
  `v-for` 을 이용하여 정수를 순회할 수 있다.(zero based가 아님에 주의. 1부터 시작.)
  ```html
  <!--  12345678910-->
  <span v-for="n in 10">{{ n }}</span> 
  ```

## 🧐 이벤트 핸들링

- ### 👆 메소드 이벤트 핸들러
  - `@event = 'method'` 와 같이 메서드를 사용할 수 있다.
  - 인수를 전달하고 싶다면 `@event = 'method(v)'`와 같이 사용할 수 있다.
  - 여러개의 메서드를 실행하고 싶다면 `@event = 'method1(), method2()'` 또는 vue3 부터는 `'method1(); method2()'`와 같이 실행할 수 있다.
  
- ### 👆 이벤트 수식어
  - `.prevent`
    ```html
    <a href="https://11st.co.kr" @click.prevent></a>
    ```
    > preventDefault()를 실행한 것과 동일하다. 기본 동작을 막는다.
  - `.stop`
    > stopPropagation, 이벤트 전파를 멈춘다.
  - `.capture`
    > addEventListener 의 capture 옵션, 캡처링 시점에 이벤트를 발생시킨다.
  - `.passive`
    > - addEventListener 의 passive 옵션, 이벤트 리스너의 로직과, 화면의 렌더링을 분리한다.
    > - 기존에는 이벤트를 처리한 후에 렌더링을 하기 때문에 이벤트 로직이 긴 시간이 소요된다면 화면의 렌더링도 그만큼 늦어지게 된다.
  - `.once`
    > 이벤트가 `한 번만` 동작하도록 한다.
  - `.self`
    > - 부모요소에게 click 이벤트가 부여되어 있을 때, 자식 요소를 클릭하면 currentTarget은 부모가 되고, target은 자식 요소가 되며 이벤트가 발생한다.
    > - 즉, 자식에게 직접 이벤트가 있지 않아도 상위 요소에게 이벤트가 있으면 자식을 클릭하더라도 이벤트가 발생한다. 
    > - self 수식어를 사용하면 실제로 이벤트가 붙어 있는 영역을 클릭했을 때만 이벤트가 발생한다.
  
  이벤트 수식어는 `@click.stop.prevent` 처럼 체이닝이 가능하다.

- ### 👆 키 수식어
  - #### 키 명령어
    ```html
    <input @keyup.enter = :submit></input>
    ```
    이 외에 tab, delete, esc, space, up, down, left, right, a, b, c, ... 등 다양한 키 명령어가 존재한다.
  
  - #### 시스템 수식어 키 목록
    - ctrl, alt, shift, meta(command) ...
    - 해당 수식어 키가 눌러진 경우에만 이벤트 리스너를 트리거 할 수 있다 (ex> .shift.a)
  
  - ##### `.extract` 수식어
    `@click.ctrl` 이라는 트리거가 정의되었다고 할 때, 기본적으로는 alt, shift 등의 키가 함께 눌려도 ctrl 키만 눌려있다면 핸들러는 실행된다.    
    하지만 `@click.ctrl.extract` 처럼 `.extract` 수식어를 사용한다면 ctrl 키만 눌려져 있을 때만 실행된다.
    
## 🧐 폼 입력 바인딩

- ### 👆 v-model
  - input, textarea, select 요소들에 `양방향 데이터 바인딩`을 생성한다.
    ```html
    <input type="text" v-model="message">
    <input type="checkbox" v-model="checked"> <!-- true, false -->
    ```
    input 입력창에 데이터를 수정,입력 하면 바인드된 message 데이터 또한 변경된다. 즉 value 속성의 값과 바인드 된다.
    
  - checkbox와 배열 데이터를 연결하면 어떤 것을 체크 했는지도 표기할 수 있다.
    ```js
    data() {
        return {
            checkedNames: []
        }
    }
    ```
    ```html
    <input type="checkbox" v-model="checkedNames" value="apple"> 
    <input type="checkbox" v-model="checkedNames" value="banana">
    <input type="checkbox" v-model="checkedNames" value="cherry">
    ```
    checkebox를 체크하면 value에 있는 값이 바인드 된 checkedNames 배열에 추가된다.
    
  - v-model은 한글 입력에 대해 한 글자가 완성될 때 까지 반영이 `지연되는 이슈`가 있다. 이를 해결 하기 위해 아래와 같이 바인딩할 수 있다.
    ```html
    <input type="text" :value="message" @input="message = $event.target.value">
    ```
    위의 코드는 v-model을 풀어 쓴 코드이다. `:value`로 message data에서 단방향 바인딩을, `@input=""` 으로 반대 방향도 바인딩 한다.
  
  - #### 수식어
    - `.lazy`
      ```html
      <input type="text" v-model.lazy="message">
      ```
      - 바로 반영되는 것이 아니라. 데이터의 변경이 끝났을 때(엔터, 언포커싱 등..) 반영한다.
      - `@change` 와 같다.
    - `.number`
      - 입력받은 데이터는 1234와 같이 숫자를 넣어도 String 형태를 가진다. `.number`는 이를 숫자 데이터의 타입으로 입력할 수 있도록 해준다.
    - `.trim`
      - 입력된 문자의 앞 뒤 공백을 제거해준다.


  
## 🧐 컴포넌트
```js
app.component('my-btn', {
  template:
`<div
  style="
    display: inline-block;
    background-color: royalblue;
    color: white;
    padding: 10px;
  "
  @click="log">
  <slot></slot>
</div>`,
  methods: {
    log() {
      console.log('Comp!');
    }
  }
})
```
- 템플릿 영역에서는 컴포넌트의 메서드를 사용할 때 this 를 생략할 수 있다(this.log -> log)

```html
<my-btn>Hello Component!!</my-btn>
```

- ### 👆 props
  컴포넌트에 등록할 수 있는 커스텀 속성.
  ```js
  app.component('my-btn', {
    template:
  `<div
    :style="{
      display: 'inline-block',
      backgroundColor: myColor,
      color: 'white',
      padding: '10px',
    }"
    @click="log">
    <slot></slot>
  </div>`,
    props: {
      myColor: {
        type: String,
        required: true,
        default: 'royalblue',
      },
    }
  })
  ```
  ```html
  <my-btn my-color="red">Hello Component!!</my-btn>
  ```
  - `v-bind` 를 사용하기 위해 `:style`로 변경.
  - js 문법이 되기 때문에 문법에 맞춰 변경.
  - js는 카멜케이스를 쓰지만 html에서는 케밥 케이스로 써야함에 유의하자.

- ### 👆 Emits
  컴포넌트 안(하위)에서 밖으로(상위) 이벤트를 알림.
  ```js
  // 컴포넌트 methods
  emits: ['customEvent'],
  methods: {
    log() {
      this.$emit('customEvent', 'My CustomEvent!')
    }
  }
  ```
  ```js
  // 부모 methods:
  hello(event) {
      console.log(event)
  },
  ``` 
  ```html
  <my-btn @custom-event="hello" >WOW~</my-btn>
  ```
  - emits 는 실제적인 기능은 아니지만 어떤 emit 들이 있는지 명시해주는 역할을 한다.
  - 사실상 @click 에 바로 `"$emit( ... )"` 넣어준 것과 동일하다.
  - `emit` 의 첫 번째 인자는 발생되는 이벤트, 두번 째 인자는 같이 전달할 데이터 이다.(event 객체에 담긴다.)
  
- ### 👆 v-model 사용하기
  ```js
  app.component('my-btn', {
    template:
  `<div
    @click="emit">
    {{ modelValue }}
  </div>`,
    props: {
      modelValue: {
        type: String,
      }
    },
    emits: ['update:modelValue'],
    methods: {
      emit() {
        this.$emit('update:modelValue', 'My CustomEvent!')
      },
    }
  })
  ```
  
  ```html
  <my-btn v-model="message"></my-btn>
  ```
  - `modelValue` 라는 값은 변경할 수 없다. 정해진 것.
  - 마찬가지로 `update:modelValue` 또한 정해진 것이다.