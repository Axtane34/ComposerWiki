
function f1(sender){
    sender.parentElement.querySelector('.cells').innerHTML += "<div class=\"cellBio\"><div class=\"item\" draggable=\"true\"><p>подраздел</p><div class=\"btnDell\" onclick=\"this.parentElement.parentElement.remove()\"></div></div></div>"
    test1()
}

function f2(sender){
    sender.parentElement.parentElement.parentElement.innerHTML += "<div class=\"cont\">\n" +
        "    <div class=\"mainC\"><input type=\"text\" placeholder=\"введите название раздела\"><div class=\"btnLabelDell\" onclick=\"this.parentElement.parentElement.remove()\"></div></div>\n" +
        "    <div class=\"cells\">\n" +
        "        <div class=\"cellBio\"><div class=\"item\" draggable=\"true\"><p>подраздел</p><div class=\"btnDell\" onclick=\"this.parentElement.parentElement.remove()\"></div></div></div>\n" +
        "    </div>\n" +
        "    <div class=\"addBtn\" onclick=f1(this)></div>\n" +
        "</div>"
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
