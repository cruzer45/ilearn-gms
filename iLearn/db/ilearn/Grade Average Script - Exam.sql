SELECT `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`) AS 'Student',`subName`, ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade'
FROM `TermGrade`
INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID`
INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID`
INNER JOIN `Subject` ON `Subject`.`subCode` = `Assments`.`assmtSubject`
WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' AND `stuID` = 100 AND `subCode` = '1AAGRIC' AND `assmtType` != 'Exam'
GROUP BY `grdStuID`, `subCode`
ORDER BY `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `subName`
