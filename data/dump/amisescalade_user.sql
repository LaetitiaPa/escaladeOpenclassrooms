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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','lpapeloux@gmail.com','Papeloux','Laetitia','$2a$10$AmVHvK50m4UHPkFjfN3UKeqC6mlL/9dM8JfuR5UidDtur.YDcHr/K'),(2,_binary '','j.doe@gmail.com','Doe','Jane','password'),(13,_binary '','jane.doe@gmail.com','Doe','Jane','$2a$10$XFDUduQ2VlauFcKkLu/8MObL2gw75ENOqBsY8g4AKSoapflRll22a'),(15,_binary '','jdoz@gmail.com','Doz','Janine','$2a$10$MqxG781sHRGxfOhnqoIb8OQ1Zze5AUdlPCOuOulvkoYtrWfD1KM7m'),(16,_binary '','jana.diaz@gmail.com','Diaz','Jana','$2a$10$RQ5dAmALr69DFr5x6J0.lupVYhd3OtHaM38S.btImx3bVO61HudR6'),(18,_binary '','user@gmail.com','test','User','$2a$10$jU.OzGQMnRjLSY5SJ/.5iuEXPYF7/GSoKP2CRZJZeTaqM4jZnXkLK'),(55,_binary '','joe.doe@gmail.com','Doe','Joe','$2a$10$hyj1W/QXgV9sgWVq9ElGxOlIVeVo4dEqvulGt123IN9FW76YwiFiG'),(56,_binary '','j.frusch@gmail.com','frusch','john','$2a$10$qjJUo5sIb34ZgofpyKZEH.AWKwRvrIb/zEPyE1hsgjNmob39rxOJy'),(58,_binary '','test3@gmail.com','testName','Test','$2a$10$cXXHlbUR/pXlln1lnVBQfuLyD9DaUd.3R0x48x72qGcUbjLOgp9HG'),(61,_binary '','j.doez@gmail.com','Doe','Alvin','$2a$10$GvO12dScS0rnfwCGWPbXVe4kuRsdPguxsg5kSWdyflEqm1O6Ptqyy');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-13 17:30:32
