

// classList
let boxEl = document.querySelector('.box');

boxEl.classList.add('active'); // class 추가.
  
let isContains = boxEl.classList.contains('active'); // 포함 여부.
console.log(isContains);

boxEl.classList.remove('active'); // class 제거.

isContains = boxEl.classList.contains('active'); // 포함 여부.
console.log(isContains);



// forEach
let boxEls = document.querySelectorAll('.box');

boxEls.forEach(function(boxEl, index){
  console.log(index);
  console.log(boxEls);

  boxEl.classList.add(`order-${index+1}`)
});

// textContent
  // getter
let content = boxEl.textContent;

  //setter
boxEl.textContent = 'box!!';
