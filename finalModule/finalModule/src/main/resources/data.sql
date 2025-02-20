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
INSERT INTO brands (name, description, status, country, create_at, update_at)
VALUES
    ('Apple', 'Thương hiệu công nghệ hàng đầu', 1, 'USA', NOW(), NOW()),
    ('Samsung', 'Tập đoàn điện tử Hàn Quốc', 1, 'South Korea', NOW(), NOW()),
    ('Sony', 'Công ty điện tử Nhật Bản', 1,  'Japan', NOW(), NOW()),
    ('Asus', 'Hãng sản xuất máy tính và phần cứng nổi tiếng', 1,  'Taiwan', NOW(), NOW()),
    ('Dell', 'Công ty máy tính hàng đầu thế giới', 1, 'USA', NOW(), NOW());

# Thêm dữ liệu vào product

INSERT INTO products (name, price, description, create_at, update_at, category_id, brand_id) VALUES
('iPhone 15', 21000000, 'Điện thoại Apple iPhone 15 128GB', NOW(), NOW(), 1, 1),
('Samsung Galaxy S23', 11000000, 'Điện thoại Samsung Galaxy S23 Ultra 256GB', NOW(), NOW(), 1, 2),
('Sony WH-1000XM5', 400000, 'Tai nghe chống ồn Sony WH-1000XM5', NOW(), NOW(), 4, 3),
('Dell XPS 15', 1500000, 'Laptop Dell XPS 15 9530', NOW(), NOW(), 3, 5),
('Asus ROG Strix G16', 1800000, 'Laptop gaming Asus ROG Strix G16', NOW(), NOW(), 3, 4);
