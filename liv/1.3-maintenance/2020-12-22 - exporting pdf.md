## Cahier des charges
Générer un pdf présentant la pièce et ses évènements associés

## Installation sur le PC
Installation de pandoc 2.11.3.1 et MicTeX Live 2020 pour faire des exports pdf à partir de csv.

Ajout des fichiers suivants dans le répertoire sous `"C:\Users\Travail\Desktop\pieces export"`:

`format.sty` (format latex pour MicTex)
```
    \usepackage{scrextend}
    \usepackage[utf8]{inputenc} 
    \usepackage[a4paper, landscape,top=2.5cm, bottom=2.5cm, left=0.5cm, right=0.5cm]{geometry} 
```

`export.bat` (script transformant exp.csv en exp.pdf)
```
pandoc -H format.sty -o exp.pdf exp.csv
```

## Ajout dans la base de donnés worshape
- Ajout procédure `show_product_events`

```sql
CREATE DEFINER=`workshape`@`%` PROCEDURE `show_product_events`(in qr_code_ nvarchar(45))
BEGIN
	DROP TEMPORARY TABLE if exists temp;
    create temporary table temp(date text, event text, descr text, info1 text, info2 text);
    
    insert into temp
		SELECT DATE_FORMAT(`workshapedb`.`event`.`date`, '%d/%m/%Y %H:%i') AS `date`,
			event,
            inner_concat(champ1,cast(valeur1 as char(45))) as 'descr',
            concat('QR Code ',qr_code_) as 'info1',
            '' as 'info2'
            FROM workshapedb.event where qr_code = qr_code_ and workshapedb.event.event='new';

    insert into temp
		select '' as 'date', '' as 'event', ''  as 'descr',
			concat(inner_concat('Duree de vie à 20°C: ', cast(duree_de_vie_20 as char(32))),' h') as 'info1',
            concat(inner_concat('Duree de vie à -18°C: ', cast(duree_de_vie_moins_18 as char(32))), ' h') as 'info2'
            from product where qr_code = qr_code_;

	insert into temp
		SELECT DATE_FORMAT(`workshapedb`.`event`.`date`, '%d/%m/%Y %H:%i') AS `date`,
        event,
        inner_concat(champ1,cast(valeur1 as char(45))) as 'descr',
        inner_concat(champ2,cast(valeur2 as char(45))) as 'info1',
        inner_concat(champ3,cast(valeur3 as char(45))) as 'info2'
        FROM workshapedb.event where qr_code = qr_code_
			and workshapedb.event.event<>'new'
            and workshapedb.event.event<>'update'
            order by workshapedb.event.date asc;

	insert into temp
		select '' as 'date', '' as 'event', ''  as 'descr', '' as 'info1', '' as 'info2';

    insert into temp
		select '' as 'date', '' as 'event', ''  as 'descr',
			'' as 'info1',
            inner_concat('Temps hors gel total (hh:mm:ss): ', sec_to_time(temps_hors_gel_total)) as 'info2'
            from product where qr_code = qr_code_;

	select * from temp;
	DROP TEMPORARY TABLE temp;
END
```

- Ajout fonction `inner_concat`
```sql
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_concat`(s1 text, s2 text) RETURNS text CHARSET utf8
BEGIN
	return concat(inner_stringify(s1),' ',inner_stringify(s2));
END
```

- Ajout fonction `inner_stringify`
```sql
CREATE DEFINER=`workshape`@`%` FUNCTION `inner_stringify`(str_to_stringify_ text) RETURNS text CHARSET utf8
BEGIN
    if str_to_stringify_ is null then
    	return "";
	else
		return str_to_stringify_;
    end if;
END
```

## Manuel
- Lancer MySQL Workbench 8.0 CE
- Exécuter la procédure `show_product_events` avec le QR Code du produit comme argument
- Exporter les données issues de l'exécution en cliquant sur le bouton 'Export recordset to an external file'
- Choisir `C:\Users\Travail\Desktop\pieces export\exp.csv` comme fichier d'export
- Exécuter `export.bat` en double-cliquant dessus depuis l'explorer Windows
Le fichier `exp.pdf` est généré.
