# audit_test


1. docker-compose file ausführen.
2. sudo docker exec -it <Container-id> bash
2.1 mysql -u root -p
2.2 password = password
2.3 Alle folgenden Befehle einfügen. Als Referenz der [Link zur MySQL Config](https://debezium.io/documentation/reference/2.0/connectors/mysql.html#setting-up-mysql).
2.4 CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
2.5 GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';
2.6 FLUSH PRIVILEGES;
3.0 Mit Datagrip connecten auf die db. 
3.1 Folgende Tabelle erstellen. 
CREATE TABLE customer
(
    id integer NOT NULL,
    fullname character varying(255),
    email character varying(255),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);
3.2 Spring starten
3.4 Insert oder Delete schicken und ausgabe in der konsole anschauen.
INSERT INTO customerdb.customer (id, fullname, email) VALUES (1, 'John Doe', 'jd@example.com');
DELETE FROM customer WHERE id=1;
