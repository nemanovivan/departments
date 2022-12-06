CREATE TABLE IF NOT EXISTS departments.departments
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default",
    parent_id integer,
    dt_from date,
    dt_till date,
    sort_priority integer,
    is_system boolean,
    creation_date date,
    correction_date date,
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT fk_parent FOREIGN KEY (parent_id)
    REFERENCES departments.departments (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
 )
