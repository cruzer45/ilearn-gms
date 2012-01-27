SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', `Grade`.`graClsCode`,count(`graID`)
FROM `Grade`
INNER JOIN `Student` ON `Student`.`stuID` = `Grade`.`graStuID`
WHERE `graFinal` < 60 
group by `graStuID` 
order by count(`graID`) desc,`graClsCode`, `graSubCode`, CONCAT_WS(' ',`stuFirstName`,`stuLastName`)
