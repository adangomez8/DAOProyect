
# Endpoints API

Por cada entity, se mostrarán los posibles metodos HTTP y su respectiva informacion necesaria.




## Estudiante

#### GET ALL

```http
  GET http://localhost:8080/api/estudiante
```

| Parametro | Type     | Descripcion                |
| :-------- | :------- | :------------------------- |
| `ciudad` | `string` | Ciudad asignada al estudiante |
| `carrera` | `string` | Filtra los estudiantes por carrera |
| `genre` | `string` | Filtra por genero|
| `orderby` | `string` | Ordenamiento disponible por nombre, nroLibreta, genero y ciudad |

#### GET

```http
  GET http://localhost:8080/api/estudiante/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. nroLibreta del estudiante |

#### POST

```http
  POST http://localhost:8080/api/estudiante JSON:
```

| Atributo | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `nroLibreta`      | `string` | **Requerido**. nroLibreta del estudiante |
| `dni`      | `string` | **Requerido**. dni del estudiante |
| `nombre`      | `string` | **Requerido**. nombre del estudiante |
| `apellido`      | `string` | **Requerido**. apellido del estudiante |
| `edad`      | `string` | **Requerido**. edad del estudiante |
| `genero`      | `string` | **Requerido**. genero del estudiante |
| `ciudad`      | `string` | **Requerido**. ciudad del estudiante |


## Carrera

#### GET ALL

```http
  GET http://localhost:8080/api/carrera
```


#### GET

```http
  GET http://localhost:8080/api/carrera/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. id de la carrera |

#### GET REPORTE

```http
  GET http://localhost:8080/api/carrera/reporte
```


#### POST

```http
  POST http://localhost:8080/api/carrera JSON:
```

| Atributo | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. id de la carrera |
| `nombre`      | `string` | **Requerido**. nombre de la carrera |
| `duracion`      | `number` | **Requerido**. años de duracion de la carrera |
| `estudiantes`      | `Estudiante[]` | **Requerido**. Insertar vacio. |


## InfoCarrera

#### GET ALL

```http
  GET http://localhost:8080/api/icarrera
```


#### GET

```http
  GET http://localhost:8080/api/icarrera/{{id}}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. id de la info carrera |




#### POST

```http
  POST http://localhost:8080/api/icarrera JSON:
```

| Atributo | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `carrera`      | `string` | **Requerido**. id de la carrera |
| `graduado`      | `boolean` | **Requerido**. ¿El estudiante se graduado? |
| `estudiante`      | `number` | **Requerido**. nroLibreta del estudiante |
| `antiguedad`      | `Estudiante[]` | **Requerido**. Hace cuanto se anoto. |

