let circleBtns = document.querySelectorAll('.cls-1')
let bodySize = document.querySelector('body')
let fog = document.querySelector('.fog')
let pushButton = document.querySelector('.pushButton')
let debugBtn = document.querySelector('.debugBtn')

let currentSvgFrame
let menu
let inMenu
let submitBtnss = document.querySelectorAll('.submit') /*кнопки сохранения формы*/
let menuBtns = document.querySelectorAll('.menu li')
let enterBtn = document.querySelector('.enterBtn')

circleBtns.forEach(circleBtn => {
    circleBtn.addEventListener('click', () => {
        enableFog(circleBtn)
        menu = document.querySelector('.menu[menuField="' + currentSvgFrame.getAttribute('menuField') + '"]')
        menu.style.display = "block"
    })
}) // событие клика по кружку e

/*=============ЗАПРЕТИТЬ TAB================*/
let picks = document.querySelectorAll('.pick')
picks.forEach(pick =>{
    pick.addEventListener('keydown', function (e) {
        if (e.which === 9) {
            e.preventDefault();
        }
    });
})

/*=============МАСКА ВВОДА===============*/
document.addEventListener('DOMContentLoaded', () => {

    const mask = (dataValue, options) => { // создаем универсальную функцию
        const elements = document.querySelectorAll(`[data-mask="${dataValue}"]`) // ищем поля ввода по селектору с переданным значением data-атрибута
        if (!elements) return // если таких полей ввода нет, прерываем функцию

        elements.forEach(el => { // для каждого из полей ввода
            IMask(el, options) // инициализируем плагин imask для необходимых полей ввода с переданными параметрами маски
        })
    }

    // Используем наше функцию mask для разных типов масок

    // Маска для номера телефона
    mask('phone', {
        mask: '+{7}(000)000-00-00'
    })

    // Маска для почтового индекса
    mask('postalCode', {
        mask: '000000' // шесть цифр
    })

    // Маска для даты
    mask('date', {
        mask: Date,
        min: new Date(1000, 0, 1), // минимальная дата 01.01.1000
    })

    // Маска для числа
    mask('number', {
        mask: Number,
        thousandsSeparator: ' ' // разделитель тысяч в числе
    })

})

/*=================*FOG*====================*/
function enableFog(circleBtn) {
    let height = bodySize.getBoundingClientRect().height;
    fog.style.display = 'block'
    fog.style.height = height + 'px'
    currentSvgFrame = circleBtn.parentElement.parentElement
    currentSvgFrame.style.pointerEvents = 'none'
    currentSvgFrame.style.zIndex = '2'
    if (circleBtn.parentElement.parentElement.getAttribute('menuField') === 'bio') {
        fog.classList.add('fogBlack')
        currentSvgFrame.style.zIndex = '0'
    }

} //логика включения затемнения

function disableFog() {
    fog.classList.remove('fogBlack')
    fog.style.display = 'none'
    currentSvgFrame.style.zIndex = '0'
    currentSvgFrame.style.pointerEvents = 'all'
    menu.style.display = "none"
    inMenu.style.display = 'none'

} //логика включения затемнения

fog.addEventListener('click', disableFog)
//логика отключения затемнения

/*=================*ЛОГИКА МЕНЮ 2го уровня*====================*/
menuBtns.forEach(menuBtn => {
    menuBtn.addEventListener('click', () => {
        inMenu = document.querySelector('.menuIn[menuInField="' + menuBtn.getAttribute('menuInField') + '"]');
        inMenu.style.display = 'block'
        menu.style.display = 'none'
        checkerNew(inMenu)
    })
});

function checkerNew(inMenu) {
    inMenu.querySelectorAll('.pick').forEach(pick => {
        pick.addEventListener('input', () => {
            let splite = pick.parentElement
            let nods = splite.querySelectorAll('.pick')
            let currentInputs = pick.querySelectorAll('input,textarea')
            currentInputs.forEach((currentInput, index) => {
                if ((currentInput.value !== "" && (currentInput.type === "number" || currentInput.type === "text")) || (currentInput.value !== "" && currentInput.type === "textarea") || currentInput.checked) {
                    nods.forEach(nod => {
                        nod.style.pointerEvents = 'none'
                    })
                    currentInput.parentElement.style.pointerEvents = 'all'
                    currentInput.parentElement.classList.add('inputPick')
                } else if (index === 0) {
                    nods.forEach(nod => {
                        nod.style.pointerEvents = 'all'
                        nod.classList.remove('inputPick')
                    })
                }
            })
        })
    })
}

enterBtn.addEventListener('click', ()=>{
    let inputValue = enterBtn.parentElement.querySelector('.splite').querySelector('input')
    if(inputValue.value === "") alert('введите имя')
    else{
        inputValue.parentElement.parentElement
        inputValue.parentElement.parentElement.parentElement.style.display = 'none'
    }
})



submitBtnss.forEach(submitBtn => {
    submitBtn.addEventListener('click', ()=> {
       disableFog()
    })
})

pushButton.addEventListener('click', ()=> {
    debugBtn.click()
})