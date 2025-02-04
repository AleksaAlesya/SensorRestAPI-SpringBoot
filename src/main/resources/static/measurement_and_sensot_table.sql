drop table measurement;


CREATE TABLE sensor
(
    id    int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name  varchar(50) NOT NULL UNIQUE,
    created_at timestamp
);

CREATE TABLE measurement
(
    id    int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    temperature_value  double precision NOT NULL,
    raining boolean NOT NULL,
    sensor_name varchar NOT NULL REFERENCES  sensor (name)  on DELETE CASCADE ,
    created_at timestamp
);


INSERT INTO measurement(temperature_value, raining, sensor_name) values (15.2, false, 'Sss');
