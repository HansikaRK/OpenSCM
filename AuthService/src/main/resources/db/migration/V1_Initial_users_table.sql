CREATE TABLE public.users (
    id uuid NOT NULL,
    created_at timestamp without time zone,
    email character varying(255) NOT NULL,
    enabled boolean NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255) NOT NULL,
    phone_number character varying(255),
    role character varying(255) NOT NULL,
    updated_at timestamp without time zone,
    username character varying(255) NOT NULL,
    CONSTRAINT users_role_check CHECK (role IN ('ROLE_CUSTOMER','ROLE_SUPPLIER','ROLE_ADMIN'))
);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);