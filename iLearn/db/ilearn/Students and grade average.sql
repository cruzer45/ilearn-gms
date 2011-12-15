SELECT `grdStuID` AS 'Student ID',CONCAT_WS(', ', `stuLastName`,`stuFirstName`) AS 'Name', `clsCode` AS 'Class', `assmtSubject` as 'Subject' ,AVG(`grdPointsEarned`) AS 'Average'
FROM `TermGrade`
INNER JOIN `Student` ON `TermGrade`.`grdStuID` = `Student`.`stuID`
inner join `Assments` on `TermGrade`.`grdAssmtID` = `Assments`.`assmtID`
inner join  `Class` on `Assments`.`assmtClassID` = `Class`.`clsID`
WHERE `grdStatus` = 'Active' 
GROUP BY `assmtClassID`,`grdStuID`, `assmtSubject`
ORDER BY `clsCode`  , CONCAT_WS(', ', `stuLastName`,`stuFirstName`) asc