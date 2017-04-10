use workshapedb;

SET SQL_SAFE_UPDATES = 0;

update event set champ3 = "Total décongelé (hh:mm)"
	where champ3 = "Total hors gel (hh:mm)";

update event set champ3 = "Temps décongelé (hh:mm)"
	where champ3 = "Temps hors gel (hh:mm)";

SET SQL_SAFE_UPDATES = 1;
