
# Endpoints API

Por cada entity, se mostrarán los posibles metodos HTTP y su respectiva informacion necesaria.




## Estudiante

#### GET ALL

```http
  GET http://localhost:8080/api/estudiante
  EJEMPLO GET http://localhost:8080/api/estudiante?ciudad=New York&carrera=Ciencias de la Comunicación&genre=Masculino
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

# CODIGO SQL DE PRUEBA:

INSERT INTO `estudiante` (`nro_libreta`, `apellido`, `ciudad`, `dni`, `edad`, `genero`, `nombre`)
VALUES
('5555', 'Guidiiiii', 'Tandil', '44455588', '54', 'Masculino', 'Josesito'),
('1234', 'Lopez', 'Buenos Aires', '98765432', '21', 'Femenino', 'Maria'),
('9876', 'Smith', 'New York', '12345678', '30', 'Masculino', 'John'),
('5678', 'García', 'Madrid', '11112222', '25', 'Femenino', 'Sofía'),
('4321', 'Chen', 'Shanghai', '88887777', '22', 'Masculino', 'Li'),
('7654', 'Kowalski', 'Warsaw', '33334444', '28', 'Femenino', 'Anna'),
('2345', 'Gomez', 'Barcelona', '55556666', '24', 'Masculino', 'Carlos'),
('8765', 'Brown', 'Los Angeles', '99998888', '29', 'Femenino', 'Emma'),
('6789', 'Martinez', 'Buenos Aires', '77776666', '27', 'Masculino', 'Juan'),
('3210', 'Lee', 'Seoul', '22223333', '23', 'Femenino', 'Hyejin');


INSERT INTO `carrera`(`id`, `duracion`, `nombre`) VALUES
('11', '4 años', 'Biología'),
('12', '3 años', 'Ciencias de la Comunicación'),
('13', '5 años', 'Medicina'),
('14', '4 años', 'Ingeniería Informática'),
('15', '3 años', 'Psicología'),
('16', '4 años', 'Derecho'),
('17', '5 años', 'Ingeniería Eléctrica'),
('18', '4 años', 'Administración de Empresas'),
('19', '3 años', 'Economía'),
('20', '2 años', 'Arte y Diseño Gráfico');

-- Inserción de datos en la tabla de relación info_carrera
INSERT INTO `info_carrera`(`id`, `antiguedad`, `graduado`, `id_carrera`, `id_estudiante`) VALUES
('1', '3 años', TRUE, '14', '5555'),
('2', '4 años', TRUE, '13', '1234'),
('3', '2 años', FALSE, '12', '9876'),
('4', '3 años', TRUE, '18', '5678'),
('5', '2 años', FALSE, '15', '4321'),
('6', '4 años', TRUE, '16', '7654'),
('7', '3 años', TRUE, '17', '2345'),
('8', '4 años', TRUE, '11', '8765'),
('9', '2 años', FALSE, '20', '6789'),
('10', '4 años', TRUE, '19', '3210');
