-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: eventdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `eventspecifics`
--

DROP TABLE IF EXISTS `eventspecifics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventspecifics` (
  `eventArtist` varchar(45) NOT NULL,
  `eventArenaRemain` int(11) NOT NULL DEFAULT '0',
  `eventArenaSold` int(11) NOT NULL DEFAULT '0',
  `eventArenaCost` float NOT NULL DEFAULT '0',
  `eventLeftRemain` int(11) DEFAULT '0',
  `eventLeftSold` int(11) DEFAULT '0',
  `eventLeftCost` float DEFAULT '0',
  `eventRightRemain` int(11) DEFAULT '0',
  `eventRightSold` int(11) DEFAULT '0',
  `eventRightCost` float DEFAULT '0',
  KEY `artist_idx` (`eventArtist`),
  KEY `cost` (`eventArenaCost`),
  CONSTRAINT `artist1` FOREIGN KEY (`eventArtist`) REFERENCES `event` (`eventArtist`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventspecifics`
--

LOCK TABLES `eventspecifics` WRITE;
/*!40000 ALTER TABLE `eventspecifics` DISABLE KEYS */;
INSERT INTO `eventspecifics` VALUES ('Bon Jovi',19998,2,50,1998,8002,60,0,10000,60),('RHCP',11000,9000,45,4500,5500,50,3100,6900,50),('TLSP',5092,9908,40,5881,4119,50,5232,4768,50),('Eminem',4000,11000,50,2500,7500,60,5800,4200,60),('Muse',14000,6000,50,7000,8000,55,7000,8000,55),('The Killers',17000,18000,55,0,15000,60,6000,9000,60),('Rolling Stones',12000,28000,50,7000,8000,65,12000,3000,65);
/*!40000 ALTER TABLE `eventspecifics` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-29 19:52:44
