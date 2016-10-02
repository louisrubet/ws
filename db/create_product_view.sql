CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `workshape`@`%` 
    SQL SECURITY DEFINER
VIEW `product_view` AS
    SELECT 
        `product`.`idproduct` AS `idproduct`,
        `product`.`reference` AS `reference`,
        `product`.`qr_code` AS `qr_code`,
        `product`.`fournisseur` AS `fournisseur`,
        `product`.`ref_fournisseur` AS `ref_fournisseur`,
        `product`.`longueur_initiale` AS `longueur_initiale`,
        `product`.`longueur_actuelle` AS `longueur_actuelle`,
        `product`.`largeur` AS `largeur`,
        `product`.`grammage` AS `grammage`,
        `product`.`type_de_tissus` AS `type_de_tissus`,
        DATE_FORMAT(`product`.`date_arrivee`, '%d/%m/%Y') AS `date_arrivee`,
        `product`.`transport_frigo` AS `transport_frigo`,
        `product`.`lieu_actuel` AS `lieu_actuel`,
        DATE_FORMAT(`product`.`lieu_depuis`, '%d/%m/%Y %T') AS `lieu_depuis`,
        `product`.`temps_hors_gel_total` AS `temps_hors_gel_total`,
        `product`.`nb_decongelation` AS `nb_decongelation`,
        `product`.`note` AS `note`
    FROM
        `product`