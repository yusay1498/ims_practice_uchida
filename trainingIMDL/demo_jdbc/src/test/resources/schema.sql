create table card
(
    bottom     integer,
    element_id integer unique,
    id         integer not null,
    "left"     integer,
    level      integer,
    "right"    integer,
    top        integer,
    name       varchar(255),
    primary key (id)
);