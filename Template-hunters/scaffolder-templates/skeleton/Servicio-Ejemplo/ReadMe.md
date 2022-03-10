# Example-Service
##  Servicio no productivo, propósito puramente de muestra

 

Este proyecto sive de muestra para ver como se deben levantar los microservicios

Es un proyecto WEB-MVC con capacidades reactive (Incorpora WEBFLUX).

Si interesa un proyecto fully Reactive entonces se debe extender en el POM de:
```sh
 <parent>
    <groupId>com.altia</groupId>
    <artifactId>imperative-microservice-pom</artifactId>
    <version>1.0.0</version>
    <relativePath>../parent-poms/microservice/imperative</relativePath>
  </parent>
````  
> Nota: los clientes Feign no funcionan con este pom ya que los feign son bloqueantes y reactive es por definicion NO bloqueante

Cuando hayas levantado el proyecto pincha ve a http://localhost:8081/entity/reactive para ver un ejemplo reactive 
(Swagger es bloqueante por tanto mostrará todo una vez se haya descargado)