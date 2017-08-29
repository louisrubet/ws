use workshapedb;
SET SQL_SAFE_UPDATES = 0;
update product
	set largeur = largeur * 1000;
SET SQL_SAFE_UPDATES = 1;
