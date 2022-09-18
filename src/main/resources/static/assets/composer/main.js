let texts = document.querySelectorAll('p')
    texts.forEach(text =>{
        text.innerHTML = text.innerHTML.replaceAll('_', ' ')
    })
let addition = document.querySelector('.addition')
addition.style.display = 'none'


let additionBtn = document.querySelector('.c4')
additionBtn.addEventListener('click', ()=>{
    if(getComputedStyle(addition).display === 'none'){
        addition.style.display = 'flex'
    }
    else addition.style.display = 'none'
})