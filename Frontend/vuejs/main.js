const app = Vue.createApp({
  data() {
    return {
      counter: 100,
      tooltip: "wheel up and down ~~ ",
      message: 'Hello world',
      myName: ' kim',
      showMessage1: false,
      showMessage2: true,
      showMessage3: false,
      fruits: ['Apple', 'Banana', 'Cherry'],
      movie: {},
      
      htmlMessage: '<div style="color:red"> 원시 HTML!</div>',
      myStyle: 'color: red; font-size: 100px;',
      abc: 'click',
      isActive: false,
      myColor: 'red',

      user: {
        name: 'kim',
        age: '26',
        email: 'asd@gmail.com',
      },

      checkedNames: [],
    }
  },
  computed: {
    //getter
    newMessage() {
      return this.message + '!!'
    },
    // computedReversedMessage() {
    //   return this.message
    //                     .split('')
    //                     .reverse()
    //                     .join('')
    // }
    computedReversedMessage: {
      get() {
        return this.message
                      .split('')
                      .reverse()
                      .join('')
      },
      set(value) {
        console.log(value)
      }
    }
  },
  
  async created() {
    // vue app이 생성된 직후
    const { data } = await axios.get('https://www.omdbapi.com/?i=tt3896198&apikey=7035c60c')
    console.log(data)
    this.movie = data
  },
  
  mounted() {
    // html 구조에 연결된 후
    console.log(document.querySelector('button')) // <button> reverse </button>
  },

  watch: {
    message(newValue, oldValue) {
      console.log(`message 데이터 ${oldValue}에서 ${newValue}로 변경됨.`)

    }
  },

  methods: {
    increase() {
      this.counter +=1
    },
    reverseMessage() {
      this.message = this.message
                        .split('')
                        .reverse()
                        .join('')
    },
    reversedMessage() {
      return this.message
                        .split('')
                        .reverse()
                        .join('')
    },
    showMyName() {
      this.showMessage = true
    },

    hello(event) {
      console.log(event)
    },

  }
})
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
  {{ modelValue }}
</div>`,
  props: {
    myColor: {
      type: String,
      default: 'royalblue'
    },
    modelValue: {
      type: String,
    }
  },
  emits: ['update:modelValue'],
  methods: {
    log() {
      this.$emit('update:modelValue', 'My CustomEvent!')
    },

  }
})

app.component('text-field', {
  template:
`<label>
  <input
   :value = "modelValue"
    @input="$emit('update:modelValue', $event.target.value)" />
  {{ modelValue }}
</label>`,
  props: {
    modelValue: {
      type: String,
    },
  }
})
app.mount('#bigave')