if (document.querySelector('.statement').value !== '') {
    document.getElementsByTagName("sss")[0].innerHTML = document.querySelector('.statement').value
}
//кнопка домой, добавить ссылку на ЛК
document.querySelector('.btnHome').setAttribute("href", "/CAHI/account")

function is_valid_datalist_value(idDataList, inputValue) {
    let option = document.querySelector("#" + idDataList + " option[value='" + inputValue + "']");
    if (option != null) {
        return option.value.length > 0;
    }
    return false;
}

let valid = document.querySelector('.valid')
let invalid = document.querySelector('.invalid')
let composerName = document.querySelector('.composer')
function doValidate(sender) {
    valid.value = ''
    invalid.value = ''
    if (is_valid_datalist_value('composers', composerName.value)) {
        valid.value = sender.value
    } else {
        invalid.value = sender.value
    }
}

function unknownFIO(sender){
    composerName.value = sender.value
    invalid.value = sender.value
    valid.value = ''
}

function insertBrt(sender){
    sender.parentElement.previousElementSibling.value = sender.value
}

let musicList = document.querySelector('.musicList')
let musicInput = document.querySelector('.musicInput')
let genreList = document.querySelector('.genreList')
let genreInput = document.querySelector('.genreInput')

if(document.querySelector('.music')){
    document.querySelector('.music').addEventListener('input', function (){
        if(this.value === ''){ //Инструментальный состав
            musicList.classList.remove('hide')
            musicInput.classList.remove('hide')
        }
        else{
            musicList.classList.add('hide')
            musicInput.classList.add('hide')
            musicInput.value = ''
        }
    })
}

if(document.querySelector('.genreList')){
    genreList.addEventListener('input', function (){
        if(this.value === 'обработка народной песни'){
            genreInput.classList.remove('hide')
        }
        else{
            genreInput.classList.add('hide')
            genreInput.value = ''
        }
    })
}




function insertText(sender, item, type){
    let count = prompt('количество '+ item+ ' '+'"'+sender.value+'"'+' в '+type)
    let str = ''
    if(sender.nextElementSibling.value === ''){
        str += sender.value + ' ('+count+')'
    }
    else{
        str +=', '+ sender.value + ' ('+count+')'
    }

    sender.nextElementSibling.value += str
}

let btnSave = document.querySelector('.btnSave')
btnSave.addEventListener('click', () => {


    let inputs = document.querySelectorAll('input')

    let selects = document.querySelectorAll('select')

    inputs.forEach(inp => {
        let value = inp.value
        inp.setAttribute("value", value)
    })
    selects.forEach(select => {
        const selectedOption = select.options[select.selectedIndex];
        console.log(selectedOption)
        let options = select.querySelectorAll('option')
        options.forEach(option => {
            option.removeAttribute("selected")
        })
        selectedOption.setAttribute("selected", "selected")
    })
    let statement = document.querySelector('.statement')
    statement.value = document.getElementsByTagName("sss")[0].innerHTML
})






