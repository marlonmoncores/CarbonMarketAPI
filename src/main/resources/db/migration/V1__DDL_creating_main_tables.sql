CREATE TABLE public."market" (
	id BIGSERIAL NOT NULL,
	name text NOT NULL,
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),
	CONSTRAINT market_pk PRIMARY KEY (id)
);

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
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),
	optional boolean not null,
	daily_portions integer not null,
	CONSTRAINT item_category_pk PRIMARY KEY (id),
	CONSTRAINT item_category_un UNIQUE (name)
);


CREATE TABLE public."item" (
	id BIGSERIAL NOT NULL,
	barcode text NOT NULL,
	name text NOT NULL,
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),
	item_category_id BIGSERIAL NOT NULL,
	CONSTRAINT item_pk PRIMARY KEY (id),
	CONSTRAINT item_un UNIQUE (barcode),
	FOREIGN KEY (item_category_id) REFERENCES item_category (id)
);



CREATE TABLE public."user_buy" (
	id BIGSERIAL NOT NULL,
	id_user BIGSERIAL NOT NULL,
	id_market BIGSERIAL,
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),

	CONSTRAINT user_buy_pk PRIMARY KEY (id),
	FOREIGN KEY (id_user) REFERENCES user_system (id),
	FOREIGN KEY (id_market) REFERENCES market (id)
);

CREATE TABLE public."user_buy_item" (
	id_user_buy BIGSERIAL NOT NULL,
	id_item BIGSERIAL NOT NULL,
	quantity BIGSERIAL NOT NULL,

	CONSTRAINT user_buy_item_pk PRIMARY KEY (id_user_buy,id_item),
	FOREIGN KEY (id_user_buy) REFERENCES user_buy (id),
	FOREIGN KEY (id_item) REFERENCES item (id)
);