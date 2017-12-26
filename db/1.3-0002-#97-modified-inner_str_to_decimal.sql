USE `workshapedb`;
DROP function IF EXISTS `inner_str_to_decimal`;

DELIMITER $$
USE `workshapedb`$$
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_str_to_decimal`(str_int_ nvarchar(45)) RETURNS decimal(10,2)
BEGIN

	declare ret_dec_ decimal(10,2);
	set ret_dec_ = 0 + str_int_;
	if ret_dec_ = 0 then
		set ret_dec_ = null;
	end if;
	return ret_dec_;

END$$

DELIMITER ;
