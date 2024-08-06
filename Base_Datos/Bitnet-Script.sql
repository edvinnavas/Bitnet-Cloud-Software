DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
	id_menu BIGINT NOT NULL,
    nombre VARCHAR(500) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id_menu)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
	id_usuario BIGINT NOT NULL,
    nombre_completo VARCHAR(500) NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(200),
    activo BIGINT NOT NULL,
    descripcion TEXT,
    -- id_rol BIGINT NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id_usuario)
    -- CONSTRAINT fk_usuario_1 FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

-- SEGURIDAD.
INSERT INTO menu (id_menu, nombre) VALUES (1,'Roles');
INSERT INTO menu (id_menu, nombre) VALUES (2,'Usuarios');
SELECT m.* FROM menu m;

INSERT INTO usuario (id_usuario, nombre_completo, nombre_usuario, contrasena, correo_electronico, activo, descripcion) 
VALUES (1, 'Edvin Francisco Navas Mejía', 'enavas', SHA2('Edfra2023', 512), 'edvin.navas@gmail.com', 1, 'Usuario administrador del sistema.');
INSERT INTO usuario (id_usuario, nombre_completo, nombre_usuario, contrasena, correo_electronico, activo, descripcion) 
VALUES (2, 'Alejandro Figueroa', 'afigueroa', SHA2('L3xAF1g2023', 512), 'a.figueroa@lexcom.gt', 1, 'Usuario administrador de la aplicación.');
INSERT INTO usuario (id_usuario, nombre_completo, nombre_usuario, contrasena, correo_electronico, activo, descripcion) 
VALUES (3, 'Juan José Figueroa', 'jfigueroa', SHA2('L3xJF1g2023', 512), 'j.figueroa@lexcom.gt', 1, 'Usuario administrador de la aplicación.');
SELECT u.* FROM usuario u;
SELECT u.* FROM usuario u WHERE u.nombre_usuario='enavas' AND TRIM(CONVERT(u.contrasena USING UTF8MB4)) = TRIM(SHA2('Edfra2023', 512));

-- CATÁLOGOS.

-- GESTIÓN.

COMMIT;