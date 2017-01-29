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
-- Table structure for table `eventbasics`
--

DROP TABLE IF EXISTS `eventbasics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventbasics` (
  `eventArtist` varchar(45) NOT NULL,
  `eventDate` varchar(45) NOT NULL,
  `eventVenue` varchar(45) NOT NULL,
  `eventCost` float NOT NULL,
  PRIMARY KEY (`eventDate`,`eventVenue`),
  KEY `artist_idx` (`eventArtist`),
  KEY `cost_idx` (`eventCost`),
  CONSTRAINT `artist` FOREIGN KEY (`eventArtist`) REFERENCES `event` (`eventArtist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cost` FOREIGN KEY (`eventCost`) REFERENCES `eventspecifics` (`eventArenaCost`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventbasics`
--

LOCK TABLES `eventbasics` WRITE;
/*!40000 ALTER TABLE `eventbasics` DISABLE KEYS */;
INSERT INTO `eventbasics` VALUES ('The Killers','2017-06-24 20:00:00','Plateia Nerou',55),('Muse','2017-09-05 21:00:00','Terra Vibe',50),('Bon Jovi','2017-09-21 21:00:00','OAKA',50),('TLSP','2017-10-08 22:00:00','SEF',40),('RHCP','2017-11-13 20:00:00','OAKA',45),('Rolling Stones','2018-06-08 21:00:00','OAKA',50),('Eminem','2018-06-21 21:00:00','SEF',50);
/*!40000 ALTER TABLE `eventbasics` ENABLE KEYS */;
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
