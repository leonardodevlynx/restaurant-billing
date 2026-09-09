-- ============================================================
-- V1__create_initial_schema.sql
-- Esquema inicial del sistema de facturación del restaurante
-- ============================================================

-- ── EXTENSIÓN para UUID ──────────────────────────────────────
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ── TABLA: mesas ─────────────────────────────────────────────
CREATE TABLE mesas (
                       id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       numero      INTEGER NOT NULL UNIQUE,
                       capacidad   INTEGER NOT NULL,
                       estado      VARCHAR(20) NOT NULL DEFAULT 'DISPONIBLE',
                       CONSTRAINT chk_mesa_estado CHECK (estado IN ('DISPONIBLE', 'OCUPADA', 'RESERVADA')),
                       CONSTRAINT chk_mesa_numero CHECK (numero > 0),
                       CONSTRAINT chk_mesa_capacidad CHECK (capacidad > 0)
);

-- ── TABLA: productos ─────────────────────────────────────────
CREATE TABLE productos (
                           id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           nombre      VARCHAR(100) NOT NULL UNIQUE,
                           descripcion VARCHAR(255),
                           precio      NUMERIC(10, 2) NOT NULL,
                           categoria   VARCHAR(50) NOT NULL,
                           disponible  BOOLEAN NOT NULL DEFAULT TRUE,
                           CONSTRAINT chk_producto_precio CHECK (precio > 0)
);

-- ── TABLA: pedidos ───────────────────────────────────────────
CREATE TABLE pedidos (
                         id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         mesa_id         UUID NOT NULL,
                         estado          VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                         fecha_creacion  TIMESTAMP NOT NULL DEFAULT NOW(),
                         total           NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
                         observacion     VARCHAR(255),
                         CONSTRAINT fk_pedido_mesa FOREIGN KEY (mesa_id) REFERENCES mesas(id),
                         CONSTRAINT chk_pedido_estado CHECK (estado IN (
                                                                        'PENDIENTE', 'PREPARANDO', 'ENTREGADO', 'POR_PAGAR', 'FACTURADO'
                             ))
);

-- ── TABLA: detalle_pedidos ───────────────────────────────────
CREATE TABLE detalle_pedidos (
                                 id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                 pedido_id       UUID NOT NULL,
                                 producto_id     UUID NOT NULL,
                                 cantidad        INTEGER NOT NULL,
                                 precio_unitario NUMERIC(10, 2) NOT NULL,
                                 subtotal        NUMERIC(10, 2) NOT NULL,
                                 observacion     VARCHAR(255),
                                 CONSTRAINT fk_detalle_pedido    FOREIGN KEY (pedido_id)   REFERENCES pedidos(id),
                                 CONSTRAINT fk_detalle_producto  FOREIGN KEY (producto_id) REFERENCES productos(id),
                                 CONSTRAINT chk_detalle_cantidad CHECK (cantidad > 0)
);

-- ── TABLA: comprobantes ──────────────────────────────────────
CREATE TABLE comprobantes (
                              id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              pedido_id            UUID NOT NULL UNIQUE,
                              tipo                 VARCHAR(20) NOT NULL,
                              serie                VARCHAR(4)  NOT NULL,
                              numero               INTEGER NOT NULL,
                              ruc_cliente          VARCHAR(11),
                              razon_social_cliente VARCHAR(200),
                              dni_cliente          VARCHAR(8),
                              valor_venta          NUMERIC(10, 2) NOT NULL,
                              igv                  NUMERIC(10, 2) NOT NULL,
                              total                NUMERIC(10, 2) NOT NULL,
                              estado               VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE_ENVIO',
                              fecha_emision        TIMESTAMP NOT NULL DEFAULT NOW(),
                              hash_cdr             VARCHAR(500),
                              CONSTRAINT fk_comprobante_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
                              CONSTRAINT uq_comprobante_serie_numero UNIQUE (serie, numero),
                              CONSTRAINT chk_comprobante_tipo CHECK (tipo IN (
                                                                              'FACTURA', 'BOLETA', 'NOTA_CREDITO', 'NOTA_DEBITO'
                                  )),
                              CONSTRAINT chk_comprobante_estado CHECK (estado IN (
                                                                                  'PENDIENTE_ENVIO', 'ENVIADO', 'ACEPTADO',
                                                                                  'RECHAZADO', 'EXCEPCION', 'GUARDADO_OFFLINE'
                                  ))
);

-- ── ÍNDICES para mejorar rendimiento ────────────────────────
CREATE INDEX idx_pedidos_estado        ON pedidos(estado);
CREATE INDEX idx_pedidos_mesa          ON pedidos(mesa_id);
CREATE INDEX idx_detalle_pedido        ON detalle_pedidos(pedido_id);
CREATE INDEX idx_comprobantes_estado   ON comprobantes(estado);
CREATE INDEX idx_comprobantes_serie    ON comprobantes(serie);