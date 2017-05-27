CREATE DATABASE  IF NOT EXISTS `workshapedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `workshapedb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: workshape-pc    Database: workshapedb
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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `idevent` int(11) NOT NULL AUTO_INCREMENT,
  `event` varchar(45) DEFAULT NULL,
  `qr_code` varchar(45) NOT NULL,
  `date` datetime DEFAULT NULL,
  `champ1` varchar(45) DEFAULT NULL,
  `valeur1` varchar(45) DEFAULT NULL,
  `champ2` varchar(45) DEFAULT NULL,
  `valeur2` varchar(45) DEFAULT NULL,
  `champ3` varchar(45) DEFAULT NULL,
  `valeur3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idevent`,`qr_code`)
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (129,'new','D329-2B-02 GE0402 37% GLA600g PW ','2017-02-06 13:51:00','Nom','Verre noir tencate',NULL,NULL,NULL,NULL),(130,'update','D329-2B-02 GE0402 37% GLA600g PW ','2017-02-06 13:52:00',NULL,NULL,NULL,NULL,NULL,NULL),(131,'new','D329-2B-02 GE0402 37% GLA600g PW 1.25','2017-02-06 13:54:00','Nom','Verre résine noir',NULL,NULL,NULL,NULL),(132,'new','PP166261016/003','2017-02-06 13:56:00','Nom','Verre résine noir',NULL,NULL,NULL,NULL),(133,'update','PP166261016/003','2017-02-06 13:57:00',NULL,NULL,NULL,NULL,NULL,NULL),(134,'new','D329-2B-00 HS3641 42% CAR205g PW AS4C','2017-02-06 13:59:00','Nom','Carbone OGON',NULL,NULL,NULL,NULL),(135,'new','D329-2B-00 HS3641 42% CAR205g PW AS4C 1.27m','2017-02-06 14:02:00','Nom','Carbone OGON',NULL,NULL,NULL,NULL),(136,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C','2017-02-06 14:02:00',NULL,NULL,NULL,NULL,NULL,NULL),(137,'new','D329-2B-00 HS3641 42% CAR205g PW AS4C 1.27m.','2017-02-06 14:04:00','Nom','Carbone OGON',NULL,NULL,NULL,NULL),(138,'new','HX40-00 HS2016 CBN 650gsm 2x2T 35%rw 1.25m','2017-02-06 14:09:00','Nom','HX40',NULL,NULL,NULL,NULL),(140,'new','12000440767473331959685780010262','2017-02-06 14:27:00','Nom','HEXTOOL',NULL,NULL,NULL,NULL),(141,'update','D329-2B-02 GE0402 37% GLA600g PW ','2017-02-06 15:13:00',NULL,NULL,NULL,NULL,NULL,NULL),(142,'update','D329-2B-02 GE0402 37% GLA600g PW ','2017-02-06 15:17:00',NULL,NULL,NULL,NULL,NULL,NULL),(143,'update','D329-2B-02 GE0402 37% GLA600g PW 1.25','2017-02-06 15:18:00',NULL,NULL,NULL,NULL,NULL,NULL),(144,'update','PP166261016/003','2017-02-06 15:18:00',NULL,NULL,NULL,NULL,NULL,NULL),(145,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C','2017-02-06 15:18:00',NULL,NULL,NULL,NULL,NULL,NULL),(146,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C 1.27m','2017-02-06 15:19:00',NULL,NULL,NULL,NULL,NULL,NULL),(147,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C 1.27m.','2017-02-06 15:19:00',NULL,NULL,NULL,NULL,NULL,NULL),(148,'update','HX40-00 HS2016 CBN 650gsm 2x2T 35%rw 1.25m','2017-02-06 15:20:00',NULL,NULL,NULL,NULL,NULL,NULL),(149,'update','12000440767473331959685780010262','2017-02-06 15:20:00',NULL,NULL,NULL,NULL,NULL,NULL),(150,'update','12000440767473331959685780010262','2017-02-06 15:26:00',NULL,NULL,NULL,NULL,NULL,NULL),(151,'update','12000440767473331959685780010262','2017-02-06 15:26:00',NULL,NULL,NULL,NULL,NULL,NULL),(152,'update','1200044076747','2017-02-06 15:42:00',NULL,NULL,NULL,NULL,NULL,NULL),(153,'update','D329-2B-02 GE0402 37% GLA600g PW 1.25','2017-02-06 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL),(154,'update','PP166261016/003','2017-02-06 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL),(155,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C','2017-02-06 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL),(156,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C 1.27m','2017-02-06 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL),(157,'update','D329-2B-00 HS3641 42% CAR205g PW AS4C 1.27m.','2017-02-06 15:48:00',NULL,NULL,NULL,NULL,NULL,NULL),(158,'update','HX40-00 HS2016 CBN 650gsm 2x2T 35%rw 1.25m','2017-02-06 15:48:00',NULL,NULL,NULL,NULL,NULL,NULL),(159,'new','http://Film de colle','2017-02-07 09:02:00','Nom','Film de colle',NULL,NULL,NULL,NULL),(160,'new','3000968;D329-2B HS0838 42% CAR205g 2x2T 1.25','2017-02-07 10:10:00','Nom','Carbone OGON',NULL,NULL,NULL,NULL),(161,'new','Pré-imprégné carbone SGL','2017-02-07 10:37:00','Nom','Preg aérazur',NULL,NULL,NULL,NULL),(162,'update','Pré-imprégné carbone SGL','2017-02-07 10:40:00',NULL,NULL,NULL,NULL,NULL,NULL),(163,'new','http://Film de colle','2017-02-07 11:23:00','Nom','Film de colle',NULL,NULL,NULL,NULL),(164,'new','M34N/42%/300T2/G-68X3/1240','2017-02-07 13:56:00','Nom','Échantillon M34N',NULL,NULL,NULL,NULL),(165,'new','M10R/42%/200P/CHS-3K 1250','2017-02-07 14:00:00','Nom','Échantillon M10R',NULL,NULL,NULL,NULL),(166,'new','/42%/200P/GLA','2017-02-07 14:02:00','Nom','Échantillon hexcel',NULL,NULL,NULL,NULL),(167,'new','M49/42%/200T/CHS','2017-02-07 14:04:00','Nom','Échantillon',NULL,NULL,NULL,NULL),(168,'new','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-07 14:11:00','Nom','UD 50g Vitech',NULL,NULL,NULL,NULL),(169,'new','M21/40%/46230-2x2/1170','2017-02-07 14:15:00','Nom','Vieux rouleau',NULL,NULL,NULL,NULL),(170,'new','UD empilé 4x50g','2017-02-07 14:18:00','Nom','UD empilé',NULL,NULL,NULL,NULL),(171,'new','//200T2/CHS/1240','2017-02-07 14:21:00','Nom','Carbone pour bras de robot',NULL,NULL,NULL,NULL),(172,'new','Film de colle2','2017-02-07 15:51:00','Nom','Film de colle',NULL,NULL,NULL,NULL),(173,'new','HEXTOOL','2017-02-07 15:54:00','Nom','HEXTOOL',NULL,NULL,NULL,NULL),(174,'new','Test OGON','2017-02-07 15:57:00','Nom','Échantillon',NULL,NULL,NULL,NULL),(175,'new','M26T/50%/G939/CHS/1230','2017-02-07 15:59:00','Nom','Carbone 3x1 M26T',NULL,NULL,NULL,NULL),(176,'new','PP165111016/001','2017-02-07 16:04:00','Nom','Verre résine noir',NULL,NULL,NULL,NULL),(177,'new','M77/45%/160P/GLA','2017-02-07 16:11:00','Nom','Verre hexcel ogon',NULL,NULL,NULL,NULL),(178,'new','PP166291016/001','2017-02-07 16:16:00','Nom','Carbone 200g ogon',NULL,NULL,NULL,NULL),(179,'new','PP165101016/003','2017-02-07 16:18:00','Nom','Carbone 200g ogon',NULL,NULL,NULL,NULL),(180,'new','PP166261016/001','2017-02-07 16:21:00','Nom','Verre 600g ogon',NULL,NULL,NULL,NULL),(181,'new','PP166261016/002','2017-02-07 16:23:00','Nom','Verre 600g ogon',NULL,NULL,NULL,NULL),(182,'new','PP165101016/002','2017-02-07 16:26:00','Nom','Carbone 200g ogon',NULL,NULL,NULL,NULL),(183,'update','PP165101016/002','2017-02-07 16:26:00',NULL,NULL,NULL,NULL,NULL,NULL),(184,'new','PP163490916/007','2017-02-07 16:29:00','Nom','Carbone 650g moule',NULL,NULL,NULL,NULL),(185,'new','M9.6GF/35%/UD600/CHS 315MM','2017-02-07 16:33:00','Nom','Carbone UD 600g',NULL,NULL,NULL,NULL),(186,'out','PP166291016/001','2017-02-08 09:04:00','En stock depuis','07/02/2017 16:16','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(187,'out','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-08 09:07:00','En stock depuis','07/02/2017 14:11','Longueur restante (m)','48.00','Total décongelé (hh:mm)','00:00'),(188,'update','M77/45%/160P/GLA','2017-02-08 12:21:00',NULL,NULL,NULL,NULL,NULL,NULL),(189,'update','Film de colle','2017-02-08 13:33:00',NULL,NULL,NULL,NULL,NULL,NULL),(190,'in','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-09 16:37:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','31:29'),(191,'out','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-09 16:37:00','En stock depuis','09/02/2017 16:37','Longueur restante (m)','','Total décongelé (hh:mm)','31:29'),(192,'in','PP166291016/001','2017-02-09 17:08:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','32:03'),(193,'in','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-10 12:01:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','19:23'),(194,'out','M9.6GF/35%/UD600/CHS 315MM','2017-02-10 14:43:00','En stock depuis','07/02/2017 16:33','Longueur restante (m)','100.00','Total décongelé (hh:mm)','00:00'),(195,'in','M9.6GF/35%/UD600/CHS 315MM','2017-02-10 15:59:00','Lieu de stockage','Chambre Froide','Consommée (m)','19.5','Temps décongelé (hh:mm)','01:16'),(196,'out','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-14 11:21:00','En stock depuis','10/02/2017 12:01','Longueur restante (m)','','Total décongelé (hh:mm)','50:52'),(197,'new',']C201030847X~1060M0021087C~2160M0021087012','2017-02-14 14:20:00','Nom',NULL,NULL,NULL,NULL,NULL),(198,'update',']C201030847X~1060M0021087C~2160M0021087012','2017-02-14 14:24:00',NULL,NULL,NULL,NULL,NULL,NULL),(199,'new',']C201030847X~1060M0021087C~2160M0021087015','2017-02-14 14:28:00','Nom','carbone ogon 200g',NULL,NULL,NULL,NULL),(200,'in','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-14 19:14:00','Lieu de stockage','Chambre Froide','Consommée (m)','2','Temps décongelé (hh:mm)','07:53'),(201,'out','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-15 11:49:00','En stock depuis','14/02/2017 19:14','Longueur restante (m)','46.00','Total décongelé (hh:mm)','58:45'),(202,'in','HR-230-50 XB3515/43%/UD50/CHS-TR50S12L','2017-02-15 18:25:00','Lieu de stockage','Chambre Froide','Consommée (m)','2','Temps décongelé (hh:mm)','06:36'),(203,'out','M77/45%/160P/GLA','2017-02-17 17:44:00','En stock depuis','07/02/2017 16:11','Longueur restante (m)','10.00','Total décongelé (hh:mm)','00:00'),(204,'out',']C201030847X~1060M0021087C~2160M0021087012','2017-02-20 08:41:00','En stock depuis','14/02/2017 14:20','Longueur restante (m)','','Total décongelé (hh:mm)','00:00'),(205,'out','PP166261016/001','2017-02-20 17:12:00','En stock depuis','07/02/2017 16:21','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(206,'out','PP166291016/001','2017-02-20 17:13:00','En stock depuis','09/02/2017 17:08','Longueur restante (m)','','Total décongelé (hh:mm)','32:03'),(207,'in','M77/45%/160P/GLA','2017-02-20 18:17:00','Lieu de stockage','Chambre Froide','Consommée (m)','10','Temps décongelé (hh:mm)','72:33'),(208,'in',']C201030847X~1060M0021087C~2160M0021087012','2017-02-20 18:26:00','Lieu de stockage','Chambre Froide','Consommée (m)','8.25','Temps décongelé (hh:mm)','09:44'),(209,'in','PP166291016/001','2017-02-21 10:10:00','Lieu de stockage','Chambre Froide','Consommée (m)','3.5','Temps décongelé (hh:mm)','16:57'),(210,'in','PP166261016/001','2017-02-21 17:45:00','Lieu de stockage','Chambre Froide','Consommée (m)','17','Temps décongelé (hh:mm)','24:33'),(211,'out','PP166291016/001','2017-02-21 17:45:00','En stock depuis','21/02/2017 10:10','Longueur restante (m)','16.50','Total décongelé (hh:mm)','49:00'),(212,'in','PP166291016/001','2017-02-27 17:25:00','Lieu de stockage','Chambre Froide','Consommée (m)','2','Temps décongelé (hh:mm)','143:40'),(213,'out','HEXTOOL','2017-02-28 13:33:00','En stock depuis','07/02/2017 15:54','Longueur restante (m)','2.50','Total décongelé (hh:mm)','00:00'),(214,'out','PP163490916/007','2017-03-02 17:11:00','En stock depuis','07/02/2017 16:29','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(215,'out','PP163490916/007','2017-03-02 17:11:00','En stock depuis','07/02/2017 16:29','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(216,'in','HEXTOOL','2017-03-03 11:25:00','Lieu de stockage','Chambre Froide','Consommée (m)','2.5','Temps décongelé (hh:mm)','69:52'),(217,'in','PP163490916/007','2017-03-03 17:50:00','Lieu de stockage','Chambre Froide','Consommée (m)','4','Temps décongelé (hh:mm)','24:38'),(218,'out','PP163490916/007','2017-03-06 08:45:00','En stock depuis','03/03/2017 17:50','Longueur restante (m)','16.00','Total décongelé (hh:mm)','24:38'),(219,'in','PP163490916/007','2017-03-08 10:57:00','Lieu de stockage','Chambre Froide','Consommée (m)','16','Temps décongelé (hh:mm)','50:11'),(220,'new','02-03-17/SFC/C/UD150/WS001','2017-03-08 18:20:00','Nom','Guidon TR50S',NULL,NULL,NULL,NULL),(221,'new','08-03-17/HEX/C/T200/WS004','2017-03-08 18:22:00','Nom','Vieux rouleau ',NULL,NULL,NULL,NULL),(222,'new','08-03-17/HEX/C/S220/WS005','2017-03-08 18:24:00','Nom','Carbone 3x1 M26T',NULL,NULL,NULL,NULL),(223,'new','08-03-17/HEX/C/P200/WS001','2017-03-08 18:26:00','Nom','Echantillon M10R',NULL,NULL,NULL,NULL),(224,'new','08-03-17/HEX/C/T300/WS006','2017-03-08 18:28:00','Nom','Echantillon M34N',NULL,NULL,NULL,NULL),(225,'new','08-03-17/HEX/C/T200/WS007','2017-03-08 18:30:00','Nom','Carbone pour bras de robot',NULL,NULL,NULL,NULL),(226,'new','08-03-17/HEX/C/T200/WS002','2017-03-08 18:31:00','Nom','Echantillon M49',NULL,NULL,NULL,NULL),(227,'new','01-03-17/SGL/C/T200/WS001','2017-03-08 18:44:00','Nom','Carbone aérazur',NULL,NULL,NULL,NULL),(228,'new','07-03-17/HEX/C/S220/C001','2017-03-08 18:45:00','Nom',NULL,NULL,NULL,NULL,NULL),(229,'update','07-03-17/HEX/C/S220/C001','2017-03-08 18:47:00',NULL,NULL,NULL,NULL,NULL,NULL),(230,'new','07-03-17/HEX/C/S220/C002','2017-03-08 18:48:00','Nom','Preg vision',NULL,NULL,NULL,NULL),(231,'new','07-03-17/HEX/C/S220/C003','2017-03-08 18:49:00','Nom','Preg vision',NULL,NULL,NULL,NULL),(232,'out','07-03-17/HEX/C/S220/C002','2017-03-09 10:13:00','En stock depuis','08/03/2017 18:48','Longueur restante (m)','50.00','Total décongelé (hh:mm)','00:00'),(233,'new','09-03-17/HEX/C/UD300/WS001','2017-03-09 11:58:00','Nom','UD300',NULL,NULL,NULL,NULL),(234,'new','09-03-17/HEX/C/P200/WS002','2017-03-09 12:16:00','Nom','Carbone ogon',NULL,NULL,NULL,NULL),(235,'new','09-03-17/HEX/C/P200/WS003','2017-03-09 12:20:00','Nom','Carbone ogon',NULL,NULL,NULL,NULL),(236,'out','09-03-17/HEX/C/P200/WS002','2017-03-09 12:21:00','En stock depuis','09/03/2017 12:16','Longueur restante (m)','50.00','Total décongelé (hh:mm)','00:00'),(237,'in','09-03-17/HEX/C/P200/WS002','2017-03-09 12:21:00','Lieu de stockage','Chambre Froide','Consommée (m)','9','Temps décongelé (hh:mm)','00:00'),(238,'new','08-03-17/HEX/C/UD600/WS003','2017-03-09 14:27:00','Nom','UD 600',NULL,NULL,NULL,NULL),(239,'new','08-03-17/VIT/C/UD4x50/WS008','2017-03-09 14:33:00','Nom','UD empilé',NULL,NULL,NULL,NULL),(240,'new','08-03-17/VIT/C/UD50/WS012','2017-03-09 14:35:00','Nom','UD 50g',NULL,NULL,NULL,NULL),(241,'new','09-03-17/VIT/C/UD110/WS008','2017-03-09 14:37:00','Nom','UD 110g guidon',NULL,NULL,NULL,NULL),(242,'new','09-03-17/HEX/C/UD150/WS007','2017-03-09 14:38:00','Nom','UD 150g guidon',NULL,NULL,NULL,NULL),(243,'new','08-03-17/HEX/V/P200/WS013','2017-03-09 14:39:00','Nom','Échantillon verre hexcel',NULL,NULL,NULL,NULL),(244,'new','09-03-17/AXS/C/T650/WS011','2017-03-09 14:40:00','Nom','Carbone moule axson',NULL,NULL,NULL,NULL),(245,'new','09-03-17/AXS/C/T200/WS009','2017-03-09 14:41:00','Nom','Carbone moule axson',NULL,NULL,NULL,NULL),(246,'new','09-03-17/AXS/C/T650/WS010','2017-03-09 14:42:00','Nom','Carbone moule axson',NULL,NULL,NULL,NULL),(247,'new','08-03-17/TEN/V/P600/WS016','2017-03-09 14:44:00','Nom','Verre noir 600g',NULL,NULL,NULL,NULL),(248,'new','08-03-17/TEN/V/P600/WS014','2017-03-09 14:46:00','Nom','Verre noir 600g',NULL,NULL,NULL,NULL),(249,'new','08-03-17/TEN/V/P600/WS015','2017-03-09 14:47:00','Nom','Verre noir 600g',NULL,NULL,NULL,NULL),(250,'new','08-03-17/TEN/C/P200/WS010','2017-03-09 14:52:00','Nom','Carbone OGON tencate',NULL,NULL,NULL,NULL),(251,'new','08-03-17/TEN/C/P200/WS009','2017-03-09 14:54:00','Nom','Carbone OGON tencate',NULL,NULL,NULL,NULL),(252,'out','08-03-17/HEX/C/UD600/WS003','2017-03-09 15:09:00','En stock depuis','09/03/2017 14:27','Longueur restante (m)','100.00','Total décongelé (hh:mm)','00:00'),(253,'in','08-03-17/HEX/C/UD600/WS003','2017-03-09 15:09:00','Lieu de stockage','Chambre Froide','Consommée (m)','20','Temps décongelé (hh:mm)','00:00'),(254,'in','08-03-17/HEX/C/UD600/WS003','2017-03-09 15:09:00','Lieu de stockage','Chambre Froide','Consommée (m)','20','Temps décongelé (hh:mm)','00:00'),(255,'new','09-03-17/TEN/V/280/WS012','2017-03-09 15:39:00','Nom','Verre moule',NULL,NULL,NULL,NULL),(256,'new','09-03-17/AXS/C/T650/WS013','2017-03-09 15:52:00','Nom','Carbone moule axson',NULL,NULL,NULL,NULL),(257,'new','09-03-17/HEX/C/UD600/WS015','2017-03-09 15:54:00','Nom','UD 600',NULL,NULL,NULL,NULL),(258,'new','09-03-17/NGF/C/UD125/WS006','2017-03-09 15:56:00','Nom','UD GRANOC',NULL,NULL,NULL,NULL),(259,'new','09-03-17/NGF/C/UD125/WS005','2017-03-09 15:58:00','Nom','UD GRANOC',NULL,NULL,NULL,NULL),(260,'new','09-03-17/VIT/C/UD50/WS014','2017-03-09 16:20:00','Nom','UD 50g',NULL,NULL,NULL,NULL),(261,'new','09-03-17/TEN/C/T200/WS020','2017-03-09 17:08:00','Nom','Carbone moule tencate',NULL,NULL,NULL,NULL),(262,'new','09-03-17/TEN/C/P205/WS019','2017-03-09 17:10:00','Nom','Échantillon carbone OGON',NULL,NULL,NULL,NULL),(263,'new','09-03-17/TEN/V/P600/WS017','2017-03-09 17:13:00','Nom','Verre noir 600g',NULL,NULL,NULL,NULL),(264,'new','09-03-17/TEN/C/P205/WS018','2017-03-09 17:14:00','Nom','Carbone OGON tencate',NULL,NULL,NULL,NULL),(265,'new','09-03-17/TEN/V/P600/WS016','2017-03-09 17:16:00','Nom','Verre noir 600g',NULL,NULL,NULL,NULL),(266,'new','09-03-17/HEX/C/FB2000/WS001','2017-03-09 17:35:00','Nom','HEXTool',NULL,NULL,NULL,NULL),(267,'new','09-03-17/HEX/C/FB2000/WS002','2017-03-09 17:37:00','Nom','HEXMC',NULL,NULL,NULL,NULL),(268,'update','02-03-17/SFC/C/UD150/WS001','2017-03-10 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL),(269,'out','09-03-17/HEX/C/UD150/WS007','2017-03-15 14:59:00','En stock depuis','09/03/2017 14:38','Longueur restante (m)','9.00','Total décongelé (hh:mm)','00:00'),(270,'out','01-03-17/SGL/C/T200/WS001','2017-03-15 17:44:00','En stock depuis','08/03/2017 18:44','Longueur restante (m)','49.00','Total décongelé (hh:mm)','00:00'),(271,'in','01-03-17/SGL/C/T200/WS001','2017-03-16 16:40:00','Lieu de stockage','Chambre Froide','Consommée (m)','5','Temps décongelé (hh:mm)','22:56'),(272,'in','07-03-17/HEX/C/S220/C002','2017-03-16 16:41:00','Lieu de stockage','Chambre Froide','Consommée (m)','32','Temps décongelé (hh:mm)','174:27'),(273,'in','09-03-17/HEX/C/UD150/WS007','2017-03-16 16:50:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.2','Temps décongelé (hh:mm)','25:51'),(274,'out','07-03-17/HEX/C/S220/C002','2017-03-16 17:04:00','En stock depuis','16/03/2017 16:41','Longueur restante (m)','18.00','Total décongelé (hh:mm)','174:27'),(275,'in','07-03-17/HEX/C/S220/C002','2017-03-17 14:35:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.5','Temps décongelé (hh:mm)','21:31'),(276,'out','09-03-17/HEX/C/FB2000/WS002','2017-03-17 16:16:00','En stock depuis','09/03/2017 17:37','Longueur restante (m)','5.00','Total décongelé (hh:mm)','00:00'),(277,'out','01-03-17/SGL/C/T200/WS001','2017-03-20 08:39:00','En stock depuis','16/03/2017 16:40','Longueur restante (m)','44.00','Total décongelé (hh:mm)','22:56'),(278,'out','07-03-17/HEX/C/S220/C002','2017-03-20 08:39:00','En stock depuis','17/03/2017 14:35','Longueur restante (m)','16.50','Total décongelé (hh:mm)','195:58'),(279,'out','09-03-17/HEX/C/UD150/WS007','2017-03-20 09:31:00','En stock depuis','16/03/2017 16:50','Longueur restante (m)','7.80','Total décongelé (hh:mm)','25:51'),(280,'in','09-03-17/HEX/C/UD150/WS007','2017-03-20 11:32:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.5','Temps décongelé (hh:mm)','02:00'),(281,'update_note','01-03-17/SGL/C/T200/WS001','2017-03-21 09:48:00',NULL,NULL,NULL,NULL,NULL,NULL),(282,'out','09-03-17/HEX/C/UD150/WS007','2017-03-21 15:58:00','En stock depuis','20/03/2017 11:32','Longueur restante (m)','6.30','Total décongelé (hh:mm)','27:51'),(283,'out','08-03-17/HEX/C/UD600/WS003','2017-03-22 16:05:00','En stock depuis','09/03/2017 15:09','Longueur restante (m)','80.00','Total décongelé (hh:mm)','00:00'),(284,'out','09-03-17/HEX/C/FB2000/WS001','2017-03-22 18:22:00','En stock depuis','09/03/2017 17:35','Longueur restante (m)','9.00','Total décongelé (hh:mm)','00:00'),(285,'out','08-03-17/TEN/V/P600/WS014','2017-03-22 20:08:00','En stock depuis','09/03/2017 14:46','Longueur restante (m)','18.00','Total décongelé (hh:mm)','00:00'),(286,'in','07-03-17/HEX/C/S220/C002','2017-03-23 08:59:00','Lieu de stockage','Chambre Froide','Consommée (m)','16.5','Temps décongelé (hh:mm)','72:19'),(287,'out','07-03-17/HEX/C/S220/C003','2017-03-23 09:00:00','En stock depuis','08/03/2017 18:49','Longueur restante (m)','50.00','Total décongelé (hh:mm)','00:00'),(288,'in','09-03-17/HEX/C/UD150/WS007','2017-03-23 09:33:00','Lieu de stockage','Chambre Froide','Consommée (m)','2','Temps décongelé (hh:mm)','41:35'),(289,'new','23-03-17/HEX/V/P395/WS001','2017-03-23 17:11:00','Nom','preg verre ogon',NULL,NULL,NULL,NULL),(290,'out','23-03-17/HEX/V/P395/WS001','2017-03-23 17:11:00','En stock depuis','23/03/2017 17:11','Longueur restante (m)','100.00','Total décongelé (hh:mm)','00:00'),(291,'out','09-03-17/HEX/C/P200/WS003','2017-03-23 17:41:00','En stock depuis','09/03/2017 12:20','Longueur restante (m)','52.00','Total décongelé (hh:mm)','00:00'),(292,'in','08-03-17/HEX/C/UD600/WS003','2017-03-24 10:28:00','Lieu de stockage','Chambre Froide','Consommée (m)','42.3','Temps décongelé (hh:mm)','42:20'),(293,'out','09-03-17/HEX/C/UD150/WS007','2017-03-24 13:41:00','En stock depuis','23/03/2017 09:33','Longueur restante (m)','4.30','Total décongelé (hh:mm)','69:26'),(294,'in','08-03-17/TEN/V/P600/WS014','2017-03-24 16:43:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','44:35'),(295,'in','09-03-17/HEX/C/P200/WS003','2017-03-24 17:27:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.40','Temps décongelé (hh:mm)','23:38'),(296,'in','09-03-17/HEX/C/UD150/WS007','2017-03-24 17:43:00','Lieu de stockage','Chambre Froide','Consommée (m)','3','Temps décongelé (hh:mm)','04:02'),(297,'out','09-03-17/HEX/C/P200/WS003','2017-03-27 08:44:00','En stock depuis','24/03/2017 17:27','Longueur restante (m)','50.60','Total décongelé (hh:mm)','23:38'),(298,'out','08-03-17/HEX/C/UD600/WS003','2017-03-27 08:46:00','En stock depuis','24/03/2017 10:28','Longueur restante (m)','37.70','Total décongelé (hh:mm)','42:20'),(299,'in','23-03-17/HEX/V/P395/WS001','2017-03-27 09:15:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','87:04'),(300,'in','09-03-17/HEX/C/FB2000/WS002','2017-03-27 17:47:00','Lieu de stockage','Chambre Froide','Consommée (m)','4','Temps décongelé (hh:mm)','240:31'),(301,'out','02-03-17/SFC/C/UD150/WS001','2017-03-28 14:07:00','En stock depuis','08/03/2017 18:20','Longueur restante (m)','9.00','Total décongelé (hh:mm)','00:00'),(302,'in','09-03-17/HEX/C/P200/WS003','2017-03-28 14:17:00','Lieu de stockage','Chambre Froide','Consommée (m)','4.8','Temps décongelé (hh:mm)','29:32'),(303,'out','09-03-17/AXS/C/T650/WS010','2017-03-28 16:39:00','En stock depuis','09/03/2017 14:42','Longueur restante (m)','3.50','Total décongelé (hh:mm)','00:00'),(304,'out','09-03-17/AXS/C/T650/WS013','2017-03-28 16:39:00','En stock depuis','09/03/2017 15:52','Longueur restante (m)','7.00','Total décongelé (hh:mm)','00:00'),(305,'out','09-03-17/AXS/C/T200/WS009','2017-03-28 16:39:00','En stock depuis','09/03/2017 14:41','Longueur restante (m)','15.00','Total décongelé (hh:mm)','00:00'),(306,'out','09-03-17/AXS/C/T650/WS011','2017-03-28 16:40:00','En stock depuis','09/03/2017 14:40','Longueur restante (m)','5.00','Total décongelé (hh:mm)','00:00'),(307,'out','09-03-17/TEN/C/T200/WS020','2017-03-28 16:40:00','En stock depuis','09/03/2017 17:08','Longueur restante (m)','1.00','Total décongelé (hh:mm)','00:00'),(308,'in','08-03-17/HEX/C/UD600/WS003','2017-03-29 11:44:00','Lieu de stockage','Chambre Froide','Consommée (m)','5.2','Temps décongelé (hh:mm)','50:58'),(309,'in','02-03-17/SFC/C/UD150/WS001','2017-03-29 11:53:00','Lieu de stockage','Chambre Froide','Consommée (m)','3','Temps décongelé (hh:mm)','21:43'),(310,'in','09-03-17/AXS/C/T650/WS011','2017-03-29 15:09:00','Lieu de stockage','Chambre Froide','Consommée (m)','5','Temps décongelé (hh:mm)','22:29'),(311,'in','01-03-17/SGL/C/T200/WS001','2017-03-29 18:54:00','Lieu de stockage','Chambre Froide','Consommée (m)','7','Temps décongelé (hh:mm)','225:15'),(312,'in','09-03-17/AXS/C/T650/WS013','2017-03-30 08:40:00','Lieu de stockage','Chambre Froide','Consommée (m)','7','Temps décongelé (hh:mm)','40:01'),(313,'new','29-03-17/VIT/C/UD50/WS001','2017-03-30 10:53:00','Nom','UD 50g',NULL,NULL,NULL,NULL),(314,'new','29-03-17/VIT/C/UD50/WS002','2017-03-30 10:55:00','Nom','UD 50g',NULL,NULL,NULL,NULL),(315,'out','09-03-17/VIT/C/UD110/WS008','2017-03-30 15:15:00','En stock depuis','09/03/2017 14:37','Longueur restante (m)','9.00','Total décongelé (hh:mm)','00:00'),(316,'in','09-03-17/AXS/C/T650/WS010','2017-03-30 18:34:00','Lieu de stockage','Chambre Froide','Consommée (m)','3.5','Temps décongelé (hh:mm)','49:55'),(317,'in','09-03-17/AXS/C/T650/WS010','2017-03-30 18:35:00','Lieu de stockage','Chambre Froide','Consommée (m)','3.5','Temps décongelé (hh:mm)','49:55'),(318,'out','08-03-17/VIT/C/UD50/WS012','2017-03-30 18:38:00','En stock depuis','09/03/2017 14:35','Longueur restante (m)','44.00','Total décongelé (hh:mm)','00:00'),(319,'in','08-03-17/VIT/C/UD50/WS012','2017-03-31 12:34:00','Lieu de stockage','Chambre Froide','Consommée (m)','2','Temps décongelé (hh:mm)','17:56'),(320,'out','08-03-17/VIT/C/UD50/WS012','2017-03-31 12:34:00','En stock depuis','31/03/2017 12:34','Longueur restante (m)','42.00','Total décongelé (hh:mm)','17:56'),(321,'in','08-03-17/VIT/C/UD50/WS012','2017-03-31 12:34:00','Lieu de stockage','Chambre Froide','Consommée (m)','19','Temps décongelé (hh:mm)','00:00'),(322,'in','07-03-17/HEX/C/S220/C003','2017-03-31 16:38:00','Lieu de stockage','Chambre Froide','Consommée (m)','28.2','Temps décongelé (hh:mm)','198:37'),(323,'out','29-03-17/VIT/C/UD50/WS002','2017-04-03 08:34:00','En stock depuis','30/03/2017 10:55','Longueur restante (m)','190.00','Total décongelé (hh:mm)','00:00'),(324,'out','29-03-17/VIT/C/UD50/WS001','2017-04-03 08:34:00','En stock depuis','30/03/2017 10:53','Longueur restante (m)','53.00','Total décongelé (hh:mm)','00:00'),(325,'out','08-03-17/VIT/C/UD50/WS012','2017-04-03 08:42:00','En stock depuis','31/03/2017 12:34','Longueur restante (m)','23.00','Total décongelé (hh:mm)','17:56'),(326,'out','09-03-17/HEX/C/P200/WS003','2017-04-03 11:23:00','En stock depuis','28/03/2017 14:17','Longueur restante (m)','45.80','Total décongelé (hh:mm)','53:10'),(327,'out','09-03-17/HEX/C/P200/WS003','2017-04-03 11:23:00','En stock depuis','28/03/2017 14:17','Longueur restante (m)','45.80','Total décongelé (hh:mm)','53:10'),(328,'out','09-03-17/HEX/C/P200/WS003','2017-04-03 11:23:00','En stock depuis','28/03/2017 14:17','Longueur restante (m)','45.80','Total décongelé (hh:mm)','53:10'),(329,'out','09-03-17/HEX/C/P200/WS003','2017-04-03 11:23:00','En stock depuis','28/03/2017 14:17','Longueur restante (m)','45.80','Total décongelé (hh:mm)','53:10'),(330,'out','09-03-17/HEX/C/P200/WS003','2017-04-03 11:23:00','En stock depuis','28/03/2017 14:17','Longueur restante (m)','45.80','Total décongelé (hh:mm)','53:10'),(331,'out','01-03-17/SGL/C/T200/WS001','2017-04-03 14:53:00','En stock depuis','29/03/2017 18:54','Longueur restante (m)','37.00','Total décongelé (hh:mm)','248:11'),(332,'in','09-03-17/HEX/C/P200/WS003','2017-04-03 15:18:00','Lieu de stockage','Chambre Froide','Consommée (m)','5.68','Temps décongelé (hh:mm)','03:55'),(333,'out','09-03-17/HEX/C/UD300/WS001','2017-04-03 17:11:00','En stock depuis','09/03/2017 11:58','Longueur restante (m)','50.00','Total décongelé (hh:mm)','00:00'),(334,'out','07-03-17/HEX/C/S220/C003','2017-04-03 17:50:00','En stock depuis','31/03/2017 16:38','Longueur restante (m)','21.80','Total décongelé (hh:mm)','198:37'),(335,'in','09-03-17/HEX/C/UD300/WS001','2017-04-05 09:51:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.5','Temps décongelé (hh:mm)','40:40'),(336,'in','09-03-17/HEX/C/UD300/WS001','2017-04-05 09:51:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.5','Temps décongelé (hh:mm)','40:40'),(337,'out','07-03-17/HEX/C/S220/C001','2017-04-07 11:18:00','En stock depuis','08/03/2017 18:45','Longueur restante (m)','','Total décongelé (hh:mm)','00:00'),(338,'in','07-03-17/HEX/C/S220/C001','2017-04-07 11:19:00','Lieu de stockage','Chambre Froide','Consommée (m)','50','Temps décongelé (hh:mm)','00:00'),(339,'in','29-03-17/VIT/C/UD50/WS002','2017-04-07 14:22:00','Lieu de stockage','Chambre Froide','Consommée (m)','126','Temps décongelé (hh:mm)','101:47'),(340,'in','29-03-17/VIT/C/UD50/WS001','2017-04-07 15:27:00','Lieu de stockage','Chambre Froide','Consommée (m)','53','Temps décongelé (hh:mm)','102:53'),(341,'in','09-03-17/VIT/C/UD110/WS008','2017-04-07 15:29:00','Lieu de stockage','Chambre Froide','Consommée (m)','3','Temps décongelé (hh:mm)','192:14'),(342,'in','07-03-17/HEX/C/S220/C003','2017-04-07 16:22:00','Lieu de stockage','Chambre Froide','Consommée (m)','12','Temps décongelé (hh:mm)','94:32'),(343,'out','09-03-17/HEX/C/P200/WS003','2017-04-10 08:47:00','En stock depuis','03/04/2017 15:18','Longueur restante (m)','40.12','Total décongelé (hh:mm)','57:05'),(344,'out','07-03-17/HEX/C/S220/C003','2017-04-10 08:48:00','En stock depuis','07/04/2017 16:22','Longueur restante (m)','9.80','Total décongelé (hh:mm)','293:09'),(345,'in','09-03-17/HEX/C/P200/WS003','2017-04-10 15:56:00','Lieu de stockage','Chambre Froide','Consommée (m)','7.14','Temps décongelé (hh:mm)','07:09'),(346,'in','01-03-17/SGL/C/T200/WS001','2017-04-12 14:32:00','Lieu de stockage','Chambre Froide','Consommée (m)','18','Temps décongelé (hh:mm)','215:38'),(347,'in','01-03-17/SGL/C/T200/WS001','2017-04-12 14:32:00','Lieu de stockage','Chambre Froide','Consommée (m)','18','Temps décongelé (hh:mm)','215:38'),(348,'out','02-03-17/SFC/C/UD150/WS001','2017-04-12 18:16:00','En stock depuis','29/03/2017 11:53','Longueur restante (m)','6.00','Total décongelé (hh:mm)','21:43'),(349,'out','08-03-17/HEX/C/UD600/WS003','2017-04-12 18:17:00','En stock depuis','29/03/2017 11:44','Longueur restante (m)','32.50','Total décongelé (hh:mm)','93:18'),(350,'out','09-03-17/HEX/C/UD300/WS001','2017-04-12 19:44:00','En stock depuis','05/04/2017 09:51','Longueur restante (m)','47.00','Total décongelé (hh:mm)','81:20'),(351,'in','02-03-17/SFC/C/UD150/WS001','2017-04-13 08:06:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','13:50'),(352,'in','09-03-17/HEX/C/UD300/WS001','2017-04-13 18:28:00','Lieu de stockage','Chambre Froide','Consommée (m)','1','Temps décongelé (hh:mm)','22:44'),(353,'out','09-03-17/HEX/C/P200/WS003','2017-04-18 10:08:00','En stock depuis','10/04/2017 15:56','Longueur restante (m)','32.98','Total décongelé (hh:mm)','64:14'),(354,'in','09-03-17/HEX/C/P200/WS003','2017-04-18 15:34:00','Lieu de stockage','Chambre Froide','Consommée (m)','9.18','Temps décongelé (hh:mm)','05:26'),(355,'out','08-03-17/HEX/C/T200/WS004','2017-04-20 17:05:00','En stock depuis','08/03/2017 18:22','Longueur restante (m)','15.00','Total décongelé (hh:mm)','00:00'),(356,'in','08-03-17/HEX/C/T200/WS004','2017-04-21 11:47:00','Lieu de stockage','Chambre Froide','Consommée (m)','15','Temps décongelé (hh:mm)','18:42'),(357,'new','26-04-17/AXS/C/200/WS001','2017-04-26 15:36:00','Nom','preg moulr',NULL,NULL,NULL,NULL),(358,'new','26-04-17/AXS/C/650/WS001','2017-04-26 15:38:00','Nom','preg moule',NULL,NULL,NULL,NULL),(359,'out','23-03-17/HEX/V/P395/WS001','2017-04-26 15:51:00','En stock depuis','27/03/2017 09:15','Longueur restante (m)','','Total décongelé (hh:mm)','87:04'),(360,'out','23-03-17/HEX/V/P395/WS001','2017-04-26 15:51:00','En stock depuis','27/03/2017 09:15','Longueur restante (m)','','Total décongelé (hh:mm)','87:04'),(361,'in','23-03-17/HEX/V/P395/WS001','2017-04-26 15:52:00','Lieu de stockage','Chambre Froide','Consommée (m)','20.85','Temps décongelé (hh:mm)','72:00'),(362,'out','09-03-17/HEX/C/P200/WS003','2017-04-28 17:24:00','En stock depuis','18/04/2017 15:34','Longueur restante (m)','23.80','Total décongelé (hh:mm)','69:40'),(363,'in','09-03-17/HEX/C/P200/WS003','2017-04-28 17:24:00','Lieu de stockage','Chambre Froide','Consommée (m)','22','Temps décongelé (hh:mm)','96:00'),(364,'out','09-03-17/HEX/C/P200/WS002','2017-05-02 11:31:00','En stock depuis','09/03/2017 12:21','Longueur restante (m)','41.00','Total décongelé (hh:mm)','00:00'),(365,'out','09-03-17/HEX/C/P200/WS003','2017-05-02 11:32:00','En stock depuis','28/04/2017 17:24','Longueur restante (m)','1.80','Total décongelé (hh:mm)','165:40'),(366,'in','09-03-17/HEX/C/P200/WS003','2017-05-02 11:32:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.80','Temps décongelé (hh:mm)','00:00'),(367,'in','09-03-17/HEX/C/P200/WS002','2017-05-04 16:36:00','Lieu de stockage','Chambre Froide','Consommée (m)','20','Temps décongelé (hh:mm)','53:05'),(368,'out','01-03-17/SGL/C/T200/WS001','2017-05-04 17:48:00','En stock depuis','12/04/2017 14:32','Longueur restante (m)','1.00','Total décongelé (hh:mm)','679:27'),(369,'in','01-03-17/SGL/C/T200/WS001','2017-05-05 15:28:00','Lieu de stockage','Chambre Froide','Consommée (m)','0','Temps décongelé (hh:mm)','21:40'),(370,'out','23-03-17/HEX/V/P395/WS001','2017-05-10 09:16:00','En stock depuis','26/04/2017 15:52','Longueur restante (m)','79.15','Total décongelé (hh:mm)','159:04'),(371,'in','23-03-17/HEX/V/P395/WS001','2017-05-10 19:33:00','Lieu de stockage','Chambre Froide','Consommée (m)','20','Temps décongelé (hh:mm)','10:17'),(372,'in','23-03-17/HEX/V/P395/WS001','2017-05-10 19:33:00','Lieu de stockage','Chambre Froide','Consommée (m)','20','Temps décongelé (hh:mm)','10:17'),(373,'out','09-03-17/HEX/C/P200/WS002','2017-05-11 09:31:00','En stock depuis','04/05/2017 16:36','Longueur restante (m)','21.00','Total décongelé (hh:mm)','53:05'),(374,'in','09-03-17/HEX/C/P200/WS002','2017-05-11 20:35:00','Lieu de stockage','Chambre Froide','Consommée (m)','14','Temps décongelé (hh:mm)','11:04'),(375,'out','26-04-17/AXS/C/200/WS001','2017-05-15 09:07:00','En stock depuis','26/04/2017 15:36','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(376,'out','26-04-17/AXS/C/200/WS001','2017-05-15 09:07:00','En stock depuis','26/04/2017 15:36','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(377,'out','26-04-17/AXS/C/200/WS001','2017-05-15 09:07:00','En stock depuis','26/04/2017 15:36','Longueur restante (m)','20.00','Total décongelé (hh:mm)','00:00'),(378,'in','26-04-17/AXS/C/200/WS001','2017-05-15 16:52:00','Lieu de stockage','Chambre Froide','Consommée (m)','1.2','Temps décongelé (hh:mm)','07:45'),(379,'new','15-05-17/NGF/C/UD55/CNRS001','2017-05-17 11:58:00','Nom','ud granoc',NULL,NULL,NULL,NULL),(380,'out','09-03-17/HEX/C/UD600/WS015','2017-05-18 15:48:00','En stock depuis','09/03/2017 15:54','Longueur restante (m)','100.00','Total décongelé (hh:mm)','00:00'),(381,'in','08-03-17/HEX/C/UD600/WS003','2017-05-18 15:50:00','Lieu de stockage','Chambre Froide','Consommée (m)','32.5','Temps décongelé (hh:mm)','861:33'),(382,'in','09-03-17/HEX/C/UD600/WS015','2017-05-19 09:11:00','Lieu de stockage','Chambre Froide','Consommée (m)','8.1','Temps décongelé (hh:mm)','17:23'),(383,'new','19-05-17/HEX/V/S200/VS001','2017-05-22 16:55:00','Nom','tissu verre casing double',NULL,NULL,NULL,NULL),(384,'out','19-05-17/HEX/V/S200/VS001','2017-05-22 16:56:00','En stock depuis','22/05/2017 16:55','Longueur restante (m)','56.00','Total décongelé (hh:mm)','00:00'),(385,'in','19-05-17/HEX/V/S200/VS001','2017-05-24 17:18:00','Lieu de stockage','Chambre Froide','Consommée (m)','10','Temps décongelé (hh:mm)','48:21'),(386,'update','08-03-17/HEX/C/UD600/WS003','2017-05-27 10:31:00',NULL,NULL,NULL,NULL,NULL,NULL),(387,'update','08-03-17/HEX/C/UD600/WS003','2017-05-27 10:33:00',NULL,NULL,NULL,NULL,NULL,NULL),(388,'update','09-03-17/AXS/C/T650/WS011','2017-05-27 10:34:00',NULL,NULL,NULL,NULL,NULL,NULL),(389,'update','HEXTOOL','2017-05-27 10:35:00',NULL,NULL,NULL,NULL,NULL,NULL),(390,'update','M77/45%/160P/GLA','2017-05-27 10:35:00',NULL,NULL,NULL,NULL,NULL,NULL),(391,'update','09-03-17/AXS/C/T650/WS013','2017-05-27 10:36:00',NULL,NULL,NULL,NULL,NULL,NULL),(392,'update','07-03-17/HEX/C/S220/C002','2017-05-27 10:36:00',NULL,NULL,NULL,NULL,NULL,NULL),(393,'update','07-03-17/HEX/C/S220/C001','2017-05-27 10:36:00',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `event_list`
--

DROP TABLE IF EXISTS `event_list`;
/*!50001 DROP VIEW IF EXISTS `event_list`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `event_list` AS SELECT 
 1 AS `event`,
 1 AS `qr_code`,
 1 AS `date`,
 1 AS `champ1`,
 1 AS `valeur1`,
 1 AS `champ2`,
 1 AS `valeur2`,
 1 AS `champ3`,
 1 AS `valeur3`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `qr_code` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `fournisseur` varchar(45) DEFAULT NULL,
  `ref_fournisseur` varchar(45) DEFAULT NULL,
  `longueur_initiale` decimal(10,2) DEFAULT NULL,
  `longueur_actuelle` decimal(10,2) DEFAULT NULL,
  `largeur` decimal(10,2) DEFAULT NULL,
  `grammage` varchar(45) DEFAULT NULL,
  `type_de_tissus` varchar(45) DEFAULT NULL,
  `date_arrivee` datetime DEFAULT NULL,
  `transport_frigo` varchar(10) DEFAULT NULL,
  `lieu_actuel` varchar(45) DEFAULT NULL,
  `lieu_depuis` datetime DEFAULT NULL,
  `temps_hors_gel_total` int(11) DEFAULT NULL,
  `nb_decongelation` int(11) DEFAULT NULL,
  `finished` int(11) DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`qr_code`),
  UNIQUE KEY `qr_code_UNIQUE` (`qr_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('01-03-17/SGL/C/T200/WS001','Carbone aérazur','2017-03-08 18:44:00','SGL','C W200-TW2/2-E323/45%',49.00,NULL,1.20,'200','sergé carbone','2017-03-08 18:32:00','non','Chambre Froide','2017-05-05 15:28:00',2524020,NULL,NULL,'Pièces produites :\nAVD 221347-0 718 285 85-04\nAVD 221347-0 718 285 86-04'),('02-03-17/SFC/C/UD150/WS001','Guidon TR50S','2017-03-08 18:20:00','SF composite','Carbone UD150 TR50S 38% R 367-2',9.00,NULL,0.60,'150','UD carbone','2017-03-08 18:18:00','non','Chambre Froide','2017-04-13 08:06:00',127980,NULL,NULL,NULL),('07-03-17/HEX/C/S220/C001','Preg Vision','2017-03-08 18:45:00','Hexcel','M18/1/43%/G939/1230',50.00,0.00,1.23,'220','satin carbone','2017-03-08 18:45:00','oui','Chambre Froide','2017-04-07 11:19:00',0,NULL,1,NULL),('07-03-17/HEX/C/S220/C002','Preg vision','2017-03-08 18:48:00','Hexcel','M18/1/43%/G939/1230',50.00,0.00,1.23,'220','satin carbone','2017-03-08 18:47:00','oui','Chambre Froide','2017-03-23 08:59:00',965820,NULL,1,NULL),('07-03-17/HEX/C/S220/C003','Preg vision','2017-03-08 18:49:00','Hexcel','M18/1/43%/G939/1230',50.00,9.80,1.23,'220','satin carbone','2017-03-08 18:48:00','oui',NULL,'2017-04-10 08:48:00',1055340,NULL,NULL,NULL),('08-03-17/HEX/C/P200/WS001','Echantillon M10R','2017-03-08 18:26:00','SF composite','M10R/42%/200P/CHS-3K 1250',2.00,2.00,1.25,'200','Taffetas carbone','2017-03-08 18:25:00','non','Chambre Froide','2017-03-08 18:26:00',0,NULL,NULL,NULL),('08-03-17/HEX/C/S220/WS005','Carbone 3x1 M26T','2017-03-08 18:24:00','Hexcel','M26T/50%/G939/CHS/1230',40.00,40.00,1.23,'200','satin carbone','2017-03-08 18:23:00','non','Chambre Froide','2017-03-08 18:24:00',0,NULL,NULL,NULL),('08-03-17/HEX/C/T200/WS002','Echantillon M49','2017-03-08 18:31:00','SF composite','M49/42%/200T/CHS',1.00,1.00,1.25,'200','sergé carbone','2017-03-08 18:30:00','non','Chambre Froide','2017-03-08 18:31:00',0,NULL,NULL,NULL),('08-03-17/HEX/C/T200/WS004','Vieux rouleau ','2017-03-08 18:22:00','Hexcel','M21/40%/46230-2x2/1170',15.00,0.00,1.17,'200','sergé carbone','2017-03-08 18:21:00','non','Chambre Froide','2017-04-21 11:47:00',67320,NULL,NULL,NULL),('08-03-17/HEX/C/T200/WS007','Carbone pour bras de robot','2017-03-08 18:30:00','Hexcel','-/-/200T/CHS/1240',20.00,20.00,1.24,'200','sergé carbone','2017-03-08 18:28:00','non','Chambre Froide','2017-03-08 18:30:00',0,NULL,NULL,NULL),('08-03-17/HEX/C/T300/WS006','Echantillon M34N','2017-03-08 18:28:00','SF composite','M34N/42%/300T/G-68X3/1240',2.00,2.00,1.24,'200','sergé','2017-03-08 18:27:00','non','Chambre Froide','2017-03-08 18:28:00',0,NULL,NULL,NULL),('08-03-17/HEX/C/UD600/WS003','UD 600','2017-03-09 14:27:00','Hexcel','M9.6GF/35%/UD600/CHS 315',100.00,0.00,0.32,'600','UD carbone','2017-03-09 14:26:00','non','Chambre Froide','2017-05-18 15:50:00',3437460,NULL,1,NULL),('08-03-17/HEX/V/P200/WS013','Échantillon verre hexcel','2017-03-09 14:39:00','Hexcel','/42%/200P/GLA',2.00,2.00,1.25,'200','Taffetas verre','2017-03-09 14:38:00',NULL,'Chambre Froide','2017-03-09 14:39:00',0,NULL,NULL,NULL),('08-03-17/TEN/C/P200/WS009','Carbone OGON tencate','2017-03-09 14:54:00','Tencate','D329-2B-00 HS3641 42% CAR205g PW',20.00,20.00,1.27,'205','Taffetas carbone','2017-03-09 14:53:00',NULL,'Chambre Froide','2017-03-09 14:54:00',0,NULL,NULL,NULL),('08-03-17/TEN/C/P200/WS010','Carbone OGON tencate','2017-03-09 14:52:00','Tencate','D329-2B-00 HS3641 42% CAR205g PW',20.00,20.00,1.27,'205','Sergé carbone','2017-03-09 14:49:00',NULL,'Chambre Froide','2017-03-09 14:52:00',0,NULL,NULL,NULL),('08-03-17/TEN/V/P600/WS014','Verre noir 600g','2017-03-09 14:46:00','Tencate','D329-2B-02 GE0402 37% GLA600g PW',18.00,NULL,1.25,'600','Taffetas verre','2017-03-09 14:45:00',NULL,'Chambre Froide','2017-03-24 16:43:00',160500,NULL,NULL,NULL),('08-03-17/TEN/V/P600/WS015','Verre noir 600g','2017-03-09 14:47:00','Tencate','D329-2B-02 GE0402 37% GLA600g PW',10.00,10.00,1.25,'600','Taffetas verre','2017-03-09 14:46:00',NULL,'Chambre Froide','2017-03-09 14:47:00',0,NULL,NULL,NULL),('08-03-17/TEN/V/P600/WS016','Verre noir 600g','2017-03-09 14:44:00','Tencate','D329-2B-02 GE0402 37% GLA600g PW',20.00,20.00,1.25,'600','Taffetas verre','2017-03-09 14:43:00',NULL,'Chambre Froide','2017-03-09 14:44:00',0,NULL,NULL,NULL),('08-03-17/VIT/C/UD4x50/WS008','UD empilé','2017-03-09 14:33:00',NULL,NULL,2.00,2.00,1.10,'4x50','UD carbone','2017-03-09 14:32:00',NULL,'Chambre Froide','2017-03-09 14:33:00',0,NULL,NULL,NULL),('08-03-17/VIT/C/UD50/WS012','UD 50g','2017-03-09 14:35:00','Vitech','XB3515/43%/UD50/CHS-TR50S12L',44.00,23.00,0.60,'50','UD carbone','2017-03-09 14:34:00','non',NULL,'2017-04-03 08:42:00',64560,NULL,NULL,NULL),('09-03-17/AXS/C/T200/WS009','Carbone moule axson','2017-03-09 14:41:00','Axson',NULL,15.00,15.00,1.20,'200','Sergé carbone','2017-03-09 14:41:00',NULL,NULL,'2017-03-28 16:39:00',0,NULL,NULL,NULL),('09-03-17/AXS/C/T650/WS010','Carbone moule axson','2017-03-09 14:42:00','Axson',NULL,3.50,-3.50,1.20,'650','Sergé carbone','2017-03-09 14:42:00',NULL,'Chambre Froide','2017-03-30 18:35:00',359400,NULL,NULL,NULL),('09-03-17/AXS/C/T650/WS011','Carbone moule axson','2017-03-09 14:40:00','Axson',NULL,5.00,0.00,1.20,'650','Sergé carbone','2017-03-09 14:40:00',NULL,'Chambre Froide','2017-03-29 15:09:00',80940,NULL,1,NULL),('09-03-17/AXS/C/T650/WS013','Carbone moule axson','2017-03-09 15:52:00','Axson',NULL,7.00,0.00,1.20,'650','Sergé carbone','2017-03-09 15:52:00',NULL,'Chambre Froide','2017-03-30 08:40:00',144060,NULL,1,NULL),('09-03-17/HEX/C/FB2000/WS001','HEXTool','2017-03-09 17:35:00','Hexcel','M61/C/2000',9.00,9.00,0.46,'2000','Fibre Coupée carbone','2017-03-09 17:33:00','non',NULL,'2017-03-22 18:22:00',0,NULL,NULL,NULL),('09-03-17/HEX/C/FB2000/WS002','HEXMC','2017-03-09 17:37:00','Hexcel','M77/C/2000/38%/CHS',5.00,1.00,0.46,'2000','Fibre Coupée carbone','2017-03-09 17:35:00',NULL,'Chambre Froide','2017-03-27 17:47:00',865860,NULL,NULL,NULL),('09-03-17/HEX/C/P200/WS002','Carbone ogon','2017-03-09 12:16:00','Hexcel','M77N/42%/200P/CHR-3K/1250',50.00,7.00,1.25,'200','Taffetas carbone','2017-03-09 12:15:00','non','Chambre Froide','2017-05-11 20:35:00',230940,NULL,NULL,NULL),('09-03-17/HEX/C/P200/WS003','Carbone ogon','2017-03-09 12:20:00','Hexcel','M77N/42%/200P/CHR-3K/1250',52.00,0.00,1.25,'200','Taffetas carbone','2017-03-09 12:19:00','non','Chambre Froide','2017-05-02 11:32:00',596400,NULL,NULL,NULL),('09-03-17/HEX/C/UD150/WS007','UD 150g guidon','2017-03-09 14:38:00','Hexcel','M79/38%/UD150/CHS 650',9.00,1.30,0.65,'150','UD carbone','2017-03-09 14:37:00','non','Chambre Froide','2017-03-24 17:43:00',264480,NULL,NULL,NULL),('09-03-17/HEX/C/UD300/WS001','UD300','2017-03-09 11:58:00','Hexcel','M79/34%/UD300/CHS 1300',50.00,46.00,1.30,'300','UD carbone','2017-03-09 11:57:00','non','Chambre Froide','2017-04-13 18:28:00',374640,NULL,NULL,NULL),('09-03-17/HEX/C/UD600/WS015','UD 600','2017-03-09 15:54:00','Hexcel','M9.6GF/35%/UD600/CHS 315',100.00,91.90,0.32,'600','UD carbone','2017-03-09 15:53:00',NULL,'Chambre Froide','2017-05-19 09:11:00',62580,NULL,NULL,NULL),('09-03-17/NGF/C/UD125/WS005','UD GRANOC','2017-03-09 15:58:00','NGF','NT91250-525S/25%/UD125',8.00,8.00,1.00,'125','UD carbone','2017-03-09 15:56:00',NULL,'Chambre Froide','2017-03-09 15:58:00',0,NULL,NULL,NULL),('09-03-17/NGF/C/UD125/WS006','UD GRANOC','2017-03-09 15:56:00','NGF','NT91250-525S/25%/UD125',8.00,8.00,1.00,'125','UD carbone','2017-03-09 15:54:00',NULL,'Chambre Froide','2017-03-09 15:56:00',0,NULL,NULL,NULL),('09-03-17/TEN/C/P205/WS018','Carbone OGON tencate','2017-03-09 17:14:00','Tencate','D329-2B-00 HS3641 42% CAR205g PW',20.00,20.00,1.25,'205','Taffetas carbone','2017-03-09 17:13:00',NULL,'Chambre Froide','2017-03-09 17:14:00',0,NULL,NULL,NULL),('09-03-17/TEN/C/P205/WS019','Échantillon carbone OGON','2017-03-09 17:10:00','Tencate','D329-2B-00 HS3641 42% CAR205g PW',0.80,0.80,1.25,'200','Sergé carbone','2017-03-09 17:09:00',NULL,'Chambre Froide','2017-03-09 17:10:00',0,NULL,NULL,NULL),('09-03-17/TEN/C/T200/WS020','Carbone moule tencate','2017-03-09 17:08:00','Tencate','HX40-00 HS2016 CBN200G 2x2T',1.00,1.00,1.25,'200','Sergé carbone','2017-03-09 17:07:00',NULL,NULL,'2017-03-28 16:40:00',0,NULL,NULL,NULL),('09-03-17/TEN/V/280/WS012','Verre moule','2017-03-09 15:39:00','Tencate',NULL,NULL,NULL,1.25,'280','Verre','2017-03-09 15:37:00',NULL,'Chambre Froide','2017-03-09 15:39:00',0,NULL,NULL,NULL),('09-03-17/TEN/V/P600/WS016','Verre noir 600g','2017-03-09 17:16:00','Tencate','D329-2B-02 GE0402 37% GLA600g PW',7.00,7.00,1.25,'600','Taffetas verre','2017-03-09 17:15:00',NULL,'Chambre Froide','2017-03-09 17:16:00',0,NULL,NULL,NULL),('09-03-17/TEN/V/P600/WS017','Verre noir 600g','2017-03-09 17:13:00','Tencate','D329-2B-02 GE0402 37% GLA600g PW',3.00,3.00,1.25,'600','Taffetas verre','2017-03-09 17:11:00',NULL,'Chambre Froide','2017-03-09 17:13:00',0,NULL,NULL,NULL),('09-03-17/VIT/C/UD110/WS008','UD 110g guidon','2017-03-09 14:37:00','Vitech','XB3515/32%/HM380-110/600',9.00,6.00,0.60,'110','UD carbone','2017-03-09 14:35:00','non','Chambre Froide','2017-04-07 15:29:00',692040,NULL,NULL,NULL),('09-03-17/VIT/C/UD50/WS014','UD 50g','2017-03-09 16:20:00','Vitech','XB3515/43%/UD50/CHS-TR50S12L',20.00,20.00,0.60,'50','UD carbone','2017-03-09 16:19:00',NULL,'Chambre Froide','2017-03-09 16:20:00',0,NULL,NULL,NULL),('15-05-17/NGF/C/UD55/CNRS001','ud granoc','2017-05-17 11:58:00','ngf','E9026A-05S',19.00,19.00,1000.00,'55','UD','2017-05-17 11:54:00','non','Chambre Froide','2017-05-17 11:58:00',0,NULL,NULL,NULL),('19-05-17/HEX/V/S200/VS001','tissu verre casing double','2017-05-22 16:55:00','hexel','hexplyM26/37%/7781/1279mm',56.00,46.00,1.26,'200','serge verre','2017-05-19 08:45:00','non','Chambre Froide','2017-05-24 17:18:00',174060,NULL,NULL,NULL),('23-03-17/HEX/V/P395/WS001','preg verre ogon','2017-03-23 17:11:00','hexcel','M77/38%/395P/G-1250MM',100.00,39.15,1.25,'395','Taffetas verre','2017-03-23 17:09:00','Non','Chambre Froide','2017-05-10 19:33:00',646680,NULL,NULL,NULL),('26-04-17/AXS/C/200/WS001','preg moule','2017-04-26 15:36:00','axson','LTC216C200',20.00,18.80,NULL,'200','carbone','2017-04-26 15:34:00','non','Chambre Froide','2017-05-15 16:52:00',27900,NULL,NULL,NULL),('26-04-17/AXS/C/650/WS001','preg moule','2017-04-26 15:38:00','axson','LTC216C650',25.00,25.00,NULL,'650','carbone','2017-04-26 15:37:00','non','Chambre Froide','2017-04-26 15:38:00',0,NULL,NULL,NULL),('29-03-17/VIT/C/UD50/WS001','UD 50g','2017-03-30 10:53:00','Vitech Composite','HR-230-50 XB3515 43%',53.00,0.00,0.60,'50','UD carbone','2017-03-30 10:51:00','non','Chambre Froide','2017-04-07 15:27:00',370380,NULL,NULL,NULL),('29-03-17/VIT/C/UD50/WS002','UD 50g','2017-03-30 10:55:00','Vitech Composite','HR-230-50 XB3515 43%',190.00,64.00,0.60,'50','UD carbone','2017-03-30 10:53:00','non','Chambre Froide','2017-04-07 14:22:00',366420,NULL,NULL,NULL),('Film de colle','Film de colle','2017-02-07 09:02:00','SF-composite',NULL,10.00,10.00,1.00,NULL,'Film de colle','2017-02-07 09:00:00',NULL,'Chambre Froide','2017-02-07 09:02:00',0,NULL,NULL,NULL),('Film de colle2','Film de colle','2017-02-07 15:51:00',NULL,NULL,10.00,10.00,1.00,NULL,'colle','2017-02-07 15:50:00',NULL,'Chambre Froide','2017-02-07 15:51:00',0,NULL,NULL,NULL),('HEXTOOL','HEXTOOL','2017-02-07 15:54:00','Axson','HEXTOOL BMI M61',2.50,0.00,1.00,'2kg/m2','Fibre broyé','2017-02-07 15:53:00',NULL,'Chambre Froide','2017-03-03 11:25:00',251520,NULL,1,NULL),('M77/45%/160P/GLA','Verre hexcel ogon','2017-02-07 16:11:00','SF composite','M77/38%/395P/GLA/1250',10.00,0.00,1.25,'395','verre taffetas','2017-02-07 16:09:00',NULL,'Chambre Froide','2017-02-20 18:17:00',261180,NULL,1,NULL),('PP163490916/007','Carbone 650g moule','2017-02-07 16:29:00','Tencate','HX40-00 HS2016 CBN650g 2x2T 35%RW',20.00,0.00,1.25,'650','Carbone sergé','2017-02-07 16:27:00',NULL,'Chambre Froide','2017-03-08 10:57:00',269340,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `product_list`
--

DROP TABLE IF EXISTS `product_list`;
/*!50001 DROP VIEW IF EXISTS `product_list`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `product_list` AS SELECT 
 1 AS `qr_code`,
 1 AS `name`,
 1 AS `longueur_actuelle`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `product_view`
--

DROP TABLE IF EXISTS `product_view`;
/*!50001 DROP VIEW IF EXISTS `product_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `product_view` AS SELECT 
 1 AS `qr_code`,
 1 AS `name`,
 1 AS `fournisseur`,
 1 AS `ref_fournisseur`,
 1 AS `longueur_initiale`,
 1 AS `longueur_actuelle`,
 1 AS `largeur`,
 1 AS `grammage`,
 1 AS `type_de_tissus`,
 1 AS `date_arrivee`,
 1 AS `transport_frigo`,
 1 AS `lieu_actuel`,
 1 AS `lieu_depuis`,
 1 AS `temps_hors_gel_total`,
 1 AS `nb_decongelation`,
 1 AS `note`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'workshapedb'
--
/*!50003 DROP FUNCTION IF EXISTS `inner_nullify_str` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_nullify_str`(str_to_nullify_ nvarchar(45)) RETURNS varchar(45) CHARSET utf8
BEGIN

    if str_to_nullify_ = "" then
    	return null;
	else
		return str_to_nullify_;
    end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `inner_str_to_date` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_str_to_date`(date_str_ nvarchar(45)) RETURNS datetime
BEGIN
	# french date format
    RETURN str_to_date(date_str_, "%d/%m/%Y %H:%i");
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `inner_str_to_decimal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_str_to_decimal`(str_int_ nvarchar(45)) RETURNS decimal(10,2)
BEGIN

	declare ret_dec_ decimal(10,2);
	set ret_dec_ = 0 + str_int_;
	if ret_dec_ = 0 then
		set ret_dec_ = null;
	end if;
	return ret_dec_;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `inner_str_to_int` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_str_to_int`(str_int_ nvarchar(45)) RETURNS int(11)
BEGIN

	declare ret_dec_ integer;
	set ret_dec_ = 0 + str_int_;
	if ret_dec_ = 0 then
		set ret_dec_ = null;
	end if;
	return ret_dec_;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `product_add` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_add`(in qr_code_ nvarchar(45),
	in date_now_ nvarchar(45),
	in name_ nvarchar(45),
    in fournisseur_ nvarchar(45),
    in ref_fournisseur_ nvarchar(45),
    in longueur_initiale_ nvarchar(45),
    in largeur_ nvarchar(45),
    in grammage_ nvarchar(45),
    in type_de_tissus_ nvarchar(45),
    in date_arrivee_ nvarchar(45),
    in transport_frigo_ nvarchar(45),
    in lieu_actuel_ nvarchar(45),
	in finished_ nvarchar(45),
	in event_label_ nvarchar(45),
	in name_label_ nvarchar(45))
BEGIN

    insert into product(qr_code, name, date_creation, fournisseur, ref_fournisseur, longueur_initiale, longueur_actuelle, largeur, grammage, type_de_tissus, date_arrivee, transport_frigo, lieu_actuel, lieu_depuis, temps_hors_gel_total, finished)
		values(qr_code_,
			inner_nullify_str(name_),
            inner_str_to_date(date_now_),
            inner_nullify_str(fournisseur_),
            inner_nullify_str(ref_fournisseur_),
            inner_str_to_decimal(longueur_initiale_),
            inner_str_to_decimal(longueur_initiale_),
            inner_str_to_decimal(largeur_),
            inner_nullify_str(grammage_),
            inner_nullify_str(type_de_tissus_),
            inner_str_to_date(date_arrivee_),
            inner_nullify_str(transport_frigo_),
            inner_nullify_str(lieu_actuel_),
            inner_str_to_date(date_now_),
            0,
            inner_str_to_int(finished_) );

    insert into event(qr_code, event, date, champ1, valeur1) values(qr_code_, event_label_, inner_str_to_date(date_now_), name_label_, inner_nullify_str(name_));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `product_in` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_in`(in qr_code_ nvarchar(45),
	in date_now_ nvarchar(45),
    in longueur_consommee_ nvarchar(45),
    in finished_ nvarchar(45),
    in temps_hors_gel_ int(11),
    in string_temps_hors_gel_ nvarchar(45),
    in lieu_actuel_ nvarchar(45),
	in event_label_ nvarchar(45),
	in lieu_actuel_label_ nvarchar(45),
	in longueur_consommee_label_ nvarchar(45),
	in temps_hors_gel_label_ nvarchar(45))
BEGIN

	SET SQL_SAFE_UPDATES = 0;
	update product
		set lieu_actuel = lieu_actuel_,
			lieu_depuis = inner_str_to_date(date_now_),
			longueur_actuelle = coalesce(longueur_actuelle - inner_str_to_decimal(longueur_consommee_), longueur_initiale - inner_str_to_decimal(longueur_consommee_)),
			temps_hors_gel_total = temps_hors_gel_total + temps_hors_gel_,
			lieu_actuel = inner_nullify_str(lieu_actuel_),
            finished = inner_str_to_int(finished_)
        where qr_code=qr_code_;
	SET SQL_SAFE_UPDATES = 1;

    insert into event(qr_code, event, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qr_code_, event_label_, inner_str_to_date(date_now_),
				lieu_actuel_label_, lieu_actuel_,
                longueur_consommee_label_, inner_str_to_decimal(longueur_consommee_),
                temps_hors_gel_label_, string_temps_hors_gel_);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `product_out` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_out`(in qr_code_ nvarchar(45),
	in date_now_ nvarchar(45),
	in lieu_actuel_ nvarchar(45),
    in en_stock_depuis_ nvarchar(45),
    in longueur_actuelle_ nvarchar(45),
    in string_total_hors_gel_ nvarchar(45),
    in event_label_ nvarchar(45),
    in en_stock_depuis_label_ nvarchar(45),
    in longueur_actuelle_label_ nvarchar(45),
    in string_total_hors_gel_label_ nvarchar(45))
begin
	declare date_now_dt_ DateTime;

	# datetime entry
    set date_now_dt_ = inner_str_to_date(date_now_);
    
    # nullify some strings
    set lieu_actuel_ = inner_nullify_str(lieu_actuel_);

	# first update product
	update product set
		lieu_actuel=lieu_actuel_,
        lieu_depuis=date_now_dt_
	where qr_code=qr_code_;

	# then record an event
    insert into event(qr_code, event, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qr_code_, event_label_, date_now_dt_,
				en_stock_depuis_label_, en_stock_depuis_,
                longueur_actuelle_label_, longueur_actuelle_,
                string_total_hors_gel_label_, string_total_hors_gel_);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `product_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_update`(in qr_code_ nvarchar(45),
	in date_now_ nvarchar(45),
	in name_ nvarchar(45),
    in fournisseur_ nvarchar(45),
    in ref_fournisseur_ nvarchar(45),
    in longueur_initiale_ nvarchar(45),
    in largeur_ nvarchar(45),
    in grammage_ nvarchar(45),
    in type_de_tissus_ nvarchar(45),
    in date_arrivee_ nvarchar(45),
    in transport_frigo_ nvarchar(10),
    in lieu_actuel_ nvarchar(45),
	in finished_ nvarchar(45),
    in event_label_ nvarchar(45))
BEGIN

	SET SQL_SAFE_UPDATES = 0;
    update product
		set name = inner_nullify_str(name_),
            fournisseur = inner_nullify_str(fournisseur_),
            ref_fournisseur = inner_nullify_str(ref_fournisseur_),
            longueur_initiale = inner_str_to_decimal(longueur_initiale_),
            largeur = inner_str_to_decimal(largeur_),
            grammage = inner_nullify_str(grammage_),
            type_de_tissus = inner_nullify_str(type_de_tissus_),
            date_arrivee = inner_str_to_date(date_arrivee_),
            transport_frigo = inner_nullify_str(transport_frigo_),
            lieu_actuel = inner_nullify_str(lieu_actuel_),
            finished = inner_str_to_int(finished_)
		where qr_code = qr_code_;
	SET SQL_SAFE_UPDATES = 1;

    insert into event(qr_code, event, date) values(qr_code_, event_label_, inner_str_to_date(date_now_));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `product_update_note` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_update_note`(
	in qr_code_ nvarchar(45),
    in date_now_ nvarchar(45),
    in note_ text,
	in event_label_ nvarchar(45))
BEGIN
	declare date_now_dt_ DateTime;

   	# datetime entry
    set date_now_dt_ = inner_str_to_date(date_now_);

	# added an entry in product table
    update product set note = note_ where qr_code = qr_code_;

	# then record an event
    insert into event(qr_code, event, date) values(qr_code_, event_label_, date_now_dt_);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `event_list`
--

/*!50001 DROP VIEW IF EXISTS `event_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`workshape`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `event_list` AS select `event`.`event` AS `event`,`event`.`qr_code` AS `qr_code`,date_format(`event`.`date`,'%d/%m/%Y %H:%i') AS `date`,`event`.`champ1` AS `champ1`,`event`.`valeur1` AS `valeur1`,`event`.`champ2` AS `champ2`,`event`.`valeur2` AS `valeur2`,`event`.`champ3` AS `champ3`,`event`.`valeur3` AS `valeur3` from `event` order by `event`.`idevent` desc,`event`.`date` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `product_list`
--

/*!50001 DROP VIEW IF EXISTS `product_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`workshape`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `product_list` AS select `product`.`qr_code` AS `qr_code`,coalesce(`product`.`name`,`product`.`qr_code`) AS `name`,coalesce(`product`.`longueur_actuelle`,`product`.`longueur_initiale`) AS `longueur_actuelle` from `product` where (isnull(`product`.`finished`) or (`product`.`finished` <> 1)) order by `product`.`date_creation` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `product_view`
--

/*!50001 DROP VIEW IF EXISTS `product_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`workshape`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `product_view` AS select `product`.`qr_code` AS `qr_code`,`product`.`name` AS `name`,`product`.`fournisseur` AS `fournisseur`,`product`.`ref_fournisseur` AS `ref_fournisseur`,`product`.`longueur_initiale` AS `longueur_initiale`,`product`.`longueur_actuelle` AS `longueur_actuelle`,`product`.`largeur` AS `largeur`,`product`.`grammage` AS `grammage`,`product`.`type_de_tissus` AS `type_de_tissus`,date_format(`product`.`date_arrivee`,'%d/%m/%Y %H:%i') AS `date_arrivee`,`product`.`transport_frigo` AS `transport_frigo`,`product`.`lieu_actuel` AS `lieu_actuel`,date_format(`product`.`lieu_depuis`,'%d/%m/%Y %H:%i') AS `lieu_depuis`,`product`.`temps_hors_gel_total` AS `temps_hors_gel_total`,`product`.`nb_decongelation` AS `nb_decongelation`,`product`.`note` AS `note` from `product` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-27 10:39:00
