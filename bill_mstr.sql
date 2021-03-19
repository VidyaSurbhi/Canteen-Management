create table bill_mstr(bill_id number(5) primary key,
date_bill date,
 item_name varchar(20) constraint fk_item_name references item_mstr(item_name),
qty_item number(4),
item_price number(10,2) ,
total_item number(10,2),
total_amt number(10,2),
total_qty number(10,2))
/