$(function () {
    // getSeat();
});

//select seat setting
function getSeat() {
    $.ajax({
        url: './seat',
        method: 'POST',
        dataType: 'json',
        data: { action: 'getSeat', eventNumber: $('#eventNumber').val() },
        success: function (data) {

            if (!data || !data.success) {
                alert('發生異常');
                return false;
            }
            let seatInfo = data.seatInfo;
            if (!seatInfo || seatInfo.length == 0) {
                //沒有座位設定
                alert('目前此場地沒有座位設定');
            } else {
                //產生當前座位設定
                $('#curX').val(data.x);
                $('#curY').val(data.y);
                genSeatTemplate(data);
            }
        }
    });
}

//create seat
function genSeatTemplate(data) {
    console.log('data:', data);
    let xVal, yVal;
    let seatInfo = [];
    if (data) {
        xVal = data.x;
        yVal = data.y;
        seatInfo = data.seatInfo;
    } else {
        try {
            xVal = new Number($('#xVal').val());
            yVal = new Number($('#yVal').val());
            if (xVal == 0 || yVal == 0) {
                throw 'X、Y軸參數錯誤';
            }
        } catch (e) {
            alert('X、Y軸參數錯誤');
            return false;
        }
    }

    $('#seatSettingTable').html('');
    let seatData = '';
    let seatIdCount = 1;
    for (let y = 0; y < yVal; y++) {
        let xTd;
        for (let x = 0; x < xVal; x++) {

            if (seatInfo.length > 0) {

                let hasNum = false;
                for (k = 0; k < seatInfo.length; k++) {
                    if (seatInfo[k]['seatSet'] == seatIdCount) {
                        hasNum = true;
                    }
                }

                if (hasNum) {
                    xTd += '<td><button id="seatId_' + seatIdCount + '" type="button" onclick="triggerSeatStatus(this);" class="btn btn-success seat seatActive"><img class="seatIcon" src="back-organizer-end/event/img/seat-2-128.png" /></button></td>';
                } else {
                    xTd += '<td><button id="seatId_' + seatIdCount + '" type="button" onclick="triggerSeatStatus(this);" class="btn btn-light seat"><img class="seatIcon" src="back-organizer-end/event/img/x.png" /></button></td>';
                }

            } else {
                xTd += '<td><button id="seatId_' + seatIdCount + '" type="button" onclick="triggerSeatStatus(this);" class="btn btn-success seat seatActive"><img class="seatIcon" src="back-organizer-end/event/img/seat-2-128.png" /></button></td>';
            }

            seatIdCount++;
        }
        seatData += '<tr>' + xTd + '</tr>';
    }
    $('#seatSettingTable').html(seatData);
    $('#curX').val(xVal);
    $('#curY').val(yVal);
}

//change seat type
function triggerSeatStatus(seat) {

    if ($(seat).hasClass('seatActive')) {
        $(seat).removeClass('seatActive')
            .removeClass('btn-success')
            .addClass('btn-light');
        $(seat).html('<img class="seatIcon" src="back-organizer-end/event/img/x.png" />');
    } else {
        $(seat).removeClass('btn-light')
            .addClass('btn-success')
            .addClass('seatActive');
        $(seat).html('<img class="seatIcon" src="back-organizer-end/event/img/seat-2-128.png" />');
    }

}

//save seat
function saveSeatTemplate(seat) {

    let seatIdList = [];
    xVal = new Number($('#xVal').val());
    yVal = new Number($('#yVal').val());
    $.each($('.seat.seatActive'), function () {
        console.log($(this));
        let seatId = $(this).attr('id').replace('seatId_', '');
        console.log(seatId);
        seatIdList.push(seatId);
    });
    let data = {
        action: 'save',
        seatIdList: seatIdList.join(),
        xVal: $('#curX').val(),
        yVal: $('#curY').val(),
        eventNumber: $('#eventNumber').val(),
        otherPageData: $('#otherPageData').val()
    };
    console.log(data);

    $.ajax({
        url: './AddEvent2Servlet',
        method: 'POST',
        data: data,
        success: function (data) {
            if (data.success) {
                alert('儲存完畢');
            } else {
                alert('發生異常');
            }
        }
    });
}