var cmtListContainerElem = document.querySelector('#cmtListContainer');



function getList(){
    var url1 = '/board/cmt?iboard='+cmtListContainerElem.dataset.iboard;
    console.log(url1);
    var url = `/board/cmt?iboard=${cmtListContainerElem.dataset.iboard}`;
    console.log(url);
    fetch(url).then(function(res){
        return res.json();
    }).then(function(data){
        console.log(data);
    });
}
getList();

function elt(name,attr){
    var node = document.createElement(name);
    if(attr){
        for(var item in attr){
            if(attr.hasOwnProperty(item)){//원래 가진 속성값인지(value,name...등 ) 새로 만들어낸 속성은 안됨
                node.setAttribute(item,attr[item]); // 노드에다가 속성을 추가
            }
        }
    }
    return node;
}