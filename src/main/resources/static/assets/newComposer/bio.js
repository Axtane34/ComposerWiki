let addBioBtns = document.querySelectorAll('.addBioBtn')
let lifeStepInputParent = document.querySelector('.bioRight')
let childAges = document.querySelectorAll('.bioLeft li')
let countAttr = 0
let bioRight = document.querySelector('.bioRight')
let splite = bioRight.querySelector('.splite')

function addBioField(parent){
    let lifeStep = document.createElement('div') //плашка, которую создает плюс *1
    let lifeStepMiniInput = document.createElement('input') //инпут поля для плашки *1
    let lifeStepInput = document.createElement('div') //большой инпут
    let lifeStepInputTitle = document.createElement('div') //тайтел для большого интупа
    let lifeStepInputTitleP = document.createElement('p') //контейнер для текста заголовка большого инпута
    let lifeStepTextAria = document.createElement('textarea') //контейнер для текста заголовка большого инпута
/*    let splite = document.createElement('div') //разделитель для логики мапа*/
    let titleIndex = document.createElement('div')
    let titleIndexP = document.createElement('p')
//помещаем созданную плашку *1 в нужного родителя
    document.querySelector('.'+ parent.className).appendChild(lifeStep);
    lifeStep.classList.add('lifeStep')
    lifeStep.setAttribute("lifeStepAttr", countAttr)
//помещаем инпут поле в  плашку *1
    document.querySelectorAll('[lifeStepAttr="'+countAttr+'"]')[0].appendChild(lifeStepMiniInput);
    lifeStepMiniInput.setAttribute("lifeStepAttr", countAttr)
    lifeStepMiniInput.setAttribute("placeholder", 'Назовите подраздел')
    lifeStepMiniInput.classList.add('lifeStepMiniInput')

//сплит


//ключ для мапа
    splite.appendChild(titleIndex)
    titleIndex.appendChild(titleIndexP)
    titleIndex.classList.add('menuTitle')
    titleIndex.classList.add('invisible')
    titleIndexP.innerHTML = 'биография'
//большой интпут
    splite.appendChild(lifeStepInput)
    lifeStepInput.setAttribute("lifeStepAttr", countAttr)
    lifeStepInput.classList.add('lifeStepInput')
//титл для большого инпута
    document.querySelectorAll('[lifeStepAttr="'+countAttr+'"]')[2].appendChild(lifeStepInputTitle);
    lifeStepInputTitle.setAttribute("lifeStepAttr", countAttr)
    lifeStepInputTitle.classList.add('lifeStepInputTitle')

//контейнер для текста заголовка большого инпута
    document.querySelectorAll('[lifeStepAttr="'+countAttr+'"]')[3].appendChild(lifeStepInputTitleP);
    lifeStepInputTitleP.setAttribute("lifeStepAttr", countAttr)
    let inputField = document.querySelectorAll('[lifeStepAttr="'+countAttr+'"]')[1]
//текстовое поле(textarea) для большого инпута
    document.querySelectorAll('[lifeStepAttr="'+countAttr+'"]')[2].appendChild(lifeStepTextAria);
    lifeStepTextAria.setAttribute("lifeStepAttr", countAttr)
    lifeStepTextAria.setAttribute("placeholder", 'Начните ввод биографии')
    lifeStepTextAria.classList.add('lifeStepTextAria')
//слушатель передачи из мини ипута в титл
    inputField.addEventListener('input', ()=>{
        lifeStepInputTitleP.innerHTML = inputField.value
    })
    inputField.addEventListener('click', ()=>{
        let colors = document.querySelectorAll('.lifeStepMiniInput')
        colors.forEach(color =>{
            color.style.backgroundColor = "rgb(233,233,233)"
        })
        inputField.style.backgroundColor = "white"
    })

//слушатель для клика по инпуту (переключение больших инпутов)
    inputField.addEventListener('click', ()=>{
        let lifeStepInput = document.querySelectorAll('.lifeStepInput')
        lifeStepInput.forEach(offLifeStepInput =>{
            offLifeStepInput.style.display = 'none'
            inputField.getAttribute('lifeStepAttr')
            let onLifeStepInput = document.querySelectorAll('[lifeStepAttr="'+inputField.getAttribute('lifeStepAttr')+'"]')[2]
            onLifeStepInput.style.display = 'block'
        })
    })
    lastLifeStep(parent)  //метод
    countAttr++
}


addBioBtns.forEach(addBioBtn => {
    addBioBtn.addEventListener('click', (e)=>{e.stopPropagation();
        let parent = addBioBtn.parentElement.parentElement.parentElement
        addBioField(parent)
        childAges.forEach(childAge =>{
            let lifeSteps = childAge.parentElement.querySelectorAll('.lifeStep')
            lifeSteps.forEach(lifeStep =>{
                lifeStep.style.display = 'block'
            })
        })

    })
})


function lastLifeStep(parent){
    let BioFields = parent.querySelectorAll('.lifeStep')
    BioFields.forEach((BioField, index) =>{
        BioField.classList.remove('lastLifeStep')
        if (index === BioFields.length - 1){
            BioField.classList.add('lastLifeStep')
        }
    })
}

childAges.forEach(childAge =>{
    childAge.addEventListener('click', ()=>{
        let lifeSteps = childAge.parentElement.querySelectorAll('.lifeStep')
        lifeSteps.forEach(lifeStep =>{
            if(getComputedStyle(lifeStep).display == 'block'){ //обращение к стилям
                lifeStep.style.display = 'none'
            }
            else{
                lifeStep.style.display = 'block'
            }

        })
    })
})