SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', SUM(`rolAbsent`) as 'Absents', SUM(`rolTardy`) as 'Lates'
FROM `RollCall`
INNER JOIN `Student` ON `RollCall`.`rolStuID` = `Student`.`stuID`
WHERE `rolStatus` = 'Active'
GROUP BY `rolStuID`;