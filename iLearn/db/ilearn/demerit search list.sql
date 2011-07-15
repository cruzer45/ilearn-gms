SELECT CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Name', `Demerits`.*, CONCAT_WS(' ',`Staff`.`staFirstName`,`Staff`.`staLastName`) AS 'Staff'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
INNER JOIN `Staff` ON `Demerits`.`demStaCode` = `Staff`.`staCode`
WHERE `Demerits`.`demStatus` = 'Active' AND (CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) LIKE '%' OR `demStuID` LIKE '%' OR `demDate` LIKE '%' OR `demClsCode` LIKE '%')