-- FIXME add corresponding key definitions in the annotations
-- No need to give an index_name for foreign with an already existing index,
-- for those with no existing index, they are automaticaly created

CREATE TABLE paris_user (
    id MEDIUMINT UNSIGNED NOT NULL PRIMARY KEY,
    creationTS TIMESTAMP NOT NULL,
    email VARCHAR(48) NOT NULL,
    type TINYINT UNSIGNED NOT NULL,
    gender TINYINT UNSIGNED NOT NULL,
    privacy TINYINT UNSIGNED NOT NULL,
    lastSignIn TIMESTAMP NULL DEFAULT NULL,
    UNIQUE U_paris_user_email (email)
) ENGINE = InnoDB ;

CREATE TABLE profile (
	id MEDIUMINT UNSIGNED NOT NULL PRIMARY KEY,
    alias VARCHAR(32) NOT NULL,
    parisType TINYINT UNSIGNED NOT NULL,
    lookingFor TINYINT,
	birthday DATE,
	starSign TINYINT UNSIGNED,
	height TINYINT UNSIGNED, -- in cm
	weight TINYINT UNSIGNED, -- in kg
	bodyType TINYINT UNSIGNED, 
	eyeColor TINYINT UNSIGNED, 
	hairColor TINYINT UNSIGNED, 
	hairLength TINYINT UNSIGNED, 
	ethnicity TINYINT UNSIGNED, 
	maritalStatus TINYINT UNSIGNED, 
	education TINYINT UNSIGNED,
	occupation VARCHAR(64),
	countryId TINYINT UNSIGNED,
	regionId SMALLINT UNSIGNED,
	cityId MEDIUMINT UNSIGNED,
	sport TINYINT UNSIGNED, 
	party TINYINT UNSIGNED, 
	smoking TINYINT UNSIGNED, 
	drinking TINYINT UNSIGNED, 
	albumId TINYINT UNSIGNED, 
	mainPhotoId SMALLINT UNSIGNED,
    description TEXT,
	seen MEDIUMINT UNSIGNED NOT NULL,
	CONSTRAINT profile_owner FOREIGN KEY (id) REFERENCES paris_user(id),
	CONSTRAINT profile_location FOREIGN KEY (countryId, regionId, cityId) REFERENCES location(country, region, city)
) ENGINE = InnoDB ;

CREATE TABLE album (
    ownerId MEDIUMINT UNSIGNED NOT NULL,
    selfId TINYINT UNSIGNED NOT NULL,
    creationTS TIMESTAMP NOT NULL,
    name VARCHAR(32) NOT NULL,
    privacy TINYINT UNSIGNED NOT NULL,
    photoCount SMALLINT UNSIGNED NOT NULL,
    lastPhotoTS TIMESTAMP NULL DEFAULT NULL,
    mainPhotoId SMALLINT UNSIGNED,
    PRIMARY KEY (ownerId, selfId),
    CONSTRAINT album_owner FOREIGN KEY (ownerId) REFERENCES paris_user(id)
) ENGINE = InnoDB ;

CREATE TABLE photo (
    ownerId MEDIUMINT UNSIGNED NOT NULL,
    parentId TINYINT UNSIGNED NOT NULL,
    selfId SMALLINT UNSIGNED NOT NULL,
    creationTS TIMESTAMP NOT NULL,
    name VARCHAR(32) NOT NULL,
    width SMALLINT UNSIGNED NOT NULL,
    height SMALLINT UNSIGNED NOT NULL,
    PRIMARY KEY (ownerId, parentId, selfId),
    CONSTRAINT photo_owner FOREIGN KEY (ownerId) REFERENCES paris_user(id),
    CONSTRAINT photo_album FOREIGN KEY (ownerId, parentId) REFERENCES album(ownerId, selfId)
) ENGINE = InnoDB ;

ALTER TABLE profile ADD
	CONSTRAINT profile_photo FOREIGN KEY FK_profile_photo_id (id, albumId, mainPhotoId) REFERENCES photo(ownerId, parentId, selfId) ;

ALTER TABLE album ADD
	CONSTRAINT album_photo FOREIGN KEY FK_album_photo_id (ownerId, selfId, mainPhotoId) REFERENCES photo(ownerId, parentId, selfId) ;

CREATE TABLE conversation (
    id INT UNSIGNED NOT NULL PRIMARY KEY,
    creationTS TIMESTAMP NOT NULL,
    lastMessageTS TIMESTAMP NOT NULL
) ENGINE = InnoDB ;

CREATE TABLE conversation_activity (
    ownerId MEDIUMINT UNSIGNED NOT NULL,
    conversationId INT UNSIGNED NOT NULL,
    creationTS TIMESTAMP NOT NULL,
    lastTS TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (ownerId, conversationId),
    CONSTRAINT conversation_activity_owner FOREIGN KEY (ownerId) REFERENCES paris_user(id),
    CONSTRAINT conversation_activity_conversation FOREIGN KEY FK_conversation_activity_conversation_id (conversationId) REFERENCES conversation(id)
) ENGINE = InnoDB ;

CREATE TABLE message (
    conversationId INT UNSIGNED NOT NULL,
    creationTS TIMESTAMP NOT NULL,
    ownerId MEDIUMINT UNSIGNED NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY (conversationId, creationTS, ownerId),
    CONSTRAINT message_conversation FOREIGN KEY (conversationId) REFERENCES conversation(id)
) ENGINE = InnoDB ;

CREATE TABLE trace (
    trackerId MEDIUMINT UNSIGNED NOT NULL,
    trackeeId MEDIUMINT UNSIGNED NOT NULL,
    creationTS TIMESTAMP NOT NULL,
    PRIMARY KEY (trackerId, trackeeId),
    CONSTRAINT trace_tracker FOREIGN KEY FK_trace_tracker_id (trackerId) REFERENCES paris_user(id),
    CONSTRAINT trace_trackee FOREIGN KEY FK_trace_trackee_id (trackeeId) REFERENCES paris_user(id)
) ENGINE = InnoDB ;
