DROP DATABASE IF EXISTS room_rental;
CREATE DATABASE room_rental CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE room_rental;

-- Tạo bảng payment_method
CREATE TABLE payment_method (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Dữ liệu mẫu cho payment_method
INSERT INTO payment_method (name) VALUES
('Theo tháng'), 
('Theo quý'), 
('Theo năm');

-- Tạo bảng rental_room
CREATE TABLE rental_room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tenant_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    start_date DATE NOT NULL,
    payment_method_id INT NOT NULL,
    note VARCHAR(200),
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(id)
);

-- Dữ liệu mẫu cho rental_room
INSERT INTO rental_room (tenant_name, phone_number, start_date, payment_method_id, note) VALUES
('Nguyễn Văn A', '0912345678', '2025-08-01', 1, 'Thuê theo tháng'),
('Trần Thị B', '0987654321', '2025-09-15', 2, 'Thanh toán theo quý'),
('Lê Văn C', '0971122334', '2025-07-20', 3, 'Thanh toán theo năm');