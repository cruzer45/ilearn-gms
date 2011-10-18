SELECT CONCAT_WS(', ',`stuLastName`,`stuFirstName`) AS 'Name',`stuClsCode`, `assmtType`, `assmtTitle`, `assmtDate`, `grdPointsEarned`
FROM `TermGrade`
INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID`
INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID`
WHERE (`grdPointsEarned` IS NULL OR `grdPointsEarned` = '' OR `grdPointsEarned` = ' ' OR `grdPointsEarned` = 'Absent' OR `grdPointsEarned` = 'Incomplete') AND `grdStatus` = 'Active'
ORDER BY CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `assmtDate`, `assmtType`