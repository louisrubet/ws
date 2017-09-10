USE `workshapedb`;
CREATE
     OR REPLACE ALGORITHM = UNDEFINED
    DEFINER = `workshape`@`%`
    SQL SECURITY DEFINER
VIEW `workshapedb`.`product_list` AS
    SELECT
        `workshapedb`.`product`.`qr_code` AS `qr_code`,
        COALESCE(`workshapedb`.`product`.`name`,
                `workshapedb`.`product`.`qr_code`) AS `name`,
        COALESCE(`workshapedb`.`product`.`longueur_actuelle`,
                `workshapedb`.`product`.`longueur_initiale`) AS `longueur_actuelle`
    FROM
        `workshapedb`.`product`
    WHERE
        (ISNULL(`workshapedb`.`product`.`finished`)
            OR (`workshapedb`.`product`.`finished` <> 1))
    ORDER BY `workshapedb`.`product`.`date_creation`  DESC;
