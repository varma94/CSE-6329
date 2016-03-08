CREATE DATABASE ADVISING;

USE ADVISING;

CREATE TABLE APPOINTMENT(   ApptID INTEGER NOT NULL AUTO_INCREMENT,
    ApptDate DATE NOT NULL,
    ApptStartHour INTEGER NOT NULL,
    ApptStartMin INTEGER NOT NULL,
    ApptEndHour INTEGER NOT NULL,
    ApptEndMin INTEGER NOT NULL,
    ApptType VARCHAR(20),
    Description VARCHAR(100),
    StudentID CHAR(10) NOT NULL,
    StudentName VARCHAR(20),
    StudentMajor VARCHAR(20),
    StudentEmail VARCHAR(40),
    AdvisorName VARCHAR(20) NOT NULL,
    PRIMARY KEY (ApptID)
);

CREATE TABLE SLOT(   
    SlotID INTEGER NOT NULL AUTO_INCREMENT,
    SlotDate DATE NOT NULL,
    SlotStartHour INTEGER NOT NULL,
    SlotStartMin INTEGER NOT NULL,
    appointmentTypeID INTEGER NOT NULL,
    advisorID INTEGER NOT NULL
    PRIMARY KEY (SlotID)
);

CREATE TABLE USER( UserID INTEGER NOT NULL AUTO_INCREMENT,
    UserEmail VARCHAR(30) NOT NULL,
    UserPassword VARCHAR(20) NOT NULL,
    UserName VARCHAR(30),
    UserDepartment VARCHAR(30),
    UserRank INTEGER,
    lognum INTEGER,
    PRIMARY KEY (UserID)
);

INSERT INTO advising.`user` (`UserEmail`, `UserPassword`, `UserName`, `UserDepartment`, `UserRank`, lognum) 
	VALUES ('admin@mavs.uta.edu', '92668751', 'Admin', 'Computer Science', 1, 0),
	VALUES ('lead.advisor@mavs.uta.edu', '3317596', 'Lead Advisor', 'Computer Science', 3, 0),
	VALUES ('advisor@mavs.uta.edu', '-1131323456', 'Advisor', 'Computer Science', 0, 0),
	VALUES ('staff@mavs.uta.edu', '109757152', 'Staff', 'Computer Science', 4, 0),
	VALUES ('student@mavs.uta.edu', '-1879145925', 'Student', 'Computer Science', 2, 0);

CREATE OR REPLACE VIEW APPTFW AS
SELECT ApptID, ApptDate, ApptStartHour, ApptStartMin, ApptEndHour, ApptEndMin
From APPOINTMENT
ORDER BY ApptDate;

CREATE OR REPLACE VIEW APPTSLOT AS
SELECT SLOT.*, APPTFW.ApptID
FROM SLOT, APPTFW
WHERE(SLOT.SlotDate = APPTFW.ApptDate 
AND SLOT.SlotStartHour <> APPTFW.ApptEndHour 
AND SLOT.SlotStartHour = APPTFW.ApptStartHour 
AND SLOT.SlotStartMin >= APPTFW.ApptStartMin)

OR (SLOT.SlotDate = APPTFW.ApptDate 
AND SLOT.SlotStartHour > APPTFW.ApptStartHour 
AND SLOT.SlotStartHour < APPTFW.ApptEndHour)

OR (SLOT.SlotDate = APPTFW.ApptDate AND SLOT.SlotStartHour <> APPTFW.ApptStartHour 
AND SLOT.SlotStartHour = APPTFW.ApptEndHour AND SLOT.SlotStartMin < APPTFW.ApptEndMin)

OR (SLOT.SlotDate = APPTFW.ApptDate AND SLOT.SlotStartHour = APPTFW.ApptStartHour 
AND SLOT.SlotStartHour = APPTFW.ApptEndHour AND SLOT.SlotStartMin >= APPTFW.ApptStartMin 
AND SLOT.SlotStartMin < APPTFW.ApptEndMin);


CREATE OR REPLACE VIEW AVAILSLOT AS 
SELECT * FROM SLOT WHERE SlotID
NOT IN (SELECT SlotID FROM APPTSLOT);

CREATE TABLE USERTYPES(
    TypeID INTEGER NOT NULL AUTO_INCREMENT,
    TypeName VARCHAR(30) NOT NULL,
    privilege INTEGER DEFAULT 2,
    PRIMARY KEY (TypeID)
);

INSERT INTO USERTYPES (TypeID, TypeName, privilege) 
VALUES (1, 'Advisor', 1),
(0, 'Administrator', 0),
(2, 'Student', 2),
(3, 'Lead Advisor', 3),
(4, 'Staff', 4);

CREATE TABLE APPOINTMENTTYPES(
    ApTypeID INTEGER NOT NULL AUTO_INCREMENT,
    ApTypeName VARCHAR(30) NOT NULL,
    ApTypeLength INTEGER DEFAULT 15, 
    PRIMARY KEY (ApTypeID)
);

INSERT INTO APPOINTMENTTYPES (ApTypeID, ApTypeName) 
VALUES ('1', 'New Student'),
('2', 'Returning Student'),
('3', 'Drop Course'),
('4', 'Enroll'),
('5', 'Others');

CREATE TABLE REPORT_BUGS(name varchar(20), email varchar(30), bugs varchar(50));

CREATE TABLE FEEDBACK(name varchar(30), email varchar(50), remarks varchar(200));