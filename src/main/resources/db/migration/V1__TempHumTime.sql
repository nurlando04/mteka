CREATE TABLE IF NOT EXISTS temphum.organisation(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    login character varying(64) NOT NULL,
    password character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    email character varying(64) NOT NULL,
    nickname character varying(64) DEFAULT 'Организация'::character varying NOT NULL,
    is_admin boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS temphum.organisation
    OWNER to temphum;

CREATE TABLE IF NOT EXISTStemphum.device
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1000),
    user_id integer NOT NULL,
    model character varying,
    created_at timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES temphum.organisation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE IF EXISTS temphum.device
    OWNER to temphum;

CREATE TABLE IF NOT EXISTStemphum.temphumdata
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    temp double precision NOT NULL,
    hum double precision NOT NULL,
    created_at timestamp without time zone NOT NULL,
    recieved_at timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    device_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT device_id FOREIGN KEY (device_id)
        REFERENCES temphum.device (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE IF EXISTS temphum.temphumdata
    OWNER to temphum;
