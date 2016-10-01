CREATE DATABASE  IF NOT EXISTS `workshapedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `workshapedb`;
-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: 192.168.1.13    Database: workshapedb
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

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
  `qr_code` varchar(45) NOT NULL,
  `date` datetime DEFAULT NULL,
  `champ` varchar(45) DEFAULT NULL,
  `valeur` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idevent`,`qr_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `idproduct` int(11) NOT NULL AUTO_INCREMENT,
  `reference` varchar(45) DEFAULT NULL,
  `qr_code` varchar(45) DEFAULT NULL,
  `fournisseur` varchar(45) DEFAULT NULL,
  `ref_fournisseur` varchar(50) DEFAULT NULL,
  `longueur_initiale` decimal(10,2) DEFAULT NULL,
  `longueur_actuelle` decimal(10,2) DEFAULT NULL,
  `largeur` decimal(10,2) DEFAULT NULL,
  `grammage` varchar(45) DEFAULT NULL,
  `type_de_tissus` varchar(45) DEFAULT NULL,
  `date_arrivee` datetime DEFAULT NULL,
  `transport_frigo` varchar(10) DEFAULT NULL,
  `lieu_actuel` varchar(45) DEFAULT NULL,
  `lieu_depuis` datetime DEFAULT NULL,
  `temps_hors_gel_total` time DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`idproduct`),
  UNIQUE KEY `idx_product_qr_code` (`qr_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `product_view`
--

DROP TABLE IF EXISTS `product_view`;
/*!50001 DROP VIEW IF EXISTS `product_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `product_view` AS SELECT 
 1 AS `idproduct`,
 1 AS `reference`,
 1 AS `qr_code`,
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
 1 AS `note`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'workshapedb'
--
/*!50003 DROP PROCEDURE IF EXISTS `product_out` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_out`(in qrcode nvarchar(45), in date_now nvarchar(45))
begin
	# first update product
	update product set lieu_actuel="sortie", lieu_depuis=date_string where qr_code=qrcode;

	# then record an event
	# datetime entry in french format, i.e. "23/12/2016 16:57:00"
    insert into event(qr_code, date, champ, valeur) values(qrcode, "%d/%m/%Y %T", "lieu_actuel", "sortie");
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
/*!50001 VIEW `product_view` AS select `product`.`idproduct` AS `idproduct`,`product`.`reference` AS `reference`,`product`.`qr_code` AS `qr_code`,`product`.`fournisseur` AS `fournisseur`,`product`.`ref_fournisseur` AS `ref_fournisseur`,`product`.`longueur_initiale` AS `longueur_initiale`,`product`.`longueur_actuelle` AS `longueur_actuelle`,`product`.`largeur` AS `largeur`,`product`.`grammage` AS `grammage`,`product`.`type_de_tissus` AS `type_de_tissus`,date_format(`product`.`date_arrivee`,'%d/%m/%Y') AS `date_arrivee`,`product`.`transport_frigo` AS `transport_frigo`,`product`.`lieu_actuel` AS `lieu_actuel`,date_format(`product`.`lieu_depuis`,'%d/%m/%Y %T') AS `lieu_depuis`,`product`.`temps_hors_gel_total` AS `temps_hors_gel_total`,`product`.`note` AS `note` from `product` */;
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

-- Dump completed on 2016-10-01 18:00:42
