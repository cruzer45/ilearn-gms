SELECT `assmtType`,`assmtTitle`,`assmtDate`,`assmtTotalPoints`,`assmtSubject`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `grdPointsEarned`,`grdRemark`
FROM `TermGrade`
INNER JOIN `Assments` ON `Assments`.`assmtID` = `Termgrade`.grdAssmtID
INNER JOIN `Student` ON `Student`.stuID = `Termgrade`.grdStuID
WHERE `assmtSubject` LIKE '%3sphy%'

ORDER BY `assmtID`