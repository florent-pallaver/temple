CREATE TABLE user_identity (
	login VARCHAR(64) NOT NULL PRIMARY KEY,
	password VARCHAR(88) NOT NULL,
	salt VARCHAR(128) NOT NULL,
	userId INT UNSIGNED NOT NULL,
	UNIQUE U_user_identity_userId (userId)
) ENGINE=MyISAM;

