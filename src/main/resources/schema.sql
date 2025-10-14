-- Crear la tabla de autores
CREATE TABLE Autor (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

-- Crear la tabla de libros con un campo de fecha de publicación
CREATE TABLE Libro (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author_id BIGINT,
                      publication_date DATE,  -- Nuevo campo para la fecha de publicación
                      FOREIGN KEY (author_id) REFERENCES Autor(id) ON DELETE CASCADE
);

-- Insertar algunos datos de ejemplo en la tabla de autores
INSERT INTO Autor (name) VALUES ('Gabriel García Márquez');
INSERT INTO Autor (name) VALUES ('Julio Cortázar');

-- Insertar algunos libros asociados a los autores con fechas de publicación
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Cien años de soledad', 1, DATE '1967-06-05');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El amor en los tiempos del cólera', 1, DATE '1985-04-05');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Rayuela', 2, DATE '1963-06-28');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('La vuelta al día en ochenta mundos', 2, DATE '1970-10-01');