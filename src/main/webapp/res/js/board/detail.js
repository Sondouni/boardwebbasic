

function displayedAt(createdAt) {
    const milliSeconds = new Date() - createdAt
    const seconds = milliSeconds / 1000
    if (seconds < 60) return `방금 전`
    const minutes = seconds / 60
    if (minutes < 60) return `${Math.floor(minutes)}분 전`
    const hours = minutes / 60
    if (hours < 24) return `${Math.floor(hours)}시간 전`
    const days = hours / 24
    if (days < 7) return `${Math.floor(days)}일 전`
    const weeks = days / 7
    if (weeks < 5) return `${Math.floor(weeks)}주 전`
    const months = days / 30
    if (months < 12) return `${Math.floor(months)}개월 전`
    const years = days / 365
    return `${Math.floor(years)}년 전`
}
/*
var timee = new Date().toString();
console.log(new Date());
console.log(timee);
console.log(document.getElementById('rdt').innerText);
var rdt = document.querySelector('commentBox')
document.getElementById('time').value = displayedAt(document.getElementById('rdt').innerText);

function isDel(icmt,iboard){
    if(confirm('Are you sure to delete?')){
        location.href='board/cmt/del?icmt='+icmt+'&iboard='+iboard;
    }
}
/*
var changeCtnt = document.querySelector('#changeCtnt');
console.log(changeCtnt.dataset.ctnt);
console.log(changeCtnt.dataset.iboard);
console.log(changeCtnt.dataset.icmt);
console.log(changeCtnt.dataset.writer);
*/
var changeBtnList = document.querySelectorAll('.change');
changeBtnList.forEach(function(item) {
    item.addEventListener('click', function(e) {
        replaceModForm();

        var commentBoxElem = e.target.parentElement;
        console.log(commentBoxElem);
        var changeCtntElem = commentBoxElem.querySelector('.changeCtnt');
        console.log("박스안"+changeCtntElem.dataset.iboard);
        var rdtElem = commentBoxElem.querySelector('.rdt');
        var frm = document.createElement('form');
        frm.action = `/board/cmt/reg?icmt=${changeCtntElem.dataset.icmt}&iboard=${changeCtntElem.dataset.iboard}`;
        frm.method = 'post';
        frm.className = "changeInput"
        frm.style.width='200px';

        var inputCtnt = document.createElement('input');
        inputCtnt.type = 'text';
        inputCtnt.name = 'ctnt';
        inputCtnt.value = changeCtntElem.dataset.ctnt;

        var inputSubmit = document.createElement('input');
        inputSubmit.type = 'submit';
        inputSubmit.value = '수정';

        var inputCancel = document.createElement('input');
        inputCancel.type = 'button';
        inputCancel.value = '취소';

        inputCancel.addEventListener('click', function() {
            replaceModForm();
            //location.href=`/board/detail?iboard=${changeCtntElem.dataset.iboard}`;
        });

        frm.append(inputCtnt);
        frm.append(inputSubmit);
        frm.append(inputCancel);

        changeCtntElem.remove();
        rdtElem.insertAdjacentElement("beforebegin", frm);
    });
});
function replaceModForm() {
    var changeInputElem = document.querySelector('.changeInput');
    if(changeInputElem) {
        var parent = changeInputElem.parentElement;
        var ctnt = changeInputElem.ctnt.value;
        changeInputElem.remove();

        var navElem = document.createElement('nav');
        navElem.className = 'changeCtnt';
        navElem.innerText = ctnt;

        var rdtElem = parent.querySelector('.rdt');
        rdtElem.insertAdjacentElement('beforebegin', navElem);

    }
    console.log(changeInputElem);
}

function backOriginal(elem, ctnt) {
    elem.innerHTML = ctnt;
}
var cmtModContainerElem = document.querySelector('.cmtModContainer');
var btnCancelElem = cmtModContainerElem.querySelector('#btnCancel');
btnCancelElem.addEventListener('click',function (){
    cmtModContainerElem.style.display = 'none';
})
function openModForm(icmt,ctnt){
    if(cmtModContainerElem){
        cmtModContainerElem.style.display = 'flex';
        var cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm');
        cmtModFrmElem.icmt.value = icmt;
        cmtModFrmElem.ctnt.value = ctnt;
    }
    console.log('icmt : '+icmt);
    console.log('ctnt : '+ctnt);
}
