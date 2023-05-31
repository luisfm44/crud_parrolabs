CREATE SCHEMA crud;

-- Create ShippingAddress table
CREATE TABLE crud.shipping_address (
    shipping_address_id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    country VARCHAR(50) NOT NULL
);

CREATE TABLE crud.customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    shipping_address_id INT,
    FOREIGN KEY (shipping_address_id) REFERENCES crud.shipping_address(shipping_address_id)
);



-- Create Product table
CREATE TABLE crud.product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(200) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    weight DECIMAL(10, 2) NOT NULL
);

-- Create Order table
CREATE TABLE crud.orders (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  orderNumber VARCHAR(255),
  date DATE,
  customer_id INT,
  shipping_address_id INT,
  paymentType VARCHAR(255),
  totalOrderValue DECIMAL(19, 2),
  UNIQUE (orderNumber),
  FOREIGN KEY (customer_id) REFERENCES crud.customer(customer_id),
  FOREIGN KEY (shipping_address_id) REFERENCES crud.shipping_address(shipping_address_id)
);


CREATE TABLE crud.order_product_quantities_map (
  order_id BIGINT NOT NULL,
  product_id INT NOT NULL,
  quantity INTEGER,
  PRIMARY KEY (order_id, product_id),
  CONSTRAINT FK_order_product_quantities_order_id FOREIGN KEY (order_id) REFERENCES crud.orders (id),
  CONSTRAINT FK_order_product_quantities_product_id FOREIGN KEY (product_id) REFERENCES crud.product (product_id)
);