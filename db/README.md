# Installation sur site

## PC Serveur Windows

### Logiciels requis
- Microsoft® SQL Server® 2008 R2 SP2 - Express Edition
Choisir la version française 'with tools' SQLEXPRWT_x64_FRA.exe (375.6 MB)
cf https://www.microsoft.com/fr-fr/download/details.aspx?id=30438
- Android command line tools (android-sdk_r24.4.1-windows.zip, 190 MB)
cf https://developer.android.com/studio/index.html

#### Installation de SQL Server Express 2008
Faire une installation par défaut, c'est-à-dire
- Configuration de l'instance
  - Instance nommée SQLExpress, ID d'instance SQLEXPRESS
- Configuration du serveur
  - Moteur de base de donnée SQL Server: NT Service\MSSQL$SQLEXPRESS, type de démarrage automatique
  - SQL Server Browser: NT AUTHORITY\LOCAL SERVICE, type de démarrage désactivé
- Configuration du moteuer de base de données
  - Mode d'authentification mixte

### Installation de Android command line tools
- Extraire android-sdk_r24.4.1-windows.zip
- Lancer SDK Manager.exe
- Sélectionner uniquement Android SDK Platform-tools
- Autoriser le debogage USB sur le téléphone

## création utilisateur
```mysql
mysql> use mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql>
mysql> INSERT INTO user
    -> (host, user, password,
    -> select_priv, insert_priv, update_priv)
    -> VALUES('localhost', 'workshape', PASSWORD('workshape'), 'Y', 'Y', 'Y');
Query OK, 1 row affected, 3 warnings (0,00 sec)

mysql> FLUSH PRIVILEGES;
Query OK, 0 rows affected (0,00 sec)

mysql> SELECT host, user, password FROM user WHERE user = 'workshape';
+-----------+-----------+-------------------------------------------+
| host      | user      | password                                  |
+-----------+-----------+-------------------------------------------+
| localhost | workshape | *C6625DD7B9F38B0B3A96F694FFAEB1953C945699 |
+-----------+-----------+-------------------------------------------+
1 row in set (0,00 sec)


##
```
