CREATE TABLE `imarket`.`products_comments` (
  `Product_Comment_Id` INT NOT NULL AUTO_INCREMENT,
  `User_Id` INT NOT NULL,
  `Product_Id` INT NOT NULL,
  `Product_Comment` VARCHAR(500) NOT NULL,
  `Created_at` TIMESTAMP NULL,
  `Modified_at` TIMESTAMP NULL,
  PRIMARY KEY (`Product_Comment_Id`),
  INDEX `products_idx` (`Product_Id` ASC) VISIBLE,
  INDEX `users_idx` (`User_Id` ASC) VISIBLE,
  CONSTRAINT `products`
    FOREIGN KEY (`Product_Id`)
    REFERENCES `imarket`.`products` (`Product_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `users`
    FOREIGN KEY (`User_Id`)
    REFERENCES `imarket`.`users` (`User_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
