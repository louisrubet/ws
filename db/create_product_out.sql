CREATE DEFINER=`workshape`@`%` PROCEDURE `product_out`(in qrcode nvarchar(45), in date_now nvarchar(45))
begin
	declare date_time DateTime;

    set date_time = str_to_date(date_now, "%d/%m/%Y %T");

	# first update product
	update product set lieu_actuel="sortie", lieu_depuis=date_time where qr_code=qrcode;

	# then record an event
	# datetime entry in french format, i.e. "23/12/2016 16:57:00"
    insert into event(qr_code, date, champ1, valeur1) values(qrcode, date_time, "lieu_actuel", "sortie");
end