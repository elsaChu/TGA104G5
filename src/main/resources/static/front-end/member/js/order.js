function init() {
  const order = document.querySelector("#order");

  fetch(`../../order/orderlist?number=`)
    .then((r) => {
      if (r.redirected) {
        Swal.fire({
          position: "center",
          icon: "warning",
          title: "請先登入",
          showConfirmButton: false,
          timer: 1000,
        }).then(() => {
          sessionStorage.setItem("URL_before_login", window.location.href);
          window.location.href = r.url;
        });
      } else {
        return r.json();
      }
    })
    .then((data) => {
      order.innerHTML =
        data.map((e) => Template(e.prodOrderNo, e.amountPrice, e.paymentDate, e.prodOrderStatus, e.deliveryStatus)).join('');
    })


}

window.onload = init;

function Template(prodOrderNo, amountPrice, paymentDate, prodOrderStatus, deliveryStatus) {
  return `
    <tr>
    <td># ${prodOrderNo}</td>
    <td>$${amountPrice}</td>
    <td>${paymentDate}</td>
    <td>${prodOrderStatus}</td>
    <td>${deliveryStatus}</td>
    <td>
      <div class="" id="button1"><input type="submit" value="查看" onclick="showDetail(${prodOrderNo})" /></div>
    </td>
  </tr>
    `;
}

function showDetail(prodOrderNo) {
  sessionStorage.setItem('prodOrderNo', prodOrderNo);
  location = './orderdetail.html';
}

