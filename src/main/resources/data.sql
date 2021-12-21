INSERT INTO USER (ID, EMAIL, PASSWORD, PHONE, REG_DATE, UPDATE_DATE, USER_NAME, STATUS, LOCK_YN) 
VALUES (1, 'test0@gmail.com', '1111', '010-1111-2222', '2021-12-21 19:03:43.000000', null, '박규태', 1, 0)
	  ,(2, 'test1@gmail.com', '2222', '010-3333-4444', '2021-12-21 19:03:43.000000', null, '정혜경', 1, 0)
      ,(3, 'test2@gmail.com', '3333', '010-5555-6666', '2021-11-16 19:03:43.000000', null, '박하은', 1, 0)
      ,(4, 'test3@gmail.com', '4444', '010-7777-8888', '2021-11-16 19:03:43.000000', null, '박하영', 2, 0);

INSERT INTO NOTICE (ID, CONTENTS, DELETED_DATE, DELETED, HITS, LIKES, REG_DATE, TITLE, UPDATE_DATE, USER_ID) 
VALUES (1, '내용1', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목1', null, 1)
      ,(2, '내용2', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목2', null, 1)
      ,(3, '내용3', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목3', null, 2)
      ,(4, '내용4', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목4', null, 2)
      ,(5, '내용5', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목5', null, 2)
      ,(6, '내용6', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목6', null, 1)
      ,(7, '내용7', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목7', null, 3)
      ,(8, '내용8', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목8', null, 3)
      ,(9, '내용9', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목9', null, 1)
      ,(10, '내용10', null, false, 0, 0, '2021-11-02 16:57:42.000000', '제목10', null, 1);

INSERT INTO NOTICE_LIKE (ID, NOTICE_ID, USER_ID) 
VALUES (1, 2, 1)
	  ,(2, 3, 1);