CREATE TABLE IF NOT EXISTS `users`
(
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NULL,
    `enabled`  INT         NOT NULL,
    PRIMARY KEY (`username`)
);

CREATE TABLE IF NOT EXISTS `authorities`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `username`  VARCHAR(45) NOT NULL,
    `authority` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);
