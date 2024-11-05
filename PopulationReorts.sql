CREATE TABLE Country (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50),
    population INT,
    continent VARCHAR(50),
    region VARCHAR(50)
);

CREATE TABLE City (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50),
    population INT,
    country_id INT,
    continent VARCHAR(50),
    region VARCHAR(50),
    district VARCHAR(50),
    is_capital BOOLEAN,
    FOREIGN KEY (country_id) REFERENCES Country(id)
);

