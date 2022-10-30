let count = document.querySelectorAll('.cellBio').length
function f1(sender){
    count++;
    sender.parentElement.parentElement.parentElement.querySelectorAll('.item, .bioMini').forEach(item=>{
        item.style.border = 'none'
    })


    document.querySelector('.bioRight').innerHTML += "<div class=\"bioAll fullBio\" pairing="+count+">\n" +
        "                                        <input oninput=\"this.setAttribute('value', this.value)\" type=\"text\" class=\"bioLabelInput\" sep =\"subLabel\" placeholder=\"введите название подраздела\">\n" +
        "                                        <div class=\"line2\"></div>\n" +
        "                                        <textarea oninput=\"this.innerHTML = this.value\" maxlength=\"120\" class=\"bioSourceInput\" sep =\"source\" placeholder=\"введите источник (не более 120 символов)\"></textarea>\n" +
        "                                        <textarea oninput=\"this.innerHTML = this.value\" placeholder=\"Введите текст подраздела\" sep =\"labelText\" class=\"bioTextInput\"></textarea>\n" +
        "                                    </div>"

    sender.parentElement.querySelector('.cells').innerHTML += "<div class=\"cellBio\"><div class=\"item\" style=\"border: 2px dotted white\" onclick='f3(this)' draggable=\"true\" pairing="+count+"><p>...</p><div class=\"btnDell\" onclick=\"btnRemove(this)\"></div></div></div>"
    test1()
    document.querySelectorAll('.bioAll input').forEach(input=>{
        input.addEventListener('input', function (){
            let attr = this.parentElement.getAttribute('pairing')
            document.querySelector('.item[pairing="'+attr+'"] p').innerHTML = input.value



        })
    })
}




function btnRemove(sender){
    let check = prompt("введите ДА, чтобы удалить подраздел")
    if(check === 'ДА') {
        let count = sender.parentElement.getAttribute('pairing')
        if(sender.parentElement.parentElement.previousElementSibling === null && sender.parentElement.parentElement.nextElementSibling === null){

        }
        else if(sender.parentElement.parentElement.previousElementSibling === null){
            f3(sender.parentElement.parentElement.nextElementSibling.firstChild)
        }
        else{
            f3(sender.parentElement.parentElement.previousElementSibling.firstChild)
        }

        document.querySelector('.bioAll[pairing="'+count+'"]').remove()
        sender.parentElement.parentElement.remove()

    }

}



function f3(sender){

    document.querySelector('.bioMini').style.border = 'none'
    sender.parentElement.parentElement.parentElement.parentElement.querySelectorAll('.item').forEach(item =>{
        item.style.border = 'none'

    })
    sender.style.border = '2px dotted white'

    document.querySelectorAll('.bioAll').forEach(frame =>{
        if(frame.getAttribute('pairing')!==sender.getAttribute('pairing')){
            frame.style.display = 'none'
        }
        else{
            frame.style.display = 'block'
        }
    })


}

function f2(sender){

    sender.parentElement.parentElement.parentElement.innerHTML += "<div class=\"cont\">\n" +
        "    <div class=\"mainC\"><input type=\"text\" placeholder=\"введите название раздела\" oninput=\"this.setAttribute('value', this.value)\"><div class=\"btnLabelDell\" onclick=globalDell(this)></div></div>\n" +
        "    <div class=\"cells\">\n" +
        "    </div>\n" +
        "    <div class=\"addBtn\" onclick=f1(this)></div>\n" +
        "</div>"
}

function globalDell(sender){
    let check = prompt("введите ДА, чтобы удалить раздел со всеми подразделами")
    if(check === 'ДА') {
        sender.parentElement.parentElement.querySelectorAll('.item').forEach(item=>{
            let count = item.getAttribute('pairing')
            document.querySelector('.bioAll[pairing="'+count+'"]').remove()
        })
        let pp = sender.parentElement.parentElement.previousElementSibling.querySelectorAll('.item')
        pp[pp.length-1].style.border = '2px dotted white'
        let count = pp[pp.length-1].getAttribute('pairing')
        document.querySelector('.bioAll[pairing="'+count+'"]').style.display = 'block'

        sender.parentElement.parentElement.remove()


    }

}




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
