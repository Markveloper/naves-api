# Naves API

Este repositorio contiene el codigo fuente para una API que proporciona informacion sobre diversas naves espaciales de peliculas y series.

## Descripcion

Esta API permite obtener informacion como nombre y series de fabricacion de una nave espacial.

## Caracteristicas

- Obtener una lista de todas las naves espaciales con paginacion.
- Consultar una nave espacial por su ID.
- Buscar naves espaciales por nombre.
- Añadir, actualizar y eliminar naves espaciales.

## Uso

Para lanzar el proyecto, primero asegurate de tener Maven y Java instalados. Luego, ejecuta los siguientes comandos:

1. Limpia el proyecto:
    ```sh
    mvn clean
    ```
2. Inicia la aplicacion:
    ```sh
    mvn spring-boot:run
    ```

Para lanzar el proyecto mediante docker asegurate de tener docker instalado:

1. En el directorio raiz del proyecto:
    ```sh
    mvn clean install
    ```

2. En el directorio raiz del proyecto:
    ```sh
    sudo docker build -t spring-boot-naves-docker:naves-docker .
    ```
2. Inicia la imagen docker:
    ```sh
    sudo docker run -p 8080:8080 spring-boot-naves-docker:naves-docker .
    ```

A continuacion se presentan ejemplos de como interactuar con la API.

### Obtener lista de las naves espaciales con paginacion

```sh
GET /api/naves?page=0&size=10&sort=id,asc
```

### Obtener una nave espacial por ID

```sh
GET /api/naves/{id}
``` 

### Buscar naves espaciales por nombre

```sh
GET /api/naves/search?name={name}
``` 

### Añadir una nueva nave espacial

```sh
POST /api/naves
{
  "name": "Example",
  "series": "Example"
}
``` 

### Actualizar una nave espacial existente

```sh
PUT /api/naves/{id}
{
  "name": "Example",
  "series": "Example"
}
``` 

### Eliminar una nave espacial

```sh
DELETE /api/naves/{id}
``` 