create table if not exists prestamos
(
    id                     int auto_increment,
    isbn                   varchar(10)            not null,
    identificacion_usuario varchar(10)            not null,
    tipo_usuario           int                    not null,
    fecha_maxima_devolucion         datetime      not null,
    constraint prestamos_pk
        primary key (id)
);

