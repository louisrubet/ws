# Dev tools installation on kubuntu 16.04

## MySQL 5.7.15

mysql install and service control

> cf http://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/

>```bash
# Installing MySQL with APT
shell> sudo apt-get install mysql-server
# Starting and Stopping the MySQL Server
shell> sudo service mysql status
shell> sudo service mysql stop
shell> sudo service mysql start # start and restart
```

Reset a mysql password if needed

>```bash
shell> sudo su
root> service mysql stop
root> echo "ALTER USER 'root'@'localhost' IDENTIFIED BY 'NEW PWD';" > mypass.sql
root> chown mysql:mysql /tmp/mypass.sql && chmod a+rwx /tmp/mypass.sql
root> mysqld_safe --init-file=/tmp/mypass.sql &
2016-09-25T15:59:28.709774Z mysqld_safe Logging to syslog.
2016-09-25T15:59:28.712856Z mysqld_safe Logging to '/var/log/mysql/error.log'.
2016-09-25T15:59:28.726690Z mysqld_safe Starting mysqld daemon with databases from /var/lib/mysql
root> rm /tmp/mypass.sql
root> exit
```

Enter mysql interpretor
>```bash
shell> mysql -u root -p
```

Create user

>```mysql
mysql> CREATE USER 'workshape'@'localhost' IDENTIFIED BY 'workshape';
Query OK, 0 rows affected (0,00 sec)
mysql> CREATE USER 'workshape'@'%' IDENTIFIED BY 'workshape';
Query OK, 0 rows affected (0,00 sec)
mysql> GRANT ALL PRIVILEGES ON *.* TO 'workshape'@'localhost' with grant option;
Query OK, 0 rows affected (0,00 sec)
mysql> GRANT ALL PRIVILEGES ON *.* TO 'workshape'@'%' with grant option;
Query OK, 0 rows affected (0,00 sec)
mysql> flush privileges;
Query OK, 0 rows affected (0,00 sec)
```

## MySQL Workbench 6.3.6

mysql workbench install
>```bash
shell> sudo apt-get install mysql-workbench
```
