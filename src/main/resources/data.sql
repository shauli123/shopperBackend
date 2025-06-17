CREATE TABLE users (
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) DEFAULT 'USER',
    PRIMARY KEY (username)
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT,
    user_username VARCHAR(255) NOT NULL,
    order_date DATE NOT NULL,
    shipping_address VARCHAR(255) NOT NULL,
    total_price INT NOT NULL,
    status VARCHAR(255) NOT NULL DEFAULT 'TEMP',
    FOREIGN KEY (user_username) REFERENCES users(username),
    PRIMARY KEY(id)
);

CREATE TABLE items (
    id INT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    img VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE order_items (
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    amount INT NOT NULL,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);

CREATE TABLE fav_items (
    item_id INT NOT NULL,
    user_username VARCHAR(255) NOT NULL,
    PRIMARY KEY (item_id, user_username), -- Corrected line
    FOREIGN KEY (item_id) REFERENCES items(id),
    FOREIGN KEY (user_username) REFERENCES users(username)
);

INSERT INTO users (first_name, last_name, email, phone, address, username, password, role) VALUES
('Miki', 'Gabay', 'mikigabay@gmail.com', '0585236376', '45 Ben Eliezer St', 'mikigabay', '$2a$10$24P9JHWZJm8yRsJRpP4a.e11OvU9ynMvz6XAKJOrxl8Nhph7mojJ2', 'USER'),
('Amitay', 'Gabay', 'amitaygabay1@gmail.com', '0504380333', '38 Erez St', 'amitaygabay', '$2a$10$K78Qy75RrDNQcAolPojuM.sI.otXpP23xhZYJ7p2fXrIMoI.k2ehO', 'USER');

INSERT INTO items (title, img, price, stock) VALUES
('Clock', 'https://www.ikea.com/gb/en/p/pluttis-wall-clock-black-10540847/', 50, 190),
('Pen', 'https://www.montblanc.com/variants/images/1647597318123162/A/w2500.jpg', 10, 500),
('Guitar', 'https://shop.sg.yamaha.com/media/catalog/product/c/4/c40_119414_2400.jpg?optimize=high&fit=bounds&height=700&width=700&canvas=700:700', 300, 100),
('3D Printer', 'https://cdn.shopify.com/s/files/1/0637/7517/8972/files/Tronxy-XY-3-SE-Standard-2-in-1-Set-Laser-3-in-1-Set-3D-Printer-DIY-Kit-255-255-260mm-Modify-15.jpg?v=1700966383', 500, 120),
('Gaming PC', 'https://www.memorypc.eu/media/84/37/92/1742991330/563471-05-1742991328-secondlast-1742991329.webp', 2500, 175),
('TV', 'https://images.philips.com/is/image/philipsconsumer/b6a49b5fde77452baa9aafe200fbceab?$pnglarge$&wid=1250', 2050, 1000),
('Smart Watch', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTypgAni-wQ_aeb0v9m8tmyO-SXUmwqMmI3NA&s', 575, 675),
('Book', 'https://dictionary.cambridge.org/images/thumb/book_noun_001_01679.jpg?version=6.0.53', 25, 1050),
('Mug', 'https://www.arakucoffee.in/cdn/shop/files/Arakuday23633-Edit.jpg?v=1686902422https://www.arakucoffee.in/cdn/shop/files/Arakuday23633-Edit.jpg?v=1686902422', 10, 2000),
('Water Gun', 'https://www.marshmellomusic.com/cdn/shop/products/JOYTIMECOLLECTIVE_SS20_PEACEWATERGUN_2.jpg?v=1599212637', 5, 3555);

