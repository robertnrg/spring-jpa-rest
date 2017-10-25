--Permisions
INSERT INTO t_permission (code, description, created_date) VALUES ('READ', 'Permission to read', '2017-01-01');
INSERT INTO t_permission (code, description, created_date) VALUES ('WRITE', 'Permission to write', '2017-01-01');
INSERT INTO t_permission (code, description, created_date) VALUES ('EDIT', 'Permission to edit', '2017-01-01');
--User status
INSERT INTO t_status (code, description, created_date) VALUES  ('ACT', 'Active user', '2017-01-01');
INSERT INTO t_status (code, description, created_date) VALUES ('INACT', 'Inactive user', '2017-01-01');
INSERT INTO t_status (code, description, created_date) VALUES ('LOCK', 'Locked user', '2017-01-01');
--Persons
INSERT INTO t_person (name, last_name, second_last_name, email, date_of_birth, gender) VALUES ('Alberto', 'Pérez', 'Flores', 'alberto.perez@example.com', '1988-01-01', 'H'); --1
INSERT INTO t_person (name, last_name, second_last_name, email, date_of_birth, gender) VALUES ('Maria', 'Hernandez', 'Cisneros', 'maria.hernandez@example.com', '1970-01-01', 'M'); --2
INSERT INTO t_person (name, last_name, second_last_name, email, date_of_birth, gender) VALUES ('Juan', 'Rodriguez', '', 'juan.rodriguez@example.com', '1985-01-01', 'H'); --3
--Users
INSERT INTO t_user (username, created_date, updated_date, t_person_id, t_status_id) VALUES ('alberto_perez', '2017-01-01', '2017-01-01', 1, 3);
INSERT INTO t_user (username, created_date, updated_date, t_person_id, t_status_id) VALUES ('maria_hernandez', '2017-01-01', '2017-01-01', 2, 1);
INSERT INTO t_user (username, created_date, updated_date, t_person_id, t_status_id) VALUES ('juan_rodriguez', '2017-01-01', '2017-01-01', 3, 2);
-- User-Permission
INSERT INTO t_user_permission (user_id, permission_id) VALUES (1, 1);
INSERT INTO t_user_permission (user_id, permission_id) VALUES (1, 2);
INSERT INTO t_user_permission (user_id, permission_id) VALUES (2, 1);
INSERT INTO t_user_permission (user_id, permission_id) VALUES (2, 3);
INSERT INTO t_user_permission (user_id, permission_id) VALUES (3, 1);
INSERT INTO t_user_permission (user_id, permission_id) VALUES (3, 2);
INSERT INTO t_user_permission (user_id, permission_id) VALUES (3, 3);