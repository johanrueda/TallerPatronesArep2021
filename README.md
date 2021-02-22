# Taller de Arquitecturas de servidores de Aplicaciones, Meta protocolos de objetos, Patrón IoC, Reflexión

# Funcionalidades
En este taller implemamos el Framework en version minima de Spring, donde utilizamos diferentes herramientas como protoclos de objetos y otros.


## Prerequisitos del sistemas
* Maven
* Git
* Java
* Heroku

## Descarga,instalacion y ejecución
Primero debemos clonar el repositorio, como veremos en el siguiente comando:

**git clone https://github.com/johanrueda/TallerPatronesArep2021.git**

Ahora ejecutamos una consola de comandos en el directorio donde fue clonado el repositorio y compilar el proyecto con el siguiente comando:

**mvn package**

Luego ejecutamos el programa con el siguiente comando:

**java -cp target/patronesReflexion-1.0-SNAPSHOT.jar edu.escuelaing.arem.app.nanoSpark.MicroSpringBoot**

de esta manera la aplicacion corre localmente, en tu navegador favorito colocas localhost:36000

Pero tambien dicha aplicacion tiene un despliegue en heroku que puedes utilizar en cualquier momento.

## Uso

Despues de que el programa este corriendo localmente podemos verificar en el navegador con las siguientes URLS
localhost:36000/index.html
localhost:36000/Apps/hello

## Documentación

Para generar la documentación de Java Doc ejecute el siguiente comando:

**mvn javadoc:javadoc**

## Desarrollo

Este proyecto se desarrollo con:
* Maven
* Java 8
* Intellij IDEA
* Heroku

## Autor

**Johan David Rueda Rodriguez**

## Licencia
Este proyecto lo contiene la licencia GNU GENERAL PUBLIC LICENSE.