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
                       publication_date DATE,
                       FOREIGN KEY (author_id) REFERENCES Autor(id) ON DELETE CASCADE
);

-- Insertar algunos datos de ejemplo en la tabla de autores
INSERT INTO Autor (name) VALUES ('Gabriel García Márquez');
INSERT INTO Autor (name) VALUES ('Julio Cortázar');
INSERT INTO Autor (name) VALUES ('Isabel Allende');
INSERT INTO Autor (name) VALUES ('Mario Vargas Llosa');
INSERT INTO Autor (name) VALUES ('Jorge Luis Borges');
INSERT INTO Autor (name) VALUES ('Laura Esquivel');
INSERT INTO Autor (name) VALUES ('Carlos Ruiz Zafón');
INSERT INTO Autor (name) VALUES ('Ernesto Sabato');

-- Insertar algunos libros asociados a los autores con fechas de publicación
-- Gabriel García Márquez (id 1)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Cien años de soledad', 1, DATE '1967-06-05');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El amor en los tiempos del cólera', 1, DATE '1985-04-05');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Crónica de una muerte anunciada', 1, DATE '1981-03-02');

-- Julio Cortázar (id 2)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Rayuela', 2, DATE '1963-06-28');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('La vuelta al día en ochenta mundos', 2, DATE '1970-10-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Bestiario', 2, DATE '1951-09-01');

-- Isabel Allende (id 3)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('La casa de los espíritus', 3, DATE '1982-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('De amor y de sombra', 3, DATE '1984-05-12');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Paula', 3, DATE '1994-11-01');

-- Mario Vargas Llosa (id 4)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('La ciudad y los perros', 4, DATE '1963-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Conversación en La Catedral', 4, DATE '1969-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('La fiesta del chivo', 4, DATE '2000-01-01');

-- Jorge Luis Borges (id 5)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Ficciones', 5, DATE '1944-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El Aleph', 5, DATE '1949-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El libro de arena', 5, DATE '1975-01-01');

-- Laura Esquivel (id 6)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Como agua para chocolate', 6, DATE '1989-09-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Tan veloz como el deseo', 6, DATE '2001-03-15');

-- Carlos Ruiz Zafón (id 7)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('La sombra del viento', 7, DATE '2001-05-17');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El juego del ángel', 7, DATE '2008-04-17');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El prisionero del cielo', 7, DATE '2011-11-17');

-- Ernesto Sabato (id 8)
INSERT INTO Libro (title, author_id, publication_date) VALUES ('El túnel', 8, DATE '1948-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Sobre héroes y tumbas', 8, DATE '1961-01-01');
INSERT INTO Libro (title, author_id, publication_date) VALUES ('Abaddón el exterminador', 8, DATE '1974-01-01');
