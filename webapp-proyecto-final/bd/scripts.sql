CREATE DATABASE proyecto_domicilios;

use proyecto_domicilios;

-- Creación tabla productos
create table `productos`
(
    `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `nombre`       VARCHAR(100) not null,
    `precio`       DOUBLE       null,
    `categoria_id` BIGINT,
    primary key (`id`),
    CONSTRAINT `fk_categoria_id` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- Creación tabla categorias

create table `categorias`
(
    `id`     bigint(20)   NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(100) not null,
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- Creación tabla usuarios

create table `usuarios`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(100) not null,
    `password` VARCHAR(100) not null,
    `email`    VARCHAR(255) null,
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE proyecto_domicilios.usuarios ADD COLUMN rol VARCHAR(100);

-- Creación tabla pedido

CREATE TABLE pedidos
(
    id                BIGINT(20)   NOT NULL AUTO_INCREMENT,
    fecha_generacion  DATETIME     NOT NULL,
    total             DOUBLE       NOT NULL,
    estado            VARCHAR(40)  NULL,
    nombre_cliente    VARCHAR(100) NULL,
    direccion_cliente VARCHAR(255) NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


ALTER TABLE proyecto_domicilios.pedidos
    ADD COLUMN tiempo_espera_oferta DOUBLE NOT NULL;

-- Creación tabla producto_pedido

CREATE TABLE productos_pedido
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    producto_id BIGINT(20) NOT NULL,
    pedido_id   BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_producto_id` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
    CONSTRAINT `fk_pedido_id` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE productos_pedido
    ADD COLUMN cantidad DOUBLE NOT NULL;

-- Creación de tabla proveedor

create table `proveedores`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
    `nombre`          VARCHAR(100) not null,
    `numero_telefono` VARCHAR(100) not null,
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- Creación de tabla ofertas_entrega_pedido

create table ofertas_entrega_pedido
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `proveedor_id`    bigint     not null,
    `pedido_id`       bigint     not null,
    `valor_domicilio` double     not null,
    `tiempo_entrega`  double     not null,
    primary key (`id`),
    CONSTRAINT `fk_oferta_proveedor_id` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`id`),
    CONSTRAINT `fk_oferta_pedido_id` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE ofertas_entrega_pedido
    ADD COLUMN estado VARCHAR(100);

-- Creación tabla stock producto
create table productos_stock
(
    id          bigint auto_increment,
    producto_id bigint not null,
    cantidad    double not null,
    constraint producto_stock_pk
        primary key (id),
    constraint fk_stock_producto_id FOREIGN KEY (`producto_id`) REFERENCES `productos` (id)
);

-- Inserts categorias

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Acompañamientos');

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Platos a la carta');

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Sopas');

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Arroces');

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Combos');

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Bebidas');

INSERT INTO proyecto_domicilios.categorias (nombre)
VALUES ('Pollo');

-- Inserts Productos

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('1 Pollo Broaster', 25000, 7);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('1/2 Pollo Broaster', 13000, 7);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('1/4 Pollo Broaster', 6500, 7);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('3/4 Pollo Broaster', 19500, 7);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Porción papa francesa', 3000, 1);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Porción papa salada', 3000, 1);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Porción yuca', 3000, 1);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Porción patacón', 3000, 1);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Bandeja Broaster', 12500, 2);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Bandeja Asado', 12500, 2);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Arroz con pollo', 13000, 2);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Sopa', 5000, 3);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Media Sopa', 3000, 3);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Combo familiar', 38000, 5);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Arroz pareja', 24000, 4);

INSERT INTO proyecto_domicilios.productos (nombre, precio, categoria_id)
VALUES ('Arroz familiar', 40000, 4);

-- Insert pedidos

INSERT INTO proyecto_domicilios.pedidos (fecha_generacion, total, estado, nombre_cliente, direccion_cliente)
VALUES ('2022-07-10 15:24:36', 28000, 'Pendiente', 'Pascual Rodríguez', 'Calle 7');

INSERT INTO proyecto_domicilios.pedidos (fecha_generacion, total, estado, nombre_cliente, direccion_cliente)
VALUES ('2022-07-10 15:28:01', 19000, 'Pendiente', 'Juan Perez', 'Carrera 10');

INSERT INTO proyecto_domicilios.pedidos (fecha_generacion, total, estado, nombre_cliente, direccion_cliente)
VALUES ('2022-07-10 15:39:18', 34000, 'Pendiente', 'Juan Avila', 'Tv 23');

-- Insert productos pedido

INSERT INTO proyecto_domicilios.productos_pedido (producto_id, pedido_id, cantidad)
VALUES (1, 1, 1);

INSERT INTO proyecto_domicilios.productos_pedido (producto_id, pedido_id, cantidad)
VALUES (5, 1, 1);

INSERT INTO proyecto_domicilios.productos_pedido (producto_id, pedido_id, cantidad)
VALUES (2, 2, 1);

INSERT INTO proyecto_domicilios.productos_pedido (producto_id, pedido_id, cantidad)
VALUES (5, 2, 1);

INSERT INTO proyecto_domicilios.productos_pedido (producto_id, pedido_id, cantidad)
VALUES (12, 3, 2);

INSERT INTO proyecto_domicilios.productos_pedido (producto_id, pedido_id, cantidad)
VALUES (15, 3, 1);

-- Insert stock
INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (1, 300);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (2, 100);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (3, 120);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (4, 80);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (5, 400);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (6, 150);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (7, 110);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (8, 100.5);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (9, 40);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (10, 38);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (11, 65);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (12, 80.5);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (13, 25.5);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (14, 62);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (15, 85.5);

INSERT INTO proyecto_domicilios.productos_stock (producto_id, cantidad)
VALUES (16, 45.5);

-- Queries

SELECT pr.nombre, pr.categoria_id, pe.nombre_cliente
FROM productos pr,
     pedidos pe,
     productos_pedido pp
WHERE pp.pedido_id = 3
  AND pp.pedido_id = pe.id
  AND pp.producto_id = pr.id;

SELECT * FROM pedidos p WHERE p.id = ?;

-- Queries selección oferta

SELECT MIN(oep.tiempo_entrega) min_list_price
FROM ofertas_entrega_pedido oep
WHERE oep.pedido_id = :idPedido
HAVING MIN(oep.valor_domicilio);

-- Insert proveedores
INSERT INTO proyecto_domicilios.proveedores (nombre, numero_telefono)
VALUES ('EntregaYa', '3251051560');

INSERT INTO proyecto_domicilios.proveedores (nombre, numero_telefono)
VALUES ('TeLoEntrego', '3165110554');

-- Inserts ofertas entregas
INSERT INTO proyecto_domicilios.ofertas_entrega_pedido (proveedor_id, pedido_id, valor_domicilio, tiempo_entrega)
VALUES (1, 1, 4000, 12.5);

INSERT INTO proyecto_domicilios.ofertas_entrega_pedido (proveedor_id, pedido_id, valor_domicilio, tiempo_entrega)
VALUES (2, 1, 4500, 13);

-- Insert usuarios
INSERT INTO proyecto_domicilios.usuarios (username, password, email, rol)
VALUES ('admin', '12345', 'admin@gmail.com', 'admin');

INSERT INTO proyecto_domicilios.usuarios (username, password, email, rol)
VALUES ('proveedor1', '12345', 'proveedor@gmail.com', 'proveedor');
