CREATE DATABASE  IF NOT EXISTS `workshapedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `workshapedb`;
-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: 192.168.1.13    Database: workshapedb
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1-log

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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `idproduct` int(11) NOT NULL AUTO_INCREMENT,
  `qr_code` varchar(45) NOT NULL,
  `reference` varchar(45) DEFAULT NULL,
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
  `temps_hors_gel_total` time DEFAULT NULL,
  `nb_decongelation` int(11) DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`idproduct`,`qr_code`),
  UNIQUE KEY `qr_code_UNIQUE` (`qr_code`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `product_view`
--

DROP TABLE IF EXISTS `product_view`;
/*!50001 DROP VIEW IF EXISTS `product_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `product_view` AS SELECT 
 1 AS `qr_code`,
 1 AS `reference`,
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
/*!50003 DROP PROCEDURE IF EXISTS `product_add` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_add`(in qr_code nvarchar(45), in date_now nvarchar(45),
	in reference nvarchar(45),
    in fournisseur nvarchar(45),
    in ref_fournisseur nvarchar(45),
    in longueur_initiale DECIMAL(10,2),
    in largeur DECIMAL(10,2),
    in grammage nvarchar(45),
    in type_de_tissus nvarchar(45),
    in date_arrivee nvarchar(45),
    in transport_frigo nvarchar(10),
    in lieu_actuel nvarchar(45))
BEGIN
	declare date_now_dt DateTime;
	declare date_arrivee_dt DateTime;

	# datetime entry in french format, i.e. "23/12/2016 16:57"
    set date_now_dt = str_to_date(date_now, "%d/%m/%Y %H:%i");
    set date_arrivee_dt = str_to_date(date_arrivee, "%d/%m/%Y %H:%i");
    
	# added an entry in product table
    insert into product(qr_code, reference, fournisseur, ref_fournisseur, longueur_initiale, longueur_actuelle, largeur, grammage, type_de_tissus, date_arrivee, transport_frigo, lieu_actuel, lieu_depuis)
		values(qr_code, reference, fournisseur, ref_fournisseur, longueur_initiale, longueur_initiale, largeur, grammage, type_de_tissus, date_arrivee_dt, transport_frigo, lieu_actuel, date_now_dt);

	# then record an event
    insert into event(qr_code, event, date) values(qr_code, "new", date_now_dt);
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_in`(in qrcode nvarchar(45), in date_now nvarchar(45), in longueur_consommee decimal(10,2), in temps_hors_gel nvarchar(45))
BEGIN
	declare date_now_dt DateTime;
    declare tps_hors_gel time;

	# datetime entry in french format, i.e. "23/12/2016 16:57"
    set date_now_dt = str_to_date(date_now, "%d/%m/%Y %H:%i");
    set tps_hors_gel = convert(temps_hors_gel, time);

	# first update product
	update product
		set lieu_actuel = "frigo",
			lieu_depuis = date_now_dt,
			longueur_actuelle = coalesce(longueur_actuelle - longueur_consommee, longueur_initiale - longueur_consommee),
			temps_hors_gel_total = coalesce(AddTime(temps_hors_gel_total, tps_hors_gel), tps_hors_gel)
        where qr_code=qrcode;

	# then record an event
    insert into event(qr_code, event, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qrcode, "in", date_now_dt,
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_out`(in qrcode nvarchar(45), in date_now nvarchar(45))
begin
	declare date_now_dt DateTime;

    # datetime entry in french format, i.e. "23/12/2016 16:57"
    set date_now_dt = str_to_date(date_now, "%d/%m/%Y %H:%i");

	# first update product
	update product set lieu_actuel=null, lieu_depuis=date_now_dt where qr_code=qrcode;

	# then record an event
    insert into event(qr_code, event, date) values(qrcode, "out", date_now_dt);
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_update`(in qr_code nvarchar(45), in date_now nvarchar(45),
	in reference nvarchar(45),
    in fournisseur nvarchar(45),
    in ref_fournisseur nvarchar(45),
    in longueur_initiale DECIMAL(10,2),
    in largeur DECIMAL(10,2),
    in grammage nvarchar(45),
    in type_de_tissus nvarchar(45),
    in date_arrivee nvarchar(45),
    in transport_frigo nvarchar(10),
    in lieu_actuel nvarchar(45))
BEGIN
	declare date_now_dt DateTime;
	declare date_arrivee_dt DateTime;

	# datetime entry in french format, i.e. "23/12/2016 16:57:00"
    set date_now_dt = str_to_date(date_now, "%d/%m/%Y %H:%i");
    set date_arrivee_dt = str_to_date(date_arrivee, "%d/%m/%Y %H:%i");
    
	# added an entry in product table
    SET SQL_SAFE_UPDATES=0;
    update product
		set reference = reference,
            fournisseur = fournisseur,
            ref_fournisseur = ref_fournisseur,
            longueur_initiale = longueur_initiale,
            largeur = largeur,
            grammage = grammage,
            type_de_tissus = type_de_tissus,
            date_arrivee = date_arrivee_dt,
            transport_frigo = transport_frigo,
            lieu_actuel = lieu_actuel
		where qr_code = qr_code;

	# then record an event
    insert into event(qr_code, event, date) values(qr_code, "update", date_now_dt);
	SET SQL_SAFE_UPDATES=1;
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
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_update_note`(in qrcode nvarchar(45), in date_now nvarchar(45), in note text)
BEGIN
	declare date_now_dt DateTime;

	# datetime entry in french format, i.e. "23/12/2016 16:57"
    set date_now_dt = str_to_date(date_now, "%d/%m/%Y %H:%i");
    
	# added an entry in product table
    update product set note = note where qr_code = qrcode;

	# then record an event
    insert into event(qr_code, event, date) values(qrcode, "update note", date_now_dt);
END ;;
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
/*!50001 VIEW `product_view` AS select `product`.`qr_code` AS `qr_code`,`product`.`reference` AS `reference`,`product`.`fournisseur` AS `fournisseur`,`product`.`ref_fournisseur` AS `ref_fournisseur`,`product`.`longueur_initiale` AS `longueur_initiale`,`product`.`longueur_actuelle` AS `longueur_actuelle`,`product`.`largeur` AS `largeur`,`product`.`grammage` AS `grammage`,`product`.`type_de_tissus` AS `type_de_tissus`,date_format(`product`.`date_arrivee`,'%d/%m/%Y %H:%i') AS `date_arrivee`,`product`.`transport_frigo` AS `transport_frigo`,`product`.`lieu_actuel` AS `lieu_actuel`,date_format(`product`.`lieu_depuis`,'%d/%m/%Y %H:%i') AS `lieu_depuis`,`product`.`temps_hors_gel_total` AS `temps_hors_gel_total`,`product`.`nb_decongelation` AS `nb_decongelation`,`product`.`note` AS `note` from `product` */;
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

-- Dump completed on 2016-10-11 23:03:00
