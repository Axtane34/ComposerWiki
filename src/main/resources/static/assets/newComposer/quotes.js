let quotesBtns = document.querySelectorAll('.plusBtn')
let quoteContainers = document.querySelectorAll('.quoteContainer')
let body = document.querySelector('body')

quotesBtns.forEach(quotesBtn =>{
    quotesBtn.addEventListener('click', ()=>{
        let split = quotesBtn.parentElement.parentElement
        let quoteContainer = split.querySelector('.quoteContainer') //контейнер со скролом цитат фремовd
        //создание
        let quoteContainerTextArea = document.createElement('textarea') //создание поля для ввода цитат
        quoteContainer.appendChild(quoteContainerTextArea); //помещение в родителя
        quoteContainer.scrollTop = quoteContainer.scrollHeight
    })
})


quoteContainers.forEach(quoteContainer =>{
    quoteContainer.addEventListener('mouseenter', ()=>{
        body.style.overflow = 'hidden'
    })
    quoteContainer.addEventListener('mouseleave', ()=>{
        body.style.overflow = 'auto'
    })
})