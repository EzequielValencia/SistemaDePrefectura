#CREATE SCHEMA `sistemaprefectura` ;

#use `sistemaprefectura`;


create table usuario(
	id int(9) not null unique auto_increment primary key,
	nombres varchar(40) not null,
    apellido_paterno varchar(40) not null,
    apellido_materno varchar(40) not null,
    usuario varchar(30) not null unique,
    contrasenia varchar(100) not null,
    catergoria int not null
);

create table articulo(
	id int(9) not null unique auto_increment primary key, 
    nombre varchar(40) not null,
    descripcion varchar(200) not null,
    catergoria_articulo int,
    stocke int not null
);

create table prestamo(
	id int(9) not null primary key auto_increment unique,
	fecha_prestamo DATE not null,
    fecha_devolucion DATE not null,
    id_solicitante int not null,
    id_usuario_que_registro int(9) not null,
    foreign key(id_usuario_que_registro) references usuario(id) on delete cascade on update cascade
);

create table articulo_prestado(
	id_prestamo int(9) not null,
    id_articulo int(9) not null,
    foreign key(id_prestamo) references prestamo(id) on delete cascade on update cascade,
    foreign key(id_articulo) references articulo(id) on delete cascade on update cascade
);

create table prefesor(
	id int(9) not null auto_increment primary key unique,
    nombre varchar(40) not null,
    apellido_paterno varchar(40) not null,
    apellido_materno varchar(40) not null
);

create table alumno(
	id int(9) not null auto_increment primary key unique,
    nombre varchar(40) not null,
    apellido_paterno varchar(40) not null,
    apellido_materno varchar(40) not null
);
