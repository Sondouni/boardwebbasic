function moveToDetail(iboard,writer){
    if(iboard){
        console.log('iboard : '+iboard)
        location.href = "/board/detail?iboard="+iboard+"&writer="+writer;
    }
}