USE db_bitnet;

DROP TABLE IF EXISTS PROPIEDAD;
CREATE TABLE PROPIEDAD (
	ID_PROPIEDAD BIGINT NOT NULL,
    NOMBRE VARCHAR(200) NOT NULL,
    VALOR VARCHAR(200) NOT NULL,
    CONSTRAINT PK_PROPIEDAD PRIMARY KEY (ID_PROPIEDAD)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS TIPO_MENU;
CREATE TABLE TIPO_MENU (
	ID_TIPO_MENU BIGINT NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL,
    ACTIVO INTEGER NOT NULL,
    DESCRIPCION TEXT,
    USUARIO_M VARCHAR(100) NOT NULL, 
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_TIPO_MENU PRIMARY KEY (ID_TIPO_MENU)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS MENU;
CREATE TABLE MENU (
	ID_MENU BIGINT NOT NULL,
    ID_TIPO_MENU BIGINT NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL,
    ACTIVO INTEGER NOT NULL,
    DESCRIPCION TEXT,
    USUARIO_M VARCHAR(100) NOT NULL, 
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_MENU PRIMARY KEY (ID_MENU),
    CONSTRAINT FK_MENU_1 FOREIGN KEY (ID_TIPO_MENU) REFERENCES TIPO_MENU (ID_TIPO_MENU)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS ROL;
CREATE TABLE ROL (
	ID_ROL BIGINT NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL,
    ACTIVO INTEGER NOT NULL,
    DESCRIPCION TEXT,
    USUARIO_M VARCHAR(100) NOT NULL, 
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_ROL PRIMARY KEY (ID_ROL)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS ROL_MENU;
CREATE TABLE ROL_MENU (
	ID_ROL BIGINT NOT NULL,
    ID_MENU BIGINT NOT NULL,
    USUARIO_M VARCHAR(100) NOT NULL, 
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_ROL_MENU PRIMARY KEY (ID_ROL, ID_MENU),
    CONSTRAINT FK_ROL_MENU_1 FOREIGN KEY (ID_ROL) REFERENCES ROL (ID_ROL),
    CONSTRAINT FK_ROL_MENU_2 FOREIGN KEY (ID_MENU) REFERENCES MENU (ID_MENU)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS USUARIO;
CREATE TABLE USUARIO (
    ID_USUARIO BIGINT NOT NULL,
    NOMBRE_COMPLETO VARCHAR(500) NOT NULL,
    NOMBRE_USUARIO VARCHAR(50) NOT NULL,
    CONTRASENA VARCHAR(255) NOT NULL,
    CORREO_ELECTRONICO VARCHAR(200),
    ACTIVO INTEGER NOT NULL,
    AUTENTICACION VARCHAR(10) NOT NULL,
    DESCRIPCION TEXT,
    ID_ROL BIGINT NOT NULL,
    USUARIO_M VARCHAR(100) NOT NULL, 
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO),
    CONSTRAINT FK_USUARIO_1 FOREIGN KEY (ID_ROL) REFERENCES ROL (ID_ROL)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS TIPO_EVENTO;
CREATE TABLE TIPO_EVENTO (
	ID_TIPO_EVENTO BIGINT NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL,
    ACTIVO INTEGER NOT NULL,
    DESCRIPCION TEXT,
    USUARIO_M VARCHAR(100) NOT NULL, 
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_TIPO_EVENTO PRIMARY KEY (ID_TIPO_EVENTO)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

DROP TABLE IF EXISTS LOG_EVENTO;
CREATE TABLE LOG_EVENTO (
	ID_EVENTO BIGINT NOT NULL,
    ID_TIPO_EVENTO BIGINT NOT NULL,
    ID_USUARIO BIGINT NOT NULL,
    DESCRIPCION TEXT NOT NULL,
    FECHA_HORA TIMESTAMP NOT NULL,
    CONSTRAINT PK_LOG_EVENTO PRIMARY KEY (ID_EVENTO, ID_TIPO_EVENTO, ID_USUARIO),
    CONSTRAINT FK_LOG_EVENTO_1 FOREIGN KEY (ID_TIPO_EVENTO) REFERENCES TIPO_EVENTO (ID_TIPO_EVENTO),
    CONSTRAINT FK_LOG_EVENTO_2 FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO)
) ENGINE = INNODB DEFAULT CHARACTER SET = UTF8MB4;

-- SELECT A.* FROM PROPIEDAD A;
-- SELECT A.* FROM TIPO_MENU A;
-- SELECT A.* FROM MENU A;
-- SELECT A.* FROM ROL A;
-- SELECT A.* FROM ROL_MENU A;
-- SELECT A.* FROM USUARIO A;
-- SELECT A.* FROM TIPO_EVENTO A;
-- SELECT A.* FROM LOG_EVENTO A;

-- DELETE FROM TIPO_MENU WHERE ID_TIPO_MENU > 0;
-- DELETE FROM MENU WHERE ID_MENU > 0;
-- DELETE FROM ROL WHERE ID_ROL > 0;
-- DELETE FROM ROL_MENU WHERE ID_ROL > 0;
-- DELETE FROM USUARIO WHERE ID_USUARIO > 0;
-- DELETE FROM TIPO_EVENTO WHERE ID_TIPO_EVENTO > 0;
-- DELETE FROM LOG_EVENTO WHERE ID_EVENTO > 0;

-- COMMIT;