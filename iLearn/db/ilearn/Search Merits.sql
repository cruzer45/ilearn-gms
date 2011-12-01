SELECT `merID`,`merDate`,`merits`,`merRemarks`
FROM `Merits`
INNER JOIN `Student` ON `Merits`.`merStuID` = `Student`.`stuID`
INNER JOIN `Staff` ON `Merits`.`merStaID`= `Staff`.`staID`
WHERE `merStatus` = 'Active'