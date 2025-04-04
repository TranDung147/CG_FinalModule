# Thêm dữ liệu vào Employee_Positions
INSERT INTO employee_positions (position_name, position_description)
VALUES ('Nhân Viên Kinh Doanh', 'Phụ trách tìm kiếm khách hàng và phát triển thị trường'),
       ('Nhân Viên Bán Hàng', 'Trực tiếp bán hàng tại cửa hàng'),
       ('Nhân Viên Thủ Kho', 'Quản lý nhập xuất kho và tồn kho');
use finalCodeGymModule;
# Thêm dữ liệu vào Roles
INSERT INTO role (role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_BUSINESS'),
       ('ROLE_SALES'),
       ('ROLE_WAREHOUSE');
INSERT INTO user (username, encryted_password, email, enabled, created_at, updated_at)
VALUES ('admin123', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'admin@gmail.com', true, NOW(),
        NOW()),
       ('hoang123', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'hoang12@gmail.com', true, NOW(),
        NOW()),
       ('vanhau123', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'vanhau12@gmail.com', true, NOW(),
        NOW()),
       ('tuantai345', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'tuantai12@gmail.com', true,
        NOW(), NOW()),
       ('thitrang05', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'trang12@gmail.com', true, NOW(),
        NOW()),
       ('hoaian678', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'hoaian123@gmail.com', true, NOW(),
        NOW()),
       ('khiem980', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'khiem12@gmail.com', true, NOW(),
        NOW()),
       ('nguyenduc123', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'duc12@gmail.com', true, NOW(),
        NOW()),
       ('phuongnha123', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'phuong12@gmail.com', true,
        NOW(), NOW()),
       ('vantuan', '$2a$10$y/odQPEQIU.RifEy1Steu.5ZuCmTRGQUB3ntp6.bWM/kw/29huOZK', 'tuan56@gmail.com', true, NOW(),
        NOW());
# Gán Roles cho Users
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1), -- admin123 có role ADMIN
       (2, 2), -- hoang123 có role EMPLOYEE
       (3, 3), -- vanhau123 có role EMPLOYEE
       (4, 4),
       (5, 4);

# Thêm dữ liệu vào Employees
INSERT INTO employees (employee_name, employee_birthday, employee_address, employee_phone, employee_work, position_id,
                       is_disabled, user_id)
VALUES ('Nguyễn Hoàng', '2000-12-12', 'Hà Nội', '0933371781', 'Full-time', 1, false, 2),
       ('Trương Văn Hậu', '2001-09-12', 'Hà Nội', '0955571781', 'Full-time', 2, false, 3),
       ('Nguyễn Tuấn Tài', '1998-12-12', 'Hà Nội', '0944441781', 'Full-time', 3, false, 4),
       ('Ngô Thị Trang', '2002-02-12', 'Hà Nội', '0955555781', 'Part-time', 3, false, 5);
# Thêm dữ liệu vào customer
INSERT INTO customers (customer_name, phone_number, address, email, birth_date, is_disabled)
VALUES ('Trần Hoài An', '0981828128', 'Hà Nội', 'hoaian@gmail.com', '2002-06-12', false),
       ('Tuấn Khiêm', '0971218291', 'Đà Nẵng', 'tuankhiem@gmail.com', '2002-06-12', false),
       ('Nguyễn Văn Đức', '0912118128', 'Huế', 'vanduc@gmail.com', '2002-06-14', false),
       ('Phương Nha', '0989129112', 'Hà Nội', 'phuongnha@gmail.com', '2001-06-12', false),
       ('Trần Văn Tuấn', '0912991991', 'Hải Phòng', 'vantuan@gmail.com', '2002-12-12', false);
INSERT INTO admins (admin_name, department, user_id)
VALUES ('Nguyễn Quản Trị', 'IT', 1);

-- Inventory Transactions Insert Statements
INSERT INTO inventory_transactions (created_at, transaction_code, transaction_type, employee_id) VALUES
                                                                                                     ('2022-01-05 10:25:33', 'IMPORT-20220105-A1B2C', 'IMPORT', 1),
                                                                                                     ('2022-02-12 14:45:12', 'EXPORT-20220212-D3E4F', 'EXPORT', 3),
                                                                                                     ('2022-03-20 09:15:47', 'IMPORT-20220320-G5H6I', 'IMPORT', 4),
                                                                                                     ('2022-04-18 16:30:21', 'EXPORT-20220418-J7K8L', 'EXPORT', 1),
                                                                                                     ('2022-05-25 11:55:36', 'IMPORT-20220525-M9N0O', 'IMPORT', 3),
                                                                                                     ('2022-06-30 13:40:05', 'EXPORT-20220630-P1Q2R', 'EXPORT', 4),
                                                                                                     ('2022-07-14 08:20:59', 'IMPORT-20220714-S3T4U', 'IMPORT', 1),
                                                                                                     ('2022-08-22 17:10:44', 'EXPORT-20220822-V5W6X', 'EXPORT', 3),
                                                                                                     ('2022-09-10 15:35:17', 'IMPORT-20220910-Y7Z8A', 'IMPORT', 4),
                                                                                                     ('2022-10-05 12:50:30', 'EXPORT-20221005-B9C0D', 'EXPORT', 1),
                                                                                                     ('2022-11-17 09:05:48', 'IMPORT-20221117-E1F2G', 'IMPORT', 3),
                                                                                                     ('2022-12-28 16:25:03', 'EXPORT-20221228-H3I4J', 'EXPORT', 4),
                                                                                                     ('2023-01-15 14:15:22', 'IMPORT-20230115-K5L6M', 'IMPORT', 1),
                                                                                                     ('2023-02-23 11:40:56', 'EXPORT-20230223-N7O8P', 'EXPORT', 3),
                                                                                                     ('2023-03-11 10:30:15', 'IMPORT-20230311-Q9R0S', 'IMPORT', 4),
                                                                                                     ('2023-04-19 15:55:42', 'EXPORT-20230419-T1U2V', 'EXPORT', 1),
                                                                                                     ('2023-05-07 08:45:29', 'IMPORT-20230507-W3X4Y', 'IMPORT', 3),
                                                                                                     ('2023-06-16 13:20:37', 'EXPORT-20230616-Z5A6B', 'EXPORT', 4),
                                                                                                     ('2023-07-24 16:10:53', 'IMPORT-20230724-C7D8E', 'IMPORT', 1),
                                                                                                     ('2023-08-30 11:35:46', 'EXPORT-20230830-F9G0H', 'EXPORT', 3),
                                                                                                     ('2023-09-12 14:50:21', 'IMPORT-20230912-I1J2K', 'IMPORT', 4),
                                                                                                     ('2023-10-21 09:15:38', 'EXPORT-20231021-L3M4N', 'EXPORT', 1),
                                                                                                     ('2023-11-05 17:40:12', 'IMPORT-20231105-O5P6Q', 'IMPORT', 3),
                                                                                                     ('2023-12-13 12:25:49', 'EXPORT-20231213-R7S8T', 'EXPORT', 4),
                                                                                                     ('2024-01-20 10:05:36', 'IMPORT-20240120-U9V0W', 'IMPORT', 1),
                                                                                                     ('2024-02-28 15:30:44', 'EXPORT-20240228-X1Y2Z', 'EXPORT', 3),
                                                                                                     ('2024-03-16 08:55:17', 'IMPORT-20240316-A3B4C', 'IMPORT', 4),
                                                                                                     ('2024-04-25 13:45:29', 'EXPORT-20240425-D5E6F', 'EXPORT', 1),
                                                                                                     ('2024-05-12 16:20:38', 'IMPORT-20240512-G7H8I', 'IMPORT', 3),
                                                                                                     ('2024-06-19 11:10:52', 'EXPORT-20240619-J9K0L', 'EXPORT', 4);

INSERT INTO categories (name, description, create_at, update_at)
VALUES ('Điện thoại', 'Các dòng điện thoại thông minh mới nhất', NOW(), NOW()),
       ('Máy tính bảng', 'Máy tính bảng phục vụ công việc và giải trí', NOW(), NOW()),
       ('Laptop', 'Laptop dành cho học tập, làm việc, gaming', NOW(), NOW()),
       ('Tai nghe', 'Tai nghe chất lượng cao, có dây và không dây', NOW(), NOW()),
       ('Tay cầm', 'Tay cầm chơi game các loại', NOW(), NOW()),
       ('Hàng cũ', 'Sản phẩm đã qua sử dụng, chất lượng tốt', NOW(), NOW()),
       ('Khuyến mãi', 'Các sản phẩm đang được giảm giá', NOW(), NOW()),
       ('Phụ kiện', 'Các loại phụ kiện điện thoại và máy tính', NOW(), NOW());

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


INSERT INTO ware_house (price, quantity, product_id) VALUES                                                        (30000000, 200, 20),
                                                        (35000000, 100, 1),
                                                        (15000000, 200, 2),
                                                        (30000000, 100, 3),
                                                        (6000000, 200, 4),
                                                        (8600000, 100, 5),
                                                        (11000000, 200, 6),
                                                        (4000000, 200, 7),
                                                        (50000000, 300, 8),
                                                        (36000000, 120, 9),
                                                        (30000000, 90, 10),
                                                        (27000000, 10, 11),
                                                        (21000000, 120, 12),
                                                        (45000000, 12, 14),
                                                        (5000000, 129, 15);

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
       ('Ha Noi', '2002-06-07', 'Nguyen Thanh Tung', true, '0390001821'),
       ('Nam Dinh', '1995-03-15', 'Le Thanh Long', true, '0987654321'),
       ('Hai Duong', '1988-11-22', 'Nguyen Thi Mai', true, '0912345678'),
       ('Vinh Phuc', '2000-07-30', 'Tran Duc Manh', true, '0976543210'),
       ('Thai Binh', '1992-05-18', 'Pham Van Cuong', true, '0945678912'),
       ('Ninh Binh', '1997-12-05', 'Hoang Thi Lan', true, '0934567890'),
       ('Ha Nam', '1990-09-25', 'Vu Van Tai', true, '0956789012'),
       ('Bac Ninh', '2002-01-10', 'Do Manh Hung', true, '0967890123'),
       ('Hung Yen', '1993-06-20', 'Mai Van Khanh', true, '0978901234'),
       ('Hai Phong', '1998-04-12', 'Nguyen Van Thang', true, '0989012345'),
       ('Lang Son', '1991-08-07', 'Tran Thi Huong', true, '0990123456');
INSERT INTO order_products (create_at, payment_status, status, total_price, customer_id)
VALUES
-- Đơn hàng của khách hàng 1
('2024-01-10', 'PENDING', 'DELIVERED', 80000, 1),
('2024-02-15', 'COMPLETED', 'DELIVERED', 120000, 1),
('2024-03-05', 'PENDING', 'DELIVERED', 45000, 1),
('2024-04-12', 'FAILED', 'CANCELLED', 60000, 1),
('2024-05-20', 'COMPLETED', 'DELIVERED', 95000, 1),

-- Đơn hàng của khách hàng 2
('2024-01-18', 'PENDING', 'DELIVERED', 70000, 2),
('2024-02-22', 'COMPLETED', 'DELIVERED', 110000, 2),
('2024-03-14', 'PENDING', 'DELIVERED', 40000, 2),
('2024-04-30', 'FAILED', 'CANCELLED', 65000, 2),
('2024-06-05', 'COMPLETED', 'DELIVERED', 99000, 2),

-- Đơn hàng của khách hàng 3
('2024-01-05', 'PENDING', 'DELIVERED', 75000, 3),
('2024-02-27', 'COMPLETED', 'DELIVERED', 125000, 3),
('2024-03-10', 'PENDING', 'DELIVERED', 42000, 3),
('2024-05-07', 'FAILED', 'CANCELLED', 59000, 3),
('2024-07-12', 'COMPLETED', 'DELIVERED', 97000, 3),

-- Đơn hàng của khách hàng 4
('2024-02-22', 'PENDING', 'DELIVERED', 77000, 4),
('2024-03-12', 'COMPLETED', 'DELIVERED', 118000, 4),
('2024-04-25', 'PENDING', 'DELIVERED', 46000, 4),
('2024-06-08', 'FAILED', 'CANCELLED', 62000, 4),
('2024-08-18', 'COMPLETED', 'DELIVERED', 98000, 4),

-- Đơn hàng của khách hàng 5
('2024-01-30', 'PENDING', 'DELIVERED', 81000, 5),
('2024-04-01', 'COMPLETED', 'DELIVERED', 130000, 5),
('2024-06-19', 'PENDING', 'DELIVERED', 48000, 5),
('2024-09-27', 'FAILED', 'CANCELLED', 63000, 5),
('2024-11-10', 'COMPLETED', 'DELIVERED', 96000, 5),

-- Đơn hàng của khách hàng 6
('2024-02-15', 'PENDING', 'DELIVERED', 70000, 6),
('2024-05-22', 'COMPLETED', 'DELIVERED', 115000, 6),
('2024-07-14', 'PENDING', 'DELIVERED', 41000, 6),
('2024-10-30', 'FAILED', 'CANCELLED', 62000, 6),
('2025-01-05', 'COMPLETED', 'DELIVERED', 100000, 6),

--
('2023-01-15', 'PENDING', 'DELIVERED', 85000, 13),
('2023-02-20', 'COMPLETED', 'DELIVERED', 130000, 13),
('2023-03-10', 'PENDING', 'DELIVERED', 47000, 13),
('2023-04-25', 'FAILED', 'CANCELLED', 62000, 13),
('2023-05-30', 'COMPLETED', 'DELIVERED', 99000, 13),

('2023-02-05', 'PENDING', 'DELIVERED', 75000, 14),
('2023-03-12', 'COMPLETED', 'DELIVERED', 115000, 14),
('2023-04-18', 'PENDING', 'DELIVERED', 43000, 14),
('2023-05-22', 'FAILED', 'CANCELLED', 58000, 14),
('2023-06-28', 'COMPLETED', 'DELIVERED', 92000, 14),

('2022-12-10', 'PENDING', 'DELIVERED', 80000, 15),
('2023-01-25', 'COMPLETED', 'DELIVERED', 125000, 15),
('2023-02-15', 'PENDING', 'DELIVERED', 45000, 15),
('2023-03-30', 'FAILED', 'CANCELLED', 60000, 15),
('2023-04-22', 'COMPLETED', 'DELIVERED', 96000, 15),

('2022-11-20', 'PENDING', 'DELIVERED', 78000, 16),
('2023-01-05', 'COMPLETED', 'DELIVERED', 120000, 16),
('2023-02-28', 'PENDING', 'DELIVERED', 44000, 16),
('2023-04-15', 'FAILED', 'CANCELLED', 59000, 16),
('2023-05-18', 'COMPLETED', 'DELIVERED', 93000, 16),

('2022-10-12', 'PENDING', 'DELIVERED', 82000, 17),
('2023-01-30', 'COMPLETED', 'DELIVERED', 135000, 17),
('2023-03-05', 'PENDING', 'DELIVERED', 46000, 17),
('2023-04-20', 'FAILED', 'CANCELLED', 63000, 17),
('2023-06-10', 'COMPLETED', 'DELIVERED', 97000, 17),

('2022-01-05', 'PENDING', 'DELIVERED', 72000, 1),
('2022-01-15', 'COMPLETED', 'DELIVERED', 95000, 1),
('2022-02-10', 'PENDING', 'DELIVERED', 48000, 1),
('2022-03-20', 'FAILED', 'CANCELLED', 65000, 1),
('2022-04-12', 'COMPLETED', 'DELIVERED', 88000, 1),

('2022-01-22', 'PENDING', 'DELIVERED', 76000, 2),
('2022-02-08', 'COMPLETED', 'DELIVERED', 105000, 2),
('2022-03-17', 'PENDING', 'DELIVERED', 43000, 2),
('2022-04-30', 'FAILED', 'CANCELLED', 59000, 2),
('2022-05-25', 'COMPLETED', 'DELIVERED', 92000, 2),

('2022-01-03', 'PENDING', 'DELIVERED', 74000, 3),
('2022-02-14', 'COMPLETED', 'DELIVERED', 118000, 3),
('2022-03-05', 'PENDING', 'DELIVERED', 45000, 3),
('2022-04-22', 'FAILED', 'CANCELLED', 61000, 3),
('2022-05-16', 'COMPLETED', 'DELIVERED', 95000, 3),

('2022-01-30', 'PENDING', 'DELIVERED', 79000, 4),
('2022-02-18', 'COMPLETED', 'DELIVERED', 112000, 4),
('2022-03-25', 'PENDING', 'DELIVERED', 47000, 4),
('2022-04-15', 'FAILED', 'CANCELLED', 63000, 4),
('2022-05-10', 'COMPLETED', 'DELIVERED', 91000, 4),

('2022-01-12', 'PENDING', 'DELIVERED', 82000, 5),
('2022-02-27', 'COMPLETED', 'DELIVERED', 125000, 5),
('2022-03-15', 'PENDING', 'DELIVERED', 46000, 5),
('2022-04-08', 'FAILED', 'CANCELLED', 62000, 5),
('2022-05-03', 'COMPLETED', 'DELIVERED', 94000, 5),

-- Tiếp tục cho các khách hàng khác
('2022-01-07', 'PENDING', 'DELIVERED', 71000, 6),
('2022-02-16', 'COMPLETED', 'DELIVERED', 98000, 6),
('2022-03-10', 'PENDING', 'DELIVERED', 44000, 6),
('2022-04-25', 'FAILED', 'CANCELLED', 58000, 6),
('2022-05-20', 'COMPLETED', 'DELIVERED', 90000, 6),

('2022-01-25', 'PENDING', 'DELIVERED', 77000, 7),
('2022-02-05', 'COMPLETED', 'DELIVERED', 106000, 7),
('2022-03-22', 'PENDING', 'DELIVERED', 42000, 7),
('2022-04-18', 'FAILED', 'CANCELLED', 60000, 7),
('2022-05-12', 'COMPLETED', 'DELIVERED', 93000, 7),

('2022-01-18', 'PENDING', 'DELIVERED', 75000, 8),
('2022-02-28', 'COMPLETED', 'DELIVERED', 115000, 8),
('2022-03-07', 'PENDING', 'DELIVERED', 45000, 8),
('2022-04-10', 'FAILED', 'CANCELLED', 59000, 8),
('2022-05-05', 'COMPLETED', 'DELIVERED', 92000, 8),

('2022-01-20', 'PENDING', 'DELIVERED', 80000, 9),
('2022-02-12', 'COMPLETED', 'DELIVERED', 108000, 9),
('2022-03-30', 'PENDING', 'DELIVERED', 47000, 9),
('2022-04-05', 'FAILED', 'CANCELLED', 64000, 9),
('2022-05-15', 'COMPLETED', 'DELIVERED', 96000, 9),

('2022-01-14', 'PENDING', 'DELIVERED', 73000, 10),
('2022-02-23', 'COMPLETED', 'DELIVERED', 120000, 10),
('2022-03-12', 'PENDING', 'DELIVERED', 43000, 10),
('2022-04-28', 'FAILED', 'CANCELLED', 57000, 10),
('2022-05-08', 'COMPLETED', 'DELIVERED', 89000, 10),

-- Khách hàng mới từ script trước
('2022-06-15', 'PENDING', 'DELIVERED', 84000, 13),
('2022-07-20', 'COMPLETED', 'DELIVERED', 128000, 13),
('2022-08-10', 'PENDING', 'DELIVERED', 46000, 13),
('2022-09-25', 'FAILED', 'CANCELLED', 61000, 13),
('2022-10-30', 'COMPLETED', 'DELIVERED', 98000, 13),

('2022-06-05', 'PENDING', 'DELIVERED', 76000, 14),
('2022-07-12', 'COMPLETED', 'DELIVERED', 114000, 14),
('2022-08-18', 'PENDING', 'DELIVERED', 44000, 14),
('2022-09-22', 'FAILED', 'CANCELLED', 59000, 14),
('2022-10-28', 'COMPLETED', 'DELIVERED', 93000, 14),

('2022-06-22', 'PENDING', 'DELIVERED', 81000, 15),
('2022-07-30', 'COMPLETED', 'DELIVERED', 126000, 15),
('2022-08-15', 'PENDING', 'DELIVERED', 45000, 15),
('2022-09-10', 'FAILED', 'CANCELLED', 62000, 15),
('2022-10-05', 'COMPLETED', 'DELIVERED', 97000, 15),

('2022-06-10', 'PENDING', 'DELIVERED', 79000, 16),
('2022-07-25', 'COMPLETED', 'DELIVERED', 121000, 16),
('2022-08-28', 'PENDING', 'DELIVERED', 47000, 16),
('2022-09-05', 'FAILED', 'CANCELLED', 64000, 16),
('2022-10-15', 'COMPLETED', 'DELIVERED', 95000, 16),

('2022-06-18', 'PENDING', 'DELIVERED', 83000, 17),
('2022-07-08', 'COMPLETED', 'DELIVERED', 132000, 17),
('2022-08-05', 'PENDING', 'DELIVERED', 48000, 17),
('2022-09-30', 'FAILED', 'CANCELLED', 65000, 17),
('2022-10-20', 'COMPLETED', 'DELIVERED', 99000, 17);

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
(3200, 1, 25, 2),

-- Đơn hàng 26
(3800, 2, 26, 3),
(7200, 1, 26, 5),

-- Đơn hàng 27
(4100, 3, 27, 6),
(5300, 2, 27, 7),

-- Đơn hàng 28
(6400, 1, 28, 8),
(4700, 2, 28, 9),
(3200, 3, 28, 10),

-- Đơn hàng 29
(5900, 2, 29, 11),
(7100, 1, 29, 12),

-- Đơn hàng 30
(4800, 3, 30, 13),
(3900, 2, 30, 14),
(5700, 1, 30, 15),

-- Đơn hàng của khách hàng 13
(5500, 2, 31, 1),
(6000, 3, 31, 2),
(1500, 1, 31, 3),

-- Đơn hàng của khách hàng 14
(2500, 2, 32, 4),
(3500, 1, 32, 5),
(2000, 4, 32, 6),

-- Đơn hàng của khách hàng 15
(8000, 2, 33, 7),
(2700, 3, 33, 8),
(5500, 1, 33, 9),

-- Đơn hàng của khách hàng 16
(9200, 2, 34, 10),
(4500, 1, 34, 11),

-- Đơn hàng của khách hàng 17
(6800, 3, 35, 12),
(2900, 2, 35, 13),

-- Tiếp tục cho các đơn hàng khác tương tự
(7500, 1, 36, 14),
(5300, 2, 36, 15),
(3400, 3, 36, 1),

(8300, 1, 37, 12),
(9500, 2, 37, 13),

(4700, 2, 38, 2),
(3900, 3, 38, 3),
(5800, 1, 38, 4),

(3200, 2, 39, 1),
(4300, 1, 39, 2),

(2500, 4, 40, 3),
(9000, 2, 40, 4),
(7600, 3, 40, 5),

(4300, 2, 41, 6),
(6500, 1, 41, 7),
(3300, 3, 41, 8),

(5500, 2, 42, 9),
(3400, 1, 42, 10),
(4300, 4, 42, 11),

(3000, 2, 43, 12),
(4500, 3, 43, 13),

(6900, 1, 44, 15),
(5400, 2, 44, 11),
(2500, 3, 44, 8),

(7300, 1, 45, 1),
(9100, 2, 45, 6),

-- Đơn hàng của khách hàng 1 năm 2022
(4200, 2, 46, 1),
(5100, 3, 46, 2),
(1300, 1, 46, 3),

-- Tiếp tục thêm chi tiết đơn hàng cho tất cả các đơn hàng mới
(2300, 2, 47, 4),
(3400, 1, 47, 5),
(1900, 4, 47, 6),

(7800, 2, 48, 7),
(2600, 3, 48, 8),
(5300, 1, 48, 9),

(9000, 2, 49, 10),
(4400, 1, 49, 11),

(6700, 3, 50, 12),
(2800, 2, 50, 13),

-- Tiếp tục với các đơn hàng khác
(7300, 1, 51, 14),
(5200, 2, 51, 15),
(3300, 3, 51, 1),

(8200, 1, 52, 12),
(9400, 2, 52, 13),

(4600, 2, 53, 2),
(3800, 3, 53, 3),
(5700, 1, 53, 4),

(3100, 2, 54, 1),
(4200, 1, 54, 2),

(2400, 4, 55, 3),
(8800, 2, 55, 4),
(7500, 3, 55, 5),

-- Tiếp tục với các đơn hàng còn lại (tôi đã rút ngắn để minh họa)
(4200, 2, 56, 6),
(6400, 1, 56, 7),
(3200, 3, 56, 8),

(5400, 2, 57, 9),
(3300, 1, 57, 10),
(4200, 4, 57, 11),

(2900, 2, 58, 12),
(4400, 3, 58, 13),

(6800, 1, 59, 15),
(5300, 2, 59, 11),
(2400, 3, 59, 8),

(7200, 1, 60, 1),
(9000, 2, 60, 6);


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
       ('CASH', 'PENDING', 10),
       ('CASH', 'PENDING', 11),
       ('CASH', 'PENDING', 12),
       ('CASH', 'PENDING', 13),
       ('CASH', 'PENDING', 14),
       ('CASH', 'PENDING', 15),
       ('CASH', 'PENDING', 16),
       ('CASH', 'PENDING', 17),
       ('CASH', 'PENDING', 18),
       ('CASH', 'PENDING', 19),
       ('CASH', 'PENDING', 20),
       ('CASH', 'PENDING', 21),
       ('CASH', 'PENDING', 22),
       ('CASH', 'PENDING', 23),
       ('CASH', 'PENDING', 24),
       ('CASH', 'PENDING', 25) ,
       ('CASH', 'PENDING', 26),
       ('CASH', 'PENDING', 27),
       ('CASH', 'PENDING', 28),
       ('CASH', 'PENDING', 29),
       ('CASH', 'PENDING', 30),
       ('CASH', 'PENDING', 31),
       ('CASH', 'PENDING', 32),
       ('CASH', 'PENDING', 33),
       ('CASH', 'PENDING', 34),
       ('CASH', 'PENDING', 35),
       ('CASH', 'PENDING', 36),
       ('CASH', 'PENDING', 37),
       ('CASH', 'PENDING', 38),
       ('CASH', 'PENDING', 39),
       ('CASH', 'PENDING', 40),
       ('CASH', 'PENDING', 41),
       ('CASH', 'PENDING', 42),
       ('CASH', 'PENDING', 43),
       ('CASH', 'PENDING', 44),
       ('CASH', 'PENDING', 45),
       ('CASH', 'PENDING', 46),
       ('CREDIT_CARD', 'PENDING', 47),
       ('ONLINE_BANKING', 'PENDING', 48),
       ('CASH', 'PENDING', 49),
       ('CREDIT_CARD', 'PENDING', 50),
       ('ONLINE_BANKING', 'PENDING', 51),
       ('CASH', 'PENDING', 52),
       ('CREDIT_CARD', 'PENDING', 53),
       ('ONLINE_BANKING', 'PENDING', 54),
       ('CASH', 'PENDING', 55),
       ('CREDIT_CARD', 'PENDING', 56),
       ('ONLINE_BANKING', 'PENDING', 57),
       ('CASH', 'PENDING', 58),
       ('CREDIT_CARD', 'PENDING', 59),
       ('ONLINE_BANKING', 'PENDING', 60);