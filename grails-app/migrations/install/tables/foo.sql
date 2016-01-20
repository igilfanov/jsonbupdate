CREATE TABLE public.foo(
  id serial NOT NULL,
  bar jsonb,
  CONSTRAINT public_foo_pkey PRIMARY KEY (id)
);