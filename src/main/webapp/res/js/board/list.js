function moveToDetail(iboard,writer){
    if(iboard){
        console.log('iboard : '+iboard)
        location.href = "/board/detail?iboard="+iboard+"&writer="+writer;
    }
}
let list = document.querySelector('#boardTable');

function replace(str){
    return str.replaceAll();
}

