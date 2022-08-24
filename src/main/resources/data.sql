CREATE DATABASE 'forum'

INSERT INTO USERS(name, email, password) VALUES('Admin', 'admin@email.com', '$2a$10$r8hCcMkz6xdcvOvscwt.8Ok10cIT8ygS37/.I8fKb6ZIJdlxg4A4G');
INSERT INTO USERS(name, email, password) VALUES('Student', 'student@email.com', '$2a$10$r8hCcMkz6xdcvOvscwt.8Ok10cIT8ygS37/.I8fKb6ZIJdlxg4A4G');
INSERT INTO USERS(name, email, password) VALUES('Moderate', 'moderate@email.com', '$2a$10$r8hCcMkz6xdcvOvscwt.8Ok10cIT8ygS37/.I8fKb6ZIJdlxg4A4G');

INSERT INTO PERFIL(name) VALUES('ROLE_ADMIN');
INSERT INTO PERFIL(name) VALUES('ROLE_STUDENT');
INSERT INTO PERFIL(name) VALUES('ROLE_MODERATE');

INSERT INTO USERS_PERFIL(user_id, perfil_id) VALUES(1, 1);
INSERT INTO USERS_PERFIL(user_id, perfil_id) VALUES(2, 2);
INSERT INTO USERS_PERFIL(user_id, perfil_id) VALUES(3, 3);

INSERT INTO COURSES(name, category,) VALUES('Spring Boot', 'Programação');
INSERT INTO COURSES(name, category,) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICS(title, message, created_date, status, author_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICS(title, message, created_date, status, author_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICS(title, message, created_date, status, author_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);