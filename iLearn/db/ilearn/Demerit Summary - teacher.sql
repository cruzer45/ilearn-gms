SELECT CONCAT_WS(' ',`staFirstName`,`staLastName`) AS 'Staff', CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', `stuClsCode`, sum(`demerits`) AS 'Demerits'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
INNER JOIN `Staff` ON `Staff`.`staCode` = `Demerits`.`demStaCode`
WHERE `demStatus` = 'Active'
GROUP BY `demStaCode`,`demStuID`
order by CONCAT_WS(' ',`staFirstName`,`staLastName`) asc , COUNT(`demID`) desc