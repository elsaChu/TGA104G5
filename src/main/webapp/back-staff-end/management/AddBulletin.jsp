<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bulletin</title>
</head>
<body>
<h1>Create New Bulletin </h1>
<form method="post" action="<%=request.getContextPath()%>/BulletinServlet">
<!-- <form method="post" action="#"> -->
	<input type="hidden" name="doTask" value="insert">
	<label>Bulletin Name </label>
	<input type="text" name="bulletinName" >
	<br>
	<label>Bulletin Content</label>
	<br>
	<textarea id="" name="bulletinContent" rows="4" cols="50"></textarea>
	<br>
	<br>
	<br>
	<label>Pin to Top: </label>
	<input type="radio" name="isTop" value="yes"/>Yes
	<input type="radio" name="isTop" value="no"/>No	
	
	<br>
	<label>Activate Bulletin: </label>
	<input type="radio" name="isOpen" value="yes"/>Activate
	<input type="radio" name="isOpen" value="no"/>Do Not Activate	
	<br>
  
	<label>Bulletin date </label>
	<br>
    請選擇日期: <input  name="bulletinDate" id="f_date1" type="text" >  <!-- f_date1見第32行  -->
    <br>
	<input type="submit" value="Create">
	</form>
</body>
<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" /> 
<script src="datetimepicker/jquery.js"></script>
<%--  <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>  --%>
 <script src="datetimepicker/jquery.datetimepicker.full.js"></script> 
<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: true,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i:s',
	       value: new Date(),
           //disabledDates:    ['2022/06/08','2022/06/09','2022/06/10'], // 去除特定不含
           //startDate:	        '2022/07/10',  // 起始日
           //minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2022-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2022-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2022-06-15');
        //      var somedate2 = new Date('2022-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>