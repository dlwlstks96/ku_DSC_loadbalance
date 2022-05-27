--mysql DB에 ku_DSC_test01 계정 생성 (암호로 test01 사용)
create user 'ku_DSC_test01'@'localhost' identified by 'test01';

--ticketingService DB 생성
create database ticketingService character set = utf8;

--ticketingService DB에 ku_DSC_test01 계정이 접근할 수 있도록 권한 부여
grant all privileges on ticketingService.* to 'ku_DSC_test01'@'localhost';

--ticketingService DB에 RESERVE01 테이블 생성
create table ticketingService.RESERVE01 (
    ID int auto_increment primary key,
    NAME varchar(255),
    BIRTH varchar(100),
    SEATNUMBER varchar(100),
    unique key (SEATNUMBER)
) engine=InnoDB character set = utf8;