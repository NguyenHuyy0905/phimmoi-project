-- Xóa cơ sở dữ liệu nếu tồn tại và tạo mới
DROP DATABASE IF EXISTS phimmoidb;
CREATE DATABASE phimmoidb;
USE phimmoidb;

-- Bảng countries (Quốc gia)
CREATE TABLE countries (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                           created_by VARCHAR(50),
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                           updated_by VARCHAR(50)
);

-- Bảng categories (Thể loại)
CREATE TABLE categories (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                            created_by VARCHAR(50),
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                            updated_by VARCHAR(50)
);

-- Bảng roles (Vai trò)
CREATE TABLE roles (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL
);

-- Bảng users (Người dùng)
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255),
                       role_id BIGINT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       created_by VARCHAR(50),
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                       updated_by VARCHAR(50),
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Bảng people (Diễn viên và đạo diễn)
CREATE TABLE people (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        dob DATE,
                        url_image VARCHAR(255),
                        job INT COMMENT '1 - Diễn viên, 2 - Đạo diễn'
);

-- Bảng films (Phim)
CREATE TABLE films (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       vi_title VARCHAR(255) NOT NULL,
                       en_title VARCHAR(255) DEFAULT '',
                       country_id BIGINT,
                       release_year INT,
                       view BIGINT COMMENT 'Lượt xem',
                       language VARCHAR(255) COMMENT 'Vietsub',
                       description TEXT,
                       url_image VARCHAR(255),
                       url_trailer VARCHAR(255),
                       price FLOAT DEFAULT 0 COMMENT 'Giá tiền (0 nghĩa là miễn phí)',
                       requires_purchase BOOLEAN DEFAULT FALSE COMMENT 'TRUE nếu phải mua mới xem được',
                       is_deleted BOOLEAN DEFAULT FALSE COMMENT 'TRUE - Đã bị xóa',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       created_by VARCHAR(50),
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                       updated_by VARCHAR(50),
                       FOREIGN KEY (country_id) REFERENCES countries(id)
);

-- Bảng tags (Thẻ)
CREATE TABLE tags (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      tag_name VARCHAR(255) NOT NULL
);

-- Bảng film_tag (Liên kết phim và thẻ)
CREATE TABLE film_tag (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          film_id BIGINT,
                          tag_id BIGINT,
                          FOREIGN KEY (film_id) REFERENCES films(id),
                          FOREIGN KEY (tag_id) REFERENCES tags(id)
);

-- Bảng chapters (Tập phim)
CREATE TABLE chapters (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          url_video VARCHAR(255),
                          film_id BIGINT,
                          FOREIGN KEY (film_id) REFERENCES films(id)
);

-- Bảng film_people (Liên kết phim và người)
CREATE TABLE film_people (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             film_id BIGINT,
                             people_id BIGINT COMMENT 'Diễn viên hoặc đạo diễn',
                             FOREIGN KEY (film_id) REFERENCES films(id),
                             FOREIGN KEY (people_id) REFERENCES people(id)
);

-- Bảng film_category (Liên kết phim và thể loại)
CREATE TABLE film_category (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               film_id BIGINT,
                               category_id BIGINT,
                               FOREIGN KEY (film_id) REFERENCES films(id),
                               FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Bảng film_user (Liên kết người dùng và phim đã mua)
CREATE TABLE film_user (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           user_id BIGINT,
                           film_id BIGINT,
                           purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES users(id),
                           FOREIGN KEY (film_id) REFERENCES films(id)
);

-- Bảng carts (Giỏ hàng)
CREATE TABLE carts (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       user_id BIGINT,
                       film_id BIGINT,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (film_id) REFERENCES films(id)
);

-- Bảng orders (Đơn hàng)
CREATE TABLE orders (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        user_id BIGINT,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(20),
                        total_money FLOAT CHECK (total_money >= 0),
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Bảng order_details (Chi tiết đơn hàng)
CREATE TABLE order_details (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               order_id BIGINT,
                               film_id BIGINT,
                               price FLOAT CHECK (price >= 0),
                               FOREIGN KEY (order_id) REFERENCES orders(id),
                               FOREIGN KEY (film_id) REFERENCES films(id)
);

-- Bảng payments (Thanh toán)
CREATE TABLE payments (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          user_id BIGINT,
                          order_id BIGINT,
                          payment_method ENUM('VNPAY', 'MOMO', 'CREDIT_CARD', 'PAYPAL') NOT NULL COMMENT 'Phương thức thanh toán',
                          amount FLOAT NOT NULL COMMENT 'Số tiền thanh toán',
                          status ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL COMMENT 'Trạng thái thanh toán',
                          transaction_id VARCHAR(255) UNIQUE COMMENT 'ID giao dịch từ cổng thanh toán (nếu có)',
                          payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày và giờ thanh toán',
                          FOREIGN KEY (order_id) REFERENCES orders(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);
