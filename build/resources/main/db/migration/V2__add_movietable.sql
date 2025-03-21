DROP TABLE IF EXISTS public.movies;

CREATE TABLE public.movies (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    budget VARCHAR(255),
    movie_name VARCHAR(255),
    overview VARCHAR(255)
);
