-- Xóa cơ sở dữ liệu nếu tồn tại và tạo mới
DROP DATABASE IF EXISTS movie;
CREATE DATABASE movie;
USE movie;

-- Bảng countries (Quốc gia)
CREATE TABLE countries (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL
);

-- Bảng categories (Thể loại)
CREATE TABLE categories (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE subscriptions (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               package_name VARCHAR(50),      -- Tên gói đăng ký (Basic, Premium, Cinematic)
                               price_per_month FLOAT, -- Giá/ tháng
                               duration_months INT,            -- Số tháng của gói (2 months)
                               ultra_hd BOOLEAN,               -- Có hỗ trợ Ultra HD hay không (TRUE/FALSE)
                               sale INT,
                               limited_availability BOOLEAN,   -- Có giới hạn quyền truy cập không (TRUE/FALSE)
                               any_device BOOLEAN,             -- Sử dụng trên mọi thiết bị (TRUE/FALSE)
                               support_24_7 BOOLEAN            -- Hỗ trợ 24/7 (TRUE/FALSE)
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
                       is_deleted BOOLEAN DEFAULT FALSE COMMENT 'TRUE - Đã bị xóa',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
--     FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Bảng people (Diễn viên và đạo diễn)
CREATE TABLE people (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        dob DATE COMMENT 'Ngay sinh',
                        pob VARCHAR(255) COMMENT 'Noi sinh',
                        url_image VARCHAR(255),
                        career INT COMMENT '1 - Diễn viên, 2 - Đạo diễn'
);

-- Bảng films (Phim)
CREATE TABLE films (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       origin_name VARCHAR(255),
                       country_id BIGINT,
                       release_year INT,
                       view BIGINT COMMENT 'Lượt xem',
                       language VARCHAR(255) COMMENT 'Vietsub',
                       description TEXT,
                       url_image VARCHAR(255),
                       url_trailer VARCHAR(255),
                       is_complete BOOLEAN DEFAULT FALSE COMMENT 'TRUE - Đã hoàn thành, FALSE - Trailer',
                       sub_id BIGINT,
                       is_deleted BOOLEAN DEFAULT FALSE COMMENT 'TRUE - Đã bị xóa',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       created_by VARCHAR(50),
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                       updated_by VARCHAR(50)
--     FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          film_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          is_updated BOOLEAN DEFAULT false COMMENT 'TRUE - Hien thi tg update moi nhat, FALSE - Hien thi thoi gian tao ra'
--     FOREIGN KEY (film_id) REFERENCES films(id),
--     FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comment_replies (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 parent_comment_id BIGINT NOT NULL,
                                 child_comment_id BIGINT NOT NULL
--     FOREIGN KEY (parent_comment_id) REFERENCES comments(id),
--     FOREIGN KEY (child_comment_id) REFERENCES comments(id),
--     CONSTRAINT chk_no_self_reply CHECK (parent_comment_id != child_comment_id)
);

-- Bảng chapters (Tập phim)
CREATE TABLE chapters (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          url_video VARCHAR(255),
                          film_id BIGINT
--     FOREIGN KEY (film_id) REFERENCES films(id)
);

-- Bảng film_people (Liên kết phim và người)
CREATE TABLE film_people (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             film_id BIGINT,
                             people_id BIGINT COMMENT 'Diễn viên hoặc đạo diễn'
--     FOREIGN KEY (film_id) REFERENCES films(id),
--     FOREIGN KEY (people_id) REFERENCES people(id)
);

-- Bảng film_category (Liên kết phim và thể loại)
CREATE TABLE film_category (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               film_id BIGINT,
                               category_id BIGINT
--     FOREIGN KEY (film_id) REFERENCES films(id),
--     FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        subscription_id BIGINT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        total_price FLOAT NOT NULL,
                        status ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL COMMENT 'Trạng thái don hang',
                        payment_method ENUM('VNPAY', 'MOMO', 'CREDIT_CARD', 'PAYPAL') NOT NULL COMMENT 'Phương thức thanh toán',
                        transaction_id VARCHAR(255) UNIQUE COMMENT 'ID giao dịch từ cổng thanh toán (nếu có)',
                        payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày và giờ thanh toán'
--     FOREIGN KEY (user_id) REFERENCES users(id),
--     FOREIGN KEY (subscription_id) REFERENCES subscriptions(id)
);

CREATE TABLE user_subscriptions (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    user_id BIGINT NOT NULL,
                                    subscription_id BIGINT NOT NULL,
                                    start_date DATE NOT NULL,
                                    end_date DATE NOT NULL,
                                    status VARCHAR(20) DEFAULT 'active'
--     FOREIGN KEY (user_id) REFERENCES users(id),
--     FOREIGN KEY (subscription_id) REFERENCES subscriptions(id)
);

CREATE TABLE reviews (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         movie_id BIGINT NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         star_rating TINYINT NOT NULL CHECK (star_rating BETWEEN 1 AND 5),
                         content TEXT,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         status ENUM('PENDING', 'APPROVE', 'CANCLE') default 'PENDING'
);



-- SELECT
--     c1.id AS comment_id,
--     c1.content AS comment_content,
--     c1.created_at AS comment_created_at,
--     sub.id AS subcomment_id,
--     sub.content AS subcomment_content,
--     sub.created_at AS subcomment_created_at
-- FROM
--     comments c1
-- LEFT JOIN
--     comment_replies cr ON c1.id = cr.parent_comment_id
-- LEFT JOIN
--     comments sub ON cr.child_comment_id = sub.id
-- WHERE
--     c1.film_id = :filmId  -- Thay :filmId bằng id của phim bạn muốn lấy bình luận
-- ORDER BY
--     c1.created_at, sub.created_at;




-- CREATE TRIGGER check_subscription_level
-- BEFORE INSERT ON orders
-- FOR EACH ROW
-- BEGIN
--     DECLARE current_level INT;
--     DECLARE new_level INT;
--
--     -- Lấy level của gói hiện tại của người dùng
--     SELECT s.level INTO current_level
--     FROM user_subscriptions us
--     JOIN subscriptions s ON us.subscription_id = s.id
--     WHERE us.user_id = NEW.user_id AND us.status = 'active';
--
--     -- Lấy level của gói mới mà người dùng muốn mua
--     SELECT level INTO new_level
--     FROM subscriptions
--     WHERE id = NEW.subscription_id;
--
--     -- Kiểm tra nếu gói mới có level thấp hơn gói hiện tại
--     IF new_level < current_level THEN
--         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Bạn không thể mua gói có chất lượng kém hơn gói hiện tại.';
--     END IF;
-- END;