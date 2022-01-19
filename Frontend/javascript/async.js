// Promise
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

function c(res){
  console.log('C')
}

a()
  .then(b)
  .then(c)


const url = 'https://www.omdbapi.com/?i=tt3896198&apikey=7035c60c'
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
  
