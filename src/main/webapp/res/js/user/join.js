function joinChk(){
    var frm = document.querySelector('#frm');
    if(frm.uid.value.length<5){
        alert('please type id more than 5 characters');
        return false;
    }else if(frm.upw.value.length<5){
        alert('please type pw more than 5 characters');
        return false;
    }else if(frm.upw.value !== frm.reupw.value) {
        alert('please type same pw');
        return false;
    }else if(frm.nm.value.length >5){
        alert('check your name. it should be not more than 5 characters');
        return false;
    }
    return true;
}