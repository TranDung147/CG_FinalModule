#Thêm dữ liệu vào category
INSERT INTO categories ( categoryid , name) VALUES
                                      (1, 'Điện thoại'),
                                      (2, 'Máy tính bảng'),
                                      (3, 'Laptop'),
                                      (4, 'Tai nghe'),
                                      (5, 'Tay cầm'),
                                      (6, 'Hàng cũ'),
                                      (7, 'Khuyến mãi');
# Thêm dữ liệu vào brand
INSERT INTO brands (name, create_at, update_at) VALUES ('Apple', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at) VALUES ('Samsung', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at) VALUES ('Sony', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at) VALUES ('Asus', NOW(), NOW());
INSERT INTO brands (name, create_at, update_at) VALUES ('Dell', NOW(), NOW());
# Thêm dữ liệu vào product

INSERT INTO products (name, price, description, create_at, update_at, category_id, brand_id) VALUES
('iPhone 15', 1200.00, 'Điện thoại Apple iPhone 15 128GB', NOW(), NOW(), 1, 1),
('Samsung Galaxy S23', 1100.00, 'Điện thoại Samsung Galaxy S23 Ultra 256GB', NOW(), NOW(), 1, 2),
('Sony WH-1000XM5', 400.00, 'Tai nghe chống ồn Sony WH-1000XM5', NOW(), NOW(), 4, 3),
('Dell XPS 15', 1500.00, 'Laptop Dell XPS 15 9530', NOW(), NOW(), 3, 5),
('Asus ROG Strix G16', 1800.00, 'Laptop gaming Asus ROG Strix G16', NOW(), NOW(), 3, 4);
