create table usuarios(

    id bigint not null auto_increment,
    nombre varchar(140) not null,
    email varchar(100) not null unique,
    contrasenia varchar(200) not null,
    tipo varchar(40) not null,

    primary key(id)
);