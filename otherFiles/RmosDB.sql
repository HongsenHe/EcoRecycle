-- script to create RMOS database
-- revised 03/07/2014

DROP DATABASE rmos;
CREATE DATABASE rmos;
USE rmos;

-- create a accepted items list
CREATE TABLE itemsList(
ItemNum mediumint(5),
ItemType varchar(30),
ItemPrice real,
PRIMARY KEY (ItemNum) );

-- create a 10 RCM list
CREATE TABLE RcmList(
RcmNum mediumint(5),
ActNum mediumint(5),
RcmLocation varchar(30),
RcmID mediumint(5),
MonthlyIssued real,
CurBalance real,
MonthlyUse real,
MonthlyWeight real,
CurrentCapacity real,
PRIMARY KEY (RcmNum) );
