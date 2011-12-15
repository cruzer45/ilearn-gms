SELECT COUNT(`grdStuID`) AS 'Pass'
FROM `TermGrade`
INNER JOIN `Assments` ON `TermGrade`.`grdAssmtID` = `Assments`.`assmtID`
WHERE (`grdPointsEarned` >= (`assmtTotalPoints` * .7)) AND `assmtID` = '11'