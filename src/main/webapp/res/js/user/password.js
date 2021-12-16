var frmElem = document.querySelector('#frm');
var submitBtnElem = document.querySelector('#submitBtn');
var resetBtn = document.querySelector('#resetBtn');

console.log(submitBtnElem.dataset.curupw);
console.log(submitBtnElem.dataset.curuid);
console.log(submitBtnElem.dataset.curiuser);
submitBtnElem.addEventListener('click',function (){
    var curPw = frmElem.upw;
    var newPw = frmElem.changedupw;
    var newPwCf = frmElem.changedupwComfirm;
    if(curPw.value.length<5 || newPw.value.length<5){
        alert('please type more than 5');
    }else {
        if(newPw.value!=newPwCf.value){
            alert('please type same Password');
        }else {
            console.log(curPw.value)
            console.log(newPw.value)
            console.log(newPwCf.value)
            frmElem.submit();
        }
    }
});

