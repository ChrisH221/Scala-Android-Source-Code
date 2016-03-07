CREATE TABLE key_code(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
keycode varchar(200) NOT NULL,
username varchar(100) NOT NULL,
FOREIGN KEY (username) REFERENCES users(username)


)