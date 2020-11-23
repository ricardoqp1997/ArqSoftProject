CREATE TABLE IF NOT EXISTS Departamento
(
    departamento_id     int primary key,
    nombre_departamento varchar(50)
);

CREATE TABLE IF NOT EXISTS Ciudad
(
    ciudad_id       int primary key,
    nombre_ciudad   varchar(50),
    departamento_id int REFERENCES Departamento (departamento_id)
);

CREATE TABLE IF NOT EXISTS consulta
(
    id_consulta      varchar primary key,
    cedula_aspirante int       NOT NULL references aspirante(cedula_aspirante),
    ip_consulta      char(15)  NOT NULL,
    navegador        char(30)  NOT NULL,
    fecha_consulta   timestamp not null

);
CREATE TABLE IF NOT EXISTS Programa
(
    programa_id     int      primary key ,
    nombre_programa char(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS Sede_Universidad
(
    sede_id        int primary key,
    nombre_sede    char(50) not null,
    direccion_sede char(50) not null,
    ciudad_id      int references Ciudad (ciudad_id)
);

CREATE TABLE IF NOT EXISTS Aspirante(
    cedula_aspirante int primary key ,
    nombre_aspirante char(50) not null ,
    apellido_aspirante char(50) not null ,
    edad_aspirante int not null ,
    sexo_aspirante char(1) not null ,
    ciudad_id int references Ciudad(ciudad_id),
    programa_id int references Programa(programa_id),
    sede_id int references Sede_Universidad(sede_id),
    qrcode_aspirante int not null ,
    examen_filename char(100) not null ,
    admitido bool not null
);