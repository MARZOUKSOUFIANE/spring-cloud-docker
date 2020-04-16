# Chaos engineering architecture
 
l'architecture représente une app de test pour le chaos engineering ou les tests de résilience.

## Composants de l'architecture 

le projet suit une architecture distribuée basée sur des microservices Spring / Spring Boot et Spring Cloud afin de simuler et rapprocher 
le maximum des socles existant, et bien comprendre le fonctionnement des tests de résilience sur ce genre d'architecture.

le projet se compose des éléments suivants:

#### Microservices edges

`- Spring Cloud Config.`

Ce microservice permet de centraliser les configurations du projet. Cela dans le but de rendre plus aisé sa maintenance.
Il permet de centraliser tous les fichiers de configuration dans un [dépôt GIT](https://github.com/MARZOUKSOUFIANE/conf) 
Ainsi, quand nous mettons à jour un fichier de configuration dans le dépôt GIT, ce microservice se met à servir la nouvelle
version, obligeant les autres Microservices concernés à la prendre en compte à la volée.

`- Spring Cloud Netflix Eureka.`
 
C'est un microservice annuaire qui contient des informations sur les différents microservices de l’application pour faciliter la communication entre ces derniers.
Les services s'enregistrent auprès de l'annuaire,Cela simplifie grandement les communications, car il suffit juste de connaitre le nom du service voulu pour pouvoir le consulter.

`- Spring Cloud Zull Proxy & Spring Cloud Gateway.`

C'est les points d'entrée (passerelle vers l'extérieur), le client du système contacte toujours l'adresse de l’un de ces serveur (Zuul ou Spring
cloud Gateway). Ces derniers re-routent ensuite les requêtes vers les services adéquats.L'intérêt est donc la simplification de l'accès par le client,

`- Spring Boot Admin.`

Un microservice offrant une interface utilisateur qui facilite la gestion et le monitoring des microservice Spring Boot en se basant sur les Endpoints de Spring Boot Actuator.
Les microservices s'enregistrent auprès de Spring Boot Admin via Spring Cloud Eureka. 

`- Zipkin.`

Service qui permet de suivre les requêtes de Microservice en Microservice et de consulter les différentes réponses et interactions afin de cibler directement les éventuels problèmes.

`- Hystrix Dashboard.`

Pour pouvoir surveiller les paramètres et les métriques Hystrix de l’application en temps réel.

***

#### ELK (Elasticsearch, Logstash, Kibana)

`- Logstash`

Pour collecter en permanence les logs de l'application à partir un fichier de log et les traiter (pipeline logstash) avant de les indexer dans elasticsearch.

`- Elasticsearch`

Pour indexer et stocker les logs fournis par logstash, ainsi que d'alimenter kibana par ces logs.

`- Kibana`

Pour mieux visualiser,centraliser et analyser les logs de toute l'application en cas de problèmes.

***

#### Microservices métier

Trois microservices qui communiquent entre eux dans un premier temps graçe à des api rest **(OpenFeign)**
Le Role ici c'est pas d'établir une approche métier trés avancée, mais l'objectif c'est de créer des microservices fonctionnels qui communiquent entre eux, car ce qui nous intéresse en premier lieu c'est l'interopérabilité entre ses microservices

`- Microservice Produits`

Un microservice qui se charge de gérer des produits et les stockent dans une base de données `MySQL`

`- Microservice Commandes`

Un microservice qui se charge de gérer des commandes et les stockent dans une base de données `H2`

`- Microservice Paiement`

Un microservice qui se charge de gérer des paiements et les stockent dans une base de données `H2`


 <img src="https://drive.google.com/uc?export=view&id=1Tp2dEXLQK7pNc6BOVuhlneCL21atsaz2" width="500" height="500" />

***

#### Microservices clients

`- Zuul Proxy Client`

Un microservice Spring MVC coté serveur avec thymeleaf.
Ce microservice consomme les autres microservices en passant par Zull Proxy.

`- Spring Cloud Gateway Client`

Un microservice Spring MVC coté serveur avec thymeleaf.
Ce microservice consomme les autres microservices en passant par Spring Cloud Gateway.

> L'objectif de créer deux microservices clients un pour Zuul et l'autre pour Spring Cloud Gateway c'est d'avoir la possibilité de tester les deux types de proxies et leur fonctionnement.

***


## Utilisation

`- Avec Docker-compose`

cette version du projet intègre **Docker** (`Dockerfile` et de `docker-compose`).
Pour utiliser le projet il faut procéder par:

 - 1. Cloner le projet : ` git clone https://gitlab.com/soufianeMARZOUK/chaos-engineering-architecture.git `
 
 - 2. Accéder au projet et généner les build de tous les microservices à la fois graçe à la commande ` mvn clean package ` 
 

> NB: le projet contient un pom.xml parent déclarant tous les microservices, ce qui permet de générer les build d'un seul coup avec ` mvn clean package ` 

 - 3. Lancer l'architecture avec docker-compose ` docker-compose up`

> NB: la version dockerisée du projet lance les microservices avec le profile Spring `docker` afin de préserver la configuration normale et la configuration relative au docker, ce profile se lance automatiquement au moment du build des images docker. 

Pour visualiser les logs il faut utiliser la stack ELK:

- créer un fichier de logs et mapper les logs vers ce ficheir `docker-compose logs > [path <file>.log]`
- télécharger elasticsearch et la lancer graçe à la commande `./bin/elasticsearch`
- télécharger logstash , et créer un fichier `logstash.conf`( [exemple](https://gitlab.com/soufianeMARZOUK/chaos-engineering-architecture/-/blob/master/Docker-ELK/logstash/pipeline/logstash.conf) )  qui contient le pipeline logstash(input, filter, output) et la lancer logstash graçce à la commande `./bin/logstash -f logstash.conf`
- télécharger kibana et la lancer graçe à la commande `./bin/kibana`, et consulter `http://localhost:5601` pour visualiser les logs.


   <img src="https://drive.google.com/uc?export=view&id=1QTT0RvbkNyjNX6nmnIQxoB6AXnnSnW7y" width="800" height="600" />


> Le projet contient aussi la version de ELK avec docker , il suffit juste d'accéder au dossier `Docker-elk` et lancer `docker-compose up` 



`- Sans Docker-compose`

Pour utiliser le projet manuellement il faut procéder  par:

 - 1. Cloner le projet : ` git clone https://gitlab.com/soufianeMARZOUK/chaos-engineering-architecture.git `
 - 2. Sur `intellij` créer un projet `project from existing sources`.
 

   <img src="https://user.oc-static.com/upload/2019/02/04/15492755217013_image10.png" width="500" height="500" />


 - 3.Importer les differents microservices comme étant des modules pour travailler sur la même fenêtre et éviter de se balader entre plusieurs fenêtres. 
    

    <img src="https://user.oc-static.com/upload/2019/02/04/15492757357983_image22.png" width="500" height="500" />


- 4. Lancer chaque microservice en commençant par le microservice de conf.

