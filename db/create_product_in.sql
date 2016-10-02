CREATE DEFINER=`workshape`@`%` PROCEDURE `product_in`(in qrcode nvarchar(45), in date_now nvarchar(45), in longueur_consommee decimal(10,2), in temps_hors_gel nvarchar(45))
BEGIN
	declare date_time DateTime;
    declare tps_hors_gel time;

	# datetime entry in french format, i.e. "23/12/2016 16:57:00"
    set date_time = str_to_date(date_now, "%d/%m/%Y %T");
    set tps_hors_gel = convert(temps_hors_gel, time);

	# first update product
	update product
		set lieu_actuel = "frigo",
			lieu_depuis = date_time,
			longueur_actuelle = longueur_actuelle - longueur_consommee,
			temps_hors_gel_total = coalesce(temps_hors_gel_total + tps_hors_gel, tps_hors_gel)
        where qr_code=qrcode;

	# then record an event
    insert into event(qr_code, date, champ1, valeur1, champ2, valeur2, champ3, valeur3)
		values(qrcode, date_time,
				"lieu_actuel", "frigo",
                "longueur_consommee", longueur_consommee,
                "temps_hors_gel", temps_hors_gel);

END