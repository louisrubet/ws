USE `workshapedb`;
DROP VIEW IF EXISTS `product_list`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `workshape`@`%` 
    SQL SECURITY DEFINER
VIEW `product_list` AS
    SELECT 
        `product`.`qr_code` AS `qr_code`,
        COALESCE(`product`.`name`, `product`.`qr_code`) AS `name`,
        COALESCE(`product`.`longueur_actuelle`,
                `product`.`longueur_initiale`) AS `longueur_actuelle`,
        `product`.`lieu_actuel` AS `lieu_actuel`
    FROM
        `product`
    WHERE
        (ISNULL(`product`.`finished`)
            OR (`product`.`finished` <> 1))
    ORDER BY `product`.`date_creation` DESC;
