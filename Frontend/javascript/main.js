const app = Vue.createApp({
  data() {
    return {
      counter: 100,
      tooltip: "wheel up and down ~~ ",
      message: 'Hello',
      myName: ' kim',
      showMessage: false,
      fruits: ['Apple', 'Banana', 'Cherry'],
      movie: {},
      
      htmlMessage: '<div style="color:red"> 원시 HTML!</div>',
      myStyle: 'color: red; font-size: 100px;',
      
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
    showMyName() {
      this.showMessage = true
    }

  }
})
app.mount('#bigave')