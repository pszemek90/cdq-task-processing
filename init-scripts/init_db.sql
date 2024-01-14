CREATE DATABASE taskdb;

\c taskdb;

CREATE TABLE tasks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    input VARCHAR(255),
    pattern VARCHAR(255),
    best_position INTEGER,
    typos INTEGER,
    progress INT,
    created_date TIMESTAMP,
    modified_date TIMESTAMP
);