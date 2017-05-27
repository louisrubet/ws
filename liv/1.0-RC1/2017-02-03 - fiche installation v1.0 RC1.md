# Fiche d'installation

Logiciel installé : __Gestion de stock des rouleaux pré-imprégnés v1.0 RC1__

Date d'installation : __03/02/2017__

## 1. Infrastructure matérielle

Serveur WORKSHAPE-PC, IP 192.168.0.15, 60 04 6A 61 FA 2A, port 3306

Smartphone BlackView BV6000, préinstallé avec le logiciel

Wifi marlene, 73albator*

## 2. Installation du serveur

#### Installation préalable requise
Microsoft Visual C++ 2013 Runtime 64 bits  vcredist_x64.exe disponible à [ce lien](https://www.microsoft.com/fr-FR/download/details.aspx?id=40784)

#### Installation de MySQL Community Server 5.7.17

Disponible à [ce lien](https://dev.mysql.com/downloads/mysql/)

Choisir l'option Custom et installer
- MySQL Server 5.7.17 - X64
- MySQL Workbench 6.3.8 - X64

#### Configuration MySQL Server
Config type: Server Machine, TCP/IP, Port Number 3306, Open firewall port

root password: workshape, workshape

add user: workshape, workshape, DB Admin

Configure MySQL as Windows service: yes, Windows service name: MySQL57, Start at System
startup:yes, Standard system account

MySQL as doc store: no

#### Configuration MySQL Workbench

Setup new connection: workshape, Standard TCP/IP, 127.0.0.1, 3306, workshape

## 3. Installation du Smartphone

Logiciel v1.0 RC1 pré-installé par Louis via Android Studio sur le Smartphone BV6000
