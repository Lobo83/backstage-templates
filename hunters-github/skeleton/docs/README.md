#Bienvenido al proyecto de ejemplo de la Arquitectura de Referencia!!!

Este es el proyecto base de la Arquitectura de Referencia de Altia.


## Añadir mas microservicios
1. Hay que añadir modulo maven
2. Al pom del modulo hay que hacer que herede de: 
    - parent-poms/microservice/imperative/pom.xml: Para proyectos MVC tradicionales con capacidades reactive 
    - parent-poms/microservice/reactive/pom.xml: Para proyectos puramente reactive (el api gateway es puramente reactive y se recomienda dejarlo así)
3. La versión del pom del modulo tiene que ser parametrica: **\<version\>${example.version}\</version>**
4. Al pom de de parent-poms/parent añadir la version del pom del modulo **\<example.version\>1.0.1\</example.version>**
5. Añadir la construcción del nuevo microservicio al Jenksinfile siguiendo como ejemplo los que ya hay  


##Ejecutar el proyecto
1. Para empezar echa un vistazo a la carpeta **bootstrap** ;)
2. ejecuta ```mvn clean install``` a nivel raiz
3. levanta los micros!