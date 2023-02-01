CREATE TABLE IF NOT EXISTS member
(
    `member_id` INT         NOT NULL AUTO_INCREMENT,
    `username`  VARCHAR(45) NOT NULL,
    `password`  TEXT        NOT NULL,
    `algorithm` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`member_id`)
);

CREATE TABLE IF NOT EXISTS `authority`
(
    `auth_id`   INT         NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(45) NOT NULL,
    `member_id` INT         NOT NULL,
    PRIMARY KEY (`auth_id`)
);

CREATE TABLE IF NOT EXISTS `product`
(
    `product_id` INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(45) NOT NULL,
    `price`      VARCHAR(45) NOT NULL,
    `currency`   VARCHAR(45) NOT NULL,
    PRIMARY KEY (`product_id`)
);
