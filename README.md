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

C'est un microservice annuaire qui contient des informations sur les différents microservices del’application pour faciliter la communication entre ces derniers.
Les services s'enregistrent auprès de l'annuaire,Cela simplifie grandement les communications, car il suffit juste de connaitre le nom du service voulu pour pouvoir le consulter.

`- Spring Cloud Zull Proxy & Spring Cloud Gateway.`

C'est les points d'entrée (passerelle vers l'extérieur), le client du système contacte toujours l'adresse de l’un de ces serveur (Zuul ou Spring
cloud Gateway). Ces derniers re-routent ensuite les requêtes vers les services adéquats.L'intérêt est donc la simplification de l'accès par le client,

`- Spring Boot Admin.`

Un microservice offrant une interface utilisateur qui facilite la géstion et le monitoring des microservice Spring Boot en se basant sur les Endpoints de Spring Boot Actuator.
Les microservices s'enregistrent auprès de Spring Boot Admin via Spring Cloud Eureka. 

`- Hystrix Dashboard.`

Pour pouvoir surveiller les paramètres et les métriques Hystrix de l’application en temps réel.

***

#### Services métier

Trois microservices qui communiquent entre eux dans un premier temps graçe à des api rest **(OpenFeign)**
Le Role ici c'est pas d'établir une approche métier trés avancée, mais l'objectif c'est de créer des microservices fonctionnels qui communiquent entre eux, car ce qui nous intéresse en premier lieu c'est l'interopérabilité entre ses microservices

`- Microservice Produits`

Un microservice qui se charge de gére des produits et les stockent dans une base de données `MySQL`

`- Microservice Commande`

Un microservice qui se charge de gére des commande et les stockent dans une base de données `H2`

`- Microservice Paiement`

Un microservice qui se charge de gére des commande et les stockent dans une base de données `H2`


![alt text](https://drive.google.com/file/d/1Tp2dEXLQK7pNc6BOVuhlneCL21atsaz2/view)



#### Services clients

`- Zuul Proxy Client`

Un microservice Spring MVC coté serveur avec thymeleaf.
Ce microservice consomme les autres microservices en passant par Zull Proxy.

`- Spring Cloud Gateway Client`

Un microservice Spring MVC coté serveur avec thymeleaf.
Ce microservice consomme les autres microservices en passant par Spring Cloud Gateway.

** l'objectif de créer deux microservices clients un pour Zuul et l'autre pour Spring Cloud Gateway c'est d'avoir la possibilité de tester les deux types de proxies et leur fonctionnement.



## Utilisation

cette version du projet contient juste les 'Dockerfile' et pas de 'docker-compose'.
Pour utiliser le projet il faut procéder manuellement par:

 - 1. Cloner le projet : ** git clone https://gitlab.com/soufianeMARZOUK/chaos-engineering-architecture.git **
 - 2. Sur `intellij` créer un projet `project from existing sources`.
 - 
  ![alt text](https://user.oc-static.com/upload/2019/02/04/15492755217013_image10.png)

 - 3. Importer les differents microservices comme étant des modules pour travailler sur la même fenêtre et éviter de se balader entre plusieurs fenêtres. 
 
 
   ![alt text](https://user.oc-static.com/upload/2019/02/04/15492757357983_image22.png)

   ![alt text](https://user.oc-static.com/upload/2019/02/04/15492757357983_image22.png)
   
- 4. Lancer chaque microservice en commençant le microservice de conf.