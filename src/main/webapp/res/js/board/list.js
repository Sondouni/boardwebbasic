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
/*
let rowCtnVal = document.querySelector('#rowCtn1');
if(rowCtnVal){
    rowCtnVal.addEventListener('change',function (e){
        console.log(rowCtnVal.value);
        location.href = "/board/list?page=1&searchType=${param.searchType}&searchText=${param.searchText}&rowCnt="+rowCtnVal.value;
    });
}
/*
function rowCnt1(num){
    let howManyPage = rowCtnVal.option.value;
    if(howManyPage>5) {
        location.href = "/board/list?page="+num+"&searchType=${param.searchType}&searchText=${param.searchText}&rowCnt="+howManyPage;
    }
}

 */

var searchFrmElem = document.querySelector('#searchFrm');
if(searchFrmElem){
    searchFrmElem.rowCnt.addEventListener('change',function (e){
        searchFrmElem.submit();
    });
}


var frmList = document.querySelector('#frmList');
if(searchFrmElem){
    frmList.rowCnt.addEventListener('change',function (e){
        frmList.submit();
    });
}

