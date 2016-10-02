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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
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
  `nb_decongelation` int(11) DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`idproduct`),
  UNIQUE KEY `idx_product_qr_code` (`qr_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'workshapedb'
--
/*!50003 DROP PROCEDURE IF EXISTS `product_in` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_in`(in qrcode nvarchar(45), in date_now nvarchar(45), in longueur_consommee decimal(10,2), in temps_hors_gel nvarchar(45))
BEGIN
	declare date_time DateTime;
    declare tps_hors_gel time;

	# datetime entry in french format, i.e. "23/12/2016 16:57:00"
    set date_time = str_to_date(date_now, "%d/%m/%Y %T");
    set tps_hors_gel = convert(temps_hors_gel, time);

	# first update product
	update product
		set lieu_actuel = "frigo",
			lieu_depuis = date_time,
			longueur_actuelle = longueur_actuelle - longueur_consommee,
			temps_hors_gel_total = coalesce(temps_hors_gel_total + tps_hors_gel, tps_hors_gel)
        where qr_code=qrcode;

	# then record an event
    insert into event(qr_code, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qrcode, date_time,
				"lieu_actuel", "frigo",
                "longueur_consommee", longueur_consommee,
                "temps_hors_gel", temps_hors_gel);

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
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_out`(in qrcode nvarchar(45), in date_now nvarchar(45))
begin
	declare date_time DateTime;

    set date_time = str_to_date(date_now, "%d/%m/%Y %T");

	# first update product
	update product set lieu_actuel="sortie", lieu_depuis=date_time where qr_code=qrcode;

	# then record an event
	# datetime entry in french format, i.e. "23/12/2016 16:57:00"
    insert into event(qr_code, date, champ1, valeur1) values(qrcode, date_time, "lieu_actuel", "sortie");
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-02 18:28:49
