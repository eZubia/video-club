## **Video Club** ##

Proyecto creado con el fin de solicionar un problema dado en la clase de Diseño de Bajo Nivel de la UACH.

<h4>Planteamiento del Problema</h4>

Se desea diseñar una base de datos relacional que almacene la información sobre los préstamos de las películas de un vídeo club.
 En la actualidad la gestión de esta información se lleva cabo del siguiente modo:

Cuando se hace un **préstamo** se rellena una ficha en la que se anota el socio que se lleva la película, la fecha y el número de la cinta que se lleva, que es único (de cada película hay varias copias en cintas
distintas). Esta ficha se deposita en el archivador de películas prestadas. 

Cuando el socio devuelve la cinta, la ficha se pasa al archivador de películas devueltas. El vídeo club tiene, además, un archivador con fichas de películas ordenadas por título; cada ficha tiene además el género de la película (comedia, terror, ...), su director y los nombres de los actores que intervienen. 

También se tiene un archivador con las fichas de los
socios, ordenadas por el código que el vídeo club les da cuando les hace el carnét; cada ficha tiene el nombre del socio, su dirección y teléfono, los nombres de sus directores favoritos, los nombres de sus actores favoritos y los géneros cinematográficos de su preferencia.

 Cuando un socio quiere tomar prestada una película de la que no hay copias disponibles, se le puede anotar en la lista de espera de esa película. 

Cada vez que se devuelve una película, se comprueba si hay alguien en su lista de espera, y si es así se llama por teléfono al primer socio de la lista para decirle que ya puede pasar a recogerla, borrándolo después de la lista.

<h4> Diagrama de procesos </h4>
En base a la descripción se pudieron identificar tres procesos principales, mostrados en el siguiente diagrama.
![diagramaprocesosimagen](https://cloud.githubusercontent.com/assets/10780058/18737094/c9fe0e6c-804a-11e6-810b-d2c4c819ab20.png)

Que vienen siendo el prestamo al socio, que en si es la acción principal del sistema, después generar una entrega cuando el socio devuelva la cinta, terminando con el chequeo de la lista de espera.

<h4>Diagrama Entidad-Relacion</h4>
Se encontraron un total de 7 entidades principales para el funcionamiento de los procesos.

> **Director**
> **Socio**
> **Película**
> **Ficha**
> **Cintas**
> **Actor**
> **Cinta**

![diagramaer](https://cloud.githubusercontent.com/assets/10780058/18737317/ff7fac2e-804c-11e6-99c2-6c090705ae1a.png)
