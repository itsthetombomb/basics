DROP TABLE IF EXISTS public.consumers;

CREATE TABLE public.consumers (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    age INTEGER NOT NULL
);

INSERT INTO public.consumers (first_name, last_name, email, age) VALUES
    ('John', 'Doe', 'john.doe@example.com', 20),
    ('Jane', 'Smith', 'jane.smith@example.com', 24),
    ('Bob', 'Johnson', 'bob.johnson@example.com', 20);
