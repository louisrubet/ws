USE workshapedb;

#ALTER TABLE `workshapedb`.`product`
#ADD COLUMN `duree_de_vie_moins_18` DECIMAL(10,2) NULL DEFAULT NULL AFTER `finished`;

#ALTER TABLE `workshapedb`.`product`
#ADD COLUMN `duree_de_vie_20` DECIMAL(10,2) NULL DEFAULT NULL AFTER `finished`;

# product_view
DROP VIEW IF EXISTS `product_view`;
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `workshape`@`%` 
    SQL SECURITY DEFINER
VIEW `product_view` AS
    SELECT 
        `product`.`qr_code` AS `qr_code`,
        `product`.`name` AS `name`,
        `product`.`fournisseur` AS `fournisseur`,
        `product`.`ref_fournisseur` AS `ref_fournisseur`,
        `product`.`longueur_initiale` AS `longueur_initiale`,
        `product`.`longueur_actuelle` AS `longueur_actuelle`,
        `product`.`largeur` AS `largeur`,
        `product`.`grammage` AS `grammage`,
        `product`.`type_de_tissus` AS `type_de_tissus`,
        DATE_FORMAT(`product`.`date_arrivee`,
                '%d/%m/%Y %H:%i') AS `date_arrivee`,
        `product`.`transport_frigo` AS `transport_frigo`,
        `product`.`lieu_actuel` AS `lieu_actuel`,
        DATE_FORMAT(`product`.`lieu_depuis`,
                '%d/%m/%Y %H:%i') AS `lieu_depuis`,
        `product`.`temps_hors_gel_total` AS `temps_hors_gel_total`,
        `product`.`nb_decongelation` AS `nb_decongelation`,
        `product`.`duree_de_vie_20` AS `duree_de_vie_20`,
        `product`.`duree_de_vie_moins_18` AS `duree_de_vie_moins_18`,
        `product`.`finished` AS `finished`,
        `product`.`note` AS `note`
    FROM
        `product`;

# product_add
DROP PROCEDURE IF EXISTS `product_add`;
DELIMITER $$
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
    in duree_de_vie_20_ nvarchar(45),
    in duree_de_vie_moins_18_ nvarchar(45),
	in event_label_ nvarchar(45),
	in name_label_ nvarchar(45))
BEGIN

    insert into product(qr_code, name, date_creation, fournisseur, ref_fournisseur, longueur_initiale, longueur_actuelle, largeur, grammage, type_de_tissus, date_arrivee, transport_frigo, lieu_actuel, lieu_depuis, temps_hors_gel_total, finished, duree_de_vie_20, duree_de_vie_moins_18)
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
            inner_str_to_int(finished_),
            inner_str_to_decimal(duree_de_vie_20_),
            inner_str_to_decimal(duree_de_vie_moins_18_));
	
    insert into event(qr_code, event, date, champ1, valeur1) values(qr_code_, event_label_, inner_str_to_date(date_now_), name_label_, inner_nullify_str(name_));
END$$
DELIMITER ;

# product_update
DROP PROCEDURE IF EXISTS `product_update`;
DELIMITER $$
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
    in duree_de_vie_20_ nvarchar(45),
    in duree_de_vie_moins_18_ nvarchar(45),
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
            finished = inner_str_to_int(finished_),
            duree_de_vie_20 = inner_str_to_decimal(duree_de_vie_20_),
            duree_de_vie_moins_18 = inner_str_to_decimal(duree_de_vie_moins_18_)
		where qr_code = qr_code_;
	SET SQL_SAFE_UPDATES = 1;
    
    insert into event(qr_code, event, date) values(qr_code_, event_label_, inner_str_to_date(date_now_));
END$$
DELIMITER ;

# inner_str_to_decimal
DROP FUNCTION IF EXISTS `inner_str_to_decimal`;
DELIMITER $$
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_str_to_decimal`(str_int_ nvarchar(45)) RETURNS decimal(10,2)
BEGIN

	declare ret_dec_ decimal(10,2);
	set ret_dec_ = 0 + str_int_;
	return ret_dec_;

END$$
DELIMITER ;
