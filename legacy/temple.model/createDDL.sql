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

CREATE TABLE `admin_divisions` (
  `id` int NOT NULL,
  `name` varchar(200) NOT NULL,
  `latitude` decimal(7,5) NOT NULL,
  `longitude` decimal(8,5) NOT NULL,
  `feature` smallint unsigned NOT NULL DEFAULT 0,
  `country` tinyint unsigned DEFAULT NULL,
  parentId int NULL DEFAULT NULL,
  divisionsCount mediumint not null default 0,
  placesCount mediumint not null default 0,
  PRIMARY KEY (id),
  KEY country_feature (country, feature),
  KEY name (name),
  KEY parentId (parentId), 
  KEY lat_lng(latitude, longitude)
) ENGINE= MyISAM DEFAULT CHARSET=utf8 ;

CREATE TABLE `populated_places` (
  `id` int NOT NULL,
  `name` varchar(200) NOT NULL,
  `latitude` decimal(7,5) NOT NULL,
  `longitude` decimal(8,5) NOT NULL,
  `feature` smallint unsigned NOT NULL DEFAULT 0,
  `country` tinyint unsigned DEFAULT NULL,
  parentId int NOT NULL,
  PRIMARY KEY (id),
  KEY country_feature (country, feature),
  KEY name (name),
  KEY parentId (parentId),
  KEY lat_lng(latitude, longitude)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ;

