SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', `stuClsCode`, SUM(`demerits`) AS 'Demerits'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
WHERE `demStatus` = 'Active'
GROUP BY `demStuID`