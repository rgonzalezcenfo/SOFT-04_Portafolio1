CREATE
DATABASE db_tennis

USE db_tennis

CREATE TABLE t_administrador
(
    telefono        VARCHAR(15) PRIMARY KEY,
    nombre_completo VARCHAR(50) NOT NULL,
    password        VARCHAR(25) NOT NULL
)

CREATE TABLE t_cliente
(
    telefono        VARCHAR(15) PRIMARY KEY,
    nombre_completo VARCHAR(50) NOT NULL,
    password        VARCHAR(25) NOT NULL

)

CREATE TABLE t_cancha
(
    numero        VARCHAR(5) PRIMARY KEY,
    tipo          VARCHAR(10) NOT NULL,
    precio_hora DOUBLE NOT NULL,
    hora_apertura INT         NOT NULL,
    hora_cierre   INT         NOT NULL
)
INSERT INTO t_cancha VALUES('1', 'AIRE LIBRE', '5000', 6, 16)
INSERT INTO t_cancha VALUES('2', 'AIRE LIBRE', '5000', 6, 16)
INSERT INTO t_cancha VALUES('3', 'AIRE LIBRE', '5000', 6, 16)
INSERT INTO t_cancha VALUES('4', 'TECHADA', '7000', 6, 22)
INSERT INTO t_cancha VALUES('5', 'TECHADA', '7000', 6, 22)
INSERT INTO t_cancha VALUES('6', 'TECHADA', '7000', 6, 22)

CREATE TABLE t_reserva
(
    id            VARCHAR(10) PRIMARY KEY,
    fecha         DATE        NOT NULL,
    hora          INT         NOT NULL,
    numero_cancha VARCHAR(10) NOT NULL,
    FOREIGN KEY (numero_cancha) REFERENCES t_cancha (numero),
    tel_cliente   VARCHAR(15) NOT NULL,
    FOREIGN KEY (tel_cliente) REFERENCES t_cliente (telefono)
)

CREATE TABLE t_evento
(
    id          VARCHAR(10) PRIMARY KEY,
    nombre      VARCHAR(25)  NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    tel_admin   VARCHAR(15)  NOT NULL,
    FOREIGN KEY (tel_admin) REFERENCES t_administrador (telefono)
)

CREATE TABLE t_evento_horario
(
    id            VARCHAR(10) PRIMARY KEY,
    id_evento     VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_evento) REFERENCES t_evento (id),
    numero_cancha VARCHAR(10) NOT NULL,
    FOREIGN KEY (numero_cancha) REFERENCES t_cancha (numero),
    fecha         DATE        NOT NULL,
    hora_inicio   INT         NOT NULL,
    hora_final    INT         NOT NULL
)