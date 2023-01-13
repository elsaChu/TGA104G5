
function init(){
  
    const prodOrderNo = sessionStorage.getItem('prodOrderNo');
    sessionStorage.removeItem('prodOrderNo');
    const orderdetail = document.querySelector("#orderdetail");
    const prodinfo1 = document.querySelector("#prodinfo1");
    const prodinfo2 = document.querySelector("#prodinfo2");

    // 訂單明細
    $.ajax({
        url: "../../order/orderdetail",                                  // 資料請求的網址
        type: "GET",                                                     // GET | POST | PUT | DELETE | PATCH
        data: { "prodOrderNo": prodOrderNo },                            // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",                                                // 預期會接收到回傳資料的格式： json | xml | html
        success: function(data){                                         // request 成功取得回應後執行
          console.log(data);
          orderdetail.innerHTML = 
          data.map((e) => Template1(e.prodName, e.prodSpec, e.prodQty, e.subtotal, e.commentRanking, e.commentDate, e.returnReason, e.refundSDate, e.refundStatus, e.refundEDate)).join('')
        },
      });

    // 訂單資訊
      $.ajax({
        url: "../../order/info",                                         // 資料請求的網址
        type: "GET",                                                     // GET | POST | PUT | DELETE | PATCH
        data: { "prodOrderNo": prodOrderNo },                            // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",                                                // 預期會接收到回傳資料的格式： json | xml | html
        success: function(data){                                         // request 成功取得回應後執行
          console.log(data);
          prodinfo1.innerHTML = Template2(data);
          prodinfo2.innerHTML = Template3(data);
        },
      });  
}

window.onload = init;

function Template1(prodName, prodSpec, prodQty, subtotal, commentRanking, commentDate, returnReason, refundSDate, refundStatus, refundEDate){
    return `
    <tr>
        <th>${prodName}</th>
        <th>${prodSpec}</th>
        <th>${prodQty}</th>
        <th>$${subtotal}</th>
        <th>${commentRanking ?? ''}</th>
        <th>${commentDate ?? ''}</th>
        <th>${returnReason ?? ''}</th>
        <th>${refundSDate ?? ''}</th>
        <th>${refundStatus ?? ''}</th>
        <th>${refundEDate ?? ''}</th>
        <th>
            <div class="">
            <input type="submit" value="評價" />
            </div>
        </th>
        <th>
            <div class="">
                <input type="submit" value="退貨" />
            </div>
        </th>
    </tr>
    `;
}

function Template2({prodOrderNo, name, phoneNumber, email, paymentDate, prodOrderStatus}){
    return `
    <p># ${prodOrderNo}</p>
    <p>${name}</p>
    <p>${phoneNumber}</p>
    <p>${email}</p>
    <p>${paymentDate}</p>
    <p>${prodOrderStatus}</p>
    `;
}

function Template3({receiverName, receiverTel, shippingAdd, deliveryStatus, orderNotes}){
    return `
    <p>${receiverName}</p>
    <p>${receiverTel}</p>
    <p>${shippingAdd}</p>
    <p>${deliveryStatus}</p>
    <p>${orderNotes ?? ''}</p>
    `;
}

