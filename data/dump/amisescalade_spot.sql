-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: amisescalade
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `spot`
--

DROP TABLE IF EXISTS `spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spot` (
  `spot_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `climbing_type` varchar(255) DEFAULT NULL,
  `cotation` varchar(255) DEFAULT NULL,
  `height` int(11) NOT NULL,
  `holds_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tag` bit(1) DEFAULT NULL,
  `track_number` int(11) NOT NULL,
  `tracks_pract` varchar(255) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  `holds` varchar(255) DEFAULT NULL,
  `trac_pract` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spot_id`),
  KEY `FK8mt0l1t157fhx6xneeee3jbqo` (`user_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (1,'Couenne','4a',1,'Trous','Coastal Goldfields',_binary '',1,'Familiale',NULL,NULL,NULL),(2,'Bloc','5b',2,'Trous','Mohr\'s Bluestem',_binary '\0',2,'Escarpée',NULL,NULL,NULL),(3,'Couenne','5b',3,'plats','Small Matweed',_binary '\0',3,'Familiale',NULL,NULL,NULL),(4,'Bloc','4a',4,'réglettes','Wai\'anae Mountains False Lobelia',_binary '\0',4,'Escarpée',NULL,NULL,NULL),(5,'Bloc','8b',5,'trous','Cedar Sedge',_binary '\0',5,'Correct',NULL,NULL,NULL),(6,'Couenne','8b',6,'réglettes','Cushion Draba',_binary '',6,'Correct',NULL,NULL,NULL),(7,'Couenne','6c',7,'réglettes','Santa Barbara Milkvetch',_binary '\0',7,'Escarpée',NULL,NULL,NULL),(8,'Couenne','7a',8,'colonettes','Lanai Sawsedge',_binary '',8,'Familiale',NULL,NULL,NULL),(9,'Bloc','5b',9,'colonettes','Woolly Princesplume',_binary '\0',9,'Familiale',NULL,NULL,NULL),(10,'Bloc','5b',10,'colonettes','Higuillo',_binary '',10,'Familiale',NULL,NULL,NULL),(19,'Bloc','4a',1,'Trous','Ceuse 2',_binary '\0',1,'Familiale',61,NULL,NULL);
/*!40000 ALTER TABLE `spot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-13 17:30:31
