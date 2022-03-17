let commentLink = document.querySelector('.comment');
let disableButton = document.querySelector('.disableButton');

function disableLink(){
    commentLink.style.pointerEvents = 'none'
    commentLink.style.cursor = 'default'
};

console.log("asd")

disableButton.addEventListener("click", ()=> {
    disableLink()
    console.log('fsdfasfa')
})