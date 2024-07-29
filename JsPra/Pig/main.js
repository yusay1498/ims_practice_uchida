const slide = document.querySelector('.slide')
const slideItems = document.querySelectorAll('.slide-item')
let count = 0
slideItems[0].classList.add('appear')
setInterval(() => {
    slideItems[count].classList.remove('appear')
    if (count === slideItems.length - 1) {
        count = 0
        slideItems[count].classList.add('appear')
    } else {

        slideItems[++count].classList.add('appear')
    }
}, 2000)