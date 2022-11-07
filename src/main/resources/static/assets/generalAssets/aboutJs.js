//если в базе пусто, скрыть блок
document.querySelectorAll('span').forEach(span=>{
    if(span.innerHTML ===''){
        span.parentElement.style.display = 'none'
    }
})
//если кол-во хоров = 1, скрыть блок
/*if(document.querySelector('.count').innerHTML === 'один хор'){
    document.querySelector('.count').parentElement.style.display ='none'
}*/
//кнопка домой, добавить ссылку на ЛК
document.querySelector('.btnHome').setAttribute("href", "/CAHI/account")