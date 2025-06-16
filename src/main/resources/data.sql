-- 清空旧数据 (可选, 谨慎操作)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE customer_coupons;
TRUNCATE TABLE deliveries;
TRUNCATE TABLE reviews;
TRUNCATE TABLE order_items;
TRUNCATE TABLE orders;
TRUNCATE TABLE coupons;
TRUNCATE TABLE customers;
TRUNCATE TABLE product_variants;
TRUNCATE TABLE product_images;
TRUNCATE TABLE products;
TRUNCATE TABLE product_categories;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. 商品分类
INSERT INTO product_categories (id, name) VALUES
                                              (1, '电子产品'),
                                              (2, '服饰鞋包'),
                                              (3, '家居生活');

-- 2. 商品主数据
INSERT INTO products (id, name, description, category_id) VALUES
                                                              (1, '智能手机 Pro', '最新款旗舰智能手机，拥有强大的摄像头和超长续航。', 1),
                                                              (2, '蓝牙降噪耳机', '沉浸式音乐体验，有效隔绝环境噪音。', 1),
                                                              (3, '纯棉休闲T恤', '100%纯棉，舒适透气，简约百搭。', 2),
                                                              (4, '复古牛皮单肩包', '优质头层牛皮，复古设计，彰显品味。', 2),
                                                              (5, '人体工学办公椅', '多维度调节，支撑腰背，缓解久坐疲劳。', 3);

-- 3. 商品图片
INSERT INTO product_images (product_id, image_url) VALUES
                                                       (1, 'http://example.com/images/phone_1.jpg'),
                                                       (1, 'http://example.com/images/phone_2.jpg'),
                                                       (2, 'http://example.com/images/headphone_1.jpg'),
                                                       (3, 'http://example.com/images/tshirt_1.jpg'),
                                                       (4, 'http://example.com/images/bag_1.jpg'),
                                                       (5, 'http://example.com/images/chair_1.jpg');

-- 4. 商品规格 (SKU)
INSERT INTO product_variants (id, product_id, attributes, price, stock) VALUES
                                                                            (101, 1, '{"color": "深空黑", "storage": "256GB"}', 5999.00, 100),
                                                                            (102, 1, '{"color": "星光银", "storage": "256GB"}', 5999.00, 80),
                                                                            (103, 1, '{"color": "深空黑", "storage": "512GB"}', 6999.00, 50),
                                                                            (201, 2, '{"color": "黑色"}', 1299.00, 200),
                                                                            (202, 2, '{"color": "白色"}', 1299.00, 150),
                                                                            (301, 3, '{"color": "白色", "size": "M"}', 99.00, 500),
                                                                            (302, 3, '{"color": "黑色", "size": "L"}', 99.00, 450),
                                                                            (401, 4, '{"color": "棕色"}', 899.00, 60),
                                                                            (501, 5, '{"color": "黑色", "material": "网布"}', 1599.00, 30);

-- 5. 客户
INSERT INTO customers (id, name, email, phone, address, age) VALUES
                                                                 (1, '张三', 'zhangsan@example.com', '13800138000', '北京市朝阳区A街道1号', 30),
                                                                 (2, '李四', 'lisi@example.com', '13900139000', '上海市浦东新区B路2号', 25);

-- 6. 优惠券模板
INSERT INTO coupons (id, code, description, discount_type, discount_value, min_spend, valid_to) VALUES
                                                                                                    (1, 'SUMMER10', '夏季促销，满100减10元', 'FIXED', 10.00, 100.00, '2025-08-31 23:59:59'),
                                                                                                    (2, 'NEW20', '新用户专享8折优惠', 'PERCENTAGE', 0.20, 0.00, '2025-12-31 23:59:59'),
                                                                                                    (3, 'VIP50', 'VIP客户满500减50', 'FIXED', 50.00, 500.00, '2025-10-31 23:59:59');

-- 7. 为客户发放优惠券
INSERT INTO customer_coupons (customer_id, coupon_id, is_used) VALUES
                                                                   (1, 1, FALSE),
                                                                   (1, 2, FALSE),
                                                                   (2, 3, FALSE);