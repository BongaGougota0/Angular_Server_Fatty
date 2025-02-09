CREATE SCHEMA `burger-app`;
USE `burger-app`;

CREATE TABLE IF NOT EXISTS `product_category`(
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS `product` (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT,
    sku VARCHAR(10),
    product_name VARCHAR(100),
    product_description VARCHAR(250),
    unit_price DOUBLE,
    image_url VARCHAR(250),
    active_status VARCHAR(1),
    units_in_stock DOUBLE,
    date_created DATETIME,
    last_updated DATETIME,
    FOREIGN KEY (category_id) REFERENCES product_category(category_id)
);

CREATE TABLE IF NOT EXISTS `users`(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(200),
    last_name VARCHAR(200),
    email VARCHAR(200),
    phone VARCHAR(12),
    user_password VARCHAR(200)
)