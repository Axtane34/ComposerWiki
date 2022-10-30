if (document.querySelector('.statement').value!==''){
    document.getElementsByTagName("sss")[0].innerHTML = document.querySelector('.statement').value
}

window.onload = function (){
    if(!document.querySelector('.draftName')){
        document.querySelector('.ls_js').value = "Обновить черновик"
    }
    document.querySelector('.ls_js').addEventListener('click',() =>{
        document.querySelector('.localSave').click()
    })


    function test1(){
        let lastCell
        let lastItem
        document.querySelectorAll('.item').forEach(item=>{
            item.addEventListener('dragstart', function (){
                setTimeout(()=>{
                    this.style.display = 'none'
                },0)
                lastItem = this
                lastCell = this.parentElement
            })
            item.addEventListener('dragend', function (){
                this.style.display = 'block'
            })
        })
        document.querySelectorAll('.cellBio').forEach(cell=>{
            cell.addEventListener('dragenter', function (e){
                if(this.parentElement.contains(lastItem)){
                    lastCell.append(this.children[0])
                    this.append(lastItem)
                }

            })
            cell.addEventListener('dragleave', function (e){
                e.preventDefault()
                lastCell = this
                if(this.parentElement.contains(lastItem)){
                    lastCell = this
                }
            })

            cell.addEventListener('dragover', function (e){
                e.preventDefault()
            })
            cell.addEventListener('drop', function (){
                if(this.parentElement.contains(lastItem)){
                    this.append(lastItem)
                }
            })
        })
    }


    let mass = []
    document.querySelectorAll('.btn_js').forEach(btn=>{
        btn.addEventListener('click', ()=>{
            test1()
            document.querySelector('.fog').style.display = 'block'
            btn.parentElement.style.zIndex = '2'
            mass.push(btn)
            let elem = btn.closest('.frame').children[mass.length]
            elem.style.display ='block'
            if(mass.length === 1) btn.style.pointerEvents = 'none'
            if(mass.length === 2){
                btn.parentElement.style.display = 'none'
                elem.children[btn.getAttribute('attr')].style.display ='block'
            }
        })
    })

    function undoOrSave(){
        let currentLevel = mass.length
        this.parentElement.style.display = 'none'
        if(currentLevel === 1){
            this.parentElement.parentElement.children[0].style.zIndex = '0'
            document.querySelector('.fog').style.display = 'none'
            mass[0].style.pointerEvents = 'auto'
            mass = []
        }
        if(currentLevel === 2){
            this.parentElement.querySelectorAll('.l3_js').forEach(btn_js =>{
                btn_js.style.display = 'none'
            })
            mass[1].parentElement.style.display = 'block'
            mass.pop()
        }
        if(this.classList[0] === 'saveBtn'){
            this.parentElement.querySelectorAll('section').forEach(section=>{
                let str = ''
                section.children[0].value =""

                section.querySelectorAll('input, textarea, select').forEach(input=>{
                    if(input.parentElement.querySelectorAll('input, textarea').length>1){
                        str = ''
                    }
                    if((input.value !== "" && input.type !== 'checkbox') || input.checked){
                        for (let el of input.parentElement.children) {

                            if (el.tagName === 'P') {
                                if(el.classList[0] !== 'except') str += el.innerHTML +" "
                            } else {
                                if(el.classList[0] !== 'except') str += el.value +" "
                            }
                        }
                    }
                })
                section.querySelectorAll('input')[0].value = str.trimEnd()
            })
        }
        if(this.classList[0] === 'saveBtn2'){
            let str = ''
            this.parentElement.querySelectorAll('.textareaCont').forEach(textareaCont=>{
                for (let el of textareaCont.children) {
                    if (el.tagName === 'P') {
                        if(el.classList[0] !== 'except') str += el.innerHTML +" "
                    } else {
                        if(el.classList[0] !== 'except') str += el.value +" "
                    }
                }
            })
            this.parentElement.querySelectorAll('section').forEach(section=>{
                section.querySelectorAll('input')[0].value = str.trimEnd()
            })
        }
        if(this.classList[0] === 'saveBtn3'){
            let str = ''
            let count = 0
            document.querySelector('.bioSect').querySelectorAll('.cont').forEach(cont=>{
                str += "label "+cont.children[0].querySelector('.mainC input').value +" " //значение
                for(let el of cont.children[1].children){

                    let attr = el.querySelector('.item').getAttribute('pairing')
                    let cont = document.querySelector('.bioAll[pairing="'+attr+'"]')
                    for(let el of cont.children){
                        if(el.tagName !== 'DIV'){
                            str += el.getAttribute('sep') + " " + el.value +" "
                        }
                    }
                }
            })
            this.parentElement.querySelector('input').value = str



        }
    }

    document.querySelector('.roundJs').addEventListener('drop', function (e){
        e.preventDefault()
        this.classList.remove('roundF')
        this.style.backgroundImage = 'url('+e.dataTransfer.files[0].name+')'
        console.log(e.dataTransfer.getData( ))

    })
    document.querySelector('.roundJs').addEventListener('dragover', function (e){
        e.preventDefault()
    })
    document.querySelector('.roundJs').addEventListener('dragenter', function (e){
        this.classList.add('roundF')
    })
    document.querySelector('.roundJs').addEventListener('dragleave', function (e){
        this.classList.remove('roundF')
    })

    let inner;
    document.querySelectorAll('.btnAddJs').forEach(btnAdd =>{
        btnAdd.addEventListener('click', function (){
            switch(this.getAttribute("btnType")) {
                case 'quote':
                    inner = "<div class=\"m2Textarea\">\n" +
                        "                                            <div class=\"textareaCont\">\n" +
                        "                                                <p style=\"display: none\">quote</p>\n" +
                        "                                                <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"введите цитату\"></textarea>\n" +
                        "                                                <div onclick=\"this.parentElement.remove()\" class=\"except btnDel btnDel_js\"></div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>"
                    break
                case 'facts':
                    inner = "<div class=\"m2Textarea\">\n" +
                        "                                            <div class=\"textareaCont\">\n" +
                        "                                                <p style=\"display: none\">facts</p>\n" +
                        "                                                <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"введите факт\"></textarea>\n" +
                        "                                                <div onclick=\"this.parentElement.remove()\" class=\"except btnDel btnDel_js\"></div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>"
                    break
                case 'quoteAbout':
                    inner = "<div class=\"m2Textarea\">\n" +
                        "                                            <div class=\"textareaCont\">\n" +
                        "                                                <p style=\"display: none\">qAuthor</p>\n" +
                        "                                                <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" placeholder=\"автор цитаты\">\n" +
                        "                                                <p style=\"display: none\">quote</p>\n" +
                        "                                                <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"введите цитату о композиторе\"></textarea>\n" +
                        "                                                <div onclick=\"this.parentElement.remove()\" class=\"except btnDel btnDel_js\"></div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>"
                    break
                case 'topic':
                    inner = "<div class=\"m2Textarea\">\n" +
                        "                                            <div class=\"textareaCont\">\n" +
                        "                                                <p style=\"display: none\">author</p>\n" +
                        "                                                <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" placeholder=\"автор статьи\">\n" +
                        "                                                <p style=\"display: none\">article</p>\n" +
                        "                                                <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"введите статью\"></textarea>\n" +
                        "                                                <div onclick=\"this.parentElement.remove()\" class=\"except btnDel btnDel_js\"></div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>"
                    break
                case 'letterFrom':
                    inner = "<div class=\"m2Textarea\">\n" +
                        "                                            <div class=\"textareaCont\">\n" +
                        "                                                <p style=\"display: none\">from</p>\n" +
                        "                                                <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" placeholder=\"адресат\">\n" +
                        "                                                <p style=\"display: none\">topic</p>\n" +
                        "                                                <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" placeholder=\"тема письма\">\n" +
                        "                                                <p style=\"display: none\">letter</p>\n" +
                        "                                                <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"введите письмо\"></textarea>\n" +
                        "                                                <div onclick=\"this.parentElement.remove()\" class=\"except btnDel btnDel_js\"></div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>"
                    break
                case 'letterTo':
                    inner = "<div class=\"m2Textarea\">\n" +
                        "                                            <div class=\"textareaCont\">\n" +
                        "                                                <p style=\"display: none\">from</p>\n" +
                        "                                                <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" placeholder=\"имя отправителя\">\n" +
                        "                                                <p style=\"display: none\">topic</p>\n" +
                        "                                                <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" placeholder=\"тема письма\">\n" +
                        "                                                <p style=\"display: none\">letter</p>\n" +
                        "                                                <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"введите письмо\"></textarea>\n" +
                        "                                                <div onclick=\"this.parentElement.remove()\" class=\"except btnDel btnDel_js\"></div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>"
                    break
                default:
                    console.log("sd")


            }

            this.parentElement.querySelector('.newDivCell').innerHTML += inner
        })
    })








    document.querySelectorAll('.back_js').forEach(back=>{
        back.addEventListener('click', undoOrSave)
    })


    document.querySelectorAll('.saveBtn_js').forEach((saveBtn =>{
        saveBtn.addEventListener('click', undoOrSave)
    }))





    document.querySelector('.localSave').addEventListener('click', ()=>{
        document.querySelectorAll('input').forEach(input =>{
            if(input.type !== 'checkbox'){
                let value = input.value
                input.setAttribute("value", value)
            }
            else{
                if(input.checked) input.setAttribute("checked", "")
                else{input.removeAttribute("checked")}
            }
        })
        document.querySelectorAll('textarea').forEach(textarea =>{
            textarea.innerHTML = textarea.value
        })
        document.querySelectorAll('select').forEach(select =>{
            const selectedOption = select.options[select.selectedIndex];
            let options = select.querySelectorAll('option')
            options.forEach(option =>{
                option.removeAttribute("selected")
            })
            selectedOption.setAttribute("selected","selected")
        })
        if(document.querySelector('.draftName')){
            document.querySelector('.draftName').value = prompt("введите название черновика")
        }
        let statement = document.querySelector('.statement')
        statement.value = document.getElementsByTagName("sss")[0].innerHTML
    })

}
