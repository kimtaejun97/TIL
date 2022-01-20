
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


### 👆 반복문과 조건문
```js
const app = Vue.createApp({
  data() {
    return {
      myName: ' kim',
      showMessage: false,
      fruits: ['Apple', 'Banana', 'Cherry']
    }
  },
  methods: {
    showMyName() {
      this.showMessage = true
    }
  }
})
app.mount('#bigave')
```

```html
<h1 v-if="showMessage">{{ myName }}</h1>

<button v-on:click="showMyName">
      Show Name!!
</button>


<ul>
  <li v-for="fruit in fruits"> {{ fruit }} </li>
</ul>
```
- 조건문은 `v-if` 디렉티브를 사용할 수 있다. 값으로는 boolean 값이 들어온다.
- 반복문은 `v-for` 디렉티브를 사용한다. 배열 등 반복 가능한 데이터를 순회하며 HTML 요소를 생성할 수 있다.

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