# Bitnet-Cloud-Software
Bitnet-Cloud-Software

docker run -d --name portainer \
           -p 8000:8000 -p 9443:9443 \
           --restart=always \
           -v /var/run/docker.sock:/var/run/docker.sock \
           -v portainer_data:/data \ 
           --env TZ=America/Guatemala \ 
           portainer/portainer-ce:latest

docker network create bitnet_network

docker run -d --name BITNET-MYSQL --network bitnet_network -p 3306:3306 --restart=always --env MYSQL_ROOT_PASSWORD=bitnetadmin --env TZ=America/Guatemala --volume "C:\VolumenDocker\BitnetMysql":/var/lib/mysql mysql:latest

mysql> CREATE DATABASE db_jasperserver;
mysql> CREATE DATABASE db_bitnet;
mysql> CREATE USER 'user_jasperserver'@'%' IDENTIFIED BY 'bitnetadmin';
mysql> CREATE USER 'user_bitnet'@'%' IDENTIFIED BY 'bitnetadmin';	
mysql> GRANT ALL ON db_jasperserver.* TO 'user_jasperserver'@'%';
mysql> GRANT ALL ON db_bitnet.* TO 'user_bitnet'@'%';

docker run -d --name BITNET-JASPERSERVER --network bitnet_network --restart=always -p 9010:8080 -p 8443:8443 --env JASPERREPORTS_USERNAME=bitnet --env JASPERREPORTS_PASSWORD=bitnetadmin --env JASPERREPORTS_EMAIL=edvin.navas@gmail.com --env JASPERREPORTS_DATABASE_TYPE=mysql --env JASPERREPORTS_DATABASE_HOST=BITNET-MYSQL --env JASPERREPORTS_DATABASE_NAME=db_jasperserver --env JASPERREPORTS_DATABASE_USER=user_jasperserver --env JASPERREPORTS_DATABASE_PASSWORD=bitnetadmin --volume "C:\VolumenDocker\BitnetJasperServer":/bitnami/jasperreports --env TZ=America/Guatemala bitnami/jasperreports:latest

C:\Users\sied_\Maven\apache-maven-3.9.2\bin\mvn archetype:generate -DarchetypeGroupId=org.glassfish.jersey.archetypes -DarchetypeArtifactId=jersey-quickstart-grizzly2 -DarchetypeVersion=3.1.2 -DgroupId=com.bitnet -DartifactId=bitnet-rest-api -Dversion=1.0-SNAPSHOT

docker build -t edvinnavas/bitnet-rest-api:v.0.1 .
docker run -t -i --name BITNET-REST-API --network bitnet_network -p 9011:8080 --restart=always --env TZ=America/Guatemala edvinnavas/bitnet-rest-api:v.0.1

docker run --name BITNET-PAYARA --network bitnet_network -p 9012:8080 -p 4848:4848 --restart=always --env TZ=America/Guatemala payara/server-full:6.2023.6