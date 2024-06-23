create table topicos(

    id bigint auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(400) not null unique,
    fecha_creacion date not null,
    estado varchar(40) not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),

    constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)

);