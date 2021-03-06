
## π§ μμνκΈ°
> https://v3.ko.vuejs.org/guide/introduction.html

```html
<script src="https://unpkg.com/vue@next"></script>
```

### π μ μΈμ  λ λλ§κ³Ό μλ ₯ νΈλ€λ§

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

- `Vue.createApp`: Vue appμ μμ±νλ€. μ΄λ κ² μμ±λ μ±μ νΉμ  html μμμ mount λμ΄ μ¬μ©λλ€.
- `mount`: html μμμ vue appμ μ°κ²°νλ€. ν΄λΉ μμ­μμλ§ vue κ΄λ ¨ λ¬Έλ²μ μ¬μ©ν  μ μλ€.
- `data()`: λ°μ΄ν° μ΅μ, `{{ }}` μ²λΌ νμ€νΈ λ³΄κ°μΌλ‘ μ¬μ©ν  μλ μκ³ , `v-bind:attr` κ³Ό κ°μ΄ μμ± κ°μΌλ‘ λ°μΈλνμ¬ μ¬μ©ν  μλ μλ€. v-bindλ μλ΅ κ°λ₯νλ€.
- `methods()`: λ©μλ μ΅μ, `v-on:event` λ‘ λ©μλλ₯Ό μΈμ  μ€νν  κ²μΈμ§ μ§μ  κ°λ₯νλ€. `@event` λ‘ μΆμ½ κ°λ₯νλ€.


## π§ λΌμ΄νμ¬μ΄ν΄ 
![img_2.png](img_2.png)
### π λΌμ΄νμ¬μ΄ν΄ ν
```js
const app = Vue.createApp({
    data() {
        return {
            message: 'Hello',
        }
    },
    // λΌμ΄νμ¬μ΄ν΄ ν
    beforeCreate() {
        console.log(this.message) // μμ±λκΈ° μ μ΄λΌ undefined
    },
    created() {
        // vue appμ΄ μμ±λ μ§ν
        console.log(this.message)
    },
    beforeMount() {
        console.log(document.querySelector('button')) // html κ΅¬μ‘°μ μ°κ²°λκΈ° μ μ΄λΌ null
    },
    mounted() {
        // html κ΅¬μ‘°μ μ°κ²°λ ν
        console.log(document.querySelector('button')) // <button> reverse </button>
    },
})
app.mount('#bigave')
```
- `beforeCreate`: appμ΄ μμ±λκΈ° μ . dataλ₯Ό μμ§ κ°μ Έμ¬ μ μλ μν.
- `created`: vue appμ΄ μμ±λ μ§ν, dataμ μ κ·Όν  μ μλ€.
- `beforeMount`: html κ΅¬μ‘°μ μ°κ²°λκΈ° μ . html μμλ₯Ό μμ§ μμ§ λͺ»νλ€.
- `mounted`: html κ΅¬μ‘°μ μ°κ²°λ ν. html μμμ μ κ·Όν  μ μλ€.


## π§ ννλ¦Ώ λ¬Έλ²
- ### π λ³΄κ°λ²
    - μ΄μ€ μ€κ΄νΈ κ΅¬λ¬Έ `{{ }}`
    - `v-once`: ν λ²λ§ λ°μ΄ν°λ₯Ό κ°μ Έμ€κ³ , λ°μν μ°κ²°μ λλλ€. λλ¬Έμ λ°μ΄ν°μ κ°±μ μ΄ μ΄λ£¨μ΄μ Έλ λ°μλμ§ μλλ€.(λ©λͺ¨λ¦¬ μ μ½)
        > λ°μ΄ν°μ κ°±μ μ μ΄λ£¨μ΄μ§μ μ μ.
    - `v-html=""`: htmlμ΄ μμ±λ λ°μ΄ν°λ₯Ό html κ΅¬μ‘°λ‘ λ³νν΄μ€λ€.
        > μΉ μ¬μ΄νΈμμ HTMLμ λμ μΌλ‘ λ λλ§ νλ κ²μ XSSμ μ·¨μ½νλ―λ‘ μ£Όμκ° νμνλ€.

- ### π μμ±
    - `v-bind:${attr}`: HTML μμ±μ λ£μ΄μ€λ€.
        ```js
        myStyle: 'color: red; font-size: 100px;',
        ```
        ```html
        <h1 :style="myStyle">MyStyle~</h1>
        ```
        v-bindλ μλ΅ν  μ μλ€.
    
- ### π λμ  μ λ¬μΈμ.
    `[ ]`λ₯Ό μ¬μ©νμ¬ λμ μΌλ‘ μΈμλ₯Ό μ λ¬ν  μ μλ€.
    ```js
    data() {
        return {
            abc: 'click'
        }
    }
    ```
    ```html
    <h1 @[abc]="method">{{ counter }}</h1> // ν΄λ¦­ν λ method μ€ν.
    ```
        
## π§ Computed μμ±
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
- κΈ°μ‘΄μ λ°μ΄ν°λ₯Ό κΈ°λ°μΌλ‘ κ³μ°ν κ°μ λ°ννκΈ° λλ¬Έμ, κΈ°μ‘΄ λ°μ΄ν°κ° λ³κ²½λλ©΄ ν¨κ» λ³κ²½λλ€. (μ μ§ λ³΄μ)
- νλ² κ³μ°λ κ°μ μΊμ±νκ³  μκΈ° λλ¬Έμ μ¬λ¬λ² μ¬μ©ν  λ λΉμ©μ΄ μ μ½λλ€.

μλ₯Ό λ€μ΄ λ©μλλ‘λ λμΌν κΈ°λ₯μ κ΅¬νν  μ μλ€.
```js
methods: {
    reversedMessage() {
        return this.message + '!!'
    }
}
```
μμ λ©μλλ λμΌν κΈ°λ₯μ κ°μ§λ€. νμ§λ§ λ©μλκ° μ¬λ¬λ² νΈμΆλλ€λ©΄, νΈμΆ λ  λλ§λ€ μ°μ°μ μ§ννκ² λλ€.    
μ΄λ° κ°λ¨ν λ©μλκ° μλλΌ λΉμ©μ΄ λΉμΌ μ°μ°μ΄λΌλ©΄ λ§μ λΆλ΄μ΄ μκΈΈ κ²μ΄λ€.    
Computedλ μΊμ±μ μ΄μ©νμ¬ μ¬λ¬λ² νΈμΆνλλΌλ νλ²λ§ μ°μ°νλ€λ μ₯μ μ κ°μ§λ€.

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
}νΒ«Β«
```
```html
<button @click="computedReversedMessage = 1234">
    setter
</button>
```
μ΄μ μ μμ±λ μ½λμμλ κΈ°λ³Έμ μΈ getterμ κΈ°λ₯λ§μ κ°μ§λ€.   
μμ μ½λμ²λΌ λ³κ²½νμ¬ getterμ setterμ κΈ°λ₯μ λͺ¨λ κ°μ§ μλ μλ€. ν λΉ μ°μ°μλ₯Ό μ΄μ©νμ¬ κ°μ μ λ¬ν  μ μλ€.



## π§ watch
```js
watch: {
    message(newValue, oldValue) {
      console.log(`message λ°μ΄ν° ${oldValue}μμ ${newValue}λ‘ λ³κ²½λ¨.`)

    }
}
```
watchλ ν΄λΉ κ°μ΄ λ³κ²½λλμ§λ₯Ό κ°μνκ³  μλ€κ° λ³κ²½λλ©΄ μ€νλλ€. watch μμμ λμ κ°μ λ€μ λ³κ²½νλ€λ©΄ λ¬΄νλ£¨νμ λΉ μ§κ² λλ μ£Όμνμ.
- μ²« λ²μ§Έ μΈμλ‘λ λ³κ²½λ νμ κ°μ κ°μ Έμ¬ μ μλ€.
- λ λ²μ§Έ μΈμλ‘λ λ³κ²½ μ μ κ°μ κ°μ Έμ¬ μ μλ€.

## π§ ν΄λμ€μ μ€νμΌ λ°μΈλ©
- ### π :class
    HTML ν΄λμ€μ λμ  μ ν.
    ```html
    <h1 :class="{ active: isActive}"
            @click = "isActive = !isActive">Hello world!</h1>
    ```
    - isActiveλΌλ dataμ κ°μ λ°λΌ `active` ν΄λμ€κ° μΆκ° λκ±°λ μΆκ°λμ§ μκ±°λ νλ€.
    - cssλ₯Ό μ μ© μ¬λΆλ‘ μμ©μ΄ κ°λ₯νλ€.
    - ν΄λμ€ μ΄λ¦μ λμκΈ°νΈλ₯Ό λ£κΈ° μν΄μλ `'class-name'`κ³Ό κ°μ΄ μ¬μ©ν΄μΌ νλ€. μλμ μμμμ ν¨κ» λ³Έλ€. 
    
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
    μ κ°μ΄ μμ±νκ³  
    ```html
    <h1 :class="myClasses"
            @click = "isActive = !isActive">Hello world!</h1>
    ```
    λ‘ μ¬μ©νμ¬λ λλ€.

- ### π :style
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
    - font-size μ΄μ§λ§ vueμμ μ΄λ₯Ό μΉ΄λ©μΌμ΄μ€λ‘ μ μ μ μλλ‘ μ§μν΄μ£ΌκΈ° λλ¬Έμ fontSizeμ κ°μ΄ μ μ΄λ λλ€.(κ·Έλ μ§ μμΌλ©΄ `'font-size'`λ‘ μ μ΄μΌ νλ€.)
    - μ€νμΌ μμ±μ κ°μΌλ‘ data κ°μ μ¬μ© κ°λ₯ νλ€(myColorμ κ°μ΄)
    - styleμ λ΄μ μ¬λ¬κ°μ κ°μ²΄ dataλ₯Ό μ μ©νκΈ° μν΄μλ λ°°μ΄ κ΅¬λ¬Έ `[ ]`μ μ¬μ©ν  μ μλ€.
        ```html
        <h1 :style="[myStyle1, myStyle2]"></h1>
        ```
      

## π§ μ‘°κ±΄λΆ λ λλ§
- ### π v-if
    ```html
    <div v-if="showMessage1">message1: {{ myName }}</div>
    <div v-else-if="showMessage2">message2: {{ myName }}</div>
    <div v-else="showMessage3">message3: {{ myName }}</div>
    ```
    - μ‘°κ±΄λ¬Έμ `v-if` λλ ν°λΈλ₯Ό μ¬μ©ν  μ μλ€. κ°μΌλ‘λ boolean κ°μ΄ λ€μ΄μ¨λ€.
    - μΌλ° μ μΈ νλ‘κ·Έλλ°κ³Ό κ°μ΄ else if, else κ΅¬λ¬Έμ μ¬μ©ν  μ μλ€.
    - v-if λ html μμ λ³΄λ©΄ `<!--v-if-->` λΌλ μ£Όμλ§μ λ¨κΈ°κ³  μμ μμ²΄λ₯Ό μ­μ νλ€. => μ ν λΉμ©μ΄ λλ€.

- ### π v-show
    - `v-if`μ λ¬λ¦¬ μ€μ λ‘ μ¬λΌμ§λ κ²μ΄ μλλΌ css styleμ `display` μμ±μ `none`μΌλ‘ λ³κ²½νλ€.
    - css style λ§μ λ³κ²½νκΈ° λλ¬Έμ μ ν λΉμ©μ΄ λ μ λ ΄νλ€.
    - κ·Έλ¬λ νμ μλλ°λ λ λλ§μ νκΈ° λλ¬Έμ μ΄κΈ° λ λλ§ λΉμ©μ΄ λλ€.



## π§ λ¦¬μ€νΈ λ λλ§ v-for
- ### π λ°°μ΄μ μν
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
  - λ°λ³΅λ¬Έμ `v-for` λλ ν°λΈλ₯Ό μ¬μ©νλ€. λ°°μ΄ λ± λ°λ³΅ κ°λ₯ν λ°μ΄ν°λ₯Ό μννλ©° HTML μμλ₯Ό μμ±ν  μ μλ€.
    - λλ² μ§Έ λ§€κ°λ³μλ‘ indexλ₯Ό λ°μ μ μλ€.

- ### π Objectμ μν
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
  - κ°λ§ λ°μ μλ μμ§λ§, key, value, index λ₯Ό λ°λ κ²μ΄ κ°λ₯νλ€.(value, key, index μμμμ μ μ)
      
- ### π‘ :key
  v-for μμ λ°μ΄ν° ν­λͺ©μ μμκ° λ³κ²½λκ±°λ μ¬μ¬μ©νλ λ±μ κ²½μ° key μμ±μ μ΄μ©νμ¬ vueκ° κ³ μ ν λ°μ΄ν°λ₯Ό κ΅¬λΆν  μ μλλ‘ μ΄λ₯Ό μλ €μ€ μ μλ€.   
  v-forλ₯Ό μ¬μ©ν λμλ ν­μ λͺμν΄μ£Όλ κ²μ΄ μ’λ€(μ μΌν κ°μΌλ‘). keyμ κ°μ ν­μ κΈ°λ³Έ νμ(λ¬Έμμ΄μ΄λ μ«μ )λ‘ μ¬μ©νλ€.
  ```html
  <ul>
    <li v-for="(value, name, index) in user"
        :key="name">
      ({{ index }}): {{ name }} = {{ value }}  
    </li>
  </ul>
  ```
  keyλ κ³ μ ν κ°μ΄μ΄μΌ νκΈ° λλ¬Έμ λλΆλΆμ κ²½μ°μ λ°°μ΄μ μννλ κ²½μ°μλ κ°μ²΄λ‘ λ¬Άμ΄λ΄μ΄ μ¬μ©νλ€.
  ```js
  fruits: [
    { id: '123', name:'apple'}
            ...
  ]
  ```

- ### π μ μ μν
  `v-for` μ μ΄μ©νμ¬ μ μλ₯Ό μνν  μ μλ€.(zero basedκ° μλμ μ£Όμ. 1λΆν° μμ.)
  ```html
  <!--  12345678910-->
  <span v-for="n in 10">{{ n }}</span> 
  ```

## π§ μ΄λ²€νΈ νΈλ€λ§

- ### π λ©μλ μ΄λ²€νΈ νΈλ€λ¬
  - `@event = 'method'` μ κ°μ΄ λ©μλλ₯Ό μ¬μ©ν  μ μλ€.
  - μΈμλ₯Ό μ λ¬νκ³  μΆλ€λ©΄ `@event = 'method(v)'`μ κ°μ΄ μ¬μ©ν  μ μλ€.
  - μ¬λ¬κ°μ λ©μλλ₯Ό μ€ννκ³  μΆλ€λ©΄ `@event = 'method1(), method2()'` λλ vue3 λΆν°λ `'method1(); method2()'`μ κ°μ΄ μ€νν  μ μλ€.
  
- ### π μ΄λ²€νΈ μμμ΄
  - `.prevent`
    ```html
    <a href="https://11st.co.kr" @click.prevent></a>
    ```
    > preventDefault()λ₯Ό μ€νν κ²κ³Ό λμΌνλ€. κΈ°λ³Έ λμμ λ§λλ€.
  - `.stop`
    > stopPropagation, μ΄λ²€νΈ μ νλ₯Ό λ©μΆλ€.
  - `.capture`
    > addEventListener μ capture μ΅μ, μΊ‘μ²λ§ μμ μ μ΄λ²€νΈλ₯Ό λ°μμν¨λ€.
  - `.passive`
    > - addEventListener μ passive μ΅μ, μ΄λ²€νΈ λ¦¬μ€λμ λ‘μ§κ³Ό, νλ©΄μ λ λλ§μ λΆλ¦¬νλ€.
    > - κΈ°μ‘΄μλ μ΄λ²€νΈλ₯Ό μ²λ¦¬ν νμ λ λλ§μ νκΈ° λλ¬Έμ μ΄λ²€νΈ λ‘μ§μ΄ κΈ΄ μκ°μ΄ μμλλ€λ©΄ νλ©΄μ λ λλ§λ κ·Έλ§νΌ λ¦μ΄μ§κ² λλ€.
  - `.once`
    > μ΄λ²€νΈκ° `ν λ²λ§` λμνλλ‘ νλ€.
  - `.self`
    > - λΆλͺ¨μμμκ² click μ΄λ²€νΈκ° λΆμ¬λμ΄ μμ λ, μμ μμλ₯Ό ν΄λ¦­νλ©΄ currentTargetμ λΆλͺ¨κ° λκ³ , targetμ μμ μμκ° λλ©° μ΄λ²€νΈκ° λ°μνλ€.
    > - μ¦, μμμκ² μ§μ  μ΄λ²€νΈκ° μμ§ μμλ μμ μμμκ² μ΄λ²€νΈκ° μμΌλ©΄ μμμ ν΄λ¦­νλλΌλ μ΄λ²€νΈκ° λ°μνλ€. 
    > - self μμμ΄λ₯Ό μ¬μ©νλ©΄ μ€μ λ‘ μ΄λ²€νΈκ° λΆμ΄ μλ μμ­μ ν΄λ¦­νμ λλ§ μ΄λ²€νΈκ° λ°μνλ€.
  
  μ΄λ²€νΈ μμμ΄λ `@click.stop.prevent` μ²λΌ μ²΄μ΄λμ΄ κ°λ₯νλ€.

- ### π ν€ μμμ΄
  - #### ν€ λͺλ Ήμ΄
    ```html
    <input @keyup.enter = :submit></input>
    ```
    μ΄ μΈμ tab, delete, esc, space, up, down, left, right, a, b, c, ... λ± λ€μν ν€ λͺλ Ήμ΄κ° μ‘΄μ¬νλ€.
  
  - #### μμ€ν μμμ΄ ν€ λͺ©λ‘
    - ctrl, alt, shift, meta(command) ...
    - ν΄λΉ μμμ΄ ν€κ° λλ¬μ§ κ²½μ°μλ§ μ΄λ²€νΈ λ¦¬μ€λλ₯Ό νΈλ¦¬κ±° ν  μ μλ€ (ex> .shift.a)
  
  - ##### `.extract` μμμ΄
    `@click.ctrl` μ΄λΌλ νΈλ¦¬κ±°κ° μ μλμλ€κ³  ν  λ, κΈ°λ³Έμ μΌλ‘λ alt, shift λ±μ ν€κ° ν¨κ» λλ €λ ctrl ν€λ§ λλ €μλ€λ©΄ νΈλ€λ¬λ μ€νλλ€.    
    νμ§λ§ `@click.ctrl.extract` μ²λΌ `.extract` μμμ΄λ₯Ό μ¬μ©νλ€λ©΄ ctrl ν€λ§ λλ €μ Έ μμ λλ§ μ€νλλ€.
    
## π§ νΌ μλ ₯ λ°μΈλ©

- ### π v-model
  - input, textarea, select μμλ€μ `μλ°©ν₯ λ°μ΄ν° λ°μΈλ©`μ μμ±νλ€.
    ```html
    <input type="text" v-model="message">
    <input type="checkbox" v-model="checked"> <!-- true, false -->
    ```
    input μλ ₯μ°½μ λ°μ΄ν°λ₯Ό μμ ,μλ ₯ νλ©΄ λ°μΈλλ message λ°μ΄ν° λν λ³κ²½λλ€. μ¦ value μμ±μ κ°κ³Ό λ°μΈλ λλ€.
    
  - checkboxμ λ°°μ΄ λ°μ΄ν°λ₯Ό μ°κ²°νλ©΄ μ΄λ€ κ²μ μ²΄ν¬ νλμ§λ νκΈ°ν  μ μλ€.
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
    checkeboxλ₯Ό μ²΄ν¬νλ©΄ valueμ μλ κ°μ΄ λ°μΈλ λ checkedNames λ°°μ΄μ μΆκ°λλ€.
    
  - v-modelμ νκΈ μλ ₯μ λν΄ ν κΈμκ° μμ±λ  λ κΉμ§ λ°μμ΄ `μ§μ°λλ μ΄μ`κ° μλ€. μ΄λ₯Ό ν΄κ²° νκΈ° μν΄ μλμ κ°μ΄ λ°μΈλ©ν  μ μλ€.
    ```html
    <input type="text" :value="message" @input="message = $event.target.value">
    ```
    μμ μ½λλ v-modelμ νμ΄ μ΄ μ½λμ΄λ€. `:value`λ‘ message dataμμ λ¨λ°©ν₯ λ°μΈλ©μ, `@input=""` μΌλ‘ λ°λ λ°©ν₯λ λ°μΈλ© νλ€.
  
  - #### μμμ΄
    - `.lazy`
      ```html
      <input type="text" v-model.lazy="message">
      ```
      - λ°λ‘ λ°μλλ κ²μ΄ μλλΌ. λ°μ΄ν°μ λ³κ²½μ΄ λλ¬μ λ(μν°, μΈν¬μ»€μ± λ±..) λ°μνλ€.
      - `@change` μ κ°λ€.
    - `.number`
      - μλ ₯λ°μ λ°μ΄ν°λ 1234μ κ°μ΄ μ«μλ₯Ό λ£μ΄λ String ννλ₯Ό κ°μ§λ€. `.number`λ μ΄λ₯Ό μ«μ λ°μ΄ν°μ νμμΌλ‘ μλ ₯ν  μ μλλ‘ ν΄μ€λ€.
    - `.trim`
      - μλ ₯λ λ¬Έμμ μ λ€ κ³΅λ°±μ μ κ±°ν΄μ€λ€.


  
## π§ μ»΄ν¬λνΈ
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
- ννλ¦Ώ μμ­μμλ μ»΄ν¬λνΈμ λ©μλλ₯Ό μ¬μ©ν  λ this λ₯Ό μλ΅ν  μ μλ€(this.log -> log)

```html
<my-btn>Hello Component!!</my-btn>
```

- ### π props
  μ»΄ν¬λνΈμ λ±λ‘ν  μ μλ μ»€μ€ν μμ±.
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
  - `v-bind` λ₯Ό μ¬μ©νκΈ° μν΄ `:style`λ‘ λ³κ²½.
  - js λ¬Έλ²μ΄ λκΈ° λλ¬Έμ λ¬Έλ²μ λ§μΆ° λ³κ²½.
  - jsλ μΉ΄λ©μΌμ΄μ€λ₯Ό μ°μ§λ§ htmlμμλ μΌλ°₯ μΌμ΄μ€λ‘ μ¨μΌν¨μ μ μνμ.

- ### π Emits
  μ»΄ν¬λνΈ μ(νμ)μμ λ°μΌλ‘(μμ) μ΄λ²€νΈλ₯Ό μλ¦Ό.
  ```js
  // μ»΄ν¬λνΈ methods
  emits: ['customEvent'],
  methods: {
    log() {
      this.$emit('customEvent', 'My CustomEvent!')
    }
  }
  ```
  ```js
  // λΆλͺ¨ methods:
  hello(event) {
      console.log(event)
  },
  ``` 
  ```html
  <my-btn @custom-event="hello" >WOW~</my-btn>
  ```
  - emits λ μ€μ μ μΈ κΈ°λ₯μ μλμ§λ§ μ΄λ€ emit λ€μ΄ μλμ§ λͺμν΄μ£Όλ μ­ν μ νλ€.
  - μ¬μ€μ @click μ λ°λ‘ `"$emit( ... )"` λ£μ΄μ€ κ²κ³Ό λμΌνλ€.
  - `emit` μ μ²« λ²μ§Έ μΈμλ λ°μλλ μ΄λ²€νΈ, λλ² μ§Έ μΈμλ κ°μ΄ μ λ¬ν  λ°μ΄ν° μ΄λ€.(event κ°μ²΄μ λ΄κΈ΄λ€.)
  
- ### π v-model μ¬μ©νκΈ°
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
  - `modelValue` λΌλ κ°μ λ³κ²½ν  μ μλ€. μ ν΄μ§ κ².
  - λ§μ°¬κ°μ§λ‘ `update:modelValue` λν μ ν΄μ§ κ²μ΄λ€.
  
- ### π slot
  ```js
  app.component('text-field', {
    template:
  `<label>
    <slot><h2>Slot Default</h2></slot>
    <input
     :value = "modelValue"
      @input="$emit('update:modelValue', $event.target.value)" />
    {{ modelValue }}
  </label>`,
    }
  })
  ```
  ```html
  <text-field v-model="message">
        <h2>WOW</h2>
      </text-field>
  ```
  - component λ΄λΆμ λͺ¨λ  λ΄μ©μ΄ slotμ μμΉμμ μΆλ ₯λλ€.
  - λ΄μ©μ΄ μλ€λ©΄ slotμ μλ default κ°μ΄ μΆλ ₯λλ€.


# π Vue3

## π§ νλ‘μ νΈ μΈν
`npm init`

```
"devDependencies": {
    "@babel/core": "^7.16.10",
    "@babel/plugin-transform-runtime": "^7.16.10",
    "@babel/preset-env": "^7.16.11",
    "@babel/runtime-corejs3": "^7.16.8",
    "@vue/compiler-sfc": "^3.2.27",
    "autoprefixer": "^10.4.2",
    "babel-loader": "^8.2.3",
    "css-loader": "^6.5.1",
    "eslint": "^8.7.0",
    "eslint-plugin-vue": "^8.3.0",
    "html-webpack-plugin": "^5.5.0",
    "postcss": "^8.4.5",
    "postcss-loader": "^6.2.1",
    "sass": "^1.49.0",
    "sass-loader": "^12.4.0",
    "vue-loader": "^17.0.0",
    "vue-style-loader": "^4.1.3",
    "webpack": "^5.66.0",
    "webpack-cli": "^4.9.1",
    "webpack-dev-server": "^4.7.3"
  },
  "dependencies": {
    "vue": "^3.2.27"
  }
```
- `npm init` μΌλ‘ μ²μ package.json μ΄κΈ°ν.
- npmμ μ΄μ©ν΄μ νμν ν¨ν€μ§ μ€μΉ.
  - devDependenciesλ `npm i -D` λ‘ λ€μ΄λ‘λ.
  - μΌλ° μμ‘΄μ±μ `npm i` 
  
- ### π babel μ€μ 
  ```json
  // babel.config.json
  {
    "presets": ["@babel/preset-env"],
    "plugins": [
      [
        "@babel/plugin-transform-runtime",
        {
          "corejs": 3
        }
      ]
    ]
  }
  ```

- ### π scripits
  ```json
  "scripts": {
    "dev": "webpack-dev-server --mode development",
    "build": "webpack --mode development",
    "babel": "babel src/main.js --out-dir dist"
  },
  ```

- ### π sass μ€μ 
  ```js
  // postcss.config.js
  module.exports = {
    plugins: [
      require('autoprefixer')
    ]
  }
  ```
- cli λͺλ Ήμ΄ μΈν.
- dev: κ°λ° μλ² λμ(localhost:8080)
- build: νλ‘μ νΈ λΉλ.

- ### π webpack
  ```js
  // webpack.config.js
  const { VueLoaderPlugin } = require('vue-loader')
  const HtmlPlugin = require('html-webpack-plugin')
  
  module.exports = (env, options) => {
    return {
      entry: './src/main.js',
      output: {
        // path: `${__dirname}/dist`,
        publicPath: '/',
        clean: true
      },
      module: {
        rules: [
          {
            test: /\.vue$/,
            use: 'vue-loader'
          },
          {
            test: /\.js$/,
            exclude: /node_modules/,
            use: 'babel-loader'
          },
          {
            test: /\.s?css$/,
            use: [
              'vue-style-loader',
              'css-loader',
              'postcss-loader',
              'sass-loader',
            ]
          }
        ]
      },
      plugins: [
        new VueLoaderPlugin(),
        new HtmlPlugin({
          template: './src/index.html'
        }),
      ]
    }
  }
  ```

- ### π Vetur νμ₯ νλ‘κ·Έλ¨.
  vue νμΌμμμ μ½λ νμ΄λΌμ΄ν.

- ### π lint
  ```json
  // .eslintrc.json
  {
    "env": {
      "browser": true,
      "node": true
    },
    "extends": [
      "eslint:recommended",
      "plugin:vue/vue3-recommended"
    ],
    "rules": {
      "semi": ["error", "never"],
      "quotes": ["error", "single"],
      "eol-last": ["error", "always"],
  
      "vue/html-closing-bracket-newline": ["error", {
        "singleline": "never",
        "multiline": "never"
      }],
      "vue/html-self-closing": ["error", {
        "html": {
          "void": "always",
          "normal": "never",
          "component": "always"
        },
        "svg": "always",
        "math": "always"
      }],
      "vue/comment-directive": "off"
    }
  }
  ```
  
## π§ μμνκΈ°

- main.js
```js
import { createApp } from 'vue'
import App from './App.vue'

const app = createApp(App)
app.mount('#app')
```
- App.vue
```js
<template>
</template>

<script>
export default {
  data() {
    return {
    }
  },
  methods: {
 
  }
}
</script>

<style lang="scss">
h1 {
  // const color = 'red'
  $color: red;
  background-color: $color;
  display: flex;
}
</style>
```

## π§ Plugin λ§λ€κΈ°.
- #### fetch.js
  ```js
  import axios from 'axios'
  
  export default {
    install(app) {
      app.config.globalProperties.$fetch = async (url = '', method, data) => {
        const { data: returnValue } = await axios(`https://asia-northeast3-heropy-api.cloudfunctions.net/api/todos/${url}`, {
          headers: {
            'content-type': 'application/json',
            'apikey': 'FcKdtJs202110',
            'username': 'bigave',
          },
          method,
          data
        })
        return returnValue
      }
    }
  }
  ```
  
- #### main
  ```js
  import fetch from './plugins/fetch'
  app.use(fetch)
  ```

- #### μ¬μ©.
  ```js
  async readTodos() {
    this.todos = await this.$fetch(null, 'GET', )
  }
  ```
  

## π§ Store: vuex
- #### store μ μ
  ```js
  export default {
    // νμ μ΅μ.
    namespaced: true,
    state: () => ({
      // data
      message: 'Hello'
      
    }),
    getters: {
      // computed
    },
    mutations: {
      // μμ  κΆνμ κ°μ§.
      updateMessage(state) {
        state.message = 'changedMessage'
      }
    },
    actions: {
      // methods
      onNav({ commit }) {
          // context.commit
        commit('updateMessage')
      }
    }
  }
  ```
  - mutationsμ λ±λ‘λ λ©μλλ contextμ commitμΌλ‘ actionμμ μ¬μ©ν  μ μλ€.

- #### store μμ±
  ```js
  import { createStore } from 'vuex'
  import navigation from './navigation'
  
  export default createStore({
    modules: {
      navigation
    }
  })
  ```
  - navigation.js(store μ μ νμΌ)μ import νκ³  μ€ν μ΄μ λͺ¨λμ λ±λ‘νλ€.
  
- #### main.jsμ plugin λ±λ‘
  ```js
  import { createApp } from 'vue'
  import App from '~/App.vue'
  import store from '~/store'
  
  const app = createApp(App)
  app.use(store)
  app.mount('#app')
  ```

- #### vue μ»΄ν¬λνΈμμ store μ state κ° κ°μ Έμ€κΈ°.
  ```js
  computed: {
      message() {
        return this.$store.state.navigation.message
      }
  }
  ```
  - $store λ³μμ stateμμ κΊΌλΈλ€. namespace(μ¬κΈ°μ  navigation) μμ κΊΌλΈλ€.
  
- #### vue μ»΄ν¬λνΈμμ storeμ actionsμ λ©μλ νΈμΆ.
  ```js
  methods: {
    onNav() {
      this.store.dispatch('navigation/onNav')
    }
  }
  ```
  - dispatch λ₯Ό μ¬μ©νκ³  λ©μλ μ΄λ¦μ λͺμν λλ namespaceλ₯Ό ν¨κ» λͺμνλ€.