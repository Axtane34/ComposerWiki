let texts = document.querySelectorAll('p')
    texts.forEach(text =>{
        text.innerHTML = text.innerHTML.replaceAll('_', ' ')
    })
