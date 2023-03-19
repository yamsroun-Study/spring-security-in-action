INSERT INTO `member` (`member_id`, `username`, `password`, `algorithm`)
VALUES ('1', 'john', '$2a$10$WCMzjWlOH9qBexvTI3/amuhXAEPtd1vRqVIrqvPiJsWYL2N2T10oS', 'BCRYPT');

INSERT INTO `authority` (`auth_id`, `name`, `member_id`)
VALUES ('1', 'READ', '1');
INSERT INTO `authority` (`auth_id`, `name`, `member_id`)
VALUES ('2', 'WRITE', '1');

INSERT INTO `product` (`product_id`, `name`, `price`, `currency`)
VALUES ('1', 'Chocolate', '10', 'USD');