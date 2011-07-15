SELECT `demID`,`demDate`,`demStaCode`,`demerits`,`demRemarks`, CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Name', CONCAT_WS(' ',`Staff`.`staFirstName`,`Staff`.`staLastName`) AS 'Staff'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
INNER JOIN `Staff` ON `Demerits`.`demStaCode` = `Staff`.`staCode`
WHERE `demStatus` = 'Active' and `demStuID` = '1'