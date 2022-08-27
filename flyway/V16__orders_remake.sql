ALTER TABLE `imarket`.`orders`
DROP FOREIGN KEY `Customer_Id`;
ALTER TABLE `imarket`.`orders`
CHANGE COLUMN `Customer_Id` `username` VARCHAR(255) NOT NULL ;
ALTER TABLE `imarket`.`orders`
ADD CONSTRAINT `username`
  FOREIGN KEY (`Username`)
  REFERENCES `imarket`.`users` (`Username`);

ALTER TABLE `imarket`.`users`
ADD INDEX `username` (`Username` ASC) VISIBLE;

INSERT INTO `imarket`.`orders`
(`Order_Id`,
`Order_Price`,
`Username`,
`Created_at`,
`Modified_at`)
VALUES
(<{Order_Id: }>,
<{Order_Price: }>,
<{Username: }>,
<{Created_at: }>,
<{Modified_at: }>);

ALTER TABLE `imarket`.`orders_details`
ADD COLUMN `Price_Per_Product` decimal(10,0) NULL AFTER `Quantity`;

ALTER TABLE `imarket`.`orders_details`
ADD COLUMN `Price` decimal(10,0) NULL AFTER `Price_Per_Product`;

ALTER TABLE `imarket`.`orders`
ADD COLUMN `Phone` decimal(10,0) NULL AFTER `Address`;


update `imarket`.`orders_details`
set Price = 60
where Order_Detail_Id = 3;

update `imarket`.`orders_details`
set Price_Per_Product = 20
where Order_Detail_Id = 3;

update `imarket`.`orders`
set Price = 135
where Order_Id = 2;