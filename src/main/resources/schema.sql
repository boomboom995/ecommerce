-- 删除已存在的表，以防重启应用时出错
DROP TABLE IF EXISTS customer_coupons;
DROP TABLE IF EXISTS deliveries;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS coupons;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS product_variants;
DROP TABLE IF EXISTS product_images;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;

-- 商品分类表
CREATE TABLE product_categories (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL
);

-- 商品主表
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          category_id BIGINT,
                          view_count BIGINT DEFAULT 0,
                          FOREIGN KEY (category_id) REFERENCES product_categories(id)
);

-- 商品图片表
CREATE TABLE product_images (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                product_id BIGINT,
                                image_url VARCHAR(255) NOT NULL,
                                FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 商品规格/SKU表
CREATE TABLE product_variants (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  product_id BIGINT,
                                  attributes VARCHAR(255),
                                  price DECIMAL(10, 2) NOT NULL,
                                  stock INT NOT NULL,
                                  purchase_count BIGINT DEFAULT 0,
                                  FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 客户表
CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE,
                           phone VARCHAR(20) UNIQUE,
                           address TEXT,
                           age INT,
                           return_rate DECIMAL(5, 4) DEFAULT 0.0
);

-- 优惠券模板表
CREATE TABLE coupons (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         code VARCHAR(50) UNIQUE NOT NULL,
                         description VARCHAR(255),
                         discount_type VARCHAR(20) NOT NULL,
                         discount_value DECIMAL(10, 2) NOT NULL,
                         min_spend DECIMAL(10, 2) DEFAULT 0.00,
                         valid_to TIMESTAMP
);

-- 用户持有券表
CREATE TABLE customer_coupons (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  customer_id BIGINT,
                                  coupon_id BIGINT,
                                  is_used BOOLEAN DEFAULT FALSE,
                                  FOREIGN KEY (customer_id) REFERENCES customers(id),
                                  FOREIGN KEY (coupon_id) REFERENCES coupons(id)
);

-- 订单表
-- ... (其他表的CREATE语句保持不变) ...

-- ▼▼▼ 修改 orders 表结构 ▼▼▼
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id BIGINT,
                        shipping_address TEXT NOT NULL,
                        original_amount DECIMAL(10, 2) NOT NULL,
                        discount_amount DECIMAL(10, 2) DEFAULT 0.00,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        coupon_code VARCHAR(50),
                        status VARCHAR(50),
                        order_date TIMESTAMP,
                        FOREIGN KEY (customer_id) REFERENCES customers(id)
);
-- ... (order_items, reviews, deliveries 表的CREATE语句保持不变) ...
-- 订单项表
CREATE TABLE order_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             order_id BIGINT,
                             variant_id BIGINT,
                             quantity INT NOT NULL,
                             price DECIMAL(10, 2) NOT NULL,
                             is_returned BOOLEAN DEFAULT FALSE,
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (variant_id) REFERENCES product_variants(id)
);

-- ▼▼▼【V2.0 修改】评论表 ▼▼▼
-- 修改 CREATE TABLE reviews ...
CREATE TABLE reviews (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,

                         order_item_id BIGINT UNIQUE, -- 新增，并设为 UNIQUE 确保一个订单项只有一个评论
                         logistics_rating TINYINT,
                         quality_rating TINYINT,
                         service_rating TINYINT,
                         comment_text TEXT,
                         created_at TIMESTAMP,
                         FOREIGN KEY (order_item_id) REFERENCES order_items(id) -- 新增
);

-- ▼▼▼【V2.0 修改】配送表 ▼▼▼
-- 将 order_id 替换为 order_item_id 以支持拆单发货
CREATE TABLE deliveries (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            order_item_id BIGINT, -- 修改点：关联到订单项
                            carrier VARCHAR(100),
                            tracking_number VARCHAR(100),
                            status VARCHAR(50),
                            shipped_date TIMESTAMP,
                            delivered_date TIMESTAMP,
                            FOREIGN KEY (order_item_id) REFERENCES order_items(id) -- 修改点
);