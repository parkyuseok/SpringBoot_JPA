DROP TABLE IF EXISTS NOTICE;

-- auto-generated definition
CREATE TABLE NOTICE
(
	ID 			BIGINT auto_increment primary key,
    TITLE		VARCHAR(255),
    CONTENTS	VARCHAR(255),
    
    HITS		INTEGER,
    LIKES		INTEGER,
    REG_DATE	timestamp
)