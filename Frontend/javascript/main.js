
// this
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

console.log('kim'.indexOf('i',2))

console.log('hello'.match('[^ab]'))
console.log(Number.parseInt('123a4'))


// unshift, push
let arr = [1,2,3]
arr.unshift(4,5,6);
arr.push([7,8,9])
console.log(arr);


// reduce
arr = [100, 200, 300, 400]
  let result = arr.reduce((acc,curr) => {
                      return acc + curr
                }, 0)
console.log(result)

// Object.assign()
source = {a :1, b: 2}
source2 ={c: 3, b: 9}
target = {d :5, e: 6}
Object.assign(target, source, source2)

console.log(target)

function User(firstName, lastName){
  this.firstName = firstName
  this.lastName = lastName
}

// prototype
User.prototype.getFullName = function() {
  return `${this.firstName} ${this.lastName}`
}

const taejunKim = new User('Taejun', 'Kim')
const heropy = new User('HEROPY', 'Park')

console.log(taejunKim.getFullName())
console.log(heropy.getFullName())
console.log(taejunKim.getFullName === heropy.getFullName)
console.log(taejunKim)

// class
class ClassUser{
  constructor(firstName, lastName){
    this.firstName = firstName
    this.lastName = lastName
  }

  get getFullName(){
    return `${this.firstName} ${this.lastName}`
  }
}

const taejun = new ClassUser('Taejun', 'Kim')
console.log(taejun.getFullName)


// class - extends

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

// 전개 연산자
const arr1 = [1, 2, 3, 4]
const arr2 = [4, 5, 6] 

const arrWrap = [arr1, arr2]
console.log(arrWrap)


// 구조 분해 할당.
const [x, , y, z, t = 20] = arr1
console.log(x) // 1
console.log(y) // 3
console.log(z) // 4
console.log(t) // 20

const objA = {
  a: 1,
  b: 2,
  c: 3
}

const {a, b:myKey} = objA
console.log(a)
console.log(myKey)