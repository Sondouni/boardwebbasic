var frm = document.querySelector('#frm');
if(frm){
    // function proc(){
    //     alert('전송');
    // } 해서 function()자리에 넣어주면 됨
    frm.addEventListener('submit',function(e){
        alert('전송');
        e.defaultPrevented();
    });
}