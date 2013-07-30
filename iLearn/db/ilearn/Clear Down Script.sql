TRUNCATE `Assments`;
TRUNCATE `Grade`;
TRUNCATE `Grade_Average`;
TRUNCATE `Grade_Year_Average`;
TRUNCATE `Log`;
TRUNCATE `Log_Actions`;
TRUNCATE `Merits`;
TRUNCATE `RollCall`;
TRUNCATE `TermGrade`;
DELETE
FROM Student
WHERE Student.stuID > 10;
DELETE
FROM Class
WHERE Class.clsCode NOT IN (
SELECT DISTINCT Student.stuClsCode
FROM Student);
DELETE
FROM ClassSubjects
WHERE ClassSubjects.clsCode NOT IN (
SELECT DISTINCT Class.clsCode
FROM Class);
DELETE
FROM Subject
WHERE Subject.subID NOT IN (
SELECT DISTINCT ClassSubjects.subCode
FROM ClassSubjects);
DELETE
FROM Subject_Weightings
WHERE Subject_Weightings.subID NOT IN (
SELECT DISTINCT Subject.subID
FROM Subject);
DELETE
FROM Staff
WHERE Staff.staCode NOT IN (
SELECT DISTINCT Subject.subStaffCode
FROM Subject);
DELETE
FROM User_Staff
WHERE User_Staff.staID NOT IN (
SELECT DISTINCT Staff.staID
FROM Staff);
DELETE
FROM `User`
WHERE `User`.usrID > 1 AND `User`.usrID NOT IN (
SELECT DISTINCT User_Staff.userID
FROM User_Staff);

