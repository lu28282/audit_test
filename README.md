# audit_test


1. docker-compose file ausführen.
2. sudo docker exec -it <Container-id> bash
3. mysql -u root -p
4. password = password
5. Alle folgenden Befehle einfügen. Als Referenz der [Link zur MySQL Config](https://debezium.io/documentation/reference/2.0/connectors/mysql.html#setting-up-mysql).
6. CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
7. GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';
8. FLUSH PRIVILEGES;
9. Mit Datagrip connecten auf die db. 
10. Folgende Tabelle erstellen. 
CREATE TABLE customer
(
    id integer NOT NULL,
    fullname character varying(255),
    email character varying(255),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);
11. Spring starten
12. Insert oder Delete schicken und ausgabe in der konsole anschauen.
INSERT INTO customerdb.customer (id, fullname, email) VALUES (1, 'John Doe', 'jd@example.com');
DELETE FROM customer WHERE id=1;
