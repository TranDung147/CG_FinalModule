#Thêm dữ liệu vào category
INSERT INTO categories (name, description, create_at, update_at)
VALUES ('Điện thoại', 'Các dòng điện thoại thông minh mới nhất', NOW(), NOW()),
       ('Máy tính bảng', 'Máy tính bảng phục vụ công việc và giải trí', NOW(), NOW()),
       ('Laptop', 'Laptop dành cho học tập, làm việc, gaming', NOW(), NOW()),
       ('Tai nghe', 'Tai nghe chất lượng cao, có dây và không dây', NOW(), NOW()),
       ('Tay cầm', 'Tay cầm chơi game các loại', NOW(), NOW()),
       ('Hàng cũ', 'Sản phẩm đã qua sử dụng, chất lượng tốt', NOW(), NOW()),
       ('Khuyến mãi', 'Các sản phẩm đang được giảm giá', NOW(), NOW()),
       ('Phụ kiện', 'Các loại phụ kiện điện thoại và máy tính', NOW(), NOW()),
       ('Đồng hồ thông minh', 'Smartwatch các loại', NOW(), NOW()),
       ('Máy ảnh', 'Máy ảnh chuyên nghiệp và bán chuyên', NOW(), NOW());

# Thêm dữ liệu vào brand
INSERT INTO brands (name, status, country, create_at, update_at)
VALUES ('Apple', true, 'Mỹ', NOW(), NOW()),
       ('Samsung', true, 'Hàn Quốc', NOW(), NOW()),
       ('Sony', true, 'Nhật Bản', NOW(), NOW()),
       ('Asus', true, 'Đài Loan', NOW(), NOW()),
       ('Dell', true, 'Mỹ', NOW(), NOW()),
       ('Xiaomi', true, 'Trung Quốc', NOW(), NOW()),
       ('Huawei', true, 'Trung Quốc', NOW(), NOW()),
       ('LG', true, 'Hàn Quốc', NOW(), NOW()),
       ('Oppo', true, 'Trung Quốc', NOW(), NOW()),
       ('Lenovo', true, 'Trung Quốc', NOW(), NOW());

INSERT INTO suppliers (supplier_code, name, address, phone, email)
VALUES ('AP001', 'Apple Store', 'California, USA', '0981921280', 'apple@gmail.com'),
       ('SS001', 'Samsung Store', 'Seoul, Korea', '0911921281', 'samsung@gmail.com'),
       ('OP001', 'Oppo Store', 'Guangdong, China', '0981921182', 'oppo@gmail.com'),
       ('XM001', 'Xiaomi Store', 'Beijing, China', '0001921283', 'xiaomi@gmail.com'),
       ('SN001', 'Sony Distributor', 'Tokyo, Japan', '0981921284', 'sony@gmail.com'),
       ('AS001', 'Asus Vietnam', 'Hanoi, Vietnam', '0981921285', 'asus@gmail.com'),
       ('DL001', 'Dell Vietnam', 'HCMC, Vietnam', '0981921286', 'dell@gmail.com'),
       ('HW001', 'Huawei Store', 'Shenzhen, China', '0981921287', 'huawei@gmail.com'),
       ('LG001', 'LG Electronics', 'Seoul, Korea', '0981921288', 'lg@gmail.com'),
       ('LN001', 'Lenovo Shop', 'Shanghai, China', '0981921289', 'lenovo@gmail.com');

INSERT INTO products (create_at, description, main_image_url, name, price, stock, update_at, brand_id, category_id,
                      supplier_id)
VALUES (NOW(), 'iPhone 14 Pro Max với Dynamic Island và camera 48MP', '/images/iphone14.jpg', 'iPhone 14 Pro Max',
        35000000, 45, NOW(), 1, 1, 1),
       (NOW(), 'Samsung Galaxy Z Fold 4 với màn hình gập độc đáo', '/images/zfold4.jpg', 'Samsung Galaxy Z Fold 4',
        40000000, 30, NOW(), 2, 1, 2),
       (NOW(), 'iPad Air 2022 với chip M1 mạnh mẽ', '/images/ipadair.jpg', 'iPad Air 2022', 18000000, 50, NOW(), 1, 2,
        1),
       (NOW(), 'MacBook Air M2 với thiết kế mới, chip M2 mạnh mẽ', '/images/macbookair.jpg', 'MacBook Air M2', 32000000,
        35, NOW(), 1, 3, 1),
       (NOW(), 'Tai nghe AirPods Pro 2 với chống ồn chủ động', '/images/airpodspro2.jpg', 'AirPods Pro 2', 6500000, 70,
        NOW(), 1, 4, 1),
       (NOW(), 'Samsung Galaxy Watch 5 Pro với thiết kế cao cấp', '/images/watch5pro.jpg', 'Samsung Galaxy Watch 5 Pro',
        9000000, 40, NOW(), 2, 5, 2),
       (NOW(), 'Apple Watch Series 8 với cảm biến nhiệt độ', '/images/applewatch8.jpg', 'Apple Watch Series 8',
        12000000, 45, NOW(), 1, 5, 1),
       (NOW(), 'Loa Bluetooth JBL Charge 5 với âm thanh mạnh mẽ', '/images/jblcharge5.jpg', 'JBL Charge 5', 4200000, 80,
        NOW(), 4, 6, 3),
       (NOW(), 'Máy ảnh Sony Alpha 7 IV với cảm biến Full Frame', '/images/sonyA7IV.jpg', 'Sony Alpha 7 IV', 52000000,
        20, NOW(), 3, 7, 5),
       (NOW(), 'Laptop Dell XPS 15 với màn hình OLED 4K', '/images/dellxps15.jpg', 'Dell XPS 15', 38000000, 25, NOW(),
        5, 3, 4),
       (NOW(), 'iPhone 13 Pro Max mới nhất với camera đẳng cấp', '/images/iphone13.jpg', 'iPhone 13 Pro Max', 32000000,
        50, NOW(), 1, 1, 1),
       (NOW(), 'Samsung Galaxy S22 Ultra với bút S-Pen', '/images/s22ultra.jpg', 'Samsung Galaxy S22 Ultra', 28000000,
        40, NOW(), 2, 1, 2),
       (NOW(), 'iPad Pro 12.9 inch với màn hình Liquid Retina XDR', '/images/ipadpro.jpg', 'iPad Pro 12.9"', 25000000,
        30, NOW(), 1, 2, 1),
       (NOW(), 'MacBook Pro 16" với chip M1 Pro', '/images/macbookpro.jpg', 'MacBook Pro 16"', 48000000, 25, NOW(), 1,
        3, 1),
       (NOW(), 'Tai nghe Sony WH-1000XM4 chống ồn', '/images/sonyheadphone.jpg', 'Sony WH-1000XM4', 7000000, 60, NOW(),
        3, 4, 5);

INSERT INTO product_details (screen_size, camera, color, cpu, ram, rom, battery, description, create_at, update_at,
                             product_id)
VALUES (6.7, 48, 'Xanh Sierra', 'Apple A15 Bionic', '6GB', '512GB', '4352mAh',
        'iPhone 13 Pro Max phiên bản cao cấp nhất', NOW(), NOW(), 1),
       (6.8, 108, 'Đen Phantom', 'Snapdragon 8 Gen 1', '12GB', '256GB', '5000mAh',
        'Flagship Samsung với bút S-Pen tích hợp', NOW(), NOW(), 2),
       (12.9, 12, 'Bạc', 'Apple M1', '8GB', '512GB', '10090mAh', 'iPad Pro với màn hình mini-LED', NOW(), NOW(), 3),
       (16.0, 0, 'Xám không gian', 'Apple M1 Pro', '16GB', '1TB', '100Wh', 'MacBook chuyên nghiệp cho công việc nặng',
        NOW(), NOW(), 4),
       (0, 0, 'Đen', 'MediaTek', '0GB', '0GB', '30h sử dụng', 'Tai nghe chống ồn hàng đầu thế giới', NOW(), NOW(), 5);

INSERT INTO ware_house (price, quantity, product_id)
VALUES (30000000, 100, 1),
       (26000000, 80, 2),
       (23000000, 60, 3),
       (45000000, 50, 4),
       (6500000, 120, 5);
# Thêm dữ liệu vào Users
INSERT INTO user (username, encryted_password, email, enabled, created_at, updated_at)
VALUES ('admin123', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'admin@gmail.com', true, NOW(),
        NOW()),
       ('hoang123', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'hoang12@gmail.com', true, NOW(),
        NOW()),
       ('vanhau123', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'vanhau12@gmail.com', true, NOW(),
        NOW()),
       ('tuantai345', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'tuantai12@gmail.com', true,
        NOW(), NOW()),
       ('thitrang05', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'trang12@gmail.com', true, NOW(),
        NOW()),
       ('hoaian678', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'hoaian123@gmail.com', true, NOW(),
        NOW()),
       ('khiem980', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'khiem12@gmail.com', true, NOW(),
        NOW()),
       ('nguyenduc123', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'duc12@gmail.com', true, NOW(),
        NOW()),
       ('phuongnha123', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'phuong12@gmail.com', true,
        NOW(), NOW()),
       ('vantuan', '$2a$10$rEIU6RWMOkNj.xqgZj7ow.VW0oEP2jkpbOdLOOt8HUWcNrLq0mRdS', 'tuan56@gmail.com', true, NOW(),
        NOW());


use finalCodeGymModule;
# Thêm dữ liệu vào Roles
INSERT INTO role (role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_EMPLOYEE');
# Gán Roles cho Users
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1), -- admin123 có role ADMIN
       (2, 2), -- hoang123 có role EMPLOYEE
       (3, 2), -- vanhau123 có role EMPLOYEE
       (4, 2), -- tuantai345 có role EMPLOYEE
       (5, 2), -- thitrang05 có role EMPLOYEE
       (6, 3), -- hoaian678 có role CUSTOMER
       (7, 3), -- khiem980 có role CUSTOMER
       (8, 3), -- nguyenduc123 có role CUSTOMER
       (9, 3), -- phuongnha123 có role CUSTOMER
       (10, 3);
-- vantuan có role CUSTOMER

# Thêm dữ liệu vào Customers
insert into customers (address, birth_date, customer_name, is_disabled, phone_number)
values ('Ha Noi', '2002-06-12', 'Tran Hoai An', true, '0981828128'),
       ('Da Nang', '2002-06-12', 'Tuan Khiem', true, '0971218291'),
       ('Hue', '2002-06-14', 'Nguyen Van Duc', true, '0912118128'),
       ('Ha Noi', '2001-06-12', 'Phuong Nha', true, '0989129112'),
       ('Hai Phong', '2002-12-12', 'Tran Van Tuan', true, '0912991991'),
       ('Bac Ninh', '2000-06-12', 'Huynh Chung', true, '0981812791'),
       ('Ha Noi', '2002-06-07', 'Van Nam', true, '0398591028'),
       ('Can Tho', '1992-06-14', 'Phan Thanh Trung', true, '0912112312'),
       ('Ca Mau', '1993-06-12', 'Ho Thi Uyen', true, '0912819112'),
       ('Da Nang', '2000-12-02', 'Tran Dang Khoa', true, '0989890012'),
       ('Quang Binh', '2000-07-03', 'Le Trong Si', true, '0900081288'),
       ('Ha Noi', '2002-06-07', 'Nguyen Thanh Tung', true, '0390001821');


# Thêm dữ liệu vào Employee_Positions
INSERT INTO employee_positions (position_name, position_description)
VALUES ('Nhân Viên Kinh Doanh', 'Phụ trách tìm kiếm khách hàng và phát triển thị trường'),
       ('Nhân Viên Chăm sóc Khách Hàng', 'Hỗ trợ và giải quyết các vấn đề của khách hàng'),
       ('Nhân Viên Bán Hàng', 'Trực tiếp bán hàng tại cửa hàng'),
       ('Nhân Viên Thủ Kho', 'Quản lý nhập xuất kho và tồn kho'),
       ('Nhân Viên Kế Toán', 'Xử lý các vấn đề tài chính và kế toán');
# Thêm dữ liệu vào Employees
INSERT INTO employees (employee_name, employee_birthday, employee_address, employee_phone, employee_work, position_id,
                       is_disabled, user_id)
VALUES ('Nguyễn Hoàng', '2000-12-12', 'Hà Nội', '0933371781', 'Full-time', 1, false, 2),
       ('Trương Văn Hậu', '2001-09-12', 'Hà Nội', '0955571781', 'Full-time', 2, false, 3),
       ('Nguyễn Tuấn Tài', '1998-12-12', 'Hà Nội', '0944441781', 'Full-time', 3, false, 4),
       ('Ngô Thị Trang', '2002-02-12', 'Hà Nội', '0955555781', 'Part-time', 5, false, 5);
# Thêm dữ liệu vào customer
INSERT INTO customers (customer_name, phone_number, address, email, birth_date, is_disabled)
VALUES ('Trần Hoài An', '0981828128', 'Hà Nội', 'hoaian@gmail.com', '2002-06-12', false),
       ('Tuấn Khiêm', '0971218291', 'Đà Nẵng', 'tuankhiem@gmail.com', '2002-06-12', false),
       ('Nguyễn Văn Đức', '0912118128', 'Huế', 'vanduc@gmail.com', '2002-06-14', false),
       ('Phương Nha', '0989129112', 'Hà Nội', 'phuongnha@gmail.com', '2001-06-12', false),
       ('Trần Văn Tuấn', '0912991991', 'Hải Phòng', 'vantuan@gmail.com', '2002-12-12', false);
INSERT INTO admins (admin_name, department, user_id)
VALUES ('Nguyễn Quản Trị', 'IT', 1);


INSERT INTO order_products (create_at, payment_status, status, total_price, customer_id)
VALUES ('2024-07-10', 'PENDING', 'DELIVERED', 80000, 1),
       ('2024-08-15', 'COMPLETED', 'DELIVERED', 120000, 1),
       ('2024-09-05', 'PENDING', 'DELIVERED', 45000, 1),
       ('2024-10-12', 'FAILED', 'CANCELLED', 60000, 1),
       ('2024-11-20', 'COMPLETED', 'DELIVERED', 95000, 1),

       ('2024-07-18', 'PENDING', 'DELIVERED', 70000, 2),
       ('2024-08-22', 'COMPLETED', 'DELIVERED', 110000, 2),
       ('2024-09-14', 'PENDING', 'DELIVERED', 40000, 2),
       ('2024-10-30', 'FAILED', 'CANCELLED', 65000, 2),
       ('2024-12-05', 'COMPLETED', 'DELIVERED', 99000, 2),

       ('2024-07-05', 'PENDING', 'DELIVERED', 75000, 3),
       ('2024-08-27', 'COMPLETED', 'DELIVERED', 125000, 3),
       ('2024-09-10', 'PENDING', 'DELIVERED', 42000, 3),
       ('2024-11-07', 'FAILED', 'CANCELLED', 59000, 3),
       ('2025-01-12', 'COMPLETED', 'DELIVERED', 97000, 3),

       ('2024-07-22', 'PENDING', 'DELIVERED', 77000, 4),
       ('2024-08-12', 'COMPLETED', 'DELIVERED', 118000, 4),
       ('2024-09-25', 'PENDING', 'DELIVERED', 46000, 4),
       ('2024-10-08', 'FAILED', 'CANCELLED', 62000, 4),
       ('2024-12-18', 'COMPLETED', 'DELIVERED', 98000, 4),

       ('2024-07-30', 'PENDING', 'DELIVERED', 81000, 5),
       ('2024-09-01', 'COMPLETED', 'DELIVERED', 130000, 5),
       ('2024-10-19', 'PENDING', 'DELIVERED', 48000, 5),
       ('2024-11-27', 'FAILED', 'CANCELLED', 63000, 5),
       ('2025-02-10', 'COMPLETED', 'DELIVERED', 96000, 5);


INSERT INTO order_details (price, quantity, order_id, product_id) VALUES
-- Đơn hàng 1
(4500, 2, 1, 1),
(5000, 3, 1, 2),
(1200, 1, 1, 3),

-- Đơn hàng 2
(2000, 2, 2, 4),
(3000, 1, 2, 5),
(1800, 4, 2, 6),

-- Đơn hàng 3
(7500, 2, 3, 7),
(2200, 3, 3, 8),
(5300, 1, 3, 9),

-- Đơn hàng 4
(8900, 2, 4, 10),
(4000, 1, 4, 11),

-- Đơn hàng 5
(6500, 3, 5, 12),
(2400, 2, 5, 13),

-- Đơn hàng 6
(7200, 1, 6, 14),
(5100, 2, 6, 15),
(3200, 3, 6, 1),

-- Đơn hàng 7
(8100, 1, 7, 12),
(9100, 2, 7, 13),

-- Đơn hàng 8
(4300, 2, 8, 2),
(3700, 3, 8, 3),
(5600, 1, 8, 4),

-- Đơn hàng 9
(3000, 2, 9, 1),
(4100, 1, 9, 2),

-- Đơn hàng 10
(2200, 4, 10, 3),
(8800, 2, 10, 4),
(7400, 3, 10, 5),

-- Đơn hàng 11
(4100, 2, 11, 6),
(6300, 1, 11, 7),
(3100, 3, 11, 8),

-- Đơn hàng 12
(5300, 2, 12, 9),
(3200, 1, 12, 10),
(4100, 4, 12, 11),

-- Đơn hàng 13
(2800, 2, 13, 12),
(4300, 3, 13, 13),

-- Đơn hàng 14
(6700, 1, 14, 15),
(5200, 2, 14, 11),
(2300, 3, 14, 8),

-- Đơn hàng 15
(7100, 1, 15, 1),
(8900, 2, 15, 6),

-- Đơn hàng 16
(3900, 2, 16, 7),
(4100, 3, 16, 8),
(5600, 1, 16, 5),

-- Đơn hàng 17
(4100, 2, 17, 1),
(2900, 1, 17, 2),

-- Đơn hàng 18
(2200, 4, 18, 3),
(8800, 2, 18, 4),
(7400, 3, 18, 5),

-- Đơn hàng 19
(3100, 2, 19, 6),
(5100, 1, 19, 7),
(4900, 3, 19, 8),

-- Đơn hàng 20
(6200, 2, 20, 9),
(3100, 1, 20, 10),
(4500, 4, 20, 11),

-- Đơn hàng 21
(2700, 2, 21, 12),
(4200, 3, 21, 13),

-- Đơn hàng 22
(5600, 1, 22, 15),
(5900, 2, 22, 11),
(2300, 3, 22, 8),

-- Đơn hàng 23
(7200, 1, 23, 1),
(6800, 2, 23, 14),

-- Đơn hàng 24
(3900, 2, 24, 2),
(4100, 3, 24, 3),
(5600, 1, 24, 4),

-- Đơn hàng 25
(4900, 2, 25, 1),
(3200, 1, 25, 2);


insert into payments (payment_method, status, order_id)
values ('CASH', 'PENDING', 1),
       ('CASH', 'PENDING', 2),
       ('CASH', 'PENDING', 3),
       ('CASH', 'PENDING', 4),
       ('CASH', 'PENDING', 5),
       ('CASH', 'PENDING', 6),
       ('CASH', 'PENDING', 7),
       ('CASH', 'PENDING', 8),
       ('CASH', 'PENDING', 9),
       ('CASH', 'PENDING', 10),('CASH', 'PENDING', 11),
       ('CASH', 'PENDING', 12),
       ('CASH', 'PENDING', 13),
       ('CASH', 'PENDING', 14),
       ('CASH', 'PENDING', 15),
       ('CASH', 'PENDING', 16),
       ('CASH', 'PENDING', 17),
       ('CASH', 'PENDING', 18),
       ('CASH', 'PENDING', 19),
       ('CASH', 'PENDING', 20),('CASH', 'PENDING', 21),
       ('CASH', 'PENDING', 22),
       ('CASH', 'PENDING', 23),
       ('CASH', 'PENDING', 24),
       ('CASH', 'PENDING', 25) ;

