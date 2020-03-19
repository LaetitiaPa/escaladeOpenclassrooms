

## Présentation du projet

Réalisation d'un site web communautaire sur le thème de l'escalade en lien avec le parcours de Développeur d'Application Java d'OpenClassrooms.


## Pré-requis développement

- Java 8
- Maven 3xxx
- SpringBoot (Embeded Tomcat version 2.1.0)
- Base de données MySQL 

## Build

- Maven

	mvn compile
	mvn package

## Paramétrage

application-prod.properties:
- server.port = 8088
- datasource url = jdbc:mysql://localhost:3306/amisescalade
- username = root
- password = 123

## Démarrage de l'application

Utiliser les commandes suivantes:

- Mode dev:
		mvn spring-boot:run
- Mode Prod:
		cd target - java -jar - escladefun.SNAPSHOT.jar

## Utiliser l'application WEB

- URL localhost:8088

![Page d'accueil Escalade](https://github.com/LaetitiaPa/escaladeOpenclassrooms/blob/master/doc/home-escalade.png)

