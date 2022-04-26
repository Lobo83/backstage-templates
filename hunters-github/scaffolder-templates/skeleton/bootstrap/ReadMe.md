#Bootstrapp

En esta carpeta se encuentran herramientas para facilitar el levantado del entorno en local

##docker

En esta carpeta hay un fichero docker-compose que levantará los servicios de infraestructura necesarios para el proyecto en local. Antes de levantarlo, es recomendable activar el WSL 2 en la configuracion local docker
Para utilizarlo hay que ejecutar en consola el comando
```sh 
docker-compose up -d
```
Se levantarán los siguientes servicios:
- consul
- postgres
- mongo
- keycloak (Dependiente de postgres)
- redis
- zookeeper
- kafka (Dependiente de zookeeper)
- zipkin

si no interesan todos basta con lanzar docker-compose y el nombre de los servicios. Por ejemplo: 
```sh 
docker compose up -d consul redis zipkin
```

Y finalmente, para pararlo 
```sh 
docker compose down
```

##Properties

En esta carpeta hay un fichero de properties junto con una macro bat.
Para utilizarlo solo hay que poner las propiedades de los microservicios en el fichero de properties y ejecutar la macro en consola
```sh 
propertiesLoader.bat
```
>Nota: Se requiere que el servicio de consul esté levantado en el puerto 8500 en local