CREATE TABLE `products` (
               `id` int NOT NULL AUTO_INCREMENT,
               `title` varchar(45) NOT NULL,
               `price` decimal(10,0) NOT NULL,
               PRIMARY KEY (`id`)
             ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('1', 'Apple', '5');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('2', 'Bread', '10');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('3', 'Butter', '15');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('4', 'Milk', '14');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('5', 'Chocolate', '22');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('6', 'Meat', '150');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('7', 'Banana', '27');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('8', 'Juice', '20');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('9', 'Grape', '29');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('10', 'Lime', '17');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('11', 'Tomato', '19');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('12', 'Cheese', '40');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('13', 'Cake', '32');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('14', 'Frozen pizza', '40');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('15', 'Strawberry', '35');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('16', 'Pineapple', '31');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('17', 'Sausage', '50');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('18', 'Icecream', '19');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('19', 'Rice', '25');
INSERT INTO `imarket`.`products` (`id`, `title`, `price`) VALUES ('20', 'Carrot', '4');



CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `imarket`.`customers` (`id`, `name`) VALUES ('1', 'Anton');
INSERT INTO `imarket`.`customers` (`id`, `name`) VALUES ('2', 'Misha');
INSERT INTO `imarket`.`customers` (`id`, `name`) VALUES ('3', 'Denis');
INSERT INTO `imarket`.`customers` (`id`, `name`) VALUES ('4', 'Alex');

CREATE TABLE `cust_buyings_info` (
  `idRecord` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NOT NULL,
  `idProduct` int NOT NULL,
  `buyPrice` decimal(10,0) NOT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRecord`),
  KEY `idCustomer_idx` (`idCustomer`),
  KEY `idProduct_idx` (`idProduct`),
  CONSTRAINT `idCustomer` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`id`),
  CONSTRAINT `idProduct` FOREIGN KEY (`idProduct`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('1', '1', '2', '10');
INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('2', '1', '3', '20');
INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('3', '1', '4', '300');
INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('4', '2', '1', '50');
INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('5', '2', '5', '100');
INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('6', '3', '7', '140');
INSERT INTO `imarket`.`cust_buyings_info` (`idRecord`, `idCustomer`, `idProduct`, `buyPrice`) VALUES ('7', '3', '9', '70');

