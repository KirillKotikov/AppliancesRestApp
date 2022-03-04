CREATE TABLE hibernate_sequence
(
    next_val BIGINT
);

insert into hibernate_sequence (1);
insert into hibernate_sequence (1);

CREATE TABLE appliance (
    id BIGINT NOT NULL AUTO_INCREMENT,
    applianceName VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE television
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    televisionName VARCHAR(30) NOT NULL UNIQUE,
    producingCountry VARCHAR(30) NOT NULL,
    companyManufacturer VARCHAR(30) NOT NULL,
    availableOnline BOOLEAN NOT NULL,
    installmentPlan BOOLEAN NOT NULL,
    applianceId BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY applianceId REFERENCES appliance (id) ON DELETE CASCADE
);

CREATE TABLE television_model
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    televisionModelName VARCHAR(30) NOT NULL UNIQUE,
    serialNumber NOT NULL UNIQUE,
    color VARCHAR(30) NOT NULL,
    size VARCHAR(30) NOT NULL,
    price numeric(19,2) NOT NULL,
    category VARCHAR(30) NOT NULL,
    technology VARCHAR(100) NOT NULL,
    inStock BOOLEAN NOT NULL,
    televisionId BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY televisionId REFERENCES television (id) ON DELETE CASCADE
);



