CREATE TABLE public."user" (
	id integer NOT NULL,
	cpf varchar(11) NOT NULL,
	email text NULL,
	"password" varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_un UNIQUE (cpf)
);
