let submitBtns = document.querySelectorAll('.submit') /*кнопки сохранения формы*/
let inputContainers = document.querySelectorAll('.inputContainer input')
const map1 = new Map(); /*мап для хранения значений полей*/

submitBtns.forEach(submitBtn => {
    submitBtn.addEventListener('click', () => {
        let btnType = submitBtn.getAttribute('btnType') //тип кнопки(quote,bio,default)
        let splits = submitBtn.parentElement.querySelectorAll('.splite') //разделители полей
        let mass = [] //массив для значений
        let inputValues
        let titleKeys
        let inputTitleKey

        splits.forEach(split => {
            inputValues = split.querySelectorAll('input, textarea, select')
            mass = getValues(inputValues, btnType)
            titleKeys = split.querySelectorAll('.menuTitle p')
            titleKeys.forEach(titleKey => {
                inputTitleKey = titleKey
            })
            map1.set(inputTitleKey.innerHTML.trim(), mass)
            pullValue(mass,inputTitleKey.innerHTML.trim())

        })
        console.log(map1)
    })

})

function getValues(inputValues, btnType) {
    let mass = []
    let strTextArea = ''
    let strInput = ''
    let count = 0

    inputValues.forEach((inputValue, index, array) => {
        switch (btnType) {
            case "quote" :
                strTextArea = inputValue.value
                if (count !== inputValues.length) {
                    mass.push(strTextArea.trim())
                    count++
                }
                break
            case "select" :
                for (let el of inputValue.parentElement.children) {
                    if (el.tagName === 'SELECT') {
                        strInput +="/" + el.value + " "
                    } else {
                        strInput += el.value + " "
                    }
                }

                let str = strInput.split("/")
                if (index === array.length-1){
                    for (let i = 1; i < str.length; i++) {
                        mass.push(str[i].trim())
                    }
                }
                break
            case "bio" :
                for (let el of inputValue.parentElement.children) {
                    if (el.querySelector('p')!== null && el.querySelector('p').tagName === 'P') {
                        mass.push((el.querySelector('p').innerHTML).trim())
                    } else {
                        mass.push(el.value.trim() + " |")
                    }
                }
                break
            case "default" :
                if ((inputValue.value !== "" && inputValue.type === "number") || (inputValue.value !== "" && inputValue.type === "textarea") || inputValue.checked) {
                    strInput = ''
                    for (let el of inputValue.parentElement.children) {
                        if (el.value === 'on' || el.innerHTML === 'точная дата') {

                        } else if (el.tagName === 'P') {
                            strInput += el.innerHTML + " "
                        } else {
                            strInput += el.value + " "
                        }
                    }
                    if (count++<1){
                        mass.push(strInput.trim())
                    }
                }
                break
            default :
                console.log("тип кноки не указан")
        }
    })
    return mass;
}

function pullValue(mass,key){
    let str = ""
    inputContainers.forEach(inputContainer => {
        if(inputContainer.getAttribute('keyValue')===key){
            for (let i = 0; i < mass.length; i++) {
                str += mass[i] + "_"
            }
            inputContainer.value = str.trim()
        }
    })
}

