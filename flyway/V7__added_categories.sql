CREATE TABLE `imarket`.`categories` (
  `Category_Id` INT NOT NULL,
  `Title` VARCHAR(45) NULL,
  `Created_at` TIMESTAMP NULL,
  `Modified_at` TIMESTAMP NULL,
  PRIMARY KEY (`Category_Id`));


INSERT INTO `imarket`.`categories` (`Category_Id`, `title`) VALUES ('1', 'Food');

ALTER TABLE products
ADD COLUMN Category_Id INT AFTER Price;

ALTER TABLE products
ADD CONSTRAINT `Category_Id` FOREIGN KEY (`Category_Id`) REFERENCES `categories` (`Category_Id`);

UPDATE products
SET Category_Id = 1;