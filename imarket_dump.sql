-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: imarket
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `Category_Id` int NOT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `Created_at` timestamp NULL DEFAULT NULL,
  `Modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Category_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Food',NULL,NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cust_buyings_info`
--

DROP TABLE IF EXISTS `cust_buyings_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cust_buyings_info` (
  `Record_Id` int NOT NULL AUTO_INCREMENT,
  `Customer_Id` int NOT NULL,
  `Product_Id` int NOT NULL,
  `Buy_Price` decimal(10,0) NOT NULL,
  `Comment` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Record_Id`),
  KEY `idCustomer_idx` (`Customer_Id`),
  KEY `idProduct_idx` (`Product_Id`),
  CONSTRAINT `idCustomer` FOREIGN KEY (`Customer_Id`) REFERENCES `customers` (`Customer_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idProduct` FOREIGN KEY (`Product_Id`) REFERENCES `products` (`Product_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cust_buyings_info`
--

LOCK TABLES `cust_buyings_info` WRITE;
/*!40000 ALTER TABLE `cust_buyings_info` DISABLE KEYS */;
INSERT INTO `cust_buyings_info` VALUES (1,1,2,10,NULL),(2,1,3,20,NULL),(3,1,4,300,NULL),(4,2,1,50,NULL),(5,2,5,100,NULL),(6,3,7,140,NULL),(7,3,9,70,NULL);
/*!40000 ALTER TABLE `cust_buyings_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `Customer_Id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Customer_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Anton'),(2,'Misha'),(3,'Denis'),(4,'Alex');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','init','SQL','V1__init.sql',1114761103,'root','2022-04-23 14:21:58',110,1),(2,'2','change column names','SQL','V2__change_column_names.sql',-620416109,'root','2022-04-23 14:21:58',19,1),(3,'3','change column names2','SQL','V3__change_column_names2.sql',-31886084,'root','2022-04-23 14:21:58',11,1),(4,'4','change column names3','SQL','V4__change_column_names3.sql',264596104,'root','2022-04-23 14:21:58',87,1),(5,'5','change constraints','SQL','V5__change_constraints.sql',-482969122,'root','2022-04-23 14:21:58',73,1),(6,'6','added timestamps','SQL','V6__added_timestamps.sql',-1527099157,'root','2022-04-23 14:21:58',45,1),(7,'7','added categories','SQL','V7__added_categories.sql',922960896,'root','2022-04-23 14:21:58',121,1),(8,'8','added categories','SQL','V8__added_categories.sql',1716740444,'root','2022-04-23 14:21:58',51,1),(9,'9','added orders','SQL','V9__added_orders.sql',1910571296,'root','2022-04-23 14:21:58',27,1),(10,'10','added orders details','SQL','V10__added_orders_details.sql',-1074088450,'root','2022-04-23 14:21:58',33,1),(11,'11','modify orders details','SQL','V11__modify_orders_details.sql',0,'root','2022-04-23 14:21:58',2,1),(12,'12','modify orders details','SQL','V12__modify_orders_details.sql',-609763355,'root','2022-04-23 14:21:59',55,1),(13,'13','security','SQL','V13__security.sql',653547052,'root','2022-04-23 14:21:59',73,1),(14,'14','added descriptions','SQL','V14__added_descriptions.sql',-1355893433,'root','2022-04-23 14:21:59',51,1),(15,'15','added product comments','SQL','V15__added_product_comments.sql',-1126860929,'root','2022-04-23 14:21:59',31,1),(16,'16','added users','SQL','V16__added_users.sql',-434093039,'root','2022-05-22 10:23:05',18,0);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `Order_Id` int NOT NULL AUTO_INCREMENT,
  `Order_Price` decimal(8,2) DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` decimal(10,0) DEFAULT NULL,
  `Created_at` timestamp NULL DEFAULT NULL,
  `Modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Order_Id`),
  KEY `Customer_Id_idx` (`Username`),
  CONSTRAINT `username` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,135.00,'user1',135,NULL,NULL,'2021-08-07 23:21:56','2021-08-07 23:21:56'),(5,NULL,'user1',103,'hjvjm',765876,'2022-06-26 16:26:48','2022-06-26 16:26:48');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_details`
--

DROP TABLE IF EXISTS `orders_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_details` (
  `Order_Detail_Id` int NOT NULL AUTO_INCREMENT,
  `Order_Id` int NOT NULL,
  `Product_Id` int NOT NULL,
  `Quantity` int DEFAULT NULL,
  `Price_Per_Product` decimal(10,0) DEFAULT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  `Created_at` varchar(45) DEFAULT NULL,
  `Modified_at` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Order_Detail_Id`),
  KEY `Product_Id_idx` (`Product_Id`),
  KEY `Order_Id` (`Order_Id`),
  CONSTRAINT `Order_Id` FOREIGN KEY (`Order_Id`) REFERENCES `orders` (`Order_Id`),
  CONSTRAINT `Product_Id` FOREIGN KEY (`Product_Id`) REFERENCES `products` (`Product_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_details`
--

LOCK TABLES `orders_details` WRITE;
/*!40000 ALTER TABLE `orders_details` DISABLE KEYS */;
INSERT INTO `orders_details` VALUES (1,2,10,1,17,17,'2021-08-07 23:21:55.964397','2021-08-07 23:21:55.964397'),(2,2,9,2,29,58,'2021-08-07 23:21:55.964397','2021-08-07 23:21:55.964397'),(3,2,8,3,20,60,'2021-08-07 23:21:55.964397','2021-08-07 23:21:55.964397'),(8,5,4,2,0,0,'2022-06-26 16:26:48.50991','2022-06-26 16:26:48.50991'),(9,5,3,5,0,0,'2022-06-26 16:26:48.517637','2022-06-26 16:26:48.517637');
/*!40000 ALTER TABLE `orders_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `Product_Id` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Price` decimal(8,2) NOT NULL,
  `Description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Category_Id` int DEFAULT NULL,
  `Created_at` timestamp NULL DEFAULT NULL,
  `Modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Product_Id`),
  KEY `Category_Id` (`Category_Id`),
  CONSTRAINT `Category_Id` FOREIGN KEY (`Category_Id`) REFERENCES `categories` (`Category_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Apple',5.00,NULL,1,NULL,NULL),(2,'Bread',10.00,NULL,1,NULL,NULL),(3,'Butter',15.00,NULL,1,NULL,NULL),(4,'Milk',14.00,NULL,1,NULL,NULL),(5,'Chocolate',22.00,NULL,1,NULL,NULL),(6,'Meat',150.00,NULL,1,NULL,NULL),(7,'Banana',27.00,NULL,1,NULL,NULL),(8,'Juice',20.00,NULL,1,NULL,NULL),(9,'Grape',29.00,NULL,1,NULL,NULL),(10,'Lime',17.00,NULL,1,NULL,NULL),(11,'Tomato',19.00,NULL,1,NULL,NULL),(12,'Cheese',40.00,NULL,1,NULL,NULL),(13,'Cake',32.00,NULL,1,NULL,NULL),(14,'Frozen pizza',40.00,NULL,1,NULL,NULL),(15,'Strawberry',35.00,NULL,1,NULL,NULL),(16,'Pineapple',31.00,NULL,1,NULL,NULL),(17,'Sausage',50.00,NULL,1,NULL,NULL),(18,'Icecream',19.00,NULL,1,NULL,NULL),(19,'Rice',25.00,NULL,1,NULL,NULL),(20,'Carrot',4.00,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_comments`
--

DROP TABLE IF EXISTS `products_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_comments` (
  `Product_Comment_Id` int NOT NULL AUTO_INCREMENT,
  `User_Id` int NOT NULL,
  `Product_Id` int NOT NULL,
  `Product_Comment` varchar(500) NOT NULL,
  `Created_at` timestamp NULL DEFAULT NULL,
  `Modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Product_Comment_Id`),
  KEY `products_idx` (`Product_Id`),
  KEY `users_idx` (`User_Id`),
  CONSTRAINT `products` FOREIGN KEY (`Product_Id`) REFERENCES `products` (`Product_Id`),
  CONSTRAINT `users` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_comments`
--

LOCK TABLES `products_comments` WRITE;
/*!40000 ALTER TABLE `products_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `Role_Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Created_at` timestamp NOT NULL,
  `Modified_at` timestamp NOT NULL,
  PRIMARY KEY (`Role_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER','2021-08-07 23:21:56','2021-08-07 23:21:56'),(2,'ROLE_ADMIN','2021-08-07 23:21:56','2021-08-07 23:21:56'),(3,'ROLE_CUSTOMER','2021-08-07 23:21:56','2021-08-07 23:21:56');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `User_Id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Created_at` timestamp NOT NULL,
  `Modified_at` timestamp NOT NULL,
  PRIMARY KEY (`User_Id`),
  KEY `username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user1','$2a$12$ez7CyZyLDdz1oxuUG0q4XOajVHJm7xHQycmj2eNFXRJrHejAfYDKm','user1@mail.ru','2021-08-07 23:21:56','2021-08-07 23:21:56'),(2,'user2','$2a$12$w7sodbwvjuFFC5HgmImUjugRJL3kMKNPPtJJDOCKQNgKKaeKmdjlS','user2@mail.ru','2021-08-07 23:21:56','2021-08-07 23:21:56');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `User_Id` int NOT NULL,
  `Role_Id` int NOT NULL,
  `Created_at` timestamp NOT NULL,
  `Modified_at` timestamp NOT NULL,
  PRIMARY KEY (`User_Id`,`Role_Id`),
  CONSTRAINT `users_Id` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1,'2021-08-07 23:21:56','2021-08-07 23:21:56'),(2,2,'2021-08-07 23:21:56','2021-08-07 23:21:56');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-27 22:10:49
