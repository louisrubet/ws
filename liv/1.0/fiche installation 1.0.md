# Fiche d'installation

Logiciel installé : __Gestion de stock des rouleaux pré-imprégnés v1.0__

Date d'installation : __27/05/2017__

## 1. Modifications de la db

Appliquer dans l'ordre les scripts suivants:
- #63-patch-db.sql
- #50-patch-db.sql
- #66-#67-#68-patch-db.sql

_Notes d'installation:
- le fichier #50-patch-db.sql contient des erreurs (ou lignes signalées comme telles) par MySQL Workbench Windows
- export des données de la db après mise à jour, MySQL Workbench renvoie le warning suivant:
  mysqldump.exe is version 5.7.12, but the MySQL Server to be dumped has version 5.7.17.
  Because the version of mysqldump is older than the server, some features may not be backed up properly.
  It is recommended you upgrade your local MySQL client programs, including mysqldump, to a version equal to or newer than that of the target server.
  The path to the dump tool must then be set in Preferences -> Administrator -> Path to mysqldump Tool:
  10:39:00 Dumping workshapedb (all tables)
  Running: mysqldump.exe --defaults-file="c:\users\benoit\appdata\local\temp\tmpmsawsi.cnf"  --user=workshape --host=workshape-pc --protocol=tcp --port=3306 --default-character-set=utf8 --single-transaction=TRUE --routines --skip-triggers "workshapedb"
  10:39:00 Export of C:\Users\BENOIT\Documents\dumps\Dump20170527.sql has finished
_

## 2. Installation de l'application sur le mobile

_
Notes d'installation:
- téléchargement des Android platform tools pour Windows contenant adb,
- branchement du smartphone par USB après validation des options développeur,
- ! le fichier workshape.apk initialement poussé ne fonctionne pas avec adb. Une nouvelle génération produit un fichier qui fonctionne. Cause indéterminée
_

_Ajout 10/09/2017:
- les fichiers script ont été renommés de façon à être descriptif, ordonnés et liés à une version
