create table respuestas(

    id bigint not null auto_increment,
    mensaje varchar(400) not null,
    topico_id bigint not null,
    fecha_creacion date not null,
    autor_id bigint not null,
    solucion boolean not null,

    primary key(id),

    constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
    constraint fk_respuestas_autor_id foreign key(autor_id) references usuarios(id)

);