let debug = document.querySelector('.debug')
let count = true
document.addEventListener('keydown', (e)=>{
    if(e.code == 'Backquote' && count) {
        debug.style.height = 500 +'px'
        count = false
    }
    else{
        debug.style.height = 0
        count = true
    }

})
/**/