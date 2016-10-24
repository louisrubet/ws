CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `workshape`@`%` 
    SQL SECURITY DEFINER
VIEW `product_list` AS
    SELECT 
        `product`.`qr_code` AS `qr_code`,
        COALESCE(`product`.`name`, `product`.`qr_code`) AS `name`
    FROM
        `product`