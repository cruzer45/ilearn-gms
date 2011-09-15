SELECT CONCAT_WS(' ',`staFirstName`,`staLastName`) AS 'Staff', CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', `stuClsCode`, COUNT(`demID`) AS 'Count'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
INNER JOIN `Staff` ON `Staff`.`staCode` = `Demerits`.`demStaCode`
WHERE `demStatus` = 'Active'
GROUP BY `demStaCode`,`demStuID`
