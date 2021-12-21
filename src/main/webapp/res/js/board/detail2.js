var cmtListContainerElem = document.querySelector('#cmtListContainer');
cmtListContainerElem.style.display = 'flex';
cmtListContainerElem.style.flexDirection = 'column';
var cmtModContainerElem = document.querySelector('.cmtModContainer');
var btnCancelElem = cmtModContainerElem.querySelector('#btnCancel');
var cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm');
var submitBtnElem = cmtModFrmElem.querySelector('input[type=submit]');

submitBtnElem.addEventListener('click',function (e){
    e.preventDefault();
   var url = '/board/cmt?proc=upd';
   var chgCtnt = cmtModFrmElem.ctnt.value;
   var param = {
       'icmt':cmtModFrmElem.icmt.value,
       'ctnt':chgCtnt
   };
   fetch(url,{
       'method':'POST',
       'headers':{'Content-Type':'application/json'},
       'body':JSON.stringify(param)
   }).then(function (res){
       return res.json();
   }).then(function (data){
       console.log('data : '+data.result);

       // var cmtCtntElem = document.querySelector('#cmtCtnt');
       // cmtCtntElem.value = chgCtnt;
        switch (data.result){
            case 0:
                alert('댓글수정실패');

                break;
            case 1:
                modCtnt(param.ctnt);
                var e = new Event('click');
                btnCancelElem.dispatchEvent(e);
                break;
        }
       cmtModContainerElem.style.display = 'none';

   }).catch(function (err){
       console.log(err);
   });

});

function getList(){
    var url1 = '/board/cmt?iboard='+cmtListContainerElem.dataset.iboard;
    console.log(url1);
    var url = `/board/cmt?iboard=${cmtListContainerElem.dataset.iboard}`;
    console.log(url);
    fetch(url).then(function(res){
        return res.json();
    }).then(function(data){
        setDisplay2(data);
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




function setDisplay2(data){
    var tableElem = document.createElement('table');
    var iuser = parseInt(`${cmtListContainerElem.dataset.iuser}`);
    var loginUserPk = cmtListContainerElem.dataset.iuser===''? 0 : Number(cmtListContainerElem.dataset.iuser);
    var iboard = parseInt(`${cmtListContainerElem.dataset.iboard}`);

    tableElem.innerHTML = `
        <tr>
                <th>comment</th>
                <th>writer</th>
                <th>writeTime</th>
                <th>etc</th>
        </tr>
    `; //템플릿 리터널
    cmtListContainerElem.appendChild(tableElem);
    data.forEach(function (item){//item은 foreach돌때마다 각각 객체
        var tr = document.createElement('tr');
        var ctnt = item.ctnt.replaceAll('<','&lt;').replaceAll('>','&gt;');
        tr.innerHTML = `
            <td id="cmtCtnt">${ctnt}</td>
            <td>${item.writerNm}</td>
            <td>${item.rdt}</td>
        `;
        tableElem.appendChild(tr);
        var lastTd = document.createElement('td');
        if(iuser===item.writer){
            var btnMod = document.createElement('button');
            btnMod.innerText = 'change';
            btnMod.addEventListener('click',function (e){
                tr.classList.add('cmt_selected');
                var ctnt = tr.children[0].innerText;
                openModForm(item.icmt,ctnt);//구조분해할당
                /* 혼자해보는 수정/삭제
                tr.innerHTML = `
                    <form action="/board/cmt/mod" method="post">
                        <td><input type="text" value="${ctnt}"></td>
                        <td>${item.writerNm}</td>
                        <td>${item.rdt}</td>
                    </form>
                 `;
                var btnSub = document.createElement('button');
                btnSub.addEventListener('submit',function (e){
                    tr.innerHTML = `
                                        <td>${ctnt}</td>
                                        <td>${item.writerNm}</td>
                                        <td>${item.rdt}</td>
                                    `;
                });
                lastTd.appendChild(btnSub);

                 */
            });
            var btnDel = document.createElement('button');
            btnDel.innerText = 'delete';

            lastTd.appendChild(btnMod);
            lastTd.appendChild(btnDel);
        };
        tr.appendChild(lastTd);

    });
}

btnCancelElem.addEventListener('click',function (){
    cmtModContainerElem.style.display = 'none';
    var selectedTrElem = document.querySelector('.cmt_selected');
    selectedTrElem.classList.remove('cmt_selected');
});

function modCtnt(ctnt){
    var selectedTrElem = document.querySelector('.cmt_selected');
    var tdCtntElem = selectedTrElem.children[0];
    tdCtntElem.innerText = ctnt;
}


function openModForm(icmt, ctnt){
    if(cmtModContainerElem){
        cmtModContainerElem.style.display = 'flex';

        cmtModFrmElem.icmt.value = icmt;
        cmtModFrmElem.ctnt.value = ctnt;
    }
    console.log('icmt : '+icmt);
    console.log('ctnt : '+ctnt);
}

function setDisplay(data){
    var containerElem = document.querySelector('#container');
    console.log(data);
    var table = elt('table');
    table.style.borderCllapse = 'collapse';
    table.style.marginTop = '5px';

    var tr = elt('tr');

    var th1= document.createElement('th');
    th1.innerText='content';
    var th2= document.createElement('th').innerText='writer';
    th2.innerText='writer';
    var th3= document.createElement('th').innerText='writeTime';
    th3.innerText='writeTime';
    var th4= document.createElement('th').innerText='etc';
    th4.innerText='etc';
    /*
    data.forEach(function(item){
        var divBox = document.createElement('div');
        divBox.style.display = 'flex';
        divBox.style.flexDirection = 'row';
        var divRdt = document.createElement('div');
        var divCtnt = document.createElement('div');
        var divWriterNm = document.createElement('div');
        divRdt.innerHTML = `   작성시간 : ${item.rdt}`;
        divCtnt.innerHTML = `${item.ctnt}  `;
        divWriterNm.innerHTML = `  작성자 : ${item.writerNm}  `;

        divBox.append(divCtnt);
        divBox.append(divWriterNm);
        divBox.append(divRdt);
        containerElem.append(divBox);
        //appendChild 는 노드를 못넣는다
    });

     */
    console.log(item);
    data.forEach(function(item){
        var trCmt = document.createElement('tr');
        for(var i =0;i<item.length;i++){
            var td = elt('td');

        }
    });
}

