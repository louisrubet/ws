CREATE DATABASE  IF NOT EXISTS `workshapedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `workshapedb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 192.168.1.13    Database: workshapedb
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.10.1-log

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
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `note` text,
  PRIMARY KEY (`qr_code`),
  UNIQUE KEY `qr_code_UNIQUE` (`qr_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `product_list`
--

DROP TABLE IF EXISTS `product_list`;
/*!50001 DROP VIEW IF EXISTS `product_list`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `product_list` AS SELECT 
 1 AS `qr_code`,
 1 AS `name`*/;
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
    in lieu_actuel_ nvarchar(45))
BEGIN
	declare date_now_dt_ DateTime;
	declare date_arrivee_dt_ DateTime;
    declare longueur_initiale_dec_ decimal(10,2);
    declare largeur_dec_ decimal(10,2);
    
	# longueur_initiale_ in decimal value
	set longueur_initiale_dec_ = inner_str_to_decimal(longueur_initiale_);

	# largeur_ in decimal value
	set largeur_dec_ = inner_str_to_decimal(largeur_);

	# datetime entry
    set date_now_dt_ = inner_str_to_date(date_now_);
    set date_arrivee_dt_ = inner_str_to_date(date_arrivee_);
    
    # nullify some strings
    set name_ = inner_nullify_str(name_);
    set fournisseur_ = inner_nullify_str(fournisseur_);
    set ref_fournisseur_ = inner_nullify_str(ref_fournisseur_);
    set grammage_ = inner_nullify_str(grammage_);
    set type_de_tissus_ = inner_nullify_str(type_de_tissus_);
    set transport_frigo_ = inner_nullify_str(transport_frigo_);
    set lieu_actuel_ = inner_nullify_str(lieu_actuel_);

	# added an entry in product table
    insert into product(qr_code, name, date_creation, fournisseur, ref_fournisseur, longueur_initiale, longueur_actuelle, largeur, grammage, type_de_tissus, date_arrivee, transport_frigo, lieu_actuel, lieu_depuis, temps_hors_gel_total)
		values(qr_code_, name_, date_now_dt_, fournisseur_, ref_fournisseur_, longueur_initiale_dec_, longueur_initiale_dec_, largeur_dec_, grammage_, type_de_tissus_, date_arrivee_dt_, transport_frigo_, lieu_actuel_, date_now_dt_, 0);

	# then record an event
    insert into event(qr_code, event, date, champ1, valeur1) values(qr_code_, "new", date_now_dt_, "Nom", name_);
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
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_in`(in qr_code_ nvarchar(45),
	in date_now_ nvarchar(45),
    in longueur_consommee_ nvarchar(45),
    in temps_hors_gel_ int(11),
    in string_temps_hors_gel_ nvarchar(45),
    in lieu_actuel_ nvarchar(45))
BEGIN
	declare date_now_dt_ DateTime;
    declare longueur_consommee_dec_ decimal(10,2);

	# longueur_consommee_ in decimal value
	set longueur_consommee_dec_ = inner_str_to_decimal(longueur_consommee_);

	# datetime entry
    set date_now_dt_ = inner_str_to_date(date_now_);

    # nullify some strings
    set lieu_actuel_ = inner_nullify_str(lieu_actuel_);

	# first update product
	SET SQL_SAFE_UPDATES = 0;
	update product
		set lieu_actuel = lieu_actuel_,
			lieu_depuis = date_now_dt_,
			longueur_actuelle = coalesce(longueur_actuelle - longueur_consommee_dec_, longueur_initiale - longueur_consommee_dec_),
			temps_hors_gel_total = temps_hors_gel_total + temps_hors_gel_,
			lieu_actuel = lieu_actuel_
        where qr_code=qr_code_;
	SET SQL_SAFE_UPDATES = 1;

	# then record an event
    insert into event(qr_code, event, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qr_code_, "in", date_now_dt_,
				"Lieu de stockage", lieu_actuel_,
                "Longueur consommée (m)", longueur_consommee_dec_,
                "Temps hors gel (hh:mm)", string_temps_hors_gel_);

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
    in string_total_hors_gel_ nvarchar(45))
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
    # stocké depuis
    # longueur actuelle
    # Total hors gel
    insert into event(qr_code, event, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qr_code_, "out", date_now_dt_,
				"En stock depuis", en_stock_depuis_,
                "Longueur restante (m)", longueur_actuelle_,
                "Total hors gel (hh:mm)", string_total_hors_gel_);
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
    in lieu_actuel_ nvarchar(45))
BEGIN
	declare date_now_dt_ DateTime;
	declare date_arrivee_dt_ DateTime;
    declare longueur_initiale_dec_ decimal(10,2);
    declare largeur_dec_ decimal(10,2);

	# longueur_initiale_ in decimal value
	set longueur_initiale_dec_ = inner_str_to_decimal(longueur_initiale_);

	set largeur_dec_ = 0 + largeur_;
	if largeur_dec_ = 0 then
		set largeur_dec_ = null;
	end if;

	# datetime entry
    set date_now_dt_ = inner_str_to_date(date_now_);
    set date_arrivee_dt_ = inner_str_to_date(date_arrivee_);

    # nullify some strings
    set name_ = inner_nullify_str(name_);
    set fournisseur_ = inner_nullify_str(fournisseur_);
    set ref_fournisseur_ = inner_nullify_str(ref_fournisseur_);
    set grammage_ = inner_nullify_str(grammage_);
    set type_de_tissus_ = inner_nullify_str(type_de_tissus_);
    set transport_frigo_ = inner_nullify_str(transport_frigo_);
    set lieu_actuel_ = inner_nullify_str(lieu_actuel_);

	# added an entry in product table
	SET SQL_SAFE_UPDATES = 0;
    update product
		set name = name_,
            fournisseur = fournisseur_,
            ref_fournisseur = ref_fournisseur_,
            longueur_initiale = longueur_initiale_dec_,
            largeur = largeur_dec_,
            grammage = grammage_,
            type_de_tissus = type_de_tissus_,
            date_arrivee = date_arrivee_dt_,
            transport_frigo = transport_frigo_,
            lieu_actuel = lieu_actuel_
		where qr_code = qr_code_;
	SET SQL_SAFE_UPDATES = 1;
	
    # then record an event
    insert into event(qr_code, event, date) values(qr_code_, "update", date_now_dt_);
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
CREATE DEFINER=`workshape`@`%` PROCEDURE `product_update_note`(in qr_code_ nvarchar(45), in date_now_ nvarchar(45), in note_ text)
BEGIN
	declare date_now_dt_ DateTime;

   	# datetime entry
    set date_now_dt_ = inner_str_to_date(date_now_);

	# added an entry in product table
    update product set note = note_ where qr_code = qr_code_;

	# then record an event
    insert into event(qr_code, event, date) values(qr_code_, "update note", date_now_dt_);
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
/*!50001 VIEW `product_list` AS select `product`.`qr_code` AS `qr_code`,coalesce(`product`.`name`,`product`.`qr_code`) AS `name` from `product` order by `product`.`date_creation` */;
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

-- Dump completed on 2017-01-24 22:42:21
