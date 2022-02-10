DROP TABLE IF EXISTS NOTICE;
DROP TABLE IF EXISTS NOTICE_LIKE;
DROP TABLE IF EXISTS USER;

-- auto-generated definition
CREATE TABLE USER
(
	ID 			 BIGINT auto_increment primary key,
    EMAIL		 VARCHAR(255),
    USER_NAME	 VARCHAR(255),
    PASSWORD     VARCHAR(255),
    PHONE		 VARCHAR(255),
    REG_DATE	 TIMESTAMP,
    UPDATE_DATE	 TIMESTAMP,
    STATUS		 INTEGER,
    LOCK_YN      BOOLEAN
);

-- auto-generated definition
CREATE TABLE NOTICE
(
	ID 			 BIGINT auto_increment primary key,
    TITLE		 VARCHAR(255),
    CONTENTS	 VARCHAR(255),
    
    HITS		 INTEGER,
    LIKES		 INTEGER,
    
    REG_DATE	 TIMESTAMP,
    UPDATE_DATE	 TIMESTAMP,
    DELETED_DATE TIMESTAMP,
    DELETED		 BOOLEAN,
    
    USER_ID      BIGINT,
    constraint FK_NOTICE_USER_ID foreign key(USER_ID) references USER(ID)
);

-- auto-generated definition
CREATE TABLE NOTICE_LIKE
(
	ID 			 BIGINT auto_increment primary key,
    NOTICE_ID    BIGINT,
	USER_ID      BIGINT,
    
    constraint FK_NOTICE_NOTICE_ID foreign key(NOTICE_ID) references NOTICE(ID)
);

-- auto-generated definition
CREATE TABLE USER_LOGIN_HISTORY
(
	ID 			 BIGINT auto_increment primary key,
    USER_ID		 BIGINT,
    EMAIL		 VARCHAR(255),
    USER_NAME	 VARCHAR(255),
    LOGIN_DATE 	 TIMESTAMP,
    IP_ADDR      VARCHAR(255)
);

CREATE TABLE BOARD_TYPE
(
	ID 			 BIGINT auto_increment primary key,
    BOARD_NAME	 VARCHAR(255),
    REG_DATE	 TIMESTAMP,
    UPDATE_DATE  TIMESTAMP,
    
    USING_YN     BOOLEAN
);

-- auto-generated definition
CREATE TABLE BOARD
(
	ID 			   BIGINT auto_increment primary key,
    TITLE		   VARCHAR(255),
    CONTENTS	   VARCHAR(255),
    REG_DATE	   TIMESTAMP,
	BOARD_TYPE_ID  BIGINT,
    USER_ID		   BIGINT,
    
    TOP_YN         BOOLEAN,
    
    constraint FK_BOARD_BOARD_TYPE_ID foreign key(BOARD_TYPE_ID) references BOARD_TYPE (ID),
    constraint FK_BOARD_USER_ID foreign key(USER_ID) references USER (ID)
);