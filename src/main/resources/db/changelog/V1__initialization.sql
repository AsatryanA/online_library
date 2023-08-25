
create table if not exists  authors
(
    created_at       timestamp(6),
    created_by       bigint,
    id               bigserial
        primary key,
    last_modified_by bigint,
    updated_at       timestamp(6),
    full_name        varchar(255) not null
        unique
);

;

create table if not exists genres
(
    created_at       timestamp(6),
    created_by       bigint,
    id               bigserial
        primary key,
    last_modified_by bigint,
    updated_at       timestamp(6),
    genre            varchar(255) not null
        unique
);



create table if not exists books
(
    price            numeric(38, 2) not null,
    author_id        bigint         not null
        constraint fkfjixh2vym2cvfj3ufxj91jem7
            references authors,
    created_at       timestamp(6),
    created_by       bigint,
    genre_id         bigint         not null
        constraint fk9hsvoalyniowgt8fbufidqj3x
            references genres,
    id               bigserial
        primary key,
    last_modified_by bigint,
    published        timestamp(6),
    updated_at       timestamp(6),
    description      varchar(255),
    image            varchar(255),
    isbn             varchar(255)   not null,
    publisher        varchar(255),
    title            varchar(255)   not null
);


create table if not exists roles
(
    id   bigserial
        primary key,
    role varchar(255) not null
        constraint roles_role_check
            check ((role)::text = ANY
                   ((ARRAY ['ROLE_SUPER_ADMIN'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_USER'::character varying])::text[]))
);


create table if not exists users
(
    created_at       timestamp(6),
    created_by       bigint,
    id               bigserial
        primary key,
    last_modified_by bigint,
    role_id          bigint       not null
        constraint fkp56c1712k691lhsyewcssf40f
            references roles,
    updated_at       timestamp(6),
    address          varchar(255),
    country          varchar(255),
    email            varchar(255) not null
        unique,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    password         varchar(255) not null,
    phone_number     varchar(255) not null
        unique,
    postal_zip       varchar(255)
);


create table if not exists authors_users
(
    author_id bigint not null
        constraint fk1dhbyx986a2fp8mo1cpa7qoe7
            references authors,
    user_id   bigint not null
        constraint fko1ha6e4p3g2krins0vnle4bpa
            references users
);


create table if not exists basket_items
(
    count   integer not null,
    book_id bigint  not null
        constraint fkd41qy53yqed1r52ue9n2m0996
            references books,
    id      bigserial
        primary key,
    user_id bigint
        constraint fklt7mooe41v11t6dq0xc8kvya6
            references users
);


create table if not exists cards
(
    created_at       timestamp(6),
    created_by       bigint,
    id               bigserial
        primary key,
    last_modified_by bigint,
    updated_at       timestamp(6),
    user_id          bigint       not null
        constraint fkcmanafgwbibfijy2o5isfk3d5
            references users,
    card_number      varchar(255) not null,
    card_type        varchar(255) not null
        constraint cards_card_type_check
            check ((card_type)::text = ANY
                   ((ARRAY ['VISA_CARD'::character varying, 'MASTER_CARD'::character varying, 'AMERICAN_EXPRESS_CARD'::character varying])::text[])),
    cvv              varchar(255) not null,
    expire_month     varchar(255) not null,
    expire_year      varchar(255) not null
);


create table if not exists genres_users
(
    genre_id bigint not null
        constraint fk2igr8va44th3be2iq1c7lrn29
            references genres,
    user_id  bigint not null
        constraint fkjvcynh2ur6xji1qxhhrfdfj03
            references users
);


create table if not exists orders
(
    delivery_price        numeric(38, 2) not null,
    latitude              double precision,
    longitude             double precision,
    total_amount          numeric(38, 2) not null,
    total_count           integer        not null,
    created_at            timestamp(6),
    created_by            bigint,
    deliver_on            timestamp(6),
    delivered_on          timestamp(6),
    id                    bigserial
        primary key,
    last_modified_by      bigint,
    updated_at            timestamp(6),
    user_id               bigint         not null
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users,
    deliver_on_status     varchar(255)   not null
        constraint orders_deliver_on_status_check
            check ((deliver_on_status)::text = ANY
                   ((ARRAY ['DETERMINED_DATE'::character varying, 'AS_SOON_AS_POSSIBLE'::character varying, 'CHECK_WITH_THE_USER'::character varying])::text[])),
    full_address          varchar(255),
    note                  varchar(255),
    receiver_name         varchar(255),
    receiver_phone_number varchar(255),
    status                varchar(255)   not null
        constraint orders_status_check
            check ((status)::text = ANY
                   ((ARRAY ['PENDING'::character varying, 'ACCEPTED'::character varying, 'COLLECTED'::character varying, 'ON_THE_WAY'::character varying, 'DELIVERED'::character varying, 'CANCELED'::character varying])::text[])),
    address_details       jsonb
);



create table if not exists order_items
(
    count            integer        not null,
    total_price      numeric(38, 2) not null,
    created_at       timestamp(6),
    created_by       bigint,
    id               bigserial
        primary key,
    last_modified_by bigint,
    order_id         bigint         not null
        constraint fkbioxgbv59vetrxe0ejfubep1w
            references orders,
    updated_at       timestamp(6),
    book_details     jsonb          not null
);

create table if not exists refresh_token
(
    created_at    timestamp(6) not null,
    id            bigserial
        primary key,
    user_id       bigint       not null
        constraint fkjtx87i0jvq2svedphegvdwcuy
            references users,
    refresh_token varchar(255) not null
);

