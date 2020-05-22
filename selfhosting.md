# 2009Scape selfhosting guide

## Requirements
You will need:
```
git
gradle
java 8
```

## Getting the code:
Git clone this repository, or download the latest source with the clone or download button

## Building the server:
In a terminal, enter both the Management-Server and Server folders and run `gradle build`

#### Optional: setting the server ip address for non-local servers
In Client/src/main/java/org/runite/Configurations.java set LOCAL_MS and LOCAL_SERVER to false

In Client/src/main/java/org/runite/Client.java set PUBLIC_IP_ADDRESS to your server's ip address

## Building the client:
In a terminal, enter the Client folder and run `gradle build`

## Installing the databases:

### Windows & Linux GUI:
- Download and install [xampp](https://www.apachefriends.org/download.html)
- Start the `Apache` and `MySQL` modules
- Navigate to http://localhost/phpmyadmin/
- Create 2 new databases named `server` and `global`
- Import `Server/server.sql` and `Server/global.sql` into their respective databases
  - _Refer [here](https://www.thecodedeveloper.com/import-large-sql-files-xampp/) for help importing the `.sql` files_

### Linux Command Line
- Get and install mariadb or some other sql compatible database from your repository
- Login and create global & server databases:
Create:
```
CREATE DATABASE server;
CREATE DATABASE global;
```
- Import global & server database contents from the files provided:
```
mysql -u root -p server < server.sql
mysql -u root -p global < global.sql
```

## Running the server/client
In a terminal, navigate to the Management-Server folder and run `gradle run`

Then navigate to the Server folder and run `gradle run`

The client can also be run with `gradle run`. Gradle also generates jars that you can distribute inside Client/build/distributions/Client.zip
