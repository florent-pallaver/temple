CREATE TABLE region (
    country TINYINT UNSIGNED NOT NULL,
	id SMALLINT UNSIGNED NOT NULL,
	name VARCHAR(255) NOT NULL,
    PRIMARY KEY (country, id),
	INDEX I_region_name (name)
) ENGINE=InnoDB;

CREATE TABLE city (
    country TINYINT UNSIGNED NOT NULL,
	regionId SMALLINT UNSIGNED NOT NULL,
	id MEDIUMINT UNSIGNED NOT NULL,
	name VARCHAR(255) NOT NULL,
	postCode VARCHAR(20) NOT NULL,
    PRIMARY KEY (country, regionId, id),
	INDEX I_city_name (name),
	CONSTRAINT city_region FOREIGN KEY FK_city_country_regionId (country, regionId) REFERENCES region(country, id)
) ENGINE=InnoDB;

CREATE TABLE location (
    country TINYINT UNSIGNED NOT NULL,
	region SMALLINT UNSIGNED,
	city MEDIUMINT UNSIGNED,
	name VARCHAR(255) NOT NULL,
    PRIMARY KEY (country, region, city),
	INDEX I_location_name (name)
) ENGINE=InnoDB;



