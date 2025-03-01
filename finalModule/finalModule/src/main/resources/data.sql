#Thêm dữ liệu vào category
INSERT INTO categories (categoryid, name, description) VALUES
(1, 'Điện thoại', 'Các dòng điện thoại thông minh mới nhất'),
(2, 'Máy tính bảng', 'Máy tính bảng phục vụ công việc và giải trí'),
(3, 'Laptop', 'Laptop dành cho học tập, làm việc, gaming'),
(4, 'Tai nghe', 'Tai nghe chất lượng cao, có dây và không dây'),
(5, 'Tay cầm', 'Tay cầm chơi game các loại'),
(6, 'Hàng cũ', 'Sản phẩm đã qua sử dụng, chất lượng tốt'),
(7, 'Khuyến mãi', 'Các sản phẩm đang được giảm giá');

# Thêm dữ liệu vào brand
INSERT INTO brands (name, create_at, update_at) VALUES ('Apple', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at)
VALUES ('Samsung', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at)
VALUES ('Sony', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at)
VALUES ('Asus', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at)
VALUES ('Dell', NOW(), NOW());
# Thêm dữ liệu vào Users
insert into user (username , encryted_password ,email , enabled , created_at , updated_at) values
  ('hoang123' , '123' , 'hoang12@gmail.com' , false , now() , now()) ,
  ('vanhau123' , '123' , 'vanhau12@gmail.com' , false , now() , now()),
  ('tuantai345' , '123' , 'tuantai12@gmail.com' , false , now() , now()),
  ('thitrang05' , '123' , 'trang12@gmail.com' , false , now() , now()),
  ('thuylinh123' , '123' , 'linh12@gmail.com' , false , now() , now()),
  ('hoaian678' , '123' , 'hoaian123@gmail.com' , false , now() , now()),
  ('khiem980' , '123' , 'khiem12@gmail.com' , false , now() , now()),
  ('nguyenduc123' , '123' , 'duc12@gmail.com' , false , now() , now()),
  ('phuongnha123' , '123' , 'phuong12@gmail.com' , false , now() , now()),
  ('vantuan' , '123' , 'tuan56@gmail.com' , false , now() , now()) ,
  ('huynhchung78' , '123' , 'chung12@gmail.com' , false , now() , now()),
  ('vannam123' , '123' , 'nam345@gmail.com' , false , now() , now()),
  ('thanhtrung89' , '123' , 'trung123@gmail.com' , false , now() , now()),
  ('houyen09' , '123' , 'uyen12@gmail.com' , false , now() , now()),
  ('dangkhoa123' , '123' , 'khoa12@gmail.com' , false , now() , now()),
  ('letrongsi123' , '123' , 'trongsi2@gmail.com' , false , now() , now()),
  ('thanhtung56' , '123' , 'tung56@gmail.com' , false , now() , now()) ;


use finalCodeGymModule ;
  # Thêm dữ liệu vào Roles
insert into role (role_name) values
  ('ROLE_ADMIN'),
  ('ROLE_EMPLOYEE'),
  ('ROLE_CUSTOMER') ;
 # Gán Roles cho Users
insert into user_role (user_id , role_id) values
  (1 ,2) ,(2 , 2) , (3 ,2) ,(4 , 2) ,(5 ,2) ,
  (6 , 3) ,(7 ,3) ,(8 , 3),(9 ,3) ,(10 , 3) ,(11 ,3) ,(12 , 3) ,
  (13 , 3) ,(14 ,3) ,(15 , 3),(16 ,3) ,(17 , 3) ;

# Thêm dữ liệu vào Customers
insert into customers (address, birth_date, customer_name, is_disabled, phone_number) values
 ('Ha Noi' , '2002-06-12' , 'Tran Hoai An' , true , '0981828128' ) ,
 ('Da Nang' , '2002-06-12' , 'Tuan Khiem' , true , '0971218291') ,
 ('Hue' , '2002-06-14' , 'Nguyen Van Duc' , true , '0912118128') ,
 ('Ha Noi' , '2001-06-12' , 'Phuong Nha' , true , '0989129112') ,
 ('Hai Phong' , '2002-12-12' , 'Tran Van Tuan' , true , '0912991991' ) ,
 ('Bac Ninh' , '2000-06-12' , 'Huynh Chung' , true , '0981812791' ) ,
 ('Ha Noi' , '2002-06-07' , 'Van Nam' , true , '0398591028' ) ,
 ('Can Tho' , '1992-06-14' , 'Phan Thanh Trung' , true , '0912112312' ) ,
 ('Ca Mau' , '1993-06-12' , 'Ho Thi Uyen' , true , '0912819112' ) ,
 ('Da Nang' , '2000-12-02' , 'Tran Dang Khoa' , true , '0989890012' ) ,
 ('Quang Binh' , '2000-07-03' , 'Le Trong Si' , true , '0900081288' ) ,
 ('Ha Noi' , '2002-06-07' , 'Nguyen Thanh Tung' , true , '0390001821' ) ;



# Thêm dữ liệu vào Employee_Positions
 insert into employee_positions (position_description, position_name) VALUES
 ('' , 'Nhân Viên Kinh Doanh') ,
 ('' , 'Nhân Viên Chăm sóc Khách Hàng') ,
 ('' , 'Nhân Viên Bán Hàng') ,
 ('' , 'Nhân Viên Thủ Kho') ,
 ('' , 'Nhân Viên Kế Toán') ;
# Thêm dữ liệu vào Employees
insert into employees (employee_address, employee_birthday, employee_name, employee_phone, is_disabled, position_id, user_id)
values ('Ha Noi' , '2000-12-12' , 'Nguyen Hoang' , '0933371781' , true , 1 , 1) ,
       ('Ha Noi' , '2001-09-12' , 'Truong Van Hau' , '0955571781' , true , 2 , 2) ,
       ('Ha Noi' , '1998-12-12' , 'Nguyen Tuan Tai' , '0944441781' , true ,3 , 3) ,
       ('Ha Noi' , '2002-02-12' , 'Ngo Thi Trang' , '0955555781' , true , 5 , 4) ,
       ('Ha Noi' , '2000-12-12' , 'Nguyen Thuy Linh' , '0933376666' , true , 4 , 5) ;