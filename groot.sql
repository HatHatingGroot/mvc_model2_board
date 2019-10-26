-- 테이블 생성
create table mvc_board(
bID NUMBER(4) PRIMARY KEY,
bName VARCHAR2(20),
bTitle VARCHAR2(100),
bContent VARCHAR2(2048),
bDate DATE DEFAULT SYSDATE,
bHit Number(4) DEFAULT 0,
bGroup NUMBER(4),
bStep NUMBER(4),
bIndent NUMBER(4)
);

--시퀀스 생성
create sequence mvc_board_seq;

commit;



