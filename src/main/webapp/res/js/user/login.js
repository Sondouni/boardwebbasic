var frm = document.querySelector('#frm');
if(frm){
    // function proc(){
    //     alert('전송');
    // } 해서 function()자리에 넣어주면 됨
    frm.addEventListener('submit',function(e){
        if(5>frm.uid.value.length || frm.uid.value.length>20){
            alert("id should be between 5~20 characters")
            e.preventDefault();
        }else if(frm.upw.value.length<5){
            alert("pw should be over than 5 characters")
            e.preventDefault();
        }
    });
}

var btn = document.querySelector('#btnHide');
/*
if(btn){
    btn.addEventListener('button',function (e){

    });
}

 */
function btnclick(){
    if(frm.upw.type==='text'){
        frm.upw.type = 'password';
        btn.value = 'show password'
    }else if(frm.upw.type==='password'){
        frm.upw.type = 'text';
        btn.value = 'hide password'
    }
}
var divall = document.querySelectorAll('div');
console.log(divall);