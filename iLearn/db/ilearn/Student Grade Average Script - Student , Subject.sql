SELECT `grdStuID`, ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade'
FROM `TermGrade`
INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID`
WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' AND `grdStuID` = '101' AND `assmtSubject` = '1AAGRIC'
GROUP BY `grdStuID`