SELECT CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) as 'Name', `Demerits`.*, CONCAT_WS(' ',`Staff`.`staFirstName`,`Staff`.`staLastName`) as 'Staff'
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
INNER JOIN `Staff` ON `Demerits`.`demStaCode` = `Staff`.`staCode`