CREATE TABLE public."user_system" (
	id BIGSERIAL NOT NULL,
	cpf varchar(11) NOT NULL,
	email text NULL,
	"password" varchar NOT NULL,
	"created_at" timestamp with time zone NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_un UNIQUE (cpf)
);

CREATE TABLE public."item_category" (
	id BIGSERIAL NOT NULL,
	name text NOT NULL,
	"created_at" timestamp with time zone NOT NULL,
	optional boolean not null,
	daily_portions integer not null,
	CONSTRAINT item_category_pk PRIMARY KEY (id),
	CONSTRAINT item_category_un UNIQUE (name)
);


CREATE TABLE public."item" (
	id BIGSERIAL NOT NULL,
	barcode text NOT NULL,
	name text NOT NULL,
	"created_at" timestamp with time zone NOT NULL,
	item_category_id BIGSERIAL NOT NULL,
	CONSTRAINT item_pk PRIMARY KEY (id),
	CONSTRAINT item_un UNIQUE (barcode),
	FOREIGN KEY (item_category_id) REFERENCES item_category (id)
);
