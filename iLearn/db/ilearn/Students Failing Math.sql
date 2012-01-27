SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', `Grade`.`graClsCode`,`graSubCode`,`graFinal`
FROM `Grade`
INNER JOIN `Student` ON `Student`.`stuID` = `Grade`.`graStuID`
WHERE `graFinal` < 60 AND (`graSubCode` LIKE '%math%')
order by `graClsCode`, `graSubCode`, CONCAT_WS(' ',`stuFirstName`,`stuLastName`)