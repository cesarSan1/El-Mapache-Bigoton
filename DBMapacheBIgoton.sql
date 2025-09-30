-- Crear la base de datos
CREATE DATABASE MapacheBigotonBD;
USE MapacheBigotonBD;

-- Tabla CLIENTE
CREATE TABLE CLIENTE (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla SERVICIO
CREATE TABLE SERVICIO (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    servicio VARCHAR(100) NOT NULL,
    descripcion TEXT,
    costo DECIMAL(10,2) NOT NULL
);

-- Tabla AGENDA
CREATE TABLE AGENDA (
    id_agenda INT AUTO_INCREMENT PRIMARY KEY,
    anio INT NOT NULL,
    mes INT NOT NULL
);

-- Tabla CITA
CREATE TABLE CITA (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    id_servicio INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
    FOREIGN KEY (id_servicio) REFERENCES SERVICIO(id_servicio)
);

-- Tabla USUARIO
CREATE TABLE USUARIO (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(35) NOT NULL,
    contrasenia VARCHAR(30) NOT NULL
);
