function init(){
    const order = document.querySelector("#order");
    $.ajax({
        url: "http://localhost:8080/TGA104G5/order/orderlist",           // 資料請求的網址
        type: "GET",                                                     // GET | POST | PUT | DELETE | PATCH
        // data: { "number": number },                                      // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",                                                // 預期會接收到回傳資料的格式： json | xml | html
        success: function(data){                                         // request 成功取得回應後執行
          console.log(data);
          order.innerHTML = 
          data.map((e) => Template(e.prodOrderNo, e.amountPrice, e.paymentDate, e.prodOrderStatus, e.deliveryStatus)).join('')
        },
   
      });


    
}

window.onload = init;

function Template(prodOrderNo, amountPrice, paymentDate, prodOrderStatus, deliveryStatus){
    return `
    <tr>
    <td>${prodOrderNo}</td>
    <td>${amountPrice}</td>
    <td>${paymentDate}</td>
    <td>${prodOrderStatus}</td>
    <td>${deliveryStatus}</td>
    <td>
      <div class="" id="button1"><input type="submit" value="檢視" onclick="showDetail(${prodOrderNo})" /></div>
    </td>
  </tr>
    `;
}

function showDetail(prodOrderNo) {
    sessionStorage.setItem('prodOrderNo', prodOrderNo);
    location = 'orderdetail.html#tab-4';
}

