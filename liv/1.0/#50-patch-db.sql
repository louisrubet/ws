USE `workshapedb`;

ALTER TABLE `workshapedb`.`product`
ADD COLUMN `finished` INT(11) NULL DEFAULT NULL AFTER `nb_decongelation`;

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `workshape`@`%`
    SQL SECURITY DEFINER
VIEW `workshapedb`.`product_view` AS
    SELECT
        `workshapedb`.`product`.`qr_code` AS `qr_code`,
        `workshapedb`.`product`.`name` AS `name`,
        `workshapedb`.`product`.`fournisseur` AS `fournisseur`,
        `workshapedb`.`product`.`ref_fournisseur` AS `ref_fournisseur`,
        `workshapedb`.`product`.`longueur_initiale` AS `longueur_initiale`,
        `workshapedb`.`product`.`longueur_actuelle` AS `longueur_actuelle`,
        `workshapedb`.`product`.`largeur` AS `largeur`,
        `workshapedb`.`product`.`grammage` AS `grammage`,
        `workshapedb`.`product`.`type_de_tissus` AS `type_de_tissus`,
        DATE_FORMAT(`workshapedb`.`product`.`date_arrivee`,
                '%d/%m/%Y %H:%i') AS `date_arrivee`,
        `workshapedb`.`product`.`transport_frigo` AS `transport_frigo`,
        `workshapedb`.`product`.`lieu_actuel` AS `lieu_actuel`,
        DATE_FORMAT(`workshapedb`.`product`.`lieu_depuis`,
                '%d/%m/%Y %H:%i') AS `lieu_depuis`,
        `workshapedb`.`product`.`temps_hors_gel_total` AS `temps_hors_gel_total`,
        `workshapedb`.`product`.`nb_decongelation` AS `nb_decongelation`,
        `workshapedb`.`product`.`finished` AS `finished`,
        `workshapedb`.`product`.`note` AS `note`
    FROM
        `workshapedb`.`product`

CREATE FUNCTION `inner_str_to_int` (str_int_ nvarchar(45))
RETURNS INTEGER
BEGIN

	declare ret_dec_ integer;
	set ret_dec_ = 0 + str_int_;
	if ret_dec_ = 0 then
		set ret_dec_ = null;
	end if;
	return ret_dec_;

END

DROP procedure IF EXISTS `product_add`;

DELIMITER $$
USE `workshapedb`$$
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
END$$

DELIMITER ;

DROP procedure IF EXISTS `product_update`;

DELIMITER $$
USE `workshapedb`$$
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
END$$

DELIMITER ;

DROP procedure IF EXISTS `product_in`;

DELIMITER $$
USE `workshapedb`$$
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

END$$

DELIMITER ;
