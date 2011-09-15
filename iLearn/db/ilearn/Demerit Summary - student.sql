SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', `stuClsCode`, COUNT(`demID`) AS 'Count'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
where `demStatus` = 'Active'
GROUP BY `demStuID`
