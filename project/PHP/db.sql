CREATE TABLE user(
username varchar(100) PRIMARY KEY,
password varchar(100) NOT NULL,
date_joined timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP


)

CREATE TABLE key_code(
id int(6) unsigned AUTO_INCREMENT PRIMARY KEY,
password varchar(100) NOT NULL,
keycode longtext,
username varchar(100) NOT NULL,
fileName varchar(200) NOT NULL,
imageEncode longtext NOT NULL
)

