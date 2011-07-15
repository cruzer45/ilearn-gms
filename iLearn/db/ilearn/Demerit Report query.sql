SELECT CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Name', `demStuID`,`demClsCode`, SUM(`demerits`) AS `demerits`,`demRemarks`
FROM `Demerits`
INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID`
WHERE `Demerits`.`demStatus` = 'Active'
GROUP BY `demClsCode`, `demStuID` 
ORDER BY `demClsCode`, `demStuID`