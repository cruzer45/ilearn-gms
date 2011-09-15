SELECT `rolDate`, CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name',`stuClsCode`,`rolAbsent`,`rolTardy`,`rolRemark`
FROM `RollCall`
INNER JOIN `Student` ON `RollCall`.`rolStuID` = `Student`.`stuID`
WHERE `rolStatus` = 'Active'
ORDER BY `rolDate` DESC;