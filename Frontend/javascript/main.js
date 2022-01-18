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
