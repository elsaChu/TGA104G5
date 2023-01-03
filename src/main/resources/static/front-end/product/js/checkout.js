$(window).on("load", function () {
    // header驗證是否已登入
    function checklogin() {
        $.ajax({
            url: '../member/MemberServlet',
            method: 'POST',
            dataType: 'json',
            data: { action: 'checkLogin' },
            success: function (data) {
                // console.log(data);
                if (data.check == '2') {
                    $('#login_menuB').show();
                    $('#login_menuA').hide();
                } else {
                    $('#login_menuA').show();
                    $('#login_menuB').hide();
                }
            }
        });
    }
    checklogin();

    document.querySelector("#receiverName").value = sessionStorage.getItem("name");
    document.querySelector("#receiverTel").value = sessionStorage.getItem("phoneNumber");
    let address = sessionStorage.getItem("address"); 
    if(address){
        document.querySelector("#shippingAdd").value = sessionStorage.getItem("address");
    };

    var check_order = function () {
        const orderList = JSON.parse(
            sessionStorage.getItem("orderDetail")
        );
        // console.log(orderList);
        let myOrderStr = "";
        let myPayment = 0;
        const myorder = document.querySelector("#myorder");
        const payment = document.querySelector("#payment");
        for (let i = 0; i < orderList.length; i++) {
            myOrderStr += `
            <li>${orderList[i].prodName} ${orderList[i].prodSpec} - ${orderList[i].prodQty}
                <span>$ ${orderList[i].subtotal}</span>
            </li>
            `;
            myPayment += orderList[i].subtotal;
        }
        // console.log(str);
        myorder.innerHTML = myOrderStr;
        payment.innerHTML = `付款金額 <span>$ ${myPayment}</span>`;

    };
    check_order();

    const prodOrderVO = JSON.parse(sessionStorage.getItem("prodOrderVO"));
    console.log(prodOrderVO);

    // 收件人姓名不可為空白
    $("#receiverName").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;收件人姓名不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#receiverName").style.border = "1.5px solid #dd2222";
            return;
        }
        prodOrderVO.receiverName = $(this).val().trim();
        $(this).addClass("check_ok");
        document.querySelector("#receiverName").style.border = "";
    });

    // 收件地址不可為空白
    $("#shippingAdd").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;收件地址不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#shippingAdd").style.border = "1.5px solid #dd2222";
            return;
        }
        prodOrderVO.shippingAdd = $(this).val().trim();
        $(this).addClass("check_ok");
        document.querySelector("#shippingAdd").style.border = "";
    });

    // 驗證電話格式
    $("#receiverTel").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;手機號碼不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#receiverTel").style.border = "1.5px solid #dd2222";
            return;
        }
        let pattern = /^09\d{2}\d{6}$/;
        if (!pattern.test($(this).val())) {
            $(this).removeClass("check_ok");
            let error_message =
                "<span class='error_message'>&emsp;手機號碼格式錯誤</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#receiverTel").style.border = "1.5px solid #dd2222";
            return;
        }
        prodOrderVO.receiverTel = $(this).val();
        $(this).addClass("check_ok");
        document.querySelector("#receiverTel").style.border = "";
    });

    // StackOverflow的cc_formatter
    function cc_format(value) {
        var v = value.replace(/\s+/g, "").replace(/[^0-9]/gi, "");
        var matches = v.match(/\d{4,16}/g);
        var match = (matches && matches[0]) || "";
        var parts = [];

        for (i = 0, len = match.length; i < len; i += 4) {
            parts.push(match.substring(i, i + 4));
        }

        if (parts.length) {
            return parts.join("   ");
        } else {
            return value;
        }
    }

    // 不讓使用者輸入數字以外的東西
    $("#creditCard").keypress(function (e) {
        if ((e.which < 48 || e.which > 57) && e.which !== 8 && e.which !== 0) {
            return false;
        }
        return true;
    });

    $("#creditCard").on("keyup", function () {
        $(this).val(cc_format($(this).val()));
        // console.log($(this).val());
        // console.log($(this).val().replace(/ +/g, ""));
    });

    // 持卡人姓名不可為空白
    $("#cardName").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;持卡人姓名不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#cardName").style.border = "1.5px solid #dd2222";
            return;
        }

        $(this).addClass("check_ok");
        document.querySelector("#cardName").style.border = "";
    });

    // 信用卡卡號格式確認
    $("#creditCard").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#creditCard").style.border = "1.5px solid #dd2222";
            return;
        }
        let pattern = /^\d{16}$/;
        if (!pattern.test($(this).val().replace(/ +/g, ""))) {
            // console.log($(this).val().replace(/ +/g, ""));
            // console.log(!pattern.test($(this).val().replace(/ +/g, "")))
            $(this).removeClass("check_ok");
            let error_message =
                "<span class='error_message'>&emsp;信用卡卡號格式錯誤</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#creditCard").style.border = "1.5px solid #dd2222";
            return;
        }
        $(this).addClass("check_ok");
        document.querySelector("#creditCard").style.border = "";
    });

    // cc_formatter用上面的cc_formatter去改的
    function expire_date_format(value) {
        var v = value.replace(/\s+/g, "").replace(/[^0-9]/gi, "");
        var matches = v.match(/\d{2,4}/g);
        var match = (matches && matches[0]) || "";
        var parts = [];

        for (i = 0, len = match.length; i < len; i += 2) {
            parts.push(match.substring(i, i + 2));
        }

        if (parts.length) {
            return parts.join(" / ");
        } else {
            return value;
        }
    }

    // 不讓使用者輸入數字以外的東西
    $("#expireDate").keypress(function (e) {
        if ((e.which < 48 || e.which > 57) && e.which !== 8 && e.which !== 0) {
            return false;
        }
        return true;
    });

    $("#expireDate").on("keyup", function () {
        $(this).val(expire_date_format($(this).val()));
        // console.log($(this).val());
        // console.log($(this).val().split(" / ").join(""));
    });

    // 確認有效期限格式
    $("#expireDate").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#expireDate").style.border = "1.5px solid #dd2222";
            return;
        }
        let pattern = /^0|^11|^12/;
        if (!pattern.test($(this).val().split(" / ").join(""))) {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;格式錯誤</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#expireDate").style.border = "1.5px solid #dd2222";
            return;
        }
        $(this).addClass("check_ok");
        document.querySelector("#expireDate").style.border = "";
    });

    // 不讓使用者輸入數字以外的東西
    $("#cvv").keypress(function (e) {
        if ((e.which < 48 || e.which > 57) && e.which !== 8 && e.which !== 0) {
            return false;
        }
        return true;
        console.log();
    });

    // $("#security_code").on("keyup", function(){
    //   console.log($(this).val());
    // });

    // 確認安全碼格式
    $("#cvv").on("blur", function () {
        $(this).siblings().children(".error_message").remove();
        if ($(this).val().trim() == "") {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;不可空白</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#cvv").style.border = "1.5px solid #dd2222";
            return;
        }
        let pattern = /^\d{3}$/;
        if (!pattern.test($(this).val())) {
            $(this).removeClass("check_ok");
            let error_message = "<span class='error_message'>&emsp;格式錯誤</span>";
            $(this).siblings().append(error_message);
            document.querySelector("#cvv").style.border = "1.5px solid #dd2222";
            return;
        }
        $(this).addClass("check_ok");
        document.querySelector("#cvv").style.border = "";
    });

    // 送出訂單
    document.querySelector("#sitebtn").addEventListener("click", function () {
        const prodOrderVO = JSON.parse(sessionStorage.getItem("prodOrderVO"));
        console.log(prodOrderVO);

        let receiverName_el = document.querySelector("#receiverName").value.trim();
        let receiverTel_el = document.querySelector("#receiverTel").value.trim();
        let shippingAdd_el = document.querySelector("#shippingAdd").value.trim();
        let orderNotes_el = document.querySelector("#orderNotes").value.trim();

        prodOrderVO.receiverName = receiverName_el;
        prodOrderVO.receiverTel = receiverTel_el;
        prodOrderVO.shippingAdd = shippingAdd_el;
        prodOrderVO.orderNotes = orderNotes_el;
        sessionStorage.setItem("prodOrderVO", JSON.stringify(prodOrderVO));

        const orderDetailList = JSON.parse(sessionStorage.getItem("orderDetail"));

        const insertOrder = {
            "productOrderVO": prodOrderVO,
            "orderDetailList": orderDetailList
        }
        if ($("#receiverName").hasClass("check_ok") &&
            $("#receiverTel").hasClass("check_ok") &&
            $("#shippingAdd").hasClass("check_ok") &&
            $("#cardName").hasClass("check_ok") &&
            $("#creditCard").hasClass("check_ok") &&
            $("#expireDate").hasClass("check_ok") &&
            $("#cvv").hasClass("check_ok")) {
            console.log("ok");

            fetch("../../order/addProdOrder", {
                method: "POST",
                body: JSON.stringify(insertOrder),
                headers: { "content-type": "application/json" },
            })
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
                        console.log("bbbbb")
                        return r.json();
                    }
                })
                .then((data) => {
                    console.log("a"+data);
                    // .then((data) => {
                    //   if (data.message == "Insert Success") {
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "訂單成立",
                        showConfirmButton: false,
                        timer: 1000,
                    }).then(() => {
                        sessionStorage.removeItem('orderDetail');
                        sessionStorage.removeItem('prodOrderVO');
                        sessionStorage.removeItem('URL_before_login');
                        window.location.href = "../member/order.html";
                    });
                    //   } else {
                    //     Swal.fire({
                    //       position: "center",
                    //       icon: "error",
                    //       title: "訂單新增失敗",
                    //       showConfirmButton: false,
                    //       timer: 1000,
                    //     }).then(() => {
                    //       window.location.href = "./shoppingcart.html";
                    //     });
                    //   }
                });
        } else {
            // console.log("資料不完整");
            if (prodOrderVO.receiverName == "") {
                let error_message = "<span class='error_message'>&emsp;收件人姓名不可空白</span>";
                $("#receiverName").siblings().append(error_message);
                document.querySelector("#receiverName").style.border = "1.5px solid #dd2222";
            }
            if (prodOrderVO.receiverTel == "") {
                let error_message = "<span class='error_message'>&emsp;手機號碼不可空白</span>";
                $("#receiverTel").siblings().append(error_message);
                document.querySelector("#receiverTel").style.border = "1.5px solid #dd2222";
            }
            if (prodOrderVO.shippingAdd == "") {
                let error_message = "<span class='error_message'>&emsp;收件地址不可空白</span>";
                $("#shippingAdd").siblings().append(error_message);
                document.querySelector("#shippingAdd").style.border = "1.5px solid #dd2222";
            }
            if ($("#creditCard").val().trim() == "") {
                let error_message = "<span class='error_message'>&emsp;信用卡號不可空白</span>";
                $("#creditCard").siblings().append(error_message);
                document.querySelector("#creditCard").style.border = "1.5px solid #dd2222";
            }
            if ($("#cardName").val().trim() == "") {
                let error_message = "<span class='error_message'>&emsp;持卡人姓名不可空白</span>";
                $("#cardName").siblings().append(error_message);
                document.querySelector("#cardName").style.border = "1.5px solid #dd2222";
            }
            if ($("#expireDate").val().trim() == "") {
                let error_message = "<span class='error_message'>&emsp;有效期不可空白</span>";
                $("#expireDate").siblings().append(error_message);
                document.querySelector("#expireDate").style.border = "1.5px solid #dd2222";
            }
            if ($("#cvv").val().trim() == "") {
                let error_message = "<span class='error_message'>&emsp;安全碼不可空白</span>";
                $("#cvv").siblings().append(error_message);
                document.querySelector("#cvv").style.border = "1.5px solid #dd2222";
            }
        }
    });



    //jquery寫法

    // $("#sitebtn").on("click", function () {
    //     console.log(prodOrderVO);
    //     sessionStorage.setItem("prodOrderVO", JSON.stringify(prodOrderVO));

    //     prodOrderVO.receiverName = $("#receiverName").val().trim();
    //     prodOrderVO.receiverTel = $("#receiverTel").val().trim();
    //     prodOrderVO.shippingAdd = $("#shippingAdd").val().trim();

    //     sessionStorage.setItem("prodOrderVO", JSON.stringify(prodOrderVO));

    //     const orderDetailList = JSON.parse(sessionStorage.getItem("orderDetail"));

    //     const insertOrder = {
    //         "productOrderVO": prodOrderVO,
    //         "orderDetailList": orderDetailList
    //     }
    //     // console.log(insertOrder);
    //     if( $("#receiverName").hasClass("check_ok") &&
    //     $("#receiverTel").hasClass("check_ok") &&
    //     $("#shippingAdd").hasClass("check_ok") &&
    //     $("#cardName").hasClass("check_ok") &&
    //     $("#creditCard").hasClass("check_ok") &&
    //     $("#expireDate").hasClass("check_ok") &&
    //     $("#cvv").hasClass("check_ok")){
    //         console.log("OK");
    //         $.ajax({
    //             url: "../../order/addProdOrder",
    //             type: "POST",
    //             contentType: "application/json",
    //             data: JSON.stringify(insertOrder),
    //             dataType: "json",
    //             success: function (a) {
    //                 console.log(a);
    //                 window.location.href = "../member/order.html";
    //             },
    //             error: function (xhr) {         // request 發生錯誤的話執行
    //                 console.log(xhr);
    //                 // Swal.fire({
    //                 //     position: "center",
    //                 //     icon: "error",
    //                 //     title: "訂單新增失敗",
    //                 //     showConfirmButton: false,
    //                 //     timer: 1000,
    //                 // }).then(() => {
    //                     window.location.href = "./shoppingcart.html";
    //                 // });
    //             },
    //             // complete: () => {
    //             //     Swal.fire({
    //             //         position: "center",
    //             //         icon: "success",
    //             //         title: "訂單成立",
    //             //         showConfirmButton: false,
    //             //         timer: 1000,
    //             //     }).then(() => {
    //             //         location.href = "../member/order.html";
    //             //     });
    //             // }
    //         });
    // }
    // else{
    //     console.log("資料不完整");
    //     // 增加錯誤框框
    //     if(prodOrderVO.receiverName == ""){
    //         // $("#receiverName").removeClass("check_ok");
    //         let error_message = "<span class='error_message'>&emsp;姓名不可空白</span>";
    //         $("#receiverName").siblings().append(error_message);
    //         document.querySelector("#receiverName").style.border = "1.5px solid #dd2222";
    //         // return;
    //     }
    // }

});











