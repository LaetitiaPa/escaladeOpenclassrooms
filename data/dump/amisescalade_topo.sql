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
-- Table structure for table `topo`
--

DROP TABLE IF EXISTS `topo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topo` (
  `topo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `availability` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `publishing_date` datetime DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `resa_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`topo_id`),
  KEY `FK9dnn4clx2unuwcy8s8js3gt8d` (`resa_id`),
  KEY `FKpurggp4l82two9ekgr11hx2qk` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topo`
--

LOCK TABLES `topo` WRITE;
/*!40000 ALTER TABLE `topo` DISABLE KEYS */;
INSERT INTO `topo` VALUES (5,_binary '\0','Auvergne','2020-03-02 00:00:00','Le meilleur guide de la région','mon topo 2',74,56),(6,_binary '\0','Provence','2020-03-03 00:00:00','Guide de Provence','Les meilleurs sites de Provence',75,56),(7,_binary '\0','Provence','2020-03-03 00:00:00','Le meilleur guide de la région','Les meilleurs sites ',76,61),(8,_binary '\0','Provence','2020-03-04 00:00:00','Guide de Provence','Les meilleurs sites d\'escalades du Sud',79,56),(9,_binary '','Provence','2020-03-03 00:00:00','Le meilleur guide de la région','Topo Provenciale',NULL,61),(10,_binary '','Provence','2020-03-03 00:00:00','Le meilleur guide de la région','Topo Provenciale - volume 2',NULL,61),(11,_binary '','Provence','2020-03-03 00:00:00','Le meilleur guide de la région','Topo Provenciale - volume 3',78,61),(12,_binary '','Aude','2020-03-03 00:00:00','Le topo de la région de l\'Aude','Mon premier topo',NULL,61),(13,_binary '','Provence','2020-03-05 00:00:00','Le topo de Provence','Mon topo test',NULL,61),(14,_binary '','Provence','2020-03-05 00:00:00','Le topo de Provence','Mon topo test 3',NULL,61),(15,_binary '\0','Provence','2020-03-05 00:00:00','Mon topo Provencial','mon topo test Provence',80,61);
/*!40000 ALTER TABLE `topo` ENABLE KEYS */;
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
