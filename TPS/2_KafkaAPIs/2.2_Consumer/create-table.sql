DROP TABLE IF EXISTS coursier;


CREATE TABLE coursier (
    coursierId varchar(30) NOT NULL,
    kafkaOffset INT NOT NULL,
    PRIMARY KEY (coursierId, kafkaOffset)
);

