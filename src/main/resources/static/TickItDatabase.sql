#------------- create database --------------
CREATE DATABASE IF NOT EXISTS TICK_IT;

use TICK_IT;
#--------------- create table ----------------
#1 隱私權政策
create table PRIVACY(
	privacyID			int not null auto_increment comment '隱私權編號' primary key,
	privacyTitle		varchar(50) comment '聯絡標題',
    privacyContent		text not null comment '聯絡內容',
    privacyCreateDate	timestamp default current_timestamp comment '建立時間'
) comment '隱私權政策';

#2 員工
create table STAFF(
	staffNumber		int not null auto_increment comment '員工編號' primary key,
    staffName		varchar(21) not null comment '員工姓名',
    staffAccount	varchar(20) not null comment '員工帳號',
    staffPassword	varchar(100) not null comment '員工密碼',
    
	unique key UK_STAFF_staffAccount (staffAccount)
)comment '員工';


#3 權限
create table PERMISSION(
	permissionNumber	int not null auto_increment comment '權限編號' primary key,
    permissionName		varchar(20) not null comment '權限名稱',
    
    unique key UK_PERMISSION_permissionName (permissionName)
)comment'權限';


#4 員工權限
create table S_PERMISSION(
	staffNumber			int not null comment '員工編號',
    permissionNumber	int not null comment '權限編號',
    
    primary key (staffNumber, permissionNumber),
    constraint FK_S_PERMISSION_staffNumber foreign key (staffNumber) references STAFF (staffNumber),
	constraint FK_S_PERMISSION_permissionNumber foreign key (permissionNumber) references PERMISSION (permissionNumber)
) comment '員工權限';


#5 廠商
create table ORGANIZER(
	organizerNumber		int not null auto_increment comment '廠商編號' primary key,
    OAccount			varchar(20) not null comment '廠商帳號',
    Opassword			varchar(100) not null comment '廠商密碼',
    organizerName		varchar(50) not null comment '廠商名稱',
    windowName			varchar(30) not null comment '窗口姓名',
    windowPhone			varchar(30) not null comment '窗口電話',
    windowEmail			varchar(50) not null comment '窗口信箱',
    accountName			varchar(30) comment '戶名',
    accountNumber		varchar(50) comment '銀行帳號',
    bankCode 			varchar(4) comment '銀行代碼',
    bankName			varchar(50) comment '銀行名稱',  
    taxIDNumber			varchar(10) comment '公司統編',
    boss				varchar(30) comment '公司負責人',
    organizerPhone		varchar(30) comment '廠商電話',
    OPass				bit(1) default 0 not null comment '廠商帳號開通',
    organizeRevoke		bit(1) comment '廠商帳號註銷',
    ORevokeSDate		date comment '註銷申請日期',
    ORevokeEDate		date comment '註銷完成日期',
    ORevokeContent		text comment '註銷原因',
    OAmount				bit(1) comment '金額結算完成',
    staffNumber			int comment'員工編號',
    
    unique key UK_ORGANIZER_OAccount (OAccount),
    unique key UK_ORGANIZER_organizerName (organizerName),
    constraint FK_ORGANIZER_staffNumber foreign key (staffNumber) references STAFF(staffNumber)
) comment '廠商';
      

#6 活動
create table `EVENT`(
	eventNumber		INT NOT NULL auto_increment comment '活動編號' primary key,
    organizerNumber INT NOT NULL comment '廠商編號',
    coOrganizer		VARCHAR(50) comment '協辦單位',
    eventName		VARCHAR(100) NOT NULL comment '活動名稱',
    eventStartDate	TIMESTAMP NOT NULL comment '開始日期',
    eventEndDate	TIMESTAMP NOT NULL comment '結束日期',
    peopleNumber	INT default 0 comment '人數',
    eventPlace		VARCHAR(255) NOT NULL comment '活動地點',
    eventP2			VARCHAR(255) comment '活動地點敘述',
    eventSummary	TEXT comment '活動簡介',
    eventDescribe	TEXT comment '活動描述',
    # eventSummary	TEXT NOT NULL comment '活動簡介',
    # eventDescribe	TEXT NOT NULL comment '活動描述',
    bigImg			LONGBLOB comment '大圖',
    # bigImg			LONGBLOB NOT NULL comment '大圖', 
    smallImg		LONGBLOB comment '小圖',
    video			LONGBLOB comment '影片',
    otherImg1		LONGBLOB comment '其他圖片1',
    otherImg2		LONGBLOB comment '其他圖片2',
    collectType		BIT(1) default 0 NOT NULL comment '收集資料類型',
    banner			INT default 0 NOT NULL comment '首頁輪播',
    focus			INT default 0 NOT NULL comment '焦點活動',
    totalClick		INT default 0 NOT NULL comment '點擊次數',
    isON			BIT(1) default 0 NOT NULL comment '是否上架',
    eventType		VARCHAR(10) NOT NULL comment '活動狀態',
    needSeat		BIT(1) default 0 NOT NULL comment '是否需要劃位',
    seatX			INT comment '總X軸',
    seatY			INT comment '總Y軸',
    
	CONSTRAINT FK_EVENT_organizerNumber FOREIGN KEY (organizerNumber) REFERENCES ORGANIZER (organizerNumber)
) comment '活動';


#7 活動類型
create table EVENT_TYPE(
	eventClassNumber	INT NOT NULL auto_increment comment '活動類型編號' primary key,
    eventClassName		VARCHAR(50) NOT NULL comment '活動類型名稱',
    eventClassState		BIT(1) NOT NULL comment '狀態',
    
    UNIQUE KEY UK_EVENT_TYPE_eventClassName (eventClassName)
) comment '活動類型';


#8 活動分類
create table EVENT_CLASS(
	eventNumber			INT NOT NULL comment '活動編號',
    eventClassNumber	INT NOT NULL comment '活動類型編號',
    
    PRIMARY KEY (eventNumber,eventClassNumber),
    CONSTRAINT FK_EVENT_CLASS_eventNumber FOREIGN KEY (eventNumber) REFERENCES `EVENT` (eventNumber),
	CONSTRAINT FK_EVENT_CLASS_eventClassNumber FOREIGN KEY (eventClassNumber) REFERENCES EVENT_TYPE (eventClassNumber)
) comment '活動分類';


#9 座位
create table SEAT(
	seatID		INT NOT NULL auto_increment comment '座位編號',
    eventNumber	INT NOT NULL comment '活動編號',
    seatType	BIT(1) default 0 NOT NULL comment '劃位狀態',
    seatNumber	INT NOT NULL comment '座位號',
    seatSet		INT NOT NULL comment '座位設定',
    
    PRIMARY KEY (seatID, eventNumber),
    CONSTRAINT FK_SEAT_eventNumber FOREIGN KEY (eventNumber) REFERENCES `EVENT` (eventNumber)
) comment '座位';


#10 聯絡我們
create table CONTACT_US(
	contactID			INT NOT NULL auto_increment comment '聯絡編號' primary key,
    contactTitle		VARCHAR(50) comment '聯絡標題',
    contactContent		TEXT NOT NULL comment '聯絡內容',
	contactCreateDate	TIMESTAMP default CURRENT_TIMESTAMP comment '建立時間'
) comment '聯絡我們';


#11 會員
create table `MEMBER` (
	`number`		INT NOT NULL AUTO_INCREMENT comment'會員編號' primary key,
	`account`		VARCHAR(20) NOT NULL comment'使用者帳號',
	`password`		VARCHAR(100) NOT NULL comment'密碼',
	email			VARCHAR(50) NOT NULL  comment'信箱',
	birthday		DATE comment'生日',
	`name`			VARCHAR(21) NOT NULL comment'姓名',
	phoneNumber		VARCHAR(15) NOT NULL comment'手機',
	subscription	BIT(1)  default 0 NOT NULL comment'訂閱',
	createDate		TIMESTAMP default CURRENT_TIMESTAMP comment'註冊日期',
	pass			BIT(1) default 0 NOT NULL comment'開通帳號',
	IDNumber		VARCHAR(10) comment'身分證',
	phone2			VARCHAR(15) comment'連絡電話',
	postalCode		VARCHAR(3) comment'郵遞區號',
	address			VARCHAR(255) comment'地址',
    
	unique key		UK_MEMBER_NUMBER(`account`),
	unique key		UK_MEMBER_EMAIL(email),
	unique key		UK_MEMBER_PHONE(phoneNumber)
)comment'會員';


#12 通知
create table NOTICE (
	noticeID		INT NOT NULL AUTO_INCREMENT comment'通知編號' primary key,
	`number`		INT NOT NULL comment'會員編號',
	noticeConetnt	VARCHAR(255) NOT NULL comment'通知內容',
	isRead			BIT(1) default 0 comment'是否已讀',
    
	constraint FK_NOTICE_NUMBER foreign key(`number`) references `MEMBER`(`number`)
)comment'通知';


#13 收藏
create table COLLECT (
	`number`	int not null comment'會員編號',
	eventNumber int not null comment'活動編號',
    
	primary key (`number`,eventNumber),
	constraint FK_COLLECT_NUMBER foreign key(`number`) references `MEMBER`(`number`),
	constraint FK_COLLECT_EVENTNUMBER foreign key(eventNumber) references `EVENT`(eventNumber)
)comment'收藏';


#14 系統公告
create table SYSTEM_BULLETIN (
	bulletinID			INT NOT NULL AUTO_INCREMENT comment'公告編號' primary key,
	bulletinName		VARCHAR(255) NOT NULL comment'公告名稱',
	bulletinContent		TEXT NOT NULL comment'公告內容',
	bulletinDate		TIMESTAMP default CURRENT_TIMESTAMP comment'公告日期',
	isTop				BIT(1) NOT NULL default 0 comment'是否置頂',
	isOpen				BIT(1) NOT NULL default 0 comment'是否顯示',
	bulletinCreateDate	TIMESTAMP default CURRENT_TIMESTAMP comment'建立時間'
)comment'系統公告';


#15 常見問題
create table COMMON_QA (
	commonID			INT NOT NULL AUTO_INCREMENT comment'常見問題編號' primary key,
	commonName			VARCHAR(255) NOT NULL comment'問題名稱',
	commonContent		TEXT NOT NULL comment'問題內容',
	commonCreateDate	TIMESTAMP default CURRENT_TIMESTAMP comment'建立日期',
	sort				INT NOT NULL comment'排序'
)comment'常見問題';


#16 活動訂單
create table `ORDER`(
	orderID			INT not null auto_increment comment '訂單編號' primary key,
	eventNumber		INT not null comment '活動編號',
	`number`		INT not null comment '會員編號',
	orderDate		TIMESTAMP default current_timestamp comment '訂單日期',
	orderType		VARCHAR(10) not null comment '訂單狀態',
	total			INT not null comment '總金額',
	totalTicket		INT not null comment '總票數',
	pData			TEXT not null comment '報名&聯絡人資料',
	reason			TEXT comment '取消原因',
	reasonMoney		INT comment '退款金額',
	eventScore		FLOAT default 0 comment '活動評價',
	eventSContent	TEXT comment '評價內容',
	eventSDate		TIMESTAMP comment '評價日期',
    
	constraint FK_ORDER_eventNumber foreign key(eventNumber) references `EVENT`(eventNumber),
	constraint FK_ORDER_number foreign key(`number`) references `MEMBER`(`number`)
) comment '活動訂單';


#17 票種
create table TICKET(
	ticketID		int not null auto_increment comment '票種編號' primary key,
	ticketName		varchar(50) not null comment '票種名稱',
	eventNumber		int not null comment '活動編號',
	price			int not null comment '價格',
	limitTicket		bit(1) default 1 not null comment '是否限票',
	ticketQuantity	int not null comment '票數',
	ticketStartTime	timestamp not null comment '販售開始時間',
	ticketEndTime	timestamp not null comment '販售結束時間',
	ticketMIN		int default 1 not null comment '最少購買張數',
	ticketMAX		int default 1 not null comment '最大購買張數',
	ticketType		varchar(50) not null comment '票種狀態',
    
	constraint FK_TICKET_eventNumber foreign key(eventNumber) references `EVENT`(eventNumber) 
)comment '票種';


#18 售出票
create table SOLD_TICKETS(
	ticketNumber	int not null auto_increment comment '票號'primary key,
	orderID			int not null comment '訂單編號',
	seatID			int comment '座位編號',
	ticketID		int not null comment '票種編號',
	isUse			bit(1) default 0 comment '是否使用',
	orderPrice		int not null comment '價格',
	ticketQR		longblob not null comment '二維碼',
    
	constraint FK_SOLD_TICKETS_orderID foreign key(orderID) references `ORDER`(orderID),
	constraint FK_SOLD_TICKETS_seatID foreign key(seatID) references SEAT(seatID),
	constraint FK_SOLD_TICKETS_ticketID foreign key(ticketID) references TICKET(ticketID)
) comment '售出票';


#19 服務條款
create table TERMS_OF_SERVICE(
	termsID		int not null auto_increment comment '條款編號' primary key,
	termsTitle	varchar(50) comment '聯絡標題',
	termsContent	text not null comment '聯絡內容',
	termsCreateDate	timestamp default current_timestamp comment '建立時間'
) comment '服務條款';


#20 商品
create table PRODUCT(
    prodNo			int not null auto_increment comment '商品編號' primary key,
    eventNumber		int not null comment '活動編號',
    organizerNumber int not null comment '廠商編號',
    prodName		varchar(100) not null comment '商品名稱',
    prodSpec		varchar(255) not null comment '商品規格',
    unitPrice		int not null comment '商品單價',
    prodStock		int not null comment '庫存數量',
    prodDetails		text comment '商品詳情',
    prodScore		float not null default 0 comment '商品總評價', 
	isPOn			bit(1) not null default 1 comment '商品是否上架',
    
    constraint FK_PRODUCT_eventNumber foreign key (eventNumber) references `EVENT` (eventNumber),
    constraint FK_PRODUCT_organizerNumber foreign key (organizerNumber) references ORGANIZER (organizerNumber)
) comment '商品';
                

#21 商品圖片
create table PRODUCT_IMG(
	prodIMGID		int not null auto_increment comment '圖片編號' primary key,
    prodNo			int not null comment '商品編號',
    prodIMGName		varchar(100) comment '圖片名稱',
    prodIMG			longblob not null comment '商品圖片',
    
    constraint FK_PRODUCT_IMG_prodNo foreign key (prodNo) references PRODUCT (prodNo)
) comment '商品圖片';


#22 商品訂單
create table PROD_ORDER(
	prodOrderNo		int not null auto_increment comment '商品訂單編號' primary key,
    `number`		int not null comment '會員編號',
    amountPrice		int not null comment '總金額',
    prodTotal		int not null comment '商品總數',
    paymentDate		timestamp not null comment '付款日期',
    receiverName	varchar(30) not null comment '收件人姓名',
    receiverTel		varchar(15) not null comment '收件人電話',
    shippingAdd		varchar(255) not null comment '收件地址',
    prodOrderStatus varchar(50) not null comment '訂單狀態',
    deliveryStatus	varchar(50) not null comment '配送狀態',
    orderNotes		varchar(255) comment '訂單備註',
    
    constraint FK_PROD_ORDER_number foreign key (`number`) references `MEMBER` (`number`)
) comment '商品訂單';


#23 商品訂單明細
create table ORDER_DETAIL(
	itemNo			int not null auto_increment comment '明細編號' primary key,
    prodOrderNo		int not null comment '商品訂單編號',
	prodNo			int not null comment '商品編號',
    prodQty			int not null comment '各商品數量',
    subtotal		int not null comment '商品小計金額',
    commentRanking	float comment '評價分數',
    commentContent	text comment '評價內容',
    commentDate		timestamp comment '評價日期',
	returnReason	text comment '退訂原因',
    refundStatus	varchar(50) comment '退款狀態',
    refundSDate		date comment '退款申請日期',
    refundEDate		date comment '退款完成日期',
    
	constraint FK_ORDER_DETAIL_prodOrderNo foreign key (prodOrderNo) references PROD_ORDER (prodOrderNo),
    constraint FK_ORDER_DETAIL_prodNo foreign key (prodNo) references PRODUCT (prodNo)
) comment '商品訂單明細';



#24 購物車
create table SHOPPING_CART(
	shoppingCartNo	int not null auto_increment comment '購物車編號' primary key,
    `number`		int not null comment '會員編號',
    prodNo			int not null comment '商品編號',
    shoppingQty		int not null comment '商品數量',
    
    constraint FK_SHOPPING_CART_number foreign key (`number`) references `MEMBER` (`number`),
    constraint FK_SHOPPING_CART_prodNo foreign key (prodNo) references PRODUCT (prodNo)
) comment '購物車';

    
#------------- create view --------------

#25 View訂單明細
create view V_ORDER_DETAIL as 
select od.*, prod.prodName, prod.prodSpec
from ORDER_DETAIL od
	join PRODUCT prod
    on od.prodNo = prod.prodNo;


#26 View商品訂單
create view V_PROD_ORDER as 
select po.*, m.account, m.name, m.phoneNumber, m.email
from PROD_ORDER po
join MEMBER m
on po.number = m.number;

#27 View商品評論星星
create view V_PROD_RANKING as 
select prodNo , COUNT(commentRanking) as commentQty, SUM(commentRanking) as totalComment
from ORDER_DETAIL
group by prodNo;

#28 View商品
create view V_PRODUCT as 
select p.*, e.eventName, e.eventType, vp.commentQty, vp.totalComment
from PRODUCT p
join EVENT e
on p.eventNumber = e.eventNumber
left join V_PROD_RANKING vp
on p.prodNo = vp.prodNo
order by prodNo;

#29 View購物車
create view V_SHOPPING_CART as
select sc.*, p.prodName, p.prodSpec, p.unitPrice, m.name, m.phoneNumber, m.address
from SHOPPING_CART sc
join PRODUCT p
	on sc.prodNo = p.prodNo 
join MEMBER m
	on sc.number = m.number;
    
#30 View商品活動分類
create view V_PRODUCT_EVENTCLASS as
select p.*, et.eventClassName, et.eventClassState from EVENT_CLASS ec
join EVENT_TYPE et
	on ec.eventClassNumber = et.eventClassNumber
		join V_PRODUCT p
			on p.eventNumber = ec.eventNumber;


-- drop database TICK_IT;